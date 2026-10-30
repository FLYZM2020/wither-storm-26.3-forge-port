/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class LookAtFormidibombGoal
/*    */   extends Goal
/*    */ {
/*    */   protected final WitherStormEntity storm;
/*    */   private FormidibombEntity target;
/*    */   
/*    */   public LookAtFormidibombGoal(WitherStormEntity storm) {
/* 17 */     this.storm = storm;
/* 18 */     setFlags(EnumSet.of(Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 24 */     if (this.storm.canBeFormidibombed(false)) {
/*    */       
/* 26 */       this.target = this.storm.getFormidibomb();
/* 27 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canContinueToUse() {
/* 38 */     return canUse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void start() {
/* 44 */     if (!this.storm.shouldIgnoreFormidibomb)
/* 45 */       this.storm.getHeadManager().getHead(0).doRoar(false); 
/* 46 */     super.start();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void stop() {
/* 52 */     if (this.target != null)
/* 53 */       this.target.setNoGravity(false); 
/* 54 */     this.target = null;
/* 55 */     super.stop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 61 */     this.storm.getLookControl().setLookAt((Entity)this.target, 30.0F, 30.0F);
/* 62 */     Vec3 entityPos = this.target.position();
/* 63 */     Vec3 headPos = this.storm.getHeadPos(0);
/* 64 */     Vec3 motion = headPos.subtract(entityPos).normalize();
/* 65 */     this.target.setNoGravity(true);
/* 66 */     this.target.setDeltaMovement(motion.multiply(0.1D, 0.1D, 0.1D));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasTarget() {
/* 71 */     return (this.target != null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\LookAtFormidibombGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */