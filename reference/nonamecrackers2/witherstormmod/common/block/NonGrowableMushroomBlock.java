/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.LevelReader;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.BushBlock;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.shapes.CollisionContext;
/*    */ import net.minecraft.world.phys.shapes.VoxelShape;
/*    */ import net.minecraftforge.common.IPlantable;
/*    */ 
/*    */ public class NonGrowableMushroomBlock extends BushBlock {
/* 17 */   protected static final VoxelShape SHAPE = Block.m_49796_(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
/*    */ 
/*    */   
/*    */   public NonGrowableMushroomBlock(BlockBehaviour.Properties properties) {
/* 21 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean m_6266_(BlockState state, BlockGetter reader, BlockPos pos) {
/* 27 */     return state.m_60804_(reader, pos);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_7898_(BlockState state, LevelReader reader, BlockPos pos) {
/* 33 */     BlockPos blockpos = pos.m_7495_();
/* 34 */     BlockState blockstate = reader.m_8055_(blockpos);
/* 35 */     if (blockstate.m_204336_(BlockTags.f_13057_)) {
/* 36 */       return true;
/*    */     }
/* 38 */     return (reader.m_45524_(pos, 0) < 13 && blockstate.canSustainPlant((BlockGetter)reader, blockpos, Direction.UP, (IPlantable)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape m_5940_(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
/* 44 */     return SHAPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\NonGrowableMushroomBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */