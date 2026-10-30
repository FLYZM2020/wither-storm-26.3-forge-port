/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.CowModel;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedMushroomCowMushroomLayer;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedMushroomCow;
/*    */ 
/*    */ public class SickenedMushroomCowRenderer extends MobRenderer<SickenedMushroomCow, CowModel<SickenedMushroomCow>> {
/* 16 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_mushroom_cow.png");
/* 17 */   private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_mushroom_cow_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedMushroomCowRenderer(EntityRendererProvider.Context context) {
/* 21 */     super(context, (EntityModel)new CowModel(context.m_174023_(WitherStormModRenderers.SICKENED_MUSHROOM_COW)), 0.7F);
/* 22 */     m_115326_((RenderLayer)new EyesLayer<SickenedMushroomCow, CowModel<SickenedMushroomCow>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 27 */             return RenderType.m_110488_(SickenedMushroomCowRenderer.EMISSIVE);
/*    */           }
/*    */         });
/* 30 */     m_115326_((RenderLayer)new SickenedMushroomCowMushroomLayer((RenderLayerParent)this, context.m_234597_()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedMushroomCow cow) {
/* 36 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedMushroomCowRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */