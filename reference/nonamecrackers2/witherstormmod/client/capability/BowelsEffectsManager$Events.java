/*     */ package nonamecrackers2.witherstormmod.client.capability;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.DimensionSpecialEffects;
/*     */ import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.util.BowelsSpecialEffects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static void tickAmbience(TickEvent.ClientTickEvent event) {
/*  96 */     Minecraft mc = Minecraft.m_91087_();
/*  97 */     if (event.phase == TickEvent.Phase.END) {
/*     */       
/*  99 */       ClientLevel world = mc.f_91073_;
/* 100 */       if (world != null && !mc.m_91104_())
/*     */       {
/* 102 */         world.getCapability(WitherStormModClientCapabilities.BOWELS_EFFECTS_MANAGER).ifPresent(manager -> manager.tick());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
/* 111 */     event.register(WitherStormMod.id("bowels"), (DimensionSpecialEffects)new BowelsSpecialEffects());
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\BowelsEffectsManager$Events.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */