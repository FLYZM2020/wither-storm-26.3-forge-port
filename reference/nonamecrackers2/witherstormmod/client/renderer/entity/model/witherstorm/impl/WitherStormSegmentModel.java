/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.TentacleModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.ThreeHeadedWitherStormModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.LowResSegmentBodyModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.SegmentBodyModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ public class WitherStormSegmentModel<T extends WitherStormEntity>
/*     */   extends ThreeHeadedWitherStormModel<T>
/*     */ {
/*     */   public WitherStormSegmentModel(ModelPart root) {
/*  21 */     super(root, 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition() {
/*  26 */     MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[] { PartPose.m_171419_(16.0F, -20.0F, -30.0F), PartPose.m_171419_(0.0F, -23.0F, -35.0F), PartPose.m_171419_(-16.0F, -20.0F, -30.0F) });
/*  27 */     PartDefinition root = mesh.m_171576_();
/*  28 */     SegmentBodyModel.createBodyModel(root, 0.2F);
/*  29 */     LowResSegmentBodyModel.createBodyModel(root, 0.3F);
/*  30 */     PartDefinition tentacles = root.m_171597_("tentacles");
/*  31 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 28, 28, 32 }, PartPose.m_171419_(20.0F, 5.0F, 0.0F));
/*  32 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 24, 28, 28, 28, 32, 32 }, PartPose.m_171419_(5.0F, 0.0F, 5.0F));
/*  33 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 24, 24, 24, 28, 28, 32 }, PartPose.m_171419_(-27.5F, -15.0F, -15.0F));
/*  34 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 20, 24, 24, 28, 28, 28 }, PartPose.m_171419_(-10.0F, -20.0F, -5.0F));
/*  35 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle4", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 24, 24, 28, 28, 32, 32 }, PartPose.m_171419_(35.0F, -20.0F, 40.0F));
/*  36 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureHeads(ModelPart root, float headScale) {
/*  42 */     super.configureHeads(root, headScale);
/*     */     
/*  44 */     this.rightHead.tractorBeamDistance = 90.0F;
/*  45 */     this.rightHead.tractorBeamStartSize = 0.1F;
/*  46 */     this.rightHead.tractorBeamEndSize = 5.0F;
/*  47 */     this.rightHead.tractorBeamXOffset = 0.0F;
/*  48 */     this.rightHead.tractorBeamYOffset = 8.0F;
/*  49 */     this.rightHead.tractorBeamZOffset = 0.0F;
/*  50 */     this.rightHead.animationOffset = 100.0F;
/*  51 */     this.rightHead.pivotOffsetX = -4.0F;
/*  52 */     this.rightHead.pivotOffsetY = 0.325F;
/*  53 */     this.rightHead.pivotOffsetZ = 0.0F;
/*     */     
/*  55 */     this.middleHead.tractorBeamDistance = 90.0F;
/*  56 */     this.middleHead.tractorBeamStartSize = 0.1F;
/*  57 */     this.middleHead.tractorBeamEndSize = 5.0F;
/*  58 */     this.middleHead.tractorBeamXOffset = 0.0F;
/*  59 */     this.middleHead.tractorBeamYOffset = 8.0F;
/*  60 */     this.middleHead.tractorBeamZOffset = 0.0F;
/*  61 */     this.middleHead.pivotOffsetX = -4.0F;
/*  62 */     this.middleHead.pivotOffsetY = 0.325F;
/*  63 */     this.middleHead.pivotOffsetZ = 0.0F;
/*     */     
/*  65 */     this.leftHead.tractorBeamDistance = 90.0F;
/*  66 */     this.leftHead.tractorBeamStartSize = 0.1F;
/*  67 */     this.leftHead.tractorBeamEndSize = 5.0F;
/*  68 */     this.leftHead.tractorBeamXOffset = 0.0F;
/*  69 */     this.leftHead.tractorBeamYOffset = 8.0F;
/*  70 */     this.leftHead.tractorBeamZOffset = 0.0F;
/*  71 */     this.leftHead.animationOffset = 175.0F;
/*  72 */     this.leftHead.pivotOffsetX = -4.0F;
/*  73 */     this.leftHead.pivotOffsetY = 0.325F;
/*  74 */     this.leftHead.pivotOffsetZ = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureTentacles(ModelPart root) {
/*  80 */     this.tentacles = new TentacleModel[5];
/*  81 */     this.tentacles[0] = new TentacleModel(root.m_171324_("tentacle0"), 3.0F);
/*  82 */     (this.tentacles[0]).animationSpeed = 0.2F;
/*  83 */     (this.tentacles[0]).yRotationalOffset = -((float)Math.toRadians(90.0D));
/*  84 */     (this.tentacles[0]).xRotationalOffset = (float)Math.toRadians(80.0D);
/*  85 */     (this.tentacles[0]).xAngularOffset = -0.2616667F;
/*  86 */     (this.tentacles[0]).yAngularOffset = -0.2616667F;
/*  87 */     (this.tentacles[0]).reach = 2.0F;
/*  88 */     this.tentacles[1] = new TentacleModel(root.m_171324_("tentacle1"), 2.0F);
/*  89 */     (this.tentacles[1]).animationSpeed = 0.5F;
/*  90 */     (this.tentacles[1]).yRotationalOffset = (float)Math.toRadians(80.0D);
/*  91 */     (this.tentacles[1]).xRotationalOffset = (float)Math.toRadians(60.0D);
/*  92 */     (this.tentacles[1]).xAngularOffset = -0.3925F;
/*  93 */     (this.tentacles[1]).yAngularOffset = -0.2616667F;
/*  94 */     (this.tentacles[1]).reach = 1.0F;
/*  95 */     this.tentacles[2] = new TentacleModel(root.m_171324_("tentacle2"), 2.5F);
/*  96 */     (this.tentacles[2]).animationSpeed = 0.2F;
/*  97 */     (this.tentacles[2]).yRotationalOffset = (float)Math.toRadians(80.0D);
/*  98 */     (this.tentacles[2]).xRotationalOffset = (float)Math.toRadians(0.0D);
/*  99 */     (this.tentacles[2]).xAngularOffset = -0.19625F;
/* 100 */     (this.tentacles[2]).yAngularOffset = -0.2616667F;
/* 101 */     (this.tentacles[2]).reach = 3.0F;
/* 102 */     this.tentacles[3] = new TentacleModel(root.m_171324_("tentacle3"), 3.0F);
/* 103 */     (this.tentacles[3]).animationSpeed = 0.2F;
/* 104 */     (this.tentacles[3]).yRotationalOffset = (float)Math.toRadians(60.0D);
/* 105 */     (this.tentacles[3]).xRotationalOffset = -((float)Math.toRadians(110.0D));
/* 106 */     (this.tentacles[3]).xAngularOffset = -0.2616667F;
/* 107 */     (this.tentacles[3]).yAngularOffset = -0.17444445F;
/* 108 */     (this.tentacles[3]).reach = 2.0F;
/* 109 */     this.tentacles[4] = new TentacleModel(root.m_171324_("tentacle4"), 2.0F);
/* 110 */     (this.tentacles[4]).animationSpeed = 0.2F;
/* 111 */     (this.tentacles[4]).yRotationalOffset = (float)Math.toRadians(270.0D);
/* 112 */     (this.tentacles[4]).xRotationalOffset = (float)Math.toRadians(20.0D);
/* 113 */     (this.tentacles[4]).xAngularOffset = -0.2616667F;
/* 114 */     (this.tentacles[4]).yAngularOffset = 0.17444445F;
/* 115 */     (this.tentacles[4]).reach = 2.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void transformForMirrored(PoseStack stack, boolean mirrored) {
/* 121 */     super.transformForMirrored(stack, mirrored);
/*     */     
/* 123 */     (this.tentacles[0]).animationOffset = mirrored ? 0.0F : 10.0F;
/* 124 */     (this.tentacles[1]).animationOffset = mirrored ? 5.0F : 15.0F;
/* 125 */     (this.tentacles[2]).animationOffset = mirrored ? 10.0F : 29.0F;
/* 126 */     (this.tentacles[3]).animationOffset = mirrored ? 15.0F : 32.0F;
/* 127 */     (this.tentacles[4]).animationOffset = mirrored ? 25.0F : 57.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormSegmentModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */