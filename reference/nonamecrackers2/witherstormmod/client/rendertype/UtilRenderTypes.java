/*     */ package nonamecrackers2.witherstormmod.client.rendertype;
/*     */ 
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.DefaultVertexFormat;
/*     */ import com.mojang.blaze3d.vertex.VertexFormat;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.Util;
/*     */ import net.minecraft.client.renderer.GameRenderer;
/*     */ import net.minecraft.client.renderer.RenderStateShard;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModShaders;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UtilRenderTypes
/*     */ {
/*  19 */   private static final RenderStateShard.ShaderStateShard ENTITY_DECAL_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::m_172688_);
/*  20 */   private static final RenderStateShard.ShaderStateShard EYES_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::m_172700_);
/*  21 */   private static final RenderStateShard.TransparencyStateShard TRANSLUCENT_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
/*     */         RenderSystem.enableBlend();
/*     */         RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */       }() -> {
/*     */         RenderSystem.disableBlend();
/*     */         RenderSystem.defaultBlendFunc();
/*     */       });
/*  28 */   private static final RenderStateShard.TransparencyStateShard ADDITIVE_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("additive_transparency", () -> {
/*     */         RenderSystem.enableBlend();
/*     */         RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
/*     */       }() -> {
/*     */         RenderSystem.disableBlend();
/*     */         RenderSystem.defaultBlendFunc();
/*     */       });
/*  35 */   protected static final RenderStateShard.TransparencyStateShard NO_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("no_transparency", () -> RenderSystem.disableBlend(), () -> {
/*     */       
/*     */       });
/*     */   
/*  39 */   private static final RenderStateShard.CullStateShard NO_CULL = new RenderStateShard.CullStateShard(false);
/*  40 */   private static final RenderStateShard.CullStateShard CULL = new RenderStateShard.CullStateShard(false);
/*  41 */   private static final RenderStateShard.LightmapStateShard LIGHTMAP = new RenderStateShard.LightmapStateShard(true);
/*  42 */   private static final RenderStateShard.OverlayStateShard OVERLAY = new RenderStateShard.OverlayStateShard(true);
/*  43 */   private static final RenderStateShard.DepthTestStateShard EQUAL_DEPTH_TEST = new RenderStateShard.DepthTestStateShard("==", 514);
/*  44 */   private static final RenderStateShard.WriteMaskStateShard COLOR_WRITE = new RenderStateShard.WriteMaskStateShard(true, false);
/*  45 */   private static final RenderStateShard.ShaderStateShard WITHER_STORM_SHADER = new RenderStateShard.ShaderStateShard(WitherStormModShaders::getWitherStormShader);
/*  46 */   private static final RenderStateShard.ShaderStateShard WITHER_STORM_DISSOLVE_SHADER = new RenderStateShard.ShaderStateShard(WitherStormModShaders::getWitherStormDissolveShader); private static final Function<ResourceLocation, RenderType> ENTITY_DECAL_TRANSLUCENT; private static final Function<ResourceLocation, RenderType> EMISSIVE_NO_CULL; private static final Function<ResourceLocation, RenderType> EMISSIVE_TRANSLUCENT;
/*     */   
/*     */   static {
/*  49 */     ENTITY_DECAL_TRANSLUCENT = Util.m_143827_(loc -> {
/*     */           RenderType.CompositeState state = RenderType.CompositeState.m_110628_().m_173292_(ENTITY_DECAL_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(loc, false, false)).m_110663_(EQUAL_DEPTH_TEST).m_110685_(TRANSLUCENT_TRANSPARENCY).m_110661_(NO_CULL).m_110671_(LIGHTMAP).m_110677_(OVERLAY).m_110691_(false);
/*     */           
/*     */           return (RenderType)RenderType.m_173215_("entity_decal_translucent", DefaultVertexFormat.f_85812_, VertexFormat.Mode.QUADS, 256, false, false, state);
/*     */         });
/*  54 */     EMISSIVE_NO_CULL = Util.m_143827_(tex -> {
/*     */           RenderStateShard.TextureStateShard state = new RenderStateShard.TextureStateShard(tex, false, false);
/*     */           
/*     */           return (RenderType)RenderType.m_173215_("emissive_no_cull", DefaultVertexFormat.f_85812_, VertexFormat.Mode.QUADS, 256, false, true, RenderType.CompositeState.m_110628_().m_173292_(EYES_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)state).m_110685_(ADDITIVE_TRANSPARENCY).m_110661_(NO_CULL).m_110687_(COLOR_WRITE).m_110691_(false));
/*     */         });
/*  59 */     EMISSIVE_TRANSLUCENT = Util.m_143827_(tex -> {
/*     */           RenderStateShard.TextureStateShard state = new RenderStateShard.TextureStateShard(tex, false, false);
/*     */           
/*     */           return (RenderType)RenderType.m_173215_("emissive_translucent", DefaultVertexFormat.f_85812_, VertexFormat.Mode.QUADS, 256, false, true, RenderType.CompositeState.m_110628_().m_173292_(EYES_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)state).m_110685_(TRANSLUCENT_TRANSPARENCY).m_110661_(CULL).m_110687_(COLOR_WRITE).m_110691_(false));
/*     */         });
/*  64 */     WITHER_STORM = Util.m_143827_(p_269670_ -> {
/*     */           RenderType.CompositeState compositeState = RenderType.CompositeState.m_110628_().m_173292_(WITHER_STORM_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(p_269670_, false, false)).m_110685_(NO_TRANSPARENCY).m_110661_(NO_CULL).m_110671_(LIGHTMAP).m_110677_(OVERLAY).m_110691_(true);
/*     */ 
/*     */ 
/*     */           
/*     */           return (RenderType)RenderType.m_173215_("wither_storm", DefaultVertexFormat.f_85812_, VertexFormat.Mode.QUADS, 256, true, false, compositeState);
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  74 */     WITHER_STORM_DISSOLVE = Util.m_143827_(tex -> {
/*     */           RenderType.CompositeState compositeState = RenderType.CompositeState.m_110628_().m_173292_(WITHER_STORM_DISSOLVE_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(tex, false, false)).m_110691_(true);
/*     */           
/*     */           return (RenderType)RenderType.m_173215_("wither_storm_dissolve", DefaultVertexFormat.f_85812_, VertexFormat.Mode.QUADS, 256, false, false, compositeState);
/*     */         });
/*  79 */     WITHER_STORM_DECAL = Util.m_143827_(tex -> {
/*     */           RenderType.CompositeState compositeState = RenderType.CompositeState.m_110628_().m_173292_(WITHER_STORM_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(tex, false, false)).m_110663_(EQUAL_DEPTH_TEST).m_110671_(LIGHTMAP).m_110677_(OVERLAY).m_110691_(false);
/*     */           return (RenderType)RenderType.m_173215_("entity_decal", DefaultVertexFormat.f_85812_, VertexFormat.Mode.QUADS, 256, false, false, compositeState);
/*     */         });
/*     */   }
/*     */   private static final Function<ResourceLocation, RenderType> WITHER_STORM; private static final Function<ResourceLocation, RenderType> WITHER_STORM_DISSOLVE; private static final Function<ResourceLocation, RenderType> WITHER_STORM_DECAL;
/*     */   
/*     */   public static RenderType entityDecalTranslucent(ResourceLocation tex) {
/*  87 */     return ENTITY_DECAL_TRANSLUCENT.apply(tex);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType emissiveNoCull(ResourceLocation tex) {
/*  92 */     return EMISSIVE_NO_CULL.apply(tex);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType emissiveTranslucent(ResourceLocation tex) {
/*  97 */     return EMISSIVE_TRANSLUCENT.apply(tex);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType witherStorm(ResourceLocation tex) {
/* 102 */     return WITHER_STORM.apply(tex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RenderType witherStormDissolve(ResourceLocation tex) {
/* 112 */     return WITHER_STORM_DISSOLVE.apply(tex);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType witherStormDecal(ResourceLocation tex) {
/* 117 */     return WITHER_STORM_DECAL.apply(tex);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\rendertype\UtilRenderTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */