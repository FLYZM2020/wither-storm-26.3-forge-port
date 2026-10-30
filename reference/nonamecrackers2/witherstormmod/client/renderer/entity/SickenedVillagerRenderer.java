/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.HumanoidModel;
/*    */ import net.minecraft.client.model.ZombieVillagerModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedVillager;
/*    */ 
/*    */ public class SickenedVillagerRenderer extends HumanoidMobRenderer<SickenedVillager, ZombieVillagerModel<SickenedVillager>> {
/* 17 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_villager.png");
/* 18 */   private static final ResourceLocation TEXTURE_EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_villager_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedVillagerRenderer(EntityRendererProvider.Context context) {
/* 22 */     super(context, (HumanoidModel)new ZombieVillagerModel(context.m_174023_(WitherStormModRenderers.SICKENED_VILLAGER)), 0.5F);
/* 23 */     m_115326_((RenderLayer)new HumanoidArmorLayer((RenderLayerParent)this, (HumanoidModel)new ZombieVillagerModel(context.m_174023_(WitherStormModRenderers.SICKENED_VILLAGER_INNER_ARMOR)), (HumanoidModel)new ZombieVillagerModel(context.m_174023_(WitherStormModRenderers.SICKENED_VILLAGER_OUTER_ARMOR)), context.m_266367_()));
/* 24 */     m_115326_((RenderLayer)new VillagerProfessionLayer((RenderLayerParent)this, context.m_174026_(), "zombie_villager"));
/* 25 */     m_115326_((RenderLayer)new EyesLayer<SickenedVillager, ZombieVillagerModel<SickenedVillager>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 30 */             return RenderType.m_110488_(SickenedVillagerRenderer.TEXTURE_EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SickenedVillager entity) {
/* 38 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isShaking(SickenedVillager entity) {
/* 44 */     return (super.m_5936_((LivingEntity)entity) || entity.isConverting());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedVillagerRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */