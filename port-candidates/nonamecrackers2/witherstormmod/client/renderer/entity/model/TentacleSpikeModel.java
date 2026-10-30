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
/* 25 */     this.base = root.getChild("base");
/* 26 */     this.middle = this.base.getChild("middle");
/* 27 */     this.end = this.middle.getChild("end");
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition() {
/* 32 */     MeshDefinition meshdefinition = new MeshDefinition();
/* 33 */     PartDefinition partdefinition = meshdefinition.getRoot();
/*    */     
/* 35 */     PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
/* 36 */     PartDefinition middle = base.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));
/* 37 */     middle.addOrReplaceChild("end", CubeListBuilder.create().texOffs(16, 13).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));
/*    */     
/* 39 */     return LayerDefinition.create(meshdefinition, 32, 32);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float anim, float f, float f1, float yRot, float xRot) {
/* 45 */     float sway = anim * (3.0F - anim) + RandomSource.create(entity.getId()).nextFloat() * 1000.0F;
/* 46 */     float zSway = Mth.cos(sway) * 0.1F;
/* 47 */     float xSway = Mth.sin(sway) * 0.1F;
/* 48 */     this.base.zRot = zSway;
/* 49 */     this.base.xRot = xSway;
/* 50 */     this.middle.zRot = zSway;
/* 51 */     this.middle.xRot = xSway;
/* 52 */     this.end.zRot = zSway;
/* 53 */     this.end.xRot = xSway;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelPart root() {
/* 59 */     return this.root;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\TentacleSpikeModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */