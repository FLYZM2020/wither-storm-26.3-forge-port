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
/* 18 */     this.rib = root.getChild("rib");
/* 19 */     this.segmentOne = this.rib.getChild("segmentOne");
/* 20 */     this.segmentTwo = this.segmentOne.getChild("segmentTwo");
/* 21 */     this.segmentThree = this.segmentTwo.getChild("segmentThree");
/* 22 */     this.segmentFour = this.segmentThree.getChild("segmentFour");
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
/* 50 */     PartDefinition base = root.addOrReplaceChild("rib", CubeListBuilder.create(), offset);
/* 51 */     PartDefinition segmentOne = base.addOrReplaceChild("segmentOne", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -21.0F, -4.0F, 13.0F, 21.0F, 13.0F, mirror), PartPose.ZERO);
/* 52 */     PartDefinition segmentTwo = segmentOne.addOrReplaceChild("segmentTwo", CubeListBuilder.create().texOffs(0, 34).addBox(-5.5F, -29.0F, -4.0F, 11.0F, 29.0F, 10.0F, mirror), PartPose.offset(0.0F, -21.0F, 0.0F));
/* 53 */     PartDefinition segmentThree = segmentTwo.addOrReplaceChild("segmentThree", CubeListBuilder.create().texOffs(0, 73).addBox(-4.5F, -29.0F, -3.0F, 9.0F, 29.0F, 8.0F, mirror), PartPose.offset(0.0F, -29.0F, -1.0F));
/* 54 */     segmentThree.addOrReplaceChild("segmentFour", CubeListBuilder.create().texOffs(52, 0).addBox(-3.5F, -32.0F, -3.0F, 7.0F, 32.0F, 6.0F, mirror), PartPose.offset(0.0F, -29.0F, 0.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\commandblock\RibModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */