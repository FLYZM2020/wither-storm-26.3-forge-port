/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ import java.nio.file.Path;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.client.gui.components.Tooltip;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.packs.PackResources;
/*    */ import net.minecraft.server.packs.PackType;
/*    */ import net.minecraft.server.packs.PathPackResources;
/*    */ import net.minecraft.server.packs.repository.Pack;
/*    */ import net.minecraft.server.packs.repository.PackSource;
/*    */ import net.minecraftforge.event.AddPackFindersEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.ModList;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ import net.minecraftforge.forgespi.locating.IModFile;
/*    */ import nonamecrackers2.crackerslib.client.event.impl.AddConfigEntryToMenuEvent;
/*    */ import nonamecrackers2.crackerslib.client.event.impl.ConfigMenuButtonEvent;
/*    */ import nonamecrackers2.crackerslib.client.event.impl.OnConfigScreenOpened;
/*    */ import nonamecrackers2.crackerslib.client.event.impl.RegisterConfigScreensEvent;
/*    */ import nonamecrackers2.crackerslib.client.gui.ConfigHomeScreen;
/*    */ import nonamecrackers2.crackerslib.client.gui.title.ImageTitle;
/*    */ import nonamecrackers2.crackerslib.client.gui.title.TitleLogo;
/*    */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ 
/*    */ public class WitherStormModClientConfigEvents {
/*    */   public static void registerConfigScreen(RegisterConfigScreensEvent event) {
/* 30 */     event.builder(ConfigHomeScreen.builder((TitleLogo)ImageTitle.ofMod("witherstormmod", 256, 128, 1.0F))
/* 31 */         .crackersDefault("https://github.com/nonamecrackers2/crackers-wither-storm-mod/issues")
/* 32 */         .addLinkButton(
/* 33 */           (Component)Component.m_237115_("gui.witherstormmod.screen.wsmoptions.nazaKofi").m_130940_(ChatFormatting.GREEN), "https://ko-fi.com/nazaru", 
/*    */           
/* 35 */           Tooltip.m_257550_((Component)Component.m_237115_("gui.witherstormmod.screen.wsmoptions.nazaKofi.info")))
/* 36 */         .build(nonamecrackers2.witherstormmod.client.gui.WitherStormModConfigHomeScreen::new))
/* 37 */       .addSpec(ModConfig.Type.CLIENT, WitherStormModConfig.CLIENT_SPEC).addSpec(ModConfig.Type.COMMON, WitherStormModConfig.COMMON_SPEC).addSpec(ModConfig.Type.SERVER, WitherStormModConfig.SERVER_SPEC).register();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void registerConfigMenuButton(ConfigMenuButtonEvent event) {
/* 42 */     event.defaultButtonWithSingleCharacter('W', 7143633);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onConfigMenuOpened(OnConfigScreenOpened event) {
/* 48 */     if (event.getModId().equals("witherstormmod")) {
/* 49 */       event.setInitialPath(event.getType().extension());
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onOptionAddedToMenu(AddConfigEntryToMenuEvent event) {
/* 55 */     if (event.getModId().equals("witherstormmod"))
/*    */     {
/* 57 */       if (event.isValue(WitherStormModConfig.SERVER.flyingDisabledWarning)) {
/* 58 */         event.setCanceled(true);
/* 59 */       } else if (event.isValue(WitherStormModConfig.SERVER.shouldChunkLoadWhenNoPlayers)) {
/* 60 */         event.setCanceled(true);
/* 61 */       } else if (event.isValue(WitherStormModConfig.CLIENT.optifineWarning)) {
/* 62 */         event.setCanceled(!CompatHelper.isOptifineLoaded());
/* 63 */       } else if (event.isValue(WitherStormModConfig.CLIENT.aprilFools)) {
/* 64 */         event.setCanceled(!WitherStormMod.isAprilFools());
/* 65 */       } else if (event.isValue(WitherStormModConfig.CLIENT.patronCosmetic)) {
/* 66 */         event.setCanceled(!Contributors.currentPlayerHasCosmetic());
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public static void addPackFindersEvent(AddPackFindersEvent event) {
/* 72 */     if (event.getPackType() == PackType.CLIENT_RESOURCES)
/*    */     {
/* 74 */       event.addRepositorySource(consumer -> consumer.accept(Pack.m_245429_("witherstormmod:programmer_art", (Component)Component.m_237115_("witherstormmod.resourcepacks.programmer_art"), false, (), event.getPackType(), Pack.Position.TOP, PackSource.f_10528_)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\WitherStormModClientConfigEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */