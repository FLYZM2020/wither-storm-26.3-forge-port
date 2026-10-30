/*    */ package nonamecrackers2.witherstormmod.api.common.event;
/*    */ 
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class WitherStormChangePhaseEvent
/*    */   extends WitherStormEvent
/*    */ {
/*    */   private final int toPhase;
/*    */   
/*    */   public WitherStormChangePhaseEvent(WitherStormEntity storm, int toPhase) {
/* 11 */     super(storm);
/* 12 */     this.toPhase = toPhase;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getToPhase() {
/* 17 */     return this.toPhase;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\event\WitherStormChangePhaseEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */