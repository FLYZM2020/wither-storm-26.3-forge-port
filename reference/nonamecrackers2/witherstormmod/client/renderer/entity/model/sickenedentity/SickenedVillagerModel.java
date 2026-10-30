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
/* 17 */   private final ModelPart hatRim = this.f_102809_.m_171324_("hat_rim");
/*    */ 
/*    */   
/*    */   public SickenedVillagerModel(ModelPart part) {
/* 21 */     super(part);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createBodyLayer() {
/* 26 */     MeshDefinition meshdefinition = HumanoidModel.m_170681_(CubeDeformation.f_171458_, 0.0F);
/* 27 */     PartDefinition partdefinition = meshdefinition.m_171576_();
/* 28 */     partdefinition.m_171599_("head", (new CubeListBuilder()).m_171514_(0, 0).m_171481_(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F).m_171514_(24, 0).m_171481_(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.f_171404_);
/* 29 */     PartDefinition partdefinition1 = partdefinition.m_171599_("hat", CubeListBuilder.m_171558_().m_171514_(32, 0).m_171488_(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.f_171404_);
/* 30 */     partdefinition1.m_171599_("hat_rim", CubeListBuilder.m_171558_().m_171514_(30, 47).m_171481_(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F), PartPose.m_171430_(-1.5707964F, 0.0F, 0.0F));
/* 31 */     partdefinition.m_171599_("body", CubeListBuilder.m_171558_().m_171514_(16, 20).m_171481_(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F).m_171514_(0, 38).m_171488_(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.f_171404_);
/* 32 */     partdefinition.m_171599_("right_arm", CubeListBuilder.m_171558_().m_171514_(44, 22).m_171481_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.m_171419_(-5.0F, 2.0F, 0.0F));
/* 33 */     partdefinition.m_171599_("left_arm", CubeListBuilder.m_171558_().m_171514_(44, 22).m_171480_().m_171481_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.m_171419_(5.0F, 2.0F, 0.0F));
/* 34 */     partdefinition.m_171599_("right_leg", CubeListBuilder.m_171558_().m_171514_(0, 22).m_171481_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.m_171419_(-2.0F, 12.0F, 0.0F));
/* 35 */     partdefinition.m_171599_("left_leg", CubeListBuilder.m_171558_().m_171514_(0, 22).m_171480_().m_171481_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.m_171419_(2.0F, 12.0F, 0.0F));
/* 36 */     return LayerDefinition.m_171565_(meshdefinition, 64, 64);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
/* 41 */     MeshDefinition meshdefinition = HumanoidModel.m_170681_(deformation, 0.0F);
/* 42 */     PartDefinition partdefinition = meshdefinition.m_171576_();
/* 43 */     partdefinition.m_171599_("head", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation), PartPose.f_171404_);
/* 44 */     partdefinition.m_171599_("body", CubeListBuilder.m_171558_().m_171514_(16, 16).m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation.m_171469_(0.1F)), PartPose.f_171404_);
/* 45 */     partdefinition.m_171599_("right_leg", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171488_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.m_171469_(0.1F)), PartPose.m_171419_(-2.0F, 12.0F, 0.0F));
/* 46 */     partdefinition.m_171599_("left_leg", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171480_().m_171488_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.m_171469_(0.1F)), PartPose.m_171419_(2.0F, 12.0F, 0.0F));
/* 47 */     partdefinition.m_171597_("hat").m_171599_("hat_rim", CubeListBuilder.m_171558_(), PartPose.f_171404_);
/* 48 */     return LayerDefinition.m_171565_(meshdefinition, 64, 32);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float p_104176_, float p_104177_, float p_104178_, float p_104179_, float p_104180_) {
/* 53 */     super.m_6973_((LivingEntity)entity, p_104176_, p_104177_, p_104178_, p_104179_, p_104180_);
/* 54 */     AnimationUtils.m_102102_(this.f_102812_, this.f_102811_, entity.m_5912_(), this.f_102608_, p_104178_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7491_(boolean flag) {
/* 59 */     this.f_102808_.f_104207_ = flag;
/* 60 */     this.f_102809_.f_104207_ = flag;
/* 61 */     this.hatRim.f_104207_ = flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\sickenedentity\SickenedVillagerModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */