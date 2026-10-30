/*     */ package nonamecrackers2.witherstormmod.common.serializer;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.syncher.EntityDataSerializer;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class null
/*     */   implements EntityDataSerializer<Optional<Vec3>>
/*     */ {
/*     */   public void write(FriendlyByteBuf buffer, Optional<Vec3> vector) {
/* 103 */     buffer.writeBoolean(vector.isPresent());
/* 104 */     vector.ifPresent(pos -> {
/*     */           buffer.writeDouble(pos.m_7096_());
/*     */           buffer.writeDouble(pos.m_7098_());
/*     */           buffer.writeDouble(pos.m_7094_());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<Vec3> read(FriendlyByteBuf buffer) {
/* 115 */     return buffer.readBoolean() ? Optional.<Vec3>of(new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble())) : Optional.<Vec3>empty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<Vec3> copy(Optional<Vec3> vector) {
/* 121 */     return vector;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\serializer\WitherStormModDataSerializers$4.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */