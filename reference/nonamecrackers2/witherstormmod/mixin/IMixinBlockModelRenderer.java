package nonamecrackers2.witherstormmod.mixin;

import java.util.BitSet;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({ModelBlockRenderer.class})
public interface IMixinBlockModelRenderer {
  @Invoker
  void callCalculateShape(BlockAndTintGetter paramBlockAndTintGetter, BlockState paramBlockState, BlockPos paramBlockPos, int[] paramArrayOfint, Direction paramDirection, @Nullable float[] paramArrayOffloat, BitSet paramBitSet);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinBlockModelRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */