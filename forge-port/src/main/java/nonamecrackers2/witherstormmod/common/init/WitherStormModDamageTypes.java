package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.WitherStormMod;

/** Damage types used by early storm head contact. */
public final class WitherStormModDamageTypes {
    public static final ResourceKey<DamageType> WITHER_SICKNESS = key("wither_sickness");
    public static final ResourceKey<DamageType> SUPER_TNT_EXPLOSION = key("super_tnt_explosion");
    public static final ResourceKey<DamageType> WITHER_STORM_ATTACK = key("wither_storm_attack");
    public static final ResourceKey<DamageType> WITHER_STORM_ATTACK_MOB = key("wither_storm_attack_mob");

    private WitherStormModDamageTypes() {}

    private static ResourceKey<DamageType> key(String path) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, WitherStormMod.id(path));
    }

    public static DamageSource source(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }

    public static DamageSource source(Level level, ResourceKey<DamageType> key, Entity attacker) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key), attacker);
    }
}
