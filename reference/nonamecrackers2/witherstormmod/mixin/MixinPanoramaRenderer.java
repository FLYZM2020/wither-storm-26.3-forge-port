/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.renderer.PanoramaRenderer;
/*    */ import nonamecrackers2.witherstormmod.client.util.PanoramaExtensions;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.Constant;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyConstant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({PanoramaRenderer.class})
/*    */ public abstract class MixinPanoramaRenderer
/*    */   implements PanoramaExtensions
/*    */ {
/*    */   @Shadow
/*    */   private float f_244569_;
/*    */   @Nullable
/*    */   private Float xOffset;
/*    */   
/*    */   public void setXOffset(float offset) {
/* 24 */     this.xOffset = Float.valueOf(offset);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setStartingSpin(float spin) {
/* 30 */     this.f_244569_ = spin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ModifyConstant(method = {"render"}, constant = {@Constant(floatValue = 10.0F)})
/*    */   private float modifyXOffset(float offset) {
/* 39 */     return (this.xOffset != null) ? this.xOffset.floatValue() : offset;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinPanoramaRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */