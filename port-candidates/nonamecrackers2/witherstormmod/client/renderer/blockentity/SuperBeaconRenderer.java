/*     */ package nonamecrackers2.witherstormmod.client.renderer.blockentity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.rendertype.RenderType;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*     */ import net.minecraft.client.renderer.entity.ItemRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.inventory.InventoryMenu;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.client.model.data.ModelData;
/*     */ import nonamecrackers2.witherstormmod.client.util.TextureAtlasAccessor;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import org.joml.Matrix3f;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ public class SuperBeaconRenderer
/*     */   extends AbstractSuperBeaconRenderer<SuperBeaconBlockEntity>
/*     */ {
/*  37 */   private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "block/tainted_dust_block");
/*     */   
/*     */   private final TextureAtlas atlas;
/*     */   private final TextureAtlasSprite texture;
/*     */   private final ItemRenderer itemRenderer;
/*     */   
/*     */   public SuperBeaconRenderer(BlockEntityRendererProvider.Context context) {
/*  44 */     super(context);
/*  45 */     Minecraft mc = Minecraft.getInstance();
/*  46 */     this.atlas = mc.getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);
/*  47 */     this.texture = this.atlas.getSprite(TEXTURE);
/*  48 */     this.itemRenderer = mc.getItemRenderer();
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SuperBeaconBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
/*     */     float baseRotation;
/*  54 */     super.render(entity, partialTicks, stack, buffer, packedLight, overlayTexture);
/*     */     
/*  56 */     Minecraft mc = Minecraft.getInstance();
/*     */     
/*  58 */     float tickCount = entity.getTicks() + partialTicks;
/*     */     
/*  60 */     float resummonTicks = entity.getResummonTicks() + partialTicks;
/*  61 */     if (entity.getResummonEntity() == WitherStormModEntityTypes.WITHER_STORM.get() && entity.getResummonTicks() > 60) {
/*     */       
/*  63 */       float speed = 200.0F / (resummonTicks - 400.0F);
/*  64 */       stack.pushPose();
/*  65 */       Vec2 shake = entity.getShake(partialTicks);
/*  66 */       stack.translate((shake.x * speed), 3.0D, (shake.y * speed));
/*  67 */       stack.translate(0.5D, 0.5D, 0.5D);
/*  68 */       stack.mulPose(Axis.ZP.rotationDegrees(tickCount * speed));
/*  69 */       stack.mulPose(Axis.XN.rotationDegrees(tickCount * speed));
/*  70 */       stack.translate(-0.5D, -0.5D, -0.5D);
/*  71 */       mc.getBlockRenderer().renderSingleBlock(Blocks.COMMAND_BLOCK.defaultBlockState(), stack, buffer, 15728880, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
/*  72 */       stack.popPose();
/*     */     } 
/*     */     
/*  75 */     stack.translate(0.5D, 0.5D, 0.5D);
/*     */     
/*  77 */     if (entity.getResummonEntity() == WitherStormModEntityTypes.WITHER_STORM.get() && entity.getResummonTicks() > 60) {
/*     */       
/*  79 */       stack.pushPose();
/*  80 */       VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(BEAM));
/*  81 */       int[] color = entity.getBeamColor();
/*  82 */       float anim = entity.getActivateAnimation(partialTicks);
/*  83 */       float r = Mth.lerp(anim, 0.5F, color[0] / 255.0F);
/*  84 */       float g = Mth.lerp(anim, 0.5F, color[1] / 255.0F);
/*  85 */       float b = Mth.lerp(anim, 0.5F, color[2] / 255.0F);
/*  86 */       float pulse = getCrsytalScale(entity, partialTicks);
/*  87 */       Vec3 pos = Vec3.atCenterOf((Vec3i)entity.getBlockPos());
/*  88 */       if (anim > 0.01F)
/*  89 */         renderConnectBeam(consumer, stack, mc.gameRenderer.getMainCamera(), pulse, pos, pos.add(0.0D, 3.0D, 0.0D), r, g, b, anim); 
/*  90 */       stack.popPose();
/*     */     } 
/*     */     
/*  93 */     stack.pushPose();
/*  94 */     float scale = 1.0F;
/*     */     
/*  96 */     if (entity.getResummonTicks() > 0) {
/*     */       
/*  98 */       baseRotation = (tickCount + (float)Math.pow((entity.getResummonTicks() + partialTicks), 2.0D)) * 0.02F;
/*  99 */       scale -= (entity.getResummonTicks() + partialTicks) / 60.0F;
/*     */     }
/*     */     else {
/*     */       
/* 103 */       baseRotation = tickCount * 0.02F;
/*     */     } 
/* 105 */     stack.scale(scale, scale, scale);
/* 106 */     stack.translate(0.0D, 1.0D, 0.0D);
/* 107 */     int total = entity.getContainerSize();
/* 108 */     float angleInterval = 360.0F / total * 0.017453292F;
/* 109 */     for (int i = 0; i < total; i++) {
/*     */       
/* 111 */       stack.pushPose();
/* 112 */       float angle = angleInterval * i;
/* 113 */       float x = Mth.sin(angle + baseRotation);
/* 114 */       float z = Mth.cos(angle + baseRotation);
/* 115 */       stack.translate(x, 0.0D, z);
/* 116 */       Vec2 shake = entity.getShake(partialTicks);
/* 117 */       if (entity.isDoingResummonAnimation())
/* 118 */         stack.translate(shake.x, 0.0F, shake.y); 
/* 119 */       stack.translate(0.0D, (Mth.sin((tickCount + (i * total)) * 0.2F) * 0.05F), 0.0D);
/* 120 */       stack.mulPose(Axis.YN.rotationDegrees(tickCount + (i * 100)));
/* 121 */       this.itemRenderer.renderStatic(entity.getItem(i), ItemDisplayContext.FIXED, packedLight, overlayTexture, stack, buffer, entity.getLevel(), (int)entity.getBlockPos().asLong());
/* 122 */       stack.popPose();
/*     */     } 
/* 124 */     stack.popPose();
/*     */     
/* 126 */     if (entity.showWorkingArea())
/*     */     {
/* 128 */       for (Map.Entry<AbstractSuperBeaconBlockEntity.Color, BlockPos> entry : (Iterable<Map.Entry<AbstractSuperBeaconBlockEntity.Color, BlockPos>>)entity.getConnected().entrySet()) {
/*     */         
/* 130 */         BlockPos pos = entry.getValue();
/* 131 */         AbstractSuperBeaconBlockEntity.Color color = entry.getKey();
/* 132 */         float angle = SuperSupportBeaconBlockEntity.getAngleBetween(pos, entity.getBlockPos());
/* 133 */         float arcHalf = 45.0F;
/*     */         
/* 135 */         VertexConsumer consumer = buffer.getBuffer(RenderType.lines());
/*     */         
/* 137 */         drawLineSegment(stack, consumer, 0.0F, 10.0F, color.getRed(), color.getGreen(), color.getBlue(), angle + arcHalf, -0.05F);
/* 138 */         drawLineSegment(stack, consumer, 11.0F, 1.0F, color.getRed(), color.getGreen(), color.getBlue(), angle + arcHalf, -0.05F);
/* 139 */         drawLineSegment(stack, consumer, 13.0F, 1.0F, color.getRed(), color.getGreen(), color.getBlue(), angle + arcHalf, -0.05F);
/* 140 */         drawLineSegment(stack, consumer, 15.0F, 0.5F, color.getRed(), color.getGreen(), color.getBlue(), angle + arcHalf, -0.05F);
/*     */         
/* 142 */         drawLineSegment(stack, consumer, 0.0F, 10.0F, color.getRed(), color.getGreen(), color.getBlue(), angle - arcHalf, 0.05F);
/* 143 */         drawLineSegment(stack, consumer, 11.0F, 1.0F, color.getRed(), color.getGreen(), color.getBlue(), angle - arcHalf, 0.05F);
/* 144 */         drawLineSegment(stack, consumer, 13.0F, 1.0F, color.getRed(), color.getGreen(), color.getBlue(), angle - arcHalf, 0.05F);
/* 145 */         drawLineSegment(stack, consumer, 15.0F, 0.5F, color.getRed(), color.getGreen(), color.getBlue(), angle - arcHalf, 0.05F);
/*     */       } 
/*     */     }
/*     */   }
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
/*     */   private static void drawLineSegment(PoseStack stack, VertexConsumer consumer, float zOffset, float distance, int r, int g, int b, float angle, float xOffset) {
/* 170 */     stack.pushPose();
/* 171 */     stack.mulPose(Axis.YP.rotationDegrees(angle));
/* 172 */     stack.translate(xOffset, 0.0D, 0.0D);
/* 173 */     Matrix4f pose = stack.last().pose();
/* 174 */     Matrix3f normal = stack.last().normal();
/* 175 */     consumer.vertex(pose, 0.0F, 0.0F, zOffset).color(r, g, b, 255).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
/* 176 */     consumer.vertex(pose, 0.0F, 0.0F, zOffset + distance).color(r, g, b, 255).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
/* 177 */     stack.popPose();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[] getUVS() {
/* 183 */     TextureAtlasAccessor accessor = (TextureAtlasAccessor)this.atlas;
/* 184 */     float uvSquish = 3.0F;
/* 185 */     return new float[] { this.texture
/*     */         
/* 187 */         .getU0() + uvSquish / accessor.getWidth(), this.texture
/* 188 */         .getV0() + uvSquish / accessor.getHeight(), this.texture
/* 189 */         .getU1() - uvSquish / accessor.getWidth(), this.texture
/* 190 */         .getV1() - uvSquish / accessor.getHeight() };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RenderType getRenderType(SuperBeaconBlockEntity entity) {
/* 197 */     return RenderType.entityTranslucent(InventoryMenu.BLOCK_ATLAS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getCrsytalScale(SuperBeaconBlockEntity entity, float partialTicks) {
/* 203 */     return Mth.lerp(entity.getActivateAnimation(partialTicks), 0.4F, (Mth.sin((entity.getTicks() + partialTicks) * 0.1F) + 10.0F) * 0.05F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void transformCrystal(SuperBeaconBlockEntity entity, float partialTicks, PoseStack stack) {
/* 209 */     float speed = Mth.lerp(entity.getActivateAnimation(partialTicks), 0.1F, 1.0F);
/* 210 */     stack.mulPose(Axis.XP.rotationDegrees(Mth.wrapDegrees(entity.getTicks() + partialTicks) * 5.0F * speed));
/* 211 */     stack.mulPose(Axis.ZN.rotationDegrees(Mth.wrapDegrees(entity.getTicks() + partialTicks) * 12.0F * speed));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\blockentity\SuperBeaconRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */