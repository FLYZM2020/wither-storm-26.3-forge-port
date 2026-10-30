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
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*    */ 
/*    */ public class WitherStormPatternChecker {
/*    */   @SubscribeEvent
/*    */   public static void checkForWitherStormPattern(BlockEvent.EntityPlaceEvent event) {
/* 31 */     if (event.getPlacedBlock().m_60713_(Blocks.f_50312_) || event.getPlacedBlock().m_60713_(Blocks.f_50313_)) {
/*    */       
/* 33 */       Level world = (Level)event.getLevel();
/* 34 */       if (event.getPos().m_123342_() >= world.m_141937_() && world.m_46791_() != Difficulty.PEACEFUL) {
/*    */         
/* 36 */         BlockPattern blockPattern = getOrCreateWitherStorm();
/* 37 */         BlockPattern.BlockPatternMatch patternHelper = blockPattern.m_61184_((LevelReader)world, event.getPos());
/* 38 */         if (patternHelper != null) {
/*    */           
/* 40 */           for (int i = 0; i < blockPattern.m_61203_(); i++) {
/*    */             
/* 42 */             for (int j = 0; j < blockPattern.m_61202_(); j++) {
/*    */               
/* 44 */               BlockInWorld cachedBlockInfo = patternHelper.m_61229_(i, j, 0);
/* 45 */               world.m_7731_(cachedBlockInfo.m_61176_(), Blocks.f_50016_.m_49966_(), 2);
/* 46 */               world.m_46796_(2001, cachedBlockInfo.m_61176_(), Block.m_49956_(cachedBlockInfo.m_61168_()));
/*    */             } 
/*    */           } 
/*    */           
/* 50 */           WitherStormEntity witherStorm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).m_20615_(world);
/* 51 */           BlockPos blockPos = patternHelper.m_61229_(1, 2, 0).m_61176_();
/* 52 */           Vec3 vector = new Vec3(blockPos.m_123341_() + 0.5D, blockPos.m_123342_() + 0.55D, blockPos.m_123343_() + 0.5D);
/* 53 */           float rotation = patternHelper.m_61233_().m_122435_();
/* 54 */           witherStorm.m_7678_(vector.f_82479_, vector.f_82480_, vector.f_82481_, rotation, 0.0F);
/* 55 */           witherStorm.f_20883_ = rotation;
/* 56 */           for (AdditionalHead head : witherStorm.getHeadManager().getOtherHeads())
/* 57 */             head.setHeadYRot(rotation); 
/* 58 */           witherStorm.makeInvulnerable();
/* 59 */           witherStorm.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0F, 1.0F);
/*    */           
/* 61 */           for (ServerPlayer player : world.m_45976_(ServerPlayer.class, witherStorm.m_20191_().m_82400_(50.0D))) {
/* 62 */             CriteriaTriggers.f_10580_.m_68256_(player, (Entity)witherStorm);
/*    */           }
/* 64 */           for (int k = 0; k < blockPattern.m_61203_(); k++) {
/*    */             
/* 66 */             for (int l = 0; l < blockPattern.m_61202_(); l++)
/*    */             {
/* 68 */               world.m_6289_(patternHelper.m_61229_(k, l, 0).m_61176_(), Blocks.f_50016_);
/*    */             }
/*    */           } 
/*    */           
/* 72 */           world.m_7967_((Entity)witherStorm);
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
/* 87 */       WITHER_STORM_PATTERN = BlockPatternBuilder.m_61243_().m_61247_(new String[] { "^^^", "#$#", "~#~" }).m_61244_('#', block -> block.m_61168_().m_204336_(WitherStormModBlockTags.WITHER_STORM_SUMMON_BASE_BLOCKS)).m_61244_('^', BlockInWorld.m_61169_(BlockStatePredicate.m_61287_(Blocks.f_50312_).or((Predicate)BlockStatePredicate.m_61287_(Blocks.f_50313_)))).m_61244_('~', BlockInWorld.m_61169_(BlockBehaviour.BlockStateBase::m_60795_)).m_61244_('$', block -> block.m_61168_().m_204336_(WitherStormModBlockTags.WITHER_STORM_SUMMON_COMMAND_BLOCKS)).m_61249_();
/*    */     }
/*    */     
/* 90 */     return WITHER_STORM_PATTERN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherStormPatternChecker.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */