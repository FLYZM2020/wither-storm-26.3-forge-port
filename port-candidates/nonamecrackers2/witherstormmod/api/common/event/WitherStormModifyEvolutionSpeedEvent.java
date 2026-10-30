/*    */ package nonamecrackers2.witherstormmod.api.common.event;
/*    */ 
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class WitherStormModifyEvolutionSpeedEvent
/*    */   extends WitherStormEvent
/*    */ {
/*    */   private double originalEvolutionSpeedModifier;
/*    */   
/*    */   public WitherStormModifyEvolutionSpeedEvent(WitherStormEntity storm, double evolutionSpeedModifier) {
/* 11 */     super(storm);
/* 12 */     this.originalEvolutionSpeedModifier = evolutionSpeedModifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getOriginalEvolutionSpeedModifier() {
/* 17 */     return this.originalEvolutionSpeedModifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEvolutionSpeedModifier(double evolutionSpeedModifier) {
/* 22 */     this.originalEvolutionSpeedModifier = evolutionSpeedModifier;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\event\WitherStormModifyEvolutionSpeedEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */