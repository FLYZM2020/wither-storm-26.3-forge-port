package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({PartDefinition.class})
public interface IMixinPartDefinition {
  @Accessor
  List<CubeDefinition> getCubes();
  
  @Accessor
  PartPose getPartPose();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinPartDefinition.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */