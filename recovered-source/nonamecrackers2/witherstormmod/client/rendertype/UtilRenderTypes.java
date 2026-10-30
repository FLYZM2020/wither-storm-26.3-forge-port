package nonamecrackers2.witherstormmod.client.rendertype;

import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import java.util.function.Function;
import net.minecraft.Util;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderStateShard.CullStateShard;
import net.minecraft.client.renderer.RenderStateShard.DepthTestStateShard;
import net.minecraft.client.renderer.RenderStateShard.LightmapStateShard;
import net.minecraft.client.renderer.RenderStateShard.OverlayStateShard;
import net.minecraft.client.renderer.RenderStateShard.ShaderStateShard;
import net.minecraft.client.renderer.RenderStateShard.TextureStateShard;
import net.minecraft.client.renderer.RenderStateShard.TransparencyStateShard;
import net.minecraft.client.renderer.RenderStateShard.WriteMaskStateShard;
import net.minecraft.client.renderer.RenderType.CompositeState;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.init.WitherStormModShaders;

public class UtilRenderTypes {
   private static final ShaderStateShard ENTITY_DECAL_SHADER = new ShaderStateShard(GameRenderer::m_172688_);
   private static final ShaderStateShard EYES_SHADER = new ShaderStateShard(GameRenderer::m_172700_);
   private static final TransparencyStateShard TRANSLUCENT_TRANSPARENCY = new TransparencyStateShard("translucent_transparency", () -> {
      RenderSystem.enableBlend();
      RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA);
   }, () -> {
      RenderSystem.disableBlend();
      RenderSystem.defaultBlendFunc();
   });
   private static final TransparencyStateShard ADDITIVE_TRANSPARENCY = new TransparencyStateShard("additive_transparency", () -> {
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(SourceFactor.ONE, DestFactor.ONE);
   }, () -> {
      RenderSystem.disableBlend();
      RenderSystem.defaultBlendFunc();
   });
   protected static final TransparencyStateShard NO_TRANSPARENCY = new TransparencyStateShard("no_transparency", () -> RenderSystem.disableBlend(), () -> {});
   private static final CullStateShard NO_CULL = new CullStateShard(false);
   private static final CullStateShard CULL = new CullStateShard(false);
   private static final LightmapStateShard LIGHTMAP = new LightmapStateShard(true);
   private static final OverlayStateShard OVERLAY = new OverlayStateShard(true);
   private static final DepthTestStateShard EQUAL_DEPTH_TEST = new DepthTestStateShard("==", 514);
   private static final WriteMaskStateShard COLOR_WRITE = new WriteMaskStateShard(true, false);
   private static final ShaderStateShard WITHER_STORM_SHADER = new ShaderStateShard(WitherStormModShaders::getWitherStormShader);
   private static final ShaderStateShard WITHER_STORM_DISSOLVE_SHADER = new ShaderStateShard(WitherStormModShaders::getWitherStormDissolveShader);
   private static final Function<ResourceLocation, RenderType> ENTITY_DECAL_TRANSLUCENT = Util.m_143827_(
      loc -> {
         CompositeState state = CompositeState.m_110628_()
            .m_173292_(ENTITY_DECAL_SHADER)
            .m_173290_(new TextureStateShard(loc, false, false))
            .m_110663_(EQUAL_DEPTH_TEST)
            .m_110685_(TRANSLUCENT_TRANSPARENCY)
            .m_110661_(NO_CULL)
            .m_110671_(LIGHTMAP)
            .m_110677_(OVERLAY)
            .m_110691_(false);
         return RenderType.m_173215_("entity_decal_translucent", DefaultVertexFormat.f_85812_, Mode.QUADS, 256, false, false, state);
      }
   );
   private static final Function<ResourceLocation, RenderType> EMISSIVE_NO_CULL = Util.m_143827_(
      tex -> {
         TextureStateShard state = new TextureStateShard(tex, false, false);
         return RenderType.m_173215_(
            "emissive_no_cull",
            DefaultVertexFormat.f_85812_,
            Mode.QUADS,
            256,
            false,
            true,
            CompositeState.m_110628_()
               .m_173292_(EYES_SHADER)
               .m_173290_(state)
               .m_110685_(ADDITIVE_TRANSPARENCY)
               .m_110661_(NO_CULL)
               .m_110687_(COLOR_WRITE)
               .m_110691_(false)
         );
      }
   );
   private static final Function<ResourceLocation, RenderType> EMISSIVE_TRANSLUCENT = Util.m_143827_(
      tex -> {
         TextureStateShard state = new TextureStateShard(tex, false, false);
         return RenderType.m_173215_(
            "emissive_translucent",
            DefaultVertexFormat.f_85812_,
            Mode.QUADS,
            256,
            false,
            true,
            CompositeState.m_110628_()
               .m_173292_(EYES_SHADER)
               .m_173290_(state)
               .m_110685_(TRANSLUCENT_TRANSPARENCY)
               .m_110661_(CULL)
               .m_110687_(COLOR_WRITE)
               .m_110691_(false)
         );
      }
   );
   private static final Function<ResourceLocation, RenderType> WITHER_STORM = Util.m_143827_(
      p_269670_ -> {
         CompositeState compositeState = CompositeState.m_110628_()
            .m_173292_(WITHER_STORM_SHADER)
            .m_173290_(new TextureStateShard(p_269670_, false, false))
            .m_110685_(NO_TRANSPARENCY)
            .m_110661_(NO_CULL)
            .m_110671_(LIGHTMAP)
            .m_110677_(OVERLAY)
            .m_110691_(true);
         return RenderType.m_173215_("wither_storm", DefaultVertexFormat.f_85812_, Mode.QUADS, 256, true, false, compositeState);
      }
   );
   private static final Function<ResourceLocation, RenderType> WITHER_STORM_DISSOLVE = Util.m_143827_(
      tex -> {
         CompositeState compositeState = CompositeState.m_110628_()
            .m_173292_(WITHER_STORM_DISSOLVE_SHADER)
            .m_173290_(new TextureStateShard(tex, false, false))
            .m_110691_(true);
         return RenderType.m_173215_("wither_storm_dissolve", DefaultVertexFormat.f_85812_, Mode.QUADS, 256, false, false, compositeState);
      }
   );
   private static final Function<ResourceLocation, RenderType> WITHER_STORM_DECAL = Util.m_143827_(
      tex -> {
         CompositeState compositeState = CompositeState.m_110628_()
            .m_173292_(WITHER_STORM_SHADER)
            .m_173290_(new TextureStateShard(tex, false, false))
            .m_110663_(EQUAL_DEPTH_TEST)
            .m_110671_(LIGHTMAP)
            .m_110677_(OVERLAY)
            .m_110691_(false);
         return RenderType.m_173215_("entity_decal", DefaultVertexFormat.f_85812_, Mode.QUADS, 256, false, false, compositeState);
      }
   );

   public static RenderType entityDecalTranslucent(ResourceLocation tex) {
      return ENTITY_DECAL_TRANSLUCENT.apply(tex);
   }

   public static RenderType emissiveNoCull(ResourceLocation tex) {
      return EMISSIVE_NO_CULL.apply(tex);
   }

   public static RenderType emissiveTranslucent(ResourceLocation tex) {
      return EMISSIVE_TRANSLUCENT.apply(tex);
   }

   public static RenderType witherStorm(ResourceLocation tex) {
      return WITHER_STORM.apply(tex);
   }

   public static RenderType witherStormDissolve(ResourceLocation tex) {
      return WITHER_STORM_DISSOLVE.apply(tex);
   }

   public static RenderType witherStormDecal(ResourceLocation tex) {
      return WITHER_STORM_DECAL.apply(tex);
   }
}
