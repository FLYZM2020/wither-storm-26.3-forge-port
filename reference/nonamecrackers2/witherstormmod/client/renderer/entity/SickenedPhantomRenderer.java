/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.PhantomModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedPhantom;
/*    */ 
/*    */ public class SickenedPhantomRenderer extends MobRenderer<SickenedPhantom, PhantomModel<SickenedPhantom>> {
/* 18 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_phantom.png");
/* 19 */   private static final ResourceLocation TEXTURE_EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_phantom_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedPhantomRenderer(EntityRendererProvider.Context context) {
/* 23 */     super(context, (EntityModel)new PhantomModel(context.m_174023_(WitherStormModRenderers.SICKENED_PHANTOM)), 0.75F);
/* 24 */     m_115326_((RenderLayer)new EyesLayer<SickenedPhantom, PhantomModel<SickenedPhantom>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 29 */             return RenderType.m_110488_(SickenedPhantomRenderer.TEXTURE_EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedPhantom phantom) {
/* 37 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void scale(SickenedPhantom phantom, PoseStack stack, float partialTicks) {
/* 42 */     int i = phantom.m_33172_();
/* 43 */     float f = 1.0F + 0.15F * i;
/* 44 */     stack.m_85841_(f, f, f);
/* 45 */     stack.m_252880_(0.0F, 1.3125F, 0.1875F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setupRotations(SickenedPhantom phantom, PoseStack stack, float p_115687_, float p_115688_, float p_115689_) {
/* 50 */     super.m_7523_((LivingEntity)phantom, stack, p_115687_, p_115688_, p_115689_);
/* 51 */     stack.m_252781_(Axis.f_252529_.m_252977_(phantom.m_146909_()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedPhantomRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */