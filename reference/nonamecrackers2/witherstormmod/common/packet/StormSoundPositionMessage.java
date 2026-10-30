/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class StormSoundPositionMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private byte phase;
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   
/*    */   public StormSoundPositionMessage(int id, double x, double y, double z, byte phase) {
/* 20 */     super(true);
/* 21 */     this.entityId = id;
/* 22 */     this.phase = phase;
/* 23 */     this.x = x;
/* 24 */     this.y = y;
/* 25 */     this.z = z;
/* 26 */     this.phase = phase;
/*    */   }
/*    */ 
/*    */   
/*    */   public StormSoundPositionMessage() {
/* 31 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityID() {
/* 36 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 41 */     return this.x;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 46 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 51 */     return this.z;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getPhase() {
/* 56 */     return this.phase;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 62 */     buffer.m_130130_(this.entityId);
/* 63 */     buffer.writeDouble(this.x);
/* 64 */     buffer.writeDouble(this.y);
/* 65 */     buffer.writeDouble(this.z);
/* 66 */     buffer.writeByte(this.phase);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 72 */     this.entityId = buffer.m_130242_();
/* 73 */     this.x = buffer.readDouble();
/* 74 */     this.y = buffer.readDouble();
/* 75 */     this.z = buffer.readDouble();
/* 76 */     this.phase = buffer.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 82 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     return "StormSoundPositionMessage[id=" + this.entityId + ", phase=" + this.phase + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\StormSoundPositionMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */