/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.DeserializationContext;
/*    */ import net.minecraft.advancements.critereon.MinMaxBounds;
/*    */ import net.minecraft.advancements.critereon.SerializationContext;
/*    */ import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ 
/*    */ 
/*    */ public class ActivateSuperBeaconTrigger
/*    */   extends SimpleCriterionTrigger<ActivateSuperBeaconTrigger.TriggerInstance>
/*    */ {
/* 17 */   private static final Identifier ID = Identifier.fromNamespaceAndPath("witherstormmod", "activate_super_beacon");
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getId() {
/* 22 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected TriggerInstance createInstance(JsonObject obj, ContextAwarePredicate player, DeserializationContext contet) {
/* 28 */     MinMaxBounds.Ints totalActivated = MinMaxBounds.Ints.fromJson(obj.get("total_activated"));
/* 29 */     return new TriggerInstance(player, totalActivated);
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayer player, int totalActivated) {
/* 34 */     trigger(player, instance -> instance.matches(totalActivated));
/*    */   }
/*    */ 
/*    */   
/*    */   public static class TriggerInstance
/*    */     extends AbstractCriterionTriggerInstance
/*    */   {
/*    */     private final MinMaxBounds.Ints totalActivated;
/*    */ 
/*    */     
/*    */     public TriggerInstance(ContextAwarePredicate player, MinMaxBounds.Ints totalActivated) {
/* 45 */       super(ActivateSuperBeaconTrigger.ID, player);
/* 46 */       this.totalActivated = totalActivated;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(int activated) {
/* 51 */       return this.totalActivated.matches(activated);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject serializeToJson(SerializationContext context) {
/* 57 */       JsonObject obj = super.serializeToJson(context);
/* 58 */       obj.add("level", this.totalActivated.serializeToJson());
/* 59 */       return obj;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\ActivateSuperBeaconTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */