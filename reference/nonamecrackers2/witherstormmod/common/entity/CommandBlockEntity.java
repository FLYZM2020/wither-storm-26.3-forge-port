/*      */ package nonamecrackers2.witherstormmod.common.entity;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import java.util.function.Predicate;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.core.BlockPos;
/*      */ import net.minecraft.core.Vec3i;
/*      */ import net.minecraft.core.particles.ParticleOptions;
/*      */ import net.minecraft.core.particles.ParticleTypes;
/*      */ import net.minecraft.nbt.CompoundTag;
/*      */ import net.minecraft.nbt.ListTag;
/*      */ import net.minecraft.nbt.Tag;
/*      */ import net.minecraft.network.FriendlyByteBuf;
/*      */ import net.minecraft.network.protocol.Packet;
/*      */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*      */ import net.minecraft.network.syncher.EntityDataAccessor;
/*      */ import net.minecraft.network.syncher.EntityDataSerializer;
/*      */ import net.minecraft.network.syncher.EntityDataSerializers;
/*      */ import net.minecraft.network.syncher.SynchedEntityData;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.server.level.ServerBossEvent;
/*      */ import net.minecraft.server.level.ServerLevel;
/*      */ import net.minecraft.server.level.ServerPlayer;
/*      */ import net.minecraft.sounds.SoundEvent;
/*      */ import net.minecraft.sounds.SoundSource;
/*      */ import net.minecraft.tags.DamageTypeTags;
/*      */ import net.minecraft.util.Mth;
/*      */ import net.minecraft.util.RandomSource;
/*      */ import net.minecraft.util.random.SimpleWeightedRandomList;
/*      */ import net.minecraft.world.BossEvent;
/*      */ import net.minecraft.world.damagesource.DamageSource;
/*      */ import net.minecraft.world.effect.MobEffectInstance;
/*      */ import net.minecraft.world.entity.Entity;
/*      */ import net.minecraft.world.entity.EntityDimensions;
/*      */ import net.minecraft.world.entity.EntityType;
/*      */ import net.minecraft.world.entity.EquipmentSlot;
/*      */ import net.minecraft.world.entity.HumanoidArm;
/*      */ import net.minecraft.world.entity.LivingEntity;
/*      */ import net.minecraft.world.entity.Mob;
/*      */ import net.minecraft.world.entity.MobSpawnType;
/*      */ import net.minecraft.world.entity.SpawnPlacements;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*      */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*      */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*      */ import net.minecraft.world.entity.player.Player;
/*      */ import net.minecraft.world.item.ItemStack;
/*      */ import net.minecraft.world.level.BlockGetter;
/*      */ import net.minecraft.world.level.Level;
/*      */ import net.minecraft.world.level.LevelReader;
/*      */ import net.minecraft.world.level.NaturalSpawner;
/*      */ import net.minecraft.world.level.ServerLevelAccessor;
/*      */ import net.minecraft.world.level.block.Blocks;
/*      */ import net.minecraft.world.level.block.state.BlockState;
/*      */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*      */ import net.minecraft.world.level.material.PushReaction;
/*      */ import net.minecraft.world.phys.Vec2;
/*      */ import net.minecraft.world.phys.Vec3;
/*      */ import net.minecraftforge.api.distmarker.Dist;
/*      */ import net.minecraftforge.common.util.LogicalSidedProvider;
/*      */ import net.minecraftforge.event.ForgeEventFactory;
/*      */ import net.minecraftforge.fml.DistExecutor;
/*      */ import net.minecraftforge.network.NetworkEvent;
/*      */ import net.minecraftforge.network.PacketDistributor;
/*      */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*      */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*      */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.commandblock.BowelsBossFightStages;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SegmentsManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightManager;
/*      */ import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightPhase;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModToolActions;
/*      */ import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
/*      */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*      */ import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
/*      */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*      */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
/*      */ import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
/*      */ import nonamecrackers2.witherstormmod.common.util.StructureAnimationHelper;
/*      */ import nonamecrackers2.witherstormmod.common.util.TentacleOffsets;
/*      */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ 
/*      */ public class CommandBlockEntity extends LivingEntity implements EntitySyncableData, BossThemeEntity {
/*   96 */   private static final EntityDataAccessor<State> STATE = SynchedEntityData.m_135353_(CommandBlockEntity.class, (EntityDataSerializer)WitherStormModDataSerializers.STATE_ENUM);
/*   97 */   private static final EntityDataAccessor<Mode> MODE = SynchedEntityData.m_135353_(CommandBlockEntity.class, (EntityDataSerializer)WitherStormModDataSerializers.MODE_ENUM);
/*   98 */   private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.m_135353_(CommandBlockEntity.class, EntityDataSerializers.f_135041_);
/*   99 */   private static final EntityDataAccessor<Integer> PHASE_KEY = SynchedEntityData.m_135353_(CommandBlockEntity.class, EntityDataSerializers.f_135028_); private static final Predicate<LivingEntity> SEARCHABLE_PLAYER_SELECTOR;
/*  100 */   static { SEARCHABLE_PLAYER_SELECTOR = (living -> { if (living instanceof Player) {
/*      */           Player player = (Player)living; if (player.m_6084_() && !(player.m_150110_()).f_35934_ && !player.m_7500_() && !player.m_5833_() && player.m_6097_());
/*      */         }  return false;
/*  103 */       }); } private static final SimpleWeightedRandomList<EntityType<? extends Mob>> IDLE_BOWELS_MOBS = SimpleWeightedRandomList.m_146263_()
/*  104 */     .m_146271_(WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 10)
/*  105 */     .m_146271_(WitherStormModEntityTypes.SICKENED_SKELETON.get(), 10)
/*  106 */     .m_146271_(WitherStormModEntityTypes.SICKENED_SPIDER.get(), 6)
/*  107 */     .m_146271_(WitherStormModEntityTypes.SICKENED_CREEPER.get(), 1)
/*  108 */     .m_146271_(WitherStormModEntityTypes.SICKENED_CHICKEN.get(), 4)
/*  109 */     .m_146271_(WitherStormModEntityTypes.SICKENED_COW.get(), 4)
/*  110 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PIG.get(), 4)
/*  111 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PARROT.get(), 2)
/*  112 */     .m_146271_(WitherStormModEntityTypes.SICKENED_WOLF.get(), 1)
/*  113 */     .m_146271_(WitherStormModEntityTypes.SICKENED_CAT.get(), 1)
/*  114 */     .m_146271_(WitherStormModEntityTypes.SICKENED_BEE.get(), 1)
/*  115 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 3)
/*  116 */     .m_146271_(WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 1)
/*  117 */     .m_146271_(WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 3).m_146270_(); public static final int HIT_GLARE_TIME = 60;
/*      */   private int modeAnim;
/*      */   private int modeAnimO;
/*      */   private int stateTicks;
/*      */   @Nullable
/*      */   private Player toLure;
/*  123 */   private final List<StructureAnimationHelper> ribStructure = new ArrayList<>(); private final TentacleManager tentacleStructure;
/*      */   private float protectionYOffset;
/*      */   private float protectionYOffsetO;
/*      */   @Nullable
/*      */   private WitherStormEntity owner;
/*      */   private final BossfightManager<CommandBlockEntity> bossfightManager;
/*  129 */   private final ServerBossEvent bossInfo = new ServerBossEvent(m_5446_(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);
/*  130 */   private final List<ServerPlayer> outsideBossBarViewers = Lists.newArrayList();
/*  131 */   private final List<ServerPlayer> tracking = Lists.newArrayList(); @Nullable
/*      */   public BlockClusterEntity podiumCluster; @Nullable
/*      */   public UUID podiumClusterUUID;
/*      */   private int specialDeathTime;
/*      */   @Nullable
/*      */   public LivingEntity killer;
/*      */   private int hitGlareTime;
/*      */   
/*      */   public CommandBlockEntity(EntityType<? extends CommandBlockEntity> type, Level world) {
/*  140 */     super(type, world);
/*  141 */     createStructureHelpers();
/*  142 */     this.tentacleStructure = new TentacleManager(this, 6, new TentacleOffsets[] { new TentacleOffsets(2.0D, -2.0D, 3.0D, 1.45F, 1.0F, 40.0F, -70.0F), new TentacleOffsets(0.0D, -2.0D, 4.0D, 1.4F, 1.0F, 35.0F, -90.0F), new TentacleOffsets(-2.0D, -2.0D, 3.0D, 1.45F, 1.0F, 40.0F, -110.0F), new TentacleOffsets(2.0D, -2.0D, -3.0D, 1.45F, 1.0F, 40.0F, 70.0F), new TentacleOffsets(0.0D, -2.0D, -4.0D, 1.4F, 1.0F, 35.0F, 90.0F), new TentacleOffsets(-2.0D, -2.0D, -3.0D, 1.45F, 1.0F, 40.0F, 110.0F) });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  152 */     this
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
/*  170 */       .bossfightManager = (new BossfightManager(BowelsBossFightStages.IDLE, (Entity)this)).addPhase(1, BowelsBossFightStages.HIT).addPhase(2, BowelsBossFightStages.MOVE_PODIUM).addPhase(3, BowelsBossFightStages.WAIT).addPhase(4, BowelsBossFightStages.MOB_WAVE_1).addPhase(5, BowelsBossFightStages.IDLE).addPhase(6, BowelsBossFightStages.HIT).addPhase(7, BowelsBossFightStages.MOVE_PODIUM).addPhase(8, BowelsBossFightStages.WAIT).addPhase(9, BowelsBossFightStages.MOB_WAVE_2).addPhase(10, BowelsBossFightStages.PROTECT_IDLE).addPhase(11, BowelsBossFightStages.IDLE).addPhase(12, BowelsBossFightStages.HIT).addPhase(13, BowelsBossFightStages.MOVE_PODIUM).addPhase(14, BowelsBossFightStages.MOB_WAVE_3).addPhase(15, BowelsBossFightStages.PROTECT_IDLE).addPhase(16, BowelsBossFightStages.IDLE).addPhase(17, BowelsBossFightStages.DEATH).addPhase(18, BowelsBossFightStages.IDLE);
/*      */   }
/*      */ 
/*      */   
/*      */   public CommandBlockEntity(Level world, WitherStormEntity owner, double x, double y, double z) {
/*  175 */     this((EntityType<? extends CommandBlockEntity>)WitherStormModEntityTypes.COMMAND_BLOCK.get(), world);
/*  176 */     m_6034_(x, y, z);
/*  177 */     setState(State.PLAYING_DEAD);
/*  178 */     setMode(Mode.RIBS);
/*  179 */     setOwner(owner);
/*  180 */     setOwnerUUID(owner.m_20148_());
/*  181 */     m_146922_(owner.m_146908_());
/*  182 */     m_5618_(owner.f_20883_);
/*  183 */     m_5616_(owner.m_6080_());
/*      */   }
/*      */ 
/*      */   
/*      */   public static AttributeSupplier.Builder createAttributes() {
/*  188 */     return LivingEntity.m_21183_().m_22268_(Attributes.f_22276_, 64.0D).m_22268_(Attributes.f_22284_, 32.0D).m_22268_(Attributes.f_22285_, 32.0D).m_22268_(Attributes.f_22278_, 1024.0D).m_22268_(Attributes.f_22277_, 32.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   private void createStructureHelpers() {
/*  193 */     this.ribStructure.add(new StructureAnimationHelper());
/*  194 */     this.ribStructure.add((new StructureAnimationHelper()).setBaseRotationAngle(0.0F, 180.0F));
/*  195 */     this.ribStructure.add((new StructureAnimationHelper()).setBaseRotationAngle(0.0F, 145.0F));
/*  196 */     this.ribStructure.add((new StructureAnimationHelper()).setBaseRotationAngle(0.0F, 35.0F));
/*  197 */     this.ribStructure.add((new StructureAnimationHelper()).setBaseRotationAngle(0.0F, -35.0F));
/*  198 */     this.ribStructure.add((new StructureAnimationHelper()).setBaseRotationAngle(0.0F, 215.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_8119_() {
/*  204 */     if (!(m_9236_()).f_46443_) {
/*      */       
/*  206 */       this.f_19804_.m_135381_(PHASE_KEY, Integer.valueOf(getBossfightManager().getCurrentPhaseIndex()));
/*  207 */       int interval = (int)(m_21223_() / m_21233_() * 80.0F);
/*  208 */       int secondaryInterval = (int)(m_21223_() / m_21233_() * 16.0F);
/*  209 */       if (!this.tracking.isEmpty() && m_21223_() < m_21233_() && interval > 0 && this.f_19797_ % interval == 0)
/*  210 */         dropClusterFromCeiling(); 
/*  211 */       if (!this.tracking.isEmpty() && m_21223_() < m_21233_() && secondaryInterval > 0 && this.f_19797_ % secondaryInterval == 0) {
/*  212 */         dropAdditionalClustersFromCeiling();
/*      */       }
/*  214 */       int mobSpawnerTimer = 100;
/*  215 */       if (WorldUtil.areaLoaded(m_9236_(), m_20183_(), 8) && getState().equals(State.BOSSFIGHT) && m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation()))
/*      */       {
/*  217 */         if (this.f_19797_ % mobSpawnerTimer == 0 && this.f_19796_.m_188500_() <= 0.25D) {
/*  218 */           summonRandomBowelsMobNearPlayer(8, 16, IDLE_BOWELS_MOBS);
/*      */         }
/*      */       }
/*      */     } 
/*  222 */     super.m_8119_();
/*      */     
/*  224 */     findOwner();
/*      */     
/*  226 */     getState().tick(this);
/*      */     
/*  228 */     this.modeAnimO = this.modeAnim;
/*  229 */     this.protectionYOffsetO = this.protectionYOffset;
/*      */     
/*  231 */     getStructures().forEach(StructureAnimationHelper::tick);
/*      */     
/*  233 */     if (!(m_9236_()).f_46443_) {
/*      */       
/*  235 */       this.tentacleStructure.findTentacles((ServerLevel)m_9236_());
/*      */       
/*  237 */       this.bossInfo.m_142711_(m_21223_() / m_21233_());
/*      */       
/*  239 */       for (ServerPlayer player : this.outsideBossBarViewers) {
/*      */         
/*  241 */         if (!m_21224_() && m_21223_() < m_21233_()) {
/*  242 */           this.bossInfo.m_6543_(player); continue;
/*      */         } 
/*  244 */         this.bossInfo.m_6539_(player);
/*      */       } 
/*      */       
/*  247 */       if (getOwner() == null || getOwner().getBowelsCommandBlock() != this) {
/*      */         
/*  249 */         for (ServerPlayer player : this.outsideBossBarViewers)
/*  250 */           this.bossInfo.m_6539_(player); 
/*  251 */         this.outsideBossBarViewers.clear();
/*      */       } 
/*      */     } 
/*      */     
/*  255 */     if (this.hitGlareTime > 0) {
/*  256 */       this.hitGlareTime--;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void m_8097_() {
/*  262 */     super.m_8097_();
/*  263 */     this.f_19804_.m_135372_(STATE, State.IDLE);
/*  264 */     this.f_19804_.m_135372_(MODE, Mode.NONE);
/*  265 */     this.f_19804_.m_135372_(OWNER_UUID, Optional.empty());
/*  266 */     this.f_19804_.m_135372_(PHASE_KEY, Integer.valueOf(0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7380_(@NotNull CompoundTag compound) {
/*  272 */     super.m_7380_(compound);
/*  273 */     compound.m_128405_("Mode", getMode().ordinal());
/*  274 */     compound.m_128405_("State", getState().ordinal());
/*  275 */     compound.m_128405_("StateTicks", this.stateTicks);
/*  276 */     compound.m_128405_("ModeAnim", this.modeAnim);
/*  277 */     compound.m_128350_("YOffset", this.protectionYOffset);
/*  278 */     compound.m_128350_("YBodyRot", this.f_20883_);
/*  279 */     compound.m_128365_("Structures", writeStructures());
/*  280 */     if (getOwnerUUID() != null)
/*  281 */       compound.m_128362_("OwnerUUID", getOwnerUUID()); 
/*  282 */     this.tentacleStructure.addSaveData(compound);
/*  283 */     if (this.podiumCluster != null && this.podiumCluster.m_6084_())
/*  284 */       compound.m_128362_("PodiumCluster", this.podiumCluster.m_20148_()); 
/*  285 */     compound.m_128365_("BossfightManager", (Tag)this.bossfightManager.write());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7378_(@NotNull CompoundTag compound) {
/*  291 */     super.m_7378_(compound);
/*  292 */     this.protectionYOffsetO = this.protectionYOffset;
/*  293 */     int mode = compound.m_128451_("Mode");
/*  294 */     if (mode >= 0 && mode < (Mode.values()).length)
/*  295 */       setMode(Mode.values()[mode]); 
/*  296 */     int state = compound.m_128451_("State");
/*  297 */     if (state >= 0 && state < (State.values()).length)
/*  298 */       setState(State.values()[state]); 
/*  299 */     this.stateTicks = compound.m_128451_("StateTicks");
/*  300 */     this.modeAnim = compound.m_128451_("ModeAnim");
/*  301 */     this.modeAnimO = this.modeAnim;
/*  302 */     this.protectionYOffset = compound.m_128457_("YOffset");
/*  303 */     m_5618_(compound.m_128457_("YBodyRot"));
/*  304 */     readStructures(compound.m_128437_("Structures", 10));
/*  305 */     if (compound.m_128441_("OwnerUUID"))
/*  306 */       setOwnerUUID(compound.m_128342_("OwnerUUID")); 
/*  307 */     this.tentacleStructure.readSaveData(compound);
/*  308 */     if (compound.m_128441_("PodiumCluster"))
/*  309 */       this.podiumClusterUUID = compound.m_128342_("PodiumCluster"); 
/*  310 */     if (compound.m_128441_("BossfightManager")) {
/*  311 */       this.bossfightManager.read(compound.m_128469_("BossfightManager"));
/*      */     }
/*      */   }
/*      */   
/*      */   public Mode getMode() {
/*  316 */     return (Mode)this.f_19804_.m_135370_(MODE);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMode(Mode mode) {
/*  321 */     this.f_19804_.m_135381_(MODE, mode);
/*      */   }
/*      */ 
/*      */   
/*      */   public State getState() {
/*  326 */     return (State)this.f_19804_.m_135370_(STATE);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setState(State state) {
/*  331 */     this.f_19804_.m_135381_(STATE, state);
/*  332 */     initState();
/*      */   }
/*      */ 
/*      */   
/*      */   private void initState() {
/*  337 */     getState().init(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void nextState() {
/*  342 */     if (getState().ordinal() + 1 < (State.values()).length) {
/*  343 */       setState(State.values()[getState().ordinal() + 1]);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setLuringPlayer(@Nullable Player player) {
/*  348 */     this.toLure = player;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Player getLuringPlayer() {
/*  353 */     return this.toLure;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7350_(@NotNull EntityDataAccessor<?> parameter) {
/*  359 */     super.m_7350_(parameter);
/*  360 */     if (parameter.equals(STATE))
/*  361 */       initState(); 
/*  362 */     if (parameter.equals(MODE)) {
/*  363 */       getMode().init(this, getState());
/*      */     }
/*      */   }
/*      */   
/*      */   public BlockState getBlockState() {
/*  368 */     return (BlockState)Blocks.f_50272_.m_49966_().m_61124_((Property)BlockStateProperties.f_61372_, (Comparable)m_6350_());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int m_213860_() {
/*  374 */     return 10;
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public AABB m_6921_() {
/*  380 */     return m_20191_().m_82400_(20.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_147240_(double strength, double x, double z) {}
/*      */ 
/*      */   
/*      */   public boolean m_6128_() {
/*  389 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6469_(DamageSource source, float amount) {
/*  396 */     if (source.m_269533_(DamageTypeTags.f_268738_)) {
/*  397 */       return super.m_6469_(source, amount);
/*      */     }
/*  399 */     boolean flag = false;
/*  400 */     Entity entity = source.m_7640_(); if (entity instanceof LivingEntity) { LivingEntity livingEntity = (LivingEntity)entity;
/*      */       
/*  402 */       ItemStack stack = livingEntity.m_21205_();
/*  403 */       if (stack.m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS) || stack.canPerformAction(WitherStormModToolActions.COMMAND_BLOCK_DAMAGE)) {
/*      */         
/*  405 */         m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_HIT.get(), 4.0F, 1.0F);
/*  406 */         if (!isVulnerable(source)) {
/*      */           
/*  408 */           if (getState() != State.BOSSFIGHT)
/*  409 */             livingEntity.m_147240_(1.0D, m_20185_() - livingEntity.m_20185_(), m_20189_() - livingEntity.m_20189_()); 
/*  410 */           flag = true;
/*      */         }
/*      */         else {
/*      */           
/*  414 */           boolean hurt = false;
/*  415 */           float health = m_21223_() - m_21233_() / 4.0F;
/*  416 */           if (health <= 0.0F) {
/*      */             
/*  418 */             hurt = super.m_6469_(source, Float.MAX_VALUE);
/*      */           }
/*      */           else {
/*      */             
/*  422 */             m_21153_(health);
/*      */             
/*  424 */             hurt = true;
/*      */           } 
/*      */           
/*  427 */           if (!(m_9236_()).f_46443_ && hurt)
/*      */           {
/*  429 */             if (!m_21224_()) {
/*      */               
/*  431 */               getBossfightManager().goToNextPhase();
/*      */               
/*  433 */               WitherStormEntity owner = getOwner();
/*  434 */               if (owner != null && owner.m_6084_()) {
/*      */                 
/*  436 */                 owner.getSegmentsManager().ifPresent(manager -> {
/*      */                       for (WitherStormSegmentEntity segment : manager.getSegments()) {
/*      */                         if (segment != null && segment.m_6084_()) {
/*      */                           segment.getTrackedEntities().clearAndMakeAllFall();
/*      */ 
/*      */ 
/*      */                           
/*      */                           for (int i = 0; i < segment.getTotalHeads(); i++) {
/*      */                             if (this.f_19796_.m_188501_() > 0.6F) {
/*      */                               segment.getHeadManager().getHead(i).hurt(null, segment.getHeadManager().getHeadInjuryTime());
/*      */                             }
/*      */                           } 
/*      */                         } 
/*      */                       } 
/*      */                     });
/*      */ 
/*      */                 
/*  453 */                 owner.getTrackedEntities().clearAndMakeAllFall();
/*      */                 
/*  455 */                 for (int i = 0; i < owner.getTotalHeads(); i++) {
/*      */                   
/*  457 */                   if (this.f_19796_.m_188501_() > 0.6F) {
/*  458 */                     owner.getHeadManager().getHead(i).hurt(null, owner.getHeadManager().getHeadInjuryTime());
/*      */                   }
/*      */                 } 
/*  461 */                 m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DAMAGE.get(), 16.0F, 1.0F);
/*  462 */                 m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_CRACKS.get(), 16.0F, 1.0F);
/*      */               } 
/*      */             } 
/*      */           }
/*      */           
/*  467 */           if ((m_9236_()).f_46443_) {
/*      */             
/*  469 */             for (int i = 0; i < 100; i++) {
/*      */               
/*  471 */               double x = (this.f_19796_.m_188501_() - 0.5F);
/*  472 */               double y = (this.f_19796_.m_188501_() - 0.5F);
/*  473 */               double z = (this.f_19796_.m_188501_() - 0.5F);
/*  474 */               Vec3 pos = m_20191_().m_82399_();
/*  475 */               m_9236_().m_7107_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), pos.f_82479_, pos.f_82480_, pos.f_82481_, x, y, z);
/*      */             } 
/*  477 */             if (health > 0.0F) {
/*  478 */               this.hitGlareTime = 60;
/*      */             }
/*      */           } 
/*  481 */           return hurt;
/*      */         } 
/*      */       }  }
/*      */ 
/*      */     
/*  486 */     if ((source.m_269533_(DamageTypeTags.f_268415_) || source.m_269533_(DamageTypeTags.f_268415_) || flag) && getState().equals(State.PLAYING_DEAD)) {
/*      */       
/*  488 */       setState(State.REACTIVATING);
/*  489 */       return false;
/*      */     } 
/*      */     
/*  492 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isVulnerable(DamageSource source) {
/*  497 */     BossfightPhase<CommandBlockEntity> currentPhase = getCurrentPhase();
/*  498 */     return (getState() == State.BOSSFIGHT && !m_6673_(source) && currentPhase.equals(BowelsBossFightStages.IDLE) && !currentPhase.equals(BowelsBossFightStages.PROTECT_IDLE));
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Iterable<ItemStack> m_6168_() {
/*  504 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public ItemStack m_6844_(@NotNull EquipmentSlot slot) {
/*  510 */     return ItemStack.f_41583_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void m_8061_(@NotNull EquipmentSlot slot, @NotNull ItemStack stack) {}
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public HumanoidArm m_5737_() {
/*  519 */     return HumanoidArm.RIGHT;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_20068_() {
/*  525 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6094_() {
/*  531 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_5829_() {
/*  537 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7334_(@NotNull Entity entity) {}
/*      */ 
/*      */   
/*      */   public void m_5997_(double x, double y, double z) {}
/*      */ 
/*      */   
/*      */   protected float m_6431_(@NotNull Pose pose, @NotNull EntityDimensions size) {
/*  549 */     return 0.5F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getModeAnim(float partialTicks) {
/*  554 */     return Mth.m_14179_(partialTicks, this.modeAnimO, this.modeAnim);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<StructureAnimationHelper> getRibStructure() {
/*  559 */     return this.ribStructure;
/*      */   }
/*      */ 
/*      */   
/*      */   public TentacleManager getTentacleStructure() {
/*  564 */     return this.tentacleStructure;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<StructureAnimationHelper> getStructures() {
/*  569 */     return new ArrayList<>(getRibStructure());
/*      */   }
/*      */ 
/*      */   
/*      */   public TargetingConditions searchablePlayersPredicate() {
/*  574 */     return TargetingConditions.m_148353_().m_26888_(SEARCHABLE_PLAYER_SELECTOR).m_26883_(6.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public void lerpStructureBaseRotTo(List<StructureAnimationHelper> structure, float xRot, float yRot, int steps) {
/*  579 */     for (StructureAnimationHelper helper : structure) {
/*  580 */       helper.lerpBaseTo(this, xRot, yRot, steps);
/*      */     }
/*      */   }
/*      */   
/*      */   public void lerpStructureRotTo(List<StructureAnimationHelper> structure, float xRot, float yRot, int steps) {
/*  585 */     for (StructureAnimationHelper helper : structure) {
/*  586 */       helper.lerpTo(this, xRot, yRot, steps);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setProtectionYOffsetAndO(float offset) {
/*  591 */     this.protectionYOffset = offset;
/*  592 */     this.protectionYOffsetO = offset;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProtectionYOffset(float offset) {
/*  597 */     this.protectionYOffset = offset;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getProtectionYOffset() {
/*  602 */     return this.protectionYOffset;
/*      */   }
/*      */ 
/*      */   
/*      */   public float lerpProtectionYOffset(float partialTicks) {
/*  607 */     return Mth.m_14179_(partialTicks, this.protectionYOffsetO, this.protectionYOffset);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getStateTicks() {
/*  612 */     return this.stateTicks;
/*      */   }
/*      */ 
/*      */   
/*      */   public Tag writeStructures() {
/*  617 */     ListTag structures = new ListTag();
/*  618 */     for (StructureAnimationHelper helper : getRibStructure()) {
/*      */       
/*  620 */       CompoundTag ribStructure = new CompoundTag();
/*  621 */       helper.write(ribStructure);
/*  622 */       structures.add(ribStructure);
/*      */     } 
/*  624 */     return (Tag)structures;
/*      */   }
/*      */ 
/*      */   
/*      */   public void readStructures(ListTag list) {
/*  629 */     for (int i = 0; i < list.size(); i++) {
/*      */       
/*  631 */       CompoundTag ribStructure = list.m_128728_(i);
/*  632 */       if (i < getRibStructure().size()) {
/*  633 */         ((StructureAnimationHelper)getRibStructure().get(i)).read(ribStructure);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeData(FriendlyByteBuf buffer) {
/*  640 */     buffer.writeInt(this.stateTicks);
/*  641 */     buffer.writeInt(this.modeAnim);
/*  642 */     buffer.writeFloat(this.protectionYOffset);
/*  643 */     for (StructureAnimationHelper helper : getStructures()) {
/*  644 */       helper.writeBuffer(buffer);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void readData(FriendlyByteBuf buffer) {
/*  650 */     this.stateTicks = buffer.readInt();
/*  651 */     this.modeAnim = buffer.readInt();
/*  652 */     this.modeAnimO = this.modeAnim;
/*  653 */     this.protectionYOffset = buffer.readFloat();
/*  654 */     this.protectionYOffsetO = this.protectionYOffset;
/*  655 */     for (StructureAnimationHelper helper : getStructures()) {
/*  656 */       helper.readBuffer(buffer);
/*      */     }
/*      */   }
/*      */   
/*      */   public void findOwner() {
/*  661 */     if (getOwnerUUID() != null && getOwner() == null)
/*      */     {
/*  663 */       if (getState() != State.BOSSFIGHT) {
/*      */         
/*  665 */         for (WitherStormEntity entity : m_9236_().m_45976_(WitherStormEntity.class, m_20191_().m_82400_(100.0D))) {
/*      */           
/*  667 */           if (entity.m_20148_().equals(getOwnerUUID()))
/*      */           {
/*  669 */             setOwner(entity);
/*  670 */             entity.getPlayDeadManager().setCommandBlock(this);
/*      */           }
/*      */         
/*      */         } 
/*  674 */       } else if (!(m_9236_()).f_46443_) {
/*      */         
/*  676 */         MinecraftServer server = m_9236_().m_7654_();
/*  677 */         for (Level level : server.m_129785_()) {
/*      */           
/*  679 */           Entity entity = ((ServerLevel)level).m_8791_(getOwnerUUID());
/*  680 */           if (entity instanceof WitherStormEntity) {
/*      */             
/*  682 */             setOwner((WitherStormEntity)entity);
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOwner(@Nullable WitherStormEntity entity) {
/*  692 */     this.owner = entity;
/*  693 */     if (entity != null) {
/*      */       
/*  695 */       setOwnerUUID(entity.m_20148_());
/*  696 */       getState().initWithOwner(entity, this);
/*      */     }
/*      */     else {
/*      */       
/*  700 */       setOwnerUUID((UUID)null);
/*      */     } 
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public WitherStormEntity getOwner() {
/*  706 */     return this.owner;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public UUID getOwnerUUID() {
/*  711 */     return ((Optional<UUID>)this.f_19804_.m_135370_(OWNER_UUID)).orElse(null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOwnerUUID(@Nullable UUID uuid) {
/*  716 */     this.f_19804_.m_135381_(OWNER_UUID, Optional.ofNullable(uuid));
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Packet<ClientGamePacketListener> m_5654_() {
/*  722 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ModeAnimationMessage(m_19879_(), this.modeAnim));
/*  723 */     return super.m_5654_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onAddedToWorld() {
/*  729 */     super.onAddedToWorld();
/*      */     
/*  731 */     if (!(m_9236_()).f_46443_) {
/*  732 */       this.tentacleStructure.createTentacles();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void onRemovedFromWorld() {
/*  738 */     super.onRemovedFromWorld();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6667_(@NotNull DamageSource source) {
/*  744 */     if (!(m_9236_()).f_46443_)
/*      */     {
/*  746 */       if (getState() == State.BOSSFIGHT && getBossfightManager().getCurrentPhase() != BowelsBossFightStages.DEATH)
/*  747 */         getBossfightManager().setCurrentFromNextInOrder(BowelsBossFightStages.DEATH); 
/*      */     }
/*  749 */     this.killer = m_21232_();
/*  750 */     super.m_6667_(source);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_6153_() {
/*  756 */     if (getCurrentPhase().equals(BowelsBossFightStages.DEATH)) {
/*      */       
/*  758 */       this.specialDeathTime++;
/*      */       
/*  760 */       if (!(m_9236_()).f_46443_) {
/*      */         
/*  762 */         if (this.specialDeathTime > 160) {
/*  763 */           getBossfightManager().goToNextPhase();
/*      */         } else {
/*  765 */           dropClusterFromCeiling();
/*      */         } 
/*  767 */         if (this.specialDeathTime > 240)
/*      */         {
/*  769 */           this.tentacleStructure.killTentacles();
/*  770 */           m_142687_(Entity.RemovalReason.KILLED);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  776 */       super.m_6153_();
/*  777 */       if (!(m_9236_()).f_46443_ && this.f_20919_ >= 20) {
/*  778 */         this.tentacleStructure.removeTentacles();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void dropClusterFromCeiling() {
/*  784 */     if (WorldUtil.areaLoaded(m_9236_(), m_20183_(), 2)) {
/*      */       
/*  786 */       float angle = 6.2831855F * this.f_19796_.m_188501_();
/*  787 */       float dist = 8.0F + this.f_19796_.m_188501_() * 24.0F;
/*  788 */       int x = Mth.m_14143_(Mth.m_14089_(angle) * dist) + m_146903_();
/*  789 */       int z = Mth.m_14143_(Mth.m_14031_(angle) * dist) + m_146907_();
/*  790 */       int y = WorldUtil.getCeilingStartingAt(m_9236_(), m_146904_() + 10, x, z);
/*  791 */       BlockPos pos = new BlockPos(x, y, z);
/*  792 */       if (m_9236_().m_46859_(pos.m_7495_())) {
/*      */         
/*  794 */         BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(m_9236_());
/*  795 */         assert cluster != null;
/*      */         
/*  797 */         cluster.populateWithRadius(pos, 1.0F, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
/*  798 */         cluster.setRotationDelta(new Vec2(10.0F * (this.f_19796_.m_188501_() - 0.5F), 10.0F * (this.f_19796_.m_188501_() - 0.5F)));
/*  799 */         cluster.setAntiStacking(true);
/*  800 */         m_9236_().m_7967_(cluster);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void dropAdditionalClustersFromCeiling() {
/*  807 */     if (WorldUtil.areaLoaded(m_9236_(), m_20183_(), 8))
/*      */     {
/*  809 */       for (int i = 0; i < 128; i++) {
/*      */         
/*  811 */         float angle = 6.2831855F * this.f_19796_.m_188501_();
/*  812 */         float dist = 32.0F + this.f_19796_.m_188501_() * 80.0F;
/*  813 */         int x = Mth.m_14143_(Mth.m_14089_(angle) * dist) + m_146903_();
/*  814 */         int z = Mth.m_14143_(Mth.m_14031_(angle) * dist) + m_146907_();
/*  815 */         int y = WorldUtil.getCeilingStartingAt(m_9236_(), m_146904_() + this.f_19796_.m_188503_(49) - 24, x, z);
/*  816 */         BlockPos pos = new BlockPos(x, y, z);
/*  817 */         if (m_9236_().m_46859_(pos.m_7495_())) {
/*      */           
/*  819 */           BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(m_9236_());
/*  820 */           assert cluster != null;
/*  821 */           cluster.populateWithRadius(pos, 1.0F, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
/*  822 */           cluster.setRotationDelta(new Vec2(10.0F * (this.f_19796_.m_188501_() - 0.5F), 10.0F * (this.f_19796_.m_188501_() - 0.5F)));
/*  823 */           cluster.setAntiStacking(true);
/*  824 */           m_9236_().m_7967_(cluster);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public BossfightManager<CommandBlockEntity> getBossfightManager() {
/*  833 */     return this.bossfightManager;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6457_(@NotNull ServerPlayer player) {
/*  839 */     super.m_6457_(player);
/*  840 */     this.bossInfo.m_6543_(player);
/*  841 */     this.tracking.add(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6452_(@NotNull ServerPlayer player) {
/*  847 */     super.m_6452_(player);
/*  848 */     this.bossInfo.m_6539_(player);
/*  849 */     this.tracking.remove(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public SoundEvent getBossTheme() {
/*  855 */     return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_FINAL_BOSS_THEME.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldPlayBossTheme() {
/*  861 */     return (super.shouldPlayBossTheme() && m_21223_() < m_21233_() && getState() == State.BOSSFIGHT);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getPosition() {
/*  867 */     return m_20182_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStillAlive() {
/*  873 */     return m_6084_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int priority() {
/*  879 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFadeTime() {
/*  885 */     return 40;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkConfig() {
/*  891 */     return ((Boolean)WitherStormModConfig.CLIENT.playWitherStormTheme.get()).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void createPodiumCluster() {
/*  896 */     if (this.podiumCluster == null && this.podiumClusterUUID == null) {
/*      */       
/*  898 */       BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(m_9236_());
/*  899 */       BlockPos bottomCorner = (new BlockPos(-5, -13, -5)).m_121955_((Vec3i)m_20183_());
/*  900 */       assert cluster != null;
/*  901 */       cluster.populate(bottomCorner, bottomCorner.m_7918_(10, 19, 10), blockstate -> true);
/*  902 */       cluster.setResetGravityOnLoad(false);
/*  903 */       cluster.setPhysics(false);
/*  904 */       cluster.m_20242_(true);
/*  905 */       cluster.setForceRender(true);
/*  906 */       m_9236_().m_7967_(cluster);
/*  907 */       this.podiumCluster = cluster;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void movePodiumCluster(Vec3 delta) {
/*  913 */     if (this.podiumCluster != null) {
/*  914 */       this.podiumCluster.m_20256_(delta);
/*      */     }
/*      */   }
/*      */   
/*      */   public void findPodiumCluster() {
/*  919 */     if (!(m_9236_()).f_46443_ && this.podiumClusterUUID != null && this.podiumCluster == null) {
/*      */       
/*  921 */       ServerLevel serverWorld = (ServerLevel)m_9236_();
/*  922 */       Entity entity = serverWorld.m_8791_(this.podiumClusterUUID);
/*  923 */       if (entity instanceof BlockClusterEntity)
/*  924 */         this.podiumCluster = (BlockClusterEntity)entity; 
/*      */     } 
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockClusterEntity getPodiumCluster() {
/*  930 */     return this.podiumCluster;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Mob summonRandomBowelsMobNearPlayer(int minRadius, int maxRadius, SimpleWeightedRandomList<EntityType<? extends Mob>> types) {
/*  935 */     ServerLevel world = (ServerLevel)m_9236_();
/*  936 */     List<ServerPlayer> players = world.m_45976_(ServerPlayer.class, m_20191_().m_82400_(128.0D));
/*  937 */     for (ServerPlayer player : players) {
/*      */       
/*  939 */       Mob entity = (Mob)((EntityType)Objects.<EntityType>requireNonNull(types.m_216820_(this.f_19796_).orElse(null))).m_20615_((Level)world);
/*  940 */       assert entity != null;
/*  941 */       BlockPos playerPos = player.m_20183_();
/*  942 */       BlockPos randomPos = getRandomPosAroundPlayer(entity.m_6095_(), playerPos, minRadius, maxRadius, 10);
/*  943 */       if (randomPos != null && hasEnoughSpace((Entity)entity, randomPos)) {
/*      */         
/*  945 */         entity.m_6034_(randomPos.m_123341_() + 0.5D, (randomPos.m_123342_() + 1), randomPos.m_123343_() + 0.5D);
/*  946 */         ForgeEventFactory.onFinalizeSpawn(entity, (ServerLevelAccessor)world, world.m_6436_(randomPos), MobSpawnType.EVENT, null, null);
/*  947 */         if (entity instanceof net.minecraft.world.entity.monster.Monster || entity instanceof net.minecraft.world.entity.animal.AbstractGolem)
/*      */         {
/*  949 */           addSpeedAttribute(entity);
/*      */         }
/*  951 */         addHealthAttribute(entity);
/*  952 */         entity.m_8032_();
/*  953 */         entity.m_21373_();
/*  954 */         world.m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), 0.2D);
/*  955 */         world.m_8767_((ParticleOptions)ParticleTypes.f_123755_, entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), 0.01D);
/*  956 */         world.m_47205_((Entity)entity);
/*  957 */         return entity;
/*      */       } 
/*      */     } 
/*  960 */     return null;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPos getRandomPosAroundPlayer(EntityType<?> type, BlockPos playerPos, int minDistance, int maxDistance, int attempts) {
/*  965 */     for (int a = 0; a < attempts; a++) {
/*  966 */       int x = playerPos.m_123341_() + this.f_19796_.m_188503_(maxDistance * 2 + 1) - maxDistance;
/*  967 */       int y = playerPos.m_123342_() + this.f_19796_.m_188503_(maxDistance);
/*  968 */       int z = playerPos.m_123343_() + this.f_19796_.m_188503_(maxDistance * 2 + 1) - maxDistance;
/*  969 */       BlockPos pos = new BlockPos(x, y, z);
/*  970 */       double distanceSquared = playerPos.m_123331_((Vec3i)pos);
/*  971 */       if (distanceSquared >= (minDistance * minDistance) && distanceSquared <= (maxDistance * maxDistance)) {
/*      */         
/*  973 */         for (int i = 0; i < 30 && m_9236_().m_8055_(pos.m_7495_()).m_60713_(Blocks.f_50016_); i++)
/*  974 */           pos = pos.m_7495_(); 
/*  975 */         if (NaturalSpawner.m_47051_(SpawnPlacements.Type.ON_GROUND, (LevelReader)m_9236_(), pos, type) && Math.sqrt(playerPos.m_123331_((Vec3i)pos)) > 6.0D)
/*  976 */           return pos; 
/*      */       } 
/*      */     } 
/*  979 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Mob summonRandomMob(int diameter, SimpleWeightedRandomList<EntityType<? extends Mob>> types) {
/*  985 */     ServerLevel world = (ServerLevel)m_9236_();
/*  986 */     Mob entity = (Mob)((EntityType)types.m_216820_(this.f_19796_).orElse(null)).m_20615_((Level)world);
/*  987 */     BlockPos pos = getRandomNearbyPos(entity.m_6095_(), diameter, 5);
/*  988 */     if (pos != null)
/*      */     {
/*  990 */       if (hasEnoughSpace((Entity)entity, pos)) {
/*      */         
/*  992 */         entity.m_6034_(pos.m_123341_() + 0.5D, (pos.m_123342_() + 1), pos.m_123343_() + 0.5D);
/*  993 */         ForgeEventFactory.onFinalizeSpawn(entity, (ServerLevelAccessor)world, world.m_6436_(pos), MobSpawnType.EVENT, null, null);
/*  994 */         if (entity instanceof net.minecraft.world.entity.monster.Monster || entity instanceof net.minecraft.world.entity.animal.AbstractGolem)
/*      */         {
/*  996 */           addSpeedAttribute(entity);
/*      */         }
/*  998 */         addHealthAttribute(entity);
/*  999 */         entity.m_8032_();
/* 1000 */         entity.m_21373_();
/* 1001 */         entity.m_21530_();
/* 1002 */         world.m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), 0.2D);
/* 1003 */         world.m_8767_((ParticleOptions)ParticleTypes.f_123755_, entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), 0.01D);
/* 1004 */         world.m_47205_((Entity)entity);
/* 1005 */         return entity;
/*      */       } 
/*      */     }
/* 1008 */     return null;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPos getRandomNearbyPos(EntityType<?> type, int diameter, int attempts) {
/* 1013 */     BlockPos pos = null;
/* 1014 */     BlockPos start = m_20183_();
/*      */     
/* 1016 */     for (int i = 0; i < attempts; i++) {
/*      */       
/* 1018 */       int x = start.m_123341_() + this.f_19796_.m_188503_(diameter) - diameter / 2;
/* 1019 */       int z = start.m_123343_() + this.f_19796_.m_188503_(diameter) - diameter / 2;
/* 1020 */       int y = start.m_123342_();
/* 1021 */       BlockPos currentPos = new BlockPos(x, y, z);
/* 1022 */       for (int j = 0; j < 30 && m_9236_().m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); j++)
/* 1023 */         currentPos = currentPos.m_7495_(); 
/* 1024 */       if (NaturalSpawner.m_47051_(SpawnPlacements.Type.ON_GROUND, (LevelReader)m_9236_(), currentPos, type) && Math.sqrt(m_20183_().m_123331_((Vec3i)currentPos)) > 6.0D) {
/*      */         
/* 1026 */         pos = currentPos;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1031 */     return pos;
/*      */   }
/*      */ 
/*      */   
/*      */   private void addHealthAttribute(Mob mob) {
/* 1036 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.m_21051_(Attributes.f_22276_))).m_22125_(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.m_217043_().m_188500_() + 0.5D) * 2.0D), AttributeModifier.Operation.ADDITION));
/*      */   }
/*      */ 
/*      */   
/*      */   private void addSpeedAttribute(Mob mob) {
/* 1041 */     if (mob instanceof SickenedVindicator || mob instanceof SickenedIronGolem) {
/*      */       
/* 1043 */       ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.m_21051_(Attributes.f_22279_))).m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08D, AttributeModifier.Operation.ADDITION));
/*      */     }
/*      */     else {
/*      */       
/* 1047 */       ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.m_21051_(Attributes.f_22279_))).m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.06D, AttributeModifier.Operation.ADDITION));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean hasEnoughSpace(Entity entity, BlockPos spawnPos) {
/* 1053 */     for (BlockPos pos : BlockPos.m_121940_(spawnPos, spawnPos.m_121955_((Vec3i)BlockPos.m_274561_(entity.m_20205_(), entity.m_20206_(), entity.m_20205_())))) {
/*      */       
/* 1055 */       if (!m_9236_().m_8055_(pos).m_60812_((BlockGetter)m_9236_(), pos).m_83281_())
/* 1056 */         return false; 
/*      */     } 
/* 1058 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public BossfightPhase<CommandBlockEntity> getCurrentPhase() {
/* 1063 */     if ((m_9236_()).f_46443_) {
/* 1064 */       return getBossfightManager().getPhase(((Integer)this.f_19804_.m_135370_(PHASE_KEY)).intValue());
/*      */     }
/* 1066 */     return getBossfightManager().getCurrentPhase();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSpecialDeathTime() {
/* 1071 */     return this.specialDeathTime;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected float m_6121_() {
/* 1077 */     return 4.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent m_7975_(@NotNull DamageSource source) {
/* 1083 */     return (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DAMAGE.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent m_5592_() {
/* 1089 */     return (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DEATH.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float m_6100_() {
/* 1095 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6149_() {
/* 1101 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_20073_() {
/* 1107 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHitGlareTime() {
/* 1112 */     return this.hitGlareTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<ServerPlayer> getOutsideBossBarViewers() {
/* 1117 */     return this.outsideBossBarViewers;
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeOutsideBossBarViewer(ServerPlayer player) {
/* 1122 */     this.outsideBossBarViewers.remove(player);
/* 1123 */     this.bossInfo.m_6539_(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_7301_(@NotNull MobEffectInstance instance) {
/* 1129 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public PushReaction m_7752_() {
/* 1135 */     return PushReaction.IGNORE;
/*      */   }
/*      */   
/*      */   public enum Mode
/*      */   {
/* 1140 */     NONE,
/* 1141 */     RIBS
/*      */     {
/*      */       
/*      */       public void playingDeadTick(CommandBlockEntity entity)
/*      */       {
/* 1146 */         super.playingDeadTick(entity);
/* 1147 */         Random random = new Random(entity.m_20148_().getLeastSignificantBits());
/* 1148 */         for (StructureAnimationHelper helper : getStructure(entity)) {
/*      */           
/* 1150 */           helper.lerpBaseTo(entity, -50.0F, 0.0F, Math.max(4, random.nextInt(11)));
/* 1151 */           helper.lerpTo(entity, Math.max(30.0F, random.nextInt(131)), random.nextInt(21) - 10.0F, Math.max(4, random.nextInt(11)));
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void idleTick(CommandBlockEntity entity) {
/* 1158 */         super.idleTick(entity);
/* 1159 */         entity.lerpStructureBaseRotTo(getStructure(entity), -50.0F, 0.0F, 10);
/* 1160 */         entity.lerpStructureRotTo(getStructure(entity), 60.0F, 0.0F, 20);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void protectTick(CommandBlockEntity entity) {
/* 1166 */         super.protectTick(entity);
/* 1167 */         entity.lerpStructureBaseRotTo(getStructure(entity), 0.0F, 0.0F, 40);
/* 1168 */         entity.lerpStructureRotTo(getStructure(entity), 70.0F, 0.0F, 20);
/* 1169 */         if (entity.getProtectionYOffset() > -0.8F && entity.getStateTicks() > 20 + entity.getState().modeTickDelay()) {
/* 1170 */           entity.protectionYOffset -= 0.05F;
/*      */         }
/*      */       }
/*      */ 
/*      */       
/*      */       public void playMovementSound(CommandBlockEntity entity) {
/* 1176 */         for (int i = 0; i < getStructure(entity).size(); i++) {
/*      */           
/* 1178 */           double x = entity.f_19796_.m_188583_() * 3.0D + entity.m_20185_();
/* 1179 */           double y = entity.m_20188_();
/* 1180 */           double z = entity.f_19796_.m_188583_() * 3.0D + entity.m_20189_();
/* 1181 */           entity.m_9236_().m_6263_(null, x, y, z, (SoundEvent)WitherStormModSoundEvents.RIB_BONE_CRACK.get(), SoundSource.AMBIENT, 0.2F, 0.8F);
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public List<StructureAnimationHelper> getStructure(CommandBlockEntity entity) {
/* 1188 */         return entity.getRibStructure();
/*      */       }
/*      */     },
/* 1191 */     TENTACLES
/*      */     {
/*      */       
/*      */       public void tick(CommandBlockEntity entity, CommandBlockEntity.State state)
/*      */       {
/* 1196 */         super.tick(entity, state);
/*      */         
/* 1198 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1200 */           entity.tentacleStructure.addTentacles();
/* 1201 */           entity.tentacleStructure.updateTentacles();
/*      */         } 
/*      */       }
/*      */     };
/*      */ 
/*      */     
/*      */     public void tick(CommandBlockEntity entity, CommandBlockEntity.State state) {
/* 1208 */       entity.modeAnim++;
/* 1209 */       if (!(entity.m_9236_()).f_46443_ && entity.f_19797_ % 120 == 0) {
/* 1210 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new CommandBlockEntity.ModeAnimationMessage(entity.m_19879_(), entity.modeAnim));
/*      */       }
/*      */     }
/*      */     
/*      */     public void idleTick(CommandBlockEntity entity) {}
/*      */     
/*      */     public void playingDeadTick(CommandBlockEntity entity) {}
/*      */     
/*      */     public void protectTick(CommandBlockEntity entity) {}
/*      */     
/*      */     public void init(CommandBlockEntity entity, CommandBlockEntity.State state) {
/* 1221 */       entity.modeAnim = 0;
/* 1222 */       if (!(entity.m_9236_()).f_46443_ && entity.isAddedToWorld())
/*      */       {
/* 1224 */         if (this == TENTACLES) {
/* 1225 */           entity.tentacleStructure.readdTentacles();
/*      */         } else {
/* 1227 */           entity.tentacleStructure.removeTentacles();
/*      */         } 
/*      */       }
/*      */     }
/*      */     
/*      */     public List<StructureAnimationHelper> getStructure(CommandBlockEntity entity) {
/* 1233 */       return Lists.newArrayList();
/*      */     } public void playMovementSound(CommandBlockEntity entity) {}
/*      */   } enum null { public void playingDeadTick(CommandBlockEntity entity) { super.playingDeadTick(entity); Random random = new Random(entity.m_20148_().getLeastSignificantBits()); for (StructureAnimationHelper helper : getStructure(entity)) { helper.lerpBaseTo(entity, -50.0F, 0.0F, Math.max(4, random.nextInt(11))); helper.lerpTo(entity, Math.max(30.0F, random.nextInt(131)), random.nextInt(21) - 10.0F, Math.max(4, random.nextInt(11))); }  }
/*      */     public void idleTick(CommandBlockEntity entity) { super.idleTick(entity); entity.lerpStructureBaseRotTo(getStructure(entity), -50.0F, 0.0F, 10); entity.lerpStructureRotTo(getStructure(entity), 60.0F, 0.0F, 20); }
/*      */     public void protectTick(CommandBlockEntity entity) { super.protectTick(entity); entity.lerpStructureBaseRotTo(getStructure(entity), 0.0F, 0.0F, 40); entity.lerpStructureRotTo(getStructure(entity), 70.0F, 0.0F, 20); if (entity.getProtectionYOffset() > -0.8F && entity.getStateTicks() > 20 + entity.getState().modeTickDelay()) entity.protectionYOffset -= 0.05F;  }
/*      */     public void playMovementSound(CommandBlockEntity entity) { for (int i = 0; i < getStructure(entity).size(); i++) { double x = entity.f_19796_.m_188583_() * 3.0D + entity.m_20185_(); double y = entity.m_20188_(); double z = entity.f_19796_.m_188583_() * 3.0D + entity.m_20189_(); entity.m_9236_().m_6263_(null, x, y, z, (SoundEvent)WitherStormModSoundEvents.RIB_BONE_CRACK.get(), SoundSource.AMBIENT, 0.2F, 0.8F); }  }
/*      */     public List<StructureAnimationHelper> getStructure(CommandBlockEntity entity) { return entity.getRibStructure(); } }
/*      */   enum null { public void tick(CommandBlockEntity entity, CommandBlockEntity.State state) { super.tick(entity, state); if (!(entity.m_9236_()).f_46443_) { entity.tentacleStructure.addTentacles(); entity.tentacleStructure.updateTentacles(); }  } }
/* 1241 */   public enum State { IDLE
/*      */     {
/*      */       
/*      */       public void tick(CommandBlockEntity entity)
/*      */       {
/* 1246 */         super.tick(entity);
/*      */         
/* 1248 */         if (modeTickDelay() < entity.getStateTicks())
/* 1249 */           entity.getMode().idleTick(entity); 
/*      */       }
/*      */     },
/* 1252 */     PLAYING_DEAD
/*      */     {
/*      */       
/*      */       public void tick(CommandBlockEntity entity)
/*      */       {
/* 1257 */         super.tick(entity);
/*      */         
/* 1259 */         if (modeTickDelay() < entity.getStateTicks()) {
/* 1260 */           entity.getMode().playingDeadTick(entity);
/*      */         }
/* 1262 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1264 */           Level world = entity.m_9236_();
/* 1265 */           Player player = world.m_45946_(entity.searchablePlayersPredicate(), entity);
/* 1266 */           if (player != null) {
/*      */             
/* 1268 */             entity.setLuringPlayer(player);
/* 1269 */             entity.nextState();
/*      */           } 
/*      */           
/* 1272 */           int time = ((Integer)WitherStormModConfig.SERVER.revivalTimeMinutes.get()).intValue();
/* 1273 */           if (((Boolean)WitherStormModConfig.SERVER.revivalTimer.get()).booleanValue() && time > 0 && entity.getStateTicks() > time * 1200)
/* 1274 */             entity.setState(State.REACTIVATING); 
/*      */         } 
/*      */       }
/*      */     },
/* 1278 */     LURING
/*      */     {
/*      */       
/*      */       public void tick(CommandBlockEntity entity)
/*      */       {
/* 1283 */         super.tick(entity);
/*      */         
/* 1285 */         if (modeTickDelay() < entity.getStateTicks()) {
/* 1286 */           entity.getMode().idleTick(entity);
/*      */         }
/* 1288 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1290 */           Player player = entity.getLuringPlayer();
/* 1291 */           if (player != null && entity.searchablePlayersPredicate().m_26883_(12.0D).m_26885_(entity, (LivingEntity)player) && entity.getStateTicks() < 240) {
/*      */             
/* 1293 */             double speed = 0.025D;
/* 1294 */             Vec3 motion = entity.m_20182_().m_82546_(player.m_20182_()).m_82541_().m_82542_(speed, speed, speed);
/* 1295 */             player.m_20334_(motion.f_82479_, (player.m_20184_()).f_82480_, motion.f_82481_);
/* 1296 */             WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new PlayerMotionMessage(new Vec3(motion.f_82479_, (player.m_20184_()).f_82480_, motion.f_82481_)));
/* 1297 */             if (player.m_20270_((Entity)entity) < 3.0D) {
/*      */               
/* 1299 */               entity.setLuringPlayer((Player)null);
/* 1300 */               entity.nextState();
/*      */             } 
/* 1302 */             for (int i = 0; i < 4; i++)
/*      */             {
/* 1304 */               double x = player.m_20185_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82362_() * 0.4D;
/* 1305 */               double y = player.m_20191_().m_82399_().m_7098_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82376_() * 0.4D;
/* 1306 */               double z = player.m_20189_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82385_() * 0.4D;
/* 1307 */               Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82490_(0.1D);
/* 1308 */               entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1313 */             entity.nextState();
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void init(CommandBlockEntity entity) {
/* 1321 */         super.init(entity);
/* 1322 */         entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.HOSTILE, 5.0F, 1.0F);
/* 1323 */         entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
/* 1324 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1326 */           ShakeScreenMessage message = new ShakeScreenMessage(40.0F, 5.0F);
/* 1327 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*      */         } 
/* 1329 */         for (int i = 0; i < 10; i++) {
/* 1330 */           entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), entity.f_19796_.m_188583_() * 0.5D, entity.f_19796_.m_188583_() * 0.5D, entity.f_19796_.m_188583_() * 0.5D);
/*      */         }
/*      */       }
/*      */ 
/*      */       
/*      */       public boolean shouldShowOwnerBossBar() {
/* 1336 */         return true;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public int modeTickDelay() {
/* 1342 */         return 40;
/*      */       }
/*      */     },
/* 1345 */     REACTIVATING
/*      */     {
/*      */       
/*      */       public void tick(CommandBlockEntity entity)
/*      */       {
/* 1350 */         super.tick(entity);
/*      */         
/* 1352 */         if (modeTickDelay() < entity.getStateTicks()) {
/* 1353 */           entity.getMode().protectTick(entity);
/*      */         }
/* 1355 */         if (!(entity.m_9236_()).f_46443_)
/*      */         {
/* 1357 */           if (entity.getOwner() != null && entity.getStateTicks() > 60) {
/*      */             
/* 1359 */             WitherStormEntity owner = entity.getOwner();
/* 1360 */             if (!owner.isReviving()) {
/* 1361 */               owner.reviveFromPlayingDead();
/*      */             }
/*      */           } 
/*      */         }
/*      */       }
/*      */ 
/*      */       
/*      */       public void init(CommandBlockEntity entity) {
/* 1369 */         super.init(entity);
/*      */         
/* 1371 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1373 */           ShakeScreenMessage message = new ShakeScreenMessage(120.0F, 5.0F);
/* 1374 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*      */         } 
/* 1376 */         entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean shouldShowOwnerBossBar() {
/* 1382 */         return true;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public int modeTickDelay() {
/* 1388 */         return 20;
/*      */       }
/*      */     },
/* 1391 */     BOSSFIGHT
/*      */     {
/*      */       
/*      */       public void tick(CommandBlockEntity entity)
/*      */       {
/* 1396 */         super.tick(entity);
/*      */         
/* 1398 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1400 */           entity.getBossfightManager().tick();
/* 1401 */           if (!entity.getCurrentPhase().equals(BowelsBossFightStages.IDLE) || entity.m_21223_() < entity.m_21233_()) {
/* 1402 */             entity.bossInfo.m_8321_(true);
/*      */           }
/*      */         } 
/* 1405 */         entity.getMode().idleTick(entity);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void init(CommandBlockEntity entity) {
/* 1411 */         super.init(entity);
/*      */       }
/*      */     };
/*      */ 
/*      */     
/*      */     public void tick(CommandBlockEntity entity) {
/* 1417 */       entity.getMode().tick(entity, entity.getState());
/* 1418 */       entity.stateTicks++;
/* 1419 */       if (this != PLAYING_DEAD)
/*      */       {
/* 1421 */         for (int i = 0; i < 5; i++) {
/*      */           
/* 1423 */           double x = entity.m_20185_() + entity.f_19796_.m_188583_();
/* 1424 */           double y = entity.m_20188_() + entity.f_19796_.m_188583_();
/* 1425 */           double z = entity.m_20189_() + entity.f_19796_.m_188583_();
/* 1426 */           float particleSpeed = (entity.m_21233_() - entity.m_21223_()) / entity.m_21233_() + 1.0F;
/* 1427 */           Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82490_(0.1D * particleSpeed);
/* 1428 */           entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void init(CommandBlockEntity entity) {
/* 1435 */       entity.stateTicks = 0;
/* 1436 */       entity.getMode().init(entity, entity.getState());
/* 1437 */       entity.setProtectionYOffset(0.0F);
/* 1438 */       if (entity.getOwner() != null) {
/* 1439 */         initWithOwner(entity.getOwner(), entity);
/*      */       }
/* 1441 */       entity.bossInfo.m_8321_(false);
/*      */     }
/*      */ 
/*      */     
/*      */     public int modeTickDelay() {
/* 1446 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean shouldShowOwnerBossBar() {
/* 1451 */       return false;
/*      */     }
/*      */     
/*      */     public void initWithOwner(WitherStormEntity owner, CommandBlockEntity entity)
/*      */     {
/* 1456 */       if (owner.m_6084_())
/*      */       {
/* 1458 */         if (shouldShowOwnerBossBar())
/* 1459 */         { owner.getBossInfo().ifPresent(info -> info.m_8321_(true)); }
/*      */         else
/* 1461 */         { owner.getBossInfo().ifPresent(info -> info.m_8321_(!owner.isPlayingDead())); }  }  } }
/*      */   enum null {
/*      */     public void tick(CommandBlockEntity entity) { super.tick(entity); if (modeTickDelay() < entity.getStateTicks()) entity.getMode().idleTick(entity);  }
/*      */   }
/*      */   enum null { public void tick(CommandBlockEntity entity) { super.tick(entity); if (modeTickDelay() < entity.getStateTicks()) entity.getMode().playingDeadTick(entity);  if (!(entity.m_9236_()).f_46443_) { Level world = entity.m_9236_(); Player player = world.m_45946_(entity.searchablePlayersPredicate(), entity); if (player != null) { entity.setLuringPlayer(player); entity.nextState(); }  int time = ((Integer)WitherStormModConfig.SERVER.revivalTimeMinutes.get()).intValue(); if (((Boolean)WitherStormModConfig.SERVER.revivalTimer.get()).booleanValue() && time > 0 && entity.getStateTicks() > time * 1200) entity.setState(CommandBlockEntity.State.REACTIVATING);  }  } }
/*      */   enum null {
/*      */     public void tick(CommandBlockEntity entity) { super.tick(entity); if (modeTickDelay() < entity.getStateTicks()) entity.getMode().idleTick(entity);  if (!(entity.m_9236_()).f_46443_) { Player player = entity.getLuringPlayer(); if (player != null && entity.searchablePlayersPredicate().m_26883_(12.0D).m_26885_(entity, (LivingEntity)player) && entity.getStateTicks() < 240) { double speed = 0.025D; Vec3 motion = entity.m_20182_().m_82546_(player.m_20182_()).m_82541_().m_82542_(speed, speed, speed); player.m_20334_(motion.f_82479_, (player.m_20184_()).f_82480_, motion.f_82481_); WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new PlayerMotionMessage(new Vec3(motion.f_82479_, (player.m_20184_()).f_82480_, motion.f_82481_))); if (player.m_20270_((Entity)entity) < 3.0D) { entity.setLuringPlayer((Player)null); entity.nextState(); }  for (int i = 0; i < 4; i++) { double x = player.m_20185_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82362_() * 0.4D; double y = player.m_20191_().m_82399_().m_7098_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82376_() * 0.4D; double z = player.m_20189_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82385_() * 0.4D; Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82490_(0.1D); entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_()); }  } else { entity.nextState(); }  }  }
/*      */     public void init(CommandBlockEntity entity) { super.init(entity); entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.HOSTILE, 5.0F, 1.0F); entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F); if (!(entity.m_9236_()).f_46443_) { ShakeScreenMessage message = new ShakeScreenMessage(40.0F, 5.0F); WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message); }  for (int i = 0; i < 10; i++) entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), entity.f_19796_.m_188583_() * 0.5D, entity.f_19796_.m_188583_() * 0.5D, entity.f_19796_.m_188583_() * 0.5D);  } public boolean shouldShowOwnerBossBar() { return true; } public int modeTickDelay() { return 40; }
/*      */   } enum null {
/*      */     public void tick(CommandBlockEntity entity) { super.tick(entity); if (modeTickDelay() < entity.getStateTicks()) entity.getMode().protectTick(entity);  if (!(entity.m_9236_()).f_46443_) if (entity.getOwner() != null && entity.getStateTicks() > 60) { WitherStormEntity owner = entity.getOwner(); if (!owner.isReviving()) owner.reviveFromPlayingDead();  }   } public void init(CommandBlockEntity entity) { super.init(entity); if (!(entity.m_9236_()).f_46443_) { ShakeScreenMessage message = new ShakeScreenMessage(120.0F, 5.0F); WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message); }  entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F); } public boolean shouldShowOwnerBossBar() { return true; } public int modeTickDelay() { return 20; }
/*      */   } enum null {
/*      */     public void tick(CommandBlockEntity entity) { super.tick(entity); if (!(entity.m_9236_()).f_46443_) { entity.getBossfightManager().tick(); if (!entity.getCurrentPhase().equals(BowelsBossFightStages.IDLE) || entity.m_21223_() < entity.m_21233_()) entity.bossInfo.m_8321_(true);  }  entity.getMode().idleTick(entity); } public void init(CommandBlockEntity entity) { super.init(entity); }
/* 1473 */   } public static class ModeAnimationMessage extends Packet { public ModeAnimationMessage(int entityId, int anim) { super(true);
/* 1474 */       this.id = entityId;
/* 1475 */       this.anim = anim; }
/*      */     
/*      */     private int id; private int anim;
/*      */     
/*      */     public ModeAnimationMessage() {
/* 1480 */       super(false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void encode(FriendlyByteBuf buffer) {
/* 1486 */       buffer.m_130130_(this.id);
/* 1487 */       buffer.writeInt(this.anim);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void decode(FriendlyByteBuf buffer) {
/* 1493 */       this.id = buffer.m_130242_();
/* 1494 */       this.anim = buffer.readInt();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Runnable getProcessor(NetworkEvent.Context context) {
/* 1500 */       return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*      */     } }
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
/*      */   public static class TentacleManager
/*      */   {
/* 1515 */     private static final UUID KNOCKBACK_MODIFIER = UUID.fromString("72aeccbe-cdfe-41c9-9d21-261f29f6da60");
/*      */     
/*      */     private final CommandBlockEntity entity;
/*      */     public final int tentacles;
/*      */     public TentacleEntity[] tentacleStructure;
/*      */     private final UUID[] savedTentacleStructure;
/*      */     private final TentacleOffsets[] offsets;
/*      */     
/*      */     public TentacleManager(CommandBlockEntity entity, int amount, TentacleOffsets[] offsets) {
/* 1524 */       this.entity = entity;
/* 1525 */       this.tentacles = amount;
/* 1526 */       this.tentacleStructure = new TentacleEntity[this.tentacles];
/* 1527 */       this.savedTentacleStructure = new UUID[this.tentacles];
/* 1528 */       this.offsets = offsets;
/*      */     }
/*      */ 
/*      */     
/*      */     public void createTentacles() {
/* 1533 */       for (int i = 0; i < this.tentacleStructure.length; i++) {
/* 1534 */         createTentacle(i);
/*      */       }
/*      */     }
/*      */     
/*      */     private void createTentacle(int index) {
/* 1539 */       if (this.tentacleStructure[index] == null || !this.tentacleStructure[index].m_6084_()) {
/*      */         
/* 1541 */         TentacleEntity tentacle = (TentacleEntity)((EntityType)WitherStormModEntityTypes.TENTACLE.get()).m_20615_(this.entity.m_9236_());
/* 1542 */         getOffsetsForTentacle(index).apply(this.entity, tentacle);
/* 1543 */         assert tentacle != null;
/* 1544 */         tentacle.setDormant(true);
/* 1545 */         tentacle.lerpCurlTo(0.0F, 0.0F, 1);
/* 1546 */         tentacle.m_20331_(true);
/* 1547 */         tentacle.m_20242_(true);
/* 1548 */         tentacle.setAnimationOffset(this.entity.f_19796_.m_188503_(35) * 10000);
/* 1549 */         tentacle.setCanStrangle(false);
/* 1550 */         ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(tentacle.m_21051_(Attributes.f_22282_))).m_22125_(new AttributeModifier(KNOCKBACK_MODIFIER, "Command block's tentacles knockback modifier", 5.0D, AttributeModifier.Operation.ADDITION));
/* 1551 */         this.tentacleStructure[index] = tentacle;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void removeTentacles() {
/* 1557 */       for (TentacleEntity tentacle : this.tentacleStructure) {
/* 1558 */         if (tentacle != null && tentacle.m_6084_()) {
/* 1559 */           tentacle.m_146870_();
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*      */     public void readdTentacles() {
/* 1565 */       createTentacles();
/* 1566 */       addTentacles();
/*      */     }
/*      */ 
/*      */     
/*      */     public void addTentacles() {
/* 1571 */       if (this.entity.isAddedToWorld() && this.entity.m_6084_())
/*      */       {
/* 1573 */         for (int i = 0; i < this.tentacleStructure.length; i++) {
/* 1574 */           addTentacle(i);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void addTentacle(int index) {
/* 1580 */       TentacleEntity tentacle = this.tentacleStructure[index];
/* 1581 */       if (tentacle != null && !tentacle.isAddedToWorld() && tentacle.m_6084_()) {
/*      */         
/* 1583 */         getOffsetsForTentacle(index).apply(this.entity, tentacle);
/* 1584 */         this.entity.m_9236_().m_7967_((Entity)tentacle);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void updateTentacles() {
/* 1590 */       for (int i = 0; i < this.tentacleStructure.length; i++) {
/*      */         
/* 1592 */         TentacleEntity tentacle = this.tentacleStructure[i];
/* 1593 */         if (tentacle != null && tentacle.m_6084_()) {
/* 1594 */           getOffsetsForTentacle(i).apply(this.entity, tentacle);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*      */     public void killTentacles() {
/* 1600 */       for (TentacleEntity tentacle : this.tentacleStructure) {
/*      */         
/* 1602 */         if (tentacle != null) {
/* 1603 */           tentacle.m_6074_();
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void findTentacles(ServerLevel world) {
/* 1610 */       for (int i = 0; i < this.savedTentacleStructure.length; i++) {
/*      */         
/* 1612 */         UUID uuid = this.savedTentacleStructure[i];
/* 1613 */         TentacleEntity preexisting = this.tentacleStructure[i];
/* 1614 */         if ((uuid != null && preexisting != null && !preexisting.m_20148_().equals(uuid)) || preexisting == null) {
/*      */           
/* 1616 */           assert uuid != null;
/* 1617 */           Entity entity = world.m_8791_(uuid);
/* 1618 */           if (entity instanceof TentacleEntity) { TentacleEntity found = (TentacleEntity)entity;
/*      */             
/* 1620 */             if (preexisting != null)
/* 1621 */               preexisting.m_146870_(); 
/* 1622 */             this.tentacleStructure[i] = found;
/* 1623 */             this.savedTentacleStructure[i] = null; }
/*      */         
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public TentacleOffsets getOffsetsForTentacle(int index) {
/* 1631 */       return this.offsets[index];
/*      */     }
/*      */ 
/*      */     
/*      */     public void addSaveData(CompoundTag compound) {
/* 1636 */       ListTag list = new ListTag();
/* 1637 */       for (TentacleEntity tentacle : this.tentacleStructure) {
/* 1638 */         CompoundTag tentacleCompound = new CompoundTag();
/* 1639 */         if (tentacle != null)
/* 1640 */           tentacleCompound.m_128362_("UUID", tentacle.m_20148_()); 
/* 1641 */         list.add(tentacleCompound);
/*      */       } 
/* 1643 */       compound.m_128365_("Tentacles", (Tag)list);
/*      */     }
/*      */ 
/*      */     
/*      */     public void readSaveData(CompoundTag compound) {
/* 1648 */       ListTag list = compound.m_128437_("Tentacles", 10);
/* 1649 */       for (int i = 0; i < this.tentacleStructure.length; i++) {
/*      */         
/* 1651 */         CompoundTag tentacleCompound = list.m_128728_(i);
/* 1652 */         if (tentacleCompound.m_128441_("UUID") && this.savedTentacleStructure[i] == null)
/*      */         {
/* 1654 */           this.savedTentacleStructure[i] = tentacleCompound.m_128342_("UUID");
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\CommandBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */