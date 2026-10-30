package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({LayerDefinition.class})
public interface IMixinLayerDefinition {
  @Accessor
  MeshDefinition getMesh();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinLayerDefinition.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */