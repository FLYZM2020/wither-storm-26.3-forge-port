package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.WitherStormMod;

public final class WitherStormModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, WitherStormMod.MOD_ID);
    public static final RegistryObject<SoundEvent> COMMAND_BLOCK_ACTIVATES = SOUNDS.register("command_block_activates", () ->
            SoundEvent.createVariableRangeEvent(WitherStormMod.id("command_block_activates")));
    public static final RegistryObject<SoundEvent> WITHER_STORM_EVOLVES = SOUNDS.register("wither_storm_evolves", () ->
            SoundEvent.createVariableRangeEvent(WitherStormMod.id("wither_storm_evolves")));
    public static final RegistryObject<SoundEvent> SUPER_TNT_FUSE = SOUNDS.register("super_tnt_fuse", () ->
            SoundEvent.createVariableRangeEvent(WitherStormMod.id("super_tnt_fuse")));
    public static final RegistryObject<SoundEvent> SYMBIONT_PULL = SOUNDS.register("withered_symbiont_pull", () ->
            SoundEvent.createVariableRangeEvent(WitherStormMod.id("withered_symbiont_pull")));
    public static final RegistryObject<SoundEvent> SYMBIONT_PREPARE = SOUNDS.register("withered_symbiont_prepare_spell", () ->
            SoundEvent.createVariableRangeEvent(WitherStormMod.id("withered_symbiont_prepare_spell")));
    public static final RegistryObject<SoundEvent> SYMBIONT_CAST = SOUNDS.register("withered_symbiont_cast_spell", () ->
            SoundEvent.createVariableRangeEvent(WitherStormMod.id("withered_symbiont_cast_spell")));
    private WitherStormModSounds() {}
}
