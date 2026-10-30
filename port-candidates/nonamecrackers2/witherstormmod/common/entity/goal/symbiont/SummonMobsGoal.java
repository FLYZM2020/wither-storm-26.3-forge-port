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
/* 30 */     setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.TARGET, Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 36 */     LivingEntity target = this.entity.getTarget();
/* 37 */     if (target != null && target.isAlive()) {
/* 38 */       return !this.spawnTypes.isEmpty();
/*    */     }
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canContinueToUse() {
/* 46 */     return (canUse() && this.time > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void start() {
/* 52 */     this.time = 60 + this.entity.getRandom().nextInt(60) + (this.entity.shouldIncreaseDifficulty() ? 40 : 0);
/* 53 */     this.entity.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_SUMMON.get(), 4.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 59 */     if (this.time > 0) {
/*    */       
/* 61 */       this.time--;
/* 62 */       if (this.time % 10 == 0) {
/*    */         
/* 64 */         Mob mob = WorldUtil.summonRandomMob((ServerLevel)this.entity.level(), this.entity.blockPosition(), this.entity.getRandom(), 16, this.entity.shouldIncreaseDifficulty() ? this.difficultSpawnTypes : this.spawnTypes, this.entity.shouldIncreaseDifficulty());
/* 65 */         if (mob != null) {
/* 66 */           addAttributes(mob);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop() {
/* 74 */     this.entity.nextStage();
/*    */   }
/*    */ 
/*    */   
/*    */   private static void addAttributes(Mob mob) {
/* 79 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.getAttribute(Attributes.MAX_HEALTH))).addPermanentModifier(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.getRandom().nextDouble() + 0.5D) * 2.0D), AttributeModifier.Operation.ADDITION));
/* 80 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.getAttribute(Attributes.MOVEMENT_SPEED))).addPermanentModifier(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08D, AttributeModifier.Operation.ADDITION));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\symbiont\SummonMobsGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */