/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.network.chat.ClickEvent;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.Style;
/*    */ import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
/*    */ import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModGuiEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onRenderOverlay(CustomizeGuiOverlayEvent.DebugText event) {
/* 23 */     Minecraft mc = Minecraft.m_91087_();
/* 24 */     if (mc.f_91066_.f_92063_) {
/*    */       
/* 26 */       List<String> text = event.getRight();
/* 27 */       text.add("");
/* 28 */       text.add("witherstormmod: " + WitherStormMod.getVersion());
/* 29 */       text.add("Buffered Instances: " + RenderBufferer.INSTANCE.getTotalInstances());
/* 30 */       boolean flag = RenderBufferer.shouldUse();
/* 31 */       String s = String.valueOf(flag);
/* 32 */       if (flag) {
/* 33 */         s = "" + ChatFormatting.GREEN + ChatFormatting.GREEN;
/*    */       } else {
/* 35 */         s = "" + ChatFormatting.RED + ChatFormatting.RED;
/* 36 */       }  text.add("Render Bufferer Active: " + s);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerJoinLevel(ClientPlayerNetworkEvent.LoggingIn event) {
/* 43 */     if (CompatHelper.isOptifineLoaded() && ((Boolean)WitherStormModConfig.CLIENT.optifineWarning.get()).booleanValue()) {
/*    */       
/* 45 */       Minecraft mc = Minecraft.m_91087_();
/* 46 */       ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/nonamecrackers2/crackers-wither-storm-mod/wiki/Known-Compatibility-Issues");
/* 47 */       mc.f_91065_.m_93076_().m_93785_((Component)Component.m_237115_("chat.witherstormmod.optifine.notice").m_130948_(Style.f_131099_.m_131157_(ChatFormatting.RED).m_131142_(clickEvent)));
/* 48 */       WitherStormModConfig.CLIENT.optifineWarning.set(Boolean.valueOf(false));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\WitherStormModGuiEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */