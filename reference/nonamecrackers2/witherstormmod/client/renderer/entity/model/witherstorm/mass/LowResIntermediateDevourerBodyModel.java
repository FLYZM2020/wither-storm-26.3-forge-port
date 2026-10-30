/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*     */ 
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LowResIntermediateDevourerBodyModel
/*     */ {
/*     */   public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
/*  13 */     PartDefinition body = root.m_171599_("lowResMass", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0F, -6.0F, -3.0F, 5.0F, 18.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  14 */         .m_171514_(0, 24).m_171496_(-3.0F, 12.0F, -3.0F, 5.0F, 3.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  15 */         .m_171514_(0, 24).m_171496_(-2.0F, 16.0F, 0.0F, 1.0F, 5.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  16 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, 0.0F, 1.0F, 7.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  17 */         .m_171514_(0, 24).m_171496_(-1.0F, 16.0F, -2.0F, 1.0F, 3.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  18 */         .m_171514_(0, 24).m_171496_(-1.0F, 16.0F, -3.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  19 */         .m_171514_(0, 24).m_171496_(-1.0F, 15.0F, -3.0F, 1.0F, 1.0F, 3.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  20 */         .m_171514_(0, 24).m_171496_(-2.0F, 15.0F, -3.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  21 */         .m_171514_(0, 24).m_171496_(-3.0F, 15.0F, 0.0F, 1.0F, 3.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  22 */         .m_171514_(0, 24).m_171496_(-2.0F, 15.0F, 0.0F, 1.0F, 1.0F, 3.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  23 */         .m_171514_(0, 24).m_171496_(1.0F, 15.0F, -2.0F, 1.0F, 1.0F, 2.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  24 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, -2.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  25 */         .m_171514_(0, 24).m_171496_(1.0F, 16.0F, -2.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  26 */         .m_171514_(0, 24).m_171496_(-1.0F, 15.0F, 2.0F, 1.0F, 3.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  27 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, 1.0F, 2.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171419_(0.0F, -15.0F, 0.0F));
/*     */     
/*  29 */     body.m_171599_("voxel_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -2.0F, 5.0F, 14.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 0.3927F));
/*     */     
/*  31 */     body.m_171599_("voxel_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -0.2412F, -0.1716F, 9.0F, 5.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -40.75F, 8.5F, -1.8753F, -0.0226F, -0.0843F));
/*     */     
/*  33 */     body.m_171599_("voxel_r3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-13.95F, 0.1602F, -0.0218F, 14.0F, 7.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-7.05F, -36.75F, 13.5F, -2.1992F, -0.3272F, -0.4164F));
/*     */     
/*  35 */     body.m_171599_("voxel_r4", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, 0.1602F, -0.0218F, 14.0F, 6.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -36.75F, 13.5F, -2.2689F, 0.0F, 0.0F));
/*     */     
/*  37 */     body.m_171599_("voxel_r5", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-16.05F, 2.026F, -0.1117F, 16.0F, 13.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-6.95F, -22.75F, 18.5F, -2.7597F, -0.4084F, -0.1582F));
/*     */     
/*  39 */     body.m_171599_("voxel_r6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.05F, 0.026F, -0.1117F, 7.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(6.95F, -22.75F, 18.5F, -2.7812F, 0.2457F, 0.0914F));
/*     */     
/*  41 */     body.m_171599_("voxel_r7", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -1.0001F, -2.9307F, 14.0F, 15.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -22.75F, 15.5F, -2.7925F, 0.0F, 0.0F));
/*     */     
/*  43 */     body.m_171599_("voxel_r8", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-9.0228F, 6.0021F, 0.1476F, 9.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-13.0F, -7.75F, 14.5F, 2.9907F, -0.5187F, 0.0752F));
/*     */     
/*  45 */     body.m_171599_("voxel_r9", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.0F, -1.1284F, -0.9729F, 6.0F, 16.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-7.0F, -7.75F, 15.5F, 3.0024F, -0.346F, 0.0475F));
/*     */     
/*  47 */     body.m_171599_("voxel_r10", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.05F, -0.9589F, 0.0186F, 10.0F, 16.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(6.95F, -7.75F, 16.5F, 3.0083F, 0.1903F, -0.0254F));
/*     */     
/*  49 */     body.m_171599_("voxel_r11", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -0.8284F, -0.9729F, 14.0F, 16.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -7.75F, 15.5F, 3.0107F, 0.0F, 0.0F));
/*     */     
/*  51 */     body.m_171599_("voxel_r12", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.95F, 6.1739F, -1.5475F, 7.0F, 11.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-7.05F, 7.25F, 6.5F, 2.4957F, -0.5904F, 0.3972F));
/*     */     
/*  53 */     body.m_171599_("voxel_r13", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -6.7344F, 0.0065F, 9.0F, 8.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(7.05F, -5.75F, 15.8F, 2.388F, 0.4187F, -0.2598F));
/*     */     
/*  55 */     body.m_171599_("voxel_r14", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -15.9504F, 0.1127F, 14.0F, 16.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -6.75F, 16.5F, 2.618F, 0.0F, 0.0F));
/*     */     
/*  57 */     body.m_171599_("voxel_r15", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-9.75F, -9.9012F, -0.3477F, 14.0F, 10.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -37.25F, -15.75F, -1.1348F, 0.0167F, -0.0403F));
/*     */     
/*  59 */     body.m_171599_("voxel_r16", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.25F, -9.6708F, -0.479F, 11.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(3.75F, -27.25F, -19.75F, -0.4152F, -0.3215F, 0.1384F));
/*     */     
/*  61 */     body.m_171599_("voxel_r17", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-8.05F, -9.6708F, -0.479F, 8.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-9.95F, -27.25F, -19.75F, -0.4097F, 0.2815F, -0.1201F));
/*     */     
/*  63 */     body.m_171599_("voxel_r18", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-9.75F, -10.6708F, -0.479F, 14.0F, 10.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -27.25F, -19.75F, -0.3927F, 0.0F, 0.0F));
/*     */     
/*  65 */     body.m_171599_("voxel_r19", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-13.75F, -12.2293F, -4.0004F, 18.0F, 14.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -16.25F, -14.75F, 0.1309F, 0.0F, 0.0F));
/*     */     
/*  67 */     body.m_171599_("voxel_r20", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.0F, -6.6204F, -0.1022F, 11.0F, 11.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(4.0F, 0.75F, -6.5F, 0.7365F, -0.0486F, -0.3414F));
/*     */     
/*  69 */     body.m_171599_("voxel_r21", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -0.4106F, -0.0912F, 7.0F, 8.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-11.25F, -4.0F, -10.5F, 0.8546F, 0.4894F, 0.4539F));
/*     */     
/*  71 */     body.m_171599_("voxel_r22", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.75F, -0.4106F, -0.0912F, 15.0F, 13.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, -4.0F, -10.5F, 0.6981F, 0.0F, 0.0F));
/*     */     
/*  73 */     body.m_171599_("voxel_r23", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0F, -12.4395F, -3.0793F, 15.0F, 12.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(4.0F, -15.5F, -16.5F, 0.0492F, -0.2846F, -0.0792F));
/*     */     
/*  75 */     body.m_171599_("voxel_r24", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.2193F, -14.3536F, -2.9688F, 15.0F, 13.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(4.0F, -4.5F, -7.5F, 0.6274F, -0.2168F, -0.1478F));
/*     */     
/*  77 */     body.m_171599_("voxel_r25", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.05F, -12.9753F, -3.0258F, 10.0F, 13.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-12.95F, -15.0F, -16.3F, 0.1122F, 0.492F, 0.1529F));
/*     */     
/*  79 */     body.m_171599_("voxel_r26", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-9.8676F, -12.6159F, -0.7064F, 10.0F, 13.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-12.95F, -5.0F, -10.3F, 0.7413F, 0.4652F, 0.3333F));
/*     */     
/*  81 */     body.m_171599_("voxel_r27", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.75F, -19.0F, 0.0F, 17.0F, 13.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.25F, 1.0F, -8.0F, 0.6109F, 0.0F, 0.0F));
/*     */     
/*  83 */     body.m_171599_("voxel_r28", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-0.6441F, -9.8547F, -9.0F, 5.0F, 10.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-16.5F, -2.0F, -3.0F, 0.0F, -0.5672F, -0.5236F));
/*     */     
/*  85 */     body.m_171599_("voxel_r29", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5533F, -13.7432F, -11.0F, 5.0F, 13.0F, 11.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(1.5F, -42.0F, -4.0F, 0.0F, -0.3927F, 1.8326F));
/*     */     
/*  87 */     body.m_171599_("voxel_r30", CubeListBuilder.m_171558_().m_171514_(0, 26).m_171496_(-0.5533F, -13.7432F, 0.0F, 5.0F, 13.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(1.5F, -42.0F, 5.0F, 0.0F, 0.6109F, 1.8326F));
/*     */     
/*  89 */     body.m_171599_("voxel_r31", CubeListBuilder.m_171558_().m_171514_(0, 26).m_171496_(-0.5533F, -13.7432F, -3.0F, 5.0F, 13.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(1.5F, -42.0F, -1.0F, 0.0F, 0.0F, 1.8326F));
/*     */     
/*  91 */     body.m_171599_("voxel_r32", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5036F, -12.7847F, -6.0F, 5.0F, 3.0F, 12.0F, CubeDeformation.f_171458_, texScale, texScale)
/*  92 */         .m_171514_(0, 25).m_171496_(-0.5036F, -9.7847F, -6.0F, 5.0F, 10.0F, 12.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-10.5F, -41.0F, -1.0F, 0.0F, 0.0F, 1.4835F));
/*     */     
/*  94 */     body.m_171599_("voxel_r33", CubeListBuilder.m_171558_().m_171514_(4, 29).m_171496_(-0.4222F, -9.2184F, -7.0F, 5.0F, 10.0F, 7.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-18.5F, -36.0F, -7.0F, -0.0275F, -0.3042F, 1.095F));
/*     */     
/*  96 */     body.m_171599_("voxel_r34", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.4222F, -9.2184F, -6.0F, 5.0F, 10.0F, 12.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-18.5F, -36.0F, -1.0F, 0.0F, 0.0F, 1.0036F));
/*     */     
/*  98 */     body.m_171599_("voxel_r35", CubeListBuilder.m_171558_().m_171514_(3, 28).m_171496_(0.0713F, -10.5213F, -8.0F, 5.0F, 8.0F, 8.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-26.5F, -28.0F, -7.0F, 0.0F, -0.3927F, 0.7418F));
/*     */     
/* 100 */     body.m_171599_("voxel_r36", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0713F, -10.5213F, -6.0F, 5.0F, 10.0F, 12.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-26.5F, -28.0F, -1.0F, 0.0F, 0.0F, 0.7418F));
/*     */     
/* 102 */     body.m_171599_("voxel_r37", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-0.112F, -9.5748F, -9.0F, 5.0F, 10.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-27.5F, -19.0F, -7.0F, 0.0F, -0.5411F, 0.1309F));
/*     */     
/* 104 */     body.m_171599_("voxel_r38", CubeListBuilder.m_171558_().m_171514_(3, 28).m_171496_(-0.112F, -9.5748F, 0.0F, 5.0F, 10.0F, 8.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-27.5F, -19.0F, 5.0F, 0.0F, 0.6981F, 0.1309F));
/*     */     
/* 106 */     body.m_171599_("voxel_r39", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.112F, -9.5748F, -6.0F, 5.0F, 10.0F, 12.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-27.5F, -19.0F, -1.0F, 0.0F, 0.0F, 0.1309F));
/*     */     
/* 108 */     body.m_171599_("voxel_r40", CubeListBuilder.m_171558_().m_171514_(5, 30).m_171496_(-1.18F, -10.9265F, -6.0F, 5.0F, 10.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-20.5F, -10.0F, -7.0F, 0.0F, -0.6545F, -0.5672F));
/*     */     
/* 110 */     body.m_171599_("voxel_r41", CubeListBuilder.m_171558_().m_171514_(3, 28).m_171496_(0.0851F, -7.1205F, 0.0F, 5.0F, 7.0F, 8.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-22.0F, -10.0F, 5.0F, 0.0F, 0.6545F, -0.5672F));
/*     */     
/* 112 */     body.m_171599_("voxel_r42", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.18F, -10.9265F, -6.0F, 5.0F, 10.0F, 12.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-20.5F, -10.0F, -1.0F, 0.0F, 0.0F, -0.5672F));
/*     */     
/* 114 */     body.m_171599_("voxel_r43", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.6441F, -10.8547F, 0.0F, 5.0F, 11.0F, 12.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-16.5F, -2.0F, 5.0F, 0.0F, 0.6981F, -0.5236F));
/*     */     
/* 116 */     body.m_171599_("voxel_r44", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.6441F, -9.8547F, -6.0F, 5.0F, 10.0F, 12.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-16.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.5236F));
/*     */     
/* 118 */     body.m_171599_("voxel_r45", CubeListBuilder.m_171558_().m_171514_(1, 26).m_171496_(-0.0981F, -10.8397F, -3.0F, 5.0F, 10.0F, 10.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-10.5F, 6.0F, -1.0F, 0.2615F, -0.0149F, -0.6571F));
/*     */     
/* 120 */     body.m_171599_("voxel_r46", CubeListBuilder.m_171558_().m_171514_(4, 29).m_171496_(-0.9641F, -9.3397F, 0.0F, 5.0F, 10.0F, 7.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-10.5F, 5.0F, 5.0F, 0.0F, 0.7418F, -0.672F));
/*     */     
/* 122 */     body.m_171599_("voxel_r47", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-0.9641F, -9.3397F, -3.0F, 5.0F, 10.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-10.5F, 5.0F, -1.0F, 0.0F, 0.0F, -0.672F));
/*     */     
/* 124 */     body.m_171599_("voxel_r48", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-0.9641F, 0.6603F, -3.0F, 5.0F, 10.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-10.5F, 5.0F, -1.0F, 0.0F, 0.0F, -1.0472F));
/*     */     
/* 126 */     body.m_171599_("voxel_r49", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-4.8774F, -3.5892F, -8.6519F, 5.0F, 8.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(24.3F, -13.3F, -4.0F, 0.0122F, 0.6108F, 0.414F));
/*     */     
/* 128 */     body.m_171599_("voxel_r50", CubeListBuilder.m_171558_().m_171514_(0, 25).m_171496_(-5.0471F, -7.7588F, 0.0F, 5.0F, 8.0F, 11.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(23.1F, -9.3F, 5.0F, 0.0F, -0.5236F, 0.3927F));
/*     */     
/* 130 */     body.m_171599_("voxel_r51", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-5.2319F, -7.6823F, -3.0F, 5.0F, 8.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(23.3F, -9.3F, -1.0F, 0.0F, 0.0F, 0.3927F));
/*     */     
/* 132 */     body.m_171599_("voxel_r52", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0053F, -16.0427F, -11.0F, 5.0F, 13.0F, 11.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(25.5F, -27.3F, -4.0F, 0.0F, 0.4363F, -0.7592F));
/*     */     
/* 134 */     body.m_171599_("voxel_r53", CubeListBuilder.m_171558_().m_171514_(8, 31).m_171496_(-5.0053F, -16.0427F, 9.0F, 5.0F, 12.0F, 4.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 135 */         .m_171514_(3, 26).m_171496_(-5.0053F, -16.0427F, 0.0F, 5.0F, 12.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(25.5F, -27.3F, 5.0F, 0.0F, -0.6109F, -0.7592F));
/*     */     
/* 137 */     body.m_171599_("voxel_r54", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0053F, -4.0427F, -3.0F, 5.0F, 4.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 138 */         .m_171514_(0, 24).m_171496_(-5.0053F, -16.0427F, -3.0F, 5.0F, 12.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(25.5F, -27.3F, -1.0F, 0.0F, 0.0F, -0.7592F));
/*     */     
/* 140 */     body.m_171599_("voxel_r55", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.3248F, -11.0671F, -13.0F, 5.0F, 11.0F, 13.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(26.3F, -16.3F, -4.0F, 0.0F, 0.5672F, -0.0436F));
/*     */     
/* 142 */     body.m_171599_("voxel_r56", CubeListBuilder.m_171558_().m_171514_(3, 28).m_171496_(-4.9725F, -7.9347F, 0.1591F, 5.0F, 9.0F, 8.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(23.2F, -19.3F, 11.3F, 0.0F, -0.9163F, -0.0436F));
/*     */     
/* 144 */     body.m_171599_("voxel_r57", CubeListBuilder.m_171558_().m_171514_(4, 29).m_171496_(-5.0251F, -11.054F, 0.0F, 5.0F, 11.0F, 7.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(26.0F, -16.3F, 5.0F, 0.0F, -0.3927F, -0.0436F));
/*     */     
/* 146 */     body.m_171599_("voxel_r58", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-5.3248F, -11.0671F, -3.0F, 5.0F, 11.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(26.3F, -16.3F, -1.0F, 0.0F, 0.0F, -0.0436F));
/*     */     
/* 148 */     body.m_171599_("voxel_r59", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-0.1165F, -8.1621F, -4.2182F, 5.0F, 12.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(12.5F, -9.0F, -4.0F, 0.2761F, 0.7016F, 1.1165F));
/*     */     
/* 150 */     body.m_171599_("voxel_r60", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-4.4657F, -8.2112F, 0.0F, 5.0F, 8.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(17.5F, -3.0F, 5.0F, 0.0F, -0.48F, 0.6545F));
/*     */     
/* 152 */     body.m_171599_("voxel_r61", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-4.4657F, -8.2112F, -3.0F, 5.0F, 8.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(17.5F, -3.0F, -1.0F, 0.0F, 0.0F, 0.6545F));
/*     */     
/* 154 */     body.m_171599_("voxel_r62", CubeListBuilder.m_171558_().m_171514_(3, 28).m_171496_(-4.9592F, -11.5142F, 0.0F, 5.0F, 12.0F, 8.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(9.5F, 5.0F, 5.0F, 0.0F, -0.6545F, 0.829F));
/*     */     
/* 156 */     body.m_171599_("voxel_r63", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-4.9592F, -11.5142F, -3.0F, 5.0F, 11.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(9.5F, 5.0F, -1.0F, 0.0F, 0.0F, 0.829F));
/*     */     
/* 158 */     body.m_171599_("voxel_r64", CubeListBuilder.m_171558_().m_171514_(2, 27).m_171496_(-2.5F, -11.0F, -3.0F, 5.0F, 11.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 0.9599F));
/*     */     
/* 160 */     body.m_171599_("voxel_r65", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5F, -3.0F, -1.0F, 3.0F, 5.0F, 2.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 4.0F, -6.0F, 0.1309F, 0.0F, 0.0F));
/*     */     
/* 162 */     body.m_171599_("voxel_r66", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.4965F, -8.2953F, -1.0F, 5.0F, 8.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 1.2519F, -0.2368F, 0.8109F));
/*     */     
/* 164 */     body.m_171599_("voxel_r67", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -4.2953F, -2.0F, 11.0F, 4.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/* 166 */     body.m_171599_("voxel_r68", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-4.4965F, -7.2953F, -2.0F, 7.0F, 8.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, 0.6981F));
/*     */     
/* 168 */     body.m_171599_("voxel_r69", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.0341F, -3.1877F, -4.5034F, 5.0F, 7.0F, 13.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(0.5F, 0.0F, -3.0F, 1.9222F, -0.318F, 0.8876F));
/*     */     
/* 170 */     body.m_171599_("voxel_r70", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -9.7983F, 6.0F, 7.0F, 13.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 2.2689F, 0.0F, -1.0036F));
/*     */     
/* 172 */     body.m_171599_("voxel_r71", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -5.7983F, 6.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 1.4399F, 0.0F, -1.0036F));
/*     */     
/* 174 */     body.m_171599_("voxel_r72", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5035F, -7.2953F, -2.0F, 5.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.5672F));
/*     */     
/* 176 */     body.m_171599_("voxel_r73", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -6.2953F, -2.0F, 5.0F, 7.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, -1.0943F, 0.0603F, 0.8873F));
/*     */     
/* 178 */     body.m_171599_("voxel_r74", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -6.2953F, -2.0F, 6.0F, 8.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, -1.0908F, 0.0F, -1.0036F));
/*     */     
/* 180 */     body.m_171599_("voxel_r75", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -7.2953F, -2.0F, 6.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.0036F));
/*     */     
/* 182 */     body.m_171599_("voxel_r76", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5F, -8.8659F, -2.4041F, 3.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-2.5F, 6.5F, -3.0F, 0.3295F, 0.1172F, -0.3295F));
/*     */     
/* 184 */     body.m_171599_("voxel_r77", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -3.0F, 5.0F, 13.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 13.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
/*     */     
/* 186 */     body.m_171599_("voxel_r78", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -2.0F, -2.0F, 5.0F, 2.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 187 */         .m_171514_(0, 24).m_171496_(-2.5F, -21.0F, -2.0F, 5.0F, 19.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 15.0F, 0.0F, -0.2182F, 0.0F, 0.0F));
/*     */     
/* 189 */     return body;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\LowResIntermediateDevourerBodyModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */