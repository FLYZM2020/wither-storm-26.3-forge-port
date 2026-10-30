/*     */ package nonamecrackers2.witherstormmod.common.entity.bossfight;
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
/*     */ public enum PhaseAdvancement
/*     */ {
/* 176 */   NEXT
/*     */   {
/*     */     
/*     */     public <T extends net.minecraft.world.entity.Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */     {
/* 181 */       manager.goToNextPhase();
/*     */     }
/*     */   },
/* 184 */   PREVIOUS
/*     */   {
/*     */     
/*     */     public <T extends net.minecraft.world.entity.Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */     {
/* 189 */       manager.goToPreviousPhase();
/*     */     }
/*     */   },
/* 192 */   SPECIFIED
/*     */   {
/*     */     
/*     */     public <T extends net.minecraft.world.entity.Entity> void toNextPhase(BossfightManager<T> manager, BossfightManager.AdvanceableBossfightPhase<T> phase)
/*     */     {
/* 197 */       manager.setCurrentPhase(phase.getPhaseToGoTo());
/*     */     }
/*     */   };
/*     */   
/*     */   public abstract <T extends net.minecraft.world.entity.Entity> void toNextPhase(BossfightManager<T> paramBossfightManager, BossfightManager.AdvanceableBossfightPhase<T> paramAdvanceableBossfightPhase);
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\bossfight\BossfightManager$AdvanceableBossfightPhase$PhaseAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */