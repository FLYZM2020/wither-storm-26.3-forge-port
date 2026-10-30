/*     */ package nonamecrackers2.witherstormmod.client.jei.category;
/*     */ 
/*     */ import mezz.jei.api.gui.drawable.IDrawable;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Icon
/*     */   implements IDrawable
/*     */ {
/*     */   private final IDrawable wrapped;
/*     */   private final ResourceLocation iconTexture;
/*     */   
/*     */   public Icon(IDrawable wrapped, ResourceLocation iconTexture) {
/*  90 */     this.wrapped = wrapped;
/*  91 */     this.iconTexture = iconTexture;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/*  97 */     return this.wrapped.getWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 103 */     return this.wrapped.getHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(GuiGraphics stack, int xOffset, int yOffset) {
/* 109 */     this.wrapped.draw(stack, xOffset, yOffset);
/* 110 */     stack.m_280398_(this.iconTexture, xOffset + 8, yOffset + 8, 0, 0.0F, 0.0F, 8, 8, 8, 8);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\jei\category\SuperBeaconCategory$Icon.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */