/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*     */ 
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.TentacleModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.ThreeHeadedWitherStormModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.IntermediateEvolvedDestroyerBodyModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.LowResIntermediateEvolvedDestroyerBodyModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ public class WitherStormIntermediateEvolvedDestroyerModel<T extends WitherStormEntity>
/*     */   extends ThreeHeadedWitherStormModel<T>
/*     */ {
/*     */   public WitherStormIntermediateEvolvedDestroyerModel(ModelPart part) {
/*  19 */     super(part, 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition() {
/*  24 */     MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[] { PartPose.m_171419_(-22.0F, -65.0F, -40.0F), PartPose.m_171419_(0.0F, -32.0F, -23.0F), PartPose.m_171419_(32.0F, -60.0F, -24.0F) });
/*  25 */     PartDefinition root = mesh.m_171576_();
/*  26 */     IntermediateEvolvedDestroyerBodyModel.createBodyModel(root, 0.2F);
/*  27 */     LowResIntermediateEvolvedDestroyerBodyModel.createBodyModel(root, 0.3F);
/*  28 */     PartDefinition tentacles = root.m_171597_("tentacles");
/*  29 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 12, 18, 20, 23, 22, 25 }, PartPose.m_171419_(-20.0F, -50.0F, 0.0F));
/*  30 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 12, 19, 22, 24, 28, 22 }, PartPose.m_171419_(20.0F, -55.0F, 0.0F));
/*  31 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 12, 17, 23, 22, 28, 24 }, PartPose.m_171419_(-10.0F, -40.0F, -10.0F));
/*  32 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 12, 14, 19, 20, 20, 22 }, PartPose.m_171419_(8.0F, -45.0F, -10.0F));
/*  33 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle4", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 12, 14, 20, 21, 23, 24 }, PartPose.m_171419_(-8.0F, -50.0F, 12.0F));
/*  34 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle5", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 12, 18, 22, 23, 24, 22 }, PartPose.m_171419_(10.0F, -45.0F, 12.0F));
/*     */ 
/*     */     
/*  37 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle6", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 23, 28, 28, 28, 32, 32 }, PartPose.m_171419_(30.0F, -155.0F, -10.0F));
/*  38 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle7", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 23, 28, 28, 28, 32, 32 }, PartPose.m_171419_(-60.0F, -120.0F, 0.0F));
/*  39 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle8", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 23, 28, 28, 28, 32, 32 }, PartPose.m_171419_(-30.0F, -155.0F, 5.0F));
/*  40 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureHeads(ModelPart root, float headScale) {
/*  46 */     super.configureHeads(root, headScale);
/*     */     
/*  48 */     this.rightHead.tractorBeamDistance = 90.0F;
/*  49 */     this.rightHead.tractorBeamStartSize = 0.1F;
/*  50 */     this.rightHead.tractorBeamEndSize = 5.0F;
/*  51 */     this.rightHead.tractorBeamXOffset = 0.0F;
/*  52 */     this.rightHead.tractorBeamYOffset = 8.0F;
/*  53 */     this.rightHead.tractorBeamZOffset = 0.0F;
/*  54 */     this.rightHead.animationOffset = 100.0F;
/*  55 */     this.rightHead.pivotOffsetX = -4.0F;
/*  56 */     this.rightHead.pivotOffsetY = 0.325F;
/*  57 */     this.rightHead.pivotOffsetZ = 0.0F;
/*     */     
/*  59 */     this.middleHead.tractorBeamDistance = 90.0F;
/*  60 */     this.middleHead.tractorBeamStartSize = 0.1F;
/*  61 */     this.middleHead.tractorBeamEndSize = 5.0F;
/*  62 */     this.middleHead.tractorBeamXOffset = 0.0F;
/*  63 */     this.middleHead.tractorBeamYOffset = 8.0F;
/*  64 */     this.middleHead.tractorBeamZOffset = 0.0F;
/*  65 */     this.middleHead.pivotOffsetX = -4.0F;
/*  66 */     this.middleHead.pivotOffsetY = 0.325F;
/*  67 */     this.middleHead.pivotOffsetZ = 0.0F;
/*     */     
/*  69 */     this.leftHead.tractorBeamDistance = 90.0F;
/*  70 */     this.leftHead.tractorBeamStartSize = 0.1F;
/*  71 */     this.leftHead.tractorBeamEndSize = 5.0F;
/*  72 */     this.leftHead.tractorBeamXOffset = 0.0F;
/*  73 */     this.leftHead.tractorBeamYOffset = 8.0F;
/*  74 */     this.leftHead.tractorBeamZOffset = 0.0F;
/*  75 */     this.leftHead.animationOffset = 175.0F;
/*  76 */     this.leftHead.pivotOffsetX = -4.0F;
/*  77 */     this.leftHead.pivotOffsetY = 0.325F;
/*  78 */     this.leftHead.pivotOffsetZ = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureTentacles(ModelPart root) {
/*  84 */     this.tentacles = new TentacleModel[9];
/*  85 */     this.tentacles[0] = new TentacleModel(root.m_171324_("tentacle0"), 3.0F);
/*  86 */     (this.tentacles[0]).animationSpeed = 0.3F;
/*  87 */     (this.tentacles[0]).yRotationalOffset = (float)Math.toRadians(90.0D);
/*  88 */     (this.tentacles[0]).xRotationalOffset = (float)Math.toRadians(90.0D);
/*  89 */     (this.tentacles[0]).xAngularOffset = -0.3925F;
/*  90 */     (this.tentacles[0]).yAngularOffset = 0.17444445F;
/*  91 */     (this.tentacles[0]).reach = 3.0F;
/*  92 */     this.tentacles[1] = new TentacleModel(root.m_171324_("tentacle1"), 3.0F);
/*  93 */     (this.tentacles[1]).animationOffset = 8.0F;
/*  94 */     (this.tentacles[1]).animationSpeed = 0.3F;
/*  95 */     (this.tentacles[1]).yRotationalOffset = (float)Math.toRadians(270.0D);
/*  96 */     (this.tentacles[1]).xRotationalOffset = (float)Math.toRadians(90.0D);
/*  97 */     (this.tentacles[1]).xAngularOffset = -0.3925F;
/*  98 */     (this.tentacles[1]).yAngularOffset = -0.17444445F;
/*  99 */     (this.tentacles[1]).reach = 2.0F;
/* 100 */     this.tentacles[2] = new TentacleModel(root.m_171324_("tentacle2"), 3.0F);
/* 101 */     (this.tentacles[2]).animationOffset = 16.0F;
/* 102 */     (this.tentacles[2]).animationSpeed = 0.3F;
/* 103 */     (this.tentacles[2]).xRotationalOffset = (float)Math.toRadians(100.0D);
/* 104 */     (this.tentacles[2]).xAngularOffset = -0.3925F;
/* 105 */     (this.tentacles[2]).yAngularOffset = 0.2616667F;
/* 106 */     (this.tentacles[2]).reach = 2.5F;
/* 107 */     this.tentacles[3] = new TentacleModel(root.m_171324_("tentacle3"), 3.0F);
/* 108 */     (this.tentacles[3]).animationOffset = 9.0F;
/* 109 */     (this.tentacles[3]).animationSpeed = 0.3F;
/* 110 */     (this.tentacles[3]).yRotationalOffset = (float)Math.toRadians(320.0D);
/* 111 */     (this.tentacles[3]).xRotationalOffset = (float)Math.toRadians(90.0D);
/* 112 */     (this.tentacles[3]).xAngularOffset = -0.3925F;
/* 113 */     (this.tentacles[3]).yAngularOffset = -0.13083334F;
/* 114 */     (this.tentacles[3]).reach = 2.75F;
/* 115 */     this.tentacles[4] = new TentacleModel(root.m_171324_("tentacle4"), 3.0F);
/* 116 */     (this.tentacles[4]).animationOffset = 12.0F;
/* 117 */     (this.tentacles[4]).animationSpeed = 0.3F;
/* 118 */     (this.tentacles[4]).yRotationalOffset = (float)Math.toRadians(120.0D);
/* 119 */     (this.tentacles[4]).xRotationalOffset = (float)Math.toRadians(70.0D);
/* 120 */     (this.tentacles[4]).xAngularOffset = -0.3925F;
/* 121 */     (this.tentacles[4]).yAngularOffset = 0.2616667F;
/* 122 */     (this.tentacles[4]).reach = 3.0F;
/* 123 */     this.tentacles[5] = new TentacleModel(root.m_171324_("tentacle5"), 3.0F);
/* 124 */     (this.tentacles[5]).animationOffset = 20.0F;
/* 125 */     (this.tentacles[5]).animationSpeed = 0.5F;
/* 126 */     (this.tentacles[5]).yRotationalOffset = (float)Math.toRadians(220.0D);
/* 127 */     (this.tentacles[5]).xRotationalOffset = (float)Math.toRadians(70.0D);
/* 128 */     (this.tentacles[5]).xAngularOffset = -0.44857144F;
/* 129 */     (this.tentacles[5]).yAngularOffset = -0.2616667F;
/* 130 */     (this.tentacles[5]).reach = 2.5F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     this.tentacles[6] = new TentacleModel(root.m_171324_("tentacle6"), 2.0F);
/* 141 */     (this.tentacles[6]).animationSpeed = 0.6F;
/* 142 */     (this.tentacles[6]).yRotationalOffset = -0.3925F;
/* 143 */     (this.tentacles[6]).xRotationalOffset = -0.628F;
/* 144 */     (this.tentacles[6]).xAngularOffset = 0.3925F;
/* 145 */     (this.tentacles[6]).yAngularOffset = -0.2616667F;
/* 146 */     (this.tentacles[6]).reach = 2.0F;
/* 147 */     (this.tentacles[6]).animationOffset = 20.0F;
/* 148 */     this.tentacles[7] = new TentacleModel(root.m_171324_("tentacle7"), 2.0F);
/* 149 */     (this.tentacles[7]).animationSpeed = 0.6F;
/* 150 */     (this.tentacles[7]).yRotationalOffset = 1.256F;
/* 151 */     (this.tentacles[7]).xAngularOffset = 0.19625F;
/* 152 */     (this.tentacles[7]).yAngularOffset = -0.3925F;
/* 153 */     (this.tentacles[7]).animationOffset = 30.0F;
/* 154 */     (this.tentacles[7]).reach = 2.0F;
/* 155 */     this.tentacles[8] = new TentacleModel(root.m_171324_("tentacle8"), 2.0F);
/* 156 */     (this.tentacles[8]).animationSpeed = 0.6F;
/* 157 */     (this.tentacles[8]).yRotationalOffset = -0.19625F;
/* 158 */     (this.tentacles[8]).xRotationalOffset = -1.0466667F;
/* 159 */     (this.tentacles[8]).xAngularOffset = 0.3925F;
/* 160 */     (this.tentacles[8]).yAngularOffset = 0.098125F;
/* 161 */     (this.tentacles[8]).animationOffset = 40.0F;
/* 162 */     (this.tentacles[8]).reach = 2.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormIntermediateEvolvedDestroyerModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */