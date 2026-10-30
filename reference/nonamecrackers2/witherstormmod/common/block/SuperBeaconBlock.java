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
/*    */   public BlockEntity m_142194_(BlockPos pos, BlockState state) {
/* 33 */     return (BlockEntity)new SuperBeaconBlockEntity(pos, state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
/* 39 */     return createBeaconTicker(level, type, (BlockEntityType<? extends AbstractSuperBeaconBlockEntity>)WitherStormModBlockEntityTypes.SUPER_BEACON.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
/* 45 */     if (level.f_46443_)
/*    */     {
/* 47 */       return InteractionResult.SUCCESS;
/*    */     }
/*    */ 
/*    */     
/* 51 */     BlockEntity entity = level.m_7702_(pos);
/* 52 */     if (entity instanceof SuperBeaconBlockEntity) { SuperBeaconBlockEntity beacon = (SuperBeaconBlockEntity)entity; if (!beacon.isDoingResummonAnimation() && beacon.canPlayerUseItems(player)) {
/*    */         
/* 54 */         ItemStack item = player.m_21120_(hand);
/* 55 */         if (!player.m_6144_() && beacon.m_6643_() > 0 && item.m_41619_()) {
/*    */           
/* 57 */           ItemStack stack = beacon.takeItem();
/* 58 */           if (!stack.m_41619_()) {
/* 59 */             player.m_36356_(stack);
/*    */           
/*    */           }
/*    */         }
/* 63 */         else if (!item.m_41619_()) {
/*    */           
/* 65 */           if (beacon.m_6643_() < 16)
/*    */           {
/* 67 */             ItemStack stack = item.m_41777_();
/* 68 */             stack.m_41764_(1);
/* 69 */             if (!(player.m_150110_()).f_35937_)
/* 70 */               item.m_41774_(1); 
/* 71 */             beacon.addItem(stack);
/* 72 */             level.m_5594_(null, pos, SoundEvents.f_12019_, SoundSource.BLOCKS, 1.0F, 1.0F);
/*    */           }
/*    */         
/*    */         } else {
/*    */           
/* 77 */           player.m_5893_((MenuProvider)beacon);
/* 78 */           player.m_36220_(WitherStormModStats.INTERACT_WITH_SUPER_BEACON);
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
/*    */   public void m_6810_(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
/* 90 */     if (!state.m_60713_(state2.m_60734_())) {
/*    */       
/* 92 */       BlockEntity entity = level.m_7702_(pos);
/* 93 */       if (entity instanceof Container) { Container container = (Container)entity;
/* 94 */         Containers.m_19002_(level, pos, container); }
/*    */       
/* 96 */       super.m_6810_(state, level, pos, state2, flag);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\SuperBeaconBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */