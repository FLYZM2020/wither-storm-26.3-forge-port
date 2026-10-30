/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.model.SkullModelBase;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ 
/*    */ 
/*    */ public class FlamingWitherSkullModel
/*    */   extends SkullModelBase
/*    */ {
/*    */   private final ModelPart root;
/*    */   private final ModelPart base;
/*    */   
/*    */   public FlamingWitherSkullModel(ModelPart root) {
/* 22 */     this.root = root;
/* 23 */     this.base = root.m_171324_("head");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition() {
/* 51 */     MeshDefinition mesh = new MeshDefinition();
/* 52 */     PartDefinition root = mesh.m_171576_();
/* 53 */     PartDefinition base = root.m_171599_("head", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171506_(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, false), PartPose.m_171419_(0.0F, -3.5F, 0.0F));
/* 54 */     PartDefinition flame = base.m_171599_("flame", CubeListBuilder.m_171558_()
/* 55 */         .m_171514_(0, 24).m_171496_(-4.0F, -8.0F, 4.0F, 8.0F, 0.0F, 8.0F, CubeDeformation.f_171458_, 0.5F, 0.5F)
/* 56 */         .m_171514_(0, 24).m_171496_(-4.0F, 0.0F, 4.0F, 8.0F, 0.0F, 8.0F, CubeDeformation.f_171458_, 0.5F, 0.5F), 
/* 57 */         PartPose.m_171419_(0.0F, 4.0F, 0.0F));
/* 58 */     flame.m_171599_("flameSide", CubeListBuilder.m_171558_()
/* 59 */         .m_171514_(0, 16).m_171496_(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, CubeDeformation.f_171458_, 0.5F, 0.5F)
/* 60 */         .m_171514_(0, 16).m_171496_(8.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, CubeDeformation.f_171458_, 0.5F, 0.5F), 
/* 61 */         PartPose.m_171423_(-4.0F, -4.0F, 8.0F, -1.5708F, 0.0F, 0.0F));
/* 62 */     return LayerDefinition.m_171565_(mesh, 32, 32);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 68 */     this.root.m_104301_(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_6251_(float f, float yRot, float xRot) {
/* 74 */     this.base.f_104204_ = yRot * 0.017453292F;
/* 75 */     this.base.f_104203_ = xRot * 0.017453292F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\FlamingWitherSkullModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */