/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ import net.minecraft.world.level.gameevent.GameEvent;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraftforge.common.ToolActions;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ 
/*    */ public class TaintedPumpkinBlock extends Block {
/*    */   public TaintedPumpkinBlock(BlockBehaviour.Properties properties) {
/* 26 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
/* 32 */     ItemStack item = player.getItemInHand(hand);
/* 33 */     if (item.canPerformAction(ToolActions.SHEARS_CARVE)) {
/*    */       
/* 35 */       if (!level.isClientSide) {
/*    */         
/* 37 */         Direction hitSide = hitResult.getDirection();
/* 38 */         Direction direction = (hitSide.getAxis() == Direction.Axis.Y) ? player.getDirection().getOpposite() : hitSide;
/* 39 */         level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
/* 40 */         level.setBlock(pos, (BlockState)((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()).defaultBlockState().setValue((Property)TaintedCarvedPumpkinBlock.FACING, (Comparable)direction), 11);
/* 41 */         item.hurtAndBreak(1, (LivingEntity)player, p -> p.broadcastBreakEvent(hand));
/*    */ 
/*    */         
/* 44 */         level.gameEvent((Entity)player, GameEvent.SHEAR, pos);
/* 45 */         player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
/*    */       } 
/* 47 */       return InteractionResult.sidedSuccess(level.isClientSide);
/*    */     } 
/*    */ 
/*    */     
/* 51 */     return InteractionResult.PASS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedPumpkinBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */