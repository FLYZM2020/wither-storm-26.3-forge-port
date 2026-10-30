/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedIronGolemCrackinessLayer;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedIronGolemModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
/*    */ 
/*    */ public class SickenedIronGolemRenderer extends MobRenderer<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>> {
/* 19 */   public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_iron_golem.png");
/* 20 */   public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_iron_golem_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedIronGolemRenderer(EntityRendererProvider.Context context) {
/* 24 */     super(context, (EntityModel)new SickenedIronGolemModel(context.m_174023_(WitherStormModRenderers.SICKENED_IRON_GOLEM)), 0.7F);
/* 25 */     m_115326_((RenderLayer)new EyesLayer<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 30 */             return RenderType.m_110488_(SickenedIronGolemRenderer.EMISSIVE);
/*    */           }
/*    */         });
/* 33 */     m_115326_((RenderLayer)new SickenedIronGolemCrackinessLayer((RenderLayerParent)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedIronGolem golem) {
/* 39 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setupRotations(SickenedIronGolem entity, PoseStack stack, float p_115016_, float p_115017_, float p_115018_) {
/* 45 */     super.m_7523_((LivingEntity)entity, stack, p_115016_, p_115017_, p_115018_);
/* 46 */     if (entity.f_267362_.m_267731_() >= 0.01D) {
/*    */       
/* 48 */       float f1 = entity.f_267362_.m_267590_(p_115018_) + 6.0F;
/* 49 */       float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
/* 50 */       stack.m_252781_(Axis.f_252403_.m_252977_(6.5F * f2));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedIronGolemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */