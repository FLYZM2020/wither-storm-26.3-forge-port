package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({PostChain.class})
public interface IMixinPostChain {
  @Accessor
  List<PostPass> getPasses();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinPostChain.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */