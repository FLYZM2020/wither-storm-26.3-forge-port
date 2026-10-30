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
/* 28 */     this.base = root.getChild("witherBase");
/* 29 */     this.ribcage = this.base.getChild("ribcage");
/* 30 */     this.tail = this.base.getChild("tail");
/* 31 */     this.rightHead = this.base.getChild("right_head");
/* 32 */     this.leftHead = this.base.getChild("left_head");
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition(CubeDeformation def) {
/* 37 */     MeshDefinition mesh = WitherStormCommandBlockModel.createBaseMesh(SingleHeadWitherStormModel.createMesh(PartPose.ZERO), def, false, true, true);
/* 38 */     GrowingHunchbackMassModel.createMassModel(mesh.getRoot(), 1.0F);
/* 39 */     return LayerDefinition.create(mesh, 160, 160);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void configureTentacles(ModelPart root) {}
/*    */ 
/*    */   
/*    */   protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 48 */     this.base.render(stack, consumer, packedLight, overlayTexture, r, g, b, a);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
/* 54 */     super.setupAnimations((WitherStormEntity)entity, partialTicks, tickCount, yRot, xRot);
/*    */     
/* 56 */     float f = Mth.cos(tickCount * 0.1F);
/* 57 */     this.ribcage.xRot = (0.065F + 0.05F * f) * 3.1415927F;
/* 58 */     this.tail.setPos(-2.0F, 6.9F + Mth.cos(this.ribcage.xRot) * 10.0F, -0.5F + Mth.sin(this.ribcage.xRot) * 10.0F);
/* 59 */     this.tail.xRot = (0.265F + 0.1F * f) * 3.1415927F;
/* 60 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.leftHead, 1, partialTicks);
/* 61 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.rightHead, 2, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void scaleMass(PoseStack stack) {
/* 67 */     stack.scale(1.001F, 1.001F, 1.001F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormGrowingHunchbackModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */