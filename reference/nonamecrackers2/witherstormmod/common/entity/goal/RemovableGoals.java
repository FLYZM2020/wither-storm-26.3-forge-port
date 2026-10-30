/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import net.minecraft.world.entity.ai.goal.GoalSelector;
/*    */ import net.minecraft.world.entity.ai.goal.WrappedGoal;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemovableGoals
/*    */ {
/*    */   private final List<Pair<Integer, Goal>> goals;
/*    */   
/*    */   private RemovableGoals(List<Pair<Integer, Goal>> goals) {
/* 19 */     this.goals = goals;
/*    */   }
/*    */ 
/*    */   
/*    */   public void putGoals(GoalSelector selector) {
/* 24 */     this.goals.forEach(pair -> {
/*    */           if (!((Set)selector.m_148105_().stream().map(WrappedGoal::m_26015_).collect(Collectors.toSet())).contains(pair.getSecond())) {
/*    */             selector.m_25352_(((Integer)pair.getFirst()).intValue(), (Goal)pair.getSecond());
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeGoals(GoalSelector selector) {
/* 33 */     this.goals.forEach(pair -> selector.m_25363_((Goal)pair.getSecond()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Builder
/*    */   {
/* 42 */     private final List<Pair<Integer, Goal>> goals = Lists.newArrayList();
/*    */ 
/*    */ 
/*    */     
/*    */     public static Builder builder() {
/* 47 */       return new Builder();
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder put(int priority, Goal goal) {
/* 52 */       this.goals.add(Pair.of(Integer.valueOf(priority), goal));
/* 53 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public RemovableGoals build(GoalSelector selector) {
/* 58 */       if (this.goals.size() > 0) {
/*    */         
/* 60 */         RemovableGoals goals = new RemovableGoals(this.goals);
/* 61 */         goals.putGoals(selector);
/* 62 */         return goals;
/*    */       } 
/*    */ 
/*    */       
/* 66 */       throw new IllegalStateException("No goals present");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\RemovableGoals.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */