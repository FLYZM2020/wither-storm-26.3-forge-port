/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock;
/*    */ 
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ 
/*    */ 
/*    */ public class RibModel
/*    */ {
/*    */   protected final ModelPart rib;
/*    */   protected final ModelPart segmentOne;
/*    */   protected final ModelPart segmentTwo;
/*    */   protected final ModelPart segmentThree;
/*    */   protected final ModelPart segmentFour;
/*    */   
/*    */   public RibModel(ModelPart root) {
/* 18 */     this.rib = root.m_171324_("rib");
/* 19 */     this.segmentOne = this.rib.m_171324_("segmentOne");
/* 20 */     this.segmentTwo = this.segmentOne.m_171324_("segmentTwo");
/* 21 */     this.segmentThree = this.segmentTwo.m_171324_("segmentThree");
/* 22 */     this.segmentFour = this.segmentThree.m_171324_("segmentFour");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void populateDefinition(PartDefinition root, PartPose offset, boolean mirror) {
/* 50 */     PartDefinition base = root.m_171599_("rib", CubeListBuilder.m_171558_(), offset);
/* 51 */     PartDefinition segmentOne = base.m_171599_("segmentOne", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171506_(-6.5F, -21.0F, -4.0F, 13.0F, 21.0F, 13.0F, mirror), PartPose.f_171404_);
/* 52 */     PartDefinition segmentTwo = segmentOne.m_171599_("segmentTwo", CubeListBuilder.m_171558_().m_171514_(0, 34).m_171506_(-5.5F, -29.0F, -4.0F, 11.0F, 29.0F, 10.0F, mirror), PartPose.m_171419_(0.0F, -21.0F, 0.0F));
/* 53 */     PartDefinition segmentThree = segmentTwo.m_171599_("segmentThree", CubeListBuilder.m_171558_().m_171514_(0, 73).m_171506_(-4.5F, -29.0F, -3.0F, 9.0F, 29.0F, 8.0F, mirror), PartPose.m_171419_(0.0F, -29.0F, -1.0F));
/* 54 */     segmentThree.m_171599_("segmentFour", CubeListBuilder.m_171558_().m_171514_(52, 0).m_171506_(-3.5F, -32.0F, -3.0F, 7.0F, 32.0F, 6.0F, mirror), PartPose.m_171419_(0.0F, -29.0F, 0.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\commandblock\RibModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */