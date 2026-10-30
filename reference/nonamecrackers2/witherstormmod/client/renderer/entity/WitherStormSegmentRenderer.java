/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormSegmentModel;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
/*    */ 
/*    */ public class WitherStormSegmentRenderer
/*    */   extends AbstractWitherStormRenderer<WitherStormSegmentEntity, AbstractWitherStormModel<WitherStormSegmentEntity>> {
/*    */   private final WitherStormSegmentModel<WitherStormSegmentEntity> segmentModel;
/*    */   
/*    */   public WitherStormSegmentRenderer(EntityRendererProvider.Context context) {
/* 16 */     this(context, new WitherStormSegmentModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_SEGMENT)));
/*    */   }
/*    */ 
/*    */   
/*    */   private WitherStormSegmentRenderer(EntityRendererProvider.Context context, WitherStormSegmentModel<WitherStormSegmentEntity> base) {
/* 21 */     super(context, base);
/* 22 */     this.segmentModel = base;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractWitherStormModel<WitherStormSegmentEntity> fetchModel(WitherStormSegmentEntity entity) {
/* 28 */     return (AbstractWitherStormModel<WitherStormSegmentEntity>)this.segmentModel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPulseAmount(WitherStormSegmentEntity entity) {
/* 34 */     return (int)(entity.getPhase() * 5.0F / 2.0F * (((Boolean)WitherStormModConfig.CLIENT.lowResModels.get()).booleanValue() ? 3.0F : 1.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\WitherStormSegmentRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */