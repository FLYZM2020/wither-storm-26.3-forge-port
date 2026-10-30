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
/*    */ import net.minecraft.resources.Identifier;
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
/* 24 */   private static final Identifier WITHER_SICKNESS_LAYER_64 = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/wither_sickness_layer/wither_sickness_layer.png");
/*    */   
/*    */   private final M model;
/*    */   
/*    */   public WitherSicknessLayer(RenderLayerParent<T, M> renderer) {
/* 29 */     super(renderer);
/* 30 */     this.model = (M)renderer.getModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, T entity, float p_225628_5_, float p_225628_6_, float partialTicks, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
/* 36 */     Identifier location = WITHER_SICKNESS_LAYER_64;
/* 37 */     entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
/*    */           if (!tracker.isActuallyImmune() && (tracker.isInfected() || tracker.isBeingCured()) && !entity.isInvisible()) {
/*    */             TiledTextureGenerator tiledTextureGenerator;
/*    */             M model = getModel();
/*    */             if (model instanceof VillagerHeadModel) {
/*    */               VillagerHeadModel m = (VillagerHeadModel)model;
/*    */               m.hatVisible(false);
/*    */             } 
/*    */             if (CompatHelper.isSodiumLoaded() && !ModList.get().isLoaded("embeddium")) {
/*    */               VertexConsumer consumer = buffer.getBuffer(UtilRenderTypes.entityDecalTranslucent(location));
/*    */             } else {
/*    */               tiledTextureGenerator = new TiledTextureGenerator(buffer.getBuffer(UtilRenderTypes.entityDecalTranslucent(location)), stack, 0.25F);
/*    */             } 
/*    */             float healthRatio = 0.8F - entity.getHealth() / entity.getMaxHealth();
/*    */             float alpha = tracker.getDelayTicks() / tracker.getApplicationDelay() * 0.5F * (Mth.cos((entity.tickCount + partialTicks) * healthRatio) + 2.0F) * 0.25F + 0.2F;
/*    */             if (tracker.isBeingCured())
/*    */               alpha = (tracker.getCureDelay() - tracker.getCureDelayTicks()) / tracker.getCureDelay() * 0.5F * alpha * 2.0F; 
/*    */             getModel().renderToBuffer(stack, (VertexConsumer)tiledTextureGenerator, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, alpha);
/*    */             if (model instanceof VillagerHeadModel) {
/*    */               VillagerHeadModel m = (VillagerHeadModel)model;
/*    */               m.hatVisible(true);
/*    */             } 
/*    */           } 
/* 60 */         }); } public M getModel() { return this.model; }
/*    */ 
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\WitherSicknessLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */