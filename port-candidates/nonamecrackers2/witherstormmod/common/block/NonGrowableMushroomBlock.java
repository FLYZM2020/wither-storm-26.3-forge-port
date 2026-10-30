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
/* 17 */   protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
/*    */ 
/*    */   
/*    */   public NonGrowableMushroomBlock(BlockBehaviour.Properties properties) {
/* 21 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean mayPlaceOn(BlockState state, BlockGetter reader, BlockPos pos) {
/* 27 */     return state.isSolidRender(reader, pos);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
/* 33 */     BlockPos blockpos = pos.below();
/* 34 */     BlockState blockstate = reader.getBlockState(blockpos);
/* 35 */     if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
/* 36 */       return true;
/*    */     }
/* 38 */     return (reader.getRawBrightness(pos, 0) < 13 && blockstate.canSustainPlant((BlockGetter)reader, blockpos, Direction.UP, (IPlantable)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
/* 44 */     return SHAPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\NonGrowableMushroomBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */