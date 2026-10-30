package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({SoundManager.class})
public interface MixinSoundManagerAccessor {
  @Accessor("soundEngine")
  SoundEngine witherstormmod$getSoundEngine();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinSoundManagerAccessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */