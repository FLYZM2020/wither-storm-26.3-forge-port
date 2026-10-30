/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.LivingEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*    */ import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class AbsorbtionLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends RenderLayer<T, M>
/*    */ {
/* 21 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/misc/absorbtion.png");
/* 22 */   private static final RenderType RENDER_TYPE = UtilRenderTypes.entityDecalTranslucent(TEXTURE);
/*    */ 
/*    */   
/*    */   public AbsorbtionLayer(RenderLayerParent<T, M> parent) {
/* 26 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(PoseStack stack, MultiBufferSource buffer, int p_117351_, T entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
/* 32 */     if (!entity.m_20145_()) {
/*    */       
/* 34 */       WitherStormEntity storm = (WitherStormEntity)entity.m_9236_().m_45963_(WitherStormEntity.class, TargetingConditions.m_148353_(), null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity.m_20191_().m_82400_(50.0D));
/* 35 */       if (storm != null && storm.getPhase() > 3 && !storm.isDeadOrPlayingDead()) {
/*    */         
/* 37 */         float alpha = Mth.m_14036_((30.0F - (float)storm.m_146892_().m_82554_(entity.m_146892_())) * 0.1F, 0.0F, 0.9F);
/* 38 */         if (alpha > 0.0F) {
/* 39 */           renderOverlay(stack, buffer, p_117351_, entity, alpha);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void renderOverlay(PoseStack stack, MultiBufferSource buffer, int packedLight, T entity, float alpha) {
/* 46 */     m_117386_().m_7695_(stack, buffer.m_6299_(RENDER_TYPE), packedLight, LivingEntityRenderer.m_115338_((LivingEntity)entity, 0.0F), 1.0F, 1.0F, 1.0F, alpha);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\AbsorbtionLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */