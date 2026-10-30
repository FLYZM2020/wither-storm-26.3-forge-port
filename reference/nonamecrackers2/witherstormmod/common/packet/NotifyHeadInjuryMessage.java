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
/*    */ public class NotifyHeadInjuryMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private byte head;
/*    */   
/*    */   public NotifyHeadInjuryMessage(WitherStormEntity entity, int head) {
/* 18 */     super(true);
/* 19 */     this.entityId = entity.m_19879_();
/* 20 */     this.head = (byte)head;
/*    */   }
/*    */ 
/*    */   
/*    */   public NotifyHeadInjuryMessage() {
/* 25 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityID() {
/* 30 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getHead() {
/* 35 */     return this.head;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 40 */     buffer.writeInt(this.entityId);
/* 41 */     buffer.writeByte(this.head);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 47 */     this.entityId = buffer.readInt();
/* 48 */     this.head = buffer.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 54 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     return "NotifyHeadInjuryMessage[entityId=" + this.entityId + ", head=" + this.head + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\NotifyHeadInjuryMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */