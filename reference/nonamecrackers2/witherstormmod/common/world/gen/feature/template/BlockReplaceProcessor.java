/*    */ package nonamecrackers2.witherstormmod.common.world.gen.feature.template;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.level.LevelReader;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
/*    */ 
/*    */ public class BlockReplaceProcessor extends StructureProcessor {
/*    */   public static final Codec<BlockReplaceProcessor> CODEC;
/*    */   private final ImmutableList<Block> toReplace;
/*    */   
/*    */   static {
/* 22 */     CODEC = BlockState.f_61039_.xmap(BlockBehaviour.BlockStateBase::m_60734_, Block::m_49966_).listOf().fieldOf("blocks").xmap(BlockReplaceProcessor::new, proc -> proc.toReplace).codec();
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockReplaceProcessor(List<Block> blocks) {
/* 27 */     this.toReplace = ImmutableList.copyOf(blocks);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public StructureTemplate.StructureBlockInfo m_7382_(LevelReader reader, BlockPos pos, BlockPos pos1, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo copy, StructurePlaceSettings settings) {
/* 33 */     BlockState prev = reader.m_8055_(copy.f_74675_());
/* 34 */     return this.toReplace.contains(prev.m_60734_()) ? copy : null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected StructureProcessorType<?> m_6953_() {
/* 40 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\template\BlockReplaceProcessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */