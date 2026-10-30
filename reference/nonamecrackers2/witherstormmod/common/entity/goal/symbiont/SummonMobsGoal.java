/*    */ package nonamecrackers2.witherstormmod.common.entity.goal.symbiont;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.util.random.SimpleWeightedRandomList;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ 
/*    */ public class SummonMobsGoal
/*    */   extends Goal {
/*    */   protected final WitheredSymbiontEntity entity;
/*    */   protected final SimpleWeightedRandomList<EntityType<? extends Mob>> spawnTypes;
/*    */   protected final SimpleWeightedRandomList<EntityType<? extends Mob>> difficultSpawnTypes;
/*    */   protected int time;
/*    */   
/*    */   public SummonMobsGoal(WitheredSymbiontEntity entity, SimpleWeightedRandomList<EntityType<? extends Mob>> types, SimpleWeightedRandomList<EntityType<? extends Mob>> difficultTypes) {
/* 27 */     this.entity = entity;
/* 28 */     this.spawnTypes = types;
/* 29 */     this.difficultSpawnTypes = difficultTypes;
/* 30 */     m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.TARGET, Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8036_() {
/* 36 */     LivingEntity target = this.entity.m_5448_();
/* 37 */     if (target != null && target.m_6084_()) {
/* 38 */       return !this.spawnTypes.m_146337_();
/*    */     }
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8045_() {
/* 46 */     return (m_8036_() && this.time > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_8056_() {
/* 52 */     this.time = 60 + this.entity.m_217043_().m_188503_(60) + (this.entity.shouldIncreaseDifficulty() ? 40 : 0);
/* 53 */     this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_SUMMON.get(), 4.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_8037_() {
/* 59 */     if (this.time > 0) {
/*    */       
/* 61 */       this.time--;
/* 62 */       if (this.time % 10 == 0) {
/*    */         
/* 64 */         Mob mob = WorldUtil.summonRandomMob((ServerLevel)this.entity.m_9236_(), this.entity.m_20183_(), this.entity.m_217043_(), 16, this.entity.shouldIncreaseDifficulty() ? this.difficultSpawnTypes : this.spawnTypes, this.entity.shouldIncreaseDifficulty());
/* 65 */         if (mob != null) {
/* 66 */           addAttributes(mob);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_8041_() {
/* 74 */     this.entity.nextStage();
/*    */   }
/*    */ 
/*    */   
/*    */   private static void addAttributes(Mob mob) {
/* 79 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.m_21051_(Attributes.f_22276_))).m_22125_(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.m_217043_().m_188500_() + 0.5D) * 2.0D), AttributeModifier.Operation.ADDITION));
/* 80 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.m_21051_(Attributes.f_22279_))).m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08D, AttributeModifier.Operation.ADDITION));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\symbiont\SummonMobsGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */