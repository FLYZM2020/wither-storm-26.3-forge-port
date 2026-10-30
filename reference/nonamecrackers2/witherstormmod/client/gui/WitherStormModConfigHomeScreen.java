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
/*    */   protected void m_7856_() {
/* 29 */     super.m_7856_();
/*    */     
/* 31 */     ((Button)m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237115_("gui.witherstormmod.button.refreshSounds.title"), button -> SoundManagersRefresher.INSTANCE.refresh())
/* 32 */         .m_252794_(5, 5)
/* 33 */         .m_253046_(20, 20)
/* 34 */         .m_257505_(Tooltip.m_257550_((Component)Component.m_237115_("gui.witherstormmod.button.refreshSounds.title")))
/* 35 */         .build(nonamecrackers2.witherstormmod.client.gui.widget.RefreshSoundsButton::new))).f_93623_ = (this.f_96541_.f_91073_ != null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\WitherStormModConfigHomeScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */