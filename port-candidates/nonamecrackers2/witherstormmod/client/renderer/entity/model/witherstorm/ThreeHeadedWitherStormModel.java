/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;
/*    */ 
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public abstract class ThreeHeadedWitherStormModel<T extends WitherStormEntity>
/*    */   extends AbstractWitherStormModel<T> {
/*    */   protected HeadModel<T> rightHead;
/*    */   protected HeadModel<T> middleHead;
/*    */   protected HeadModel<T> leftHead;
/*    */   
/*    */   public ThreeHeadedWitherStormModel(ModelPart root, float headScale) {
/* 18 */     super(root, headScale);
/*    */   }
/*    */ 
/*    */   
/*    */   protected static MeshDefinition createMesh(PartPose[] headPositions) {
/* 23 */     MeshDefinition mesh = AbstractWitherStormModel.createMesh();
/* 24 */     PartDefinition root = mesh.getRoot();
/* 25 */     PartDefinition heads = root.getChild("heads");
/* 26 */     HeadModel.populateDefinition(heads.addOrReplaceChild(HEADS[0], CubeListBuilder.create(), headPositions[0]));
/* 27 */     HeadModel.populateDefinition(heads.addOrReplaceChild(HEADS[1], CubeListBuilder.create(), headPositions[1]));
/* 28 */     HeadModel.populateDefinition(heads.addOrReplaceChild(HEADS[2], CubeListBuilder.create(), headPositions[2]));
/* 29 */     return mesh;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void configureHeads(ModelPart root, float scale) {
/* 35 */     this.rightHead = (HeadModel)new HeadModel<>(root.getChild(HEADS[0]), scale);
/* 36 */     this.middleHead = (HeadModel)new HeadModel<>(root.getChild(HEADS[1]), scale);
/* 37 */     this.leftHead = (HeadModel)new HeadModel<>(root.getChild(HEADS[2]), scale);
/* 38 */     this.heads.put(2, this.rightHead);
/* 39 */     this.heads.put(0, this.middleHead);
/* 40 */     this.heads.put(1, this.leftHead);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\ThreeHeadedWitherStormModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */