/*    */ package nonamecrackers2.witherstormmod.client.renderer.blockentity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.ItemRenderer;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.WitheredPhlegmBlockEntity;
/*    */ 
/*    */ public class WitheredPhlegmRenderer
/*    */   implements BlockEntityRenderer<WitheredPhlegmBlockEntity>
/*    */ {
/*    */   private final ItemRenderer itemRenderer;
/*    */   
/*    */   public WitheredPhlegmRenderer(BlockEntityRendererProvider.Context context) {
/* 21 */     this.itemRenderer = context.getItemRenderer();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(WitheredPhlegmBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
/* 27 */     stack.translate(0.5D, 0.5D, 0.5D);
/* 28 */     long seed = entity.getBlockPos().asLong();
/* 29 */     RandomSource random = RandomSource.create(seed);
/* 30 */     int totalItems = 0;
/* 31 */     for (int i = 0; i < entity.getContainerSize(); i++) {
/*    */       
/* 33 */       if (!entity.getItem(i).isEmpty())
/* 34 */         totalItems++; 
/*    */     } 
/* 36 */     float ratio = totalItems / entity.getContainerSize();
/* 37 */     for (int j = 0; j < entity.getContainerSize(); j++) {
/*    */       
/* 39 */       ItemStack item = entity.getItem(j);
/* 40 */       if (!item.isEmpty()) {
/*    */         
/* 42 */         stack.pushPose();
/* 43 */         float scale = 0.5F + (1.0F - ratio) / 2.0F;
/* 44 */         float dist = 1.0F;
/* 45 */         stack.scale(scale, scale, scale);
/* 46 */         stack.translate(random.nextDouble() * dist - dist / 2.0D, random.nextDouble() * dist - dist / 2.0D, random.nextDouble() * dist - dist / 2.0D);
/* 47 */         stack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
/* 48 */         stack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
/* 49 */         this.itemRenderer.renderStatic(item, ItemDisplayContext.FIXED, packedLight, overlayTexture, stack, buffer, entity.getLevel(), (int)seed);
/* 50 */         stack.popPose();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getViewDistance() {
/* 58 */     return 32;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\blockentity\WitheredPhlegmRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */