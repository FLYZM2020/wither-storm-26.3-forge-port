/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ItemBlockRenderTypes;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.resources.ResourceLocation;
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
/*  30 */     this.f_114477_ = 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(FormidibombEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
/*  36 */     stack.m_85836_();
/*  37 */     stack.m_85837_(0.0D, 0.5D, 0.0D);
/*  38 */     if (entity.getFuseLife() - partialTicks + 1.0F < 20.0F) {
/*     */       
/*  40 */       float f = 1.0F - (entity.getFuseLife() - partialTicks + 1.0F) / 20.0F;
/*  41 */       f = Mth.m_14036_(f, 0.0F, 1.0F);
/*  42 */       f *= f;
/*  43 */       f *= f;
/*  44 */       float f1 = 1.0F + f * 20.0F;
/*  45 */       stack.m_85841_(f1, f1, f1);
/*     */     } 
/*     */     
/*  48 */     if (entity.getFuseLife() - partialTicks < 240.0F) {
/*     */       
/*  50 */       float f = (240.0F - entity.getFuseLife() + partialTicks) / 150.0F;
/*  51 */       Random random = new Random(289L);
/*  52 */       VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
/*  53 */       stack.m_85836_();
/*     */       
/*  55 */       for (int i = 0; i < (f + f * f) / 2.0F * 10.0F; i++) {
/*     */         
/*  57 */         stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0F));
/*  58 */         stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0F));
/*  59 */         stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0F));
/*  60 */         stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0F));
/*  61 */         stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0F));
/*  62 */         stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0F + f * 90.0F));
/*  63 */         float f3 = random.nextFloat() * (float)entity.m_20191_().m_82309_() * 0.2F + f * (float)Math.max(1.0D, ((40.0F - entity.getFuseLife() + partialTicks) / 6.0F));
/*  64 */         float f4 = random.nextFloat() * 0.025F + f;
/*  65 */         Matrix4f matrix4f = stack.m_85850_().m_252922_();
/*  66 */         int k = 255;
/*  67 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/*  68 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/*  69 */         builder.m_252986_(matrix4f, -((float)(Math.sqrt(3.0D) / 2.0D)) * f4, f3, -0.5F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/*  70 */         builder.m_252986_(matrix4f, (float)(Math.sqrt(3.0D) / 2.0D) * f4, f3, -0.5F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/*  71 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/*  72 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/*  73 */         builder.m_252986_(matrix4f, (float)(Math.sqrt(3.0D) / 2.0D) * f4, f3, -0.5F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/*  74 */         builder.m_252986_(matrix4f, 0.0F, f3, 1.0F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/*  75 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/*  76 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/*  77 */         builder.m_252986_(matrix4f, 0.0F, f3, 1.0F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/*  78 */         builder.m_252986_(matrix4f, -((float)(Math.sqrt(3.0D) / 2.0D)) * f4, f3, -0.5F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/*     */       } 
/*     */       
/*  81 */       stack.m_85849_();
/*     */     } 
/*     */     
/*  84 */     stack.m_252781_(Axis.f_252436_.m_252977_(-90.0F));
/*  85 */     stack.m_85837_(-0.5D, -0.5D, 0.5D);
/*  86 */     stack.m_252781_(Axis.f_252436_.m_252977_(90.0F));
/*  87 */     float ticks = entity.getAirTime() + partialTicks;
/*  88 */     if (!entity.m_20096_()) {
/*     */       
/*  90 */       stack.m_85837_(0.5D, 0.5D, 0.5D);
/*  91 */       stack.m_252781_(Axis.f_252436_.m_252977_(ticks));
/*  92 */       stack.m_252781_(Axis.f_252529_.m_252977_(ticks * 0.5F));
/*  93 */       stack.m_85837_(-0.5D, -0.5D, -0.5D);
/*     */     } 
/*  95 */     BlockState state = entity.getBlockState();
/*  96 */     int fuse = (entity.getStartFuse() > 0 && entity.getFuseLife() > 0) ? (entity.getStartFuse() / entity.getFuseLife()) : 0;
/*  97 */     renderWhiteSolidBlock(state, stack, buffer, p_225623_6_, (fuse % 2 == 0));
/*  98 */     stack.m_85849_();
/*  99 */     super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void renderWhiteSolidBlock(BlockState state, PoseStack stack, MultiBufferSource buffer, int packedLight, boolean flash) {
/*     */     int i;
/* 106 */     if (flash) {
/* 107 */       i = OverlayTexture.m_118093_(OverlayTexture.m_118088_(1.0F), 10);
/*     */     } else {
/* 109 */       i = OverlayTexture.f_118083_;
/*     */     } 
/* 111 */     Minecraft mc = Minecraft.m_91087_();
/* 112 */     mc.m_91289_().renderSingleBlock(state, stack, buffer, packedLight, i, ModelData.EMPTY, ItemBlockRenderTypes.m_109284_(state, false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(FormidibombEntity entity) {
/* 118 */     return InventoryMenu.f_39692_;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\FormidibombRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */