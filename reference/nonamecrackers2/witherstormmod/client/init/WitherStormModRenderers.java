/*     */ package nonamecrackers2.witherstormmod.client.init;
/*     */ 
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.BeeModel;
/*     */ import net.minecraft.client.model.ChickenModel;
/*     */ import net.minecraft.client.model.CowModel;
/*     */ import net.minecraft.client.model.CreeperModel;
/*     */ import net.minecraft.client.model.EntityModel;
/*     */ import net.minecraft.client.model.HumanoidModel;
/*     */ import net.minecraft.client.model.IllagerModel;
/*     */ import net.minecraft.client.model.ParrotModel;
/*     */ import net.minecraft.client.model.PhantomModel;
/*     */ import net.minecraft.client.model.PigModel;
/*     */ import net.minecraft.client.model.SkeletonModel;
/*     */ import net.minecraft.client.model.SnowGolemModel;
/*     */ import net.minecraft.client.model.SpiderModel;
/*     */ import net.minecraft.client.model.ZombieVillagerModel;
/*     */ import net.minecraft.client.model.geom.LayerDefinitions;
/*     */ import net.minecraft.client.model.geom.ModelLayerLocation;
/*     */ import net.minecraft.client.model.geom.ModelLayers;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.entity.LivingEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*     */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraftforge.client.event.EntityRenderersEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.AbsorbtionLayer;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.WitherSicknessLayer;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.FlamingWitherSkullModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.SantaHatModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.TaintedSlimeModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleSpikeModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitherStormHeadModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitheredSymbiontModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock.RibcageModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedIronGolemModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedVillagerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormCommandBlockModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDestroyerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDismantledModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDestroyerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormGrowingHunchbackModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback1_1Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback1_2Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback2_1Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback3_1Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback3_2Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchbackModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateEvolvedDestroyerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateEvolvedDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormPregnantHunchbackModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormSegmentModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormTornEvolvedDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormModRenderers
/*     */ {
/* 108 */   public static final ModelLayerLocation WITHER_STORM_0 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase0");
/* 109 */   public static final ModelLayerLocation WITHER_STORM_1 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase1");
/* 110 */   public static final ModelLayerLocation WITHER_STORM_1_1 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase1_1");
/* 111 */   public static final ModelLayerLocation WITHER_STORM_1_2 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase1_2");
/* 112 */   public static final ModelLayerLocation WITHER_STORM_2 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase2");
/* 113 */   public static final ModelLayerLocation WITHER_STORM_2_1 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase2_1");
/* 114 */   public static final ModelLayerLocation WITHER_STORM_3 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase3");
/* 115 */   public static final ModelLayerLocation WITHER_STORM_3_1 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase3_1");
/* 116 */   public static final ModelLayerLocation WITHER_STORM_3_2 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase3_2");
/* 117 */   public static final ModelLayerLocation WITHER_STORM_4 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase4");
/* 118 */   public static final ModelLayerLocation WITHER_STORM_4_5 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase4_5");
/* 119 */   public static final ModelLayerLocation WITHER_STORM_5 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase5");
/* 120 */   public static final ModelLayerLocation WITHER_STORM_5_5 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase5_5");
/* 121 */   public static final ModelLayerLocation WITHER_STORM_6 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase6");
/* 122 */   public static final ModelLayerLocation WITHER_STORM_6_5 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase6_5");
/* 123 */   public static final ModelLayerLocation WITHER_STORM_7 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase7");
/*     */   
/* 125 */   public static final ModelLayerLocation WITHER_STORM_DISMANTLED = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "dismantled");
/* 126 */   public static final ModelLayerLocation WITHER_STORM_TORN = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "torn");
/*     */   
/* 128 */   public static final ModelLayerLocation WITHER_STORM_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "armor");
/* 129 */   public static final ModelLayerLocation WITHER_STORM_SEGMENT = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm_segment"), "main");
/* 130 */   public static final ModelLayerLocation FLAMING_WITHER_SKULL = new ModelLayerLocation(new ResourceLocation("witherstormmod", "flaming_wither_skull"), "main");
/* 131 */   public static final ModelLayerLocation SICKENED_ZOMBIE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_zombie"), "main");
/* 132 */   public static final ModelLayerLocation SICKENED_ZOMBIE_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_zombie"), "inner_armor");
/* 133 */   public static final ModelLayerLocation SICKENED_ZOMBIE_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_zombie"), "outer_armor");
/* 134 */   public static final ModelLayerLocation SICKENED_SKELETON = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_skeleton"), "main");
/* 135 */   public static final ModelLayerLocation SICKENED_SKELETON_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_skeleton"), "inner_armor");
/* 136 */   public static final ModelLayerLocation SICKENED_SKELETON_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_skeleton"), "outer_armor");
/* 137 */   public static final ModelLayerLocation SICKENED_SPIDER = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_spider"), "main");
/* 138 */   public static final ModelLayerLocation SICKENED_CREEPER = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_creeper"), "main");
/* 139 */   public static final ModelLayerLocation SICKENED_CREEPER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_creeper"), "armor");
/* 140 */   public static final ModelLayerLocation SICKENED_VILLAGER = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_villager"), "main");
/* 141 */   public static final ModelLayerLocation SICKENED_VILLAGER_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_villager"), "inner_armor");
/* 142 */   public static final ModelLayerLocation SICKENED_VILLAGER_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_villager"), "outer_armor");
/* 143 */   public static final ModelLayerLocation SICKENED_PHANTOM = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_phantom"), "main");
/* 144 */   public static final ModelLayerLocation SICKENED_CHICKEN = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_chicken"), "main");
/* 145 */   public static final ModelLayerLocation SICKENED_PARROT = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_parrot"), "main");
/* 146 */   public static final ModelLayerLocation SICKENED_COW = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_cow"), "main");
/* 147 */   public static final ModelLayerLocation SICKENED_PIG = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_pig"), "main");
/* 148 */   public static final ModelLayerLocation SICKENED_BEE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_bee"), "main");
/* 149 */   public static final ModelLayerLocation SICKENED_MUSHROOM_COW = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_mushroom_cow"), "main");
/* 150 */   public static final ModelLayerLocation SICKENED_PILLAGER = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_pillager"), "main");
/* 151 */   public static final ModelLayerLocation SICKENED_VINDICATOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_vindicator"), "main");
/* 152 */   public static final ModelLayerLocation SICKENED_IRON_GOLEM = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_iron_golem"), "main");
/* 153 */   public static final ModelLayerLocation SICKENED_SNOW_GOLEM = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_snow_golem"), "main");
/* 154 */   public static final ModelLayerLocation SICKENED_GOAT = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_goat"), "main");
/* 155 */   public static final ModelLayerLocation WITHERED_SYMBIONT = new ModelLayerLocation(new ResourceLocation("witherstormmod", "withered_symbiont"), "main");
/* 156 */   public static final ModelLayerLocation SYMBIONT_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "withered_symbiont"), "inner_armor");
/* 157 */   public static final ModelLayerLocation SYMBIONT_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "withered_symbiont"), "outer_armor");
/* 158 */   public static final ModelLayerLocation WITHER_STORM_HEAD = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm_head"), "main");
/* 159 */   public static final ModelLayerLocation TENTACLE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "tentacle"), "main");
/* 160 */   public static final ModelLayerLocation RIBCAGE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "ribcage"), "main");
/* 161 */   public static final ModelLayerLocation TAINTED_SLIME = new ModelLayerLocation(new ResourceLocation("witherstormmod", "tainted_slime"), "main");
/* 162 */   public static final ModelLayerLocation TENTACLE_SPIKE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "tentacle_spike"), "main");
/* 163 */   public static final ModelLayerLocation SANTA_HAT = new ModelLayerLocation(WitherStormMod.id("santa_hat"), "main");
/* 164 */   public static final ModelLayerLocation TAINTED_SIGN = ModelLayers.m_171291_(WitherStormModBlocks.TAINTED);
/* 165 */   public static final ModelLayerLocation TAINTED_HANGING_SIGN = ModelLayers.m_247439_(WitherStormModBlocks.TAINTED);
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
/* 170 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.WITHER_STORM.get(), nonamecrackers2.witherstormmod.client.renderer.entity.WitherStormRenderer::new);
/* 171 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get(), nonamecrackers2.witherstormmod.client.renderer.entity.BlockClusterRenderer::new);
/* 172 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.WITHER_STORM_SEGMENT.get(), nonamecrackers2.witherstormmod.client.renderer.entity.WitherStormSegmentRenderer::new);
/* 173 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.FLAMING_WITHER_SKULL.get(), nonamecrackers2.witherstormmod.client.renderer.entity.FlamingWitherSkullRenderer::new);
/* 174 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.BLUE_FLAMING_WITHER_SKULL.get(), nonamecrackers2.witherstormmod.client.renderer.entity.BlueFlamingWitherSkullRenderer::new);
/* 175 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedZombieRenderer::new);
/* 176 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedSkeletonRenderer::new);
/* 177 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedSpiderRenderer::new);
/* 178 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedCreeperRenderer::new);
/* 179 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SUPER_TNT.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SuperTNTRenderer::new);
/* 180 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.FORMIDIBOMB.get(), nonamecrackers2.witherstormmod.client.renderer.entity.FormidibombRenderer::new);
/* 181 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.COMMAND_BLOCK.get(), nonamecrackers2.witherstormmod.client.renderer.entity.CommandBlockRenderer::new);
/* 182 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get(), nonamecrackers2.witherstormmod.client.renderer.entity.WitheredSymbiontRenderer::new);
/* 183 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.WITHER_STORM_HEAD.get(), nonamecrackers2.witherstormmod.client.renderer.entity.WitherStormHeadRenderer::new);
/* 184 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.TENTACLE.get(), nonamecrackers2.witherstormmod.client.renderer.entity.TentacleRenderer::new);
/* 185 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedVillagerRenderer::new);
/* 186 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedPhantomRenderer::new);
/* 187 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedChickenRenderer::new);
/* 188 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedParrotRenderer::new);
/* 189 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedWolfRenderer::new);
/* 190 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedCatRenderer::new);
/* 191 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedCowRenderer::new);
/* 192 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedPigRenderer::new);
/* 193 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedMushroomCowRenderer::new);
/* 194 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedBeeRenderer::new);
/* 195 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedPillagerRenderer::new);
/* 196 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedVindicatorRenderer::new);
/* 197 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedIronGolemRenderer::new);
/* 198 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), nonamecrackers2.witherstormmod.client.renderer.entity.SickenedSnowGolemRenderer::new);
/* 199 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.TAINTED_SLIME.get(), nonamecrackers2.witherstormmod.client.renderer.entity.TaintedSlimeRenderer::new);
/* 200 */     event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.TENTACLE_SPIKE.get(), nonamecrackers2.witherstormmod.client.renderer.entity.TentacleSpikeRenderer::new);
/*     */     
/* 202 */     event.registerBlockEntityRenderer((BlockEntityType)WitherStormModBlockEntityTypes.SUPER_BEACON.get(), nonamecrackers2.witherstormmod.client.renderer.blockentity.SuperBeaconRenderer::new);
/* 203 */     event.registerBlockEntityRenderer((BlockEntityType)WitherStormModBlockEntityTypes.SUPER_SUPPORT_BEACON.get(), nonamecrackers2.witherstormmod.client.renderer.blockentity.SuperSupportBeaconRenderer::new);
/* 204 */     event.registerBlockEntityRenderer((BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get(), nonamecrackers2.witherstormmod.client.renderer.blockentity.WitheredPhlegmRenderer::new);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
/* 210 */     LayerDefinition humanoid = LayerDefinition.m_171565_(HumanoidModel.m_170681_(CubeDeformation.f_171458_, 0.0F), 64, 64);
/* 211 */     LayerDefinition humanoidInner = LayerDefinition.m_171565_(HumanoidModel.m_170681_(LayerDefinitions.f_171107_, 0.0F), 64, 32);
/* 212 */     LayerDefinition humanoidOuter = LayerDefinition.m_171565_(HumanoidModel.m_170681_(LayerDefinitions.f_171106_, 0.0F), 64, 32);
/*     */     
/* 214 */     event.registerLayerDefinition(WITHER_STORM_0, () -> WitherStormCommandBlockModel.createLayerDefinition(CubeDeformation.f_171458_));
/*     */     
/* 216 */     event.registerLayerDefinition(WITHER_STORM_1, () -> WitherStormHunchbackModel.createLayerDefinition(CubeDeformation.f_171458_));
/* 217 */     event.registerLayerDefinition(WITHER_STORM_1_1, () -> WitherStormHunchback1_1Model.createLayerDefinition(CubeDeformation.f_171458_));
/* 218 */     event.registerLayerDefinition(WITHER_STORM_1_2, () -> WitherStormHunchback1_2Model.createLayerDefinition(CubeDeformation.f_171458_));
/*     */     
/* 220 */     event.registerLayerDefinition(WITHER_STORM_2, () -> WitherStormGrowingHunchbackModel.createLayerDefinition(CubeDeformation.f_171458_));
/* 221 */     event.registerLayerDefinition(WITHER_STORM_2_1, () -> WitherStormHunchback2_1Model.createLayerDefinition(CubeDeformation.f_171458_));
/*     */     
/* 223 */     event.registerLayerDefinition(WITHER_STORM_3, () -> WitherStormPregnantHunchbackModel.createLayerDefinition(CubeDeformation.f_171458_));
/* 224 */     event.registerLayerDefinition(WITHER_STORM_3_1, () -> WitherStormHunchback3_1Model.createLayerDefinition(CubeDeformation.f_171458_));
/* 225 */     event.registerLayerDefinition(WITHER_STORM_3_2, () -> WitherStormHunchback3_2Model.createLayerDefinition(CubeDeformation.f_171458_));
/*     */     
/* 227 */     event.registerLayerDefinition(WITHER_STORM_4, WitherStormDestroyerModel::createLayerDefinition);
/* 228 */     event.registerLayerDefinition(WITHER_STORM_4_5, WitherStormIntermediateEvolvedDestroyerModel::createLayerDefinition);
/*     */     
/* 230 */     event.registerLayerDefinition(WITHER_STORM_5, WitherStormEvolvedDestroyerModel::createLayerDefinition);
/* 231 */     event.registerLayerDefinition(WITHER_STORM_5_5, WitherStormIntermediateDevourerModel::createLayerDefinition);
/*     */     
/* 233 */     event.registerLayerDefinition(WITHER_STORM_6, WitherStormDevourerModel::createLayerDefinition);
/* 234 */     event.registerLayerDefinition(WITHER_STORM_6_5, WitherStormIntermediateEvolvedDevourerModel::createLayerDefinition);
/*     */     
/* 236 */     event.registerLayerDefinition(WITHER_STORM_7, WitherStormEvolvedDevourerModel::createLayerDefinition);
/* 237 */     event.registerLayerDefinition(WITHER_STORM_DISMANTLED, WitherStormDismantledModel::createLayerDefinition);
/* 238 */     event.registerLayerDefinition(WITHER_STORM_TORN, WitherStormTornEvolvedDevourerModel::createLayerDefinition);
/* 239 */     event.registerLayerDefinition(WITHER_STORM_ARMOR, () -> WitherStormCommandBlockModel.createLayerDefinition(LayerDefinitions.f_171107_));
/* 240 */     event.registerLayerDefinition(WITHER_STORM_SEGMENT, WitherStormSegmentModel::createLayerDefinition);
/* 241 */     event.registerLayerDefinition(FLAMING_WITHER_SKULL, FlamingWitherSkullModel::createLayerDefinition);
/* 242 */     event.registerLayerDefinition(SICKENED_ZOMBIE, () -> humanoid);
/* 243 */     event.registerLayerDefinition(SICKENED_ZOMBIE_INNER_ARMOR, () -> humanoidInner);
/* 244 */     event.registerLayerDefinition(SICKENED_ZOMBIE_OUTER_ARMOR, () -> humanoidOuter);
/* 245 */     event.registerLayerDefinition(SICKENED_SKELETON, SkeletonModel::m_170942_);
/* 246 */     event.registerLayerDefinition(SICKENED_SKELETON_INNER_ARMOR, () -> humanoidInner);
/* 247 */     event.registerLayerDefinition(SICKENED_SKELETON_OUTER_ARMOR, () -> humanoidOuter);
/* 248 */     event.registerLayerDefinition(SICKENED_CREEPER, () -> CreeperModel.m_170525_(CubeDeformation.f_171458_));
/* 249 */     event.registerLayerDefinition(SICKENED_CREEPER_ARMOR, () -> CreeperModel.m_170525_(new CubeDeformation(2.0F)));
/* 250 */     event.registerLayerDefinition(SICKENED_SPIDER, SpiderModel::m_170985_);
/* 251 */     event.registerLayerDefinition(SICKENED_VILLAGER, SickenedVillagerModel::createBodyLayer);
/* 252 */     event.registerLayerDefinition(SICKENED_VILLAGER_INNER_ARMOR, () -> ZombieVillagerModel.m_171093_(new CubeDeformation(0.5F)));
/* 253 */     event.registerLayerDefinition(SICKENED_VILLAGER_OUTER_ARMOR, () -> ZombieVillagerModel.m_171093_(new CubeDeformation(1.0F)));
/* 254 */     event.registerLayerDefinition(SICKENED_PHANTOM, PhantomModel::m_170789_);
/* 255 */     event.registerLayerDefinition(SICKENED_CHICKEN, ChickenModel::m_170491_);
/* 256 */     event.registerLayerDefinition(SICKENED_PARROT, ParrotModel::m_170781_);
/* 257 */     event.registerLayerDefinition(SICKENED_COW, CowModel::m_170516_);
/* 258 */     event.registerLayerDefinition(SICKENED_PIG, () -> PigModel.m_170800_(CubeDeformation.f_171458_));
/* 259 */     event.registerLayerDefinition(SICKENED_MUSHROOM_COW, CowModel::m_170516_);
/* 260 */     event.registerLayerDefinition(SICKENED_BEE, BeeModel::m_170440_);
/* 261 */     event.registerLayerDefinition(SICKENED_PILLAGER, IllagerModel::m_170689_);
/* 262 */     event.registerLayerDefinition(SICKENED_VINDICATOR, IllagerModel::m_170689_);
/* 263 */     event.registerLayerDefinition(SICKENED_IRON_GOLEM, SickenedIronGolemModel::createBodyLayer);
/* 264 */     event.registerLayerDefinition(SICKENED_SNOW_GOLEM, SnowGolemModel::m_170966_);
/* 265 */     event.registerLayerDefinition(WITHERED_SYMBIONT, WitheredSymbiontModel::createLayerDefinition);
/* 266 */     event.registerLayerDefinition(SYMBIONT_INNER_ARMOR, () -> humanoidInner);
/* 267 */     event.registerLayerDefinition(SYMBIONT_OUTER_ARMOR, () -> humanoidOuter);
/* 268 */     event.registerLayerDefinition(WITHER_STORM_HEAD, WitherStormHeadModel::createLayerDefinition);
/* 269 */     event.registerLayerDefinition(TENTACLE, TentacleModel::createLayerDefinition);
/* 270 */     event.registerLayerDefinition(RIBCAGE, RibcageModel::createLayerDefinition);
/* 271 */     event.registerLayerDefinition(TAINTED_SLIME, TaintedSlimeModel::createLayerDefinition);
/* 272 */     event.registerLayerDefinition(TENTACLE_SPIKE, TentacleSpikeModel::createLayerDefinition);
/* 273 */     event.registerLayerDefinition(SANTA_HAT, SantaHatModel::createLayerDefinition);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void addRendererLayers(EntityRenderersEvent.AddLayers event) {
/* 279 */     Minecraft mc = Minecraft.m_91087_();
/* 280 */     EntityRenderDispatcher manager = mc.m_91290_();
/* 281 */     manager.getSkinMap().forEach((type, renderer) -> {
/*     */           if (renderer instanceof LivingEntityRenderer) {
/*     */             LivingEntityRenderer<Player, EntityModel<Player>> livingRenderer = (LivingEntityRenderer<Player, EntityModel<Player>>)renderer;
/*     */             
/*     */             if (((Boolean)WitherStormModConfig.CLIENT.witherSicknessLayer.get()).booleanValue()) {
/*     */               livingRenderer.m_115326_((RenderLayer)new WitherSicknessLayer((RenderLayerParent)livingRenderer));
/*     */             }
/*     */             
/*     */             livingRenderer.m_115326_((RenderLayer)new AbsorbtionLayer((RenderLayerParent)livingRenderer));
/*     */           } 
/*     */         });
/* 292 */     for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : (Iterable<Map.Entry<EntityType<?>, EntityRenderer<?>>>)manager.f_114362_.entrySet()) {
/*     */       
/* 294 */       EntityRenderer<?> renderer = entry.getValue();
/* 295 */       if (renderer instanceof LivingEntityRenderer) {
/*     */ 
/*     */         
/* 298 */         LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer = (LivingEntityRenderer)renderer;
/* 299 */         if (((Boolean)WitherStormModConfig.CLIENT.witherSicknessLayer.get()).booleanValue())
/* 300 */           livingRenderer.m_115326_((RenderLayer)new WitherSicknessLayer((RenderLayerParent)livingRenderer)); 
/* 301 */         livingRenderer.m_115326_((RenderLayer)new AbsorbtionLayer((RenderLayerParent)livingRenderer));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\init\WitherStormModRenderers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */