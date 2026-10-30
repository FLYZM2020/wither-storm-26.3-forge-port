package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({GameRenderer.class})
public interface IMixinGameRenderer {
  @Invoker
  double callGetFov(Camera paramCamera, float paramFloat, boolean paramBoolean);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinGameRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */