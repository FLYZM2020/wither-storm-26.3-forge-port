/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.PhantomModel;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedPhantom;
/*    */ 
/*    */ public class SickenedPhantomRenderer extends MobRenderer<SickenedPhantom, PhantomModel<SickenedPhantom>> {
/* 18 */   private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_phantom.png");
/* 19 */   private static final Identifier TEXTURE_EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_phantom_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedPhantomRenderer(EntityRendererProvider.Context context) {
/* 23 */     super(context, (EntityModel)new PhantomModel(context.bakeLayer(WitherStormModRenderers.SICKENED_PHANTOM)), 0.75F);
/* 24 */     addLayer((RenderLayer)new EyesLayer<SickenedPhantom, PhantomModel<SickenedPhantom>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType renderType()
/*    */           {
/* 29 */             return RenderType.eyes(SickenedPhantomRenderer.TEXTURE_EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(SickenedPhantom phantom) {
/* 37 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void scale(SickenedPhantom phantom, PoseStack stack, float partialTicks) {
/* 42 */     int i = phantom.getPhantomSize();
/* 43 */     float f = 1.0F + 0.15F * i;
/* 44 */     stack.scale(f, f, f);
/* 45 */     stack.translate(0.0F, 1.3125F, 0.1875F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setupRotations(SickenedPhantom phantom, PoseStack stack, float p_115687_, float p_115688_, float p_115689_) {
/* 50 */     super.setupRotations((LivingEntity)phantom, stack, p_115687_, p_115688_, p_115689_);
/* 51 */     stack.mulPose(Axis.XP.rotationDegrees(phantom.getXRot()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedPhantomRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */