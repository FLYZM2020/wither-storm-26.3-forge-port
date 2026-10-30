#!/usr/bin/env python3
"""Migrate straightforward 1.20 resource identifiers and listener imports.

Edits candidate files only. It does not adapt rendering or event registration.
Constructor calls with unsupported argument syntax are reported for review.
"""
import re
from pathlib import Path

root = Path(__file__).resolve().parent.parent / 'port-candidates'
protected = r'//[^\n]*|/\*[\s\S]*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\''
lexer = re.compile(rf'(?P<protected>{protected})|(?P<new>\bnew\s+ResourceLocation\s*\()|(?P<type>\bResourceLocation\b)')
parts = re.compile(rf'(?P<protected>{protected})|(?P<delimiter>[()\[\]{{}},])')
constructors = [0, 0]
ambiguous = []

def constructor_arity(text, start):
    stack = ['(']
    commas = 0
    pairs = {')': '(', ']': '[', '}': '{'}
    for match in parts.finditer(text, start):
        value = match.group('delimiter')
        if value is None:
            continue
        if value in '([{':
            stack.append(value)
        elif value in ')]}':
            if not stack or stack.pop() != pairs[value]:
                return None
            if not stack:
                return commas + 1
        elif value == ',' and len(stack) == 1:
            commas += 1
    return None

changed_files = 0
for path in root.rglob('*.java'):
    source = path.read_text(encoding='utf-8')
    updated = source.replace('net.minecraftforge.eventbus.api.SubscribeEvent', 'net.minecraftforge.eventbus.api.listener.SubscribeEvent')
    def replace(match):
        if match.group('protected') is not None:
            return match.group()
        if match.group('new') is not None:
            arity = constructor_arity(updated, match.end())
            if arity in (1, 2):
                constructors[arity - 1] += 1
                return 'Identifier.' + ('parse' if arity == 1 else 'fromNamespaceAndPath') + '('
            ambiguous.append(f'{path}:{updated.count(chr(10), 0, match.start()) + 1}')
            return match.group()
        return 'Identifier'
    result = lexer.sub(replace, updated)
    if result != source:
        path.write_text(result, encoding='utf-8')
        changed_files += 1
print(f'{changed_files} files updated; {constructors[0]} one-argument and {constructors[1]} two-argument constructors migrated')
if ambiguous:
    print('Unresolved constructor calls:\n' + '\n'.join(ambiguous))
