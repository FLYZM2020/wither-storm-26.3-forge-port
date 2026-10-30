/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraft.world.effect.MobEffects;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.projectile.Snowball;
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
/* 41 */     Entity entity = result.m_82443_();
/* 42 */     if (this.givesWitherEffect && entity instanceof LivingEntity)
/* 43 */       ((LivingEntity)entity).m_7292_(new MobEffectInstance(MobEffects.f_19615_, 150, 1)); 
/* 44 */     if (!entity.m_9236_().m_5776_()) { Level level = entity.m_9236_(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level; if (this.givesWitherEffect)
/*    */         {
/* 46 */           for (int i = 0; i < 5; i++) {
/*    */             
/* 48 */             double x = entity.m_20185_() + serverLevel.m_213780_().m_188583_() * 0.5D;
/* 49 */             double y = entity.m_20188_() + serverLevel.m_213780_().m_188583_() * 0.5D;
/* 50 */             double z = entity.m_20189_() + serverLevel.m_213780_().m_188583_() * 0.5D;
/* 51 */             Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/* 52 */             serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123755_, x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0D);
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