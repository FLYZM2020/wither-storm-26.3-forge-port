package nonamecrackers2.witherstormmod.mixin;

import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({LivingEntity.class})
public interface MixinLivingEntityAccessor {
  @Accessor("lastHurtByPlayer")
  @Nullable
  Player witherstormmod$getLastHurtByPlayer();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinLivingEntityAccessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */