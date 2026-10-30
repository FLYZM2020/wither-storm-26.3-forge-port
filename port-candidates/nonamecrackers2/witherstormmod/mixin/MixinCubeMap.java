package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.renderer.CubeMap;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({CubeMap.class})
public interface MixinCubeMap {
  @Accessor
  Identifier[] getImages();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinCubeMap.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */