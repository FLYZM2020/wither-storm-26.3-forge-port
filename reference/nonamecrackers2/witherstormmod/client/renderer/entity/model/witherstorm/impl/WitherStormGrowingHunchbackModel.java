/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.util.Mth;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.SingleHeadWitherStormModel;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.GrowingHunchbackMassModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ public class WitherStormGrowingHunchbackModel<T extends WitherStormEntity>
/*    */   extends SingleHeadWitherStormModel<T>
/*    */ {
/*    */   private final ModelPart base;
/*    */   private final ModelPart rightHead;
/*    */   private final ModelPart leftHead;
/*    */   private final ModelPart ribcage;
/*    */   private final ModelPart tail;
/*    */   
/*    */   public WitherStormGrowingHunchbackModel(ModelPart root) {
/* 26 */     super(root, 0.7F);
/*    */     
/* 28 */     this.base = root.m_171324_("witherBase");
/* 29 */     this.ribcage = this.base.m_171324_("ribcage");
/* 30 */     this.tail = this.base.m_171324_("tail");
/* 31 */     this.rightHead = this.base.m_171324_("right_head");
/* 32 */     this.leftHead = this.base.m_171324_("left_head");
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition(CubeDeformation def) {
/* 37 */     MeshDefinition mesh = WitherStormCommandBlockModel.createBaseMesh(SingleHeadWitherStormModel.createMesh(PartPose.f_171404_), def, false, true, true);
/* 38 */     GrowingHunchbackMassModel.createMassModel(mesh.m_171576_(), 1.0F);
/* 39 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void configureTentacles(ModelPart root) {}
/*    */ 
/*    */   
/*    */   protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 48 */     this.base.m_104306_(stack, consumer, packedLight, overlayTexture, r, g, b, a);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
/* 54 */     super.setupAnimations((WitherStormEntity)entity, partialTicks, tickCount, yRot, xRot);
/*    */     
/* 56 */     float f = Mth.m_14089_(tickCount * 0.1F);
/* 57 */     this.ribcage.f_104203_ = (0.065F + 0.05F * f) * 3.1415927F;
/* 58 */     this.tail.m_104227_(-2.0F, 6.9F + Mth.m_14089_(this.ribcage.f_104203_) * 10.0F, -0.5F + Mth.m_14031_(this.ribcage.f_104203_) * 10.0F);
/* 59 */     this.tail.f_104203_ = (0.265F + 0.1F * f) * 3.1415927F;
/* 60 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.leftHead, 1, partialTicks);
/* 61 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.rightHead, 2, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void scaleMass(PoseStack stack) {
/* 67 */     stack.m_85841_(1.001F, 1.001F, 1.001F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormGrowingHunchbackModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */