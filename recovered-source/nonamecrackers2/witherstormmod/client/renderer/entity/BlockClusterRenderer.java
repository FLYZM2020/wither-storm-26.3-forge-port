package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.client.entity.ClientBlockClusterEntity;
import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;

public class BlockClusterRenderer extends EntityRenderer<BlockClusterEntity> {
   public BlockClusterRenderer(Context context) {
      super(context);
   }

   public void render(BlockClusterEntity cluster, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
      if (!(cluster instanceof ClientBlockClusterEntity entity)) {
         throw new ClassCastException("Can only render instances of ClientBlockClusterEntity! Is a mod doing something it shouldn't be doing?");
      } else {
         stack.m_85836_();
         stack.m_85837_(0.0, 0.5, 0.0);
         float x = Mth.m_14179_(partialTicks, entity.shakeO.f_82470_, entity.shake.f_82470_);
         float z = Mth.m_14179_(partialTicks, entity.shakeO.f_82471_, entity.shake.f_82471_);
         stack.m_252781_(Axis.f_252436_.m_252977_(-entity.getClusterYRot(partialTicks) - x * 50.0F));
         stack.m_252781_(Axis.f_252529_.m_252977_(entity.getClusterXRot(partialTicks) - z * 30.0F));
         stack.m_85837_(0.0, -0.5, 0.0);
         double xOffset = -0.5 + (Math.round(entity.m_20191_().m_82362_()) % 2L == 0L ? -0.5 : 0.0);
         double yOffset = -0.5 + (Math.round(entity.m_20191_().m_82376_()) % 2L == 0L ? -0.5 : 0.0);
         double zOffset = -0.5 + (Math.round(entity.m_20191_().m_82385_()) % 2L == 0L ? -0.5 : 0.0);
         stack.m_85837_(xOffset, yOffset, zOffset);
         Minecraft minecraft = Minecraft.m_91087_();
         BlockRenderDispatcher dispatcher = minecraft.m_91289_();
         Level world = entity.m_9236_();
         BlockAndTintGetter getter = entity.getBlockGetter();
         float fade = entity.lerpFadeAmount(partialTicks);
         float scale = Math.max(0.8F, fade * 0.5F + 0.5F);
         stack.m_85841_(scale, scale, scale);
         stack.m_85837_(x, 0.0, z);
         float r = Math.min(1.0F, fade + 0.1F);
         float g = fade;
         float b = Math.min(1.0F, fade + 0.2F);

         for (Entry<RenderType, Map<BlockPos, BlockState>> entry : entity.toRender().entrySet()) {
            RenderType type = entry.getKey();
            RenderBufferer.pushUseAsyncBuilder();
            RenderBufferer.buildAndOrRender(
               entity.getToRenderUniqueId() + ", " + type.hashCode(),
               type,
               entity::m_213877_,
               (s, c, p, o, unusedR, unusedG, unusedB, a) -> {
                  for (Entry<BlockPos, BlockState> blockEntry : entry.getValue().entrySet()) {
                     BlockState statex = blockEntry.getValue();
                     BlockPos relativePosx = blockEntry.getKey();
                     s.m_85836_();
                     s.m_85837_(relativePosx.m_123341_(), relativePosx.m_123342_() + entity.m_20191_().m_82376_() / 2.0, relativePosx.m_123343_());
                     dispatcher.renderBatched(
                        statex,
                        relativePosx.m_121955_(entity.getStartPos()),
                        getter,
                        s,
                        c,
                        true,
                        RandomSource.m_216335_(relativePosx.m_121878_()),
                        ModelData.EMPTY,
                        type
                     );
                     s.m_85849_();
                  }
               },
               stack,
               p_225623_6_,
               OverlayTexture.f_118083_,
               r,
               g,
               b,
               1.0F
            );
         }

         for (Entry<BlockPos, BlockState> entry : entity.tilesToRender().entrySet()) {
            BlockState state = entry.getValue();
            if (state.m_60799_() == RenderShape.ENTITYBLOCK_ANIMATED) {
               BlockPos relativePos = entry.getKey();
               BlockPos pos = BlockPos.m_274561_(
                  entity.m_20185_() + relativePos.m_123341_(),
                  entity.m_20186_() + relativePos.m_123342_() + entity.m_20191_().m_82376_() / 2.0 - 0.5,
                  entity.m_20189_() + relativePos.m_123343_()
               );
               stack.m_85836_();
               stack.m_85837_(relativePos.m_123341_(), relativePos.m_123342_() + entity.m_20191_().m_82376_() / 2.0, relativePos.m_123343_());
               CompoundTag data = entity.getTileDataFromOffsetPos(relativePos);
               if (data != null) {
                  String id = data.m_128461_("id");
                  BlockEntity tile = ((BlockEntityType)ForgeRegistries.BLOCK_ENTITY_TYPES.getValue(new ResourceLocation(id))).m_155264_(pos, state);
                  BlockEntityRenderer<BlockEntity> tileRenderer = minecraft.m_167982_().m_112265_(tile);
                  tile.m_142339_(world);
                  tile.m_142466_(data);
                  if (tileRenderer != null) {
                     tileRenderer.m_6922_(tile, partialTicks, stack, buffer, LevelRenderer.m_109541_(getter, pos), OverlayTexture.f_118083_);
                  }
               }

               stack.m_85849_();
            }
         }

         stack.m_85849_();
         super.m_7392_(entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
      }
   }

   public boolean shouldRender(BlockClusterEntity entity, Frustum p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
      return !WitherStormModConfig.CLIENT.blockClusterRendering.get() && !entity.forceRender()
         ? false
         : super.m_5523_(entity, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
   }

   public ResourceLocation getTextureLocation(BlockClusterEntity entity) {
      return null;
   }
}
