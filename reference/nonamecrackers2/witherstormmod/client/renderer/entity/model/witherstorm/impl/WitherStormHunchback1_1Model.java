/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.util.Mth;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.WSHunchback1_1;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ public class WitherStormHunchback1_1Model<T extends WitherStormEntity>
/*    */   extends AbstractWitherStormModel<T>
/*    */ {
/*    */   private final ModelPart base;
/*    */   private final ModelPart centerHead;
/*    */   private final ModelPart rightHead;
/*    */   private final ModelPart leftHead;
/*    */   private final ModelPart ribcage;
/*    */   private final ModelPart tail;
/*    */   
/*    */   public WitherStormHunchback1_1Model(ModelPart root) {
/* 26 */     super(root, 1.0F);
/*    */     
/* 28 */     this.base = root.m_171324_("witherBase");
/* 29 */     this.ribcage = this.base.m_171324_("ribcage");
/* 30 */     this.tail = this.base.m_171324_("tail");
/* 31 */     this.centerHead = this.base.m_171324_("center_head");
/* 32 */     this.rightHead = this.base.m_171324_("right_head");
/* 33 */     this.leftHead = this.base.m_171324_("left_head");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void configureHeads(ModelPart root, float scale) {}
/*    */ 
/*    */   
/*    */   protected void configureTentacles(ModelPart root) {}
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition(CubeDeformation def) {
/* 44 */     MeshDefinition mesh = WitherStormCommandBlockModel.createBaseMesh(AbstractWitherStormModel.createMesh(), def, true, true, true);
/* 45 */     WSHunchback1_1.createBodyModel(mesh.m_171576_(), 1.0F);
/* 46 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
/* 52 */     super.setupAnimations((WitherStormEntity)entity, partialTicks, tickCount, yRot, xRot);
/*    */     
/* 54 */     float f = Mth.m_14089_(tickCount * 0.1F);
/* 55 */     this.ribcage.f_104203_ = (0.065F + 0.05F * f) * 3.1415927F;
/* 56 */     this.tail.m_104227_(-2.0F, 6.9F + Mth.m_14089_(this.ribcage.f_104203_) * 10.0F, -0.5F + Mth.m_14031_(this.ribcage.f_104203_) * 10.0F);
/* 57 */     this.tail.f_104203_ = (0.265F + 0.1F * f) * 3.1415927F;
/* 58 */     this.centerHead.f_104204_ = yRot * 0.017453292F;
/* 59 */     this.centerHead.f_104203_ = xRot * 0.017453292F;
/* 60 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.leftHead, 1, partialTicks);
/* 61 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.rightHead, 2, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 67 */     this.base.m_104306_(stack, consumer, packedLight, overlayTexture, r, g, b, a);
/*    */   }
/*    */   
/*    */   public void scaleMass(PoseStack stack) {}
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormHunchback1_1Model.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */