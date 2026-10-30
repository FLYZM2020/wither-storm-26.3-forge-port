/*     */ package nonamecrackers2.witherstormmod.client.renderer.blockentity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import net.minecraft.client.Camera;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.blockentity.BeaconRenderer;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.core.Position;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ public abstract class AbstractSuperBeaconRenderer<T extends AbstractSuperBeaconBlockEntity>
/*     */   implements BlockEntityRenderer<T>
/*     */ {
/*  24 */   protected static final ResourceLocation BEAM = new ResourceLocation("witherstormmod", "textures/misc/beam.png");
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
/*  44 */     stack.m_85836_();
/*     */     
/*  46 */     VertexConsumer consumer = buffer.m_6299_(getRenderType(entity));
/*  47 */     float scale = getCrsytalScale(entity, partialTicks);
/*  48 */     stack.m_85837_(0.5D, 0.5D, 0.5D);
/*  49 */     transformCrystal(entity, partialTicks, stack);
/*  50 */     float[] uvs = getUVS();
/*  51 */     int light = getLightTexture(entity, packedLight);
/*     */     
/*  53 */     renderOrb(stack, consumer, scale, uvs, light);
/*     */     
/*  55 */     stack.m_85849_();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderOrb(PoseStack stack, VertexConsumer consumer, float scale, int packedLight) {
/*  60 */     renderOrb(stack, consumer, scale, new float[] { 0.0F, 0.0F, 1.0F, 1.0F }, packedLight);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderOrb(PoseStack stack, VertexConsumer consumer, float scale, float[] uvs, int packedLight) {
/*  65 */     stack.m_85836_();
/*  66 */     stack.m_252781_(Axis.f_252529_.m_252977_(45.0F));
/*  67 */     drawBox(consumer, stack, OverlayTexture.f_118083_, packedLight, 1.0F, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0F);
/*  68 */     stack.m_85849_();
/*     */     
/*  70 */     stack.m_85836_();
/*  71 */     stack.m_252781_(Axis.f_252436_.m_252977_(45.0F));
/*  72 */     drawBox(consumer, stack, OverlayTexture.f_118083_, packedLight, 1.0F, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0F);
/*  73 */     stack.m_85849_();
/*     */     
/*  75 */     stack.m_85836_();
/*  76 */     stack.m_252781_(Axis.f_252403_.m_252977_(45.0F));
/*  77 */     drawBox(consumer, stack, OverlayTexture.f_118083_, packedLight, 1.0F, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0F);
/*  78 */     stack.m_85849_();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderBeam(boolean active, PoseStack stack, int[] color, MultiBufferSource buffer, float partialTicks, long gameTime, int beamHeight, float beamThickness, float outerThickness) {
/*  83 */     if (active) {
/*     */       
/*  85 */       stack.m_85836_();
/*  86 */       stack.m_85837_(0.0D, 0.5D, 0.0D);
/*  87 */       float r = color[0] / 255.0F;
/*  88 */       float g = color[1] / 255.0F;
/*  89 */       float b = color[2] / 255.0F;
/*  90 */       BeaconRenderer.m_112184_(stack, buffer, BeaconRenderer.f_112102_, partialTicks, 1.0F, gameTime, 0, beamHeight, new float[] { r, g, b, 1.0F }, beamThickness, outerThickness);
/*  91 */       stack.m_85849_();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderConnectBeam(VertexConsumer consumer, PoseStack stack, Camera camera, float pulse, Vec3 start, Vec3 end, float r, float g, float b, float a) {
/*  97 */     stack.m_85836_();
/*  98 */     double dX = start.f_82479_ - end.f_82479_;
/*  99 */     double dY = start.f_82480_ - end.f_82480_;
/* 100 */     double dZ = start.f_82481_ - end.f_82481_;
/* 101 */     float yaw = (float)Math.atan2(dZ, dX) - 1.5707964F;
/* 102 */     float pitch = (float)Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + 3.1415927F;
/* 103 */     stack.m_252781_(Axis.f_252392_.m_252961_(yaw));
/* 104 */     stack.m_252781_(Axis.f_252529_.m_252961_(pitch));
/* 105 */     Matrix4f pose = stack.m_85850_().m_252922_();
/* 106 */     consumer.m_252986_(pose, -pulse, 0.0F, 0.0F).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 107 */     consumer.m_252986_(pose, pulse, 0.0F, 0.0F).m_85950_(r, g, b, a).m_7421_(1.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 108 */     consumer.m_252986_(pose, pulse, (float)start.m_82554_(end), 0.0F).m_85950_(r, g, b, a).m_7421_(1.0F, 1.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 109 */     consumer.m_252986_(pose, -pulse, (float)start.m_82554_(end), 0.0F).m_85950_(r, g, b, a).m_7421_(0.0F, 1.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*     */     
/* 111 */     consumer.m_252986_(pose, 0.0F, 0.0F, -pulse).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 112 */     consumer.m_252986_(pose, 0.0F, 0.0F, pulse).m_85950_(r, g, b, a).m_7421_(1.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 113 */     consumer.m_252986_(pose, 0.0F, (float)start.m_82554_(end), pulse).m_85950_(r, g, b, a).m_7421_(1.0F, 1.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 114 */     consumer.m_252986_(pose, 0.0F, (float)start.m_82554_(end), -pulse).m_85950_(r, g, b, a).m_7421_(0.0F, 1.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 115 */     stack.m_85849_();
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
/*     */   public int m_142163_() {
/* 134 */     return 256;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender(T entity, Vec3 pos) {
/* 140 */     return Vec3.m_82512_((Vec3i)entity.m_58899_()).m_82542_(1.0D, 0.0D, 1.0D).m_82509_((Position)pos.m_82542_(1.0D, 0.0D, 1.0D), m_142163_());
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
/* 151 */     Matrix4f pose = stack.m_85850_().m_252922_();
/*     */     
/* 153 */     consumer.m_252986_(pose, -scale, -scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 154 */     consumer.m_252986_(pose, -scale, scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 155 */     consumer.m_252986_(pose, scale, scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 156 */     consumer.m_252986_(pose, scale, -scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*     */     
/* 158 */     consumer.m_252986_(pose, -scale, -scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 159 */     consumer.m_252986_(pose, -scale, scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 160 */     consumer.m_252986_(pose, scale, scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 161 */     consumer.m_252986_(pose, scale, -scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*     */     
/* 163 */     consumer.m_252986_(pose, -scale, -scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 164 */     consumer.m_252986_(pose, -scale, -scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 165 */     consumer.m_252986_(pose, scale, -scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 166 */     consumer.m_252986_(pose, scale, -scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*     */     
/* 168 */     consumer.m_252986_(pose, -scale, scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 169 */     consumer.m_252986_(pose, -scale, scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 170 */     consumer.m_252986_(pose, scale, scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 171 */     consumer.m_252986_(pose, scale, scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*     */     
/* 173 */     consumer.m_252986_(pose, -scale, -scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 174 */     consumer.m_252986_(pose, -scale, -scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 175 */     consumer.m_252986_(pose, -scale, scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 176 */     consumer.m_252986_(pose, -scale, scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*     */     
/* 178 */     consumer.m_252986_(pose, scale, -scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 179 */     consumer.m_252986_(pose, scale, -scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 180 */     consumer.m_252986_(pose, scale, scale, scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/* 181 */     consumer.m_252986_(pose, scale, scale, -scale).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
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