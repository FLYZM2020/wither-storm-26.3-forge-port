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
/* 21 */     this.itemRenderer = context.m_234447_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(WitheredPhlegmBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
/* 27 */     stack.m_85837_(0.5D, 0.5D, 0.5D);
/* 28 */     long seed = entity.m_58899_().m_121878_();
/* 29 */     RandomSource random = RandomSource.m_216335_(seed);
/* 30 */     int totalItems = 0;
/* 31 */     for (int i = 0; i < entity.m_6643_(); i++) {
/*    */       
/* 33 */       if (!entity.m_8020_(i).m_41619_())
/* 34 */         totalItems++; 
/*    */     } 
/* 36 */     float ratio = totalItems / entity.m_6643_();
/* 37 */     for (int j = 0; j < entity.m_6643_(); j++) {
/*    */       
/* 39 */       ItemStack item = entity.m_8020_(j);
/* 40 */       if (!item.m_41619_()) {
/*    */         
/* 42 */         stack.m_85836_();
/* 43 */         float scale = 0.5F + (1.0F - ratio) / 2.0F;
/* 44 */         float dist = 1.0F;
/* 45 */         stack.m_85841_(scale, scale, scale);
/* 46 */         stack.m_85837_(random.m_188500_() * dist - dist / 2.0D, random.m_188500_() * dist - dist / 2.0D, random.m_188500_() * dist - dist / 2.0D);
/* 47 */         stack.m_252781_(Axis.f_252529_.m_252977_(random.m_188501_() * 360.0F));
/* 48 */         stack.m_252781_(Axis.f_252436_.m_252977_(random.m_188501_() * 360.0F));
/* 49 */         this.itemRenderer.m_269128_(item, ItemDisplayContext.FIXED, packedLight, overlayTexture, stack, buffer, entity.m_58904_(), (int)seed);
/* 50 */         stack.m_85849_();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int m_142163_() {
/* 58 */     return 32;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\blockentity\WitheredPhlegmRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */