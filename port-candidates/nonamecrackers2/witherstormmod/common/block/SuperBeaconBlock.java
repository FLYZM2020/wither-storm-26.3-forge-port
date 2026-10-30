/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.Containers;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModStats;
/*    */ 
/*    */ public class SuperBeaconBlock extends AbstractSuperBeaconBlock {
/*    */   public SuperBeaconBlock(BlockBehaviour.Properties properties) {
/* 27 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
/* 33 */     return (BlockEntity)new SuperBeaconBlockEntity(pos, state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
/* 39 */     return createBeaconTicker(level, type, (BlockEntityType<? extends AbstractSuperBeaconBlockEntity>)WitherStormModBlockEntityTypes.SUPER_BEACON.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
/* 45 */     if (level.isClientSide)
/*    */     {
/* 47 */       return InteractionResult.SUCCESS;
/*    */     }
/*    */ 
/*    */     
/* 51 */     BlockEntity entity = level.getBlockEntity(pos);
/* 52 */     if (entity instanceof SuperBeaconBlockEntity) { SuperBeaconBlockEntity beacon = (SuperBeaconBlockEntity)entity; if (!beacon.isDoingResummonAnimation() && beacon.canPlayerUseItems(player)) {
/*    */         
/* 54 */         ItemStack item = player.getItemInHand(hand);
/* 55 */         if (!player.isShiftKeyDown() && beacon.getContainerSize() > 0 && item.isEmpty()) {
/*    */           
/* 57 */           ItemStack stack = beacon.takeItem();
/* 58 */           if (!stack.isEmpty()) {
/* 59 */             player.addItem(stack);
/*    */           
/*    */           }
/*    */         }
/* 63 */         else if (!item.isEmpty()) {
/*    */           
/* 65 */           if (beacon.getContainerSize() < 16)
/*    */           {
/* 67 */             ItemStack stack = item.copy();
/* 68 */             stack.setCount(1);
/* 69 */             if (!(player.getAbilities()).instabuild)
/* 70 */               item.shrink(1); 
/* 71 */             beacon.addItem(stack);
/* 72 */             level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
/*    */           }
/*    */         
/*    */         } else {
/*    */           
/* 77 */           player.openMenu((MenuProvider)beacon);
/* 78 */           player.awardStat(WitherStormModStats.INTERACT_WITH_SUPER_BEACON);
/*    */         } 
/*    */       }  }
/*    */     
/* 82 */     return InteractionResult.CONSUME;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
/* 90 */     if (!state.is(state2.getBlock())) {
/*    */       
/* 92 */       BlockEntity entity = level.getBlockEntity(pos);
/* 93 */       if (entity instanceof Container) { Container container = (Container)entity;
/* 94 */         Containers.dropContents(level, pos, container); }
/*    */       
/* 96 */       super.onRemove(state, level, pos, state2, flag);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\SuperBeaconBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */