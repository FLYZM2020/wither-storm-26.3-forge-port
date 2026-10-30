/*    */ package nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior;
/*    */ 
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ public abstract class WitherStormPullBehavior<T extends Entity>
/*    */ {
/*    */   private final double defaultSpeed;
/*    */   
/*    */   public WitherStormPullBehavior(double speed) {
/* 13 */     this.defaultSpeed = speed;
/*    */   }
/*    */ 
/*    */   
/*    */   public WitherStormPullBehavior() {
/* 18 */     this(0.5D);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract Vec3 pullEntity(T paramT, WitherStormEntity paramWitherStormEntity, Vec3 paramVec31, Vec3 paramVec32, double paramDouble);
/*    */   
/*    */   public double getSpeed(T entity, WitherStormEntity storm, Vec3 absorptionPoint) {
/* 25 */     return this.defaultSpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPullIn(T entity, WitherStormEntity storm) {
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean doClientsideVelocityUpdates(T entity, WitherStormEntity storm) {
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\ai\witherstorm\pullbehavior\WitherStormPullBehavior.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */