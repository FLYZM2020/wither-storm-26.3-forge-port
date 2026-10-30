/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ 
/*    */ public class SuperBeaconSetEffectMessage
/*    */   extends Packet
/*    */ {
/*    */   private int effect;
/*    */   
/*    */   public SuperBeaconSetEffectMessage(int effect) {
/* 13 */     super(true);
/* 14 */     this.effect = effect;
/*    */   }
/*    */ 
/*    */   
/*    */   public SuperBeaconSetEffectMessage() {
/* 19 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEffectId() {
/* 24 */     return this.effect;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 30 */     this.effect = buffer.m_130242_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 36 */     buffer.m_130130_(this.effect);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 42 */     return () -> WitherStormModMessageHandlerServer.processSuperBeaconSetEffectMessage(this, context.getSender());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\SuperBeaconSetEffectMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */