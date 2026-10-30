/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.DeserializationContext;
/*    */ import net.minecraft.advancements.critereon.EntityPredicate;
/*    */ import net.minecraft.advancements.critereon.SerializationContext;
/*    */ import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.level.storage.loot.LootContext;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public class EscapeWitherStormTrigger
/*    */   extends SimpleCriterionTrigger<EscapeWitherStormTrigger.Instance>
/*    */ {
/* 20 */   private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "escape_wither_storm");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation m_7295_() {
/* 25 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayer player, WitherStormEntity entity) {
/* 30 */     LootContext context = EntityPredicate.m_36616_(player, (Entity)entity);
/* 31 */     m_66234_(player, instance -> instance.matches(context));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext parser) {
/* 39 */     ContextAwarePredicate entity = EntityPredicate.m_285855_(object, "entity", parser);
/* 40 */     return new Instance(ID, player, entity);
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends AbstractCriterionTriggerInstance
/*    */   {
/*    */     private final ContextAwarePredicate entity;
/*    */     
/*    */     public Instance(ResourceLocation id, ContextAwarePredicate player, ContextAwarePredicate entity) {
/* 49 */       super(id, player);
/* 50 */       this.entity = entity;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(LootContext context) {
/* 55 */       return this.entity.m_285831_(context);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject m_7683_(SerializationContext serializer) {
/* 61 */       JsonObject object = super.m_7683_(serializer);
/* 62 */       object.add("entity", this.entity.m_286026_(serializer));
/* 63 */       return object;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\EscapeWitherStormTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */