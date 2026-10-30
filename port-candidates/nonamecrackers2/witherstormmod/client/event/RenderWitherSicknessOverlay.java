/*     */ package nonamecrackers2.witherstormmod.client.event;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraftforge.client.event.RenderGuiOverlayEvent;
/*     */ import net.minecraftforge.client.gui.overlay.ForgeGui;
/*     */ import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
/*     */ import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
/*     */ 
/*     */ 
/*     */ public class RenderWitherSicknessOverlay
/*     */ {
/*  23 */   private static final Identifier WITHER_SICKNESS_ICONS = Identifier.fromNamespaceAndPath("witherstormmod", "textures/gui/wither_sickness.png");
/*     */   
/*  25 */   private final RandomSource random = RandomSource.create();
/*     */   
/*     */   private int lastHealth;
/*     */   private int displayHealth;
/*     */   private long lastHealthTime;
/*     */   private long healthBlinkTime;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void renderOverlay(RenderGuiOverlayEvent.Pre event) {
/*  34 */     Minecraft mc = Minecraft.getInstance();
/*  35 */     ForgeGui gui = (ForgeGui)mc.gui;
/*  36 */     if (event.getOverlay() == VanillaGuiOverlay.PLAYER_HEALTH.type() && !mc.options.hideGui && gui.shouldDrawSurvivalElements()) {
/*     */       
/*  38 */       LocalPlayer player = mc.player;
/*  39 */       if (player.hasEffect((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get())) {
/*     */         
/*  41 */         gui.setupOverlayRenderState(true, false);
/*  42 */         int width = event.getWindow().getGuiScaledWidth();
/*  43 */         int height = event.getWindow().getGuiScaledHeight();
/*  44 */         event.setCanceled(true);
/*  45 */         RenderSystem.enableBlend();
/*  46 */         int health = Mth.ceil(player.getHealth());
/*  47 */         int tickCount = gui.getGuiTicks();
/*  48 */         boolean highlight = (this.healthBlinkTime > tickCount && (this.healthBlinkTime - tickCount) / 3L % 2L == 1L);
/*     */         
/*  50 */         if (health < this.lastHealth && player.invulnerableTime > 0) {
/*     */           
/*  52 */           this.lastHealthTime = Util.getMillis();
/*  53 */           this.healthBlinkTime = (tickCount + 20);
/*     */         }
/*  55 */         else if (health > this.lastHealth && player.invulnerableTime > 0) {
/*     */           
/*  57 */           this.lastHealthTime = Util.getMillis();
/*  58 */           this.healthBlinkTime = (tickCount + 10);
/*     */         } 
/*     */         
/*  61 */         if (Util.getMillis() - this.lastHealthTime > 1000L) {
/*     */           
/*  63 */           this.lastHealth = health;
/*  64 */           this.displayHealth = health;
/*  65 */           this.lastHealthTime = Util.getMillis();
/*     */         } 
/*     */         
/*  68 */         this.lastHealth = health;
/*  69 */         int healthLast = this.displayHealth;
/*     */         
/*  71 */         AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
/*  72 */         float healthMax = (float)maxHealth.getValue();
/*  73 */         int absorbtion = Mth.ceil(player.getAbsorptionAmount());
/*     */         
/*  75 */         int healthRows = Mth.ceil((healthMax + absorbtion) / 2.0F / 10.0F);
/*  76 */         int rowHeight = Math.max(10 - healthRows - 2, 3);
/*     */         
/*  78 */         this.random.setSeed((tickCount * 312871));
/*     */         
/*  80 */         int left = width / 2 - 91;
/*  81 */         int top = height - gui.leftHeight;
/*  82 */         gui.leftHeight += healthRows * rowHeight;
/*  83 */         if (rowHeight != 10) {
/*  84 */           gui.leftHeight += 10 - rowHeight;
/*     */         }
/*  86 */         int regen = -1;
/*  87 */         if (player.hasEffect(MobEffects.REGENERATION)) {
/*  88 */           regen = tickCount % Mth.ceil(healthMax + 5.0F);
/*     */         }
/*  90 */         int TOP = player.level().getLevelData().isHardcore() ? 9 : 0;
/*  91 */         int BACKGROUND = highlight ? 25 : 16;
/*  92 */         int margin = 34;
/*  93 */         float absorbtionRemaining = absorbtion;
/*     */         
/*  95 */         for (int i = Mth.ceil((healthMax + absorbtion) / 2.0F) - 1; i >= 0; i--) {
/*     */           
/*  97 */           int row = Mth.ceil((i + 1) / 10.0F) - 1;
/*  98 */           int x = left + i % 10 * 8;
/*  99 */           int y = top - row * rowHeight;
/*     */           
/* 101 */           if (health <= 4)
/* 102 */             y += this.random.nextInt(2); 
/* 103 */           if (i == regen) {
/* 104 */             y -= 2;
/*     */           }
/* 106 */           event.getGuiGraphics().blit(WITHER_SICKNESS_ICONS, x, y, BACKGROUND, TOP, 9, 9);
/*     */           
/* 108 */           if (highlight)
/*     */           {
/* 110 */             if (i * 2 + 1 < healthLast) {
/* 111 */               event.getGuiGraphics().blit(WITHER_SICKNESS_ICONS, x, y, margin, TOP, 9, 9);
/* 112 */             } else if (i * 2 + 1 == healthLast) {
/* 113 */               event.getGuiGraphics().blit(WITHER_SICKNESS_ICONS, x, y, margin + 9, TOP, 9, 9);
/*     */             } 
/*     */           }
/* 116 */           if (absorbtionRemaining > 0.0F) {
/*     */             
/* 118 */             if (absorbtionRemaining == absorbtion && absorbtion % 2.0F == 1.0F)
/*     */             {
/* 120 */               event.getGuiGraphics().blit(WITHER_SICKNESS_ICONS, x, y, margin + 9, TOP, 9, 9);
/* 121 */               absorbtionRemaining--;
/*     */             }
/*     */             else
/*     */             {
/* 125 */               event.getGuiGraphics().blit(WITHER_SICKNESS_ICONS, x, y, margin, TOP, 9, 9);
/* 126 */               absorbtionRemaining -= 2.0F;
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 131 */           else if (i * 2 + 1 < health) {
/* 132 */             event.getGuiGraphics().blit(WITHER_SICKNESS_ICONS, x, y, margin, TOP, 9, 9);
/* 133 */           } else if (i * 2 + 1 == health) {
/* 134 */             event.getGuiGraphics().blit(WITHER_SICKNESS_ICONS, x, y, margin + 9, TOP, 9, 9);
/*     */           } 
/*     */         } 
/*     */         
/* 138 */         RenderSystem.disableBlend();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\RenderWitherSicknessOverlay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */