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
/*    */   public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
/* 32 */     ItemStack item = player.m_21120_(hand);
/* 33 */     if (item.canPerformAction(ToolActions.SHEARS_CARVE)) {
/*    */       
/* 35 */       if (!level.f_46443_) {
/*    */         
/* 37 */         Direction hitSide = hitResult.m_82434_();
/* 38 */         Direction direction = (hitSide.m_122434_() == Direction.Axis.Y) ? player.m_6350_().m_122424_() : hitSide;
/* 39 */         level.m_5594_(null, pos, SoundEvents.f_12296_, SoundSource.BLOCKS, 1.0F, 1.0F);
/* 40 */         level.m_7731_(pos, (BlockState)((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()).m_49966_().m_61124_((Property)TaintedCarvedPumpkinBlock.FACING, (Comparable)direction), 11);
/* 41 */         item.m_41622_(1, (LivingEntity)player, p -> p.m_21190_(hand));
/*    */ 
/*    */         
/* 44 */         level.m_142346_((Entity)player, GameEvent.f_157781_, pos);
/* 45 */         player.m_36246_(Stats.f_12982_.m_12902_(Items.f_42574_));
/*    */       } 
/* 47 */       return InteractionResult.m_19078_(level.f_46443_);
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