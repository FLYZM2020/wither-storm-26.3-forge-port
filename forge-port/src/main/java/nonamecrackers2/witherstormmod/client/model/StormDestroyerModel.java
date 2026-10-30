package nonamecrackers2.witherstormmod.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.wither.WitherBossModel;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.client.renderer.StormSegmentRenderer;

/** Approximate stage 4 display using the original body, head and tentacle geometry. */
public final class StormDestroyerModel extends WitherBossModel {
    private final ModelPart massGroup, headGroup, tentacleGroup;
    private final ModelPart[] tentacleScales;
    private final boolean devourerForm;
    private final boolean segmentForm;
    private final ModelPart[] heads;
    private final TentacleModel[] tentacles;

    public StormDestroyerModel(ModelPart root) {
        super(root);
        massGroup = root.getChild("mass_group");
        devourerForm = root.hasChild("devourer_marker");
        segmentForm = root.hasChild("segment_marker");
        headGroup = root.getChild("heads");
        tentacleGroup = root.getChild("tentacles");
        heads = new ModelPart[] {headGroup.getChild("center"), headGroup.getChild("left"), headGroup.getChild("right")};
        int count = tentacleGroup.hasChild("scale8") ? 9 : tentacleGroup.hasChild("scale6") ? 7 : 5;
        tentacles = new TentacleModel[count];
        tentacleScales = new ModelPart[count];
        for (int i = 0; i < count; i++) {
            tentacleScales[i] = tentacleGroup.getChild("scale" + i);
            float scale = segmentForm ? new float[] {3.0F, 2.0F, 2.5F, 3.0F, 2.0F}[i]
                    : count == 5 || count == 9 && !devourerForm && i >= 6 ? 2.0F : 3.0F;
            tentacles[i] = new TentacleModel(tentacleScales[i].getChild("tentacle" + i), scale);
        }
        configureTentacles();
    }

    private void configureTentacles() {
        if (segmentForm) {
            float[] yaw = {-90, 80, 80, 60, 270}, pitch = {80, 60, 0, -110, 20};
            float[] xCurve = {-0.2616667F, -0.3925F, -0.19625F, -0.2616667F, -0.2616667F};
            float[] yCurve = {-0.2616667F, -0.2616667F, -0.2616667F, -0.17444445F, 0.17444445F};
            float[] reach = {2, 1, 3, 2, 2};
            for (int i = 0; i < tentacles.length; i++) {
                TentacleModel t = tentacles[i];
                t.yRotationalOffset = yaw[i] * Mth.DEG_TO_RAD; t.xRotationalOffset = pitch[i] * Mth.DEG_TO_RAD;
                t.xAngularOffset = xCurve[i]; t.yAngularOffset = yCurve[i]; t.reach = reach[i];
                t.animationSpeed = i == 1 ? 0.5F : 0.2F;
            }
            return;
        }
        if (tentacles.length != 5) {
            configureEvolvedTentacles();
            return;
        }
        for (TentacleModel t : tentacles) { t.animationSpeed = 0.6F; t.reach = 2.0F; }
        TentacleModel a = tentacles[0], b = tentacles[1], c = tentacles[2], d = tentacles[3], e = tentacles[4];
        a.yRotationalOffset = 1.57F; a.xRotationalOffset = 0.3925F; a.xAngularOffset = -0.19625F; a.yAngularOffset = -0.19625F;
        b.yRotationalOffset = -0.3925F; b.xRotationalOffset = -0.628F; b.xAngularOffset = 0.3925F; b.yAngularOffset = -0.2616667F; b.animationOffset = 20.0F;
        c.yRotationalOffset = -1.7444445F; c.xAngularOffset = 0.3925F; c.yAngularOffset = -0.19625F; c.animationOffset = 10.0F;
        d.yRotationalOffset = 1.256F; d.xAngularOffset = -0.5233334F; d.yAngularOffset = 0.3925F; d.animationOffset = 30.0F;
        e.yRotationalOffset = -0.19625F; e.xRotationalOffset = -1.0466667F; e.xAngularOffset = 0.2616667F; e.yAngularOffset = 0.098125F; e.animationOffset = 40.0F;
    }

    private void configureEvolvedTentacles() {
        boolean intermediate = tentacles.length == 9 && !devourerForm;
        float[] y = {90, 270, 0, 320, 120, 220, 45};
        float[] x = {90, 90, 100, 90, 70, 70, 90};
        float[] yCurve = {0.17444445F, -0.17444445F, 0.2616667F, -0.13083334F, 0.2616667F, -0.2616667F, 0.3925F};
        float[] offset = {0, 8, 16, 9, 12, 20, 24};
        float[] reach = {2, 1, 1.5F, 1.75F, 2, 1.5F, 2};
        int firstCount = intermediate ? 6 : 7;
        for (int i = 0; i < firstCount; i++) {
            TentacleModel t = tentacles[i];
            t.yRotationalOffset = y[i] * Mth.DEG_TO_RAD;
            t.xRotationalOffset = x[i] * Mth.DEG_TO_RAD;
            t.xAngularOffset = i == 6 ? 0.2616667F : i == 5 ? -0.44857144F : -0.3925F;
            t.yAngularOffset = yCurve[i]; t.animationOffset = offset[i];
            t.animationSpeed = intermediate ? (i == 5 ? 0.5F : 0.3F) : (i == 6 ? 0.15F : i == 1 || i == 5 ? 0.4F : 0.2F);
            t.reach = reach[i] + (intermediate ? 1.0F : 0.0F);
        }
        if (intermediate) {
            TentacleModel a = tentacles[6], b = tentacles[7], c = tentacles[8];
            a.animationSpeed = b.animationSpeed = c.animationSpeed = 0.6F;
            a.reach = b.reach = c.reach = 2.0F;
            a.yRotationalOffset = -0.3925F; a.xRotationalOffset = -0.628F; a.xAngularOffset = 0.3925F; a.yAngularOffset = -0.2616667F; a.animationOffset = 20;
            b.yRotationalOffset = 1.256F; b.xAngularOffset = 0.19625F; b.yAngularOffset = -0.3925F; b.animationOffset = 30;
            c.yRotationalOffset = -0.19625F; c.xRotationalOffset = -1.0466667F; c.xAngularOffset = 0.3925F; c.yAngularOffset = 0.098125F; c.animationOffset = 40;
        } else if (devourerForm) {
            TentacleModel a = tentacles[7], b = tentacles[8];
            a.animationOffset = 80; a.animationSpeed = 0.25F; a.yRotationalOffset = -100 * Mth.DEG_TO_RAD;
            a.xRotationalOffset = 120 * Mth.DEG_TO_RAD; a.xAngularOffset = 0.2616667F; a.yAngularOffset = 0.3925F; a.reach = 2;
            b.animationOffset = 35; b.animationSpeed = 0.25F; b.yRotationalOffset = 120 * Mth.DEG_TO_RAD;
            b.xRotationalOffset = 100 * Mth.DEG_TO_RAD; b.xAngularOffset = 0.3925F; b.yAngularOffset = -0.2616667F; b.reach = 2;
        }
    }

    @Override
    public void setupAnim(WitherRenderState state) {
        super.setupAnim(state);
        boolean mirrored = segmentForm && state instanceof StormSegmentRenderer.SegmentState segment && segment.mirrored;
        massGroup.xScale = mirrored ? -10.0F : 10.0F;
        massGroup.yScale = massGroup.zScale = 10.0F;
        if (segmentForm) {
            tentacleGroup.xScale = mirrored ? -1.0F : 1.0F;
            float[] offsets = mirrored ? new float[] {0, 5, 10, 15, 25} : new float[] {10, 15, 29, 32, 57};
            for (int i = 0; i < tentacles.length; i++) tentacles[i].animationOffset = offsets[i];
        }
        headGroup.xScale = headGroup.yScale = headGroup.zScale = 3.0F;
        for (int i = 0; i < tentacleScales.length; i++) {
            float scale = tentacles[i].scale;
            tentacleScales[i].xScale = tentacleScales[i].yScale = tentacleScales[i].zScale = scale;
        }
        for (int i = 0; i < heads.length; i++) {
            ModelPart head = heads[i];
            float yaw = i == 0 ? state.yRot : state.yHeadRots[i - 1] - state.bodyRot;
            float pitch = i == 0 ? state.xRot : state.xHeadRots[i - 1];
            head.yRot = Mth.PI + yaw * Mth.DEG_TO_RAD;
            head.xRot = -pitch * Mth.DEG_TO_RAD;
            head.getChild("lowerJaw").xRot = (0.065F + 0.02F * Mth.cos((state.ageInTicks + i * 75) * 0.1F)) * Mth.PI - 0.5F;
        }
        for (TentacleModel tentacle : tentacles) tentacle.setupAnimations(state.ageInTicks, 0.0F);
    }

    public static LayerDefinition createLayer() { return createLayer(0); }
    public static LayerDefinition createIntermediateLayer() { return createLayer(1); }
    public static LayerDefinition createEvolvedLayer() { return createLayer(2); }
    public static LayerDefinition createIntermediateDevourerLayer() { return createLayer(3); }
    public static LayerDefinition createDevourerLayer() { return createLayer(4); }
    public static LayerDefinition createDismantledLayer() { return createLayer(5); }
    public static LayerDefinition createIntermediateEvolvedDevourerLayer() { return createLayer(6); }
    public static LayerDefinition createEvolvedDevourerLayer() { return createLayer(7); }
    public static LayerDefinition createSegmentLayer() { return createLayer(8); }

    private static LayerDefinition createLayer(int variant) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        if (variant >= 3 && variant <= 7) root.addOrReplaceChild("devourer_marker", CubeListBuilder.create(), PartPose.ZERO);
        if (variant == 8) root.addOrReplaceChild("segment_marker", CubeListBuilder.create(), PartPose.ZERO);
        // WitherBossModel needs these parts even though stage 4 uses separate heads.
        for (String name : new String[] {"ribcage", "tail", "center_head", "right_head", "left_head"}) {
            root.addOrReplaceChild(name, CubeListBuilder.create(), PartPose.ZERO);
        }
        PartDefinition mass = root.addOrReplaceChild("mass_group", CubeListBuilder.create(), PartPose.ZERO);
        switch (variant) {
            case 1 -> IntermediateEvolvedDestroyerBodyModel.createBodyModel(mass, 0.2F);
            case 2 -> EvolvedDestroyerBodyModel.createBodyModel(mass, 0.2F);
            case 3 -> IntermediateDevourerBodyModel.createBodyModel(mass, 0.2F);
            case 4 -> DevourerBodyModel.createBodyModel(mass, 0.2F);
            case 5 -> DismantledBodyModel.createBodyModel(mass, 0.2F);
            case 6 -> IntermediateEvolvedDevourerBodyModel.createBodyModel(mass, 0.2F);
            case 7 -> EvolvedDevourerBodyModel.createBodyModel(mass, 0.2F);
            case 8 -> SegmentBodyModel.createBodyModel(mass, 0.2F);
            default -> DestroyerBodyModel.createBodyModel(mass, 0.2F);
        }
        PartDefinition heads = root.addOrReplaceChild("heads", CubeListBuilder.create(), PartPose.ZERO);
        if (variant == 8) {
            addHead(heads, "right", 16.0F, -20.0F, -30.0F);
            addHead(heads, "center", 0.0F, -23.0F, -35.0F);
            addHead(heads, "left", -16.0F, -20.0F, -30.0F);
        } else {
            addHead(heads, "right", -22.0F, -65.0F, -40.0F);
            addHead(heads, "center", 0.0F, -32.0F, -23.0F);
            addHead(heads, "left", 32.0F, -60.0F, -24.0F);
        }
        PartDefinition tentacles = root.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.ZERO);
        int[][] lengths = switch (variant) {
            case 1 -> new int[][] {{12,18,20,23,22,25},{12,19,22,24,28,22},{12,17,23,22,28,24},
                    {12,14,19,20,20,22},{12,14,20,21,23,24},{12,18,22,23,24,22},
                    {23,28,28,28,32,32},{23,28,28,28,32,32},{23,28,28,28,32,32}};
            case 2 -> new int[][] {{18,24,24,28,28,32},{18,24,24,24,28,38},{18,24,24,28,32,28},
                    {18,18,24,24,24,28},{18,18,24,28,32,32},{18,20,26,28,32,28},{18,20,26,28,28,24}};
            case 3, 4, 5, 6, 7 -> new int[][] {{18,24,24,28,28,32},{18,24,24,24,28,38},{18,24,24,28,32,28},
                    {18,18,24,24,24,28},{18,18,24,28,32,32},{18,20,26,28,32,28},{18,20,26,28,28,24},
                    {16,20,24,28,32,28},{20,20,24,24,28,32}};
            case 8 -> new int[][] {{18,24,24,28,28,32},{24,28,28,28,32,32},{24,24,24,28,28,32},
                    {20,24,24,28,28,28},{24,24,28,28,32,32}};
            default -> new int[][] {{23,28,28,28,32,32},{23,28,28,28,32,32},{23,28,28,28,32,32},
                    {23,28,28,28,32,32},{23,28,28,28,32,32}};
        };
        float[][] offsets = variant == 0 ? new float[][] {{-10,-30,0},{30,-115,-10},{10,-40,10},{-50,-100,0},{-10,-95,15}}
                : variant == 1 ? new float[][] {{-20,-50,0},{20,-55,0},{-10,-40,-10},{8,-45,-10},{-8,-50,12},{10,-45,12},{30,-155,-10},{-60,-120,0},{-30,-155,5}}
                : variant == 2 ? new float[][] {{-20,-50,0},{20,-55,0},{-10,-40,-10},{8,-45,-10},{-8,-50,12},{10,-45,12},{-2,0,0}}
                : variant == 3 ? new float[][] {{-20,-50,0},{20,-55,0},{-10,-40,-10},{8,-45,-10},{-8,-50,12},{10,-45,12},{-2,0,0},{-34,-43,0},{38,-48,2}}
                : variant == 8 ? new float[][] {{20,5,0},{5,0,5},{-27.5F,-15,-15},{-10,-20,-5},{35,-20,40}}
                : new float[][] {{-20,-25,5},{20,-27.5F,7},{-10,-30,-10},{8,-34,-6},{-8,-25,16},{10,-23,19},{-2,0,0},{-24,-28,0},{28,-28,2}};
        for (int i = 0; i < offsets.length; i++) {
            PartDefinition scale = tentacles.addOrReplaceChild("scale" + i, CubeListBuilder.create(), PartPose.ZERO);
            TentacleModel.populateDefinition(scale.addOrReplaceChild("tentacle" + i, CubeListBuilder.create(), PartPose.ZERO),
                    lengths[i], PartPose.offset(offsets[i][0], offsets[i][1], offsets[i][2]));
        }
        return LayerDefinition.create(mesh, 160, 160);
    }

    private static void addHead(PartDefinition root, String name, float x, float y, float z) {
        PartDefinition head = root.addOrReplaceChild(name, CubeListBuilder.create(), PartPose.offset(x, y, z));
        StormJawGeometry.populateDefinition(head);
    }
}
