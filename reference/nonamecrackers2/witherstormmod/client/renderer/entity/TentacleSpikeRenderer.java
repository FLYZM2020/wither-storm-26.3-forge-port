/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.mojang.math.Axis;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleSpikeModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;
/*    */ 
/*    */ public class TentacleSpikeRenderer
/*    */   extends EntityRenderer<TentacleSpike>
/*    */ {
/* 20 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/tentacle_spike/tentacle_spike.png");
/* 21 */   private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/tentacle_spike/tentacle_spike_emissive.png");
/*    */   
/*    */   private final TentacleSpikeModel<TentacleSpike> model;
/*    */   
/*    */   public TentacleSpikeRenderer(EntityRendererProvider.Context context) {
/* 26 */     super(context);
/* 27 */     this.model = new TentacleSpikeModel(context.m_174023_(WitherStormModRenderers.TENTACLE_SPIKE));
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
/* 44 */       }  stack.m_85836_();
/* 45 */       stack.m_252781_(Axis.f_252436_.m_252977_(90.0F - fang.m_146908_()));
/* 46 */       stack.m_85841_(-horzScale, -vertScale, horzScale);
/* 47 */       this.model.setupAnim(fang, animProg, 0.0F, 0.0F, fang.m_146908_(), fang.m_146909_());
/* 48 */       VertexConsumer consumer = buffers.m_6299_(this.model.m_103119_(getTextureLocation(fang)));
/* 49 */       this.model.m_7695_(stack, consumer, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 50 */       VertexConsumer emissive = buffers.m_6299_(RenderType.m_110488_(EMISSIVE));
/* 51 */       this.model.m_7695_(stack, emissive, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 52 */       stack.m_85849_();
/* 53 */       super.m_7392_((Entity)fang, p_114486_, partialTicks, stack, buffers, packedLight);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(TentacleSpike fang) {
/* 60 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\TentacleSpikeRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */