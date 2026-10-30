/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UpdateBodyRotMessage
/*     */   extends Packet
/*     */ {
/*     */   private float yBodyRot;
/*     */   private float xBodyRot;
/*     */   private int entityId;
/*     */   
/*     */   private UpdateBodyRotMessage(WitherStormEntity entity) {
/* 176 */     super(true);
/* 177 */     this.yBodyRot = entity.f_20883_;
/* 178 */     this.xBodyRot = entity.xBodyRot;
/* 179 */     this.entityId = entity.m_19879_();
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdateBodyRotMessage() {
/* 184 */     super(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) {
/* 190 */     this.yBodyRot = buffer.readFloat();
/* 191 */     this.xBodyRot = buffer.readFloat();
/* 192 */     this.entityId = buffer.m_130242_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/* 198 */     buffer.writeFloat(this.yBodyRot);
/* 199 */     buffer.writeFloat(this.xBodyRot);
/* 200 */     buffer.m_130130_(this.entityId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 206 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 212 */     return "UpdateBodyRotMessage[yBodyRot=" + this.yBodyRot + ", xBodyRot=" + this.xBodyRot + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processMessage(UpdateBodyRotMessage message) {
/* 218 */     Minecraft mc = Minecraft.m_91087_();
/* 219 */     ClientLevel world = mc.f_91073_;
/* 220 */     WitherStormEntity entity = (WitherStormEntity)mc.f_91073_.m_6815_(message.entityId);
/* 221 */     if (entity != null)
/* 222 */       entity.lerpBodyRotationTo(message.xBodyRot, message.yBodyRot, 3); 
/* 223 */     world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity distantEntity = distantRenderer.get(message.entityId);
/*     */           if (distantEntity != null)
/*     */             distantEntity.lerpBodyRotationTo(message.xBodyRot, message.yBodyRot, 3); 
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\controller\WitherStormBodyController$UpdateBodyRotMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */