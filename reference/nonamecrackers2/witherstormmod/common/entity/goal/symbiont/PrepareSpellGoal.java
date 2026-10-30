/*    */ package nonamecrackers2.witherstormmod.common.entity.goal.symbiont;
/*    */ 
/*    */ import net.minecraft.Util;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSymbiontSpellTypes;
/*    */ 
/*    */ public class PrepareSpellGoal extends Goal {
/*    */   protected final WitheredSymbiontEntity entity;
/*    */   
/*    */   public PrepareSpellGoal(WitheredSymbiontEntity entity) {
/* 19 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8036_() {
/* 25 */     LivingEntity target = this.entity.m_5448_();
/* 26 */     if (target != null && target.m_6084_()) {
/*    */       
/* 28 */       if (this.entity.isCastingSpell()) {
/* 29 */         return false;
/*    */       }
/* 31 */       return this.entity.canPickSpell();
/*    */     } 
/*    */ 
/*    */     
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8045_() {
/* 42 */     LivingEntity target = this.entity.m_5448_();
/* 43 */     return (target != null && target.m_6084_() && this.entity.canPickSpell());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_8056_() {
/* 49 */     this.entity.setSpell(getRandomSpell(this.entity.m_217043_(), this.entity.getSpell()));
/* 50 */     (this.entity.getUseSpellGoal()).nextAttackTickCount = this.entity.f_19797_ + 40 + this.entity.m_217043_().m_188503_(20);
/* 51 */     this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_PREPARE_SPELL.get(), 4.0F, 1.0F);
/* 52 */     this.entity.setNextSpellPickCount(400 + this.entity.m_217043_().m_188503_(400) - (this.entity.shouldIncreaseDifficulty() ? 320 : 0));
/* 53 */     if (this.entity.shouldNotGoOverHalfHealth() && this.entity.m_21223_() / this.entity.m_21233_() <= 0.5F) {
/* 54 */       this.entity.setHalfHealthLimit(false);
/*    */     }
/*    */   }
/*    */   
/*    */   public static SpellType getRandomSpell(RandomSource random, SpellType toRemove) {
/* 59 */     return (SpellType)Util.m_214621_(((IForgeRegistry)WitherStormModRegistries.SPELL_TYPES.get()).getValues().stream().filter(s -> (s != WitherStormModSymbiontSpellTypes.EMPTY.get() && s != toRemove)).toList(), random);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\symbiont\PrepareSpellGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */