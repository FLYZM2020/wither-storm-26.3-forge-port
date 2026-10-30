/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.util.Mth;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.SingleHeadWitherStormModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.TentacleModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.WSHunchback3_1;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormHunchback3_1Model<T extends WitherStormEntity>
/*     */   extends SingleHeadWitherStormModel<T>
/*     */ {
/*     */   private final ModelPart base;
/*     */   private final ModelPart rightHead;
/*     */   private final ModelPart leftHead;
/*     */   private final ModelPart ribcage;
/*     */   
/*     */   public WitherStormHunchback3_1Model(ModelPart root) {
/*  30 */     super(root, 0.7F);
/*     */     
/*  32 */     this.base = root.m_171324_("witherBase");
/*  33 */     this.ribcage = this.base.m_171324_("ribcage");
/*  34 */     this.rightHead = this.base.m_171324_("right_head");
/*  35 */     this.leftHead = this.base.m_171324_("left_head");
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition(CubeDeformation def) {
/*  40 */     MeshDefinition mesh = WitherStormCommandBlockModel.createBaseMesh(SingleHeadWitherStormModel.createMesh(PartPose.f_171404_), def, false, false, false);
/*  41 */     PartDefinition root = mesh.m_171576_();
/*  42 */     WSHunchback3_1.createBodyModel(root, 1.0F);
/*  43 */     PartDefinition tentacles = root.m_171597_("tentacles");
/*  44 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 24, 24, 32 }, PartPose.m_171419_(0.0F, 0.0F, 20.0F));
/*  45 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 24, 24, 32 }, PartPose.m_171419_(0.0F, 0.0F, 20.0F));
/*  46 */     TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[] { 18, 24, 24, 24, 24, 32 }, PartPose.m_171419_(0.0F, 20.0F, 20.0F));
/*  47 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureTentacles(ModelPart root) {
/*  53 */     this.tentacles = new TentacleModel[3];
/*  54 */     this.tentacles[0] = new TentacleModel(root.m_171324_("tentacle0"), 0.5F);
/*  55 */     (this.tentacles[0]).animationSpeed = 0.5F;
/*  56 */     (this.tentacles[0]).yRotationalOffset = 4.14F;
/*  57 */     (this.tentacles[0]).xAngularOffset = -0.17444445F;
/*  58 */     (this.tentacles[0]).yAngularOffset = 0.3488889F;
/*  59 */     (this.tentacles[0]).reach = 2.0F;
/*  60 */     this.tentacles[1] = new TentacleModel(root.m_171324_("tentacle1"), 0.5F);
/*  61 */     (this.tentacles[1]).animationSpeed = 0.5F;
/*  62 */     (this.tentacles[1]).yRotationalOffset = 11.775001F;
/*  63 */     (this.tentacles[1]).xRotationalOffset = 9.859601F;
/*  64 */     (this.tentacles[1]).xAngularOffset = 0.17444445F;
/*  65 */     (this.tentacles[1]).yAngularOffset = 0.3488889F;
/*  66 */     (this.tentacles[1]).animationOffset = 10.0F;
/*  67 */     (this.tentacles[1]).reach = 2.15F;
/*  68 */     this.tentacles[2] = new TentacleModel(root.m_171324_("tentacle2"), 0.5F);
/*  69 */     (this.tentacles[2]).animationSpeed = 0.5F;
/*  70 */     (this.tentacles[2]).yRotationalOffset = 3.14F;
/*  71 */     (this.tentacles[2]).xRotationalOffset = 0.785F;
/*  72 */     (this.tentacles[2]).xAngularOffset = 0.5233334F;
/*  73 */     (this.tentacles[2]).yAngularOffset = -0.17444445F;
/*  74 */     (this.tentacles[2]).animationOffset = 32.0F;
/*  75 */     (this.tentacles[2]).reach = 1.85F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
/*  81 */     super.setupAnimations((WitherStormEntity)entity, partialTicks, tickCount, yRot, xRot);
/*     */     
/*  83 */     float f = Mth.m_14089_(tickCount * 0.1F);
/*  84 */     this.ribcage.f_104203_ = (0.065F + 0.05F * f) * 3.1415927F;
/*  85 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.leftHead, 1, partialTicks);
/*  86 */     WitherStormCommandBlockModel.setupHeadRotation((WitherStormEntity)entity, this.rightHead, 2, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/*  92 */     this.base.m_104306_(stack, consumer, packedLight, overlayTexture, r, g, b, a);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void scaleMass(PoseStack stack) {
/*  98 */     stack.m_252781_(Axis.f_252529_.m_252977_(20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void scaleTentacles(PoseStack stack, TentacleModel model) {
/* 104 */     super.scaleTentacles(stack, model);
/* 105 */     stack.m_252781_(Axis.f_252529_.m_252977_(20.0F));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormHunchback3_1Model.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */