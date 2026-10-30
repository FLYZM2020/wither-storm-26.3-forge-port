/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*    */ 
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LowResIntermediateEvolvedDestroyerBodyModel
/*    */ {
/*    */   public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
/* 13 */     PartDefinition body = root.m_171599_("lowResMass", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0F, -6.0F, -3.0F, 5.0F, 18.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 14 */         .m_171514_(0, 24).m_171496_(-3.0F, -11.0F, -10.0F, 11.0F, 9.0F, 8.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 15 */         .m_171514_(0, 24).m_171496_(-6.0F, -18.0F, -3.0F, 11.0F, 12.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 16 */         .m_171514_(0, 24).m_171496_(-3.0F, 12.0F, -3.0F, 5.0F, 3.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 17 */         .m_171514_(0, 24).m_171496_(-6.0F, -14.3919F, 2.4473F, 11.0F, 9.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 18 */         .m_171514_(0, 24).m_171496_(-2.0F, 16.0F, 0.0F, 1.0F, 5.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 19 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, 0.0F, 1.0F, 7.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 20 */         .m_171514_(0, 24).m_171496_(-1.0F, 16.0F, -2.0F, 1.0F, 3.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 21 */         .m_171514_(0, 24).m_171496_(-1.0F, 16.0F, -3.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 22 */         .m_171514_(0, 24).m_171496_(-1.0F, 15.0F, -3.0F, 1.0F, 1.0F, 3.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 23 */         .m_171514_(0, 24).m_171496_(-2.0F, 15.0F, -3.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 24 */         .m_171514_(0, 24).m_171496_(-3.0F, 15.0F, 0.0F, 1.0F, 3.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 25 */         .m_171514_(0, 24).m_171496_(-2.0F, 15.0F, 0.0F, 1.0F, 1.0F, 3.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 26 */         .m_171514_(0, 24).m_171496_(1.0F, 15.0F, -2.0F, 1.0F, 1.0F, 2.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 27 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, -2.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 28 */         .m_171514_(0, 24).m_171496_(1.0F, 16.0F, -2.0F, 1.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 29 */         .m_171514_(0, 24).m_171496_(-1.0F, 15.0F, 2.0F, 1.0F, 3.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 30 */         .m_171514_(0, 24).m_171496_(0.0F, 15.0F, 1.0F, 2.0F, 1.0F, 1.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171419_(0.0F, -15.0F, 0.0F));
/*    */     
/* 32 */     body.m_171599_("voxel_r1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5F, -3.0F, -1.0F, 3.0F, 5.0F, 2.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 4.0F, -6.0F, 0.1309F, 0.0F, 0.0F));
/*    */     
/* 34 */     body.m_171599_("voxel_r2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -2.0F, 5.0F, 14.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 0.3927F));
/*    */     
/* 36 */     body.m_171599_("voxel_r3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.4965F, -8.2953F, -1.0F, 5.0F, 8.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 1.2519F, -0.2368F, 0.8109F));
/*    */     
/* 38 */     body.m_171599_("voxel_r4", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -4.2953F, -2.0F, 11.0F, 4.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*    */     
/* 40 */     body.m_171599_("voxel_r5", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.704F, -9.1279F, -2.0F, 7.0F, 11.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(8.5F, -8.0F, -1.0F, 0.0F, 0.0F, -0.6545F));
/*    */     
/* 42 */     body.m_171599_("voxel_r6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-4.4965F, -7.2953F, -2.0F, 7.0F, 8.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, 0.6981F));
/*    */     
/* 44 */     body.m_171599_("voxel_r7", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.0341F, -3.1877F, -4.5034F, 5.0F, 7.0F, 13.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(0.5F, 0.0F, -3.0F, 1.9222F, -0.318F, 0.8876F));
/*    */     
/* 46 */     body.m_171599_("voxel_r8", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -9.7983F, 6.0F, 7.0F, 13.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 2.2689F, 0.0F, -1.0036F));
/*    */     
/* 48 */     body.m_171599_("voxel_r9", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217F, -10.2414F, -5.7983F, 6.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-7.5F, -5.0F, -1.0F, 1.4399F, 0.0F, -1.0036F));
/*    */     
/* 50 */     body.m_171599_("voxel_r10", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -8.2953F, -2.0F, 12.0F, 10.0F, 7.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 1.0036F, 0.0F, -1.0036F));
/*    */     
/* 52 */     body.m_171599_("voxel_r11", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5035F, -7.2953F, -2.0F, 5.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.5672F));
/*    */     
/* 54 */     body.m_171599_("voxel_r12", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965F, -6.2953F, -2.0F, 5.0F, 7.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale).m_171555_(false), PartPose.m_171423_(4.5F, -2.0F, -1.0F, -1.0943F, 0.0603F, 0.8873F));
/*    */     
/* 56 */     body.m_171599_("voxel_r13", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -6.2953F, -2.0F, 6.0F, 8.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, -1.0908F, 0.0F, -1.0036F));
/*    */     
/* 58 */     body.m_171599_("voxel_r14", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.564F, -3.5178F, -9.0F, 6.0F, 10.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-9.5F, -11.0F, -6.0F, 0.0F, -1.1345F, 0.1309F));
/*    */     
/* 60 */     body.m_171599_("voxel_r15", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.9555F, -5.4921F, -5.0F, 8.0F, 10.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-9.5F, -8.0F, -5.0F, 0.0F, -0.7418F, 0.1309F));
/*    */     
/* 62 */     body.m_171599_("voxel_r16", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.3471F, -12.4664F, -5.0F, 6.0F, 13.0F, 9.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-9.5F, -5.0F, -1.0F, 0.0F, 0.0F, 0.1309F));
/*    */     
/* 64 */     body.m_171599_("voxel_r17", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035F, -7.2953F, -2.0F, 6.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.0036F));
/*    */     
/* 66 */     body.m_171599_("voxel_r18", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -2.0F, 5.0F, 14.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, -0.4363F));
/*    */     
/* 68 */     body.m_171599_("voxel_r19", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5F, -8.8659F, -2.4041F, 3.0F, 9.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-2.5F, 6.5F, -3.0F, 0.3295F, 0.1172F, -0.3295F));
/*    */     
/* 70 */     body.m_171599_("voxel_r20", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -14.0F, -3.0F, 5.0F, 13.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 13.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
/*    */     
/* 72 */     body.m_171599_("voxel_r21", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5F, -2.0F, -2.0F, 5.0F, 2.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale)
/* 73 */         .m_171514_(0, 24).m_171496_(-2.5F, -21.0F, -2.0F, 5.0F, 19.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, 15.0F, 0.0F, -0.2182F, 0.0F, 0.0F));
/*    */     
/* 75 */     body.m_171599_("voxel_r22", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-5.5F, -3.3919F, -2.5527F, 11.0F, 5.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(-0.5F, -14.0F, 5.0F, 1.2217F, 0.0F, 0.0F));
/*    */     
/* 77 */     body.m_171599_("voxel_r23", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5F, -5.3919F, -2.5527F, 7.0F, 9.0F, 5.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(4.5F, -9.0F, 5.0F, 0.0F, 0.829F, 0.0F));
/*    */     
/* 79 */     body.m_171599_("voxel_r24", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-9.0F, -8.0F, -2.0F, 14.0F, 8.0F, 8.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(3.0F, -11.0F, -8.0F, -0.9163F, 0.0F, 0.0F));
/*    */     
/* 81 */     body.m_171599_("voxel_r25", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.0F, -8.0F, -1.0F, 9.0F, 11.0F, 6.0F, CubeDeformation.f_171458_, texScale, texScale), PartPose.m_171423_(8.0F, -5.0F, -7.0F, 0.0F, -1.0908F, 0.0F));
/*    */     
/* 83 */     return body;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\LowResIntermediateEvolvedDestroyerBodyModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */