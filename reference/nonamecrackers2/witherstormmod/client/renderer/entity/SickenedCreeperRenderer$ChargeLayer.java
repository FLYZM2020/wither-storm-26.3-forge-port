/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.CreeperModel;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.geom.EntityModelSet;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedCreeper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ChargeLayer
/*    */   extends EnergySwirlLayer<SickenedCreeper, CreeperModel<SickenedCreeper>>
/*    */ {
/* 72 */   private static final ResourceLocation POWER_LOCATION = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
/*    */   
/*    */   private final CreeperModel<SickenedCreeper> model;
/*    */   
/*    */   public ChargeLayer(RenderLayerParent<SickenedCreeper, CreeperModel<SickenedCreeper>> renderer, EntityModelSet set) {
/* 77 */     super(renderer);
/* 78 */     this.model = new CreeperModel(set.m_171103_(WitherStormModRenderers.SICKENED_CREEPER_ARMOR));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float m_7631_(float p_225634_1_) {
/* 84 */     return p_225634_1_ * 0.01F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation m_7029_() {
/* 90 */     return POWER_LOCATION;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected EntityModel<SickenedCreeper> m_7193_() {
/* 96 */     return (EntityModel<SickenedCreeper>)this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedCreeperRenderer$ChargeLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */