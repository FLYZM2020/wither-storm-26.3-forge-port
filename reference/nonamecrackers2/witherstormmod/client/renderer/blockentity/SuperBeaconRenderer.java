/*     */ package nonamecrackers2.witherstormmod.client.renderer.blockentity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*     */ import net.minecraft.client.renderer.entity.ItemRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.resources.ResourceLocation;
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
/*  37 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "block/tainted_dust_block");
/*     */   
/*     */   private final TextureAtlas atlas;
/*     */   private final TextureAtlasSprite texture;
/*     */   private final ItemRenderer itemRenderer;
/*     */   
/*     */   public SuperBeaconRenderer(BlockEntityRendererProvider.Context context) {
/*  44 */     super(context);
/*  45 */     Minecraft mc = Minecraft.m_91087_();
/*  46 */     this.atlas = mc.m_91304_().m_119428_(InventoryMenu.f_39692_);
/*  47 */     this.texture = this.atlas.m_118316_(TEXTURE);
/*  48 */     this.itemRenderer = mc.m_91291_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SuperBeaconBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
/*     */     float baseRotation;
/*  54 */     super.render(entity, partialTicks, stack, buffer, packedLight, overlayTexture);
/*     */     
/*  56 */     Minecraft mc = Minecraft.m_91087_();
/*     */     
/*  58 */     float tickCount = entity.getTicks() + partialTicks;
/*     */     
/*  60 */     float resummonTicks = entity.getResummonTicks() + partialTicks;
/*  61 */     if (entity.getResummonEntity() == WitherStormModEntityTypes.WITHER_STORM.get() && entity.getResummonTicks() > 60) {
/*     */       
/*  63 */       float speed = 200.0F / (resummonTicks - 400.0F);
/*  64 */       stack.m_85836_();
/*  65 */       Vec2 shake = entity.getShake(partialTicks);
/*  66 */       stack.m_85837_((shake.f_82470_ * speed), 3.0D, (shake.f_82471_ * speed));
/*  67 */       stack.m_85837_(0.5D, 0.5D, 0.5D);
/*  68 */       stack.m_252781_(Axis.f_252403_.m_252977_(tickCount * speed));
/*  69 */       stack.m_252781_(Axis.f_252495_.m_252977_(tickCount * speed));
/*  70 */       stack.m_85837_(-0.5D, -0.5D, -0.5D);
/*  71 */       mc.m_91289_().renderSingleBlock(Blocks.f_50272_.m_49966_(), stack, buffer, 15728880, OverlayTexture.f_118083_, ModelData.EMPTY, null);
/*  72 */       stack.m_85849_();
/*     */     } 
/*     */     
/*  75 */     stack.m_85837_(0.5D, 0.5D, 0.5D);
/*     */     
/*  77 */     if (entity.getResummonEntity() == WitherStormModEntityTypes.WITHER_STORM.get() && entity.getResummonTicks() > 60) {
/*     */       
/*  79 */       stack.m_85836_();
/*  80 */       VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_(BEAM));
/*  81 */       int[] color = entity.getBeamColor();
/*  82 */       float anim = entity.getActivateAnimation(partialTicks);
/*  83 */       float r = Mth.m_14179_(anim, 0.5F, color[0] / 255.0F);
/*  84 */       float g = Mth.m_14179_(anim, 0.5F, color[1] / 255.0F);
/*  85 */       float b = Mth.m_14179_(anim, 0.5F, color[2] / 255.0F);
/*  86 */       float pulse = getCrsytalScale(entity, partialTicks);
/*  87 */       Vec3 pos = Vec3.m_82512_((Vec3i)entity.m_58899_());
/*  88 */       if (anim > 0.01F)
/*  89 */         renderConnectBeam(consumer, stack, mc.f_91063_.m_109153_(), pulse, pos, pos.m_82520_(0.0D, 3.0D, 0.0D), r, g, b, anim); 
/*  90 */       stack.m_85849_();
/*     */     } 
/*     */     
/*  93 */     stack.m_85836_();
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
/* 105 */     stack.m_85841_(scale, scale, scale);
/* 106 */     stack.m_85837_(0.0D, 1.0D, 0.0D);
/* 107 */     int total = entity.m_6643_();
/* 108 */     float angleInterval = 360.0F / total * 0.017453292F;
/* 109 */     for (int i = 0; i < total; i++) {
/*     */       
/* 111 */       stack.m_85836_();
/* 112 */       float angle = angleInterval * i;
/* 113 */       float x = Mth.m_14031_(angle + baseRotation);
/* 114 */       float z = Mth.m_14089_(angle + baseRotation);
/* 115 */       stack.m_85837_(x, 0.0D, z);
/* 116 */       Vec2 shake = entity.getShake(partialTicks);
/* 117 */       if (entity.isDoingResummonAnimation())
/* 118 */         stack.m_252880_(shake.f_82470_, 0.0F, shake.f_82471_); 
/* 119 */       stack.m_85837_(0.0D, (Mth.m_14031_((tickCount + (i * total)) * 0.2F) * 0.05F), 0.0D);
/* 120 */       stack.m_252781_(Axis.f_252392_.m_252977_(tickCount + (i * 100)));
/* 121 */       this.itemRenderer.m_269128_(entity.m_8020_(i), ItemDisplayContext.FIXED, packedLight, overlayTexture, stack, buffer, entity.m_58904_(), (int)entity.m_58899_().m_121878_());
/* 122 */       stack.m_85849_();
/*     */     } 
/* 124 */     stack.m_85849_();
/*     */     
/* 126 */     if (entity.showWorkingArea())
/*     */     {
/* 128 */       for (Map.Entry<AbstractSuperBeaconBlockEntity.Color, BlockPos> entry : (Iterable<Map.Entry<AbstractSuperBeaconBlockEntity.Color, BlockPos>>)entity.getConnected().entrySet()) {
/*     */         
/* 130 */         BlockPos pos = entry.getValue();
/* 131 */         AbstractSuperBeaconBlockEntity.Color color = entry.getKey();
/* 132 */         float angle = SuperSupportBeaconBlockEntity.getAngleBetween(pos, entity.m_58899_());
/* 133 */         float arcHalf = 45.0F;
/*     */         
/* 135 */         VertexConsumer consumer = buffer.m_6299_(RenderType.m_110504_());
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
/* 170 */     stack.m_85836_();
/* 171 */     stack.m_252781_(Axis.f_252436_.m_252977_(angle));
/* 172 */     stack.m_85837_(xOffset, 0.0D, 0.0D);
/* 173 */     Matrix4f pose = stack.m_85850_().m_252922_();
/* 174 */     Matrix3f normal = stack.m_85850_().m_252943_();
/* 175 */     consumer.m_252986_(pose, 0.0F, 0.0F, zOffset).m_6122_(r, g, b, 255).m_252939_(normal, 0.0F, 0.0F, 1.0F).m_5752_();
/* 176 */     consumer.m_252986_(pose, 0.0F, 0.0F, zOffset + distance).m_6122_(r, g, b, 255).m_252939_(normal, 0.0F, 0.0F, 1.0F).m_5752_();
/* 177 */     stack.m_85849_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[] getUVS() {
/* 183 */     TextureAtlasAccessor accessor = (TextureAtlasAccessor)this.atlas;
/* 184 */     float uvSquish = 3.0F;
/* 185 */     return new float[] { this.texture
/*     */         
/* 187 */         .m_118409_() + uvSquish / accessor.getWidth(), this.texture
/* 188 */         .m_118411_() + uvSquish / accessor.getHeight(), this.texture
/* 189 */         .m_118410_() - uvSquish / accessor.getWidth(), this.texture
/* 190 */         .m_118412_() - uvSquish / accessor.getHeight() };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RenderType getRenderType(SuperBeaconBlockEntity entity) {
/* 197 */     return RenderType.m_110473_(InventoryMenu.f_39692_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getCrsytalScale(SuperBeaconBlockEntity entity, float partialTicks) {
/* 203 */     return Mth.m_14179_(entity.getActivateAnimation(partialTicks), 0.4F, (Mth.m_14031_((entity.getTicks() + partialTicks) * 0.1F) + 10.0F) * 0.05F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void transformCrystal(SuperBeaconBlockEntity entity, float partialTicks, PoseStack stack) {
/* 209 */     float speed = Mth.m_14179_(entity.getActivateAnimation(partialTicks), 0.1F, 1.0F);
/* 210 */     stack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14177_(entity.getTicks() + partialTicks) * 5.0F * speed));
/* 211 */     stack.m_252781_(Axis.f_252393_.m_252977_(Mth.m_14177_(entity.getTicks() + partialTicks) * 12.0F * speed));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\blockentity\SuperBeaconRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */