package nonamecrackers2.witherstormmod.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

/** Per-world settings ported from the original server configuration. */
public final class StormConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.DoubleValue EVOLUTION_SPEED = BUILDER
            .comment("Growth duration multiplier; higher values slow evolution.")
            .defineInRange("evolution.evolutionAttributeModifier", 1.0D, 0.01D, 32.0D);
    public static final ForgeConfigSpec.DoubleValue TRACTOR_PULL_SPEED = BUILDER
            .comment("Tractor beam pull speed; the early beam uses temporary vanilla target selection.")
            .defineInRange("witherStorm.tractorPullSpeedModifier", 0.2D, 0.1D, 1.0D);
    public static final ForgeConfigSpec.BooleanValue TEMPORARY_CHUNK_LOADING = BUILDER
            .comment("Keep the storm's nearby chunks active while it is running; tickets expire after it stops.")
            .define("performance.temporaryChunkLoading", true);
    public static final ForgeConfigSpec.IntValue TEMPORARY_CHUNK_RADIUS = BUILDER
            .comment("Temporary ticket radius; this does not restore a storm when a server restarts.")
            .defineInRange("performance.temporaryChunkRadius", 2, 0, 4);
    public static final ForgeConfigSpec.BooleanValue SICKNESS_ENABLED = BUILDER
            .define("wither_sickness.witherSicknessEnabled", true);
    public static final ForgeConfigSpec.IntValue REQUIRED_CONTACTS = BUILDER
            .comment("Number of prior storm target acquisitions before another acquisition starts infection.")
            .defineInRange("wither_sickness.requiredContacts", 6, 1, 40);
    public static final ForgeConfigSpec.BooleanValue INCREASE_SICKNESS_AMPLIFIER = BUILDER
            .comment("Reinfection increases sickness strength until it decays away from the storm.")
            .define("wither_sickness.increaseAmplifier", true);
    public static final ForgeConfigSpec.BooleanValue SYMBIONT_ATTACK_MOBS = BUILDER
            .comment("Allow newly spawned symbionts to target eligible mobs as well as players.")
            .define("withered_symbiont.shouldSymbiontAttackMobs", false);
    public static final ForgeConfigSpec.BooleanValue SYMBIONT_SUMMON_ENABLED = BUILDER
            .define("withered_symbiont.canSummonSymbiont", true);
    public static final ForgeConfigSpec.IntValue SYMBIONT_CHECK_SECONDS = BUILDER
            .defineInRange("withered_symbiont.minimumSpawnCheckInterval", 60, 1, 240);
    public static final ForgeConfigSpec.IntValue SYMBIONT_STORM_DELAY_MINUTES = BUILDER
            .comment("Storm-wide summon delay in minutes, plus up to ten random minutes.")
            .defineInRange("withered_symbiont.witherStormSummoningDelay", 10, 1, 20);
    public static final ForgeConfigSpec.IntValue SYMBIONT_PLAYER_DELAY_MINUTES = BUILDER
            .defineInRange("withered_symbiont.playerSummoningDelay", 10, 1, 60);
    public static final ForgeConfigSpec.IntValue SYMBIONT_PLAYER_KILL_DELAY_MINUTES = BUILDER
            .defineInRange("withered_symbiont.playerSummoningDelayOnKill", 40, 1, 60);
    public static final ForgeConfigSpec.IntValue SYMBIONT_PLAYER_PROTECTION_MINUTES = BUILDER
            .defineInRange("withered_symbiont.playerInvulnerableTime", 5, 1, 10);
    public static final ForgeConfigSpec.BooleanValue STORM_INVULNERABILITY = BUILDER
            .comment("Prevent ordinary damage and regenerate health from phase 4 onward.")
            .define("witherStorm.witherStormInvulnerability", true);
    public static final ForgeConfigSpec.IntValue FLYING_HEIGHT = BUILDER
            .comment("Approximate height above terrain for the early large-storm flight implementation.")
            .defineInRange("witherStorm.flyingHeight", 75, 10, 150);
    public static final ForgeConfigSpec.DoubleValue FORMIDIBOMB_POWER = BUILDER
            .comment("Explosion power of the initial 26.3 formidibomb implementation.")
            .defineInRange("formidibomb.explosionPower", 48.0, 1.0, 56.0);
    public static final ForgeConfigSpec.IntValue FORMIDIBOMB_CRAFT_FUSE = BUILDER
            .defineInRange("formidibomb.craftFuseTicks", 12000, 1, 12000);
    public static final ForgeConfigSpec.IntValue FORMIDIBOMB_FIRE_FUSE = BUILDER
            .comment("Maximum remaining fuse when a placed Formidibomb catches fire.")
            .defineInRange("formidibomb.catchFireFuseTicks", 1200, 1, 12000);
    public static final ForgeConfigSpec.BooleanValue FORMIDIBOMB_AUTO_FUSE = BUILDER
            .define("formidibomb.formidibombFuseEnabled", true);
    public static final ForgeConfigSpec.BooleanValue REVIVAL_TIMER_ENABLED = BUILDER
            .define("playing_dead.revivalTimer", true);
    public static final ForgeConfigSpec.IntValue REVIVAL_TIME_MINUTES = BUILDER
            .defineInRange("playing_dead.revivalTimeMinutes", 60, 1, 120);
    public static final ForgeConfigSpec.IntValue REVIVAL_PLAYER_PROTECTION_MINUTES = BUILDER
            .comment("Minutes after revival during which the temporary target selector ignores players and symbiont summoning is disabled.")
            .defineInRange("playing_dead.revivalPlayerProtection", 3, 1, 40);
    public static final ForgeConfigSpec.IntValue HIGH_PROXIMITY_SECONDS = BUILDER
            .defineInRange("wither_sickness.requiredProximitySeconds", 600, 12, 1200);
    public static final ForgeConfigSpec.IntValue LOW_PROXIMITY_SECONDS = BUILDER
            .defineInRange("wither_sickness.lowImmuneRequiredProximitySeconds", 360, 12, 1200);
    public static final ForgeConfigSpec.IntValue HIGH_APPLICATION_SECONDS = BUILDER
            .defineInRange("wither_sickness.applicationDelay", 720, 12, 1200);
    public static final ForgeConfigSpec.IntValue LOW_APPLICATION_SECONDS = BUILDER
            .defineInRange("wither_sickness.lowImmuneApplicationDelay", 410, 12, 1200);
    public static final ForgeConfigSpec.IntValue CURE_SECONDS = BUILDER
            .defineInRange("wither_sickness.cureDelay", 480, 12, 1200);
    public static final ForgeConfigSpec.IntValue LOW_CURE_SECONDS = BUILDER
            .defineInRange("wither_sickness.lowImmuneCureDelay", 480, 12, 1200);
    public static final ForgeConfigSpec.IntValue HIGH_PROXIMITY_OFFSET = BUILDER
            .defineInRange("wither_sickness.proximitySecondsModifierMax", 180, 12, 1200);
    public static final ForgeConfigSpec.IntValue HIGH_APPLICATION_OFFSET = BUILDER
            .defineInRange("wither_sickness.applicationDelayModifierMax", 300, 12, 1200);
    public static final ForgeConfigSpec.IntValue HIGH_CURE_OFFSET = BUILDER
            .defineInRange("wither_sickness.cureDelayModifierMax", 180, 12, 1200);
    public static final ForgeConfigSpec.IntValue LOW_PROXIMITY_OFFSET = BUILDER
            .defineInRange("wither_sickness.lowImmuneProximityModifierMax", 180, 12, 1200);
    public static final ForgeConfigSpec.IntValue LOW_APPLICATION_OFFSET = BUILDER
            .defineInRange("wither_sickness.lowImmuneApplicationModifierMax", 140, 12, 1200);
    public static final ForgeConfigSpec.IntValue LOW_CURE_OFFSET = BUILDER
            .defineInRange("wither_sickness.lowImmuneCureDelayModifierMax", 180, 12, 1200);
    public static final ForgeConfigSpec.BooleanValue KEEP_SICKNESS_AFTER_RESPAWN = BUILDER
            .define("wither_sickness.keepSicknessAfterRespawn", true);
    public static final ForgeConfigSpec SPEC = BUILDER.build();

    private StormConfig() {}
}
