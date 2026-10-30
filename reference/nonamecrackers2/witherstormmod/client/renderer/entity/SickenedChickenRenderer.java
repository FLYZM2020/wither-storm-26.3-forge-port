/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.ChickenModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedChicken;
/*    */ 
/*    */ public class SickenedChickenRenderer extends MobRenderer<SickenedChicken, ChickenModel<SickenedChicken>> {
/* 16 */   public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_chicken.png");
/* 17 */   public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_chicken_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedChickenRenderer(EntityRendererProvider.Context context) {
/* 21 */     super(context, (EntityModel)new ChickenModel(context.m_174023_(WitherStormModRenderers.SICKENED_CHICKEN)), 0.3F);
/* 22 */     m_115326_((RenderLayer)new EyesLayer<SickenedChicken, ChickenModel<SickenedChicken>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 27 */             return RenderType.m_110488_(SickenedChickenRenderer.EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedChicken chicken) {
/* 35 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getBob(SickenedChicken chicken, float partialTicks) {
/* 40 */     float f = Mth.m_14179_(partialTicks, chicken.f_28229_, chicken.f_28226_);
/* 41 */     float f1 = Mth.m_14179_(partialTicks, chicken.f_28228_, chicken.f_28227_);
/* 42 */     return (Mth.m_14031_(f) + 1.0F) * f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedChickenRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */