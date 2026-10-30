/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.HumanoidModel;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.WitheredSymbiontEyesLayer;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.WitheredSymbiontTearLayer;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitheredSymbiontModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ 
/*    */ public class WitheredSymbiontRenderer extends MobRenderer<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>> {
/* 21 */   private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/withered_symbiont/withered_symbiont.png");
/* 22 */   private static final Identifier EGG_TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/misc/crackers.png");
/*    */ 
/*    */   
/*    */   public WitheredSymbiontRenderer(EntityRendererProvider.Context context) {
/* 26 */     super(context, (EntityModel)new WitheredSymbiontModel(context.bakeLayer(WitherStormModRenderers.WITHERED_SYMBIONT)), 0.8F);
/* 27 */     addLayer((RenderLayer)new WitheredSymbiontEyesLayer((RenderLayerParent)this));
/* 28 */     addLayer((RenderLayer)new WitheredSymbiontTearLayer((RenderLayerParent)this));
/* 29 */     addLayer((RenderLayer)new ItemInHandLayer((RenderLayerParent)this, context.getItemInHandRenderer()));
/* 30 */     addLayer((RenderLayer)new HumanoidArmorLayer((RenderLayerParent)this, new HumanoidModel(context.bakeLayer(WitherStormModRenderers.SYMBIONT_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(WitherStormModRenderers.SYMBIONT_OUTER_ARMOR)), context.getModelManager()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(WitheredSymbiontEntity entity) {
/* 36 */     if (entity.hasCustomName() && entity.getName().getString().equals("nonamecrackers2"))
/* 37 */       return EGG_TEXTURE; 
/* 38 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setupRotations(WitheredSymbiontEntity entity, PoseStack stack, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
/* 44 */     super.setupRotations((LivingEntity)entity, stack, p_225621_3_, p_225621_4_, p_225621_5_);
/* 45 */     if (entity.walkAnimation.speed() >= 0.01D) {
/*    */       
/* 47 */       float f1 = entity.walkAnimation.position(p_225621_5_) + 6.0F;
/* 48 */       float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
/* 49 */       stack.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void scale(WitheredSymbiontEntity entity, PoseStack stack, float partialTicks) {
/* 56 */     stack.scale(1.8F, 1.8F, 1.8F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\WitheredSymbiontRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */