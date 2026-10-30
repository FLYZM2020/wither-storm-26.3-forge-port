/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.MinMaxBounds;
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
/*    */ 
/*    */ public class Instance
/*    */   extends AbstractCriterionTriggerInstance
/*    */ {
/*    */   private final ContextAwarePredicate linked;
/*    */   private final MinMaxBounds.Ints totalLinked;
/*    */   
/*    */   public Instance(ContextAwarePredicate player, ContextAwarePredicate linked, MinMaxBounds.Ints totalLinked) {
/* 51 */     super(LinkAmuletTrigger.ID, player);
/* 52 */     this.linked = linked;
/* 53 */     this.totalLinked = totalLinked;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(LootContext context, int totalLinked) {
/* 58 */     return (this.linked.m_285831_(context) && this.totalLinked.m_55390_(totalLinked));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonObject m_7683_(SerializationContext context) {
/* 64 */     JsonObject obj = super.m_7683_(context);
/* 65 */     obj.add("linked", this.linked.m_286026_(context));
/* 66 */     obj.add("total_linked", this.totalLinked.m_55328_());
/* 67 */     return obj;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\LinkAmuletTrigger$Instance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */