/*     */ package nonamecrackers2.witherstormmod.mixin;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.entity.AreaEffectCloud;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
/*     */ import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.util.DragonFireballAccessor;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({DragonFireball.class})
/*     */ public class MixinDragonFireball
/*     */   extends AbstractHurtingProjectile
/*     */   implements DragonFireballAccessor
/*     */ {
/*     */   @Unique
/*  38 */   private static final EntityDataAccessor<Boolean> CREATED_FROM_SYMBIONT = SynchedEntityData.defineId(MixinDragonFireball.class, EntityDataSerializers.BOOLEAN);
/*     */ 
/*     */   
/*     */   private MixinDragonFireball(EntityType<? extends AbstractHurtingProjectile> type, Level level) {
/*  42 */     super(type, level);
/*  43 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/*  49 */     super.defineSynchedData();
/*  50 */     this.entityData.define(CREATED_FROM_SYMBIONT, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Inject(method = {"onHit"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/AreaEffectCloud;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)V", shift = At.Shift.AFTER)}, locals = LocalCapture.CAPTURE_FAILHARD)
/*     */   public void witherstormmod$addMobEffects_onHit(HitResult result, CallbackInfo ci, List<LivingEntity> list, AreaEffectCloud areaEffectCloud, Entity entity) {
/*  60 */     if (createdBySymbiont()) {
/*     */       
/*  62 */       ((MixinAreaEffectCloud)areaEffectCloud).witherstormmod$getEffects().clear();
/*  63 */       areaEffectCloud.setParticle((ParticleOptions)ParticleTypes.SMOKE);
/*  64 */       areaEffectCloud.setRadius(2.5F);
/*  65 */       areaEffectCloud.setDuration(120);
/*  66 */       areaEffectCloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 2));
/*  67 */       areaEffectCloud.setRadiusPerTick((10.0F - areaEffectCloud.getRadius()) / areaEffectCloud.getDuration());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"getTrailParticle"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void witherstormmod$useCustomTrailParticle_getTrailParticle(CallbackInfoReturnable<ParticleOptions> ci) {
/*  74 */     if (createdBySymbiont()) {
/*  75 */       ci.setReturnValue(WitherStormModParticleTypes.COMMAND_BLOCK.get());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean createdBySymbiont() {
/*  81 */     return ((Boolean)this.entityData.get(CREATED_FROM_SYMBIONT)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatedBySymbiont(boolean flag) {
/*  87 */     this.entityData.set(CREATED_FROM_SYMBIONT, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/*  93 */     super.addAdditionalSaveData(tag);
/*  94 */     if (createdBySymbiont()) {
/*  95 */       tag.putBoolean("CreatedBySymbiont", createdBySymbiont());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 101 */     super.readAdditionalSaveData(tag);
/* 102 */     if (tag.contains("CreatedBySymbiont", 1))
/* 103 */       setCreatedBySymbiont(tag.getBoolean("CreatedBySymbiont")); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinDragonFireball.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */