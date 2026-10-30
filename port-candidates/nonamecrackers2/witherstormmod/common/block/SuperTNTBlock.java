/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.level.Explosion;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.TntBlock;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.material.PushReaction;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SuperTNTEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ 
/*    */ public class SuperTNTBlock extends TntBlock {
/*    */   public SuperTNTBlock(BlockBehaviour.Properties properties) {
/* 20 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
/* 26 */     if (!world.isClientSide) {
/*    */       
/* 28 */       SuperTNTEntity entity = new SuperTNTEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, explosion.getIndirectSourceEntity());
/* 29 */       entity.setFuse(world.random.nextInt(entity.getFuse() / 4) + entity.getFuse() / 8);
/* 30 */       world.addFreshEntity((Entity)entity);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onCaughtFire(BlockState state, Level world, BlockPos pos, Direction face, LivingEntity igniter) {
/* 37 */     if (!world.isClientSide) {
/*    */       
/* 39 */       SuperTNTEntity entity = new SuperTNTEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, igniter);
/* 40 */       world.addFreshEntity((Entity)entity);
/* 41 */       world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), (SoundEvent)WitherStormModSoundEvents.SUPER_TNT_PRIMED.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PushReaction getPistonPushReaction(BlockState state) {
/* 48 */     return PushReaction.BLOCK;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\SuperTNTBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */