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
/*  38 */     m_146922_(angle * 57.295776F);
/*  39 */     m_6034_(x, y, z);
/*  40 */     this.damageModifier = damageModifier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwner(@Nullable LivingEntity entity) {
/*  50 */     this.owner = entity;
/*  51 */     this.ownerUUID = (entity == null) ? null : entity.m_20148_();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/*  57 */     if (this.owner == null && this.ownerUUID != null) { Level level = m_9236_(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/*     */         
/*  59 */         Entity entity = serverLevel.m_8791_(this.ownerUUID);
/*  60 */         if (entity instanceof LivingEntity)
/*  61 */           this.owner = (LivingEntity)entity;  }
/*     */        }
/*  63 */      return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7378_(CompoundTag tag) {
/*  69 */     this.warmupDelayTicks = tag.m_128451_("WarmupDelay");
/*  70 */     if (tag.m_128403_("Owner"))
/*  71 */       this.ownerUUID = tag.m_128342_("Owner"); 
/*  72 */     this.damageModifier = tag.m_128457_("DamageModifier");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7380_(CompoundTag tag) {
/*  78 */     tag.m_128405_("WarmupDelay", this.warmupDelayTicks);
/*  79 */     if (this.ownerUUID != null)
/*  80 */       tag.m_128362_("Owner", this.ownerUUID); 
/*  81 */     tag.m_128350_("DamageModifier", this.damageModifier);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  87 */     super.m_8119_();
/*     */     
/*  89 */     if ((m_9236_()).f_46443_) {
/*     */       
/*  91 */       if (this.clientSideAttackStarted) {
/*     */         
/*  93 */         this.lifeTicks--;
/*  94 */         if (this.lifeTicks == 20)
/*     */         {
/*  96 */           for (int i = 0; i < 12; i++)
/*     */           {
/*  98 */             double d0 = m_20185_() + (this.f_19796_.m_188500_() * 2.0D - 1.0D) * m_20205_() * 0.5D;
/*  99 */             double d1 = m_20186_() + 0.05D + this.f_19796_.m_188500_() * 2.0D;
/* 100 */             double d2 = m_20189_() + (this.f_19796_.m_188500_() * 2.0D - 1.0D) * m_20205_() * 0.5D;
/* 101 */             double d3 = (this.f_19796_.m_188500_() * 2.0D - 1.0D) * 0.3D;
/* 102 */             double d4 = 0.3D + this.f_19796_.m_188500_() * 0.3D;
/* 103 */             double d5 = (this.f_19796_.m_188500_() * 2.0D - 1.0D) * 0.3D;
/* 104 */             m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123797_, d0, d1, d2, d3, d4, d5);
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/* 109 */     } else if (--this.warmupDelayTicks < 0) {
/*     */       
/* 111 */       if (this.warmupDelayTicks == -2)
/*     */       {
/* 113 */         for (LivingEntity entity : m_9236_().m_45976_(LivingEntity.class, m_20191_().m_82377_(0.6D, 0.0D, 0.6D))) {
/* 114 */           dealDamageTo(entity);
/*     */         }
/*     */       }
/* 117 */       if (!this.sentSpikeEvent) {
/*     */         
/* 119 */         m_9236_().m_7605_(this, (byte)4);
/* 120 */         this.sentSpikeEvent = true;
/*     */       } 
/*     */       
/* 123 */       if (--this.lifeTicks < 0) {
/* 124 */         m_146870_();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void dealDamageTo(LivingEntity entity) {
/* 130 */     LivingEntity owner = getOwner();
/* 131 */     if (entity.m_6084_() && !entity.m_20147_() && entity != owner)
/*     */     {
/* 133 */       if (owner == null) {
/*     */         
/* 135 */         entity.m_6469_(m_269291_().m_269425_(), 6.0F);
/*     */       }
/*     */       else {
/*     */         
/* 139 */         if (owner.m_7307_((Entity)entity)) {
/*     */           return;
/*     */         }
/* 142 */         entity.m_6469_(m_269291_().m_269104_(this, (Entity)owner), 6.0F + this.damageModifier);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7822_(byte event) {
/* 150 */     super.m_7822_(event);
/* 151 */     if (event == 4) {
/*     */       
/* 153 */       this.clientSideAttackStarted = true;
/* 154 */       if (!m_20067_()) {
/* 155 */         m_9236_().m_7785_(m_20185_(), m_20186_(), m_20189_(), (SoundEvent)WitherStormModSoundEvents.TENTACLE_SPIKE_STAB.get(), m_5720_(), 1.0F, this.f_19796_.m_188501_() * 0.2F + 0.85F, false);
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