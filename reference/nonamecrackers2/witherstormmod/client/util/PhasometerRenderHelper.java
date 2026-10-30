/*    */ package nonamecrackers2.witherstormmod.client.util;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import nonamecrackers2.witherstormmod.common.item.PhasometerItem;
/*    */ 
/*    */ public class PhasometerRenderHelper {
/*    */   public static void renderPhasometerOverlay(ItemStack item, GuiGraphics stack, float partialTicks, int width, int height, String dotDotDot) {
/* 15 */     CompoundTag tag = item.m_41784_();
/* 16 */     Minecraft mc = Minecraft.m_91087_();
/* 17 */     if (tag.m_128441_(PhasometerItem.DataEntry.PHASE.tagName)) {
/*    */       
/* 19 */       List<PhasometerItem.DataEntry> entries = PhasometerItem.getEntries(tag);
/* 20 */       for (int i = 0; i < entries.size(); i++) {
/*    */         
/* 22 */         Component text = ((PhasometerItem.DataEntry)entries.get(i)).getDisplayText(tag);
/* 23 */         Objects.requireNonNull(mc.f_91062_); stack.m_280653_(mc.f_91062_, text, width / 2, 30 + i * (9 + 2), -1);
/*    */       } 
/*    */     } else {
/*    */       MutableComponent mutableComponent;
/*    */ 
/*    */       
/* 29 */       if (tag.m_128441_(PhasometerItem.DataEntry.OBSTRUCTED.tagName)) {
/* 30 */         Component text = PhasometerItem.DataEntry.OBSTRUCTED.getDisplayText(tag);
/*    */       } else {
/* 32 */         mutableComponent = Component.m_237110_("description.phasometer.searching", new Object[] { dotDotDot }).m_130940_(ChatFormatting.GRAY);
/* 33 */       }  stack.m_280653_(mc.f_91062_, (Component)mutableComponent, width / 2, 30, -1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\PhasometerRenderHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */