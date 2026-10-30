/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.PigModel;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedPig;
/*    */ 
/*    */ public class SickenedPigRenderer extends MobRenderer<SickenedPig, PigModel<SickenedPig>> {
/* 15 */   private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_pig.png");
/* 16 */   private static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_pig_emissive.png");
/* 17 */   private static final Identifier EGG_TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/misc/sickened_reuben.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public SickenedPigRenderer(EntityRendererProvider.Context context) {
/* 22 */     super(context, (EntityModel)new PigModel(context.bakeLayer(WitherStormModRenderers.SICKENED_PIG)), 0.7F);
/* 23 */     addLayer((RenderLayer)new EyesLayer<SickenedPig, PigModel<SickenedPig>>((RenderLayerParent)this)
/*    */         {
/*    */           public RenderType renderType() {
/* 26 */             return RenderType.eyes(SickenedPigRenderer.EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(SickenedPig pig) {
/* 34 */     if (pig.hasCustomName() && pig.getName().getString().equals("reuben"))
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