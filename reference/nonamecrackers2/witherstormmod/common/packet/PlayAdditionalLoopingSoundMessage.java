/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class PlayAdditionalLoopingSoundMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private SoundEvent event;
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   
/*    */   public PlayAdditionalLoopingSoundMessage(WitherStormEntity entity, SoundEvent event) {
/* 23 */     super(true);
/* 24 */     this.entityId = entity.m_19879_();
/* 25 */     this.event = event;
/* 26 */     this.x = entity.m_20185_();
/* 27 */     this.y = entity.m_20188_();
/* 28 */     this.z = entity.m_20189_();
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayAdditionalLoopingSoundMessage() {
/* 33 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityID() {
/* 38 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundEvent getSound() {
/* 43 */     return this.event;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 48 */     return this.x;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 53 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 58 */     return this.z;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 64 */     buffer.m_130130_(this.entityId);
/* 65 */     buffer.writeRegistryId(ForgeRegistries.SOUND_EVENTS, this.event);
/* 66 */     buffer.writeDouble(this.x);
/* 67 */     buffer.writeDouble(this.y);
/* 68 */     buffer.writeDouble(this.z);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
/* 74 */     this.entityId = buffer.m_130242_();
/* 75 */     this.event = (SoundEvent)buffer.readRegistryId();
/* 76 */     this.x = buffer.readDouble();
/* 77 */     this.y = buffer.readDouble();
/* 78 */     this.z = buffer.readDouble();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 84 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 90 */     return "PlayAdditionalLoopingSoundMessage[id=" + this.entityId + ", event=" + this.event + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\PlayAdditionalLoopingSoundMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */