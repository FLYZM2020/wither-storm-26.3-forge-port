/*    */ package nonamecrackers2.witherstormmod.common.predicate;
/*    */ 
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ 
/*    */ public class EntityPredicateBuilder<T extends Entity>
/*    */   extends PredicateBuilder<T, EntityPredicateBuilder<T>>
/*    */ {
/*    */   private EntityPredicateBuilder(PredicateBuilder.Comparison comparisonMethod) {
/* 11 */     super(comparisonMethod);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static <T extends Entity> EntityPredicateBuilder<T> or() {
/* 22 */     return new EntityPredicateBuilder<>(PredicateBuilder.Comparison.OR);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static <T extends Entity> EntityPredicateBuilder<T> and() {
/* 33 */     return new EntityPredicateBuilder<>(PredicateBuilder.Comparison.AND);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityPredicateBuilder<T> isExactly(EntityType<? extends T> type) {
/* 44 */     return addTest(e -> e.m_6095_().equals(type));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityPredicateBuilder<T> isNotExactly(EntityType<? extends T> type) {
/* 55 */     return addTest(e -> !e.m_6095_().equals(type));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityPredicateBuilder<T> isTag(TagKey<EntityType<?>> tag) {
/* 66 */     return addTest(e -> e.m_6095_().m_204039_(tag));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityPredicateBuilder<T> isNotTag(TagKey<EntityType<?>> tag) {
/* 77 */     return addTest(e -> !e.m_6095_().m_204039_(tag));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\predicate\EntityPredicateBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */