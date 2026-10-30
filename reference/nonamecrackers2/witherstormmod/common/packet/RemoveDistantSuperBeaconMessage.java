/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class RemoveDistantSuperBeaconMessage
/*    */   extends Packet
/*    */ {
/*    */   private BlockPos pos;
/*    */   
/*    */   public RemoveDistantSuperBeaconMessage(BlockPos pos) {
/* 17 */     super(true);
/* 18 */     this.pos = pos;
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoveDistantSuperBeaconMessage() {
/* 23 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos getPos() {
/* 28 */     return this.pos;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 34 */     this.pos = buffer.m_130135_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 40 */     buffer.m_130064_(this.pos);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 46 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\RemoveDistantSuperBeaconMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */