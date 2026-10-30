/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.ItemBlockRenderTypes;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.inventory.InventoryMenu;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.client.model.data.ModelData;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SuperTNTEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ 
/*    */ public class SuperTNTRenderer
/*    */   extends EntityRenderer<SuperTNTEntity> {
/*    */   public SuperTNTRenderer(EntityRendererProvider.Context context) {
/* 24 */     super(context);
/* 25 */     this.shadowRadius = 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SuperTNTEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
/* 31 */     stack.pushPose();
/* 32 */     stack.translate(0.0D, 0.5D, 0.0D);
/* 33 */     if (entity.getFuse() - partialTicks + 1.0F < 10.0F) {
/*    */       
/* 35 */       float f = 1.0F - (entity.getFuse() - partialTicks + 1.0F) / 10.0F;
/* 36 */       f = Mth.clamp(f, 0.0F, 1.0F);
/* 37 */       f *= f;
/* 38 */       f *= f;
/* 39 */       float f1 = 1.0F + f * 5.0F;
/* 40 */       stack.scale(f1, f1, f1);
/*    */     } 
/*    */     
/* 43 */     stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
/* 44 */     stack.translate(-0.5D, -0.5D, 0.5D);
/* 45 */     stack.mulPose(Axis.YP.rotationDegrees(90.0F));
/* 46 */     renderWhiteSolidBlock(((Block)WitherStormModBlocks.SUPER_TNT.get()).defaultBlockState(), stack, buffer, p_225623_6_, (entity.getFuse() / 5 % 2 == 0));
/* 47 */     stack.popPose();
/* 48 */     super.render((Entity)entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void renderWhiteSolidBlock(BlockState state, PoseStack stack, MultiBufferSource buffer, int packedLight, boolean flash) {
/*    */     int i;
/* 55 */     if (flash) {
/* 56 */       i = OverlayTexture.pack(OverlayTexture.u(1.0F), 10);
/*    */     } else {
/* 58 */       i = OverlayTexture.NO_OVERLAY;
/*    */     } 
/* 60 */     Minecraft mc = Minecraft.getInstance();
/* 61 */     mc.getBlockRenderer().renderSingleBlock(state, stack, buffer, packedLight, i, ModelData.EMPTY, ItemBlockRenderTypes.getRenderType(state, false));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(SuperTNTEntity entity) {
/* 67 */     return InventoryMenu.BLOCK_ATLAS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SuperTNTRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */