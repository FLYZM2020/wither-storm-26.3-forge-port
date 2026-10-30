/*    */ package nonamecrackers2.witherstormmod.client.gui;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.gui.components.AbstractButton;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.client.gui.components.Tooltip;
/*    */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ import nonamecrackers2.crackerslib.client.gui.ConfigHomeScreen;
/*    */ import nonamecrackers2.crackerslib.client.gui.title.TitleLogo;
/*    */ import nonamecrackers2.witherstormmod.client.audio.SoundManagersRefresher;
/*    */ 
/*    */ public class WitherStormModConfigHomeScreen
/*    */   extends ConfigHomeScreen
/*    */ {
/*    */   public WitherStormModConfigHomeScreen(String modid, Map<ModConfig.Type, ForgeConfigSpec> specs, TitleLogo title, boolean isWorldLoaded, boolean hasSinglePlayerServer, Screen previous, List<Supplier<AbstractButton>> extraButtons, int totalColumns) {
/* 23 */     super(modid, specs, title, isWorldLoaded, hasSinglePlayerServer, previous, extraButtons, totalColumns);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void init() {
/* 29 */     super.init();
/*    */     
/* 31 */     ((Button)addRenderableWidget((GuiEventListener)Button.builder((Component)Component.translatable("gui.witherstormmod.button.refreshSounds.title"), button -> SoundManagersRefresher.INSTANCE.refresh())
/* 32 */         .pos(5, 5)
/* 33 */         .size(20, 20)
/* 34 */         .tooltip(Tooltip.create((Component)Component.translatable("gui.witherstormmod.button.refreshSounds.title")))
/* 35 */         .build(nonamecrackers2.witherstormmod.client.gui.widget.RefreshSoundsButton::new))).active = (this.minecraft.level != null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\WitherStormModConfigHomeScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */