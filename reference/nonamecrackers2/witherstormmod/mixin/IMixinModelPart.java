package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import java.util.Map;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ModelPart.class})
public interface IMixinModelPart {
  @Accessor
  Map<String, ModelPart> getChildren();
  
  @Accessor
  List<ModelPart.Cube> getCubes();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinModelPart.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */