package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({OverlayTexture.class})
public interface IMixinOverlayTexture {
  @Accessor
  DynamicTexture getTexture();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinOverlayTexture.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */