package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({AreaEffectCloud.class})
public interface MixinAreaEffectCloud {
  @Accessor("effects")
  List<MobEffectInstance> witherstormmod$getEffects();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinAreaEffectCloud.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */