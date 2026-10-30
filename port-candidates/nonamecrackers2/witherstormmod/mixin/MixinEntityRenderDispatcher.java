/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({EntityRenderDispatcher.class})
/*    */ public class MixinEntityRenderDispatcher
/*    */ {
/*    */   @Inject(method = {"renderHitbox"}, at = {@At("TAIL")})
/*    */   private static void witherstormmod$renderAdditionalHitboxes_renderHitbox(PoseStack stack, VertexConsumer consumer, Entity entity, float partialTick, CallbackInfo ci) {
/* 22 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/* 23 */       AbstractWitherStormRenderer.renderExtraHitboxes(stack, consumer, storm, partialTick); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinEntityRenderDispatcher.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */