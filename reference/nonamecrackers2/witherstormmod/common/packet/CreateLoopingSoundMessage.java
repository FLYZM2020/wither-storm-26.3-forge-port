/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class CreateLoopingSoundMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private byte phase;
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   
/*    */   public CreateLoopingSoundMessage(WitherStormEntity entity) {
/* 21 */     super(true);
/* 22 */     this.entityId = entity.m_19879_();
/* 23 */     this.phase = (byte)entity.getPhase();
/* 24 */     this.x = entity.m_20185_();
/* 25 */     this.y = entity.m_20188_();
/* 26 */     this.z = entity.m_20189_();
/*    */   }
/*    */ 
/*    */   
/*    */   public CreateLoopingSoundMessage() {
/* 31 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityID() {
/* 36 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getPhase() {
/* 41 */     return this.phase;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 46 */     return this.x;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 51 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 56 */     return this.z;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 62 */     buffer.m_130130_(this.entityId);
/* 63 */     buffer.writeByte(this.phase);
/* 64 */     buffer.writeDouble(this.x);
/* 65 */     buffer.writeDouble(this.y);
/* 66 */     buffer.writeDouble(this.z);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
/* 72 */     this.entityId = buffer.m_130242_();
/* 73 */     this.phase = buffer.readByte();
/* 74 */     this.x = buffer.readDouble();
/* 75 */     this.y = buffer.readDouble();
/* 76 */     this.z = buffer.readDouble();
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
/* 88 */     return "CreateLoopingSoundMessage[id=" + this.entityId + ", phase=" + this.phase + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\CreateLoopingSoundMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */