/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.pullbehavior;
/*    */ 
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.item.ItemEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class ItemPullBehavior
/*    */   extends WitherStormPullBehavior<ItemEntity>
/*    */ {
/*    */   private static final double MAX_ITEM_SPEED = 5.0D;
/*    */   private static final double ITEM_ROTATION_SPEED = 0.25D;
/*    */   
/*    */   public Vec3 pullEntity(ItemEntity itemEntity, WitherStormEntity storm, Vec3 absorptionPoint, Vec3 defaultVelocity, double defaultSpeed) {
/* 18 */     if (itemEntity.position().distanceTo(absorptionPoint) > storm.getUnmodifiedWidth() * 1.5D && absorptionPoint.distanceTo(itemEntity.position()) > 4.0D) {
/*    */       
/* 20 */       Vec3 rotationVector = absorptionPoint.subtract(itemEntity.position()).normalize().cross(new Vec3(0.0D, -1.0D, 0.0D)).normalize().scale(0.25D);
/* 21 */       Vec3 delta = absorptionPoint.subtract(itemEntity.position()).normalize();
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
/*    */   public double getSpeed(ItemEntity entity, WitherStormEntity storm, Vec3 absorptionPoint) {
/* 37 */     double speed = 0.375D;
/* 38 */     double configSpeedModifier = ((Double)WitherStormModConfig.SERVER.blockClusterPullSpeedModifier.get()).doubleValue();
/* 39 */     speed *= configSpeedModifier;
/* 40 */     speed *= Mth.clamp(entity.position().distanceTo(absorptionPoint) / configSpeedModifier, 0.1D, 1.0D);
/* 41 */     return speed;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canPullIn(ItemEntity entity, WitherStormEntity storm) {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean doClientsideVelocityUpdates(ItemEntity entity, WitherStormEntity storm) {
/* 53 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\pullbehavior\ItemPullBehavior.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */