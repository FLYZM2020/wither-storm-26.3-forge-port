/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*    */ 
/*    */ public class ClientWitherSicknessEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 16 */     if (event.phase == TickEvent.Phase.END) {
/*    */       
/* 18 */       Minecraft mc = Minecraft.m_91087_();
/* 19 */       ClientLevel world = mc.f_91073_;
/* 20 */       if (world != null)
/*    */       {
/* 22 */         if (!mc.m_91104_())
/*    */         {
/* 24 */           for (Entity entity : world.m_104735_()) {
/*    */             
/* 26 */             if (entity instanceof LivingEntity) {
/*    */               
/* 28 */               LivingEntity living = (LivingEntity)entity;
/* 29 */               living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> tracker.tick());
/*    */             } 
/*    */           } 
/*    */         }
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\ClientWitherSicknessEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */