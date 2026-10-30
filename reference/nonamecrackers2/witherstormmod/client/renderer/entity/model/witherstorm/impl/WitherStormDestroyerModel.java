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
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.DestroyerBodyModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.LowResDestroyerBodyModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ public class WitherStormDestroyerModel<T extends WitherStormEntity>
/*     */   extends ThreeHeadedWitherStormModel<T>
/*     */ {
/*     */   public WitherStormDestroyerModel(ModelPart root) {
/*  19 */     super(root, 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition() {
/*  24 */     MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[] { PartPose.m_171419_(-22.0F, -65.0F, -40.0F), PartPose.m_171419_(0.0F, -32.0F, -23.0F), PartPose.m_171419_(32.0F, -60.0F, -24.0F) });
/*  25 */     PartDefinition root = mesh.m_171576_();
/*  26 */     DestroyerBodyModel.createBodyModel(root, 0.2F);
/*  27 */     LowResDestroyerBodyModel.createBodyModel(root, 0.3F);
/*  28 */     PartDefinition tentacles = root.m_171597_("tentacles");
/*  29 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 23, 28, 28, 28, 32, 32 }, PartPose.m_171419_(-10.0F, -30.0F, 0.0F));
/*  30 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 23, 28, 28, 28, 32, 32 }, PartPose.m_171419_(30.0F, -115.0F, -10.0F));
/*  31 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 23, 28, 28, 28, 32, 32 }, PartPose.m_171419_(10.0F, -40.0F, 10.0F));
/*  32 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 23, 28, 28, 28, 32, 32 }, PartPose.m_171419_(-50.0F, -100.0F, 0.0F));
/*  33 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle4", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 23, 28, 28, 28, 32, 32 }, PartPose.m_171419_(-10.0F, -95.0F, 15.0F));
/*  34 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureHeads(ModelPart part, float headScale) {
/*  40 */     super.configureHeads(part, headScale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     this.rightHead.tractorBeamDistance = 90.0F;
/*  46 */     this.rightHead.tractorBeamStartSize = 0.1F;
/*  47 */     this.rightHead.tractorBeamEndSize = 5.0F;
/*  48 */     this.rightHead.tractorBeamXOffset = 0.0F;
/*  49 */     this.rightHead.tractorBeamYOffset = 8.0F;
/*  50 */     this.rightHead.tractorBeamZOffset = 0.0F;
/*  51 */     this.rightHead.animationOffset = 100.0F;
/*  52 */     this.rightHead.pivotOffsetX = -4.0F;
/*  53 */     this.rightHead.pivotOffsetY = 0.325F;
/*  54 */     this.rightHead.pivotOffsetZ = 0.0F;
/*     */     
/*  56 */     this.middleHead.tractorBeamDistance = 90.0F;
/*  57 */     this.middleHead.tractorBeamStartSize = 0.1F;
/*  58 */     this.middleHead.tractorBeamEndSize = 5.0F;
/*  59 */     this.middleHead.tractorBeamXOffset = 0.0F;
/*  60 */     this.middleHead.tractorBeamYOffset = 8.0F;
/*  61 */     this.middleHead.tractorBeamZOffset = 0.0F;
/*  62 */     this.middleHead.pivotOffsetX = -4.0F;
/*  63 */     this.middleHead.pivotOffsetY = 0.325F;
/*  64 */     this.middleHead.pivotOffsetZ = 0.0F;
/*     */     
/*  66 */     this.leftHead.tractorBeamDistance = 90.0F;
/*  67 */     this.leftHead.tractorBeamStartSize = 0.1F;
/*  68 */     this.leftHead.tractorBeamEndSize = 5.0F;
/*  69 */     this.leftHead.tractorBeamXOffset = 0.0F;
/*  70 */     this.leftHead.tractorBeamYOffset = 8.0F;
/*  71 */     this.leftHead.tractorBeamZOffset = 0.0F;
/*  72 */     this.leftHead.animationOffset = 175.0F;
/*  73 */     this.leftHead.pivotOffsetX = -4.0F;
/*  74 */     this.leftHead.pivotOffsetY = 0.325F;
/*  75 */     this.leftHead.pivotOffsetZ = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureTentacles(ModelPart root) {
/*  81 */     this.tentacles = new TentacleModel[5];
/*  82 */     this.tentacles[0] = new TentacleModel(root.m_171324_("tentacle0"), 2.0F);
/*  83 */     (this.tentacles[0]).animationSpeed = 0.6F;
/*  84 */     (this.tentacles[0]).yRotationalOffset = 1.57F;
/*  85 */     (this.tentacles[0]).xRotationalOffset = 0.3925F;
/*  86 */     (this.tentacles[0]).xAngularOffset = -0.19625F;
/*  87 */     (this.tentacles[0]).yAngularOffset = -0.19625F;
/*  88 */     (this.tentacles[0]).reach = 2.0F;
/*  89 */     this.tentacles[1] = new TentacleModel(root.m_171324_("tentacle1"), 2.0F);
/*  90 */     (this.tentacles[1]).animationSpeed = 0.6F;
/*  91 */     (this.tentacles[1]).yRotationalOffset = -0.3925F;
/*  92 */     (this.tentacles[1]).xRotationalOffset = -0.628F;
/*  93 */     (this.tentacles[1]).xAngularOffset = 0.3925F;
/*  94 */     (this.tentacles[1]).yAngularOffset = -0.2616667F;
/*  95 */     (this.tentacles[1]).reach = 2.0F;
/*  96 */     (this.tentacles[1]).animationOffset = 20.0F;
/*  97 */     this.tentacles[2] = new TentacleModel(root.m_171324_("tentacle2"), 2.0F);
/*  98 */     (this.tentacles[2]).animationSpeed = 0.6F;
/*  99 */     (this.tentacles[2]).yRotationalOffset = -1.7444445F;
/* 100 */     (this.tentacles[2]).xAngularOffset = 0.3925F;
/* 101 */     (this.tentacles[2]).yAngularOffset = -0.19625F;
/* 102 */     (this.tentacles[2]).reach = 2.0F;
/* 103 */     (this.tentacles[2]).animationOffset = 10.0F;
/* 104 */     this.tentacles[3] = new TentacleModel(root.m_171324_("tentacle3"), 2.0F);
/* 105 */     (this.tentacles[3]).animationSpeed = 0.6F;
/* 106 */     (this.tentacles[3]).yRotationalOffset = 1.256F;
/* 107 */     (this.tentacles[3]).xAngularOffset = -0.5233334F;
/* 108 */     (this.tentacles[3]).yAngularOffset = 0.3925F;
/* 109 */     (this.tentacles[3]).animationOffset = 30.0F;
/* 110 */     (this.tentacles[3]).reach = 2.0F;
/* 111 */     this.tentacles[4] = new TentacleModel(root.m_171324_("tentacle4"), 2.0F);
/* 112 */     (this.tentacles[4]).animationSpeed = 0.6F;
/* 113 */     (this.tentacles[4]).yRotationalOffset = -0.19625F;
/* 114 */     (this.tentacles[4]).xRotationalOffset = -1.0466667F;
/* 115 */     (this.tentacles[4]).xAngularOffset = 0.2616667F;
/* 116 */     (this.tentacles[4]).yAngularOffset = 0.098125F;
/* 117 */     (this.tentacles[4]).animationOffset = 40.0F;
/* 118 */     (this.tentacles[4]).reach = 2.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormDestroyerModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */