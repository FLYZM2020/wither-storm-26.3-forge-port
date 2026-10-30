package nonamecrackers2.witherstormmod.client.model;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;

/** Original stage 2/3 central jaw geometry. */
public final class StormJawGeometry {
    private StormJawGeometry() {}
  public static void populateDefinition(PartDefinition root) {
    PartDefinition upper = root.addOrReplaceChild("upperJaw", CubeListBuilder.create()
        .texOffs(0, 65).addBox(-4.0F, -6.5F, 12.0F, 8.0F, 6.0F, 2.0F, false)
        .texOffs(0, 47).addBox(-2.0F, -8.5F, 10.0F, 4.0F, 2.0F, 2.0F, false)
        .texOffs(0, 35).addBox(-4.0F, -8.5F, 0.0F, 8.0F, 2.0F, 10.0F, false)
        .texOffs(0, 47).addBox(-6.0F, -6.5F, 0.0F, 12.0F, 6.0F, 12.0F, false)
        .texOffs(4, 13).addBox(-1.0F, -4.5F, 13.1F, 2.0F, 2.0F, 1.0F, CubeDeformation.NONE, 0.2F, 0.2F), 
        PartPose.offset(0.0F, 2.5F, 0.0F));
    upper.addOrReplaceChild("upperTeeth", CubeListBuilder.create()
        .texOffs(0, 54).addBox(-1.0F, -1.0F, 13.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-3.0F, -1.0F, 12.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-5.0F, -1.0F, 11.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -1.0F, 9.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -1.0F, 7.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(1.0F, -1.0F, 13.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(3.0F, -1.0F, 12.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(4.0F, -1.0F, 10.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -1.0F, 8.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -1.0F, 6.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, false), 
        PartPose.offset(0.0F, 0.5F, 0.0F));
    PartDefinition lower = root.addOrReplaceChild("lowerJaw", CubeListBuilder.create()
        .texOffs(0, 73).addBox(-4.0F, 0.5F, 12.0F, 8.0F, 2.0F, 2.0F, false)
        .texOffs(48, 0).addBox(-6.0F, 0.5F, 0.0F, 12.0F, 2.0F, 12.0F, false), 
        PartPose.offset(0.0F, 2.5F, 0.0F));
    lower.addOrReplaceChild("lowerTeeth", CubeListBuilder.create()
        .texOffs(0, 54).addBox(0.0F, -3.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(2.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(4.0F, -3.0F, 3.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(5.0F, -3.0F, -7.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-2.0F, -3.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-4.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-5.0F, -3.0F, 2.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -3.0F, -6.0F, 1.0F, 1.0F, 1.0F, false)
        .texOffs(0, 54).addBox(-6.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, false), 
        PartPose.offset(0.0F, 2.5F, 8.0F));
  }

  }
