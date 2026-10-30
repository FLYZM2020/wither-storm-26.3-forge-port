/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.ChickenModel;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedChicken;
/*    */ 
/*    */ public class SickenedChickenRenderer extends MobRenderer<SickenedChicken, ChickenModel<SickenedChicken>> {
/* 16 */   public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_chicken.png");
/* 17 */   public static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_chicken_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedChickenRenderer(EntityRendererProvider.Context context) {
/* 21 */     super(context, (EntityModel)new ChickenModel(context.bakeLayer(WitherStormModRenderers.SICKENED_CHICKEN)), 0.3F);
/* 22 */     addLayer((RenderLayer)new EyesLayer<SickenedChicken, ChickenModel<SickenedChicken>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType renderType()
/*    */           {
/* 27 */             return RenderType.eyes(SickenedChickenRenderer.EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(SickenedChicken chicken) {
/* 35 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getBob(SickenedChicken chicken, float partialTicks) {
/* 40 */     float f = Mth.lerp(partialTicks, chicken.oFlap, chicken.flap);
/* 41 */     float f1 = Mth.lerp(partialTicks, chicken.oFlapSpeed, chicken.flapSpeed);
/* 42 */     return (Mth.sin(f) + 1.0F) * f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedChickenRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */