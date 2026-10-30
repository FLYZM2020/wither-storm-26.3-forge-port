/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.SnowGolemModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedSnowGolemHeadLayer;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;
/*    */ 
/*    */ public class SickenedSnowGolemRenderer extends MobRenderer<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>> {
/* 16 */   private static final ResourceLocation SNOW_GOLEM_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_snow_golem.png");
/* 17 */   private static final ResourceLocation SNOW_GOLEM_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_snow_golem_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedSnowGolemRenderer(EntityRendererProvider.Context context) {
/* 21 */     super(context, (EntityModel)new SnowGolemModel(context.m_174023_(WitherStormModRenderers.SICKENED_SNOW_GOLEM)), 0.5F);
/* 22 */     m_115326_((RenderLayer)new SickenedSnowGolemHeadLayer((RenderLayerParent)this, context.m_234597_(), context.m_174025_()));
/* 23 */     m_115326_((RenderLayer)new EyesLayer<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 28 */             return RenderType.m_110488_(SickenedSnowGolemRenderer.SNOW_GOLEM_EMISSIVE_LOCATION);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedSnowGolem entity) {
/* 35 */     return SNOW_GOLEM_LOCATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedSnowGolemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */