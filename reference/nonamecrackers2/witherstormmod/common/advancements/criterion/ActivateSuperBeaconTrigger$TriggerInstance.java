/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.MinMaxBounds;
/*    */ import net.minecraft.advancements.critereon.SerializationContext;
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
/*    */ public class TriggerInstance
/*    */   extends AbstractCriterionTriggerInstance
/*    */ {
/*    */   private final MinMaxBounds.Ints totalActivated;
/*    */   
/*    */   public TriggerInstance(ContextAwarePredicate player, MinMaxBounds.Ints totalActivated) {
/* 45 */     super(ActivateSuperBeaconTrigger.ID, player);
/* 46 */     this.totalActivated = totalActivated;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(int activated) {
/* 51 */     return this.totalActivated.m_55390_(activated);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonObject m_7683_(SerializationContext context) {
/* 57 */     JsonObject obj = super.m_7683_(context);
/* 58 */     obj.add("level", this.totalActivated.m_55328_());
/* 59 */     return obj;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\ActivateSuperBeaconTrigger$TriggerInstance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */