package nonamecrackers2.witherstormmod.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.util.Mth;

/** Early adaptation of the original body geometry and tentacles. */
public final class SymbiontModel extends SkeletonModel<SkeletonRenderState> {
    private final ModelPart[][] tentacles = new ModelPart[3][5];
    public SymbiontModel(ModelPart root) {
        super(root);
        hat.visible = false;
        for (int i = 0; i < tentacles.length; i++) {
            ModelPart part = body.getChild(i == 0 ? "tentacle" : "tentacle" + (i + 1));
            tentacles[i][0] = part;
            for (int j = 1; j < 5; j++) {
                part = part.getChild("segment" + j);
                tentacles[i][j] = part;
            }
        }
    }

    public static LayerDefinition createLayer() {
    MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
    PartDefinition root = mesh.getRoot();
    
    root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(1.9F, 12.0F, 0.0F));
    PartDefinition body = root.getChild("body");
    body.addOrReplaceChild("mass1", CubeListBuilder.create().texOffs(36, 32).addBox(-4.5F, -4.5F, -1.5F, 6.0F, 9.0F, 3.0F, false), PartPose.offsetAndRotation(1.2478F, 5.7655F, 2.1684F, -0.0783F, -0.1909F, -0.2221F));
    body.addOrReplaceChild("mass2", CubeListBuilder.create().texOffs(32, 4).addBox(-3.5F, -2.5F, -2.5F, 7.0F, 9.0F, 3.0F, false), PartPose.offsetAndRotation(-0.6213F, 2.8595F, -1.0036F, 0.2661F, -0.1641F, 0.244F));
    body.addOrReplaceChild("mass3", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, false), PartPose.offsetAndRotation(-2.0355F, 9.8385F, 1.8323F, -2.5517F, -0.5708F, 1.7651F));
    body.addOrReplaceChild("mass4", CubeListBuilder.create().texOffs(16, 32).addBox(-2.5F, -0.5F, -4.5F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(1.5F, 6.5F, -1.5F, 0.6346F, -0.678F, -0.4326F));
    PartDefinition rightLeg = root.getChild("right_leg");
    rightLeg.addOrReplaceChild("mass5", CubeListBuilder.create().texOffs(16, 32).addBox(-2.5F, -2.5F, 1.5F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(-1.5F, 7.5F, -1.5F, 0.0406F, 0.4854F, -1.2322F));
    rightLeg.addOrReplaceChild("mass6", CubeListBuilder.create().texOffs(16, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(-1.5F, 7.5F, -1.5F, 0.6929F, 0.4557F, 0.3503F));
    rightLeg.addOrReplaceChild("mass7", CubeListBuilder.create().texOffs(16, 32).addBox(-1.5F, -3.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(-1.5F, 3.5F, -1.5F, 0.432F, -0.8648F, -0.6805F));
    PartDefinition rightArm = root.getChild("right_arm");
    rightArm.addOrReplaceChild("mass8", CubeListBuilder.create().texOffs(0, 32).addBox(-2.5F, -1.5F, -1.5F, 4.0F, 4.0F, 4.0F, false), PartPose.offsetAndRotation(-1.2211F, 4.1554F, 1.9027F, 1.6619F, -0.8156F, -1.5077F));
    rightArm.addOrReplaceChild("mass9", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 2.0F, 21.0F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(5.0F, 22.0F, 0.0F, 1.7759F, 0.1628F, -0.3449F));
    rightArm.addOrReplaceChild("mass10", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, -15.0F, 12.0F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(5.0F, 22.0F, 0.0F, 0.9318F, -0.52F, -0.4963F));
    PartDefinition head = root.getChild("head");
    head.addOrReplaceChild("mass11", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, false), PartPose.offsetAndRotation(-5.2146F, -1.96F, 2.8784F, 3.1231F, -0.7686F, -1.9387F));
    head.addOrReplaceChild("mass12", CubeListBuilder.create().texOffs(16, 32).addBox(-1.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(-4.7943F, -4.4645F, -3.6255F, -2.4784F, -0.4058F, -2.4209F));
    head.addOrReplaceChild("mass13", CubeListBuilder.create().texOffs(16, 32).addBox(-1.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(-2.5F, -7.5F, 2.5F, -2.4343F, -0.4891F, 2.7597F));
    
    PartDefinition tentacle = body.addOrReplaceChild("tentacle", CubeListBuilder.create(), PartPose.offset(-3.5F, 9.5F, 0.0F));
    PartDefinition segment1 = tentacle.addOrReplaceChild("segment1", CubeListBuilder.create().texOffs(54, 30).addBox(-1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 8.0F, false), PartPose.ZERO);
    PartDefinition segment2 = segment1.addOrReplaceChild("segment2", CubeListBuilder.create().texOffs(72, 33).addBox(-1.3F, -1.3F, 0.0F, 2.6F, 2.6F, 9.0F, false), PartPose.offset(0.0F, 0.0F, 9.0F));
    PartDefinition segment3 = segment2.addOrReplaceChild("segment3", CubeListBuilder.create().texOffs(68, 19).addBox(-0.9F, -0.9F, 0.0F, 1.8F, 1.8F, 11.0F, false), PartPose.offset(0.0F, 0.0F, 9.0F));
    segment3.addOrReplaceChild("segment4", CubeListBuilder.create().texOffs(58, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 16.0F, false), PartPose.offset(0.0F, 0.0F, 11.0F));
    
    PartDefinition tentacle2 = body.addOrReplaceChild("tentacle2", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));
    PartDefinition segment5 = tentacle2.addOrReplaceChild("segment1", CubeListBuilder.create().texOffs(54, 30).addBox(-1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 8.0F, false), PartPose.ZERO);
    PartDefinition segment6 = segment5.addOrReplaceChild("segment2", CubeListBuilder.create().texOffs(72, 33).addBox(-1.3F, -1.3F, 0.0F, 2.6F, 2.6F, 9.0F, false), PartPose.offset(0.0F, 0.0F, 9.0F));
    PartDefinition segment7 = segment6.addOrReplaceChild("segment3", CubeListBuilder.create().texOffs(68, 19).addBox(-0.9F, -0.9F, 0.0F, 1.8F, 1.8F, 11.0F, false), PartPose.offset(0.0F, 0.0F, 9.0F));
    segment7.addOrReplaceChild("segment4", CubeListBuilder.create().texOffs(58, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 16.0F, false), PartPose.offset(0.0F, 0.0F, 11.0F));
    
    PartDefinition tentacle3 = body.addOrReplaceChild("tentacle3", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.5F, 0.0F));
    PartDefinition segment9 = tentacle3.addOrReplaceChild("segment1", CubeListBuilder.create().texOffs(54, 30).addBox(-1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 8.0F, false), PartPose.ZERO);
    PartDefinition segment10 = segment9.addOrReplaceChild("segment2", CubeListBuilder.create().texOffs(72, 33).addBox(-1.3F, -1.3F, 0.0F, 2.6F, 2.6F, 9.0F, false), PartPose.offset(0.0F, 0.0F, 9.0F));
    PartDefinition segment11 = segment10.addOrReplaceChild("segment3", CubeListBuilder.create().texOffs(68, 19).addBox(-0.9F, -0.9F, 0.0F, 1.8F, 1.8F, 11.0F, false), PartPose.offset(0.0F, 0.0F, 9.0F));
    segment11.addOrReplaceChild("segment4", CubeListBuilder.create().texOffs(58, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 16.0F, false), PartPose.offset(0.0F, 0.0F, 11.0F));
    
    return LayerDefinition.create(mesh, 96, 96);
    }

    @Override public void setupAnim(SkeletonRenderState state) {
        super.setupAnim(state);
        float[] xAngles = {10.0F, 15.0F, 50.0F};
        float[] yAngles = {-30.0F, 40.0F, -10.0F};
        for (int i = 0; i < 3; i++) {
            ModelPart[] p = tentacles[i];
            float time = (state.ageInTicks + i * 200.0F) * 0.04F;
            float f = Mth.cos(time) * 1.5F + Mth.cos(state.walkAnimationPos);
            float s = Mth.sin(time * 0.5F) * 1.5F + Mth.sin(state.walkAnimationPos);
            p[0].yRot = s * f * 0.05F;
            p[0].xRot = f * s * 0.05F;
            p[1].xRot = f * -0.1F;
            for (int j = 2; j < 5; j++) p[j].xRot = f * (0.1F - (j - 2) * 0.025F) + xAngles[i] * Mth.DEG_TO_RAD;
            for (int j = 1; j < 5; j++) p[j].yRot = s * (0.125F - j * 0.025F) + yAngles[i] * Mth.DEG_TO_RAD;
        }
    }
}
