/*    */ package nonamecrackers2.witherstormmod.api.common.event;
/*    */ 
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class WitherStormModifyFlyingSpeedEvent
/*    */   extends WitherStormEvent
/*    */ {
/*    */   private double originalSpeed;
/*    */   
/*    */   public WitherStormModifyFlyingSpeedEvent(WitherStormEntity storm, double originalSpeed) {
/* 11 */     super(storm);
/* 12 */     this.originalSpeed = originalSpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getOriginalSpeed() {
/* 17 */     return this.originalSpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSpeed(double speed) {
/* 22 */     this.originalSpeed = speed;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\event\WitherStormModifyFlyingSpeedEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */