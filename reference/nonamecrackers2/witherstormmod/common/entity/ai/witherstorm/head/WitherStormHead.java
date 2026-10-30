/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.NotifyHeadInjuryMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.OnHeadAttackedMessage;
/*     */ import nonamecrackers2.witherstormmod.common.util.HeadConfiguration;
/*     */ import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class WitherStormHead
/*     */ {
/*     */   protected final WitherStormEntity storm;
/*     */   protected final int headIndex;
/*     */   private final boolean syncHeadRotations;
/*     */   protected float lerpXRot;
/*     */   protected float lerpYRot;
/*     */   protected float lerpXSteps;
/*     */   protected float lerpYSteps;
/*     */   protected float mouthAnim;
/*     */   protected float mouthAnimO;
/*     */   protected float jawBrokenAnimation;
/*     */   protected float jawBrokenAnimationO;
/*     */   protected boolean isHeadShaking;
/*     */   protected float headShakeAnim;
/*     */   protected float headShakeAnimO;
/*     */   @Nullable
/*  53 */   protected Vec3 headPos = Vec3.f_82478_; @Nullable
/*  54 */   protected Vec3 headPosO = Vec3.f_82478_;
/*     */   
/*     */   @Nullable
/*     */   protected Vec3 distractionPos;
/*     */   
/*     */   protected int distractedTime;
/*     */   
/*     */   @Nullable
/*     */   protected AABB box;
/*     */   
/*     */   protected int nextClusterPickup;
/*     */   protected int idleClusterPickup;
/*     */   protected int nextRoarTick;
/*     */   protected int roarTick;
/*     */   protected int biteTick;
/*     */   protected int nextShake;
/*  70 */   protected double tractorBeamCutoffDistance = -1.0D;
/*     */   
/*     */   public int nextHeadUpdate;
/*     */   
/*     */   public int idleHeadUpdates;
/*     */   
/*     */   protected int headHits;
/*     */   protected int requiredHits;
/*     */   protected int headHurtDuration;
/*     */   
/*     */   public WitherStormHead(WitherStormEntity storm, int headIndex, boolean syncHeadRotations) {
/*  81 */     if (headIndex >= storm.getTotalHeads())
/*  82 */       throw new IllegalArgumentException("Head index too large! Maximum amount of heads allowed is " + storm.getTotalHeads()); 
/*  83 */     this.storm = storm;
/*  84 */     this.headIndex = headIndex;
/*  85 */     this.syncHeadRotations = syncHeadRotations;
/*  86 */     this.requiredHits = getRandomHitCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(int phase) {
/*  91 */     this.requiredHits = getRandomHitCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean syncHeadRotations() {
/*  96 */     return this.syncHeadRotations;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 101 */     return this.headIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag save() {
/* 106 */     CompoundTag tag = new CompoundTag();
/* 107 */     tag.m_128379_("IsRoaring", isRoaring());
/* 108 */     tag.m_128405_("RoaringTime", getRoarTicks());
/* 109 */     if (getDistractedPos() != null)
/* 110 */       tag.m_128365_("DistractedPos", (Tag)WitherStormModNBTUtil.writeVector3d(getDistractedPos())); 
/* 111 */     tag.m_128405_("DistractedTime", getDistractedTime());
/* 112 */     tag.m_128405_("AttackCooldown", getHeadInjureAttemptCooldown());
/* 113 */     tag.m_128405_("InjuryTime", getHeadInjuryTicks());
/* 114 */     tag.m_128405_("Hits", getHeadHits());
/* 115 */     return tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundTag tag) {
/* 120 */     if (tag.m_128441_("IsRoaring"))
/* 121 */       setRoar(tag.m_128471_("IsRoaring")); 
/* 122 */     if (tag.m_128441_("RoaringTime"))
/* 123 */       setRoarTicks(tag.m_128451_("RoaringTime")); 
/* 124 */     if (tag.m_128441_("DistractedPos"))
/* 125 */       setDistractedPos(WitherStormModNBTUtil.readVector3d(tag.m_128469_("DistractedPos"))); 
/* 126 */     if (tag.m_128441_("DistractedTime"))
/* 127 */       this.distractedTime = tag.m_128451_("DistractedTime"); 
/* 128 */     if (tag.m_128441_("AttackCooldown"))
/* 129 */       setHeadInjureAttemptCooldown(tag.m_128451_("AttackCooldown")); 
/* 130 */     if (tag.m_128441_("InjuryTime"))
/* 131 */       setHeadInjuryTicks(tag.m_128451_("InjuryTime")); 
/* 132 */     if (tag.m_128441_("Hits")) {
/* 133 */       setHeadHitCount(tag.m_128451_("Hits"));
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canShootNormalWitherSkulls() {
/* 158 */     return !this.storm.tractorBeamActive(this.headIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canShootFlamingSkull() {
/* 163 */     return this.storm.tractorBeamActive(this.headIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadXRot(float partialTicks) {
/* 168 */     return Mth.m_14179_(partialTicks, getHeadXRotO(), getHeadXRot());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadYRot(float partialTicks) {
/* 173 */     return Mth.m_14179_(partialTicks, getHeadYRotO(), getHeadYRot());
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getHeadPos() {
/* 178 */     return this.headPos;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getHeadPosO() {
/* 183 */     return this.headPosO;
/*     */   }
/*     */ 
/*     */   
/*     */   public void baseTick(HeadConfiguration config) {
/* 188 */     this.headPosO = this.headPos;
/* 189 */     this.headPos = calculateHeadPosition(config);
/* 190 */     float size = (this.storm.getPhase() > 3) ? 3.0F : 0.5F;
/* 191 */     this.box = new AABB(this.headPos.f_82479_ - size, this.headPos.f_82480_ - size, this.headPos.f_82481_ - size, this.headPos.f_82479_ + size, this.headPos.f_82480_ + size, this.headPos.f_82481_ + size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 196 */     handleAnimations();
/*     */ 
/*     */     
/* 199 */     if (this.lerpXSteps > 0.0F) {
/*     */       
/* 201 */       float rot = getHeadXRot();
/* 202 */       float rotNew = (float)(rot + Mth.m_14175_(this.lerpXRot - rot) / this.lerpXSteps);
/* 203 */       setHeadXRot(rotNew);
/* 204 */       this.lerpXSteps--;
/*     */     } 
/*     */     
/* 207 */     if (this.lerpYSteps > 0.0F) {
/*     */       
/* 209 */       float rot = getHeadYRot();
/* 210 */       float rotNew = (float)(rot + Mth.m_14175_(this.lerpYRot - rot) / this.lerpYSteps);
/* 211 */       setHeadYRot(rotNew);
/* 212 */       this.lerpYSteps--;
/*     */     } 
/*     */     
/* 215 */     if (!this.storm.isOnDistantRenderer()) {
/*     */       
/* 217 */       float x = getHeadXRot();
/* 218 */       float y = getHeadYRot();
/* 219 */       Vec3 end = this.headPos.m_82549_(this.storm.getViewVector(x, y, 250.0F));
/* 220 */       BlockHitResult hitResult = this.storm.m_9236_().m_45547_(new ClipContext(this.headPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
/* 221 */       if (hitResult.m_6662_() == HitResult.Type.BLOCK) {
/* 222 */         this.tractorBeamCutoffDistance = this.headPos.m_82554_(hitResult.m_82450_());
/*     */       } else {
/* 224 */         this.tractorBeamCutoffDistance = -1.0D;
/*     */       } 
/*     */     } 
/* 227 */     if (this.headHurtDuration > 0) {
/* 228 */       this.headHurtDuration--;
/*     */     }
/*     */   }
/*     */   
/*     */   public void doAi() {
/* 233 */     doHeadLookLogic();
/*     */     
/* 235 */     if (getHeadInjuryTicks() > 0) {
/*     */       
/* 237 */       decreaseHeadInjuryTicks();
/* 238 */       if (getHeadInjuryTicks() == 0 && !this.storm.isDeadOrPlayingDead())
/* 239 */         this.storm.playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TRACTOR_BEAM_ACTIVATES.get(), this.headIndex, this.storm.m_6121_() + 2.5F, 1.0F); 
/*     */     } 
/* 241 */     if (getHeadInjureAttemptCooldown() > 0) {
/* 242 */       decreaseInjureAttemptCooldown();
/*     */     }
/* 244 */     if (isHeadInjured() && !this.storm.isDeadOrPlayingDead())
/*     */     {
/* 246 */       if (this.nextShake > 0) {
/*     */         
/* 248 */         this.nextShake--;
/* 249 */         if (this.nextShake == 0) {
/* 250 */           this.isHeadShaking = true;
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void doServerAi() {
/* 257 */     if (this.storm.tractorBeamActive(this.headIndex))
/*     */     {
/* 259 */       if (this.storm.f_19797_ >= this.nextClusterPickup) {
/*     */         
/* 261 */         if (this.storm instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity) {
/* 262 */           this.nextClusterPickup = this.storm.f_19797_ + 12;
/* 263 */         } else if (this.storm.getPhase() <= 2) {
/* 264 */           this.nextClusterPickup = this.storm.f_19797_ + 24;
/* 265 */         } else if (this.storm.getPhase() == 3) {
/* 266 */           this.nextClusterPickup = this.storm.f_19797_ + 15;
/* 267 */         } else if (this.storm.getPhase() == 4) {
/* 268 */           this.nextClusterPickup = this.storm.f_19797_ + 5 + this.storm.m_217043_().m_188503_(20);
/* 269 */         } else if (this.storm.getPhase() == 5) {
/* 270 */           this.nextClusterPickup = this.storm.f_19797_ + 5 + this.storm.m_217043_().m_188503_(15);
/* 271 */         } else if (this.storm.getPhase() >= 6) {
/* 272 */           this.nextClusterPickup = this.storm.f_19797_ + this.storm.m_217043_().m_188503_(15);
/*     */         } else {
/* 274 */           this.nextClusterPickup = this.storm.f_19797_ + 45;
/* 275 */         }  this.idleClusterPickup++;
/* 276 */         float x = getHeadXRot();
/* 277 */         float y = getHeadYRot();
/* 278 */         if (((Boolean)WitherStormModConfig.SERVER.tractorBeamClusterPickUp.get()).booleanValue())
/*     */         {
/* 280 */           if (this.storm instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity) {
/* 281 */             this.storm.createClusterFromLook(x, y, (int)Math.max(1.0D, Math.min(1.25D, 1.0D + 0.125D * this.storm.m_217043_().m_188583_())), this.headIndex);
/*     */           } else {
/* 283 */             this.storm.createClusterFromLook(x, y, (int)this.storm.getClusterRadius(), this.headIndex);
/*     */           }  } 
/* 285 */         if (((Boolean)WitherStormModConfig.SERVER.tractorBeamsRemoveFluids.get()).booleanValue())
/*     */         {
/* 287 */           this.storm.removeFluidFromLook(x, y, this.headIndex);
/*     */         }
/* 289 */         this.idleClusterPickup = 0;
/*     */       } 
/*     */     }
/*     */     
/* 293 */     if (this.storm.f_19797_ >= this.nextHeadUpdate) {
/*     */       
/* 295 */       if (this.storm.getPhase() < 4) {
/* 296 */         this.nextHeadUpdate = this.storm.f_19797_ + 10 + this.storm.m_217043_().m_188503_(10);
/*     */       } else {
/* 298 */         this.nextHeadUpdate = this.storm.f_19797_ + 1200 + this.storm.m_217043_().m_188503_(120);
/* 299 */       }  int k3 = this.idleHeadUpdates;
/* 300 */       this.idleHeadUpdates++;
/* 301 */       if (k3 > 15) {
/*     */         
/* 303 */         if (canShootNormalWitherSkulls()) {
/*     */           
/* 305 */           Vec3 pos = getHeadPos();
/* 306 */           double d0 = Mth.m_216263_(this.storm.m_217043_(), pos.f_82479_ - 10.0D, pos.f_82481_ + 10.0D);
/* 307 */           double d1 = Mth.m_216263_(this.storm.m_217043_(), pos.f_82480_ - 5.0D, pos.f_82480_ + 5.0D);
/* 308 */           double d2 = Mth.m_216263_(this.storm.m_217043_(), pos.f_82481_ - 10.0D, pos.f_82481_ + 10.0D);
/* 309 */           this.storm.performRangedAttack(this.headIndex, d0, d1, d2, true);
/*     */         } 
/* 311 */         this.idleHeadUpdates = 0;
/*     */       } 
/*     */       
/* 314 */       if (getTarget() != null) {
/*     */         
/* 316 */         if (canShootNormalWitherSkulls())
/* 317 */           this.storm.performRangedAttack(this.headIndex, getTarget()); 
/* 318 */         if (this.storm.getPhase() < 4) {
/* 319 */           this.nextHeadUpdate = this.storm.f_19797_ + 40 + this.storm.m_217043_().m_188503_(20);
/*     */         } else {
/* 321 */           this.nextHeadUpdate = this.storm.f_19797_ + 1800 + this.storm.m_217043_().m_188503_(160);
/* 322 */         }  this.idleHeadUpdates = 0;
/*     */       }
/*     */       else {
/*     */         
/* 326 */         this.nextHeadUpdate = this.storm.f_19797_ + 40 + this.storm.m_217043_().m_188503_(20);
/*     */       } 
/*     */     } 
/*     */     
/* 330 */     if (this.nextRoarTick == 0) {
/* 331 */       this.nextRoarTick = this.storm.f_19797_ + 200 + this.storm.m_217043_().m_188503_(200);
/*     */     }
/* 333 */     if (this.storm.f_19797_ > this.nextRoarTick) {
/*     */       
/* 335 */       if (canShootFlamingSkull() && !this.storm.isAttractingFormidibomb()) {
/*     */         
/* 337 */         Vec3 view = this.storm.getViewVector(getHeadXRot(), getHeadYRot(), 1.0F);
/* 338 */         Vec3 headPos = getHeadPos();
/* 339 */         this.storm.spawnFlamingWitherSkull(this.headIndex, view.f_82479_ + headPos.f_82479_, view.f_82480_ + headPos.f_82480_, view.f_82481_ + headPos.f_82481_);
/*     */       } 
/*     */       
/* 342 */       doRoar(this.storm.isHeadInjured(this.headIndex));
/*     */       
/* 344 */       int min = ((Integer)WitherStormModConfig.SERVER.minimumRoarInterval.get()).intValue() * 20;
/* 345 */       int max = ((Integer)WitherStormModConfig.SERVER.maximumRoarInterval.get()).intValue() * 20;
/* 346 */       int randomMax = max - min;
/* 347 */       int random = 0;
/* 348 */       if (randomMax > 0)
/* 349 */         random = this.storm.m_217043_().m_188503_(randomMax); 
/* 350 */       this.nextRoarTick = this.storm.f_19797_ + min + random;
/*     */     } 
/*     */     
/* 353 */     if (isRoaring()) {
/*     */       
/* 355 */       this.roarTick++;
/* 356 */       if (this.roarTick > 40) {
/*     */         
/* 358 */         setRoar(false);
/* 359 */         this.roarTick = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 363 */     if (isBiting()) {
/*     */       
/* 365 */       this.biteTick++;
/* 366 */       if (this.biteTick > 10) {
/*     */         
/* 368 */         setBiting(false);
/* 369 */         this.biteTick = 0;
/* 370 */         this.storm.playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BITE.get(), this.headIndex, Math.max(2.0F, this.storm.m_6121_()), 1.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 374 */     if (this.distractedTime > 0) {
/*     */       
/* 376 */       this.distractedTime--;
/* 377 */       if (this.distractedTime <= 0) {
/* 378 */         setDistractedPos(null);
/*     */       }
/*     */     } 
/* 381 */     if (isHeadInjured()) {
/*     */       
/* 383 */       this.storm.getRemovableGoalsManager().removeGoals(WitherStormEntity.REMOVABLE_LOOK_GOALS[this.headIndex], this.storm.getGoalSelectorForHead(this.headIndex));
/* 384 */       this.storm.getRemovableGoalsManager().removeGoals(WitherStormEntity.REMOVABLE_TARGET_GOALS[this.headIndex], this.storm.getTargetSelectorForHead(this.headIndex));
/*     */     }
/*     */     else {
/*     */       
/* 388 */       this.storm.getRemovableGoalsManager().putGoals(WitherStormEntity.REMOVABLE_LOOK_GOALS[this.headIndex], this.storm.getGoalSelectorForHead(this.headIndex));
/* 389 */       this.storm.getRemovableGoalsManager().putGoals(WitherStormEntity.REMOVABLE_TARGET_GOALS[this.headIndex], this.storm.getTargetSelectorForHead(this.headIndex));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 calculateHeadPosition(HeadConfiguration config) {
/* 395 */     float yBodyRot = (this.storm.f_20883_ + 180.0F) * 0.017453292F;
/* 396 */     float yBodyRot90 = (this.storm.f_20883_ + 270.0F) * 0.017453292F;
/* 397 */     float xBodyRot = -(this.storm.xBodyRot + 270.0F) * 0.017453292F;
/* 398 */     Vec3 headOffset = config.getOffsetForHead(this.headIndex);
/* 399 */     double staticX = headOffset.f_82479_;
/* 400 */     double staticY = headOffset.f_82480_;
/* 401 */     double staticZ = headOffset.f_82481_;
/* 402 */     double xOffset = Mth.m_14089_(yBodyRot) * staticX;
/* 403 */     double zOffset = Mth.m_14031_(yBodyRot) * staticX;
/* 404 */     float offset = (float)Mth.m_14136_(staticZ, staticY);
/* 405 */     double rawX = (Mth.m_14089_(xBodyRot + offset) * Mth.m_14089_(yBodyRot90));
/* 406 */     double rawY = Mth.m_14031_(xBodyRot + offset);
/* 407 */     double rawZ = (Mth.m_14089_(xBodyRot + offset) * Mth.m_14031_(yBodyRot90));
/* 408 */     double sqrt = Math.sqrt(staticZ * staticZ + staticY * staticY);
/* 409 */     double x = xOffset + this.storm.m_20185_() + rawX * sqrt;
/* 410 */     double y = this.storm.m_20186_() + rawY * sqrt;
/* 411 */     double z = zOffset + this.storm.m_20189_() + rawZ * sqrt;
/* 412 */     return new Vec3(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handleAnimations() {
/* 417 */     this.mouthAnimO = this.mouthAnim;
/* 418 */     if (!isBiting() && isRoaring()) {
/*     */       
/* 420 */       this.mouthAnim += (1.0F - this.mouthAnim) * 0.15F + 0.04F;
/* 421 */       if (this.mouthAnim > 2.0F) {
/* 422 */         this.mouthAnim = 2.0F;
/*     */       }
/* 424 */     } else if (isBiting()) {
/*     */       
/* 426 */       this.mouthAnim += (1.0F - this.mouthAnim) * 0.16F + 0.1F;
/* 427 */       if (this.mouthAnim > 1.4F) {
/* 428 */         this.mouthAnim = 1.4F;
/*     */       }
/*     */     } else {
/*     */       
/* 432 */       this.mouthAnim += -this.mouthAnim * 0.16F - 0.02F;
/* 433 */       if (this.mouthAnim < 0.0F) {
/* 434 */         this.mouthAnim = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 438 */     this.jawBrokenAnimationO = this.jawBrokenAnimation;
/* 439 */     if (this.storm.m_20096_() && this.storm.isDeadOrPlayingDead()) {
/*     */       
/* 441 */       this.jawBrokenAnimation += (1.0F - this.jawBrokenAnimation) * 0.2F + 0.05F;
/* 442 */       if (this.jawBrokenAnimation > 1.5F) {
/* 443 */         this.jawBrokenAnimation = 1.5F;
/*     */       }
/*     */     } else {
/*     */       
/* 447 */       this.jawBrokenAnimation += -this.jawBrokenAnimation * 0.2F - 0.05F;
/* 448 */       if (this.jawBrokenAnimation < 0.0F) {
/* 449 */         this.jawBrokenAnimation = 0.0F;
/*     */       }
/*     */     } 
/* 452 */     this.headShakeAnimO = this.headShakeAnim;
/* 453 */     if (this.isHeadShaking) {
/*     */       
/* 455 */       this.headShakeAnim += 0.02F + this.storm.m_217043_().m_188501_() * 0.05F;
/* 456 */       if (this.headShakeAnimO >= 2.0F) {
/*     */         
/* 458 */         this.headShakeAnimO = 0.0F;
/* 459 */         this.headShakeAnim = 0.0F;
/* 460 */         this.isHeadShaking = false;
/* 461 */         this.nextShake = 20 + this.storm.m_217043_().m_188503_(20);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRoaring() {
/* 468 */     return ((Boolean)this.storm.m_20088_().m_135370_(HeadManager.HEAD_ROARS.get(this.headIndex))).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoarTicks(int ticks) {
/* 473 */     this.roarTick = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRoarTicks() {
/* 478 */     return this.roarTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBiting() {
/* 483 */     return ((Boolean)this.storm.m_20088_().m_135370_(HeadManager.HEADS_BITING.get(this.headIndex))).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoar(boolean flag) {
/* 488 */     this.storm.m_20088_().m_135381_(HeadManager.HEAD_ROARS.get(this.headIndex), Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBiting(boolean flag) {
/* 493 */     this.storm.m_20088_().m_135381_(HeadManager.HEADS_BITING.get(this.headIndex), Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRoar(boolean screaming) {
/* 498 */     setRoar(true);
/* 499 */     SoundEvent event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_ROAR.get();
/* 500 */     if (screaming)
/* 501 */       event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_HURT.get(); 
/* 502 */     if (this.storm.areOtherHeadsDisabled() || (this.storm.getPhase() < 4 && this.storm.getPhase() > 1)) {
/*     */       
/* 504 */       if (this.headIndex == 0) {
/* 505 */         this.storm.playSound(event, this.headIndex, Math.max(6.0F, this.storm.m_6121_() + 2.5F), 1.0F);
/*     */       }
/* 507 */     } else if (this.storm.getPhase() > 3 && !this.storm.areOtherHeadsDisabled()) {
/*     */       
/* 509 */       this.storm.playSound(event, this.headIndex, Math.max(6.0F, this.storm.m_6121_() + 2.5F), 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startBiting() {
/* 515 */     this.biteTick = 0;
/* 516 */     this.storm.m_20088_().m_135381_(HeadManager.HEADS_BITING.get(this.headIndex), Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Vec3 getDistractedPos() {
/* 521 */     return this.distractionPos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDistractedPos(@Nullable Vec3 pos) {
/* 526 */     this.distractionPos = pos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeDistracted(Vec3 pos, int time) {
/* 531 */     this.distractedTime = time;
/* 532 */     setDistractedPos(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void lerpHeadTo(float lerpToX, float lerpToY, float steps) {
/* 537 */     this.lerpXRot = lerpToX;
/* 538 */     this.lerpYRot = lerpToY;
/* 539 */     this.lerpXSteps = steps;
/* 540 */     this.lerpYSteps = steps;
/*     */   }
/*     */ 
/*     */   
/*     */   public void lerpHeadXTo(float lerpToX, float steps) {
/* 545 */     this.lerpXRot = lerpToX;
/* 546 */     this.lerpXSteps = steps;
/*     */   }
/*     */ 
/*     */   
/*     */   public void lerpHeadYTo(float lerpToY, float steps) {
/* 551 */     this.lerpYRot = lerpToY;
/* 552 */     this.lerpYSteps = steps;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDistractedTime() {
/* 557 */     return this.distractedTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNextRoarTick() {
/* 562 */     return this.nextRoarTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNextRoarTick(int ticks) {
/* 567 */     this.nextRoarTick = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMouthAnimation(float partialTicks) {
/* 572 */     return Mth.m_14179_(partialTicks, this.mouthAnimO, this.mouthAnim);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBrokenJawAnimation(float partialTicks) {
/* 577 */     return Mth.m_14179_(partialTicks, this.jawBrokenAnimationO, this.jawBrokenAnimation);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRollAngle(float partialTicks) {
/* 582 */     float lerp = Mth.m_14036_(Mth.m_14179_(partialTicks, this.headShakeAnimO, this.headShakeAnim), 0.0F, 1.0F);
/* 583 */     return Mth.m_14031_(lerp * 3.1415927F) * Mth.m_14031_(lerp * 3.1415927F * 12.0F) * 0.05F * 3.1415927F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTractorBeamCutoff() {
/* 588 */     return this.tractorBeamCutoffDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSee(Entity entity) {
/* 593 */     Vec3 pos = getHeadPos();
/* 594 */     Vec3 entityPos = entity.m_20299_(1.0F);
/* 595 */     if (entity.m_9236_() != this.storm.m_9236_())
/* 596 */       return false; 
/* 597 */     return (this.storm.m_9236_().m_45547_(new ClipContext(pos, entityPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this.storm)).m_6662_() == HitResult.Type.MISS);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getRandomHitCount() {
/* 602 */     if (this.storm.getPhase() > 3) {
/* 603 */       return 3 + this.storm.m_217043_().m_188503_(3);
/*     */     }
/* 605 */     return 1 + this.storm.m_217043_().m_188503_(2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeadInjureAttemptCooldown() {
/* 610 */     return ((Integer)this.storm.m_20088_().m_135370_(HeadManager.INJURE_ATTEMPT_COOLDOWN.get(this.headIndex))).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadInjureAttemptCooldown(int amount) {
/* 615 */     this.storm.m_20088_().m_135381_(HeadManager.INJURE_ATTEMPT_COOLDOWN.get(this.headIndex), Integer.valueOf(amount));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeadInjuryTicks() {
/* 620 */     return ((Integer)this.storm.m_20088_().m_135370_(HeadManager.HURT_HEAD_TIME.get(this.headIndex))).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadInjuryTicks(int amount) {
/* 625 */     this.storm.m_20088_().m_135381_(HeadManager.HURT_HEAD_TIME.get(this.headIndex), Integer.valueOf(amount));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeadHits() {
/* 630 */     return this.headHits;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadHitCount(int amount) {
/* 635 */     this.headHits = amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void decreaseHeadInjuryTicks() {
/* 640 */     setHeadInjuryTicks(getHeadInjuryTicks() - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void decreaseInjureAttemptCooldown() {
/* 645 */     setHeadInjureAttemptCooldown(getHeadInjureAttemptCooldown() - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHeadInjured() {
/* 650 */     return (getHeadInjuryTicks() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeadHurtDuration() {
/* 655 */     return this.headHurtDuration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void hurt(@Nullable Entity entity, int injuryTime) {
/* 660 */     setHeadInjuryTicks(injuryTime);
/* 661 */     doRoar(true);
/* 662 */     Vec3 view = this.storm.getViewVector(getHeadXRot(), getHeadYRot(), 1.0F);
/* 663 */     Vec3 headPos = getHeadPos();
/* 664 */     this.storm.spawnBlueFlamingWitherSkull(this.headIndex, view.f_82479_ + headPos.f_82479_, view.f_82480_ + headPos.f_82480_, view.f_82481_ + headPos.f_82481_);
/* 665 */     setRoarTicks(20);
/* 666 */     setHeadInjureAttemptCooldown(40);
/* 667 */     if (!this.storm.m_9236_().m_5776_()) {
/*     */       
/* 669 */       this.requiredHits = getRandomHitCount();
/* 670 */       this.headHits = 0;
/*     */       
/* 672 */       NotifyHeadInjuryMessage message = new NotifyHeadInjuryMessage(this.storm, this.headIndex);
/* 673 */       ResourceKey<Level> dimension = this.storm.m_9236_().m_46472_();
/* 674 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> dimension), message);
/*     */       
/* 676 */       if (entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity; if (this.storm.alreadyATarget(entity, true)) {
/*     */           
/* 678 */           entity.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.makeInvulnerable(((Integer)WitherStormModConfig.SERVER.headEscapeTime.get()).intValue() * 20 + player.m_217043_().m_188503_(80)));
/* 679 */           WitherStormModCriteriaTriggers.ESCAPE_STORM.trigger(player, this.storm);
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean checkAndCountAttack() {
/* 686 */     if (((Boolean)WitherStormModConfig.SERVER.canAttackHeads.get()).booleanValue()) {
/*     */       
/* 688 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.storm), new OnHeadAttackedMessage(this.storm.m_19879_(), this.headIndex));
/* 689 */       this.headHits++;
/* 690 */       if (this.headHits >= this.requiredHits)
/*     */       {
/* 692 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 696 */       doRoar(true);
/* 697 */       setRoarTicks(20);
/* 698 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 703 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHeadAttackedOnClient() {
/* 709 */     this.headHurtDuration = 10;
/* 710 */     this.isHeadShaking = true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AABB getBoundingBox() {
/* 715 */     return this.box;
/*     */   }
/*     */   
/*     */   public abstract float getHeadXRot();
/*     */   
/*     */   public abstract float getHeadYRot();
/*     */   
/*     */   public abstract float getHeadXRotO();
/*     */   
/*     */   public abstract float getHeadYRotO();
/*     */   
/*     */   public abstract void setHeadXRot(float paramFloat);
/*     */   
/*     */   public abstract void setHeadYRot(float paramFloat);
/*     */   
/*     */   @Nullable
/*     */   public abstract LivingEntity getTarget();
/*     */   
/*     */   public abstract void setTarget(@Nullable LivingEntity paramLivingEntity);
/*     */   
/*     */   public abstract void setLookPos(@Nullable Vec3 paramVec3, int paramInt);
/*     */   
/*     */   public abstract void doHeadLookLogic();
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\head\WitherStormHead.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */