/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ItemBlockRenderTypes;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.rendertype.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.inventory.InventoryMenu;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.client.model.data.ModelData;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FormidibombRenderer
/*     */   extends EntityRenderer<FormidibombEntity>
/*     */ {
/*     */   public FormidibombRenderer(EntityRendererProvider.Context context) {
/*  29 */     super(context);
/*  30 */     this.shadowRadius = 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(FormidibombEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
/*  36 */     stack.pushPose();
/*  37 */     stack.translate(0.0D, 0.5D, 0.0D);
/*  38 */     if (entity.getFuseLife() - partialTicks + 1.0F < 20.0F) {
/*     */       
/*  40 */       float f = 1.0F - (entity.getFuseLife() - partialTicks + 1.0F) / 20.0F;
/*  41 */       f = Mth.clamp(f, 0.0F, 1.0F);
/*  42 */       f *= f;
/*  43 */       f *= f;
/*  44 */       float f1 = 1.0F + f * 20.0F;
/*  45 */       stack.scale(f1, f1, f1);
/*     */     } 
/*     */     
/*  48 */     if (entity.getFuseLife() - partialTicks < 240.0F) {
/*     */       
/*  50 */       float f = (240.0F - entity.getFuseLife() + partialTicks) / 150.0F;
/*  51 */       Random random = new Random(289L);
/*  52 */       VertexConsumer builder = buffer.getBuffer(RenderType.lightning());
/*  53 */       stack.pushPose();
/*     */       
/*  55 */       for (int i = 0; i < (f + f * f) / 2.0F * 10.0F; i++) {
/*     */         
/*  57 */         stack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
/*  58 */         stack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
/*  59 */         stack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F));
/*  60 */         stack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
/*  61 */         stack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
/*  62 */         stack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F + f * 90.0F));
/*  63 */         float f3 = random.nextFloat() * (float)entity.getBoundingBox().getSize() * 0.2F + f * (float)Math.max(1.0D, ((40.0F - entity.getFuseLife() + partialTicks) / 6.0F));
/*  64 */         float f4 = random.nextFloat() * 0.025F + f;
/*  65 */         Matrix4f matrix4f = stack.last().pose();
/*  66 */         int k = 255;
/*  67 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, k).endVertex();
/*  68 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, k).endVertex();
/*  69 */         builder.vertex(matrix4f, -((float)(Math.sqrt(3.0D) / 2.0D)) * f4, f3, -0.5F * f4).color(255, 0, 255, 0).endVertex();
/*  70 */         builder.vertex(matrix4f, (float)(Math.sqrt(3.0D) / 2.0D) * f4, f3, -0.5F * f4).color(255, 0, 255, 0).endVertex();
/*  71 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, k).endVertex();
/*  72 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, k).endVertex();
/*  73 */         builder.vertex(matrix4f, (float)(Math.sqrt(3.0D) / 2.0D) * f4, f3, -0.5F * f4).color(255, 0, 255, 0).endVertex();
/*  74 */         builder.vertex(matrix4f, 0.0F, f3, 1.0F * f4).color(255, 0, 255, 0).endVertex();
/*  75 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, k).endVertex();
/*  76 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, k).endVertex();
/*  77 */         builder.vertex(matrix4f, 0.0F, f3, 1.0F * f4).color(255, 0, 255, 0).endVertex();
/*  78 */         builder.vertex(matrix4f, -((float)(Math.sqrt(3.0D) / 2.0D)) * f4, f3, -0.5F * f4).color(255, 0, 255, 0).endVertex();
/*     */       } 
/*     */       
/*  81 */       stack.popPose();
/*     */     } 
/*     */     
/*  84 */     stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
/*  85 */     stack.translate(-0.5D, -0.5D, 0.5D);
/*  86 */     stack.mulPose(Axis.YP.rotationDegrees(90.0F));
/*  87 */     float ticks = entity.getAirTime() + partialTicks;
/*  88 */     if (!entity.onGround()) {
/*     */       
/*  90 */       stack.translate(0.5D, 0.5D, 0.5D);
/*  91 */       stack.mulPose(Axis.YP.rotationDegrees(ticks));
/*  92 */       stack.mulPose(Axis.XP.rotationDegrees(ticks * 0.5F));
/*  93 */       stack.translate(-0.5D, -0.5D, -0.5D);
/*     */     } 
/*  95 */     BlockState state = entity.getBlockState();
/*  96 */     int fuse = (entity.getStartFuse() > 0 && entity.getFuseLife() > 0) ? (entity.getStartFuse() / entity.getFuseLife()) : 0;
/*  97 */     renderWhiteSolidBlock(state, stack, buffer, p_225623_6_, (fuse % 2 == 0));
/*  98 */     stack.popPose();
/*  99 */     super.render((Entity)entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void renderWhiteSolidBlock(BlockState state, PoseStack stack, MultiBufferSource buffer, int packedLight, boolean flash) {
/*     */     int i;
/* 106 */     if (flash) {
/* 107 */       i = OverlayTexture.pack(OverlayTexture.u(1.0F), 10);
/*     */     } else {
/* 109 */       i = OverlayTexture.NO_OVERLAY;
/*     */     } 
/* 111 */     Minecraft mc = Minecraft.getInstance();
/* 112 */     mc.getBlockRenderer().renderSingleBlock(state, stack, buffer, packedLight, i, ModelData.EMPTY, ItemBlockRenderTypes.getRenderType(state, false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getTextureLocation(FormidibombEntity entity) {
/* 118 */     return InventoryMenu.BLOCK_ATLAS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\FormidibombRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */