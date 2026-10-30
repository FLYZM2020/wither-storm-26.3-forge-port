/*     */ package nonamecrackers2.witherstormmod;
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoField;
/*     */ import net.minecraft.advancements.CriteriaTriggers;
/*     */ import net.minecraft.advancements.CriterionTrigger;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.item.ItemProperties;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.packs.resources.PreparableReloadListener;
/*     */ import net.minecraft.server.packs.resources.ReloadableResourceManager;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.CrossbowItem;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.FlowerPotBlock;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.command.EntitySelectorManager;
/*     */ import net.minecraftforge.common.command.IEntitySelectorType;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.fml.ModLoadingContext;
/*     */ import net.minecraftforge.fml.config.IConfigSpec;
/*     */ import net.minecraftforge.fml.config.ModConfig;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*     */ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
/*     */ import nonamecrackers2.crackerslib.common.extending.BlockEntityTypeExtender;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.WitherStormWorldInteractions;
/*     */ import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
/*     */ import nonamecrackers2.witherstormmod.client.audio.SoundManagersRefresher;
/*     */ import nonamecrackers2.witherstormmod.client.capability.BowelsEffectsManager;
/*     */ import nonamecrackers2.witherstormmod.client.event.ParticleEvents;
/*     */ import nonamecrackers2.witherstormmod.client.event.WitherStormModClientConfigEvents;
/*     */ import nonamecrackers2.witherstormmod.client.event.WitherStormModRegisterBlockColors;
/*     */ import nonamecrackers2.witherstormmod.client.gui.overlay.OverlayRenderers;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientEvents;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRecipeBookTypes;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModShaders;
/*     */ import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;
/*     */ import nonamecrackers2.witherstormmod.client.shader.PostProcessingShaders;
/*     */ import nonamecrackers2.witherstormmod.common.command.argument.entityselector.WitherStormSelector;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
/*     */ import nonamecrackers2.witherstormmod.common.event.WitherStormModClusterInteractionEvents;
/*     */ import nonamecrackers2.witherstormmod.common.event.WitherStormModDataEvents;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModActivities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEvents;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItemTabs;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMemoryTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMenuTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPaintingTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPotions;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSensorTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModStats;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSymbiontSpellTypes;
/*     */ import nonamecrackers2.witherstormmod.common.item.FormidiBladeItem;
/*     */ import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.maven.artifact.versioning.ArtifactVersion;
/*     */ 
/*     */ @Mod("witherstormmod")
/*     */ public class WitherStormMod {
/*  90 */   public static final Logger LOGGER = LogManager.getLogger();
/*     */   public static final String MOD_ID = "witherstormmod";
/*  92 */   private static final ResourceLocation BOWELS = new ResourceLocation("witherstormmod", "bowels");
/*  93 */   public static final LocalDate DATE = LocalDate.now();
/*     */   
/*     */   private static ArtifactVersion version;
/*     */   private static boolean isAprilFools;
/*     */   
/*     */   public WitherStormMod() {
/*  99 */     ModLoadingContext context = ModLoadingContext.get();
/* 100 */     version = context.getActiveContainer().getModInfo().getVersion();
/* 101 */     context.registerConfig(ModConfig.Type.CLIENT, (IConfigSpec)WitherStormModConfig.CLIENT_SPEC);
/* 102 */     context.registerConfig(ModConfig.Type.COMMON, (IConfigSpec)WitherStormModConfig.COMMON_SPEC);
/* 103 */     context.registerConfig(ModConfig.Type.SERVER, (IConfigSpec)WitherStormModConfig.SERVER_SPEC);
/* 104 */     IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
/* 105 */     modEventBus.addListener(WitherStormModEntityTypes::addEntityAttributes);
/* 106 */     modEventBus.addListener(WitherStormModEntityTypes::registerSpawnPlacements);
/* 107 */     modEventBus.addListener(WitherStormModCapabilities::registerCapabilities);
/* 108 */     modEventBus.addListener(WitherStormModDataEvents::gatherData);
/* 109 */     modEventBus.addListener(WitherStormModConfig::registerPresets);
/* 110 */     modEventBus.addListener(WitherStormModClusterInteractionEvents::registerClusterInteractions);
/* 111 */     modEventBus.addListener(WitherStormModRegistries::registerRegistries);
/* 112 */     modEventBus.addListener(this::commonSetup);
/* 113 */     modEventBus.addListener(this::clientSetup);
/*     */     
/* 115 */     WitherStormModItemTabs.TABS.register(modEventBus);
/* 116 */     WitherStormModBlocks.BLOCKS.register(modEventBus);
/* 117 */     WitherStormModEffects.EFFECTS.register(modEventBus);
/* 118 */     WitherStormModEntityTypes.ENTITIES.register(modEventBus);
/* 119 */     WitherStormModFeatures.FEATURES.register(modEventBus);
/* 120 */     WitherStormModItems.ITEMS.register(modEventBus);
/* 121 */     WitherStormModParticleTypes.PARTICLE_TYPES.register(modEventBus);
/* 122 */     WitherStormModSoundEvents.SOUND_EVENTS.register(modEventBus);
/* 123 */     WitherStormModStructures.STRUCTURE_FEATURES.register(modEventBus);
/* 124 */     WitherStormModBlockEntityTypes.BLOCK_ENTITIES.register(modEventBus);
/* 125 */     WitherStormModDataSerializers.DATA_SERIALIZERS.register(modEventBus);
/* 126 */     WitherStormModAttributes.ATTRIBUTES.register(modEventBus);
/* 127 */     WitherStormModMemoryTypes.MEMORY_MODULE_TYPES.register(modEventBus);
/* 128 */     WitherStormModSensorTypes.SENSOR_TYPES.register(modEventBus);
/* 129 */     WitherStormModActivities.ACTIVITIES.register(modEventBus);
/* 130 */     WitherStormModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
/* 131 */     WitherStormModPaintingTypes.PAINTING_MOTIVES.register(modEventBus);
/* 132 */     WitherStormModMenuTypes.MENU_TYPES.register(modEventBus);
/* 133 */     WitherStormModRecipeTypes.RECIPE_TYPES.register(modEventBus);
/* 134 */     WitherStormModPotions.POTIONS.register(modEventBus);
/* 135 */     WitherStormModSymbiontSpellTypes.register(modEventBus);
/*     */     
/* 137 */     IEventBus forgeBus = MinecraftForge.EVENT_BUS;
/* 138 */     forgeBus.addGenericListener(Level.class, WitherStormModCapabilities::attachWorldCapabilities);
/* 139 */     forgeBus.addGenericListener(Entity.class, WitherStormModCapabilities::attachEntityCapabilities);
/* 140 */     forgeBus.addListener(WitherStormModDataEvents::addResourceListeners);
/* 141 */     DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     int month = DATE.get(ChronoField.MONTH_OF_YEAR);
/* 166 */     int day = DATE.get(ChronoField.DAY_OF_MONTH);
/* 167 */     isAprilFools = (month == 4 && day == 1);
/*     */     
/* 169 */     HeadManager.bootstrap();
/*     */   }
/*     */ 
/*     */   
/*     */   private void commonSetup(FMLCommonSetupEvent event) {
/* 174 */     WitherStormModEvents.registerEvents();
/* 175 */     WitherStormModStructures.registerPieceTypes();
/* 176 */     WitherStormModPacketHandlers.registerPackets();
/* 177 */     WitherStormModStats.register();
/* 178 */     ((FlowerPotBlock)Blocks.f_50276_).addPlant(WitherStormModBlocks.TAINTED_MUSHROOM.getId(), (Supplier)WitherStormModBlocks.POTTED_TAINTED_MUSHROOM);
/* 179 */     EntitySelectorManager.register("w", (IEntitySelectorType)new WitherStormSelector());
/* 180 */     event.enqueueWork(() -> {
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.PLAY_DEAD_TRIGGER);
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.REVIVAL_TRIGGER);
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.ESCAPE_STORM);
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.CURED_SICKENED_MOB);
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.ACTIVATE_SUPER_BEACON);
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.RING_BELL_NEAR_STORM);
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.SUMMON_MOB_SUPER_BEACON);
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.LINK_AMULET);
/*     */           CriteriaTriggers.m_10595_((CriterionTrigger)WitherStormModCriteriaTriggers.NEARLY_KILL_WITHER_STORM);
/*     */           WitherStormModItems.registerBrewingRecipes();
/*     */           WitherStormWorldInteractions.initialize();
/*     */           BlockEntityTypeExtender.addToBlockEntityType(BlockEntityType.f_58924_, new Block[] { (Block)WitherStormModBlocks.TAINTED_SIGN.get(), (Block)WitherStormModBlocks.TAINTED_WALL_SIGN.get() });
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clientSetup(FMLClientSetupEvent event) {
/* 199 */     WitherStormModClientEvents.registerEvents();
/* 200 */     WitherStormModMenuScreens.register();
/* 201 */     event.enqueueWork(() -> {
/*     */           Minecraft mc = Minecraft.m_91087_();
/*     */           
/*     */           ReloadableResourceManager manager = (ReloadableResourceManager)mc.m_91098_();
/*     */           
/*     */           manager.m_7217_((PreparableReloadListener)SoundManagersRefresher.INSTANCE);
/*     */           
/*     */           ItemProperties.register(Items.f_42717_, new ResourceLocation("witherstormmod", "ender_pearl"), ());
/*     */           FormidiBladeItem.registerItemProperty();
/*     */         });
/* 211 */     Contributors.getContributors();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ServerLevel bowels(ServerLevel world) {
/* 216 */     return world.m_7654_().m_129880_(ResourceKey.m_135785_(Registries.f_256858_, bowelsLocation()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResourceLocation bowelsLocation() {
/* 221 */     return BOWELS;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArtifactVersion getVersion() {
/* 226 */     return version;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAprilFools() {
/* 231 */     return isAprilFools;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResourceLocation id(String path) {
/* 236 */     return new ResourceLocation("witherstormmod", path);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\WitherStormMod.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */