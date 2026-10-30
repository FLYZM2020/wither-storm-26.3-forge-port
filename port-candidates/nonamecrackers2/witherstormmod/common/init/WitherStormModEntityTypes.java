/*     */ package nonamecrackers2.witherstormmod.common.init;
/*     */ 
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.MobCategory;
/*     */ import net.minecraft.world.entity.SpawnPlacements;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.levelgen.Heightmap;
/*     */ import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
/*     */ import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.RegistryObject;
/*     */ import nonamecrackers2.witherstormmod.client.util.ClientBlockClusterFactory;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlueFlamingWitherSkullEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedBee;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedCat;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedChicken;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedCow;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedCreeper;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedMushroomCow;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedParrot;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedPhantom;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedPig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedPillager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedSkeleton;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedSpider;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedVillager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedVindicator;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedWolf;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedZombie;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SuperTNTEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.TaintedSlime;
/*     */ import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormModEntityTypes
/*     */ {
/*  53 */   public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "witherstormmod");
/*     */   
/*  55 */   public static final RegistryObject<EntityType<WitherStormEntity>> WITHER_STORM = register("wither_storm", EntityType.Builder.of(WitherStormEntity::new, MobCategory.MONSTER).sized(0.9F, 3.5F).setTrackingRange(512).clientTrackingRange(512).fireImmune());
/*  56 */   public static final RegistryObject<EntityType<BlockClusterEntity>> BLOCK_CLUSTER = register("block_cluster", EntityType.Builder.of(BlockClusterEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).setTrackingRange(512).clientTrackingRange(512).fireImmune().updateInterval(10).setCustomClientFactory(ClientBlockClusterFactory::make));
/*  57 */   public static final RegistryObject<EntityType<WitherStormSegmentEntity>> WITHER_STORM_SEGMENT = register("wither_storm_segment", EntityType.Builder.of(WitherStormSegmentEntity::new, MobCategory.MONSTER).sized(30.0F, 25.0F).setTrackingRange(512).clientTrackingRange(512).fireImmune());
/*  58 */   public static final RegistryObject<EntityType<FlamingWitherSkullEntity>> FLAMING_WITHER_SKULL = register("flaming_wither_skull", EntityType.Builder.of(FlamingWitherSkullEntity::new, MobCategory.MISC).sized(0.8F, 0.8F).clientTrackingRange(4).updateInterval(10));
/*  59 */   public static final RegistryObject<EntityType<BlueFlamingWitherSkullEntity>> BLUE_FLAMING_WITHER_SKULL = register("blue_flaming_wither_skull", EntityType.Builder.of(BlueFlamingWitherSkullEntity::new, MobCategory.MISC).sized(0.8F, 0.8F).clientTrackingRange(4).updateInterval(10));
/*  60 */   public static final RegistryObject<EntityType<SickenedZombie>> SICKENED_ZOMBIE = register("sickened_zombie", EntityType.Builder.of(SickenedZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8));
/*  61 */   public static final RegistryObject<EntityType<SickenedSkeleton>> SICKENED_SKELETON = register("sickened_skeleton", EntityType.Builder.of(SickenedSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8));
/*  62 */   public static final RegistryObject<EntityType<SickenedSpider>> SICKENED_SPIDER = register("sickened_spider", EntityType.Builder.of(SickenedSpider::new, MobCategory.MONSTER).sized(1.6F, 1.1F).clientTrackingRange(8));
/*  63 */   public static final RegistryObject<EntityType<SickenedCreeper>> SICKENED_CREEPER = register("sickened_creeper", EntityType.Builder.of(SickenedCreeper::new, MobCategory.MONSTER).sized(0.6F, 1.7F).clientTrackingRange(8));
/*  64 */   public static final RegistryObject<EntityType<SuperTNTEntity>> SUPER_TNT = register("super_tnt", EntityType.Builder.of(SuperTNTEntity::new, MobCategory.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10));
/*  65 */   public static final RegistryObject<EntityType<FormidibombEntity>> FORMIDIBOMB = register("formidibomb", EntityType.Builder.of(FormidibombEntity::new, MobCategory.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10));
/*  66 */   public static final RegistryObject<EntityType<CommandBlockEntity>> COMMAND_BLOCK = register("command_block", EntityType.Builder.of(CommandBlockEntity::new, MobCategory.MISC).fireImmune().sized(1.0F, 1.0F).clientTrackingRange(10));
/*  67 */   public static final RegistryObject<EntityType<WitheredSymbiontEntity>> WITHERED_SYMBIONT = register("withered_symbiont", EntityType.Builder.of(WitheredSymbiontEntity::new, MobCategory.MONSTER).fireImmune().sized(1.2F, 3.8F).clientTrackingRange(10));
/*  68 */   public static final RegistryObject<EntityType<WitherStormHeadEntity>> WITHER_STORM_HEAD = register("wither_storm_head", EntityType.Builder.of(WitherStormHeadEntity::new, MobCategory.MONSTER).fireImmune().sized(5.0F, 5.0F).clientTrackingRange(10));
/*  69 */   public static final RegistryObject<EntityType<TentacleEntity>> TENTACLE = register("tentacle", EntityType.Builder.of(TentacleEntity::new, MobCategory.MONSTER).fireImmune().sized(7.5F, 9.5F).clientTrackingRange(10));
/*  70 */   public static final RegistryObject<EntityType<SickenedVillager>> SICKENED_VILLAGER = register("sickened_villager", EntityType.Builder.of(SickenedVillager::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8));
/*  71 */   public static final RegistryObject<EntityType<SickenedPhantom>> SICKENED_PHANTOM = register("sickened_phantom", EntityType.Builder.of(SickenedPhantom::new, MobCategory.MONSTER).sized(0.9F, 0.5F).clientTrackingRange(8));
/*  72 */   public static final RegistryObject<EntityType<SickenedChicken>> SICKENED_CHICKEN = register("sickened_chicken", EntityType.Builder.of(SickenedChicken::new, MobCategory.MONSTER).sized(0.4F, 0.7F).clientTrackingRange(10));
/*  73 */   public static final RegistryObject<EntityType<SickenedParrot>> SICKENED_PARROT = register("sickened_parrot", EntityType.Builder.of(SickenedParrot::new, MobCategory.MONSTER).sized(0.5F, 0.9F).clientTrackingRange(8));
/*  74 */   public static final RegistryObject<EntityType<SickenedWolf>> SICKENED_WOLF = register("sickened_wolf", EntityType.Builder.of(SickenedWolf::new, MobCategory.MONSTER).sized(0.6F, 0.85F).clientTrackingRange(10));
/*  75 */   public static final RegistryObject<EntityType<SickenedCat>> SICKENED_CAT = register("sickened_cat", EntityType.Builder.of(SickenedCat::new, MobCategory.MONSTER).sized(0.6F, 0.7F).clientTrackingRange(8));
/*  76 */   public static final RegistryObject<EntityType<SickenedCow>> SICKENED_COW = register("sickened_cow", EntityType.Builder.of(SickenedCow::new, MobCategory.MONSTER).sized(0.9F, 1.4F).clientTrackingRange(10));
/*  77 */   public static final RegistryObject<EntityType<SickenedPig>> SICKENED_PIG = register("sickened_pig", EntityType.Builder.of(SickenedPig::new, MobCategory.MONSTER).sized(0.9F, 0.9F).clientTrackingRange(10));
/*  78 */   public static final RegistryObject<EntityType<SickenedMushroomCow>> SICKENED_MUSHROOM_COW = register("sickened_mushroom_cow", EntityType.Builder.of(SickenedMushroomCow::new, MobCategory.MONSTER).sized(0.9F, 1.4F).clientTrackingRange(10));
/*  79 */   public static final RegistryObject<EntityType<SickenedBee>> SICKENED_BEE = register("sickened_bee", EntityType.Builder.of(SickenedBee::new, MobCategory.MONSTER).sized(0.7F, 0.6F).clientTrackingRange(8));
/*  80 */   public static final RegistryObject<EntityType<SickenedPillager>> SICKENED_PILLAGER = register("sickened_pillager", EntityType.Builder.of(SickenedPillager::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8));
/*  81 */   public static final RegistryObject<EntityType<SickenedVindicator>> SICKENED_VINDICATOR = register("sickened_vindicator", EntityType.Builder.of(SickenedVindicator::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8));
/*  82 */   public static final RegistryObject<EntityType<SickenedIronGolem>> SICKENED_IRON_GOLEM = register("sickened_iron_golem", EntityType.Builder.of(SickenedIronGolem::new, MobCategory.MONSTER).sized(1.4F, 2.7F).clientTrackingRange(10).fireImmune().immuneTo(new Block[] { Blocks.POWDER_SNOW }));
/*  83 */   public static final RegistryObject<EntityType<SickenedSnowGolem>> SICKENED_SNOW_GOLEM = register("sickened_snow_golem", EntityType.Builder.of(SickenedSnowGolem::new, MobCategory.MONSTER).immuneTo(new Block[] { Blocks.POWDER_SNOW }).sized(0.7F, 1.9F).clientTrackingRange(8));
/*  84 */   public static final RegistryObject<EntityType<TaintedSlime>> TAINTED_SLIME = register("tainted_slime", EntityType.Builder.of(TaintedSlime::new, MobCategory.MONSTER).noSummon().sized(2.04F, 2.04F).clientTrackingRange(10));
/*  85 */   public static final RegistryObject<EntityType<TentacleSpike>> TENTACLE_SPIKE = register("tentacle_spike", EntityType.Builder.of(TentacleSpike::new, MobCategory.MISC).sized(0.5F, 1.4F).clientTrackingRange(6).updateInterval(2));
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.world.entity.Entity> RegistryObject<EntityType<T>> register(String id, EntityType.Builder<T> builder) {
/*  89 */     return ENTITIES.register(id, () -> builder.build((Identifier.fromNamespaceAndPath("witherstormmod", id)).toString()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addEntityAttributes(EntityAttributeCreationEvent event) {
/*  94 */     event.put((EntityType)WITHER_STORM.get(), WitherStormEntity.createAttributes().build());
/*  95 */     event.put((EntityType)WITHER_STORM_SEGMENT.get(), WitherStormSegmentEntity.createAttributes().build());
/*  96 */     event.put((EntityType)SICKENED_ZOMBIE.get(), SickenedZombie.createAttributes().build());
/*  97 */     event.put((EntityType)SICKENED_SKELETON.get(), SickenedSkeleton.createAttributes().build());
/*  98 */     event.put((EntityType)SICKENED_SPIDER.get(), SickenedSpider.createAttributes().build());
/*  99 */     event.put((EntityType)SICKENED_CREEPER.get(), SickenedCreeper.createAttributes().build());
/* 100 */     event.put((EntityType)COMMAND_BLOCK.get(), CommandBlockEntity.createAttributes().build());
/* 101 */     event.put((EntityType)WITHERED_SYMBIONT.get(), WitheredSymbiontEntity.createAttributes().build());
/* 102 */     event.put((EntityType)WITHER_STORM_HEAD.get(), WitherStormHeadEntity.createAttributes().build());
/* 103 */     event.put((EntityType)TENTACLE.get(), TentacleEntity.createAttributes().build());
/* 104 */     event.put((EntityType)SICKENED_VILLAGER.get(), SickenedVillager.createAttributes().build());
/* 105 */     event.put((EntityType)SICKENED_PHANTOM.get(), SickenedPhantom.createAttributes().build());
/* 106 */     event.put((EntityType)SICKENED_CHICKEN.get(), SickenedChicken.createAttributes().build());
/* 107 */     event.put((EntityType)SICKENED_PARROT.get(), SickenedParrot.createAttributes().build());
/* 108 */     event.put((EntityType)SICKENED_WOLF.get(), SickenedWolf.createAttributes().build());
/* 109 */     event.put((EntityType)SICKENED_CAT.get(), SickenedCat.createAttributes().build());
/* 110 */     event.put((EntityType)SICKENED_COW.get(), SickenedCow.createAttributes().build());
/* 111 */     event.put((EntityType)SICKENED_PIG.get(), SickenedPig.createAttributes().build());
/* 112 */     event.put((EntityType)SICKENED_MUSHROOM_COW.get(), SickenedMushroomCow.createAttributes().build());
/* 113 */     event.put((EntityType)SICKENED_BEE.get(), SickenedBee.createAttributes().build());
/* 114 */     event.put((EntityType)SICKENED_PILLAGER.get(), SickenedPillager.createAttributes().build());
/* 115 */     event.put((EntityType)SICKENED_VINDICATOR.get(), SickenedVindicator.createAttributes().build());
/* 116 */     event.put((EntityType)SICKENED_IRON_GOLEM.get(), SickenedIronGolem.createAttributes().build());
/* 117 */     event.put((EntityType)SICKENED_SNOW_GOLEM.get(), SickenedSnowGolem.createAttributes().build());
/* 118 */     event.put((EntityType)TAINTED_SLIME.get(), TaintedSlime.createAttributes().build());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
/* 123 */     event.register((EntityType)SICKENED_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
/* 124 */     event.register((EntityType)SICKENED_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
/* 125 */     event.register((EntityType)SICKENED_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
/* 126 */     event.register((EntityType)SICKENED_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModEntityTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */