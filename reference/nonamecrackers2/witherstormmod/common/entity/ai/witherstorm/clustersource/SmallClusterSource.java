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
/*     */ public class SmallClusterSource
/*     */   extends BlockClusterSource
/*     */ {
/*     */   public SmallClusterSource() {
/*  19 */     super(1024);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createCluster(WitherStormEntity storm) {
/*  25 */     int phase = storm.getPhase();
/*     */     
/*  27 */     switch (phase) { case 4: 
/*     */       case 5: 
/*     */       case 6: 
/*     */       case 7:
/*     */       
/*     */       default:
/*  33 */         break; }  int multiClusters = 1;
/*     */ 
/*     */     
/*  36 */     for (int i = 0; i < multiClusters; i++)
/*     */     {
/*  38 */       super.createCluster(storm);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getClusterSizeRadius(WitherStormEntity storm) {
/*  45 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getClusterSearchRadius(WitherStormEntity storm) {
/*  51 */     return storm.getEntityConsumptionRadius() * storm.phaseRadiusMultiplier(storm.getPhase());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int calculateShakeTime(WitherStormEntity storm, RandomSource random) {
/*  57 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidClusterBlock(WitherStormEntity storm, BlockState state) {
/*  63 */     return (!state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST) && !state.m_204336_(WitherStormModBlockTags.SMALL_CLUSTER_BLACKLIST));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isInvalidInitialStartBlock(WitherStormEntity storm, BlockState state) {
/*  69 */     return (state.m_204336_(WitherStormModBlockTags.LESS_FAVORABLE_BLOCKS) && storm.m_217043_().m_188500_() <= 0.9D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getPickupInterval(WitherStormEntity storm) {
/*  75 */     int phase = storm.getPhase();
/*     */     
/*  77 */     if (((Boolean)WitherStormModConfig.SERVER.constantBlackhole.get()).booleanValue()) {
/*  78 */       return 6;
/*     */     }
/*  80 */     switch (phase) { case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       100;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onClusterAddedToWorld(WitherStormEntity storm, BlockClusterEntity cluster, BlockPos startPos, BlockState startState) {
/*  94 */     SoundType sound = startState.getSoundType((LevelReader)storm.m_9236_(), startPos, null);
/*  95 */     storm.m_9236_().m_5594_(null, startPos, sound.m_56775_(), SoundSource.BLOCKS, (sound.m_56773_() + 1.0F) / 2.0F, sound.m_56774_() * 0.8F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canUse(WitherStormEntity storm) {
/* 101 */     return !(storm instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\clustersource\SmallClusterSource.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */