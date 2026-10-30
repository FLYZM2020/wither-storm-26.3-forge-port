/*    */ package nonamecrackers2.witherstormmod.common.serializer;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.syncher.EntityDataSerializer;
/*    */ import net.minecraft.world.phys.Vec2;
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
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements EntityDataSerializer<Vec2>
/*    */ {
/*    */   public void write(FriendlyByteBuf buffer, Vec2 vector) {
/* 81 */     buffer.writeFloat(vector.f_82470_);
/* 82 */     buffer.writeFloat(vector.f_82471_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vec2 read(FriendlyByteBuf buffer) {
/* 88 */     return new Vec2(buffer.readFloat(), buffer.readFloat());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vec2 copy(Vec2 vector) {
/* 94 */     return new Vec2(vector.f_82470_, vector.f_82471_);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\serializer\WitherStormModDataSerializers$3.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */