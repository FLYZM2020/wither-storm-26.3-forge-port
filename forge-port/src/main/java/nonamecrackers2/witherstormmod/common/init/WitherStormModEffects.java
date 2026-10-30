package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.potion.WitherSicknessEffect;

public final class WitherStormModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, WitherStormMod.MOD_ID);
    public static final RegistryObject<MobEffect> WITHER_SICKNESS = EFFECTS.register("wither_sickness", WitherSicknessEffect::new);
    private WitherStormModEffects() {}
}
