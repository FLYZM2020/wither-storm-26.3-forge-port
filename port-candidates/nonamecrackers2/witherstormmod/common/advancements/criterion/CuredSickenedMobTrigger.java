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
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.level.storage.loot.LootContext;
/*    */ 
/*    */ public class CuredSickenedMobTrigger
/*    */   extends SimpleCriterionTrigger<CuredSickenedMobTrigger.Instance>
/*    */ {
/* 19 */   private static final Identifier ID = Identifier.fromNamespaceAndPath("witherstormmod", "cured_sickened_mob");
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getId() {
/* 24 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayer player, Mob entity, Mob conversion) {
/* 29 */     LootContext entityContext = EntityPredicate.createContext(player, (Entity)entity);
/* 30 */     LootContext conversionContext = EntityPredicate.createContext(player, (Entity)conversion);
/* 31 */     trigger(player, instance -> instance.matches(entityContext, conversionContext));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext parser) {
/* 39 */     ContextAwarePredicate sickened = EntityPredicate.fromJson(object, "sickened", parser);
/* 40 */     ContextAwarePredicate conversion = EntityPredicate.fromJson(object, "converison", parser);
/* 41 */     return new Instance(ID, player, sickened, conversion);
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends AbstractCriterionTriggerInstance
/*    */   {
/*    */     private final ContextAwarePredicate sickened;
/*    */     private final ContextAwarePredicate conversion;
/*    */     
/*    */     public Instance(Identifier id, ContextAwarePredicate player, ContextAwarePredicate sickened, ContextAwarePredicate conversion) {
/* 51 */       super(id, player);
/* 52 */       this.sickened = sickened;
/* 53 */       this.conversion = conversion;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(LootContext sickened, LootContext conversion) {
/* 58 */       if (!this.sickened.matches(sickened)) {
/* 59 */         return false;
/*    */       }
/* 61 */       return this.conversion.matches(conversion);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject serializeToJson(SerializationContext serializer) {
/* 67 */       JsonObject object = super.serializeToJson(serializer);
/* 68 */       object.add("sickened", this.sickened.toJson(serializer));
/* 69 */       object.add("conversion", this.sickened.toJson(serializer));
/* 70 */       return object;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\CuredSickenedMobTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */