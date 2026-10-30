/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraft.world.phys.shapes.BooleanOp;
/*    */ import net.minecraft.world.phys.shapes.CollisionContext;
/*    */ import net.minecraft.world.phys.shapes.Shapes;
/*    */ import net.minecraft.world.phys.shapes.VoxelShape;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*    */ 
/*    */ public class SuperSupportBeaconBlock extends AbstractSuperBeaconBlock {
/* 26 */   private static final VoxelShape SHAPE = Shapes.join(Block.box(3.0D, 4.0D, 3.0D, 13.0D, 14.0D, 13.0D), Shapes.join(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), Block.box(2.0D, 3.0D, 2.0D, 14.0D, 4.0D, 14.0D), BooleanOp.OR), BooleanOp.OR);
/*    */ 
/*    */   
/*    */   public SuperSupportBeaconBlock(BlockBehaviour.Properties properties) {
/* 30 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractSuperBeaconBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
/* 36 */     return (AbstractSuperBeaconBlockEntity)new SuperSupportBeaconBlockEntity(pos, state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
/* 42 */     return createBeaconTicker(level, type, (BlockEntityType<? extends AbstractSuperBeaconBlockEntity>)WitherStormModBlockEntityTypes.SUPER_SUPPORT_BEACON.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
/* 48 */     return SHAPE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
/* 54 */     if (level.isClientSide)
/*    */     {
/* 56 */       return InteractionResult.SUCCESS;
/*    */     }
/*    */ 
/*    */     
/* 60 */     BlockEntity entity = level.getBlockEntity(pos);
/* 61 */     if (entity instanceof SuperSupportBeaconBlockEntity) { SuperSupportBeaconBlockEntity beacon = (SuperSupportBeaconBlockEntity)entity;
/*    */       
/* 63 */       SuperBeaconBlockEntity connected = beacon.getConnectedBeaconEntity();
/* 64 */       if (connected == null || !connected.isDoingResummonAnimation())
/* 65 */         player.openMenu((MenuProvider)beacon);  }
/*    */     
/* 67 */     return InteractionResult.CONSUME;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\SuperSupportBeaconBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */