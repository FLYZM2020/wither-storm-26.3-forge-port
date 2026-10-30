/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.TentacleModel;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.ThreeHeadedWitherStormModel;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.LowResTornEvolvedDevourerBodyModel;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.TornEvolvedDevourerBodyModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import org.joml.Matrix3f;
/*    */ import org.joml.Matrix4f;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormTornEvolvedDevourerModel<T extends WitherStormEntity>
/*    */   extends WitherStormEvolvedDevourerModel<T>
/*    */ {
/*    */   public WitherStormTornEvolvedDevourerModel(ModelPart root) {
/* 28 */     super(root);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition() {
/* 33 */     MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[] { PartPose.offset(-22.0F, -65.0F, -40.0F), PartPose.offset(0.0F, -32.0F, -23.0F), PartPose.offset(32.0F, -60.0F, -24.0F) });
/* 34 */     PartDefinition root = mesh.getRoot();
/* 35 */     TornEvolvedDevourerBodyModel.createBodyModel(root, 0.2F);
/* 36 */     LowResTornEvolvedDevourerBodyModel.createBodyModel(root, 0.3F);
/* 37 */     PartDefinition tentacles = root.getChild("tentacles");
/* 38 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacle0", CubeListBuilder.create(), PartPose.ZERO), new int[] { 18, 24, 24, 28, 28, 32 }, PartPose.offset(-20.0F, -25.0F, 5.0F));
/* 39 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacle1", CubeListBuilder.create(), PartPose.ZERO), new int[] { 18, 24, 24, 24, 28, 38 }, PartPose.offset(20.0F, -27.5F, 7.0F));
/* 40 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacle2", CubeListBuilder.create(), PartPose.ZERO), new int[] { 18, 24, 24, 28, 32, 28 }, PartPose.offset(-10.0F, -30.0F, -10.0F));
/* 41 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacle3", CubeListBuilder.create(), PartPose.ZERO), new int[] { 18, 18, 24, 24, 24, 28 }, PartPose.offset(8.0F, -34.0F, -6.0F));
/* 42 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacle4", CubeListBuilder.create(), PartPose.ZERO), new int[] { 18, 18, 24, 28, 32, 32 }, PartPose.offset(-8.0F, -25.0F, 16.0F));
/* 43 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacle5", CubeListBuilder.create(), PartPose.ZERO), new int[] { 18, 20, 26, 28, 32, 28 }, PartPose.offset(10.0F, -23.0F, 19.0F));
/* 44 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacle6", CubeListBuilder.create(), PartPose.ZERO), new int[] { 18, 20, 26, 28, 28, 24 }, PartPose.offset(-2.0F, 0.0F, 0.0F));
/* 45 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacleLarge0", CubeListBuilder.create(), PartPose.ZERO), new int[] { 16, 20, 24, 28, 32, 28 }, PartPose.offset(-24.0F, -28.0F, 0.0F));
/* 46 */     TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacleLarge1", CubeListBuilder.create(), PartPose.ZERO), new int[] { 20, 20, 24, 24, 28, 32 }, PartPose.offset(28.0F, -28.0F, 2.0F));
/* 47 */     return LayerDefinition.create(mesh, 160, 160);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderMassDecal(T entity, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayCoords, float r, float g, float b, float a) {
/* 53 */     stack.pushPose();
/* 54 */     VertexConsumer builder = buffer.getBuffer(RenderType.lightning());
/* 55 */     PoseStack.Pose entry = stack.last();
/* 56 */     Matrix4f matrix4f = entry.pose();
/* 57 */     Matrix3f matrix3f = entry.normal();
/* 58 */     float aR = 0.5F;
/* 59 */     float aG = 0.3F;
/* 60 */     float aB = 0.8F;
/* 61 */     float aA = a * 0.2F;
/* 62 */     float size = 0.35F;
/* 63 */     float topZOffset = 0.4F;
/* 64 */     float stretch = 1.1F;
/* 65 */     if (lowResModelsEnabled((WitherStormEntity)entity)) {
/*    */       
/* 67 */       topZOffset = 0.45F;
/* 68 */       stretch = 1.4F;
/* 69 */       stack.translate(-0.12D, -2.0D, -0.8D);
/*    */     }
/*    */     else {
/*    */       
/* 73 */       stack.translate(-0.12D, -2.0D, -0.9D);
/*    */     } 
/* 75 */     builder.vertex(matrix4f, size * stretch, size, 0.0F).color(aR, aG, aB, aA).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 76 */     builder.vertex(matrix4f, size * stretch, -size, -topZOffset).color(aR, aG, aB, aA).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 77 */     builder.vertex(matrix4f, -size * stretch, -size, -topZOffset).color(aR, aG, aB, aA).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 78 */     builder.vertex(matrix4f, -size * stretch, size, 0.0F).color(aR, aG, aB, aA).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*    */     
/* 80 */     builder.vertex(matrix4f, -size * stretch, size, 0.0F).color(aR, aG, aB, aA).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 81 */     builder.vertex(matrix4f, -size * stretch, -size, -topZOffset).color(aR, aG, aB, aA).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 82 */     builder.vertex(matrix4f, size * stretch, -size, -topZOffset).color(aR, aG, aB, aA).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 83 */     builder.vertex(matrix4f, size * stretch, size, 0.0F).color(aR, aG, aB, aA).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 84 */     stack.popPose();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormTornEvolvedDevourerModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */