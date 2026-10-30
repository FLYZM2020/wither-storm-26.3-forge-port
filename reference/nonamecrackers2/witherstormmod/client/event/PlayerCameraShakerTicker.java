/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.client.capability.PlayerCameraShaker;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*    */ 
/*    */ 
/*    */ public class PlayerCameraShakerTicker
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 17 */     if (event.phase == TickEvent.Phase.START) {
/*    */       
/* 19 */       Minecraft mc = Minecraft.m_91087_();
/* 20 */       ClientLevel world = mc.f_91073_;
/* 21 */       if (world != null && !mc.m_91104_())
/*    */       {
/* 23 */         for (Entity entity : world.m_104735_()) {
/*    */           
/* 25 */           if (entity instanceof LocalPlayer) {
/*    */             
/* 27 */             LocalPlayer player = (LocalPlayer)entity;
/* 28 */             player.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> shaker.tick());
/*    */           } 
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\PlayerCameraShakerTicker.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */