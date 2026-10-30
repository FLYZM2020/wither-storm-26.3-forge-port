/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherStormModChunkLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements ICapabilitySerializable<Tag>
/*    */ {
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
/* 83 */     return (capability == WitherStormModCapabilities.CHUNK_LOADER) ? chunkLoader.cast() : LazyOptional.empty();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Tag serializeNBT() {
/* 89 */     return (Tag)((WitherStormModChunkLoader)chunkLoader.orElse(null)).write();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deserializeNBT(Tag nbt) {
/* 95 */     ((WitherStormModChunkLoader)chunkLoader.orElse(null)).read((CompoundTag)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModCapabilities$8.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */