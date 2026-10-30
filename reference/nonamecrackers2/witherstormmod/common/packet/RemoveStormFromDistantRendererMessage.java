/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoveStormFromDistantRendererMessage
/*    */   extends DistantRendererMessage
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public RemoveStormFromDistantRendererMessage(List<Integer> applicable, WitherStormEntity entity) {
/* 20 */     super(true, applicable);
/* 21 */     this.id = entity.m_19879_();
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoveStormFromDistantRendererMessage() {
/* 26 */     super(false, Lists.newArrayList());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 31 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 37 */     super.encode(buffer);
/* 38 */     buffer.m_130130_(this.id);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 44 */     super.decode(buffer);
/* 45 */     this.id = buffer.m_130242_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 51 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     return "RemoveStormFromDistantRendererMessage[id=" + this.id + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\RemoveStormFromDistantRendererMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */