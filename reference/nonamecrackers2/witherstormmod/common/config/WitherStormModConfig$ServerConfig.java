/*     */ package nonamecrackers2.witherstormmod.common.config;
/*     */ 
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ import nonamecrackers2.crackerslib.common.config.ConfigHelper;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
/*     */ import nonamecrackers2.witherstormmod.common.util.ItemPreservationCondition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServerConfig
/*     */   extends ConfigHelper
/*     */ {
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> shouldChunkLoadWhenNoPlayers;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> shouldChaseWhenTargetStopped;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> targetStationaryChunkRadius;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> evolutionAttributeModifier;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> targetStationaryMinutes;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> targetRunawayMinutes;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> invulnerabilityTime;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> chasingFlyingSpeed;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> normalFlyingSpeed;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> usePhaseAsDistanceMultiplier;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> distanceMultiplier;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> chaseOnPhaseChange;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> targetRunawayAttempts;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> targetRunawayAttemptMinutes;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> targetRunawayAttemptsRequired;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> minutesTillRunawayAttemptDiminish;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> targettingDistractionsEnabled;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> distractionTimeMinutes;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> maximumDistractionDistance;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> minimumDistractionDistance;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> randomDistractionChances;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> searchRangeMultiplier;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> distractionWaitTime;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> clustersRemoveItems;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> squashHitbox;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> hunchbackClusterPickupInterval;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> clusterPickupInterval;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> devourerClusterPickupInterval;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> tractorBeamBlockSearchRadius;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> flyingHeight;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> dynamicFlyingHeight;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> dynamicFlyingHeightTime;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> tillShouldShowHole;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> rotationSpeed;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> canPickupMobClusters;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> witherStormInvulnerability;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> amuletOverride;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> smartBossbar;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> headEscapeTime;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> randomBowelsEntrace;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> chunkLoadingRadius;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> randomStrollingWhenTargetHidden;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> boatingForTooLongDistractions;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> boatingForTooLongSeconds;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> maxRandomStrollTargetingTypeRadius;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> ignoreUltimateTargetIfHidden;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> caveRumbles;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> crossbowsSupportEnderPearls;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> removeNearbyJunk;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> mobsRunIntoPortals;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> preventWitherStormCamping;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> occludeSoundsUnderground;
/*     */   public final ForgeConfigSpec.ConfigValue<UltimateTargetManager.TargetingType> ultimateTargetingType;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> witherSicknessEnabled;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> sickenedMobConversions;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> increaseAmplifier;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> requiredContacts;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> requiredProximitySeconds;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> applicationDelay;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> cureDelay;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> lowImmuneRequiredProximitySeconds;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> lowImmuneApplicationDelay;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> lowImmuneCureDelay;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> proximitySecondsModifierMax;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> applicationDelayModifierMax;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> cureDelayModifierMax;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> lowImmuneProximityModifierMax;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> lowImmuneApplicationModifierMax;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> lowImmuneCureDelayModifierMax;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> keepSicknessAfterRespawn;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> craftFuseTicks;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> catchFireFuseTicks;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> dropInterval;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> shouldDropFromInventory;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> lowerBlockResistance;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> revivalTimer;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> revivalTimeMinutes;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> revivalPlayerProtection;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> canSummonSymbiont;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> minimumSpawnCheckInterval;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> witherStormSummoningDelay;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> playerInvulnerableTime;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> playerSummoningDelay;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> playerSummoningDelayOnKill;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> flamingSkullExplosionSize;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> flamingSkullSpeedModifier;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> tractorPullSpeedModifier;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> bowelsFallResistance;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> flyingDisabledWarning;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> shouldShowHole;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> resummonedPhase;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> minimumRoarInterval;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> maximumRoarInterval;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> clusterSizeModifier;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> attackableWhenNotVulnerable;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> bookDropsInInventory;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> canAttackHeads;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> healthScalePerPlayer;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> farthestTargetingTime;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> randomizedTargetingTime;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> randomlySpeedUpWithTargetChange;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> tractorBeamClusterPickUp;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> tractorBeamsRemoveFluids;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> blockClusterPullSpeedModifier;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> tractorBeamClusterSpeedModifier;
/*     */   public final ForgeConfigSpec.ConfigValue<Double> caveRumbleIntensity;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> chanceForExtendedRumbles;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> caveRumbleIntervalMin;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> caveRumbleIntervalMax;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> caveRumblesMessWithRedstone;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> shouldSymbiontAttackMobs;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> formidibombFuseEnabled;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> endOfPhaseFiveBombableExclusively;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> shouldPlayGlobalSoundsCrossDimensionally;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> witherStormsFollowBiggerStorms;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> onlyTryPickingUpTractorTagged;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> tractorBeamFluidRemovalHeight;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> constantBlackhole;
/*     */   public final ForgeConfigSpec.ConfigValue<ItemPreservationCondition> itemPreservation;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> preserveDropsForAllMobs;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> instantChomp;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> healFromChomp;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> convertFallingBlocks;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> specialTargetingBias;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> specialTargetingBiasChance;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> canClustersSpiralCounterClockwise;
/*     */   
/*     */   protected ServerConfig(ForgeConfigSpec.Builder builder) {
/* 461 */     super(builder, "witherstormmod");
/*     */     
/* 463 */     builder.comment("Server options").push("server");
/*     */     
/* 465 */     builder.comment("Misc").push("misc");
/*     */     
/* 467 */     this.shouldChunkLoadWhenNoPlayers = createValue(Boolean.valueOf(false), "shouldChunkLoadWhenNoPlayers", false, "Toggle to enable/disable Wither Storm chunk loading when no players are online");
/*     */ 
/*     */     
/* 470 */     this.invulnerabilityTime = createRangedIntValue(50, 1, 320, "invulnerabilityTime", false, "Specifies the invulnerability time when the Wither Storm has been summoned, in seconds. When invulnerable, the Wither Storm doesn't move and can't be attacked");
/*     */     
/* 472 */     this.flyingHeight = createRangedIntValue(75, 10, 150, "flyingHeight", false, "Specifies the height the Wither Storm will fly at during phase 4 and up");
/*     */     
/* 474 */     this.dynamicFlyingHeight = createValue(Boolean.valueOf(false), "dynamicFlyingHeight", false, "Makes the Wither Storm randomly adjust it's flying height itself between 40 and 80");
/*     */     
/* 476 */     this.dynamicFlyingHeightTime = createRangedIntValue(60, 15, 1200, "dynamicFlyingHeightTime", false, "The time (in seconds) that the Wither Storm should take before adjusting it's dynamic flying height");
/*     */     
/* 478 */     this.tillShouldShowHole = createRangedIntValue(6, 1, 30, "tillShouldShowHole", false, "If the Wither Storm's ultimate target has a command block tool and the Wither Storm is at phase 7, a timer specified by this value (in minutes) + random will countdown until the hole will automatically appear");
/*     */     
/* 480 */     this.shouldShowHole = createValue(Boolean.valueOf(true), "shouldShowHole", false, "Specifies if the bowels entrance hole in the Wither Storm's mass at the end of phase 7 should be available or not");
/*     */     
/* 482 */     this.rotationSpeed = createRangedDoubleValue(0.1D, 0.1D, 1.0D, "rotationSpeed", false, "Specifies the rotation speed of the Wither Storm");
/*     */     
/* 484 */     this.witherStormInvulnerability = createValue(Boolean.valueOf(true), "witherStormInvulnerability", false, "Specifies if the Wither Storm should regenerate its health and be melee attackable");
/*     */     
/* 486 */     this.smartBossbar = createValue(Boolean.valueOf(true), "smartBossbar", false, "The Wither Storm's bossbar and boss music will automatically toggle based on whether the player is underground or not. Disable to revert back to default bossbar/boss theme logic");
/*     */     
/* 488 */     this.randomBowelsEntrace = createValue(Boolean.valueOf(true), "randomBowelsEntrance", false, "Specifies if players should spawn somewhere random when entering the bowels. Disable to revert to a static entrance position");
/*     */     
/* 490 */     this.crossbowsSupportEnderPearls = createValue(Boolean.valueOf(true), "crossbowsSupportEnderPearls", false, "Specifies if crossbows should be able to also shoot ender pearls. Disable if facing compatibility issues");
/*     */     
/* 492 */     this.preventWitherStormCamping = createValue(Boolean.valueOf(true), "preventWitherStormCamping", false, "Specifies if players should temporarily respawn away from the Wither Storm when they die to it, if their respawn point is near the Storm");
/*     */     
/* 494 */     this.bowelsFallResistance = createValue(Boolean.valueOf(true), "bowelsFallResistance", false, "Specifies if max strength damange resistance should be given to the player when falling out of the bowels, to mitigate fall damage");
/*     */     
/* 496 */     this.resummonedPhase = createRangedIntValue(4, 0, 7, "resummonedPhase", false, "Specifies the phase the Wither Storm should be set to when resummoned by a withered beacon");
/*     */     
/* 498 */     this.canAttackHeads = createValue(Boolean.valueOf(true), "canAttackHeads", false, "Specifies if the Wither Storm's heads can be attacked");
/*     */     
/* 500 */     this.endOfPhaseFiveBombableExclusively = createValue(Boolean.valueOf(false), "endOfPhaseFiveBombableExclusively", false, "Specifies if only the end of phase five can be formidibombed, instead of all of phase 5");
/*     */     
/* 502 */     this.shouldPlayGlobalSoundsCrossDimensionally = createValue(Boolean.valueOf(false), "shouldPlayGlobalSoundsCrossDimensionally", false, "Specifies if global sounds made by the Wither Storm (e.x. evolve sound) should play cross dimensionally");
/*     */     
/* 504 */     this.onlyTryPickingUpTractorTagged = createValue(Boolean.valueOf(false), "onlyTryPickingUpTractorTagged", false, "Makes the Wither Storm attempt to only grab blocks that were tagged in the Tractor Distractions json");
/*     */     
/* 506 */     this.constantBlackhole = createValue(Boolean.valueOf(false), "constantBlackhole", false, "CAUTION THIS WILL BE EXTREMELY LAGGY, THIS SETS THE WITHER STORM TO HAVE NO SMALL CLUSTER COOLDOWN");
/*     */     
/* 508 */     this.instantChomp = createValue(Boolean.valueOf(false), "instantChomp", false, "Specifies if a Wither Storm head should immediately kill the player upon biting them. Does not apply to heads inside the bowels");
/*     */     
/* 510 */     this.healFromChomp = createValue(Boolean.valueOf(false), "healFromChomp", false, "Specifies if the Wither Storm should heal whenever it eats a mob (determined by half the mob's max health)");
/*     */     
/* 512 */     builder.pop();
/*     */     
/* 514 */     builder.comment("Ultimate Target Logic").push("ultimate_target_logic");
/*     */     
/* 516 */     this.ultimateTargetingType = createEnumValue((Enum)UltimateTargetManager.TargetingType.NEAREST, "ultimateTargetingType", false, "Specifies the targeting type the Wither Storm should use to determine it's ultimate target");
/*     */     
/* 518 */     this.farthestTargetingTime = createRangedIntValue(15, 1, 60, "farthestTargetingTime", false, "Makes the Wither Storm target the player that was farthest from it while using FARTHEST targeting for the configured amount of time (in minutes)");
/*     */     
/* 520 */     this.randomizedTargetingTime = createRangedIntValue(15, 1, 60, "randomizedTargetingTime", false, "If you're using RANDOMIZED targeting you can use this to set how often (in minutes) the Wither Storm should switch its targeting type");
/*     */     
/* 522 */     this.randomlySpeedUpWithTargetChange = createValue(Boolean.valueOf(true), "randomlySpeedUpWithTargetChange", false, "When set to true the Wither Storm will have a 10% chance to speedup when it changes it's targeting type while using RANDOMIZED targeting");
/*     */     
/* 524 */     this.amuletOverride = createValue(Boolean.valueOf(true), "amuletOverride", false, "Specifies if the amulet should override the Wither Storm's targeting");
/*     */     
/* 526 */     this.maxRandomStrollTargetingTypeRadius = createRangedIntValue(500, 200, 5000, "maxRandomStrollTargetingTypeRadius", false, "The max amount of distance a Wither Storm can travel at once when using the RANDOM_STROLL or RANDOM_STROLL_NEAR_PLAYER targeting type");
/*     */     
/* 528 */     this.ignoreUltimateTargetIfHidden = createValue(Boolean.valueOf(true), "ignoreUltimateTargetIfHidden", false, "If the Wither Storm's ultimate target is hidden (bossbar is not visible for that target) for an extended period of time, it will ignore that target temporarily and go for a different player. Toggle to disable. NOTE: Only functional on a multiplayer environment");
/*     */     
/* 530 */     this.witherStormsFollowBiggerStorms = createValue(Boolean.valueOf(true), "witherStormsFollowBiggerStorms", false, "Specifies if smaller Wither Storms should follow one with more consumed entities");
/*     */     
/* 532 */     builder.comment("Chases").push("chases");
/*     */     
/* 534 */     this.shouldChaseWhenTargetStopped = createValue(Boolean.valueOf(true), "shouldChaseWhenTargetStopped", false, "If the ultimate target is stationary for a specific amount of time, the Wither Storm will begin to move towards it at a faster rate (chase). Toggle to enable/disable this feature");
/*     */     
/* 536 */     this.chaseOnPhaseChange = createValue(Boolean.valueOf(true), "chaseOnPhaseChange", false, "Specifies if the Wither Storm should accelerate when it evolves into the next phase.");
/*     */     
/* 538 */     builder.pop();
/*     */     
/* 540 */     builder.comment("Target Stationary Logic").push("target_stationary_logic");
/*     */     
/* 542 */     this.targetStationaryChunkRadius = createRangedIntValue(8, 0, 16, "targetStationaryChunkRadius", true, "If the player remains in a radius of chunks specified by this value for a specific amount of time, the Wither Storm will begin to move towards the player at a faster rate");
/*     */     
/* 544 */     this.targetStationaryMinutes = createRangedIntValue(30, 1, 120, "targetStationaryMinutes", true, "Specifies the amount of time in minutes that the Wither Storm's ultimate target must be stationary in order for it to accelerate");
/*     */     
/* 546 */     this.usePhaseAsDistanceMultiplier = createValue(Boolean.valueOf(true), "usePhaseAsDistanceMultiplier", false, "Specifies if the Wither Storm should use the phase as a multiplier when calculating the time in ticks it takes for it to start accelerating, when its ultimate target is stationary");
/*     */     
/* 548 */     this.distanceMultiplier = createRangedDoubleValue(1.0D, 0.1D, 24.0D, "distanceMultiplier", false, "Specifies if the Wither Storm should use this value as a multiplier when calculating the time in ticks it takes for it to start accelerating, when its ultimate target is stationary");
/*     */     
/* 550 */     this.targetRunawayMinutes = createRangedIntValue(10, 1, 90, "targetRunawayMinutes", false, "Specifies the amount of time in minutes that the Wither Storm's ultimate target has begun to run away in order to slow back down again");
/*     */     
/* 552 */     builder.pop();
/*     */     
/* 554 */     builder.comment("Runaway Attempts").push("runaway_attempts");
/*     */     
/* 556 */     this.targetRunawayAttempts = createValue(Boolean.valueOf(true), "targetRunawayAttempts", false, "Specifies if the Wither Storm should count the times its ultimate target leaves the stationary chunk radius. After a specified amount of attempts has been reached, the Wither Storm will accelerate to its Target Stationary Speed");
/*     */     
/* 558 */     this.targetRunawayAttemptMinutes = createRangedIntValue(2, 1, 20, "targetRunawayAttemptMinutes", false, "Specifies the required time, in minutes, the Wither Storms ultimate target must be stationary in order to be able to count a runaway attempt when it tries to leave the stationary chunk radius");
/*     */     
/* 560 */     this.targetRunawayAttemptsRequired = createRangedIntValue(5, 1, 32, "targetRunawayAttemptsRequired", false, "Specifies the amount of runaway attempts made by the Wither Storms ultimate target that must be met for it to begin a chase");
/*     */     
/* 562 */     this.minutesTillRunawayAttemptDiminish = createRangedIntValue(16, 1, 48, "minutesTillRunawayAttemptDiminish", false, "Specifies the required time that must pass (in minutes) in order to decrease the runaway attempt amount. This timer only counts if the Wither Storms ultimate target remains in the stationary chunk radius");
/*     */     
/* 564 */     builder.pop();
/*     */     
/* 566 */     builder.comment("Distractions").push("distractions");
/*     */     
/* 568 */     this.targettingDistractionsEnabled = createValue(Boolean.valueOf(true), "targettingDistractionsEnabled", false, "Specifies when the Wither Storm is done chasing its ultimate target if it should become 'distracted' and go to a random nearby area");
/*     */     
/* 570 */     this.distractionTimeMinutes = createRangedIntValue(25, 1, 25, "distractionTimeMinutes", false, "Specifies the time in minutes that the Wither Storm should be distracted for, plus some random modifying and more");
/*     */     
/* 572 */     this.maximumDistractionDistance = createRangedIntValue(1000, 100, 3000, "maximumDistractionDistance", false, "Distractions will not occur if the Wither Storm's ultimate target is outside the radius determined by this value + its target range. Although, if the ultimate target is inside the radius, but then leaves, a distraction can then later occur");
/*     */     
/* 574 */     this.minimumDistractionDistance = createRangedIntValue(50, 10, 500, "minimumDistractionDistance", false, "Distractions will not occur IMMEDIATELY if the Wither Storm's ultimate target is INSIDE the radius determined by this value + its target range. If the target is inside this radius, the Wither Storm will wait before becoming distracted, to see if the target is outside the radius. If not, a distraction will not occur. Set to 0 to disable");
/*     */     
/* 576 */     this.randomDistractionChances = createValue(Boolean.valueOf(true), "randomDistractionChances", false, "Distractions may or may not occur if the conditions are met/unmet based off of a random chance. Toggle to enable/disable");
/*     */     
/* 578 */     this.searchRangeMultiplier = createRangedIntValue(1, 1, 8, "searchableRangeMultiplier", false, "Specifies the search radius multiplier for when searching for a random location to go to");
/*     */     
/* 580 */     this.distractionWaitTime = createRangedIntValue(2, 1, 20, "distractionWaitTime", false, "For when the Wither Storm needs to wait to become distracted, this value (in minutes) will specifiy the wait time, plus a random modifier");
/*     */     
/* 582 */     this.boatingForTooLongDistractions = createValue(Boolean.valueOf(true), "boatingForTooLongDistractions", false, "Specifies if the Wither Storm should get distracted when a player boats across water for too long. Used to prevent specific scenarios where the Wither Storm is chasing players but it can't quite keep up");
/*     */     
/* 584 */     this.boatingForTooLongSeconds = createRangedIntValue(60, 30, 300, "boatingForTooLongSeconds", false, "The amount of time a player needs to be boating until the Wither Storm will become distracted");
/*     */     
/* 586 */     builder.pop();
/*     */     
/* 588 */     builder.comment("Random Strolling").push("random_strolling");
/*     */     
/* 590 */     this.randomStrollingWhenTargetHidden = createValue(Boolean.valueOf(true), "randomStrollingWhenTargetHidden", false, "Specifies if the Wither Storm should stroll around the player if they're hidden from the storm");
/*     */     
/* 592 */     builder.pop();
/*     */     
/* 594 */     builder.comment("Speed").push("speed");
/*     */     
/* 596 */     this.chasingFlyingSpeed = createRangedDoubleValue(0.4D, 0.01D, 1.0D, "chasingFlyingSpeed", true, "Specifies a modifier value of Target Stationary Flying Speed attribute. The higher the value, the faster the Wither Storm will go when its ultimate target is stationary");
/*     */     
/* 598 */     this.normalFlyingSpeed = createRangedDoubleValue(0.02D, 0.01D, 1.0D, "normalFlyingSpeed", true, "Specifies a modifier value of Slow Flying Speed attribute. The higher the value, the faster the Wither Storm will go when it's ultimate target is not stationary");
/*     */     
/* 600 */     builder.pop();
/*     */     
/* 602 */     builder.pop();
/*     */     
/* 604 */     builder.comment("Targeting").push("targeting");
/*     */     
/* 606 */     this.headEscapeTime = createRangedIntValue(40, 0, 60, "headEscapeTime", false, "The targeting invulnerability time in seconds players who escape from a Wither Storm after injuring a head get");
/*     */     
/* 608 */     this.tractorPullSpeedModifier = createRangedDoubleValue(0.2D, 0.1D, 1.0D, "tractorPullSpeedModifier", false, "Modifies the tractor beam pull speed, higher = faster");
/*     */     
/* 610 */     this.specialTargetingBias = createValue(Boolean.valueOf(true), "specialTargetingBias", false, "Specifies if certain mobs (by default, players) should be targeted over others");
/*     */     
/* 612 */     this.specialTargetingBiasChance = createRangedIntValue(75, 0, 100, "specialTargetingBiasChance", false, "The percent chance certain mobs (by default, players) should be picked up over others");
/*     */     
/* 614 */     builder.pop();
/*     */     
/* 616 */     builder.comment("Evolution").push("evolution");
/*     */     
/* 618 */     this.evolutionAttributeModifier = createRangedDoubleValue(1.0D, 0.01D, 32.0D, "evolutionAttributeModifier", false, "Specifies a modifier value of the evolution rate attribute. The higher the value, the longer it takes for the Wither Storm to make a complete evolution (from phases 0 to 7), and vice versa. Cannot be lower than 0");
/*     */     
/* 620 */     builder.pop();
/*     */     
/* 622 */     builder.comment("Performance").push("performance");
/*     */     
/* 624 */     this.clustersRemoveItems = createValue(Boolean.valueOf(true), "clustersRemoveItems", false, "Specifies if Block Clusters should remove non-important items in its path. NOTE: Disabling can cause major lag");
/*     */     
/* 626 */     this.squashHitbox = createValue(Boolean.valueOf(false), "squashHitbox", true, "If true, the hitbox of the Wither Storm and Wither Storm Segment's will be shrunk vertically to one block. Enable if facing major server lag in the bigger Wither Storm phases");
/*     */     
/* 628 */     this.chunkLoadingRadius = createRangedIntValue(12, 6, 32, "chunkLoadingRadius", true, "Specifies the chunk loading radius for the Wither Storm");
/*     */     
/* 630 */     this.removeNearbyJunk = createValue(Boolean.valueOf(true), "removeNearbyJunk", false, "Specifies if junk items near the Wither Storm should be immediately destroyed. NOTE: Disabling will cause massive server side and potential FPS lag!");
/*     */     
/* 632 */     this.mobsRunIntoPortals = createValue(Boolean.valueOf(true), "mobsRunIntoPortals", false, "Specifies if mobs should go into nearby nether portals when running away from a Wither Storm");
/*     */     
/* 634 */     builder.pop();
/*     */     
/* 636 */     builder.comment("World Consumption").push("world_consumption");
/*     */     
/* 638 */     this.hunchbackClusterPickupInterval = createRangedIntValue(20, 10, 80, "hunchbackClusterPickupInterval", false, "Alters the interval (in ticks) of picking up block clusters for the hunchback phases (phase 0 - 3). NOTE: This value changes the evolution speed of the Wither Storm significantly");
/*     */     
/* 640 */     this.clusterPickupInterval = createRangedIntValue(40, 10, 80, "clusterPickupInterval", false, "Alters the interval (in ticks) of picking up block clusters for the destroyer phases (phase 4 - 5). NOTE: This value changes the evolution speed of the Wither Storm significantly");
/*     */     
/* 642 */     this.devourerClusterPickupInterval = createRangedIntValue(40, 10, 80, "devourerClusterPickupInterval", false, "Alters the interval (in ticks) of picking up block clusters for the devourer phases (phase 6+). NOTE: This value changes the evolution speed of the Wither Storm significantly");
/*     */     
/* 644 */     this.canPickupMobClusters = createValue(Boolean.valueOf(true), "canPickupMobClusters", false, "Specifies if the Wither Storm's tractor beams can pull in multiple mobs at once that interesect the beam");
/*     */     
/* 646 */     this.clusterSizeModifier = createRangedIntValue(0, 0, 16, "clusterSizeModifier", false, "Increases the radius of block clusters linearly by this amount. NOTE: Greatly impacts the Wither Storm's evolution rate. Greater values also lead to worse performance!");
/*     */     
/* 648 */     this.tractorBeamClusterPickUp = createValue(Boolean.valueOf(true), "tractorBeamClusterPickUp", false, "Specifies if the Wither Storm should be able to pick up block clusters with its tractor beams");
/*     */     
/* 650 */     this.tractorBeamsRemoveFluids = createValue(Boolean.valueOf(true), "tractorBeamsRemoveFluids", false, "Makes it so the Wither Storm will remove fluids above Y 63 with its tractor beams");
/*     */     
/* 652 */     this.blockClusterPullSpeedModifier = createRangedDoubleValue(1.0D, 0.1D, 10.0D, "blockClusterPullSpeedModifier", false, "Modifies the Wither Storm's block cluster pull speed by multiplying the default speed with this value");
/*     */     
/* 654 */     this.tractorBeamClusterSpeedModifier = createRangedDoubleValue(1.0D, 0.1D, 10.0D, "tractorBeamClusterSpeedModifier", false, "Multiplies the speed of block clusters being pulled in by a Wither Storm's tractor beam by this value");
/*     */     
/* 656 */     this.tractorBeamBlockSearchRadius = createRangedIntValue(10, 4, 256, "tractorBeamBlockSearchRadius", false, "How far the tractor beams should search for distraction blocks, NOTE: High values may cause lag");
/*     */     
/* 658 */     this.canClustersSpiralCounterClockwise = createValue(Boolean.valueOf(false), "canClustersSpiralCounterClockwise", false, "Specifies if block clusters can have a chance to spiral counter clockwise when being consumed by the Wither Storm");
/*     */     
/* 660 */     this.convertFallingBlocks = createValue(Boolean.valueOf(false), "convertFallingBlocks", false, "Specifies if the Wither Storm should convert falling blocks into clusters (NOTE: This can be very laggy and cause the Wither Storm to evolve fast!)");
/*     */     
/* 662 */     this.tractorBeamFluidRemovalHeight = createRangedIntValue(63, -64, 320, "tractorBeamFluidRemovalHeight", false, "Specifies the height of which tractor beams should remove water. The default of 63 is ocean height and upwards");
/*     */     
/* 664 */     builder.pop();
/*     */     
/* 666 */     builder.comment("Caves").push("caves");
/*     */     
/* 668 */     this.caveRumbles = createValue(Boolean.valueOf(true), "caveRumbles", false, "Specifies if the screen should shake and other various cave rumble effects should occur when underground and near the Storm");
/*     */     
/* 670 */     this.occludeSoundsUnderground = createValue(Boolean.valueOf(true), "occludeSoundsUnderground", false, "Specifies if sounds from the Wither Storm should be heard when deep underground");
/*     */     
/* 672 */     this.caveRumbleIntensity = createRangedDoubleValue(0.25D, 0.0D, 1.0D, "caveRumbleIntensity", false, "Higher values makes cave rumbles more 'intense,' and makes more dripstone and glowberries fall");
/*     */     
/* 674 */     this.chanceForExtendedRumbles = createValue(Boolean.valueOf(true), "chanceForExtendedRumbles", false, "Specifies if a random chance for extended cave rumbles to occur should be possible");
/*     */     
/* 676 */     this.caveRumbleIntervalMin = createRangedIntValue(60, 5, 1800, "caveRumbleIntervalMin", false, "Specifies the minimum interval possible (in seconds) between cave rumbles");
/*     */     
/* 678 */     this.caveRumbleIntervalMax = createRangedIntValue(180, 5, 1800, "caveRumbleIntervalMax", false, "Specifies the maximum interval possible (in seconds) between cave rumbles");
/*     */     
/* 680 */     this.caveRumblesMessWithRedstone = createValue(Boolean.valueOf(true), "caveRumblesMessWithRedstone", false, "Specifies if cave rumbles should mess with redstone type blocks");
/*     */     
/* 682 */     builder.pop();
/*     */     
/* 684 */     builder.comment("Wither Sickness").push("wither_sickness");
/*     */     
/* 686 */     this.witherSicknessEnabled = createValue(Boolean.valueOf(true), "witherSicknessEnabled", true, "Specifies if mobs should be able to receive wither sickness");
/*     */     
/* 688 */     this.sickenedMobConversions = createValue(Boolean.valueOf(true), "sickenedMobConversions", false, "Specifies if mobs should convert to sickened mobs once they die from wither sickness");
/*     */     
/* 690 */     this.increaseAmplifier = createValue(Boolean.valueOf(true), "increaseAmplifier", false, "Specifies if entities who are reinfected over a short period of time should receive wither sickness with a greater strength");
/*     */     
/* 692 */     this.requiredContacts = createRangedIntValue(6, 1, 40, "requiredContacts", true, "After a mob has been targetted by the Wither Storm a set maximum of times, the mob will become infected");
/*     */     
/* 694 */     this.requiredProximitySeconds = createRangedIntValue(600, 12, 1200, "requiredProximitySeconds", true, "Specifies the amount of time in seconds high immunity mobs (players) must be near the Wither Storm in order to to begin infection");
/*     */     
/* 696 */     this.applicationDelay = createRangedIntValue(720, 12, 1200, "applicationDelay", true, "Specifies the amount of time in seconds high immunity mobs (players) must be infected in order to be applied the wither sickness effect");
/*     */     
/* 698 */     this.cureDelay = createRangedIntValue(480, 12, 1200, "cureDelay", true, "Specifies the delay, in seconds, before high immunity mobs (players) are cured of wither sickness");
/*     */     
/* 700 */     this.lowImmuneRequiredProximitySeconds = createRangedIntValue(360, 12, 1200, "lowImmuneRequiredProximitySeconds", true, "Specifies the amount of time in seconds that low immunity mobs must be near the Wither Storm in order to begin infection");
/*     */     
/* 702 */     this.lowImmuneApplicationDelay = createRangedIntValue(410, 12, 1200, "lowImmuneApplicationDelay", true, "Specifies the amount of time in seconds low immunity mobs must be infected in order to be applied the wither sickness effect");
/*     */     
/* 704 */     this.lowImmuneCureDelay = createRangedIntValue(480, 12, 1200, "lowImmuneCureDelay", true, "Specifies the delay, in seconds, before low immunity mobs are cured of wither sickness");
/*     */     
/* 706 */     this.proximitySecondsModifierMax = createRangedIntValue(180, 12, 1200, "proximitySecondsModifierMax", false, "High immunity mobs (players) will be assigned a random proximity seconds modifier that will change the proximity seconds time. This value will set the maximum potential limit for that modifier, in seconds");
/*     */     
/* 708 */     this.applicationDelayModifierMax = createRangedIntValue(300, 12, 1200, "applicationDelayModifierMax", false, "High immunity mobs (players) will be assigned a random application delay modifier that will change the application delay. This value will set the maximum potential limit for that modifier, in seconds");
/*     */     
/* 710 */     this.cureDelayModifierMax = createRangedIntValue(180, 12, 1200, "cureDelayModifierMax", false, "High immunity mobs (players) will be assigned a random cure delay modifier that will change the cure delay. This value will set the maximum potential limit for that modifier, in seconds");
/*     */     
/* 712 */     this.lowImmuneProximityModifierMax = createRangedIntValue(180, 12, 1200, "lowImmuneProximityModifierMax", false, "Low immunity mobs will be assigned a random proximity seconds delay modifier that will change the proximity seconds time. This value will set the maximum potential limit for that modifier, in seconds");
/*     */     
/* 714 */     this.lowImmuneApplicationModifierMax = createRangedIntValue(140, 12, 1200, "lowImmuneApplicationModifierMax", false, "Low immunity mobs will be assigned a random application delay modifier that will change the proximity seconds time. This value will set the maximum potential limit for that modifier, in seconds");
/*     */     
/* 716 */     this.lowImmuneCureDelayModifierMax = createRangedIntValue(180, 12, 1200, "lowImmuneCureDelayModifierMax", false, "Low immunity mobs will be assigned a random cure delay modifier that will change the cure delay time. This value will set the maximum potential limit for that modifier, in seconds");
/*     */     
/* 718 */     this.keepSicknessAfterRespawn = createValue(Boolean.valueOf(true), "keepSicknessAfterRespawn", false, "Specifies if Wither Sickness should be removed after a player respawns");
/*     */     
/* 720 */     builder.pop();
/*     */     
/* 722 */     builder.comment("Formidibomb").push("formidibomb");
/*     */     
/* 724 */     this.craftFuseTicks = createRangedIntValue(12000, 1, 12000, "craftFuseTicks", false, "Specifies the fuse in ticks that count down to the formidibomb's explosion. The fuse is set when the block is crafted");
/*     */     
/* 726 */     this.catchFireFuseTicks = createRangedIntValue(1200, 1, 12000, "catchFireFuseTicks", false, "If the formidibomb is manually set on fire the fuse tick count will be set to this value, if the original fuse is greater than this value");
/*     */     
/* 728 */     this.shouldDropFromInventory = createValue(Boolean.valueOf(true), "shouldDropFromInventory", false, "Specifies if after a set amount of time the formidibomb item should drop out of whatever inventory is holding it and should spawn as the entity");
/*     */     
/* 730 */     this.dropInterval = createRangedIntValue(4, 1, 8, "dropInterval", false, "Specifies the interval for when the formidibomb will drop out of its inventory (if enabled). This value divides the crafted fuse ticks to get the interval (e.x. 12000 / 4 = 3000)");
/*     */     
/* 732 */     this.lowerBlockResistance = createValue(Boolean.valueOf(true), "lowerBlockResistance", false, "Lowers the resistance of blocks in the path of the explosion, effectively increasing the strength of the explosion which allows for obsidian, etc. to be destroyed");
/*     */     
/* 734 */     this.formidibombFuseEnabled = createValue(Boolean.valueOf(true), "formidibombFuseEnabled", false, "Specifies if a formidibomb's auto detonation fuse should be enabled");
/*     */     
/* 736 */     builder.pop();
/*     */     
/* 738 */     builder.comment("Playing dead").push("playing_dead");
/*     */     
/* 740 */     this.revivalTimer = createValue(Boolean.valueOf(true), "revivalTimer", false, "Specifies if the Wither Storm should automatically revive if not found after a period of time");
/*     */     
/* 742 */     this.revivalTimeMinutes = createRangedIntValue(60, 1, 120, "revivalTimeMinutes", false, "Specifies when (in minutes) the Wither Storm will automatically revive when playing dead");
/*     */     
/* 744 */     this.revivalPlayerProtection = createRangedIntValue(3, 1, 40, "revivalPlayerProtection", false, "Specifies the time (in minutes) after being revived that the Wither Storm should ignore players");
/*     */     
/* 746 */     builder.pop();
/*     */     
/* 748 */     builder.comment("Withered Symbiont").push("withered_symbiont");
/*     */     
/* 750 */     this.canSummonSymbiont = createValue(Boolean.valueOf(true), "canSummonSymbiont", false, "Specifies if the Wither Storm can summon the Withered Symbiont");
/*     */     
/* 752 */     this.shouldSymbiontAttackMobs = createValue(Boolean.valueOf(false), "shouldSymbiontAttackMobs", false, "Specifies if the Withered Symbiont is hostile towards other mobs (This is only applied to Symbionts spawned after this change is applied)");
/*     */     
/* 754 */     this.minimumSpawnCheckInterval = createRangedIntValue(60, 1, 240, "minimumSpawnCheckInterval", false, "Specifies the minimum interval (+random) in seconds that the Wither Storm should check for Withered Symbiont spawn conditions");
/*     */     
/* 756 */     this.witherStormSummoningDelay = createRangedIntValue(10, 1, 20, "witherStormSummoningDelay", false, "Specifies the delay in minutes (+random) that the Wither Storm will be able to summon a Withered Symbiont");
/*     */     
/* 758 */     this.playerInvulnerableTime = createRangedIntValue(5, 1, 10, "playerInvulnerableTime", false, "Specifies the time in minutes (+random) that the players who killed a Withered Symbiont should be ignored by the Wither Storm for");
/*     */     
/* 760 */     this.playerSummoningDelay = createRangedIntValue(10, 1, 60, "playerSummoningDelay", false, "Specifies the delay in minutes (+random) that the Wither Storm should be able summon a Withered Symbiont for the player it summoned one for");
/*     */     
/* 762 */     this.playerSummoningDelayOnKill = createRangedIntValue(40, 1, 60, "playerSummoningDelayOnKill", false, "If a player kills a Withered Symbiont, their sumoning delay will be increased to this value in minutes (+random)");
/*     */     
/* 764 */     this.attackableWhenNotVulnerable = createValue(Boolean.valueOf(false), "attackableWhenNotVulnerable", false, "Specifies if the Withered Symbiont can be attacked from behind if it's not vulnerable");
/*     */     
/* 766 */     this.bookDropsInInventory = createValue(Boolean.valueOf(true), "bookDropsInInventory", false, "If multiple players are near the Withered Symbiont, the command block book will automatically drop into the inventory of the player the Symbiont was spawned for, if they are nearby and have inventory space");
/*     */     
/* 768 */     this.healthScalePerPlayer = createRangedDoubleValue(20.0D, 0.0D, 100.0D, "healthScalePerPlayer", false, "Adds this value multiplied by the amount of nearby players to the Symbiont's max health when it is spawned. Set to zero to disable this feature");
/*     */     
/* 770 */     builder.pop();
/*     */     
/* 772 */     builder.comment("Flaming Skulls").push("flaming_skulls");
/*     */     
/* 774 */     this.flamingSkullExplosionSize = createRangedDoubleValue(5.0D, 1.0D, 16.0D, "flamingSkullExplosionSize", false, "The flaming skull explosion size when they collide with blocks");
/*     */     
/* 776 */     this.flamingSkullSpeedModifier = createRangedDoubleValue(1.0D, 0.5D, 8.0D, "flamingSkullSpeedModifier", false, "The speed modifier for flaming wither skulls. Higher = faster");
/*     */     
/* 778 */     builder.pop();
/*     */     
/* 780 */     builder.comment("Roaring").push("roaring");
/*     */     
/* 782 */     this.minimumRoarInterval = createRangedIntValue(20, 1, 100, "minimumRoarInterval", false, "Specifies the lowest time, in seconds, it will take for one of the Wither Storm's heads to initiate a roar and shoot a flaming skull");
/*     */     
/* 784 */     this.maximumRoarInterval = createRangedIntValue(50, 1, 100, "maximumRoarInterval", false, "Specifies the greatest time, in seconds, it will take for one of the Wither Storm's heads to initiate a roar and shoot a flaming skull");
/*     */     
/* 786 */     builder.pop();
/*     */     
/* 788 */     builder.comment("Item Preservation").push("item_preservation");
/*     */     
/* 790 */     this.itemPreservation = createEnumValue((Enum)ItemPreservationCondition.CHOMPED_OR_KILLED_NEAR_HEAD, "itemPreservation", false, "When a player dies, a block cluster containing their items will be created into the world based on the condition defined by this value. Acts as a gravestone");
/*     */     
/* 792 */     this.preserveDropsForAllMobs = createValue(Boolean.valueOf(false), "preserveDropsForAllMobs", false, "Specifies if drops should be preserved using block clusters (gravestones) for all mobs, not just players");
/*     */     
/* 794 */     builder.pop();
/*     */     
/* 796 */     this.flyingDisabledWarning = createValue(Boolean.valueOf(true), "flyingEnabledWarning", false, "Specifies if a warning should be printed out to server operators if flying is disabled");
/*     */     
/* 798 */     builder.pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\config\WitherStormModConfig$ServerConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */