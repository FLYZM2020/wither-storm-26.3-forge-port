/*    */ package nonamecrackers2.witherstormmod.common.advancements.criterion;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
/*    */ import net.minecraft.advancements.critereon.ContextAwarePredicate;
/*    */ import net.minecraft.advancements.critereon.DeserializationContext;
/*    */ import net.minecraft.advancements.critereon.EntityPredicate;
/*    */ import net.minecraft.advancements.critereon.MinMaxBounds;
/*    */ import net.minecraft.advancements.critereon.SerializationContext;
/*    */ import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.level.storage.loot.LootContext;
/*    */ 
/*    */ 
/*    */ public class LinkAmuletTrigger
/*    */   extends SimpleCriterionTrigger<LinkAmuletTrigger.Instance>
/*    */ {
/* 20 */   private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "link_amulet");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation m_7295_() {
/* 25 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext context) {
/* 31 */     ContextAwarePredicate linked = EntityPredicate.m_285855_(object, "linked", context);
/* 32 */     MinMaxBounds.Ints totalLinked = MinMaxBounds.Ints.m_55373_(object.get("total_linked"));
/* 33 */     return new Instance(player, linked, totalLinked);
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayer player, Entity linked, int totalLinked) {
/* 38 */     LootContext context = EntityPredicate.m_36616_(player, linked);
/* 39 */     m_66234_(player, instance -> instance.matches(context, totalLinked));
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Instance
/*    */     extends AbstractCriterionTriggerInstance
/*    */   {
/*    */     private final ContextAwarePredicate linked;
/*    */     
/*    */     private final MinMaxBounds.Ints totalLinked;
/*    */     
/*    */     public Instance(ContextAwarePredicate player, ContextAwarePredicate linked, MinMaxBounds.Ints totalLinked) {
/* 51 */       super(LinkAmuletTrigger.ID, player);
/* 52 */       this.linked = linked;
/* 53 */       this.totalLinked = totalLinked;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(LootContext context, int totalLinked) {
/* 58 */       return (this.linked.m_285831_(context) && this.totalLinked.m_55390_(totalLinked));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject m_7683_(SerializationContext context) {
/* 64 */       JsonObject obj = super.m_7683_(context);
/* 65 */       obj.add("linked", this.linked.m_286026_(context));
/* 66 */       obj.add("total_linked", this.totalLinked.m_55328_());
/* 67 */       return obj;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\advancements\criterion\LinkAmuletTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */