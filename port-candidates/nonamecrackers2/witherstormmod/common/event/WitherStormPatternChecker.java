/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.advancements.CriteriaTriggers;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.world.Difficulty;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.LevelReader;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.pattern.BlockInWorld;
/*    */ import net.minecraft.world.level.block.state.pattern.BlockPattern;
/*    */ import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
/*    */ import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.event.level.BlockEvent;
/*    */ import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*    */ 
/*    */ public class WitherStormPatternChecker {
/*    */   @SubscribeEvent
/*    */   public static void checkForWitherStormPattern(BlockEvent.EntityPlaceEvent event) {
/* 31 */     if (event.getPlacedBlock().is(Blocks.WITHER_SKELETON_SKULL) || event.getPlacedBlock().is(Blocks.WITHER_SKELETON_WALL_SKULL)) {
/*    */       
/* 33 */       Level world = (Level)event.getLevel();
/* 34 */       if (event.getPos().getY() >= world.getMinBuildHeight() && world.getDifficulty() != Difficulty.PEACEFUL) {
/*    */         
/* 36 */         BlockPattern blockPattern = getOrCreateWitherStorm();
/* 37 */         BlockPattern.BlockPatternMatch patternHelper = blockPattern.find((LevelReader)world, event.getPos());
/* 38 */         if (patternHelper != null) {
/*    */           
/* 40 */           for (int i = 0; i < blockPattern.getWidth(); i++) {
/*    */             
/* 42 */             for (int j = 0; j < blockPattern.getHeight(); j++) {
/*    */               
/* 44 */               BlockInWorld cachedBlockInfo = patternHelper.getBlock(i, j, 0);
/* 45 */               world.setBlock(cachedBlockInfo.getPos(), Blocks.AIR.defaultBlockState(), 2);
/* 46 */               world.levelEvent(2001, cachedBlockInfo.getPos(), Block.getId(cachedBlockInfo.getState()));
/*    */             } 
/*    */           } 
/*    */           
/* 50 */           WitherStormEntity witherStorm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).create(world);
/* 51 */           BlockPos blockPos = patternHelper.getBlock(1, 2, 0).getPos();
/* 52 */           Vec3 vector = new Vec3(blockPos.getX() + 0.5D, blockPos.getY() + 0.55D, blockPos.getZ() + 0.5D);
/* 53 */           float rotation = patternHelper.getForwards().toYRot();
/* 54 */           witherStorm.moveTo(vector.x, vector.y, vector.z, rotation, 0.0F);
/* 55 */           witherStorm.yBodyRot = rotation;
/* 56 */           for (AdditionalHead head : witherStorm.getHeadManager().getOtherHeads())
/* 57 */             head.setHeadYRot(rotation); 
/* 58 */           witherStorm.makeInvulnerable();
/* 59 */           witherStorm.playSound((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0F, 1.0F);
/*    */           
/* 61 */           for (ServerPlayer player : world.getEntitiesOfClass(ServerPlayer.class, witherStorm.getBoundingBox().inflate(50.0D))) {
/* 62 */             CriteriaTriggers.SUMMONED_ENTITY.trigger(player, (Entity)witherStorm);
/*    */           }
/* 64 */           for (int k = 0; k < blockPattern.getWidth(); k++) {
/*    */             
/* 66 */             for (int l = 0; l < blockPattern.getHeight(); l++)
/*    */             {
/* 68 */               world.blockUpdated(patternHelper.getBlock(k, l, 0).getPos(), Blocks.AIR);
/*    */             }
/*    */           } 
/*    */           
/* 72 */           world.addFreshEntity((Entity)witherStorm);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   private static BlockPattern WITHER_STORM_PATTERN;
/*    */   
/*    */   private static BlockPattern getOrCreateWitherStorm() {
/* 80 */     if (WITHER_STORM_PATTERN == null)
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 87 */       WITHER_STORM_PATTERN = BlockPatternBuilder.start().aisle(new String[] { "^^^", "#$#", "~#~" }).where('#', block -> block.getState().is(WitherStormModBlockTags.WITHER_STORM_SUMMON_BASE_BLOCKS)).where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.WITHER_SKELETON_SKULL).or((Predicate)BlockStatePredicate.forBlock(Blocks.WITHER_SKELETON_WALL_SKULL)))).where('~', BlockInWorld.hasState(BlockBehaviour.BlockStateBase::isAir)).where('$', block -> block.getState().is(WitherStormModBlockTags.WITHER_STORM_SUMMON_COMMAND_BLOCKS)).build();
/*    */     }
/*    */     
/* 90 */     return WITHER_STORM_PATTERN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherStormPatternChecker.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */