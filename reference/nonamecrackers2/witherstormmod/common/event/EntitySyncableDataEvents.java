/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.network.PacketDistributor;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*    */ import nonamecrackers2.witherstormmod.common.packet.EntitySyncableDataMessage;
/*    */ import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
/*    */ 
/*    */ 
/*    */ public class EntitySyncableDataEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
/* 18 */     sendChanges((ServerPlayer)event.getEntity());
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onChangedDimensions(PlayerEvent.PlayerChangedDimensionEvent event) {
/* 24 */     sendChanges((ServerPlayer)event.getEntity());
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
/* 30 */     sendChanges((ServerPlayer)event.getEntity());
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
/* 36 */     sendChanges((ServerPlayer)event.getEntity(), event.getTarget());
/*    */   }
/*    */ 
/*    */   
/*    */   public static void sendChanges(ServerPlayer player) {
/* 41 */     ServerLevel world = (ServerLevel)player.m_9236_();
/* 42 */     for (Entity entity : world.m_8583_()) {
/* 43 */       sendChanges(player, entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void sendChanges(ServerPlayer player, Entity entity) {
/* 48 */     if (entity instanceof EntitySyncableData) {
/*    */       
/* 50 */       EntitySyncableDataMessage message = new EntitySyncableDataMessage(entity.m_19879_(), (EntitySyncableData)entity);
/* 51 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> player), message);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\EntitySyncableDataEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */