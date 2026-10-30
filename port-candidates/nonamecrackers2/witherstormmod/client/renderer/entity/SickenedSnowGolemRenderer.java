/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.SnowGolemModel;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedSnowGolemHeadLayer;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;
/*    */ 
/*    */ public class SickenedSnowGolemRenderer extends MobRenderer<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>> {
/* 16 */   private static final Identifier SNOW_GOLEM_LOCATION = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_snow_golem.png");
/* 17 */   private static final Identifier SNOW_GOLEM_EMISSIVE_LOCATION = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_snow_golem_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedSnowGolemRenderer(EntityRendererProvider.Context context) {
/* 21 */     super(context, (EntityModel)new SnowGolemModel(context.bakeLayer(WitherStormModRenderers.SICKENED_SNOW_GOLEM)), 0.5F);
/* 22 */     addLayer((RenderLayer)new SickenedSnowGolemHeadLayer((RenderLayerParent)this, context.getBlockRenderDispatcher(), context.getItemRenderer()));
/* 23 */     addLayer((RenderLayer)new EyesLayer<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType renderType()
/*    */           {
/* 28 */             return RenderType.eyes(SickenedSnowGolemRenderer.SNOW_GOLEM_EMISSIVE_LOCATION);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(SickenedSnowGolem entity) {
/* 35 */     return SNOW_GOLEM_LOCATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedSnowGolemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */