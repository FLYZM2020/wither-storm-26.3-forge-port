/*     */ package nonamecrackers2.witherstormmod.common.event;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.monster.Phantom;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.living.MobEffectEvent;
/*     */ import net.minecraftforge.event.entity.living.MobSpawnEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.common.accessor.LivingEntityAccessor;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateWitherSicknessTrackerMessage;
/*     */ 
/*     */ public class WitherSicknessEvents {
/*     */   @SubscribeEvent
/*     */   public static void onWorldTick(TickEvent.LevelTickEvent event) {
/*  38 */     Level level = event.level; if (level instanceof ServerLevel) { ServerLevel world = (ServerLevel)level;
/*     */       
/*  40 */       if (event.phase == TickEvent.Phase.START) {
/*     */         
/*  42 */         List<WitherStormEntity> storms = Lists.newArrayList();
/*  43 */         for (Entity entity : world.m_8583_()) {
/*     */           
/*  45 */           if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity; if (storm.getPhase() > 1)
/*  46 */               storms.add(storm);  }
/*     */         
/*  48 */         }  for (Iterator<Entity> iterator = world.m_8583_().iterator(); iterator.hasNext(); ) { Entity entity = iterator.next();
/*     */           
/*  50 */           if (entity instanceof LivingEntity) {
/*     */             
/*  52 */             LivingEntity living = (LivingEntity)entity;
/*  53 */             living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
/*     */                   if (!tracker.isActuallyImmune()) {
/*     */                     boolean nearby = false;
/*     */                     for (WitherStormEntity storm : storms) {
/*     */                       nearby = storm.isEntityNearby((Entity)living);
/*     */                       if (nearby) {
/*     */                         break;
/*     */                       }
/*     */                     } 
/*     */                     if (entity.m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
/*     */                       nearby = true;
/*     */                     }
/*     */                     tracker.setNearStorm(nearby);
/*     */                   } 
/*     */                   tracker.tick();
/*     */                 });
/*     */           }  }
/*     */       
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerClone(PlayerEvent.Clone event) {
/*  79 */     if (event.isWasDeath()) {
/*     */       
/*  81 */       Player original = event.getOriginal();
/*  82 */       Player player = event.getEntity();
/*  83 */       original.reviveCaps();
/*  84 */       LazyOptional<WitherSicknessTracker> optional = original.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER);
/*  85 */       if (optional.isPresent()) {
/*     */         
/*  87 */         WitherSicknessTracker oldTracker = optional.resolve().get();
/*  88 */         player.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
/*     */               tracker.copyFrom(oldTracker);
/*     */               
/*     */               if (((Boolean)WitherStormModConfig.SERVER.keepSicknessAfterRespawn.get()).booleanValue()) {
/*     */                 MobEffectInstance effect = original.m_21124_((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get());
/*     */                 
/*     */                 if (effect != null) {
/*     */                   player.m_7292_(effect);
/*     */                 }
/*     */               } else {
/*     */                 tracker.setInfected(false);
/*     */                 
/*     */                 tracker.setProximityTicks(0);
/*     */                 tracker.setContacts(0);
/*     */                 tracker.setContactDecreaseTicks(0);
/*     */               } 
/*     */             });
/*     */       } 
/* 106 */       original.invalidateCaps();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onCheckDespawn(MobSpawnEvent.AllowDespawn event) {
/* 113 */     Mob mob = event.getEntity();
/* 114 */     mob.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
/*     */           if (!tracker.isActuallyImmune() && (tracker.isInfected() || tracker.isBeingCured())) {
/*     */             event.setResult(Event.Result.DENY);
/*     */           }
/*     */         });
/*     */     
/* 120 */     if (mob.m_6095_().equals(EntityType.f_20509_) && ((Boolean)WitherStormModConfig.COMMON.phantomsOrbitWitherStorm.get()).booleanValue()) {
/*     */       
/* 122 */       List<Phantom> phantoms = mob.m_9236_().m_45976_(Phantom.class, mob.m_20191_().m_82400_(100.0D));
/* 123 */       List<WitherStormEntity> storms = mob.m_9236_().m_45976_(WitherStormEntity.class, mob.m_20191_().m_82400_(100.0D));
/* 124 */       if (phantoms.size() < 12 && storms.size() >= 1) {
/* 125 */         event.setResult(Event.Result.DENY);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
/* 132 */     UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage((Entity)event.getEntity());
/* 133 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), message);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerChangedDimensions(PlayerEvent.PlayerLoggedInEvent event) {
/* 139 */     UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage((Entity)event.getEntity());
/* 140 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), message);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
/* 146 */     UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage((Entity)event.getEntity());
/* 147 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), message);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
/* 153 */     UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage(event.getTarget());
/* 154 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), message);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onMobEffectRemove(MobEffectEvent.Remove event) {
/* 160 */     LivingEntity entity = event.getEntity();
/* 161 */     if (((LivingEntityAccessor)entity).hasDeathProtection() && event.getEffect() == WitherStormModEffects.WITHER_SICKNESS.get())
/* 162 */       event.setCanceled(true); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherSicknessEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */