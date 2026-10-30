/*     */ package nonamecrackers2.witherstormmod.common.predicate;
/*     */ 
/*     */ import com.google.common.collect.Lists;
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
/*     */ public abstract class PredicateBuilder<T, B extends PredicateBuilder<T, B>>
/*     */ {
/*  16 */   private final List<Predicate<T>> predicates = Lists.newArrayList();
/*     */   
/*     */   private final Comparison comparisonMethod;
/*     */   
/*     */   protected PredicateBuilder(Comparison comparisonMethod) {
/*  21 */     this.comparisonMethod = comparisonMethod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public B addTest(Predicate<T> predicate) {
/*  29 */     this.predicates.add(predicate);
/*  30 */     return (B)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public B isInstanceOf(Class<?> clazz) {
/*  38 */     return addTest(e -> clazz.isAssignableFrom(e.getClass()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public B isNotInstanceOf(Class<?> clazz) {
/*  46 */     return addTest(e -> !clazz.isAssignableFrom(e.getClass()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public B isExactly(Class<? extends T> clazz) {
/*  54 */     return addTest(e -> e.getClass().equals(clazz));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public B isNotExactly(Class<? extends T> clazz) {
/*  62 */     return addTest(e -> !e.getClass().equals(clazz));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate<T> build() {
/*  73 */     return this.comparisonMethod.combine(this.predicates);
/*     */   }
/*     */   
/*     */   public enum Comparison
/*     */   {
/*  78 */     OR
/*     */     {
/*     */       
/*     */       protected <T> Predicate<T> combine(Predicate<T> current, Predicate<T> other)
/*     */       {
/*  83 */         return current.or(other);
/*     */       }
/*     */     },
/*  86 */     AND
/*     */     {
/*     */       
/*     */       protected <T> Predicate<T> combine(Predicate<T> current, Predicate<T> other)
/*     */       {
/*  91 */         return current.and(other);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> Predicate<T> combine(List<Predicate<T>> predicates) {
/*  99 */       if (predicates.isEmpty())
/* 100 */         return o -> true; 
/* 101 */       Predicate<T> base = predicates.get(0);
/* 102 */       for (int i = 1; i < predicates.size(); i++)
/* 103 */         base = combine(base, predicates.get(i)); 
/* 104 */       return base;
/*     */     }
/*     */     
/*     */     protected abstract <T> Predicate<T> combine(Predicate<T> param1Predicate1, Predicate<T> param1Predicate2);
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected <T> Predicate<T> combine(Predicate<T> current, Predicate<T> other) {
/*     */       return current.or(other);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected <T> Predicate<T> combine(Predicate<T> current, Predicate<T> other) {
/*     */       return current.and(other);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\predicate\PredicateBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */