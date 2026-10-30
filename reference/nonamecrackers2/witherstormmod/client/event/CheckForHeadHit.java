/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.GameType;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*    */ import nonamecrackers2.witherstormmod.common.packet.InjureHeadMessage;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheckForHeadHit
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void blockLeftHit(PlayerInteractEvent.LeftClickEmpty event) {
/* 28 */     checkForHeadHit(event.getLevel(), event.getEntity(), event.getHand());
/*    */   }
/*    */ 
/*    */   
/*    */   public static void playerAttack(AttackEntityEvent event) {
/* 33 */     checkForHeadHit(event.getEntity().m_9236_(), event.getEntity(), InteractionHand.MAIN_HAND);
/*    */   }
/*    */ 
/*    */   
/*    */   private static void checkForHeadHit(Level level, Player player, InteractionHand hand) {
/* 38 */     if (level.f_46443_) {
/*    */       
/* 40 */       ClientLevel clientWorld = (ClientLevel)level;
/* 41 */       LocalPlayer localPlayer = (LocalPlayer)player;
/* 42 */       Minecraft mc = Minecraft.m_91087_();
/* 43 */       if (mc.f_91072_.m_105295_() != GameType.SPECTATOR) {
/*    */         
/* 45 */         List<WitherStormEntity> entities = clientWorld.m_45976_(WitherStormEntity.class, localPlayer.m_20191_().m_82400_(50.0D));
/* 46 */         for (WitherStormEntity storm : entities) {
/*    */           
/* 48 */           for (WitherStormHead head : storm.getHeadManager().getHeads()) {
/*    */             
/* 50 */             if (storm.tractorBeamActive(head.getIndex())) {
/*    */               
/* 52 */               Vec3 pos = localPlayer.m_20299_(1.0F);
/* 53 */               Vec3 eye = localPlayer.m_20252_(1.0F);
/* 54 */               float pickRange = mc.f_91072_.m_105286_();
/* 55 */               Vec3 reach = pos.m_82520_(eye.f_82479_ * pickRange, eye.f_82480_ * pickRange, eye.f_82481_ * pickRange);
/* 56 */               if (WorldUtil.checkForIntersect(head.getBoundingBox(), pos, reach)) {
/*    */                 
/* 58 */                 if (head.getHeadInjureAttemptCooldown() <= 0 && head.getHeadInjuryTicks() <= 0 && !storm.isDeadOrPlayingDead()) {
/*    */                   
/* 60 */                   InjureHeadMessage message = new InjureHeadMessage(storm, head.getIndex(), hand);
/* 61 */                   WitherStormModPacketHandlers.MAIN.sendToServer(message);
/*    */                   
/*    */                   continue;
/*    */                 } 
/* 65 */                 localPlayer.m_6330_(SoundEvents.f_12318_, localPlayer.m_5720_(), 1.0F, 1.0F);
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\CheckForHeadHit.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */