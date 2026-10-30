/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.SpiderModel;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.SpiderRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.monster.spider.Spider;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedSpider;
/*    */ 
/*    */ public class SickenedSpiderRenderer extends SpiderRenderer<SickenedSpider> {
/* 18 */   private static final Identifier SICKENED_SPIDER_LOCATION = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_spider.png");
/* 19 */   private static final Identifier SICKENED_SPIDER_EMISSIVE_LOCATION = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_spider_emissive.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public SickenedSpiderRenderer(EntityRendererProvider.Context context) {
/* 24 */     super(context, WitherStormModRenderers.SICKENED_SPIDER);
/* 25 */     this.layers.removeIf(l -> l instanceof net.minecraft.client.renderer.entity.layers.SpiderEyesLayer);
/* 26 */     addLayer((RenderLayer)new EyesLayer<SickenedSpider, SpiderModel<SickenedSpider>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType renderType()
/*    */           {
/* 31 */             return RenderType.eyes(SickenedSpiderRenderer.SICKENED_SPIDER_EMISSIVE_LOCATION);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void scale(SickenedSpider entity, PoseStack stack, float p_225620_3_) {
/* 45 */     stack.scale(1.2F, 1.2F, 1.2F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isShaking(SickenedSpider entity) {
/* 51 */     return (super.isShaking((LivingEntity)entity) || entity.isConverting());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(SickenedSpider entity) {
/* 57 */     return SICKENED_SPIDER_LOCATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedSpiderRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */