/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class UpdateDamagingProjectileMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private double xPower;
/*    */   private double yPower;
/*    */   private double zPower;
/*    */   
/*    */   public UpdateDamagingProjectileMessage(AbstractHurtingProjectile entity) {
/* 20 */     super(true);
/* 21 */     this.entityId = entity.m_19879_();
/* 22 */     this.xPower = entity.f_36813_;
/* 23 */     this.yPower = entity.f_36814_;
/* 24 */     this.zPower = entity.f_36815_;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityId() {
/* 29 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getXPower() {
/* 34 */     return this.xPower;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getYPower() {
/* 39 */     return this.yPower;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZPower() {
/* 44 */     return this.zPower;
/*    */   }
/*    */ 
/*    */   
/*    */   public UpdateDamagingProjectileMessage() {
/* 49 */     super(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 55 */     this.entityId = buffer.m_130242_();
/* 56 */     this.xPower = buffer.readDouble();
/* 57 */     this.yPower = buffer.readDouble();
/* 58 */     this.zPower = buffer.readDouble();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 64 */     buffer.m_130130_(this.entityId);
/* 65 */     buffer.writeDouble(this.xPower);
/* 66 */     buffer.writeDouble(this.yPower);
/* 67 */     buffer.writeDouble(this.zPower);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 73 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 79 */     return "UpdateDamagingProjectileMessage[id=" + this.entityId + ", xPower=" + this.xPower + ", yPower=" + this.yPower + ", zPower=" + this.zPower + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\UpdateDamagingProjectileMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */