/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.WitherStormHeadRenderer;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitherStormHeadModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
/*    */ 
/*    */ public class WitherStormHeadEyesLayer
/*    */   extends EyesLayer<WitherStormHeadEntity, WitherStormHeadModel> {
/* 17 */   private static final RenderType EYES = RenderType.m_110488_(WitherStormHeadRenderer.EMISSIVE);
/* 18 */   private static final RenderType EYES_HURT = RenderType.m_110488_(WitherStormHeadRenderer.EMISSIVE_HURT);
/* 19 */   private static final RenderType ENTITY_CUTOUT = RenderType.m_110452_(WitherStormHeadRenderer.EMISSIVE);
/*    */ 
/*    */   
/*    */   public WitherStormHeadEyesLayer(RenderLayerParent<WitherStormHeadEntity, WitherStormHeadModel> renderer) {
/* 23 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, WitherStormHeadEntity entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
/* 29 */     VertexConsumer builder = null;
/* 30 */     if (entity.isPlayingDead()) {
/* 31 */       builder = buffer.m_6299_(ENTITY_CUTOUT);
/* 32 */     } else if (entity.isHurt()) {
/* 33 */       builder = buffer.m_6299_(EYES_HURT);
/*    */     } else {
/* 35 */       builder = buffer.m_6299_(EYES);
/* 36 */     }  ((WitherStormHeadModel)m_117386_()).m_7695_(stack, builder, 15728640, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderType m_5708_() {
/* 42 */     return EYES;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\WitherStormHeadEyesLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */