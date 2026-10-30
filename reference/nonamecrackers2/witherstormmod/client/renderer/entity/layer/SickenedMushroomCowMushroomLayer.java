/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.CowModel;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
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
/* 35 */     if (!entity.m_6162_()) {
/*    */       
/* 37 */       Minecraft minecraft = Minecraft.m_91087_();
/* 38 */       boolean flag = (minecraft.m_91314_((Entity)entity) && entity.m_20145_());
/* 39 */       if (!entity.m_20145_() || flag) {
/*    */         
/* 41 */         BlockState state = ((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get()).m_49966_();
/* 42 */         int i = LivingEntityRenderer.m_115338_((LivingEntity)entity, 0.0F);
/* 43 */         BakedModel bakedmodel = this.blockRenderer.m_110910_(state);
/* 44 */         stack.m_85836_();
/* 45 */         stack.m_252880_(0.2F, -0.35F, 0.5F);
/* 46 */         stack.m_252781_(Axis.f_252436_.m_252977_(-48.0F));
/* 47 */         stack.m_85841_(-1.0F, -1.0F, 1.0F);
/* 48 */         stack.m_252880_(-0.5F, -0.5F, -0.5F);
/* 49 */         renderMushroomBlock(stack, buffer, 15728880, flag, state, i, bakedmodel);
/* 50 */         stack.m_85849_();
/* 51 */         stack.m_85836_();
/* 52 */         stack.m_252880_(0.2F, -0.35F, 0.5F);
/* 53 */         stack.m_252781_(Axis.f_252436_.m_252977_(42.0F));
/* 54 */         stack.m_252880_(0.1F, 0.0F, -0.6F);
/* 55 */         stack.m_252781_(Axis.f_252436_.m_252977_(-48.0F));
/* 56 */         stack.m_85841_(-1.0F, -1.0F, 1.0F);
/* 57 */         stack.m_252880_(-0.5F, -0.5F, -0.5F);
/* 58 */         renderMushroomBlock(stack, buffer, 15728880, flag, state, i, bakedmodel);
/* 59 */         stack.m_85849_();
/* 60 */         stack.m_85836_();
/* 61 */         ((CowModel)m_117386_()).m_102450_().m_104299_(stack);
/* 62 */         stack.m_252880_(0.0F, -0.7F, -0.2F);
/* 63 */         stack.m_252781_(Axis.f_252436_.m_252977_(-78.0F));
/* 64 */         stack.m_85841_(-1.0F, -1.0F, 1.0F);
/* 65 */         stack.m_252880_(-0.5F, -0.5F, -0.5F);
/* 66 */         renderMushroomBlock(stack, buffer, 15728880, flag, state, i, bakedmodel);
/* 67 */         stack.m_85849_();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void renderMushroomBlock(PoseStack stack, MultiBufferSource buffer, int p_234855_, boolean p_234856_, BlockState state, int p_234858_, BakedModel model) {
/* 74 */     if (p_234856_) {
/* 75 */       this.blockRenderer.m_110937_().renderModel(stack.m_85850_(), buffer.m_6299_(RenderType.m_110491_(InventoryMenu.f_39692_)), state, model, 0.0F, 0.0F, 0.0F, p_234855_, p_234858_, ModelData.EMPTY, null);
/*    */     } else {
/* 77 */       this.blockRenderer.renderSingleBlock(state, stack, buffer, p_234855_, p_234858_, ModelData.EMPTY, null);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\SickenedMushroomCowMushroomLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */