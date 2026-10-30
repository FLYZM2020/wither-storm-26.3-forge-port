/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.renderer.LevelRenderer;
/*    */ import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({LevelRenderer.class})
/*    */ public abstract class MixinLevelRenderer
/*    */ {
/*    */   @Shadow
/*    */   @Nullable
/*    */   private ClientLevel f_109465_;
/*    */   
/*    */   @Inject(method = {"allChanged"}, at = {@At("TAIL")})
/*    */   public void allChangedTail(CallbackInfo ci) {
/* 27 */     if (this.f_109465_ != null)
/* 28 */       RenderBufferer.INSTANCE.levelReload(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinLevelRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */