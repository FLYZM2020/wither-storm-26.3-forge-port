/*    */ package nonamecrackers2.witherstormmod.common.block;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.WallTorchBlock;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ 
/*    */ public class TaintedWallTorchBlock
/*    */   extends WallTorchBlock
/*    */ {
/*    */   protected final Supplier<? extends ParticleOptions> flameParticle;
/*    */   
/*    */   public TaintedWallTorchBlock(BlockBehaviour.Properties properties, Supplier<? extends ParticleOptions> flameParticle) {
/* 21 */     super(properties, null);
/* 22 */     this.flameParticle = flameParticle;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_214162_(BlockState state, Level level, BlockPos pos, RandomSource random) {
/* 28 */     Direction direction = (Direction)state.m_61143_((Property)f_58119_);
/* 29 */     double x = pos.m_123341_() + 0.5D + random.m_188583_() * 0.1D;
/* 30 */     double y = pos.m_123342_() + 0.7D + random.m_188583_() * 0.1D;
/* 31 */     double z = pos.m_123343_() + 0.5D + random.m_188583_() * 0.1D;
/* 32 */     Direction opposite = direction.m_122424_();
/* 33 */     level.m_7106_((ParticleOptions)ParticleTypes.f_123762_, x + 0.27D * opposite.m_122429_(), y + 0.22D, z + 0.27D * opposite.m_122431_(), 0.0D, 0.0D, 0.0D);
/* 34 */     level.m_7106_(this.flameParticle.get(), x + 0.27D * opposite.m_122429_(), y + 0.22D, z + 0.27D * opposite.m_122431_(), 0.0D, 0.01D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedWallTorchBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */