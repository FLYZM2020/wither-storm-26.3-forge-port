/*    */ package nonamecrackers2.witherstormmod.common.entity.goal.symbiont;
/*    */ 
/*    */ import net.minecraft.util.Util;
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
/*    */   public boolean canUse() {
/* 25 */     LivingEntity target = this.entity.getTarget();
/* 26 */     if (target != null && target.isAlive()) {
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
/*    */   public boolean canContinueToUse() {
/* 42 */     LivingEntity target = this.entity.getTarget();
/* 43 */     return (target != null && target.isAlive() && this.entity.canPickSpell());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void start() {
/* 49 */     this.entity.setSpell(getRandomSpell(this.entity.getRandom(), this.entity.getSpell()));
/* 50 */     (this.entity.getUseSpellGoal()).nextAttackTickCount = this.entity.tickCount + 40 + this.entity.getRandom().nextInt(20);
/* 51 */     this.entity.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_PREPARE_SPELL.get(), 4.0F, 1.0F);
/* 52 */     this.entity.setNextSpellPickCount(400 + this.entity.getRandom().nextInt(400) - (this.entity.shouldIncreaseDifficulty() ? 320 : 0));
/* 53 */     if (this.entity.shouldNotGoOverHalfHealth() && this.entity.getHealth() / this.entity.getMaxHealth() <= 0.5F) {
/* 54 */       this.entity.setHalfHealthLimit(false);
/*    */     }
/*    */   }
/*    */   
/*    */   public static SpellType getRandomSpell(RandomSource random, SpellType toRemove) {
/* 59 */     return (SpellType)Util.getRandom(((IForgeRegistry)WitherStormModRegistries.SPELL_TYPES.get()).getValues().stream().filter(s -> (s != WitherStormModSymbiontSpellTypes.EMPTY.get() && s != toRemove)).toList(), random);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\symbiont\PrepareSpellGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */