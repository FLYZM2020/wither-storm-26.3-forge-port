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
/* 16 */   public static final DirectionProperty FACING = BlockStateProperties.f_61374_;
/*    */   
/*    */   public TaintedStatue(BlockBehaviour.Properties pProperties) {
/* 19 */     super(pProperties);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState m_5573_(BlockPlaceContext pContext) {
/* 26 */     return (BlockState)m_49966_().m_61124_((Property)FACING, (Comparable)pContext.m_8125_().m_122424_());
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockState m_6843_(BlockState pState, Rotation pRotation) {
/* 31 */     return (BlockState)pState.m_61124_((Property)FACING, (Comparable)pRotation.m_55954_((Direction)pState.m_61143_((Property)FACING)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState m_6943_(BlockState pState, Mirror pMirror) {
/* 37 */     return pState.m_60717_(pMirror.m_54846_((Direction)pState.m_61143_((Property)FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> pBuilder) {
/* 42 */     pBuilder.m_61104_(new Property[] { (Property)FACING });
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedStatue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */