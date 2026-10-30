/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*    */ 
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LowResSegmentBodyModel
/*    */ {
/*    */   public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
/* 13 */     PartDefinition body = root.m_171599_("lowResMass", CubeListBuilder.m_171558_().m_171514_(17, 40).m_171496_(-6.0F, -0.5F, -10.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 14 */         .m_171514_(17, 40).m_171496_(-3.0F, -0.5F, -10.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 15 */         .m_171514_(17, 40).m_171496_(0.0F, -0.5F, -10.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 16 */         .m_171514_(17, 40).m_171496_(-3.0F, 0.5F, -10.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171419_(2.0F, -4.0F, 0.0F));
/*    */     
/* 18 */     body.m_171599_("part1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0F, -10.0F, -6.0F, 7.0F, 9.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.0F, 2.5F, -0.5F, 0.0F, 0.2618F, 0.0436F));
/*    */     
/* 20 */     body.m_171599_("part2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-7.0F, -4.3F, -9.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.0F, 2.5F, -0.5F, 0.0F, 0.0F, -0.0436F));
/*    */     
/* 22 */     body.m_171599_("part3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-7.0F, -7.0F, -7.0F, 14.0F, 9.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.0F, 2.5F, -0.5F, 0.0F, 0.0F, -0.2182F));
/*    */     
/* 24 */     body.m_171599_("part4", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.1745F, -3.0038F, 4.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 25 */         .m_171514_(0, 24).m_171496_(-2.1745F, -6.0038F, -1.0F, 6.0F, 7.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.0F, 0.5F, -0.5F, 0.0F, 0.2618F, 0.1309F));
/*    */     
/* 27 */     body.m_171599_("part5", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.0F, -5.0F, -3.2F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.0F, 4.5F, -0.5F, -2.4771F, 1.2923F, -2.5016F));
/*    */     
/* 29 */     body.m_171599_("part6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.0F, -5.0F, -5.0F, 11.0F, 6.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.0F, 4.5F, -0.5F, -0.2618F, 0.0F, -0.0436F));
/*    */     
/* 31 */     body.m_171599_("part7", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.0F, -5.0F, 0.0F, 11.0F, 7.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(0.0F, 4.5F, -0.5F, 0.0F, 0.0F, -0.0436F));
/*    */     
/* 33 */     body.m_171599_("part8", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5F, -9.0F, -0.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.m_171423_(2.5F, 11.5F, 10.0F, 0.7854F, 0.0F, 0.0F));
/*    */     
/* 35 */     return body;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\LowResSegmentBodyModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */