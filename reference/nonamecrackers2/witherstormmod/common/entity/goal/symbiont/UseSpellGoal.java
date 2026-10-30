/*    */ package nonamecrackers2.witherstormmod.common.entity.goal.symbiont;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ 
/*    */ 
/*    */ public class UseSpellGoal
/*    */   extends Goal
/*    */ {
/*    */   protected final WitheredSymbiontEntity entity;
/*    */   public int nextAttackTickCount;
/*    */   
/*    */   public UseSpellGoal(WitheredSymbiontEntity entity) {
/* 18 */     this.entity = entity;
/* 19 */     m_7021_(EnumSet.of(Goal.Flag.MOVE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8036_() {
/* 25 */     LivingEntity target = this.entity.m_5448_();
/* 26 */     if (target != null && target.m_6084_()) {
/*    */       
/* 28 */       if (this.entity.isCastingSpell())
/* 29 */         return false; 
/* 30 */       if (!this.entity.hasSpell())
/* 31 */         return false; 
/* 32 */       if (this.entity.getSpellInstance() == null) {
/* 33 */         return false;
/*    */       }
/* 35 */       return (this.entity.f_19797_ > this.nextAttackTickCount);
/*    */     } 
/*    */ 
/*    */     
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8045_() {
/* 46 */     LivingEntity target = this.entity.m_5448_();
/* 47 */     return ((target != null && target.m_6084_() && this.entity.hasSpell() && this.entity.f_19797_ >= this.nextAttackTickCount) || this.entity.isCastingSpell());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_8056_() {
/* 53 */     float effectiveDifficulty = this.entity.m_9236_().m_6436_(this.entity.m_20183_()).m_19056_();
/* 54 */     int spellDelay = this.entity.getSpellInstance().getDelay(this.entity.m_217043_(), effectiveDifficulty + (this.entity.shouldIncreaseDifficulty() ? 60 : false));
/* 55 */     if (spellDelay < this.entity.getSpell().spellTime() + 10)
/* 56 */       spellDelay = this.entity.getSpell().spellTime() + 10; 
/* 57 */     this.nextAttackTickCount = this.entity.f_19797_ + spellDelay;
/* 58 */     this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_CAST_SPELL.get(), 4.0F, 1.0F);
/* 59 */     this.entity.beginSpellCasting();
/* 60 */     if (this.entity.m_5448_() instanceof net.minecraft.world.entity.player.Player)
/* 61 */       this.entity.spellUsed(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\symbiont\UseSpellGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */