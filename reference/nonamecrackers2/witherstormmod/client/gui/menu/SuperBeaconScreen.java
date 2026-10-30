/*     */ package nonamecrackers2.witherstormmod.client.gui.menu;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.components.AbstractSelectionList;
/*     */ import net.minecraft.client.gui.components.Button;
/*     */ import net.minecraft.client.gui.components.ObjectSelectionList;
/*     */ import net.minecraft.client.gui.components.Tooltip;
/*     */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*     */ import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.resources.MobEffectTextureManager;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.FormattedText;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.FormattedCharSequence;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.inventory.ContainerListener;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.AbstractSuperBeaconMenu;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.packet.SuperBeaconSetEffectMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.SuperBeaconToggleAreaMessage;
/*     */ 
/*     */ public class SuperBeaconScreen extends AbstractContainerScreen<AbstractSuperBeaconMenu> {
/*  33 */   private static final ResourceLocation BORDER = new ResourceLocation("witherstormmod", "textures/gui/container/super_beacon.png");
/*  34 */   private static final ResourceLocation WINDOW = new ResourceLocation("witherstormmod", "textures/gui/container/super_beacon_window.png");
/*  35 */   private static final ResourceLocation BUTTONS = new ResourceLocation("witherstormmod", "textures/gui/container/super_beacon_buttons.png");
/*  36 */   private static final Component SELECTED_EFFECT = (Component)Component.m_237115_("container.witherstormmod.withered_beacon.selected");
/*  37 */   private static final Component AVAILABLE_EFFECTS = (Component)Component.m_237115_("container.witherstormmod.withered_beacon.available_effects");
/*  38 */   private static final Component INFO = (Component)Component.m_237115_("withered_beacon.info"); private static final int WINDOW_X = 10; private static final int WINDOW_Y = 9; private static final int WINDOW_WIDTH = 210; private static final int WINDOW_HEIGHT = 116; private static final int DIVIDED_WINDOW_WIDTH = 105;
/*     */   private static final int BUTTON_WIDTH = 22;
/*     */   private static final int BOTTOM_BAR_HEIGHT = 32;
/*     */   @Nullable
/*     */   private MobEffect primary;
/*     */   private int level;
/*     */   private final EffectList effectList;
/*     */   @Nullable
/*     */   private BeaconButton select;
/*     */   @Nullable
/*     */   private BeaconButton unselect;
/*     */   @Nullable
/*     */   private Button info;
/*     */   @Nullable
/*     */   private Button exitInfo;
/*     */   @Nullable
/*     */   private BeaconButton showArea;
/*     */   private boolean shouldRenderInfo;
/*     */   private boolean shouldShowArea;
/*     */   private int setEffectCooldown;
/*     */   
/*     */   public SuperBeaconScreen(final AbstractSuperBeaconMenu menu, Inventory inventory, Component name) {
/*  60 */     super((AbstractContainerMenu)menu, inventory, name);
/*  61 */     this.f_97726_ = 230;
/*  62 */     this.f_97727_ = 157;
/*  63 */     this.effectList = new EffectList(Minecraft.m_91087_(), 105, 116, 9, 125);
/*  64 */     this.effectList.m_93507_(115);
/*  65 */     menu.m_38893_(new ContainerListener()
/*     */         {
/*     */           public void m_7934_(AbstractContainerMenu container, int slot, ItemStack stack) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void m_142153_(AbstractContainerMenu container, int slot, int value) {
/*  73 */             SuperBeaconScreen.this.primary = menu.getPrimaryEffect();
/*  74 */             SuperBeaconScreen.this.level = menu.getLevel();
/*  75 */             SuperBeaconScreen.this.shouldShowArea = menu.shouldShowArea();
/*  76 */             SuperBeaconScreen.this.setEffectCooldown = menu.getCooldown();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7856_() {
/*  84 */     super.m_7856_();
/*     */     
/*  86 */     int width = (this.f_96543_ - this.f_97726_) / 2;
/*  87 */     int height = (this.f_96544_ - this.f_97727_) / 2;
/*  88 */     this.effectList.m_93437_(105, 116, height + 9 + 24, height + 9 + 116 - 4);
/*  89 */     this.effectList.m_93507_(width + 10 + 105 + 1);
/*  90 */     m_142416_((GuiEventListener)this.effectList);
/*     */     
/*  92 */     int buttonY = 125 + height + 16 - 11;
/*  93 */     int buttonMiddle = 115 + width;
/*  94 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 104 */       .select = (BeaconButton)Button.m_253074_((Component)Component.m_237119_(), button -> { MobEffect effect = getSelectedEffect(); if (effect != null) { WitherStormModPacketHandlers.MAIN.sendToServer(new SuperBeaconSetEffectMessage(MobEffect.m_19459_(effect))); this.f_96541_.f_91074_.m_6915_(); }  }).m_252794_(buttonMiddle - 2 - 22, buttonY).m_253046_(22, 22).build(builder -> new BeaconButton(builder, 88));
/*     */     
/* 106 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       .unselect = (BeaconButton)Button.m_253074_((Component)Component.m_237119_(), button -> { if (!this.shouldRenderInfo) WitherStormModPacketHandlers.MAIN.sendToServer(new SuperBeaconSetEffectMessage(MobEffect.m_19459_(null)));  }).m_252794_(buttonMiddle + 2, buttonY).m_253046_(22, 22).build(builder -> new BeaconButton(builder, 110));
/*     */ 
/*     */     
/* 115 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 121 */       .info = Button.m_253074_((Component)Component.m_237113_("i"), button -> { this.shouldRenderInfo = true; m_7787_((GuiEventListener)this.exitInfo); }).m_252794_(width + 10, buttonY).m_253046_(20, 20).m_253136_();
/*     */ 
/*     */     
/* 124 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       .exitInfo = Button.m_253074_((Component)Component.m_237113_("X").m_130940_(ChatFormatting.RED), button -> { this.shouldRenderInfo = false; m_169411_((GuiEventListener)button); }).m_252794_(width - 20, height - 30).m_253046_(20, 20).m_253136_();
/*     */ 
/*     */     
/* 133 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 141 */       .showArea = (BeaconButton)Button.m_253074_((Component)Component.m_237119_(), button -> { WitherStormModPacketHandlers.MAIN.sendToServer(new SuperBeaconToggleAreaMessage(!this.shouldShowArea)); this.f_96541_.f_91074_.m_6915_(); }).m_252794_(width + this.f_97726_ - 10 - 22, buttonY).m_253046_(22, 22).m_257505_(Tooltip.m_257550_((Component)Component.m_237115_("gui.witherstormmod.button.showArea.description"))).build(builder -> new BeaconButton(builder, 132));
/*     */     
/* 143 */     if (this.shouldRenderInfo)
/* 144 */       m_7787_((GuiEventListener)this.exitInfo); 
/* 145 */     this.exitInfo.setFGColor(16711680);
/* 146 */     m_7787_((GuiEventListener)this.select);
/* 147 */     m_7787_((GuiEventListener)this.unselect);
/* 148 */     m_7787_((GuiEventListener)this.info);
/* 149 */     m_7787_((GuiEventListener)this.showArea);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_88315_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
/* 155 */     m_280273_(stack);
/* 156 */     super.m_88315_(stack, mouseX, mouseY, partialTicks);
/* 157 */     MobEffect effect = getSelectedEffect();
/* 158 */     if (this.setEffectCooldown > 0) {
/* 159 */       this.select.m_257544_(Tooltip.m_257550_((Component)Component.m_237115_("gui.witherstormmod.button.select.cooldown.description")));
/*     */     } else {
/* 161 */       this.select.m_257544_(null);
/* 162 */     }  this.select.f_93623_ = (!this.shouldRenderInfo && effect != null && effect != this.primary && this.setEffectCooldown == 0);
/* 163 */     this.unselect.f_93623_ = (!this.shouldRenderInfo && this.primary != null);
/* 164 */     this.info.f_93623_ = !this.shouldRenderInfo;
/* 165 */     this.showArea.f_93623_ = !this.shouldRenderInfo;
/* 166 */     this.showArea.overlayTexY = 22 * (this.shouldShowArea ? 6 : 7);
/* 167 */     this.showArea.m_88315_(stack, mouseX, mouseY, partialTicks);
/* 168 */     this.select.m_88315_(stack, mouseX, mouseY, partialTicks);
/* 169 */     this.unselect.m_88315_(stack, mouseX, mouseY, partialTicks);
/* 170 */     this.info.m_88315_(stack, mouseX, mouseY, partialTicks);
/* 171 */     if (this.shouldRenderInfo) {
/*     */       
/* 173 */       stack.m_280168_().m_85836_();
/* 174 */       stack.m_280168_().m_85837_(0.0D, 0.0D, 1.0D);
/* 175 */       m_280273_(stack);
/* 176 */       this.exitInfo.m_88315_(stack, mouseX, mouseY, partialTicks);
/* 177 */       int textX = (this.f_96543_ - this.f_97726_) / 2 - 20;
/* 178 */       int textWidth = this.f_97726_ + 40;
/* 179 */       int textHeight = this.f_96547_.m_92920_(INFO.getString(), textWidth);
/* 180 */       int textY = (this.f_96544_ - this.f_97727_) / 2 + this.f_97727_ / 2 - textHeight / 2;
/* 181 */       stack.m_280168_().m_85837_(0.0D, 0.0D, 1.0D);
/* 182 */       List<FormattedCharSequence> text = this.f_96547_.m_92923_((FormattedText)INFO, textWidth);
/* 183 */       for (int i = 0; i < text.size(); i++) {
/* 184 */         Objects.requireNonNull(this.f_96547_); stack.m_280648_(this.f_96547_, text.get(i), textX, i * 9 + textY, -1);
/* 185 */       }  stack.m_280168_().m_85849_();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7286_(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
/* 192 */     drawMenu(stack, WINDOW);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_280003_(GuiGraphics stack, int mouseX, int mouseY) {
/* 198 */     stack.m_280218_(BORDER, 0, 0, 0, 0, this.f_97726_, this.f_97727_);
/* 199 */     stack.m_280218_(BORDER, 116, 13, 0, this.f_97727_, 105, 20);
/* 200 */     stack.m_280653_(this.f_96547_, SELECTED_EFFECT, 62, 19, -1);
/* 201 */     stack.m_280653_(this.f_96547_, AVAILABLE_EFFECTS, 167, 19, -1);
/* 202 */     Objects.requireNonNull(this.f_96547_); stack.m_280430_(this.f_96547_, (Component)Component.m_237110_("container.witherstormmod.withered_beacon.level", new Object[] { (this.level > 0) ? Component.m_237115_("enchantment.level." + this.level) : "" }), 20, 115 - 9, -1);
/* 203 */     if (this.primary != null) {
/*     */       
/* 205 */       MobEffectTextureManager textureManager = this.f_96541_.m_91306_();
/* 206 */       TextureAtlasSprite sprite = textureManager.m_118732_(this.primary);
/* 207 */       int x = 62 - (int)(sprite.m_245424_().m_246492_() * 1.5F);
/* 208 */       int y = 67 - (int)(sprite.m_245424_().m_245330_() * 1.5F) - 8;
/* 209 */       stack.m_280159_(x, y, 0, 54, 54, sprite);
/* 210 */       stack.m_280653_(this.f_96547_, this.primary.m_19482_(), 62, y + sprite.m_245424_().m_246492_() * 3, -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawMenu(GuiGraphics stack, ResourceLocation tex) {
/* 216 */     stack.m_280246_(1.0F, 1.0F, 1.0F, 1.0F);
/* 217 */     int width = (this.f_96543_ - this.f_97726_) / 2;
/* 218 */     int height = (this.f_96544_ - this.f_97727_) / 2;
/* 219 */     stack.m_280218_(tex, width, height, 0, 0, this.f_97726_, this.f_97727_);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private MobEffect getSelectedEffect() {
/* 224 */     EffectList.Entry entry = (EffectList.Entry)this.effectList.m_93511_();
/* 225 */     if (entry != null) {
/* 226 */       return entry.getEffect();
/*     */     }
/* 228 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValidEffects(Set<MobEffect> effects) {
/* 233 */     this.effectList.clear();
/* 234 */     for (MobEffect effect : effects)
/* 235 */       this.effectList.addEffect(effect); 
/*     */   }
/*     */   
/*     */   public static class EffectList
/*     */     extends ObjectSelectionList<EffectList.Entry>
/*     */   {
/*     */     public EffectList(Minecraft mc, int width, int height, int top, int bottom) {
/* 242 */       super(mc, width, height, top, bottom, 20);
/* 243 */       m_93488_(false);
/* 244 */       m_93496_(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public void addEffect(MobEffect effect) {
/* 249 */       m_7085_((AbstractSelectionList.Entry)new Entry(effect));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_5759_() {
/* 255 */       return getWidth();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int m_5756_() {
/* 261 */       return this.f_93393_ + getWidth() - 7;
/*     */     }
/*     */ 
/*     */     
/*     */     public void clear() {
/* 266 */       m_93516_();
/*     */     }
/*     */     
/*     */     public class Entry
/*     */       extends ObjectSelectionList.Entry<Entry>
/*     */     {
/*     */       private final MobEffect effect;
/*     */       private final Component name;
/*     */       
/*     */       private Entry(MobEffect effect) {
/* 276 */         this.effect = effect;
/* 277 */         this.name = (Component)effect.m_19482_().m_6881_();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Component m_142172_() {
/* 283 */         return this.name;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void m_6311_(GuiGraphics stack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean selected, float partialTicks) {
/* 289 */         MobEffectTextureManager textureManager = SuperBeaconScreen.EffectList.this.f_93386_.m_91306_();
/* 290 */         TextureAtlasSprite sprite = textureManager.m_118732_(this.effect);
/* 291 */         stack.m_280246_(1.0F, 1.0F, 1.0F, 1.0F);
/* 292 */         stack.m_280159_(left, top + height / 2 - 9, 0, 18, 18, sprite);
/* 293 */         Objects.requireNonNull(SuperBeaconScreen.EffectList.this.f_93386_.f_91062_); stack.m_280430_(SuperBeaconScreen.EffectList.this.f_93386_.f_91062_, this.name, left + 28, top + height / 2 - 9 / 2, -1);
/*     */       }
/*     */ 
/*     */       
/*     */       public MobEffect getEffect() {
/* 298 */         return this.effect;
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean m_6375_(double x, double y, int clickType)
/*     */       {
/* 304 */         if (clickType == 0) {
/*     */           
/* 306 */           SuperBeaconScreen.EffectList.this.m_6987_((AbstractSelectionList.Entry)this);
/* 307 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 311 */         return false; } } } public class Entry extends ObjectSelectionList.Entry<EffectList.Entry> { private final MobEffect effect; public boolean m_6375_(double x, double y, int clickType) { if (clickType == 0) { SuperBeaconScreen.EffectList.this.m_6987_((AbstractSelectionList.Entry)this); return true; }  return false; } private final Component name; private Entry(MobEffect effect) { this.effect = effect;
/*     */       this.name = (Component)effect.m_19482_().m_6881_(); } public Component m_142172_() { return this.name; } public void m_6311_(GuiGraphics stack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean selected, float partialTicks) { MobEffectTextureManager textureManager = SuperBeaconScreen.EffectList.this.f_93386_.m_91306_();
/*     */       TextureAtlasSprite sprite = textureManager.m_118732_(this.effect);
/*     */       stack.m_280246_(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       stack.m_280159_(left, top + height / 2 - 9, 0, 18, 18, sprite);
/*     */       Objects.requireNonNull(SuperBeaconScreen.EffectList.this.f_93386_.f_91062_);
/*     */       stack.m_280430_(SuperBeaconScreen.EffectList.this.f_93386_.f_91062_, this.name, left + 28, top + height / 2 - 9 / 2, -1); } public MobEffect getEffect() {
/*     */       return this.effect;
/*     */     } }
/*     */    private static class BeaconButton extends Button
/*     */   {
/*     */     public int overlayTexY; public BeaconButton(Button.Builder builder, int overlayTexY) {
/* 323 */       super(builder);
/* 324 */       this.overlayTexY = overlayTexY;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void m_87963_(GuiGraphics stack, int mouseX, int mouseY, float partialTikcs) {
/* 330 */       int i = 0;
/* 331 */       if (m_198029_())
/* 332 */         i += 22; 
/* 333 */       if (!m_142518_())
/* 334 */         i = 44; 
/* 335 */       RenderSystem.enableDepthTest();
/* 336 */       stack.m_280163_(SuperBeaconScreen.BUTTONS, m_252754_(), m_252907_(), 0.0F, i, this.f_93618_, this.f_93619_, 255, 255);
/* 337 */       stack.m_280163_(SuperBeaconScreen.BUTTONS, m_252754_(), m_252907_(), 0.0F, this.overlayTexY, this.f_93618_, this.f_93619_, 255, 255);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\menu\SuperBeaconScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */