/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraft.world.effect.MobEffects;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.EntityHitResult;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.util.SnowballAccessor;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({Snowball.class})
/*    */ public class MixinSnowball
/*    */   implements SnowballAccessor
/*    */ {
/*    */   @Unique
/*    */   private boolean givesWitherEffect;
/*    */   
/*    */   public void setWitherEffect(boolean flag) {
/* 29 */     this.givesWitherEffect = flag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasWitherEffect() {
/* 35 */     return this.givesWitherEffect;
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"onHitEntity"}, at = {@At("HEAD")})
/*    */   public void onHitEntity(EntityHitResult result, CallbackInfo callbackInfo) {
/* 41 */     Entity entity = result.getEntity();
/* 42 */     if (this.givesWitherEffect && entity instanceof LivingEntity)
/* 43 */       ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WITHER, 150, 1)); 
/* 44 */     if (!entity.level().isClientSide()) { Level level = entity.level(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level; if (this.givesWitherEffect)
/*    */         {
/* 46 */           for (int i = 0; i < 5; i++) {
/*    */             
/* 48 */             double x = entity.getX() + serverLevel.getRandom().nextGaussian() * 0.5D;
/* 49 */             double y = entity.getEyeY() + serverLevel.getRandom().nextGaussian() * 0.5D;
/* 50 */             double z = entity.getZ() + serverLevel.getRandom().nextGaussian() * 0.5D;
/* 51 */             Vec3 delta = entity.getEyePosition(1.0F).subtract(x, y, z).normalize().multiply(0.1D, 0.1D, 0.1D);
/* 52 */             serverLevel.sendParticles((ParticleOptions)ParticleTypes.LARGE_SMOKE, x, y, z, 0, delta.x(), delta.y(), delta.z(), 1.0D);
/*    */           } 
/*    */         } }
/*    */        }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinSnowball.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */