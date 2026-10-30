/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model;
/*     */ 
/*     */ import net.minecraft.client.model.AnimationUtils;
/*     */ import net.minecraft.client.model.HumanoidModel;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ 
/*     */ public class WitheredSymbiontModel<T extends WitheredSymbiontEntity>
/*     */   extends HumanoidModel<T> {
/*     */   private final ModelPart tentacle;
/*     */   private final ModelPart segment1;
/*     */   private final ModelPart segment2;
/*     */   private final ModelPart segment3;
/*     */   private final ModelPart segment4;
/*     */   private final ModelPart tentacle2;
/*     */   private final ModelPart segment5;
/*     */   private final ModelPart segment6;
/*     */   private final ModelPart segment7;
/*     */   private final ModelPart segment8;
/*     */   private final ModelPart tentacle3;
/*     */   private final ModelPart segment9;
/*     */   private final ModelPart segment10;
/*     */   private final ModelPart segment11;
/*     */   private final ModelPart segment12;
/*     */   private float crouchAnim;
/*     */   
/*     */   public WitheredSymbiontModel(ModelPart root) {
/*  37 */     super(root);
/*     */     
/*  39 */     ModelPart body = root.m_171324_("body");
/*  40 */     this.tentacle = body.m_171324_("tentacle");
/*  41 */     this.segment1 = this.tentacle.m_171324_("segment1");
/*  42 */     this.segment2 = this.segment1.m_171324_("segment2");
/*  43 */     this.segment3 = this.segment2.m_171324_("segment3");
/*  44 */     this.segment4 = this.segment3.m_171324_("segment4");
/*  45 */     this.tentacle2 = body.m_171324_("tentacle2");
/*  46 */     this.segment5 = this.tentacle2.m_171324_("segment1");
/*  47 */     this.segment6 = this.segment5.m_171324_("segment2");
/*  48 */     this.segment7 = this.segment6.m_171324_("segment3");
/*  49 */     this.segment8 = this.segment7.m_171324_("segment4");
/*  50 */     this.tentacle3 = body.m_171324_("tentacle3");
/*  51 */     this.segment9 = this.tentacle3.m_171324_("segment1");
/*  52 */     this.segment10 = this.segment9.m_171324_("segment2");
/*  53 */     this.segment11 = this.segment10.m_171324_("segment3");
/*  54 */     this.segment12 = this.segment11.m_171324_("segment4");
/*  55 */     this.f_102809_.f_104207_ = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition() {
/*  60 */     MeshDefinition mesh = HumanoidModel.m_170681_(CubeDeformation.f_171458_, 0.0F);
/*  61 */     PartDefinition root = mesh.m_171576_();
/*     */     
/*  63 */     root.m_171599_("left_leg", CubeListBuilder.m_171558_().m_171514_(16, 48).m_171481_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.m_171419_(1.9F, 12.0F, 0.0F));
/*  64 */     PartDefinition body = root.m_171597_("body");
/*  65 */     body.m_171599_("mass1", CubeListBuilder.m_171558_().m_171514_(36, 32).m_171506_(-4.5F, -4.5F, -1.5F, 6.0F, 9.0F, 3.0F, false), PartPose.m_171423_(1.2478F, 5.7655F, 2.1684F, -0.0783F, -0.1909F, -0.2221F));
/*  66 */     body.m_171599_("mass2", CubeListBuilder.m_171558_().m_171514_(32, 4).m_171506_(-3.5F, -2.5F, -2.5F, 7.0F, 9.0F, 3.0F, false), PartPose.m_171423_(-0.6213F, 2.8595F, -1.0036F, 0.2661F, -0.1641F, 0.244F));
/*  67 */     body.m_171599_("mass3", CubeListBuilder.m_171558_().m_171514_(0, 32).m_171506_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, false), PartPose.m_171423_(-2.0355F, 9.8385F, 1.8323F, -2.5517F, -0.5708F, 1.7651F));
/*  68 */     body.m_171599_("mass4", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.5F, -0.5F, -4.5F, 5.0F, 5.0F, 5.0F, false), PartPose.m_171423_(1.5F, 6.5F, -1.5F, 0.6346F, -0.678F, -0.4326F));
/*  69 */     PartDefinition rightLeg = root.m_171597_("right_leg");
/*  70 */     rightLeg.m_171599_("mass5", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.5F, -2.5F, 1.5F, 5.0F, 5.0F, 5.0F, false), PartPose.m_171423_(-1.5F, 7.5F, -1.5F, 0.0406F, 0.4854F, -1.2322F));
/*  71 */     rightLeg.m_171599_("mass6", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.m_171423_(-1.5F, 7.5F, -1.5F, 0.6929F, 0.4557F, 0.3503F));
/*  72 */     rightLeg.m_171599_("mass7", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-1.5F, -3.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.m_171423_(-1.5F, 3.5F, -1.5F, 0.432F, -0.8648F, -0.6805F));
/*  73 */     PartDefinition rightArm = root.m_171597_("right_arm");
/*  74 */     rightArm.m_171599_("mass8", CubeListBuilder.m_171558_().m_171514_(0, 32).m_171506_(-2.5F, -1.5F, -1.5F, 4.0F, 4.0F, 4.0F, false), PartPose.m_171423_(-1.2211F, 4.1554F, 1.9027F, 1.6619F, -0.8156F, -1.5077F));
/*  75 */     rightArm.m_171599_("mass9", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.0F, 2.0F, 21.0F, 5.0F, 5.0F, 5.0F, false), PartPose.m_171423_(5.0F, 22.0F, 0.0F, 1.7759F, 0.1628F, -0.3449F));
/*  76 */     rightArm.m_171599_("mass10", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.0F, -15.0F, 12.0F, 5.0F, 5.0F, 5.0F, false), PartPose.m_171423_(5.0F, 22.0F, 0.0F, 0.9318F, -0.52F, -0.4963F));
/*  77 */     PartDefinition head = root.m_171597_("head");
/*  78 */     head.m_171599_("mass11", CubeListBuilder.m_171558_().m_171514_(0, 32).m_171506_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, false), PartPose.m_171423_(-5.2146F, -1.96F, 2.8784F, 3.1231F, -0.7686F, -1.9387F));
/*  79 */     head.m_171599_("mass12", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-1.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.m_171423_(-4.7943F, -4.4645F, -3.6255F, -2.4784F, -0.4058F, -2.4209F));
/*  80 */     head.m_171599_("mass13", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-1.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.m_171423_(-2.5F, -7.5F, 2.5F, -2.4343F, -0.4891F, 2.7597F));
/*     */     
/*  82 */     PartDefinition tentacle = body.m_171599_("tentacle", CubeListBuilder.m_171558_(), PartPose.m_171419_(-3.5F, 9.5F, 0.0F));
/*  83 */     PartDefinition segment1 = tentacle.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(54, 30).m_171506_(-1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 8.0F, false), PartPose.f_171404_);
/*  84 */     PartDefinition segment2 = segment1.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(72, 33).m_171506_(-1.3F, -1.3F, 0.0F, 2.6F, 2.6F, 9.0F, false), PartPose.m_171419_(0.0F, 0.0F, 9.0F));
/*  85 */     PartDefinition segment3 = segment2.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(68, 19).m_171506_(-0.9F, -0.9F, 0.0F, 1.8F, 1.8F, 11.0F, false), PartPose.m_171419_(0.0F, 0.0F, 9.0F));
/*  86 */     segment3.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(58, 0).m_171506_(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 16.0F, false), PartPose.m_171419_(0.0F, 0.0F, 11.0F));
/*     */     
/*  88 */     PartDefinition tentacle2 = body.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.m_171419_(2.5F, 10.5F, 0.0F));
/*  89 */     PartDefinition segment5 = tentacle2.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(54, 30).m_171506_(-1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 8.0F, false), PartPose.f_171404_);
/*  90 */     PartDefinition segment6 = segment5.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(72, 33).m_171506_(-1.3F, -1.3F, 0.0F, 2.6F, 2.6F, 9.0F, false), PartPose.m_171419_(0.0F, 0.0F, 9.0F));
/*  91 */     PartDefinition segment7 = segment6.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(68, 19).m_171506_(-0.9F, -0.9F, 0.0F, 1.8F, 1.8F, 11.0F, false), PartPose.m_171419_(0.0F, 0.0F, 9.0F));
/*  92 */     segment7.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(58, 0).m_171506_(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 16.0F, false), PartPose.m_171419_(0.0F, 0.0F, 11.0F));
/*     */     
/*  94 */     PartDefinition tentacle3 = body.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.m_171419_(-0.5F, 0.5F, 0.0F));
/*  95 */     PartDefinition segment9 = tentacle3.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(54, 30).m_171506_(-1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 8.0F, false), PartPose.f_171404_);
/*  96 */     PartDefinition segment10 = segment9.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(72, 33).m_171506_(-1.3F, -1.3F, 0.0F, 2.6F, 2.6F, 9.0F, false), PartPose.m_171419_(0.0F, 0.0F, 9.0F));
/*  97 */     PartDefinition segment11 = segment10.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(68, 19).m_171506_(-0.9F, -0.9F, 0.0F, 1.8F, 1.8F, 11.0F, false), PartPose.m_171419_(0.0F, 0.0F, 9.0F));
/*  98 */     segment11.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(58, 0).m_171506_(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 16.0F, false), PartPose.m_171419_(0.0F, 0.0F, 11.0F));
/*     */     
/* 100 */     return LayerDefinition.m_171565_(mesh, 96, 96);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void prepareMobModel(T entity, float p_212843_2_, float p_212843_3_, float partialTicks) {
/* 106 */     super.m_6839_((LivingEntity)entity, p_212843_2_, p_212843_3_, partialTicks);
/*     */     
/* 108 */     float tickCount = entity.isVulnerable() ? 0.0F : (((WitheredSymbiontEntity)entity).f_19797_ + partialTicks);
/*     */     
/* 110 */     float additionalAnim = ((WitheredSymbiontEntity)entity).f_267362_.m_267590_(partialTicks);
/* 111 */     animateTentacle(tickCount, additionalAnim, this.tentacle, this.segment1, this.segment2, this.segment3, this.segment4, 0.0F, 0.4F, 1.5F, 0.0F, 0.0F, -30.0F, 10.0F);
/* 112 */     animateTentacle(tickCount, additionalAnim, this.tentacle2, this.segment5, this.segment6, this.segment7, this.segment8, 20.0F, 0.4F, 1.5F, 0.0F, 0.0F, 40.0F, 15.0F);
/* 113 */     animateTentacle(tickCount, additionalAnim, this.tentacle3, this.segment9, this.segment10, this.segment11, this.segment12, 40.0F, 0.4F, 1.5F, 0.0F, 0.0F, -10.0F, 50.0F);
/*     */     
/* 115 */     this.crouchAnim = entity.getVulnerableAnim(partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float p_225597_2_, float p_225597_3_, float tickCount, float p_225597_5_, float p_225597_6_) {
/* 121 */     super.m_6973_((LivingEntity)entity, p_225597_2_, p_225597_3_, tickCount, p_225597_5_, p_225597_6_);
/*     */     
/* 123 */     float tick = entity.isVulnerable() ? 0.0F : tickCount;
/*     */     
/* 125 */     float f = Mth.m_14031_(this.f_102608_ * 3.1415927F);
/* 126 */     float f1 = Mth.m_14031_((1.0F - (1.0F - this.f_102608_) * (1.0F - this.f_102608_)) * 3.1415927F);
/* 127 */     this.f_102811_.f_104205_ = 0.0F;
/* 128 */     this.f_102812_.f_104205_ = 0.0F;
/* 129 */     this.f_102811_.f_104204_ = -(0.1F - f * 0.6F);
/* 130 */     this.f_102812_.f_104204_ = 0.1F - f * 0.6F;
/* 131 */     float f2 = -3.1415927F / (entity.m_5912_() ? 1.5F : 2.25F);
/* 132 */     this.f_102811_.f_104203_ = f2;
/* 133 */     this.f_102812_.f_104203_ = f2;
/* 134 */     this.f_102811_.f_104203_ += f * 1.2F - f1 * 0.4F;
/* 135 */     this.f_102812_.f_104203_ += f * 1.2F - f1 * 0.4F;
/* 136 */     AnimationUtils.m_102082_(this.f_102811_, this.f_102812_, tick);
/*     */     
/* 138 */     this.f_102810_.f_104203_ += this.crouchAnim;
/* 139 */     this.f_102811_.f_104203_ += this.crouchAnim;
/* 140 */     this.f_102812_.f_104203_ += this.crouchAnim;
/* 141 */     this.f_102813_.f_104202_ += this.crouchAnim * 10.5F;
/* 142 */     this.f_102814_.f_104202_ += this.crouchAnim * 10.5F;
/* 143 */     this.f_102813_.f_104201_ += this.crouchAnim * 0.4F;
/* 144 */     this.f_102814_.f_104201_ += this.crouchAnim * 0.4F;
/* 145 */     this.f_102808_.f_104201_ += this.crouchAnim * 2.0F;
/* 146 */     this.f_102810_.f_104201_ += this.crouchAnim * 3.0F;
/* 147 */     this.f_102812_.f_104201_ += this.crouchAnim * 3.0F;
/* 148 */     this.f_102811_.f_104201_ += this.crouchAnim * 3.0F;
/* 149 */     this.f_102812_.f_104202_ += this.crouchAnim * 2.0F;
/* 150 */     this.f_102811_.f_104202_ += this.crouchAnim * 2.0F;
/*     */     
/* 152 */     if (entity.isCastingSpell() || entity.isSummoningMobs() || entity.m_21224_() || entity.hasAttackDelay()) {
/*     */       
/* 154 */       this.f_102812_.f_104204_ = -0.4F - 0.8F * Mth.m_14031_(tickCount * 0.5F);
/* 155 */       this.f_102811_.f_104204_ = -0.4F - 0.8F * -Mth.m_14031_(tickCount * 0.5F);
/* 156 */       this.f_102812_.f_104203_ = -2.5F;
/* 157 */       this.f_102811_.f_104203_ = -2.5F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void animateTentacle(float animation, float additionalAnim, ModelPart base, ModelPart segment1, ModelPart segment2, ModelPart segment3, ModelPart segment4, float offset, float speed, float reach, float xRotOffset, float yRotOffset, float xAngleOffset, float yAngleOffset) {
/* 163 */     float f = Mth.m_14089_((animation + offset * 10.0F) * speed * 0.1F) * reach + Mth.m_14089_(additionalAnim);
/* 164 */     float s = Mth.m_14031_((animation + offset * 10.0F) * speed / 2.0F * 0.1F) * reach + Mth.m_14031_(additionalAnim);
/* 165 */     float yRotOffRadians = (float)Math.toRadians(yRotOffset);
/* 166 */     float xRotOffRadians = (float)Math.toRadians(xRotOffset);
/* 167 */     float xAngleOffRadians = (float)Math.toRadians(yAngleOffset);
/* 168 */     float yAngleOffRadians = (float)Math.toRadians(xAngleOffset);
/* 169 */     base.f_104204_ = s * f * 0.05F + yRotOffRadians;
/* 170 */     base.f_104203_ = f * s * 0.05F + xRotOffRadians;
/* 171 */     segment1.f_104203_ = f * -0.1F;
/* 172 */     segment2.f_104203_ = f * 0.1F + xAngleOffRadians;
/* 173 */     segment3.f_104203_ = f * 0.075F + xAngleOffRadians;
/* 174 */     segment4.f_104203_ = f * 0.05F + xAngleOffRadians;
/* 175 */     segment1.f_104204_ = s * 0.1F + yAngleOffRadians;
/* 176 */     segment2.f_104204_ = s * 0.075F + yAngleOffRadians;
/* 177 */     segment3.f_104204_ = s * 0.05F + yAngleOffRadians;
/* 178 */     segment4.f_104204_ = s * 0.01F + yAngleOffRadians;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
/* 183 */     modelRenderer.f_104203_ = x;
/* 184 */     modelRenderer.f_104204_ = y;
/* 185 */     modelRenderer.f_104205_ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\WitheredSymbiontModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */