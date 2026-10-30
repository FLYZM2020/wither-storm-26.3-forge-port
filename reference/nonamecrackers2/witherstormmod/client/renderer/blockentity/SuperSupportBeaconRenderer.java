/*    */ package nonamecrackers2.witherstormmod.client.renderer.blockentity;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.mojang.math.Axis;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Vec3i;
/*    */ import net.minecraft.resources.ResourceLocation;
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
/* 25 */   private static final ResourceLocation DIAMOND = new ResourceLocation("witherstormmod", "textures/block/support_beacon_diamond.png");
/* 26 */   private static final ResourceLocation EMERALD = new ResourceLocation("witherstormmod", "textures/block/support_beacon_emerald.png");
/* 27 */   private static final ResourceLocation IRON = new ResourceLocation("witherstormmod", "textures/block/support_beacon_iron.png");
/* 28 */   private static final ResourceLocation REDSTONE = new ResourceLocation("witherstormmod", "textures/block/support_beacon_redstone.png");
/* 29 */   private static final Map<AbstractSuperBeaconBlockEntity.Color, ResourceLocation> TEX_BY_COLOR = (Map<AbstractSuperBeaconBlockEntity.Color, ResourceLocation>)ImmutableMap.of(AbstractSuperBeaconBlockEntity.Color.AQUA, DIAMOND, AbstractSuperBeaconBlockEntity.Color.GREEN, EMERALD, AbstractSuperBeaconBlockEntity.Color.GRAY, IRON, AbstractSuperBeaconBlockEntity.Color.RED, REDSTONE);
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
/* 44 */       stack.m_85836_();
/* 45 */       stack.m_85837_(0.5D, 0.6D, 0.5D);
/* 46 */       Minecraft mc = Minecraft.m_91087_();
/* 47 */       VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_(BEAM));
/* 48 */       int[] color = entity.getBeamColor();
/* 49 */       float anim = entity.getActivateAnimation(partialTicks);
/* 50 */       float r = Mth.m_14179_(anim, 0.5F, color[0] / 255.0F);
/* 51 */       float g = Mth.m_14179_(anim, 0.5F, color[1] / 255.0F);
/* 52 */       float b = Mth.m_14179_(anim, 0.5F, color[2] / 255.0F);
/* 53 */       float pulse = getCrsytalScale(entity, partialTicks);
/* 54 */       if (anim > 0.01F)
/* 55 */         renderConnectBeam(consumer, stack, mc.f_91063_.m_109153_(), pulse, Vec3.m_82512_((Vec3i)entity.m_58899_()), Vec3.m_82512_((Vec3i)pos).m_82520_(0.0D, -0.1D, 0.0D), r, g, b, anim); 
/* 56 */       stack.m_85849_();
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
/* 69 */     return RenderType.m_110458_((entity.getColor() == null) ? IRON : TEX_BY_COLOR.get(entity.getColor()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float getCrsytalScale(SuperSupportBeaconBlockEntity entity, float partialTicks) {
/* 75 */     return Mth.m_14179_(entity.getActivateAnimation(partialTicks), 0.2F, (Mth.m_14031_((entity.getTicks() + partialTicks) * 0.1F) + 10.0F) * 0.035F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void transformCrystal(SuperSupportBeaconBlockEntity entity, float partialTicks, PoseStack stack) {
/* 81 */     stack.m_85837_(0.0D, 0.1D, 0.0D);
/* 82 */     float speed = Mth.m_14179_(entity.getActivateAnimation(partialTicks), 0.2F, 1.0F);
/* 83 */     stack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14177_(entity.getTicks() + partialTicks) * 4.0F * speed));
/* 84 */     stack.m_252781_(Axis.f_252392_.m_252977_(Mth.m_14177_(entity.getTicks() + partialTicks) * 8.0F * speed));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\blockentity\SuperSupportBeaconRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */