/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.tags.DamageTypeTags;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityDimensions;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobType;
/*     */ import net.minecraft.world.entity.PathfinderMob;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.control.BodyRotationControl;
/*     */ import net.minecraft.world.entity.ai.control.LookControl;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.monster.RangedAttackMob;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.WitherSkull;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.LookAtDistractionGoal;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.LookAtTargetGoal;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.NearestDistractionGoal;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.YAffectedLookRandomlyGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions;
/*     */ import nonamecrackers2.witherstormmod.common.util.ConditionalLookController;
/*     */ import nonamecrackers2.witherstormmod.common.util.EmptyBodyController;
/*     */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*     */ import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class WitherStormHeadEntity
/*     */   extends Monster
/*     */   implements WitherStormBase, RangedAttackMob {
/*  65 */   private static final EntityDataAccessor<Boolean> IS_ACTIVE = SynchedEntityData.m_135353_(WitherStormHeadEntity.class, EntityDataSerializers.f_135035_);
/*  66 */   private static final EntityDataAccessor<Boolean> IS_ROARING = SynchedEntityData.m_135353_(WitherStormHeadEntity.class, EntityDataSerializers.f_135035_);
/*  67 */   private static final EntityDataAccessor<Boolean> IS_BITING = SynchedEntityData.m_135353_(WitherStormHeadEntity.class, EntityDataSerializers.f_135035_);
/*  68 */   private static final EntityDataAccessor<Boolean> IS_HURT = SynchedEntityData.m_135353_(WitherStormHeadEntity.class, EntityDataSerializers.f_135035_);
/*     */   @Nullable
/*     */   private Vec3 distractedPos;
/*  71 */   private int nextRoar = 400 + this.f_19796_.m_188503_(600); private int distractedTime;
/*     */   private int roarTime;
/*  73 */   private int shootTime = 100;
/*     */   
/*     */   private int biteTime;
/*     */   private float mouthAnim;
/*     */   private float mouthAnimO;
/*     */   private float fadeAnimation;
/*     */   private float fadeAnimationO;
/*     */   private boolean isShaking;
/*     */   private float shakeAnim;
/*     */   private float shakeAnimO;
/*     */   private LookAtTargetGoal<WitherStormHeadEntity> lookGoal;
/*     */   private int specialDeathTime;
/*     */   
/*     */   public WitherStormHeadEntity(EntityType<? extends WitherStormHeadEntity> type, Level world) {
/*  87 */     super(type, world);
/*  88 */     m_21573_().m_7008_(true);
/*  89 */     this.f_19811_ = true;
/*  90 */     m_20242_(true);
/*  91 */     this.f_21365_ = (LookControl)new ConditionalLookController((Mob)this, entity -> false);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected BodyRotationControl m_7560_() {
/*  97 */     return (BodyRotationControl)new EmptyBodyController((Mob)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 103 */     super.m_8097_();
/* 104 */     this.f_19804_.m_135372_(IS_ACTIVE, Boolean.valueOf(true));
/* 105 */     this.f_19804_.m_135372_(IS_ROARING, Boolean.valueOf(false));
/* 106 */     this.f_19804_.m_135372_(IS_BITING, Boolean.valueOf(false));
/* 107 */     this.f_19804_.m_135372_(IS_HURT, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/* 113 */     this.f_21345_.m_25352_(0, new DoNothingGoal(this));
/* 114 */     this.f_21345_.m_25352_(1, (Goal)new LookAtDistractionGoal((Mob)this, 0));
/* 115 */     this.lookGoal = new LookAtTargetGoal((LivingEntity)this, 0, s -> Integer.valueOf(3));
/* 116 */     this.f_21345_.m_25352_(2, (Goal)this.lookGoal);
/* 117 */     this.f_21345_.m_25352_(3, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 12.0F));
/* 118 */     this.f_21345_.m_25352_(4, (Goal)new YAffectedLookRandomlyGoal((Mob)this));
/* 119 */     this.f_21346_.m_25352_(0, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
/* 120 */     this.f_21346_.m_25352_(1, (Goal)new NearestDistractionGoal((Mob)this, 0, WitherStormEntity.DISTRACTION_SELECTOR, 8));
/* 121 */     this.f_21346_.m_25352_(2, (Goal)new AttackGoal<>(this, LivingEntity.class, 100, true, false, WitherStormEntity.DESTROYER_LIVING_ENTITY_SELECTOR.and(entity -> !isATarget(entity))));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/* 126 */     return Monster.m_33035_()
/* 127 */       .m_22268_(Attributes.f_22276_, 60.0D)
/* 128 */       .m_22268_(Attributes.f_22277_, 40.0D)
/* 129 */       .m_22268_(Attributes.f_22279_, 0.0D)
/* 130 */       .m_22268_(Attributes.f_22284_, 8.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag compound) {
/* 136 */     compound.m_128379_("IsRoaring", isRoaring());
/* 137 */     compound.m_128405_("RoarTime", this.roarTime);
/*     */     
/* 139 */     super.m_7380_(compound);
/*     */     
/* 141 */     compound.m_128379_("IsActive", ((Boolean)this.f_19804_.m_135370_(IS_ACTIVE)).booleanValue());
/* 142 */     if (getDistractedPos(0) != null)
/* 143 */       compound.m_128365_("DistractedPos", (Tag)WitherStormModNBTUtil.writeVector3d(Objects.<Vec3>requireNonNull(getDistractedPos(0)))); 
/* 144 */     compound.m_128405_("DistractedTime", this.distractedTime);
/* 145 */     compound.m_128350_("YBodyRot", this.f_20883_);
/* 146 */     compound.m_128379_("IsHurt", isHurt());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag compound) {
/* 152 */     this.f_19804_.m_135381_(IS_ROARING, Boolean.valueOf(compound.m_128471_("IsRoaring")));
/* 153 */     this.roarTime = compound.m_128451_("RoarTime");
/*     */     
/* 155 */     super.m_7378_(compound);
/*     */     
/* 157 */     if (compound.m_128441_("IsActive"))
/* 158 */       this.f_19804_.m_135381_(IS_ACTIVE, Boolean.valueOf(compound.m_128471_("IsActive"))); 
/* 159 */     if (compound.m_128441_("DistractedPos"))
/* 160 */       setDistractedPos(0, WitherStormModNBTUtil.readVector3d(compound.m_128469_("DistractedPos"))); 
/* 161 */     this.distractedTime = compound.m_128451_("DistractedTime");
/* 162 */     if (compound.m_128441_("YBodyRot")) {
/*     */       
/* 164 */       this.f_20883_ = compound.m_128457_("YBodyRot");
/* 165 */       this.f_20884_ = this.f_20883_;
/*     */     } 
/* 167 */     if (compound.m_128441_("IsHurt")) {
/* 168 */       setHurt(compound.m_128471_("IsHurt"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_8107_() {
/* 174 */     super.m_8107_();
/*     */     
/* 176 */     if (this.distractedTime > 0) {
/*     */       
/* 178 */       this.distractedTime--;
/* 179 */       if (this.distractedTime == 0) {
/* 180 */         setDistractedPos(0, (Vec3)null);
/*     */       }
/*     */     } 
/* 183 */     if (!isDeadOrPlayingDead()) {
/*     */       
/* 185 */       if (!(m_9236_()).f_46443_) {
/*     */         
/* 187 */         if (this.nextRoar > 0) {
/*     */           
/* 189 */           this.nextRoar--;
/* 190 */           if (this.nextRoar == 0) {
/*     */             
/* 192 */             setRoar(false);
/* 193 */             this.nextRoar = 400 + this.f_19796_.m_188503_(600);
/* 194 */             this.roarTime = 40;
/*     */           } 
/*     */         } 
/*     */         
/* 198 */         if (this.roarTime > 0) {
/*     */           
/* 200 */           this.roarTime--;
/* 201 */           if (this.roarTime == 0) {
/* 202 */             disableRoar();
/*     */           }
/*     */         } 
/* 205 */         if (this.biteTime > 0) {
/*     */           
/* 207 */           this.biteTime--;
/* 208 */           if (this.biteTime == 0) {
/*     */             
/* 210 */             setBite(false);
/* 211 */             m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BITE.get(), m_6121_(), 1.0F);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 216 */       if (isHurt() && this.f_19797_ % 20 == 0 && this.shootTime > 60)
/*     */       {
/* 218 */         this.isShaking = true;
/*     */       }
/*     */ 
/*     */       
/* 222 */       if (isHurt())
/*     */       {
/* 224 */         if (this.shootTime > 0) {
/*     */           
/* 226 */           this.shootTime--;
/*     */           
/* 228 */           if (this.shootTime < 60) {
/*     */             
/* 230 */             LivingEntity entity = m_5448_();
/* 231 */             if (entity != null) {
/*     */               
/* 233 */               setLookAt(0, entity.m_20182_());
/* 234 */               this.isShaking = false;
/*     */             } 
/*     */           } 
/*     */           
/* 238 */           if (this.shootTime == 0) {
/*     */             
/* 240 */             shootSkullAtTarget();
/* 241 */             this.shootTime = 60 + this.f_19796_.m_188503_(40);
/* 242 */             this.isShaking = false;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/* 252 */     super.m_8119_();
/*     */     
/* 254 */     this.mouthAnimO = this.mouthAnim;
/* 255 */     if (!isBiting() && isRoaring()) {
/*     */       
/* 257 */       this.mouthAnim += (1.0F - this.mouthAnim) * 0.15F + 0.04F;
/* 258 */       if (this.mouthAnim > 2.0F) {
/* 259 */         this.mouthAnim = 2.0F;
/*     */       }
/* 261 */     } else if (isBiting()) {
/*     */       
/* 263 */       this.mouthAnim += (1.0F - this.mouthAnim) * 0.16F + 0.1F;
/* 264 */       if (this.mouthAnim > 1.4F) {
/* 265 */         this.mouthAnim = 1.4F;
/*     */       }
/*     */     } else {
/*     */       
/* 269 */       this.mouthAnim += -this.mouthAnim * 0.16F - 0.02F;
/* 270 */       if (this.mouthAnim < 0.0F) {
/* 271 */         this.mouthAnim = 0.0F;
/*     */       }
/*     */     } 
/* 274 */     this.fadeAnimationO = this.fadeAnimation;
/* 275 */     if (isPlayingDead()) {
/*     */       
/* 277 */       this.fadeAnimation += 1.0F + this.f_19796_.m_188501_() * 2.0F;
/* 278 */       if (this.fadeAnimation > 300.0F) {
/* 279 */         this.fadeAnimation = 300.0F;
/*     */       }
/*     */     } else {
/*     */       
/* 283 */       this.fadeAnimation -= 1.0F + this.f_19796_.m_188501_() * 2.0F;
/* 284 */       if (this.fadeAnimation < 0.0F) {
/* 285 */         this.fadeAnimation = 0.0F;
/*     */       }
/*     */     } 
/* 288 */     this.shakeAnimO = this.shakeAnim;
/* 289 */     if (this.isShaking) {
/*     */       
/* 291 */       this.shakeAnim += 0.02F + this.f_19796_.m_188501_() * 0.05F;
/* 292 */       if (this.shakeAnimO >= 2.0F) {
/*     */         
/* 294 */         this.shakeAnimO = 0.0F;
/* 295 */         this.shakeAnim = 0.0F;
/* 296 */         this.isShaking = false;
/*     */       } 
/*     */     } 
/*     */     
/* 300 */     if ((m_9236_()).f_46443_ && ((Boolean)WitherStormModConfig.CLIENT.tractorBeamParticles.get()).booleanValue())
/*     */     {
/* 302 */       if (tractorBeamActive(0))
/*     */       {
/* 304 */         for (int amount = 0; amount < 5; amount++) {
/*     */           
/* 306 */           float x = m_146909_();
/* 307 */           float y = this.f_20885_;
/* 308 */           Vec3 lookVec = getViewVector(x, y, this.f_19796_.m_188501_() * 200.0F);
/* 309 */           Vec3 headPos = getHeadPos(0);
/* 310 */           Vec3 pos = headPos.m_82549_(lookVec).m_82520_(0.0D, 1.5D, 0.0D);
/* 311 */           double distanceFromHead = Math.sqrt(pos.m_82557_(headPos));
/* 312 */           double distanceAllowed = distanceFromHead * 2.0D * 0.02D;
/* 313 */           double randX = this.f_19796_.m_188583_() * distanceAllowed;
/* 314 */           double randY = this.f_19796_.m_188583_() * distanceAllowed;
/* 315 */           double randZ = this.f_19796_.m_188583_() * distanceAllowed;
/* 316 */           pos = pos.m_82520_(randX, randY, randZ);
/* 317 */           Pair<Boolean, Integer> result = TractorBeamHelper.isInsideTractorBeam(pos, (LivingEntity)this, 4.0D);
/* 318 */           if (((Boolean)result.getFirst()).booleanValue()) {
/*     */             
/* 320 */             Vec3 delta = pos.m_82546_(headPos).m_82541_().m_82490_(-0.8D);
/* 321 */             if (WitherStormMod.isAprilFools() && ((Boolean)WitherStormModConfig.CLIENT.aprilFools.get()).booleanValue()) {
/* 322 */               m_9236_().m_6493_((ParticleOptions)ParticleTypes.f_123750_, true, pos.f_82479_, pos.f_82480_, pos.f_82481_, delta.f_82479_, delta.f_82480_, delta.f_82481_);
/*     */             } else {
/* 324 */               m_9236_().m_6493_((ParticleOptions)new TractorBeamParticleOptions(m_19879_(), ((Integer)result.getSecond()).intValue()), true, pos.f_82479_, pos.f_82480_, pos.f_82481_, delta.f_82479_, delta.f_82480_, delta.f_82481_);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6667_(@NotNull DamageSource source) {
/* 334 */     super.m_6667_(source);
/* 335 */     setRoar(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_6153_() {
/* 341 */     this.specialDeathTime++;
/* 342 */     if (!(m_9236_()).f_46443_) {
/*     */       
/* 344 */       m_146926_(m_146909_() - 1.0F);
/* 345 */       if (m_146909_() < -50.0F)
/* 346 */         m_146926_(-50.0F); 
/*     */     } 
/* 348 */     if (this.specialDeathTime > 120) {
/* 349 */       m_142687_(Entity.RemovalReason.KILLED);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_5997_(double deltaX, double deltaY, double deltaZ) {}
/*     */ 
/*     */   
/*     */   protected SoundEvent m_7515_() {
/* 358 */     return !isPlayingDead() ? (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_GROWL.get() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_7975_(@NotNull DamageSource source) {
/* 364 */     return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_HURT.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_8100_() {
/* 370 */     return 80 + this.f_19796_.m_188503_(40);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float m_6121_() {
/* 376 */     return 8.0F;
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
/*     */   protected void m_8024_() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual isPlayingDead : ()Z
/*     */     //   4: ifne -> 280
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual isHurt : ()Z
/*     */     //   11: ifne -> 280
/*     */     //   14: aload_0
/*     */     //   15: invokevirtual m_5448_ : ()Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   18: ifnull -> 263
/*     */     //   21: ldc2_w 0.2
/*     */     //   24: dstore_1
/*     */     //   25: aload_0
/*     */     //   26: invokevirtual m_5448_ : ()Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   29: astore_3
/*     */     //   30: aload_3
/*     */     //   31: invokevirtual m_20202_ : ()Lnet/minecraft/world/entity/Entity;
/*     */     //   34: astore #4
/*     */     //   36: aload_0
/*     */     //   37: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*     */     //   40: aload_3
/*     */     //   41: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*     */     //   44: invokevirtual m_82546_ : (Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;
/*     */     //   47: invokevirtual m_82541_ : ()Lnet/minecraft/world/phys/Vec3;
/*     */     //   50: dload_1
/*     */     //   51: invokevirtual m_82490_ : (D)Lnet/minecraft/world/phys/Vec3;
/*     */     //   54: astore #5
/*     */     //   56: aload #4
/*     */     //   58: instanceof net/minecraft/world/entity/LivingEntity
/*     */     //   61: ifeq -> 84
/*     */     //   64: aload #4
/*     */     //   66: checkcast net/minecraft/world/entity/LivingEntity
/*     */     //   69: astore #7
/*     */     //   71: getstatic nonamecrackers2/witherstormmod/common/entity/WitherStormEntity.DESTROYER_LIVING_ENTITY_SELECTOR : Ljava/util/function/Predicate;
/*     */     //   74: aload #7
/*     */     //   76: invokeinterface test : (Ljava/lang/Object;)Z
/*     */     //   81: ifeq -> 88
/*     */     //   84: iconst_1
/*     */     //   85: goto -> 89
/*     */     //   88: iconst_0
/*     */     //   89: istore #6
/*     */     //   91: aload_3
/*     */     //   92: invokevirtual m_20159_ : ()Z
/*     */     //   95: ifeq -> 131
/*     */     //   98: getstatic nonamecrackers2/witherstormmod/common/config/WitherStormModConfig.COMMON : Lnonamecrackers2/witherstormmod/common/config/WitherStormModConfig$CommonConfig;
/*     */     //   101: getfield shouldPickUpVehicles : Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;
/*     */     //   104: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   107: checkcast java/lang/Boolean
/*     */     //   110: invokevirtual booleanValue : ()Z
/*     */     //   113: ifeq -> 131
/*     */     //   116: iload #6
/*     */     //   118: ifeq -> 131
/*     */     //   121: aload #4
/*     */     //   123: aload #5
/*     */     //   125: invokevirtual m_20256_ : (Lnet/minecraft/world/phys/Vec3;)V
/*     */     //   128: goto -> 137
/*     */     //   131: aload_3
/*     */     //   132: aload #5
/*     */     //   134: invokevirtual m_20256_ : (Lnet/minecraft/world/phys/Vec3;)V
/*     */     //   137: aload_3
/*     */     //   138: instanceof net/minecraft/world/entity/player/Player
/*     */     //   141: ifeq -> 171
/*     */     //   144: getstatic nonamecrackers2/witherstormmod/common/init/WitherStormModPacketHandlers.MAIN : Lnet/minecraftforge/network/simple/SimpleChannel;
/*     */     //   147: getstatic net/minecraftforge/network/PacketDistributor.PLAYER : Lnet/minecraftforge/network/PacketDistributor;
/*     */     //   150: aload_3
/*     */     //   151: <illegal opcode> get : (Lnet/minecraft/world/entity/Entity;)Ljava/util/function/Supplier;
/*     */     //   156: invokevirtual with : (Ljava/util/function/Supplier;)Lnet/minecraftforge/network/PacketDistributor$PacketTarget;
/*     */     //   159: new nonamecrackers2/witherstormmod/common/packet/PlayerMotionMessage
/*     */     //   162: dup
/*     */     //   163: aload #5
/*     */     //   165: invokespecial <init> : (Lnet/minecraft/world/phys/Vec3;)V
/*     */     //   168: invokevirtual send : (Lnet/minecraftforge/network/PacketDistributor$PacketTarget;Ljava/lang/Object;)V
/*     */     //   171: aload_0
/*     */     //   172: invokevirtual m_20191_ : ()Lnet/minecraft/world/phys/AABB;
/*     */     //   175: aload_3
/*     */     //   176: invokevirtual m_20191_ : ()Lnet/minecraft/world/phys/AABB;
/*     */     //   179: invokevirtual m_82381_ : (Lnet/minecraft/world/phys/AABB;)Z
/*     */     //   182: ifeq -> 263
/*     */     //   185: aload_3
/*     */     //   186: instanceof net/minecraft/world/entity/player/Player
/*     */     //   189: ifeq -> 246
/*     */     //   192: aload_3
/*     */     //   193: checkcast net/minecraft/world/entity/player/Player
/*     */     //   196: astore #7
/*     */     //   198: aload #7
/*     */     //   200: invokevirtual m_21224_ : ()Z
/*     */     //   203: ifne -> 238
/*     */     //   206: aload #7
/*     */     //   208: invokevirtual m_6084_ : ()Z
/*     */     //   211: ifeq -> 238
/*     */     //   214: aload #7
/*     */     //   216: invokevirtual m_150110_ : ()Lnet/minecraft/world/entity/player/Abilities;
/*     */     //   219: getfield f_35934_ : Z
/*     */     //   222: ifne -> 238
/*     */     //   225: aload #7
/*     */     //   227: aload_0
/*     */     //   228: invokestatic witherStormAttack : (Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/damagesource/DamageSource;
/*     */     //   231: ldc_w 3.5
/*     */     //   234: invokevirtual m_6469_ : (Lnet/minecraft/world/damagesource/DamageSource;F)Z
/*     */     //   237: pop
/*     */     //   238: aload_0
/*     */     //   239: iconst_1
/*     */     //   240: invokevirtual setBite : (Z)V
/*     */     //   243: goto -> 263
/*     */     //   246: aload_3
/*     */     //   247: aload_0
/*     */     //   248: invokestatic witherStormAttackMob : (Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/damagesource/DamageSource;
/*     */     //   251: ldc_w 3.4028235E38
/*     */     //   254: invokevirtual m_6469_ : (Lnet/minecraft/world/damagesource/DamageSource;F)Z
/*     */     //   257: pop
/*     */     //   258: aload_0
/*     */     //   259: iconst_1
/*     */     //   260: invokevirtual setBite : (Z)V
/*     */     //   263: aload_0
/*     */     //   264: getfield f_19797_ : I
/*     */     //   267: bipush #80
/*     */     //   269: irem
/*     */     //   270: ifne -> 280
/*     */     //   273: aload_0
/*     */     //   274: ldc_w 10.0
/*     */     //   277: invokevirtual m_5634_ : (F)V
/*     */     //   280: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #382	-> 0
/*     */     //   #384	-> 14
/*     */     //   #386	-> 21
/*     */     //   #387	-> 25
/*     */     //   #388	-> 30
/*     */     //   #389	-> 36
/*     */     //   #390	-> 56
/*     */     //   #391	-> 91
/*     */     //   #392	-> 121
/*     */     //   #394	-> 131
/*     */     //   #395	-> 137
/*     */     //   #396	-> 144
/*     */     //   #397	-> 171
/*     */     //   #399	-> 185
/*     */     //   #401	-> 198
/*     */     //   #402	-> 225
/*     */     //   #403	-> 238
/*     */     //   #407	-> 246
/*     */     //   #408	-> 258
/*     */     //   #413	-> 263
/*     */     //   #414	-> 273
/*     */     //   #416	-> 280
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   71	13	7	living	Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   88	1	7	living	Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   198	48	7	player	Lnet/minecraft/world/entity/player/Player;
/*     */     //   25	238	1	speed	D
/*     */     //   30	233	3	target	Lnet/minecraft/world/entity/Entity;
/*     */     //   36	227	4	vehicle	Lnet/minecraft/world/entity/Entity;
/*     */     //   56	207	5	delta	Lnet/minecraft/world/phys/Vec3;
/*     */     //   91	172	6	flag	Z
/*     */     //   0	281	0	this	Lnonamecrackers2/witherstormmod/common/entity/WitherStormHeadEntity;
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
/*     */   public void setDistractedPos(int head, @Nullable Vec3 pos) {
/* 421 */     this.distractedPos = pos;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Vec3 getDistractedPos(int head) {
/* 427 */     return this.distractedPos;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void makeDistracted(Vec3 pos, int time, int head) {
/* 433 */     this.distractedTime = time;
/* 434 */     setDistractedPos(head, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoar(boolean screaming) {
/* 439 */     this.f_19804_.m_135381_(IS_ROARING, Boolean.valueOf(true));
/* 440 */     SoundEvent event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_ROAR.get();
/* 441 */     if (screaming)
/* 442 */       event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_HURT.get(); 
/* 443 */     m_5496_(event, m_6121_(), 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoarTime(int time) {
/* 448 */     this.roarTime = time;
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableRoar() {
/* 453 */     this.f_19804_.m_135381_(IS_ROARING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRoaring() {
/* 458 */     return ((Boolean)this.f_19804_.m_135370_(IS_ROARING)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBite(boolean biting) {
/* 463 */     if (biting)
/* 464 */       this.biteTime = 10; 
/* 465 */     this.f_19804_.m_135381_(IS_BITING, Boolean.valueOf(biting));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBiting() {
/* 470 */     return ((Boolean)this.f_19804_.m_135370_(IS_BITING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 476 */     if (source.m_269533_(DamageTypeTags.f_268738_)) {
/* 477 */       return super.m_6469_(source, amount);
/*     */     }
/* 479 */     if (!isPlayingDead() && !isHurt()) {
/*     */       
/* 481 */       if (!isRoaring()) {
/*     */         
/* 483 */         setRoar(true);
/* 484 */         this.roarTime = 20;
/*     */       } 
/* 486 */       boolean flag = super.m_6469_(source, amount);
/* 487 */       if (m_21223_() < m_21233_() / 1.5F)
/* 488 */         setHurt(true); 
/* 489 */       return flag;
/*     */     } 
/*     */     
/* 492 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_5830_() {
/* 498 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6043_() {
/* 504 */     this.f_20891_ = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(@NotNull MobEffectInstance effect, @Nullable Entity entity) {
/* 510 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/* 516 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6072_() {
/* 522 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7301_(@NotNull MobEffectInstance effect) {
/* 528 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_5789_() {
/* 534 */     return !isPlayingDead();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float m_6431_(@NotNull Pose pose, EntityDimensions size) {
/* 540 */     return size.f_20378_ / 1.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_5802_(@NotNull BlockPos pos) {}
/*     */ 
/*     */   
/*     */   public boolean m_6573_(@NotNull Player player) {
/* 548 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMouthAnimation(float partialTicks) {
/* 553 */     return Mth.m_14179_(partialTicks, this.mouthAnimO, this.mouthAnim);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6094_() {
/* 559 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6063_() {
/* 565 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDeadOrPlayingDead() {
/* 571 */     return (isPlayingDead() || m_21224_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPlayingDead() {
/* 577 */     return !((Boolean)this.f_19804_.m_135370_(IS_ACTIVE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActive(boolean active) {
/* 582 */     this.f_19804_.m_135381_(IS_ACTIVE, Boolean.valueOf(active));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMouthAnimation(int head, float partialTicks) {
/* 588 */     return Mth.m_14179_(partialTicks, this.mouthAnimO, this.mouthAnim);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrokenJawAnimation(int head, float partialTicks) {
/* 594 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFadeAnimation(float partialTicks) {
/* 600 */     return Mth.m_14179_(partialTicks, this.fadeAnimationO, this.fadeAnimation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFadeAnimation() {
/* 606 */     return this.fadeAnimation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTentacleAnimation(float partialTicks) {
/* 612 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadYRot(int head) {
/* 618 */     return this.f_20885_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadYRotO(int head) {
/* 624 */     return this.f_20886_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadXRot(int head) {
/* 630 */     return m_146909_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadXRotO(int head) {
/* 636 */     return this.f_19860_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getXBodyRot() {
/* 642 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getXBodyRotO() {
/* 648 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPosBehindBack(Vec3 pos) {
/* 654 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_147240_(double strength, double x, double z) {}
/*     */ 
/*     */   
/*     */   public boolean m_20068_() {
/* 662 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isATarget(LivingEntity entity) {
/* 667 */     for (WitherStormHeadEntity entity1 : m_9236_().m_45976_(WitherStormHeadEntity.class, m_20191_().m_82400_(m_21133_(Attributes.f_22277_)))) {
/*     */       
/* 669 */       if (!entity1.isHurt() && entity1.m_5448_() == entity)
/* 670 */         return true; 
/*     */     } 
/* 672 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHurt() {
/* 677 */     return isHeadInjured(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHeadInjured(int head) {
/* 683 */     return ((Boolean)this.f_19804_.m_135370_(IS_HURT)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHurt(boolean hurt) {
/* 688 */     this.f_19804_.m_135381_(IS_HURT, Boolean.valueOf(hurt));
/* 689 */     if (hurt) {
/*     */       
/* 691 */       this.f_21345_.m_25363_((Goal)this.lookGoal);
/*     */     }
/*     */     else {
/*     */       
/* 695 */       this.f_21345_.m_25363_((Goal)this.lookGoal);
/* 696 */       this.f_21345_.m_25352_(2, (Goal)this.lookGoal);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadShakeAnim(int head, float partialTicks) {
/* 703 */     float lerp = Mth.m_14036_(Mth.m_14179_(partialTicks, this.shakeAnimO, this.shakeAnim), 0.0F, 1.0F);
/* 704 */     return Mth.m_14031_(lerp * 3.1415927F) * Mth.m_14031_(lerp * 3.1415927F * 12.0F) * 0.05F * 3.1415927F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areOtherHeadsDisabled() {
/* 710 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getHeadPos(int head) {
/* 716 */     return m_20191_().m_82399_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalHeads() {
/* 722 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLookAt(int head, Vec3 pos, int steps) {
/* 728 */     if (pos != null) {
/* 729 */       this.f_21365_.m_24964_(pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public LivingEntity getTarget(int head) {
/* 735 */     return m_5448_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(int head, LivingEntity entity) {
/* 741 */     m_6710_(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tractorBeamActive(int head) {
/* 747 */     return (super.tractorBeamActive(head) && !isPlayingDead());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSee(int head, Entity entity) {
/* 753 */     return m_142582_(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6504_(@NotNull LivingEntity head, float p_33318_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void shootSkullAtTarget() {
/* 763 */     LivingEntity entity = m_5448_();
/* 764 */     if (entity != null) {
/*     */       
/* 766 */       Vec3 direction = entity.m_20182_().m_82546_(m_20182_()).m_82541_();
/* 767 */       WitherSkull witherSkull = new WitherSkull(m_9236_(), (LivingEntity)this, direction.f_82479_, direction.f_82480_, direction.f_82481_);
/* 768 */       m_9236_().m_7967_((Entity)witherSkull);
/* 769 */       if (m_217043_().m_188503_(16) == 1)
/* 770 */         witherSkull.m_37629_(true); 
/* 771 */       witherSkull.m_5496_(SoundEvents.f_12558_, 2.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class DoNothingGoal
/*     */     extends Goal
/*     */   {
/*     */     private final WitherStormHeadEntity entity;
/*     */     
/*     */     public DoNothingGoal(WitherStormHeadEntity entity) {
/* 781 */       m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
/* 782 */       this.entity = entity;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean m_8036_() {
/* 788 */       return this.entity.isPlayingDead();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class AttackGoal<T extends LivingEntity>
/*     */     extends NearestAttackableTargetGoal<T>
/*     */   {
/*     */     public AttackGoal(WitherStormHeadEntity entity, Class<T> clazz, int randomInterval, boolean mustSee, boolean mustReach, Predicate<LivingEntity> predicate) {
/* 796 */       super((Mob)entity, clazz, randomInterval, mustSee, mustReach, predicate);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     protected AABB m_7255_(double followRange) {
/* 802 */       return this.f_26135_.m_20191_().m_82400_(followRange);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\WitherStormHeadEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */