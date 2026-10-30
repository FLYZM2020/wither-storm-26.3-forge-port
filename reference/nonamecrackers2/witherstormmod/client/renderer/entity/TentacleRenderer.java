/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.LivingEntityRenderer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
/*    */ 
/*    */ public class TentacleRenderer
/*    */   extends EntityRenderer<TentacleEntity> {
/* 18 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/tentacle/tentacle.png");
/*    */   
/*    */   private final TentacleModel model;
/*    */   
/*    */   public TentacleRenderer(EntityRendererProvider.Context context) {
/* 23 */     super(context);
/* 24 */     this.model = new TentacleModel(context.m_174023_(WitherStormModRenderers.TENTACLE));
/* 25 */     this.f_114477_ = 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(TentacleEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
/* 31 */     super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
/*    */     
/* 33 */     stack.m_85836_();
/* 34 */     stack.m_85841_(-1.0F, -1.0F, 1.0F);
/* 35 */     stack.m_85841_(2.0F, 2.0F, 2.0F);
/* 36 */     VertexConsumer builder = buffer.m_6299_(this.model.m_103119_(getTextureLocation(entity)));
/* 37 */     this.model.setupAnim(entity, partialTicks, 0.0F, 0.0F, entity.m_146908_(), entity.m_146909_());
/* 38 */     int i = LivingEntityRenderer.m_115338_((LivingEntity)entity, 0.0F);
/* 39 */     this.model.m_7695_(stack, builder, packedLight, i, 1.0F, 1.0F, 1.0F, 1.0F);
/* 40 */     stack.m_85849_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(TentacleEntity entity) {
/* 46 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\TentacleRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */