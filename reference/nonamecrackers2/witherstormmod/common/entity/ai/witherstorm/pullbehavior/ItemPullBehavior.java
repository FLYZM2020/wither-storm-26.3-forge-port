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
/* 18 */     if (itemEntity.m_20182_().m_82554_(absorptionPoint) > storm.getUnmodifiedWidth() * 1.5D && absorptionPoint.m_82554_(itemEntity.m_20182_()) > 4.0D) {
/*    */       
/* 20 */       Vec3 rotationVector = absorptionPoint.m_82546_(itemEntity.m_20182_()).m_82541_().m_82537_(new Vec3(0.0D, -1.0D, 0.0D)).m_82541_().m_82490_(0.25D);
/* 21 */       Vec3 delta = absorptionPoint.m_82546_(itemEntity.m_20182_()).m_82541_();
/* 22 */       Vec3 itemVelocity = delta.m_82490_(defaultSpeed).m_82549_(rotationVector);
/*    */       
/* 24 */       if (itemVelocity.m_82553_() > 5.0D)
/* 25 */         itemVelocity = itemVelocity.m_82541_().m_82490_(5.0D); 
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
/* 40 */     speed *= Mth.m_14008_(entity.m_20182_().m_82554_(absorptionPoint) / configSpeedModifier, 0.1D, 1.0D);
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