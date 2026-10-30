/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*     */ import org.joml.Matrix3f;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeadModel<T extends LivingEntity & WitherStormBase>
/*     */ {
/*     */   protected final ModelPart head;
/*     */   protected final ModelPart upper;
/*     */   protected final ModelPart lower;
/*     */   private float jawHinge;
/*     */   public float tractorBeamXOffset;
/*     */   public float tractorBeamYOffset;
/*     */   public float tractorBeamZOffset;
/*     */   public float pivotOffsetX;
/*     */   public float pivotOffsetY;
/*     */   public float pivotOffsetZ;
/*     */   public float scale;
/*     */   public float tractorBeamStartSize;
/*     */   public float tractorBeamDistance;
/*     */   public float tractorBeamEndSize;
/*     */   public float animationOffset;
/*     */   
/*     */   public HeadModel(ModelPart root, float scale) {
/*  45 */     this.head = root;
/*  46 */     this.upper = root.m_171324_("upperJaw");
/*  47 */     this.lower = root.m_171324_("lowerJaw");
/*     */     
/*  49 */     this.scale = scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void populateDefinition(PartDefinition root) {
/*  54 */     PartDefinition upper = root.m_171599_("upperJaw", CubeListBuilder.m_171558_()
/*  55 */         .m_171514_(0, 65).m_171506_(-4.0F, -6.5F, 12.0F, 8.0F, 6.0F, 2.0F, false)
/*  56 */         .m_171514_(0, 47).m_171506_(-2.0F, -8.5F, 10.0F, 4.0F, 2.0F, 2.0F, false)
/*  57 */         .m_171514_(0, 35).m_171506_(-4.0F, -8.5F, 0.0F, 8.0F, 2.0F, 10.0F, false)
/*  58 */         .m_171514_(0, 47).m_171506_(-6.0F, -6.5F, 0.0F, 12.0F, 6.0F, 12.0F, false)
/*  59 */         .m_171514_(4, 13).m_171496_(-1.0F, -4.5F, 13.1F, 2.0F, 2.0F, 1.0F, CubeDeformation.f_171458_, 0.2F, 0.2F), 
/*  60 */         PartPose.m_171419_(0.0F, 2.5F, 0.0F));
/*  61 */     upper.m_171599_("upperTeeth", CubeListBuilder.m_171558_()
/*  62 */         .m_171514_(0, 54).m_171506_(-1.0F, -1.0F, 13.0F, 1.0F, 1.0F, 1.0F, false)
/*  63 */         .m_171514_(0, 54).m_171506_(-3.0F, -1.0F, 12.0F, 1.0F, 1.0F, 1.0F, false)
/*  64 */         .m_171514_(0, 54).m_171506_(-5.0F, -1.0F, 11.0F, 1.0F, 1.0F, 1.0F, false)
/*  65 */         .m_171514_(0, 54).m_171506_(-6.0F, -1.0F, 9.0F, 1.0F, 1.0F, 1.0F, false)
/*  66 */         .m_171514_(0, 54).m_171506_(-6.0F, -1.0F, 7.0F, 1.0F, 1.0F, 1.0F, false)
/*  67 */         .m_171514_(0, 54).m_171506_(-6.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
/*  68 */         .m_171514_(0, 54).m_171506_(-6.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, false)
/*  69 */         .m_171514_(0, 54).m_171506_(-6.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, false)
/*  70 */         .m_171514_(0, 54).m_171506_(1.0F, -1.0F, 13.0F, 1.0F, 1.0F, 1.0F, false)
/*  71 */         .m_171514_(0, 54).m_171506_(3.0F, -1.0F, 12.0F, 1.0F, 1.0F, 1.0F, false)
/*  72 */         .m_171514_(0, 54).m_171506_(4.0F, -1.0F, 10.0F, 1.0F, 1.0F, 1.0F, false)
/*  73 */         .m_171514_(0, 54).m_171506_(5.0F, -1.0F, 8.0F, 1.0F, 1.0F, 1.0F, false)
/*  74 */         .m_171514_(0, 54).m_171506_(5.0F, -1.0F, 6.0F, 1.0F, 1.0F, 1.0F, false)
/*  75 */         .m_171514_(0, 54).m_171506_(5.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
/*  76 */         .m_171514_(0, 54).m_171506_(5.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, false)
/*  77 */         .m_171514_(0, 54).m_171506_(5.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, false), 
/*  78 */         PartPose.m_171419_(0.0F, 0.5F, 0.0F));
/*  79 */     PartDefinition lower = root.m_171599_("lowerJaw", CubeListBuilder.m_171558_()
/*  80 */         .m_171514_(0, 73).m_171506_(-4.0F, 0.5F, 12.0F, 8.0F, 2.0F, 2.0F, false)
/*  81 */         .m_171514_(48, 0).m_171506_(-6.0F, 0.5F, 0.0F, 12.0F, 2.0F, 12.0F, false), 
/*  82 */         PartPose.m_171419_(0.0F, 2.5F, 0.0F));
/*  83 */     lower.m_171599_("lowerTeeth", CubeListBuilder.m_171558_()
/*  84 */         .m_171514_(0, 54).m_171506_(0.0F, -3.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
/*  85 */         .m_171514_(0, 54).m_171506_(2.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
/*  86 */         .m_171514_(0, 54).m_171506_(4.0F, -3.0F, 3.0F, 1.0F, 1.0F, 1.0F, false)
/*  87 */         .m_171514_(0, 54).m_171506_(5.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, false)
/*  88 */         .m_171514_(0, 54).m_171506_(5.0F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, false)
/*  89 */         .m_171514_(0, 54).m_171506_(5.0F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F, false)
/*  90 */         .m_171514_(0, 54).m_171506_(5.0F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, false)
/*  91 */         .m_171514_(0, 54).m_171506_(5.0F, -3.0F, -7.0F, 1.0F, 1.0F, 1.0F, false)
/*  92 */         .m_171514_(0, 54).m_171506_(-2.0F, -3.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
/*  93 */         .m_171514_(0, 54).m_171506_(-4.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
/*  94 */         .m_171514_(0, 54).m_171506_(-5.0F, -3.0F, 2.0F, 1.0F, 1.0F, 1.0F, false)
/*  95 */         .m_171514_(0, 54).m_171506_(-6.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, false)
/*  96 */         .m_171514_(0, 54).m_171506_(-6.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, false)
/*  97 */         .m_171514_(0, 54).m_171506_(-6.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, false)
/*  98 */         .m_171514_(0, 54).m_171506_(-6.0F, -3.0F, -6.0F, 1.0F, 1.0F, 1.0F, false)
/*  99 */         .m_171514_(0, 54).m_171506_(-6.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, false), 
/* 100 */         PartPose.m_171419_(0.0F, 2.5F, 8.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelPart root() {
/* 105 */     return this.head;
/*     */   }
/*     */ 
/*     */   
/*     */   public void scale(PoseStack stack) {
/* 110 */     stack.m_85841_(this.scale, this.scale, this.scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot, int head) {
/* 115 */     if (head > 0) {
/*     */       
/* 117 */       this.head.f_104204_ = (Mth.m_14179_(partialTicks, ((WitherStormBase)entity).getHeadYRotO(head), ((WitherStormBase)entity).getHeadYRot(head)) - Mth.m_14179_(partialTicks, ((LivingEntity)entity).f_20884_, ((LivingEntity)entity).f_20883_)) * 0.017453292F + 3.1416F;
/* 118 */       this.head.f_104203_ = -(Mth.m_14179_(partialTicks, ((WitherStormBase)entity).getHeadXRotO(head), ((WitherStormBase)entity).getHeadXRot(head)) * 0.017453292F);
/*     */     }
/*     */     else {
/*     */       
/* 122 */       this.head.f_104204_ = 3.1416F + yRot * 0.017453292F;
/* 123 */       this.head.f_104203_ = -xRot * 0.017453292F;
/*     */     } 
/* 125 */     float hinge = ((WitherStormBase)entity).getMouthAnimation(head, partialTicks);
/* 126 */     this.jawHinge = hinge * 0.3F;
/*     */     
/* 128 */     float ticks = ((LivingEntity)entity).f_19797_ + partialTicks;
/* 129 */     if (((WitherStormBase)entity).isDeadOrPlayingDead())
/* 130 */       ticks = 0.0F; 
/* 131 */     float f = Mth.m_14089_((ticks + this.animationOffset) * 0.1F);
/* 132 */     float d = Mth.m_14089_(this.jawHinge);
/* 133 */     this.lower.f_104203_ = d * 10.0F - 10.0F + (0.065F + 0.02F * f) * 3.1415927F - 0.5F;
/* 134 */     Random random = new Random(entity.m_19879_());
/* 135 */     boolean mirror = false;
/* 136 */     for (int i = 0; i < head; i++) {
/*     */       
/* 138 */       mirror = random.nextBoolean();
/* 139 */       if (i == head)
/*     */         break; 
/*     */     } 
/* 142 */     float s = Mth.m_14089_(((WitherStormBase)entity).getBrokenJawAnimation(head, partialTicks) * 0.3F);
/* 143 */     this.lower.f_104205_ = (s * 10.0F - 10.0F) * (mirror ? -1.0F : 1.0F);
/* 144 */     this.head.f_104205_ = ((WitherStormBase)entity).getHeadShakeAnim(head, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRenderTractorBeam(T entity, int head) {
/* 149 */     boolean flag = false;
/* 150 */     if (((WitherStormBase)entity).areOtherHeadsDisabled()) {
/* 151 */       flag = (head == 0);
/*     */     } else {
/* 153 */       flag = true;
/* 154 */     }  return (flag && !((WitherStormBase)entity).isHeadInjured(head));
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTractorBeam(T entity, PoseStack stack, MultiBufferSource buffer, int packedLightIn, float r, float g, float b, float a, float partialTicks, double tractorBeamCutoff, float endFadeAlpha) {
/* 159 */     stack.m_85836_();
/* 160 */     stack.m_252781_(Axis.f_252436_.m_252977_(-Mth.m_14189_(partialTicks, ((LivingEntity)entity).f_20884_, ((LivingEntity)entity).f_20883_)));
/* 161 */     Pose pose = entity.m_20089_();
/* 162 */     if (pose != Pose.SLEEPING) {
/*     */       
/* 164 */       float xBodyRot = Mth.m_14189_(partialTicks, ((WitherStormBase)entity).getXBodyRotO(), ((WitherStormBase)entity).getXBodyRot());
/* 165 */       stack.m_252781_(Axis.f_252529_.m_252977_(-xBodyRot));
/*     */     } 
/* 167 */     stack.m_252880_((this.head.f_104200_ + this.tractorBeamXOffset) / 8.0F * this.scale, -((this.head.f_104201_ - this.tractorBeamYOffset) / 8.0F) * this.scale, -((this.head.f_104202_ - this.tractorBeamZOffset) / 8.0F) * this.scale);
/* 168 */     stack.m_85841_(this.scale, this.scale, this.scale);
/* 169 */     stack.m_252781_(Axis.f_252403_.m_252961_(-this.head.f_104205_));
/* 170 */     stack.m_252781_(Axis.f_252436_.m_252961_(-this.head.f_104204_ - (float)Math.toRadians(90.0D)));
/* 171 */     stack.m_252781_(Axis.f_252403_.m_252961_(-this.head.f_104203_));
/* 172 */     stack.m_252880_(this.pivotOffsetX / 8.0F * this.scale, this.pivotOffsetY / 8.0F * this.scale, this.pivotOffsetZ / 8.0F * this.scale);
/* 173 */     PoseStack.Pose entry = stack.m_85850_();
/* 174 */     Matrix4f matrix4f = entry.m_252922_();
/* 175 */     Matrix3f matrix3f = entry.m_252943_();
/* 176 */     VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
/* 177 */     float distance = this.tractorBeamDistance;
/* 178 */     if (tractorBeamCutoff != -1.0D)
/* 179 */       distance = (float)tractorBeamCutoff; 
/* 180 */     float distanceScale = this.tractorBeamEndSize / this.tractorBeamDistance;
/* 181 */     float endSize = distanceScale * distance;
/*     */ 
/*     */     
/* 184 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 185 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 186 */     builder.m_252986_(matrix4f, -distance, -endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 187 */     builder.m_252986_(matrix4f, -distance, endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */     
/* 189 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 190 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 191 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 192 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */     
/* 194 */     builder.m_252986_(matrix4f, -distance, endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 195 */     builder.m_252986_(matrix4f, -distance, -endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 196 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 197 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     builder.m_252986_(matrix4f, -distance, endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 205 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 206 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 207 */     builder.m_252986_(matrix4f, -distance, endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */     
/* 209 */     builder.m_252986_(matrix4f, -distance, -endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 210 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 211 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 212 */     builder.m_252986_(matrix4f, -distance, -endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */ 
/*     */     
/* 215 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 216 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 217 */     builder.m_252986_(matrix4f, -distance, -endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 218 */     builder.m_252986_(matrix4f, -distance, endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */     
/* 220 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 221 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 222 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 223 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */     
/* 225 */     builder.m_252986_(matrix4f, -distance, endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 226 */     builder.m_252986_(matrix4f, -distance, -endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 227 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 228 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     builder.m_252986_(matrix4f, -distance, endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 236 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 237 */     builder.m_252986_(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 238 */     builder.m_252986_(matrix4f, -distance, endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */     
/* 240 */     builder.m_252986_(matrix4f, -distance, -endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 241 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 242 */     builder.m_252986_(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 243 */     builder.m_252986_(matrix4f, -distance, -endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */     
/* 245 */     stack.m_85849_();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\HeadModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */