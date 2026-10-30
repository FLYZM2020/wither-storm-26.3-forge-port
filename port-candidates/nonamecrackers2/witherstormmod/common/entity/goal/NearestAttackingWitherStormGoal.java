/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class NearestAttackingWitherStormGoal
/*    */   extends NearestAttackableTargetGoal<WitherStormEntity> {
/*    */   public NearestAttackingWitherStormGoal(Mob mob, int randomInterval) {
/* 15 */     super(mob, WitherStormEntity.class, randomInterval, true, false, null);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   protected AABB getTargetSearchArea(double range) {
/* 21 */     return this.mob.getBoundingBox().inflate(range, range * 2.0D, range);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void findTarget() {
/* 27 */     super.findTarget();
/* 28 */     LivingEntity livingEntity = this.target; if (livingEntity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)livingEntity;
/*    */       
/* 30 */       boolean flag = true;
/* 31 */       if (storm.getPhase() > 3 && storm.distanceTo((Entity)this.mob) > 30.0D)
/*    */       {
/* 33 */         if (((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.mob, (LivingEntity)storm, 4.0D).getFirst()).booleanValue() && !this.mob.onGround())
/* 34 */           flag = false; 
/*    */       }
/* 36 */       if (flag) {
/* 37 */         this.target = null;
/*    */       } }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   protected double getFollowDistance() {
/* 44 */     return 100.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\NearestAttackingWitherStormGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */