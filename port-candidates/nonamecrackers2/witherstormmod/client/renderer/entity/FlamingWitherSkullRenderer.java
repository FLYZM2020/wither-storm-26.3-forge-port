/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.FlamingWitherSkullModel;
/*    */ import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
/*    */ import nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity;
/*    */ 
/*    */ public class FlamingWitherSkullRenderer<T extends FlamingWitherSkullEntity>
/*    */   extends EntityRenderer<T>
/*    */ {
/* 21 */   private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/flaming_wither_skull/flaming_wither_skull.png");
/* 22 */   private static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/flaming_wither_skull/flaming_wither_skull_emissive.png");
/*    */   
/*    */   private final FlamingWitherSkullModel model;
/*    */ 
/*    */   
/*    */   public FlamingWitherSkullRenderer(EntityRendererProvider.Context context) {
/* 28 */     super(context);
/* 29 */     this.model = new FlamingWitherSkullModel(context.bakeLayer(WitherStormModRenderers.FLAMING_WITHER_SKULL));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getBlockLightLevel(FlamingWitherSkullEntity p_225624_1_, BlockPos p_225624_2_) {
/* 35 */     return 15;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
/* 42 */     stack.pushPose();
/* 43 */     stack.scale(-1.0F, -1.0F, 1.0F);
/* 44 */     stack.scale(1.8F, 1.8F, 1.8F);
/* 45 */     float f = Mth.rotLerp(partialTicks, ((FlamingWitherSkullEntity)entity).yRotO, entity.getYRot());
/* 46 */     float f1 = Mth.lerp(partialTicks, ((FlamingWitherSkullEntity)entity).xRotO, entity.getXRot());
/* 47 */     VertexConsumer builder = buffer.getBuffer(this.model.renderType(getTextureLocation(entity)));
/* 48 */     this.model.setupAnim(partialTicks, f, f1);
/* 49 */     this.model.renderToBuffer(stack, builder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/* 50 */     VertexConsumer emissive = buffer.getBuffer(UtilRenderTypes.emissiveNoCull(getEmissiveTextureLocation(entity)));
/* 51 */     this.model.renderToBuffer(stack, emissive, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/* 52 */     stack.popPose();
/* 53 */     super.render((Entity)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(T entity) {
/* 59 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   public Identifier getEmissiveTextureLocation(T entity) {
/* 64 */     return EMISSIVE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\FlamingWitherSkullRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */