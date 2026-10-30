/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.pullbehavior;
/*    */ 
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.monster.cubemob.Slime;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class SlimePullBehavior
/*    */   extends WitherStormPullBehavior<Slime>
/*    */ {
/*    */   private static final double MAX_SLIME_SPEED = 5.0D;
/*    */   private static final double SLIME_ROTATION_SPEED = 0.25D;
/*    */   
/*    */   public Vec3 pullEntity(Slime slime, WitherStormEntity storm, Vec3 absorptionPoint, Vec3 defaultVelocity, double defaultSpeed) {
/* 18 */     if (slime.position().distanceTo(absorptionPoint) > storm.getUnmodifiedWidth() * 1.5D) {
/*    */       
/* 20 */       Vec3 rotationVector = absorptionPoint.subtract(slime.position()).normalize().cross(new Vec3(0.0D, -1.0D, 0.0D)).normalize().scale(0.25D);
/* 21 */       Vec3 delta = absorptionPoint.subtract(slime.position()).normalize();
/* 22 */       Vec3 itemVelocity = delta.scale(defaultSpeed).add(rotationVector);
/*    */       
/* 24 */       if (itemVelocity.length() > 5.0D)
/* 25 */         itemVelocity = itemVelocity.normalize().scale(5.0D); 
/* 26 */       return itemVelocity;
/*    */     } 
/*    */ 
/*    */     
/* 30 */     return defaultVelocity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getSpeed(Slime entity, WitherStormEntity storm, Vec3 absorptionPoint) {
/* 37 */     double speed = 0.375D;
/* 38 */     double configSpeedModifier = ((Double)WitherStormModConfig.SERVER.blockClusterPullSpeedModifier.get()).doubleValue();
/* 39 */     speed *= configSpeedModifier;
/* 40 */     speed *= Mth.clamp(entity.position().distanceTo(absorptionPoint) / configSpeedModifier, 0.1D, 1.0D);
/* 41 */     return speed;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canPullIn(Slime entity, WitherStormEntity storm) {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean doClientsideVelocityUpdates(Slime entity, WitherStormEntity storm) {
/* 53 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\pullbehavior\SlimePullBehavior.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */