package nonamecrackers2.witherstormmod.common.util;

import net.minecraft.network.FriendlyByteBuf;

public interface EntitySyncableData {
  void writeData(FriendlyByteBuf paramFriendlyByteBuf);
  
  void readData(FriendlyByteBuf paramFriendlyByteBuf);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\EntitySyncableData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */