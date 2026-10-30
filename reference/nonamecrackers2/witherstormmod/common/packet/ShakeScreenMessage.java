/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class ShakeScreenMessage
/*    */   extends Packet
/*    */ {
/*    */   private float duration;
/*    */   private float power;
/*    */   
/*    */   public ShakeScreenMessage(float duration, float power) {
/* 17 */     super(true);
/* 18 */     this.duration = duration;
/* 19 */     this.power = power;
/*    */   }
/*    */ 
/*    */   
/*    */   public ShakeScreenMessage() {
/* 24 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDuration() {
/* 29 */     return this.duration;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPower() {
/* 34 */     return this.power;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 40 */     this.duration = buffer.readFloat();
/* 41 */     this.power = buffer.readFloat();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 47 */     buffer.writeFloat(this.duration);
/* 48 */     buffer.writeFloat(this.power);
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
/* 60 */     return "ShakeScreenMessage[duration=" + this.duration + ", power=" + this.power + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\ShakeScreenMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */