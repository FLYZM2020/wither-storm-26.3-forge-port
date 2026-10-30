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
/*    */ 
/*    */ 
/*    */ public class SummonMobSuperBeaconTrigger
/*    */   extends SimpleCriterionTrigger<SummonMobSuperBeaconTrigger.Instance>
/*    */ {
/* 19 */   private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "summon_mob_withered_beacon");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation m_7295_() {
/* 24 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext context) {
/* 30 */     ContextAwarePredicate summoned = EntityPredicate.m_285855_(object, "resummoned", context);
/* 31 */     return new Instance(player, summoned);
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayer player, Entity summoned) {
/* 36 */     LootContext context = EntityPredicate.m_36616_(player, summoned);
/* 37 */     m_66234_(player, instance -> instance.matches(context));
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Instance
/*    */     extends AbstractCriterionTriggerInstance
/*    */   {
/*    */     private final ContextAwarePredicate summoned;
/*    */ 
/*    */     
/*    */     public Instance(ContextAwarePredicate player, ContextAwarePredicate summoned) {
/* 48 */       super(SummonMobSuperBeaconTrigger.ID, player);
/* 49 */       this.summoned = summoned;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(LootContext context) {
/* 54 */       return this.summoned.m_285831_(context);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject m_7683_(SerializationContext context) {
/* 60 */       JsonObject obj = super.m_7683_(context);
/* 61 */       obj.add("summoned", this.summoned.m_286026_(context));
/* 62 */       return obj;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\SummonMobSuperBeaconTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */