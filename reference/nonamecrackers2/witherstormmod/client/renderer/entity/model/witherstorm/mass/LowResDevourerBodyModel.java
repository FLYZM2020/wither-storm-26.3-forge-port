/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*     */ 
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LowResDevourerBodyModel
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
/*  39 */     body.m_171599_("part6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-11.9513F, -15.9413F, 0.013F, 12.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-19.15F, -19.05F, 30.65F, 2.5743F, -0.5406F, 0.2977F));
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
/*  54 */     body.m_171599_("part12", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-16.9283F, 0.0265F, -0.2188F, 17.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.95F, -19.15F, 31.05F, -3.132F, -0.6102F, -0.0305F));
/*     */     
/*  56 */     body.m_171599_("part13", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -11.076F, -0.0816F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  57 */         .m_171514_(0, 24).m_171496_(1.25F, -11.076F, -0.0816F, 18.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  58 */         .m_171514_(0, 24).m_171496_(-18.75F, -11.076F, -0.0816F, 18.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -30.25F, 30.75F, -3.098F, 0.0F, 0.0F));
/*     */     
/*  60 */     body.m_171599_("part14", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.05F, -6.2013F, 0.0631F, 11.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(18.95F, -35.75F, 28.05F, -2.6473F, 0.2316F, 0.1231F));
/*     */     
/*  62 */     body.m_171599_("part15", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-18.95F, 3.1051F, 0.0496F, 1.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  63 */         .m_171514_(0, 24).m_171496_(-17.95F, 3.1051F, 0.0496F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -30.15F, 30.95F, -2.6202F, -0.3843F, -0.2121F));
/*     */     
/*  65 */     body.m_171599_("part16", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.25F, -5.1081F, 0.0706F, 18.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  66 */         .m_171514_(0, 24).m_171496_(-0.75F, -5.1081F, 0.0706F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  67 */         .m_171514_(0, 24).m_171496_(-18.75F, -5.1081F, 0.0706F, 18.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  68 */         .m_171514_(0, 24).m_171496_(-0.75F, -23.1081F, 0.0706F, 2.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  69 */         .m_171514_(0, 24).m_171496_(1.25F, -23.1081F, 0.0706F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  70 */         .m_171514_(0, 24).m_171496_(-18.75F, -23.1081F, 0.0706F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -50.75F, 20.25F, -2.6616F, 0.0F, 0.0F));
/*     */     
/*  72 */     body.m_171599_("part17", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -18.1384F, -0.1095F, 2.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  73 */         .m_171514_(0, 24).m_171496_(1.25F, -18.1384F, -0.1095F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  74 */         .m_171514_(0, 24).m_171496_(-18.75F, -18.1384F, -0.1095F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -56.0F, 3.0F, -1.8762F, 0.0F, 0.0F));
/*     */     
/*  76 */     body.m_171599_("part18", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -13.2882F, -0.3956F, 2.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  77 */         .m_171514_(0, 24).m_171496_(-18.75F, -13.2882F, -0.3956F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  78 */         .m_171514_(0, 24).m_171496_(1.25F, -13.2882F, -0.3956F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -54.0F, -10.0F, -1.4399F, 0.0F, 0.0F));
/*     */     
/*  80 */     body.m_171599_("part19", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, 6.7118F, -0.3956F, 13.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -54.0F, -10.0F, -0.6037F, -0.3286F, 0.219F));
/*     */     
/*  82 */     body.m_171599_("part20", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.95F, -9.0082F, -1.0205F, 13.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -40.1F, -19.3F, -0.8306F, 0.3286F, -0.219F));
/*     */     
/*  84 */     body.m_171599_("part21", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -0.2882F, -0.3956F, 2.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  85 */         .m_171514_(0, 24).m_171496_(-18.75F, -0.2882F, -0.3956F, 18.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  86 */         .m_171514_(0, 24).m_171496_(1.25F, -0.2882F, -0.3956F, 18.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -54.0F, -10.0F, -0.5672F, 0.0F, 0.0F));
/*     */     
/*  88 */     body.m_171599_("part22", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.05F, -3.1725F, 0.0533F, 18.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(18.95F, -28.0F, -21.9F, 0.0517F, -0.5666F, -0.0278F));
/*     */     
/*  90 */     body.m_171599_("part23", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-17.75F, -0.2146F, -0.9767F, 17.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.25F, -31.0F, -21.0F, 0.0036F, 0.3923F, 0.0181F));
/*     */     
/*  92 */     body.m_171599_("part24", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.25F, -7.208F, -0.6714F, 18.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  93 */         .m_171514_(0, 24).m_171496_(-0.75F, -7.208F, -0.6714F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  94 */         .m_171514_(0, 24).m_171496_(-18.75F, -7.208F, -0.6714F, 18.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -24.0F, -21.0F, 0.0436F, 0.0F, 0.0F));
/*     */     
/*  96 */     body.m_171599_("part_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(18.05F, -3.9331F, -0.0549F, 1.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  97 */         .m_171514_(0, 24).m_171496_(0.05F, -3.9331F, -0.0549F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(18.95F, -36.4F, -20.2F, -0.3064F, -0.4183F, 0.1278F));
/*     */     
/*  99 */     body.m_171599_("part25", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-15.95F, -9.977F, -0.0195F, 16.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -30.6F, -21.9F, -0.2962F, 0.335F, -0.1F));
/*     */     
/* 101 */     body.m_171599_("part26", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -10.1692F, -0.0746F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 102 */         .m_171514_(0, 24).m_171496_(-18.75F, -10.1692F, -0.0746F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 103 */         .m_171514_(0, 24).m_171496_(1.25F, -10.1692F, -0.0746F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -30.4F, -21.9F, -0.2793F, 0.0F, 0.0F));
/*     */     
/* 105 */     body.m_171599_("part27", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -6.7472F, 0.0307F, 4.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 106 */         .m_171514_(0, 24).m_171496_(3.95F, -6.7472F, 0.0307F, 16.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -18.25F, -18.25F, 0.577F, -0.4114F, -0.2546F));
/*     */     
/* 108 */     body.m_171599_("part28", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-14.95F, 0.1489F, -0.0248F, 15.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -24.25F, -21.65F, 0.6206F, 0.4114F, 0.2546F));
/*     */     
/* 110 */     body.m_171599_("part29", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, 0.1989F, 0.0618F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 111 */         .m_171514_(0, 24).m_171496_(-18.75F, 0.1989F, 0.0618F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 112 */         .m_171514_(0, 24).m_171496_(1.25F, 0.1989F, 0.0618F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -24.25F, -21.75F, 0.5236F, 0.0F, 0.0F));
/*     */     
/* 114 */     body.m_171599_("part30", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(10.0F, -6.6204F, -0.1022F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 115 */         .m_171514_(0, 24).m_171496_(0.0F, -6.6204F, -0.1022F, 10.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(4.0F, 0.75F, -6.5F, 0.7153F, -0.1996F, -0.1706F));
/*     */     
/* 117 */     body.m_171599_("part31", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-14.95F, -0.3463F, -0.0146F, 15.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-13.05F, -4.0F, -10.6F, 0.8898F, 0.1996F, 0.1706F));
/*     */     
/* 119 */     body.m_171599_("part32", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.75F, -0.4106F, -0.0912F, 17.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -4.0F, -10.5F, 0.6981F, 0.0F, 0.0F));
/*     */     
/* 121 */     body.m_171599_("part33", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0384F, -7.2163F, 0.0219F, 15.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.0F, -10.5F, -11.8F, 0.49F, -0.1874F, -0.1119F));
/*     */     
/* 123 */     body.m_171599_("part34", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0F, -0.4395F, -0.0793F, 15.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(4.0F, -15.5F, -16.5F, 0.4839F, -0.1103F, -0.0706F));
/*     */     
/* 125 */     body.m_171599_("part35", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.05F, -0.9753F, -0.0258F, 12.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-12.95F, -15.0F, -16.3F, 0.4943F, 0.2316F, 0.1231F));
/*     */     
/* 127 */     body.m_171599_("part36", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.75F, -19.0F, 0.0F, 17.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, 1.0F, -8.0F, 0.48F, 0.0F, 0.0F));
/*     */     
/* 129 */     body.m_171599_("part37", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0392F, -12.92F, 0.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 130 */         .m_171514_(0, 24).m_171496_(-0.0392F, -22.92F, 0.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-40.5F, -41.15F, 9.0F, 0.0F, 0.4189F, 1.0036F));
/*     */     
/* 132 */     body.m_171599_("part38", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -14.0994F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-43.4F, -27.25F, 9.0F, 0.0F, 0.4887F, 0.2182F));
/*     */     
/* 134 */     body.m_171599_("part39", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0392F, -10.92F, -12.0F, 5.0F, 8.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 135 */         .m_171514_(0, 24).m_171496_(-0.0392F, -22.92F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-40.5F, -41.15F, -3.0F, 0.0F, -0.3491F, 1.0036F));
/*     */     
/* 137 */     body.m_171599_("part40", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0086F, -1.7819F, -2.0F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 138 */         .m_171514_(0, 24).m_171496_(-0.0086F, -13.7819F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 139 */         .m_171514_(0, 24).m_171496_(-0.0086F, -25.7819F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-40.4F, -41.25F, -1.0F, 0.0F, 0.0F, 1.0036F));
/*     */     
/* 141 */     body.m_171599_("part41", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -2.0994F, -12.0F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 142 */         .m_171514_(0, 24).m_171496_(-0.1099F, -14.0994F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-43.4F, -27.25F, -3.0F, 0.0F, -0.4887F, 0.2182F));
/*     */     
/* 144 */     body.m_171599_("part42", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -2.0994F, -2.0F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 145 */         .m_171514_(0, 24).m_171496_(-0.1099F, -14.0994F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-43.4F, -27.25F, -1.0F, 0.0F, 0.0F, 0.2182F));
/*     */     
/* 147 */     body.m_171599_("part43", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -11.2415F, -12.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-33.4F, -17.25F, -3.0F, 0.0F, -0.5236F, -0.7854F));
/*     */     
/* 149 */     body.m_171599_("part44", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, 0.7585F, 0.0F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 150 */         .m_171514_(0, 24).m_171496_(-0.1099F, -11.2415F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-33.4F, -17.25F, 9.0F, 0.0F, 0.48F, -0.7854F));
/*     */     
/* 152 */     body.m_171599_("part45", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.1099F, -2.2415F, -2.0F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 153 */         .m_171514_(0, 24).m_171496_(-0.1099F, -14.2415F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-33.4F, -17.25F, -1.0F, 0.0F, 0.0F, -0.7854F));
/*     */     
/* 155 */     body.m_171599_("part46", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0672F, -9.0045F, -12.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 156 */         .m_171514_(0, 24).m_171496_(-0.0672F, -21.0045F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-28.4F, -1.25F, -3.0F, 0.0F, -0.6981F, -0.3054F));
/*     */     
/* 158 */     body.m_171599_("part47", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0147F, -2.2993F, 0.0F, 5.0F, 10.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-32.9F, -15.25F, 9.0F, 0.0F, 0.3665F, -0.3054F));
/*     */     
/* 160 */     body.m_171599_("part48", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0672F, -5.0045F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 161 */         .m_171514_(0, 24).m_171496_(-0.0672F, -17.0045F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-28.4F, -1.25F, -1.0F, 0.0F, 0.0F, -0.3054F));
/*     */     
/* 163 */     body.m_171599_("part49", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0048F, -5.0256F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 164 */         .m_171514_(0, 24).m_171496_(-0.0048F, -17.0256F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-13.0F, 6.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/* 166 */     body.m_171599_("part50", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -10.0F, -2.0F, 5.0F, 10.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, -1.2217F));
/*     */     
/* 168 */     body.m_171599_("part51", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0294F, 2.294F, 0.0138F, 5.0F, 9.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 169 */         .m_171514_(0, 24).m_171496_(-5.0294F, -8.706F, 0.0138F, 5.0F, 11.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(28.5F, -44.5F, 20.1F, 0.0955F, -1.0006F, -1.2042F));
/*     */     
/* 171 */     body.m_171599_("part52", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.961F, 2.649F, 0.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 172 */         .m_171514_(0, 24).m_171496_(-4.961F, 1.649F, 0.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 173 */         .m_171514_(0, 24).m_171496_(-4.961F, -10.351F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(28.5F, -49.6F, 9.0F, 0.0F, -0.3927F, -1.0908F));
/*     */     
/* 175 */     body.m_171599_("part53", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0132F, 3.9309F, -12.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 176 */         .m_171514_(0, 24).m_171496_(-5.0132F, -8.0691F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(26.5F, -50.7F, -3.0F, 0.0F, 0.5149F, -1.0908F));
/*     */     
/* 178 */     body.m_171599_("part54", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0208F, -12.9759F, -2.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 179 */         .m_171514_(0, 24).m_171496_(-5.0208F, -11.9759F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 180 */         .m_171514_(0, 24).m_171496_(-5.0208F, -24.9759F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(41.5F, -42.9F, -1.0F, 0.0F, 0.0F, -1.0908F));
/*     */     
/* 182 */     body.m_171599_("part55", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9699F, 3.0973F, 0.1F, 5.0F, 3.0F, 14.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 183 */         .m_171514_(0, 24).m_171496_(-4.9699F, -6.9027F, 0.1F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(43.0F, -34.1F, 8.9F, 0.0F, -0.3054F, -0.1745F));
/*     */     
/* 185 */     body.m_171599_("part56", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0218F, 5.119F, -12.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 186 */         .m_171514_(0, 24).m_171496_(-5.0218F, -6.881F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(42.7F, -36.1F, -3.0F, 0.0F, 0.7418F, -0.1745F));
/*     */     
/* 188 */     body.m_171599_("part57", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0579F, -5.0416F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 189 */         .m_171514_(0, 24).m_171496_(-5.0579F, -17.0416F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(44.5F, -26.1F, -1.0F, 0.0F, 0.0F, -0.1745F));
/*     */     
/* 191 */     body.m_171599_("part58", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.996F, -1.6845F, -0.0289F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 192 */         .m_171514_(0, 24).m_171496_(-4.996F, -13.6845F, -0.0289F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(34.95F, -15.5F, 20.3F, 0.0F, -0.6981F, 0.4363F));
/*     */     
/* 194 */     body.m_171599_("part59", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0061F, 0.2384F, 0.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 195 */         .m_171514_(0, 24).m_171496_(-5.0061F, -11.7616F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(39.5F, -15.5F, 9.0F, 0.0F, -0.3491F, 0.4363F));
/*     */     
/* 197 */     body.m_171599_("part60", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0061F, 1.2384F, -11.0F, 5.0F, 6.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 198 */         .m_171514_(0, 24).m_171496_(-5.0061F, -11.7616F, -11.0F, 5.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(39.5F, -15.5F, -3.0F, 0.0F, 0.8465F, 0.4363F));
/*     */     
/* 200 */     body.m_171599_("part61", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0929F, -0.9617F, -2.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 201 */         .m_171514_(0, 24).m_171496_(-5.0929F, -12.9617F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 202 */         .m_171514_(0, 24).m_171496_(-5.0929F, -24.9617F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(34.0F, -3.5F, -1.0F, 0.0F, 0.0F, 0.4363F));
/*     */     
/* 204 */     body.m_171599_("part62", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.0179F, 3.2586F, 0.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 205 */         .m_171514_(0, 24).m_171496_(-4.0179F, -8.7414F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(25.5F, -1.3F, 9.0F, 0.0475F, -0.346F, 1.1698F));
/*     */     
/* 207 */     body.m_171599_("part63", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9523F, 2.2409F, -12.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 208 */         .m_171514_(0, 24).m_171496_(-4.9523F, -9.7591F, -12.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(24.5F, -1.1F, -3.0F, 0.0F, 0.9599F, 1.309F));
/*     */     
/* 210 */     body.m_171599_("part64", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.7207F, -2.8735F, -2.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 211 */         .m_171514_(0, 24).m_171496_(-4.7207F, -14.8735F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.5F, 0.0F, -1.0F, 0.0F, 0.0F, 1.309F));
/*     */     
/* 213 */     body.m_171599_("part65", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.883F, -10.9741F, -2.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(10.5F, 6.5F, -1.0F, 0.0F, 0.0F, 0.9599F));
/*     */     
/* 215 */     body.m_171599_("part66", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -11.0F, -2.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 1.1345F));
/*     */     
/* 217 */     body.m_171599_("part67", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0489F, -3.0731F, -2.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-10.7F, 8.0F, -1.0F, 0.0F, 0.0F, -0.8727F));
/*     */     
/* 219 */     body.m_171599_("part68", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5F, -3.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 4.0F, -6.0F, 0.1309F, 0.0F, 0.0F));
/*     */     
/* 221 */     body.m_171599_("part69", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.4965F, -8.2953F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 1.2519F, -0.2368F, 0.8109F));
/*     */     
/* 223 */     body.m_171599_("part70", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -4.2953F, -2.0F, 11.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/* 225 */     body.m_171599_("part71", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-4.4965F, -7.2953F, -2.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, 0.6981F));
/*     */     
/* 227 */     body.m_171599_("part72", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.0341F, -3.1877F, -4.5034F, 5.0F, 7.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.5F, 0.0F, -3.0F, 1.9222F, -0.318F, 0.8876F));
/*     */     
/* 229 */     body.m_171599_("part73", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -9.7983F, 6.0F, 7.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 2.2689F, 0.0F, -1.0036F));
/*     */     
/* 231 */     body.m_171599_("part74", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -5.7983F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 1.4399F, 0.0F, -1.0036F));
/*     */     
/* 233 */     body.m_171599_("part75", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -8.2953F, -2.0F, 6.0F, 10.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 1.0036F, 0.0F, -1.0036F));
/*     */     
/* 235 */     body.m_171599_("part76", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5035F, -7.2953F, -2.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.5672F));
/*     */     
/* 237 */     body.m_171599_("part77", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -6.2953F, -2.0F, 5.0F, 7.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, -1.0943F, 0.0603F, 0.8873F));
/*     */     
/* 239 */     body.m_171599_("part78", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -6.2953F, -2.0F, 6.0F, 8.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, -1.0908F, 0.0F, -1.0036F));
/*     */     
/* 241 */     body.m_171599_("part79", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -7.2953F, -2.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.0036F));
/*     */     
/* 243 */     body.m_171599_("part80", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5F, -8.8659F, -2.4041F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-2.5F, 6.5F, -3.0F, 0.3295F, 0.1172F, -0.3295F));
/*     */     
/* 245 */     body.m_171599_("part81", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -3.0F, 5.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 13.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
/*     */     
/* 247 */     body.m_171599_("part82", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -2.0F, -2.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 248 */         .m_171514_(0, 24).m_171496_(-2.5F, -21.0F, -2.0F, 5.0F, 19.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 15.0F, 0.0F, -0.2182F, 0.0F, 0.0F));
/*     */     
/* 250 */     return body;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\LowResDevourerBodyModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */