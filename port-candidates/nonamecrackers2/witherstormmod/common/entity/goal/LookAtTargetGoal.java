/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.function.Function;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LookAtTargetGoal<T extends LivingEntity & WitherStormBase>
/*    */   extends Goal
/*    */ {
/*    */   protected final T mob;
/*    */   protected final int headIndex;
/*    */   protected final Function<T, Integer> lookStepsGetter;
/*    */   protected LivingEntity target;
/*    */   
/*    */   public LookAtTargetGoal(T mob, int headIndex, Function<T, Integer> lookStepsGetter) {
/* 21 */     this.mob = mob;
/* 22 */     this.headIndex = headIndex;
/* 23 */     this.lookStepsGetter = lookStepsGetter;
/* 24 */     setFlags(EnumSet.of(Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 30 */     LivingEntity target = getTarget();
/* 31 */     if (target != null && target.isAlive()) {
/*    */       
/* 33 */       this.target = target;
/* 34 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected LivingEntity getTarget() {
/* 44 */     return ((WitherStormBase)this.mob).getTarget(this.headIndex);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void stop() {
/* 50 */     this.target = null;
/* 51 */     ((WitherStormBase)this.mob).setLookAt(this.headIndex, null);
/* 52 */     super.stop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 58 */     ((WitherStormBase)this.mob).setLookAt(this.headIndex, this.target.getEyePosition(), ((Integer)this.lookStepsGetter.apply(this.mob)).intValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\LookAtTargetGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */