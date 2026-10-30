/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.PigModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedPig;
/*    */ 
/*    */ public class SickenedPigRenderer extends MobRenderer<SickenedPig, PigModel<SickenedPig>> {
/* 15 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_pig.png");
/* 16 */   private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_pig_emissive.png");
/* 17 */   private static final ResourceLocation EGG_TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/misc/sickened_reuben.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public SickenedPigRenderer(EntityRendererProvider.Context context) {
/* 22 */     super(context, (EntityModel)new PigModel(context.m_174023_(WitherStormModRenderers.SICKENED_PIG)), 0.7F);
/* 23 */     m_115326_((RenderLayer)new EyesLayer<SickenedPig, PigModel<SickenedPig>>((RenderLayerParent)this)
/*    */         {
/*    */           public RenderType m_5708_() {
/* 26 */             return RenderType.m_110488_(SickenedPigRenderer.EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedPig pig) {
/* 34 */     if (pig.m_8077_() && pig.m_7755_().getString().equals("reuben"))
/*    */     {
/* 36 */       return EGG_TEXTURE;
/*    */     }
/* 38 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedPigRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */