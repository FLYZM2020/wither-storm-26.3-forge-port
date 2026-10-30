/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.DeserializationContext;
/*    */ import net.minecraft.advancements.critereon.MinMaxBounds;
/*    */ import net.minecraft.advancements.critereon.SerializationContext;
/*    */ import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ 
/*    */ 
/*    */ public class ActivateSuperBeaconTrigger
/*    */   extends SimpleCriterionTrigger<ActivateSuperBeaconTrigger.TriggerInstance>
/*    */ {
/* 17 */   private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "activate_super_beacon");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation m_7295_() {
/* 22 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected TriggerInstance createInstance(JsonObject obj, ContextAwarePredicate player, DeserializationContext contet) {
/* 28 */     MinMaxBounds.Ints totalActivated = MinMaxBounds.Ints.m_55373_(obj.get("total_activated"));
/* 29 */     return new TriggerInstance(player, totalActivated);
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayer player, int totalActivated) {
/* 34 */     m_66234_(player, instance -> instance.matches(totalActivated));
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
/* 51 */       return this.totalActivated.m_55390_(activated);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject m_7683_(SerializationContext context) {
/* 57 */       JsonObject obj = super.m_7683_(context);
/* 58 */       obj.add("level", this.totalActivated.m_55328_());
/* 59 */       return obj;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\ActivateSuperBeaconTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */