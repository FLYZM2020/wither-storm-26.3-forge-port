/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.packet.CreateDebrisMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.CreateLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveStormFromDistantRendererMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.WitherStormToDistantRendererMessage;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormSyncHelper
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
/*  26 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/*  27 */       sendWitherStormsToPlayer(serverPlayer); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerChangeDimensions(PlayerEvent.PlayerChangedDimensionEvent event) {
/*  37 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/*  38 */       sendWitherStormsToPlayer(serverPlayer); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
/*  47 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/*  48 */       sendWitherStormsToPlayer(serverPlayer); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
/*  58 */     Entity target = event.getTarget();
/*  59 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player; if (target instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)target;
/*     */         
/*  61 */         PacketDistributor.PacketTarget packetTarget = PacketDistributor.PLAYER.with(() -> player);
/*  62 */         storm.getPlayDeadManager().sendChanges(packetTarget, false);
/*  63 */         WitherStormModPacketHandlers.MAIN.send(packetTarget, new CreateDebrisMessage(storm, storm.isDeadOrPlayingDead())); }
/*     */        }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void sendWitherStormsToPlayer(ServerPlayer player) {
/*  74 */     WorldUtil.getAllStorms(player.m_284548_()).forEach(storm -> sendWitherStormToClient(PacketDistributor.PLAYER.with(()), storm));
/*     */   }
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
/*     */   public static void sendWitherStormToClient(PacketDistributor.PacketTarget target, WitherStormEntity storm) {
/*  87 */     WitherStormModPacketHandlers.MAIN.send(target, new WitherStormToDistantRendererMessage(WorldUtil.getStormIds(storm), storm));
/*  88 */     if (storm.shouldPlaySoundLoops()) {
/*     */       
/*  90 */       CreateLoopingSoundMessage createLoopingSoundMessage = new CreateLoopingSoundMessage(storm);
/*  91 */       WitherStormModPacketHandlers.MAIN.send(target, createLoopingSoundMessage);
/*     */     } 
/*  93 */     storm.getPlayDeadManager().sendChanges(target, false);
/*  94 */     CreateDebrisMessage message = new CreateDebrisMessage(storm, storm.isDeadOrPlayingDead());
/*  95 */     WitherStormModPacketHandlers.MAIN.send(target, message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendWitherStormToClient(WitherStormEntity storm) {
/* 103 */     Objects.requireNonNull(storm.m_9236_()); sendWitherStormToClient(PacketDistributor.DIMENSION.with(storm.m_9236_()::m_46472_), storm);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeWitherStorm(PacketDistributor.PacketTarget target, WitherStormEntity storm) {
/* 114 */     WitherStormModPacketHandlers.MAIN.send(target, new RemoveStormFromDistantRendererMessage(WorldUtil.getStormIds(storm), storm));
/* 115 */     if (storm.shouldPlaySoundLoop) {
/*     */       
/* 117 */       RemoveSoundLoopMessage message = new RemoveSoundLoopMessage(storm);
/* 118 */       WitherStormModPacketHandlers.MAIN.send(target, message);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeWitherStorm(WitherStormEntity storm) {
/* 129 */     Objects.requireNonNull(storm.m_9236_()); removeWitherStorm(PacketDistributor.DIMENSION.with(storm.m_9236_()::m_46472_), storm);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\WitherStormSyncHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */