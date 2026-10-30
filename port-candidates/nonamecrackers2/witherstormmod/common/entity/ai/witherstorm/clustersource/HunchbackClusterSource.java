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
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public class HunchbackClusterSource
/*     */   extends BlockClusterSource {
/*     */   public HunchbackClusterSource() {
/*  19 */     super(256);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shouldScanUpwards(WitherStormEntity storm) {
/*  25 */     if (storm.getRandom().nextInt(2) == 0) {
/*     */       
/*  27 */       int x = storm.getBlockX();
/*  28 */       int z = storm.getBlockZ();
/*  29 */       int height = WorldUtil.getCeilingStartingAt(storm.level(), storm.getBlockY(), x, z);
/*  30 */       BlockPos pos = new BlockPos(x, height, z);
/*  31 */       return !storm.level().getBlockState(pos).isAir();
/*     */     } 
/*     */ 
/*     */     
/*  35 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createCluster(WitherStormEntity storm) {
/*  42 */     switch (storm.getPhase()) { case 1: 
/*     */       case 2: 
/*     */       case 3:
/*     */       
/*     */       default:
/*  47 */         break; }  int multiClusters = 1;
/*     */     
/*  49 */     for (int i = 0; i < multiClusters; i++) {
/*  50 */       super.createCluster(storm);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected int calculateShakeTime(WitherStormEntity storm, RandomSource random) {
/*  56 */     if (storm.getConsumedEntities() >= storm.adjustAmountForEvolutionSpeed(15000))
/*  57 */       return 0; 
/*  58 */     if (storm.getConsumedEntities() >= storm.adjustAmountForEvolutionSpeed(10000)) {
/*  59 */       return random.nextInt(10);
/*     */     }
/*  61 */     return random.nextInt(40);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getClusterSizeRadius(WitherStormEntity storm) {
/*  67 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isInvalidInitialStartBlock(WitherStormEntity storm, BlockState state) {
/*  73 */     return (state.is(WitherStormModBlockTags.LESS_FAVORABLE_BLOCKS_HUNCH) && storm.getRandom().nextDouble() <= 0.995D && storm.getPhase() == 3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getClusterSearchRadius(WitherStormEntity storm) {
/*  79 */     return storm.entityConsumptionRadiusHunch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getPickupInterval(WitherStormEntity storm) {
/*  85 */     if (((Boolean)WitherStormModConfig.SERVER.constantBlackhole.get()).booleanValue())
/*  86 */       return 1; 
/*  87 */     return Math.max(1, 60 - Math.round(storm.getConsumedEntities() * 0.00375F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canUse(WitherStormEntity storm) {
/*  93 */     return (storm.getPhase() <= 3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onClusterAddedToWorld(WitherStormEntity storm, BlockClusterEntity cluster, BlockPos startPos, BlockState startState) {
/*  99 */     SoundType sound = startState.getSoundType((LevelReader)storm.level(), startPos, null);
/* 100 */     storm.level().playSound(null, startPos, sound.getBreakSound(), SoundSource.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\clustersource\HunchbackClusterSource.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */