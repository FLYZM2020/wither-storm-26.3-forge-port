/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class OnHeadAttackedMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private int headIndex;
/*    */   
/*    */   public OnHeadAttackedMessage(int entityId, int headIndex) {
/* 15 */     super(true);
/* 16 */     this.entityId = entityId;
/* 17 */     this.headIndex = headIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public OnHeadAttackedMessage() {
/* 22 */     super(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void decode(FriendlyByteBuf buffer) {
/* 28 */     this.entityId = buffer.m_130242_();
/* 29 */     this.headIndex = buffer.m_130242_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void encode(FriendlyByteBuf buffer) {
/* 35 */     buffer.m_130130_(this.entityId);
/* 36 */     buffer.m_130130_(this.headIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityId() {
/* 41 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeadIndex() {
/* 46 */     return this.headIndex;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 52 */     return client(() -> WitherStormModMessageHandlerClient.processOnHeadAttackedMessage(this));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\OnHeadAttackedMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */