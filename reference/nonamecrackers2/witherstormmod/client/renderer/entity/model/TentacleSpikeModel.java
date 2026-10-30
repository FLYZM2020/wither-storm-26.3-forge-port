/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model;
/*    */ 
/*    */ import net.minecraft.client.model.HierarchicalModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;
/*    */ 
/*    */ public class TentacleSpikeModel<T extends TentacleSpike>
/*    */   extends HierarchicalModel<T> {
/*    */   private final ModelPart root;
/*    */   private final ModelPart base;
/*    */   private final ModelPart middle;
/*    */   private final ModelPart end;
/*    */   
/*    */   public TentacleSpikeModel(ModelPart root) {
/* 24 */     this.root = root;
/* 25 */     this.base = root.m_171324_("base");
/* 26 */     this.middle = this.base.m_171324_("middle");
/* 27 */     this.end = this.middle.m_171324_("end");
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition() {
/* 32 */     MeshDefinition meshdefinition = new MeshDefinition();
/* 33 */     PartDefinition partdefinition = meshdefinition.m_171576_();
/*    */     
/* 35 */     PartDefinition base = partdefinition.m_171599_("base", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-3.0F, -7.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.f_171404_);
/* 36 */     PartDefinition middle = base.m_171599_("middle", CubeListBuilder.m_171558_().m_171514_(0, 13).m_171488_(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.m_171419_(0.0F, -7.0F, 0.0F));
/* 37 */     middle.m_171599_("end", CubeListBuilder.m_171558_().m_171514_(16, 13).m_171488_(-1.0F, -11.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.m_171419_(0.0F, -9.0F, 0.0F));
/*    */     
/* 39 */     return LayerDefinition.m_171565_(meshdefinition, 32, 32);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float anim, float f, float f1, float yRot, float xRot) {
/* 45 */     float sway = anim * (3.0F - anim) + RandomSource.m_216335_(entity.m_19879_()).m_188501_() * 1000.0F;
/* 46 */     float zSway = Mth.m_14089_(sway) * 0.1F;
/* 47 */     float xSway = Mth.m_14031_(sway) * 0.1F;
/* 48 */     this.base.f_104205_ = zSway;
/* 49 */     this.base.f_104203_ = xSway;
/* 50 */     this.middle.f_104205_ = zSway;
/* 51 */     this.middle.f_104203_ = xSway;
/* 52 */     this.end.f_104205_ = zSway;
/* 53 */     this.end.f_104203_ = xSway;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelPart m_142109_() {
/* 59 */     return this.root;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\TentacleSpikeModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */