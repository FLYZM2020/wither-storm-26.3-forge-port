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
/*  74 */     this.rib = new RibModel(root.m_171324_("rib"));
/*  75 */     this.rib2 = new RibModel(root.m_171324_("rib2"));
/*  76 */     this.rib3 = new RibModel(root.m_171324_("rib3"));
/*  77 */     this.rib4 = new RibModel(root.m_171324_("rib4"));
/*  78 */     this.rib5 = new RibModel(root.m_171324_("rib5"));
/*  79 */     this.rib6 = new RibModel(root.m_171324_("rib6"));
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
/* 101 */     PartDefinition root = definition.m_171576_();
/* 102 */     RibModel.populateDefinition(root.m_171599_("rib", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_(0.0F, 24.0F, 68.0F), false);
/* 103 */     RibModel.populateDefinition(root.m_171599_("rib2", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_(0.0F, 24.0F, -68.0F), false);
/* 104 */     RibModel.populateDefinition(root.m_171599_("rib3", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_(42.0F, 24.0F, -57.0F), false);
/* 105 */     RibModel.populateDefinition(root.m_171599_("rib4", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_(42.0F, 24.0F, 57.0F), false);
/* 106 */     RibModel.populateDefinition(root.m_171599_("rib5", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_(-42.0F, 24.0F, 57.0F), true);
/* 107 */     RibModel.populateDefinition(root.m_171599_("rib6", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_(-42.0F, 24.0F, -57.0F), true);
/* 108 */     return LayerDefinition.m_171565_(definition, 128, 128);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7695_(PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 114 */     stack.m_85836_();
/* 115 */     stack.m_85837_(0.0D, -1.5D, 0.0D);
/* 116 */     for (ObjectIterator<Int2ObjectMap.Entry<RibModel>> objectIterator = this.ribs.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<RibModel> entry = objectIterator.next();
/* 117 */       ((RibModel)entry.getValue()).rib.m_104301_(stack, buffer, packedLight, packedOverlay); }
/* 118 */      stack.m_85849_();
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
/* 132 */         model.rib.f_104203_ = (float)Math.toRadians(helper.getBaseXRot(partialTicks));
/* 133 */         model.rib.f_104204_ = (float)Math.toRadians(helper.getBaseYRot(partialTicks));
/* 134 */         model.segmentOne.f_104203_ = (float)Math.toRadians((helper.getXRot(partialTicks) * 0.4F));
/* 135 */         model.segmentOne.f_104204_ = (float)Math.toRadians((helper.getYRot(partialTicks) * 0.4F));
/* 136 */         model.segmentTwo.f_104203_ = (float)Math.toRadians((helper.getXRot(partialTicks) * 0.4F));
/* 137 */         model.segmentTwo.f_104204_ = (float)Math.toRadians((helper.getYRot(partialTicks) * 0.4F));
/* 138 */         model.segmentThree.f_104203_ = (float)Math.toRadians((helper.getXRot(partialTicks) * 0.8F));
/* 139 */         model.segmentThree.f_104204_ = (float)Math.toRadians((helper.getYRot(partialTicks) * 0.8F));
/* 140 */         model.segmentFour.f_104203_ = (float)Math.toRadians(helper.getXRot(partialTicks));
/* 141 */         model.segmentFour.f_104204_ = (float)Math.toRadians(helper.getYRot(partialTicks));
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
/* 148 */     modelRenderer.f_104203_ = x;
/* 149 */     modelRenderer.f_104204_ = y;
/* 150 */     modelRenderer.f_104205_ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\commandblock\RibcageModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */