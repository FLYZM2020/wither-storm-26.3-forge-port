/*     */ package nonamecrackers2.witherstormmod.common.packet;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*     */ import org.apache.commons.compress.utils.Lists;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UpdateStormPositionMessage
/*     */   extends DistantRendererMessage
/*     */ {
/*     */   private int entityId;
/*     */   private short xa;
/*     */   private short ya;
/*     */   private short za;
/*     */   private byte yRot;
/*     */   private byte xRot;
/*     */   private boolean onGround;
/*     */   private boolean hasRot;
/*     */   private boolean hasPos;
/*     */   
/*     */   public UpdateStormPositionMessage(List<Integer> applicable, int id) {
/*  27 */     super(true, applicable);
/*  28 */     this.entityId = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdateStormPositionMessage(List<Integer> applicable, int id, byte yRot, byte xRot, boolean onGround) {
/*  33 */     this(applicable, id);
/*  34 */     this.yRot = yRot;
/*  35 */     this.xRot = xRot;
/*  36 */     this.onGround = onGround;
/*  37 */     this.hasRot = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdateStormPositionMessage(List<Integer> applicable, int id, short x, short y, short z, byte yRot, byte xRot, boolean onGround) {
/*  42 */     this(applicable, id);
/*  43 */     this.xa = x;
/*  44 */     this.ya = y;
/*  45 */     this.za = z;
/*  46 */     this.yRot = yRot;
/*  47 */     this.xRot = xRot;
/*  48 */     this.onGround = onGround;
/*  49 */     this.hasRot = true;
/*  50 */     this.hasPos = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdateStormPositionMessage(List<Integer> applicable, int id, short x, short y, short z, boolean onGround) {
/*  55 */     this(applicable, id);
/*  56 */     this.xa = x;
/*  57 */     this.ya = y;
/*  58 */     this.za = z;
/*  59 */     this.onGround = onGround;
/*  60 */     this.hasPos = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdateStormPositionMessage() {
/*  65 */     super(false, Lists.newArrayList());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityID() {
/*  70 */     return this.entityId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRotation() {
/*  75 */     return this.hasRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPosition() {
/*  80 */     return this.hasPos;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onGround() {
/*  85 */     return this.onGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getX() {
/*  90 */     return this.xa;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getY() {
/*  95 */     return this.ya;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getZ() {
/* 100 */     return this.za;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getYRot() {
/* 105 */     return this.yRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getXRot() {
/* 110 */     return this.xRot;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) {
/* 116 */     super.decode(buffer);
/* 117 */     this.entityId = buffer.m_130242_();
/* 118 */     this.hasPos = buffer.readBoolean();
/* 119 */     if (this.hasPos) {
/*     */       
/* 121 */       this.xa = buffer.readShort();
/* 122 */       this.ya = buffer.readShort();
/* 123 */       this.za = buffer.readShort();
/*     */     } 
/* 125 */     this.hasRot = buffer.readBoolean();
/* 126 */     if (this.hasRot) {
/*     */       
/* 128 */       this.yRot = buffer.readByte();
/* 129 */       this.xRot = buffer.readByte();
/*     */     } 
/* 131 */     this.onGround = buffer.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/* 137 */     super.encode(buffer);
/* 138 */     buffer.m_130130_(this.entityId);
/* 139 */     buffer.writeBoolean(this.hasPos);
/* 140 */     if (this.hasPos) {
/*     */       
/* 142 */       buffer.writeShort(this.xa);
/* 143 */       buffer.writeShort(this.ya);
/* 144 */       buffer.writeShort(this.za);
/*     */     } 
/* 146 */     buffer.writeBoolean(this.hasRot);
/* 147 */     if (this.hasRot) {
/*     */       
/* 149 */       buffer.writeByte(this.yRot);
/* 150 */       buffer.writeByte(this.xRot);
/*     */     } 
/* 152 */     buffer.writeBoolean(this.onGround);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 158 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 164 */     return "UpdateWitherstormPositionMessage[id=" + this.entityId + ", hasPos=" + this.hasPos + ", hasRot=" + this.hasRot + ", x=" + this.xa + ", y=" + this.ya + ", z=" + this.za + ", yRot=" + this.yRot + ", xRot=" + this.xRot + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\UpdateStormPositionMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */