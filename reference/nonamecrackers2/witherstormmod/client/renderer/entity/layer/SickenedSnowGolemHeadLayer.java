/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.SnowGolemModel;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.block.BlockRenderDispatcher;
/*    */ import net.minecraft.client.renderer.entity.ItemRenderer;
/*    */ import net.minecraft.client.renderer.entity.LivingEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.client.resources.model.BakedModel;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.inventory.InventoryMenu;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.client.model.data.ModelData;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ 
/*    */ public class SickenedSnowGolemHeadLayer extends RenderLayer<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>> {
/*    */   private final BlockRenderDispatcher blockRenderer;
/*    */   
/*    */   public SickenedSnowGolemHeadLayer(RenderLayerParent<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>> parent, BlockRenderDispatcher blockRenderDispatcher, ItemRenderer itemRenderer) {
/* 31 */     super(parent);
/* 32 */     this.blockRenderer = blockRenderDispatcher;
/* 33 */     this.itemRenderer = itemRenderer;
/*    */   }
/*    */   
/*    */   private final ItemRenderer itemRenderer;
/*    */   
/*    */   public void render(PoseStack poseStack, MultiBufferSource buffer, int p_117351_, SickenedSnowGolem entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
/* 39 */     if (entity.m_29930_()) {
/*    */       
/* 41 */       boolean flag = (Minecraft.m_91087_().m_91314_((Entity)entity) && entity.m_20145_());
/* 42 */       if (!entity.m_20145_() || flag) {
/*    */         
/* 44 */         poseStack.m_85836_();
/* 45 */         ((SnowGolemModel)m_117386_()).m_103851_().m_104299_(poseStack);
/* 46 */         poseStack.m_252880_(0.0F, -0.34375F, 0.0F);
/* 47 */         poseStack.m_252781_(Axis.f_252436_.m_252977_(180.0F));
/* 48 */         poseStack.m_85841_(0.625F, -0.625F, -0.625F);
/* 49 */         ItemStack itemstack = new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
/* 50 */         if (flag) {
/*    */           
/* 52 */           BlockState blockstate = ((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()).m_49966_();
/* 53 */           BakedModel bakedmodel = this.blockRenderer.m_110910_(blockstate);
/* 54 */           int i = LivingEntityRenderer.m_115338_((LivingEntity)entity, 0.0F);
/* 55 */           poseStack.m_252880_(-0.5F, -0.5F, -0.5F);
/* 56 */           this.blockRenderer.m_110937_().renderModel(poseStack.m_85850_(), buffer.m_6299_(RenderType.m_110491_(InventoryMenu.f_39692_)), blockstate, bakedmodel, 0.0F, 0.0F, 0.0F, p_117351_, i, ModelData.EMPTY, null);
/*    */         }
/*    */         else {
/*    */           
/* 60 */           this.itemRenderer.m_269491_((LivingEntity)entity, itemstack, ItemDisplayContext.HEAD, false, poseStack, buffer, entity.m_9236_(), p_117351_, LivingEntityRenderer.m_115338_((LivingEntity)entity, 0.0F), entity.m_19879_());
/*    */         } 
/*    */         
/* 63 */         poseStack.m_85849_();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\SickenedSnowGolemHeadLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */