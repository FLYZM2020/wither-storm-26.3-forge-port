/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
/*    */ 
/*    */ public class EntitySyncableDataMessage
/*    */   extends Packet
/*    */ {
/*    */   private int id;
/*    */   private final EntitySyncableData entity;
/*    */   private FriendlyByteBuf buffer;
/*    */   
/*    */   public EntitySyncableDataMessage(int id, EntitySyncableData entity) {
/* 19 */     super(true);
/* 20 */     this.id = id;
/* 21 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySyncableDataMessage() {
/* 26 */     super(false);
/* 27 */     this.entity = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 32 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendlyByteBuf getBuffer() {
/* 37 */     return this.buffer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 43 */     buffer.m_130130_(this.id);
/* 44 */     this.entity.writeData(buffer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
/* 50 */     this.id = buffer.m_130242_();
/* 51 */     this.buffer = buffer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 57 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return "EntitySyncableDataMessage[entity=" + this.id + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\EntitySyncableDataMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */