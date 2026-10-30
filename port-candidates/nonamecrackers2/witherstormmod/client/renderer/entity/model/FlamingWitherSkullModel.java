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
/* 23 */     this.base = root.getChild("head");
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
/* 52 */     PartDefinition root = mesh.getRoot();
/* 53 */     PartDefinition base = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, false), PartPose.offset(0.0F, -3.5F, 0.0F));
/* 54 */     PartDefinition flame = base.addOrReplaceChild("flame", CubeListBuilder.create()
/* 55 */         .texOffs(0, 24).addBox(-4.0F, -8.0F, 4.0F, 8.0F, 0.0F, 8.0F, CubeDeformation.NONE, 0.5F, 0.5F)
/* 56 */         .texOffs(0, 24).addBox(-4.0F, 0.0F, 4.0F, 8.0F, 0.0F, 8.0F, CubeDeformation.NONE, 0.5F, 0.5F), 
/* 57 */         PartPose.offset(0.0F, 4.0F, 0.0F));
/* 58 */     flame.addOrReplaceChild("flameSide", CubeListBuilder.create()
/* 59 */         .texOffs(0, 16).addBox(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, CubeDeformation.NONE, 0.5F, 0.5F)
/* 60 */         .texOffs(0, 16).addBox(8.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, CubeDeformation.NONE, 0.5F, 0.5F), 
/* 61 */         PartPose.offsetAndRotation(-4.0F, -4.0F, 8.0F, -1.5708F, 0.0F, 0.0F));
/* 62 */     return LayerDefinition.create(mesh, 32, 32);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 68 */     this.root.render(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(float f, float yRot, float xRot) {
/* 74 */     this.base.yRot = yRot * 0.017453292F;
/* 75 */     this.base.xRot = xRot * 0.017453292F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\FlamingWitherSkullModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */