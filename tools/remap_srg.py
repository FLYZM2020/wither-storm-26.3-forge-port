#!/usr/bin/env python3
"""Replace mapped SRG member identifiers in recovered Java sources.

Run: python remap_srg.py srg-map.json input.java output.java
Unmapped identifiers are retained for manual review. This does not port APIs.
"""
import json
from pathlib import Path
import re
import sys

if len(sys.argv) != 4:
    raise SystemExit(__doc__)
names = json.loads(Path(sys.argv[1]).read_text(encoding='utf-8'))
source = Path(sys.argv[2]).read_text(encoding='utf-8')
pattern = re.compile(r'(?P<protected>//[^\n]*|/\*[\s\S]*?\*/|\"(?:\\.|[^\"\\])*\"|\'(?:\\.|[^\'\\])*\')|(?P<identifier>\b[mf]_\d+_\b)')
missing = set()
def replace(match):
    identifier = match.group('identifier')
    if identifier is None:
        return match.group()
    if identifier not in names:
        missing.add(identifier)
    return names.get(identifier, identifier)
result = pattern.sub(replace, source)
Path(sys.argv[3]).write_text(result, encoding='utf-8')
print(f'{len(missing)} unmapped identifiers; API changes still require review')
