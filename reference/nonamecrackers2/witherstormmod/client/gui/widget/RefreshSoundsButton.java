/*    */ package nonamecrackers2.witherstormmod.client.gui.widget;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class RefreshSoundsButton
/*    */   extends Button
/*    */ {
/* 12 */   private static final ResourceLocation SOUND_REFRESHER = new ResourceLocation("witherstormmod", "textures/gui/sound_refresher.png");
/*    */ 
/*    */   
/*    */   public RefreshSoundsButton(Button.Builder builder) {
/* 16 */     super(builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_87963_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
/* 22 */     int x = 0;
/* 23 */     int y = 0;
/* 24 */     if (!this.f_93623_)
/* 25 */       x = 20; 
/* 26 */     if (m_198029_())
/* 27 */       y = 20; 
/* 28 */     RenderSystem.enableDepthTest();
/* 29 */     stack.m_280163_(SOUND_REFRESHER, m_252754_(), m_252907_(), x, y, this.f_93618_, this.f_93619_, 256, 256);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\widget\RefreshSoundsButton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */