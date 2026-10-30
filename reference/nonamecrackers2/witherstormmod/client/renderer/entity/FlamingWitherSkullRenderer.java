/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.resources.ResourceLocation;
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
/* 21 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/flaming_wither_skull/flaming_wither_skull.png");
/* 22 */   private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/flaming_wither_skull/flaming_wither_skull_emissive.png");
/*    */   
/*    */   private final FlamingWitherSkullModel model;
/*    */ 
/*    */   
/*    */   public FlamingWitherSkullRenderer(EntityRendererProvider.Context context) {
/* 28 */     super(context);
/* 29 */     this.model = new FlamingWitherSkullModel(context.m_174023_(WitherStormModRenderers.FLAMING_WITHER_SKULL));
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
/* 42 */     stack.m_85836_();
/* 43 */     stack.m_85841_(-1.0F, -1.0F, 1.0F);
/* 44 */     stack.m_85841_(1.8F, 1.8F, 1.8F);
/* 45 */     float f = Mth.m_14189_(partialTicks, ((FlamingWitherSkullEntity)entity).f_19859_, entity.m_146908_());
/* 46 */     float f1 = Mth.m_14179_(partialTicks, ((FlamingWitherSkullEntity)entity).f_19860_, entity.m_146909_());
/* 47 */     VertexConsumer builder = buffer.m_6299_(this.model.m_103119_(getTextureLocation(entity)));
/* 48 */     this.model.m_6251_(partialTicks, f, f1);
/* 49 */     this.model.m_7695_(stack, builder, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 50 */     VertexConsumer emissive = buffer.m_6299_(UtilRenderTypes.emissiveNoCull(getEmissiveTextureLocation(entity)));
/* 51 */     this.model.m_7695_(stack, emissive, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 52 */     stack.m_85849_();
/* 53 */     super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 59 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getEmissiveTextureLocation(T entity) {
/* 64 */     return EMISSIVE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\FlamingWitherSkullRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */