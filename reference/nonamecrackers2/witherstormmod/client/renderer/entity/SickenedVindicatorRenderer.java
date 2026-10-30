/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.IllagerModel;
/*    */ import net.minecraft.client.renderer.ItemInHandRenderer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.IllagerRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedVindicator;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SickenedVindicatorRenderer extends IllagerRenderer<SickenedVindicator> {
/* 21 */   public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_vindicator.png");
/* 22 */   public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_vindicator_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedVindicatorRenderer(EntityRendererProvider.Context context) {
/* 26 */     super(context, new IllagerModel(context.m_174023_(WitherStormModRenderers.SICKENED_VINDICATOR)), 0.5F);
/* 27 */     m_115326_((RenderLayer)new ItemInHandLayer<SickenedVindicator, IllagerModel<SickenedVindicator>>((RenderLayerParent)this, context.m_234598_()) {
/*    */           public void render(@NotNull PoseStack stack, @NotNull MultiBufferSource source, int i, @NotNull SickenedVindicator sickenedVindicator, float f1, float f2, float f3, float f4, float f5, float f6) {
/* 29 */             if (sickenedVindicator.m_5912_()) {
/* 30 */               super.m_6494_(stack, source, i, (LivingEntity)sickenedVindicator, f1, f2, f3, f4, f5, f6);
/*    */             }
/*    */           }
/*    */         });
/* 34 */     m_115326_((RenderLayer)new EyesLayer<SickenedVindicator, IllagerModel<SickenedVindicator>>((RenderLayerParent)this) {
/*    */           @NotNull
/*    */           public RenderType m_5708_() {
/* 37 */             return RenderType.m_110488_(SickenedVindicatorRenderer.EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ResourceLocation getTextureLocation(@NotNull SickenedVindicator sickenedVindicator) {
/* 45 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isShaking(SickenedVindicator entity) {
/* 51 */     return (super.m_5936_((LivingEntity)entity) || entity.isConverting());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedVindicatorRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */