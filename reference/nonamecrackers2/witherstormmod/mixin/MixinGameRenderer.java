/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.capability.PlayerCameraShaker;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*    */ import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
/*    */ import nonamecrackers2.witherstormmod.client.shader.PostProcessingShaders;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({GameRenderer.class})
/*    */ public class MixinGameRenderer
/*    */ {
/*    */   @Inject(method = {"renderLevel"}, at = {@At("TAIL")})
/*    */   public void renderLevelTail(float partialTicks, long l, PoseStack stack, CallbackInfo ci) {
/* 28 */     PostProcessingShaders.INSTANCE.renderShaders(partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"bobView"}, at = {@At("TAIL")})
/*    */   public void bobViewTail(PoseStack stack, float partialTicks, CallbackInfo ci) {
/* 37 */     Minecraft mc = Minecraft.m_91087_();
/* 38 */     Entity entity = mc.m_91288_(); if (entity instanceof LocalPlayer) { LocalPlayer player = (LocalPlayer)entity;
/*    */       
/* 40 */       player.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> {
/*    */             float x = shaker.getXShake(partialTicks);
/*    */             float y = shaker.getYShake(partialTicks);
/*    */             stack.m_85837_(Mth.m_14031_((float)Math.toRadians(x)), Mth.m_14031_((float)Math.toRadians(y)), 0.0D);
/*    */           }); }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"close"}, at = {@At("TAIL")})
/*    */   public void witherstormmod$shutdownRenderBufferer_close(CallbackInfo ci) {
/* 52 */     RenderBufferer.INSTANCE.shutdown();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinGameRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */