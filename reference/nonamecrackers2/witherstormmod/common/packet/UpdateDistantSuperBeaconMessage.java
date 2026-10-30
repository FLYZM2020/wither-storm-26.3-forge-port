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
/*    */ public class UpdateDistantSuperBeaconMessage
/*    */   extends Packet
/*    */ {
/*    */   private BlockPos pos;
/*    */   private int[] color;
/*    */   public boolean active;
/*    */   public int beaconHeight;
/*    */   public float beamWidth;
/*    */   public float outerBeamWidth;
/*    */   
/*    */   public UpdateDistantSuperBeaconMessage(BlockPos pos, int[] color, boolean active, int beaconHeight, float beamWidth, float outerBeamWidth) {
/* 22 */     super(true);
/* 23 */     this.pos = pos;
/* 24 */     this.color = color;
/* 25 */     this.active = active;
/* 26 */     this.beaconHeight = beaconHeight;
/* 27 */     this.beamWidth = beamWidth;
/* 28 */     this.outerBeamWidth = outerBeamWidth;
/*    */   }
/*    */ 
/*    */   
/*    */   public UpdateDistantSuperBeaconMessage() {
/* 33 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos getPos() {
/* 38 */     return this.pos;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] getColor() {
/* 43 */     return this.color;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isActive() {
/* 48 */     return this.active;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBeaconHeight() {
/* 53 */     return this.beaconHeight;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBeamWidth() {
/* 58 */     return this.beamWidth;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getOuterBeamWidth() {
/* 63 */     return this.outerBeamWidth;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 69 */     this.pos = buffer.m_130135_();
/* 70 */     this.color = buffer.m_130100_();
/* 71 */     this.active = buffer.readBoolean();
/* 72 */     this.beaconHeight = buffer.readInt();
/* 73 */     this.beamWidth = buffer.readFloat();
/* 74 */     this.outerBeamWidth = buffer.readFloat();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 80 */     buffer.m_130064_(this.pos);
/* 81 */     buffer.m_130089_(this.color);
/* 82 */     buffer.writeBoolean(this.active);
/* 83 */     buffer.writeInt(this.beaconHeight);
/* 84 */     buffer.writeFloat(this.beamWidth);
/* 85 */     buffer.writeFloat(this.outerBeamWidth);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 91 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\UpdateDistantSuperBeaconMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */