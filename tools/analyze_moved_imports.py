#!/usr/bin/env python3
"""Report old Minecraft imports and unique 26.3 class-name matches.

Usage: python analyze_moved_imports.py minecraft-class-jar [report.txt]
Matches are hints only: classes can change meaning, signatures or rendering
architecture, so this script never rewrites source code.
"""
from collections import Counter, defaultdict
from pathlib import Path
import re, sys, zipfile

if len(sys.argv) not in (2, 3): raise SystemExit(__doc__)
jar = Path(sys.argv[1]); root = Path(__file__).resolve().parent.parent
with zipfile.ZipFile(jar) as z:
    names = {s[:-6].replace('/', '.').replace('$', '.') for s in z.namelist()
             if s.startswith('net/minecraft/') and s.endswith('.class')}
by_short = defaultdict(list)
for name in names: by_short[name.rsplit('.', 1)[-1]].append(name)
imports = Counter()
for path in (root / 'port-candidates').rglob('*.java'):
    for name in re.findall(r'^\s*(?:/\*.*?\*/\s*)?import\s+(net\.minecraft\.[\w.]+)\s*;', path.read_text(encoding='utf-8'), re.M):
        imports[name] += 1
missing = [(old, count, sorted(by_short.get(old.rsplit('.', 1)[-1], []))) for old, count in imports.items() if old not in names]
missing.sort(key=lambda row: (-row[1], row[0]))
lines = [f'Old imports: {len(imports)}; missing in 26.3: {len(missing)}; unique-name hints: {sum(len(x[2]) == 1 for x in missing)}', '']
for old, count, candidates in missing:
    outcome = candidates[0] if len(candidates) == 1 else ('NO MATCH' if not candidates else f'AMBIGUOUS ({len(candidates)})')
    lines.append(f'{count:>3}  {old} -> {outcome}')
output = '\n'.join(lines) + '\n'
if len(sys.argv) == 3: Path(sys.argv[2]).write_text(output, encoding='utf-8')
print('\n'.join(lines[:32]))
