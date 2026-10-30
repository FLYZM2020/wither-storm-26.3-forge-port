package nonamecrackers2.witherstormmod.mixin;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.concurrent.locks.ReadWriteLock;
import net.minecraft.network.syncher.SynchedEntityData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({SynchedEntityData.class})
public interface IMixinSynchedEntityData {
  @Accessor
  Int2ObjectMap<SynchedEntityData.DataItem<?>> getItemsById();
  
  @Accessor
  ReadWriteLock getLock();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinSynchedEntityData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */