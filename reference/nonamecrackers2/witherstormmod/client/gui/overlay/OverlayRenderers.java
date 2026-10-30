/*    */ package nonamecrackers2.witherstormmod.client.gui.overlay;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.mojang.blaze3d.vertex.BufferBuilder;
/*    */ import com.mojang.blaze3d.vertex.Tesselator;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
/*    */ import net.minecraftforge.client.gui.overlay.ForgeGui;
/*    */ import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
/*    */ import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeManager;
/*    */ import nonamecrackers2.witherstormmod.client.capability.PlayerScreenBlinder;
/*    */ import nonamecrackers2.witherstormmod.client.capability.PlayerTractorBeamEffects;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ 
/*    */ public class OverlayRenderers {
/* 23 */   private static final ResourceLocation TRACTOR_BEAM_OUTLINE = new ResourceLocation("witherstormmod", "textures/misc/tractor_beam_outline.png");
/*    */ 
/*    */   
/*    */   public static void registerOverlays(RegisterGuiOverlaysEvent event) {
/* 27 */     event.registerAboveAll("tractor_beam", (gui, stack, partialTicks, width, height) -> {
/*    */           Minecraft mc = Minecraft.m_91087_();
/*    */ 
/*    */           
/*    */           gui.setupOverlayRenderState(true, false);
/*    */ 
/*    */           
/*    */           mc.f_91074_.getCapability(WitherStormModClientCapabilities.TRACTOR_BEAM_EFFECTS).ifPresent(());
/*    */         });
/*    */     
/* 37 */     event.registerBelow(VanillaGuiOverlay.HOTBAR.id(), "blinding", (gui, stack, partialTicks, width, height) -> {
/*    */           Minecraft mc = Minecraft.m_91087_();
/*    */ 
/*    */           
/*    */           gui.setupOverlayRenderState(true, false);
/*    */ 
/*    */           
/*    */           mc.f_91074_.getCapability(WitherStormModClientCapabilities.SCREEN_BLINDER).ifPresent(());
/*    */         });
/*    */ 
/*    */     
/* 48 */     event.registerAboveAll("bosstheme_watermark", (gui, stack, partialTicks, width, height) -> {
/*    */           Minecraft mc = Minecraft.m_91087_();
/*    */           gui.setupOverlayRenderState(true, false);
/*    */           mc.f_91073_.getCapability(WitherStormModClientCapabilities.BOSS_THEME_MANAGER).ifPresent(());
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void renderTextureOverlay(GuiGraphics graphics, ResourceLocation location, float alpha, int width, int height) {
/* 68 */     RenderSystem.disableDepthTest();
/* 69 */     RenderSystem.depthMask(false);
/* 70 */     graphics.m_280246_(1.0F, 1.0F, 1.0F, alpha);
/* 71 */     graphics.m_280398_(location, 0, 0, -90, 0.0F, 0.0F, width, height, width, height);
/* 72 */     RenderSystem.depthMask(true);
/* 73 */     RenderSystem.enableDepthTest();
/* 74 */     graphics.m_280246_(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   private static void renderSolidOverlay(float r, float g, float b, float alpha, int width, int height) {
/* 79 */     Tesselator tesselator = Tesselator.m_85913_();
/* 80 */     BufferBuilder builder = tesselator.m_85915_();
/* 81 */     RenderSystem.disableDepthTest();
/* 82 */     RenderSystem.depthMask(false);
/* 83 */     RenderSystem.defaultBlendFunc();
/* 84 */     RenderSystem.setShader(GameRenderer::m_172811_);
/* 85 */     builder.m_166779_(VertexFormat.Mode.QUADS, DefaultVertexFormat.f_85815_);
/* 86 */     builder.m_5483_(0.0D, height, 0.0D).m_85950_(r, g, b, alpha).m_5752_();
/* 87 */     builder.m_5483_(width, height, 0.0D).m_85950_(r, g, b, alpha).m_5752_();
/* 88 */     builder.m_5483_(width, 0.0D, 0.0D).m_85950_(r, g, b, alpha).m_5752_();
/* 89 */     builder.m_5483_(0.0D, 0.0D, 0.0D).m_85950_(r, g, b, alpha).m_5752_();
/* 90 */     tesselator.m_85914_();
/* 91 */     RenderSystem.depthMask(true);
/* 92 */     RenderSystem.enableDepthTest();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\overlay\OverlayRenderers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */