/*    */ package nonamecrackers2.witherstormmod.client.util;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.player.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FormidiBladeAnimationHelper
/*    */ {
/*    */   public static void onRenderItemInHand(AbstractClientPlayer player, ItemStack item, PoseStack stack, InteractionHand hand, float partialTicks, MultiBufferSource buffer) {
/* 18 */     if (item.getItem() instanceof nonamecrackers2.witherstormmod.common.item.FormidiBladeItem) {
/*    */       
/* 20 */       VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(AmuletAnimationHelper.GLARE));
/* 21 */       CompoundTag tag = item.getTag();
/* 22 */       float pulseIntensity = 0.0F;
/* 23 */       if (tag != null)
/* 24 */         pulseIntensity = Math.min(1.0F, tag.getFloat("Power")); 
/* 25 */       AmuletAnimationHelper.drawGlare(stack, hand, 1.0F, -0.33D, 0.089D, -0.43D, 0.5058824F, 0.0F, 0.7764706F, 0.0F, player.tickCount, partialTicks, consumer, pulseIntensity);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\FormidiBladeAnimationHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */