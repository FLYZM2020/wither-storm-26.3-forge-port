/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ 
/*    */ public class SuperBeaconToggleAreaMessage
/*    */   extends Packet
/*    */ {
/*    */   private boolean shouldShow;
/*    */   
/*    */   public SuperBeaconToggleAreaMessage(boolean shouldShow) {
/* 13 */     super(true);
/* 14 */     this.shouldShow = shouldShow;
/*    */   }
/*    */ 
/*    */   
/*    */   public SuperBeaconToggleAreaMessage() {
/* 19 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldShowArea() {
/* 24 */     return this.shouldShow;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 30 */     this.shouldShow = buffer.readBoolean();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 36 */     buffer.writeBoolean(this.shouldShow);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 42 */     return () -> WitherStormModMessageHandlerServer.processSuperBeaconToggleAreaMessage(this, context.getSender());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\SuperBeaconToggleAreaMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */