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
/*    */ public class UpdateStormHeadLookMessage
/*    */   extends DistantRendererMessage
/*    */ {
/*    */   private int entityId;
/*    */   private byte yHeadRot;
/*    */   
/*    */   @Deprecated
/*    */   public UpdateStormHeadLookMessage(List<Integer> applicable, WitherStormEntity entity, byte yHeadRot) {
/* 22 */     super(true, applicable);
/* 23 */     this.entityId = entity.m_19879_();
/* 24 */     this.yHeadRot = yHeadRot;
/*    */   }
/*    */ 
/*    */   
/*    */   public UpdateStormHeadLookMessage(List<Integer> applicable, int id, byte yHeadRot) {
/* 29 */     super(true, applicable);
/* 30 */     this.entityId = id;
/* 31 */     this.yHeadRot = yHeadRot;
/*    */   }
/*    */ 
/*    */   
/*    */   public UpdateStormHeadLookMessage() {
/* 36 */     super(false, Lists.newArrayList());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityID() {
/* 41 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getYRot() {
/* 46 */     return this.yHeadRot;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 62 */     super.decode(buffer);
/* 63 */     this.entityId = buffer.m_130242_();
/* 64 */     this.yHeadRot = buffer.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 72 */     super.encode(buffer);
/* 73 */     buffer.m_130130_(this.entityId);
/* 74 */     buffer.writeByte(this.yHeadRot);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 82 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     return "UpdateStormHeadLookMessage[id=" + this.entityId + ", yHeadRot=" + this.yHeadRot + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\UpdateStormHeadLookMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */