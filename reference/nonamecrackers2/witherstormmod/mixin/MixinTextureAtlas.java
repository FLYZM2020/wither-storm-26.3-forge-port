/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.client.renderer.texture.SpriteLoader;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*    */ import nonamecrackers2.witherstormmod.client.util.TextureAtlasAccessor;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({TextureAtlas.class})
/*    */ public abstract class MixinTextureAtlas
/*    */   implements TextureAtlasAccessor
/*    */ {
/*    */   @Unique
/*    */   private int totalWidth;
/*    */   @Unique
/*    */   private int totalHeight;
/*    */   
/*    */   @Inject(method = {"upload"}, at = {@At("TAIL")})
/*    */   public void uploadTail(SpriteLoader.Preparations preperations, CallbackInfo ci) {
/* 27 */     this.totalWidth = preperations.f_243669_();
/* 28 */     this.totalHeight = preperations.f_244632_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 34 */     return this.totalHeight;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWidth() {
/* 40 */     return this.totalWidth;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinTextureAtlas.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */