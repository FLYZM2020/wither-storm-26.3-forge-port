/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.syncher.SynchedEntityData;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StormMetadataMessage
/*    */   extends DistantRendererMessage
/*    */ {
/*    */   private int entityId;
/*    */   private List<SynchedEntityData.DataValue<?>> packedItems;
/*    */   
/*    */   @Deprecated
/*    */   public StormMetadataMessage(List<Integer> applicable, int id, SynchedEntityData data) {
/* 22 */     super(true, applicable);
/* 23 */     this.entityId = id;
/* 24 */     this.packedItems = getPackedData(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public StormMetadataMessage(List<Integer> applicable, int id, List<SynchedEntityData.DataValue<?>> packedItems) {
/* 29 */     super(true, applicable);
/* 30 */     this.entityId = id;
/* 31 */     this.packedItems = packedItems;
/*    */   }
/*    */ 
/*    */   
/*    */   public StormMetadataMessage() {
/* 36 */     super(false, Lists.newArrayList());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityID() {
/* 41 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<SynchedEntityData.DataValue<?>> getUnpackedItems() {
/* 46 */     return this.packedItems;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 52 */     super.encode(buffer);
/* 53 */     buffer.m_130130_(this.entityId);
/* 54 */     for (SynchedEntityData.DataValue<?> value : this.packedItems)
/* 55 */       value.m_252897_(buffer); 
/* 56 */     buffer.writeByte(255);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 62 */     super.decode(buffer);
/* 63 */     this.entityId = buffer.m_130242_();
/* 64 */     List<SynchedEntityData.DataValue<?>> packedData = Lists.newArrayList();
/*    */     int j;
/* 66 */     while ((j = buffer.readUnsignedByte()) != 255)
/* 67 */       packedData.add(SynchedEntityData.DataValue.m_252860_(buffer, j)); 
/* 68 */     this.packedItems = packedData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 74 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     return "StormMetadataMessage[id=" + this.entityId + ", data=" + this.packedItems.toString() + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\StormMetadataMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */