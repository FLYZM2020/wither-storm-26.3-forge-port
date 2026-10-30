/*     */ package nonamecrackers2.witherstormmod.client.gui.menu;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.components.AbstractSelectionList;
/*     */ import net.minecraft.client.gui.components.ObjectSelectionList;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.resources.MobEffectTextureManager;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.effect.MobEffect;
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
/*     */ public class EffectList
/*     */   extends ObjectSelectionList<SuperBeaconScreen.EffectList.Entry>
/*     */ {
/*     */   public EffectList(Minecraft mc, int width, int height, int top, int bottom) {
/* 242 */     super(mc, width, height, top, bottom, 20);
/* 243 */     m_93488_(false);
/* 244 */     m_93496_(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEffect(MobEffect effect) {
/* 249 */     m_7085_((AbstractSelectionList.Entry)new Entry(effect));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_5759_() {
/* 255 */     return getWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_5756_() {
/* 261 */     return this.f_93393_ + getWidth() - 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 266 */     m_93516_();
/*     */   }
/*     */   
/*     */   public class Entry
/*     */     extends ObjectSelectionList.Entry<Entry>
/*     */   {
/*     */     private final MobEffect effect;
/*     */     private final Component name;
/*     */     
/*     */     private Entry(MobEffect effect) {
/* 276 */       this.effect = effect;
/* 277 */       this.name = (Component)effect.m_19482_().m_6881_();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Component m_142172_() {
/* 283 */       return this.name;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void m_6311_(GuiGraphics stack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean selected, float partialTicks) {
/* 289 */       MobEffectTextureManager textureManager = SuperBeaconScreen.EffectList.this.f_93386_.m_91306_();
/* 290 */       TextureAtlasSprite sprite = textureManager.m_118732_(this.effect);
/* 291 */       stack.m_280246_(1.0F, 1.0F, 1.0F, 1.0F);
/* 292 */       stack.m_280159_(left, top + height / 2 - 9, 0, 18, 18, sprite);
/* 293 */       Objects.requireNonNull(SuperBeaconScreen.EffectList.this.f_93386_.f_91062_); stack.m_280430_(SuperBeaconScreen.EffectList.this.f_93386_.f_91062_, this.name, left + 28, top + height / 2 - 9 / 2, -1);
/*     */     }
/*     */ 
/*     */     
/*     */     public MobEffect getEffect() {
/* 298 */       return this.effect;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean m_6375_(double x, double y, int clickType) {
/* 304 */       if (clickType == 0) {
/*     */         
/* 306 */         SuperBeaconScreen.EffectList.this.m_6987_((AbstractSelectionList.Entry)this);
/* 307 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 311 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\menu\SuperBeaconScreen$EffectList.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */