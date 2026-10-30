/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.SerializationContext;
/*    */ import net.minecraft.resources.ResourceLocation;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Instance
/*    */   extends AbstractCriterionTriggerInstance
/*    */ {
/*    */   private final ContextAwarePredicate entity;
/*    */   
/*    */   public Instance(ResourceLocation id, ContextAwarePredicate player, ContextAwarePredicate entity) {
/* 53 */     super(id, player);
/* 54 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(LootContext context) {
/* 59 */     return this.entity.m_285831_(context);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonObject m_7683_(SerializationContext serializer) {
/* 65 */     JsonObject object = super.m_7683_(serializer);
/* 66 */     object.add("entity", this.entity.m_286026_(serializer));
/* 67 */     return object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\WitherStormPlayDeadTrigger$Instance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */