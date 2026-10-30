/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.GoatModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedGoat;
/*    */ 
/*    */ public class SickenedGoatRenderer extends MobRenderer<SickenedGoat, GoatModel<SickenedGoat>> {
/* 15 */   private static final ResourceLocation GOAT_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_snow_golem.png");
/* 16 */   private static final ResourceLocation GOAT_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_snow_golem_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedGoatRenderer(EntityRendererProvider.Context context) {
/* 20 */     super(context, (EntityModel)new GoatModel(context.m_174023_(WitherStormModRenderers.SICKENED_GOAT)), 0.5F);
/* 21 */     m_115326_((RenderLayer)new EyesLayer<SickenedGoat, GoatModel<SickenedGoat>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 26 */             return RenderType.m_110488_(SickenedGoatRenderer.GOAT_EMISSIVE_LOCATION);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedGoat entity) {
/* 33 */     return GOAT_LOCATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedGoatRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */