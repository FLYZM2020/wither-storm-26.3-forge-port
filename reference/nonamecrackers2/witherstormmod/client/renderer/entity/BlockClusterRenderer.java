/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.LevelRenderer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.block.BlockRenderDispatcher;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*     */ import net.minecraft.client.renderer.culling.Frustum;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.level.BlockAndTintGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.RenderShape;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.client.model.data.ModelData;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.client.entity.ClientBlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ 
/*     */ public class BlockClusterRenderer extends EntityRenderer<BlockClusterEntity> {
/*     */   public BlockClusterRenderer(EntityRendererProvider.Context context) {
/*  39 */     super(context);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(BlockClusterEntity cluster, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
/*  45 */     if (!(cluster instanceof ClientBlockClusterEntity))
/*  46 */       throw new ClassCastException("Can only render instances of ClientBlockClusterEntity! Is a mod doing something it shouldn't be doing?"); 
/*  47 */     ClientBlockClusterEntity entity = (ClientBlockClusterEntity)cluster;
/*  48 */     stack.m_85836_();
/*  49 */     stack.m_85837_(0.0D, 0.5D, 0.0D);
/*  50 */     float x = Mth.m_14179_(partialTicks, entity.shakeO.f_82470_, entity.shake.f_82470_);
/*  51 */     float z = Mth.m_14179_(partialTicks, entity.shakeO.f_82471_, entity.shake.f_82471_);
/*  52 */     stack.m_252781_(Axis.f_252436_.m_252977_(-entity.getClusterYRot(partialTicks) - x * 50.0F));
/*  53 */     stack.m_252781_(Axis.f_252529_.m_252977_(entity.getClusterXRot(partialTicks) - z * 30.0F));
/*  54 */     stack.m_85837_(0.0D, -0.5D, 0.0D);
/*  55 */     double xOffset = -0.5D + ((Math.round(entity.m_20191_().m_82362_()) % 2L == 0L) ? -0.5D : 0.0D);
/*  56 */     double yOffset = -0.5D + ((Math.round(entity.m_20191_().m_82376_()) % 2L == 0L) ? -0.5D : 0.0D);
/*  57 */     double zOffset = -0.5D + ((Math.round(entity.m_20191_().m_82385_()) % 2L == 0L) ? -0.5D : 0.0D);
/*  58 */     stack.m_85837_(xOffset, yOffset, zOffset);
/*  59 */     Minecraft minecraft = Minecraft.m_91087_();
/*  60 */     BlockRenderDispatcher dispatcher = minecraft.m_91289_();
/*  61 */     Level world = entity.m_9236_();
/*  62 */     BlockAndTintGetter getter = entity.getBlockGetter();
/*  63 */     float fade = entity.lerpFadeAmount(partialTicks);
/*  64 */     float scale = Math.max(0.8F, fade * 0.5F + 0.5F);
/*  65 */     stack.m_85841_(scale, scale, scale);
/*  66 */     stack.m_85837_(x, 0.0D, z);
/*     */     
/*  68 */     float r = Math.min(1.0F, fade + 0.1F);
/*  69 */     float g = fade;
/*  70 */     float b = Math.min(1.0F, fade + 0.2F);
/*  71 */     for (Map.Entry<RenderType, Map<BlockPos, BlockState>> entry : (Iterable<Map.Entry<RenderType, Map<BlockPos, BlockState>>>)entity.toRender().entrySet()) {
/*     */       
/*  73 */       RenderType type = entry.getKey();
/*  74 */       RenderBufferer.pushUseAsyncBuilder();
/*  75 */       Objects.requireNonNull(entity); RenderBufferer.buildAndOrRender(entity.getToRenderUniqueId() + ", " + entity.getToRenderUniqueId(), type, entity::m_213877_, (s, c, p, o, unusedR, unusedG, unusedB, a) -> { for (Map.Entry<BlockPos, BlockState> blockEntry : (Iterable<Map.Entry<BlockPos, BlockState>>)((Map)entry.getValue()).entrySet()) { BlockState state = blockEntry.getValue(); BlockPos relativePos = blockEntry.getKey(); s.m_85836_(); s.m_85837_(relativePos.m_123341_(), relativePos.m_123342_() + entity.m_20191_().m_82376_() / 2.0D, relativePos.m_123343_()); dispatcher.renderBatched(state, relativePos.m_121955_((Vec3i)entity.getStartPos()), getter, s, c, true, RandomSource.m_216335_(relativePos.m_121878_()), ModelData.EMPTY, type); s.m_85849_(); }  }stack, p_225623_6_, OverlayTexture.f_118083_, r, g, b, 1.0F);
/*     */     } 
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
/*  89 */     for (Map.Entry<BlockPos, BlockState> entry : (Iterable<Map.Entry<BlockPos, BlockState>>)entity.tilesToRender().entrySet()) {
/*     */       
/*  91 */       BlockState state = entry.getValue();
/*  92 */       if (state.m_60799_() == RenderShape.ENTITYBLOCK_ANIMATED) {
/*     */         
/*  94 */         BlockPos relativePos = entry.getKey();
/*  95 */         BlockPos pos = BlockPos.m_274561_(entity.m_20185_() + relativePos.m_123341_(), entity.m_20186_() + relativePos.m_123342_() + entity.m_20191_().m_82376_() / 2.0D - 0.5D, entity.m_20189_() + relativePos.m_123343_());
/*  96 */         stack.m_85836_();
/*  97 */         stack.m_85837_(relativePos.m_123341_(), relativePos.m_123342_() + entity.m_20191_().m_82376_() / 2.0D, relativePos.m_123343_());
/*  98 */         CompoundTag data = entity.getTileDataFromOffsetPos(relativePos);
/*  99 */         if (data != null) {
/*     */           
/* 101 */           String id = data.m_128461_("id");
/* 102 */           BlockEntity tile = ((BlockEntityType)ForgeRegistries.BLOCK_ENTITY_TYPES.getValue(new ResourceLocation(id))).m_155264_(pos, state);
/* 103 */           BlockEntityRenderer<BlockEntity> tileRenderer = minecraft.m_167982_().m_112265_(tile);
/* 104 */           tile.m_142339_(world);
/* 105 */           tile.m_142466_(data);
/* 106 */           if (tileRenderer != null)
/* 107 */             tileRenderer.m_6922_(tile, partialTicks, stack, buffer, LevelRenderer.m_109541_(getter, pos), OverlayTexture.f_118083_); 
/*     */         } 
/* 109 */         stack.m_85849_();
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     stack.m_85849_();
/*     */     
/* 115 */     super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender(BlockClusterEntity entity, Frustum p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
/* 121 */     if (((Boolean)WitherStormModConfig.CLIENT.blockClusterRendering.get()).booleanValue() || entity.forceRender()) {
/* 122 */       return super.m_5523_((Entity)entity, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
/*     */     }
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(BlockClusterEntity entity) {
/* 130 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\BlockClusterRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */