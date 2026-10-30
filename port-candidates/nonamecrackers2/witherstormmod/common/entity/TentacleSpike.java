/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.TraceableEntity;
/*     */ import net.minecraft.world.level.Level;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ 
/*     */ public class TentacleSpike
/*     */   extends Entity implements TraceableEntity {
/*     */   @Nullable
/*     */   private UUID ownerUUID;
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*  24 */   private int lifeTicks = 22; private boolean sentSpikeEvent;
/*     */   private int warmupDelayTicks;
/*     */   private boolean clientSideAttackStarted;
/*     */   private float damageModifier;
/*     */   
/*     */   public TentacleSpike(EntityType<? extends TentacleSpike> type, Level level) {
/*  30 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   public TentacleSpike(Level level, double x, double y, double z, float angle, int warmupDelay, LivingEntity owner, float damageModifier) {
/*  35 */     this((EntityType<? extends TentacleSpike>)WitherStormModEntityTypes.TENTACLE_SPIKE.get(), level);
/*  36 */     this.warmupDelayTicks = warmupDelay;
/*  37 */     setOwner(owner);
/*  38 */     setYRot(angle * 57.295776F);
/*  39 */     setPos(x, y, z);
/*  40 */     this.damageModifier = damageModifier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwner(@Nullable LivingEntity entity) {
/*  50 */     this.owner = entity;
/*  51 */     this.ownerUUID = (entity == null) ? null : entity.getUUID();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/*  57 */     if (this.owner == null && this.ownerUUID != null) { Level level = level(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/*     */         
/*  59 */         Entity entity = serverLevel.getEntity(this.ownerUUID);
/*  60 */         if (entity instanceof LivingEntity)
/*  61 */           this.owner = (LivingEntity)entity;  }
/*     */        }
/*  63 */      return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditionalSaveData(CompoundTag tag) {
/*  69 */     this.warmupDelayTicks = tag.getInt("WarmupDelay");
/*  70 */     if (tag.hasUUID("Owner"))
/*  71 */       this.ownerUUID = tag.getUUID("Owner"); 
/*  72 */     this.damageModifier = tag.getFloat("DamageModifier");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addAdditionalSaveData(CompoundTag tag) {
/*  78 */     tag.putInt("WarmupDelay", this.warmupDelayTicks);
/*  79 */     if (this.ownerUUID != null)
/*  80 */       tag.putUUID("Owner", this.ownerUUID); 
/*  81 */     tag.putFloat("DamageModifier", this.damageModifier);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  87 */     super.tick();
/*     */     
/*  89 */     if ((level()).isClientSide) {
/*     */       
/*  91 */       if (this.clientSideAttackStarted) {
/*     */         
/*  93 */         this.lifeTicks--;
/*  94 */         if (this.lifeTicks == 20)
/*     */         {
/*  96 */           for (int i = 0; i < 12; i++)
/*     */           {
/*  98 */             double d0 = getX() + (this.random.nextDouble() * 2.0D - 1.0D) * getBbWidth() * 0.5D;
/*  99 */             double d1 = getY() + 0.05D + this.random.nextDouble() * 2.0D;
/* 100 */             double d2 = getZ() + (this.random.nextDouble() * 2.0D - 1.0D) * getBbWidth() * 0.5D;
/* 101 */             double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
/* 102 */             double d4 = 0.3D + this.random.nextDouble() * 0.3D;
/* 103 */             double d5 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
/* 104 */             level().addParticle((ParticleOptions)ParticleTypes.CRIT, d0, d1, d2, d3, d4, d5);
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/* 109 */     } else if (--this.warmupDelayTicks < 0) {
/*     */       
/* 111 */       if (this.warmupDelayTicks == -2)
/*     */       {
/* 113 */         for (LivingEntity entity : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(0.6D, 0.0D, 0.6D))) {
/* 114 */           dealDamageTo(entity);
/*     */         }
/*     */       }
/* 117 */       if (!this.sentSpikeEvent) {
/*     */         
/* 119 */         level().broadcastEntityEvent(this, (byte)4);
/* 120 */         this.sentSpikeEvent = true;
/*     */       } 
/*     */       
/* 123 */       if (--this.lifeTicks < 0) {
/* 124 */         discard();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void dealDamageTo(LivingEntity entity) {
/* 130 */     LivingEntity owner = getOwner();
/* 131 */     if (entity.isAlive() && !entity.isInvulnerable() && entity != owner)
/*     */     {
/* 133 */       if (owner == null) {
/*     */         
/* 135 */         entity.hurt(damageSources().magic(), 6.0F);
/*     */       }
/*     */       else {
/*     */         
/* 139 */         if (owner.isAlliedTo((Entity)entity)) {
/*     */           return;
/*     */         }
/* 142 */         entity.hurt(damageSources().indirectMagic(this, (Entity)owner), 6.0F + this.damageModifier);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEntityEvent(byte event) {
/* 150 */     super.handleEntityEvent(event);
/* 151 */     if (event == 4) {
/*     */       
/* 153 */       this.clientSideAttackStarted = true;
/* 154 */       if (!isSilent()) {
/* 155 */         level().playLocalSound(getX(), getY(), getZ(), (SoundEvent)WitherStormModSoundEvents.TENTACLE_SPIKE_STAB.get(), getSoundSource(), 1.0F, this.random.nextFloat() * 0.2F + 0.85F, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getAnimationProgress(float partialTick) {
/* 161 */     if (!this.clientSideAttackStarted)
/*     */     {
/* 163 */       return 0.0F;
/*     */     }
/*     */ 
/*     */     
/* 167 */     int i = this.lifeTicks - 2;
/* 168 */     return (i <= 0) ? 1.0F : (1.0F - (i - partialTick) / 20.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\TentacleSpike.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */