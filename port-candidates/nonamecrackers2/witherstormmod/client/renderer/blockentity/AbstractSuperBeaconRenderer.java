/*     */ package nonamecrackers2.witherstormmod.client.renderer.blockentity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import net.minecraft.client.Camera;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.rendertype.RenderType;
/*     */ import net.minecraft.client.renderer.blockentity.BeaconRenderer;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.core.Position;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ public abstract class AbstractSuperBeaconRenderer<T extends AbstractSuperBeaconBlockEntity>
/*     */   implements BlockEntityRenderer<T>
/*     */ {
/*  24 */   protected static final Identifier BEAM = Identifier.fromNamespaceAndPath("witherstormmod", "textures/misc/beam.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSuperBeaconRenderer(BlockEntityRendererProvider.Context context) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(T entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
/*  44 */     stack.pushPose();
/*     */     
/*  46 */     VertexConsumer consumer = buffer.getBuffer(getRenderType(entity));
/*  47 */     float scale = getCrsytalScale(entity, partialTicks);
/*  48 */     stack.translate(0.5D, 0.5D, 0.5D);
/*  49 */     transformCrystal(entity, partialTicks, stack);
/*  50 */     float[] uvs = getUVS();
/*  51 */     int light = getLightTexture(entity, packedLight);
/*     */     
/*  53 */     renderOrb(stack, consumer, scale, uvs, light);
/*     */     
/*  55 */     stack.popPose();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderOrb(PoseStack stack, VertexConsumer consumer, float scale, int packedLight) {
/*  60 */     renderOrb(stack, consumer, scale, new float[] { 0.0F, 0.0F, 1.0F, 1.0F }, packedLight);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderOrb(PoseStack stack, VertexConsumer consumer, float scale, float[] uvs, int packedLight) {
/*  65 */     stack.pushPose();
/*  66 */     stack.mulPose(Axis.XP.rotationDegrees(45.0F));
/*  67 */     drawBox(consumer, stack, OverlayTexture.NO_OVERLAY, packedLight, 1.0F, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0F);
/*  68 */     stack.popPose();
/*     */     
/*  70 */     stack.pushPose();
/*  71 */     stack.mulPose(Axis.YP.rotationDegrees(45.0F));
/*  72 */     drawBox(consumer, stack, OverlayTexture.NO_OVERLAY, packedLight, 1.0F, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0F);
/*  73 */     stack.popPose();
/*     */     
/*  75 */     stack.pushPose();
/*  76 */     stack.mulPose(Axis.ZP.rotationDegrees(45.0F));
/*  77 */     drawBox(consumer, stack, OverlayTexture.NO_OVERLAY, packedLight, 1.0F, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0F);
/*  78 */     stack.popPose();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderBeam(boolean active, PoseStack stack, int[] color, MultiBufferSource buffer, float partialTicks, long gameTime, int beamHeight, float beamThickness, float outerThickness) {
/*  83 */     if (active) {
/*     */       
/*  85 */       stack.pushPose();
/*  86 */       stack.translate(0.0D, 0.5D, 0.0D);
/*  87 */       float r = color[0] / 255.0F;
/*  88 */       float g = color[1] / 255.0F;
/*  89 */       float b = color[2] / 255.0F;
/*  90 */       BeaconRenderer.renderBeaconBeam(stack, buffer, BeaconRenderer.BEAM_LOCATION, partialTicks, 1.0F, gameTime, 0, beamHeight, new float[] { r, g, b, 1.0F }, beamThickness, outerThickness);
/*  91 */       stack.popPose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderConnectBeam(VertexConsumer consumer, PoseStack stack, Camera camera, float pulse, Vec3 start, Vec3 end, float r, float g, float b, float a) {
/*  97 */     stack.pushPose();
/*  98 */     double dX = start.x - end.x;
/*  99 */     double dY = start.y - end.y;
/* 100 */     double dZ = start.z - end.z;
/* 101 */     float yaw = (float)Math.atan2(dZ, dX) - 1.5707964F;
/* 102 */     float pitch = (float)Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + 3.1415927F;
/* 103 */     stack.mulPose(Axis.YN.rotation(yaw));
/* 104 */     stack.mulPose(Axis.XP.rotation(pitch));
/* 105 */     Matrix4f pose = stack.last().pose();
/* 106 */     consumer.vertex(pose, -pulse, 0.0F, 0.0F).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 107 */     consumer.vertex(pose, pulse, 0.0F, 0.0F).color(r, g, b, a).uv(1.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 108 */     consumer.vertex(pose, pulse, (float)start.distanceTo(end), 0.0F).color(r, g, b, a).uv(1.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 109 */     consumer.vertex(pose, -pulse, (float)start.distanceTo(end), 0.0F).color(r, g, b, a).uv(0.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */     
/* 111 */     consumer.vertex(pose, 0.0F, 0.0F, -pulse).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 112 */     consumer.vertex(pose, 0.0F, 0.0F, pulse).color(r, g, b, a).uv(1.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 113 */     consumer.vertex(pose, 0.0F, (float)start.distanceTo(end), pulse).color(r, g, b, a).uv(1.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 114 */     consumer.vertex(pose, 0.0F, (float)start.distanceTo(end), -pulse).color(r, g, b, a).uv(0.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 115 */     stack.popPose();
/*     */   }
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
/*     */   protected int getLightTexture(T entity, int packedLight) {
/* 128 */     return entity.isActive() ? packedLight : 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getViewDistance() {
/* 134 */     return 256;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender(T entity, Vec3 pos) {
/* 140 */     return Vec3.atCenterOf((Vec3i)entity.getBlockPos()).multiply(1.0D, 0.0D, 1.0D).closerThan((Position)pos.multiply(1.0D, 0.0D, 1.0D), getViewDistance());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRenderOffScreen(T entity) {
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void drawBox(VertexConsumer consumer, PoseStack stack, int overlayTexture, int packedLight, float alpha, float uMin, float vMin, float uMax, float vMax, float scale) {
/* 151 */     Matrix4f pose = stack.last().pose();
/*     */     
/* 153 */     consumer.vertex(pose, -scale, -scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 154 */     consumer.vertex(pose, -scale, scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 155 */     consumer.vertex(pose, scale, scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 156 */     consumer.vertex(pose, scale, -scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */     
/* 158 */     consumer.vertex(pose, -scale, -scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 159 */     consumer.vertex(pose, -scale, scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 160 */     consumer.vertex(pose, scale, scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 161 */     consumer.vertex(pose, scale, -scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */     
/* 163 */     consumer.vertex(pose, -scale, -scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 164 */     consumer.vertex(pose, -scale, -scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 165 */     consumer.vertex(pose, scale, -scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 166 */     consumer.vertex(pose, scale, -scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */     
/* 168 */     consumer.vertex(pose, -scale, scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 169 */     consumer.vertex(pose, -scale, scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 170 */     consumer.vertex(pose, scale, scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 171 */     consumer.vertex(pose, scale, scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */     
/* 173 */     consumer.vertex(pose, -scale, -scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 174 */     consumer.vertex(pose, -scale, -scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 175 */     consumer.vertex(pose, -scale, scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 176 */     consumer.vertex(pose, -scale, scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */     
/* 178 */     consumer.vertex(pose, scale, -scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 179 */     consumer.vertex(pose, scale, -scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMin, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 180 */     consumer.vertex(pose, scale, scale, scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMax).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/* 181 */     consumer.vertex(pose, scale, scale, -scale).color(1.0F, 1.0F, 1.0F, alpha).uv(uMax, vMin).overlayCoords(overlayTexture).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */   }
/*     */   
/*     */   protected abstract float[] getUVS();
/*     */   
/*     */   protected abstract RenderType getRenderType(T paramT);
/*     */   
/*     */   protected abstract float getCrsytalScale(T paramT, float paramFloat);
/*     */   
/*     */   protected abstract void transformCrystal(T paramT, float paramFloat, PoseStack paramPoseStack);
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\blockentity\AbstractSuperBeaconRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */