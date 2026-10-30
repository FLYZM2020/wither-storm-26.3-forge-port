/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*    */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.HeadModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
/*    */ 
/*    */ public class WitherStormHeadModel
/*    */   extends EntityModel<WitherStormHeadEntity> {
/*    */   private final HeadModel<WitherStormHeadEntity> head;
/*    */   
/*    */   public WitherStormHeadModel(ModelPart root) {
/* 20 */     this.head = new HeadModel(root, 3.0F);
/* 21 */     this.head.tractorBeamDistance = 20.0F;
/* 22 */     this.head.tractorBeamStartSize = 0.1F;
/* 23 */     this.head.tractorBeamEndSize = 2.0F;
/* 24 */     this.head.tractorBeamXOffset = 0.0F;
/* 25 */     this.head.tractorBeamYOffset = 8.0F;
/* 26 */     this.head.tractorBeamZOffset = 0.0F;
/* 27 */     this.head.pivotOffsetX = -4.0F;
/* 28 */     this.head.pivotOffsetY = 0.325F;
/* 29 */     this.head.pivotOffsetZ = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LayerDefinition createLayerDefinition() {
/* 34 */     MeshDefinition mesh = new MeshDefinition();
/* 35 */     HeadModel.populateDefinition(mesh.m_171576_());
/* 36 */     return LayerDefinition.m_171565_(mesh, 160, 160);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnim(WitherStormHeadEntity entity, float walkAnimPos, float walkAnimSpeed, float bob, float yRot, float xRot) {
/* 42 */     this.head.setupAnimations((LivingEntity)entity, Minecraft.m_91087_().getPartialTick(), bob, yRot, xRot, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7695_(PoseStack stack, VertexConsumer builder, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
/* 48 */     stack.m_85836_();
/* 49 */     this.head.scale(stack);
/* 50 */     this.head.root().m_104301_(stack, builder, p_225598_3_, p_225598_4_);
/* 51 */     stack.m_85849_();
/*    */   }
/*    */ 
/*    */   
/*    */   public HeadModel<WitherStormHeadEntity> getHead() {
/* 56 */     return this.head;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\WitherStormHeadModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */