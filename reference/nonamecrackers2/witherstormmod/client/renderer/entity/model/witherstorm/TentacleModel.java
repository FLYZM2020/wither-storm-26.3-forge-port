/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;
/*     */ 
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.util.Mth;
/*     */ 
/*     */ public class TentacleModel
/*     */ {
/*     */   public final ModelPart tentacle;
/*     */   protected final ModelPart segmentOne;
/*     */   protected final ModelPart segmentTwo;
/*     */   protected final ModelPart segmentThree;
/*     */   protected final ModelPart segmentFour;
/*     */   protected final ModelPart segmentFive;
/*     */   protected final ModelPart segmentSix;
/*     */   public float scale;
/*     */   public float xRotationalOffset;
/*     */   public float yRotationalOffset;
/*  21 */   public float animationSpeed = 1.0F;
/*     */   public float yAngularOffset;
/*     */   public float xAngularOffset;
/*     */   public float animationOffset;
/*  25 */   public float reach = 1.0F;
/*     */ 
/*     */   
/*     */   public TentacleModel(ModelPart root, float scale) {
/*  29 */     this.tentacle = root.m_171324_("base");
/*  30 */     this.segmentOne = this.tentacle.m_171324_("segment1");
/*  31 */     this.segmentTwo = this.segmentOne.m_171324_("segment2");
/*  32 */     this.segmentThree = this.segmentTwo.m_171324_("segment3");
/*  33 */     this.segmentFour = this.segmentThree.m_171324_("segment4");
/*  34 */     this.segmentFive = this.segmentFour.m_171324_("segment5");
/*  35 */     this.segmentSix = this.segmentFive.m_171324_("segment6");
/*  36 */     this.scale = scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void populateDefinition(PartDefinition root, int[] length, PartPose offset) {
/*  76 */     PartDefinition tentacle = root.m_171599_("base", CubeListBuilder.m_171558_(), offset);
/*  77 */     PartDefinition segment1 = tentacle.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(36 + 24 - length[0], 14 + 24 - length[0]).m_171506_(-6.0F, -6.0F, -length[0], 12.0F, 12.0F, length[0], false), PartPose.f_171404_);
/*  78 */     PartDefinition segment2 = segment1.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(76 + 32 - length[1], 32 + 32 - length[1]).m_171506_(-5.0F, -5.0F, -length[1], 10.0F, 10.0F, length[1], false), PartPose.m_171419_(0.0F, 0.0F, -length[0]));
/*  79 */     PartDefinition segment3 = segment2.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(76 + 32 - length[2], 33 + 32 - length[2]).m_171506_(-4.0F, -4.0F, -length[2], 8.0F, 8.0F, length[2], false), PartPose.m_171419_(0.0F, 0.0F, -length[1]));
/*  80 */     PartDefinition segment4 = segment3.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(77 + 32 - length[3], 34 + 32 - length[3]).m_171506_(-3.0F, -3.0F, -length[3], 6.0F, 6.0F, length[3], false), PartPose.m_171419_(0.0F, 0.0F, -length[2]));
/*  81 */     PartDefinition segment5 = segment4.m_171599_("segment5", CubeListBuilder.m_171558_().m_171514_(76 + 34 - length[4], 36 + 34 - length[4]).m_171506_(-2.0F, -2.0F, -length[4], 4.0F, 4.0F, length[4], false), PartPose.m_171419_(0.0F, 0.0F, -length[3]));
/*  82 */     segment5.m_171599_("segment6", CubeListBuilder.m_171558_().m_171514_(77 + 34 - length[5], 38 + 34 - length[5]).m_171506_(-1.0F, -1.0F, -length[5], 2.0F, 2.0F, length[5], false), PartPose.m_171419_(0.0F, 0.0F, -length[4]));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupAnimations(float animation, float partialTicks) {
/*  87 */     float speed = this.animationSpeed;
/*  88 */     float f = Mth.m_14089_((animation + this.animationOffset * 10.0F) * speed * 0.1F) * this.reach;
/*  89 */     float s = Mth.m_14031_((animation + this.animationOffset * 10.0F) * speed / 2.0F * 0.1F) * this.reach;
/*  90 */     this.tentacle.f_104204_ = s * f * 0.05F + this.yRotationalOffset;
/*  91 */     this.tentacle.f_104203_ = f * s * 0.05F + this.xRotationalOffset;
/*  92 */     this.segmentOne.f_104203_ = f * -0.1F;
/*  93 */     this.segmentTwo.f_104203_ = f * 0.1F + this.xAngularOffset;
/*  94 */     this.segmentThree.f_104203_ = f * 0.075F + this.xAngularOffset;
/*  95 */     this.segmentFour.f_104203_ = f * 0.05F + this.xAngularOffset;
/*  96 */     this.segmentFive.f_104203_ = f * 0.1F + this.xAngularOffset;
/*  97 */     this.segmentSix.f_104203_ = f * 0.1F + this.xAngularOffset;
/*  98 */     this.segmentTwo.f_104204_ = this.yAngularOffset;
/*  99 */     this.segmentThree.f_104204_ = this.yAngularOffset;
/* 100 */     this.segmentFour.f_104204_ = this.yAngularOffset;
/* 101 */     this.segmentFive.f_104204_ = this.yAngularOffset;
/* 102 */     this.segmentSix.f_104204_ = this.yAngularOffset;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\TentacleModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */