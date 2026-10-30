/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.EntityModel;
/*     */ import net.minecraft.client.renderer.ItemBlockRenderTypes;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.rendertype.RenderType;
/*     */ import net.minecraft.client.renderer.block.BlockRenderDispatcher;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.resources.model.ModelBakery;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.inventory.InventoryMenu;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.client.model.data.ModelData;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock.RibcageModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandBlockRenderer
/*     */   extends EntityRenderer<CommandBlockEntity>
/*     */ {
/*  37 */   private static final Identifier RIBCAGE_LOCATION = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/command_block/ribcage.png");
/*     */   private final RibcageModel ribcage;
/*     */   @Nullable
/*     */   private EntityModel<CommandBlockEntity> model;
/*     */   
/*     */   public CommandBlockRenderer(EntityRendererProvider.Context context) {
/*  43 */     super(context);
/*  44 */     this.ribcage = new RibcageModel(context.bakeLayer(WitherStormModRenderers.RIBCAGE));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getBlockLightLevel(CommandBlockEntity entity, BlockPos pos) {
/*  50 */     return (entity.getMode() == CommandBlockEntity.Mode.TENTACLES) ? 15 : super.getBlockLightLevel((Entity)entity, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(CommandBlockEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
/*  57 */     this.model = getModel(entity.getMode());
/*  58 */     if (this.model != null) {
/*     */       
/*  60 */       stack.pushPose();
/*  61 */       stack.scale(-1.0F, -1.0F, 1.0F);
/*  62 */       stack.mulPose(Axis.YN.rotationDegrees(-Mth.lerp(partialTicks, entity.yBodyRotO, entity.yBodyRot) + 90.0F));
/*  63 */       VertexConsumer builder = buffer.getBuffer(this.model.renderType(getTextureLocation(entity)));
/*  64 */       this.model.setupAnim((Entity)entity, entity.getModeAnim(partialTicks), partialTicks, 0.0F, entity.getYRot(), entity.getXRot());
/*  65 */       this.model.renderToBuffer(stack, builder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*  66 */       stack.popPose();
/*     */     } 
/*     */     
/*  69 */     stack.pushPose();
/*  70 */     stack.translate(-0.5D, entity.lerpProtectionYOffset(partialTicks), -0.5D);
/*  71 */     Minecraft mc = Minecraft.getInstance();
/*  72 */     BlockRenderDispatcher blockRenderer = mc.getBlockRenderer();
/*  73 */     BlockState state = entity.getBlockState();
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
/*     */ 
/*     */ 
/*     */     
/* 118 */     blockRenderer.renderSingleBlock(state, stack, buffer, packedLight, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, ItemBlockRenderTypes.getRenderType(entity.getBlockState(), false));
/*     */     
/* 120 */     int health = Mth.floor((entity.getMaxHealth() - entity.getHealth()) / entity.getMaxHealth() / 4.0F);
/* 121 */     PoseStack.Pose entry = stack.last();
/* 122 */     if (health < 5 && health > 0) {
/*     */       
/* 124 */       RenderType type = ModelBakery.DESTROY_TYPES.get(health * 2);
/* 125 */       SheetedDecalTextureGenerator sheetedDecalTextureGenerator = new SheetedDecalTextureGenerator(buffer.getBuffer(type), entry.pose(), entry.normal(), 1.0F);
/* 126 */       blockRenderer.getModelRenderer().renderModel(entry, (VertexConsumer)sheetedDecalTextureGenerator, state, blockRenderer.getBlockModel(state), 1.0F, 1.0F, 1.0F, packedLight, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, type);
/*     */     } 
/*     */     
/* 129 */     stack.popPose();
/*     */     
/* 131 */     if (entity.getSpecialDeathTime() > 0 || entity.getHitGlareTime() > 0) {
/*     */       
/* 133 */       float f1 = 0.0F;
/* 134 */       float k = 1.0F;
/* 135 */       if (entity.getSpecialDeathTime() > 0) {
/*     */         
/* 137 */         f1 = (entity.getSpecialDeathTime() + partialTicks) / 20.0F;
/* 138 */         k = 1.0F;
/*     */       }
/* 140 */       else if (entity.getHitGlareTime() > 0) {
/*     */         
/* 142 */         f1 = 2.0F + (entity.getHitGlareTime() - partialTicks) / 30.0F;
/* 143 */         k = (entity.getHitGlareTime() - partialTicks) / 60.0F;
/*     */       } 
/* 145 */       Random random = new Random(122L);
/* 146 */       VertexConsumer builder = buffer.getBuffer(RenderType.lightning());
/* 147 */       stack.pushPose();
/* 148 */       stack.translate(0.0D, entity.getBoundingBox().getYsize() / 2.0D, 0.0D);
/*     */ 
/*     */       
/* 151 */       for (int i = 0; i < (f1 + f1 * f1) / 2.0F; i++) {
/*     */         
/* 153 */         stack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
/* 154 */         stack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
/* 155 */         stack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F));
/* 156 */         stack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
/* 157 */         stack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
/* 158 */         stack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F + f1 * 90.0F));
/* 159 */         float f3 = random.nextFloat() * 0.2F + f1;
/* 160 */         float f4 = (random.nextFloat() + 0.2F) * 0.05F * f1;
/* 161 */         Matrix4f matrix4f = stack.last().pose();
/* 162 */         float sqrt = (float)(Math.sqrt(3.0D) / 2.0D);
/* 163 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(1.0F, 1.0F, 1.0F, k).endVertex();
/* 164 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(1.0F, 1.0F, 1.0F, k).endVertex();
/* 165 */         builder.vertex(matrix4f, -sqrt * f4, f3, -0.5F * f4).color(255, 123, 0, 0).endVertex();
/* 166 */         builder.vertex(matrix4f, sqrt * f4, f3, -0.5F * f4).color(255, 123, 0, 0).endVertex();
/* 167 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(1.0F, 1.0F, 1.0F, k).endVertex();
/* 168 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(1.0F, 1.0F, 1.0F, k).endVertex();
/* 169 */         builder.vertex(matrix4f, sqrt * f4, f3, -0.5F * f4).color(255, 123, 0, 0).endVertex();
/* 170 */         builder.vertex(matrix4f, 0.0F, f3, 1.0F * f4).color(255, 123, 0, 0).endVertex();
/* 171 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(1.0F, 1.0F, 1.0F, k).endVertex();
/* 172 */         builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(1.0F, 1.0F, 1.0F, k).endVertex();
/* 173 */         builder.vertex(matrix4f, 0.0F, f3, 1.0F * f4).color(255, 123, 0, 0).endVertex();
/* 174 */         builder.vertex(matrix4f, -sqrt * f4, f3, -0.5F * f4).color(255, 123, 0, 0).endVertex();
/*     */       } 
/*     */       
/* 177 */       stack.popPose();
/*     */     } 
/*     */     
/* 180 */     super.render((Entity)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
/*     */   }
/*     */ 
/*     */   
/*     */   private EntityModel<CommandBlockEntity> getModel(CommandBlockEntity.Mode mode) {
/* 185 */     return (mode == CommandBlockEntity.Mode.RIBS) ? (EntityModel<CommandBlockEntity>)this.ribcage : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getTextureLocation(CommandBlockEntity entity) {
/* 191 */     return (entity.getMode() == CommandBlockEntity.Mode.RIBS) ? RIBCAGE_LOCATION : InventoryMenu.BLOCK_ATLAS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\CommandBlockRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */