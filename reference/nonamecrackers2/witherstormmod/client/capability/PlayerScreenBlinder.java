/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ 
/*    */ import net.minecraft.util.Mth;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ 
/*    */ 
/*    */ public class PlayerScreenBlinder
/*    */ {
/*    */   private int duration;
/*    */   private int fadeInDuration;
/*    */   private int fadeOutDuration;
/*    */   private float fade;
/*    */   private float fadeO;
/*    */   
/*    */   public void tick() {
/* 16 */     this.fadeO = this.fade;
/*    */     
/* 18 */     if (((Boolean)WitherStormModConfig.CLIENT.blindingEffects.get()).booleanValue())
/*    */     {
/* 20 */       if (this.fadeInDuration > 0) {
/*    */         
/* 22 */         this.fade += (1.0F - this.fade) / this.fadeInDuration;
/* 23 */         this.fadeInDuration--;
/*    */       }
/* 25 */       else if (this.duration > 0) {
/*    */         
/* 27 */         this.duration--;
/*    */       }
/* 29 */       else if (this.fadeOutDuration > 0) {
/*    */         
/* 31 */         this.fade += (0.0F - this.fade) / this.fadeOutDuration;
/* 32 */         this.fadeOutDuration--;
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public float getFade(float partialTicks) {
/* 39 */     return Mth.m_14179_(partialTicks, this.fadeO, this.fade);
/*    */   }
/*    */ 
/*    */   
/*    */   public void blind(int duration, int fadeInDuration, int fadeOutDuration) {
/* 44 */     if (((Boolean)WitherStormModConfig.CLIENT.cameraShakeEffects.get()).booleanValue()) {
/*    */       
/* 46 */       this.duration = duration;
/* 47 */       this.fadeInDuration = fadeInDuration;
/* 48 */       this.fadeOutDuration = fadeOutDuration;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\PlayerScreenBlinder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */