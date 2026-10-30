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
/*    */ import net.minecraft.client.renderer.RenderType;
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
/* 33 */     MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[] { PartPose.m_171419_(-22.0F, -65.0F, -40.0F), PartPose.m_171419_(0.0F, -32.0F, -23.0F), PartPose.m_171419_(32.0F, -60.0F, -24.0F) });
/* 34 */     PartDefinition root = mesh.m_171576_();
/* 35 */     TornEvolvedDevourerBodyModel.createBodyModel(root, 0.2F);
/* 36 */     LowResTornEvolvedDevourerBodyModel.createBodyModel(root, 0.3F);
/* 37 */     PartDefinition tentacles = root.m_171597_("tentacles");
/* 38 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 28, 28, 32 }, PartPose.m_171419_(-20.0F, -25.0F, 5.0F));
/* 39 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 24, 28, 38 }, PartPose.m_171419_(20.0F, -27.5F, 7.0F));
/* 40 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 28, 32, 28 }, PartPose.m_171419_(-10.0F, -30.0F, -10.0F));
/* 41 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 18, 24, 24, 24, 28 }, PartPose.m_171419_(8.0F, -34.0F, -6.0F));
/* 42 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle4", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 18, 24, 28, 32, 32 }, PartPose.m_171419_(-8.0F, -25.0F, 16.0F));
/* 43 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle5", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 20, 26, 28, 32, 28 }, PartPose.m_171419_(10.0F, -23.0F, 19.0F));
/* 44 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle6", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 20, 26, 28, 28, 24 }, PartPose.m_171419_(-2.0F, 0.0F, 0.0F));
/* 45 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacleLarge0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 16, 20, 24, 28, 32, 28 }, PartPose.m_171419_(-24.0F, -28.0F, 0.0F));
/* 46 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacleLarge1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 20, 20, 24, 24, 28, 32 }, PartPose.m_171419_(28.0F, -28.0F, 2.0F));
/* 47 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderMassDecal(T entity, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayCoords, float r, float g, float b, float a) {
/* 53 */     stack.m_85836_();
/* 54 */     VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
/* 55 */     PoseStack.Pose entry = stack.m_85850_();
/* 56 */     Matrix4f matrix4f = entry.m_252922_();
/* 57 */     Matrix3f matrix3f = entry.m_252943_();
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
/* 69 */       stack.m_85837_(-0.12D, -2.0D, -0.8D);
/*    */     }
/*    */     else {
/*    */       
/* 73 */       stack.m_85837_(-0.12D, -2.0D, -0.9D);
/*    */     } 
/* 75 */     builder.m_252986_(matrix4f, size * stretch, size, 0.0F).m_85950_(aR, aG, aB, aA).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 76 */     builder.m_252986_(matrix4f, size * stretch, -size, -topZOffset).m_85950_(aR, aG, aB, aA).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 77 */     builder.m_252986_(matrix4f, -size * stretch, -size, -topZOffset).m_85950_(aR, aG, aB, aA).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 78 */     builder.m_252986_(matrix4f, -size * stretch, size, 0.0F).m_85950_(aR, aG, aB, aA).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*    */     
/* 80 */     builder.m_252986_(matrix4f, -size * stretch, size, 0.0F).m_85950_(aR, aG, aB, aA).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 81 */     builder.m_252986_(matrix4f, -size * stretch, -size, -topZOffset).m_85950_(aR, aG, aB, aA).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 82 */     builder.m_252986_(matrix4f, size * stretch, -size, -topZOffset).m_85950_(aR, aG, aB, aA).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 83 */     builder.m_252986_(matrix4f, size * stretch, size, 0.0F).m_85950_(aR, aG, aB, aA).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 84 */     stack.m_85849_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormTornEvolvedDevourerModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */