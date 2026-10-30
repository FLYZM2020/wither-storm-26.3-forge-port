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
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.level.storage.loot.LootContext;
/*    */ 
/*    */ public class CuredSickenedMobTrigger
/*    */   extends SimpleCriterionTrigger<CuredSickenedMobTrigger.Instance>
/*    */ {
/* 19 */   private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "cured_sickened_mob");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation m_7295_() {
/* 24 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayer player, Mob entity, Mob conversion) {
/* 29 */     LootContext entityContext = EntityPredicate.m_36616_(player, (Entity)entity);
/* 30 */     LootContext conversionContext = EntityPredicate.m_36616_(player, (Entity)conversion);
/* 31 */     m_66234_(player, instance -> instance.matches(entityContext, conversionContext));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext parser) {
/* 39 */     ContextAwarePredicate sickened = EntityPredicate.m_285855_(object, "sickened", parser);
/* 40 */     ContextAwarePredicate conversion = EntityPredicate.m_285855_(object, "converison", parser);
/* 41 */     return new Instance(ID, player, sickened, conversion);
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends AbstractCriterionTriggerInstance
/*    */   {
/*    */     private final ContextAwarePredicate sickened;
/*    */     private final ContextAwarePredicate conversion;
/*    */     
/*    */     public Instance(ResourceLocation id, ContextAwarePredicate player, ContextAwarePredicate sickened, ContextAwarePredicate conversion) {
/* 51 */       super(id, player);
/* 52 */       this.sickened = sickened;
/* 53 */       this.conversion = conversion;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(LootContext sickened, LootContext conversion) {
/* 58 */       if (!this.sickened.m_285831_(sickened)) {
/* 59 */         return false;
/*    */       }
/* 61 */       return this.conversion.m_285831_(conversion);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject m_7683_(SerializationContext serializer) {
/* 67 */       JsonObject object = super.m_7683_(serializer);
/* 68 */       object.add("sickened", this.sickened.m_286026_(serializer));
/* 69 */       object.add("conversion", this.sickened.m_286026_(serializer));
/* 70 */       return object;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\CuredSickenedMobTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */