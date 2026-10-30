package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Creeper.class})
public interface IMixinCreeper {
  @Accessor
  void setExplosionRadius(int paramInt);
  
  @Accessor
  void setMaxSwell(int paramInt);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinCreeper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */