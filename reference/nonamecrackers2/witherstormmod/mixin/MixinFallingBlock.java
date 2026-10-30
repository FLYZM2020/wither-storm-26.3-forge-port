package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.FallingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({FallingBlock.class})
public interface MixinFallingBlock {
  @Invoker
  void callFalling(FallingBlockEntity paramFallingBlockEntity);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinFallingBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */