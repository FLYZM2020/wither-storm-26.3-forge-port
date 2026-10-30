/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.CowModel;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedMushroomCowMushroomLayer;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedMushroomCow;
/*    */ 
/*    */ public class SickenedMushroomCowRenderer extends MobRenderer<SickenedMushroomCow, CowModel<SickenedMushroomCow>> {
/* 16 */   private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_mushroom_cow.png");
/* 17 */   private static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_mushroom_cow_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedMushroomCowRenderer(EntityRendererProvider.Context context) {
/* 21 */     super(context, (EntityModel)new CowModel(context.bakeLayer(WitherStormModRenderers.SICKENED_MUSHROOM_COW)), 0.7F);
/* 22 */     addLayer((RenderLayer)new EyesLayer<SickenedMushroomCow, CowModel<SickenedMushroomCow>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType renderType()
/*    */           {
/* 27 */             return RenderType.eyes(SickenedMushroomCowRenderer.EMISSIVE);
/*    */           }
/*    */         });
/* 30 */     addLayer((RenderLayer)new SickenedMushroomCowMushroomLayer((RenderLayerParent)this, context.getBlockRenderDispatcher()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(SickenedMushroomCow cow) {
/* 36 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedMushroomCowRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */