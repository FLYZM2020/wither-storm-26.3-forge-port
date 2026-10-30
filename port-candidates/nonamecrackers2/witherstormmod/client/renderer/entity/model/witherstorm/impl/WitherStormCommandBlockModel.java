/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.util.Mth;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ public class WitherStormCommandBlockModel<T extends WitherStormEntity>
/*     */   extends AbstractWitherStormModel<T>
/*     */ {
/*     */   private final ModelPart base;
/*     */   private final ModelPart centerHead;
/*     */   private final ModelPart rightHead;
/*     */   private final ModelPart leftHead;
/*     */   private final ModelPart ribcage;
/*     */   private final ModelPart tail;
/*     */   
/*     */   public WitherStormCommandBlockModel(ModelPart root) {
/*  28 */     super(root, 1.0F);
/*     */     
/*  30 */     this.base = root.getChild("witherBase");
/*  31 */     this.ribcage = this.base.getChild("ribcage");
/*  32 */     this.tail = this.base.getChild("tail");
/*  33 */     this.centerHead = this.base.getChild("center_head");
/*  34 */     this.rightHead = this.base.getChild("right_head");
/*  35 */     this.leftHead = this.base.getChild("left_head");
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition(CubeDeformation def) {
/*  40 */     return LayerDefinition.create(createBaseMesh(AbstractWitherStormModel.createMesh(), def, true, true, true), 160, 160);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MeshDefinition createBaseMesh(MeshDefinition mesh, CubeDeformation def, boolean hasCenterHead, boolean hasRibcageExtension, boolean hasTail) {
/*  45 */     PartDefinition base = mesh.getRoot().addOrReplaceChild("witherBase", CubeListBuilder.create(), PartPose.ZERO);
/*  46 */     base.addOrReplaceChild("shoulders", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F, 3.9F, -0.5F, 20.0F, 3.0F, 3.0F, def), PartPose.ZERO);
/*  47 */     PartDefinition ribcage = base.addOrReplaceChild("ribcage", CubeListBuilder.create()
/*  48 */         .texOffs(0, 22).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, def)
/*  49 */         .texOffs(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, def)
/*  50 */         .texOffs(24, 22).addBox(-4.0F, 4.5F, 0.5F, 11.0F, 2.0F, 2.0F, def)
/*  51 */         .texOffs(24, 22).addBox(-4.0F, 7.5F, 0.5F, 11.0F, 2.0F, 2.0F, def), 
/*  52 */         PartPose.offsetAndRotation(-2.0F, 6.9F, -0.5F, 0.20420352F, 0.0F, 0.0F));
/*  53 */     if (hasRibcageExtension) {
/*     */       
/*  55 */       PartDefinition ribcageExtension = ribcage.addOrReplaceChild("ribcageExtension", CubeListBuilder.create().texOffs(128, 40).addBox(-5.5F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  56 */           .texOffs(128, 40).addBox(-5.5F, -5.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  57 */           .texOffs(128, 40).addBox(-5.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  58 */           .texOffs(128, 40).addBox(3.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  59 */           .texOffs(128, 40).addBox(3.5F, -5.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  60 */           .texOffs(128, 40).addBox(3.5F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, def)
/*  61 */           .texOffs(140, 44).addBox(2.5F, -2.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  62 */           .texOffs(140, 44).addBox(2.5F, -5.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  63 */           .texOffs(140, 44).addBox(2.5F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  64 */           .texOffs(140, 44).addBox(-3.5F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  65 */           .texOffs(140, 44).addBox(-3.5F, -5.0F, -4.0F, 1.0F, 2.0F, 2.0F, def)
/*  66 */           .texOffs(140, 44).addBox(-3.5F, -2.0F, -4.0F, 1.0F, 2.0F, 2.0F, def), 
/*  67 */           PartPose.offset(1.5F, 9.5F, -3.5F));
/*  68 */       ribcageExtension.addOrReplaceChild("block", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, def.extend(0.001F), 0.5F, 0.5F), PartPose.ZERO);
/*     */     } 
/*  70 */     if (hasTail) {
/*  71 */       base.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(12, 22).addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, def), PartPose.offsetAndRotation(-2.0F, 6.9F + Mth.cos(0.20420352F) * 10.0F, -0.5F + Mth.sin(0.20420352F) * 10.0F, 0.83252203F, 0.0F, 0.0F));
/*     */     }
/*  73 */     if (hasCenterHead)
/*  74 */       base.addOrReplaceChild("center_head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, def), PartPose.ZERO); 
/*  75 */     CubeListBuilder sideHeads = CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, def);
/*  76 */     base.addOrReplaceChild("right_head", sideHeads, PartPose.offset(-8.0F, 4.0F, 0.0F));
/*  77 */     base.addOrReplaceChild("left_head", sideHeads, PartPose.offset(10.0F, 4.0F, 0.0F));
/*  78 */     return mesh;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureHeads(ModelPart root, float scale) {}
/*     */ 
/*     */   
/*     */   protected void configureTentacles(ModelPart root) {}
/*     */ 
/*     */   
/*     */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
/*  90 */     super.setupAnimations((WitherStormEntity)entity, partialTicks, tickCount, yRot, xRot);
/*     */     
/*  92 */     float f = Mth.cos(tickCount * 0.1F);
/*  93 */     this.ribcage.xRot = (0.065F + 0.05F * f) * 3.1415927F;
/*  94 */     this.tail.setPos(-2.0F, 6.9F + Mth.cos(this.ribcage.xRot) * 10.0F, -0.5F + Mth.sin(this.ribcage.xRot) * 10.0F);
/*  95 */     this.tail.xRot = (0.265F + 0.1F * f) * 3.1415927F;
/*  96 */     this.centerHead.yRot = yRot * 0.017453292F;
/*  97 */     this.centerHead.xRot = xRot * 0.017453292F;
/*  98 */     setupHeadRotation((WitherStormEntity)entity, this.leftHead, 1, partialTicks);
/*  99 */     setupHeadRotation((WitherStormEntity)entity, this.rightHead, 2, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/* 105 */     this.base.render(stack, consumer, packedLight, overlayTexture, r, g, b, a);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setupHeadRotation(WitherStormEntity storm, ModelPart headModel, int head, float partialTick) {
/* 110 */     headModel.yRot = (Mth.lerp(partialTick, storm.getHeadYRotO(head), storm.getHeadYRot(head)) - Mth.lerp(partialTick, storm.yBodyRotO, storm.yBodyRot)) * 0.017453292F;
/* 111 */     headModel.xRot = Mth.lerp(partialTick, storm.getHeadXRotO(head), storm.getHeadXRot(head)) * 0.017453292F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\impl\WitherStormCommandBlockModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */