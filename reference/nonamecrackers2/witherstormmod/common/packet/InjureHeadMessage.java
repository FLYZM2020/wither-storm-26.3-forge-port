/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class InjureHeadMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private byte head;
/*    */   private InteractionHand hand;
/*    */   
/*    */   public InjureHeadMessage(WitherStormEntity entity, int head, InteractionHand hand) {
/* 17 */     super(true);
/* 18 */     this.entityId = entity.m_19879_();
/* 19 */     this.head = (byte)head;
/* 20 */     this.hand = hand;
/*    */   }
/*    */ 
/*    */   
/*    */   public InjureHeadMessage() {
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
/*    */   public InteractionHand getHand() {
/* 40 */     return this.hand;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 46 */     buffer.writeInt(this.entityId);
/* 47 */     buffer.writeByte(this.head);
/* 48 */     buffer.m_130068_((Enum)this.hand);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
/* 54 */     this.entityId = buffer.readInt();
/* 55 */     this.head = buffer.readByte();
/* 56 */     this.hand = (InteractionHand)buffer.m_130066_(InteractionHand.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 62 */     return () -> WitherStormModMessageHandlerServer.processInjureHeadMessage(this, context.getSender());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 68 */     return "InjureHeadMessage[entityId=" + this.entityId + ", head=" + this.head + ", hand=" + this.hand + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\InjureHeadMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */