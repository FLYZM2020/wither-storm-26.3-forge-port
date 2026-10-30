/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class BlindScreenMessage
/*    */   extends Packet
/*    */ {
/*    */   private int duration;
/*    */   private int fadeInDuration;
/*    */   private int fadeOutDuration;
/*    */   
/*    */   public BlindScreenMessage(int duration, int fadeInDuration, int fadeOutDuration) {
/* 18 */     super(true);
/* 19 */     this.duration = duration;
/* 20 */     this.fadeInDuration = fadeInDuration;
/* 21 */     this.fadeOutDuration = fadeOutDuration;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlindScreenMessage() {
/* 26 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDuration() {
/* 31 */     return this.duration;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFadeInDuration() {
/* 36 */     return this.fadeInDuration;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFadeOutDuration() {
/* 41 */     return this.fadeOutDuration;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
/* 47 */     this.duration = buffer.readInt();
/* 48 */     this.fadeInDuration = buffer.readInt();
/* 49 */     this.fadeOutDuration = buffer.readInt();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 55 */     buffer.writeInt(this.duration);
/* 56 */     buffer.writeInt(this.fadeInDuration);
/* 57 */     buffer.writeInt(this.fadeOutDuration);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 63 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\BlindScreenMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */