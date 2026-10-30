/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({MobEffect.class})
/*    */ public class MixinMobEffect
/*    */ {
/*    */   @Redirect(method = {"applyInstantenousEffect"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
/*    */   public void redirectHeal(LivingEntity entity, float amount) {
/* 34 */     if (!(entity instanceof nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity)) {
/* 35 */       entity.m_5634_(amount);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Redirect(method = {"applyInstantenousEffect"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
/*    */   public boolean redirectHurt(LivingEntity entity, DamageSource source, float amount) {
/* 44 */     if (entity.m_6336_() == WitherStormModMobTypes.SICKENED && !(entity instanceof WitherStormEntity))
/*    */     {
/* 46 */       return false;
/*    */     }
/*    */     
/* 49 */     if (!(entity instanceof WitherStormEntity) || ((WitherStormEntity)entity).getPhase() < 3) {
/* 50 */       return entity.m_6469_(source, amount);
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinMobEffect.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */