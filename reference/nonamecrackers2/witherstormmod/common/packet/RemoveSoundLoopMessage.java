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
/*    */ public class RemoveSoundLoopMessage
/*    */   extends Packet
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public RemoveSoundLoopMessage(WitherStormEntity entity) {
/* 17 */     super(true);
/* 18 */     this.id = entity.m_19879_();
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoveSoundLoopMessage() {
/* 23 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 28 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 34 */     buffer.m_130130_(this.id);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 40 */     this.id = buffer.m_130242_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 46 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     return "RemoveSoundLoopMessage[id=" + this.id + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\RemoveSoundLoopMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */