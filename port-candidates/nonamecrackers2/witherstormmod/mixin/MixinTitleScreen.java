/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.client.gui.screens.TitleScreen;
/*    */ import net.minecraft.client.renderer.CubeMap;
/*    */ import net.minecraft.client.renderer.PanoramaRenderer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ 
/*    */ @Mixin({TitleScreen.class})
/*    */ public class MixinTitleScreen {
/*    */   private static final CubeMap WITHERSTORMMOD_CUBE_MAP;
/*    */   
/*    */   static {
/* 18 */     WITHERSTORMMOD_CUBE_MAP = (CubeMap)Util.make(new CubeMap(Identifier.parse("textures/gui/title/background/panorama")), map -> ((MixinCubeMap)map).getImages()[0] = Identifier.fromNamespaceAndPath("witherstormmod", "textures/gui/title/background/panorama_0.png"));
/*    */   }
/*    */   
/* 21 */   private final PanoramaRenderer witherstormmodPanorama = new PanoramaRenderer(WITHERSTORMMOD_CUBE_MAP);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Redirect(method = {"render"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/PanoramaRenderer;render(FF)V"))
/*    */   public void render_overridePanorama(PanoramaRenderer panorama, float partialTicks, float alpha) {
/* 29 */     if (((Boolean)WitherStormModConfig.CLIENT.customPanorama.get()).booleanValue()) {
/* 30 */       this.witherstormmodPanorama.render(partialTicks, alpha);
/*    */     } else {
/* 32 */       panorama.render(partialTicks, alpha);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinTitleScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */