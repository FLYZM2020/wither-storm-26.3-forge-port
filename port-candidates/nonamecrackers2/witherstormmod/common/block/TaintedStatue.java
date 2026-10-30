/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ 
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.world.item.context.BlockPlaceContext;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.Mirror;
/*    */ import net.minecraft.world.level.block.Rotation;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.StateDefinition;
/*    */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*    */ import net.minecraft.world.level.block.state.properties.DirectionProperty;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ 
/*    */ public class TaintedStatue extends Block {
/* 16 */   public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
/*    */   
/*    */   public TaintedStatue(BlockBehaviour.Properties pProperties) {
/* 19 */     super(pProperties);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState getStateForPlacement(BlockPlaceContext pContext) {
/* 26 */     return (BlockState)defaultBlockState().setValue((Property)FACING, (Comparable)pContext.getHorizontalDirection().getOpposite());
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockState rotate(BlockState pState, Rotation pRotation) {
/* 31 */     return (BlockState)pState.setValue((Property)FACING, (Comparable)pRotation.rotate((Direction)pState.getValue((Property)FACING)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState mirror(BlockState pState, Mirror pMirror) {
/* 37 */     return pState.rotate(pMirror.getRotation((Direction)pState.getValue((Property)FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
/* 42 */     pBuilder.add(new Property[] { (Property)FACING });
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedStatue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */