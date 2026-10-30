/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.model.Model;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ 
/*    */ public class SantaHatModel
/*    */   extends Model
/*    */ {
/* 20 */   public static final Identifier TEXTURE = WitherStormMod.id("textures/misc/santa_hat.png");
/*    */   
/*    */   private final ModelPart root;
/*    */   
/*    */   public SantaHatModel(ModelPart root) {
/* 25 */     super(RenderType::entityCutoutNoCull);
/* 26 */     this.root = root;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float alpha) {
/* 32 */     this.root.render(stack, consumer, packedLight, overlayTexture, r, g, b, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition() {
/* 37 */     MeshDefinition meshdefinition = new MeshDefinition();
/* 38 */     PartDefinition partdefinition = meshdefinition.getRoot();
/*    */     
/* 40 */     PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 6.1F));
/* 41 */     PartDefinition segment1 = base.addOrReplaceChild("segment1", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -3.0F, -5.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.0F, -0.1222F));
/* 42 */     PartDefinition segment2 = segment1.addOrReplaceChild("segment2", CubeListBuilder.create().texOffs(0, 29).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.6196F));
/* 43 */     PartDefinition segment3 = segment2.addOrReplaceChild("segment3", CubeListBuilder.create().texOffs(30, 15).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.0F, -0.3229F));
/* 44 */     PartDefinition segment4 = segment3.addOrReplaceChild("segment4", CubeListBuilder.create().texOffs(24, 29).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -3.0F, 0.0F, 0.0F, 0.0F, -0.4887F));
/* 45 */     segment4.addOrReplaceChild("tip", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.7F, 0.0F, 0.0F, 0.0F, -0.5672F));
/*    */     
/* 47 */     return LayerDefinition.create(meshdefinition, 64, 64);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\SantaHatModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */