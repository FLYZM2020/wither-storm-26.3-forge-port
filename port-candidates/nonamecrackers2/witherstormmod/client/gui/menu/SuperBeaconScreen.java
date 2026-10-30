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
/*     */ import net.minecraft.resources.Identifier;
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
/*  33 */   private static final Identifier BORDER = Identifier.fromNamespaceAndPath("witherstormmod", "textures/gui/container/super_beacon.png");
/*  34 */   private static final Identifier WINDOW = Identifier.fromNamespaceAndPath("witherstormmod", "textures/gui/container/super_beacon_window.png");
/*  35 */   private static final Identifier BUTTONS = Identifier.fromNamespaceAndPath("witherstormmod", "textures/gui/container/super_beacon_buttons.png");
/*  36 */   private static final Component SELECTED_EFFECT = (Component)Component.translatable("container.witherstormmod.withered_beacon.selected");
/*  37 */   private static final Component AVAILABLE_EFFECTS = (Component)Component.translatable("container.witherstormmod.withered_beacon.available_effects");
/*  38 */   private static final Component INFO = (Component)Component.translatable("withered_beacon.info"); private static final int WINDOW_X = 10; private static final int WINDOW_Y = 9; private static final int WINDOW_WIDTH = 210; private static final int WINDOW_HEIGHT = 116; private static final int DIVIDED_WINDOW_WIDTH = 105;
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
/*  61 */     this.imageWidth = 230;
/*  62 */     this.imageHeight = 157;
/*  63 */     this.effectList = new EffectList(Minecraft.getInstance(), 105, 116, 9, 125);
/*  64 */     this.effectList.setLeftPos(115);
/*  65 */     menu.addSlotListener(new ContainerListener()
/*     */         {
/*     */           public void slotChanged(AbstractContainerMenu container, int slot, ItemStack stack) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void dataChanged(AbstractContainerMenu container, int slot, int value) {
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
/*     */   protected void init() {
/*  84 */     super.init();
/*     */     
/*  86 */     int width = (this.width - this.imageWidth) / 2;
/*  87 */     int height = (this.height - this.imageHeight) / 2;
/*  88 */     this.effectList.updateSize(105, 116, height + 9 + 24, height + 9 + 116 - 4);
/*  89 */     this.effectList.setLeftPos(width + 10 + 105 + 1);
/*  90 */     addRenderableWidget((GuiEventListener)this.effectList);
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
/* 104 */       .select = (BeaconButton)Button.builder((Component)Component.empty(), button -> { MobEffect effect = getSelectedEffect(); if (effect != null) { WitherStormModPacketHandlers.MAIN.sendToServer(new SuperBeaconSetEffectMessage(MobEffect.getId(effect))); this.minecraft.player.closeContainer(); }  }).pos(buttonMiddle - 2 - 22, buttonY).size(22, 22).build(builder -> new BeaconButton(builder, 88));
/*     */     
/* 106 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       .unselect = (BeaconButton)Button.builder((Component)Component.empty(), button -> { if (!this.shouldRenderInfo) WitherStormModPacketHandlers.MAIN.sendToServer(new SuperBeaconSetEffectMessage(MobEffect.getId(null)));  }).pos(buttonMiddle + 2, buttonY).size(22, 22).build(builder -> new BeaconButton(builder, 110));
/*     */ 
/*     */     
/* 115 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 121 */       .info = Button.builder((Component)Component.literal("i"), button -> { this.shouldRenderInfo = true; addWidget((GuiEventListener)this.exitInfo); }).pos(width + 10, buttonY).size(20, 20).build();
/*     */ 
/*     */     
/* 124 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       .exitInfo = Button.builder((Component)Component.literal("X").withStyle(ChatFormatting.RED), button -> { this.shouldRenderInfo = false; removeWidget((GuiEventListener)button); }).pos(width - 20, height - 30).size(20, 20).build();
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
/* 141 */       .showArea = (BeaconButton)Button.builder((Component)Component.empty(), button -> { WitherStormModPacketHandlers.MAIN.sendToServer(new SuperBeaconToggleAreaMessage(!this.shouldShowArea)); this.minecraft.player.closeContainer(); }).pos(width + this.imageWidth - 10 - 22, buttonY).size(22, 22).tooltip(Tooltip.create((Component)Component.translatable("gui.witherstormmod.button.showArea.description"))).build(builder -> new BeaconButton(builder, 132));
/*     */     
/* 143 */     if (this.shouldRenderInfo)
/* 144 */       addWidget((GuiEventListener)this.exitInfo); 
/* 145 */     this.exitInfo.setFGColor(16711680);
/* 146 */     addWidget((GuiEventListener)this.select);
/* 147 */     addWidget((GuiEventListener)this.unselect);
/* 148 */     addWidget((GuiEventListener)this.info);
/* 149 */     addWidget((GuiEventListener)this.showArea);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
/* 155 */     renderBackground(stack);
/* 156 */     super.render(stack, mouseX, mouseY, partialTicks);
/* 157 */     MobEffect effect = getSelectedEffect();
/* 158 */     if (this.setEffectCooldown > 0) {
/* 159 */       this.select.setTooltip(Tooltip.create((Component)Component.translatable("gui.witherstormmod.button.select.cooldown.description")));
/*     */     } else {
/* 161 */       this.select.setTooltip(null);
/* 162 */     }  this.select.active = (!this.shouldRenderInfo && effect != null && effect != this.primary && this.setEffectCooldown == 0);
/* 163 */     this.unselect.active = (!this.shouldRenderInfo && this.primary != null);
/* 164 */     this.info.active = !this.shouldRenderInfo;
/* 165 */     this.showArea.active = !this.shouldRenderInfo;
/* 166 */     this.showArea.overlayTexY = 22 * (this.shouldShowArea ? 6 : 7);
/* 167 */     this.showArea.render(stack, mouseX, mouseY, partialTicks);
/* 168 */     this.select.render(stack, mouseX, mouseY, partialTicks);
/* 169 */     this.unselect.render(stack, mouseX, mouseY, partialTicks);
/* 170 */     this.info.render(stack, mouseX, mouseY, partialTicks);
/* 171 */     if (this.shouldRenderInfo) {
/*     */       
/* 173 */       stack.pose().pushPose();
/* 174 */       stack.pose().translate(0.0D, 0.0D, 1.0D);
/* 175 */       renderBackground(stack);
/* 176 */       this.exitInfo.render(stack, mouseX, mouseY, partialTicks);
/* 177 */       int textX = (this.width - this.imageWidth) / 2 - 20;
/* 178 */       int textWidth = this.imageWidth + 40;
/* 179 */       int textHeight = this.font.wordWrapHeight(INFO.getString(), textWidth);
/* 180 */       int textY = (this.height - this.imageHeight) / 2 + this.imageHeight / 2 - textHeight / 2;
/* 181 */       stack.pose().translate(0.0D, 0.0D, 1.0D);
/* 182 */       List<FormattedCharSequence> text = this.font.split((FormattedText)INFO, textWidth);
/* 183 */       for (int i = 0; i < text.size(); i++) {
/* 184 */         Objects.requireNonNull(this.font); stack.drawString(this.font, text.get(i), textX, i * 9 + textY, -1);
/* 185 */       }  stack.pose().popPose();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderBg(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
/* 192 */     drawMenu(stack, WINDOW);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderLabels(GuiGraphics stack, int mouseX, int mouseY) {
/* 198 */     stack.blit(BORDER, 0, 0, 0, 0, this.imageWidth, this.imageHeight);
/* 199 */     stack.blit(BORDER, 116, 13, 0, this.imageHeight, 105, 20);
/* 200 */     stack.drawCenteredString(this.font, SELECTED_EFFECT, 62, 19, -1);
/* 201 */     stack.drawCenteredString(this.font, AVAILABLE_EFFECTS, 167, 19, -1);
/* 202 */     Objects.requireNonNull(this.font); stack.drawString(this.font, (Component)Component.translatable("container.witherstormmod.withered_beacon.level", new Object[] { (this.level > 0) ? Component.translatable("enchantment.level." + this.level) : "" }), 20, 115 - 9, -1);
/* 203 */     if (this.primary != null) {
/*     */       
/* 205 */       MobEffectTextureManager textureManager = this.minecraft.getMobEffectTextures();
/* 206 */       TextureAtlasSprite sprite = textureManager.get(this.primary);
/* 207 */       int x = 62 - (int)(sprite.contents().width() * 1.5F);
/* 208 */       int y = 67 - (int)(sprite.contents().height() * 1.5F) - 8;
/* 209 */       stack.blit(x, y, 0, 54, 54, sprite);
/* 210 */       stack.drawCenteredString(this.font, this.primary.getDisplayName(), 62, y + sprite.contents().width() * 3, -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawMenu(GuiGraphics stack, Identifier tex) {
/* 216 */     stack.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 217 */     int width = (this.width - this.imageWidth) / 2;
/* 218 */     int height = (this.height - this.imageHeight) / 2;
/* 219 */     stack.blit(tex, width, height, 0, 0, this.imageWidth, this.imageHeight);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private MobEffect getSelectedEffect() {
/* 224 */     EffectList.Entry entry = (EffectList.Entry)this.effectList.getSelected();
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
/* 243 */       setRenderBackground(false);
/* 244 */       setRenderTopAndBottom(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public void addEffect(MobEffect effect) {
/* 249 */       addEntry((AbstractSelectionList.Entry)new Entry(effect));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getRowWidth() {
/* 255 */       return getWidth();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int getScrollbarPosition() {
/* 261 */       return this.x0 + getWidth() - 7;
/*     */     }
/*     */ 
/*     */     
/*     */     public void clear() {
/* 266 */       clearEntries();
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
/* 277 */         this.name = (Component)effect.getDisplayName().copy();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Component getNarration() {
/* 283 */         return this.name;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void render(GuiGraphics stack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean selected, float partialTicks) {
/* 289 */         MobEffectTextureManager textureManager = SuperBeaconScreen.EffectList.this.minecraft.getMobEffectTextures();
/* 290 */         TextureAtlasSprite sprite = textureManager.get(this.effect);
/* 291 */         stack.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 292 */         stack.blit(left, top + height / 2 - 9, 0, 18, 18, sprite);
/* 293 */         Objects.requireNonNull(SuperBeaconScreen.EffectList.this.minecraft.font); stack.drawString(SuperBeaconScreen.EffectList.this.minecraft.font, this.name, left + 28, top + height / 2 - 9 / 2, -1);
/*     */       }
/*     */ 
/*     */       
/*     */       public MobEffect getEffect() {
/* 298 */         return this.effect;
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean mouseClicked(double x, double y, int clickType)
/*     */       {
/* 304 */         if (clickType == 0) {
/*     */           
/* 306 */           SuperBeaconScreen.EffectList.this.setSelected((AbstractSelectionList.Entry)this);
/* 307 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 311 */         return false; } } } public class Entry extends ObjectSelectionList.Entry<EffectList.Entry> { private final MobEffect effect; public boolean mouseClicked(double x, double y, int clickType) { if (clickType == 0) { SuperBeaconScreen.EffectList.this.setSelected((AbstractSelectionList.Entry)this); return true; }  return false; } private final Component name; private Entry(MobEffect effect) { this.effect = effect;
/*     */       this.name = (Component)effect.getDisplayName().copy(); } public Component getNarration() { return this.name; } public void render(GuiGraphics stack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean selected, float partialTicks) { MobEffectTextureManager textureManager = SuperBeaconScreen.EffectList.this.minecraft.getMobEffectTextures();
/*     */       TextureAtlasSprite sprite = textureManager.get(this.effect);
/*     */       stack.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       stack.blit(left, top + height / 2 - 9, 0, 18, 18, sprite);
/*     */       Objects.requireNonNull(SuperBeaconScreen.EffectList.this.minecraft.font);
/*     */       stack.drawString(SuperBeaconScreen.EffectList.this.minecraft.font, this.name, left + 28, top + height / 2 - 9 / 2, -1); } public MobEffect getEffect() {
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
/*     */     public void renderWidget(GuiGraphics stack, int mouseX, int mouseY, float partialTikcs) {
/* 330 */       int i = 0;
/* 331 */       if (isHoveredOrFocused())
/* 332 */         i += 22; 
/* 333 */       if (!isActive())
/* 334 */         i = 44; 
/* 335 */       RenderSystem.enableDepthTest();
/* 336 */       stack.blit(SuperBeaconScreen.BUTTONS, getX(), getY(), 0.0F, i, this.width, this.height, 255, 255);
/* 337 */       stack.blit(SuperBeaconScreen.BUTTONS, getX(), getY(), 0.0F, this.overlayTexY, this.width, this.height, 255, 255);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\menu\SuperBeaconScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */