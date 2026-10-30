/*     */ package nonamecrackers2.witherstormmod.common.init;
/*     */ 
/*     */ import net.minecraft.resources.ResourceLocation;
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
/*  55 */   public static final RegistryObject<EntityType<WitherStormEntity>> WITHER_STORM = register("wither_storm", EntityType.Builder.m_20704_(WitherStormEntity::new, MobCategory.MONSTER).m_20699_(0.9F, 3.5F).setTrackingRange(512).m_20702_(512).m_20719_());
/*  56 */   public static final RegistryObject<EntityType<BlockClusterEntity>> BLOCK_CLUSTER = register("block_cluster", EntityType.Builder.m_20704_(BlockClusterEntity::new, MobCategory.MISC).m_20699_(1.0F, 1.0F).setTrackingRange(512).m_20702_(512).m_20719_().m_20717_(10).setCustomClientFactory(ClientBlockClusterFactory::make));
/*  57 */   public static final RegistryObject<EntityType<WitherStormSegmentEntity>> WITHER_STORM_SEGMENT = register("wither_storm_segment", EntityType.Builder.m_20704_(WitherStormSegmentEntity::new, MobCategory.MONSTER).m_20699_(30.0F, 25.0F).setTrackingRange(512).m_20702_(512).m_20719_());
/*  58 */   public static final RegistryObject<EntityType<FlamingWitherSkullEntity>> FLAMING_WITHER_SKULL = register("flaming_wither_skull", EntityType.Builder.m_20704_(FlamingWitherSkullEntity::new, MobCategory.MISC).m_20699_(0.8F, 0.8F).m_20702_(4).m_20717_(10));
/*  59 */   public static final RegistryObject<EntityType<BlueFlamingWitherSkullEntity>> BLUE_FLAMING_WITHER_SKULL = register("blue_flaming_wither_skull", EntityType.Builder.m_20704_(BlueFlamingWitherSkullEntity::new, MobCategory.MISC).m_20699_(0.8F, 0.8F).m_20702_(4).m_20717_(10));
/*  60 */   public static final RegistryObject<EntityType<SickenedZombie>> SICKENED_ZOMBIE = register("sickened_zombie", EntityType.Builder.m_20704_(SickenedZombie::new, MobCategory.MONSTER).m_20699_(0.6F, 1.95F).m_20702_(8));
/*  61 */   public static final RegistryObject<EntityType<SickenedSkeleton>> SICKENED_SKELETON = register("sickened_skeleton", EntityType.Builder.m_20704_(SickenedSkeleton::new, MobCategory.MONSTER).m_20699_(0.6F, 1.99F).m_20702_(8));
/*  62 */   public static final RegistryObject<EntityType<SickenedSpider>> SICKENED_SPIDER = register("sickened_spider", EntityType.Builder.m_20704_(SickenedSpider::new, MobCategory.MONSTER).m_20699_(1.6F, 1.1F).m_20702_(8));
/*  63 */   public static final RegistryObject<EntityType<SickenedCreeper>> SICKENED_CREEPER = register("sickened_creeper", EntityType.Builder.m_20704_(SickenedCreeper::new, MobCategory.MONSTER).m_20699_(0.6F, 1.7F).m_20702_(8));
/*  64 */   public static final RegistryObject<EntityType<SuperTNTEntity>> SUPER_TNT = register("super_tnt", EntityType.Builder.m_20704_(SuperTNTEntity::new, MobCategory.MISC).m_20719_().m_20699_(0.98F, 0.98F).m_20702_(10).m_20717_(10));
/*  65 */   public static final RegistryObject<EntityType<FormidibombEntity>> FORMIDIBOMB = register("formidibomb", EntityType.Builder.m_20704_(FormidibombEntity::new, MobCategory.MISC).m_20719_().m_20699_(0.98F, 0.98F).m_20702_(10));
/*  66 */   public static final RegistryObject<EntityType<CommandBlockEntity>> COMMAND_BLOCK = register("command_block", EntityType.Builder.m_20704_(CommandBlockEntity::new, MobCategory.MISC).m_20719_().m_20699_(1.0F, 1.0F).m_20702_(10));
/*  67 */   public static final RegistryObject<EntityType<WitheredSymbiontEntity>> WITHERED_SYMBIONT = register("withered_symbiont", EntityType.Builder.m_20704_(WitheredSymbiontEntity::new, MobCategory.MONSTER).m_20719_().m_20699_(1.2F, 3.8F).m_20702_(10));
/*  68 */   public static final RegistryObject<EntityType<WitherStormHeadEntity>> WITHER_STORM_HEAD = register("wither_storm_head", EntityType.Builder.m_20704_(WitherStormHeadEntity::new, MobCategory.MONSTER).m_20719_().m_20699_(5.0F, 5.0F).m_20702_(10));
/*  69 */   public static final RegistryObject<EntityType<TentacleEntity>> TENTACLE = register("tentacle", EntityType.Builder.m_20704_(TentacleEntity::new, MobCategory.MONSTER).m_20719_().m_20699_(7.5F, 9.5F).m_20702_(10));
/*  70 */   public static final RegistryObject<EntityType<SickenedVillager>> SICKENED_VILLAGER = register("sickened_villager", EntityType.Builder.m_20704_(SickenedVillager::new, MobCategory.MONSTER).m_20699_(0.6F, 1.95F).m_20702_(8));
/*  71 */   public static final RegistryObject<EntityType<SickenedPhantom>> SICKENED_PHANTOM = register("sickened_phantom", EntityType.Builder.m_20704_(SickenedPhantom::new, MobCategory.MONSTER).m_20699_(0.9F, 0.5F).m_20702_(8));
/*  72 */   public static final RegistryObject<EntityType<SickenedChicken>> SICKENED_CHICKEN = register("sickened_chicken", EntityType.Builder.m_20704_(SickenedChicken::new, MobCategory.MONSTER).m_20699_(0.4F, 0.7F).m_20702_(10));
/*  73 */   public static final RegistryObject<EntityType<SickenedParrot>> SICKENED_PARROT = register("sickened_parrot", EntityType.Builder.m_20704_(SickenedParrot::new, MobCategory.MONSTER).m_20699_(0.5F, 0.9F).m_20702_(8));
/*  74 */   public static final RegistryObject<EntityType<SickenedWolf>> SICKENED_WOLF = register("sickened_wolf", EntityType.Builder.m_20704_(SickenedWolf::new, MobCategory.MONSTER).m_20699_(0.6F, 0.85F).m_20702_(10));
/*  75 */   public static final RegistryObject<EntityType<SickenedCat>> SICKENED_CAT = register("sickened_cat", EntityType.Builder.m_20704_(SickenedCat::new, MobCategory.MONSTER).m_20699_(0.6F, 0.7F).m_20702_(8));
/*  76 */   public static final RegistryObject<EntityType<SickenedCow>> SICKENED_COW = register("sickened_cow", EntityType.Builder.m_20704_(SickenedCow::new, MobCategory.MONSTER).m_20699_(0.9F, 1.4F).m_20702_(10));
/*  77 */   public static final RegistryObject<EntityType<SickenedPig>> SICKENED_PIG = register("sickened_pig", EntityType.Builder.m_20704_(SickenedPig::new, MobCategory.MONSTER).m_20699_(0.9F, 0.9F).m_20702_(10));
/*  78 */   public static final RegistryObject<EntityType<SickenedMushroomCow>> SICKENED_MUSHROOM_COW = register("sickened_mushroom_cow", EntityType.Builder.m_20704_(SickenedMushroomCow::new, MobCategory.MONSTER).m_20699_(0.9F, 1.4F).m_20702_(10));
/*  79 */   public static final RegistryObject<EntityType<SickenedBee>> SICKENED_BEE = register("sickened_bee", EntityType.Builder.m_20704_(SickenedBee::new, MobCategory.MONSTER).m_20699_(0.7F, 0.6F).m_20702_(8));
/*  80 */   public static final RegistryObject<EntityType<SickenedPillager>> SICKENED_PILLAGER = register("sickened_pillager", EntityType.Builder.m_20704_(SickenedPillager::new, MobCategory.MONSTER).m_20699_(0.6F, 1.95F).m_20702_(8));
/*  81 */   public static final RegistryObject<EntityType<SickenedVindicator>> SICKENED_VINDICATOR = register("sickened_vindicator", EntityType.Builder.m_20704_(SickenedVindicator::new, MobCategory.MONSTER).m_20699_(0.6F, 1.95F).m_20702_(8));
/*  82 */   public static final RegistryObject<EntityType<SickenedIronGolem>> SICKENED_IRON_GOLEM = register("sickened_iron_golem", EntityType.Builder.m_20704_(SickenedIronGolem::new, MobCategory.MONSTER).m_20699_(1.4F, 2.7F).m_20702_(10).m_20719_().m_20714_(new Block[] { Blocks.f_152499_ }));
/*  83 */   public static final RegistryObject<EntityType<SickenedSnowGolem>> SICKENED_SNOW_GOLEM = register("sickened_snow_golem", EntityType.Builder.m_20704_(SickenedSnowGolem::new, MobCategory.MONSTER).m_20714_(new Block[] { Blocks.f_152499_ }).m_20699_(0.7F, 1.9F).m_20702_(8));
/*  84 */   public static final RegistryObject<EntityType<TaintedSlime>> TAINTED_SLIME = register("tainted_slime", EntityType.Builder.m_20704_(TaintedSlime::new, MobCategory.MONSTER).m_20698_().m_20699_(2.04F, 2.04F).m_20702_(10));
/*  85 */   public static final RegistryObject<EntityType<TentacleSpike>> TENTACLE_SPIKE = register("tentacle_spike", EntityType.Builder.m_20704_(TentacleSpike::new, MobCategory.MISC).m_20699_(0.5F, 1.4F).m_20702_(6).m_20717_(2));
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.world.entity.Entity> RegistryObject<EntityType<T>> register(String id, EntityType.Builder<T> builder) {
/*  89 */     return ENTITIES.register(id, () -> builder.m_20712_((new ResourceLocation("witherstormmod", id)).toString()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addEntityAttributes(EntityAttributeCreationEvent event) {
/*  94 */     event.put((EntityType)WITHER_STORM.get(), WitherStormEntity.createAttributes().m_22265_());
/*  95 */     event.put((EntityType)WITHER_STORM_SEGMENT.get(), WitherStormSegmentEntity.createAttributes().m_22265_());
/*  96 */     event.put((EntityType)SICKENED_ZOMBIE.get(), SickenedZombie.m_34328_().m_22265_());
/*  97 */     event.put((EntityType)SICKENED_SKELETON.get(), SickenedSkeleton.m_32166_().m_22265_());
/*  98 */     event.put((EntityType)SICKENED_SPIDER.get(), SickenedSpider.m_33815_().m_22265_());
/*  99 */     event.put((EntityType)SICKENED_CREEPER.get(), SickenedCreeper.m_32318_().m_22265_());
/* 100 */     event.put((EntityType)COMMAND_BLOCK.get(), CommandBlockEntity.createAttributes().m_22265_());
/* 101 */     event.put((EntityType)WITHERED_SYMBIONT.get(), WitheredSymbiontEntity.createAttributes().m_22265_());
/* 102 */     event.put((EntityType)WITHER_STORM_HEAD.get(), WitherStormHeadEntity.createAttributes().m_22265_());
/* 103 */     event.put((EntityType)TENTACLE.get(), TentacleEntity.createAttributes().m_22265_());
/* 104 */     event.put((EntityType)SICKENED_VILLAGER.get(), SickenedVillager.m_34328_().m_22265_());
/* 105 */     event.put((EntityType)SICKENED_PHANTOM.get(), SickenedPhantom.createAttributes().m_22265_());
/* 106 */     event.put((EntityType)SICKENED_CHICKEN.get(), SickenedChicken.m_28263_().m_22265_());
/* 107 */     event.put((EntityType)SICKENED_PARROT.get(), SickenedParrot.m_29438_().m_22265_());
/* 108 */     event.put((EntityType)SICKENED_WOLF.get(), SickenedWolf.m_30425_().m_22265_());
/* 109 */     event.put((EntityType)SICKENED_CAT.get(), SickenedCat.m_28168_().m_22265_());
/* 110 */     event.put((EntityType)SICKENED_COW.get(), SickenedCow.m_28307_().m_22265_());
/* 111 */     event.put((EntityType)SICKENED_PIG.get(), SickenedPig.m_29503_().m_22265_());
/* 112 */     event.put((EntityType)SICKENED_MUSHROOM_COW.get(), SickenedMushroomCow.m_28307_().m_22265_());
/* 113 */     event.put((EntityType)SICKENED_BEE.get(), SickenedBee.m_27858_().m_22265_());
/* 114 */     event.put((EntityType)SICKENED_PILLAGER.get(), SickenedPillager.m_33307_().m_22265_());
/* 115 */     event.put((EntityType)SICKENED_VINDICATOR.get(), SickenedVindicator.m_34104_().m_22265_());
/* 116 */     event.put((EntityType)SICKENED_IRON_GOLEM.get(), SickenedIronGolem.createAttributes().m_22265_());
/* 117 */     event.put((EntityType)SICKENED_SNOW_GOLEM.get(), SickenedSnowGolem.m_29934_().m_22265_());
/* 118 */     event.put((EntityType)TAINTED_SLIME.get(), TaintedSlime.createAttributes().m_22265_());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
/* 123 */     event.register((EntityType)SICKENED_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219013_, SpawnPlacementRegisterEvent.Operation.OR);
/* 124 */     event.register((EntityType)SICKENED_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219013_, SpawnPlacementRegisterEvent.Operation.OR);
/* 125 */     event.register((EntityType)SICKENED_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219013_, SpawnPlacementRegisterEvent.Operation.OR);
/* 126 */     event.register((EntityType)SICKENED_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219013_, SpawnPlacementRegisterEvent.Operation.OR);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModEntityTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */