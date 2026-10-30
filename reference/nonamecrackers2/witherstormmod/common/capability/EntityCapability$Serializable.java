/*    */ package nonamecrackers2.witherstormmod.common.capability;
/*    */ 
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
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
/*    */ public class Serializable<O extends EntityCapability<?, ?>, C extends Capability<O>>
/*    */   implements ICapabilitySerializable<Tag>
/*    */ {
/*    */   private final C capability;
/*    */   private final LazyOptional<O> optional;
/*    */   
/*    */   public Serializable(C capability, LazyOptional<O> optional) {
/* 40 */     this.capability = capability;
/* 41 */     this.optional = optional;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 47 */     return (cap == this.capability) ? this.optional.cast() : LazyOptional.empty();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Tag serializeNBT() {
/* 53 */     return this.optional.isPresent() ? (Tag)((EntityCapability)this.optional.orElse(null)).write() : null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deserializeNBT(Tag nbt) {
/* 59 */     if (this.optional.isPresent())
/* 60 */       ((EntityCapability)this.optional.orElse(null)).read((CompoundTag)nbt); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\EntityCapability$Serializable.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */