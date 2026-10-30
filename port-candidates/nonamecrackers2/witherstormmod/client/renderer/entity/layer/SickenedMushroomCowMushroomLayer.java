/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.CowModel;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.block.BlockRenderDispatcher;
/*    */ import net.minecraft.client.renderer.entity.LivingEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.client.resources.model.BakedModel;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.inventory.InventoryMenu;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.client.model.data.ModelData;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedMushroomCow;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ 
/*    */ public class SickenedMushroomCowMushroomLayer<T extends SickenedMushroomCow>
/*    */   extends RenderLayer<T, CowModel<T>> {
/*    */   private final BlockRenderDispatcher blockRenderer;
/*    */   
/*    */   public SickenedMushroomCowMushroomLayer(RenderLayerParent<T, CowModel<T>> parent, BlockRenderDispatcher blockRenderer) {
/* 28 */     super(parent);
/* 29 */     this.blockRenderer = blockRenderer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(PoseStack stack, MultiBufferSource buffer, int p_117351_, T entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
/* 35 */     if (!entity.isBaby()) {
/*    */       
/* 37 */       Minecraft minecraft = Minecraft.getInstance();
/* 38 */       boolean flag = (minecraft.shouldEntityAppearGlowing((Entity)entity) && entity.isInvisible());
/* 39 */       if (!entity.isInvisible() || flag) {
/*    */         
/* 41 */         BlockState state = ((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get()).defaultBlockState();
/* 42 */         int i = LivingEntityRenderer.getOverlayCoords((LivingEntity)entity, 0.0F);
/* 43 */         BakedModel bakedmodel = this.blockRenderer.getBlockModel(state);
/* 44 */         stack.pushPose();
/* 45 */         stack.translate(0.2F, -0.35F, 0.5F);
/* 46 */         stack.mulPose(Axis.YP.rotationDegrees(-48.0F));
/* 47 */         stack.scale(-1.0F, -1.0F, 1.0F);
/* 48 */         stack.translate(-0.5F, -0.5F, -0.5F);
/* 49 */         renderMushroomBlock(stack, buffer, 15728880, flag, state, i, bakedmodel);
/* 50 */         stack.popPose();
/* 51 */         stack.pushPose();
/* 52 */         stack.translate(0.2F, -0.35F, 0.5F);
/* 53 */         stack.mulPose(Axis.YP.rotationDegrees(42.0F));
/* 54 */         stack.translate(0.1F, 0.0F, -0.6F);
/* 55 */         stack.mulPose(Axis.YP.rotationDegrees(-48.0F));
/* 56 */         stack.scale(-1.0F, -1.0F, 1.0F);
/* 57 */         stack.translate(-0.5F, -0.5F, -0.5F);
/* 58 */         renderMushroomBlock(stack, buffer, 15728880, flag, state, i, bakedmodel);
/* 59 */         stack.popPose();
/* 60 */         stack.pushPose();
/* 61 */         ((CowModel)getParentModel()).getHead().translateAndRotate(stack);
/* 62 */         stack.translate(0.0F, -0.7F, -0.2F);
/* 63 */         stack.mulPose(Axis.YP.rotationDegrees(-78.0F));
/* 64 */         stack.scale(-1.0F, -1.0F, 1.0F);
/* 65 */         stack.translate(-0.5F, -0.5F, -0.5F);
/* 66 */         renderMushroomBlock(stack, buffer, 15728880, flag, state, i, bakedmodel);
/* 67 */         stack.popPose();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void renderMushroomBlock(PoseStack stack, MultiBufferSource buffer, int p_234855_, boolean p_234856_, BlockState state, int p_234858_, BakedModel model) {
/* 74 */     if (p_234856_) {
/* 75 */       this.blockRenderer.getModelRenderer().renderModel(stack.last(), buffer.getBuffer(RenderType.outline(InventoryMenu.BLOCK_ATLAS)), state, model, 0.0F, 0.0F, 0.0F, p_234855_, p_234858_, ModelData.EMPTY, null);
/*    */     } else {
/* 77 */       this.blockRenderer.renderSingleBlock(state, stack, buffer, p_234855_, p_234858_, ModelData.EMPTY, null);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\SickenedMushroomCowMushroomLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */