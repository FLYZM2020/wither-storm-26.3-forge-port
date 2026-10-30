/*     */ package nonamecrackers2.witherstormmod.common.entity.bossfight;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ 
/*     */ public class BossfightPhase<T extends Entity> {
/*     */   @Nullable
/*     */   private BossfightPhase<T> parent;
/*     */   private boolean hasFixedTime;
/*     */   private int time;
/*     */   private Predicate<T> shouldMoveToNextPhase;
/*     */   private Consumer<T> initAction;
/*     */   private Consumer<T> finishAction = entity -> {
/*     */     
/*     */     };
/*     */   private BiConsumer<Integer, T> action = (time, entity) -> {
/*     */     
/*     */     };
/*     */   
/*     */   public BossfightPhase(Consumer<T> initAction, Predicate<T> shouldMoveToNextPhase) {
/*  23 */     this.initAction = initAction;
/*  24 */     this.shouldMoveToNextPhase = shouldMoveToNextPhase;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightPhase(Predicate<T> shouldMoveToNextPhase) {
/*  29 */     this(entity -> {  }shouldMoveToNextPhase);
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightPhase(Consumer<T> initAction, int time) {
/*  34 */     this(initAction, entity -> false);
/*  35 */     setFixedTime(time);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> BossfightPhase<T> blank() {
/*  40 */     return new BossfightPhase<>(entity -> false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> BossfightPhase<T> copyOf(BossfightPhase<T> parent, Consumer<T> initAction, Predicate<T> shouldMoveToNextPhase) {
/*  45 */     BossfightPhase<T> phase = (new BossfightPhase<>(parent.initAction.andThen(initAction), parent.shouldMoveToNextPhase.or(shouldMoveToNextPhase))).setTickAction(parent.action).setFinishAction(parent.finishAction);
/*  46 */     if (parent.hasFixedTime)
/*  47 */       phase.setFixedTime(parent.time); 
/*  48 */     phase.parent = parent;
/*  49 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightPhase<T> setTickAction(BiConsumer<Integer, T> action) {
/*  54 */     this.action = action;
/*  55 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightPhase<T> setFinishAction(Consumer<T> action) {
/*  60 */     this.finishAction = action;
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightPhase<T> setFixedTime(int time) {
/*  66 */     this.hasFixedTime = true;
/*  67 */     this.time = time;
/*  68 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(BossfightManager<T> manager, T entity) {
/*  73 */     this.initAction.accept(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(BossfightManager<T> manager, T entity) {
/*  78 */     this.action.accept(Integer.valueOf(manager.getTicksSincePhaseInit()), entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finish(BossfightManager<T> manager, T entity) {
/*  83 */     this.finishAction.accept(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldAdvance(BossfightManager<T> manager, T entity) {
/*  88 */     if (this.hasFixedTime)
/*  89 */       return (manager.getTicksSincePhaseInit() > this.time); 
/*  90 */     return this.shouldMoveToNextPhase.test(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  96 */     if (super.equals(obj))
/*     */     {
/*  98 */       return true;
/*     */     }
/* 100 */     if (this.parent != null) {
/*     */       
/* 102 */       BossfightPhase<T> current = this.parent;
/* 103 */       while (current != null) {
/*     */         
/* 105 */         if (current == obj)
/* 106 */           return true; 
/* 107 */         current = current.parent;
/*     */       } 
/* 109 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\bossfight\BossfightPhase.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */