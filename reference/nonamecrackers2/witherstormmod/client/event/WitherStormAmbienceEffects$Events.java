/*     */ package nonamecrackers2.witherstormmod.client.event;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraftforge.client.event.ViewportEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Events
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onWorldTick(TickEvent.ClientTickEvent event) {
/* 175 */     if (event.phase == TickEvent.Phase.START) {
/*     */       
/* 177 */       Minecraft mc = Minecraft.m_91087_();
/* 178 */       ClientLevel level = mc.f_91073_;
/* 179 */       if (level != null && !mc.m_91104_()) {
/* 180 */         level.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).ifPresent(WitherStormAmbienceEffects::tick);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void fogColor(ViewportEvent.ComputeFogColor event) {
/* 187 */     if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
/*     */       
/* 189 */       Minecraft mc = Minecraft.m_91087_();
/* 190 */       mc.f_91073_.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).ifPresent(effects -> {
/*     */             float alpha = effects.lerpAlpha((float)event.getPartialTick());
/*     */             Color fog = effects.lerpColorsByTransition(SkyColorSet::fogColor, SkyColorSet::nightFogColor, (float)event.getPartialTick());
/*     */             int[] color = { fog.getRed(), fog.getGreen(), fog.getBlue() };
/*     */             float rDelta = event.getRed() * 255.0F - color[0];
/*     */             float gDelta = event.getGreen() * 255.0F - color[1];
/*     */             float bDelta = event.getBlue() * 255.0F - color[2];
/*     */             color[0] = (int)(color[0] + rDelta * alpha);
/*     */             color[1] = (int)(color[1] + gDelta * alpha);
/*     */             color[2] = (int)(color[2] + bDelta * alpha);
/*     */             event.setRed(color[0] / 255.0F);
/*     */             event.setGreen(color[1] / 255.0F);
/*     */             event.setBlue(color[2] / 255.0F);
/*     */           });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\WitherStormAmbienceEffects$Events.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */