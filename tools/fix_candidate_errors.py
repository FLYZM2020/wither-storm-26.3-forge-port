#!/usr/bin/env python3
"""按 javac 诊断定位到具体行，对旧版候选源码做机械性的 26.3 API 适配。
用法: python3 tools/fix_candidate_errors.py candidate-compile.log [classes.txt]
classes.txt 为 26.3 依赖中全部类名（每行一个），用于修正已迁移包的唯一同名类 import。
只改报错所在行，避免误伤；每类替换都与 26.3 API 语义等价（默认值与 1.20.1 旧行为一致）。"""
import re, sys, collections

ERR = re.compile(r'^(/.*?port-candidates/.*?\.java):(\d+): error: (.*)$')

def find_call_end(s, start):
    """s[start] == '('; return index of matching ')'."""
    depth = 0
    i = start
    in_str = None
    while i < len(s):
        c = s[i]
        if in_str:
            if c == '\\': i += 1
            elif c == in_str: in_str = None
        elif c in '"\'': in_str = c
        elif c == '(': depth += 1
        elif c == ')':
            depth -= 1
            if depth == 0: return i
        i += 1
    return -1

def split_args(a):
    out, depth, cur, in_str = [], 0, '', None
    for c in a:
        if in_str:
            cur += c
            if c == in_str: in_str = None
            continue
        if c in '"\'': in_str = c
        if c in '([{<': depth += 1
        if c in ')]}>': depth -= 1
        if c == ',' and depth == 0: out.append(cur); cur = ''
        else: cur += c
    out.append(cur)
    return [x.strip() for x in out]

def rewrite_calls(line, name, fn):
    """Rewrite every `.name(args)` on the line using fn(args)->replacement text (incl. leading '.') or None."""
    res, i = '', 0
    pat = re.compile(r'\.' + re.escape(name) + r'\(')
    while True:
        m = pat.search(line, i)
        if not m:
            return res + line[i:]
        end = find_call_end(line, m.end() - 1)
        if end < 0:
            return res + line[i:]
        args = split_args(line[m.end():end])
        rep = fn(args)
        if rep is None:
            res += line[i:end + 1]
        else:
            res += line[i:m.start()] + rep
        i = end + 1

OPT_DEFAULTS = {
    'Integer': ('getInt', 'getIntOr', '0'),
    'Boolean': ('getBoolean', 'getBooleanOr', 'false'),
    'Float': ('getFloat', 'getFloatOr', '0.0F'),
    'Double': ('getDouble', 'getDoubleOr', '0.0D'),
    'Long': ('getLong', 'getLongOr', '0L'),
    'String': ('getString', 'getStringOr', '""'),
    'Byte': ('getByte', 'getByteOr', '(byte)0'),
    'Short': ('getShort', 'getShortOr', '(short)0'),
}

def fix_line(line, msg):
    m = re.match(r'incompatible types: Optional<(\w+)> cannot be converted to', msg)
    if m:
        t = m.group(1)
        if t in OPT_DEFAULTS:
            old, new, d = OPT_DEFAULTS[t]
            return rewrite_calls(line, old, lambda a: f'.{new}({a[0]}, {d})' if len(a) == 1 and a[0] else None)
        if t == 'CompoundTag':
            return rewrite_calls(line, 'getCompound', lambda a: f'.getCompoundOrEmpty({a[0]})' if len(a) == 1 else None)
        if t == 'ListTag':
            return rewrite_calls(line, 'getList', lambda a: f'.getListOrEmpty({a[0]})' if len(a) == 2 else None)
    if 'isClientSide has private access' in msg:
        return re.sub(r'\.isClientSide\b(?!\s*\()', '.isClientSide()', line)
    if 'random has protected access in Level' in msg:
        return re.sub(r'\.random\b(?!\s*\()', '.getRandom()', line)
    if re.match(r'[xz] has private access in ChunkPos', msg):
        v = msg[0]
        return re.sub(r'\.' + v + r'\b(?!\s*\()', '.' + v + '()', line)
    if msg.startswith('method getList in class CompoundTag'):
        return rewrite_calls(line, 'getList', lambda a: f'.getListOrEmpty({a[0]})' if len(a) == 2 else None)
    if msg.startswith('method contains in class CompoundTag'):
        return rewrite_calls(line, 'contains', lambda a: f'.contains({a[0]})' if len(a) == 2 else None)
    im = re.search(r'import\s+(static\s+)?([\w.]+)\.(\w+)\s*;', line)
    if im and not im.group(1) and im.group(2).startswith(('net.minecraft', 'com.mojang')) and ('does not exist' in msg or msg.startswith('cannot find symbol')):
        cands = [c for c in MOVED.get(im.group(3), []) if '.internal.' not in c]
        if len(cands) == 1:
            return line[:im.start(2)] + cands[0][:-(len(im.group(3)) + 1)] + line[im.end(2):]
    if msg.startswith('no suitable method found for mulPose(Quaternionf)'):
        return line.replace('.mulPose(', '.rotate(')
    if msg.startswith('cannot find symbol'):
        if '.getProfiler()' in line:
            return re.sub(r'(?:\b\w+(?:\(\))?\.)+getProfiler\(\)', 'net.minecraft.util.profiling.Profiler.get()', line)
        if '.putUUID(' in line:
            line = rewrite_calls(line, 'putUUID', lambda a: f'.store({a[0]}, net.minecraft.core.UUIDUtil.CODEC, {a[1]})' if len(a) == 2 else None)
        if '.getUUID(' in line:
            line = rewrite_calls(line, 'getUUID', lambda a: f'.read({a[0]}, net.minecraft.core.UUIDUtil.CODEC).orElse(null)' if len(a) == 1 and a[0] else None)
        if '.hasUUID(' in line:
            line = rewrite_calls(line, 'hasUUID', lambda a: f'.contains({a[0]})' if len(a) == 1 else None)
    return line

MOVED = collections.defaultdict(list)
def load_classes(path):
    for c in open(path):
        c = c.strip()
        if c.startswith(('net.minecraft.', 'net.minecraftforge.', 'com.mojang.')):
            MOVED[c.rsplit('.', 1)[1]].append(c)

def main(log):
    by_file = collections.defaultdict(list)
    seen = set()
    for l in open(log, encoding='utf-8', errors='replace'):
        m = ERR.match(l.rstrip('\n'))
        if m and m.groups() not in seen:
            seen.add(m.groups())
            by_file[m.group(1)].append((int(m.group(2)), m.group(3)))
    changed = 0
    for f, errs in by_file.items():
        lines = open(f, encoding='utf-8').read().split('\n')
        orig = list(lines)
        for ln, msg in errs:
            lines[ln - 1] = fix_line(lines[ln - 1], msg)
        changed += sum(a != b for a, b in zip(orig, lines))
        if lines != orig:
            open(f, 'w', encoding='utf-8').write('\n'.join(lines))
    print('changed lines:', changed)

if __name__ == '__main__':
    if len(sys.argv) > 2: load_classes(sys.argv[2])
    main(sys.argv[1])
