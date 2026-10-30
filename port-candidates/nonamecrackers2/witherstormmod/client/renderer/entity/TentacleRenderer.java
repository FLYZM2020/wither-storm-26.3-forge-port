/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.LivingEntityRenderer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
/*    */ 
/*    */ public class TentacleRenderer
/*    */   extends EntityRenderer<TentacleEntity> {
/* 18 */   private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/tentacle/tentacle.png");
/*    */   
/*    */   private final TentacleModel model;
/*    */   
/*    */   public TentacleRenderer(EntityRendererProvider.Context context) {
/* 23 */     super(context);
/* 24 */     this.model = new TentacleModel(context.bakeLayer(WitherStormModRenderers.TENTACLE));
/* 25 */     this.shadowRadius = 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(TentacleEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
/* 31 */     super.render((Entity)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
/*    */     
/* 33 */     stack.pushPose();
/* 34 */     stack.scale(-1.0F, -1.0F, 1.0F);
/* 35 */     stack.scale(2.0F, 2.0F, 2.0F);
/* 36 */     VertexConsumer builder = buffer.getBuffer(this.model.renderType(getTextureLocation(entity)));
/* 37 */     this.model.setupAnim(entity, partialTicks, 0.0F, 0.0F, entity.getYRot(), entity.getXRot());
/* 38 */     int i = LivingEntityRenderer.getOverlayCoords((LivingEntity)entity, 0.0F);
/* 39 */     this.model.renderToBuffer(stack, builder, packedLight, i, 1.0F, 1.0F, 1.0F, 1.0F);
/* 40 */     stack.popPose();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(TentacleEntity entity) {
/* 46 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\TentacleRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */