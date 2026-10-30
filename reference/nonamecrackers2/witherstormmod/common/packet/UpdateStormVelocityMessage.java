/*     */ package nonamecrackers2.witherstormmod.common.packet;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UpdateStormVelocityMessage
/*     */   extends DistantRendererMessage
/*     */ {
/*     */   private int entityId;
/*     */   private int x;
/*     */   private int y;
/*     */   private int z;
/*     */   
/*     */   @Deprecated
/*     */   public UpdateStormVelocityMessage(List<Integer> applicable, WitherStormEntity entity) {
/*  26 */     super(true, applicable);
/*  27 */     this.entityId = entity.m_19879_();
/*  28 */     Vec3 delta = entity.m_20184_();
/*  29 */     double x = Mth.m_14008_(delta.f_82479_, -3.9D, 3.9D);
/*  30 */     double y = Mth.m_14008_(delta.f_82480_, -3.9D, 3.9D);
/*  31 */     double z = Mth.m_14008_(delta.f_82481_, -3.9D, 3.9D);
/*  32 */     this.x = (int)(x * 8000.0D);
/*  33 */     this.y = (int)(y * 8000.0D);
/*  34 */     this.z = (int)(z * 8000.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdateStormVelocityMessage(List<Integer> applicable, int entityId, int xa, int ya, int za) {
/*  39 */     super(true, applicable);
/*  40 */     this.entityId = entityId;
/*  41 */     this.x = xa;
/*  42 */     this.y = ya;
/*  43 */     this.z = za;
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdateStormVelocityMessage() {
/*  48 */     super(false, Lists.newArrayList());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityID() {
/*  53 */     return this.entityId;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getX() {
/*  58 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getY() {
/*  63 */     return this.y;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getZ() {
/*  68 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) {
/*  74 */     super.decode(buffer);
/*  75 */     this.entityId = buffer.m_130242_();
/*  76 */     this.x = buffer.readShort();
/*  77 */     this.y = buffer.readShort();
/*  78 */     this.z = buffer.readShort();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/*  84 */     super.encode(buffer);
/*  85 */     buffer.m_130130_(this.entityId);
/*  86 */     buffer.writeShort(this.x);
/*  87 */     buffer.writeShort(this.y);
/*  88 */     buffer.writeShort(this.z);
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
/* 100 */     return "UpdateStormVelocityMessage[id=" + this.entityId + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\UpdateStormVelocityMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */