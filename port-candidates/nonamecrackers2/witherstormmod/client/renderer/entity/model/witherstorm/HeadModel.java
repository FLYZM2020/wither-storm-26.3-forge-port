/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.rendertype.RenderType;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*     */ import org.joml.Matrix3f;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeadModel<T extends LivingEntity & WitherStormBase>
/*     */ {
/*     */   protected final ModelPart head;
/*     */   protected final ModelPart upper;
/*     */   protected final ModelPart lower;
/*     */   private float jawHinge;
/*     */   public float tractorBeamXOffset;
/*     */   public float tractorBeamYOffset;
/*     */   public float tractorBeamZOffset;
/*     */   public float pivotOffsetX;
/*     */   public float pivotOffsetY;
/*     */   public float pivotOffsetZ;
/*     */   public float scale;
/*     */   public float tractorBeamStartSize;
/*     */   public float tractorBeamDistance;
/*     */   public float tractorBeamEndSize;
/*     */   public float animationOffset;
/*     */   
/*     */   public HeadModel(ModelPart root, float scale) {
/*  45 */     this.head = root;
/*  46 */     this.upper = root.getChild("upperJaw");
/*  47 */     this.lower = root.getChild("lowerJaw");
/*     */     
/*  49 */     this.scale = scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void populateDefinition(PartDefinition root) {
/*  54 */     PartDefinition upper = root.addOrReplaceChild("upperJaw", CubeListBuilder.create()
/*  55 */         .texOffs(0, 65).addBox(-4.0F, -6.5F, 12.0F, 8.0F, 6.0F, 2.0F, false)
/*  56 */         .texOffs(0, 47).addBox(-2.0F, -8.5F, 10.0F, 4.0F, 2.0F, 2.0F, false)
/*  57 */         .texOffs(0, 35).addBox(-4.0F, -8.5F, 0.0F, 8.0F, 2.0F, 10.0F, false)
/*  58 */         .texOffs(0, 47).addBox(-6.0F, -6.5F, 0.0F, 12.0F, 6.0F, 12.0F, false)
/*  59 */         .texOffs(4, 13).addBox(-1.0F, -4.5F, 13.1F, 2.0F, 2.0F, 1.0F, CubeDeformation.NONE, 0.2F, 0.2F), 
/*  60 */         PartPose.offset(0.0F, 2.5F, 0.0F));
/*  61 */     upper.addOrReplaceChild("upperTeeth", CubeListBuilder.create()
/*  62 */         .texOffs(0, 54).addBox(-1.0F, -1.0F, 13.0F, 1.0F, 1.0F, 1.0F, false)
/*  63 */         .texOffs(0, 54).addBox(-3.0F, -1.0F, 12.0F, 1.0F, 1.0F, 1.0F, false)
/*  64 */         .texOffs(0, 54).addBox(-5.0F, -1.0F, 11.0F, 1.0F, 1.0F, 1.0F, false)
/*  65 */         .texOffs(0, 54).addBox(-6.0F, -1.0F, 9.0F, 1.0F, 1.0F, 1.0F, false)
/*  66 */         .texOffs(0, 54).addBox(-6.0F, -1.0F, 7.0F, 1.0F, 1.0F, 1.0F, false)
/*  67 */         .texOffs(0, 54).addBox(-6.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
/*  68 */         .texOffs(0, 54).addBox(-6.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, false)
/*  69 */         .texOffs(0, 54).addBox(-6.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, false)
/*  70 */         .texOffs(0, 54).addBox(1.0F, -1.0F, 13.0F, 1.0F, 1.0F, 1.0F, false)
/*  71 */         .texOffs(0, 54).addBox(3.0F, -1.0F, 12.0F, 1.0F, 1.0F, 1.0F, false)
/*  72 */         .texOffs(0, 54).addBox(4.0F, -1.0F, 10.0F, 1.0F, 1.0F, 1.0F, false)
/*  73 */         .texOffs(0, 54).addBox(5.0F, -1.0F, 8.0F, 1.0F, 1.0F, 1.0F, false)
/*  74 */         .texOffs(0, 54).addBox(5.0F, -1.0F, 6.0F, 1.0F, 1.0F, 1.0F, false)
/*  75 */         .texOffs(0, 54).addBox(5.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
/*  76 */         .texOffs(0, 54).addBox(5.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, false)
/*  77 */         .texOffs(0, 54).addBox(5.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, false), 
/*  78 */         PartPose.offset(0.0F, 0.5F, 0.0F));
/*  79 */     PartDefinition lower = root.addOrReplaceChild("lowerJaw", CubeListBuilder.create()
/*  80 */         .texOffs(0, 73).addBox(-4.0F, 0.5F, 12.0F, 8.0F, 2.0F, 2.0F, false)
/*  81 */         .texOffs(48, 0).addBox(-6.0F, 0.5F, 0.0F, 12.0F, 2.0F, 12.0F, false), 
/*  82 */         PartPose.offset(0.0F, 2.5F, 0.0F));
/*  83 */     lower.addOrReplaceChild("lowerTeeth", CubeListBuilder.create()
/*  84 */         .texOffs(0, 54).addBox(0.0F, -3.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
/*  85 */         .texOffs(0, 54).addBox(2.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
/*  86 */         .texOffs(0, 54).addBox(4.0F, -3.0F, 3.0F, 1.0F, 1.0F, 1.0F, false)
/*  87 */         .texOffs(0, 54).addBox(5.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, false)
/*  88 */         .texOffs(0, 54).addBox(5.0F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, false)
/*  89 */         .texOffs(0, 54).addBox(5.0F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F, false)
/*  90 */         .texOffs(0, 54).addBox(5.0F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, false)
/*  91 */         .texOffs(0, 54).addBox(5.0F, -3.0F, -7.0F, 1.0F, 1.0F, 1.0F, false)
/*  92 */         .texOffs(0, 54).addBox(-2.0F, -3.0F, 5.0F, 1.0F, 1.0F, 1.0F, false)
/*  93 */         .texOffs(0, 54).addBox(-4.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, false)
/*  94 */         .texOffs(0, 54).addBox(-5.0F, -3.0F, 2.0F, 1.0F, 1.0F, 1.0F, false)
/*  95 */         .texOffs(0, 54).addBox(-6.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, false)
/*  96 */         .texOffs(0, 54).addBox(-6.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, false)
/*  97 */         .texOffs(0, 54).addBox(-6.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, false)
/*  98 */         .texOffs(0, 54).addBox(-6.0F, -3.0F, -6.0F, 1.0F, 1.0F, 1.0F, false)
/*  99 */         .texOffs(0, 54).addBox(-6.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, false), 
/* 100 */         PartPose.offset(0.0F, 2.5F, 8.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelPart root() {
/* 105 */     return this.head;
/*     */   }
/*     */ 
/*     */   
/*     */   public void scale(PoseStack stack) {
/* 110 */     stack.scale(this.scale, this.scale, this.scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot, int head) {
/* 115 */     if (head > 0) {
/*     */       
/* 117 */       this.head.yRot = (Mth.lerp(partialTicks, ((WitherStormBase)entity).getHeadYRotO(head), ((WitherStormBase)entity).getHeadYRot(head)) - Mth.lerp(partialTicks, ((LivingEntity)entity).yBodyRotO, ((LivingEntity)entity).yBodyRot)) * 0.017453292F + 3.1416F;
/* 118 */       this.head.xRot = -(Mth.lerp(partialTicks, ((WitherStormBase)entity).getHeadXRotO(head), ((WitherStormBase)entity).getHeadXRot(head)) * 0.017453292F);
/*     */     }
/*     */     else {
/*     */       
/* 122 */       this.head.yRot = 3.1416F + yRot * 0.017453292F;
/* 123 */       this.head.xRot = -xRot * 0.017453292F;
/*     */     } 
/* 125 */     float hinge = ((WitherStormBase)entity).getMouthAnimation(head, partialTicks);
/* 126 */     this.jawHinge = hinge * 0.3F;
/*     */     
/* 128 */     float ticks = ((LivingEntity)entity).tickCount + partialTicks;
/* 129 */     if (((WitherStormBase)entity).isDeadOrPlayingDead())
/* 130 */       ticks = 0.0F; 
/* 131 */     float f = Mth.cos((ticks + this.animationOffset) * 0.1F);
/* 132 */     float d = Mth.cos(this.jawHinge);
/* 133 */     this.lower.xRot = d * 10.0F - 10.0F + (0.065F + 0.02F * f) * 3.1415927F - 0.5F;
/* 134 */     Random random = new Random(entity.getId());
/* 135 */     boolean mirror = false;
/* 136 */     for (int i = 0; i < head; i++) {
/*     */       
/* 138 */       mirror = random.nextBoolean();
/* 139 */       if (i == head)
/*     */         break; 
/*     */     } 
/* 142 */     float s = Mth.cos(((WitherStormBase)entity).getBrokenJawAnimation(head, partialTicks) * 0.3F);
/* 143 */     this.lower.zRot = (s * 10.0F - 10.0F) * (mirror ? -1.0F : 1.0F);
/* 144 */     this.head.zRot = ((WitherStormBase)entity).getHeadShakeAnim(head, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRenderTractorBeam(T entity, int head) {
/* 149 */     boolean flag = false;
/* 150 */     if (((WitherStormBase)entity).areOtherHeadsDisabled()) {
/* 151 */       flag = (head == 0);
/*     */     } else {
/* 153 */       flag = true;
/* 154 */     }  return (flag && !((WitherStormBase)entity).isHeadInjured(head));
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTractorBeam(T entity, PoseStack stack, MultiBufferSource buffer, int packedLightIn, float r, float g, float b, float a, float partialTicks, double tractorBeamCutoff, float endFadeAlpha) {
/* 159 */     stack.pushPose();
/* 160 */     stack.mulPose(Axis.YP.rotationDegrees(-Mth.rotLerp(partialTicks, ((LivingEntity)entity).yBodyRotO, ((LivingEntity)entity).yBodyRot)));
/* 161 */     Pose pose = entity.getPose();
/* 162 */     if (pose != Pose.SLEEPING) {
/*     */       
/* 164 */       float xBodyRot = Mth.rotLerp(partialTicks, ((WitherStormBase)entity).getXBodyRotO(), ((WitherStormBase)entity).getXBodyRot());
/* 165 */       stack.mulPose(Axis.XP.rotationDegrees(-xBodyRot));
/*     */     } 
/* 167 */     stack.translate((this.head.x + this.tractorBeamXOffset) / 8.0F * this.scale, -((this.head.y - this.tractorBeamYOffset) / 8.0F) * this.scale, -((this.head.z - this.tractorBeamZOffset) / 8.0F) * this.scale);
/* 168 */     stack.scale(this.scale, this.scale, this.scale);
/* 169 */     stack.mulPose(Axis.ZP.rotation(-this.head.zRot));
/* 170 */     stack.mulPose(Axis.YP.rotation(-this.head.yRot - (float)Math.toRadians(90.0D)));
/* 171 */     stack.mulPose(Axis.ZP.rotation(-this.head.xRot));
/* 172 */     stack.translate(this.pivotOffsetX / 8.0F * this.scale, this.pivotOffsetY / 8.0F * this.scale, this.pivotOffsetZ / 8.0F * this.scale);
/* 173 */     PoseStack.Pose entry = stack.last();
/* 174 */     Matrix4f matrix4f = entry.pose();
/* 175 */     Matrix3f matrix3f = entry.normal();
/* 176 */     VertexConsumer builder = buffer.getBuffer(RenderType.lightning());
/* 177 */     float distance = this.tractorBeamDistance;
/* 178 */     if (tractorBeamCutoff != -1.0D)
/* 179 */       distance = (float)tractorBeamCutoff; 
/* 180 */     float distanceScale = this.tractorBeamEndSize / this.tractorBeamDistance;
/* 181 */     float endSize = distanceScale * distance;
/*     */ 
/*     */     
/* 184 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 185 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 186 */     builder.vertex(matrix4f, -distance, -endSize, endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 187 */     builder.vertex(matrix4f, -distance, endSize, endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */     
/* 189 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 190 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 191 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 192 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */     
/* 194 */     builder.vertex(matrix4f, -distance, endSize, -endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 195 */     builder.vertex(matrix4f, -distance, -endSize, -endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 196 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 197 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     builder.vertex(matrix4f, -distance, endSize, -endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 205 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 206 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 207 */     builder.vertex(matrix4f, -distance, endSize, endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */     
/* 209 */     builder.vertex(matrix4f, -distance, -endSize, endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 210 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 211 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 212 */     builder.vertex(matrix4f, -distance, -endSize, -endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */ 
/*     */     
/* 215 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 216 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 217 */     builder.vertex(matrix4f, -distance, -endSize, -endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 218 */     builder.vertex(matrix4f, -distance, endSize, -endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */     
/* 220 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 221 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 222 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 223 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */     
/* 225 */     builder.vertex(matrix4f, -distance, endSize, endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 226 */     builder.vertex(matrix4f, -distance, -endSize, endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 227 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 228 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     builder.vertex(matrix4f, -distance, endSize, endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 236 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 237 */     builder.vertex(matrix4f, 0.0F, 0.0F + this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 238 */     builder.vertex(matrix4f, -distance, endSize, -endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */     
/* 240 */     builder.vertex(matrix4f, -distance, -endSize, -endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 241 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F - this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 242 */     builder.vertex(matrix4f, 0.0F, 0.0F - this.tractorBeamStartSize, 0.0F + this.tractorBeamStartSize).color(r, g, b, a).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/* 243 */     builder.vertex(matrix4f, -distance, -endSize, endSize).color(r, g, b, endFadeAlpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
/*     */     
/* 245 */     stack.popPose();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\HeadModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */