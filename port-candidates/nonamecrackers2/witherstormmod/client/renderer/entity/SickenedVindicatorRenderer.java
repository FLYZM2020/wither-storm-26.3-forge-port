/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.IllagerModel;
/*    */ import net.minecraft.client.renderer.ItemInHandRenderer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.IllagerRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedVindicator;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SickenedVindicatorRenderer extends IllagerRenderer<SickenedVindicator> {
/* 21 */   public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_vindicator.png");
/* 22 */   public static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_vindicator_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedVindicatorRenderer(EntityRendererProvider.Context context) {
/* 26 */     super(context, new IllagerModel(context.bakeLayer(WitherStormModRenderers.SICKENED_VINDICATOR)), 0.5F);
/* 27 */     addLayer((RenderLayer)new ItemInHandLayer<SickenedVindicator, IllagerModel<SickenedVindicator>>((RenderLayerParent)this, context.getItemInHandRenderer()) {
/*    */           public void render(@NotNull PoseStack stack, @NotNull MultiBufferSource source, int i, @NotNull SickenedVindicator sickenedVindicator, float f1, float f2, float f3, float f4, float f5, float f6) {
/* 29 */             if (sickenedVindicator.isAggressive()) {
/* 30 */               super.render(stack, source, i, (LivingEntity)sickenedVindicator, f1, f2, f3, f4, f5, f6);
/*    */             }
/*    */           }
/*    */         });
/* 34 */     addLayer((RenderLayer)new EyesLayer<SickenedVindicator, IllagerModel<SickenedVindicator>>((RenderLayerParent)this) {
/*    */           @NotNull
/*    */           public RenderType renderType() {
/* 37 */             return RenderType.eyes(SickenedVindicatorRenderer.EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Identifier getTextureLocation(@NotNull SickenedVindicator sickenedVindicator) {
/* 45 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isShaking(SickenedVindicator entity) {
/* 51 */     return (super.isShaking((LivingEntity)entity) || entity.isConverting());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedVindicatorRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */