/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity;
/*    */ 
/*    */ import net.minecraft.client.model.HierarchicalModel;
/*    */ import net.minecraft.client.model.IronGolemModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
/*    */ 
/*    */ public class SickenedIronGolemModel<T extends SickenedIronGolem>
/*    */   extends HierarchicalModel<T> {
/*    */   private final ModelPart root;
/*    */   private final ModelPart head;
/*    */   private final ModelPart rightArm;
/*    */   private final ModelPart leftArm;
/*    */   private final ModelPart rightLeg;
/*    */   private final ModelPart leftLeg;
/*    */   
/*    */   public SickenedIronGolemModel(ModelPart root) {
/* 21 */     this.root = root;
/* 22 */     this.head = root.getChild("head");
/* 23 */     this.rightArm = root.getChild("right_arm");
/* 24 */     this.leftArm = root.getChild("left_arm");
/* 25 */     this.rightLeg = root.getChild("right_leg");
/* 26 */     this.leftLeg = root.getChild("left_leg");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelPart root() {
/* 32 */     return this.root;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createBodyLayer() {
/* 37 */     return IronGolemModel.createBodyLayer();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float walkAnimation, float animationSpeed, float bob, float yRot, float xRot) {
/* 43 */     this.head.yRot = yRot * 0.017453292F;
/* 44 */     this.head.xRot = xRot * 0.017453292F;
/* 45 */     this.rightLeg.xRot = -1.5F * Mth.triangleWave(walkAnimation, 13.0F) * animationSpeed;
/* 46 */     this.leftLeg.xRot = 1.5F * Mth.triangleWave(walkAnimation, 13.0F) * animationSpeed;
/* 47 */     this.rightLeg.yRot = 0.0F;
/* 48 */     this.leftLeg.yRot = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void prepareMobModel(T entity, float walkAnimation, float animationSpeed, float partialTicks) {
/* 54 */     int i = entity.getAttackAnimationTick();
/* 55 */     if (i > 0) {
/*    */       
/* 57 */       this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave(i - partialTicks, 10.0F);
/* 58 */       this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave(i - partialTicks, 10.0F);
/*    */     }
/*    */     else {
/*    */       
/* 62 */       this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(walkAnimation, 13.0F)) * animationSpeed;
/* 63 */       this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(walkAnimation, 13.0F)) * animationSpeed;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\sickenedentity\SickenedIronGolemModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */