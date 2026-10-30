/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*     */ 
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LowResDismantledBodyModel
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
/*  31 */     body.m_171599_("part2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-13.0563F, -13.002F, 0.0055F, 13.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-29.35F, -8.85F, 19.3F, 1.4882F, -0.2954F, 1.7976F));
/*     */     
/*  33 */     body.m_171599_("part3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-12.9838F, -13.0207F, 0.0017F, 13.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-18.75F, -1.85F, 21.7F, 1.8162F, -0.1854F, 0.5843F));
/*     */     
/*  35 */     body.m_171599_("part4", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-19.25F, -15.214F, -3.1328F, 13.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.25F, 2.25F, 23.5F, 1.8497F, -0.1286F, 0.371F));
/*     */     
/*  37 */     body.m_171599_("part5", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(6.25F, -15.214F, -3.1328F, 13.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, 2.25F, 23.5F, 1.8497F, 0.1286F, -0.371F));
/*     */     
/*  39 */     body.m_171599_("part6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -15.214F, -0.1328F, 14.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, 2.25F, 23.5F, 2.0508F, 0.0F, 0.0F));
/*     */     
/*  41 */     body.m_171599_("part7", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-14.904F, -12.5148F, -0.0157F, 15.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-28.95F, -19.05F, 22.85F, 0.9267F, -0.9568F, 2.2947F));
/*     */     
/*  43 */     body.m_171599_("part8", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-11.9513F, -15.9413F, 0.013F, 12.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(-19.15F, -19.05F, 30.65F, 2.5743F, -0.5406F, 0.2977F));
/*     */     
/*  45 */     body.m_171599_("part9", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-19.25F, -2.0646F, -2.0619F, 12.0F, 2.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  46 */         .m_171514_(0, 24).m_171480_().m_171496_(-19.25F, -20.0646F, -2.0619F, 12.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.25F, -19.25F, 31.25F, 2.6589F, -0.123F, 0.045F));
/*     */     
/*  48 */     body.m_171599_("part10", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0621F, 1.9527F, 0.0131F, 13.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(22.35F, -46.0F, -6.3F, -1.7068F, -0.9976F, 1.7322F));
/*     */     
/*  50 */     body.m_171599_("part11", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, 6.7118F, -0.3956F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -54.0F, -10.0F, -0.6037F, -0.3286F, 0.219F));
/*     */     
/*  52 */     body.m_171599_("part12", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0114F, -0.9148F, -0.0446F, 11.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(27.35F, -36.4F, -9.25F, -2.722F, -0.8277F, 2.8242F));
/*     */     
/*  54 */     body.m_171599_("part13", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.05F, -3.9331F, -0.0549F, 11.0F, 12.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(18.95F, -36.4F, -20.2F, -0.3064F, -0.4183F, 0.1278F));
/*     */     
/*  56 */     body.m_171599_("part14", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0729F, -2.6003F, -0.0204F, 18.0F, 15.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(37.05F, -16.25F, 17.95F, 0.6628F, 0.6132F, -3.1135F));
/*     */     
/*  58 */     body.m_171599_("part15", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(12.25F, -24.0646F, -0.5619F, 21.0F, 15.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  59 */         .m_171514_(0, 24).m_171496_(12.25F, -24.0646F, -3.5619F, 21.0F, 15.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -19.25F, 31.25F, 2.6142F, 0.0112F, -0.4007F));
/*     */     
/*  61 */     body.m_171599_("part16", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(7.25F, -2.0646F, -2.0619F, 12.0F, 2.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  62 */         .m_171514_(0, 24).m_171496_(7.25F, -20.0646F, -2.0619F, 12.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -19.25F, 31.25F, 2.6589F, 0.123F, -0.045F));
/*     */     
/*  64 */     body.m_171599_("part17", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -5.0646F, -0.0619F, 14.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  65 */         .m_171514_(0, 24).m_171496_(-6.75F, -23.0646F, -0.0619F, 14.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -19.25F, 31.25F, 2.7925F, 0.0F, 0.0F));
/*     */     
/*  67 */     body.m_171599_("part18", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -5.8678F, -0.0087F, 11.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -25.05F, 31.05F, -3.0955F, 0.2181F, 0.0155F));
/*     */     
/*  69 */     body.m_171599_("part19", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.9062F, -8.5007F, -0.1143F, 13.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-24.65F, -27.45F, 19.95F, -0.0606F, -1.4403F, -3.1065F));
/*     */     
/*  71 */     body.m_171599_("part20", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.9283F, 0.0265F, -0.2188F, 11.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.95F, -19.15F, 31.05F, -3.132F, -0.6102F, -0.0305F));
/*     */     
/*  73 */     body.m_171599_("part21", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -11.076F, -0.0816F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  74 */         .m_171514_(0, 24).m_171496_(1.25F, -11.076F, -0.0816F, 18.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  75 */         .m_171514_(0, 24).m_171496_(-18.75F, -11.076F, -0.0816F, 18.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -30.25F, 30.75F, -3.098F, 0.0F, 0.0F));
/*     */     
/*  77 */     body.m_171599_("part22", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.11F, -5.137F, -0.0404F, 11.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(28.15F, -30.75F, 20.75F, -0.8452F, 0.9056F, 2.416F));
/*     */     
/*  79 */     body.m_171599_("part23", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.05F, -6.2013F, 0.0631F, 11.0F, 11.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(18.95F, -35.75F, 28.05F, -2.6473F, 0.2316F, 0.1231F));
/*     */     
/*  81 */     body.m_171599_("part24", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-19.8969F, -12.2757F, 0.1673F, 20.0F, 17.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-22.05F, -40.15F, 16.85F, -1.4872F, -1.089F, -1.6651F));
/*     */     
/*  83 */     body.m_171599_("part25", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.95F, 3.1051F, 0.0496F, 6.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -30.15F, 30.95F, -2.6202F, -0.3843F, -0.2121F));
/*     */     
/*  85 */     body.m_171599_("part26", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.25F, -5.1081F, 0.0706F, 18.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  86 */         .m_171514_(0, 24).m_171496_(-0.75F, -5.1081F, 0.0706F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  87 */         .m_171514_(0, 24).m_171496_(-18.75F, -5.1081F, 0.0706F, 18.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  88 */         .m_171514_(0, 24).m_171496_(-0.75F, -23.1081F, 0.0706F, 2.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  89 */         .m_171514_(0, 24).m_171496_(1.25F, -23.1081F, 0.0706F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  90 */         .m_171514_(0, 24).m_171496_(-18.75F, -23.1081F, 0.0706F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -50.75F, 20.25F, -2.6616F, 0.0F, 0.0F));
/*     */     
/*  92 */     body.m_171599_("part27", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -18.1384F, -0.1095F, 2.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  93 */         .m_171514_(0, 24).m_171496_(1.25F, -18.1384F, -0.1095F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  94 */         .m_171514_(0, 24).m_171496_(-18.75F, -18.1384F, -0.1095F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -56.0F, 3.0F, -1.8762F, 0.0F, 0.0F));
/*     */     
/*  96 */     body.m_171599_("part28", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(17.95F, -20.274F, -0.0226F, 10.0F, 12.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  97 */         .m_171514_(0, 24).m_171496_(-0.05F, -16.274F, -0.0226F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -49.8F, 3.0F, -1.547F, 0.2987F, 1.388F));
/*     */     
/*  99 */     body.m_171599_("part29", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-18.05F, -18.1384F, -0.1095F, 18.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.95F, -56.0F, 3.0F, -1.5983F, -0.3042F, -1.4793F));
/*     */     
/* 101 */     body.m_171599_("part30", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -13.2882F, -0.3956F, 2.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 102 */         .m_171514_(0, 24).m_171496_(-18.75F, -13.2882F, 5.6044F, 18.0F, 13.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 103 */         .m_171514_(0, 24).m_171496_(-18.75F, -13.2882F, -0.3956F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 104 */         .m_171514_(0, 24).m_171496_(1.25F, -13.2882F, -0.3956F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -54.0F, -10.0F, -1.4399F, 0.0F, 0.0F));
/*     */     
/* 106 */     body.m_171599_("part31", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(18.05F, -10.8751F, -0.0504F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 107 */         .m_171514_(0, 24).m_171496_(0.05F, -10.8751F, -0.0504F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(18.95F, -49.7F, -7.0F, -1.5423F, -0.1278F, 1.3508F));
/*     */     
/* 109 */     body.m_171599_("part32", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -0.2882F, -0.3956F, 2.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 110 */         .m_171514_(0, 24).m_171496_(-18.75F, -0.2882F, -0.3956F, 18.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 111 */         .m_171514_(0, 24).m_171496_(1.25F, -0.2882F, -0.3956F, 18.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -54.0F, -10.0F, -0.5672F, 0.0F, 0.0F));
/*     */     
/* 113 */     body.m_171599_("part33", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-19.05F, -0.5569F, 0.0261F, 19.0F, 17.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.95F, -54.0F, -10.5F, -1.3687F, 0.9903F, -1.3305F));
/*     */     
/* 115 */     body.m_171599_("part34", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.75F, -0.2146F, -0.9767F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-18.25F, -31.0F, -21.0F, 0.0036F, 0.3923F, 0.0181F));
/*     */     
/* 117 */     body.m_171599_("part35", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.25F, -7.208F, -0.6714F, 18.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 118 */         .m_171514_(0, 24).m_171496_(-0.75F, -7.208F, -0.6714F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 119 */         .m_171514_(0, 24).m_171496_(-18.75F, -7.208F, -0.6714F, 18.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -24.0F, -21.0F, 0.0436F, 0.0F, 0.0F));
/*     */     
/* 121 */     body.m_171599_("part36", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.9926F, -7.6892F, 0.0223F, 7.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-23.55F, -30.6F, -13.6F, -1.8659F, 1.2786F, -1.8781F));
/*     */     
/* 123 */     body.m_171599_("part37", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.95F, -9.977F, -0.0195F, 7.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -30.6F, -21.9F, -0.2962F, 0.335F, -0.1F));
/*     */     
/* 125 */     body.m_171599_("part38", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, -10.1692F, -0.0746F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 126 */         .m_171514_(0, 24).m_171496_(-18.75F, -10.1692F, -0.0746F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 127 */         .m_171514_(0, 24).m_171496_(1.25F, -10.1692F, -0.0746F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -30.4F, -21.9F, -0.2793F, 0.0F, 0.0F));
/*     */     
/* 129 */     body.m_171599_("part39", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.05F, -6.7472F, 0.0307F, 4.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 130 */         .m_171514_(0, 24).m_171496_(3.95F, -6.7472F, 0.0307F, 12.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.05F, -18.25F, -18.25F, 0.577F, -0.4114F, -0.2546F));
/*     */     
/* 132 */     body.m_171599_("part40", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0141F, -7.1011F, 0.1069F, 12.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(30.45F, -24.25F, -7.15F, 2.4773F, -0.625F, -2.712F));
/*     */     
/* 134 */     body.m_171599_("part41", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-17.024F, -5.1149F, 0.0412F, 17.0F, 14.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-22.35F, -24.25F, -11.65F, 1.6593F, 1.0061F, 1.6517F));
/*     */     
/* 136 */     body.m_171599_("part42", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.95F, 0.1489F, -0.0248F, 7.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-19.05F, -24.25F, -21.65F, 0.6206F, 0.4114F, 0.2546F));
/*     */     
/* 138 */     body.m_171599_("part43", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.75F, 0.1989F, 0.0618F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 139 */         .m_171514_(0, 24).m_171496_(-18.75F, 0.1989F, 0.0618F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 140 */         .m_171514_(0, 24).m_171496_(1.25F, 0.1989F, 0.0618F, 18.0F, 10.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -24.25F, -21.75F, 0.5236F, 0.0F, 0.0F));
/*     */     
/* 142 */     body.m_171599_("part44", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(10.0F, -6.6204F, -0.1022F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 143 */         .m_171514_(0, 24).m_171496_(0.0F, -6.6204F, -0.1022F, 10.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(4.0F, 0.75F, -6.5F, 0.7153F, -0.1996F, -0.1706F));
/*     */     
/* 145 */     body.m_171599_("part45", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-14.95F, -0.3463F, -0.0146F, 15.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-13.05F, -4.0F, -10.6F, 0.8898F, 0.1996F, 0.1706F));
/*     */     
/* 147 */     body.m_171599_("part46", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.75F, -0.4106F, -0.0912F, 17.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, -4.0F, -10.5F, 0.6981F, 0.0F, 0.0F));
/*     */     
/* 149 */     body.m_171599_("part47", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0026F, -12.4879F, 0.0061F, 12.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(35.3F, -10.5F, -0.8F, 2.6182F, -0.3897F, -2.9386F));
/*     */     
/* 151 */     body.m_171599_("part48", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0384F, -7.2163F, 0.0219F, 18.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.0F, -10.5F, -11.8F, 0.49F, -0.1874F, -0.1119F));
/*     */     
/* 153 */     body.m_171599_("part49", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0F, -0.4395F, -0.0793F, 15.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(4.0F, -15.5F, -16.5F, 0.4839F, -0.1103F, -0.0706F));
/*     */     
/* 155 */     body.m_171599_("part50", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-15.05F, -0.9753F, -0.0258F, 15.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-12.95F, -15.0F, -16.3F, 0.4943F, 0.2316F, 0.1231F));
/*     */     
/* 157 */     body.m_171599_("part51", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-12.75F, -19.0F, 0.0F, 17.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.25F, 1.0F, -8.0F, 0.48F, 0.0F, 0.0F));
/*     */     
/* 159 */     body.m_171599_("part52", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0672F, -9.0045F, -12.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-28.4F, -1.25F, -3.0F, 0.0F, -0.6981F, -0.3054F));
/*     */     
/* 161 */     body.m_171599_("part53", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.044F, -13.0153F, -6.5784F, 6.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-33.5F, -22.75F, -3.0F, -0.3938F, 0.5588F, 1.7578F));
/*     */     
/* 163 */     body.m_171599_("part54", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(1.9328F, -7.0045F, -13.0F, 5.0F, 1.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 164 */         .m_171514_(0, 24).m_171496_(1.9328F, -20.0045F, -13.0F, 5.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-28.4F, -1.25F, -3.0F, -0.0479F, -0.6699F, -0.6419F));
/*     */     
/* 166 */     body.m_171599_("part55", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-7.0198F, -5.1664F, -2.0F, 7.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 167 */         .m_171514_(0, 24).m_171496_(-19.0198F, -5.1664F, -2.0F, 12.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-29.9F, -5.85F, -1.0F, 0.0F, 0.0F, 1.9199F));
/*     */     
/* 169 */     body.m_171599_("part56", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0672F, -5.0045F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-28.4F, -1.25F, -1.0F, 0.0F, 0.0F, -0.3054F));
/*     */     
/* 171 */     body.m_171599_("part57", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.0048F, -5.0256F, -2.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 172 */         .m_171514_(0, 24).m_171496_(-0.0048F, -17.0256F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-13.0F, 6.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/* 174 */     body.m_171599_("part58", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -10.0F, -2.0F, 5.0F, 10.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, -1.2217F));
/*     */     
/* 176 */     body.m_171599_("part59", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.051F, -9.1636F, -13.1116F, 5.0F, 11.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(27.9F, -41.7F, 16.0F, 0.2347F, 1.3475F, -0.8946F));
/*     */     
/* 178 */     body.m_171599_("part60", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0294F, -8.706F, 0.0138F, 5.0F, 11.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(28.5F, -44.5F, 20.1F, 0.0955F, -1.0006F, -1.2042F));
/*     */     
/* 180 */     body.m_171599_("part61", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.0179F, 3.2586F, 0.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 181 */         .m_171514_(0, 24).m_171496_(-4.0179F, 0.2586F, 0.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 182 */         .m_171514_(0, 24).m_171496_(-4.0179F, -11.7414F, 0.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(25.5F, -1.3F, 9.0F, 0.0475F, -0.346F, 1.1698F));
/*     */     
/* 184 */     body.m_171599_("part62", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.0342F, -3.0348F, -1.9937F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 185 */         .m_171514_(0, 24).m_171496_(-5.0342F, -15.0348F, -1.9937F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(34.7F, -9.8F, 9.0F, 0.3244F, 0.1313F, -0.633F));
/*     */     
/* 187 */     body.m_171599_("part63", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9523F, 2.2409F, -12.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 188 */         .m_171514_(0, 24).m_171496_(-4.9523F, -6.7591F, -12.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(24.5F, -1.1F, -3.0F, 0.0F, 0.9599F, 1.309F));
/*     */     
/* 190 */     body.m_171599_("part64", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.7207F, -3.8735F, -2.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 191 */         .m_171514_(0, 24).m_171496_(-4.7207F, -15.8735F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(19.5F, 0.0F, -1.0F, 0.0F, 0.0F, 1.309F));
/*     */     
/* 193 */     body.m_171599_("part65", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.9406F, -1.0988F, -2.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 194 */         .m_171514_(0, 24).m_171496_(-4.9406F, -13.0988F, -2.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(33.5F, -8.7F, -1.0F, 0.0F, 0.0F, -0.5236F));
/*     */     
/* 196 */     body.m_171599_("part66", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.883F, -10.9741F, -2.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(10.5F, 6.5F, -1.0F, 0.0F, 0.0F, 0.9599F));
/*     */     
/* 198 */     body.m_171599_("part67", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -11.0F, -2.0F, 5.0F, 11.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 1.1345F));
/*     */     
/* 200 */     body.m_171599_("part68", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(0.0489F, -3.0731F, -2.0F, 5.0F, 3.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-10.7F, 8.0F, -1.0F, 0.0F, 0.0F, -0.8727F));
/*     */     
/* 202 */     body.m_171599_("part69", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5F, -3.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 4.0F, -6.0F, 0.1309F, 0.0F, 0.0F));
/*     */     
/* 204 */     body.m_171599_("part70", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.4965F, -8.2953F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 1.2519F, -0.2368F, 0.8109F));
/*     */     
/* 206 */     body.m_171599_("part71", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -4.2953F, -2.0F, 11.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/* 208 */     body.m_171599_("part72", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-4.4965F, -7.2953F, -2.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, 0.6981F));
/*     */     
/* 210 */     body.m_171599_("part73", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.0341F, -3.1877F, -4.5034F, 5.0F, 7.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.5F, 0.0F, -3.0F, 1.9222F, -0.318F, 0.8876F));
/*     */     
/* 212 */     body.m_171599_("part74", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -9.7983F, 6.0F, 7.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 2.2689F, 0.0F, -1.0036F));
/*     */     
/* 214 */     body.m_171599_("part75", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -5.7983F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 1.4399F, 0.0F, -1.0036F));
/*     */     
/* 216 */     body.m_171599_("part76", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -8.2953F, -2.0F, 6.0F, 10.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 1.0036F, 0.0F, -1.0036F));
/*     */     
/* 218 */     body.m_171599_("part77", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5035F, -7.2953F, -2.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.5672F));
/*     */     
/* 220 */     body.m_171599_("part78", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -6.2953F, -2.0F, 5.0F, 7.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, -1.0943F, 0.0603F, 0.8873F));
/*     */     
/* 222 */     body.m_171599_("part79", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -6.2953F, -2.0F, 6.0F, 8.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, -1.0908F, 0.0F, -1.0036F));
/*     */     
/* 224 */     body.m_171599_("part80", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -7.2953F, -2.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.0036F));
/*     */     
/* 226 */     body.m_171599_("part81", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5F, -8.8659F, -2.4041F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-2.5F, 6.5F, -3.0F, 0.3295F, 0.1172F, -0.3295F));
/*     */     
/* 228 */     body.m_171599_("part82", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -3.0F, 5.0F, 13.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 13.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
/*     */     
/* 230 */     body.m_171599_("part83", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -2.0F, -2.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 231 */         .m_171514_(0, 24).m_171496_(-2.5F, -21.0F, -2.0F, 5.0F, 19.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 15.0F, 0.0F, -0.2182F, 0.0F, 0.0F));
/*     */     
/* 233 */     return body;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\LowResDismantledBodyModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */