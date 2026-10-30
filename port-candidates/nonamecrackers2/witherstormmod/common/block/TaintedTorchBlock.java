/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.TorchBlock;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ 
/*    */ 
/*    */ public class TaintedTorchBlock
/*    */   extends TorchBlock
/*    */ {
/*    */   protected final Supplier<? extends ParticleOptions> flameParticle;
/*    */   
/*    */   public TaintedTorchBlock(BlockBehaviour.Properties properties, Supplier<? extends ParticleOptions> flameParticle) {
/* 20 */     super(properties, null);
/* 21 */     this.flameParticle = flameParticle;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
/* 27 */     double x = pos.getX() + 0.5D + random.nextGaussian() * 0.1D;
/* 28 */     double y = pos.getY() + 0.7D + random.nextGaussian() * 0.1D;
/* 29 */     double z = pos.getZ() + 0.5D + random.nextGaussian() * 0.1D;
/* 30 */     level.addParticle((ParticleOptions)ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
/* 31 */     level.addParticle(this.flameParticle.get(), x, y, z, 0.0D, 0.01D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedTorchBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */