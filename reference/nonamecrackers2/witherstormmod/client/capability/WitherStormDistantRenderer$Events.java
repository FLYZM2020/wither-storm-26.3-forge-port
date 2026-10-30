/*     */ package nonamecrackers2.witherstormmod.client.capability;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import net.minecraft.client.Camera;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.GameRenderer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.culling.Frustum;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.client.event.RenderLevelStageEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinGameRenderer;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Matrix4fc;
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
/*     */ public class Events
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void renderTickDistantRenderer(RenderLevelStageEvent event) {
/* 291 */     if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
/* 292 */       render(event.getPoseStack(), event.getPartialTick());
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void clientTickDistantRenderer(TickEvent.ClientTickEvent event) {
/* 298 */     Minecraft mc = Minecraft.m_91087_();
/* 299 */     if (event.phase == TickEvent.Phase.START) {
/*     */       
/* 301 */       ClientLevel world = mc.f_91073_;
/* 302 */       if (world != null)
/*     */       {
/* 304 */         world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
/*     */               if ((!mc.m_91104_() || !mc.m_91091_()) && ((Boolean)WitherStormModConfig.CLIENT.distantRenderer.get()).booleanValue()) {
/*     */                 distantRenderer.tick();
/*     */               }
/*     */             });
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void render(PoseStack stack, float partialTicks) {
/* 317 */     if (((Boolean)WitherStormModConfig.CLIENT.distantRenderer.get()).booleanValue()) {
/*     */       
/* 319 */       Minecraft mc = Minecraft.m_91087_();
/* 320 */       ClientLevel world = mc.f_91073_;
/* 321 */       world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
/*     */             Matrix4f originalMatrix = RenderSystem.getProjectionMatrix();
/*     */             GameRenderer renderer = mc.f_91063_;
/*     */             Camera renderInfo = renderer.m_109153_();
/*     */             Matrix4f projection = null;
/*     */             if (!CompatHelper.isVrActive()) {
/*     */               double fov = ((IMixinGameRenderer)renderer).callGetFov(renderInfo, partialTicks, true);
/*     */               projection = (new Matrix4f()).perspective((float)(fov * 0.01745329238474369D), mc.m_91268_().m_85441_() / mc.m_91268_().m_85442_(), 0.05F, renderer.m_109152_() * 180.0F);
/*     */               Matrix4f defaultProjection = renderer.m_253088_(fov);
/*     */               Matrix4f invertedDefaultProjection = new Matrix4f((Matrix4fc)defaultProjection);
/*     */               invertedDefaultProjection.invert();
/*     */               Matrix4f distortionMatrix = new Matrix4f((Matrix4fc)invertedDefaultProjection);
/*     */               distortionMatrix.mul((Matrix4fc)originalMatrix);
/*     */               projection.mul((Matrix4fc)distortionMatrix);
/*     */             } else {
/*     */               projection = new Matrix4f((Matrix4fc)originalMatrix);
/*     */               WitherStormDistantRenderer.setClipPlanes(projection, 0.05F, renderer.m_109152_() * 180.0F);
/*     */             } 
/*     */             renderer.m_252879_(projection);
/*     */             Vec3 pos = renderInfo.m_90583_();
/*     */             MultiBufferSource.BufferSource buffer = mc.m_91269_().m_110104_();
/*     */             Frustum clippinghelper = new Frustum(stack.m_85850_().m_252922_(), projection);
/*     */             clippinghelper.m_113002_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_());
/*     */             distantRenderer.renderTick(stack, buffer, partialTicks, clippinghelper);
/*     */             buffer.m_109911_();
/*     */             renderer.m_252879_(originalMatrix);
/*     */           });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\WitherStormDistantRenderer$Events.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */