/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.clustersource;
/*     */ 
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.SoundType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ 
/*     */ public class NatureClusterSource
/*     */   extends BlockClusterSource
/*     */ {
/*     */   public NatureClusterSource() {
/*  19 */     super(256);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shouldntCountToConsumedEntities(WitherStormEntity storm) {
/*  25 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createCluster(WitherStormEntity storm) {
/*  31 */     int phase = storm.getPhase();
/*     */     
/*  33 */     switch (phase) { case 0: 
/*     */       case 1: 
/*     */       case 2:
/*     */       
/*     */       default:
/*  38 */         break; }  int multiClusters = 10;
/*     */ 
/*     */     
/*  41 */     for (int i = 0; i < multiClusters; i++) {
/*  42 */       super.createCluster(storm);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getClusterSizeRadius(WitherStormEntity storm) {
/*  48 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getClusterSearchRadius(WitherStormEntity storm) {
/*  54 */     if (storm.getPhase() <= 3)
/*     */     {
/*  56 */       return storm.entityConsumptionRadiusHunch() + 12;
/*     */     }
/*     */ 
/*     */     
/*  60 */     return storm.getEntityConsumptionRadius() * storm.phaseRadiusMultiplierNature(storm.getPhase());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int calculateShakeTime(WitherStormEntity storm, RandomSource random) {
/*  67 */     int phase = storm.getPhase();
/*     */     
/*  69 */     switch (phase) { case 0: case 1: case 2: case 3:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  75 */       0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidClusterBlock(WitherStormEntity storm, BlockState state) {
/*  82 */     return (!state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST) && state.m_204336_(WitherStormModBlockTags.NATURE_CLUSTER_WHITELIST));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getPickupInterval(WitherStormEntity storm) {
/*  88 */     int phase = storm.getPhase();
/*     */     
/*  90 */     if (((Boolean)WitherStormModConfig.SERVER.constantBlackhole.get()).booleanValue()) {
/*  91 */       return 1;
/*     */     }
/*  93 */     switch (phase) { case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 104 */       100;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onClusterAddedToWorld(WitherStormEntity storm, BlockClusterEntity cluster, BlockPos startPos, BlockState startState) {
/* 111 */     SoundType sound = startState.getSoundType((LevelReader)storm.m_9236_(), startPos, null);
/* 112 */     storm.m_9236_().m_5594_(null, startPos, sound.m_56775_(), SoundSource.BLOCKS, (sound.m_56773_() + 1.0F) / 2.0F, sound.m_56774_() * 0.8F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canUse(WitherStormEntity storm) {
/* 118 */     return !(storm instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\clustersource\NatureClusterSource.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */