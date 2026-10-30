/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import nonamecrackers2.witherstormmod.common.capability.ChunkLoadingBlockEntities;
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
/*    */   implements ICapabilitySerializable<CompoundTag>
/*    */ {
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 60 */     return (cap == WitherStormModCapabilities.CHUNK_LOADING_BLOCK_ENTITIES) ? chunkLoadingBlockEntities.cast() : LazyOptional.empty();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundTag serializeNBT() {
/* 66 */     return ((ChunkLoadingBlockEntities)chunkLoadingBlockEntities.orElse(null)).write();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundTag nbt) {
/* 72 */     ((ChunkLoadingBlockEntities)chunkLoadingBlockEntities.orElse(null)).read(nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModCapabilities$7.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */