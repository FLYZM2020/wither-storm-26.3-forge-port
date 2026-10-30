/*    */ package nonamecrackers2.witherstormmod.common.serializer;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.syncher.EntityDataSerializer;
/*    */ import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
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
/*    */   implements EntityDataSerializer<List<CompoundTag>>
/*    */ {
/*    */   public void write(FriendlyByteBuf buffer, List<CompoundTag> list) {
/* 57 */     CompoundTag compound = new CompoundTag();
/* 58 */     compound.m_128365_("List", (Tag)WitherStormModNBTUtil.writeCompoundList(list));
/* 59 */     buffer.m_130079_(compound);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<CompoundTag> read(FriendlyByteBuf buffer) {
/* 65 */     CompoundTag compound = buffer.m_130260_();
/* 66 */     return WitherStormModNBTUtil.readCompoundList(compound.m_128437_("List", 10));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<CompoundTag> copy(List<CompoundTag> list) {
/* 72 */     return new ArrayList<>(list);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\serializer\WitherStormModDataSerializers$2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */