/*    */ package nonamecrackers2.witherstormmod.client.gui.menu;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.WitheredPhlegmMenu;
/*    */ 
/*    */ public class WitheredPhlegmScreen extends AbstractContainerScreen<WitheredPhlegmMenu> {
/* 13 */   public static final Identifier CONTAINER_BACKGROUND = WitherStormMod.id("textures/gui/container/withered_phlegm.png");
/*    */ 
/*    */   
/*    */   public WitheredPhlegmScreen(WitheredPhlegmMenu menu, Inventory inventory, Component title) {
/* 17 */     super((AbstractContainerMenu)menu, inventory, title);
/* 18 */     this.imageHeight = 207;
/* 19 */     this.inventoryLabelY = 114;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
/* 25 */     renderBackground(stack);
/* 26 */     super.render(stack, mouseX, mouseY, partialTicks);
/* 27 */     int xp = ((WitheredPhlegmMenu)this.menu).getXp();
/* 28 */     if (xp > 0) {
/*    */       
/* 30 */       int x = this.width / 2 - this.imageWidth / 2 + this.titleLabelX;
/* 31 */       int y = this.height / 2 - this.imageHeight / 2 + this.inventoryLabelY - 20;
/* 32 */       Objects.requireNonNull(this.font); stack.drawString(this.font, (Component)Component.literal("XP:"), x, y - 9 - 2, 4210752, false);
/* 33 */       String level = String.valueOf(xp);
/* 34 */       stack.drawString(this.font, level, (x + 1), y, 0, false);
/* 35 */       stack.drawString(this.font, level, (x - 1), y, 0, false);
/* 36 */       stack.drawString(this.font, level, x, (y + 1), 0, false);
/* 37 */       stack.drawString(this.font, level, x, (y - 1), 0, false);
/* 38 */       stack.drawString(this.font, level, x, y, 8453920, false);
/*    */     } 
/* 40 */     renderTooltip(stack, mouseX, mouseY);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderBg(GuiGraphics stack, float partialTick, int mouseX, int mouseY) {
/* 46 */     int x = (this.width - this.imageWidth) / 2;
/* 47 */     int y = (this.height - this.imageHeight) / 2;
/* 48 */     stack.blit(CONTAINER_BACKGROUND, x, y, 0, 0, this.imageWidth, this.imageHeight);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\menu\WitheredPhlegmScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */