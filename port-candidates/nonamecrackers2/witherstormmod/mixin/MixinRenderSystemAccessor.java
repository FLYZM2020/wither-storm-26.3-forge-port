/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import org.joml.Vector3f;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.gen.Accessor;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({RenderSystem.class})
/*    */ public interface MixinRenderSystemAccessor
/*    */ {
/*    */   @Accessor("shaderLightDirections")
/*    */   static Vector3f[] witherstormmod$getShaderLightDirections() {
/* 15 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinRenderSystemAccessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */