/*    */ package nonamecrackers2.witherstormmod.common.world.gen.feature.template;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.List;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.world.level.LevelReader;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
/*    */ 
/*    */ 
/*    */ public class BlockIgnoreIrreplacableProcessor
/*    */   extends BlockIgnoreProcessor
/*    */ {
/* 19 */   public static final BlockIgnoreIrreplacableProcessor AIR = new BlockIgnoreIrreplacableProcessor((List<Block>)ImmutableList.of(Blocks.AIR), false);
/* 20 */   public static final BlockIgnoreIrreplacableProcessor AIR_REMOVE = new BlockIgnoreIrreplacableProcessor((List<Block>)ImmutableList.of(Blocks.AIR), true);
/*    */   
/*    */   private final boolean removeMode;
/*    */ 
/*    */   
/*    */   public BlockIgnoreIrreplacableProcessor(List<Block> ignore, boolean remove) {
/* 26 */     super(ignore);
/* 27 */     this.removeMode = remove;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public StructureTemplate.StructureBlockInfo processBlock(LevelReader world, BlockPos pos, BlockPos pos1, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo copy, StructurePlaceSettings settings) {
/* 33 */     BlockState previous = world.getBlockState(copy.pos());
/* 34 */     StructureTemplate.StructureBlockInfo info = copy;
/*    */ 
/*    */     
/* 37 */     if (previous.is(BlockTags.FEATURES_CANNOT_REPLACE))
/* 38 */       info = null; 
/* 39 */     if (super.processBlock(world, pos, pos1, original, copy, settings) == null) {
/* 40 */       info = null;
/*    */     }
/* 42 */     if (this.removeMode && info != null) {
/* 43 */       info = new StructureTemplate.StructureBlockInfo(info.pos(), Blocks.AIR.defaultBlockState(), info.nbt());
/*    */     }
/* 45 */     return info;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\template\BlockIgnoreIrreplacableProcessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */