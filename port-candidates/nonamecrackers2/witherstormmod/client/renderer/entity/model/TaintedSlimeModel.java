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
/* 26 */     PartDefinition partdefinition = meshdefinition.getRoot();
/*    */     
/* 28 */     PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));
/* 29 */     bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 20).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
/* 30 */     partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
/*    */     
/* 32 */     return LayerDefinition.create(meshdefinition, 48, 48);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelPart root() {
/* 38 */     return this.root;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float walkAnimation, float animationSpeed, float bob, float yRot, float xRot) {
/* 44 */     root().getAllParts().forEach(ModelPart::resetPose);
/* 45 */     animate(((TaintedSlime)entity).idle, TaintedSlimeAnimations.MODEL_IDLE, bob);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\TaintedSlimeModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */