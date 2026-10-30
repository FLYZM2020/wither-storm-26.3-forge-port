/*     */ package nonamecrackers2.witherstormmod.client.instancing;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.blaze3d.shaders.FogShape;
/*     */ import com.mojang.blaze3d.shaders.Uniform;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinLightTexture;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinOverlayTexture;
/*     */ import nonamecrackers2.witherstormmod.mixin.MixinRenderSystemAccessor;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.joml.Matrix3fc;
/*     */ import org.joml.Vector3f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class RenderBufferer
/*     */ {
/*  37 */   public static final RenderBufferer INSTANCE = new RenderBufferer(Minecraft.m_91087_());
/*  38 */   private static final Logger LOGGER = LogManager.getLogger("witherstormmod/RenderBufferer");
/*     */   private final Minecraft mc;
/*  40 */   private final Map<Object, BufferedInstance> instances = Maps.newHashMap();
/*  41 */   private final ExecutorService asyncBufferBuilderPool = new ThreadPoolExecutor(0, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
/*     */   
/*     */   private boolean noFog;
/*     */   private boolean tempDisabled;
/*     */   private boolean useAsyncBuilder;
/*     */   private boolean flipFaces;
/*     */   private boolean cullFaces;
/*     */   
/*     */   private RenderBufferer(Minecraft mc) {
/*  50 */     this.mc = mc;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTotalInstances() {
/*  55 */     return this.instances.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTick() {
/*  74 */     Iterator<BufferedInstance> iterator = this.instances.values().iterator();
/*  75 */     while (iterator.hasNext()) {
/*     */       
/*  77 */       BufferedInstance instance = iterator.next();
/*  78 */       if (instance.getBuffer() != null && (!instance.hasRemoveSupplier() || instance.shouldRemove()) && !instance.wasRenderedLastFrame()) {
/*     */         
/*  80 */         instance.close();
/*  81 */         iterator.remove();
/*     */       } 
/*  83 */       instance.setRenderedLastFrame(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void render(Object key, RenderType type, @Nullable Supplier<Boolean> shouldRemove, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a, boolean renderBuffered) {
/*  89 */     if (renderBuffered && !this.tempDisabled) {
/*     */       
/*  91 */       BufferedInstance instance = this.instances.computeIfAbsent(key, e -> this.useAsyncBuilder ? new AsyncBufferedInstance(type, bufferer, shouldRemove) : new BufferedInstance(type, bufferer, shouldRemove));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       if (instance != null)
/*     */       {
/* 100 */         if (instance.requiresComputing()) {
/*     */           
/* 102 */           PoseStack s = new PoseStack();
/* 103 */           s.m_85841_(-1.0F, -1.0F, 1.0F);
/* 104 */           instance.buildBuffer(s, this.asyncBufferBuilderPool);
/*     */         } 
/* 106 */         if (instance instanceof AsyncBufferedInstance) { AsyncBufferedInstance async = (AsyncBufferedInstance)instance;
/* 107 */           async.checkBufferBuilderStatus(); }
/* 108 */          instance.setBufferer(bufferer);
/* 109 */         VertexBuffer buffer = instance.getBuffer();
/* 110 */         if (buffer != null)
/*     */         {
/* 112 */           buffer.m_85921_();
/*     */           
/* 114 */           stack.m_85836_();
/* 115 */           stack.m_85841_(-1.0F, -1.0F, 1.0F);
/* 116 */           RenderType renderType = instance.getRenderType();
/* 117 */           renderType.m_110185_();
/*     */           
/* 119 */           applyOverlays(packedLight, overlayTexture, r, g, b, a);
/* 120 */           float prevStart = RenderSystem.getShaderFogStart();
/* 121 */           if (this.noFog)
/* 122 */             RenderSystem.setShaderFogStart(Float.MAX_VALUE); 
/* 123 */           FogShape prev = RenderSystem.getShaderFogShape();
/* 124 */           RenderSystem.setShaderFogShape(FogShape.SPHERE);
/*     */           
/* 126 */           if (this.cullFaces)
/* 127 */             RenderSystem.enableCull(); 
/* 128 */           if (this.flipFaces) {
/* 129 */             GL11.glFrontFace(2304);
/*     */           }
/* 131 */           Vector3f[] shaderLights = MixinRenderSystemAccessor.witherstormmod$getShaderLightDirections();
/* 132 */           Vector3f[] storedOriginalShaderLights = Arrays.<Vector3f>copyOf(shaderLights, shaderLights.length); int i;
/* 133 */           for (i = 0; i < shaderLights.length; i++) {
/* 134 */             shaderLights[i] = shaderLights[i].mul((Matrix3fc)RenderSystem.getInverseViewRotationMatrix(), new Vector3f());
/*     */           }
/* 136 */           buffer.m_253207_(stack.m_85850_().m_252922_(), RenderSystem.getProjectionMatrix(), RenderSystem.getShader());
/* 137 */           instance.setRenderedLastFrame(true);
/*     */           
/* 139 */           for (i = 0; i < shaderLights.length; i++) {
/* 140 */             shaderLights[i] = storedOriginalShaderLights[i];
/*     */           }
/* 142 */           if (this.cullFaces)
/* 143 */             RenderSystem.disableCull(); 
/* 144 */           if (this.flipFaces) {
/* 145 */             GL11.glFrontFace(2305);
/*     */           }
/* 147 */           resetOverlays();
/* 148 */           RenderSystem.setShaderFogShape(prev);
/* 149 */           if (this.noFog) {
/* 150 */             RenderSystem.setShaderFogStart(prevStart);
/*     */           }
/* 152 */           renderType.m_110188_();
/* 153 */           RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 154 */           stack.m_85849_();
/*     */           
/* 156 */           VertexBuffer.m_85931_();
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 165 */       bufferer.bufferInto(stack, Minecraft.m_91087_().m_91269_().m_110104_().m_6299_(type), packedLight, overlayTexture, r, g, b, a);
/*     */     } 
/* 167 */     this.noFog = false;
/* 168 */     this.tempDisabled = false;
/* 169 */     this.useAsyncBuilder = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void applyOverlays(int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 174 */     IMixinLightTexture lightTexture = (IMixinLightTexture)this.mc.f_91063_.m_109154_();
/* 175 */     IMixinOverlayTexture overlay = (IMixinOverlayTexture)this.mc.f_91063_.m_109155_();
/* 176 */     float[] packedLightCol = getRGB(lightTexture.getLightTexture(), (packedLight & 0xFFFF) / 16, (packedLight >> 16 & 0xFFFF) / 16);
/* 177 */     float[] overlayCol = getRGB(overlay.getTexture(), overlayTexture & 0xFFFF, overlayTexture >> 16 & 0xFFFF);
/* 178 */     Uniform uniform = RenderSystem.getShader().m_173348_("OverlayTextureColor");
/* 179 */     if (uniform != null)
/* 180 */       uniform.m_5805_(overlayCol[0], overlayCol[1], overlayCol[2], overlayCol[3]); 
/* 181 */     RenderSystem.setShaderColor(r * packedLightCol[0], g * packedLightCol[1], b * packedLightCol[2], a * packedLightCol[3]);
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetOverlays() {
/* 186 */     Uniform uniform = RenderSystem.getShader().m_173348_("OverlayTextureColor");
/* 187 */     if (uniform != null) {
/* 188 */       uniform.m_5805_(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private static float[] getRGB(DynamicTexture texture, int u, int v) {
/* 193 */     int color = texture.m_117991_().m_84985_(u, v);
/* 194 */     float r = (color >> 0 & 0xFF) / 255.0F;
/* 195 */     float g = (color >> 8 & 0xFF) / 255.0F;
/* 196 */     float b = (color >> 16 & 0xFF) / 255.0F;
/* 197 */     float a = (color >> 24 & 0xFF) / 255.0F;
/* 198 */     return new float[] { r, g, b, a };
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean shouldUse() {
/* 203 */     return (((Boolean)WitherStormModConfig.CLIENT.vertexBufferRendering.get()).booleanValue() && (!CompatHelper.isOptifineLoaded() || !CompatHelper.areShadersRunning()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void buildAndOrRender(Object key, RenderType type, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a, boolean renderBuffered) {
/* 208 */     INSTANCE.render(key, type, null, bufferer, stack, packedLight, overlayTexture, r, g, b, a, renderBuffered);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void buildAndOrRender(Object key, RenderType type, Supplier<Boolean> shouldRemove, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a, boolean renderBuffered) {
/* 213 */     INSTANCE.render(key, type, shouldRemove, bufferer, stack, packedLight, overlayTexture, r, g, b, a, renderBuffered);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void buildAndOrRender(Object key, RenderType type, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 218 */     INSTANCE.render(key, type, null, bufferer, stack, packedLight, overlayTexture, r, g, b, a, shouldUse());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void buildAndOrRender(Object key, RenderType type, Supplier<Boolean> shouldRemove, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 223 */     INSTANCE.render(key, type, shouldRemove, bufferer, stack, packedLight, overlayTexture, r, g, b, a, shouldUse());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void pushNoFog() {
/* 228 */     INSTANCE.noFog = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void pushTempDisabled() {
/* 233 */     INSTANCE.tempDisabled = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void pushUseAsyncBuilder() {
/* 238 */     if (((Boolean)WitherStormModConfig.CLIENT.asyncBufferBuilders.get()).booleanValue()) {
/* 239 */       INSTANCE.useAsyncBuilder = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void pushFlipFaces() {
/* 244 */     INSTANCE.flipFaces = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void popFlipFaces() {
/* 249 */     INSTANCE.flipFaces = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void pushCullFaces() {
/* 254 */     INSTANCE.cullFaces = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void popCullFaces() {
/* 259 */     INSTANCE.cullFaces = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void purgeInstances() {
/* 264 */     LOGGER.debug("Clearing {} instances", Integer.valueOf(this.instances.size()));
/* 265 */     Iterator<Map.Entry<Object, BufferedInstance>> iterator = this.instances.entrySet().iterator();
/* 266 */     while (iterator.hasNext()) {
/*     */       
/* 268 */       Map.Entry<Object, BufferedInstance> instance = iterator.next();
/* 269 */       ((BufferedInstance)instance.getValue()).close();
/* 270 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void levelReload() {
/* 276 */     LOGGER.debug("Refreshing...");
/* 277 */     purgeInstances();
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown() {
/* 282 */     LOGGER.debug("Shutting down...");
/* 283 */     purgeInstances();
/* 284 */     this.asyncBufferBuilderPool.shutdown();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Events
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 292 */       Minecraft mc = Minecraft.m_91087_();
/* 293 */       if (event.phase == TickEvent.Phase.START && !mc.m_91104_() && mc.f_91073_ != null) {
/* 294 */         RenderBufferer.INSTANCE.tick();
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onRender(TickEvent.RenderTickEvent event) {
/* 300 */       if (event.phase == TickEvent.Phase.START)
/* 301 */         RenderBufferer.INSTANCE.renderTick(); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\instancing\RenderBufferer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */