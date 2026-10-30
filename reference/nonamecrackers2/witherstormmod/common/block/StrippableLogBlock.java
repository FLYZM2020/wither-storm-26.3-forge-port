/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.world.item.context.UseOnContext;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.RotatedPillarBlock;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ import net.minecraftforge.common.ToolAction;
/*    */ import net.minecraftforge.common.ToolActions;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class StrippableLogBlock
/*    */   extends RotatedPillarBlock
/*    */ {
/*    */   private final Supplier<Block> supplier;
/*    */   
/*    */   public StrippableLogBlock(BlockBehaviour.Properties properties, Supplier<Block> supplier) {
/* 21 */     super(properties);
/* 22 */     this.supplier = supplier;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
/* 28 */     BlockState transformed = super.getToolModifiedState(state, context, toolAction, simulate);
/* 29 */     if (transformed == null && ToolActions.AXE_STRIP == toolAction) {
/* 30 */       return (BlockState)((Block)this.supplier.get()).m_49966_().m_61124_((Property)RotatedPillarBlock.f_55923_, state.m_61143_((Property)RotatedPillarBlock.f_55923_));
/*    */     }
/* 32 */     return transformed;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\StrippableLogBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */