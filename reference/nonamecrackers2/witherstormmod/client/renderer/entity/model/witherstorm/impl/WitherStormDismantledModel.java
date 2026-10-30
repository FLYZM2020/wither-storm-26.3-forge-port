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
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.DismantledBodyModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.LowResDismantledBodyModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ public class WitherStormDismantledModel<T extends WitherStormEntity>
/*     */   extends ThreeHeadedWitherStormModel<T>
/*     */ {
/*     */   public WitherStormDismantledModel(ModelPart root) {
/*  19 */     super(root, 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition() {
/*  24 */     MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[] { PartPose.m_171419_(-22.0F, -65.0F, -40.0F), PartPose.m_171419_(0.0F, -32.0F, -23.0F), PartPose.m_171419_(32.0F, -60.0F, -24.0F) });
/*  25 */     PartDefinition root = mesh.m_171576_();
/*  26 */     DismantledBodyModel.createBodyModel(root, 0.2F);
/*     */     
/*  28 */     LowResDismantledBodyModel.createBodyModel(root, 0.3F);
/*  29 */     PartDefinition tentacles = root.m_171597_("tentacles");
/*  30 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 28, 28, 32 }, PartPose.m_171419_(-20.0F, -25.0F, 5.0F));
/*  31 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 24, 28, 38 }, PartPose.m_171419_(20.0F, -27.5F, 7.0F));
/*  32 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 28, 32, 28 }, PartPose.m_171419_(-10.0F, -30.0F, -10.0F));
/*  33 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 18, 24, 24, 24, 28 }, PartPose.m_171419_(8.0F, -34.0F, -6.0F));
/*  34 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle4", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 18, 24, 28, 32, 32 }, PartPose.m_171419_(-8.0F, -25.0F, 16.0F));
/*  35 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle5", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 20, 26, 28, 32, 28 }, PartPose.m_171419_(10.0F, -23.0F, 19.0F));
/*  36 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle6", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 20, 26, 28, 28, 24 }, PartPose.m_171419_(-2.0F, 0.0F, 0.0F));
/*  37 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacleLarge0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 16, 20, 24, 28, 32, 28 }, PartPose.m_171419_(-24.0F, -28.0F, 0.0F));
/*  38 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacleLarge1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 20, 20, 24, 24, 28, 32 }, PartPose.m_171419_(28.0F, -28.0F, 2.0F));
/*  39 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureHeads(ModelPart root, float headScale) {
/*  45 */     super.configureHeads(root, headScale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     this.rightHead.tractorBeamDistance = 90.0F;
/*  51 */     this.rightHead.tractorBeamStartSize = 0.1F;
/*  52 */     this.rightHead.tractorBeamEndSize = 5.0F;
/*  53 */     this.rightHead.tractorBeamXOffset = 0.0F;
/*  54 */     this.rightHead.tractorBeamYOffset = 8.0F;
/*  55 */     this.rightHead.tractorBeamZOffset = 0.0F;
/*  56 */     this.rightHead.animationOffset = 100.0F;
/*  57 */     this.rightHead.pivotOffsetX = -4.0F;
/*  58 */     this.rightHead.pivotOffsetY = 0.325F;
/*  59 */     this.rightHead.pivotOffsetZ = 0.0F;
/*     */     
/*  61 */     this.middleHead.tractorBeamDistance = 90.0F;
/*  62 */     this.middleHead.tractorBeamStartSize = 0.1F;
/*  63 */     this.middleHead.tractorBeamEndSize = 5.0F;
/*  64 */     this.middleHead.tractorBeamXOffset = 0.0F;
/*  65 */     this.middleHead.tractorBeamYOffset = 8.0F;
/*  66 */     this.middleHead.tractorBeamZOffset = 0.0F;
/*  67 */     this.middleHead.pivotOffsetX = -4.0F;
/*  68 */     this.middleHead.pivotOffsetY = 0.325F;
/*  69 */     this.middleHead.pivotOffsetZ = 0.0F;
/*     */     
/*  71 */     this.leftHead.tractorBeamDistance = 90.0F;
/*  72 */     this.leftHead.tractorBeamStartSize = 0.1F;
/*  73 */     this.leftHead.tractorBeamEndSize = 5.0F;
/*  74 */     this.leftHead.tractorBeamXOffset = 0.0F;
/*  75 */     this.leftHead.tractorBeamYOffset = 8.0F;
/*  76 */     this.leftHead.tractorBeamZOffset = 0.0F;
/*  77 */     this.leftHead.animationOffset = 175.0F;
/*  78 */     this.leftHead.pivotOffsetX = -4.0F;
/*  79 */     this.leftHead.pivotOffsetY = 0.325F;
/*  80 */     this.leftHead.pivotOffsetZ = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureTentacles(ModelPart root) {
/*  86 */     this.tentacles = new TentacleModel[9];
/*  87 */     this.tentacles[0] = new TentacleModel(root.m_171324_("tentacle0"), 3.0F);
/*  88 */     (this.tentacles[0]).tentacle.m_104227_(-20.0F, -25.0F, 5.0F);
/*  89 */     (this.tentacles[0]).animationSpeed = 0.2F;
/*  90 */     (this.tentacles[0]).yRotationalOffset = (float)Math.toRadians(90.0D);
/*  91 */     (this.tentacles[0]).xRotationalOffset = (float)Math.toRadians(90.0D);
/*  92 */     (this.tentacles[0]).xAngularOffset = -0.3925F;
/*  93 */     (this.tentacles[0]).yAngularOffset = 0.17444445F;
/*  94 */     (this.tentacles[0]).reach = 2.0F;
/*  95 */     this.tentacles[1] = new TentacleModel(root.m_171324_("tentacle1"), 3.0F);
/*  96 */     (this.tentacles[1]).tentacle.m_104227_(20.0F, -27.5F, 7.0F);
/*  97 */     (this.tentacles[1]).animationOffset = 8.0F;
/*  98 */     (this.tentacles[1]).animationSpeed = 0.4F;
/*  99 */     (this.tentacles[1]).yRotationalOffset = (float)Math.toRadians(270.0D);
/* 100 */     (this.tentacles[1]).xRotationalOffset = (float)Math.toRadians(90.0D);
/* 101 */     (this.tentacles[1]).xAngularOffset = -0.3925F;
/* 102 */     (this.tentacles[1]).yAngularOffset = -0.17444445F;
/* 103 */     (this.tentacles[1]).reach = 1.0F;
/* 104 */     this.tentacles[2] = new TentacleModel(root.m_171324_("tentacle2"), 3.0F);
/* 105 */     (this.tentacles[2]).tentacle.m_104227_(-10.0F, -30.0F, -10.0F);
/* 106 */     (this.tentacles[2]).animationOffset = 16.0F;
/* 107 */     (this.tentacles[2]).animationSpeed = 0.2F;
/* 108 */     (this.tentacles[2]).xRotationalOffset = (float)Math.toRadians(100.0D);
/* 109 */     (this.tentacles[2]).xAngularOffset = -0.3925F;
/* 110 */     (this.tentacles[2]).yAngularOffset = 0.2616667F;
/* 111 */     (this.tentacles[2]).reach = 1.5F;
/* 112 */     this.tentacles[3] = new TentacleModel(root.m_171324_("tentacle3"), 3.0F);
/* 113 */     (this.tentacles[3]).tentacle.m_104227_(8.0F, -34.0F, -6.0F);
/* 114 */     (this.tentacles[3]).animationOffset = 9.0F;
/* 115 */     (this.tentacles[3]).animationSpeed = 0.2F;
/* 116 */     (this.tentacles[3]).yRotationalOffset = (float)Math.toRadians(320.0D);
/* 117 */     (this.tentacles[3]).xRotationalOffset = (float)Math.toRadians(90.0D);
/* 118 */     (this.tentacles[3]).xAngularOffset = -0.3925F;
/* 119 */     (this.tentacles[3]).yAngularOffset = -0.13083334F;
/* 120 */     (this.tentacles[3]).reach = 1.75F;
/* 121 */     this.tentacles[4] = new TentacleModel(root.m_171324_("tentacle4"), 3.0F);
/* 122 */     (this.tentacles[4]).tentacle.m_104227_(-8.0F, -25.0F, 16.0F);
/* 123 */     (this.tentacles[4]).animationOffset = 12.0F;
/* 124 */     (this.tentacles[4]).animationSpeed = 0.2F;
/* 125 */     (this.tentacles[4]).yRotationalOffset = (float)Math.toRadians(120.0D);
/* 126 */     (this.tentacles[4]).xRotationalOffset = (float)Math.toRadians(70.0D);
/* 127 */     (this.tentacles[4]).xAngularOffset = -0.3925F;
/* 128 */     (this.tentacles[4]).yAngularOffset = 0.2616667F;
/* 129 */     (this.tentacles[4]).reach = 2.0F;
/* 130 */     this.tentacles[5] = new TentacleModel(root.m_171324_("tentacle5"), 3.0F);
/* 131 */     (this.tentacles[5]).tentacle.m_104227_(10.0F, -23.0F, 19.0F);
/* 132 */     (this.tentacles[5]).animationOffset = 20.0F;
/* 133 */     (this.tentacles[5]).animationSpeed = 0.4F;
/* 134 */     (this.tentacles[5]).yRotationalOffset = (float)Math.toRadians(220.0D);
/* 135 */     (this.tentacles[5]).xRotationalOffset = (float)Math.toRadians(70.0D);
/* 136 */     (this.tentacles[5]).xAngularOffset = -0.44857144F;
/* 137 */     (this.tentacles[5]).yAngularOffset = -0.2616667F;
/* 138 */     (this.tentacles[5]).reach = 1.5F;
/* 139 */     this.tentacles[6] = new TentacleModel(root.m_171324_("tentacle6"), 3.0F);
/* 140 */     (this.tentacles[6]).tentacle.m_104227_(-2.0F, 0.0F, 0.0F);
/* 141 */     (this.tentacles[6]).animationOffset = 24.0F;
/* 142 */     (this.tentacles[6]).animationSpeed = 0.15F;
/* 143 */     (this.tentacles[6]).yRotationalOffset = (float)Math.toRadians(45.0D);
/* 144 */     (this.tentacles[6]).xRotationalOffset = (float)Math.toRadians(90.0D);
/* 145 */     (this.tentacles[6]).xAngularOffset = 0.2616667F;
/* 146 */     (this.tentacles[6]).yAngularOffset = 0.3925F;
/* 147 */     (this.tentacles[6]).reach = 2.0F;
/*     */     
/* 149 */     this.tentacles[7] = new TentacleModel(root.m_171324_("tentacleLarge0"), 4.5F);
/* 150 */     (this.tentacles[7]).animationOffset = 80.0F;
/* 151 */     (this.tentacles[7]).animationSpeed = 0.25F;
/* 152 */     (this.tentacles[7]).yRotationalOffset = (float)Math.toRadians(-100.0D);
/* 153 */     (this.tentacles[7]).xRotationalOffset = (float)Math.toRadians(120.0D);
/* 154 */     (this.tentacles[7]).xAngularOffset = 0.2616667F;
/* 155 */     (this.tentacles[7]).yAngularOffset = 0.3925F;
/* 156 */     (this.tentacles[7]).reach = 2.0F;
/* 157 */     this.tentacles[8] = new TentacleModel(root.m_171324_("tentacleLarge1"), 4.5F);
/* 158 */     (this.tentacles[8]).animationOffset = 35.0F;
/* 159 */     (this.tentacles[8]).animationSpeed = 0.25F;
/* 160 */     (this.tentacles[8]).yRotationalOffset = (float)Math.toRadians(120.0D);
/* 161 */     (this.tentacles[8]).xRotationalOffset = (float)Math.toRadians(100.0D);
/* 162 */     (this.tentacles[8]).xAngularOffset = 0.3925F;
/* 163 */     (this.tentacles[8]).yAngularOffset = -0.2616667F;
/* 164 */     (this.tentacles[8]).reach = 2.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormDismantledModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */