/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ import net.minecraft.util.Mth;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.SingleHeadWitherStormModel;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.WSHunchback2_1;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ public class WitherStormHunchback2_1Model<T extends WitherStormEntity>
/*    */   extends SingleHeadWitherStormModel<T>
/*    */ {
/*    */   private final ModelPart base;
/*    */   private final ModelPart rightHead;
/*    */   private final ModelPart leftHead;
/*    */   private final ModelPart ribcage;
/*    */   
/*    */   public WitherStormHunchback2_1Model(ModelPart root) {
/* 26 */     super(root, 0.7F);
/*    */     
/* 28 */     this.base = root.m_171324_("witherBase");
/* 29 */     this.ribcage = this.base.m_171324_("ribcage");
/* 30 */     this.rightHead = this.base.m_171324_("right_head");
/* 31 */     this.leftHead = this.base.m_171324_("left_head");
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition(CubeDeformation def) {
/* 36 */     MeshDefinition mesh = WitherStormCommandBlockModel.createBaseMesh(SingleHeadWitherStormModel.createMesh(PartPose.f_171404_), def, false, true, false);
/* 37 */     PartDefinition root = mesh.m_171576_();
/* 38 */     WSHunchback2_1.createBodyModel(root, 1.0F);
/* 39 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void configureTentacles(ModelPart root) {}
/*    */ 
/*    */   
/*    */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
/* 48 */     super.setupAnimations((WitherStormEntity)entity, partialTicks, tickCount, yRot, xRot);
/*    */     
/* 50 */     float f = Mth.m_14089_(tickCount * 0.1F);
/* 51 */     this.ribcage.f_104203_ = (0.065F + 0.05F * f) * 3.1415927F;
/* 52 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.leftHead, 1, partialTicks);
/* 53 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.rightHead, 2, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 59 */     this.base.m_104306_(stack, consumer, packedLight, overlayTexture, r, g, b, a);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void scaleMass(PoseStack stack) {
/* 65 */     stack.m_85841_(1.001F, 1.001F, 1.001F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormHunchback2_1Model.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */