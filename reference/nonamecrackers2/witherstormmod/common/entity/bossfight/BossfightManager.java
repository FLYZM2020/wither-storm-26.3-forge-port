/*     */ package nonamecrackers2.witherstormmod.common.entity.bossfight;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ 
/*     */ public class BossfightManager<T extends Entity>
/*     */ {
/*     */   private final T entity;
/*  13 */   private final Int2ObjectMap<AdvanceableBossfightPhase<T>> phases = (Int2ObjectMap<AdvanceableBossfightPhase<T>>)new Int2ObjectOpenHashMap();
/*     */   @Nullable
/*     */   private AdvanceableBossfightPhase<T> currentPhase;
/*     */   private int phaseIndex;
/*     */   private int ticksSincePhaseInit;
/*     */   
/*     */   private BossfightManager(BossfightPhase<T> defaultPhase, AdvanceableBossfightPhase.PhaseAdvancement advancement, int next, T entity) {
/*  20 */     this.entity = entity;
/*  21 */     addPhase(0, defaultPhase, advancement, next);
/*  22 */     setCurrentPhase(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightManager(BossfightPhase<T> defaultPhase, AdvanceableBossfightPhase.PhaseAdvancement advancement, T entity) {
/*  27 */     this(defaultPhase, advancement, 0, entity);
/*  28 */     if (advancement == AdvanceableBossfightPhase.PhaseAdvancement.SPECIFIED) {
/*  29 */       throw new IllegalArgumentException("Use BossFightManager(defaultPhase, next, entity) instead");
/*     */     }
/*     */   }
/*     */   
/*     */   public BossfightManager(BossfightPhase<T> defaultPhase, int next, T entity) {
/*  34 */     this(defaultPhase, AdvanceableBossfightPhase.PhaseAdvancement.SPECIFIED, next, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightManager(BossfightPhase<T> defaultPhase, T entity) {
/*  39 */     this(defaultPhase, AdvanceableBossfightPhase.PhaseAdvancement.NEXT, 0, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   private BossfightManager<T> addPhase(int key, BossfightPhase<T> phase, AdvanceableBossfightPhase.PhaseAdvancement advancement, int next) {
/*  44 */     this.phases.put(key, new AdvanceableBossfightPhase<>(phase, advancement, key));
/*  45 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightManager<T> addPhase(int key, BossfightPhase<T> phase, AdvanceableBossfightPhase.PhaseAdvancement advancement) {
/*  50 */     if (advancement == AdvanceableBossfightPhase.PhaseAdvancement.SPECIFIED)
/*  51 */       throw new IllegalArgumentException("Use addPhase(key, phase, next) instead"); 
/*  52 */     return addPhase(key, phase, advancement, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightManager<T> addPhase(int key, BossfightPhase<T> phase, int next) {
/*  57 */     return addPhase(key, phase, AdvanceableBossfightPhase.PhaseAdvancement.SPECIFIED, next);
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightManager<T> addPhase(int key, BossfightPhase<T> phase) {
/*  62 */     return addPhase(key, phase, AdvanceableBossfightPhase.PhaseAdvancement.NEXT, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  67 */     this.ticksSincePhaseInit++;
/*  68 */     this.currentPhase.getPhase().tick(this, this.entity);
/*  69 */     if (this.currentPhase.getPhase().shouldAdvance(this, this.entity)) {
/*  70 */       this.currentPhase.advance(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void goToNextPhase() {
/*  75 */     int nextPhase = this.phaseIndex + 1;
/*  76 */     if (nextPhase > this.phases.size() - 1)
/*  77 */       nextPhase = 0; 
/*  78 */     setCurrentPhase(nextPhase);
/*     */   }
/*     */ 
/*     */   
/*     */   public void goToPreviousPhase() {
/*  83 */     int nextPhase = this.phaseIndex - 1;
/*  84 */     if (nextPhase < 0)
/*  85 */       nextPhase = this.phases.size() - 1; 
/*  86 */     setCurrentPhase(nextPhase);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentPhase(int key) {
/*  91 */     if (this.currentPhase != null)
/*  92 */       this.currentPhase.getPhase().finish(this, this.entity); 
/*  93 */     this.phaseIndex = key;
/*  94 */     this.currentPhase = (AdvanceableBossfightPhase<T>)this.phases.get(key);
/*  95 */     this.currentPhase.getPhase().init(this, this.entity);
/*  96 */     this.ticksSincePhaseInit = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentFromNextInOrder(BossfightPhase<T> phase) {
/* 101 */     for (ObjectIterator<Int2ObjectMap.Entry<AdvanceableBossfightPhase<T>>> objectIterator = this.phases.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<AdvanceableBossfightPhase<T>> entry = objectIterator.next();
/*     */       
/* 103 */       AdvanceableBossfightPhase<T> value = (AdvanceableBossfightPhase<T>)entry.getValue();
/* 104 */       if (value.getPhase() == phase) {
/*     */         
/* 106 */         setCurrentPhase(entry.getIntKey());
/*     */         break;
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightPhase<T> getPhase(int key) {
/* 114 */     return ((AdvanceableBossfightPhase<T>)this.phases.get(key)).getPhase();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicksSincePhaseInit() {
/* 119 */     return this.ticksSincePhaseInit;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightPhase<T> getCurrentPhase() {
/* 124 */     return this.currentPhase.getPhase();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentPhaseIndex() {
/* 129 */     return this.phaseIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag write() {
/* 134 */     CompoundTag compound = new CompoundTag();
/* 135 */     compound.m_128405_("CurrentPhase", this.phaseIndex);
/* 136 */     compound.m_128405_("PhaseTicks", this.ticksSincePhaseInit);
/* 137 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundTag compound) {
/* 142 */     setCurrentPhase(compound.m_128451_("CurrentPhase"));
/* 143 */     this.ticksSincePhaseInit = compound.m_128451_("PhaseTicks");
/*     */   }
/*     */ 
/*     */   
/*     */   public static class AdvanceableBossfightPhase<T extends Entity>
/*     */   {
/*     */     private final BossfightPhase<T> phase;
/*     */     private final PhaseAdvancement phaseAdvancement;
/*     */     private final int nextPhase;
/*     */     
/*     */     public AdvanceableBossfightPhase(BossfightPhase<T> phase, PhaseAdvancement advancement, int next) {
/* 154 */       this.phase = phase;
/* 155 */       this.phaseAdvancement = advancement;
/* 156 */       this.nextPhase = next;
/*     */     }
/*     */ 
/*     */     
/*     */     public void advance(BossfightManager<T> manager) {
/* 161 */       this.phaseAdvancement.toNextPhase(manager, this);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getPhaseToGoTo() {
/* 166 */       return this.nextPhase;
/*     */     }
/*     */ 
/*     */     
/*     */     public BossfightPhase<T> getPhase() {
/* 171 */       return this.phase;
/*     */     }
/*     */     
/*     */     public enum PhaseAdvancement
/*     */     {
/* 176 */       NEXT
/*     */       {
/*     */         
/*     */         public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */         {
/* 181 */           manager.goToNextPhase();
/*     */         }
/*     */       },
/* 184 */       PREVIOUS
/*     */       {
/*     */         
/*     */         public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */         {
/* 189 */           manager.goToPreviousPhase();
/*     */         }
/*     */       },
/* 192 */       SPECIFIED
/*     */       {
/*     */         
/*     */         public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */         {
/* 197 */           manager.setCurrentPhase(phase.getPhaseToGoTo()); } }; public abstract <T extends Entity> void toNextPhase(BossfightManager<T> param2BossfightManager, BossfightManager.AdvanceableBossfightPhase<T> param2AdvanceableBossfightPhase); } } public enum PhaseAdvancement { NEXT { public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase) { manager.goToNextPhase(); } }, PREVIOUS { public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase) { manager.setCurrentPhase(phase.getPhaseToGoTo()); } }, SPECIFIED { public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase) { manager.goToPreviousPhase(); } }; public abstract <T extends Entity> void toNextPhase(BossfightManager<T> param1BossfightManager, BossfightManager.AdvanceableBossfightPhase<T> param1AdvanceableBossfightPhase); } enum null { public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase) { manager.goToNextPhase(); } } enum null { public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase) { manager.goToPreviousPhase(); } } enum null { public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase) { manager.setCurrentPhase(phase.getPhaseToGoTo()); }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\bossfight\BossfightManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */