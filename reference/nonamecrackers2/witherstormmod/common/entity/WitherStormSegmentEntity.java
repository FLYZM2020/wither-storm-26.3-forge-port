/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerBossEvent;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.tags.DamageTypeTags;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityDimensions;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.control.BodyRotationControl;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.BowelsInstanceManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SegmentsManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SymbiontSummoningManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.util.ClusterBuilderHelper;
/*     */ import nonamecrackers2.witherstormmod.common.util.StormHeadOffsets;
/*     */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class WitherStormSegmentEntity
/*     */   extends WitherStormEntity
/*     */ {
/*  57 */   private static final EntityDataAccessor<Optional<UUID>> PARENT_UUID = SynchedEntityData.m_135353_(WitherStormSegmentEntity.class, EntityDataSerializers.f_135041_);
/*  58 */   private static final EntityDimensions STARTING_SIZE = EntityDimensions.m_20395_(15.0F, 17.5F); @Nullable
/*  59 */   private WitherStormEntity parent; private final int tillFreeFall; private static final EntityDimensions EVOLVED_SIZE = EntityDimensions.m_20395_(15.0F, 17.5F);
/*     */   
/*     */   private int dropTime;
/*     */   
/*  63 */   private int nextDropTime = 120 + this.f_19796_.m_188503_(160); private int timeWithParent;
/*     */   @Nullable
/*     */   private Vec3 wantedSegmentPos;
/*     */   @Nullable
/*     */   private Vec3 randomStrollPos;
/*     */   private int tillNextRandomStroll;
/*     */   private float randomBodyRotAngleOffset;
/*     */   
/*     */   public WitherStormSegmentEntity(EntityType<? extends WitherStormEntity> entityTypeIn, Level worldIn) {
/*  72 */     super(entityTypeIn, worldIn);
/*  73 */     this.f_21364_ = 0;
/*  74 */     this.partsEnabled = false;
/*  75 */     this.shouldFollowUltimateTarget = false;
/*  76 */     this.chunkloads = false;
/*  77 */     this.shouldPlaySoundLoop = false;
/*  78 */     this.shouldPlayGlobalSounds = false;
/*  79 */     this.shouldIgnoreFormidibomb = true;
/*  80 */     this.tillFreeFall = Math.max(220, this.f_19796_.m_188503_(260));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected BodyRotationControl m_7560_() {
/*  86 */     return (BodyRotationControl)new BodyController();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected HeadManager makeHeadManager() {
/*  92 */     return new HeadManager(this, StormHeadOffsets.SEGMENT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Optional<SegmentsManager> makeSegmentsManager() {
/*  98 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Optional<SymbiontSummoningManager> makeSummoningManager() {
/* 104 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Optional<BowelsInstanceManager> makeBowelsInstanceManager() {
/* 110 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Optional<UltimateTargetManager> makeUltimateTargetManager() {
/* 116 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Optional<ServerBossEvent> makeBossEvent() {
/* 122 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormSegmentEntity(WitherStormEntity parent) {
/* 127 */     this((EntityType<? extends WitherStormEntity>)WitherStormModEntityTypes.WITHER_STORM_SEGMENT.get(), parent.m_9236_());
/* 128 */     setParent(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/* 133 */     return Monster.m_33035_()
/* 134 */       .m_22268_((Attribute)WitherStormModAttributes.TARGET_STATIONARY_FLYING_SPEED.get(), 0.4D)
/* 135 */       .m_22268_((Attribute)WitherStormModAttributes.SLOW_FLYING_SPEED.get(), 0.05D)
/* 136 */       .m_22268_((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get(), 1.0D)
/* 137 */       .m_22268_(Attributes.f_22280_, 0.0D)
/* 138 */       .m_22268_(Attributes.f_22276_, 4000.0D)
/* 139 */       .m_22268_(Attributes.f_22279_, 0.6D)
/* 140 */       .m_22268_(Attributes.f_22277_, 160.0D)
/* 141 */       .m_22268_((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get(), 40.0D)
/* 142 */       .m_22268_(Attributes.f_22284_, 6.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 148 */     super.m_8097_();
/* 149 */     this.f_19804_.m_135372_(PARENT_UUID, Optional.empty());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(@NotNull CompoundTag compound) {
/* 155 */     super.m_7380_(compound);
/* 156 */     if (getParentUUID() != null)
/* 157 */       compound.m_128362_("Parent", getParentUUID()); 
/* 158 */     compound.m_128405_("TimeWithParent", this.timeWithParent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag compound) {
/* 164 */     super.m_7378_(compound);
/* 165 */     if (compound.m_128441_("Parent"))
/* 166 */       setParentUUID(compound.m_128342_("Parent")); 
/* 167 */     this.timeWithParent = compound.m_128451_("TimeWithParent");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/* 173 */     super.m_8119_();
/* 174 */     WitherStormEntity parent = getParent();
/*     */     
/* 176 */     if (parent == null && getParentUUID() != null) {
/*     */       
/* 178 */       List<? extends Entity> entities = m_9236_().m_45976_(WitherStormEntity.class, m_20191_().m_82400_(1000.0D));
/* 179 */       if (!(m_9236_()).f_46443_)
/* 180 */         entities = Lists.newArrayList(((ServerLevel)m_9236_()).m_8583_()); 
/* 181 */       for (Entity entity : entities) {
/*     */         
/* 183 */         if (getParentUUID().equals(entity.m_20148_())) {
/*     */           
/* 185 */           WitherStormEntity storm = (WitherStormEntity)entity;
/* 186 */           setParent(storm);
/*     */         } 
/*     */       } 
/*     */       
/* 190 */       if (isOnDistantRenderer())
/*     */       {
/* 192 */         m_9236_().getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(renderer -> {
/*     */               for (WitherStormEntity storm : renderer.getKnown()) {
/*     */                 if (getParentUUID().equals(storm.m_20148_())) {
/*     */                   setParent(storm);
/*     */                 }
/*     */               } 
/*     */             });
/*     */       }
/*     */     } 
/*     */     
/* 202 */     if (parent != null) {
/* 203 */       this.timeWithParent++;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_8024_() {
/* 209 */     super.m_8024_();
/* 210 */     WitherStormEntity parent = getParent();
/* 211 */     if (parent != null) {
/*     */       
/* 213 */       calculateDesiredPos();
/*     */       
/* 215 */       Vec3 desiredPos = getDesiredPos();
/*     */       
/* 217 */       Vec3 parentPos = new Vec3(parent.m_20185_() - m_20185_(), parent.m_20186_() - m_20186_(), parent.m_20189_() - m_20189_());
/* 218 */       if (parentPos.m_165924_() > 200.0D) {
/* 219 */         assert desiredPos != null;
/* 220 */         m_20219_(desiredPos);
/*     */       } 
/*     */       
/* 223 */       if (parent.f_267362_.m_267731_() < 0.3F && m_20182_().m_82554_(Objects.<Vec3>requireNonNull(desiredPos)) < 50.0D) {
/*     */         
/* 225 */         if (this.tillNextRandomStroll == 0) {
/*     */           
/* 227 */           double x = this.f_19796_.m_188500_() * 20.0D - 10.0D + desiredPos.f_82479_;
/* 228 */           double y = this.f_19796_.m_188500_() * 40.0D - 20.0D + desiredPos.f_82480_;
/* 229 */           double z = this.f_19796_.m_188500_() * 20.0D - 10.0D + desiredPos.f_82481_;
/* 230 */           this.randomStrollPos = new Vec3(x, y, z);
/* 231 */           this.tillNextRandomStroll = 200 + this.f_19796_.m_188503_(100);
/* 232 */           this.randomBodyRotAngleOffset = (this.f_19796_.m_188501_() * 20.0F + 20.0F) * (isMirrored() ? -1.0F : 1.0F);
/*     */         } 
/*     */         
/* 235 */         if (this.tillNextRandomStroll > 0) {
/* 236 */           this.tillNextRandomStroll--;
/*     */         }
/* 238 */         if (this.randomStrollPos != null)
/*     */         {
/* 240 */           if (m_20182_().m_82554_(this.randomStrollPos) < 5.0D) {
/* 241 */             this.tillNextRandomStroll = 0;
/*     */           }
/*     */         }
/*     */       } else {
/*     */         
/* 246 */         this.tillNextRandomStroll = 0;
/* 247 */         this.randomStrollPos = null;
/* 248 */         this.randomBodyRotAngleOffset = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 252 */     CommandBlockEntity commandBlock = getBowelsCommandBlock();
/* 253 */     if (commandBlock != null)
/*     */     {
/* 255 */       if (commandBlock.m_21223_() < commandBlock.m_21233_()) {
/*     */         
/* 257 */         this.nextDropTime--;
/* 258 */         if (this.nextDropTime == 0) {
/*     */           
/* 260 */           this.dropTime = 10 + this.f_19796_.m_188503_(5);
/* 261 */           this.nextDropTime = (int)((360 + this.f_19796_.m_188503_(160)) * Math.max(0.2D, (commandBlock.m_21223_() / commandBlock.m_21233_())));
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 266 */     if (this.dropTime > 0) {
/* 267 */       this.dropTime--;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double getHeightToAscendTo(Vec3 vector3d, double height, double ascendSpeed) {
/* 273 */     return (getParent() != null) ? getParent().getDesiredSegmentY(getSegmentIndex()) : super.getHeightToAscendTo(vector3d, height, ascendSpeed);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFlyingSpeed(Vec3 ultimateTarget) {
/* 279 */     WitherStormEntity parent = getParent();
/* 280 */     if (parent != null) {
/*     */       
/* 282 */       if (this.randomStrollPos == null) {
/*     */         
/* 284 */         double speed = parent.m_21133_(Attributes.f_22280_);
/* 285 */         if (parent.shouldSpeedUp()) {
/* 286 */           speed += parent.getDefaultChasingSpeed() + 0.05D;
/*     */         } else {
/* 288 */           speed += parent.getDefaultNormalSpeed() + 0.08D;
/* 289 */         }  return speed;
/*     */       } 
/*     */ 
/*     */       
/* 293 */       return 0.01D;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 298 */     return super.getFlyingSpeed(ultimateTarget);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Vec3 doFlying(Vec3 vector3d) {
/* 305 */     if (getParent() != null && !isDeadOrPlayingDead()) {
/*     */       Vec3 wanted;
/* 307 */       double ascendSpeed = (this.randomStrollPos == null) ? 0.02D : 0.01D;
/*     */ 
/*     */       
/* 310 */       if (this.randomStrollPos != null) {
/* 311 */         wanted = this.randomStrollPos;
/*     */       } else {
/* 313 */         wanted = getDesiredPos();
/* 314 */       }  assert wanted != null;
/* 315 */       double height = wanted.f_82480_;
/*     */       
/* 317 */       double d0 = vector3d.f_82480_;
/* 318 */       if (m_20186_() < height || (!m_7090_() && m_20186_() < height + 5.0D))
/* 319 */         d0 = (height - m_20186_()) * ascendSpeed; 
/* 320 */       vector3d = new Vec3(vector3d.f_82479_, d0, vector3d.f_82481_);
/*     */       
/* 322 */       Vec3 vector3d1 = new Vec3(wanted.f_82479_ - m_20185_(), 0.0D, wanted.f_82481_ - m_20189_());
/* 323 */       double speed = getFlyingSpeed(vector3d1);
/* 324 */       speed = Math.min(speed, Math.sqrt(m_20238_(wanted)) * 0.01D);
/* 325 */       if (vector3d1.m_165924_() > 1.0D) {
/*     */         
/* 327 */         Vec3 vector3d2 = vector3d1.m_82541_();
/* 328 */         vector3d = vector3d.m_82520_(vector3d2.f_82479_ * speed - vector3d.f_82479_ * 0.6D, 0.0D, vector3d2.f_82481_ * speed - vector3d.f_82481_ * 0.6D);
/*     */       } 
/*     */       
/* 331 */       return vector3d;
/*     */     } 
/*     */ 
/*     */     
/* 335 */     return super.doFlying(vector3d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_6153_() {
/* 342 */     super.m_6153_();
/* 343 */     if ((m_9236_()).f_46443_ && !m_20096_()) {
/*     */       
/* 345 */       float xOffset = (this.f_19796_.m_188501_() - 0.5F) * ((float)m_20191_().m_82362_() + 5.0F);
/* 346 */       float yOffset = (this.f_19796_.m_188501_() - 0.5F) * ((float)m_20191_().m_82376_() + 5.0F);
/* 347 */       float zOffset = (this.f_19796_.m_188501_() - 0.5F) * ((float)m_20191_().m_82385_() + 5.0F);
/* 348 */       m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123813_, m_20185_() + xOffset, m_20188_() + yOffset, m_20189_() + zOffset, -5.0D, 0.0D, 0.0D);
/*     */     } 
/* 350 */     if (m_20096_())
/*     */     {
/* 352 */       for (WitherStormHead head : this.headManager.getHeads()) {
/* 353 */         head.lerpHeadXTo(90.0F, 10.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public EntityDimensions m_6972_(@NotNull Pose pose) {
/* 360 */     EntityDimensions size = getUnmodifiedDimensions(pose);
/* 361 */     if (((Boolean)WitherStormModConfig.SERVER.squashHitbox.get()).booleanValue() && getPhase() > 3)
/* 362 */       size = EntityDimensions.m_20395_(size.f_20377_, 1.0F); 
/* 363 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDimensions getUnmodifiedDimensions(Pose pose) {
/* 369 */     EntityDimensions size = STARTING_SIZE;
/* 370 */     if (getPhase() == 7)
/* 371 */       size = EVOLVED_SIZE; 
/* 372 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float m_6431_(@NotNull Pose pose, @NotNull EntityDimensions size) {
/* 378 */     return 10.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setPhase(int phase, int consumedEntities) {
/* 384 */     boolean result = false;
/* 385 */     if (getParent() != null) {
/*     */       
/* 387 */       result = false;
/* 388 */       this.f_19804_.m_135381_(PHASE, Integer.valueOf(getParent().getPhase()));
/*     */ 
/*     */     
/*     */     }
/* 392 */     else if (phase <= 7) {
/*     */       
/* 394 */       this.f_19804_.m_135381_(PHASE, Integer.valueOf(Math.max(phase, 6)));
/* 395 */       result = (phase >= 6);
/*     */     }
/*     */     else {
/*     */       
/* 399 */       result = false;
/*     */     } 
/*     */     
/* 402 */     this.clusterRadius = (int)Math.max(1.0D, (getPhase() * 0.75F));
/* 403 */     this.entityConsumptionRadius = (getPhase() > 3) ? 64 : 16;
/*     */     
/* 405 */     setConsumedEntities(consumedEntities);
/* 406 */     m_20090_();
/* 407 */     m_6210_();
/* 408 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParent(WitherStormEntity parent) {
/* 413 */     this.parent = parent;
/* 414 */     if (parent != null) {
/*     */       
/* 416 */       this.f_19804_.m_135381_(PARENT_UUID, Optional.of(parent.m_20148_()));
/* 417 */       assert getParent() != null;
/* 418 */       getPlayDeadManager().setStateRaw(getParent().getPlayDeadManager().getState());
/* 419 */       getPlayDeadManager().setTickAmount(getParent().getPlayDeadManager().getTicks());
/*     */     }
/*     */     else {
/*     */       
/* 423 */       this.f_19804_.m_135381_(PARENT_UUID, Optional.empty());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setParentUUID(UUID uuid) {
/* 429 */     this.f_19804_.m_135381_(PARENT_UUID, Optional.of(uuid));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getParentUUID() {
/* 434 */     return ((Optional<UUID>)this.f_19804_.m_135370_(PARENT_UUID)).orElse(null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public WitherStormEntity getParent() {
/* 439 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LivingEntity getUltimateTarget() {
/* 445 */     return (getParent() != null) ? getParent().getUltimateTarget() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConsumedEntities() {
/* 451 */     return (getParent() != null) ? getParent().getConsumedEntities() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConsumedEntities(int newAmount) {}
/*     */ 
/*     */   
/*     */   public void addToConsumedEntities(int amount) {}
/*     */ 
/*     */   
/*     */   public int getInvulnerableTicks() {
/* 463 */     return (getParent() != null) ? getParent().getInvulnerableTicks() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInvulnerableTicks(int ticks) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean targetInUseBySegment(Entity entity) {
/* 489 */     WitherStormEntity parent = getParent();
/* 490 */     if (parent != null) {
/*     */       
/* 492 */       if (parent.alreadyATarget(entity, true))
/* 493 */         return true; 
/* 494 */       Optional<SegmentsManager> manager = getParent().getSegmentsManager();
/* 495 */       if (manager.isPresent()) {
/*     */         
/* 497 */         WitherStormSegmentEntity[] segments = ((SegmentsManager)manager.get()).getSegments();
/* 498 */         for (WitherStormSegmentEntity segment : segments) {
/*     */           
/* 500 */           if (segment != null && !equals(segment))
/*     */           {
/* 502 */             if (segment.alreadyATarget(entity, true))
/* 503 */               return true; 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 508 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDebrisRings(boolean hidden) {}
/*     */ 
/*     */   
/*     */   public void createDebrisClusters(boolean hidden) {}
/*     */ 
/*     */   
/*     */   public int getTimeTillFreeFall() {
/* 520 */     return this.tillFreeFall;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBigFall() {
/* 526 */     super.onBigFall();
/* 527 */     for (int i = 0; i < 6; i++) {
/* 528 */       m_9236_().m_255391_((Entity)this, m_20185_(), m_20188_() - i, m_20189_(), 16.0F, false, Level.ExplosionInteraction.MOB);
/*     */     }
/*     */   }
/*     */   
/*     */   public void regatherCapabilities() {
/* 533 */     gatherCapabilities();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldDoCustomMovement() {
/* 539 */     PlayDeadManager.State state = getPlayDeadManager().getState();
/* 540 */     return (this.dropTime <= 0 && getDeathTime() < getTimeTillFreeFall() && (state != PlayDeadManager.State.FALLING || getPlayDeadManager().getTicks() <= 200) && this.shouldDoCustomMovement);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canFallOnBack() {
/* 546 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getUltimateTargetPos() {
/* 552 */     return (getParent() != null) ? getParent().getUltimateTargetPos() : super.getUltimateTargetPos();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandBlockEntity getBowelsCommandBlock() {
/* 558 */     return (getParent() != null) ? getParent().getBowelsCommandBlock() : super.getBowelsCommandBlock();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropDropsAt(Entity player) {}
/*     */ 
/*     */   
/*     */   protected boolean canTrackEntity(Entity entity) {
/* 567 */     if (!super.canTrackEntity(entity)) {
/* 568 */       return false;
/*     */     }
/* 570 */     return (getParent() == null || !getParent().getTrackedEntities().contains(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBeingTornApart() {
/* 576 */     return (getParent() != null) ? getParent().isBeingTornApart() : super.isBeingTornApart();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeWithParent() {
/* 581 */     return this.timeWithParent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isInsideOtherTractorBeam(LivingEntity entity, int head) {
/* 587 */     if (getParent() != null) {
/*     */       
/* 589 */       List<WitherStormEntity> storms = Lists.newArrayList((Object[])new WitherStormEntity[] { getParent() });
/* 590 */       getParent().getSegmentsManager().ifPresent(manager -> {
/*     */             for (WitherStormSegmentEntity segment : manager.getSegments()) {
/*     */               if (segment != null && segment.m_6084_()) {
/*     */                 storms.add(segment);
/*     */               }
/*     */             } 
/*     */           });
/*     */       
/* 598 */       for (WitherStormEntity storm : storms) {
/*     */         
/* 600 */         Pair<Boolean, Integer> flag = TractorBeamHelper.isInsideTractorBeam((Entity)entity, (LivingEntity)storm, 5.0D);
/* 601 */         if (((Boolean)flag.getFirst()).booleanValue() && ((Integer)flag.getSecond()).intValue() != head)
/* 602 */           return true; 
/*     */       } 
/* 604 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 608 */     return super.isInsideOtherTractorBeam(entity, head);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropMassCluster(int radius) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldShine() {
/* 618 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void calculateDesiredPos() {
/* 623 */     WitherStormEntity parent = getParent();
/* 624 */     int index = getSegmentIndex();
/* 625 */     assert parent != null;
/* 626 */     this
/*     */ 
/*     */       
/* 629 */       .wantedSegmentPos = new Vec3(parent.getDesiredSegmentX(index), parent.getDesiredSegmentY(index), parent.getDesiredSegmentZ(index));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private Vec3 getDesiredPos() {
/* 635 */     if (this.wantedSegmentPos == null && getParent() != null)
/* 636 */       calculateDesiredPos(); 
/* 637 */     return this.wantedSegmentPos;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getSegmentIndex() {
/* 642 */     return isMirrored() ? 1 : 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float floatIn) {
/* 648 */     if (source.m_269533_(DamageTypeTags.f_268738_)) {
/* 649 */       return super.m_6469_(source, floatIn);
/*     */     }
/* 651 */     if (isCompletelyInvulnerable()) {
/* 652 */       return false;
/*     */     }
/* 654 */     return super.m_6469_(source, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void splitCluster(BlockClusterEntity cluster, List<Entity> toAdd) {
/* 660 */     BlockClusterEntity split = cluster.splitAt(Direction.Axis.m_235688_(this.f_19796_));
/* 661 */     if (split != null) {
/*     */       
/* 663 */       m_9236_().m_7967_(split);
/* 664 */       if (this.f_19796_.m_188499_() && getParent() != null) {
/* 665 */         getParent().getTrackedEntities().trackEntityToConsume(split);
/*     */       } else {
/* 667 */         toAdd.add(split);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int loadRadius() {
/* 674 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropSmallMassCluster(int radius) {
/* 680 */     BlockClusterEntity cluster = ClusterBuilderHelper.buildSmallRandomDeathCluster(m_9236_(), this.f_19796_, radius);
/* 681 */     cluster.setSink(-1);
/* 682 */     cluster.m_146884_(m_20182_().m_82520_(this.f_19796_.m_188583_() * 5.0D, getUnmodifiedHeight() / 2.0D + this.f_19796_.m_188583_() * 5.0D, this.f_19796_.m_188583_() * 5.0D));
/* 683 */     cluster.m_20334_(this.f_19796_.m_188583_() * 0.4D, this.f_19796_.m_188583_() * 0.3D, this.f_19796_.m_188583_() * 0.4D);
/* 684 */     cluster.setRotationDelta(new Vec2(this.f_19796_.m_188503_(90) * 0.3F / 2.0F, this.f_19796_.m_188503_(90) * 0.3F / 2.0F));
/* 685 */     m_9236_().m_7967_(cluster);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropDeathClusters() {
/* 691 */     if (getDeathTime() > 10 && getDeathTime() % 10 == 0) {
/* 692 */       dropSmallMassCluster(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void searchForPlayingJukeboxes() {
/* 698 */     WitherStormEntity parent = getParent();
/* 699 */     getPlayingJukeboxes().clear();
/* 700 */     if (parent != null)
/*     */     {
/* 702 */       for (BlockPos pos : parent.getPlayingJukeboxes()) {
/* 703 */         getPlayingJukeboxes().add(pos);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void storePet(Entity entity) {
/* 710 */     WitherStormEntity parent = getParent();
/* 711 */     if (parent != null) {
/* 712 */       parent.storePet(entity);
/*     */     } else {
/* 714 */       super.storePet(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private class BodyController
/*     */     extends WitherStormBodyController {
/*     */     public BodyController() {
/* 721 */       super(WitherStormSegmentEntity.this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected float getYRotD() {
/* 727 */       return Mth.m_14177_(super.getYRotD() + WitherStormSegmentEntity.this.randomBodyRotAngleOffset);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\WitherStormSegmentEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */