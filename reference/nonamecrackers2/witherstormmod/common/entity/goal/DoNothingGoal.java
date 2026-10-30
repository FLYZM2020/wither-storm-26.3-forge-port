/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ public class DoNothingGoal
/*    */   extends Goal
/*    */ {
/*    */   private final WitherStormEntity storm;
/*    */   private final int head;
/*    */   
/*    */   public DoNothingGoal(WitherStormEntity entity, int head) {
/* 15 */     m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
/* 16 */     this.storm = entity;
/* 17 */     this.head = head;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_8036_() {
/* 22 */     return (this.storm.shouldDoNothing() || (this.head > 0 && this.storm.areOtherHeadsDisabled()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\DoNothingGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */