/*    */ package nonamecrackers2.witherstormmod.client.gui.menu;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.WitheredPhlegmMenu;
/*    */ 
/*    */ public class WitheredPhlegmScreen extends AbstractContainerScreen<WitheredPhlegmMenu> {
/* 13 */   public static final ResourceLocation CONTAINER_BACKGROUND = WitherStormMod.id("textures/gui/container/withered_phlegm.png");
/*    */ 
/*    */   
/*    */   public WitheredPhlegmScreen(WitheredPhlegmMenu menu, Inventory inventory, Component title) {
/* 17 */     super((AbstractContainerMenu)menu, inventory, title);
/* 18 */     this.f_97727_ = 207;
/* 19 */     this.f_97731_ = 114;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_88315_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
/* 25 */     m_280273_(stack);
/* 26 */     super.m_88315_(stack, mouseX, mouseY, partialTicks);
/* 27 */     int xp = ((WitheredPhlegmMenu)this.f_97732_).getXp();
/* 28 */     if (xp > 0) {
/*    */       
/* 30 */       int x = this.f_96543_ / 2 - this.f_97726_ / 2 + this.f_97728_;
/* 31 */       int y = this.f_96544_ / 2 - this.f_97727_ / 2 + this.f_97731_ - 20;
/* 32 */       Objects.requireNonNull(this.f_96547_); stack.m_280614_(this.f_96547_, (Component)Component.m_237113_("XP:"), x, y - 9 - 2, 4210752, false);
/* 33 */       String level = String.valueOf(xp);
/* 34 */       stack.drawString(this.f_96547_, level, (x + 1), y, 0, false);
/* 35 */       stack.drawString(this.f_96547_, level, (x - 1), y, 0, false);
/* 36 */       stack.drawString(this.f_96547_, level, x, (y + 1), 0, false);
/* 37 */       stack.drawString(this.f_96547_, level, x, (y - 1), 0, false);
/* 38 */       stack.drawString(this.f_96547_, level, x, y, 8453920, false);
/*    */     } 
/* 40 */     m_280072_(stack, mouseX, mouseY);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_7286_(GuiGraphics stack, float partialTick, int mouseX, int mouseY) {
/* 46 */     int x = (this.f_96543_ - this.f_97726_) / 2;
/* 47 */     int y = (this.f_96544_ - this.f_97727_) / 2;
/* 48 */     stack.m_280218_(CONTAINER_BACKGROUND, x, y, 0, 0, this.f_97726_, this.f_97727_);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\menu\WitheredPhlegmScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */