/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*    */ 
/*    */ 
/*    */ public class LookAtDistractionGoal<T extends Mob & WitherStormBase>
/*    */   extends Goal
/*    */ {
/*    */   protected final int headIndex;
/*    */   protected final T entity;
/*    */   protected final Supplier<Double> allowedTargetingRadius;
/*    */   protected Vec3 target;
/*    */   
/*    */   public LookAtDistractionGoal(T entity, int headIndex, Supplier<Double> allowedTargetingRadius) {
/* 20 */     this.entity = entity;
/* 21 */     this.allowedTargetingRadius = allowedTargetingRadius;
/* 22 */     setFlags(EnumSet.of(Goal.Flag.LOOK));
/* 23 */     this.headIndex = headIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public LookAtDistractionGoal(T entity, int headIndex) {
/* 28 */     this(entity, headIndex, () -> Double.valueOf(Double.POSITIVE_INFINITY));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 34 */     Vec3 target = ((WitherStormBase)this.entity).getDistractedPos(this.headIndex);
/* 35 */     if (target != null && target.subtract(this.entity.position()).horizontalDistance() <= ((Double)this.allowedTargetingRadius.get()).doubleValue() && !((WitherStormBase)this.entity).isPosBehindBack(target)) {
/*    */       
/* 37 */       this.target = target;
/* 38 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stop() {
/* 49 */     this.target = null;
/* 50 */     ((WitherStormBase)this.entity).setDistractedPos(this.headIndex, null);
/* 51 */     super.stop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 57 */     ((WitherStormBase)this.entity).setLookAt(this.headIndex, this.target, 10);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\LookAtDistractionGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */