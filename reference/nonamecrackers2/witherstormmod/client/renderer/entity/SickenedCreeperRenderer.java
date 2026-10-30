/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.CreeperModel;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.geom.EntityModelSet;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedCreeper;
/*    */ 
/*    */ public class SickenedCreeperRenderer extends MobRenderer<SickenedCreeper, CreeperModel<SickenedCreeper>> {
/* 22 */   private static final ResourceLocation SICKENED_CREEPER_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_creeper.png");
/* 23 */   private static final ResourceLocation SICKENED_CREEPER_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_creeper_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedCreeperRenderer(EntityRendererProvider.Context context) {
/* 27 */     super(context, (EntityModel)new CreeperModel(context.m_174023_(WitherStormModRenderers.SICKENED_CREEPER)), 0.5F);
/* 28 */     m_115326_((RenderLayer)new ChargeLayer((RenderLayerParent<SickenedCreeper, CreeperModel<SickenedCreeper>>)this, context.m_174027_()));
/* 29 */     m_115326_((RenderLayer)new EyesLayer<SickenedCreeper, CreeperModel<SickenedCreeper>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 34 */             return RenderType.m_110488_(SickenedCreeperRenderer.SICKENED_CREEPER_EMISSIVE_LOCATION);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void scale(SickenedCreeper entity, PoseStack stack, float partialTicks) {
/* 42 */     float f = entity.m_32320_(partialTicks);
/* 43 */     float f1 = 1.0F + Mth.m_14031_(f * 100.0F) * f * 0.01F;
/* 44 */     f *= f;
/* 45 */     f *= f;
/* 46 */     float f2 = (1.0F + f * 0.4F) * f1;
/* 47 */     float f3 = (1.0F + f * 0.1F) / f1;
/* 48 */     stack.m_85841_(f2, f3, f2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float getWhiteOverlayProgress(SickenedCreeper entity, float partialTicks) {
/* 54 */     float f = entity.m_32320_(partialTicks);
/* 55 */     return ((int)(f * 10.0F) % 2 == 0) ? 0.0F : Mth.m_14036_(f, 0.5F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedCreeper creeper) {
/* 61 */     return SICKENED_CREEPER_LOCATION;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isShaking(SickenedCreeper entity) {
/* 67 */     return (super.m_5936_((LivingEntity)entity) || entity.isConverting());
/*    */   }
/*    */   
/*    */   private static class ChargeLayer
/*    */     extends EnergySwirlLayer<SickenedCreeper, CreeperModel<SickenedCreeper>> {
/* 72 */     private static final ResourceLocation POWER_LOCATION = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
/*    */     
/*    */     private final CreeperModel<SickenedCreeper> model;
/*    */     
/*    */     public ChargeLayer(RenderLayerParent<SickenedCreeper, CreeperModel<SickenedCreeper>> renderer, EntityModelSet set) {
/* 77 */       super(renderer);
/* 78 */       this.model = new CreeperModel(set.m_171103_(WitherStormModRenderers.SICKENED_CREEPER_ARMOR));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected float m_7631_(float p_225634_1_) {
/* 84 */       return p_225634_1_ * 0.01F;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected ResourceLocation m_7029_() {
/* 90 */       return POWER_LOCATION;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected EntityModel<SickenedCreeper> m_7193_() {
/* 96 */       return (EntityModel<SickenedCreeper>)this.model;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedCreeperRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */