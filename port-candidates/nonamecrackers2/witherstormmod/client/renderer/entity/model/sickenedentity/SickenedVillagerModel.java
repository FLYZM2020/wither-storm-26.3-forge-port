/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity;
/*    */ import net.minecraft.client.model.AnimationUtils;
/*    */ import net.minecraft.client.model.HumanoidModel;
/*    */ import net.minecraft.client.model.VillagerHeadModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedVillager;
/*    */ 
/*    */ public class SickenedVillagerModel<T extends SickenedVillager> extends HumanoidModel<T> implements VillagerHeadModel {
/* 17 */   private final ModelPart hatRim = this.hat.getChild("hat_rim");
/*    */ 
/*    */   
/*    */   public SickenedVillagerModel(ModelPart part) {
/* 21 */     super(part);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createBodyLayer() {
/* 26 */     MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
/* 27 */     PartDefinition partdefinition = meshdefinition.getRoot();
/* 28 */     partdefinition.addOrReplaceChild("head", (new CubeListBuilder()).texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F).texOffs(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.ZERO);
/* 29 */     PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.ZERO);
/* 30 */     partdefinition1.addOrReplaceChild("hat_rim", CubeListBuilder.create().texOffs(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F), PartPose.rotation(-1.5707964F, 0.0F, 0.0F));
/* 31 */     partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F).texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.ZERO);
/* 32 */     partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(44, 22).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
/* 33 */     partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
/* 34 */     partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
/* 35 */     partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
/* 36 */     return LayerDefinition.create(meshdefinition, 64, 64);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
/* 41 */     MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
/* 42 */     PartDefinition partdefinition = meshdefinition.getRoot();
/* 43 */     partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation), PartPose.ZERO);
/* 44 */     partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation.extend(0.1F)), PartPose.ZERO);
/* 45 */     partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.1F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
/* 46 */     partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.1F)), PartPose.offset(2.0F, 12.0F, 0.0F));
/* 47 */     partdefinition.getChild("hat").addOrReplaceChild("hat_rim", CubeListBuilder.create(), PartPose.ZERO);
/* 48 */     return LayerDefinition.create(meshdefinition, 64, 32);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float p_104176_, float p_104177_, float p_104178_, float p_104179_, float p_104180_) {
/* 53 */     super.setupAnim((LivingEntity)entity, p_104176_, p_104177_, p_104178_, p_104179_, p_104180_);
/* 54 */     AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, entity.isAggressive(), this.attackTime, p_104178_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void hatVisible(boolean flag) {
/* 59 */     this.head.visible = flag;
/* 60 */     this.hat.visible = flag;
/* 61 */     this.hatRim.visible = flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\sickenedentity\SickenedVillagerModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */