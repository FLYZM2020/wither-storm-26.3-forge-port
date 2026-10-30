/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.EntityModel;
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
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedIronGolemCrackinessLayer;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedIronGolemModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
/*    */ 
/*    */ public class SickenedIronGolemRenderer extends MobRenderer<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>> {
/* 19 */   public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_iron_golem.png");
/* 20 */   public static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_iron_golem_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedIronGolemRenderer(EntityRendererProvider.Context context) {
/* 24 */     super(context, (EntityModel)new SickenedIronGolemModel(context.bakeLayer(WitherStormModRenderers.SICKENED_IRON_GOLEM)), 0.7F);
/* 25 */     addLayer((RenderLayer)new EyesLayer<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType renderType()
/*    */           {
/* 30 */             return RenderType.eyes(SickenedIronGolemRenderer.EMISSIVE);
/*    */           }
/*    */         });
/* 33 */     addLayer((RenderLayer)new SickenedIronGolemCrackinessLayer((RenderLayerParent)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(SickenedIronGolem golem) {
/* 39 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setupRotations(SickenedIronGolem entity, PoseStack stack, float p_115016_, float p_115017_, float p_115018_) {
/* 45 */     super.setupRotations((LivingEntity)entity, stack, p_115016_, p_115017_, p_115018_);
/* 46 */     if (entity.walkAnimation.speed() >= 0.01D) {
/*    */       
/* 48 */       float f1 = entity.walkAnimation.position(p_115018_) + 6.0F;
/* 49 */       float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
/* 50 */       stack.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedIronGolemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */