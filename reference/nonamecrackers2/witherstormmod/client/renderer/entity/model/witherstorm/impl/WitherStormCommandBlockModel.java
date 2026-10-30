/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.util.Mth;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ public class WitherStormCommandBlockModel<T extends WitherStormEntity>
/*     */   extends AbstractWitherStormModel<T>
/*     */ {
/*     */   private final ModelPart base;
/*     */   private final ModelPart centerHead;
/*     */   private final ModelPart rightHead;
/*     */   private final ModelPart leftHead;
/*     */   private final ModelPart ribcage;
/*     */   private final ModelPart tail;
/*     */   
/*     */   public WitherStormCommandBlockModel(ModelPart root) {
/*  28 */     super(root, 1.0F);
/*     */     
/*  30 */     this.base = root.m_171324_("witherBase");
/*  31 */     this.ribcage = this.base.m_171324_("ribcage");
/*  32 */     this.tail = this.base.m_171324_("tail");
/*  33 */     this.centerHead = this.base.m_171324_("center_head");
/*  34 */     this.rightHead = this.base.m_171324_("right_head");
/*  35 */     this.leftHead = this.base.m_171324_("left_head");
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition(CubeDeformation def) {
/*  40 */     return LayerDefinition.m_171565_(createBaseMesh(AbstractWitherStormModel.createMesh(), def, true, true, true), 160, 160);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MeshDefinition createBaseMesh(MeshDefinition mesh, CubeDeformation def, boolean hasCenterHead, boolean hasRibcageExtension, boolean hasTail) {
/*  45 */     PartDefinition base = mesh.m_171576_().m_171599_("witherBase", CubeListBuilder.m_171558_(), PartPose.f_171404_);
/*  46 */     base.m_171599_("shoulders", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171488_(-10.0F, 3.9F, -0.5F, 20.0F, 3.0F, 3.0F, def), PartPose.f_171404_);
/*  47 */     PartDefinition ribcage = base.m_171599_("ribcage", CubeListBuilder.m_171558_()
/*  48 */         .m_171514_(0, 22).m_171488_(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, def)
/*  49 */         .m_171514_(24, 22).m_171488_(-4.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, def)
/*  50 */         .m_171514_(24, 22).m_171488_(-4.0F, 4.5F, 0.5F, 11.0F, 2.0F, 2.0F, def)
/*  51 */         .m_171514_(24, 22).m_171488_(-4.0F, 7.5F, 0.5F, 11.0F, 2.0F, 2.0F, def), 
/*  52 */         PartPose.m_171423_(-2.0F, 6.9F, -0.5F, 0.20420352F, 0.0F, 0.0F));
/*  53 */     if (hasRibcageExtension) {
/*     */       
/*  55 */       PartDefinition ribcageExtension = ribcage.m_171599_("ribcageExtension", CubeListBuilder.m_171558_().m_171514_(128, 40).m_171488_(-5.5F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  56 */           .m_171514_(128, 40).m_171488_(-5.5F, -5.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  57 */           .m_171514_(128, 40).m_171488_(-5.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  58 */           .m_171514_(128, 40).m_171488_(3.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  59 */           .m_171514_(128, 40).m_171488_(3.5F, -5.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  60 */           .m_171514_(128, 40).m_171488_(3.5F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  61 */           .m_171514_(140, 44).m_171488_(2.5F, -2.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  62 */           .m_171514_(140, 44).m_171488_(2.5F, -5.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  63 */           .m_171514_(140, 44).m_171488_(2.5F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  64 */           .m_171514_(140, 44).m_171488_(-3.5F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  65 */           .m_171514_(140, 44).m_171488_(-3.5F, -5.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  66 */           .m_171514_(140, 44).m_171488_(-3.5F, -2.0F, -4.0F, 1.0F, 2.0F, 2.0F, def), 
/*  67 */           PartPose.m_171419_(1.5F, 9.5F, -3.5F));
/*  68 */       ribcageExtension.m_171599_("block", CubeListBuilder.m_171558_().m_171514_(48, 0).m_171496_(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, def.m_171469_(0.001F), 0.5F, 0.5F), PartPose.f_171404_);
/*     */     } 
/*  70 */     if (hasTail) {
/*  71 */       base.m_171599_("tail", CubeListBuilder.m_171558_().m_171514_(12, 22).m_171488_(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, def), PartPose.m_171423_(-2.0F, 6.9F + Mth.m_14089_(0.20420352F) * 10.0F, -0.5F + Mth.m_14031_(0.20420352F) * 10.0F, 0.83252203F, 0.0F, 0.0F));
/*     */     }
/*  73 */     if (hasCenterHead)
/*  74 */       base.m_171599_("center_head", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, def), PartPose.f_171404_); 
/*  75 */     CubeListBuilder sideHeads = CubeListBuilder.m_171558_().m_171514_(32, 0).m_171488_(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, def);
/*  76 */     base.m_171599_("right_head", sideHeads, PartPose.m_171419_(-8.0F, 4.0F, 0.0F));
/*  77 */     base.m_171599_("left_head", sideHeads, PartPose.m_171419_(10.0F, 4.0F, 0.0F));
/*  78 */     return mesh;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureHeads(ModelPart root, float scale) {}
/*     */ 
/*     */   
/*     */   protected void configureTentacles(ModelPart root) {}
/*     */ 
/*     */   
/*     */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
/*  90 */     super.setupAnimations((WitherStormEntity)entity, partialTicks, tickCount, yRot, xRot);
/*     */     
/*  92 */     float f = Mth.m_14089_(tickCount * 0.1F);
/*  93 */     this.ribcage.f_104203_ = (0.065F + 0.05F * f) * 3.1415927F;
/*  94 */     this.tail.m_104227_(-2.0F, 6.9F + Mth.m_14089_(this.ribcage.f_104203_) * 10.0F, -0.5F + Mth.m_14031_(this.ribcage.f_104203_) * 10.0F);
/*  95 */     this.tail.f_104203_ = (0.265F + 0.1F * f) * 3.1415927F;
/*  96 */     this.centerHead.f_104204_ = yRot * 0.017453292F;
/*  97 */     this.centerHead.f_104203_ = xRot * 0.017453292F;
/*  98 */     setupHeadRotation((WitherStormEntity)entity, this.leftHead, 1, partialTicks);
/*  99 */     setupHeadRotation((WitherStormEntity)entity, this.rightHead, 2, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 105 */     this.base.m_104306_(stack, consumer, packedLight, overlayTexture, r, g, b, a);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setupHeadRotation(WitherStormEntity storm, ModelPart headModel, int head, float partialTick) {
/* 110 */     headModel.f_104204_ = (Mth.m_14179_(partialTick, storm.getHeadYRotO(head), storm.getHeadYRot(head)) - Mth.m_14179_(partialTick, storm.f_20884_, storm.f_20883_)) * 0.017453292F;
/* 111 */     headModel.f_104203_ = Mth.m_14179_(partialTick, storm.getHeadXRotO(head), storm.getHeadXRot(head)) * 0.017453292F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormCommandBlockModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */