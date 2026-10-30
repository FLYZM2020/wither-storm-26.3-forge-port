#!/usr/bin/env python3
"""Generate an SRG-to-official name dictionary from 1.20.1 mappings.

Run with: python build_srg_map.py joined.tsrg client.txt server.txt output.json
Only unambiguous entries are emitted; overload descriptors are matched before
names are emitted. This is a migration aid, not a source compatibility fix.
"""
import collections
import json
import re
import sys

if len(sys.argv) != 5:
    raise SystemExit(__doc__)
tsrg_path, *mapping_paths, output = sys.argv[1:]
texts = [open(path, encoding='utf-8').read().splitlines() for path in mapping_paths]
classes = {}
for lines in texts:
    for line in lines:
        if not line.startswith((' ', '#')) and line.endswith(':') and ' -> ' in line:
            official, obfuscated = line[:-1].split(' -> ')
            classes[official] = obfuscated
primitives = {'void':'V', 'boolean':'Z', 'byte':'B', 'char':'C', 'short':'S', 'int':'I', 'long':'J', 'float':'F', 'double':'D'}
def desc(typ):
    dimensions = 0
    while typ.endswith('[]'):
        typ = typ[:-2]
        dimensions += 1
    return '[' * dimensions + (primitives[typ] if typ in primitives else 'L' + classes.get(typ, typ.replace('.', '/')) + ';')

members = {}
for lines in texts:
    owner = None
    for line in lines:
        if not line.startswith((' ', '#')) and line.endswith(':') and ' -> ' in line:
            owner = line[:-1].split(' -> ')[1]
        elif owner and line.startswith('    ') and ' -> ' in line and not line.lstrip().startswith('#'):
            left, obf = line.strip().rsplit(' -> ', 1)
            left = re.sub(r'^\d+:\d+:', '', left)
            left = re.sub(r':\d+:\d+$', '', left)
            if '(' in left:
                match = re.fullmatch(r'(\S+)\s+([^\s(]+)\((.*)\)', left)
                if not match:
                    continue
                return_type, name, args = match.groups()
                descriptor = '(' + ''.join(desc(t) for t in args.split(',') if t) + ')' + desc(return_type)
                members[(owner, obf, descriptor)] = name
            else:
                parts = left.split()
                if len(parts) == 2:
                    members[(owner, obf, None)] = parts[1]

candidates = collections.defaultdict(set)
owner = None
with open(tsrg_path, encoding='utf-8') as f:
    for line in f:
        if not line.startswith(('\t', 'tsrg2 ')):
            owner = line.split()[0]
        elif line.startswith('\t') and not line.startswith('\t\t'):
            parts = line.split()
            if len(parts) >= 3:
                if parts[1].startswith('(') and len(parts) >= 4:
                    obf, descriptor, srg = parts[:3]
                else:
                    obf, srg, descriptor = parts[0], parts[1], None
                name = members.get((owner, obf, descriptor))
                if name and re.fullmatch(r'[mf]_\d+_', srg):
                    candidates[srg].add(name)
resolved = {key: next(iter(value)) for key, value in candidates.items() if len(value) == 1}
with open(output, 'w', encoding='utf-8') as f:
    json.dump(resolved, f, indent=2, sort_keys=True)
print(f'{len(resolved)} unambiguous member names; {sum(len(v)>1 for v in candidates.values())} conflicts omitted')
