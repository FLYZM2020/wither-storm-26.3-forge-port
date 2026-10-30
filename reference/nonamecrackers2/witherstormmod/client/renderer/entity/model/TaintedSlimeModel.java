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
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.animation.TaintedSlimeAnimations;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TaintedSlime;
/*    */ 
/*    */ public class TaintedSlimeModel<T extends TaintedSlime>
/*    */   extends HierarchicalModel<T> {
/*    */   private final ModelPart root;
/*    */   
/*    */   public TaintedSlimeModel(ModelPart root) {
/* 20 */     this.root = root;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition() {
/* 25 */     MeshDefinition meshdefinition = new MeshDefinition();
/* 26 */     PartDefinition partdefinition = meshdefinition.m_171576_();
/*    */     
/* 28 */     PartDefinition bone = partdefinition.m_171599_("bone", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 19.0F, 0.0F));
/* 29 */     bone.m_171599_("cube_r1", CubeListBuilder.m_171558_().m_171514_(0, 20).m_171488_(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.m_171423_(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
/* 30 */     partdefinition.m_171599_("bb_main", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
/*    */     
/* 32 */     return LayerDefinition.m_171565_(meshdefinition, 48, 48);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelPart m_142109_() {
/* 38 */     return this.root;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float walkAnimation, float animationSpeed, float bob, float yRot, float xRot) {
/* 44 */     m_142109_().m_171331_().forEach(ModelPart::m_233569_);
/* 45 */     m_233381_(((TaintedSlime)entity).idle, TaintedSlimeAnimations.MODEL_IDLE, bob);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\TaintedSlimeModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */