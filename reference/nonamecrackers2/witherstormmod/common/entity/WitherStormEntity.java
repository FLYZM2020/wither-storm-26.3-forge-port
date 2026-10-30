/*      */ package nonamecrackers2.witherstormmod.common.entity;
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.mojang.datafixers.util.Pair;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import java.util.function.BiConsumer;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.function.Supplier;
/*      */ import java.util.stream.Stream;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.core.BlockPos;
/*      */ import net.minecraft.core.Direction;
/*      */ import net.minecraft.core.Vec3i;
/*      */ import net.minecraft.core.particles.BlockParticleOption;
/*      */ import net.minecraft.core.particles.ParticleOptions;
/*      */ import net.minecraft.core.particles.ParticleTypes;
/*      */ import net.minecraft.nbt.CompoundTag;
/*      */ import net.minecraft.nbt.ListTag;
/*      */ import net.minecraft.nbt.NbtUtils;
/*      */ import net.minecraft.nbt.Tag;
/*      */ import net.minecraft.network.FriendlyByteBuf;
/*      */ import net.minecraft.network.chat.Component;
/*      */ import net.minecraft.network.protocol.Packet;
/*      */ import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
/*      */ import net.minecraft.network.syncher.EntityDataAccessor;
/*      */ import net.minecraft.network.syncher.EntityDataSerializer;
/*      */ import net.minecraft.network.syncher.EntityDataSerializers;
/*      */ import net.minecraft.network.syncher.SynchedEntityData;
/*      */ import net.minecraft.resources.ResourceKey;
/*      */ import net.minecraft.server.level.ServerBossEvent;
/*      */ import net.minecraft.server.level.ServerLevel;
/*      */ import net.minecraft.server.level.ServerPlayer;
/*      */ import net.minecraft.sounds.SoundEvent;
/*      */ import net.minecraft.sounds.SoundEvents;
/*      */ import net.minecraft.sounds.SoundSource;
/*      */ import net.minecraft.tags.BlockTags;
/*      */ import net.minecraft.tags.DamageTypeTags;
/*      */ import net.minecraft.tags.StructureTags;
/*      */ import net.minecraft.util.Mth;
/*      */ import net.minecraft.world.BossEvent;
/*      */ import net.minecraft.world.damagesource.DamageSource;
/*      */ import net.minecraft.world.damagesource.DamageTypes;
/*      */ import net.minecraft.world.effect.MobEffectInstance;
/*      */ import net.minecraft.world.effect.MobEffects;
/*      */ import net.minecraft.world.entity.Entity;
/*      */ import net.minecraft.world.entity.EntityDimensions;
/*      */ import net.minecraft.world.entity.EntitySelector;
/*      */ import net.minecraft.world.entity.EntityType;
/*      */ import net.minecraft.world.entity.ExperienceOrb;
/*      */ import net.minecraft.world.entity.FlyingMob;
/*      */ import net.minecraft.world.entity.LivingEntity;
/*      */ import net.minecraft.world.entity.Mob;
/*      */ import net.minecraft.world.entity.MobType;
/*      */ import net.minecraft.world.entity.MoverType;
/*      */ import net.minecraft.world.entity.PathfinderMob;
/*      */ import net.minecraft.world.entity.Pose;
/*      */ import net.minecraft.world.entity.PowerableMob;
/*      */ import net.minecraft.world.entity.TamableAnimal;
/*      */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*      */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*      */ import net.minecraft.world.entity.ai.control.BodyRotationControl;
/*      */ import net.minecraft.world.entity.ai.control.LookControl;
/*      */ import net.minecraft.world.entity.ai.goal.Goal;
/*      */ import net.minecraft.world.entity.ai.goal.GoalSelector;
/*      */ import net.minecraft.world.entity.ai.goal.WrappedGoal;
/*      */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*      */ import net.minecraft.world.entity.animal.AbstractSchoolingFish;
/*      */ import net.minecraft.world.entity.animal.Squid;
/*      */ import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
/*      */ import net.minecraft.world.entity.boss.wither.WitherBoss;
/*      */ import net.minecraft.world.entity.decoration.ArmorStand;
/*      */ import net.minecraft.world.entity.item.FallingBlockEntity;
/*      */ import net.minecraft.world.entity.item.ItemEntity;
/*      */ import net.minecraft.world.entity.item.PrimedTnt;
/*      */ import net.minecraft.world.entity.monster.Monster;
/*      */ import net.minecraft.world.entity.monster.Phantom;
/*      */ import net.minecraft.world.entity.monster.Slime;
/*      */ import net.minecraft.world.entity.player.Player;
/*      */ import net.minecraft.world.entity.projectile.FireworkRocketEntity;
/*      */ import net.minecraft.world.entity.projectile.Projectile;
/*      */ import net.minecraft.world.entity.projectile.ThrownEnderpearl;
/*      */ import net.minecraft.world.entity.projectile.WitherSkull;
/*      */ import net.minecraft.world.entity.vehicle.AbstractMinecart;
/*      */ import net.minecraft.world.entity.vehicle.Boat;
/*      */ import net.minecraft.world.item.Item;
/*      */ import net.minecraft.world.item.ItemStack;
/*      */ import net.minecraft.world.level.BlockGetter;
/*      */ import net.minecraft.world.level.ClipContext;
/*      */ import net.minecraft.world.level.ItemLike;
/*      */ import net.minecraft.world.level.Level;
/*      */ import net.minecraft.world.level.block.Block;
/*      */ import net.minecraft.world.level.block.entity.BlockEntity;
/*      */ import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
/*      */ import net.minecraft.world.level.block.state.BlockState;
/*      */ import net.minecraft.world.level.gameevent.GameEvent;
/*      */ import net.minecraft.world.level.levelgen.Heightmap;
/*      */ import net.minecraft.world.level.material.Fluid;
/*      */ import net.minecraft.world.level.material.Fluids;
/*      */ import net.minecraft.world.phys.AABB;
/*      */ import net.minecraft.world.phys.BlockHitResult;
/*      */ import net.minecraft.world.phys.HitResult;
/*      */ import net.minecraft.world.phys.Vec2;
/*      */ import net.minecraft.world.phys.Vec3;
/*      */ import net.minecraft.world.phys.shapes.VoxelShape;
/*      */ import net.minecraftforge.common.ForgeConfigSpec;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ import net.minecraftforge.common.Tags;
/*      */ import net.minecraftforge.common.util.LazyOptional;
/*      */ import net.minecraftforge.event.ForgeEventFactory;
/*      */ import net.minecraftforge.eventbus.api.Event;
/*      */ import net.minecraftforge.network.PacketDistributor;
/*      */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*      */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.WitherStormWorldInteractions;
/*      */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
/*      */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
/*      */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*      */ import nonamecrackers2.witherstormmod.api.common.event.CanWitherStormTargetMobEvent;
/*      */ import nonamecrackers2.witherstormmod.api.common.event.WitherStormChangePhaseEvent;
/*      */ import nonamecrackers2.witherstormmod.api.common.event.WitherStormConsumeEvent;
/*      */ import nonamecrackers2.witherstormmod.api.common.event.WitherStormEvolveEvent;
/*      */ import nonamecrackers2.witherstormmod.api.common.event.WitherStormModifyEvolutionSpeedEvent;
/*      */ import nonamecrackers2.witherstormmod.api.common.event.WitherStormModifyFlyingSpeedEvent;
/*      */ import nonamecrackers2.witherstormmod.client.capability.WitherStormLoopingSoundManager;
/*      */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*      */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*      */ import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
/*      */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.BowelsInstanceManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.IgnoredTargetsManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PersistentTrackedEntities;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SegmentsManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SymbiontSummoningManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.WitherStormSyncHelper;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormLookController;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.DoNothingGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.FindNearestFormidibombGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.LookAtDistractionGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.LookAtFormidibombGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.LookAtTargetGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.NearestBlockDistractionGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.RemovableGoals;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.RemovableGoalsManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormHurtByTargetGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormLookRandomlyGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormNearestDistractionGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormPriorityTargetingGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormTargetingGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.section.CollisionActionSection;
/*      */ import nonamecrackers2.witherstormmod.common.entity.section.FallingSection;
/*      */ import nonamecrackers2.witherstormmod.common.entity.section.Section;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*      */ import nonamecrackers2.witherstormmod.common.packet.CreateLoopingSoundMessage;
/*      */ import nonamecrackers2.witherstormmod.common.packet.GlobalSoundMessage;
/*      */ import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
/*      */ import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
/*      */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*      */ import nonamecrackers2.witherstormmod.common.packet.StormSoundPositionMessage;
/*      */ import nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions;
/*      */ import nonamecrackers2.witherstormmod.common.predicate.EntityPredicateBuilder;
/*      */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*      */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
/*      */ import nonamecrackers2.witherstormmod.common.util.ClusterBuilderHelper;
/*      */ import nonamecrackers2.witherstormmod.common.util.DebrisCluster;
/*      */ import nonamecrackers2.witherstormmod.common.util.DebrisRingSettings;
/*      */ import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
/*      */ import nonamecrackers2.witherstormmod.common.util.EvolutionProfiler;
/*      */ import nonamecrackers2.witherstormmod.common.util.StormHeadOffsets;
/*      */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*      */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ 
/*      */ public class WitherStormEntity extends Monster implements PowerableMob, EntitySyncableData, BossThemeEntity, WitherStormBase, ChunkLoader {
/*  196 */   private static final List<DataAccessorHolder<?>> DATA_ACCESSORS = Lists.newArrayList();
/*  197 */   protected static final EntityDataAccessor<Integer> INVULNERABLE = SynchedEntityData.m_135353_(WitherStormEntity.class, EntityDataSerializers.f_135028_);
/*  198 */   protected static final EntityDataAccessor<Integer> STARTING_INVULNERABLE = SynchedEntityData.m_135353_(WitherStormEntity.class, EntityDataSerializers.f_135028_);
/*  199 */   protected static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.m_135353_(WitherStormEntity.class, EntityDataSerializers.f_135028_);
/*  200 */   private static final EntityDataAccessor<Integer> CONSUMED_ENTITIES = SynchedEntityData.m_135353_(WitherStormEntity.class, EntityDataSerializers.f_135028_);
/*  201 */   private static final EntityDataAccessor<Boolean> MIRRORED = SynchedEntityData.m_135353_(WitherStormEntity.class, EntityDataSerializers.f_135035_);
/*  202 */   private static final EntityDataAccessor<Boolean> SHOULD_SHOW_HOLE = SynchedEntityData.m_135353_(WitherStormEntity.class, EntityDataSerializers.f_135035_);
/*  203 */   private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("9B8DA22B-138B-4B68-879D-3FD329FAF903");
/*  204 */   private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("C806DBFA-2B10-4BEA-B16C-C3233707399C");
/*  205 */   public static final Predicate<LivingEntity> DESTROYER_LIVING_ENTITY_SELECTOR = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and()
/*  206 */     .addTest(Entity::m_6097_))
/*  207 */     .isNotTag(WitherStormModEntityTags.WITHER_STORM_TARGETING_BLACKLIST)
/*  208 */     .isNotInstanceOf(WitherBoss.class))
/*  209 */     .isNotInstanceOf(FlyingMob.class))
/*  210 */     .isNotInstanceOf(EnderDragon.class))
/*  211 */     .isNotInstanceOf(WitherStormEntity.class))
/*  212 */     .isNotInstanceOf(WitherSickened.class))
/*  213 */     .isNotInstanceOf(CommandBlockEntity.class))
/*  214 */     .isNotInstanceOf(WitheredSymbiontEntity.class))
/*  215 */     .isNotInstanceOf(WitherStormHeadEntity.class))
/*  216 */     .isNotInstanceOf(TentacleEntity.class))
/*  217 */     .isNotInstanceOf(AmbientCreature.class))
/*  218 */     .isNotInstanceOf(ArmorStand.class)).build();
/*  219 */   public static final Predicate<LivingEntity> HUNCHBACK_LIVING_ENTITY_SELECTOR = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and()
/*  220 */     .addTest(DESTROYER_LIVING_ENTITY_SELECTOR))
/*  221 */     .isNotInstanceOf(AbstractSchoolingFish.class))
/*  222 */     .isNotInstanceOf(Squid.class)).build();
/*  223 */   public static final Predicate<Entity> DISTRACTION_SELECTOR = ((EntityPredicateBuilder)EntityPredicateBuilder.or().isInstanceOf(FireworkRocketEntity.class)).build();
/*  224 */   public static final Predicate<Entity> TRACTOR_BEAM_PULLABLE = ((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or().isExactly(EntityType.f_20515_).isInstanceOf(Boat.class)).isInstanceOf(AbstractMinecart.class)).build();
/*  225 */   public static final Predicate<Entity> ABSORBABLE = ((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().isNotInstanceOf(Player.class)).isNotInstanceOf(PrimedTnt.class)).build();
/*  226 */   public static final Predicate<Entity> CAN_TRAVEL_TO_BOWELS = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().isNotInstanceOf(BlockClusterEntity.class)).isNotInstanceOf(Projectile.class)).isNotInstanceOf(ItemEntity.class)).isNotInstanceOf(Phantom.class)).build(); public static final Predicate<Entity> PICKABLE; static {
/*  227 */     PICKABLE = ((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or().isInstanceOf(ItemEntity.class)).addTest(e -> { if (e instanceof Slime) { Slime slime = (Slime)e; if (slime.m_33633_()); }  return false; })).build();
/*  228 */   } private static final EntityDimensions STARTING_SIZE = EntityDimensions.m_20395_(0.9F, 3.5F);
/*  229 */   private static final EntityDimensions DESTROYER_SIZE = EntityDimensions.m_20395_(10.0F, 30.0F);
/*  230 */   private static final EntityDimensions EVOLVED_DESTROYER_SIZE = EntityDimensions.m_20395_(10.0F, 60.0F);
/*  231 */   private static final EntityDimensions DEVOURER_SIZE = EntityDimensions.m_20395_(15.0F, 90.0F);
/*  232 */   private static final EntityDimensions EVOLVED_DEVOURER_SIZE = EntityDimensions.m_20395_(15.0F, 120.0F);
/*  233 */   private static final double[] SEGMENT_DESIRED_X = new double[] { 75.0D, 75.0D };
/*  234 */   private static final double[] SEGMENT_DESIRED_Z = new double[] { 50.0D, -50.0D };
/*  235 */   public static final String[] REMOVABLE_LOOK_GOALS = new String[] { "lookGoals0", "lookGoals1", "lookGoals2" };
/*  236 */   public static final String[] REMOVABLE_TARGET_GOALS = new String[] { "targetGoals0", "targetGoals1", "targetGoals2" };
/*      */   public static final int MAX_PHASE = 7;
/*      */   protected final HeadManager headManager;
/*      */   protected final PlayDeadManager playDeadManager;
/*      */   protected final Optional<UltimateTargetManager> targetManager;
/*      */   protected final Optional<SegmentsManager> segments;
/*      */   protected final Optional<SymbiontSummoningManager> summoningManager;
/*      */   protected final Optional<BowelsInstanceManager> bowelsInstance;
/*      */   protected RemovableGoalsManager removableGoals;
/*      */   protected final Optional<ServerBossEvent> bossEvent;
/*      */   private int lastConsumedEntities;
/*      */   private int tentacleTickCount;
/*      */   private int tentacleTickCountO;
/*      */   private int destroyBlocksTick;
/*  250 */   protected int entityConsumptionRadius = 16;
/*  251 */   protected float clusterRadius = 1.0F;
/*      */   public int idleTargetTicks;
/*      */   public boolean chunkloads = true;
/*  254 */   protected Section[] sections = new Section[3];
/*      */   private FallingSection fallingSection;
/*      */   public boolean partsEnabled = true;
/*      */   public boolean shouldFollowUltimateTarget = true;
/*      */   public boolean shouldPlaySoundLoop = true;
/*      */   public boolean shouldPlayGlobalSounds = true;
/*      */   public int witherStormDeathTime;
/*      */   public boolean shouldDoCustomMovement = true;
/*  262 */   protected List<DebrisCluster> debrisClusters = (List<DebrisCluster>)ImmutableList.of();
/*  263 */   protected List<DebrisCluster> hunchbackDebrisClusters = (List<DebrisCluster>)ImmutableList.of();
/*  264 */   protected List<DebrisRingSettings> debrisRings = (List<DebrisRingSettings>)ImmutableList.of();
/*      */   private boolean isOnDistantRenderer;
/*      */   protected float onGroundAnimation;
/*      */   protected float onGroundAnimationO;
/*      */   public float xBodyRot;
/*      */   public float xBodyRotO;
/*      */   public boolean shouldIgnoreFormidibomb;
/*      */   private List<GoalSelector> headGoalSelectors;
/*      */   private List<GoalSelector> headTargetSelectors;
/*      */   private int flickerTime;
/*  274 */   private int nextFlicker = 40;
/*      */   private boolean shouldFlicker;
/*  276 */   private final List<ServerPlayer> playersTracking = Lists.newArrayList();
/*      */   private float shineAlpha;
/*      */   private float shineAlphaO;
/*      */   private boolean resummoned;
/*  280 */   private final EvolutionProfiler evolutionProfiler = new EvolutionProfiler();
/*  281 */   private final Map<UUID, CompoundTag> consumedPets = Maps.newHashMap();
/*  282 */   private final PersistentTrackedEntities trackedEntities = new PersistentTrackedEntities();
/*      */   private final IgnoredTargetsManager ignoredTargets;
/*  284 */   private final List<BlockPos> playingJukeboxes = Lists.newArrayList();
/*  285 */   private int nextUndergroundRumble = 1200 + this.f_19796_.m_188503_(1200);
/*      */   private float shineScale;
/*      */   private float phaseProgress;
/*      */   private boolean isLocked;
/*      */   private int lastFlyingSwitchTime;
/*  290 */   private double currentFlyingHeight = 10.0D;
/*      */   
/*      */   private float lerpBodyXRot;
/*      */   private float lerpBodyYRot;
/*      */   private int bodyLerpSteps;
/*      */   private Predicate<LivingEntity> entitySelector;
/*      */   
/*      */   public WitherStormEntity(EntityType<? extends WitherStormEntity> entityTypeIn, Level worldIn) {
/*  298 */     super(entityTypeIn, worldIn);
/*  299 */     m_21153_(m_21233_());
/*  300 */     m_21573_().m_7008_(true);
/*  301 */     this.f_21364_ = 10000;
/*  302 */     this.f_19811_ = true;
/*  303 */     this.f_21365_ = (LookControl)new WitherStormLookController(this);
/*  304 */     buildSections();
/*  305 */     this.segments = makeSegmentsManager();
/*  306 */     this.bowelsInstance = makeBowelsInstanceManager();
/*  307 */     this.headManager = makeHeadManager();
/*  308 */     this.targetManager = makeUltimateTargetManager();
/*  309 */     this.summoningManager = makeSummoningManager();
/*  310 */     this.playDeadManager = new PlayDeadManager(this);
/*  311 */     this.bossEvent = makeBossEvent();
/*  312 */     this.ignoredTargets = new IgnoredTargetsManager(this);
/*      */   }
/*      */ 
/*      */   
/*      */   protected HeadManager makeHeadManager() {
/*  317 */     return new HeadManager(this, StormHeadOffsets.MAIN);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Optional<SegmentsManager> makeSegmentsManager() {
/*  322 */     return Optional.of(new SegmentsManager(this));
/*      */   }
/*      */ 
/*      */   
/*      */   protected Optional<SymbiontSummoningManager> makeSummoningManager() {
/*  327 */     return Optional.of(new SymbiontSummoningManager(this));
/*      */   }
/*      */ 
/*      */   
/*      */   protected Optional<BowelsInstanceManager> makeBowelsInstanceManager() {
/*  332 */     return Optional.of(new BowelsInstanceManager(this));
/*      */   }
/*      */ 
/*      */   
/*      */   protected Optional<UltimateTargetManager> makeUltimateTargetManager() {
/*  337 */     return Optional.of(new UltimateTargetManager(this));
/*      */   }
/*      */ 
/*      */   
/*      */   protected Optional<ServerBossEvent> makeBossEvent() {
/*  342 */     return Optional.of((ServerBossEvent)(new ServerBossEvent(m_5446_(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).m_7003_(true));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void buildSections() {
/*  347 */     this.sections[0] = new Section(this, 30.0F, 15.0F, 13.0D, 28.0D, 0.0D, 4);
/*  348 */     this.sections[1] = new Section(this, 30.0F, 15.0F, -13.0D, 28.0D, 0.0D, 4);
/*  349 */     this.fallingSection = new FallingSection(this, 50.0F, 70.0F, 0.0D, 60.0D, 30.0D, 4);
/*  350 */     this.sections[2] = (new CollisionActionSection(this, 16.0F, 16.0F, -3.0D, 34.0D, -24.0D, storm -> (storm.isBeingTornApart() && storm.getPhase() >= 7), entity -> {
/*      */           if (!(m_9236_()).f_46443_ && (CAN_TRAVEL_TO_BOWELS.test(entity) || entity instanceof ThrownEnderpearl) && !alreadyATarget(entity, true)) {
/*      */             if (entity instanceof ThrownEnderpearl) {
/*      */               ThrownEnderpearl pearl = (ThrownEnderpearl)entity;
/*      */               
/*      */               Entity owner = pearl.m_19749_();
/*      */               
/*      */               if (owner instanceof ServerPlayer) {
/*      */                 ServerPlayer player = (ServerPlayer)owner;
/*      */                 
/*      */                 if (player.f_8906_.f_9742_.m_129536_() && player.m_9236_() == pearl.m_9236_() && !player.m_5803_()) {
/*      */                   if (player.m_20159_()) {
/*      */                     player.m_8127_();
/*      */                   }
/*      */                   
/*      */                   player.m_6021_(pearl.m_20185_(), pearl.m_20186_(), pearl.m_20189_());
/*      */                   
/*      */                   player.f_19789_ = 0.0F;
/*      */                   
/*      */                   player.m_6469_(m_269291_().m_268989_(), 5.0F);
/*      */                   
/*      */                   sendToBowels(owner);
/*      */                 } 
/*      */               } else if (owner != null) {
/*      */                 owner.m_6021_(pearl.m_20185_(), pearl.m_20186_(), pearl.m_20189_());
/*      */                 owner.f_19789_ = 0.0F;
/*      */                 sendToBowels(owner);
/*      */               } 
/*      */               pearl.m_146870_();
/*      */             } else {
/*      */               sendToBowels(entity);
/*      */             } 
/*      */           }
/*  383 */         })).setColor(0.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   protected BodyRotationControl m_7560_() {
/*  389 */     return (BodyRotationControl)new WitherStormBodyController(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_8097_() {
/*  395 */     super.m_8097_();
/*  396 */     this.f_19804_.m_135372_(INVULNERABLE, Integer.valueOf(0));
/*  397 */     this.f_19804_.m_135372_(STARTING_INVULNERABLE, Integer.valueOf(((Integer)WitherStormModConfig.SERVER.invulnerabilityTime.get()).intValue() * 20));
/*  398 */     this.f_19804_.m_135372_(PHASE, Integer.valueOf(0));
/*  399 */     this.f_19804_.m_135372_(CONSUMED_ENTITIES, Integer.valueOf(0));
/*  400 */     this.f_19804_.m_135372_(MIRRORED, Boolean.valueOf(false));
/*  401 */     this.f_19804_.m_135372_(SHOULD_SHOW_HOLE, Boolean.valueOf(false));
/*  402 */     for (DataAccessorHolder<?> holder : DATA_ACCESSORS) {
/*  403 */       holder.defineTo(this.f_19804_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void m_8099_() {
/*  409 */     this.entitySelector = (entity -> (getPhase() <= 3) ? HUNCHBACK_LIVING_ENTITY_SELECTOR.test(entity) : DESTROYER_LIVING_ENTITY_SELECTOR.test(entity));
/*      */ 
/*      */ 
/*      */     
/*  413 */     this.headGoalSelectors = (List<GoalSelector>)ImmutableList.builder().add(new GoalSelector(m_9236_().m_46658_())).add(new GoalSelector(m_9236_().m_46658_())).build();
/*  414 */     this.headTargetSelectors = (List<GoalSelector>)ImmutableList.builder().add(new GoalSelector(m_9236_().m_46658_())).add(new GoalSelector(m_9236_().m_46658_())).build();
/*      */     
/*  416 */     this.removableGoals = new RemovableGoalsManager();
/*      */     
/*  418 */     this.f_21345_.m_25352_(0, (Goal)new DoNothingGoal(this, 0));
/*  419 */     this.removableGoals.put(REMOVABLE_LOOK_GOALS[0], RemovableGoals.Builder.builder()
/*  420 */         .put(1, (Goal)new LookAtFormidibombGoal(this))
/*  421 */         .put(2, (Goal)new LookAtDistractionGoal((Mob)this, 0, () -> Double.valueOf(300.0D)))
/*      */         
/*  423 */         .put(4, (Goal)new LookAtTargetGoal((LivingEntity)this, 0, s -> Integer.valueOf((s.getPhase() > 3) ? 50 : 3)))
/*  424 */         .build(this.f_21345_));
/*  425 */     this.f_21345_.m_25352_(7, (Goal)new WitherStormLookRandomlyGoal(this, 0, 120));
/*  426 */     this.removableGoals.put(REMOVABLE_TARGET_GOALS[0], RemovableGoals.Builder.builder()
/*  427 */         .put(0, (Goal)new FindNearestFormidibombGoal(this, 0))
/*  428 */         .put(1, (Goal)new WitherStormHurtByTargetGoal((PathfinderMob)this, this.entitySelector))
/*  429 */         .put(2, (Goal)new WitherStormNearestDistractionGoal(this, 0, DISTRACTION_SELECTOR, 2))
/*  430 */         .put(3, (Goal)new WitherStormPriorityTargetingGoal(this, this.entitySelector, 0))
/*  431 */         .put(4, (Goal)new NearestBlockDistractionGoal((Mob)this, 0))
/*  432 */         .put(5, (Goal)new WitherStormTargetingGoal(this, this.entitySelector, 0))
/*  433 */         .build(this.f_21346_));
/*      */     
/*  435 */     addHeadGoals((selector, head) -> {
/*      */           selector.m_25352_(0, (Goal)new DoNothingGoal(this, head.intValue()));
/*      */ 
/*      */           
/*      */           this.removableGoals.put(REMOVABLE_LOOK_GOALS[head.intValue()], RemovableGoals.Builder.builder().put(1, (Goal)new LookAtDistractionGoal((Mob)this, head.intValue(), ())).put(2, (Goal)new LookAtTargetGoal((LivingEntity)this, head.intValue(), ())).build(selector));
/*      */           
/*      */           selector.m_25352_(3, (Goal)new WitherStormLookRandomlyGoal(this, head.intValue(), 120));
/*      */         });
/*      */     
/*  444 */     addHeadTargetGoals((selector, head) -> this.removableGoals.put(REMOVABLE_TARGET_GOALS[head.intValue()], RemovableGoals.Builder.builder().put(0, (Goal)new WitherStormNearestDistractionGoal(this, head.intValue(), DISTRACTION_SELECTOR, 2)).put(1, (Goal)new WitherStormPriorityTargetingGoal(this, this.entitySelector, head.intValue())).put(3, (Goal)new NearestBlockDistractionGoal((Mob)this, head.intValue())).put(4, (Goal)new WitherStormTargetingGoal(this, this.entitySelector, head.intValue())).build(selector)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Predicate<LivingEntity> getEntitySelector() {
/*  457 */     return this.entitySelector;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_262441_(Predicate<Goal> predicate) {
/*  463 */     super.m_262441_(predicate);
/*  464 */     this.headGoalSelectors.forEach(sel -> sel.m_262460_(predicate));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addHeadGoals(BiConsumer<GoalSelector, Integer> goals) {
/*  471 */     for (int i = 0; i < this.headGoalSelectors.size(); i++) {
/*  472 */       goals.accept(this.headGoalSelectors.get(i), Integer.valueOf(i + 1));
/*      */     }
/*      */   }
/*      */   
/*      */   protected void addHeadTargetGoals(BiConsumer<GoalSelector, Integer> goals) {
/*  477 */     for (int i = 0; i < this.headTargetSelectors.size(); i++) {
/*  478 */       goals.accept(this.headTargetSelectors.get(i), Integer.valueOf(i + 1));
/*      */     }
/*      */   }
/*      */   
/*      */   public static AttributeSupplier.Builder createAttributes() {
/*  483 */     return Monster.m_33035_().m_22266_((Attribute)WitherStormModAttributes.TARGET_STATIONARY_FLYING_SPEED.get()).m_22266_((Attribute)WitherStormModAttributes.SLOW_FLYING_SPEED.get()).m_22268_((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get(), 1.0D).m_22268_(Attributes.f_22280_, 0.0D).m_22268_(Attributes.f_22276_, 400.0D).m_22268_(Attributes.f_22279_, 0.6D).m_22268_(Attributes.f_22277_, 120.0D).m_22268_((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get(), 40.0D).m_22268_(Attributes.f_22284_, 8.0D)
/*  484 */       .m_22268_(Attributes.f_22281_, 3.5D);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldSpeedUp() {
/*  489 */     UltimateTargetManager manager = this.targetManager.orElse((UltimateTargetManager)null);
/*  490 */     if (manager != null) {
/*      */       
/*  492 */       Vec3 pos = manager.getUltimateTargetPos();
/*  493 */       if (getPhase() >= 4 && ((Boolean)WitherStormModConfig.SERVER.shouldChaseWhenTargetStopped.get()).booleanValue() && pos != null) {
/*  494 */         return (manager.isTargetStationary() && m_20182_().m_82554_(pos) > 122.0D && !manager.isDistracted());
/*      */       }
/*  496 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  500 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getFlyingSpeed(Vec3 ultimateTargetDist) {
/*  506 */     if (getPhase() > 3) {
/*      */       
/*  508 */       double flyingSpeed = m_21133_(Attributes.f_22280_);
/*  509 */       if (shouldSpeedUp()) {
/*      */         
/*  511 */         flyingSpeed += Math.min(getDefaultChasingSpeed(), m_20182_().m_82554_(Objects.<Vec3>requireNonNull(getUltimateTargetPos())) * 0.001D);
/*      */ 
/*      */       
/*      */       }
/*  515 */       else if (ultimateTargetDist.m_165924_() > 205.0D) {
/*  516 */         flyingSpeed += getDefaultNormalSpeed() + 0.03D;
/*      */       } else {
/*  518 */         flyingSpeed += getDefaultNormalSpeed();
/*      */       } 
/*  520 */       return flyingSpeed;
/*      */     } 
/*      */ 
/*      */     
/*  524 */     return m_21133_((Attribute)WitherStormModAttributes.SLOW_FLYING_SPEED.get());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected double getDefaultChasingSpeed() {
/*  530 */     return attributeOrConfigValue((Attribute)WitherStormModAttributes.TARGET_STATIONARY_FLYING_SPEED.get(), WitherStormModConfig.SERVER.chasingFlyingSpeed);
/*      */   }
/*      */ 
/*      */   
/*      */   protected double getDefaultNormalSpeed() {
/*  535 */     return attributeOrConfigValue((Attribute)WitherStormModAttributes.SLOW_FLYING_SPEED.get(), WitherStormModConfig.SERVER.normalFlyingSpeed);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_8107_() {
/*  541 */     if (this.bodyLerpSteps > 0) {
/*      */       
/*  543 */       this.xBodyRot += (this.lerpBodyXRot - this.xBodyRot) / this.bodyLerpSteps;
/*  544 */       this.f_20883_ += Mth.m_14177_(this.lerpBodyYRot - this.f_20883_) / this.bodyLerpSteps;
/*  545 */       this.bodyLerpSteps--;
/*      */     } 
/*      */     
/*  548 */     Vec3 vector3d = m_20184_().m_82542_(1.0D, 0.6D, 1.0D);
/*  549 */     if (!(m_9236_()).f_46443_ && !shouldDoNothing()) {
/*      */       
/*  551 */       this.targetManager.ifPresent(UltimateTargetManager::tick);
/*      */       
/*  553 */       this.summoningManager.ifPresent(SymbiontSummoningManager::tick);
/*      */       
/*  555 */       if (!isDeadOrPlayingDead()) {
/*  556 */         vector3d = doFlying(vector3d);
/*      */       }
/*      */     } 
/*  559 */     if (shouldDoCustomMovement()) {
/*  560 */       m_20256_(vector3d);
/*      */     }
/*  562 */     if (vector3d.m_165925_() > 0.05D) {
/*  563 */       m_146922_((float)Mth.m_14136_(vector3d.f_82481_, vector3d.f_82479_) * 57.295776F - 90.0F);
/*      */     }
/*  565 */     super.m_8107_();
/*      */     
/*  567 */     if (!isDeadOrPlayingDead() && !shouldDoNothing()) {
/*  568 */       this.headManager.aiStep();
/*      */     }
/*  570 */     boolean flag = m_7090_();
/*      */     
/*  572 */     if (getPhase() < 4)
/*      */     {
/*  574 */       for (WitherStormHead head : this.headManager.getHeads()) {
/*      */         
/*  576 */         Vec3 pos = head.getHeadPos();
/*  577 */         double d8 = pos.f_82479_;
/*  578 */         double d10 = pos.f_82480_;
/*  579 */         double d2 = pos.f_82481_;
/*  580 */         if (head instanceof nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.MainHead) {
/*      */           
/*  582 */           d8 = m_20185_();
/*  583 */           d10 = m_20188_();
/*  584 */           d2 = m_20189_();
/*      */         } 
/*  586 */         m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123762_, d8 + this.f_19796_.m_188583_() * 0.30000001192092896D, d10 + this.f_19796_.m_188583_() * 0.30000001192092896D, d2 + this.f_19796_.m_188583_() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);
/*  587 */         if (flag && (m_9236_()).f_46441_.m_188503_(4) == 0) {
/*  588 */           m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123811_, d8 + this.f_19796_.m_188583_() * 0.30000001192092896D, d10 + this.f_19796_.m_188583_() * 0.30000001192092896D, d2 + this.f_19796_.m_188583_() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
/*      */         }
/*      */       } 
/*      */     }
/*  592 */     if (getPhase() < 3)
/*      */     {
/*  594 */       for (int i = 0; i < 5; i++) {
/*      */         
/*  596 */         float angle = (this.f_20883_ + 90.0F) * 0.017453292F;
/*  597 */         double x = Mth.m_14089_(angle) * 0.3D + m_20185_();
/*  598 */         double z = Mth.m_14031_(angle) * 0.3D + m_20189_();
/*  599 */         double y = m_20186_() + 1.4D;
/*  600 */         double startX = x + this.f_19796_.m_188583_();
/*  601 */         double startY = y + this.f_19796_.m_188583_();
/*  602 */         double startZ = z + this.f_19796_.m_188583_();
/*  603 */         Vec3 delta = (new Vec3(x, y, z)).m_82492_(startX, startY, startZ).m_82541_().m_82490_(0.1D);
/*  604 */         m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), startX, startY, startZ, delta.f_82479_, delta.f_82480_, delta.f_82481_);
/*      */       } 
/*      */     }
/*      */     
/*  608 */     if (getInvulnerableTicks() > 0 && getPhase() < 4)
/*      */     {
/*  610 */       for (int i1 = 0; i1 < 3; i1++) {
/*  611 */         m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123811_, m_20185_() + this.f_19796_.m_188583_(), m_20186_() + (this.f_19796_.m_188501_() * 3.3F), m_20189_() + this.f_19796_.m_188583_(), 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
/*      */       }
/*      */     }
/*  614 */     if (!m_21224_() && m_6084_()) {
/*  615 */       getPlayDeadManager().tick();
/*      */     }
/*      */   }
/*      */   
/*      */   protected Vec3 doFlying(Vec3 vector3d) {
/*  620 */     double ascendSpeed = 0.02D;
/*  621 */     if (getPhase() > 3)
/*      */     {
/*  623 */       if (!((Boolean)WitherStormModConfig.SERVER.dynamicFlyingHeight.get()).booleanValue()) {
/*      */         
/*  625 */         this.currentFlyingHeight = ((Integer)WitherStormModConfig.SERVER.flyingHeight.get()).intValue();
/*  626 */         ascendSpeed = 0.005D;
/*      */       }
/*      */       else {
/*      */         
/*  630 */         int timeDiff = this.f_19797_ - this.lastFlyingSwitchTime;
/*  631 */         if (timeDiff >= ((Integer)WitherStormModConfig.SERVER.dynamicFlyingHeightTime.get()).intValue() * 20) {
/*      */           
/*  633 */           this.lastFlyingSwitchTime = this.f_19797_;
/*  634 */           this.currentFlyingHeight = (40 + m_217043_().m_188503_(41));
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  639 */     double finalHeight = getHeightToAscendTo(vector3d, this.currentFlyingHeight, ascendSpeed);
/*  640 */     vector3d = new Vec3(vector3d.f_82479_, finalHeight, vector3d.f_82481_);
/*      */     
/*  642 */     if (m_5448_() != null && getPhase() < 4) {
/*      */       
/*  644 */       LivingEntity entity = m_5448_();
/*  645 */       Vec3 vector3d1 = new Vec3(entity.m_20185_() - m_20185_(), 0.0D, entity.m_20189_() - m_20189_());
/*  646 */       if (vector3d1.m_165924_() > 20.0D)
/*      */       {
/*  648 */         Vec3 vector3d2 = vector3d1.m_82541_();
/*  649 */         vector3d = vector3d.m_82520_(vector3d2.f_82479_ * 0.3D - vector3d.f_82479_ * 0.6D, 0.0D, vector3d2.f_82481_ * 0.3D - vector3d.f_82481_ * 0.6D);
/*      */       }
/*      */     
/*  652 */     } else if (!isNearbyTickingFormidibomb() && shouldTrackUltimateTarget() && (m_5448_() == null || getPhase() > 3)) {
/*      */       
/*  654 */       double minDistance = 12000.0D;
/*  655 */       if (getPhase() > 3) {
/*  656 */         minDistance = 6000.0D;
/*      */       }
/*  658 */       Vec3 pos = getUltimateTargetPos();
/*      */       
/*  660 */       if (pos != null) {
/*      */ 
/*      */         
/*  663 */         Vec3 vector3d1 = new Vec3(pos.m_7096_() - m_20185_(), 0.0D, pos.m_7094_() - m_20189_());
/*  664 */         double speed = getFlyingSpeed(vector3d1);
/*  665 */         WitherStormModifyFlyingSpeedEvent event = new WitherStormModifyFlyingSpeedEvent(this, speed);
/*  666 */         MinecraftForge.EVENT_BUS.post((Event)event);
/*  667 */         speed = event.getOriginalSpeed();
/*  668 */         if (vector3d1.m_165925_() > minDistance) {
/*      */           
/*  670 */           Vec3 vector3d2 = vector3d1.m_82541_();
/*  671 */           vector3d = vector3d.m_82520_(vector3d2.f_82479_ * speed - vector3d.f_82479_ * 0.6D, 0.0D, vector3d2.f_82481_ * speed - vector3d.f_82481_ * 0.6D);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  676 */     return vector3d;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getHeightToAscendTo(Vec3 vector3d, double height, double ascendSpeed) {
/*  681 */     double finalHeight = vector3d.f_82480_;
/*  682 */     double yToStart = Math.min((m_9236_().m_151558_() - 1), m_20191_().m_82399_().m_7098_());
/*      */     
/*  684 */     int radius = (int)((m_6972_(m_20089_())).f_20377_ * 1.5F);
/*  685 */     double highest = -1.0D;
/*      */     
/*  687 */     for (int x = -radius; x < radius; x++) {
/*      */       
/*  689 */       for (int z = -radius; z < radius; z++) {
/*      */         int currentHeight;
/*  691 */         Heightmap.Types type = Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
/*      */         
/*  693 */         if (getPhase() < 4) {
/*  694 */           type = Heightmap.Types.WORLD_SURFACE;
/*      */         }
/*      */ 
/*      */         
/*  698 */         if (getPhase() > 3) {
/*  699 */           currentHeight = m_9236_().m_6924_(type, x + Mth.m_14107_(m_20185_()), z + Mth.m_14107_(m_20189_()));
/*      */         } else {
/*  701 */           currentHeight = WorldUtil.getHeightStartingAt(m_9236_(), Mth.m_14107_(yToStart), x + m_146903_(), z + m_146907_());
/*      */         } 
/*  703 */         if (currentHeight > (int)yToStart) {
/*  704 */           currentHeight = (int)yToStart;
/*      */         }
/*  706 */         if (highest == -1.0D || currentHeight > highest) {
/*  707 */           highest = currentHeight;
/*      */         }
/*      */       } 
/*      */     } 
/*  711 */     double heightToAscendTo = highest + height;
/*  712 */     if (m_20186_() < heightToAscendTo || (!m_7090_() && m_20186_() < heightToAscendTo + 5.0D))
/*  713 */       finalHeight = (heightToAscendTo - m_20186_()) * ascendSpeed; 
/*  714 */     return finalHeight;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double distanceTo(BlockPos pos) {
/*  720 */     float x = (float)(m_20185_() - pos.m_123341_());
/*  721 */     float y = (float)(m_20186_() - pos.m_123342_());
/*  722 */     float z = (float)(m_20189_() - pos.m_123343_());
/*  723 */     return Mth.m_14116_(x * x + y * y + z * z);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean targetInUseBySegment(Entity entity) {
/*  728 */     if (getSegmentsManager().isPresent()) {
/*      */       
/*  730 */       WitherStormSegmentEntity[] segments = ((SegmentsManager)getSegmentsManager().get()).getSegments();
/*  731 */       for (WitherStormSegmentEntity segment : segments) {
/*      */         
/*  733 */         if (segment != null)
/*      */         {
/*  735 */           if (segment.alreadyATarget(entity, true))
/*  736 */             return true; 
/*      */         }
/*      */       } 
/*      */     } 
/*  740 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent m_7515_() {
/*  746 */     if (!isDeadOrPlayingDead()) {
/*      */       
/*  748 */       if (getPhase() < 4) {
/*  749 */         return SoundEvents.f_12554_;
/*      */       }
/*  751 */       return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_GROWL.get();
/*      */     } 
/*      */ 
/*      */     
/*  755 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent m_7975_(@NotNull DamageSource source) {
/*  762 */     if (getPhase() < 4) {
/*  763 */       return SoundEvents.f_12557_;
/*      */     }
/*  765 */     return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_HURT.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent m_5592_() {
/*  771 */     if (getPhase() < 4) {
/*  772 */       return SoundEvents.f_12556_;
/*      */     }
/*  774 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int m_8100_() {
/*  780 */     if (getPhase() > 3) {
/*  781 */       return Math.max(80, this.f_19796_.m_188503_(120));
/*      */     }
/*  783 */     return super.m_8100_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float m_6121_() {
/*  789 */     if (getPhase() > 3) {
/*  790 */       return 25.0F;
/*      */     }
/*  792 */     return super.m_6121_();
/*      */   }
/*      */ 
/*      */   
/*      */   public static float m_21376_(float p_82204_1_, float p_82204_2_, float p_82204_3_) {
/*  797 */     float f = Mth.m_14177_(p_82204_2_ - p_82204_1_);
/*      */     
/*  799 */     if (f > p_82204_3_)
/*      */     {
/*  801 */       f = p_82204_3_;
/*      */     }
/*  803 */     if (f < -p_82204_3_)
/*      */     {
/*  805 */       f = -p_82204_3_;
/*      */     }
/*      */     
/*  808 */     return p_82204_1_ + f;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHeadYRot(int head) {
/*  814 */     return this.headManager.getHead(head).getHeadYRot();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHeadXRot(int head) {
/*  820 */     return this.headManager.getHead(head).getHeadXRot();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHeadYRotO(int head) {
/*  826 */     return this.headManager.getHead(head).getHeadYRotO();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHeadXRotO(int head) {
/*  832 */     return this.headManager.getHead(head).getHeadXRotO();
/*      */   }
/*      */ 
/*      */   
/*      */   private void tickExtraHeadGoals() {
/*  837 */     int i = m_9236_().m_7654_().m_129921_() + m_19879_();
/*  838 */     if (i % 2 != 0 && this.f_19797_ > 1) {
/*      */       
/*  840 */       m_9236_().m_46473_().m_6180_("targetSelectorHeads");
/*  841 */       this.headTargetSelectors.forEach(sel -> sel.m_186081_(false));
/*  842 */       m_9236_().m_46473_().m_7238_();
/*  843 */       m_9236_().m_46473_().m_6180_("goalSelectorHeads");
/*  844 */       this.headGoalSelectors.forEach(sel -> sel.m_186081_(false));
/*  845 */       m_9236_().m_46473_().m_7238_();
/*      */     }
/*      */     else {
/*      */       
/*  849 */       m_9236_().m_46473_().m_6180_("targetSelectorHeads");
/*  850 */       this.headTargetSelectors.forEach(GoalSelector::m_25373_);
/*  851 */       m_9236_().m_46473_().m_7238_();
/*  852 */       m_9236_().m_46473_().m_6180_("goalSelectorHeads");
/*  853 */       this.headGoalSelectors.forEach(GoalSelector::m_25373_);
/*  854 */       m_9236_().m_46473_().m_7238_();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void searchForPlayingJukeboxes() {
/*  860 */     for (BlockEntity entity : WorldUtil.getBlockEntitiesInAABB(m_9236_(), getSearchBox())) {
/*      */       
/*  862 */       if (entity instanceof JukeboxBlockEntity) { JukeboxBlockEntity jukebox = (JukeboxBlockEntity)entity;
/*      */         
/*  864 */         if (jukebox.m_272025_())
/*      */         {
/*  866 */           if (!this.playingJukeboxes.contains(jukebox.m_58899_())) {
/*  867 */             this.playingJukeboxes.add(jukebox.m_58899_());
/*      */           }
/*      */         } }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void m_8024_() {
/*  876 */     tickExtraHeadGoals();
/*  877 */     if (getInvulnerableTicks() > 0) {
/*      */       
/*  879 */       int ticks = getInvulnerableTicks() - 1;
/*  880 */       if (ticks <= 0) {
/*      */         
/*  882 */         m_9236_().m_255391_((Entity)this, m_20185_(), m_20188_(), m_20189_(), 7.0F, false, Level.ExplosionInteraction.MOB);
/*  883 */         if (!m_20067_()) {
/*  884 */           m_9236_().m_6798_(1023, m_20183_(), 0);
/*      */         }
/*      */       } 
/*  887 */       setInvulnerableTicks(ticks);
/*  888 */       float healthPerTick = (m_21233_() - 1.0F) / getStartingInvulnerableTicks() * 10.0F;
/*  889 */       if (this.f_19797_ % 10 == 0) {
/*  890 */         m_5634_(healthPerTick);
/*      */       }
/*  892 */       this.bossEvent.ifPresent(bossEvent -> bossEvent.m_142711_(m_21223_() / m_21233_()));
/*      */     }
/*      */     else {
/*      */       
/*  896 */       super.m_8024_();
/*      */       
/*  898 */       if (!isDeadOrPlayingDead()) {
/*      */         
/*  900 */         this.headManager.customServerAiStep();
/*      */ 
/*      */         
/*  903 */         if (this.destroyBlocksTick > 0) {
/*      */           
/*  905 */           this.destroyBlocksTick--;
/*  906 */           if (this.destroyBlocksTick == 0 && ForgeEventFactory.getMobGriefingEvent(m_9236_(), (Entity)this)) {
/*      */             
/*  908 */             int i1 = Mth.m_14107_(m_20186_());
/*  909 */             int l1 = Mth.m_14107_(m_20185_());
/*  910 */             int i2 = Mth.m_14107_(m_20189_());
/*  911 */             boolean flag = false;
/*      */             
/*  913 */             for (int k2 = -1; k2 <= 1; k2++) {
/*      */               
/*  915 */               for (int l2 = -1; l2 <= 1; l2++) {
/*      */                 
/*  917 */                 for (int j = 0; j <= 3; j++) {
/*      */                   
/*  919 */                   int i3 = l1 + k2;
/*  920 */                   int k = i1 + j;
/*  921 */                   int l = i2 + l2;
/*  922 */                   BlockPos blockpos = new BlockPos(i3, k, l);
/*  923 */                   BlockState blockstate = m_9236_().m_8055_(blockpos);
/*  924 */                   if (blockstate.canEntityDestroy((BlockGetter)m_9236_(), blockpos, (Entity)this) && !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST) && ForgeEventFactory.onEntityDestroyBlock((LivingEntity)this, blockpos, blockstate)) {
/*  925 */                     flag = (m_9236_().m_46953_(blockpos, true, (Entity)this) || flag);
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*  930 */             if (flag) {
/*  931 */               m_9236_().m_5898_(null, 1022, m_20183_(), 0);
/*      */             }
/*      */           } 
/*      */         } 
/*  935 */         for (BlockClusterSource source : WitherStormWorldInteractions.getInstance().getClusterSources()) {
/*  936 */           source.tick(this);
/*      */         }
/*      */         
/*  939 */         if (((Boolean)WitherStormModConfig.SERVER.convertFallingBlocks.get()).booleanValue()) {
/*      */           
/*  941 */           List<FallingBlockEntity> fallingBlocks = m_9236_().m_45976_(FallingBlockEntity.class, getSearchBox());
/*  942 */           for (FallingBlockEntity fallingBlock : fallingBlocks) {
/*      */             
/*  944 */             if (WorldUtil.isLoaded((ServerLevel)m_9236_(), fallingBlock.m_20183_()) && WorldUtil.canSeeOrIsNotInASmallArea((Entity)this, (Entity)fallingBlock)) {
/*      */               
/*  946 */               BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(m_9236_());
/*  947 */               Map<BlockPos, BlockState> map = Maps.newHashMap();
/*  948 */               map.put(BlockPos.f_121853_, fallingBlock.m_31980_());
/*  949 */               cluster.populate(map);
/*  950 */               cluster.m_20219_(fallingBlock.m_20182_());
/*  951 */               cluster.setRotationDelta(new Vec2(this.f_19796_.m_188503_(20) * 0.1F / 2.0F, this.f_19796_.m_188503_(20) * 0.1F / 2.0F));
/*  952 */               cluster.m_20242_(true);
/*  953 */               cluster.setPhysics(false);
/*  954 */               cluster.setCreatedFromFallingBlock(true);
/*  955 */               fallingBlock.m_146870_();
/*  956 */               m_9236_().m_7967_(cluster);
/*  957 */               this.trackedEntities.trackEntityToConsume(cluster);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  964 */         if (ForgeEventFactory.getMobGriefingEvent(m_9236_(), (Entity)this)) {
/*      */           double addRad, consumptionRadius;
/*      */ 
/*      */           
/*  968 */           if (getPhase() >= 6) {
/*      */             
/*  970 */             addRad = this.entityConsumptionRadius + 50.0D + 100.0D;
/*  971 */             consumptionRadius = this.entityConsumptionRadius;
/*      */           }
/*  973 */           else if (getPhase() <= 3) {
/*      */             
/*  975 */             addRad = entityConsumptionRadiusHunch() + 50.0D + 0.0D;
/*  976 */             consumptionRadius = entityConsumptionRadiusHunch();
/*      */           }
/*      */           else {
/*      */             
/*  980 */             addRad = this.entityConsumptionRadius + 50.0D + 0.0D;
/*  981 */             consumptionRadius = this.entityConsumptionRadius;
/*      */           } 
/*      */           
/*  984 */           AABB searchBB = m_20191_().m_82400_(addRad);
/*  985 */           List<AABB> playerBoundingBoxes = m_9236_().m_45976_(ServerPlayer.class, searchBB).stream().map(p -> p.m_20191_().m_82400_(8.0D)).toList();
/*  986 */           List<Entity> entities = m_9236_().m_6443_(Entity.class, searchBB, PICKABLE);
/*  987 */           for (Entity entity : entities) {
/*      */             
/*  989 */             if (!this.trackedEntities.contains(entity))
/*      */             {
/*  991 */               if (this.f_19796_.m_188501_() >= 0.9F) {
/*      */ 
/*      */                 
/*  994 */                 if (entity instanceof ItemEntity) { ItemEntity itemEntity = (ItemEntity)entity;
/*      */                   
/*  996 */                   ItemStack item = itemEntity.m_32055_();
/*  997 */                   if (getPhase() > 3 && item.m_204117_(WitherStormModItemTags.JUNK) && ((Boolean)WitherStormModConfig.SERVER.removeNearbyJunk.get()).booleanValue()) {
/*      */                     
/*  999 */                     if (playerBoundingBoxes.stream().allMatch(box -> !box.m_82390_(entity.m_20182_())))
/* 1000 */                       entity.m_146870_();  continue;
/*      */                   } 
/* 1002 */                   if (entity.m_20270_((Entity)this) <= consumptionRadius)
/*      */                   {
/* 1004 */                     if ((item.m_204117_(WitherStormModItemTags.UNAPPETIZING) && entity.m_20270_((Entity)this) < ((getPhase() > 3) ? 35 : 2)) || !item.m_204117_(WitherStormModItemTags.UNAPPETIZING))
/*      */                     {
/* 1006 */                       if (!item.m_150930_((Item)WitherStormModItems.COMMAND_BLOCK_BOOK.get()) && !item.m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS) && !item.m_150930_((Item)WitherStormModItems.WITHERED_NETHER_STAR.get()))
/*      */                       {
/* 1008 */                         if (canTrackEntity(entity)) {
/*      */                           
/* 1010 */                           this.trackedEntities.trackEntityToConsume(entity);
/* 1011 */                           entity.m_20242_(true);
/*      */                         }  } 
/*      */                     }
/*      */                   }
/*      */                   continue; }
/*      */                 
/* 1017 */                 if (entity instanceof Slime)
/*      */                 {
/* 1019 */                   if (entity.m_20270_((Entity)this) <= consumptionRadius)
/*      */                   {
/* 1021 */                     if (canTrackEntity(entity)) {
/*      */                       
/* 1023 */                       this.trackedEntities.trackEntityToConsume(entity);
/* 1024 */                       entity.m_20242_(true);
/*      */                     } 
/*      */                   }
/*      */                 }
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 1033 */         double defaultSpeed = ((Double)WitherStormModConfig.SERVER.tractorPullSpeedModifier.get()).doubleValue();
/*      */ 
/*      */         
/* 1036 */         if (getPhase() < 4)
/*      */         {
/* 1038 */           for (WitherStormHead head : this.headManager.getHeads()) {
/* 1039 */             pullInTarget((Entity)head.getTarget(), defaultSpeed, head);
/*      */           }
/*      */         }
/*      */         
/* 1043 */         for (Entity entity : m_9236_().m_45976_(Entity.class, getSearchBox())) {
/*      */           
/* 1045 */           if (!getIgnoredTargets().shouldIgnoreEntity(entity)) {
/*      */             
/* 1047 */             Iterator<WitherStormHead> iterator1 = this.headManager.getHeads().iterator(); while (true) { WitherStormHead head; while (true) { if (iterator1.hasNext()) { head = iterator1.next();
/*      */                   
/* 1049 */                   if (TractorBeamHelper.isInsideTractorBeam(entity, (LivingEntity)this, 4.0D, head.getIndex()))
/*      */                   
/* 1051 */                   { if (getPhase() > 3 && head.getTarget() == entity) {
/*      */                       
/* 1053 */                       pullInTarget(entity, defaultSpeed, head); continue;
/*      */                     } 
/* 1055 */                     if (((Boolean)WitherStormModConfig.SERVER.canPickupMobClusters.get()).booleanValue() && !isDistracted(head.getIndex())) { if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity; if (this.entitySelector.test(living)) break;  continue; }  if (TRACTOR_BEAM_PULLABLE.test(entity)) break;  }  }  continue; }  // Byte code: goto -> 0 }  if (EntitySelector.f_20406_.test(entity) && !alreadyATarget(entity, true) && !targetInUseBySegment(entity) && head.canSee(entity))
/*      */               {
/* 1057 */                 if (!this.trackedEntities.contains(entity)) {
/*      */                   double speed;
/*      */                   
/* 1060 */                   if (TRACTOR_BEAM_PULLABLE.test(entity)) {
/* 1061 */                     speed = 0.4D;
/* 1062 */                   } else if (!(entity instanceof Player)) {
/* 1063 */                     speed = defaultSpeed - 0.05D + (new Random(entity.m_19879_())).nextDouble() * 0.1D;
/*      */                   } else {
/* 1065 */                     speed = defaultSpeed;
/* 1066 */                   }  pullInTarget(entity, speed, head);
/* 1067 */                   if (ABSORBABLE.test(entity) && entity.m_20182_().m_82554_(head.getHeadPos()) < 20.0D) {
/* 1068 */                     this.trackedEntities.trackEntityToConsume(entity);
/*      */                   }
/*      */                 } 
/*      */               } }
/*      */           
/*      */           } 
/*      */         } 
/*      */         
/* 1076 */         if (isAddedToWorld())
/*      */         {
/* 1078 */           if (this.f_19797_ % 20 == 0 && isCompletelyInvulnerable()) {
/* 1079 */             m_5634_(10.0F);
/*      */           }
/*      */         }
/* 1082 */         this.bossEvent.ifPresent(bossEvent -> bossEvent.m_142711_(m_21223_() / m_21233_()));
/*      */         
/* 1084 */         if (m_5448_() != null) {
/* 1085 */           this.idleTargetTicks++;
/*      */         }
/* 1087 */         if (this.idleTargetTicks > 1800 || m_5448_() == null) {
/*      */           
/* 1089 */           m_6710_(null);
/* 1090 */           this.idleTargetTicks = 0;
/*      */         } 
/*      */ 
/*      */         
/* 1094 */         if (getPhase() > 3)
/*      */         {
/* 1096 */           for (WitherStormHead head : this.headManager.getHeads()) {
/*      */             
/* 1098 */             if (tractorBeamActive(head.getIndex())) {
/*      */               
/* 1100 */               AABB headBox = head.getBoundingBox();
/* 1101 */               List<Projectile> headBoxEntities = m_9236_().m_45976_(Projectile.class, headBox);
/* 1102 */               for (Projectile projectile : headBoxEntities) {
/*      */                 
/* 1104 */                 if (projectile.m_19749_() != this) {
/*      */                   
/* 1106 */                   if (head.getHeadInjureAttemptCooldown() <= 0 && head.getHeadInjuryTicks() <= 0) {
/*      */                     
/* 1108 */                     head.setHeadInjureAttemptCooldown(40);
/* 1109 */                     if (!isDeadOrPlayingDead())
/*      */                     {
/* 1111 */                       if (head.checkAndCountAttack()) {
/*      */                         
/* 1113 */                         head.hurt(projectile.m_19749_(), this.headManager.getHeadInjuryTime());
/* 1114 */                         Entity entity = projectile.m_19749_(); if (entity instanceof Player) { Player owner = (Player)entity;
/* 1115 */                           owner.m_6330_(SoundEvents.f_11686_, SoundSource.PLAYERS, 1.0F, 1.0F); }
/*      */                       
/*      */                       }  } 
/*      */                   } 
/* 1119 */                   if (!(projectile instanceof net.minecraft.world.entity.projectile.ThrownTrident)) {
/* 1120 */                     projectile.m_146870_();
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/* 1127 */         CommandBlockEntity commandBlock = getBowelsCommandBlock();
/* 1128 */         if (commandBlock != null)
/*      */         {
/* 1130 */           if (commandBlock.m_21223_() < commandBlock.m_21233_())
/*      */           {
/* 1132 */             if (this.nextFlicker > 0) {
/*      */               
/* 1134 */               this.nextFlicker--;
/* 1135 */               if (this.nextFlicker == 0) {
/*      */                 
/* 1137 */                 doFlicker();
/* 1138 */                 this.nextFlicker = (int)(this.f_19796_.m_188503_(40) + 60.0D * Math.max(0.2D, (commandBlock.m_21223_() / commandBlock.m_21233_())));
/*      */               } 
/*      */             } 
/*      */           }
/*      */         }
/*      */ 
/*      */         
/* 1145 */         if ((this.f_19862_ || this.f_19863_) && ForgeEventFactory.getMobGriefingEvent(m_9236_(), (Entity)this) && getPhase() > 3)
/*      */         {
/* 1147 */           for (int i = 0; i < 10; i++) {
/*      */             BlockPos pos; int x, y, z;
/* 1149 */             Direction direction = Direction.m_235672_(this.f_19796_);
/*      */             
/* 1151 */             AABB box = m_20191_();
/* 1152 */             int scale = 2;
/* 1153 */             switch (direction.m_122434_()) {
/*      */ 
/*      */               
/*      */               case Y:
/* 1157 */                 x = Mth.m_14107_(box.f_82288_ + (box.f_82291_ - box.f_82288_) * this.f_19796_.m_188500_());
/* 1158 */                 y = Mth.m_14107_((direction == Direction.DOWN) ? (box.f_82289_ - scale) : (box.f_82292_ + scale));
/* 1159 */                 z = Mth.m_14107_(box.f_82290_ + (box.f_82293_ - box.f_82290_) * this.f_19796_.m_188500_());
/* 1160 */                 pos = new BlockPos(x, y, z);
/*      */                 break;
/*      */ 
/*      */               
/*      */               case X:
/* 1165 */                 x = Mth.m_14107_((direction == Direction.WEST) ? (box.f_82288_ - scale) : (box.f_82291_ + scale));
/* 1166 */                 y = Mth.m_14107_(box.f_82289_ + (box.f_82292_ - box.f_82289_) * this.f_19796_.m_188500_());
/* 1167 */                 z = Mth.m_14107_(box.f_82290_ + (box.f_82293_ - box.f_82290_) * this.f_19796_.m_188500_());
/* 1168 */                 pos = new BlockPos(x, y, z);
/*      */                 break;
/*      */ 
/*      */               
/*      */               case Z:
/* 1173 */                 x = Mth.m_14107_(box.f_82288_ + (box.f_82291_ - box.f_82288_) * this.f_19796_.m_188500_());
/* 1174 */                 y = Mth.m_14107_(box.f_82289_ + (box.f_82292_ - box.f_82289_) * this.f_19796_.m_188500_());
/* 1175 */                 z = Mth.m_14107_((direction == Direction.NORTH) ? (box.f_82290_ - scale) : (box.f_82293_ + scale));
/* 1176 */                 pos = new BlockPos(x, y, z);
/*      */                 break;
/*      */               
/*      */               default:
/* 1180 */                 throw new IllegalArgumentException("Unexpected value: " + direction.m_122434_());
/*      */             } 
/* 1182 */             BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(m_9236_());
/* 1183 */             assert cluster != null;
/* 1184 */             cluster.populateWithRadius(pos, 3.0F + this.f_19796_.m_188501_() * 2.0F, state -> !state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
/* 1185 */             if (cluster.getSize() > 0) {
/*      */               
/* 1187 */               this.trackedEntities.trackEntityToConsume(cluster);
/* 1188 */               cluster.setRotationDelta(new Vec2(this.f_19796_.m_188503_(20) * 0.1F / 2.0F, this.f_19796_.m_188503_(20) * 0.1F / 2.0F));
/* 1189 */               cluster.m_20242_(true);
/* 1190 */               cluster.setPhysics(false);
/* 1191 */               cluster.setShakeTime(5);
/* 1192 */               m_9236_().m_7967_(cluster);
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1214 */         if (((Boolean)WitherStormModConfig.SERVER.caveRumbles.get()).booleanValue() && this.nextUndergroundRumble > 0) {
/*      */           
/* 1216 */           this.nextUndergroundRumble--;
/* 1217 */           if (this.nextUndergroundRumble == 0) {
/*      */             
/* 1219 */             if (getPhase() > 3)
/*      */             {
/* 1221 */               for (ServerPlayer tracking : m_9236_().m_45976_(ServerPlayer.class, getSearchBox().m_82400_(50.0D))) {
/*      */                 
/* 1223 */                 if (!WorldUtil.canSeeOrIsNotInASmallArea((Entity)this, (Entity)tracking))
/* 1224 */                   WorldUtil.doCaveRumble((ServerLevel)m_9236_(), tracking, ((Double)WitherStormModConfig.SERVER.caveRumbleIntensity.get()).doubleValue(), this.f_19796_); 
/*      */               } 
/*      */             }
/* 1227 */             if (((Boolean)WitherStormModConfig.SERVER.chanceForExtendedRumbles.get()).booleanValue() && this.f_19796_.m_188503_(3) != 0) {
/*      */               
/* 1229 */               this.nextUndergroundRumble = 100 + this.f_19796_.m_188503_(60);
/*      */             }
/*      */             else {
/*      */               
/* 1233 */               int min = ((Integer)WitherStormModConfig.SERVER.caveRumbleIntervalMin.get()).intValue() * 20;
/* 1234 */               int diff = ((Integer)WitherStormModConfig.SERVER.caveRumbleIntervalMax.get()).intValue() * 20 - min;
/* 1235 */               if (diff > 0) {
/* 1236 */                 this.nextUndergroundRumble = min + this.f_19796_.m_188503_(diff);
/*      */               } else {
/* 1238 */                 this.nextUndergroundRumble = min;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/* 1243 */         searchForPlayingJukeboxes();
/*      */         
/* 1245 */         Iterator<BlockPos> iterator = this.playingJukeboxes.iterator();
/* 1246 */         while (iterator.hasNext()) {
/*      */           
/* 1248 */           BlockPos pos = iterator.next();
/* 1249 */           if (!getSearchBox().m_82390_(Vec3.m_82512_((Vec3i)pos))) {
/*      */             
/* 1251 */             iterator.remove();
/*      */             
/*      */             continue;
/*      */           } 
/* 1255 */           BlockEntity entity = m_9236_().m_7702_(pos);
/* 1256 */           if (entity instanceof JukeboxBlockEntity) { JukeboxBlockEntity jukebox = (JukeboxBlockEntity)entity;
/*      */             
/* 1258 */             if (!jukebox.m_272025_()) {
/* 1259 */               iterator.remove();
/*      */             }
/*      */             continue; }
/*      */           
/* 1263 */           iterator.remove();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTrackEntity(Entity entity) {
/* 1273 */     SegmentsManager manager = getSegmentsManager().orElse((SegmentsManager)null);
/* 1274 */     if (manager != null)
/*      */     {
/* 1276 */       for (WitherStormSegmentEntity segment : manager.getSegments()) {
/*      */         
/* 1278 */         if (segment != null && segment.m_6084_())
/*      */         {
/* 1280 */           if (segment.getTrackedEntities().contains(entity))
/* 1281 */             return false; 
/*      */         }
/*      */       } 
/*      */     }
/* 1285 */     return WorldUtil.canSeeOrIsNotInASmallArea((Entity)this, entity);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_6153_() {
/* 1291 */     if (getPhase() > 3) {
/* 1292 */       this.witherStormDeathTime++;
/*      */     } else {
/* 1294 */       this.f_20919_++;
/*      */     } 
/* 1296 */     if (!m_20096_())
/*      */     {
/* 1298 */       for (WitherStormHead head : this.headManager.getHeads()) {
/* 1299 */         head.lerpHeadXTo(-50.0F, 64.0F);
/*      */       }
/*      */     }
/* 1302 */     if (!(m_9236_()).f_46443_) {
/*      */       
/* 1304 */       if (getPhase() > 5 && getDeathTime() < 240 && ForgeEventFactory.getMobGriefingEvent(m_9236_(), (Entity)this)) {
/* 1305 */         dropDeathClusters();
/*      */       }
/* 1307 */       if (getPhase() > 3 && getDeathTime() == 360) {
/*      */         
/* 1309 */         m_142687_(Entity.RemovalReason.KILLED);
/* 1310 */         Player nearest = m_9236_().m_45924_(m_20185_(), m_20186_(), m_20189_(), m_21133_(Attributes.f_22277_) + 50.0D, false);
/* 1311 */         if (nearest != null) {
/* 1312 */           dropDropsAt((Entity)nearest);
/*      */         }
/* 1314 */       } else if (getPhase() < 4 && this.f_20919_ == 20) {
/*      */         
/* 1316 */         m_142687_(Entity.RemovalReason.KILLED);
/*      */       } 
/*      */       
/* 1319 */       this.bossEvent.ifPresent(event -> event.m_142711_(1.0F - getDeathTime() / 360.0F));
/*      */     }
/*      */     else {
/*      */       
/* 1323 */       if (this.f_19797_ % 20 == 0) {
/*      */         
/* 1325 */         int size = Math.max(10, this.f_19796_.m_188503_(15));
/* 1326 */         for (int i = 0; i < size; i++) {
/*      */           
/* 1328 */           for (DebrisCluster cluster : getDebrisClusters()) {
/*      */             
/* 1330 */             if (!cluster.isDisabled()) {
/* 1331 */               cluster.setDisabled((this.f_19796_.m_188503_(getDebrisClusters().size()) == 0));
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/* 1336 */       float percentage = Math.max(0.0F, (360.0F - getDeathTime()) / 360.0F);
/* 1337 */       for (DebrisRingSettings settings : getDebrisRings())
/* 1338 */         settings.setAlpha(percentage); 
/* 1339 */       setShineAlpha(percentage);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void dropDeathClusters() {
/* 1345 */     int interval = 240 / getPhase();
/* 1346 */     if (getDeathTime() % interval == 0) {
/* 1347 */       dropMassCluster(getPhase() - 2);
/*      */     }
/* 1349 */     if (getDeathTime() % 5 == 0) {
/* 1350 */       dropMassCluster(2);
/*      */     }
/* 1352 */     if (getDeathTime() > 5)
/*      */     {
/* 1354 */       for (int i = 0; i < 3; i++) {
/* 1355 */         dropSmallMassCluster(1);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean alreadyATarget(Entity entity, boolean countMain) {
/* 1361 */     for (WitherStormHead head : this.headManager.getHeads()) {
/*      */       
/* 1363 */       if (countMain || !(head instanceof nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.MainHead))
/*      */       {
/* 1365 */         if (entity.equals(head.getTarget()))
/* 1366 */           return true; 
/*      */       }
/*      */     } 
/* 1369 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeFluidFromLook(float x, float y, int head) {
/* 1374 */     Vec3 vecPos = getHeadPos(head);
/* 1375 */     Vec3 end = vecPos.m_82549_(getViewVector(x, y, 200.0F));
/*      */     
/* 1377 */     if (ForgeEventFactory.getMobGriefingEvent(m_9236_(), (Entity)this) && getPhase() > 3) {
/*      */       
/* 1379 */       BlockHitResult result = m_9236_().m_45547_(new ClipContext(vecPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, null));
/* 1380 */       BlockPos hitPos = result.m_82425_();
/* 1381 */       if (WorldUtil.isLoaded((ServerLevel)m_9236_(), hitPos) && hitPos.m_123342_() > ((Integer)WitherStormModConfig.SERVER.tractorBeamFluidRemovalHeight.get()).intValue())
/*      */       {
/* 1383 */         for (int hitX = -6; hitX <= 6; hitX++) {
/*      */           
/* 1385 */           for (int hitY = -6; hitY <= 6; hitY++) {
/*      */             
/* 1387 */             for (int hitZ = -6; hitZ <= 6; hitZ++) {
/*      */               
/* 1389 */               BlockPos pos = hitPos.m_7918_(hitX, hitY, hitZ);
/* 1390 */               BlockState state = m_9236_().m_8055_(pos);
/* 1391 */               if (state.m_60819_().m_192917_((Fluid)Fluids.f_76193_))
/* 1392 */                 m_9236_().m_7731_(pos, Fluids.f_76192_.m_76145_().m_76188_(), 3); 
/* 1393 */               if (state.m_60819_().m_192917_((Fluid)Fluids.f_76195_)) {
/* 1394 */                 m_9236_().m_7731_(pos, Fluids.f_76194_.m_76145_().m_76188_(), 3);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void createClusterFromLook(float x, float y, int time, int head) {
/* 1405 */     Vec3 vecPos = getHeadPos(head);
/* 1406 */     Vec3 end = vecPos.m_82549_(getViewVector(x, y, 200.0F));
/*      */     
/* 1408 */     if (ForgeEventFactory.getMobGriefingEvent(m_9236_(), (Entity)this)) {
/*      */       
/* 1410 */       BlockHitResult result = m_9236_().m_45547_(new ClipContext(vecPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
/* 1411 */       BlockPos hitPos = result.m_82425_();
/* 1412 */       if (WorldUtil.isLoaded((ServerLevel)m_9236_(), hitPos))
/*      */       {
/*      */ 
/*      */         
/* 1416 */         for (int i = 0; i < 512; i++) {
/*      */           int offsetX, offsetY, offsetZ;
/*      */ 
/*      */ 
/*      */           
/* 1421 */           if (getPhase() <= 3) {
/*      */             
/* 1423 */             offsetX = (int)Math.round(this.f_19796_.m_188583_() * 1.0D);
/* 1424 */             offsetY = (int)Math.round(this.f_19796_.m_188583_() * 1.0D);
/* 1425 */             offsetZ = (int)Math.round(this.f_19796_.m_188583_() * 1.0D);
/*      */           }
/*      */           else {
/*      */             
/* 1429 */             offsetX = (int)Math.round(this.f_19796_.m_188583_() * 2.25D);
/* 1430 */             offsetY = (int)Math.round(this.f_19796_.m_188583_() * 2.25D);
/* 1431 */             offsetZ = (int)Math.round(this.f_19796_.m_188583_() * 2.25D);
/*      */           } 
/* 1433 */           BlockPos pos = hitPos.m_7918_(offsetX, offsetY, offsetZ);
/*      */           
/* 1435 */           if (WorldUtil.isBlockExposed(m_9236_(), pos)) {
/*      */ 
/*      */             
/* 1438 */             BlockState state = m_9236_().m_8055_(pos);
/* 1439 */             if (!state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST))
/*      */             {
/*      */               
/* 1442 */               if (this.f_19796_.m_188500_() <= 0.999D || (!state.m_204336_(Tags.Blocks.STONE) && !state.m_204336_(BlockTags.f_144274_) && !state.m_204336_(BlockTags.f_13029_)))
/*      */               {
/* 1444 */                 if (!((Boolean)WitherStormModConfig.SERVER.onlyTryPickingUpTractorTagged.get()).booleanValue() || state.m_204336_(WitherStormModBlockTags.TRACTOR_BEAM_DISTRACTION_BLOCKS)) {
/*      */ 
/*      */                   
/* 1447 */                   double radius = 1.0D;
/* 1448 */                   if (getPhase() == 4) {
/* 1449 */                     radius = Math.max(1.0D, Math.min(1.5D, 1.0D + 0.125D * this.f_19796_.m_188583_()));
/* 1450 */                   } else if (getPhase() == 5) {
/* 1451 */                     radius = Math.max(1.0D, Math.min(3.0D, 1.0D + 0.5D * this.f_19796_.m_188583_()));
/* 1452 */                   } else if (getPhase() == 6) {
/* 1453 */                     radius = Math.max(1.0D, Math.min(4.5D, 1.0D + 0.75D * this.f_19796_.m_188583_()));
/* 1454 */                   } else if (getPhase() == 7) {
/* 1455 */                     radius = Math.max(1.0D, Math.min(8.0D, 1.5D + 1.25D * this.f_19796_.m_188583_()));
/*      */                   } 
/*      */                   
/* 1458 */                   BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(m_9236_());
/* 1459 */                   assert cluster != null;
/* 1460 */                   cluster.populateWithRadius(pos, (float)radius, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
/* 1461 */                   cluster.setTime(time);
/* 1462 */                   if (this.f_19796_.m_188503_(3) == 0)
/* 1463 */                     cluster.m_5496_((SoundEvent)WitherStormModSoundEvents.BLOCK_CLUSTER_SHAKE.get(), 2.0F, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2F + 1.0F); 
/* 1464 */                   cluster.setCreatedFromTractorBeam(true);
/* 1465 */                   cluster.setHeadCreatedFrom(head);
/* 1466 */                   cluster.setTractorBeamDistanceThreshold(this.f_19796_.m_188500_() * 5.0D);
/* 1467 */                   this.trackedEntities.trackEntityToConsume(cluster);
/* 1468 */                   cluster.setRotationDelta(new Vec2((this.f_19796_.m_188503_(120) - 60) * 0.05F / (float)radius * 3.0F, (this.f_19796_.m_188503_(120) - 60) * 0.05F / (float)radius * 3.0F));
/* 1469 */                   cluster.m_20242_(true);
/* 1470 */                   cluster.setPhysics(false);
/* 1471 */                   m_9236_().m_7967_(cluster);
/*      */                   break;
/*      */                 }  } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setPlayerDeltaMovement(ServerPlayer player, Vec3 motion) {
/* 1482 */     PlayerMotionMessage message = new PlayerMotionMessage(motion);
/* 1483 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> player), message);
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public AABB m_6921_() {
/* 1489 */     return m_20191_().m_82400_(50.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public EntityDimensions m_6972_(@NotNull Pose pose) {
/* 1495 */     EntityDimensions size = getUnmodifiedDimensions(pose);
/* 1496 */     if (((Boolean)WitherStormModConfig.SERVER.squashHitbox.get()).booleanValue() && getPhase() > 3)
/* 1497 */       size = EntityDimensions.m_20395_(size.f_20377_, 1.0F); 
/* 1498 */     if (getPlayDeadManager().getState() == PlayDeadManager.State.PLAYING_DEAD)
/* 1499 */       size = EntityDimensions.m_20395_(size.f_20377_, 0.1F); 
/* 1500 */     return size;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityDimensions getUnmodifiedDimensions(Pose pos) {
/* 1505 */     EntityDimensions size = STARTING_SIZE;
/* 1506 */     if (getPhase() == 4) {
/* 1507 */       size = DESTROYER_SIZE;
/* 1508 */     } else if (getPhase() == 5) {
/* 1509 */       size = EVOLVED_DESTROYER_SIZE;
/* 1510 */     } else if (getPhase() == 6) {
/* 1511 */       size = DEVOURER_SIZE;
/* 1512 */     } else if (getPhase() == 7) {
/* 1513 */       size = EVOLVED_DEVOURER_SIZE;
/* 1514 */     }  return size;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getUnmodifiedHeight() {
/* 1519 */     return (getUnmodifiedDimensions(m_20089_())).f_20378_;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getUnmodifiedWidth() {
/* 1524 */     return (getUnmodifiedDimensions(m_20089_())).f_20377_;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getUnmodifiedSize() {
/* 1529 */     float width = getUnmodifiedWidth();
/* 1530 */     float height = getUnmodifiedHeight();
/* 1531 */     return (width + height + width) / 3.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected float m_6431_(@NotNull Pose pose, @NotNull EntityDimensions size) {
/* 1537 */     if (getPhase() > 3) {
/* 1538 */       return 17.5F;
/*      */     }
/* 1540 */     return super.m_6431_(pose, size);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6075_() {
/* 1546 */     this.headManager.baseTick();
/* 1547 */     super.m_6075_();
/* 1548 */     this.xBodyRotO = this.xBodyRot;
/* 1549 */     this.shineAlphaO = this.shineAlpha;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_8119_() {
/* 1555 */     super.m_8119_();
/*      */     
/* 1557 */     if (!(m_9236_()).f_46443_ && this.evolutionProfiler.isProfiling()) {
/* 1558 */       this.evolutionProfiler.tick(this);
/*      */     }
/* 1560 */     this.tentacleTickCountO = this.tentacleTickCount;
/* 1561 */     if (!isDeadOrPlayingDead()) {
/* 1562 */       this.tentacleTickCount += (int)(1.0F + this.f_267362_.m_267731_());
/*      */     }
/* 1564 */     this.lastConsumedEntities = getConsumedEntities();
/*      */     
/* 1566 */     if (!(m_9236_()).f_46443_) {
/*      */       
/* 1568 */       this.trackedEntities.tick((ServerLevel)m_9236_());
/* 1569 */       this.ignoredTargets.tick();
/* 1570 */       this.bowelsInstance.ifPresent(BowelsInstanceManager::tick);
/*      */ 
/*      */       
/* 1573 */       Vec3 absorptionPoint = m_20191_().m_82399_();
/* 1574 */       List<Entity> currentTracked = this.trackedEntities.getCurrentTrackedEntities();
/* 1575 */       List<Entity> toAdd = Lists.newArrayList();
/* 1576 */       AABB absorptionBox = m_20191_();
/* 1577 */       if (getPhase() > 3)
/* 1578 */         absorptionBox = m_20191_().m_82406_(getUnmodifiedWidth() / 1.5D); 
/* 1579 */       for (Entity entity : currentTracked) {
/*      */         
/* 1581 */         if (entity.m_6084_()) {
/*      */           
/* 1583 */           doEntityPulling(entity, absorptionPoint, absorptionBox);
/*      */           
/* 1585 */           if (entity instanceof BlockClusterEntity) { BlockClusterEntity cluster = (BlockClusterEntity)entity;
/*      */             
/* 1587 */             if (WorldUtil.isLoaded((ServerLevel)m_9236_(), cluster.m_20183_()) && cluster.getShakeTime() <= 0 && cluster.shouldCrumble() && this.f_19797_ % 20 == 0 && this.f_19796_.m_188503_(3) == 0) {
/* 1588 */               splitCluster(cluster, toAdd);
/*      */             } }
/*      */           
/*      */           continue;
/*      */         } 
/* 1593 */         this.trackedEntities.stopTrackingEntity(entity);
/*      */       } 
/*      */ 
/*      */       
/* 1597 */       for (Entity entity : toAdd) {
/* 1598 */         this.trackedEntities.trackEntityToConsume(entity);
/*      */       }
/*      */ 
/*      */       
/* 1602 */       if (getPhase() > 5) {
/*      */         
/* 1604 */         findSegments();
/* 1605 */         createSegments();
/* 1606 */         addSegments();
/*      */       } 
/* 1608 */       if (getPhase() < 6) {
/* 1609 */         removeSegments();
/*      */       } else {
/* 1611 */         readdSegments();
/*      */       } 
/* 1613 */       this.bossEvent.ifPresent(event -> {
/*      */             for (ServerPlayer tracking : this.playersTracking) {
/*      */               if (!smartBossMusic() || WorldUtil.canSeeOrIsNotInASmallArea((Entity)this, (Entity)tracking)) {
/*      */                 event.m_6543_(tracking);
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/*      */               event.m_6539_(tracking);
/*      */             } 
/*      */           });
/*      */     } 
/* 1625 */     int phase = getPhase();
/* 1626 */     float prevConsumption = getConsumptionAmountForPhase(phase - 1);
/* 1627 */     float consumptionForPhase = getConsumptionAmountForPhase(phase);
/* 1628 */     float consumption = getConsumedEntities();
/* 1629 */     this.phaseProgress = Mth.m_14036_((consumption - prevConsumption) / (consumptionForPhase - prevConsumption), 0.0F, 1.0F);
/*      */     
/* 1631 */     if (m_9236_().m_5776_()) {
/*      */       
/* 1633 */       for (DebrisCluster cluster : getDebrisClusters()) {
/*      */         
/* 1635 */         if (!cluster.isDisabled()) {
/* 1636 */           cluster.tick();
/*      */         }
/*      */       } 
/* 1639 */       if (shouldShine())
/*      */       {
/* 1641 */         if (phase > 0) {
/*      */           
/* 1643 */           float factor = phase - this.phaseProgress;
/* 1644 */           this.shineScale = getUnmodifiedHeight() * 10.0F / factor;
/*      */         }
/*      */         else {
/*      */           
/* 1648 */           this.shineScale = getUnmodifiedHeight();
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1653 */     checkConsumptionAmount();
/*      */     
/* 1655 */     if (this.partsEnabled) {
/*      */       
/* 1657 */       Section[] sections = getSections();
/* 1658 */       for (Section section : sections) {
/*      */         
/* 1660 */         if (section.isActive()) {
/* 1661 */           section.tick();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1666 */     if (!(m_9236_()).f_46443_ && shouldPlaySoundLoops()) {
/*      */       
/* 1668 */       StormSoundPositionMessage message = new StormSoundPositionMessage(m_19879_(), m_20185_(), m_20188_(), m_20189_(), (byte)getPhase());
/* 1669 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> m_9236_().m_46472_()), message);
/*      */     } 
/*      */     
/* 1672 */     this.headManager.tick();
/*      */     
/* 1674 */     this.onGroundAnimationO = this.onGroundAnimation;
/* 1675 */     if (m_20096_() && isDeadOrPlayingDead()) {
/*      */       
/* 1677 */       this.onGroundAnimation += 1.0F + this.f_19796_.m_188501_() * 2.0F;
/* 1678 */       if (this.onGroundAnimation > 300.0F) {
/* 1679 */         this.onGroundAnimation = 300.0F;
/*      */       }
/*      */     } else {
/*      */       
/* 1683 */       this.onGroundAnimation -= 1.0F + this.f_19796_.m_188501_() * 2.0F;
/* 1684 */       if (this.onGroundAnimation < 0.0F) {
/* 1685 */         this.onGroundAnimation = 0.0F;
/*      */       }
/*      */     } 
/* 1688 */     if (this.flickerTime > 0) {
/*      */       
/* 1690 */       this.flickerTime--;
/* 1691 */       this.shouldFlicker = (Mth.m_14089_((this.flickerTime + this.f_19796_.m_188503_(20))) * Mth.m_14031_((this.flickerTime + 30 + this.f_19796_.m_188503_(20))) < -0.5F);
/* 1692 */       if (this.flickerTime == 0) {
/* 1693 */         this.shouldFlicker = false;
/*      */       }
/*      */     } 
/*      */     
/* 1697 */     if ((m_9236_()).f_46443_ && ((Boolean)WitherStormModConfig.CLIENT.tractorBeamParticles.get()).booleanValue() && !isOnDistantRenderer())
/*      */     {
/* 1699 */       for (int i = 0; i < getTotalHeads(); i++) {
/*      */         
/* 1701 */         if (tractorBeamActive(i) && !isDeadOrPlayingDead() && getXBodyRot() == 0.0F) {
/*      */           
/* 1703 */           Vec3 headPos = getHeadPos(i);
/* 1704 */           float x = getHeadXRot(i);
/* 1705 */           float y = getHeadYRot(i);
/* 1706 */           for (int amount = 0; amount < 5; amount++) {
/*      */             
/* 1708 */             Vec3 lookVec = getViewVector(x, y, this.f_19796_.m_188501_() * 200.0F);
/* 1709 */             Vec3 pos = headPos.m_82549_(lookVec).m_82520_(0.0D, 5.5D, 0.0D);
/* 1710 */             double distanceFromHead = Math.sqrt(pos.m_82557_(headPos));
/* 1711 */             double distanceAllowed = distanceFromHead * 2.0D * 0.02D;
/* 1712 */             double randX = this.f_19796_.m_188583_() * distanceAllowed;
/* 1713 */             double randY = this.f_19796_.m_188583_() * distanceAllowed;
/* 1714 */             double randZ = this.f_19796_.m_188583_() * distanceAllowed;
/* 1715 */             pos = pos.m_82520_(randX, randY, randZ);
/* 1716 */             Pair<Boolean, Integer> result = TractorBeamHelper.isInsideTractorBeam(pos, (LivingEntity)this, 4.0D);
/* 1717 */             if (((Boolean)result.getFirst()).booleanValue()) {
/*      */               
/* 1719 */               Vec3 delta = pos.m_82546_(headPos).m_82541_().m_82490_(-0.8D);
/* 1720 */               if (WitherStormMod.isAprilFools() && ((Boolean)WitherStormModConfig.CLIENT.aprilFools.get()).booleanValue()) {
/* 1721 */                 m_9236_().m_6493_((ParticleOptions)ParticleTypes.f_123750_, true, pos.f_82479_, pos.f_82480_, pos.f_82481_, delta.f_82479_, delta.f_82480_, delta.f_82481_);
/*      */               } else {
/* 1723 */                 m_9236_().m_6493_((ParticleOptions)new TractorBeamParticleOptions(m_19879_(), ((Integer)result.getSecond()).intValue()), true, pos.f_82479_, pos.f_82480_, pos.f_82481_, delta.f_82479_, delta.f_82480_, delta.f_82481_);
/*      */               } 
/*      */             } 
/* 1726 */           }  if (getPhase() >= 4) {
/*      */             
/* 1728 */             float spread = 8.0F;
/* 1729 */             for (int p = 0; p < 10; p++) {
/*      */               
/* 1731 */               double cutoff = getTractorBeamCutoffDistance(i);
/* 1732 */               Vec3 end = headPos.m_82549_(getViewVector(x + this.f_19796_.m_188501_() * spread - spread / 2.0F, y + this.f_19796_.m_188501_() * spread - spread / 2.0F, (float)((cutoff == -1.0D) ? 200.0D : (cutoff + 30.0D)))).m_82520_(0.0D, 5.5D, 0.0D);
/* 1733 */               BlockHitResult blockHitResult = m_9236_().m_45547_(new ClipContext(headPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
/* 1734 */               BlockPos hitPos = blockHitResult.m_82425_();
/* 1735 */               if (WorldUtil.isBlockExposed(m_9236_(), hitPos)) {
/*      */                 
/* 1737 */                 BlockState hitState = m_9236_().m_8055_(hitPos);
/*      */                 
/* 1739 */                 Vec3 delta = Vec3.m_82512_((Vec3i)hitPos).m_82546_(headPos).m_82541_();
/* 1740 */                 m_9236_().m_6493_((ParticleOptions)(new BlockParticleOption(ParticleTypes.f_123794_, hitState)).setPos(hitPos), true, hitPos.m_123341_() + 0.5D, hitPos.m_123342_() + 1.0D, hitPos.m_123343_(), -delta.f_82479_, -delta.f_82480_, -delta.f_82481_);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected <T extends Entity> void doEntityPulling(T entity, Vec3 absorptionPoint, AABB absorptionBox) {
/* 1751 */     WitherStormPullBehavior<T> behavior = null;
/* 1752 */     if (WitherStormWorldInteractions.getInstance().hasPullBehavior(entity.m_6095_()))
/* 1753 */       behavior = WitherStormWorldInteractions.getInstance().getPullBehavior(entity.m_6095_()); 
/* 1754 */     if (behavior == null || behavior.canPullIn((Entity)entity, this)) {
/*      */ 
/*      */       
/* 1757 */       double speed = 0.5D;
/* 1758 */       if (behavior != null) {
/* 1759 */         speed = behavior.getSpeed((Entity)entity, this, absorptionPoint);
/*      */       }
/*      */       
/* 1762 */       Vec3 delta = absorptionPoint.m_82546_(entity.m_20182_());
/* 1763 */       double distanceToStorm = delta.m_82553_();
/* 1764 */       Vec3 defaultVelocity = delta.m_82541_().m_82542_(speed, speed, speed);
/*      */ 
/*      */       
/* 1767 */       if (distanceToStorm >= 320.0D || !WorldUtil.isLoaded((ServerLevel)m_9236_(), entity.m_20183_())) {
/* 1768 */         entity.m_6021_(absorptionPoint.f_82479_, absorptionPoint.f_82480_, absorptionPoint.f_82481_);
/*      */       }
/*      */       
/* 1771 */       if (behavior != null) {
/* 1772 */         entity.m_20256_(behavior.pullEntity((Entity)entity, this, absorptionPoint, defaultVelocity, speed));
/*      */       } else {
/* 1774 */         entity.m_20256_(defaultVelocity);
/*      */       } 
/*      */       
/* 1777 */       if (entity instanceof ItemEntity || (behavior != null && behavior.doClientsideVelocityUpdates((Entity)entity, this))) {
/*      */         
/* 1779 */         ServerLevel world = (ServerLevel)m_9236_();
/* 1780 */         for (ServerPlayer player : world.m_6907_()) {
/* 1781 */           player.f_8906_.m_9829_((Packet)new ClientboundSetEntityMotionPacket((Entity)entity));
/*      */         }
/*      */       } 
/*      */       
/* 1785 */       if (absorptionBox.m_82390_(entity.m_20182_())) {
/*      */         
/* 1787 */         this.trackedEntities.stopTrackingEntity((Entity)entity);
/* 1788 */         if (entity instanceof BlockClusterEntity) { BlockClusterEntity cluster = (BlockClusterEntity)entity; if (!cluster.shouldntCountToConsumedEntities())
/*      */           {
/* 1790 */             consumeEntity(cluster, cluster.getSize()); }  }
/*      */         
/* 1792 */         if (entity instanceof ItemEntity) { ItemEntity itemEntity = (ItemEntity)entity;
/*      */           
/* 1794 */           consumeEntity((Entity)itemEntity, itemEntity.m_32055_().m_41613_()); }
/*      */         
/* 1796 */         if (!(entity instanceof BlockClusterEntity) && !(entity instanceof ItemEntity))
/*      */         {
/* 1798 */           consumeEntity((Entity)entity, 1);
/*      */         }
/* 1800 */         if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity;
/*      */           
/* 1802 */           if (living instanceof TamableAnimal) { TamableAnimal tamable = (TamableAnimal)living; if (tamable.m_21805_() != null)
/* 1803 */               storePet((Entity)tamable);  }
/* 1804 */            living.m_6469_(WitherStormModDamageTypes.witherStormAttackMob((LivingEntity)this), Float.MAX_VALUE); }
/*      */         
/*      */         else
/*      */         
/* 1808 */         { entity.m_146870_(); }
/*      */       
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Optional<UltimateTargetManager> getUltimateTargetManager() {
/* 1816 */     return this.targetManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public void checkConsumptionAmount() {
/* 1821 */     if (!isDeadOrPlayingDead() && getConsumedEntities() > getConsumptionAmountForPhase(getPhase()) && getConsumedEntities() != this.lastConsumedEntities) {
/* 1822 */       evolve(false);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean evolve(boolean force) {
/* 1827 */     int nextPhase = getPhase() + 1;
/* 1828 */     if (canEvolve(force) && !MinecraftForge.EVENT_BUS.post((Event)new WitherStormEvolveEvent(this, nextPhase))) {
/*      */       
/* 1830 */       evolveToPhase(nextPhase);
/* 1831 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1835 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void evolveToPhase(int phase) {
/* 1841 */     setPhase(phase);
/* 1842 */     if (this.evolutionProfiler.isProfiling())
/* 1843 */       this.evolutionProfiler.onEvolve(this); 
/* 1844 */     this.targetManager.ifPresent(manager -> {
/*      */           if (((Boolean)WitherStormModConfig.SERVER.chaseOnPhaseChange.get()).booleanValue() && getPhase() > 3) {
/*      */             manager.accelerate();
/*      */           }
/*      */         });
/* 1849 */     if (this.shouldPlayGlobalSounds)
/*      */     {
/* 1851 */       if (getPhase() == 4) {
/* 1852 */         playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_EVOLVES.get(), 1.0F, 1.0F);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void m_6210_() {
/* 1859 */     double x = m_20185_();
/* 1860 */     double y = m_20186_();
/* 1861 */     double z = m_20189_();
/* 1862 */     super.m_6210_();
/* 1863 */     m_6034_(x, y, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSoundToEveryone(SoundEvent event, float volume, float pitch) {
/* 1868 */     GlobalSoundMessage message = new GlobalSoundMessage(event, volume, pitch);
/* 1869 */     if (((Boolean)WitherStormModConfig.SERVER.shouldPlayGlobalSoundsCrossDimensionally.get()).booleanValue()) {
/* 1870 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.ALL.noArg(), message);
/*      */     } else {
/* 1872 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> m_9236_().m_46472_()), message);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void onRemovedFromWorld() {
/* 1878 */     super.onRemovedFromWorld();
/*      */     
/* 1880 */     if (!(m_9236_()).f_46443_) {
/* 1881 */       WitherStormSyncHelper.removeWitherStorm(this);
/*      */     }
/* 1883 */     if (!m_6084_()) {
/*      */       
/* 1885 */       getPlayDeadManager().removePodium();
/* 1886 */       this.trackedEntities.destroyAllClusters();
/* 1887 */       this.trackedEntities.clearAndMakeAllFall();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6667_(@NotNull DamageSource source) {
/* 1894 */     super.m_6667_(source);
/* 1895 */     if (!(m_9236_()).f_46443_) {
/*      */       
/* 1897 */       spawnConsumedPets(Vec3.m_82539_((Vec3i)m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, m_20183_())));
/*      */       
/* 1899 */       if (getPhase() > 3) {
/*      */         
/* 1901 */         if (this.shouldPlaySoundLoop) {
/*      */           
/* 1903 */           m_9236_().m_6263_(null, m_20185_(), m_20186_(), m_20189_(), (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_DEATH.get(), SoundSource.HOSTILE, 20.0F, 1.0F);
/* 1904 */           RemoveSoundLoopMessage message = new RemoveSoundLoopMessage(this);
/* 1905 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> m_9236_().m_46472_()), message);
/*      */         } 
/*      */         
/* 1908 */         for (WitherStormHead head : this.headManager.getHeads()) {
/* 1909 */           head.doRoar(false);
/*      */         }
/* 1911 */         this.trackedEntities.clearAndMakeAllFall();
/*      */       } 
/*      */       
/* 1914 */       List<LivingEntity> entities = m_9236_().m_45976_(LivingEntity.class, getSearchBox());
/* 1915 */       for (LivingEntity living : entities) {
/*      */         
/* 1917 */         living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(WitherSicknessTracker::cure);
/*      */         
/* 1919 */         if (living instanceof WitherSickened) { WitherSickened sickened = (WitherSickened)living;
/* 1920 */           sickened.cure((ServerLevel)m_9236_()); }
/*      */       
/*      */       } 
/* 1923 */       getSegmentsManager().ifPresent(SegmentsManager::killSegments);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void performRangedAttack(int head, LivingEntity entity) {
/* 1929 */     performRangedAttack(head, entity.m_20185_(), entity.m_20186_() + entity.m_20192_() * 0.5D, entity.m_20189_(), (head == 0 && this.f_19796_.m_188501_() < 0.001F));
/*      */   }
/*      */ 
/*      */   
/*      */   public void performRangedAttack(int head, double x, double y, double z, boolean dangerous) {
/* 1934 */     if (!m_20067_()) {
/* 1935 */       m_9236_().m_5898_(null, 1024, m_20183_(), 0);
/*      */     }
/* 1937 */     Vec3 pos = getHeadPos(head);
/* 1938 */     double d3 = x - pos.f_82479_;
/* 1939 */     double d4 = y - pos.f_82480_;
/* 1940 */     double d5 = z - pos.f_82481_;
/* 1941 */     WitherSkull witherskullentity = new WitherSkull(m_9236_(), (LivingEntity)this, d3, d4, d5);
/*      */     
/* 1943 */     witherskullentity.m_5602_((Entity)this);
/* 1944 */     if (dangerous) {
/* 1945 */       witherskullentity.m_37629_(true);
/*      */     }
/* 1947 */     witherskullentity.m_20343_(pos.f_82479_, pos.f_82480_, pos.f_82481_);
/* 1948 */     m_9236_().m_7967_((Entity)witherskullentity);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnFlamingWitherSkull(int head, double x, double y, double z) {
/* 1953 */     playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SHOOT.get(), head, Math.max(5.0F, m_6121_() - 5.0F), 1.0F);
/*      */     
/* 1955 */     Vec3 pos = getHeadPos(head);
/* 1956 */     double speed = ((Double)WitherStormModConfig.SERVER.flamingSkullSpeedModifier.get()).doubleValue();
/* 1957 */     double d3 = (x - pos.f_82479_) * speed;
/* 1958 */     double d4 = (y - pos.f_82480_) * speed;
/* 1959 */     double d5 = (z - pos.f_82481_) * speed;
/* 1960 */     FlamingWitherSkullEntity skull = new FlamingWitherSkullEntity(m_9236_(), (LivingEntity)this, d3, d4, d5);
/*      */     
/* 1962 */     skull.m_5602_((Entity)this);
/* 1963 */     skull.m_20343_(pos.f_82479_, pos.f_82480_, pos.f_82481_);
/* 1964 */     m_9236_().m_7967_((Entity)skull);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnBlueFlamingWitherSkull(int head, double x, double y, double z) {
/* 1969 */     playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SHOOT.get(), head, Math.max(5.0F, m_6121_() - 5.0F), 1.0F);
/*      */     
/* 1971 */     Vec3 pos = getHeadPos(head);
/* 1972 */     double speed = ((Double)WitherStormModConfig.SERVER.flamingSkullSpeedModifier.get()).doubleValue();
/* 1973 */     double d3 = (x - pos.f_82479_) * speed;
/* 1974 */     double d4 = (y - pos.f_82480_) * speed;
/* 1975 */     double d5 = (z - pos.f_82481_) * speed;
/* 1976 */     BlueFlamingWitherSkullEntity skull = new BlueFlamingWitherSkullEntity(m_9236_(), (LivingEntity)this, d3, d4, d5);
/*      */     
/* 1978 */     skull.m_5602_((Entity)this);
/* 1979 */     skull.m_20343_(pos.f_82479_, pos.f_82480_, pos.f_82481_);
/* 1980 */     m_9236_().m_7967_((Entity)skull);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getDesiredSegmentX(int segment) {
/* 1985 */     if (segment <= 0)
/*      */     {
/* 1987 */       return m_20185_();
/*      */     }
/*      */ 
/*      */     
/* 1991 */     double staticX = SEGMENT_DESIRED_X[segment - 1];
/* 1992 */     double staticZ = SEGMENT_DESIRED_Z[segment - 1];
/* 1993 */     if (isPlayingDead()) {
/*      */       
/* 1995 */       staticX = 45.0D;
/* 1996 */       staticZ = 0.0D;
/*      */     } 
/* 1998 */     float f = (this.f_20883_ + (180 * (segment - 1))) * 0.017453292F;
/* 1999 */     float offset = (float)Mth.m_14136_(staticZ, staticX);
/* 2000 */     float f1 = Mth.m_14089_(f + offset);
/* 2001 */     return m_20185_() + f1 * Math.sqrt(staticX * staticX + staticZ * staticZ);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDesiredSegmentY(int ignoredSegment) {
/* 2007 */     double pos = m_20188_();
/* 2008 */     if (isPlayingDead())
/* 2009 */       pos = m_20191_().m_82399_().m_7098_() + 10.0D; 
/* 2010 */     return pos;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getDesiredSegmentZ(int segment) {
/* 2015 */     if (segment <= 0)
/*      */     {
/* 2017 */       return m_20189_();
/*      */     }
/*      */ 
/*      */     
/* 2021 */     double staticX = SEGMENT_DESIRED_X[segment - 1];
/* 2022 */     double staticZ = SEGMENT_DESIRED_Z[segment - 1];
/* 2023 */     if (isPlayingDead()) {
/*      */       
/* 2025 */       staticX = 45.0D;
/* 2026 */       staticZ = 0.0D;
/*      */     } 
/* 2028 */     float f = (this.f_20883_ + (180 * (segment - 1))) * 0.017453292F;
/* 2029 */     float offset = (float)Mth.m_14136_(staticZ, staticX);
/* 2030 */     float f1 = Mth.m_14031_(f + offset);
/* 2031 */     return m_20189_() + f1 * Math.sqrt(staticX * staticX + staticZ * staticZ);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_5997_(double deltaX, double deltaY, double deltaZ) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_147240_(double strength, double x, double z) {}
/*      */ 
/*      */   
/*      */   public Vec3 getHeadPos(int head) {
/* 2044 */     return this.headManager.getHead(head).getHeadPos();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7380_(@NotNull CompoundTag compound) {
/* 2050 */     CompoundTag headsTag = new CompoundTag();
/* 2051 */     for (WitherStormHead head : this.headManager.getHeads())
/* 2052 */       headsTag.m_128365_(String.valueOf(head.getIndex()), (Tag)head.save()); 
/* 2053 */     compound.m_128365_("Heads", (Tag)headsTag);
/*      */     
/* 2055 */     CompoundTag playDeadManagerNBT = new CompoundTag();
/* 2056 */     PlayDeadManager playDeadManager = getPlayDeadManager();
/* 2057 */     playDeadManagerNBT.m_128379_("PodiumPlaced", playDeadManager.isPodiumPlaced());
/* 2058 */     if (playDeadManager.getPodiumPos() != null)
/* 2059 */       playDeadManagerNBT.m_128365_("PodiumPos", (Tag)NbtUtils.m_129224_(playDeadManager.getPodiumPos())); 
/* 2060 */     playDeadManagerNBT.m_128405_("StateTicks", playDeadManager.getTicks());
/* 2061 */     playDeadManagerNBT.m_128405_("State", playDeadManager.getState().ordinal());
/* 2062 */     playDeadManagerNBT.m_128405_("RevivalTime", playDeadManager.getTicksSinceRevival());
/* 2063 */     playDeadManagerNBT.m_128379_("RecentlyRevived", playDeadManager.hasRecentlyBeenRevived());
/* 2064 */     playDeadManagerNBT.m_128405_("CommandBlockMissingTicks", playDeadManager.getTicksSinceCommandBlockMissing());
/*      */     
/* 2066 */     compound.m_128365_("PlayDeadManager", (Tag)playDeadManagerNBT);
/* 2067 */     compound.m_128405_("Phase", getPhase());
/*      */     
/* 2069 */     super.m_7380_(compound);
/*      */     
/* 2071 */     compound.m_128405_("Invul", getInvulnerableTicks());
/* 2072 */     compound.m_128405_("StartingInvul", getStartingInvulnerableTicks());
/* 2073 */     compound.m_128405_("ConsumedEntities", getConsumedEntities());
/*      */     
/* 2075 */     compound.m_128379_("OtherHeadsDisabled", areOtherHeadsDisabled());
/*      */     
/* 2077 */     this.targetManager.ifPresent(manager -> manager.save(compound));
/*      */     
/* 2079 */     compound.m_128350_("YBodyRot", this.f_20883_);
/* 2080 */     compound.m_128350_("XBodyRot", this.xBodyRot);
/* 2081 */     compound.m_128379_("Mirrored", isMirrored());
/*      */     
/* 2083 */     this.summoningManager.ifPresent(manager -> compound.m_128405_("SymbiontSummoningCooldown", manager.getSummoningDelay()));
/*      */     
/* 2085 */     compound.m_128379_("ShouldShowHole", ((Boolean)this.f_19804_.m_135370_(SHOULD_SHOW_HOLE)).booleanValue());
/*      */     
/* 2087 */     compound.m_128379_("Resummoned", this.resummoned);
/*      */     
/* 2089 */     CompoundTag tag = new CompoundTag();
/* 2090 */     this.evolutionProfiler.save(tag);
/* 2091 */     compound.m_128365_("EvolutionProfiler", (Tag)tag);
/*      */     
/* 2093 */     ListTag consumedPets = new ListTag();
/* 2094 */     for (Map.Entry<UUID, CompoundTag> entry : this.consumedPets.entrySet()) {
/*      */       
/* 2096 */       UUID id = entry.getKey();
/* 2097 */       CompoundTag entity = entry.getValue();
/* 2098 */       CompoundTag entityTag = new CompoundTag();
/* 2099 */       entityTag.m_128362_("id", id);
/* 2100 */       entityTag.m_128365_("Entity", (Tag)entity);
/* 2101 */       consumedPets.add(entityTag);
/*      */     } 
/* 2103 */     compound.m_128365_("ConsumedPets", (Tag)consumedPets);
/*      */     
/* 2105 */     compound.m_128365_("TrackedEntities", (Tag)getTrackedEntities().save());
/*      */     
/* 2107 */     compound.m_128365_("IgnoredTargets", (Tag)getIgnoredTargets().save());
/*      */     
/* 2109 */     ListTag playingJukeboxes = new ListTag();
/* 2110 */     for (BlockPos pos : this.playingJukeboxes) {
/*      */       
/* 2112 */       CompoundTag entry = new CompoundTag();
/* 2113 */       entry.m_128365_("Pos", (Tag)NbtUtils.m_129224_(pos));
/* 2114 */       playingJukeboxes.add(entry);
/*      */     } 
/* 2116 */     compound.m_128365_("PlayingJukeboxes", (Tag)playingJukeboxes);
/*      */     
/* 2118 */     compound.m_128379_("IsConsumptionLocked", this.isLocked);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7378_(CompoundTag compound) {
/* 2124 */     CompoundTag headsTag = compound.m_128469_("Heads");
/* 2125 */     for (WitherStormHead head : this.headManager.getHeads()) {
/* 2126 */       head.read(headsTag.m_128469_(String.valueOf(head.getIndex())));
/*      */     }
/* 2128 */     CompoundTag playDeadManagerNBT = compound.m_128469_("PlayDeadManager");
/* 2129 */     PlayDeadManager playDeadManager = getPlayDeadManager();
/* 2130 */     playDeadManager.setPodiumPlaced(playDeadManagerNBT.m_128471_("PodiumPlaced"));
/* 2131 */     if (playDeadManagerNBT.m_128441_("PodiumPos"))
/* 2132 */       playDeadManager.setPodiumPos(NbtUtils.m_129239_(playDeadManagerNBT.m_128469_("PodiumPos"))); 
/* 2133 */     playDeadManager.setTickAmountAndO(playDeadManagerNBT.m_128451_("StateTicks"));
/* 2134 */     int state = playDeadManagerNBT.m_128451_("State");
/* 2135 */     if (state >= 0 && state < (PlayDeadManager.State.values()).length)
/* 2136 */       playDeadManager.setState(PlayDeadManager.State.values()[state]); 
/* 2137 */     playDeadManager.setTicksSinceRevival(playDeadManagerNBT.m_128451_("RevivalTime"));
/* 2138 */     playDeadManager.setRecentlyRevived(playDeadManagerNBT.m_128471_("RecentlyRevived"));
/* 2139 */     playDeadManager.setTicksSinceCommandBlockMissing(playDeadManagerNBT.m_128451_("CommandBlockMissingTicks"));
/* 2140 */     if (compound.m_128441_("ConsumedEntities")) {
/* 2141 */       setPhase(compound.m_128451_("Phase"), compound.m_128451_("ConsumedEntities"));
/*      */     } else {
/* 2143 */       setPhase(compound.m_128451_("Phase"));
/*      */     } 
/* 2145 */     super.m_7378_(compound);
/*      */     
/* 2147 */     setInvulnerableTicks(compound.m_128451_("Invul"));
/* 2148 */     setStartingInvulnerableTicks(compound.m_128451_("StartingInvul"));
/* 2149 */     if (m_8077_())
/* 2150 */       this.bossEvent.ifPresent(bossEvent -> bossEvent.m_6456_(m_5446_())); 
/* 2151 */     setOtherHeadsDisabled(compound.m_128471_("OtherHeadsDisabled"));
/*      */     
/* 2153 */     this.targetManager.ifPresent(manager -> manager.read(compound));
/*      */     
/* 2155 */     m_5618_(compound.m_128457_("YBodyRot"));
/* 2156 */     setXBodyRot(compound.m_128457_("XBodyRot"));
/* 2157 */     setMirrored(compound.m_128471_("Mirrored"));
/*      */     
/* 2159 */     getBossInfo().ifPresent(info -> info.m_8321_(!isPlayingDead()));
/*      */     
/* 2161 */     this.summoningManager.ifPresent(manager -> manager.setSummoningDelay(compound.m_128451_("SymbiontSummoningCooldown")));
/*      */     
/* 2163 */     this.f_19804_.m_135381_(SHOULD_SHOW_HOLE, Boolean.valueOf(compound.m_128471_("ShouldShowHole")));
/*      */     
/* 2165 */     this.resummoned = compound.m_128471_("Resummoned");
/*      */     
/* 2167 */     CompoundTag tag = compound.m_128469_("EvolutionProfiler");
/* 2168 */     this.evolutionProfiler.read(tag);
/*      */     
/* 2170 */     this.consumedPets.clear();
/* 2171 */     ListTag consumedPets = compound.m_128437_("ConsumedPets", 10);
/* 2172 */     for (int i = 0; i < consumedPets.size(); i++) {
/*      */       
/* 2174 */       CompoundTag entityTag = consumedPets.m_128728_(i);
/* 2175 */       this.consumedPets.put(entityTag.m_128342_("id"), entityTag.m_128469_("Entity"));
/*      */     } 
/*      */     
/* 2178 */     getTrackedEntities().read(compound.m_128469_("TrackedEntities"));
/*      */     
/* 2180 */     getIgnoredTargets().read(compound.m_128469_("IgnoredEntities"));
/*      */     
/* 2182 */     this.playingJukeboxes.clear();
/* 2183 */     ListTag playingJukeboxes = compound.m_128437_("PlayingJukeboxes", 10);
/* 2184 */     for (int j = 0; j < playingJukeboxes.size(); j++) {
/*      */       
/* 2186 */       CompoundTag entry = playingJukeboxes.m_128728_(j);
/* 2187 */       this.playingJukeboxes.add(NbtUtils.m_129239_(entry.m_128469_("Pos")));
/*      */     } 
/*      */     
/* 2190 */     this.isLocked = compound.m_128471_("IsConsumptionLocked");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6593_(Component component) {
/* 2196 */     super.m_6593_(component);
/* 2197 */     this.bossEvent.ifPresent(bossEvent -> bossEvent.m_6456_(m_5446_()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInvulnerableTicks(int ticks) {
/* 2202 */     this.f_19804_.m_135381_(INVULNERABLE, Integer.valueOf(ticks));
/*      */   }
/*      */ 
/*      */   
/*      */   public int phaseRadiusMultiplier(int phase) {
/* 2207 */     switch (phase) { case 0: 
/*      */       case 1: case 2: 
/*      */       case 3: 
/*      */       case 4: 
/*      */       case 5:
/*      */       
/*      */       case 6:
/*      */       
/*      */       case 7:
/* 2216 */        }  throw new IllegalStateException("Unexpected Wither Storm Phase value: " + phase);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int phaseRadiusMultiplierNature(int phase) {
/* 2223 */     switch (phase) { case 0: 
/*      */       case 1: case 2: 
/*      */       case 3: 
/*      */       case 4: 
/*      */       case 5:
/*      */       case 6:
/*      */       
/*      */       case 7:
/* 2231 */        }  throw new IllegalStateException("Erm... Unexpected Wither Storm Phase value: " + phase);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInvulnerableTicks() {
/* 2238 */     return ((Integer)this.f_19804_.m_135370_(INVULNERABLE)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStartingInvulnerableTicks(int ticks) {
/* 2243 */     this.f_19804_.m_135381_(STARTING_INVULNERABLE, Integer.valueOf(ticks));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getStartingInvulnerableTicks() {
/* 2248 */     return ((Integer)this.f_19804_.m_135370_(STARTING_INVULNERABLE)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPhase() {
/* 2253 */     return ((Integer)this.f_19804_.m_135370_(PHASE)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getConsumptionAmountForPhase(int phase) {
/* 2258 */     switch (phase) { case 0: 
/*      */       case 1: 
/*      */       case 2: 
/*      */       case 3: 
/*      */       case 4: 
/*      */       case 5:
/*      */       
/*      */       case 6:
/*      */       
/*      */       case 7:
/* 2268 */        }  int consumptionAmount = 0;
/*      */     
/* 2270 */     return adjustAmountForEvolutionSpeed(consumptionAmount);
/*      */   }
/*      */ 
/*      */   
/*      */   public int adjustAmountForEvolutionSpeed(int consumptionAmount) {
/* 2275 */     return (int)(consumptionAmount * getEvolutionSpeedModifier());
/*      */   }
/*      */ 
/*      */   
/*      */   public double getEvolutionSpeedModifier() {
/* 2280 */     double modifier = attributeOrConfigValue((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get(), WitherStormModConfig.SERVER.evolutionAttributeModifier);
/* 2281 */     WitherStormModifyEvolutionSpeedEvent event = new WitherStormModifyEvolutionSpeedEvent(this, modifier);
/* 2282 */     MinecraftForge.EVENT_BUS.post((Event)event);
/* 2283 */     return event.getOriginalEvolutionSpeedModifier();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setPhase(int phase) {
/* 2288 */     return setPhase(phase, getConsumptionAmountForPhase(phase - 1));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setPhase(int phase, int consumedEntities) {
/* 2293 */     if (phase >= 0 && phase <= 7) {
/*      */       
/* 2295 */       MinecraftForge.EVENT_BUS.post((Event)new WitherStormChangePhaseEvent(this, phase));
/*      */       
/* 2297 */       this.f_19804_.m_135381_(PHASE, Integer.valueOf(phase));
/* 2298 */       this.clusterRadius = (int)Math.max(1.0F, getPhase() * 0.75F);
/* 2299 */       this.entityConsumptionRadius = (getPhase() > 3) ? 80 : (12 + Math.round(getConsumedEntities() * 0.00445F));
/*      */ 
/*      */       
/* 2302 */       setConsumedEntities(consumedEntities);
/* 2303 */       m_20090_();
/* 2304 */       m_6210_();
/* 2305 */       if (isAddedToWorld())
/*      */       {
/* 2307 */         if (phase < 6) {
/* 2308 */           removeSegments();
/*      */         } else {
/* 2310 */           readdSegments();
/*      */         } 
/*      */       }
/* 2313 */       if (phase == 6) {
/*      */         
/* 2315 */         if (getConsumedEntities() < getSubPhaseRequirement(phase)) {
/* 2316 */           setOtherHeadsDisabled(true);
/*      */         }
/*      */       } else {
/*      */         
/* 2320 */         setOtherHeadsDisabled(false);
/*      */       } 
/*      */       
/* 2323 */       updateSections();
/*      */       
/* 2325 */       if (!(m_9236_()).f_46443_)
/*      */       {
/* 2327 */         if (shouldPlaySoundLoops()) {
/*      */           
/* 2329 */           CreateLoopingSoundMessage message = new CreateLoopingSoundMessage(this);
/* 2330 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> m_9236_().m_46472_()), message);
/*      */         }
/*      */         else {
/*      */           
/* 2334 */           RemoveSoundLoopMessage message = new RemoveSoundLoopMessage(this);
/* 2335 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> m_9236_().m_46472_()), message);
/*      */         } 
/*      */       }
/*      */       
/* 2339 */       this.headManager.update(phase);
/*      */       
/* 2341 */       AttributeInstance health = m_21051_(Attributes.f_22276_);
/* 2342 */       AttributeInstance armor = m_21051_(Attributes.f_22284_);
/* 2343 */       if (!(m_9236_()).f_46443_)
/*      */       {
/* 2345 */         if (phase < 4) {
/*      */           
/* 2347 */           health.m_22127_(HEALTH_MODIFIER_UUID);
/* 2348 */           armor.m_22127_(ARMOR_MODIFIER_UUID);
/* 2349 */           this.currentFlyingHeight = 10.0D;
/*      */         }
/*      */         else {
/*      */           
/* 2353 */           AttributeModifier healthModifier = new AttributeModifier(HEALTH_MODIFIER_UUID, "Phase health modifier", 624.0D, AttributeModifier.Operation.ADDITION);
/* 2354 */           assert health != null;
/* 2355 */           if (!health.m_22109_(healthModifier))
/* 2356 */             health.m_22125_(healthModifier); 
/* 2357 */           AttributeModifier armorModifier = new AttributeModifier(ARMOR_MODIFIER_UUID, "Phase armor modifier", ((phase + 1) * 2), AttributeModifier.Operation.ADDITION);
/* 2358 */           assert armor != null;
/* 2359 */           if (!armor.m_22109_(armorModifier))
/* 2360 */             armor.m_22125_(armorModifier); 
/* 2361 */           this.currentFlyingHeight = ((Integer)WitherStormModConfig.SERVER.flyingHeight.get()).intValue();
/*      */         } 
/*      */       }
/*      */       
/* 2365 */       this.segments.ifPresent(manager -> {
/*      */             for (WitherStormSegmentEntity entity : manager.getSegments()) {
/*      */               if (entity != null) {
/*      */                 entity.setPhase(getPhase());
/*      */               }
/*      */             } 
/*      */           });
/*      */ 
/*      */       
/* 2374 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 2378 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateSections() {
/* 2384 */     if (getPhase() == 5) {
/*      */       
/* 2386 */       if (getConsumptionAmountForPhase(getPhase()) <= getConsumedEntities())
/*      */       {
/* 2388 */         this.sections[0].setSize(60.0F, 35.0F);
/* 2389 */         this.sections[1].setSize(60.0F, 35.0F);
/* 2390 */         this.sections[0].setOffset(24.0D, 28.0D, 0.0D);
/* 2391 */         this.sections[1].setOffset(-24.0D, 28.0D, 0.0D);
/* 2392 */         this.fallingSection.setSize(50.0F, 70.0F);
/* 2393 */         this.fallingSection.setOffset(0.0D, 60.0D, 30.0D);
/*      */       }
/*      */       else
/*      */       {
/* 2397 */         this.sections[0].setSize(30.0F, 15.0F);
/* 2398 */         this.sections[1].setSize(30.0F, 15.0F);
/* 2399 */         this.sections[0].setOffset(13.0D, 28.0D, 0.0D);
/* 2400 */         this.sections[1].setOffset(-13.0D, 28.0D, 0.0D);
/* 2401 */         this.fallingSection.setSize(30.0F, 35.0F);
/* 2402 */         this.fallingSection.setOffset(0.0D, 35.0D, 15.0D);
/*      */       }
/*      */     
/* 2405 */     } else if (getPhase() == 6) {
/*      */       
/* 2407 */       this.sections[0].setSize(60.0F, 35.0F);
/* 2408 */       this.sections[1].setSize(60.0F, 35.0F);
/* 2409 */       this.sections[0].setOffset(24.0D, 28.0D, 0.0D);
/* 2410 */       this.sections[1].setOffset(-24.0D, 28.0D, 0.0D);
/* 2411 */       this.fallingSection.setSize(50.0F, 70.0F);
/* 2412 */       this.fallingSection.setOffset(0.0D, 60.0D, 30.0D);
/*      */     }
/* 2414 */     else if (getPhase() == 7) {
/*      */       
/* 2416 */       this.sections[0].setSize(95.0F, 45.0F);
/* 2417 */       this.sections[1].setSize(95.0F, 45.0F);
/* 2418 */       this.sections[0].setOffset(28.0D, 28.0D, 0.0D);
/* 2419 */       this.sections[1].setOffset(-28.0D, 28.0D, 0.0D);
/* 2420 */       this.fallingSection.setSize(60.0F, 85.0F);
/* 2421 */       this.fallingSection.setOffset(0.0D, 80.0D, 30.0D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7350_(@NotNull EntityDataAccessor<?> parameter) {
/* 2428 */     super.m_7350_(parameter);
/* 2429 */     if (parameter.equals(PHASE)) {
/*      */       
/* 2431 */       updateSections();
/* 2432 */       m_6210_();
/* 2433 */       this.headManager.update(getPhase());
/*      */     }
/* 2435 */     else if (parameter.equals(CONSUMED_ENTITIES)) {
/*      */       
/* 2437 */       updateSections();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeInvulnerable() {
/* 2443 */     setInvulnerableTicks(((Integer)WitherStormModConfig.SERVER.invulnerabilityTime.get()).intValue() * 20);
/* 2444 */     this.bossEvent.ifPresent(event -> event.m_142711_(0.0F));
/* 2445 */     m_21153_(1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7601_(@NotNull BlockState blockState, @NotNull Vec3 vector3d) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6457_(@NotNull ServerPlayer player) {
/* 2456 */     super.m_6457_(player);
/* 2457 */     this.playersTracking.add(player);
/* 2458 */     CommandBlockEntity commandBlock = getBowelsCommandBlock();
/* 2459 */     if (commandBlock != null) {
/* 2460 */       commandBlock.getOutsideBossBarViewers().add(player);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public SoundEvent getBossTheme() {
/* 2466 */     if (hasRecentlyBeenRevived())
/* 2467 */       return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_REVIVAL_THEME.get(); 
/* 2468 */     if (getPhase() == 5 && getConsumptionAmountForPhase(5) < getConsumedEntities())
/* 2469 */       return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_FORMIDIBOMB_THEME.get(); 
/* 2470 */     if (isBeingTornApart()) {
/* 2471 */       return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BOWELS_EXPOSED_THEME.get();
/*      */     }
/* 2473 */     return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BOSS_THEME.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int priority() {
/* 2479 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldPlayBossTheme() {
/* 2485 */     boolean commandBlockOverride = false;
/* 2486 */     CommandBlockEntity commandBlock = getPlayDeadManager().getCommandBlock();
/* 2487 */     if (commandBlock != null && commandBlock.m_6084_())
/* 2488 */       commandBlockOverride = commandBlock.getState().shouldShowOwnerBossBar(); 
/* 2489 */     return (super.shouldPlayBossTheme() && this.shouldPlaySoundLoop && !m_21525_() && !m_20067_() && (!isDeadOrPlayingDead() || commandBlockOverride));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkConfig() {
/* 2495 */     return ((Boolean)WitherStormModConfig.CLIENT.playWitherStormTheme.get()).booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean smartBossMusic() {
/* 2501 */     return ((Boolean)WitherStormModConfig.SERVER.smartBossbar.get()).booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStillAlive() {
/* 2507 */     return m_6084_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getPosition() {
/* 2513 */     return m_20182_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6452_(@NotNull ServerPlayer player) {
/* 2519 */     super.m_6452_(player);
/* 2520 */     this.playersTracking.remove(player);
/* 2521 */     this.bossEvent.ifPresent(bossEvent -> bossEvent.m_6539_(player));
/* 2522 */     CommandBlockEntity commandBlock = getBowelsCommandBlock();
/* 2523 */     if (commandBlock != null) {
/* 2524 */       commandBlock.removeOutsideBossBarViewer(player);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void m_6043_() {
/* 2530 */     this.f_20891_ = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_142535_(float p_225503_1_, float p_225503_2_, @NotNull DamageSource source) {
/* 2536 */     int damage = m_5639_(p_225503_1_, p_225503_2_);
/* 2537 */     if (damage > 15)
/* 2538 */       onBigFall(); 
/* 2539 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onBigFall() {
/* 2544 */     if (getPhase() > 3) {
/*      */       
/* 2546 */       m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_THUMP.get(), m_6121_() + 3.0F, 1.0F);
/* 2547 */       shake(30.0F, 12.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_147207_(@NotNull MobEffectInstance effect, @Nullable Entity entity) {
/* 2554 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public MobType m_6336_() {
/* 2560 */     return WitherStormModMobTypes.SICKENED;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_7341_(@NotNull Entity entity) {
/* 2566 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6072_() {
/* 2572 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_7301_(@NotNull MobEffectInstance effect) {
/* 2578 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_7090_() {
/* 2584 */     return (getInvulnerableTicks() > 900 && getPhase() < 4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_5789_() {
/* 2590 */     return (shouldDoNothing() && getPhase() < 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6469_(DamageSource source, float floatIn) {
/* 2606 */     if (source.m_269533_(DamageTypeTags.f_268738_)) {
/* 2607 */       return super.m_6469_(source, floatIn);
/*      */     }
/* 2609 */     if (m_6673_(source))
/*      */     {
/* 2611 */       return false;
/*      */     }
/* 2613 */     if (!source.m_276093_(DamageTypes.f_268722_) && !(source.m_7639_() instanceof WitherStormEntity)) {
/*      */       
/* 2615 */       if (shouldDoNothing() && !source.m_276093_(DamageTypes.f_268724_))
/*      */       {
/* 2617 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2621 */       if (getPhase() > 3 && isCompletelyInvulnerable()) {
/* 2622 */         return false;
/*      */       }
/* 2624 */       if (m_7090_()) {
/*      */         
/* 2626 */         Entity entity = source.m_7640_();
/* 2627 */         if (entity instanceof net.minecraft.world.entity.projectile.AbstractArrow) {
/* 2628 */           return false;
/*      */         }
/*      */       } 
/* 2631 */       Entity entity1 = source.m_7639_();
/* 2632 */       if (!(entity1 instanceof Player) && entity1 instanceof LivingEntity && ((LivingEntity)entity1).m_6336_() == m_6336_())
/*      */       {
/* 2634 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2638 */       if (this.destroyBlocksTick <= 0) {
/* 2639 */         this.destroyBlocksTick = 20;
/*      */       }
/* 2641 */       for (AdditionalHead head : this.headManager.getOtherHeads()) {
/* 2642 */         head.idleHeadUpdates += 3;
/*      */       }
/* 2644 */       return super.m_6469_(source, floatIn);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2650 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getConsumedEntities() {
/* 2656 */     return ((Integer)this.f_19804_.m_135370_(CONSUMED_ENTITIES)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConsumedEntities(int newAmount) {
/* 2661 */     this.f_19804_.m_135381_(CONSUMED_ENTITIES, Integer.valueOf(newAmount));
/* 2662 */     updateSections();
/* 2663 */     if (getPhase() == 6) {
/*      */       
/* 2665 */       int amount = getSubPhaseRequirement(getPhase());
/* 2666 */       if (newAmount > amount && areOtherHeadsDisabled()) {
/*      */         
/* 2668 */         setOtherHeadsDisabled(false);
/* 2669 */         for (AdditionalHead head : this.headManager.getOtherHeads())
/* 2670 */           head.setNextRoarTick(this.f_19797_ + this.f_19796_.m_188503_(30)); 
/* 2671 */         getSegmentsManager().ifPresent(manager -> {
/*      */               for (WitherStormSegmentEntity entity : manager.getSegments()) {
/*      */                 if (entity != null) {
/*      */                   for (AdditionalHead head : entity.headManager.getOtherHeads()) {
/*      */                     head.setNextRoarTick(entity.f_19797_ + entity.f_19796_.m_188503_(30));
/*      */                   }
/*      */                 }
/*      */               } 
/*      */             });
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void consumeEntity(@Nullable Entity entity, int amount) {
/* 2688 */     WitherStormConsumeEvent event = new WitherStormConsumeEvent(this, entity, amount);
/* 2689 */     if (!MinecraftForge.EVENT_BUS.post((Event)event)) {
/*      */       
/* 2691 */       amount = event.getConsumedAmount();
/* 2692 */       addToConsumedEntities(amount);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void addToConsumedEntities(int amount) {
/* 2698 */     if (!isConsumptionLocked()) {
/* 2699 */       setConsumedEntities(getConsumedEntities() + amount);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int m_8132_() {
/* 2705 */     return (getPhase() > 3) ? 180 : super.m_8132_();
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public LivingEntity getUltimateTarget() {
/* 2710 */     return this.targetManager.<LivingEntity>map(UltimateTargetManager::getUltimateTarget).orElse((LivingEntity)null);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Vec3 getUltimateTargetPos() {
/* 2715 */     return this.targetManager.<Vec3>map(manager -> {
/*      */           if (manager.isDistracted()) {
/*      */             assert manager.getDistractedPos() != null;
/*      */             
/*      */             return Vec3.m_82512_((Vec3i)manager.getDistractedPos());
/*      */           } 
/*      */           
/*      */           if (manager.isRandomStrolling()) {
/*      */             assert manager.getRandomStrollPos() != null;
/*      */             
/*      */             return Vec3.m_82512_((Vec3i)manager.getRandomStrollPos());
/*      */           } 
/*      */           
/*      */           return manager.getUltimateTargetPos();
/* 2729 */         }).orElse((Vec3)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public Section[] getSections() {
/* 2734 */     if (this.xBodyRot != 0.0F) {
/* 2735 */       return new Section[] { (Section)this.fallingSection, this.sections[2] };
/*      */     }
/* 2737 */     return this.sections;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onAddedToWorld() {
/* 2743 */     super.onAddedToWorld();
/* 2744 */     if (!(m_9236_()).f_46443_) {
/*      */       
/* 2746 */       WitherStormSyncHelper.sendWitherStormToClient(this);
/* 2747 */       this.bowelsInstance.ifPresent(BowelsInstanceManager::loadInstance);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void findSegments() {
/* 2753 */     if (!(m_9236_()).f_46443_)
/*      */     {
/* 2755 */       getSegmentsManager().ifPresent(manager -> {
/*      */             ServerLevel world = (ServerLevel)m_9236_();
/*      */             manager.findSegments(world);
/*      */           });
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void createSegments() {
/* 2765 */     getSegmentsManager().ifPresent(SegmentsManager::createSegments);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeSegments() {
/* 2770 */     getSegmentsManager().ifPresent(SegmentsManager::removeSegments);
/*      */   }
/*      */ 
/*      */   
/*      */   public void readdSegments() {
/* 2775 */     getSegmentsManager().ifPresent(SegmentsManager::readdSegments);
/*      */   }
/*      */ 
/*      */   
/*      */   public Optional<SegmentsManager> getSegmentsManager() {
/* 2780 */     return this.segments;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addSegments() {
/* 2785 */     getSegmentsManager().ifPresent(SegmentsManager::addSegments);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean areOtherHeadsDisabled() {
/* 2791 */     return this.headManager.areOtherHeadsDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOtherHeadsDisabled(boolean value) {
/* 2796 */     this.headManager.setOtherHeadsDisabled(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMirrored(boolean mirrored) {
/* 2801 */     this.f_19804_.m_135381_(MIRRORED, Boolean.valueOf(mirrored));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMirrored() {
/* 2806 */     return ((Boolean)this.f_19804_.m_135370_(MIRRORED)).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void createDebrisClusters(boolean hidden) {
/* 2811 */     ImmutableList.Builder<DebrisCluster> builder = ImmutableList.builder();
/* 2812 */     for (int i = 0; i < 100; i++) {
/*      */       
/* 2814 */       DebrisCluster cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0F, this.f_19796_.m_188501_() * 100.0F, 40.0F + this.f_19796_.m_188501_() * 160.0F, this.f_19796_.m_188501_() * 2.0F - 1.0F, 1.0F);
/* 2815 */       cluster.randomize(this.f_19796_, 15, 18.0F);
/* 2816 */       cluster.setDisabled(hidden);
/* 2817 */       cluster.determineRenderPhase();
/* 2818 */       builder.add(cluster);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2830 */     ImmutableList.Builder<DebrisCluster> hunchbackClustersBuilder = ImmutableList.builder();
/*      */     int j;
/* 2832 */     for (j = 0; j < 30; j++) {
/*      */       
/* 2834 */       DebrisCluster cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0F, this.f_19796_.m_188501_() * 3.0F, this.f_19796_.m_188501_() * 0.75F + 1.25F, this.f_19796_.m_188501_() * 8.0F - 4.0F, 0.125F);
/* 2835 */       cluster.randomize(this.f_19796_, 1, 0.1F);
/* 2836 */       cluster.setDisabled(hidden);
/* 2837 */       cluster.setGlowing(false);
/* 2838 */       cluster.setRenderPhase(1);
/* 2839 */       hunchbackClustersBuilder.add(cluster);
/*      */     } 
/*      */     
/* 2842 */     for (j = 0; j < 20; j++) {
/*      */       
/* 2844 */       DebrisCluster cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0F, this.f_19796_.m_188501_() * 4.0F, this.f_19796_.m_188501_() * 5.0F + 2.0F, this.f_19796_.m_188501_() * 12.0F - 6.0F, 0.25F);
/* 2845 */       cluster.randomize(this.f_19796_, 1, 0.5F);
/* 2846 */       cluster.setDisabled(hidden);
/* 2847 */       cluster.setGlowing(false);
/* 2848 */       cluster.setRenderPhase(2);
/* 2849 */       hunchbackClustersBuilder.add(cluster);
/*      */     } 
/*      */     
/* 2852 */     for (j = 0; j < 5; j++) {
/*      */       
/* 2854 */       DebrisCluster cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0F, this.f_19796_.m_188501_() * 4.0F, this.f_19796_.m_188501_() * 1.25F + 3.75F, this.f_19796_.m_188501_() * 6.0F - 3.0F, 0.125F);
/* 2855 */       cluster.randomize(this.f_19796_, 3, 0.5F);
/* 2856 */       cluster.setGlowing(true);
/* 2857 */       cluster.setDisabled(hidden);
/* 2858 */       cluster.setRenderPhase(2);
/* 2859 */       hunchbackClustersBuilder.add(cluster);
/*      */     } 
/*      */     
/* 2862 */     for (j = 0; j < 25; j++) {
/*      */       
/* 2864 */       DebrisCluster cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0F, this.f_19796_.m_188501_() * 20.0F - 10.0F, this.f_19796_.m_188501_() * 20.0F + 5.0F, this.f_19796_.m_188501_() * 6.0F - 3.0F, 0.25F);
/* 2865 */       cluster.randomize(this.f_19796_, 4, 2.5F);
/* 2866 */       cluster.setGlowing(false);
/* 2867 */       cluster.setDisabled(hidden);
/* 2868 */       cluster.setRenderPhase(3);
/* 2869 */       hunchbackClustersBuilder.add(cluster);
/*      */     } 
/*      */     
/* 2872 */     for (j = 0; j < 5; j++) {
/*      */       
/* 2874 */       DebrisCluster cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0F, this.f_19796_.m_188501_() * 15.0F - 5.0F, this.f_19796_.m_188501_() * 7.5F + 20.0F, this.f_19796_.m_188501_() * 2.0F - 1.0F, 0.125F);
/* 2875 */       cluster.randomize(this.f_19796_, 8, 1.0F);
/* 2876 */       cluster.setGlowing(true);
/* 2877 */       cluster.setDisabled(hidden);
/* 2878 */       cluster.setRenderPhase(3);
/* 2879 */       hunchbackClustersBuilder.add(cluster);
/*      */     } 
/*      */     
/* 2882 */     for (j = 0; j < 15; j++) {
/*      */       
/* 2884 */       DebrisCluster cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0F, this.f_19796_.m_188501_() * 10.0F - 5.0F, this.f_19796_.m_188501_() * 24.0F + 6.0F, this.f_19796_.m_188501_() * 5.0F - 2.5F, 1.0F);
/* 2885 */       cluster.randomize(this.f_19796_, 1, 0.5F);
/* 2886 */       cluster.setDisabled(hidden);
/* 2887 */       cluster.setGlowing(false);
/* 2888 */       cluster.setRenderPhase(3);
/* 2889 */       hunchbackClustersBuilder.add(cluster);
/*      */     } 
/* 2891 */     this.hunchbackDebrisClusters = (List<DebrisCluster>)hunchbackClustersBuilder.build();
/* 2892 */     this.debrisClusters = (List<DebrisCluster>)builder.build();
/*      */   }
/*      */ 
/*      */   
/*      */   public void createDebrisRings(boolean hidden) {
/* 2897 */     this.debrisRings = (List<DebrisRingSettings>)ImmutableList.of(new DebrisRingSettings(16, 100.0F, 60.0F, 30.0F, 25.0F, 0.02F, true, 4, hidden), new DebrisRingSettings(24, 160.0F, 120.0F, 10.0F, 50.0F, 0.005F, false, 4, hidden), new DebrisRingSettings(24, 180.0F, 100.0F, 30.0F, 60.0F, 0.001F, true, 4, hidden), new DebrisRingSettings(24, 130.0F, 50.0F, 80.0F, 10.0F, 0.008F, false, 4, hidden), new DebrisRingSettings(36, 240.0F, 200.0F, 0.0F, 40.0F, 0.002F, true, 6, hidden), new DebrisRingSettings(36, 250.0F, 210.0F, -30.0F, 10.0F, 0.001F, true, 6, hidden));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<DebrisRingSettings> getDebrisRings() {
/* 2903 */     return this.debrisRings;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<DebrisCluster> getDebrisClusters() {
/* 2908 */     return (getPhase() > 3) ? this.debrisClusters : this.hunchbackDebrisClusters;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDeathTime() {
/* 2913 */     return this.witherStormDeathTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_5802_(@NotNull BlockPos pos) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6573_(@NotNull Player player) {
/* 2924 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isOnDistantRenderer() {
/* 2929 */     return this.isOnDistantRenderer;
/*      */   }
/*      */ 
/*      */   
/*      */   public final void setOnDistantRenderer() {
/* 2934 */     this.isOnDistantRenderer = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEntityNearby(Entity entity) {
/* 2939 */     return getSearchBox().m_82390_(entity.m_20182_());
/*      */   }
/*      */ 
/*      */   
/*      */   public AABB getSearchBox() {
/* 2944 */     double range = (getPhase() > 3) ? m_21133_(Attributes.f_22277_) : m_21133_((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get());
/* 2945 */     return (getPhase() > 3) ? m_20191_().m_82377_(range, range + 255.0D, range) : m_20191_().m_82377_(range, range * 2.0D, range);
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(SoundEvent event, int head, float volume, float pitch) {
/* 2950 */     Vec3 pos = getHeadPos(head);
/* 2951 */     if (!m_20067_()) {
/* 2952 */       m_9236_().m_6263_(null, pos.f_82479_, pos.f_82480_, pos.f_82481_, event, m_5720_(), volume, pitch);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public float getTentacleAnimation(float partialTicks) {
/* 2958 */     return Mth.m_14179_(partialTicks, this.tentacleTickCountO, this.tentacleTickCount);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFadeAnimation(float partialTicks) {
/* 2964 */     return Mth.m_14179_(partialTicks, this.onGroundAnimationO, this.onGroundAnimation);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getMouthAnimation(int head, float partialTicks) {
/* 2970 */     return this.headManager.getHead(head).getMouthAnimation(partialTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBrokenJawAnimation(int head, float partialTicks) {
/* 2976 */     return this.headManager.getHead(head).getBrokenJawAnimation(partialTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFadeAnimation() {
/* 2982 */     return this.onGroundAnimation;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBeingTornApart() {
/* 2987 */     if (((Boolean)WitherStormModConfig.SERVER.shouldShowHole.get()).booleanValue())
/*      */     {
/* 2989 */       if (getPhase() >= 7) {
/*      */         
/* 2991 */         if (getConsumedEntities() >= getConsumptionAmountForPhase(7)) {
/* 2992 */           return true;
/*      */         }
/* 2994 */         return ((Boolean)this.f_19804_.m_135370_(SHOULD_SHOW_HOLE)).booleanValue();
/*      */       } 
/*      */     }
/* 2997 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setShouldShowHole(boolean flag) {
/* 3002 */     this.f_19804_.m_135381_(SHOULD_SHOW_HOLE, Boolean.valueOf(flag));
/*      */   }
/*      */ 
/*      */   
/*      */   public PlayDeadManager getPlayDeadManager() {
/* 3007 */     return this.playDeadManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldDoNothing() {
/* 3012 */     if (getInvulnerableTicks() > 0) {
/* 3013 */       return true;
/*      */     }
/* 3015 */     return getPlayDeadManager().getState().disablesAi();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDeadOrPlayingDead() {
/* 3021 */     if (m_21224_()) {
/* 3022 */       return true;
/*      */     }
/* 3024 */     return getPlayDeadManager().getState().disablesAi();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPlayingDead() {
/* 3030 */     return getPlayDeadManager().getState().disablesAi();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canEvolve(boolean force) {
/* 3035 */     if (!force) {
/* 3036 */       return (this.evolutionProfiler.isProfiling() || getPhase() < 5 || (getPhase() > 5 && getPhase() < 7));
/*      */     }
/* 3038 */     return (getPhase() < 7);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSubPhaseRequirement(int phase) {
/* 3043 */     int amount = getConsumptionAmountForPhase(phase - 1);
/* 3044 */     return amount + (getConsumptionAmountForPhase(phase) - amount) / 2;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldDoCustomMovement() {
/* 3049 */     PlayDeadManager.State state = getPlayDeadManager().getState();
/* 3050 */     if (state == PlayDeadManager.State.FALLING && getPlayDeadManager().getTicks() > 300) {
/* 3051 */       return false;
/*      */     }
/* 3053 */     return this.shouldDoCustomMovement;
/*      */   }
/*      */ 
/*      */   
/*      */   public PersistentTrackedEntities getTrackedEntities() {
/* 3058 */     return this.trackedEntities;
/*      */   }
/*      */ 
/*      */   
/*      */   public Optional<ServerBossEvent> getBossInfo() {
/* 3063 */     return this.bossEvent;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canFallOnBack() {
/* 3068 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeData(FriendlyByteBuf buffer) {
/* 3075 */     this.headManager.packHeadRotations().toPacket(buffer);
/* 3076 */     buffer.writeBoolean(m_20096_());
/* 3077 */     buffer.writeByte((byte)Mth.m_14143_(this.f_20883_ * 256.0F / 360.0F));
/* 3078 */     buffer.writeByte((byte)Mth.m_14143_(this.xBodyRot * 256.0F / 360.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void readData(FriendlyByteBuf buffer) {
/* 3084 */     this.headManager.updateHeadsFromPacked(HeadManager.PackedHeadRots.fromPacket(buffer));
/* 3085 */     m_6853_(buffer.readBoolean());
/* 3086 */     this.f_20883_ = (buffer.readByte() * 360) / 256.0F;
/* 3087 */     this.f_20884_ = this.f_20883_;
/* 3088 */     this.xBodyRot = (buffer.readByte() * 360) / 256.0F;
/* 3089 */     this.xBodyRotO = this.xBodyRot;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6094_() {
/* 3095 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6063_() {
/* 3101 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setXBodyRot(float rot) {
/* 3106 */     this.xBodyRot = rot;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_6138_() {
/* 3112 */     if (canFallOnBack() && getPlayDeadManager().getState() == PlayDeadManager.State.PLAYING_DEAD)
/*      */       return; 
/* 3114 */     super.m_6138_();
/*      */   }
/*      */ 
/*      */   
/*      */   public void onFallOnBack() {
/* 3119 */     m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_THUMP.get(), m_6121_() + 3.0F, 1.0F);
/* 3120 */     shake(30.0F, 12.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOnBack() {
/* 3125 */     return (this.xBodyRot >= 90.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void shake(float duration, float power) {
/* 3130 */     if (!(m_9236_()).f_46443_) {
/*      */       
/* 3132 */       ShakeScreenMessage message = new ShakeScreenMessage(duration, power);
/* 3133 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), message);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFormidibomb(FormidibombEntity entity) {
/* 3139 */     getPlayDeadManager().setFormidibomb(entity);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public FormidibombEntity getFormidibomb() {
/* 3144 */     return getPlayDeadManager().getFormidibomb();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeFormidibombed(boolean isExplosion) {
/* 3149 */     if (this.shouldIgnoreFormidibomb)
/*      */     {
/* 3151 */       return false;
/*      */     }
/* 3153 */     if (getPhase() < 5)
/*      */     {
/* 3155 */       return false;
/*      */     }
/* 3157 */     if (getPhase() > 6 && isBeingTornApart())
/*      */     {
/* 3159 */       return false;
/*      */     }
/* 3161 */     if (getFormidibomb() == null)
/*      */     {
/* 3163 */       return false;
/*      */     }
/* 3165 */     if (((Boolean)WitherStormModConfig.SERVER.endOfPhaseFiveBombableExclusively.get()).booleanValue() && getPhaseProgress() < 1.0F)
/*      */     {
/* 3167 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 3171 */     FormidibombEntity formidibomb = getFormidibomb();
/*      */     
/* 3173 */     if (!isExplosion && !formidibomb.m_6084_()) {
/* 3174 */       return false;
/*      */     }
/* 3176 */     return (formidibomb.getFuseLife() <= 600.0F + m_20270_((Entity)formidibomb));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void explode() {
/* 3182 */     if (getPhase() > 3 && !isDeadOrPlayingDead()) {
/* 3183 */       getPlayDeadManager().explode();
/*      */     }
/*      */   }
/*      */   
/*      */   public void reviveFromPlayingDead() {
/* 3188 */     if (isPlayingDead()) {
/* 3189 */       getPlayDeadManager().revive();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isReviving() {
/* 3194 */     return (getPlayDeadManager().getState() == PlayDeadManager.State.REVIVING);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6128_() {
/* 3200 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAttractingFormidibomb() {
/* 3205 */     boolean flag = false;
/* 3206 */     Stream<WrappedGoal> goals = this.f_21345_.m_25386_();
/* 3207 */     for (WrappedGoal prioritizedGoal : (WrappedGoal[])goals.<WrappedGoal>toArray(x$0 -> new WrappedGoal[x$0])) {
/*      */       
/* 3209 */       Goal goal = prioritizedGoal.m_26015_(); if (goal instanceof LookAtFormidibombGoal) { LookAtFormidibombGoal lookAtFormidibombGoal = (LookAtFormidibombGoal)goal;
/*      */         
/* 3211 */         if (lookAtFormidibombGoal.hasTarget())
/* 3212 */           flag = true;  }
/*      */     
/*      */     } 
/* 3215 */     return flag;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNearbyTickingFormidibomb() {
/* 3220 */     FormidibombEntity formidibomb = getFormidibomb();
/* 3221 */     return (formidibomb != null && formidibomb.m_6084_() && formidibomb.getStartFuse() > 0 && formidibomb.getFuseLife() <= 800);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean targetApplicable(LivingEntity entity, int head, TargetingConditions conditions) {
/* 3226 */     if (entity != this) {
/*      */       
/* 3228 */       if (!conditions.m_26885_((LivingEntity)this, entity)) {
/* 3229 */         return false;
/*      */       }
/* 3231 */       if (!this.headManager.getHead(head).canSee((Entity)entity)) {
/* 3232 */         return false;
/*      */       }
/* 3234 */       if (this.ignoredTargets.shouldIgnoreEntity((Entity)entity)) {
/* 3235 */         return false;
/*      */       }
/* 3237 */       if (this.trackedEntities.contains((Entity)entity)) {
/* 3238 */         return false;
/*      */       }
/* 3240 */       if (entity instanceof Player) { Player player = (Player)entity;
/*      */         
/* 3242 */         if (player.m_21205_().m_41720_() instanceof net.minecraft.world.item.ShieldItem) {
/*      */           
/* 3244 */           if (player.m_6117_()) {
/* 3245 */             return false;
/*      */           }
/* 3247 */         } else if (player.m_21206_().m_41720_() instanceof net.minecraft.world.item.ShieldItem) {
/*      */           
/* 3249 */           if (player.m_6117_()) {
/* 3250 */             return false;
/*      */           }
/*      */         } 
/* 3253 */         if (hasRecentlyBeenRevived()) {
/* 3254 */           return false;
/*      */         } }
/*      */       
/* 3257 */       if (getPhase() > 3 && entity.m_20145_()) {
/* 3258 */         return false;
/*      */       }
/* 3260 */       if (getPhase() > 3 && (targetInUseBySegment((Entity)entity) || alreadyATarget((Entity)entity, (head != 0)))) {
/* 3261 */         return false;
/*      */       }
/* 3263 */       if (isInsideOtherTractorBeam(entity, head)) {
/* 3264 */         return false;
/*      */       }
/* 3266 */       List<? extends LivingEntity> entities = m_9236_().m_45976_(LivingEntity.class, m_20191_().m_82377_(10.0D, 255.0D, 10.0D));
/* 3267 */       for (LivingEntity entityBelow : entities) {
/*      */         
/* 3269 */         if (entityBelow.m_7306_((Entity)entity)) {
/* 3270 */           return false;
/*      */         }
/*      */       } 
/* 3273 */       if (getPhase() > 3)
/*      */       {
/* 3275 */         if (isEntityBehindBack((Entity)entity)) {
/* 3276 */           return false;
/*      */         }
/*      */       }
/* 3279 */       LazyOptional<PlayerWitherStormData> optional = entity.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA);
/* 3280 */       if (optional.isPresent()) {
/*      */         
/* 3282 */         PlayerWitherStormData data = optional.resolve().get();
/* 3283 */         if (data.hasKilledSymbiontRecently()) {
/* 3284 */           return false;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3290 */       if (MinecraftForge.EVENT_BUS.post((Event)new CanWitherStormTargetMobEvent(this, entity))) {
/* 3291 */         return false;
/*      */       }
/* 3293 */       return !MinecraftForge.EVENT_BUS.post((Event)new CanWitherStormTargetMobEvent(this, entity));
/*      */     } 
/*      */ 
/*      */     
/* 3297 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isInsideOtherTractorBeam(LivingEntity entity, int head) {
/* 3303 */     List<WitherStormEntity> storms = Lists.newArrayList((Object[])new WitherStormEntity[] { this });
/* 3304 */     getSegmentsManager().ifPresent(manager -> {
/*      */           for (WitherStormSegmentEntity segment : manager.getSegments()) {
/*      */             if (segment != null && segment.m_6084_()) {
/*      */               storms.add(segment);
/*      */             }
/*      */           } 
/*      */         });
/*      */     
/* 3312 */     for (WitherStormEntity storm : storms) {
/*      */       
/* 3314 */       Pair<Boolean, Integer> flag = TractorBeamHelper.isInsideTractorBeam((Entity)entity, (LivingEntity)storm, 5.0D);
/* 3315 */       if (((Boolean)flag.getFirst()).booleanValue() && ((Integer)flag.getSecond()).intValue() != head)
/* 3316 */         return true; 
/*      */     } 
/* 3318 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6783_(double sqrDistance) {
/* 3324 */     double d0 = m_20191_().m_82309_();
/* 3325 */     if (Double.isNaN(d0)) {
/* 3326 */       d0 = 1.0D;
/*      */     }
/* 3328 */     d0 = d0 * 248.0D * Entity.m_20150_();
/* 3329 */     return (sqrDistance < d0 * d0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasRecentlyBeenRevived() {
/* 3334 */     return getPlayDeadManager().hasRecentlyBeenRevived();
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Vec3 getDistractedPos(int head) {
/* 3340 */     return this.headManager.getHead(head).getDistractedPos();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDistractedPos(int head, @Nullable Vec3 pos) {
/* 3346 */     this.headManager.getHead(head).setDistractedPos(pos);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void makeDistracted(Vec3 pos, int time, int head) {
/* 3352 */     this.headManager.getHead(head).makeDistracted(pos, time);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPosBehindBack(Vec3 pos) {
/* 3358 */     float angle = (float)(Mth.m_14136_(pos.m_7096_() - m_20185_(), pos.m_7094_() - m_20189_()) * 57.29577951308232D);
/* 3359 */     float angleDiff = (Mth.m_14177_(-this.f_20883_) - angle + 180.0F + 360.0F) % 360.0F - 180.0F;
/* 3360 */     return (angleDiff > 80.0F || angleDiff < -80.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeDistracted(int head, WitherStormBase.DistractionType type) {
/* 3366 */     return (super.canBeDistracted(head, type) && getPhase() > 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendToBowels(Entity entity) {
/* 3371 */     if (entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity;
/* 3372 */       WitherStormBowelsManager.queueEnter(player, this); }
/*      */     else
/* 3374 */     { WitherStormBowelsManager.enter((ServerLevel)entity.m_9236_(), this, entity); }
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   public float getXBodyRot() {
/* 3380 */     return this.xBodyRot;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getXBodyRotO() {
/* 3386 */     return this.xBodyRotO;
/*      */   }
/*      */ 
/*      */   
/*      */   public void doFlicker() {
/* 3391 */     this.flickerTime = 60;
/* 3392 */     m_9236_().m_7605_((Entity)this, (byte)11);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7822_(byte event) {
/* 3398 */     if (event == 11) {
/* 3399 */       doFlicker();
/*      */     } else {
/* 3401 */       super.m_7822_(event);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean shouldFlicker() {
/* 3406 */     return this.shouldFlicker;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public CommandBlockEntity getBowelsCommandBlock() {
/* 3411 */     BowelsInstanceManager manager = this.bowelsInstance.orElse((BowelsInstanceManager)null);
/* 3412 */     if (manager != null) {
/* 3413 */       return manager.getCommandBlock();
/*      */     }
/* 3415 */     return null;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public WitherStormBowelsManager.BowelsInstance getBowelsInstance() {
/* 3420 */     BowelsInstanceManager manager = this.bowelsInstance.orElse((BowelsInstanceManager)null);
/* 3421 */     if (manager != null) {
/* 3422 */       return manager.getBowelsInstance();
/*      */     }
/* 3424 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropDropsAt(Entity player) {
/* 3429 */     ItemStack stack = new ItemStack((ItemLike)WitherStormModItems.WITHERED_NETHER_STAR.get());
/* 3430 */     ItemEntity item = new ItemEntity(m_9236_(), player.m_20185_(), player.m_20188_() + 2.0D, player.m_20189_(), stack);
/* 3431 */     item.m_20334_(0.0D, -0.08D, 0.0D);
/* 3432 */     item.m_20242_(true);
/* 3433 */     m_9236_().m_7967_((Entity)item);
/* 3434 */     ServerLevel level = (ServerLevel)m_9236_();
/* 3435 */     ExperienceOrb.m_147082_(level, player.m_20182_().m_82520_(0.0D, 10.0D, 0.0D), ForgeEventFactory.getExperienceDrop((LivingEntity)this, this.f_20888_, m_213860_()));
/* 3436 */     for (Player nearby : m_9236_().m_45976_(Player.class, player.m_20191_().m_82400_(15.0D))) {
/*      */       
/* 3438 */       BlockPos nearestVillage = level.m_215011_(StructureTags.f_215889_, player.m_20183_(), 50, false);
/* 3439 */       if (nearestVillage != null && Math.sqrt(nearestVillage.m_123331_((Vec3i)player.m_20183_())) < 200.0D) {
/* 3440 */         nearby.m_7292_(new MobEffectInstance(MobEffects.f_19595_, 48000, 4, false, false, true));
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean shouldPlaySoundLoops() {
/* 3446 */     return (this.shouldPlaySoundLoop && !m_20067_() && !isDeadOrPlayingDead());
/*      */   }
/*      */ 
/*      */   
/*      */   public static SoundEvent getSoundForLoop(int phase, float fade) {
/* 3451 */     SoundEvent event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_LOOP.get();
/* 3452 */     if (phase > 3) {
/* 3453 */       event = WitherStormLoopingSoundManager.getSoundBasedOnDistance(fade);
/* 3454 */     } else if (phase < 3) {
/* 3455 */       event = (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_PULSE_LOOP.get();
/* 3456 */     }  return event;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldTrackUltimateTarget() {
/* 3461 */     CommandBlockEntity entity = getBowelsCommandBlock();
/* 3462 */     if (entity != null && entity.m_21223_() < entity.m_21233_()) {
/* 3463 */       return false;
/*      */     }
/* 3465 */     return this.shouldFollowUltimateTarget;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldRotateTowardsUltimateTarget() {
/* 3470 */     CommandBlockEntity entity = getBowelsCommandBlock();
/* 3471 */     return (entity == null || entity.m_21223_() / entity.m_21233_() > 0.25F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHeadInjured(int head) {
/* 3477 */     return this.headManager.getHead(head).isHeadInjured();
/*      */   }
/*      */ 
/*      */   
/*      */   public GoalSelector getGoalSelectorForHead(int head) {
/* 3482 */     if (head > 0) {
/* 3483 */       return this.headGoalSelectors.get(head - 1);
/*      */     }
/* 3485 */     return this.f_21345_;
/*      */   }
/*      */ 
/*      */   
/*      */   public GoalSelector getTargetSelectorForHead(int head) {
/* 3490 */     if (head > 0) {
/* 3491 */       return this.headTargetSelectors.get(head - 1);
/*      */     }
/* 3493 */     return this.f_21346_;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHeadShakeAnim(int head, float partialTicks) {
/* 3499 */     return this.headManager.getHead(head).getRollAngle(partialTicks);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tractorBeamActive(int head) {
/* 3505 */     boolean flag = false;
/* 3506 */     if (getPhase() < 4) {
/* 3507 */       flag = (head == 0 && getPhase() > 1);
/* 3508 */     } else if (getPhase() > 1) {
/* 3509 */       flag = true;
/* 3510 */     }  return (super.tractorBeamActive(head) && flag && !isDeadOrPlayingDead());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void pullInTarget(Entity target, double speed, WitherStormHead head) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: ifnull -> 542
/*      */     //   4: aload_1
/*      */     //   5: instanceof nonamecrackers2/witherstormmod/common/entity/WitherStormEntity
/*      */     //   8: ifne -> 542
/*      */     //   11: aload_0
/*      */     //   12: aload #4
/*      */     //   14: invokevirtual getIndex : ()I
/*      */     //   17: invokevirtual tractorBeamActive : (I)Z
/*      */     //   20: ifeq -> 542
/*      */     //   23: aload_1
/*      */     //   24: invokevirtual m_20202_ : ()Lnet/minecraft/world/entity/Entity;
/*      */     //   27: astore #5
/*      */     //   29: aconst_null
/*      */     //   30: astore #6
/*      */     //   32: aload #4
/*      */     //   34: invokevirtual getHeadPos : ()Lnet/minecraft/world/phys/Vec3;
/*      */     //   37: astore #7
/*      */     //   39: aload_1
/*      */     //   40: instanceof net/minecraft/world/entity/player/Player
/*      */     //   43: ifne -> 62
/*      */     //   46: aload_1
/*      */     //   47: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*      */     //   50: aload #7
/*      */     //   52: invokevirtual m_82554_ : (Lnet/minecraft/world/phys/Vec3;)D
/*      */     //   55: ldc2_w 25.0
/*      */     //   58: dcmpg
/*      */     //   59: ifge -> 69
/*      */     //   62: aload #7
/*      */     //   64: astore #6
/*      */     //   66: goto -> 87
/*      */     //   69: aload_1
/*      */     //   70: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*      */     //   73: aload_0
/*      */     //   74: aload #4
/*      */     //   76: invokevirtual getIndex : ()I
/*      */     //   79: ldc2_w -5.0
/*      */     //   82: invokestatic calculateClosestPoint : (Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/LivingEntity;ID)Lnet/minecraft/world/phys/Vec3;
/*      */     //   85: astore #6
/*      */     //   87: dload_2
/*      */     //   88: aload_1
/*      */     //   89: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*      */     //   92: aload #6
/*      */     //   94: invokevirtual m_82554_ : (Lnet/minecraft/world/phys/Vec3;)D
/*      */     //   97: ldc2_w 0.1
/*      */     //   100: dconst_1
/*      */     //   101: invokestatic m_14008_ : (DDD)D
/*      */     //   104: dmul
/*      */     //   105: dstore_2
/*      */     //   106: aload #6
/*      */     //   108: aload_1
/*      */     //   109: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*      */     //   112: invokevirtual m_82546_ : (Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;
/*      */     //   115: invokevirtual m_82541_ : ()Lnet/minecraft/world/phys/Vec3;
/*      */     //   118: dload_2
/*      */     //   119: invokevirtual m_82490_ : (D)Lnet/minecraft/world/phys/Vec3;
/*      */     //   122: astore #8
/*      */     //   124: aload #5
/*      */     //   126: instanceof net/minecraft/world/entity/LivingEntity
/*      */     //   129: ifeq -> 153
/*      */     //   132: aload #5
/*      */     //   134: checkcast net/minecraft/world/entity/LivingEntity
/*      */     //   137: astore #10
/*      */     //   139: aload_0
/*      */     //   140: getfield entitySelector : Ljava/util/function/Predicate;
/*      */     //   143: aload #10
/*      */     //   145: invokeinterface test : (Ljava/lang/Object;)Z
/*      */     //   150: ifeq -> 157
/*      */     //   153: iconst_1
/*      */     //   154: goto -> 158
/*      */     //   157: iconst_0
/*      */     //   158: istore #9
/*      */     //   160: aload_1
/*      */     //   161: invokevirtual m_20159_ : ()Z
/*      */     //   164: ifeq -> 200
/*      */     //   167: getstatic nonamecrackers2/witherstormmod/common/config/WitherStormModConfig.COMMON : Lnonamecrackers2/witherstormmod/common/config/WitherStormModConfig$CommonConfig;
/*      */     //   170: getfield shouldPickUpVehicles : Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;
/*      */     //   173: invokevirtual get : ()Ljava/lang/Object;
/*      */     //   176: checkcast java/lang/Boolean
/*      */     //   179: invokevirtual booleanValue : ()Z
/*      */     //   182: ifeq -> 200
/*      */     //   185: iload #9
/*      */     //   187: ifeq -> 200
/*      */     //   190: aload #5
/*      */     //   192: aload #8
/*      */     //   194: invokevirtual m_20256_ : (Lnet/minecraft/world/phys/Vec3;)V
/*      */     //   197: goto -> 206
/*      */     //   200: aload_1
/*      */     //   201: aload #8
/*      */     //   203: invokevirtual m_20256_ : (Lnet/minecraft/world/phys/Vec3;)V
/*      */     //   206: new net/minecraft/world/phys/AABB
/*      */     //   209: dup
/*      */     //   210: aload #7
/*      */     //   212: getfield f_82479_ : D
/*      */     //   215: ldc2_w 2.0
/*      */     //   218: dsub
/*      */     //   219: aload #7
/*      */     //   221: getfield f_82480_ : D
/*      */     //   224: ldc2_w 4.0
/*      */     //   227: dsub
/*      */     //   228: aload #7
/*      */     //   230: getfield f_82481_ : D
/*      */     //   233: ldc2_w 2.0
/*      */     //   236: dsub
/*      */     //   237: aload #7
/*      */     //   239: getfield f_82479_ : D
/*      */     //   242: ldc2_w 2.0
/*      */     //   245: dadd
/*      */     //   246: aload #7
/*      */     //   248: getfield f_82480_ : D
/*      */     //   251: ldc2_w 2.0
/*      */     //   254: dadd
/*      */     //   255: aload #7
/*      */     //   257: getfield f_82481_ : D
/*      */     //   260: ldc2_w 2.0
/*      */     //   263: dadd
/*      */     //   264: invokespecial <init> : (DDDDDD)V
/*      */     //   267: astore #10
/*      */     //   269: aload_1
/*      */     //   270: instanceof net/minecraft/server/level/ServerPlayer
/*      */     //   273: ifeq -> 290
/*      */     //   276: aload_1
/*      */     //   277: checkcast net/minecraft/server/level/ServerPlayer
/*      */     //   280: astore #11
/*      */     //   282: aload_0
/*      */     //   283: aload #11
/*      */     //   285: aload #8
/*      */     //   287: invokevirtual setPlayerDeltaMovement : (Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/phys/Vec3;)V
/*      */     //   290: aload #10
/*      */     //   292: aload_1
/*      */     //   293: invokevirtual m_20191_ : ()Lnet/minecraft/world/phys/AABB;
/*      */     //   296: invokevirtual m_82381_ : (Lnet/minecraft/world/phys/AABB;)Z
/*      */     //   299: ifeq -> 542
/*      */     //   302: aload_1
/*      */     //   303: instanceof net/minecraft/world/entity/LivingEntity
/*      */     //   306: ifeq -> 542
/*      */     //   309: aload_1
/*      */     //   310: checkcast net/minecraft/world/entity/LivingEntity
/*      */     //   313: astore #11
/*      */     //   315: aload #11
/*      */     //   317: invokevirtual m_21224_ : ()Z
/*      */     //   320: ifne -> 542
/*      */     //   323: aload_1
/*      */     //   324: instanceof net/minecraft/world/entity/player/Player
/*      */     //   327: ifeq -> 412
/*      */     //   330: aload_1
/*      */     //   331: checkcast net/minecraft/world/entity/player/Player
/*      */     //   334: astore #12
/*      */     //   336: aload #12
/*      */     //   338: invokevirtual m_21224_ : ()Z
/*      */     //   341: ifne -> 404
/*      */     //   344: aload_0
/*      */     //   345: getstatic net/minecraft/world/entity/ai/attributes/Attributes.f_22281_ : Lnet/minecraft/world/entity/ai/attributes/Attribute;
/*      */     //   348: invokevirtual m_21133_ : (Lnet/minecraft/world/entity/ai/attributes/Attribute;)D
/*      */     //   351: d2f
/*      */     //   352: fstore #13
/*      */     //   354: getstatic nonamecrackers2/witherstormmod/common/config/WitherStormModConfig.SERVER : Lnonamecrackers2/witherstormmod/common/config/WitherStormModConfig$ServerConfig;
/*      */     //   357: getfield instantChomp : Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;
/*      */     //   360: invokevirtual get : ()Ljava/lang/Object;
/*      */     //   363: checkcast java/lang/Boolean
/*      */     //   366: invokevirtual booleanValue : ()Z
/*      */     //   369: ifeq -> 377
/*      */     //   372: ldc_w 3.4028235E38
/*      */     //   375: fstore #13
/*      */     //   377: aload #12
/*      */     //   379: aload_0
/*      */     //   380: invokestatic witherStormAttack : (Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/damagesource/DamageSource;
/*      */     //   383: fload #13
/*      */     //   385: invokevirtual m_6469_ : (Lnet/minecraft/world/damagesource/DamageSource;F)Z
/*      */     //   388: pop
/*      */     //   389: aload #12
/*      */     //   391: invokevirtual m_21224_ : ()Z
/*      */     //   394: ifeq -> 404
/*      */     //   397: aload_0
/*      */     //   398: aload #12
/*      */     //   400: iconst_1
/*      */     //   401: invokevirtual consumeEntity : (Lnet/minecraft/world/entity/Entity;I)V
/*      */     //   404: aload #4
/*      */     //   406: invokevirtual startBiting : ()V
/*      */     //   409: goto -> 542
/*      */     //   412: aload_0
/*      */     //   413: aload #11
/*      */     //   415: iconst_1
/*      */     //   416: invokevirtual consumeEntity : (Lnet/minecraft/world/entity/Entity;I)V
/*      */     //   419: getstatic nonamecrackers2/witherstormmod/common/config/WitherStormModConfig.SERVER : Lnonamecrackers2/witherstormmod/common/config/WitherStormModConfig$ServerConfig;
/*      */     //   422: getfield healFromChomp : Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;
/*      */     //   425: invokevirtual get : ()Ljava/lang/Object;
/*      */     //   428: checkcast java/lang/Boolean
/*      */     //   431: invokevirtual booleanValue : ()Z
/*      */     //   434: ifeq -> 448
/*      */     //   437: aload_0
/*      */     //   438: aload #11
/*      */     //   440: invokevirtual m_21233_ : ()F
/*      */     //   443: fconst_2
/*      */     //   444: fdiv
/*      */     //   445: invokevirtual m_5634_ : (F)V
/*      */     //   448: aload #11
/*      */     //   450: instanceof net/minecraft/world/entity/TamableAnimal
/*      */     //   453: ifeq -> 477
/*      */     //   456: aload #11
/*      */     //   458: checkcast net/minecraft/world/entity/TamableAnimal
/*      */     //   461: astore #13
/*      */     //   463: aload #13
/*      */     //   465: invokevirtual m_21805_ : ()Ljava/util/UUID;
/*      */     //   468: ifnull -> 477
/*      */     //   471: aload_0
/*      */     //   472: aload #13
/*      */     //   474: invokevirtual storePet : (Lnet/minecraft/world/entity/Entity;)V
/*      */     //   477: aload_1
/*      */     //   478: aload_0
/*      */     //   479: invokestatic witherStormAttackMob : (Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/damagesource/DamageSource;
/*      */     //   482: ldc_w 3.4028235E38
/*      */     //   485: invokevirtual m_6469_ : (Lnet/minecraft/world/damagesource/DamageSource;F)Z
/*      */     //   488: pop
/*      */     //   489: aload #4
/*      */     //   491: invokevirtual startBiting : ()V
/*      */     //   494: aload #4
/*      */     //   496: instanceof nonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/AdditionalHead
/*      */     //   499: ifeq -> 542
/*      */     //   502: aload #4
/*      */     //   504: checkcast nonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/AdditionalHead
/*      */     //   507: astore #13
/*      */     //   509: aload #13
/*      */     //   511: aload_0
/*      */     //   512: getfield f_19797_ : I
/*      */     //   515: aload_0
/*      */     //   516: getfield f_19796_ : Lnet/minecraft/util/RandomSource;
/*      */     //   519: bipush #20
/*      */     //   521: invokeinterface m_188503_ : (I)I
/*      */     //   526: iadd
/*      */     //   527: aload_0
/*      */     //   528: getfield f_19796_ : Lnet/minecraft/util/RandomSource;
/*      */     //   531: bipush #60
/*      */     //   533: invokeinterface m_188503_ : (I)I
/*      */     //   538: iadd
/*      */     //   539: putfield nextHeadUpdate : I
/*      */     //   542: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #3515	-> 0
/*      */     //   #3517	-> 23
/*      */     //   #3518	-> 29
/*      */     //   #3519	-> 32
/*      */     //   #3520	-> 39
/*      */     //   #3521	-> 62
/*      */     //   #3523	-> 69
/*      */     //   #3524	-> 87
/*      */     //   #3525	-> 106
/*      */     //   #3526	-> 124
/*      */     //   #3527	-> 160
/*      */     //   #3528	-> 190
/*      */     //   #3530	-> 200
/*      */     //   #3531	-> 206
/*      */     //   #3532	-> 269
/*      */     //   #3533	-> 282
/*      */     //   #3534	-> 290
/*      */     //   #3536	-> 323
/*      */     //   #3538	-> 336
/*      */     //   #3540	-> 344
/*      */     //   #3541	-> 354
/*      */     //   #3542	-> 372
/*      */     //   #3543	-> 377
/*      */     //   #3544	-> 389
/*      */     //   #3545	-> 397
/*      */     //   #3547	-> 404
/*      */     //   #3551	-> 412
/*      */     //   #3553	-> 419
/*      */     //   #3554	-> 437
/*      */     //   #3555	-> 448
/*      */     //   #3556	-> 471
/*      */     //   #3557	-> 477
/*      */     //   #3558	-> 489
/*      */     //   #3559	-> 494
/*      */     //   #3560	-> 509
/*      */     //   #3564	-> 542
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   139	14	10	living	Lnet/minecraft/world/entity/LivingEntity;
/*      */     //   157	1	10	living	Lnet/minecraft/world/entity/LivingEntity;
/*      */     //   282	8	11	player	Lnet/minecraft/server/level/ServerPlayer;
/*      */     //   354	50	13	damage	F
/*      */     //   336	76	12	player	Lnet/minecraft/world/entity/player/Player;
/*      */     //   463	14	13	tamable	Lnet/minecraft/world/entity/TamableAnimal;
/*      */     //   509	33	13	additionalHead	Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/AdditionalHead;
/*      */     //   315	227	11	living	Lnet/minecraft/world/entity/LivingEntity;
/*      */     //   29	513	5	vehicle	Lnet/minecraft/world/entity/Entity;
/*      */     //   32	510	6	targetPullPos	Lnet/minecraft/world/phys/Vec3;
/*      */     //   39	503	7	headPos	Lnet/minecraft/world/phys/Vec3;
/*      */     //   124	418	8	delta	Lnet/minecraft/world/phys/Vec3;
/*      */     //   160	382	9	flag	Z
/*      */     //   269	273	10	headBB	Lnet/minecraft/world/phys/AABB;
/*      */     //   0	543	0	this	Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity;
/*      */     //   0	543	1	target	Lnet/minecraft/world/entity/Entity;
/*      */     //   0	543	2	speed	D
/*      */     //   0	543	4	head	Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/WitherStormHead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_21226_() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dropMassCluster(int radius) {
/* 3575 */     BlockClusterEntity cluster = ClusterBuilderHelper.buildRandomDeathCluster(m_9236_(), this.f_19796_, radius);
/* 3576 */     cluster.setSink(radius / 2 + 1);
/* 3577 */     cluster.m_146884_(m_20182_().m_82520_(0.0D, getUnmodifiedHeight() / 2.0D, 0.0D));
/* 3578 */     cluster.m_20334_(this.f_19796_.m_188583_() * 0.3D, 0.0D, this.f_19796_.m_188583_() * 0.3D);
/* 3579 */     cluster.setRotationDelta(new Vec2(this.f_19796_.m_188503_(20) * 0.3F / 2.0F, this.f_19796_.m_188503_(20) * 0.3F / 2.0F));
/* 3580 */     cluster.setAntiStacking(true);
/* 3581 */     m_9236_().m_7967_(cluster);
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropSmallMassCluster(int radius) {
/* 3586 */     BlockClusterEntity cluster = ClusterBuilderHelper.buildSmallRandomDeathCluster(m_9236_(), this.f_19796_, radius);
/* 3587 */     cluster.setSink(-1);
/* 3588 */     cluster.m_20334_(this.f_19796_.m_188583_() * 0.6D, this.f_19796_.m_188583_() * 0.3D, this.f_19796_.m_188583_() * 0.6D);
/* 3589 */     cluster.m_146884_(m_20182_().m_82520_(this.f_19796_.m_188583_() * 20.0D, getUnmodifiedHeight() / 2.0D + this.f_19796_.m_188583_() * 40.0D, this.f_19796_.m_188583_() * 20.0D));
/* 3590 */     cluster.setRotationDelta(new Vec2(this.f_19796_.m_188503_(90) * 0.3F / 2.0F, this.f_19796_.m_188503_(90) * 0.3F / 2.0F));
/* 3591 */     m_9236_().m_7967_(cluster);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCompletelyInvulnerable() {
/* 3596 */     return ((Boolean)WitherStormModConfig.SERVER.witherStormInvulnerability.get()).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldShine() {
/* 3601 */     return (getPhase() > 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getShineAlpha(float partialTicks) {
/* 3606 */     return Mth.m_14179_(partialTicks, this.shineAlphaO, this.shineAlpha);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setShineAlpha(float alpha) {
/* 3611 */     this.shineAlpha = alpha;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setResummoned(boolean flag) {
/* 3616 */     this.resummoned = flag;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_142079_() {
/* 3622 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getClusterRadius() {
/* 3627 */     return this.clusterRadius;
/*      */   }
/*      */ 
/*      */   
/*      */   public HeadManager getHeadManager() {
/* 3632 */     return this.headManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public RemovableGoalsManager getRemovableGoalsManager() {
/* 3637 */     return this.removableGoals;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int m_21529_() {
/* 3643 */     if (getPhase() > 3 && !isHeadInjured(0) && !isDistracted(0))
/* 3644 */       return 1; 
/* 3645 */     if (isDistracted(0)) {
/* 3646 */       return 2;
/*      */     }
/* 3648 */     return super.m_21529_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6074_() {
/* 3654 */     m_142687_(Entity.RemovalReason.KILLED);
/* 3655 */     m_146850_(GameEvent.f_223707_);
/* 3656 */     getSegmentsManager().ifPresent(manager -> {
/*      */           for (WitherStormSegmentEntity entity : manager.getSegments()) {
/*      */             if (entity != null) {
/*      */               entity.m_6074_();
/*      */             }
/*      */           } 
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getTractorBeamCutoffDistance(int head) {
/* 3669 */     return this.headManager.getHead(head).getTractorBeamCutoff();
/*      */   }
/*      */ 
/*      */   
/*      */   public EvolutionProfiler getEvolutionProfiler() {
/* 3674 */     return this.evolutionProfiler;
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnConsumedPets(Vec3 pos) {
/* 3679 */     for (Iterator<Map.Entry<UUID, CompoundTag>> iterator = this.consumedPets.entrySet().iterator(); iterator.hasNext(); ) { Map.Entry<UUID, CompoundTag> entry = iterator.next();
/*      */       
/* 3681 */       CompoundTag tag = entry.getValue();
/* 3682 */       if (!tag.m_128456_())
/*      */       {
/* 3684 */         EntityType.m_20642_(tag, m_9236_()).ifPresent(entity -> {
/*      */               entity.m_146884_(pos);
/*      */               
/*      */               if (entity instanceof LivingEntity) {
/*      */                 LivingEntity living = (LivingEntity)entity;
/*      */                 living.m_21153_(living.m_21233_());
/*      */                 living.m_21219_();
/*      */                 living.m_7292_(new MobEffectInstance(MobEffects.f_19604_, 200, 0));
/*      */               } 
/*      */               ((ServerLevel)m_9236_()).m_8847_(entity);
/*      */             });
/*      */       } }
/*      */     
/* 3697 */     this.consumedPets.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   public IgnoredTargetsManager getIgnoredTargets() {
/* 3702 */     return this.ignoredTargets;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<BlockPos> getPlayingJukeboxes() {
/* 3707 */     return this.playingJukeboxes;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getShineScale() {
/* 3712 */     return this.shineScale;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLookAt(int head, Vec3 pos, int time) {
/* 3718 */     this.headManager.getHead(head).setLookPos(pos, time);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public LivingEntity getTarget(int head) {
/* 3724 */     return this.headManager.getHead(head).getTarget();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTarget(int head, LivingEntity entity) {
/* 3730 */     this.headManager.getHead(head).setTarget(entity);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void splitCluster(BlockClusterEntity cluster, List<Entity> toAdd) {
/* 3735 */     BlockClusterEntity split = cluster.splitAt(Direction.Axis.m_235688_(this.f_19796_));
/* 3736 */     if (split != null) {
/*      */       
/* 3738 */       m_9236_().m_7967_(split);
/* 3739 */       this.segments.ifPresentOrElse(manager -> {
/*      */             if (this.f_19796_.m_188499_()) {
/*      */               toAdd.add(split);
/*      */             } else {
/*      */               int size = (manager.getSegments()).length;
/*      */               int index = this.f_19796_.m_188503_(size);
/*      */               WitherStormSegmentEntity segment = manager.getSegments()[index];
/*      */               if (segment != null && !segment.m_213877_()) {
/*      */                 segment.getTrackedEntities().trackEntityToConsume(split);
/*      */               } else {
/*      */                 toAdd.add(split);
/*      */               } 
/*      */             } 
/*      */           }() -> toAdd.add(split));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int loadRadius() {
/* 3764 */     return ((Integer)WitherStormModConfig.SERVER.chunkLoadingRadius.get()).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStillValidForChunkLoading() {
/* 3770 */     return (m_146911_() == null || !m_146911_().m_146965_());
/*      */   }
/*      */ 
/*      */   
/*      */   public float getPhaseProgress() {
/* 3775 */     return this.phaseProgress;
/*      */   }
/*      */ 
/*      */   
/*      */   public int entityConsumptionRadiusHunch() {
/* 3780 */     int radiusValue = 12;
/* 3781 */     radiusValue = (int)(radiusValue + Math.round(getConsumedEntities() * 0.00445D));
/* 3782 */     if (radiusValue > 48)
/* 3783 */       radiusValue = 48; 
/* 3784 */     return radiusValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEntityConsumptionRadius() {
/* 3789 */     return this.entityConsumptionRadius;
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeConsumptionLocked(boolean flag) {
/* 3794 */     this.isLocked = flag;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isConsumptionLocked() {
/* 3799 */     return this.isLocked;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 m_21074_(Vec3 vec, float f) {
/* 3805 */     m_19920_(m_6113_(), vec);
/* 3806 */     m_6478_(MoverType.SELF, m_20184_());
/* 3807 */     return m_20184_();
/*      */   }
/*      */ 
/*      */   
/*      */   private Vec3 wsCollide(Vec3 delta) {
/* 3812 */     AABB aabb = m_20191_();
/* 3813 */     List<VoxelShape> list = m_9236_().m_183134_((Entity)this, aabb.m_82369_(delta));
/* 3814 */     return (delta.m_82556_() == 0.0D) ? delta : m_198894_((Entity)this, delta, aabb, m_9236_(), list);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStepHeight() {
/* 3820 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6478_(MoverType moverType, Vec3 delta) {
/* 3826 */     if (this.f_19794_) {
/*      */       
/* 3828 */       m_6034_(m_20185_() + delta.f_82479_, m_20186_() + delta.f_82480_, m_20189_() + delta.f_82481_);
/*      */     }
/*      */     else {
/*      */       
/* 3832 */       m_9236_().m_46473_().m_6180_("move");
/*      */       
/* 3834 */       if (this.f_19865_.m_82556_() > 1.0E-7D) {
/*      */         
/* 3836 */         delta = delta.m_82559_(this.f_19865_);
/* 3837 */         this.f_19865_ = Vec3.f_82478_;
/* 3838 */         m_20256_(Vec3.f_82478_);
/*      */       } 
/*      */       
/* 3841 */       Vec3 vec3 = wsCollide(delta);
/* 3842 */       double d0 = vec3.m_82556_();
/* 3843 */       if (d0 > 1.0E-7D) {
/*      */         
/* 3845 */         if (this.f_19789_ != 0.0F && d0 >= 1.0D) {
/*      */           
/* 3847 */           BlockHitResult blockhitresult = m_9236_().m_45547_(new ClipContext(m_20182_(), m_20182_().m_82549_(vec3), ClipContext.Block.FALLDAMAGE_RESETTING, ClipContext.Fluid.WATER, (Entity)this));
/* 3848 */           if (blockhitresult.m_6662_() != HitResult.Type.MISS) {
/* 3849 */             m_183634_();
/*      */           }
/*      */         } 
/* 3852 */         m_6034_(m_20185_() + vec3.f_82479_, m_20186_() + vec3.f_82480_, m_20189_() + vec3.f_82481_);
/*      */       } 
/*      */       
/* 3855 */       m_9236_().m_46473_().m_7238_();
/*      */       
/* 3857 */       m_9236_().m_46473_().m_6180_("rest");
/*      */       
/* 3859 */       boolean noXMovement = !Mth.m_14082_(delta.f_82479_, vec3.f_82479_);
/* 3860 */       boolean noZMovement = !Mth.m_14082_(delta.f_82481_, vec3.f_82481_);
/* 3861 */       this.f_19862_ = (noXMovement || noZMovement);
/* 3862 */       this.f_19863_ = (delta.f_82480_ != vec3.f_82480_);
/* 3863 */       this.f_201939_ = (this.f_19863_ && delta.f_82480_ < 0.0D);
/*      */       
/* 3865 */       m_289603_(this.f_201939_, vec3);
/*      */       
/* 3867 */       BlockPos pos = m_216999_();
/* 3868 */       BlockState state = m_9236_().m_8055_(pos);
/*      */       
/* 3870 */       if (!m_213877_()) {
/*      */         
/* 3872 */         if (this.f_19862_) {
/*      */           
/* 3874 */           Vec3 vec31 = m_20184_();
/* 3875 */           m_20334_(noXMovement ? 0.0D : vec31.f_82479_, vec31.f_82480_, noZMovement ? 0.0D : vec31.f_82481_);
/*      */         } 
/*      */         
/* 3878 */         Block block = state.m_60734_();
/* 3879 */         if (delta.f_82480_ != vec3.f_82480_) {
/* 3880 */           block.m_5548_((BlockGetter)m_9236_(), (Entity)this);
/*      */         }
/* 3882 */         if (m_20096_()) {
/* 3883 */           block.m_141947_(m_9236_(), pos, state, (Entity)this);
/*      */         }
/*      */       } 
/* 3886 */       m_9236_().m_46473_().m_7238_();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_20101_() {}
/*      */ 
/*      */   
/*      */   protected void m_146872_() {}
/*      */ 
/*      */   
/*      */   protected Entity.MovementEmission m_142319_() {
/* 3899 */     return Entity.MovementEmission.NONE;
/*      */   }
/*      */ 
/*      */   
/*      */   public void lerpBodyRotationTo(float xBodyRot, float yBodyRot, int steps) {
/* 3904 */     this.lerpBodyXRot = xBodyRot;
/* 3905 */     this.lerpBodyYRot = yBodyRot;
/* 3906 */     this.bodyLerpSteps = steps;
/*      */   }
/*      */ 
/*      */   
/*      */   private double attributeOrConfigValue(Attribute attribute, ForgeConfigSpec.ConfigValue<Double> config) {
/* 3911 */     AttributeInstance instance = m_21051_(attribute);
/* 3912 */     if (instance.m_22135_() != attribute.m_22082_()) {
/* 3913 */       return instance.m_22135_();
/*      */     }
/* 3915 */     return ((Double)config.get()).doubleValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSee(int head, Entity entity) {
/* 3921 */     return this.headManager.getHead(head).canSee(entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public void storePet(Entity entity) {
/* 3926 */     this.consumedPets.computeIfAbsent(entity.m_20148_(), u -> {
/*      */           CompoundTag tag = new CompoundTag();
/*      */           tag.m_128359_("id", Objects.<String>requireNonNull(entity.m_20078_()));
/*      */           entity.m_20240_(tag);
/*      */           return tag;
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/* 3938 */     if (super.equals(obj) && obj instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)obj; if (storm.isOnDistantRenderer() == isOnDistantRenderer()); }  return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isOccludedSound(SoundEvent event) {
/* 3943 */     if (event == WitherStormModSoundEvents.WITHER_STORM_GROWL.get())
/* 3944 */       return true; 
/* 3945 */     if (event == WitherStormModSoundEvents.WITHER_STORM_HURT.get())
/* 3946 */       return true; 
/* 3947 */     if (event == WitherStormModSoundEvents.WITHER_STORM_SHOOT.get())
/* 3948 */       return true; 
/* 3949 */     if (event == WitherStormModSoundEvents.WITHER_STORM_BITE.get())
/* 3950 */       return true; 
/* 3951 */     if (event == WitherStormModSoundEvents.WITHER_STORM_ROAR.get()) {
/* 3952 */       return true;
/*      */     }
/* 3954 */     return (event == WitherStormModSoundEvents.WITHER_STORM_TRACTOR_BEAM_ACTIVATES.get());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> EntityDataAccessor<T> registerDataAccessor(EntityDataSerializer<T> serializer, Supplier<T> defaultValue) {
/*      */     try {
/* 3961 */       Class<?> oclass = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
/* 3962 */       if (oclass.getPackageName().startsWith("nonamecrackers2.witherstormmod.common.entity.ai.witherstorm")) {
/*      */         
/* 3964 */         EntityDataAccessor<T> accessor = SynchedEntityData.m_135353_(WitherStormEntity.class, serializer);
/* 3965 */         DATA_ACCESSORS.add(new DataAccessorHolder(accessor, defaultValue));
/* 3966 */         return accessor;
/*      */       } 
/*      */ 
/*      */       
/* 3970 */       throw new RuntimeException("This method is for internal use only!");
/*      */     
/*      */     }
/* 3973 */     catch (ClassNotFoundException classNotFoundException) {
/*      */ 
/*      */       
/* 3976 */       return null;
/*      */     } 
/*      */   } private static final class DataAccessorHolder<T> extends Record { private final EntityDataAccessor<T> accessor; private final Supplier<T> defaultValue;
/* 3979 */     private DataAccessorHolder(EntityDataAccessor<T> accessor, Supplier<T> defaultValue) { this.accessor = accessor; this.defaultValue = defaultValue; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #3979	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder;
/*      */       // Local variable type table:
/*      */       //   start	length	slot	name	signature
/* 3979 */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder<TT;>; } public EntityDataAccessor<T> accessor() { return this.accessor; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #3979	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder;
/*      */       // Local variable type table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder<TT;>; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #3979	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder;
/*      */       //   0	8	1	o	Ljava/lang/Object;
/*      */       // Local variable type table:
/*      */       //   start	length	slot	name	signature
/* 3979 */       //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity$DataAccessorHolder<TT;>; } public Supplier<T> defaultValue() { return this.defaultValue; }
/*      */ 
/*      */     
/*      */     private void defineTo(SynchedEntityData data) {
/* 3983 */       data.m_135372_(this.accessor, this.defaultValue.get());
/*      */     } }
/*      */ 
/*      */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\WitherStormEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */