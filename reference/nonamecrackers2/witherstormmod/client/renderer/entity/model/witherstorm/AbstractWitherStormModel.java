/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.MeshDefinition;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*     */ import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinModelPart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractWitherStormModel<T extends WitherStormEntity>
/*     */ {
/*     */   public static final String MASS = "mass";
/*     */   public static final String LOWRESMASS = "lowResMass";
/*     */   public static final String TENTACLES = "tentacles";
/*     */   public static final String HEADSROOT = "heads";
/*  36 */   public static final String[] HEADS = new String[] { "head0", "head1", "head2" };
/*     */   protected ModelPart body;
/*     */   protected ModelPart lowResBody;
/*  39 */   protected TentacleModel[] tentacles = new TentacleModel[0];
/*  40 */   public final Int2ObjectMap<HeadModel<T>> heads = (Int2ObjectMap<HeadModel<T>>)new Int2ObjectOpenHashMap();
/*     */   
/*     */   public final float headScale;
/*     */   
/*     */   protected AbstractWitherStormModel(ModelPart root, float headScale) {
/*  45 */     this.body = root.m_171324_("mass");
/*  46 */     this.lowResBody = root.m_171324_("lowResMass");
/*  47 */     configureTentacles(root.m_171324_("tentacles"));
/*  48 */     configureHeads(root.m_171324_("heads"), headScale);
/*  49 */     this.headScale = headScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static MeshDefinition createMesh() {
/*  58 */     MeshDefinition mesh = new MeshDefinition();
/*  59 */     PartDefinition root = mesh.m_171576_();
/*  60 */     root.m_171599_("mass", CubeListBuilder.m_171558_(), PartPose.f_171404_);
/*  61 */     root.m_171599_("lowResMass", CubeListBuilder.m_171558_(), PartPose.f_171404_);
/*  62 */     root.m_171599_("tentacles", CubeListBuilder.m_171558_(), PartPose.f_171404_);
/*  63 */     root.m_171599_("heads", CubeListBuilder.m_171558_(), PartPose.f_171404_);
/*  64 */     return mesh;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
/*  69 */     for (ObjectIterator<Int2ObjectMap.Entry<HeadModel<T>>> objectIterator = this.heads.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<HeadModel<T>> entry = objectIterator.next();
/*  70 */       ((HeadModel)entry.getValue()).setupAnimations(entity, partialTicks, tickCount, yRot, xRot, entry.getIntKey()); }
/*     */     
/*  72 */     for (int i = 0; i < this.tentacles.length; i++) {
/*  73 */       this.tentacles[i].setupAnimations(entity.getTentacleAnimation(partialTicks), partialTicks);
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderHeads(T entity, PoseStack stack, VertexConsumer consumer, int overlayTexture, int packedLight, float r, float g, float b, float a) {
/*  78 */     renderHeads(i -> true, entity, stack, consumer, overlayTexture, packedLight, r, g, b, a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderHeads(Predicate<Integer> canRender, T entity, PoseStack stack, VertexConsumer consumer, int overlayTexture, int packedLight, float r, float g, float b, float a) {
/*  83 */     for (ObjectIterator<Int2ObjectMap.Entry<HeadModel<T>>> objectIterator = this.heads.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<HeadModel<T>> entry = objectIterator.next();
/*     */       
/*  85 */       if (canRender.test(Integer.valueOf(entry.getIntKey())) && (!entity.areOtherHeadsDisabled() || (entity.areOtherHeadsDisabled() && entry.getIntKey() == 0))) {
/*  86 */         renderHead(entry.getIntKey(), entity, stack, consumer, overlayTexture, packedLight, r, g, b, a);
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public void renderHead(int head, T entity, PoseStack stack, VertexConsumer consumer, int overlayTexture, int packedLight, float r, float g, float b, float a) {
/*  92 */     HeadModel<T> headModel = (HeadModel<T>)this.heads.get(head);
/*  93 */     stack.m_85836_();
/*  94 */     headModel.scale(stack);
/*  95 */     int hurtDir = entity.getHeadManager().getHead(head).getHeadHurtDuration();
/*  96 */     int overlay = (hurtDir > 0) ? 3 : overlayTexture;
/*  97 */     if (!entity.areOtherHeadsDisabled() || head == 0)
/*  98 */       headModel.root().m_104306_(stack, consumer, packedLight, overlay, r, g, b, a); 
/*  99 */     stack.m_85849_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(T entity, PoseStack stack, MultiBufferSource source, RenderType type, @Nullable RenderType emissiveType, @Nullable RenderType massEmissiveType, @Nullable RenderType hurtOverlayType, int packedLight, int overlayTexture, float r, float g, float b, float alpha) {
/* 104 */     stack.m_85836_();
/*     */     
/* 106 */     VertexConsumer consumer = source.m_6299_(type);
/* 107 */     renderHeads(entity, stack, consumer, overlayTexture, packedLight, r, g, b, alpha);
/* 108 */     stack.m_85836_();
/* 109 */     transformForMirrored(stack, entity.isMirrored());
/* 110 */     for (int i = 0; i < this.tentacles.length; i++) {
/*     */       
/* 112 */       stack.m_85836_();
/* 113 */       scaleTentacles(stack, this.tentacles[i]);
/* 114 */       (this.tentacles[i]).tentacle.m_104306_(stack, consumer, packedLight, overlayTexture, 1.0F, 1.0F, 1.0F, alpha);
/* 115 */       stack.m_85849_();
/*     */     } 
/* 117 */     renderExtra(stack, consumer, packedLight, overlayTexture, r, g, b, alpha);
/* 118 */     stack.m_85849_();
/*     */     
/* 120 */     if (hurtOverlayType != null) {
/* 121 */       renderHeads(i -> entity.isHeadInjured(i.intValue()), entity, stack, source.m_6299_(hurtOverlayType), overlayTexture, packedLight, r, g, b, alpha);
/*     */     }
/* 123 */     if ((!entity.m_20096_() || !entity.isDeadOrPlayingDead()) && !entity.shouldFlicker())
/*     */     {
/* 125 */       if (emissiveType != null) {
/*     */         
/* 127 */         VertexConsumer emissive = source.m_6299_(emissiveType);
/* 128 */         renderHeads(i -> !entity.isHeadInjured(i.intValue()), entity, stack, emissive, overlayTexture, packedLight, r, g, b, alpha);
/*     */       } 
/*     */     }
/*     */     
/* 132 */     stack.m_85836_();
/*     */     
/* 134 */     transformForMirrored(stack, entity.isMirrored());
/* 135 */     scaleMass(stack);
/*     */     
/* 137 */     RenderBufferer.pushCullFaces();
/* 138 */     if (entity.isMirrored())
/* 139 */       RenderBufferer.pushFlipFaces(); 
/* 140 */     boolean flag = RenderBufferer.shouldUse();
/* 141 */     if (flag && entity.m_21224_() && CompatHelper.areShadersRunning())
/* 142 */       flag = false; 
/* 143 */     Objects.requireNonNull(getMassModel(entity)); RenderBufferer.buildAndOrRender("" + this + ", " + this + ", " + type, type, () -> Boolean.valueOf(false), getMassModel(entity)::m_104306_, stack, packedLight, overlayTexture, 1.0F, 1.0F, 1.0F, alpha, flag);
/* 144 */     if (flag && emissiveType != null) {
/*     */       
/* 146 */       RenderBufferer.pushNoFog();
/* 147 */       Objects.requireNonNull(getMassModel(entity)); RenderBufferer.buildAndOrRender("" + this + ", " + this + ", " + massEmissiveType + ", emissive", massEmissiveType, () -> Boolean.valueOf(false), getMassModel(entity)::m_104306_, stack, packedLight, overlayTexture, 1.0F, 1.0F, 1.0F, alpha, true);
/*     */     } 
/* 149 */     RenderBufferer.popFlipFaces();
/* 150 */     RenderBufferer.popCullFaces();
/*     */     
/* 152 */     if (!entity.m_21224_()) {
/* 153 */       renderMassDecal(entity, stack, source, packedLight, overlayTexture, r, g, b, alpha);
/*     */     }
/* 155 */     stack.m_85849_();
/*     */     
/* 157 */     stack.m_85849_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void transformForMirrored(PoseStack stack, boolean mirrored) {
/* 162 */     stack.m_85841_(mirrored ? -1.0F : 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {}
/*     */   
/*     */   public ModelPart getMassModel(T entity) {
/* 169 */     if (lowResMassPresent()) {
/* 170 */       return lowResModelsEnabled(entity) ? this.lowResBody : this.body;
/*     */     }
/* 172 */     return this.body;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean lowResModelsEnabled(T entity) {
/* 177 */     return (lowResMassPresent() && (((Boolean)WitherStormModConfig.CLIENT.lowResModels.get()).booleanValue() || (((Boolean)WitherStormModConfig.CLIENT.witherStormLOD.get()).booleanValue() && entity.isOnDistantRenderer())));
/*     */   }
/*     */ 
/*     */   
/*     */   public void scaleMass(PoseStack stack) {
/* 182 */     stack.m_85841_(10.0F, 10.0F, 10.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void scaleTentacles(PoseStack stack, TentacleModel model) {
/* 187 */     stack.m_85841_(model.scale, model.scale, model.scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean massPresent() {
/* 192 */     return (this.body != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean lowResMassPresent() {
/* 197 */     return !this.lowResBody.m_171326_();
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelPart getRandomPart(T entity, RandomSource random) {
/* 202 */     ModelPart model = getMassModel(entity);
/* 203 */     List<ModelPart> children = new ArrayList<>(((IMixinModelPart)model).getChildren().values());
/* 204 */     if (!children.isEmpty()) {
/*     */       
/* 206 */       ModelPart potential = children.get(random.m_188503_(children.size()));
/* 207 */       if (!potential.m_171326_())
/* 208 */         model = potential; 
/*     */     } 
/* 210 */     return model;
/*     */   }
/*     */   
/*     */   public void renderMassDecal(T entity, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayCoords, float r, float g, float b, float a) {}
/*     */   
/*     */   protected abstract void configureTentacles(ModelPart paramModelPart);
/*     */   
/*     */   protected abstract void configureHeads(ModelPart paramModelPart, float paramFloat);
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\AbstractWitherStormModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */