package nonamecrackers2.witherstormmod.mixin;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.server.level.DistanceManager;
import net.minecraft.server.level.Ticket;
import net.minecraft.util.SortedArraySet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({DistanceManager.class})
public interface MixinDistanceManager {
  @Accessor
  Long2ObjectOpenHashMap<SortedArraySet<Ticket<?>>> getTickets();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinDistanceManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */