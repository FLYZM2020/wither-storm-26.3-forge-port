/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.SkeletonModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.monster.AbstractSkeleton;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedSkeleton;
/*    */ 
/*    */ public class SickenedSkeletonRenderer extends SkeletonRenderer {
/* 16 */   private static final ResourceLocation SICKENED_SKELETON_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_skeleton.png");
/* 17 */   private static final ResourceLocation SICKENED_SKELETON_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_skeleton_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedSkeletonRenderer(EntityRendererProvider.Context context) {
/* 21 */     super(context, WitherStormModRenderers.SICKENED_SKELETON, WitherStormModRenderers.SICKENED_SKELETON_INNER_ARMOR, WitherStormModRenderers.SICKENED_SKELETON_OUTER_ARMOR);
/* 22 */     m_115326_((RenderLayer)new EyesLayer<AbstractSkeleton, SkeletonModel<AbstractSkeleton>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 27 */             return RenderType.m_110488_(SickenedSkeletonRenderer.SICKENED_SKELETON_EMISSIVE_LOCATION);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation m_5478_(AbstractSkeleton entity) {
/* 35 */     return SICKENED_SKELETON_LOCATION;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean m_5936_(AbstractSkeleton entity) {
/* 41 */     return (super.m_5936_(entity) || ((SickenedSkeleton)entity).isConverting());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedSkeletonRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */