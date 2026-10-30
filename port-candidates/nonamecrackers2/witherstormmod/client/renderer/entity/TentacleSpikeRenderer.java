/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.mojang.math.Axis;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleSpikeModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;
/*    */ 
/*    */ public class TentacleSpikeRenderer
/*    */   extends EntityRenderer<TentacleSpike>
/*    */ {
/* 20 */   private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/tentacle_spike/tentacle_spike.png");
/* 21 */   private static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/tentacle_spike/tentacle_spike_emissive.png");
/*    */   
/*    */   private final TentacleSpikeModel<TentacleSpike> model;
/*    */   
/*    */   public TentacleSpikeRenderer(EntityRendererProvider.Context context) {
/* 26 */     super(context);
/* 27 */     this.model = new TentacleSpikeModel(context.bakeLayer(WitherStormModRenderers.TENTACLE_SPIKE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(TentacleSpike fang, float p_114486_, float partialTicks, PoseStack stack, MultiBufferSource buffers, int packedLight) {
/* 33 */     float animProg = fang.getAnimationProgress(partialTicks);
/* 34 */     if (animProg != 0.0F) {
/*    */       
/* 36 */       float vertScale = 1.0F;
/* 37 */       float horzScale = 1.0F;
/* 38 */       if (animProg > 0.9F)
/* 39 */         horzScale *= (1.0F - animProg) / 0.1F; 
/* 40 */       if (animProg > 0.9F) {
/* 41 */         vertScale *= (1.0F - animProg) / 0.1F;
/* 42 */       } else if (animProg < 0.08F) {
/* 43 */         vertScale *= animProg / 0.08F;
/* 44 */       }  stack.pushPose();
/* 45 */       stack.mulPose(Axis.YP.rotationDegrees(90.0F - fang.getYRot()));
/* 46 */       stack.scale(-horzScale, -vertScale, horzScale);
/* 47 */       this.model.setupAnim(fang, animProg, 0.0F, 0.0F, fang.getYRot(), fang.getXRot());
/* 48 */       VertexConsumer consumer = buffers.getBuffer(this.model.renderType(getTextureLocation(fang)));
/* 49 */       this.model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/* 50 */       VertexConsumer emissive = buffers.getBuffer(RenderType.eyes(EMISSIVE));
/* 51 */       this.model.renderToBuffer(stack, emissive, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/* 52 */       stack.popPose();
/* 53 */       super.render((Entity)fang, p_114486_, partialTicks, stack, buffers, packedLight);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(TentacleSpike fang) {
/* 60 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\TentacleSpikeRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */