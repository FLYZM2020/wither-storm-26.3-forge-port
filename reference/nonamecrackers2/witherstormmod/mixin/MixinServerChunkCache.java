package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.server.level.DistanceManager;
import net.minecraft.server.level.ServerChunkCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ServerChunkCache.class})
public interface MixinServerChunkCache {
  @Accessor
  DistanceManager getDistanceManager();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinServerChunkCache.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */