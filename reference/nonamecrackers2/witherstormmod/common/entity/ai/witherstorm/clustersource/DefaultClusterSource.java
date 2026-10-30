/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.clustersource;
/*    */ 
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.phys.Vec2;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class DefaultClusterSource
/*    */   extends BlockClusterSource
/*    */ {
/*    */   public DefaultClusterSource() {
/* 13 */     super(256);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float getClusterSizeRadius(WitherStormEntity storm) {
/* 19 */     return storm.getClusterRadius() + ((Integer)WitherStormModConfig.SERVER.clusterSizeModifier.get()).intValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int calculateShakeTime(WitherStormEntity storm, RandomSource random) {
/* 25 */     return random.m_188503_(10) + 20;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Vec2 calculateRotationDelta(WitherStormEntity storm, RandomSource random) {
/* 31 */     return new Vec2(random.m_188503_(20) * 0.1F / 2.0F, random.m_188503_(20) * 0.1F / 2.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getPickupInterval(WitherStormEntity storm) {
/* 37 */     if (storm.getPhase() > 3 && storm.shouldSpeedUp())
/* 38 */       return ((Integer)WitherStormModConfig.SERVER.devourerClusterPickupInterval.get()).intValue() * 4; 
/* 39 */     if (storm.getPhase() < 6) {
/* 40 */       return ((Integer)WitherStormModConfig.SERVER.clusterPickupInterval.get()).intValue();
/*    */     }
/* 42 */     return ((Integer)WitherStormModConfig.SERVER.devourerClusterPickupInterval.get()).intValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canUse(WitherStormEntity storm) {
/* 48 */     return (storm.getPhase() >= 4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\clustersource\DefaultClusterSource.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */