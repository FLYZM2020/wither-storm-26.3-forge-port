/*    */ package nonamecrackers2.witherstormmod.client.gui.widget;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.resources.Identifier;
/*    */ 
/*    */ 
/*    */ public class RefreshSoundsButton
/*    */   extends Button
/*    */ {
/* 12 */   private static final Identifier SOUND_REFRESHER = Identifier.fromNamespaceAndPath("witherstormmod", "textures/gui/sound_refresher.png");
/*    */ 
/*    */   
/*    */   public RefreshSoundsButton(Button.Builder builder) {
/* 16 */     super(builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderWidget(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
/* 22 */     int x = 0;
/* 23 */     int y = 0;
/* 24 */     if (!this.active)
/* 25 */       x = 20; 
/* 26 */     if (isHoveredOrFocused())
/* 27 */       y = 20; 
/* 28 */     RenderSystem.enableDepthTest();
/* 29 */     stack.blit(SOUND_REFRESHER, getX(), getY(), x, y, this.width, this.height, 256, 256);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\widget\RefreshSoundsButton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */