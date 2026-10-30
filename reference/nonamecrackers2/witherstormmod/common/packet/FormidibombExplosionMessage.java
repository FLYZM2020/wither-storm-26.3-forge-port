/*     */ package nonamecrackers2.witherstormmod.common.packet;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*     */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*     */ 
/*     */ 
/*     */ public class FormidibombExplosionMessage
/*     */   extends Packet
/*     */ {
/*     */   private int entityId;
/*     */   private double x;
/*     */   private double y;
/*     */   private double z;
/*     */   private byte radius;
/*     */   private byte squish;
/*     */   
/*     */   public FormidibombExplosionMessage(@Nullable Entity entity, double x, double y, double z, int radius, int squish) {
/*  24 */     super(true);
/*  25 */     if (entity != null)
/*  26 */       this.entityId = entity.m_19879_(); 
/*  27 */     this.x = x;
/*  28 */     this.y = y;
/*  29 */     this.z = z;
/*  30 */     this.radius = (byte)radius;
/*  31 */     this.squish = (byte)squish;
/*     */   }
/*     */ 
/*     */   
/*     */   public FormidibombExplosionMessage() {
/*  36 */     super(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  41 */     return this.entityId;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getX() {
/*  46 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getY() {
/*  51 */     return this.y;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getZ() {
/*  56 */     return this.z;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRadius() {
/*  61 */     return this.radius;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSquish() {
/*  66 */     return this.squish;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/*  72 */     buffer.writeInt(this.entityId);
/*  73 */     buffer.writeDouble(this.x);
/*  74 */     buffer.writeDouble(this.y);
/*  75 */     buffer.writeDouble(this.z);
/*  76 */     buffer.writeByte(this.radius);
/*  77 */     buffer.writeByte(this.squish);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
/*  83 */     this.entityId = buffer.readInt();
/*  84 */     this.x = buffer.readDouble();
/*  85 */     this.y = buffer.readDouble();
/*  86 */     this.z = buffer.readDouble();
/*  87 */     this.radius = buffer.readByte();
/*  88 */     this.squish = buffer.readByte();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Runnable getProcessor(NetworkEvent.Context context) {
/*  94 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return "FormidibombExplosionMessage[id=" + this.entityId + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", radius=" + this.radius + ", squish=" + this.squish + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\FormidibombExplosionMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */