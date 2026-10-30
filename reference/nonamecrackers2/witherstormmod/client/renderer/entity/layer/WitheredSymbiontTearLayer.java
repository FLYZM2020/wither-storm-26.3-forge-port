/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitheredSymbiontModel;
/*    */ import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ 
/*    */ public class WitheredSymbiontTearLayer
/*    */   extends RenderLayer<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>>
/*    */ {
/* 18 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/withered_symbiont/withered_symbiont_tear.png");
/*    */ 
/*    */   
/*    */   public WitheredSymbiontTearLayer(RenderLayerParent<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>> parent) {
/* 22 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(PoseStack stack, MultiBufferSource buffer, int p_117351_, WitheredSymbiontEntity entity, float p_117353_, float p_117354_, float partialTicks, float p_117356_, float p_117357_, float p_117358_) {
/* 28 */     VertexConsumer consumer = buffer.m_6299_(UtilRenderTypes.emissiveTranslucent(TEXTURE));
/*    */     
/* 30 */     ((WitheredSymbiontModel)m_117386_()).m_7695_(stack, consumer, 15728640, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, entity.getTearAlpha(partialTicks));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\WitheredSymbiontTearLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */