package nonamecrackers2.witherstormmod;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.entity.SuperTntEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import nonamecrackers2.witherstormmod.common.config.StormConfig;
import nonamecrackers2.witherstormmod.common.command.StormCommands;
import nonamecrackers2.witherstormmod.common.event.WitherStormPatternChecker;
import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSounds;
import nonamecrackers2.witherstormmod.common.init.WitherStormModTickets;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntities;
import nonamecrackers2.witherstormmod.common.sickness.WitherSicknessTracker;

@Mod(WitherStormMod.MOD_ID)
public final class WitherStormMod {
    public static final String MOD_ID = "witherstormmod";
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);
    public static final RegistryObject<SimpleParticleType> COMMAND_PARTICLE = PARTICLES.register("command_block", () -> new SimpleParticleType(true) {});
    public static final RegistryObject<EntityType<WitherStormEntity>> WITHER_STORM = ENTITIES.register("wither_storm", () ->
        EntityType.Builder.of(WitherStormEntity::new, MobCategory.MONSTER)
            .sized(0.9F, 3.5F).fireImmune().clientTrackingRange(32).build(ENTITIES.key("wither_storm")));
    public static final RegistryObject<EntityType<WitherStormSegmentEntity>> WITHER_STORM_SEGMENT = ENTITIES.register("wither_storm_segment", () ->
        EntityType.Builder.<WitherStormSegmentEntity>of(WitherStormSegmentEntity::new, MobCategory.MONSTER)
            .sized(30.0F, 25.0F).fireImmune().clientTrackingRange(32).build(ENTITIES.key("wither_storm_segment")));
    public static final RegistryObject<EntityType<FormidibombEntity>> FORMIDIBOMB = ENTITIES.register("formidibomb", () ->
        EntityType.Builder.<FormidibombEntity>of(FormidibombEntity::new, MobCategory.MISC)
            .sized(0.98F, 0.98F).clientTrackingRange(16).updateInterval(10).build(ENTITIES.key("formidibomb")));
    public static final RegistryObject<EntityType<SuperTntEntity>> SUPER_TNT = ENTITIES.register("super_tnt", () ->
        EntityType.Builder.<SuperTntEntity>of(SuperTntEntity::new, MobCategory.MISC)
            .sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build(ENTITIES.key("super_tnt")));
    public static final RegistryObject<EntityType<WitheredSymbiontEntity>> WITHERED_SYMBIONT = ENTITIES.register("withered_symbiont", () ->
        EntityType.Builder.<WitheredSymbiontEntity>of(WitheredSymbiontEntity::new, MobCategory.MONSTER)
            .sized(0.7F, 2.4F).fireImmune().clientTrackingRange(10).build(ENTITIES.key("withered_symbiont")));

    public WitherStormMod(FMLJavaModLoadingContext context) {
        ENTITIES.register(context.getModBusGroup());
        PARTICLES.register(context.getModBusGroup());
        WitherStormModBlocks.BLOCKS.register(context.getModBusGroup());
        WitherStormModBlockEntities.TYPES.register(context.getModBusGroup());
        WitherStormModAttributes.ATTRIBUTES.register(context.getModBusGroup());
        WitherStormModSounds.SOUNDS.register(context.getModBusGroup());
        WitherStormModTickets.TYPES.register(context.getModBusGroup());
        WitherStormModEffects.EFFECTS.register(context.getModBusGroup());
        WitherStormModItems.ITEMS.register(context.getModBusGroup());
        context.registerConfig(ModConfig.Type.SERVER, StormConfig.SPEC);
        EntityAttributeCreationEvent.BUS.addListener(WitherStormMod::registerAttributes);
        RegisterCommandsEvent.BUS.addListener(StormCommands::register);
        LivingEvent.LivingTickEvent.BUS.addListener(WitherSicknessTracker::onLivingTick);
        MobSpawnEvent.AllowDespawn.BUS.addListener(WitherSicknessTracker::onAllowDespawn);
        PlayerEvent.Clone.BUS.addListener(WitherSicknessTracker::onPlayerClone);
        BuildCreativeModeTabContentsEvent.BUS.addListener(WitherStormMod::addCreativeItems);
        BlockEvent.EntityPlaceEvent.BUS.addListener(WitherStormPatternChecker::onSkullPlaced);
        TickEvent.LevelTickEvent.Post.BUS.addListener(WitherStormPatternChecker::onLevelTick);
    }

    private static void addCreativeItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) event.accept(WitherStormModItems.GOLDEN_APPLE_STEW.get());
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) event.accept(WitherStormModItems.COMMAND_BLOCK_BOOK.get());
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(WitherStormModItems.SUPER_TNT.get());
            event.accept(WitherStormModItems.FORMIDIBOMB.get());
        }
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(WITHER_STORM.get(), WitherBoss.createAttributes()
                .add(Attributes.MAX_HEALTH, 400.0).add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.FOLLOW_RANGE, 120.0).add(Attributes.ARMOR, 8.0).add(Attributes.ATTACK_DAMAGE, 3.5)
                .add(WitherStormModAttributes.TARGET_STATIONARY_FLYING_SPEED.getHolder().orElseThrow())
                .add(WitherStormModAttributes.SLOW_FLYING_SPEED.getHolder().orElseThrow())
                .add(WitherStormModAttributes.EVOLUTION_SPEED.getHolder().orElseThrow())
                .add(WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.getHolder().orElseThrow(), 40.0)
                .build());
        event.put(WITHERED_SYMBIONT.get(), WitherSkeleton.createAttributes()
                .add(Attributes.MAX_HEALTH, 60.0).add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0).add(Attributes.ATTACK_DAMAGE, 16.0)
                .add(Attributes.FOLLOW_RANGE, 45.0).add(Attributes.STEP_HEIGHT, 1.0).build());
        event.put(WITHER_STORM_SEGMENT.get(), WitherBoss.createAttributes()
                .add(Attributes.MAX_HEALTH, 4000.0).add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.FOLLOW_RANGE, 160.0).add(Attributes.ARMOR, 6.0).build());
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
