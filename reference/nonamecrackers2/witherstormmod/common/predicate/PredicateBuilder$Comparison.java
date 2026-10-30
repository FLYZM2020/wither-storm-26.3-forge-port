/*     */ package nonamecrackers2.witherstormmod.common.predicate;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum Comparison
/*     */ {
/*  78 */   OR
/*     */   {
/*     */     
/*     */     protected <T> Predicate<T> combine(Predicate<T> current, Predicate<T> other)
/*     */     {
/*  83 */       return current.or(other);
/*     */     }
/*     */   },
/*  86 */   AND
/*     */   {
/*     */     
/*     */     protected <T> Predicate<T> combine(Predicate<T> current, Predicate<T> other)
/*     */     {
/*  91 */       return current.and(other);
/*     */     }
/*     */   };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> Predicate<T> combine(List<Predicate<T>> predicates) {
/*  99 */     if (predicates.isEmpty())
/* 100 */       return o -> true; 
/* 101 */     Predicate<T> base = predicates.get(0);
/* 102 */     for (int i = 1; i < predicates.size(); i++)
/* 103 */       base = combine(base, predicates.get(i)); 
/* 104 */     return base;
/*     */   }
/*     */   
/*     */   protected abstract <T> Predicate<T> combine(Predicate<T> paramPredicate1, Predicate<T> paramPredicate2);
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\predicate\PredicateBuilder$Comparison.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */