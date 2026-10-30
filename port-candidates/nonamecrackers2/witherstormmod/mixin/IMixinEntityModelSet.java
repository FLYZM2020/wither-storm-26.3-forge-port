package nonamecrackers2.witherstormmod.mixin;

import java.util.Map;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({EntityModelSet.class})
public interface IMixinEntityModelSet {
  @Accessor
  Map<ModelLayerLocation, LayerDefinition> getRoots();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinEntityModelSet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */