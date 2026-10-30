package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({LightTexture.class})
public interface IMixinLightTexture {
  @Accessor
  DynamicTexture getLightTexture();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinLightTexture.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */