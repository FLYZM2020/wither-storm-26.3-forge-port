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
/*    */ import net.minecraft.resources.ResourceLocation;
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
/* 25 */     this.f_114477_ = 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SuperTNTEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
/* 31 */     stack.m_85836_();
/* 32 */     stack.m_85837_(0.0D, 0.5D, 0.0D);
/* 33 */     if (entity.m_32100_() - partialTicks + 1.0F < 10.0F) {
/*    */       
/* 35 */       float f = 1.0F - (entity.m_32100_() - partialTicks + 1.0F) / 10.0F;
/* 36 */       f = Mth.m_14036_(f, 0.0F, 1.0F);
/* 37 */       f *= f;
/* 38 */       f *= f;
/* 39 */       float f1 = 1.0F + f * 5.0F;
/* 40 */       stack.m_85841_(f1, f1, f1);
/*    */     } 
/*    */     
/* 43 */     stack.m_252781_(Axis.f_252436_.m_252977_(-90.0F));
/* 44 */     stack.m_85837_(-0.5D, -0.5D, 0.5D);
/* 45 */     stack.m_252781_(Axis.f_252436_.m_252977_(90.0F));
/* 46 */     renderWhiteSolidBlock(((Block)WitherStormModBlocks.SUPER_TNT.get()).m_49966_(), stack, buffer, p_225623_6_, (entity.m_32100_() / 5 % 2 == 0));
/* 47 */     stack.m_85849_();
/* 48 */     super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void renderWhiteSolidBlock(BlockState state, PoseStack stack, MultiBufferSource buffer, int packedLight, boolean flash) {
/*    */     int i;
/* 55 */     if (flash) {
/* 56 */       i = OverlayTexture.m_118093_(OverlayTexture.m_118088_(1.0F), 10);
/*    */     } else {
/* 58 */       i = OverlayTexture.f_118083_;
/*    */     } 
/* 60 */     Minecraft mc = Minecraft.m_91087_();
/* 61 */     mc.m_91289_().renderSingleBlock(state, stack, buffer, packedLight, i, ModelData.EMPTY, ItemBlockRenderTypes.m_109284_(state, false));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(SuperTNTEntity entity) {
/* 67 */     return InventoryMenu.f_39692_;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SuperTNTRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */