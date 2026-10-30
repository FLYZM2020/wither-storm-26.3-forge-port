/*     */ package nonamecrackers2.witherstormmod.common.entity.bossfight;
/*     */ 
/*     */ import net.minecraft.world.entity.Entity;
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
/*     */ 
/*     */ public class AdvanceableBossfightPhase<T extends Entity>
/*     */ {
/*     */   private final BossfightPhase<T> phase;
/*     */   private final PhaseAdvancement phaseAdvancement;
/*     */   private final int nextPhase;
/*     */   
/*     */   public AdvanceableBossfightPhase(BossfightPhase<T> phase, PhaseAdvancement advancement, int next) {
/* 154 */     this.phase = phase;
/* 155 */     this.phaseAdvancement = advancement;
/* 156 */     this.nextPhase = next;
/*     */   }
/*     */ 
/*     */   
/*     */   public void advance(BossfightManager<T> manager) {
/* 161 */     this.phaseAdvancement.toNextPhase(manager, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPhaseToGoTo() {
/* 166 */     return this.nextPhase;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossfightPhase<T> getPhase() {
/* 171 */     return this.phase;
/*     */   }
/*     */   
/*     */   public enum PhaseAdvancement
/*     */   {
/* 176 */     NEXT
/*     */     {
/*     */       
/*     */       public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */       {
/* 181 */         manager.goToNextPhase();
/*     */       }
/*     */     },
/* 184 */     PREVIOUS
/*     */     {
/*     */       
/*     */       public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */       {
/* 189 */         manager.goToPreviousPhase();
/*     */       }
/*     */     },
/* 192 */     SPECIFIED
/*     */     {
/*     */       
/*     */       public <T extends Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */       {
/* 197 */         manager.setCurrentPhase(phase.getPhaseToGoTo());
/*     */       }
/*     */     };
/*     */     
/*     */     public abstract <T extends Entity> void toNextPhase(BossfightManager<T> param2BossfightManager, BossfightManager.AdvanceableBossfightPhase<T> param2AdvanceableBossfightPhase);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\bossfight\BossfightManager$AdvanceableBossfightPhase.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */