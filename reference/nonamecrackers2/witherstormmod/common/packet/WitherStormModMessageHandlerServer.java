/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.AbstractSuperBeaconMenu;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class WitherStormModMessageHandlerServer
/*    */ {
/* 21 */   public static final Logger LOGGER = LogManager.getLogger();
/*    */ 
/*    */   
/*    */   public static void processInjureHeadMessage(InjureHeadMessage message, ServerPlayer player) {
/* 25 */     ServerLevel world = player.m_284548_();
/* 26 */     double pickRange = player.m_21051_((Attribute)ForgeMod.BLOCK_REACH.get()).m_22135_();
/* 27 */     Vec3 pos = player.m_20299_(1.0F);
/* 28 */     Vec3 eye = player.m_20252_(1.0F);
/* 29 */     Vec3 reach = pos.m_82520_(eye.f_82479_ * pickRange, eye.f_82480_ * pickRange, eye.f_82481_ * pickRange);
/* 30 */     Entity entity = world.m_6815_(message.getEntityID());
/* 31 */     int headIndex = message.getHead();
/* 32 */     if (entity instanceof WitherStormEntity) {
/*    */       
/* 34 */       WitherStormEntity storm = (WitherStormEntity)entity;
/* 35 */       WitherStormHead head = storm.getHeadManager().getHead(headIndex);
/* 36 */       if (storm.tractorBeamActive(headIndex))
/*    */       {
/* 38 */         if (WorldUtil.checkForIntersect(head.getBoundingBox(), pos, reach))
/*    */         {
/* 40 */           if (head.getHeadInjureAttemptCooldown() <= 0 && head.getHeadInjuryTicks() <= 0)
/*    */           {
/* 42 */             head.setHeadInjureAttemptCooldown(20);
/* 43 */             if (head.checkAndCountAttack())
/* 44 */               head.hurt((Entity)player, storm.getHeadManager().getHeadInjuryTime()); 
/* 45 */             player.m_6330_(SoundEvents.f_12313_, player.m_5720_(), 1.0F, 1.0F);
/*    */           }
/*    */           else
/*    */           {
/* 49 */             player.m_6330_(SoundEvents.f_12318_, player.m_5720_(), 1.0F, 1.0F);
/*    */           }
/*    */         
/*    */         }
/*    */       }
/*    */     } else {
/*    */       
/* 56 */       LOGGER.warn("Received entity " + entity + " that is not an instance of WitherStormEntity");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void processSuperBeaconSetEffectMessage(SuperBeaconSetEffectMessage message, ServerPlayer player) {
/* 62 */     AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof AbstractSuperBeaconMenu) { AbstractSuperBeaconMenu menu = (AbstractSuperBeaconMenu)abstractContainerMenu;
/*    */       
/* 64 */       MobEffect effect = MobEffect.m_19453_(message.getEffectId());
/* 65 */       if (menu.getCooldown() == 0 || effect == null) {
/*    */         
/* 67 */         if (effect != null && effect != menu.getPrimaryEffect()) {
/*    */           
/* 69 */           menu.doPowerUp(player);
/* 70 */           menu.activateCooldown(200);
/*    */         } 
/* 72 */         menu.updateEffects(message.getEffectId());
/*    */       }  }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public static void processSuperBeaconToggleAreaMessage(SuperBeaconToggleAreaMessage message, ServerPlayer player) {
/* 79 */     AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof AbstractSuperBeaconMenu) { AbstractSuperBeaconMenu menu = (AbstractSuperBeaconMenu)abstractContainerMenu;
/* 80 */       menu.setShowArea(message.shouldShowArea()); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\WitherStormModMessageHandlerServer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */