/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ 
/*    */ public class WitherStormModSoundEvents
/*    */ {
/* 12 */   public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "witherstormmod");
/*    */   
/* 14 */   public static final RegistryObject<SoundEvent> WITHER_STORM_ROAR = createSoundEvent("wither_storm_roar");
/* 15 */   public static final RegistryObject<SoundEvent> WITHER_STORM_EVOLVES = createSoundEvent("wither_storm_evolves");
/* 16 */   public static final RegistryObject<SoundEvent> WITHER_STORM_CLOSE_LOOP = createSoundEvent("wither_storm_close_loop");
/* 17 */   public static final RegistryObject<SoundEvent> WITHER_STORM_DISTANT_LOOP = createSoundEvent("wither_storm_distant_loop");
/* 18 */   public static final RegistryObject<SoundEvent> WITHER_STORM_FAR_LOOP = createSoundEvent("wither_storm_far_loop");
/* 19 */   public static final RegistryObject<SoundEvent> WITHER_STORM_GROWL = createSoundEvent("wither_storm_ambient");
/* 20 */   public static final RegistryObject<SoundEvent> WITHER_STORM_SPLITS = createSoundEvent("wither_storm_splits");
/* 21 */   public static final RegistryObject<SoundEvent> WITHER_STORM_REACTIVATES = createSoundEvent("wither_storm_reactivates");
/* 22 */   public static final RegistryObject<SoundEvent> WITHER_STORM_BOSS_THEME = createSoundEvent("wither_storm_boss_theme");
/* 23 */   public static final RegistryObject<SoundEvent> WITHER_STORM_DEATH = createSoundEvent("wither_storm_death");
/* 24 */   public static final RegistryObject<SoundEvent> WITHER_STORM_HURT = createSoundEvent("wither_storm_hurt");
/* 25 */   public static final RegistryObject<SoundEvent> WITHER_STORM_BITE = createSoundEvent("wither_storm_bite");
/* 26 */   public static final RegistryObject<SoundEvent> WITHER_STORM_TRACTOR_BEAM = createSoundEvent("wither_storm_tractor_beam");
/* 27 */   public static final RegistryObject<SoundEvent> WITHER_STORM_LOOP = createSoundEvent("wither_storm_loop");
/* 28 */   public static final RegistryObject<SoundEvent> WITHER_STORM_SHOOT = createSoundEvent("wither_storm_shoot");
/* 29 */   public static final RegistryObject<SoundEvent> WITHER_STORM_THUMP = createSoundEvent("wither_storm_thump");
/* 30 */   public static final RegistryObject<SoundEvent> WITHER_STORM_TREMBLE = createSoundEvent("wither_storm_tremble");
/* 31 */   public static final RegistryObject<SoundEvent> SUPER_TNT_PRIMED = createSoundEvent("super_tnt_fuse");
/* 32 */   public static final RegistryObject<SoundEvent> FORMIDIBOMB_EXPLOSION = createSoundEvent("formidibomb_explosion");
/* 33 */   public static final RegistryObject<SoundEvent> TREMBLE = createSoundEvent("tremble");
/* 34 */   public static final RegistryObject<SoundEvent> FORMIDIBOMB_PULSE_LOOP = createSoundEvent("formidibomb_pulse_loop");
/* 35 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_PULSE_LOOP = createSoundEvent("command_block_pulse_loop");
/* 36 */   public static final RegistryObject<SoundEvent> RIB_BONE_CRACK = createSoundEvent("rib_bone_crack");
/* 37 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_ACTIVATES = createSoundEvent("command_block_activates");
/* 38 */   public static final RegistryObject<SoundEvent> WITHER_STORM_REVIVAL_THEME = createSoundEvent("wither_storm_reviving_theme");
/* 39 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_CAST_SPELL = createSoundEvent("withered_symbiont_cast_spell");
/* 40 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_SUMMON = createSoundEvent("withered_symbiont_summon");
/* 41 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_PREPARE_SPELL = createSoundEvent("withered_symbiont_prepare_spell");
/* 42 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_DEATH = createSoundEvent("withered_symbiont_death");
/* 43 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_HURT = createSoundEvent("withered_symbiont_hurt");
/* 44 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_AMBIENT = createSoundEvent("withered_symbiont_ambient");
/* 45 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_STEP = createSoundEvent("withered_symbiont_step");
/* 46 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_PULL = createSoundEvent("withered_symbiont_pull");
/* 47 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_LAUNCH_MOB = createSoundEvent("withered_symbiont_launch_mob");
/* 48 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_POWER_DOWN = createSoundEvent("withered_symbiont_power_down");
/* 49 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_HEART_BEAT = createSoundEvent("withered_symbiont_heart_beat");
/* 50 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_THEME = createSoundEvent("withered_symbiont_theme");
/* 51 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_INTENSE_THEME = createSoundEvent("withered_symbiont_intense_theme");
/* 52 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_SUMMON = createSoundEvent("command_block_summon");
/* 53 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_SPAWN = createSoundEvent("withered_symbiont_spawn");
/* 54 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_HIT = createSoundEvent("command_block_hit");
/* 55 */   public static final RegistryObject<SoundEvent> BOWELS_LOOP = createSoundEvent("bowels_loop");
/* 56 */   public static final RegistryObject<SoundEvent> BOWELS_MOOD = createSoundEvent("bowels_mood");
/* 57 */   public static final RegistryObject<SoundEvent> BOWELS_TRANSPORT = createSoundEvent("bowels_transport");
/* 58 */   public static final RegistryObject<SoundEvent> BOWELS_TREMBLE = createSoundEvent("bowels_tremble");
/* 59 */   public static final RegistryObject<SoundEvent> WHOOSH = createSoundEvent("whoosh");
/* 60 */   public static final RegistryObject<SoundEvent> LOUD_TREMBLE = createSoundEvent("loud_tremble");
/* 61 */   public static final RegistryObject<SoundEvent> BOWELS_LOUD_HURT = createSoundEvent("bowels_loud_hurt");
/* 62 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_DAMAGE = createSoundEvent("command_block_damage");
/* 63 */   public static final RegistryObject<SoundEvent> WITHER_STORM_FINAL_BOSS_THEME = createSoundEvent("wither_storm_final_boss_theme");
/* 64 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_POWER_DOWN = createSoundEvent("command_block_power_down");
/* 65 */   public static final RegistryObject<SoundEvent> WITHERED_SYMBIONT_NORMAL_DEATH = createSoundEvent("withered_symbiont_normal_death");
/* 66 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_DEATH = createSoundEvent("command_block_death");
/* 67 */   public static final RegistryObject<SoundEvent> FORMIDIBOMB_EXPLOSION_QUIET = createSoundEvent("formidibomb_explosion_quiet");
/* 68 */   public static final RegistryObject<SoundEvent> WITHER_STORM_TRACTOR_BEAM_ACTIVATES = createSoundEvent("wither_storm_tractor_beam_activate");
/* 69 */   public static final RegistryObject<SoundEvent> AMULET_BIND = createSoundEvent("amulet_bind");
/* 70 */   public static final RegistryObject<SoundEvent> AMULET_UNBIND = createSoundEvent("amulet_unbind");
/* 71 */   public static final RegistryObject<SoundEvent> AMULET_SWAPS = createSoundEvent("amulet_swaps");
/* 72 */   public static final RegistryObject<SoundEvent> BLOCK_CLUSTER_SHAKE = createSoundEvent("block_cluster_shake");
/* 73 */   public static final RegistryObject<SoundEvent> WITHERED_BEACON_ACTIVATE = createSoundEvent("withered_beacon_activate");
/* 74 */   public static final RegistryObject<SoundEvent> WITHERED_BEACON_DEACTIVATE = createSoundEvent("withered_beacon_deactivate");
/* 75 */   public static final RegistryObject<SoundEvent> WITHERED_BEACON_AMBIENT = createSoundEvent("withered_beacon_ambient");
/* 76 */   public static final RegistryObject<SoundEvent> WITHERED_BEACON_POWER_UP = createSoundEvent("withered_beacon_power_up");
/* 77 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_BUILD = createSoundEvent("command_block_build");
/* 78 */   public static final RegistryObject<SoundEvent> WITHER_STORM_FORMIDIBOMB_THEME = createSoundEvent("wither_storm_formidibomb_theme");
/* 79 */   public static final RegistryObject<SoundEvent> WITHER_STORM_BOWELS_EXPOSED_THEME = createSoundEvent("wither_storm_bowels_exposed_theme");
/* 80 */   public static final RegistryObject<SoundEvent> FLAMING_SKULL_IMPACT = createSoundEvent("flaming_skull_impact");
/* 81 */   public static final RegistryObject<SoundEvent> EARTH_RUMBLE = createSoundEvent("earth_rumble");
/* 82 */   public static final RegistryObject<SoundEvent> CAVE_WIND = createSoundEvent("cave_wind");
/* 83 */   public static final RegistryObject<SoundEvent> TENTACLE_SPIKE_STAB = createSoundEvent("tentacle_spike_stab");
/* 84 */   public static final RegistryObject<SoundEvent> FORMIDI_BLADE_CHARGING = createSoundEvent("formidi_blade_charging");
/* 85 */   public static final RegistryObject<SoundEvent> FORMIDI_BLADE_DECHARGE = createSoundEvent("formidi_blade_decharge");
/* 86 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_CRACKS = createSoundEvent("command_block_cracks");
/* 87 */   public static final RegistryObject<SoundEvent> COMMAND_BLOCK_DESTRUCT = createSoundEvent("command_block_destruct");
/* 88 */   public static final RegistryObject<SoundEvent> WITHERED_PHLEGM_BLOCK_OPEN = createSoundEvent("withered_phlegm_block_open");
/* 89 */   public static final RegistryObject<SoundEvent> WITHERED_PHLEGM_BLOCK_CLOSE = createSoundEvent("withered_phlegm_block_close");
/* 90 */   public static final RegistryObject<SoundEvent> MOB_INFECTED = createSoundEvent("mob_infected");
/* 91 */   public static final RegistryObject<SoundEvent> MOB_CURED = createSoundEvent("mob_cured");
/*    */ 
/*    */   
/*    */   private static RegistryObject<SoundEvent> createSoundEvent(String name) {
/* 95 */     return SOUND_EVENTS.register(name, () -> SoundEvent.m_262824_(new ResourceLocation("witherstormmod", name)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModSoundEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */