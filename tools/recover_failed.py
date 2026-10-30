#!/usr/bin/env python3
"""Recover failed community decompilations one top-level class at a time.

Requires /tmp/wither-classfiles, /tmp/vineflower-1.12.0.jar, and Java 25.
Output is migration material, not code ready for 26.3.
"""
from pathlib import Path
import re
import shutil
import subprocess
import tempfile

root = Path(__file__).resolve().parent.parent
reference = root / 'reference' / 'nonamecrackers2' / 'witherstormmod'
classes = Path('/tmp/wither-classfiles')
out = root / 'recovered-source' / 'nonamecrackers2' / 'witherstormmod'
marker = re.compile(r"could not be decompiled|Decompilation failed|This method could not|Byte code:")
files = set()
for path in reference.rglob('*.java'):
    if marker.search(path.read_text(encoding='utf-8')):
        rel = path.relative_to(reference)
        files.add(rel.with_name(rel.name.split('$')[0]))
log = root / 'candidate-compile.log'
if log.exists():
    for match in re.finditer(r'^(.+\.java):\d+: error:', log.read_text(encoding='utf-8'), re.M):
        try:
            rel = Path(match.group(1)).relative_to(root / 'port-candidates' / 'nonamecrackers2' / 'witherstormmod')
        except ValueError:
            continue
        if not (out / rel).exists():
            files.add(rel)
for rel in sorted(files):
    if (out / rel).exists():
        continue
    class_rel = Path('nonamecrackers2/witherstormmod') / rel.with_suffix('.class')
    original = classes / class_rel
    if not original.exists():
        print('missing', class_rel, flush=True)
        continue
    with tempfile.TemporaryDirectory(prefix='storm-recover-') as temporary:
        temp = Path(temporary)
        for part in original.parent.glob(original.stem + '$*.class'):
            shutil.copy2(part, temp / part.name)
        shutil.copy2(original, temp / original.name)
        target = out / rel.parent
        target.mkdir(parents=True, exist_ok=True)
        proc = subprocess.run(['/tmp/wither-jdk25/bin/java', '-Xmx900m', '-jar', '/tmp/vineflower-1.12.0.jar', '--thread-count=1', '--log-level=error', str(temp), str(target)], timeout=180)
        print(f'{rel}: exit={proc.returncode}', flush=True)
