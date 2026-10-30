/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.VillagerHeadModel;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.fml.ModList;
/*    */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*    */ import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
/*    */ import nonamecrackers2.witherstormmod.client.util.TiledTextureGenerator;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*    */ 
/*    */ public class WitherSicknessLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends RenderLayer<T, M> {
/* 24 */   private static final ResourceLocation WITHER_SICKNESS_LAYER_64 = new ResourceLocation("witherstormmod", "textures/entity/wither_sickness_layer/wither_sickness_layer.png");
/*    */   
/*    */   private final M model;
/*    */   
/*    */   public WitherSicknessLayer(RenderLayerParent<T, M> renderer) {
/* 29 */     super(renderer);
/* 30 */     this.model = (M)renderer.m_7200_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, T entity, float p_225628_5_, float p_225628_6_, float partialTicks, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
/* 36 */     ResourceLocation location = WITHER_SICKNESS_LAYER_64;
/* 37 */     entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
/*    */           if (!tracker.isActuallyImmune() && (tracker.isInfected() || tracker.isBeingCured()) && !entity.m_20145_()) {
/*    */             TiledTextureGenerator tiledTextureGenerator;
/*    */             M model = getModel();
/*    */             if (model instanceof VillagerHeadModel) {
/*    */               VillagerHeadModel m = (VillagerHeadModel)model;
/*    */               m.m_7491_(false);
/*    */             } 
/*    */             if (CompatHelper.isSodiumLoaded() && !ModList.get().isLoaded("embeddium")) {
/*    */               VertexConsumer consumer = buffer.m_6299_(UtilRenderTypes.entityDecalTranslucent(location));
/*    */             } else {
/*    */               tiledTextureGenerator = new TiledTextureGenerator(buffer.m_6299_(UtilRenderTypes.entityDecalTranslucent(location)), stack, 0.25F);
/*    */             } 
/*    */             float healthRatio = 0.8F - entity.m_21223_() / entity.m_21233_();
/*    */             float alpha = tracker.getDelayTicks() / tracker.getApplicationDelay() * 0.5F * (Mth.m_14089_((entity.f_19797_ + partialTicks) * healthRatio) + 2.0F) * 0.25F + 0.2F;
/*    */             if (tracker.isBeingCured())
/*    */               alpha = (tracker.getCureDelay() - tracker.getCureDelayTicks()) / tracker.getCureDelay() * 0.5F * alpha * 2.0F; 
/*    */             getModel().m_7695_(stack, (VertexConsumer)tiledTextureGenerator, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, alpha);
/*    */             if (model instanceof VillagerHeadModel) {
/*    */               VillagerHeadModel m = (VillagerHeadModel)model;
/*    */               m.m_7491_(true);
/*    */             } 
/*    */           } 
/* 60 */         }); } public M getModel() { return this.model; }
/*    */ 
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\WitherSicknessLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */