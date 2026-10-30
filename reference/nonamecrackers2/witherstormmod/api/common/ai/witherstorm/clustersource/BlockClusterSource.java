/*     */ package nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource;
/*     */ 
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public abstract class BlockClusterSource {
/*     */   public BlockClusterSource(int maximumCreationAttempts) {
/*  26 */     this.maximumCreationAttempts = maximumCreationAttempts;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(WitherStormEntity storm) {
/*  31 */     if (ForgeEventFactory.getMobGriefingEvent(storm.m_9236_(), (Entity)storm) && canUse(storm) && storm.f_19797_ % getPickupInterval(storm) == 0)
/*  32 */       createCluster(storm); 
/*     */   }
/*     */   protected final int maximumCreationAttempts;
/*     */   
/*     */   protected void createCluster(WitherStormEntity storm) {
/*  37 */     createClusterNearby(storm, 
/*     */         
/*  39 */         searchCenter(storm), 
/*  40 */         getClusterSizeRadius(storm), 
/*  41 */         getClusterSearchRadius(storm), 
/*  42 */         calculateShakeTime(storm, storm.m_217043_()), this.maximumCreationAttempts, 
/*     */         
/*  44 */         calculateRotationDelta(storm, storm.m_217043_()), 
/*  45 */         shouldScanUpwards(storm), 
/*  46 */         shouldntCountToConsumedEntities(storm));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockPos searchCenter(WitherStormEntity storm) {
/*  52 */     int flooredX = Mth.m_14107_(storm.m_20185_());
/*  53 */     int flooredY = Math.min(storm.m_9236_().m_151558_() - 1, Mth.m_14107_(storm.m_20188_() + 1.0D));
/*  54 */     int flooredZ = Mth.m_14107_(storm.m_20189_());
/*  55 */     return new BlockPos(flooredX, flooredY, flooredZ);
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract float getClusterSizeRadius(WitherStormEntity paramWitherStormEntity);
/*     */   
/*     */   protected int getClusterSearchRadius(WitherStormEntity storm) {
/*  62 */     return storm.getEntityConsumptionRadius();
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract int calculateShakeTime(WitherStormEntity paramWitherStormEntity, RandomSource paramRandomSource);
/*     */   
/*     */   protected Vec2 calculateRotationDelta(WitherStormEntity storm, RandomSource random) {
/*  69 */     if (storm.getConsumedEntities() < storm.adjustAmountForEvolutionSpeed(10000)) {
/*  70 */       return new Vec2((random.m_188503_(20) - 10) * 0.125F, (random.m_188503_(20) - 10) * 0.125F);
/*     */     }
/*  72 */     return new Vec2((random.m_188503_(20) - 10) * 0.75F, (random.m_188503_(20) - 10) * 0.75F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isInvalidInitialStartBlock(WitherStormEntity storm, BlockState state) {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isValidClusterBlock(WitherStormEntity storm, BlockState state) {
/*  82 */     return !state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST);
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract int getPickupInterval(WitherStormEntity paramWitherStormEntity);
/*     */   
/*     */   protected abstract boolean canUse(WitherStormEntity paramWitherStormEntity);
/*     */   
/*     */   protected boolean shouldScanUpwards(WitherStormEntity storm) {
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean shouldntCountToConsumedEntities(WitherStormEntity storm) {
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean shouldPullBlocksWithRandomYOffset(WitherStormEntity storm, float clusterSizeRadius) {
/* 101 */     return (clusterSizeRadius <= 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onClusterAddedToWorld(WitherStormEntity storm, BlockClusterEntity cluster, BlockPos startPos, BlockState startState) {}
/*     */   
/*     */   protected void createClusterNearby(WitherStormEntity storm, BlockPos searchCenter, float clusterSizeRadius, int radius, int shakeTime, int maxAttempts, Vec2 rotationDelta, boolean scanUpwards, boolean countToConsumedEntities) {
/* 108 */     for (int i = 0; i < maxAttempts; i++) {
/*     */       
/* 110 */       int randomX = storm.m_217043_().m_188503_(radius * 2) - radius;
/* 111 */       int randomZ = storm.m_217043_().m_188503_(radius * 2) - radius;
/* 112 */       double distance = Math.sqrt((randomX * randomX + randomZ * randomZ));
/* 113 */       if (distance < radius) {
/*     */         
/* 115 */         BlockPos blockToCollect = new BlockPos(searchCenter.m_123341_() + randomX, searchCenter.m_123342_(), searchCenter.m_123343_() + randomZ);
/* 116 */         if (WorldUtil.isLoaded((ServerLevel)storm.m_9236_(), blockToCollect)) {
/*     */           
/* 118 */           BlockState blockState = storm.m_9236_().m_8055_(blockToCollect);
/* 119 */           while (blockToCollect.m_123342_() > storm.m_9236_().m_141937_() && blockToCollect.m_123342_() < storm.m_9236_().m_151558_() && (blockState.m_60713_(Blocks.f_50016_) || blockState.m_60713_(Blocks.f_49990_))) {
/*     */             
/* 121 */             blockToCollect = scanUpwards ? blockToCollect.m_7494_() : blockToCollect.m_7495_();
/* 122 */             blockState = storm.m_9236_().m_8055_(blockToCollect);
/*     */           } 
/*     */ 
/*     */           
/* 126 */           if (!scanUpwards && shouldPullBlocksWithRandomYOffset(storm, clusterSizeRadius)) {
/*     */             
/* 128 */             BlockPos originalBlockToCollect = blockToCollect;
/* 129 */             BlockState originalBlockState = blockState;
/*     */             
/* 131 */             int minY = blockToCollect.m_123342_();
/* 132 */             int maxY = Math.max(storm.m_9236_().m_141937_(), minY - 10);
/* 133 */             int randomY = storm.m_217043_().m_188503_(minY - maxY + 1) + maxY;
/* 134 */             blockToCollect = new BlockPos(blockToCollect.m_123341_(), randomY, blockToCollect.m_123343_());
/* 135 */             blockState = storm.m_9236_().m_8055_(blockToCollect);
/*     */             
/* 137 */             if (blockState.m_60795_() || blockState.m_60713_(Blocks.f_49990_) || blockState.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST)) {
/*     */               
/* 139 */               blockToCollect = originalBlockToCollect;
/* 140 */               blockState = originalBlockState;
/*     */             } 
/*     */           } 
/*     */           
/* 144 */           if (!isInvalidInitialStartBlock(storm, blockState) && WorldUtil.isBlockExposed(storm.m_9236_(), blockToCollect))
/*     */           {
/*     */ 
/*     */             
/* 148 */             if (storm.m_9236_().m_45976_(WitheredSymbiontEntity.class, (new AABB(blockToCollect)).m_82400_(15.0D)).stream().filter(LivingEntity::m_6084_).findFirst().isEmpty()) {
/*     */               
/* 150 */               BlockClusterEntity clusterEntity = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(storm.m_9236_());
/* 151 */               assert clusterEntity != null;
/* 152 */               clusterEntity.populateWithRadius(blockToCollect, clusterSizeRadius, state -> isValidClusterBlock(storm, state));
/* 153 */               if (clusterEntity.getSize() > 0) {
/*     */                 
/* 155 */                 if (clusterEntity.getSize() >= 55 && storm.m_217043_().m_188503_(3) == 0)
/* 156 */                   clusterEntity.setShouldCrumble(true); 
/* 157 */                 clusterEntity.setTime(50);
/* 158 */                 clusterEntity.setShakeTime(shakeTime);
/* 159 */                 if (clusterEntity.getSize() >= 2)
/* 160 */                   clusterEntity.m_5496_((SoundEvent)WitherStormModSoundEvents.BLOCK_CLUSTER_SHAKE.get(), 2.0F, (storm.m_217043_().m_188501_() - storm.m_217043_().m_188501_()) * 0.2F + 1.0F); 
/* 161 */                 storm.getTrackedEntities().trackEntityToConsume((Entity)clusterEntity);
/* 162 */                 clusterEntity.setRotationDelta(rotationDelta);
/* 163 */                 if (storm.m_217043_().m_188499_())
/* 164 */                   clusterEntity.m_20049_("RotateClockwise"); 
/* 165 */                 clusterEntity.m_20242_(true);
/* 166 */                 clusterEntity.setPhysics(false);
/* 167 */                 clusterEntity.setShouldntCountToConsumedEntities(countToConsumedEntities);
/* 168 */                 onClusterAddedToWorld(storm, clusterEntity, blockToCollect, blockState);
/* 169 */                 storm.m_9236_().m_7967_((Entity)clusterEntity);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } else {
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\ai\witherstorm\clustersource\BlockClusterSource.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */