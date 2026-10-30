/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.EntityModel;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.LayerDefinition;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.StructureAnimationHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RibcageModel
/*     */   extends EntityModel<CommandBlockEntity>
/*     */ {
/*     */   private final Int2ObjectMap<RibModel> ribs;
/*     */   private final RibModel rib;
/*     */   private final RibModel rib2;
/*     */   private final RibModel rib3;
/*     */   private final RibModel rib4;
/*     */   private final RibModel rib5;
/*     */   private final RibModel rib6;
/*     */   
/*     */   public RibcageModel(ModelPart root) {
/*  72 */     Int2ObjectOpenHashMap int2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/*     */     
/*  74 */     this.rib = new RibModel(root.getChild("rib"));
/*  75 */     this.rib2 = new RibModel(root.getChild("rib2"));
/*  76 */     this.rib3 = new RibModel(root.getChild("rib3"));
/*  77 */     this.rib4 = new RibModel(root.getChild("rib4"));
/*  78 */     this.rib5 = new RibModel(root.getChild("rib5"));
/*  79 */     this.rib6 = new RibModel(root.getChild("rib6"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     int2ObjectOpenHashMap.put(0, this.rib);
/*  89 */     int2ObjectOpenHashMap.put(1, this.rib2);
/*  90 */     int2ObjectOpenHashMap.put(2, this.rib3);
/*  91 */     int2ObjectOpenHashMap.put(3, this.rib4);
/*  92 */     int2ObjectOpenHashMap.put(4, this.rib5);
/*  93 */     int2ObjectOpenHashMap.put(5, this.rib6);
/*     */     
/*  95 */     this.ribs = (Int2ObjectMap<RibModel>)int2ObjectOpenHashMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LayerDefinition createLayerDefinition() {
/* 100 */     MeshDefinition definition = new MeshDefinition();
/* 101 */     PartDefinition root = definition.getRoot();
/* 102 */     RibModel.populateDefinition(root.addOrReplaceChild("rib", CubeListBuilder.create(), PartPose.ZERO), PartPose.offset(0.0F, 24.0F, 68.0F), false);
/* 103 */     RibModel.populateDefinition(root.addOrReplaceChild("rib2", CubeListBuilder.create(), PartPose.ZERO), PartPose.offset(0.0F, 24.0F, -68.0F), false);
/* 104 */     RibModel.populateDefinition(root.addOrReplaceChild("rib3", CubeListBuilder.create(), PartPose.ZERO), PartPose.offset(42.0F, 24.0F, -57.0F), false);
/* 105 */     RibModel.populateDefinition(root.addOrReplaceChild("rib4", CubeListBuilder.create(), PartPose.ZERO), PartPose.offset(42.0F, 24.0F, 57.0F), false);
/* 106 */     RibModel.populateDefinition(root.addOrReplaceChild("rib5", CubeListBuilder.create(), PartPose.ZERO), PartPose.offset(-42.0F, 24.0F, 57.0F), true);
/* 107 */     RibModel.populateDefinition(root.addOrReplaceChild("rib6", CubeListBuilder.create(), PartPose.ZERO), PartPose.offset(-42.0F, 24.0F, -57.0F), true);
/* 108 */     return LayerDefinition.create(definition, 128, 128);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderToBuffer(PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 114 */     stack.pushPose();
/* 115 */     stack.translate(0.0D, -1.5D, 0.0D);
/* 116 */     for (ObjectIterator<Int2ObjectMap.Entry<RibModel>> objectIterator = this.ribs.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<RibModel> entry = objectIterator.next();
/* 117 */       ((RibModel)entry.getValue()).rib.render(stack, buffer, packedLight, packedOverlay); }
/* 118 */      stack.popPose();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(CommandBlockEntity entity, float animation, float partialTicks, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
/* 124 */     for (ObjectIterator<Int2ObjectMap.Entry<RibModel>> objectIterator = this.ribs.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<RibModel> entry = objectIterator.next();
/*     */       
/* 126 */       RibModel model = (RibModel)entry.getValue();
/* 127 */       int key = entry.getIntKey();
/* 128 */       List<StructureAnimationHelper> structure = entity.getRibStructure();
/* 129 */       if (key < structure.size()) {
/*     */         
/* 131 */         StructureAnimationHelper helper = structure.get(key);
/* 132 */         model.rib.xRot = (float)Math.toRadians(helper.getBaseXRot(partialTicks));
/* 133 */         model.rib.yRot = (float)Math.toRadians(helper.getBaseYRot(partialTicks));
/* 134 */         model.segmentOne.xRot = (float)Math.toRadians((helper.getXRot(partialTicks) * 0.4F));
/* 135 */         model.segmentOne.yRot = (float)Math.toRadians((helper.getYRot(partialTicks) * 0.4F));
/* 136 */         model.segmentTwo.xRot = (float)Math.toRadians((helper.getXRot(partialTicks) * 0.4F));
/* 137 */         model.segmentTwo.yRot = (float)Math.toRadians((helper.getYRot(partialTicks) * 0.4F));
/* 138 */         model.segmentThree.xRot = (float)Math.toRadians((helper.getXRot(partialTicks) * 0.8F));
/* 139 */         model.segmentThree.yRot = (float)Math.toRadians((helper.getYRot(partialTicks) * 0.8F));
/* 140 */         model.segmentFour.xRot = (float)Math.toRadians(helper.getXRot(partialTicks));
/* 141 */         model.segmentFour.yRot = (float)Math.toRadians(helper.getYRot(partialTicks));
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
/* 148 */     modelRenderer.xRot = x;
/* 149 */     modelRenderer.yRot = y;
/* 150 */     modelRenderer.zRot = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\commandblock\RibcageModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */