/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.BaseEntityBlock;
/*    */ import net.minecraft.world.level.block.RenderShape;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
/*    */ 
/*    */ public abstract class AbstractSuperBeaconBlock
/*    */   extends BaseEntityBlock
/*    */ {
/*    */   public AbstractSuperBeaconBlock(BlockBehaviour.Properties properties) {
/* 20 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderShape getRenderShape(BlockState state) {
/* 26 */     return RenderShape.MODEL;
/*    */   }
/*    */ 
/*    */   
/*    */   protected static <T extends BlockEntity> BlockEntityTicker<T> createBeaconTicker(Level level, BlockEntityType<T> type, BlockEntityType<? extends AbstractSuperBeaconBlockEntity> type1) {
/* 31 */     return createTickerHelper(type, type1, (l, s, p, entity) -> entity.tick());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
/* 37 */     if (stack.hasCustomHoverName()) {
/*    */       
/* 39 */       BlockEntity blockEntity = level.getBlockEntity(pos);
/* 40 */       if (blockEntity instanceof AbstractSuperBeaconBlockEntity) { AbstractSuperBeaconBlockEntity beacon = (AbstractSuperBeaconBlockEntity)blockEntity;
/* 41 */         beacon.setCustomName(stack.getHoverName()); }
/*    */     
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\AbstractSuperBeaconBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */