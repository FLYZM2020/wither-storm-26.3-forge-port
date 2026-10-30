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
/*     */ import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
/*     */ import net.minecraft.world.entity.projectile.DragonFireball;
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
/*  38 */   private static final EntityDataAccessor<Boolean> CREATED_FROM_SYMBIONT = SynchedEntityData.m_135353_(MixinDragonFireball.class, EntityDataSerializers.f_135035_);
/*     */ 
/*     */   
/*     */   private MixinDragonFireball(EntityType<? extends AbstractHurtingProjectile> type, Level level) {
/*  42 */     super(type, level);
/*  43 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/*  49 */     super.m_8097_();
/*  50 */     this.f_19804_.m_135372_(CREATED_FROM_SYMBIONT, Boolean.valueOf(false));
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
/*  63 */       areaEffectCloud.m_19724_((ParticleOptions)ParticleTypes.f_123762_);
/*  64 */       areaEffectCloud.m_19712_(2.5F);
/*  65 */       areaEffectCloud.m_19734_(120);
/*  66 */       areaEffectCloud.m_19716_(new MobEffectInstance(MobEffects.f_19615_, 60, 2));
/*  67 */       areaEffectCloud.m_19738_((10.0F - areaEffectCloud.m_19743_()) / areaEffectCloud.m_19748_());
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
/*  81 */     return ((Boolean)this.f_19804_.m_135370_(CREATED_FROM_SYMBIONT)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatedBySymbiont(boolean flag) {
/*  87 */     this.f_19804_.m_135381_(CREATED_FROM_SYMBIONT, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/*  93 */     super.m_7380_(tag);
/*  94 */     if (createdBySymbiont()) {
/*  95 */       tag.m_128379_("CreatedBySymbiont", createdBySymbiont());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 101 */     super.m_7378_(tag);
/* 102 */     if (tag.m_128425_("CreatedBySymbiont", 1))
/* 103 */       setCreatedBySymbiont(tag.m_128471_("CreatedBySymbiont")); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinDragonFireball.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */