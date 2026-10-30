/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*     */ 
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LowResEvolvedDestroyerBodyModel
/*     */ {
/*     */   public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
/*  13 */     PartDefinition body = root.m_171599_("lowResMass", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0F, -6.0F, -3.0F, 5.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  14 */         .m_171514_(0, 24).m_171496_(-7.0F, -29.0F, -8.0F, 13.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  15 */         .m_171514_(0, 24).m_171496_(6.0F, -29.0F, -8.0F, 1.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  16 */         .m_171514_(0, 24).m_171496_(-19.0F, -25.0F, -4.0F, 1.0F, 15.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  17 */         .m_171514_(0, 24).m_171480_().m_171496_(18.0F, -25.0F, -4.0F, 1.0F, 15.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  18 */         .m_171514_(0, 24).m_171496_(-7.0F, -24.0F, 3.0F, 13.0F, 14.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  19 */         .m_171514_(0, 24).m_171496_(-7.0F, -10.0F, 3.0F, 13.0F, 2.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  20 */         .m_171514_(0, 24).m_171496_(-3.0F, 12.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  21 */         .m_171514_(0, 24).m_171496_(-2.0F, 16.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  22 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  23 */         .m_171514_(0, 24).m_171496_(-1.0F, 16.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  24 */         .m_171514_(0, 24).m_171496_(-1.0F, 16.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  25 */         .m_171514_(0, 24).m_171496_(-1.0F, 15.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  26 */         .m_171514_(0, 24).m_171496_(-2.0F, 15.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  27 */         .m_171514_(0, 24).m_171496_(-3.0F, 15.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  28 */         .m_171514_(0, 24).m_171496_(-2.0F, 15.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  29 */         .m_171514_(0, 24).m_171496_(1.0F, 15.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  30 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  31 */         .m_171514_(0, 24).m_171496_(1.0F, 16.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  32 */         .m_171514_(0, 24).m_171496_(-1.0F, 15.0F, 2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  33 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171419_(0.0F, -15.0F, 0.0F));
/*     */     
/*  35 */     body.m_171599_("part1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5F, -3.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 4.0F, -6.0F, 0.1309F, 0.0F, 0.0F));
/*     */     
/*  37 */     body.m_171599_("part2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5F, -3.0F, -2.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  38 */         .m_171514_(0, 24).m_171496_(-0.5F, -21.0F, -2.0F, 5.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 0.3927F));
/*     */     
/*  40 */     body.m_171599_("part3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.4965F, -8.2953F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 1.2519F, -0.2368F, 0.8109F));
/*     */     
/*  42 */     body.m_171599_("part4", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -4.2953F, -2.0F, 11.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/*  44 */     body.m_171599_("part5", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.0341F, -3.1877F, -4.5034F, 6.0F, 7.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.5F, 0.0F, -3.0F, 1.8785F, -0.318F, 0.8876F));
/*     */     
/*  46 */     body.m_171599_("part6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -9.7983F, 6.0F, 7.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 2.2689F, 0.0F, -1.0036F));
/*     */     
/*  48 */     body.m_171599_("part7", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -5.7983F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 1.4399F, 0.0F, -1.0036F));
/*     */     
/*  50 */     body.m_171599_("part8", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5035F, -7.2953F, -2.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.5672F));
/*     */     
/*  52 */     body.m_171599_("part9", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5035F, -9.2953F, -2.0F, 5.0F, 10.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, -0.7144F, 0.1674F, -1.3419F));
/*     */     
/*  54 */     body.m_171599_("part10", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -9.2953F, -2.0F, 5.0F, 10.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, -0.7144F, -0.1674F, 1.3419F));
/*     */     
/*  56 */     body.m_171599_("part11", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -16.2953F, -7.0F, 6.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, 0.9599F));
/*     */     
/*  58 */     body.m_171599_("part12", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -16.2953F, -7.0F, 6.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.9599F));
/*     */     
/*  60 */     body.m_171599_("part13", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.5F, 1.0F, -2.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  61 */         .m_171514_(0, 24).m_171496_(-4.5F, -17.0F, -2.0F, 5.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, -0.4363F));
/*     */     
/*  63 */     body.m_171599_("part14", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5F, -8.8659F, -2.4041F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-2.5F, 6.5F, -3.0F, 0.3295F, 0.1172F, -0.3295F));
/*     */     
/*  65 */     body.m_171599_("part15", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -18.0F, -3.0F, 5.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 13.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
/*     */     
/*  67 */     body.m_171599_("part16", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -2.0F, -2.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 15.0F, 0.0F, -0.2182F, 0.0F, 0.0F));
/*     */     
/*  69 */     body.m_171599_("part17", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -21.6493F, 0.9289F, 5.0F, 19.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, 15.0F, -3.0F, -0.2182F, 0.0F, 0.0F));
/*     */     
/*  71 */     body.m_171599_("part18", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(10.5F, -10.0495F, -7.867F, 1.0F, 9.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  72 */         .m_171514_(0, 24).m_171496_(-10.5F, -10.0495F, -7.867F, 21.0F, 9.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, -19.0F, 0.0F, 0.1309F, 0.0F, 0.0F));
/*     */     
/*  74 */     body.m_171599_("part19", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.5F, -14.0F, -9.0F, 22.0F, 18.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, -6.0F, 0.0F, -0.0873F, 0.0F, 0.0F));
/*     */     
/*  76 */     body.m_171599_("part20", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-1.0F, 1.0F, 1.0F, 2.0F, 5.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  77 */         .m_171514_(0, 24).m_171480_().m_171496_(-1.0F, -11.0F, 1.0F, 2.0F, 12.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  78 */         .m_171514_(0, 24).m_171480_().m_171496_(-14.0F, 2.0F, 1.0F, 13.0F, 4.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false)
/*  79 */         .m_171514_(0, 24).m_171480_().m_171496_(-14.0F, -11.0F, 1.0F, 13.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.0F, -14.0F, 2.0F, 0.0F, -0.6109F, 0.0F));
/*     */     
/*  81 */     body.m_171599_("part21", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.0F, 2.0F, 1.0F, 2.0F, 4.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  82 */         .m_171514_(0, 24).m_171496_(1.0F, 2.0F, 1.0F, 13.0F, 4.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  83 */         .m_171514_(0, 24).m_171496_(-1.0F, -11.0F, 1.0F, 2.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  84 */         .m_171514_(0, 24).m_171496_(1.0F, -11.0F, 1.0F, 13.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.0F, -14.0F, 2.0F, 0.0F, 0.6109F, 0.0F));
/*     */     
/*  86 */     body.m_171599_("part22", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(0.5F, 0.0F, -2.5F, 13.0F, 5.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(6.5F, -29.0F, -1.5F, 0.0F, 0.0F, 0.2618F));
/*     */     
/*  88 */     body.m_171599_("part23", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(0.5F, 0.0F, 0.5F, 13.0F, 5.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(6.5F, -29.0F, -8.5F, 0.0F, -0.2182F, 0.2618F));
/*     */     
/*  90 */     body.m_171599_("part24", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-13.5F, 0.0F, 0.5F, 13.0F, 5.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-6.5F, -29.0F, -8.5F, 0.0F, 0.2182F, -0.2618F));
/*     */     
/*  92 */     body.m_171599_("part25", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-13.5F, 0.0F, -2.5F, 13.0F, 5.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-6.5F, -29.0F, -1.5F, 0.0F, 0.0F, -0.2618F));
/*     */     
/*  94 */     body.m_171599_("part26", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(5.5F, -18.0F, -12.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale).m_171555_(false), PartPose.m_171423_(0.5F, -6.0F, 0.0F, 0.0F, -0.4363F, 0.0F));
/*     */     
/*  96 */     body.m_171599_("part27", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-14.5F, -18.0F, -12.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, -6.0F, 0.0F, 0.0F, 0.4363F, 0.0F));
/*     */     
/*  98 */     body.m_171599_("part28", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.5F, -10.0F, -8.0F, 13.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  99 */         .m_171514_(0, 24).m_171496_(-6.5F, -22.0F, -8.0F, 13.0F, 12.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, -6.0F, 0.0F, -0.48F, 0.0F, 0.0F));
/*     */     
/* 101 */     body.m_171599_("part29", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-6.5F, 14.0F, -8.0F, 13.0F, 2.0F, 8.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 102 */         .m_171514_(0, 24).m_171496_(-6.5F, 0.0F, -10.0F, 13.0F, 14.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(-0.5F, -8.0F, 13.0F, -0.6109F, 0.0F, 0.0F));
/*     */     
/* 104 */     return body;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\LowResEvolvedDestroyerBodyModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */