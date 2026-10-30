package nonamecrackers2.witherstormmod.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.wither.WitherBossModel;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.util.Mth;

/** Geometry of the original command-block stage, adapted to 26.3 model layers. */
public final class StormEarlyModel extends WitherBossModel {
    private final ModelPart tentacleGroup;
    private final TentacleModel[] tentacles;
    private final ModelPart jawHead;

    public StormEarlyModel(ModelPart root) {
        super(root);
        tentacleGroup = root.hasChild("tentacles") ? root.getChild("tentacles") : null;
        ModelPart center = root.getChild("center_head");
        jawHead = center.hasChild("lowerJaw") ? center : null;
        tentacles = tentacleGroup == null ? new TentacleModel[0] : new TentacleModel[] {
                new TentacleModel(tentacleGroup.getChild("tentacle0"), 0.5F),
                new TentacleModel(tentacleGroup.getChild("tentacle1"), 0.5F),
                new TentacleModel(tentacleGroup.getChild("tentacle2"), 0.5F)
        };
        if (tentacles.length > 0) configureTentacles();
    }

    private void configureTentacles() {
        TentacleModel a = tentacles[0], b = tentacles[1], c = tentacles[2];
        a.animationSpeed = b.animationSpeed = c.animationSpeed = 0.5F;
        a.yRotationalOffset = 4.14F; a.xAngularOffset = -0.17444445F;
        a.yAngularOffset = 0.3488889F; a.reach = 2.0F;
        b.yRotationalOffset = 11.775001F; b.xRotationalOffset = 9.859601F;
        b.xAngularOffset = 0.17444445F; b.yAngularOffset = 0.3488889F;
        b.animationOffset = 10.0F; b.reach = 2.15F;
        c.yRotationalOffset = 3.14F; c.xRotationalOffset = 0.785F;
        c.xAngularOffset = 0.5233334F; c.yAngularOffset = -0.17444445F;
        c.animationOffset = 32.0F; c.reach = 1.85F;
    }

    @Override
    public void setupAnim(WitherRenderState state) {
        super.setupAnim(state);
        if (jawHead != null) {
            jawHead.xScale = jawHead.yScale = jawHead.zScale = 0.7F;
            jawHead.yRot = Mth.PI + state.yRot * Mth.DEG_TO_RAD;
            jawHead.xRot = -state.xRot * Mth.DEG_TO_RAD;
            jawHead.getChild("lowerJaw").xRot = (0.065F + 0.02F * Mth.cos(state.ageInTicks * 0.1F)) * Mth.PI - 0.5F;
        }
        if (tentacleGroup != null) {
            tentacleGroup.xScale = tentacleGroup.yScale = tentacleGroup.zScale = 0.5F;
            for (TentacleModel tentacle : tentacles) tentacle.setupAnimations(state.ageInTicks, 0.0F);
        }
    }

    public static LayerDefinition createLayer() {
        return createLayer(0);
    }

    public static LayerDefinition createHunchbackLayer() {
        return createLayer(1);
    }

    public static LayerDefinition createGrowingLayer() { return createLayer(2); }

    public static LayerDefinition createPregnantLayer() { return createLayer(3); }
    public static LayerDefinition createHunchbackOneQuarterLayer() { return createLayer(4); }
    public static LayerDefinition createHunchbackHalfLayer() { return createLayer(5); }
    public static LayerDefinition createGrowingHalfLayer() { return createLayer(6); }
    public static LayerDefinition createPregnantOneLayer() { return createLayer(7); }
    public static LayerDefinition createPregnantTwoLayer() { return createLayer(8); }

    private static LayerDefinition createLayer(int stage) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        CubeDeformation deformation = CubeDeformation.NONE;
        root.addOrReplaceChild("shoulders", CubeListBuilder.create().texOffs(0, 16)
                .addBox(-10.0F, 3.9F, -0.5F, 20.0F, 3.0F, 3.0F, deformation), PartPose.ZERO);
        PartDefinition ribcage = root.addOrReplaceChild("ribcage", CubeListBuilder.create()
                .texOffs(0, 22).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, deformation)
                .texOffs(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, deformation)
                .texOffs(24, 22).addBox(-4.0F, 4.5F, 0.5F, 11.0F, 2.0F, 2.0F, deformation)
                .texOffs(24, 22).addBox(-4.0F, 7.5F, 0.5F, 11.0F, 2.0F, 2.0F, deformation),
                PartPose.offsetAndRotation(-2.0F, 6.9F, -0.5F, 0.20420352F, 0.0F, 0.0F));
        PartDefinition extension = ribcage.addOrReplaceChild("ribcageExtension", CubeListBuilder.create()
                .texOffs(128, 40).addBox(-5.5F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, deformation)
                .texOffs(128, 40).addBox(-5.5F, -5.0F, -4.0F, 2.0F, 2.0F, 8.0F, deformation)
                .texOffs(128, 40).addBox(-5.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F, deformation)
                .texOffs(128, 40).addBox(3.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F, deformation)
                .texOffs(128, 40).addBox(3.5F, -5.0F, -4.0F, 2.0F, 2.0F, 8.0F, deformation)
                .texOffs(128, 40).addBox(3.5F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, deformation)
                .texOffs(140, 44).addBox(2.5F, -2.0F, -4.0F, 1.0F, 2.0F, 2.0F, deformation)
                .texOffs(140, 44).addBox(2.5F, -5.0F, -4.0F, 1.0F, 2.0F, 2.0F, deformation)
                .texOffs(140, 44).addBox(2.5F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, deformation)
                .texOffs(140, 44).addBox(-3.5F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, deformation)
                .texOffs(140, 44).addBox(-3.5F, -5.0F, -4.0F, 1.0F, 2.0F, 2.0F, deformation)
                .texOffs(140, 44).addBox(-3.5F, -2.0F, -4.0F, 1.0F, 2.0F, 2.0F, deformation),
                PartPose.offset(1.5F, 9.5F, -3.5F));
        extension.addOrReplaceChild("block", CubeListBuilder.create().texOffs(48, 0)
                .addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, deformation.extend(0.001F), 0.5F, 0.5F), PartPose.ZERO);
        root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(12, 22)
                .addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, deformation),
                PartPose.offsetAndRotation(-2.0F, 6.9F + Mth.cos(0.20420352F) * 10.0F,
                        -0.5F + Mth.sin(0.20420352F) * 10.0F, 0.83252203F, 0.0F, 0.0F));
        CubeListBuilder centerHead = CubeListBuilder.create();
        boolean hasJaw = stage == 2 || stage == 3 || stage >= 6;
        if (!hasJaw) centerHead.texOffs(0, 0)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation);
        PartDefinition center = root.addOrReplaceChild("center_head", centerHead, PartPose.ZERO);
        if (hasJaw) StormJawGeometry.populateDefinition(center);
        CubeListBuilder sideHeads = CubeListBuilder.create().texOffs(32, 0)
                .addBox(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, deformation);
        root.addOrReplaceChild("right_head", sideHeads, PartPose.offset(-8.0F, 4.0F, 0.0F));
        root.addOrReplaceChild("left_head", sideHeads, PartPose.offset(10.0F, 4.0F, 0.0F));
        switch (stage) {
            case 1 -> HunchbackBodyModel.createBodyModel(root, 1.0F);
            case 2 -> GrowingHunchbackMassModel.createMassModel(root, 1.0F);
            case 3 -> PregnantHunchbackBodyModel.createBodyModel(root, 1.0F);
            case 4 -> WSHunchback1_1.createBodyModel(root, 1.0F);
            case 5 -> WSHunchback1_2.createBodyModel(root, 1.0F);
            case 6 -> WSHunchback2_1.createBodyModel(root, 1.0F);
            case 7 -> WSHunchback3_1.createBodyModel(root, 1.0F);
            case 8 -> WSHunchback3_2.createBodyModel(root, 1.0F);
            default -> { }
        }
        if (stage == 3 || stage == 7 || stage == 8) {
            PartDefinition tentacles = root.addOrReplaceChild("tentacles", CubeListBuilder.create(),
                    PartPose.rotation(20.0F * Mth.DEG_TO_RAD, 0.0F, 0.0F));
            int[] lengths = {18, 24, 24, 24, 24, 32};
            for (int i = 0; i < 3; i++) {
                TentacleModel.populateDefinition(tentacles.addOrReplaceChild("tentacle" + i,
                        CubeListBuilder.create(), PartPose.ZERO), lengths,
                        PartPose.offset(0.0F, i == 2 ? 20.0F : 0.0F, 20.0F));
            }
        }
        return LayerDefinition.create(mesh, 160, 160);
    }
}
