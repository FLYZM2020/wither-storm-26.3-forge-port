/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.EntityModel;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.part.TentaclePartEntity;
/*     */ 
/*     */ 
/*     */ public class TentacleModel
/*     */   extends EntityModel<TentacleEntity>
/*     */ {
/*     */   private final List<ModelPart> segments;
/*     */   private final ModelPart base;
/*     */   private final ModelPart segment;
/*     */   private final ModelPart segment2;
/*     */   private final ModelPart segment3;
/*     */   private final ModelPart segment4;
/*     */   private final ModelPart segment5;
/*     */   
/*     */   public TentacleModel(ModelPart root) {
/*  32 */     this.base = root;
/*  33 */     this.segment = this.base.m_171324_("segment");
/*  34 */     this.segment2 = this.segment.m_171324_("segment2");
/*  35 */     this.segment3 = this.segment2.m_171324_("segment3");
/*  36 */     this.segment4 = this.segment3.m_171324_("segment4");
/*  37 */     this.segment5 = this.segment4.m_171324_("segment5");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.segments = Lists.newArrayList((Object[])new ModelPart[] { this.segment, this.segment2, this.segment3, this.segment4, this.segment5 });
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition() {
/*  74 */     MeshDefinition mesh = new MeshDefinition();
/*  75 */     PartDefinition base = mesh.m_171576_();
/*  76 */     PartDefinition segment = base.m_171599_("segment", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171506_(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, false), PartPose.f_171404_);
/*  77 */     PartDefinition segment2 = segment.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171506_(-5.0F, -16.0F, -5.0F, 10.0F, 16.0F, 10.0F, false), PartPose.m_171419_(0.0F, -12.0F, 0.0F));
/*  78 */     PartDefinition segment3 = segment2.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(40, 16).m_171506_(-4.0F, -20.0F, -4.0F, 8.0F, 20.0F, 8.0F, false), PartPose.m_171419_(0.0F, -16.0F, 0.0F));
/*  79 */     PartDefinition segment4 = segment3.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(34, 44).m_171506_(-3.0F, -24.0F, -3.0F, 6.0F, 24.0F, 6.0F, false), PartPose.m_171419_(0.0F, -20.0F, 0.0F));
/*  80 */     segment4.m_171599_("segment5", CubeListBuilder.m_171558_().m_171514_(0, 50).m_171506_(-2.0F, -24.0F, -2.0F, 4.0F, 24.0F, 4.0F, false), PartPose.m_171419_(0.0F, -24.0F, 0.0F));
/*  81 */     return LayerDefinition.m_171565_(mesh, 128, 128);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(TentacleEntity entity, float partialTicks, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
/*  87 */     float offset = 90.0F;
/*  88 */     TentaclePartEntity<TentacleEntity> part = entity.getTentacle();
/*  89 */     List<TentaclePartEntity<TentacleEntity>> chained = Lists.newArrayList((Object[])new TentaclePartEntity[] { part });
/*  90 */     chained.addAll(part.getChained());
/*  91 */     TentaclePartEntity<TentacleEntity> previous = null;
/*  92 */     for (int j = 0; j < this.segments.size(); j++) {
/*     */       
/*  94 */       TentaclePartEntity<TentacleEntity> chain = chained.get(j);
/*  95 */       float prevXRot = 0.0F;
/*  96 */       float prevYRot = 0.0F;
/*  97 */       if (previous != null) {
/*     */         
/*  99 */         prevXRot = Mth.m_14179_(partialTicks, previous.f_19860_, previous.m_146909_()) + offset;
/* 100 */         prevYRot = Mth.m_14179_(partialTicks, previous.f_19859_, previous.m_146908_());
/*     */       } 
/* 102 */       ModelPart segment = this.segments.get(j);
/* 103 */       segment.f_104203_ = -(Mth.m_14179_(partialTicks, chain.f_19860_, chain.m_146909_()) - prevXRot + offset) * 0.017453292F;
/* 104 */       segment.f_104204_ = (Mth.m_14179_(partialTicks, chain.f_19859_, chain.m_146908_()) - prevYRot) * 0.017453292F;
/* 105 */       previous = chain;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7695_(PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 112 */     this.base.m_104301_(stack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\TentacleModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */