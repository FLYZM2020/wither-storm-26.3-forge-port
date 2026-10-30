package nonamecrackers2.witherstormmod.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;

public class OverlayRenderers {
   private static final ResourceLocation TRACTOR_BEAM_OUTLINE = new ResourceLocation("witherstormmod", "textures/misc/tractor_beam_outline.png");

   public static void registerOverlays(RegisterGuiOverlaysEvent event) {
      event.registerAboveAll(
         "tractor_beam",
         (gui, stack, partialTicks, width, height) -> {
            Minecraft mc = Minecraft.m_91087_();
            gui.setupOverlayRenderState(true, false);
            mc.f_91074_
               .getCapability(WitherStormModClientCapabilities.TRACTOR_BEAM_EFFECTS)
               .ifPresent(
                  effects -> {
                     if (effects.getTicksInTractorBeam() > 0
                        && (Boolean)WitherStormModConfig.CLIENT.renderTractorBeamOverlay.get()
                        && (Boolean)WitherStormModConfig.CLIENT.renderTractorBeams.get()) {
                        renderTextureOverlay(stack, TRACTOR_BEAM_OUTLINE, effects.getPercent(), width, height);
                     }
                  }
               );
         }
      );
      event.registerBelow(VanillaGuiOverlay.HOTBAR.id(), "blinding", (gui, stack, partialTicks, width, height) -> {
         Minecraft mc = Minecraft.m_91087_();
         gui.setupOverlayRenderState(true, false);
         mc.f_91074_.getCapability(WitherStormModClientCapabilities.SCREEN_BLINDER).ifPresent(effects -> {
            float fade = effects.getFade(partialTicks);
            if (fade > 0.0F && (Boolean)WitherStormModConfig.CLIENT.blindingEffects.get()) {
               renderSolidOverlay(1.0F, 1.0F, 1.0F, fade, width, height);
            }
         });
      });
      event.registerAboveAll(
         "bosstheme_watermark",
         (gui, stack, partialTicks, width, height) -> {
            Minecraft mc = Minecraft.m_91087_();
            gui.setupOverlayRenderState(true, false);
            mc.f_91073_
               .getCapability(WitherStormModClientCapabilities.BOSS_THEME_MANAGER)
               .ifPresent(
                  manager -> {
                     int time = manager.getWatermarkTime();
                     Component watermark = manager.getWatermark();
                     if (watermark != null && time > 0) {
                        float fade = 40.0F;
                        int alpha = Mth.m_14143_(
                           Math.min(1.0F, (fade - (time - (manager.getWatermarkStartTime() - fade))) / fade) * Math.min(1.0F, time / fade) * 255.0F
                        );
                        stack.m_280430_(gui.m_93082_(), watermark, 10, height - 9 - 8, 16777215 + (alpha << 24));
                     }
                  }
               );
         }
      );
   }

   private static void renderTextureOverlay(GuiGraphics graphics, ResourceLocation location, float alpha, int width, int height) {
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      graphics.m_280246_(1.0F, 1.0F, 1.0F, alpha);
      graphics.m_280398_(location, 0, 0, -90, 0.0F, 0.0F, width, height, width, height);
      RenderSystem.depthMask(true);
      RenderSystem.enableDepthTest();
      graphics.m_280246_(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private static void renderSolidOverlay(float r, float g, float b, float alpha, int width, int height) {
      Tesselator tesselator = Tesselator.m_85913_();
      BufferBuilder builder = tesselator.m_85915_();
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShader(GameRenderer::m_172811_);
      builder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85815_);
      builder.m_5483_(0.0, height, 0.0).m_85950_(r, g, b, alpha).m_5752_();
      builder.m_5483_(width, height, 0.0).m_85950_(r, g, b, alpha).m_5752_();
      builder.m_5483_(width, 0.0, 0.0).m_85950_(r, g, b, alpha).m_5752_();
      builder.m_5483_(0.0, 0.0, 0.0).m_85950_(r, g, b, alpha).m_5752_();
      tesselator.m_85914_();
      RenderSystem.depthMask(true);
      RenderSystem.enableDepthTest();
   }
}
