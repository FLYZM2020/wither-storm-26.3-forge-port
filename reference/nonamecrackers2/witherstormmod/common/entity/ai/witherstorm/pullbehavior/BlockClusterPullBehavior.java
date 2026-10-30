/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.pullbehavior;
/*     */ 
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*     */ 
/*     */ public class BlockClusterPullBehavior
/*     */   extends WitherStormPullBehavior<BlockClusterEntity> {
/*     */   private static final double HUNCH_MAX_CLUSTER_SPEED = 2.0D;
/*     */   private static final double MAX_CLUSTER_SPEED = 5.0D;
/*     */   private static final double CLUSTER_TIME = 15.0D;
/*     */   
/*     */   public Vec3 pullEntity(BlockClusterEntity cluster, WitherStormEntity storm, Vec3 absorptionPoint, Vec3 defaultVelocity, double defaultSpeed) {
/*  20 */     cluster.setFadePos(storm.m_20183_());
/*  21 */     if (storm.getPhase() > 3) {
/*     */       
/*  23 */       cluster.setFadeStrength(75.0F);
/*  24 */       cluster.setFadeDistanceOffset(20);
/*     */     } 
/*  26 */     if (cluster.m_20182_().m_82554_(absorptionPoint) > storm.getUnmodifiedWidth() * 1.5D) {
/*     */       
/*  28 */       double clusterRotationSpeed = getClusterRotationSpeed(cluster, storm);
/*  29 */       if (storm.getPhase() <= 3) {
/*  30 */         clusterRotationSpeed = 0.2D;
/*     */       }
/*  32 */       boolean rotateCounterClockwise = (((Boolean)WitherStormModConfig.SERVER.canClustersSpiralCounterClockwise.get()).booleanValue() && cluster.m_19880_().contains("RotateClockwise"));
/*  33 */       Vec3 rotationVector = absorptionPoint.m_82546_(cluster.m_20182_()).m_82541_().m_82537_(new Vec3(0.0D, rotateCounterClockwise ? 1.0D : -1.0D, 0.0D)).m_82541_().m_82490_(clusterRotationSpeed);
/*     */       
/*  35 */       if (cluster.createdFromTractorBeam() && cluster.m_20182_().m_82554_(storm.m_146892_()) > 25.0D) {
/*     */         
/*  37 */         int head = cluster.getHeadCreatedFrom();
/*  38 */         if (storm.tractorBeamActive(head)) {
/*     */           
/*  40 */           Vec3 pos = TractorBeamHelper.calculateClosestPoint(cluster.m_20182_(), (LivingEntity)storm, head);
/*  41 */           double distance = Math.sqrt(cluster.m_20182_().m_82557_(pos));
/*  42 */           double distanceFromHead = Math.sqrt(cluster.m_20182_().m_82557_(storm.getHeadPos(head)));
/*  43 */           double tractDistance = 4.0D * (distanceFromHead + 20.0D) * 0.015D;
/*  44 */           double threshold = cluster.getTractorBeamDistanceThreshold() * Mth.m_14008_((distanceFromHead - 60.0D) * 0.1D, 0.0D, 1.0D);
/*  45 */           double tractorAlignSpeed = Mth.m_14008_(distance + threshold - tractDistance, 0.0D, 4.0D);
/*  46 */           Vec3 delta = pos.m_82546_(cluster.m_20182_()).m_82541_().m_82490_(tractorAlignSpeed);
/*  47 */           Vec3 toHeadDelta = storm.getHeadPos(cluster.getHeadCreatedFrom()).m_82546_(cluster.m_20182_()).m_82541_().m_82490_(defaultSpeed);
/*  48 */           return toHeadDelta.m_82549_(delta);
/*     */         } 
/*     */ 
/*     */         
/*  52 */         return defaultVelocity.m_82549_(rotationVector);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  57 */       return defaultVelocity.m_82549_(rotationVector);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  62 */     return defaultVelocity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getClusterRotationSpeed(BlockClusterEntity cluster, WitherStormEntity storm) {
/*  68 */     double clusterRotationSpeed, rotationSpeed = 2.0E-4D + cluster.time * 15.0D * 4.0D;
/*  69 */     int size = cluster.getSize();
/*  70 */     double clusterSize = Math.pow(size, -0.25D);
/*     */     
/*  72 */     if (storm.getPhase() <= 3) {
/*  73 */       clusterRotationSpeed = Math.min(rotationSpeed * 10000.0D + cluster.time * 15.0D * clusterSize, clusterSize);
/*     */     } else {
/*  75 */       clusterRotationSpeed = Math.min(rotationSpeed + cluster.time * 15.0D * clusterSize, clusterSize);
/*     */     } 
/*  77 */     if (clusterRotationSpeed > 5.0D && storm.getPhase() >= 4) {
/*  78 */       clusterRotationSpeed = 5.0D;
/*  79 */     } else if (clusterRotationSpeed > 2.0D && storm.getPhase() <= 3) {
/*  80 */       clusterRotationSpeed = 2.0D;
/*  81 */     }  return clusterRotationSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSpeed(BlockClusterEntity entity, WitherStormEntity storm, Vec3 absorptionPoint) {
/*     */     double speed, configSpeedModifier;
/*  87 */     Vec3 delta = absorptionPoint.m_82546_(entity.m_20182_());
/*  88 */     double distanceToStorm = delta.m_82553_();
/*     */     
/*  90 */     if (storm.getPhase() <= 3) {
/*  91 */       speed = 0.125D;
/*  92 */     } else if (distanceToStorm >= 240.0D && !entity.createdFromTractorBeam()) {
/*  93 */       speed = 0.375D;
/*     */     } else {
/*  95 */       speed = 0.0625D;
/*  96 */     }  speed += entity.time * 0.005D;
/*     */     
/*  98 */     if (entity.createdFromTractorBeam()) {
/*  99 */       configSpeedModifier = ((Double)WitherStormModConfig.SERVER.tractorBeamClusterSpeedModifier.get()).doubleValue();
/*     */     } else {
/* 101 */       configSpeedModifier = ((Double)WitherStormModConfig.SERVER.blockClusterPullSpeedModifier.get()).doubleValue();
/* 102 */     }  speed *= configSpeedModifier;
/* 103 */     speed *= Mth.m_14008_(entity.m_20182_().m_82554_(absorptionPoint) / configSpeedModifier, 0.1D, 1.0D);
/* 104 */     return speed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPullIn(BlockClusterEntity entity, WitherStormEntity storm) {
/* 110 */     return (entity.getShakeTime() <= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doClientsideVelocityUpdates(BlockClusterEntity entity, WitherStormEntity storm) {
/* 116 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\pullbehavior\BlockClusterPullBehavior.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */