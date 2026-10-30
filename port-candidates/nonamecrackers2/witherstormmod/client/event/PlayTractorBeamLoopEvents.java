/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.client.audio.TractorBeamLoop;
/*    */ import nonamecrackers2.witherstormmod.client.audio.WitherStormTractorBeamLoop;
/*    */ import nonamecrackers2.witherstormmod.client.capability.WitherStormLoopingSoundManager;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ 
/*    */ public class PlayTractorBeamLoopEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void clientTickEvent(TickEvent.ClientTickEvent event) {
/* 23 */     if (event.phase == TickEvent.Phase.START) {
/*    */       
/* 25 */       Minecraft mc = Minecraft.getInstance();
/* 26 */       ClientLevel clientWorld = mc.level;
/* 27 */       if (clientWorld != null)
/*    */       {
/* 29 */         if (!mc.isPaused())
/*    */         {
/* 31 */           for (Entity entity : clientWorld.entitiesForRendering()) {
/*    */             
/* 33 */             if (entity instanceof WitherStormEntity) {
/*    */               
/* 35 */               WitherStormEntity storm = (WitherStormEntity)entity;
/* 36 */               if (!storm.isDeadOrPlayingDead() && !storm.isSilent())
/*    */               {
/* 38 */                 for (WitherStormHead head : storm.getHeadManager().getHeads()) {
/*    */                   
/* 40 */                   LocalPlayer player = mc.player;
/* 41 */                   if (storm.tractorBeamActive(head.getIndex())) {
/*    */                     
/* 43 */                     Vec3 pos = TractorBeamHelper.calculateClosestPoint(player.position(), (LivingEntity)storm, head.getIndex());
/* 44 */                     double distance = Math.sqrt(player.distanceToSqr(pos));
/* 45 */                     WitherStormLoopingSoundManager manager = (WitherStormLoopingSoundManager)clientWorld.getCapability(WitherStormModClientCapabilities.LOOPING_MANAGER).orElse(null);
/* 46 */                     if (manager != null && !manager.alreadyHasLoop(storm.getId(), head.getIndex()) && distance <= TractorBeamLoop.DISTANCE_REQUIRED) {
/*    */                       
/* 48 */                       WitherStormTractorBeamLoop loop = new WitherStormTractorBeamLoop(storm, head.getIndex());
/* 49 */                       loop.setPos(pos);
/* 50 */                       manager.putBeamSound(storm.getId(), head.getIndex(), loop);
/*    */                     } 
/*    */                   } 
/*    */                 } 
/*    */               }
/*    */             } 
/*    */           } 
/*    */         }
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\PlayTractorBeamLoopEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */