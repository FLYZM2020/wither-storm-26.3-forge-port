/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.entity.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateDamagingProjectileMessage;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class FlamingWitherSkullEntity
/*     */   extends AbstractHurtingProjectile
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   public FlamingWitherSkullEntity(EntityType<? extends FlamingWitherSkullEntity> type, Level world) {
/*  44 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public FlamingWitherSkullEntity(EntityType<? extends FlamingWitherSkullEntity> type, Level world, LivingEntity owner, double x, double y, double z) {
/*  49 */     super(type, owner, x, y, z, world);
/*  50 */     this.f_36813_ = x * 0.1D;
/*  51 */     this.f_36814_ = y * 0.1D;
/*  52 */     this.f_36815_ = z * 0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public FlamingWitherSkullEntity(Level world, LivingEntity owner, double x, double y, double z) {
/*  57 */     this((EntityType<? extends FlamingWitherSkullEntity>)WitherStormModEntityTypes.FLAMING_WITHER_SKULL.get(), world, owner, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float m_6884_() {
/*  63 */     return 0.9F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6060_() {
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6051_() {
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected ParticleOptions m_5967_() {
/*  81 */     return (WitherStormMod.isAprilFools() && ((Boolean)WitherStormModConfig.CLIENT.aprilFools.get()).booleanValue()) ? (ParticleOptions)ParticleTypes.f_123750_ : super.m_5967_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ParticleOptions getParticle() {
/*  86 */     return (ParticleOptions)ParticleTypes.f_123744_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  92 */     super.m_8119_();
/*  93 */     Vec3 vec3 = m_20184_();
/*  94 */     double d0 = m_20185_() + vec3.f_82479_ + this.f_19796_.m_188501_();
/*  95 */     double d1 = m_20186_() + vec3.f_82480_ + this.f_19796_.m_188501_();
/*  96 */     double d2 = m_20189_() + vec3.f_82481_ + this.f_19796_.m_188501_();
/*  97 */     m_9236_().m_7106_(getParticle(), d0 - 0.5D, d1, d2 - 0.5D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_5790_(@NotNull EntityHitResult ray) {
/* 103 */     super.m_5790_(ray);
/* 104 */     if (!(m_9236_()).f_46443_) {
/*     */       boolean flag;
/* 106 */       Entity victim = ray.m_82443_();
/*     */       
/* 108 */       if (victim instanceof LivingEntity) { LivingEntity living = (LivingEntity)victim;
/*     */         
/* 110 */         ItemStack use = living.m_21211_();
/* 111 */         if (use.m_150930_(Items.f_42740_)) {
/*     */           
/* 113 */           explodeAndDiscard();
/*     */           
/*     */           return;
/*     */         }  }
/*     */       
/* 118 */       Entity owner = m_19749_();
/*     */       
/* 120 */       if (owner instanceof LivingEntity) { LivingEntity livingOwner = (LivingEntity)owner;
/*     */         
/* 122 */         flag = victim.m_6469_(damageSource(this, (Entity)livingOwner), 10.0F);
/* 123 */         if (flag)
/*     */         {
/* 125 */           if (victim.m_6084_()) {
/* 126 */             m_19970_(livingOwner, victim);
/*     */           } else {
/* 128 */             livingOwner.m_5634_(10.0F);
/*     */           } 
/*     */         } }
/*     */       else
/*     */       
/* 133 */       { flag = victim.m_6469_(m_269291_().m_269425_(), 8.0F); }
/*     */ 
/*     */       
/* 136 */       if (flag && victim instanceof LivingEntity) {
/*     */         
/* 138 */         int i = 0;
/* 139 */         if (m_9236_().m_46791_() == Difficulty.NORMAL) {
/* 140 */           i = 10;
/* 141 */         } else if (m_9236_().m_46791_() == Difficulty.HARD) {
/* 142 */           i = 40;
/*     */         } 
/* 144 */         if (i > 0) {
/* 145 */           ((LivingEntity)victim).m_7292_(new MobEffectInstance(MobEffects.f_19615_, 180, 1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_6532_(@NotNull HitResult ray) {
/* 153 */     super.m_6532_(ray);
/* 154 */     if (!(m_9236_()).f_46443_ && ray.m_6662_() != HitResult.Type.ENTITY) {
/* 155 */       explodeAndDiscard();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void explodeAndDiscard() {
/* 160 */     boolean flag = ForgeEventFactory.getMobGriefingEvent(m_9236_(), m_19749_());
/* 161 */     m_5496_((SoundEvent)WitherStormModSoundEvents.FLAMING_SKULL_IMPACT.get(), 6.0F, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * -0.2F + 1.0F);
/* 162 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p((m_20182_()).f_82479_, (m_20182_()).f_82480_, (m_20182_()).f_82481_, 45.0D, m_9236_().m_46472_())), new ShakeScreenMessage(20.0F, 4.0F));
/* 163 */     m_9236_().m_255391_((Entity)this, m_20185_(), m_20186_(), m_20189_(), (float)(((Double)WitherStormModConfig.SERVER.flamingSkullExplosionSize.get()).doubleValue() + this.f_19796_.m_188503_(2)), flag, Level.ExplosionInteraction.MOB);
/* 164 */     m_146870_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(@NotNull DamageSource source, float amount) {
/* 170 */     Entity entity = source.m_7639_(); if (entity instanceof LivingEntity) { LivingEntity livingEntity = (LivingEntity)entity;
/*     */       
/* 172 */       ItemStack item = livingEntity.m_21205_();
/* 173 */       if (!item.m_41619_() && item.m_41720_() instanceof net.minecraft.world.item.SwordItem) {
/*     */         
/* 175 */         boolean flag = super.m_6469_(source, amount);
/* 176 */         if (flag && !m_9236_().m_5776_()) {
/*     */           
/* 178 */           item.m_41622_(120 + this.f_19796_.m_188503_(140), livingEntity, e -> e.m_21190_(InteractionHand.MAIN_HAND));
/* 179 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new UpdateDamagingProjectileMessage(this));
/*     */         } 
/* 181 */         return flag;
/*     */       }  }
/*     */     
/* 184 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_5931_() {
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static DamageSource damageSource(FlamingWitherSkullEntity cause, Entity entity) {
/* 195 */     return WitherStormModDamageTypes.source(cause.m_9236_().m_9598_(), WitherStormModDamageTypes.FLAMING_WITHER_SKULL, (Entity)cause, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 201 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(FriendlyByteBuf buffer) {
/* 207 */     buffer.writeDouble(this.f_36813_);
/* 208 */     buffer.writeDouble(this.f_36814_);
/* 209 */     buffer.writeDouble(this.f_36815_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSpawnData(FriendlyByteBuf additionalData) {
/* 215 */     this.f_36813_ = additionalData.readDouble();
/* 216 */     this.f_36814_ = additionalData.readDouble();
/* 217 */     this.f_36815_ = additionalData.readDouble();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\FlamingWitherSkullEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */