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
/*    */ public abstract class SingleHeadWitherStormModel<T extends WitherStormEntity>
/*    */   extends AbstractWitherStormModel<T> {
/*    */   protected HeadModel<T> primaryHead;
/*    */   
/*    */   public SingleHeadWitherStormModel(ModelPart root, float scale) {
/* 16 */     super(root, scale);
/*    */   }
/*    */ 
/*    */   
/*    */   protected static MeshDefinition createMesh(PartPose headPos) {
/* 21 */     MeshDefinition mesh = AbstractWitherStormModel.createMesh();
/* 22 */     PartDefinition root = mesh.m_171576_();
/* 23 */     PartDefinition heads = root.m_171597_("heads");
/* 24 */     HeadModel.populateDefinition(heads.m_171599_(HEADS[0], CubeListBuilder.m_171558_(), headPos));
/* 25 */     return mesh;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void configureHeads(ModelPart root, float scale) {
/* 31 */     this.primaryHead = (HeadModel)new HeadModel<>(root.m_171324_(HEADS[0]), scale);
/* 32 */     this.primaryHead.tractorBeamDistance = 90.0F;
/* 33 */     this.primaryHead.tractorBeamStartSize = 0.1F;
/* 34 */     this.primaryHead.tractorBeamEndSize = 5.0F;
/* 35 */     this.primaryHead.tractorBeamXOffset = 0.0F;
/* 36 */     this.primaryHead.tractorBeamYOffset = 34.0F;
/* 37 */     this.primaryHead.tractorBeamZOffset = 0.0F;
/* 38 */     this.primaryHead.pivotOffsetX = -19.0F;
/* 39 */     this.primaryHead.pivotOffsetY = 1.85F;
/* 40 */     this.heads.put(0, this.primaryHead);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\SingleHeadWitherStormModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */