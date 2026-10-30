/*    */ package nonamecrackers2.witherstormmod.client.util;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.client.extensions.common.IClientItemExtensions;
/*    */ 
/*    */ 
/*    */ public class TaintedCarvedPumpkinExtensions
/*    */   implements IClientItemExtensions
/*    */ {
/* 15 */   public static final TaintedCarvedPumpkinExtensions INSTANCE = new TaintedCarvedPumpkinExtensions();
/* 16 */   private static final ResourceLocation TAINTED_PUMPKIN_BLUR = new ResourceLocation("witherstormmod", "textures/misc/tainted_pumpkin_blur.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderHelmetOverlay(ItemStack stack, Player player, int width, int height, float partialTick) {
/* 23 */     GuiGraphics graphics = new GuiGraphics(Minecraft.m_91087_(), Minecraft.m_91087_().m_91269_().m_110104_());
/* 24 */     RenderSystem.disableDepthTest();
/* 25 */     RenderSystem.depthMask(false);
/* 26 */     graphics.m_280246_(1.0F, 1.0F, 1.0F, 1.0F);
/* 27 */     graphics.m_280398_(TAINTED_PUMPKIN_BLUR, 0, 0, -90, 0.0F, 0.0F, width, height, width, height);
/* 28 */     RenderSystem.depthMask(true);
/* 29 */     RenderSystem.enableDepthTest();
/* 30 */     graphics.m_280246_(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\TaintedCarvedPumpkinExtensions.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */