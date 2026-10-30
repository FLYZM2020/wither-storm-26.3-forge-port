/*     */ package nonamecrackers2.witherstormmod.common.packet;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StormTeleportMessage
/*     */   extends DistantRendererMessage
/*     */ {
/*     */   private int entityId;
/*     */   private double x;
/*     */   private double y;
/*     */   private double z;
/*     */   private byte yRot;
/*     */   private byte xRot;
/*     */   private boolean onGround;
/*     */   
/*     */   @Deprecated
/*     */   public StormTeleportMessage(List<Integer> applicable, WitherStormEntity entity) {
/*  28 */     super(true, applicable);
/*  29 */     this.entityId = entity.m_19879_();
/*  30 */     Vec3 vec = entity.m_213870_();
/*  31 */     this.x = vec.f_82479_;
/*  32 */     this.y = vec.f_82480_;
/*  33 */     this.z = vec.f_82481_;
/*  34 */     this.yRot = (byte)(int)(entity.m_146908_() * 256.0F / 360.0F);
/*  35 */     this.xRot = (byte)(int)(entity.m_146909_() * 256.0F / 360.0F);
/*  36 */     this.onGround = entity.m_20096_();
/*     */   }
/*     */ 
/*     */   
/*     */   public StormTeleportMessage(List<Integer> applicable, int entityId, double x, double y, double z, byte yRot, byte xRot, boolean onGround) {
/*  41 */     super(true, applicable);
/*  42 */     this.entityId = entityId;
/*  43 */     this.x = x;
/*  44 */     this.y = y;
/*  45 */     this.z = z;
/*  46 */     this.yRot = yRot;
/*  47 */     this.xRot = xRot;
/*  48 */     this.onGround = onGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public StormTeleportMessage() {
/*  53 */     super(false, Lists.newArrayList());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityID() {
/*  58 */     return this.entityId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onGround() {
/*  63 */     return this.onGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getX() {
/*  68 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getY() {
/*  73 */     return this.y;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getZ() {
/*  78 */     return this.z;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getYRot() {
/*  83 */     return this.yRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getXRot() {
/*  88 */     return this.xRot;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/*  94 */     super.encode(buffer);
/*  95 */     buffer.m_130130_(this.entityId);
/*  96 */     buffer.writeDouble(this.x);
/*  97 */     buffer.writeDouble(this.y);
/*  98 */     buffer.writeDouble(this.z);
/*  99 */     buffer.writeByte(this.yRot);
/* 100 */     buffer.writeByte(this.xRot);
/* 101 */     buffer.writeBoolean(this.onGround);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
/* 107 */     super.decode(buffer);
/* 108 */     this.entityId = buffer.m_130242_();
/* 109 */     this.x = buffer.readDouble();
/* 110 */     this.y = buffer.readDouble();
/* 111 */     this.z = buffer.readDouble();
/* 112 */     this.yRot = buffer.readByte();
/* 113 */     this.xRot = buffer.readByte();
/* 114 */     this.onGround = buffer.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 120 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 126 */     return "StormTeleportMessage[id=" + this.entityId + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", yRot=" + this.yRot + ", xRot=" + this.xRot + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\StormTeleportMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */