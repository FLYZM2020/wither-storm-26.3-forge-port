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
/*     */ import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
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
/*  50 */     this.xPower = x * 0.1D;
/*  51 */     this.yPower = y * 0.1D;
/*  52 */     this.zPower = z * 0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public FlamingWitherSkullEntity(Level world, LivingEntity owner, double x, double y, double z) {
/*  57 */     this((EntityType<? extends FlamingWitherSkullEntity>)WitherStormModEntityTypes.FLAMING_WITHER_SKULL.get(), world, owner, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getInertia() {
/*  63 */     return 0.9F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnFire() {
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean displayFireAnimation() {
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected ParticleOptions getTrailParticle() {
/*  81 */     return (WitherStormMod.isAprilFools() && ((Boolean)WitherStormModConfig.CLIENT.aprilFools.get()).booleanValue()) ? (ParticleOptions)ParticleTypes.HEART : super.getTrailParticle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ParticleOptions getParticle() {
/*  86 */     return (ParticleOptions)ParticleTypes.FLAME;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  92 */     super.tick();
/*  93 */     Vec3 vec3 = getDeltaMovement();
/*  94 */     double d0 = getX() + vec3.x + this.random.nextFloat();
/*  95 */     double d1 = getY() + vec3.y + this.random.nextFloat();
/*  96 */     double d2 = getZ() + vec3.z + this.random.nextFloat();
/*  97 */     level().addParticle(getParticle(), d0 - 0.5D, d1, d2 - 0.5D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onHitEntity(@NotNull EntityHitResult ray) {
/* 103 */     super.onHitEntity(ray);
/* 104 */     if (!(level()).isClientSide) {
/*     */       boolean flag;
/* 106 */       Entity victim = ray.getEntity();
/*     */       
/* 108 */       if (victim instanceof LivingEntity) { LivingEntity living = (LivingEntity)victim;
/*     */         
/* 110 */         ItemStack use = living.getUseItem();
/* 111 */         if (use.is(Items.SHIELD)) {
/*     */           
/* 113 */           explodeAndDiscard();
/*     */           
/*     */           return;
/*     */         }  }
/*     */       
/* 118 */       Entity owner = getOwner();
/*     */       
/* 120 */       if (owner instanceof LivingEntity) { LivingEntity livingOwner = (LivingEntity)owner;
/*     */         
/* 122 */         flag = victim.hurt(damageSource(this, (Entity)livingOwner), 10.0F);
/* 123 */         if (flag)
/*     */         {
/* 125 */           if (victim.isAlive()) {
/* 126 */             doEnchantDamageEffects(livingOwner, victim);
/*     */           } else {
/* 128 */             livingOwner.heal(10.0F);
/*     */           } 
/*     */         } }
/*     */       else
/*     */       
/* 133 */       { flag = victim.hurt(damageSources().magic(), 8.0F); }
/*     */ 
/*     */       
/* 136 */       if (flag && victim instanceof LivingEntity) {
/*     */         
/* 138 */         int i = 0;
/* 139 */         if (level().getDifficulty() == Difficulty.NORMAL) {
/* 140 */           i = 10;
/* 141 */         } else if (level().getDifficulty() == Difficulty.HARD) {
/* 142 */           i = 40;
/*     */         } 
/* 144 */         if (i > 0) {
/* 145 */           ((LivingEntity)victim).addEffect(new MobEffectInstance(MobEffects.WITHER, 180, 1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onHit(@NotNull HitResult ray) {
/* 153 */     super.onHit(ray);
/* 154 */     if (!(level()).isClientSide && ray.getType() != HitResult.Type.ENTITY) {
/* 155 */       explodeAndDiscard();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void explodeAndDiscard() {
/* 160 */     boolean flag = ForgeEventFactory.getMobGriefingEvent(level(), getOwner());
/* 161 */     playSound((SoundEvent)WitherStormModSoundEvents.FLAMING_SKULL_IMPACT.get(), 6.0F, (this.random.nextFloat() - this.random.nextFloat()) * -0.2F + 1.0F);
/* 162 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p((position()).x, (position()).y, (position()).z, 45.0D, level().dimension())), new ShakeScreenMessage(20.0F, 4.0F));
/* 163 */     level().explode((Entity)this, getX(), getY(), getZ(), (float)(((Double)WitherStormModConfig.SERVER.flamingSkullExplosionSize.get()).doubleValue() + this.random.nextInt(2)), flag, Level.ExplosionInteraction.MOB);
/* 164 */     discard();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hurt(@NotNull DamageSource source, float amount) {
/* 170 */     Entity entity = source.getEntity(); if (entity instanceof LivingEntity) { LivingEntity livingEntity = (LivingEntity)entity;
/*     */       
/* 172 */       ItemStack item = livingEntity.getMainHandItem();
/* 173 */       if (!item.isEmpty() && item.getItem() instanceof net.minecraft.world.item.SwordItem) {
/*     */         
/* 175 */         boolean flag = super.hurt(source, amount);
/* 176 */         if (flag && !level().isClientSide()) {
/*     */           
/* 178 */           item.hurtAndBreak(120 + this.random.nextInt(140), livingEntity, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
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
/*     */   protected boolean shouldBurn() {
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static DamageSource damageSource(FlamingWitherSkullEntity cause, Entity entity) {
/* 195 */     return WitherStormModDamageTypes.source(cause.level().registryAccess(), WitherStormModDamageTypes.FLAMING_WITHER_SKULL, (Entity)cause, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> getAddEntityPacket() {
/* 201 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(FriendlyByteBuf buffer) {
/* 207 */     buffer.writeDouble(this.xPower);
/* 208 */     buffer.writeDouble(this.yPower);
/* 209 */     buffer.writeDouble(this.zPower);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSpawnData(FriendlyByteBuf additionalData) {
/* 215 */     this.xPower = additionalData.readDouble();
/* 216 */     this.yPower = additionalData.readDouble();
/* 217 */     this.zPower = additionalData.readDouble();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\FlamingWitherSkullEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */