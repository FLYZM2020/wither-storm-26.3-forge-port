/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*     */ 
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LowResTornEvolvedDevourerBodyModel
/*     */ {
/*     */   public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
/*  13 */     PartDefinition body = root.m_171599_("lowResMass", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0F, -6.0F, -3.0F, 5.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  14 */         .m_171514_(0, 24).m_171496_(-3.0F, 12.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  15 */         .m_171514_(0, 24).m_171496_(-2.0F, 16.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  16 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  17 */         .m_171514_(0, 24).m_171496_(-1.0F, 16.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  18 */         .m_171514_(0, 24).m_171496_(-1.0F, 16.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  19 */         .m_171514_(0, 24).m_171496_(-1.0F, 15.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  20 */         .m_171514_(0, 24).m_171496_(-2.0F, 15.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  21 */         .m_171514_(0, 24).m_171496_(-3.0F, 15.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  22 */         .m_171514_(0, 24).m_171496_(-2.0F, 15.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  23 */         .m_171514_(0, 24).m_171496_(1.0F, 15.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  24 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  25 */         .m_171514_(0, 24).m_171496_(1.0F, 16.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  26 */         .m_171514_(0, 24).m_171496_(-1.0F, 15.0F, 2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  27 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171419_(0.0F, -15.0F, 0.0F));
/*     */     
/*  29 */     body.m_171599_("part1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -2.0F, 5.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 0.3927F));
/*     */     
/*  31 */     body.m_171599_("part2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-12.9838F, -13.0207F, 0.0017F, 13.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-18.75F, -1.85F, 21.7F, 1.8162F, -0.1854F, 0.5843F));
/*     */     
/*  33 */     body.m_171599_("part3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-19.25F, -15.214F, -3.1328F, 13.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.25F, 2.25F, 23.5F, 1.8497F, -0.1286F, 0.371F));
/*     */     
/*  35 */     body.m_171599_("part4", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(6.25F, -15.214F, -3.1328F, 13.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, 2.25F, 23.5F, 1.8497F, 0.1286F, -0.371F));
/*     */     
/*  37 */     body.m_171599_("part5", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -15.214F, -0.1328F, 14.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, 2.25F, 23.5F, 2.0508F, 0.0F, 0.0F));
/*     */     
/*  39 */     body.m_171599_("part6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-13.9513F, -15.9413F, 0.013F, 14.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-19.15F, -19.05F, 30.65F, 2.5743F, -0.5406F, 0.2977F));
/*     */     
/*  41 */     body.m_171599_("part7", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-19.25F, -2.0646F, -2.0619F, 12.0F, 2.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  42 */         .m_171514_(0, 24).m_171480_().m_171496_(-19.25F, -20.0646F, -2.0619F, 12.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.25F, -19.25F, 31.25F, 2.6589F, -0.123F, 0.045F));
/*     */     
/*  44 */     body.m_171599_("part8", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(12.25F, -24.0646F, -3.5619F, 14.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -19.25F, 31.25F, 2.6142F, 0.0112F, -0.4007F));
/*     */     
/*  46 */     body.m_171599_("part9", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(7.25F, -2.0646F, -2.0619F, 12.0F, 2.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  47 */         .m_171514_(0, 24).m_171496_(7.25F, -20.0646F, -2.0619F, 12.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -19.25F, 31.25F, 2.6589F, 0.123F, -0.045F));
/*     */     
/*  49 */     body.m_171599_("part10", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -5.0646F, -0.0619F, 14.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  50 */         .m_171514_(0, 24).m_171496_(-6.75F, -23.0646F, -0.0619F, 14.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -19.25F, 31.25F, 2.7925F, 0.0F, 0.0F));
/*     */     
/*  52 */     body.m_171599_("part11", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -5.8678F, -0.0087F, 11.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -25.05F, 31.05F, -3.0955F, 0.2181F, 0.0155F));
/*     */     
/*  54 */     body.m_171599_("part12", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-16.9283F, 0.0265F, -0.2188F, 17.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.95F, -19.15F, 31.05F, -3.1327F, -0.4793F, -0.0291F));
/*     */     
/*  56 */     body.m_171599_("part13", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -11.076F, -0.0816F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  57 */         .m_171514_(0, 24).m_171496_(1.25F, -11.076F, -0.0816F, 18.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  58 */         .m_171514_(0, 24).m_171496_(-18.75F, -11.076F, -0.0816F, 18.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -30.25F, 30.75F, -3.098F, 0.0F, 0.0F));
/*     */     
/*  60 */     body.m_171599_("part14", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, 18.0412F, -0.0476F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  61 */         .m_171514_(0, 24).m_171496_(-0.75F, 0.0412F, -0.0476F, 2.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  62 */         .m_171514_(0, 24).m_171496_(-18.75F, 18.0412F, -0.0476F, 18.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  63 */         .m_171514_(0, 24).m_171496_(-18.75F, 0.0412F, -0.0476F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  64 */         .m_171514_(0, 24).m_171496_(1.25F, 18.0412F, -0.0476F, 18.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  65 */         .m_171514_(0, 24).m_171496_(1.25F, 0.0412F, -0.0476F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -57.05F, 33.25F, -2.4958F, 0.0F, 0.0F));
/*     */     
/*  67 */     body.m_171599_("part15", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-0.05F, 18.0412F, -0.0476F, 18.0F, 2.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  68 */         .m_171514_(0, 24).m_171480_().m_171496_(-0.05F, 0.0412F, -0.0476F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(19.05F, -57.05F, 33.25F, -2.5705F, 0.2768F, 0.2154F));
/*     */     
/*  70 */     body.m_171599_("part16", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-17.95F, 18.0412F, -0.0476F, 18.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  71 */         .m_171514_(0, 24).m_171496_(-17.95F, 0.0412F, -0.0476F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -57.05F, 33.25F, -2.4657F, -0.2767F, -0.2156F));
/*     */     
/*  73 */     body.m_171599_("part17", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.25F, 18.1476F, -0.0853F, 18.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  74 */         .m_171514_(0, 24).m_171496_(-0.75F, 18.1476F, -0.0853F, 2.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  75 */         .m_171514_(0, 24).m_171496_(-18.75F, 18.1476F, -0.0853F, 18.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  76 */         .m_171514_(0, 24).m_171496_(-0.75F, 0.1476F, -0.0853F, 2.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  77 */         .m_171514_(0, 24).m_171496_(1.25F, 0.1476F, -0.0853F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  78 */         .m_171514_(0, 24).m_171496_(-18.75F, 0.1476F, -0.0853F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -30.05F, 30.85F, 3.0543F, 0.0F, 0.0F));
/*     */     
/*  80 */     body.m_171599_("part18", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(0.05F, 16.1476F, -0.0853F, 18.0F, 1.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  81 */         .m_171514_(0, 24).m_171480_().m_171496_(0.05F, -1.8524F, -0.0853F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  82 */         .m_171514_(0, 24).m_171480_().m_171496_(18.05F, 17.1476F, -0.0853F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  83 */         .m_171514_(0, 24).m_171480_().m_171496_(0.05F, 17.1476F, -0.0853F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(18.95F, -30.05F, 30.85F, 3.0513F, 0.2608F, -0.0233F));
/*     */     
/*  85 */     body.m_171599_("part19", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-18.05F, 18.1476F, -0.0853F, 18.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  86 */         .m_171514_(0, 24).m_171496_(-18.05F, 16.1476F, -0.0853F, 18.0F, 2.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  87 */         .m_171514_(0, 24).m_171496_(-18.05F, -1.8524F, -0.0853F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.95F, -30.05F, 30.85F, 3.0513F, -0.2608F, 0.0233F));
/*     */     
/*  89 */     body.m_171599_("part20", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -18.1384F, -0.1095F, 2.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  90 */         .m_171514_(0, 24).m_171496_(1.25F, -18.1384F, -0.1095F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  91 */         .m_171514_(0, 24).m_171496_(-18.75F, -18.1384F, -0.1095F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -80.0F, 3.0F, -1.8762F, 0.0F, 0.0F));
/*     */     
/*  93 */     body.m_171599_("part21", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -13.2882F, -0.3956F, 2.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  94 */         .m_171514_(0, 24).m_171496_(-18.75F, -13.2882F, -0.3956F, 18.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  95 */         .m_171514_(0, 24).m_171496_(1.25F, -13.2882F, -0.3956F, 18.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -78.0F, -10.0F, -1.4399F, 0.0F, 0.0F));
/*     */     
/*  97 */     body.m_171599_("part22", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -11.2626F, -0.004F, 18.0F, 12.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -78.0F, -13.0F, -1.3657F, -0.0711F, 0.4386F));
/*     */     
/*  99 */     body.m_171599_("part23", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -12.8482F, -0.0306F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -65.8F, -23.3F, -0.7429F, -0.402F, 0.345F));
/*     */     
/* 101 */     body.m_171599_("part24", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.25F, -15.9264F, -0.0929F, 18.0F, 16.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 102 */         .m_171514_(0, 24).m_171496_(-0.75F, -15.9264F, -0.0929F, 2.0F, 16.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 103 */         .m_171514_(0, 24).m_171496_(-18.75F, -15.9264F, -0.0929F, 18.0F, 16.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -65.7F, -23.3F, -0.672F, 0.0F, 0.0F));
/*     */     
/* 105 */     body.m_171599_("part25", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-23.05F, -10.9264F, -0.0929F, 5.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 106 */         .m_171514_(0, 24).m_171496_(-18.05F, -10.9264F, -0.0929F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.95F, -65.7F, -23.3F, -0.6952F, 0.2376F, -0.1938F));
/*     */     
/* 108 */     body.m_171599_("part26", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.192F, -10.0817F, -0.0438F, 13.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(33.85F, -52.6F, -17.0F, -0.3518F, -0.123F, 0.045F));
/*     */     
/* 110 */     body.m_171599_("part27", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.05F, -13.9123F, 0.0311F, 18.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(18.95F, -52.6F, -28.2F, -0.4181F, -0.5692F, 0.2351F));
/*     */     
/* 112 */     body.m_171599_("part28", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.25F, -14.1003F, -0.0373F, 18.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 113 */         .m_171514_(0, 24).m_171496_(-0.75F, -14.1003F, -0.0373F, 2.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 114 */         .m_171514_(0, 24).m_171496_(-18.75F, -14.1003F, -0.0373F, 18.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -52.4F, -28.2F, -0.3491F, 0.0F, 0.0F));
/*     */     
/* 116 */     body.m_171599_("part29", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-20.95F, -14.1003F, -0.0373F, 3.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 117 */         .m_171514_(0, 24).m_171496_(-17.95F, -14.1003F, -0.0373F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -52.4F, -28.2F, -0.3729F, 0.3516F, -0.1339F));
/*     */     
/* 119 */     body.m_171599_("part30", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -28.5003F, -7.2373F, 2.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 120 */         .m_171514_(0, 24).m_171496_(-18.75F, -28.5003F, -7.2373F, 18.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 121 */         .m_171514_(0, 24).m_171496_(1.25F, -28.5003F, -7.2373F, 18.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171419_(-0.25F, -24.0F, -21.0F));
/*     */     
/* 123 */     body.m_171599_("part31", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-14.0084F, -11.5003F, -0.0114F, 14.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-35.85F, -41.0F, -22.1F, 0.1309F, 0.6545F, 0.0F));
/*     */     
/* 125 */     body.m_171599_("part32", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-17.95F, -11.5003F, 0.1627F, 18.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -41.0F, -28.4F, 0.0F, 0.3491F, 0.0F));
/*     */     
/* 127 */     body.m_171599_("part33", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0567F, -6.9003F, -0.0206F, 11.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(34.15F, -45.6F, -18.6F, 0.2752F, -0.4317F, -0.0372F));
/*     */     
/* 129 */     body.m_171599_("part34", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -13.5003F, -0.0373F, 18.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -39.0F, -28.2F, 0.0F, -0.5672F, 0.0F));
/*     */     
/* 131 */     body.m_171599_("part35", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0011F, -5.4718F, 0.0234F, 11.0F, 8.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(32.15F, -27.8F, -18.6F, 0.7805F, -0.6178F, -0.5208F));
/*     */     
/* 133 */     body.m_171599_("part36", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-12.0317F, -5.1888F, 0.0189F, 12.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-34.65F, -35.5F, -20.5F, 0.5506F, 0.7081F, 0.2457F));
/*     */     
/* 135 */     body.m_171599_("part37", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-16.95F, -8.9854F, 0.0985F, 17.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-19.05F, -30.0F, -25.7F, 0.3103F, 0.3757F, 0.1171F));
/*     */     
/* 137 */     body.m_171599_("part38", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-11.9612F, -3.5467F, -0.0198F, 12.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-34.05F, -29.8F, -19.1F, 0.7805F, 0.6178F, 0.5208F));
/*     */     
/* 139 */     body.m_171599_("part39", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-16.15F, -6.9703F, 0.0167F, 16.0F, 8.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-18.85F, -23.8F, -21.7F, 0.6404F, 0.284F, 0.2058F));
/*     */     
/* 141 */     body.m_171599_("part40", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.15F, -6.9703F, 0.0167F, 14.0F, 8.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(18.85F, -23.8F, -21.7F, 0.6404F, -0.284F, -0.2058F));
/*     */     
/* 143 */     PartDefinition part41 = body.m_171599_("part41", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(6.25F, -7.208F, -0.6714F, 13.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 144 */         .m_171514_(0, 24).m_171496_(-0.75F, -7.208F, -0.6714F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 145 */         .m_171514_(0, 24).m_171496_(-18.75F, -7.208F, -0.6714F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 146 */         .m_171514_(0, 24).m_171496_(-8.75F, -7.208F, -0.6714F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -24.0F, -21.0F, 0.6109F, 0.0F, 0.0F));
/*     */     
/* 148 */     part41.m_171599_("part44_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-7.7326F, -0.9323F, -17.1831F, 10.0F, 2.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.75F, -2.208F, -0.6714F, 2.7094F, 0.0411F, -0.5029F));
/*     */     
/* 150 */     part41.m_171599_("part43_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-7.7326F, -2.9017F, 1.5262F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.75F, -2.208F, -0.6714F, -2.2648F, 0.0411F, -0.5029F));
/*     */     
/* 152 */     part41.m_171599_("part42_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0366F, -3.5339F, 2.1066F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(2.75F, -2.208F, -0.6714F, -2.4164F, 0.1274F, 0.4185F));
/*     */     
/* 154 */     part41.m_171599_("part42_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.0395F, 1.8056F, 0.7308F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(2.75F, -3.208F, 5.3286F, -0.4733F, 0.233F, 0.4235F));
/*     */     
/* 156 */     body.m_171599_("part42", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -16.0732F, -2.7038F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 157 */         .m_171514_(0, 24).m_171496_(-18.75F, -16.0732F, -2.7038F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 158 */         .m_171514_(0, 24).m_171496_(1.25F, -16.0732F, -2.7038F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -24.0F, -21.0F, 0.288F, 0.0F, 0.0F));
/*     */     
/* 160 */     body.m_171599_("part43", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0737F, -7.4439F, 0.0463F, 14.0F, 8.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(31.85F, -33.0F, -21.0F, 0.369F, -0.6641F, -0.234F));
/*     */     
/* 162 */     body.m_171599_("part44", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -8.9854F, 0.0985F, 14.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -30.0F, -25.7F, 0.3103F, -0.3757F, -0.1171F));
/*     */     
/* 164 */     body.m_171599_("part45", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -6.7472F, 0.0307F, 4.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 165 */         .m_171514_(0, 24).m_171496_(3.95F, -6.7472F, 0.0307F, 16.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -18.25F, -18.25F, 0.577F, -0.4114F, -0.2546F));
/*     */     
/* 167 */     body.m_171599_("part46", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-15.95F, 0.1489F, -0.0248F, 16.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -24.25F, -21.65F, 0.6206F, 0.4114F, 0.2546F));
/*     */     
/* 169 */     PartDefinition part47 = body.m_171599_("part47", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(5.25F, 0.1989F, 0.0618F, 14.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 170 */         .m_171514_(0, 24).m_171496_(-18.75F, 0.1989F, 0.0618F, 10.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -24.25F, -21.75F, 0.5236F, 0.0F, 0.0F));
/*     */     
/* 172 */     part47.m_171599_("part50_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.2499F, -1.504F, -9.4638F, 2.0F, 10.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-9.0083F, 5.6428F, 8.1579F, -2.8937F, -0.7736F, 2.9112F));
/*     */     
/* 174 */     part47.m_171599_("part49_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.3265F, 0.496F, -1.8192F, 2.0F, 8.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-10.0988F, 5.1989F, -0.3635F, -2.9278F, 0.5964F, -3.0757F));
/*     */     
/* 176 */     part47.m_171599_("part50_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.5F, -2.5F, -3.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(3.75F, 1.6989F, 3.0618F, 0.0F, 0.0F, 0.5236F));
/*     */     
/* 178 */     part47.m_171599_("part49_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.5F, -2.5F, -3.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-6.25F, 1.6989F, 3.0618F, 0.0F, 0.0F, -0.5236F));
/*     */     
/* 180 */     part47.m_171599_("part49_r3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.1326F, 0.1209F, -0.4472F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(6.4372F, 8.1989F, 0.7052F, -3.0806F, -0.7486F, -2.8368F));
/*     */     
/* 182 */     part47.m_171599_("part48_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.4166F, -2.4513F, 0.258F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(6.25F, 5.1989F, 0.0618F, 3.113F, -0.6563F, 2.986F));
/*     */     
/* 184 */     part47.m_171599_("part48_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.8289F, -2.4019F, 0.1568F, 2.0F, 11.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(7.25F, 5.1989F, 6.0618F, 0.0F, -0.829F, 0.0F));
/*     */     
/* 186 */     body.m_171599_("part48", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(10.0F, -6.6204F, -0.1022F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 187 */         .m_171514_(0, 24).m_171496_(0.0F, -6.6204F, -0.1022F, 10.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(4.0F, 0.75F, -6.5F, 0.7153F, -0.1996F, -0.1706F));
/*     */     
/* 189 */     body.m_171599_("part49", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-14.95F, -0.3463F, -0.0146F, 15.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-13.05F, -4.0F, -10.6F, 0.8898F, 0.1996F, 0.1706F));
/*     */     
/* 191 */     body.m_171599_("part50", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.75F, -0.4106F, -0.0912F, 17.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -4.0F, -10.5F, 0.6981F, 0.0F, 0.0F));
/*     */     
/* 193 */     body.m_171599_("part51", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0384F, -7.2163F, 0.0219F, 15.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.0F, -10.5F, -11.8F, 0.49F, -0.1874F, -0.1119F));
/*     */     
/* 195 */     PartDefinition part52 = body.m_171599_("part52", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(4.0F, -0.4395F, -0.0793F, 11.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(4.0F, -15.5F, -16.5F, 0.4839F, -0.1103F, -0.0706F));
/*     */     
/* 197 */     part52.m_171599_("part54_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -1.0F, -2.5F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-1.4074F, 3.8724F, -0.6706F, 2.2533F, 0.3172F, -0.6719F));
/*     */     
/* 199 */     part52.m_171599_("part54_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.5685F, 2.1572F, -1.3748F, 7.0F, 2.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.1931F, 2.0695F, 5.9207F, 0.5236F, 0.0F, -0.3054F));
/*     */     
/* 201 */     part52.m_171599_("part53_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.5F, -5.5F, -3.0F, 9.0F, 12.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(2.5F, 6.0605F, 2.9207F, 0.0F, 0.0F, -0.6109F));
/*     */     
/* 203 */     body.m_171599_("part53", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.05F, -0.9753F, -0.0258F, 12.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-12.95F, -15.0F, -16.3F, 0.4943F, 0.2316F, 0.1231F));
/*     */     
/* 205 */     PartDefinition part54 = body.m_171599_("part54", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.75F, -13.0F, 0.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, 1.0F, -8.0F, 0.48F, 0.0F, 0.0F));
/*     */     
/* 207 */     part54.m_171599_("part56_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.1934F, -3.9686F, -3.4346F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.2869F, -18.1526F, 0.0F, 2.3998F, 0.0F, 0.48F));
/*     */     
/* 209 */     part54.m_171599_("part56_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.3448F, 1.6068F, -2.8615F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-8.2104F, -17.3786F, 7.0F, 0.7418F, 0.0F, 0.48F));
/*     */     
/* 211 */     part54.m_171599_("part55_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0F, -4.5F, -3.0F, 11.0F, 8.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-10.75F, -12.5F, 3.0F, 0.0F, 0.0F, 0.8727F));
/*     */     
/* 213 */     part54.m_171599_("part55_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.0F, -2.7376F, -4.1592F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-1.75F, -16.0F, 0.0F, 2.1817F, 0.0F, 0.0F));
/*     */     
/* 215 */     part54.m_171599_("part55_r3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.5F, 1.9054F, -3.1351F, 13.0F, 2.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-2.25F, -15.0F, 7.0F, 0.7418F, 0.0F, 0.0F));
/*     */     
/* 217 */     body.m_171599_("part55", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -2.0994F, -2.0F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 218 */         .m_171514_(0, 24).m_171496_(-0.1099F, -11.0994F, -2.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 219 */         .m_171514_(0, 24).m_171496_(-0.1099F, -23.0994F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-43.4F, -27.25F, -1.0F, 0.0F, 0.0F, -0.48F));
/*     */     
/* 221 */     body.m_171599_("part56", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0232F, -4.9735F, -12.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 222 */         .m_171514_(0, 24).m_171496_(0.0232F, -16.9735F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-34.8F, -75.85F, -3.0F, 0.0F, -0.3054F, 1.3526F));
/*     */     
/* 224 */     body.m_171599_("part57", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0232F, -2.9735F, 0.0F, 5.0F, 3.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 225 */         .m_171514_(0, 24).m_171496_(0.0232F, -16.9735F, 0.0F, 5.0F, 14.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-34.8F, -75.85F, 9.0F, 0.0F, 0.4363F, 1.3526F));
/*     */     
/* 227 */     body.m_171599_("part58", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0232F, -4.9735F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 228 */         .m_171514_(0, 24).m_171496_(0.0232F, -16.9735F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-34.8F, -75.85F, -1.0F, 0.0F, 0.0F, 1.3526F));
/*     */     
/* 230 */     body.m_171599_("part59", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.004F, -0.9735F, -12.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 231 */         .m_171514_(0, 24).m_171496_(-0.004F, -12.9735F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-46.8F, -70.85F, -3.0F, 0.0F, -0.3491F, 1.1781F));
/*     */     
/* 233 */     body.m_171599_("part60", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.004F, -3.9735F, 0.0F, 5.0F, 4.0F, 15.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 234 */         .m_171514_(0, 24).m_171496_(-0.004F, -12.9735F, 0.0F, 5.0F, 9.0F, 15.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-46.8F, -70.85F, 9.0F, 0.0F, 0.6545F, 1.1781F));
/*     */     
/* 236 */     body.m_171599_("part61", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.004F, -0.9735F, -2.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 237 */         .m_171514_(0, 24).m_171496_(-0.004F, -12.9735F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-46.8F, -70.85F, -1.0F, 0.0F, 0.0F, 1.1781F));
/*     */     
/* 239 */     body.m_171599_("part62", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0425F, -8.9606F, -12.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-53.4F, -64.75F, -3.0F, 0.0F, -0.3054F, 0.829F));
/*     */     
/* 241 */     body.m_171599_("part63", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0425F, -8.9606F, 0.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-53.4F, -64.75F, 9.0F, 0.0F, 0.6981F, 0.829F));
/*     */     
/* 243 */     body.m_171599_("part64", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0425F, -8.9606F, -2.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-53.4F, -64.75F, -1.0F, 0.0F, 0.0F, 0.829F));
/*     */     
/* 245 */     body.m_171599_("part65", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.07F, 5.2511F, -11.935F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 246 */         .m_171514_(0, 24).m_171496_(0.07F, -6.7489F, -11.935F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-48.7F, -57.75F, -14.0F, 0.0F, -1.1345F, 0.0436F));
/*     */     
/* 248 */     body.m_171599_("part66", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0847F, -4.9749F, -12.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 249 */         .m_171514_(0, 24).m_171496_(-0.0847F, -16.9749F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-54.1F, -47.75F, -3.0F, 0.0F, -0.4363F, 0.0436F));
/*     */     
/* 251 */     body.m_171599_("part67", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0718F, -3.3112F, -0.0014F, 5.0F, 4.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 252 */         .m_171514_(0, 24).m_171496_(-0.0718F, -14.3112F, -0.0014F, 5.0F, 11.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-48.0F, -50.15F, 19.4F, 0.3253F, 0.8731F, 0.4583F));
/*     */     
/* 254 */     body.m_171599_("part68", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0847F, -4.9749F, 0.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 255 */         .m_171514_(0, 24).m_171496_(-0.0847F, -16.9749F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-54.1F, -47.75F, 9.0F, 0.0F, 0.5236F, 0.0436F));
/*     */     
/* 257 */     body.m_171599_("part69", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0847F, -16.9749F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 258 */         .m_171514_(0, 24).m_171496_(-0.0847F, -4.9749F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-54.1F, -47.75F, -1.0F, 0.0F, 0.0F, 0.0436F));
/*     */     
/* 260 */     body.m_171599_("part70", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0346F, -4.7469F, -12.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 261 */         .m_171514_(0, 24).m_171496_(-0.0346F, -16.7469F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-46.4F, -32.85F, -3.0F, 0.0F, -0.5236F, -0.48F));
/*     */     
/* 263 */     body.m_171599_("part71", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0294F, -2.1577F, 0.0376F, 5.0F, 6.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 264 */         .m_171514_(0, 24).m_171496_(-0.0294F, -14.1577F, 0.0376F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-40.9F, -35.25F, 19.4F, 0.1681F, 0.8068F, -0.2521F));
/*     */     
/* 266 */     body.m_171599_("part72", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -11.0994F, 0.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 267 */         .m_171514_(0, 24).m_171496_(-0.1099F, -23.0994F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-43.4F, -27.25F, 9.0F, 0.0F, 0.5236F, -0.48F));
/*     */     
/* 269 */     body.m_171599_("part73", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -3.2415F, -12.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 270 */         .m_171514_(0, 24).m_171496_(-0.1099F, -15.2415F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-33.4F, -17.25F, -3.0F, 0.0F, -0.5236F, -0.7854F));
/*     */     
/* 272 */     body.m_171599_("part74", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, 0.7585F, 0.0F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 273 */         .m_171514_(0, 24).m_171496_(-0.1099F, -3.2415F, 0.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 274 */         .m_171514_(0, 24).m_171496_(-0.1099F, -15.2415F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-33.4F, -17.25F, 9.0F, 0.0F, 0.48F, -0.7854F));
/*     */     
/* 276 */     body.m_171599_("part75", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0013F, -9.3725F, -0.0051F, 5.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-33.7F, -25.25F, 19.7F, -0.0668F, 0.6516F, -0.8952F));
/*     */     
/* 278 */     body.m_171599_("part76", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -2.2415F, -2.0F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 279 */         .m_171514_(0, 24).m_171496_(-0.1099F, -14.2415F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-33.4F, -17.25F, -1.0F, 0.0F, 0.0F, -0.7854F));
/*     */     
/* 281 */     body.m_171599_("part77", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0672F, -9.0045F, -12.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 282 */         .m_171514_(0, 24).m_171496_(-0.0672F, -21.0045F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-28.4F, -1.25F, -3.0F, 0.0F, -0.6981F, -0.3054F));
/*     */     
/* 284 */     body.m_171599_("part78", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0147F, -2.2993F, 0.0F, 5.0F, 10.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-32.9F, -15.25F, 9.0F, 0.0F, 0.3665F, -0.3054F));
/*     */     
/* 286 */     body.m_171599_("part79", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0672F, -5.0045F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 287 */         .m_171514_(0, 24).m_171496_(-0.0672F, -17.0045F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-28.4F, -1.25F, -1.0F, 0.0F, 0.0F, -0.3054F));
/*     */     
/* 289 */     body.m_171599_("part80", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0048F, -5.0256F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 290 */         .m_171514_(0, 24).m_171496_(-0.0048F, -17.0256F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-13.0F, 6.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/* 292 */     body.m_171599_("part81", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -10.0F, -2.0F, 5.0F, 10.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, -1.2217F));
/*     */     
/* 294 */     body.m_171599_("part82", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9699F, 0.0973F, 0.1F, 5.0F, 6.0F, 14.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(43.0F, -34.1F, 8.9F, 0.0F, -0.3054F, -0.1745F));
/*     */     
/* 296 */     body.m_171599_("part83", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0218F, 5.119F, -12.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(42.7F, -36.1F, -3.0F, 0.0F, 0.7418F, -0.1745F));
/*     */     
/* 298 */     body.m_171599_("part84", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0579F, -5.0416F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(44.5F, -26.1F, -1.0F, 0.0F, 0.0F, -0.1745F));
/*     */     
/* 300 */     body.m_171599_("part85", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0251F, -4.0204F, 0.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 301 */         .m_171514_(0, 24).m_171496_(-5.0251F, -16.0204F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(34.9F, -74.5F, 9.0F, 0.0F, -0.4363F, -1.2217F));
/*     */     
/* 303 */     body.m_171599_("part86", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0251F, -4.0204F, -2.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 304 */         .m_171514_(0, 24).m_171496_(-5.0251F, -16.0204F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(34.9F, -74.5F, -1.0F, 0.0F, 0.0F, -1.2217F));
/*     */     
/* 306 */     body.m_171599_("part87", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9718F, -6.9442F, -15.0F, 5.0F, 7.0F, 15.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 307 */         .m_171514_(0, 24).m_171496_(-4.9718F, -15.9442F, -15.0F, 5.0F, 9.0F, 15.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(49.0F, -67.1F, -3.0F, 0.0F, 0.6109F, -1.0908F));
/*     */     
/* 309 */     body.m_171599_("part88", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9709F, 0.0095F, -3.3729F, 5.0F, 6.0F, 8.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(45.2F, -59.7F, 22.9F, -0.6819F, -0.9338F, -0.3005F));
/*     */     
/* 311 */     body.m_171599_("part89", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0437F, -0.1556F, -0.0354F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 312 */         .m_171514_(0, 24).m_171496_(-5.0437F, -12.1556F, -0.0354F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(44.0F, -65.7F, 20.5F, 0.0F, -1.0908F, -1.0908F));
/*     */     
/* 314 */     body.m_171599_("part90", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9718F, -3.9442F, 0.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 315 */         .m_171514_(0, 24).m_171496_(-4.9718F, -15.9442F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(49.0F, -67.1F, 9.0F, 0.0F, -0.3054F, -1.0908F));
/*     */     
/* 317 */     body.m_171599_("part91", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9718F, -3.9442F, -2.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 318 */         .m_171514_(0, 24).m_171496_(-4.9718F, -15.9442F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(49.0F, -67.1F, -1.0F, 0.0F, 0.0F, -1.0908F));
/*     */     
/* 320 */     body.m_171599_("part92", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0214F, -16.0916F, -10.0F, 5.0F, 10.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(56.5F, -52.8F, -3.0F, -0.0999F, 0.5148F, -0.6808F));
/*     */     
/* 322 */     body.m_171599_("part93", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0214F, -4.0916F, 0.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 323 */         .m_171514_(0, 24).m_171496_(-5.0214F, -16.0916F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(56.5F, -52.8F, 9.0F, 0.0F, -0.2182F, -0.48F));
/*     */     
/* 325 */     body.m_171599_("part94", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0214F, -4.0916F, -2.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 326 */         .m_171514_(0, 24).m_171496_(-5.0214F, -16.0916F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(56.5F, -52.8F, -1.0F, 0.0F, 0.0F, -0.48F));
/*     */     
/* 328 */     body.m_171599_("part95", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9518F, -3.0182F, -15.0F, 5.0F, 4.0F, 15.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 329 */         .m_171514_(0, 24).m_171496_(-4.9518F, -12.0182F, -15.0F, 5.0F, 9.0F, 15.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(54.5F, -44.1F, -3.0F, 0.0F, 0.6894F, 0.2182F));
/*     */     
/* 331 */     body.m_171599_("part96", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9161F, -7.563F, 0.1404F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(51.3F, -46.3F, 20.3F, 0.0F, -1.0472F, 0.2182F));
/*     */     
/* 333 */     body.m_171599_("part97", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9518F, -9.0182F, 0.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(54.5F, -44.1F, 9.0F, 0.0F, -0.3054F, 0.2182F));
/*     */     
/* 335 */     body.m_171599_("part98", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9518F, -9.0182F, -2.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(54.5F, -44.1F, -1.0F, 0.0F, 0.0F, 0.2182F));
/*     */     
/* 337 */     body.m_171599_("part99", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0225F, -3.0598F, -13.0F, 5.0F, 11.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 338 */         .m_171514_(0, 24).m_171496_(-5.0225F, -14.0598F, -13.0F, 5.0F, 11.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(43.6F, -31.0F, -3.0F, -0.0621F, 0.5108F, 0.5485F));
/*     */     
/* 340 */     body.m_171599_("part100", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0216F, 4.8429F, -0.0255F, 5.0F, 6.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 341 */         .m_171514_(0, 24).m_171496_(-5.0216F, -7.1571F, -0.0255F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(43.6F, -37.4F, 20.3F, 0.0F, -0.9599F, 0.6981F));
/*     */     
/* 343 */     body.m_171599_("part101", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0225F, -5.0598F, 0.0F, 5.0F, 7.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 344 */         .m_171514_(0, 24).m_171496_(-5.0225F, -17.0598F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(43.6F, -31.0F, 9.0F, 0.0F, -0.3491F, 0.6981F));
/*     */     
/* 346 */     body.m_171599_("part102", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0225F, -5.0598F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 347 */         .m_171514_(0, 24).m_171496_(-5.0225F, -17.0598F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(43.6F, -31.0F, -1.0F, 0.0F, 0.0F, 0.6981F));
/*     */     
/* 349 */     body.m_171599_("part103", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.996F, -1.6845F, -0.0289F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 350 */         .m_171514_(0, 24).m_171496_(-4.996F, -13.6845F, -0.0289F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(34.95F, -15.5F, 20.3F, 0.0F, -0.6981F, 0.4363F));
/*     */     
/* 352 */     body.m_171599_("part104", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0061F, 0.2384F, 0.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 353 */         .m_171514_(0, 24).m_171496_(-5.0061F, -11.7616F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(39.5F, -15.5F, 9.0F, 0.0F, -0.3491F, 0.4363F));
/*     */     
/* 355 */     body.m_171599_("part105", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0061F, 1.2384F, -11.0F, 5.0F, 6.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 356 */         .m_171514_(0, 24).m_171496_(-5.0061F, -11.7616F, -11.0F, 5.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(39.5F, -15.5F, -3.0F, 0.0F, 0.8465F, 0.4363F));
/*     */     
/* 358 */     body.m_171599_("part106", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0929F, -0.9617F, -2.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 359 */         .m_171514_(0, 24).m_171496_(-5.0929F, -12.9617F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 360 */         .m_171514_(0, 24).m_171496_(-5.0929F, -24.9617F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(34.0F, -3.5F, -1.0F, 0.0F, 0.0F, 0.4363F));
/*     */     
/* 362 */     body.m_171599_("part107", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.0179F, 3.2586F, 0.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 363 */         .m_171514_(0, 24).m_171496_(-4.0179F, -8.7414F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(25.5F, -1.3F, 9.0F, 0.0475F, -0.346F, 1.1698F));
/*     */     
/* 365 */     body.m_171599_("part108", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9523F, 2.2409F, -12.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 366 */         .m_171514_(0, 24).m_171496_(-4.9523F, -9.7591F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(24.5F, -1.1F, -3.0F, 0.0F, 0.9599F, 1.309F));
/*     */     
/* 368 */     body.m_171599_("part109", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.7207F, -2.8735F, -2.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 369 */         .m_171514_(0, 24).m_171496_(-4.7207F, -14.8735F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.5F, 0.0F, -1.0F, 0.0F, 0.0F, 1.309F));
/*     */     
/* 371 */     body.m_171599_("part110", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.883F, -10.9741F, -2.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(10.5F, 6.5F, -1.0F, 0.0F, 0.0F, 0.9599F));
/*     */     
/* 373 */     body.m_171599_("part111", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -11.0F, -2.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 1.1345F));
/*     */     
/* 375 */     body.m_171599_("part112", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0489F, -3.0731F, -2.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-10.7F, 8.0F, -1.0F, 0.0F, 0.0F, -0.8727F));
/*     */     
/* 377 */     body.m_171599_("part113", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5F, -3.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 4.0F, -6.0F, 0.1309F, 0.0F, 0.0F));
/*     */     
/* 379 */     body.m_171599_("part114", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.4965F, -8.2953F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 1.2519F, -0.2368F, 0.8109F));
/*     */     
/* 381 */     body.m_171599_("part115", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -4.2953F, -2.0F, 11.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/* 383 */     body.m_171599_("part116", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-4.4965F, -7.2953F, -2.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, 0.6981F));
/*     */     
/* 385 */     body.m_171599_("part117", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.0341F, -3.1877F, -4.5034F, 5.0F, 7.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.5F, 0.0F, -3.0F, 1.9222F, -0.318F, 0.8876F));
/*     */     
/* 387 */     body.m_171599_("part118", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -9.7983F, 6.0F, 7.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 2.2689F, 0.0F, -1.0036F));
/*     */     
/* 389 */     body.m_171599_("part119", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -5.7983F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 1.4399F, 0.0F, -1.0036F));
/*     */     
/* 391 */     body.m_171599_("part120", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -8.2953F, -2.0F, 6.0F, 10.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 1.0036F, 0.0F, -1.0036F));
/*     */     
/* 393 */     body.m_171599_("part121", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5035F, -7.2953F, -2.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.5672F));
/*     */     
/* 395 */     body.m_171599_("part122", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -6.2953F, -2.0F, 5.0F, 7.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, -1.0943F, 0.0603F, 0.8873F));
/*     */     
/* 397 */     body.m_171599_("part123", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -6.2953F, -2.0F, 6.0F, 8.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, -1.0908F, 0.0F, -1.0036F));
/*     */     
/* 399 */     body.m_171599_("part124", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -7.2953F, -2.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.0036F));
/*     */     
/* 401 */     body.m_171599_("part125", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5F, -8.8659F, -2.4041F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-2.5F, 6.5F, -3.0F, 0.3295F, 0.1172F, -0.3295F));
/*     */     
/* 403 */     body.m_171599_("part126", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -3.0F, 5.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 13.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
/*     */     
/* 405 */     body.m_171599_("part127", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -2.0F, -2.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 406 */         .m_171514_(0, 24).m_171496_(-2.5F, -21.0F, -2.0F, 5.0F, 19.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 15.0F, 0.0F, -0.2182F, 0.0F, 0.0F));
/*     */     
/* 408 */     return body;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\LowResTornEvolvedDevourerBodyModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */