/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class PlayerMotionMessage
/*    */   extends Packet
/*    */ {
/*    */   private Vec3 motion;
/*    */   
/*    */   public PlayerMotionMessage(Vec3 motion) {
/* 17 */     super(true);
/* 18 */     this.motion = motion;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerMotionMessage() {
/* 23 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Vec3 getMotion() {
/* 28 */     return this.motion;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 34 */     buffer.writeDouble(this.motion.f_82479_);
/* 35 */     buffer.writeDouble(this.motion.f_82480_);
/* 36 */     buffer.writeDouble(this.motion.f_82481_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 42 */     double x = buffer.readDouble();
/* 43 */     double y = buffer.readDouble();
/* 44 */     double z = buffer.readDouble();
/* 45 */     this.motion = new Vec3(x, y, z);
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
/* 57 */     return "PlayerMotionMessageToClient[motion=" + String.valueOf(this.motion) + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\PlayerMotionMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */