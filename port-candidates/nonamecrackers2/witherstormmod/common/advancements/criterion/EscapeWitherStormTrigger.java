/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.DeserializationContext;
/*    */ import net.minecraft.advancements.critereon.EntityPredicate;
/*    */ import net.minecraft.advancements.critereon.SerializationContext;
/*    */ import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.level.storage.loot.LootContext;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public class EscapeWitherStormTrigger
/*    */   extends SimpleCriterionTrigger<EscapeWitherStormTrigger.Instance>
/*    */ {
/* 20 */   private static final Identifier ID = Identifier.fromNamespaceAndPath("witherstormmod", "escape_wither_storm");
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getId() {
/* 25 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayer player, WitherStormEntity entity) {
/* 30 */     LootContext context = EntityPredicate.createContext(player, (Entity)entity);
/* 31 */     trigger(player, instance -> instance.matches(context));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext parser) {
/* 39 */     ContextAwarePredicate entity = EntityPredicate.fromJson(object, "entity", parser);
/* 40 */     return new Instance(ID, player, entity);
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends AbstractCriterionTriggerInstance
/*    */   {
/*    */     private final ContextAwarePredicate entity;
/*    */     
/*    */     public Instance(Identifier id, ContextAwarePredicate player, ContextAwarePredicate entity) {
/* 49 */       super(id, player);
/* 50 */       this.entity = entity;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(LootContext context) {
/* 55 */       return this.entity.matches(context);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject serializeToJson(SerializationContext serializer) {
/* 61 */       JsonObject object = super.serializeToJson(serializer);
/* 62 */       object.add("entity", this.entity.toJson(serializer));
/* 63 */       return object;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\EscapeWitherStormTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */