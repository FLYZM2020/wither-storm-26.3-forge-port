#!/usr/bin/env python3
"""Migrate selected Minecraft 26.3 package moves in port-candidates.

These changes only fix import locations; class behaviour, APIs, and render
states still need to be migrated before the affected classes can compile.
"""
from pathlib import Path
import re

root = Path(__file__).resolve().parent.parent / 'port-candidates'
moves = {
    'net.minecraft.Util': 'net.minecraft.util.Util',
    'net.minecraft.client.renderer.RenderType': 'net.minecraft.client.renderer.rendertype.RenderType',
    'net.minecraft.world.entity.animal.AbstractGolem': 'net.minecraft.world.entity.animal.golem.AbstractGolem',
    'net.minecraft.world.entity.animal.Bee': 'net.minecraft.world.entity.animal.bee.Bee',
    'net.minecraft.world.entity.animal.Cat': 'net.minecraft.world.entity.animal.feline.Cat',
    'net.minecraft.world.entity.animal.IronGolem': 'net.minecraft.world.entity.animal.golem.IronGolem',
    'net.minecraft.world.entity.animal.Parrot': 'net.minecraft.world.entity.animal.parrot.Parrot',
    'net.minecraft.world.entity.animal.Pig': 'net.minecraft.world.entity.animal.pig.Pig',
    'net.minecraft.world.entity.animal.Wolf': 'net.minecraft.world.entity.animal.wolf.Wolf',
    'net.minecraft.world.entity.monster.AbstractSkeleton': 'net.minecraft.world.entity.monster.skeleton.AbstractSkeleton',
    'net.minecraft.world.entity.monster.Pillager': 'net.minecraft.world.entity.monster.illager.Pillager',
    'net.minecraft.world.entity.monster.Slime': 'net.minecraft.world.entity.monster.cubemob.Slime',
    'net.minecraft.world.entity.monster.Spider': 'net.minecraft.world.entity.monster.spider.Spider',
    'net.minecraft.world.entity.monster.Vindicator': 'net.minecraft.world.entity.monster.illager.Vindicator',
    'net.minecraft.world.entity.monster.WitherSkeleton': 'net.minecraft.world.entity.monster.skeleton.WitherSkeleton',
    'net.minecraft.world.entity.monster.Zombie': 'net.minecraft.world.entity.monster.zombie.Zombie',
    'net.minecraft.world.entity.monster.ZombieVillager': 'net.minecraft.world.entity.monster.zombie.ZombieVillager',
    'net.minecraft.world.entity.npc.Villager': 'net.minecraft.world.entity.npc.villager.Villager',
    'net.minecraft.world.entity.projectile.AbstractArrow': 'net.minecraft.world.entity.projectile.arrow.AbstractArrow',
    'net.minecraft.world.entity.projectile.AbstractHurtingProjectile': 'net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile',
    'net.minecraft.world.entity.projectile.Arrow': 'net.minecraft.world.entity.projectile.arrow.Arrow',
    'net.minecraft.world.entity.projectile.DragonFireball': 'net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball',
    'net.minecraft.world.entity.projectile.Snowball': 'net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball',
    'net.minecraft.world.entity.projectile.ThrownEnderpearl': 'net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl',
    'net.minecraft.world.entity.projectile.ThrownTrident': 'net.minecraft.world.entity.projectile.arrow.ThrownTrident',
    'net.minecraft.world.entity.projectile.WitherSkull': 'net.minecraft.world.entity.projectile.hurtingprojectile.WitherSkull',
    'net.minecraft.world.entity.vehicle.AbstractMinecart': 'net.minecraft.world.entity.vehicle.minecart.AbstractMinecart',
    'net.minecraft.world.entity.vehicle.Boat': 'net.minecraft.world.entity.vehicle.boat.Boat',
    'net.minecraft.world.level.chunk.ChunkStatus': 'net.minecraft.world.level.chunk.status.ChunkStatus',
}
pattern = re.compile(r'(?m)^(\s*(?:/\*.*?\*/\s*)?import\s+)(net\.minecraft\.[\w.]+)(\s*;)')
files = count = 0
for path in root.rglob('*.java'):
    before = path.read_text(encoding='utf-8')
    def replace(match):
        global count
        new = moves.get(match.group(2))
        if new is None: return match.group()
        count += 1
        return match.group(1) + new + match.group(3)
    after = pattern.sub(replace, before)
    if before != after:
        path.write_text(after, encoding='utf-8')
        files += 1
print(f'{count} import statements migrated in {files} candidate files; APIs still need adaptation')
