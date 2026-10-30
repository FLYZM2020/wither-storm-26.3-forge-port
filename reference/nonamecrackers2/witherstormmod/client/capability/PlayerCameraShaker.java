/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ 
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.util.Mth;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ 
/*    */ 
/*    */ public class PlayerCameraShaker
/*    */ {
/*    */   private final LocalPlayer player;
/*    */   private float xShake;
/*    */   private float yShake;
/*    */   private float xShakeO;
/*    */   private float yShakeO;
/*    */   private float duration;
/*    */   private float initialDuration;
/*    */   private float power;
/*    */   
/*    */   public PlayerCameraShaker(LocalPlayer player) {
/* 20 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 25 */     this.xShakeO = this.xShake;
/* 26 */     this.yShakeO = this.yShake;
/*    */     
/* 28 */     if (((Boolean)WitherStormModConfig.CLIENT.cameraShakeEffects.get()).booleanValue())
/*    */     {
/* 30 */       if (this.duration > 0.0F) {
/*    */         float power;
/*    */         
/* 33 */         if (this.player.m_20096_()) {
/* 34 */           power = this.power;
/*    */         } else {
/* 36 */           power = 0.0F;
/*    */         } 
/* 38 */         this.xShake = power * (this.player.m_217043_().m_188501_() * 2.0F - 1.0F) * getDurationPercentage();
/* 39 */         this.yShake = power * (this.player.m_217043_().m_188501_() * 2.0F - 1.0F) * getDurationPercentage();
/*    */         
/* 41 */         this.duration--;
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void shake(float duration, float power) {
/* 48 */     if (((Boolean)WitherStormModConfig.CLIENT.cameraShakeEffects.get()).booleanValue()) {
/*    */       
/* 50 */       this.initialDuration = duration;
/* 51 */       this.duration = duration;
/* 52 */       this.power = power;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float getXShake(float partialTicks) {
/* 58 */     return Mth.m_14179_(partialTicks, this.xShakeO, this.xShake);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getYShake(float partialTicks) {
/* 63 */     return Mth.m_14179_(partialTicks, this.yShakeO, this.yShake);
/*    */   }
/*    */ 
/*    */   
/*    */   private float getDurationPercentage() {
/* 68 */     return this.duration / this.initialDuration;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\PlayerCameraShaker.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */