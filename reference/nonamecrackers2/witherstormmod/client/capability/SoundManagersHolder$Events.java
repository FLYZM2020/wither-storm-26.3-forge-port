/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Events
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 34 */     Minecraft mc = Minecraft.m_91087_();
/* 35 */     if (event.phase == TickEvent.Phase.START) {
/*    */       
/* 37 */       ClientLevel world = mc.f_91073_;
/* 38 */       if (world != null && !mc.m_91104_())
/*    */       {
/* 40 */         world.getCapability(WitherStormModClientCapabilities.SOUND_MANAGERS).ifPresent(holder -> {
/*    */               for (ISoundManager manager : holder.getManagers())
/*    */                 manager.tick(); 
/*    */             });
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\SoundManagersHolder$Events.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */