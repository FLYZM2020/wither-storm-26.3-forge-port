/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.SerializationContext;
/*    */ import net.minecraft.world.level.storage.loot.LootContext;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Instance
/*    */   extends AbstractCriterionTriggerInstance
/*    */ {
/*    */   private final ContextAwarePredicate summoned;
/*    */   
/*    */   public Instance(ContextAwarePredicate player, ContextAwarePredicate summoned) {
/* 48 */     super(SummonMobSuperBeaconTrigger.ID, player);
/* 49 */     this.summoned = summoned;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(LootContext context) {
/* 54 */     return this.summoned.m_285831_(context);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonObject m_7683_(SerializationContext context) {
/* 60 */     JsonObject obj = super.m_7683_(context);
/* 61 */     obj.add("summoned", this.summoned.m_286026_(context));
/* 62 */     return obj;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\SummonMobSuperBeaconTrigger$Instance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */