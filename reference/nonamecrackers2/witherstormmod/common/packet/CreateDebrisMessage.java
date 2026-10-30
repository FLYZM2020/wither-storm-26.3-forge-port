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
/*    */ public class CreateDebrisMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private boolean hidden;
/*    */   
/*    */   public CreateDebrisMessage(WitherStormEntity entity, boolean hidden) {
/* 18 */     super(true);
/* 19 */     this.entityId = entity.m_19879_();
/* 20 */     this.hidden = hidden;
/*    */   }
/*    */ 
/*    */   
/*    */   public CreateDebrisMessage() {
/* 25 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityID() {
/* 30 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDebrisHidden() {
/* 35 */     return this.hidden;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 41 */     buffer.m_130130_(this.entityId);
/* 42 */     buffer.writeBoolean(this.hidden);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 48 */     this.entityId = buffer.m_130242_();
/* 49 */     this.hidden = buffer.readBoolean();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 55 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return "CreateDebrisMessage[id=" + this.entityId + ", hidden=" + this.hidden + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\CreateDebrisMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */