/*    */ package nonamecrackers2.witherstormmod.client.renderer.blockentity;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.mojang.math.Axis;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Vec3i;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;
/*    */ 
/*    */ public class SuperSupportBeaconRenderer
/*    */   extends AbstractSuperBeaconRenderer<SuperSupportBeaconBlockEntity>
/*    */ {
/* 24 */   private static final float[] UVS = new float[] { 0.375F, 0.375F, 0.625F, 0.625F };
/* 25 */   private static final Identifier DIAMOND = Identifier.fromNamespaceAndPath("witherstormmod", "textures/block/support_beacon_diamond.png");
/* 26 */   private static final Identifier EMERALD = Identifier.fromNamespaceAndPath("witherstormmod", "textures/block/support_beacon_emerald.png");
/* 27 */   private static final Identifier IRON = Identifier.fromNamespaceAndPath("witherstormmod", "textures/block/support_beacon_iron.png");
/* 28 */   private static final Identifier REDSTONE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/block/support_beacon_redstone.png");
/* 29 */   private static final Map<AbstractSuperBeaconBlockEntity.Color, Identifier> TEX_BY_COLOR = (Map<AbstractSuperBeaconBlockEntity.Color, Identifier>)ImmutableMap.of(AbstractSuperBeaconBlockEntity.Color.AQUA, DIAMOND, AbstractSuperBeaconBlockEntity.Color.GREEN, EMERALD, AbstractSuperBeaconBlockEntity.Color.GRAY, IRON, AbstractSuperBeaconBlockEntity.Color.RED, REDSTONE);
/*    */ 
/*    */   
/*    */   public SuperSupportBeaconRenderer(BlockEntityRendererProvider.Context context) {
/* 33 */     super(context);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SuperSupportBeaconBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
/* 39 */     super.render(entity, partialTicks, stack, buffer, packedLight, overlayTexture);
/*    */     
/* 41 */     BlockPos pos = entity.getBeamPos();
/* 42 */     if (pos != null) {
/*    */       
/* 44 */       stack.pushPose();
/* 45 */       stack.translate(0.5D, 0.6D, 0.5D);
/* 46 */       Minecraft mc = Minecraft.getInstance();
/* 47 */       VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(BEAM));
/* 48 */       int[] color = entity.getBeamColor();
/* 49 */       float anim = entity.getActivateAnimation(partialTicks);
/* 50 */       float r = Mth.lerp(anim, 0.5F, color[0] / 255.0F);
/* 51 */       float g = Mth.lerp(anim, 0.5F, color[1] / 255.0F);
/* 52 */       float b = Mth.lerp(anim, 0.5F, color[2] / 255.0F);
/* 53 */       float pulse = getCrsytalScale(entity, partialTicks);
/* 54 */       if (anim > 0.01F)
/* 55 */         renderConnectBeam(consumer, stack, mc.gameRenderer.getMainCamera(), pulse, Vec3.atCenterOf((Vec3i)entity.getBlockPos()), Vec3.atCenterOf((Vec3i)pos).add(0.0D, -0.1D, 0.0D), r, g, b, anim); 
/* 56 */       stack.popPose();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float[] getUVS() {
/* 63 */     return UVS;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected RenderType getRenderType(SuperSupportBeaconBlockEntity entity) {
/* 69 */     return RenderType.entityCutoutNoCull((entity.getColor() == null) ? IRON : TEX_BY_COLOR.get(entity.getColor()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float getCrsytalScale(SuperSupportBeaconBlockEntity entity, float partialTicks) {
/* 75 */     return Mth.lerp(entity.getActivateAnimation(partialTicks), 0.2F, (Mth.sin((entity.getTicks() + partialTicks) * 0.1F) + 10.0F) * 0.035F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void transformCrystal(SuperSupportBeaconBlockEntity entity, float partialTicks, PoseStack stack) {
/* 81 */     stack.translate(0.0D, 0.1D, 0.0D);
/* 82 */     float speed = Mth.lerp(entity.getActivateAnimation(partialTicks), 0.2F, 1.0F);
/* 83 */     stack.mulPose(Axis.XP.rotationDegrees(Mth.wrapDegrees(entity.getTicks() + partialTicks) * 4.0F * speed));
/* 84 */     stack.mulPose(Axis.YN.rotationDegrees(Mth.wrapDegrees(entity.getTicks() + partialTicks) * 8.0F * speed));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\blockentity\SuperSupportBeaconRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */