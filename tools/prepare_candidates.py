#!/usr/bin/env python3
"""Stage fresh Java migration candidates without overwriting active port-candidates."""
import json
from pathlib import Path
import re
import shutil

root = Path(__file__).resolve().parent.parent
reference = root / 'reference' / 'nonamecrackers2' / 'witherstormmod'
target = root / 'prepared-candidates' / 'nonamecrackers2' / 'witherstormmod'
names = json.loads((root / 'tools' / 'srg-1.20.1.json').read_text(encoding='utf-8'))
pattern = re.compile(r'(?P<protected>//[^\n]*|/\*[\s\S]*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\')|(?P<identifier>\b[mf]_\d+_\b)')
unmapped = set()
def replace(match):
    identifier = match.group('identifier')
    if identifier is None:
        return match.group()
    if identifier not in names:
        unmapped.add(identifier)
    return names.get(identifier, identifier)
recovered = root / 'recovered-source' / 'nonamecrackers2' / 'witherstormmod'
if target.exists():
    shutil.rmtree(target)
count = 0
for src in reference.rglob('*.java'):
    rel = src.relative_to(reference)
    # Nested classes are emitted inside the top-level source by the decompiler.
    if '$' in src.name:
        continue
    if (recovered / rel).exists():
        src = recovered / rel
    dst = target / rel
    dst.parent.mkdir(parents=True, exist_ok=True)
    dst.write_text(pattern.sub(replace, src.read_text(encoding='utf-8')), encoding='utf-8')
    count += 1
(root / 'prepared-candidates' / 'UNMAPPED.txt').write_text('\n'.join(sorted(unmapped)) + '\n', encoding='utf-8')
print(f'{count} staged files; {len(unmapped)} distinct unmapped member identifiers')
