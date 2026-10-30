/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import net.minecraft.world.entity.ai.goal.GoalSelector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemovableGoalsManager
/*    */ {
/* 15 */   private final Map<String, RemovableGoals> goalLists = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */   
/*    */   public void put(String id, RemovableGoals goals) {
/* 20 */     this.goalLists.put(id, goals);
/*    */   }
/*    */ 
/*    */   
/*    */   public void putGoals(String id, GoalSelector selector) {
/* 25 */     ((RemovableGoals)this.goalLists.get(id)).putGoals(selector);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeGoals(String id, GoalSelector selector) {
/* 30 */     ((RemovableGoals)this.goalLists.get(id)).removeGoals(selector);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\RemovableGoalsManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */