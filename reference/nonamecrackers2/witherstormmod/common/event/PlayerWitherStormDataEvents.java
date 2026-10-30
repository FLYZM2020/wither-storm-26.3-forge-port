/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*    */ 
/*    */ 
/*    */ public class PlayerWitherStormDataEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
/* 16 */     if (event.phase == TickEvent.Phase.END) {
/*    */       
/* 18 */       Player player = event.player;
/* 19 */       player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.tick());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerClone(PlayerEvent.Clone event) {
/* 28 */     if (event.isWasDeath()) {
/*    */       
/* 30 */       Player original = event.getOriginal();
/* 31 */       Player player = event.getEntity();
/* 32 */       original.reviveCaps();
/* 33 */       LazyOptional<PlayerWitherStormData> optional = original.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA);
/* 34 */       if (optional.isPresent()) {
/*    */         
/* 36 */         PlayerWitherStormData oldData = optional.resolve().get();
/* 37 */         player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.copyFrom(oldData));
/*    */       } 
/*    */ 
/*    */       
/* 41 */       original.invalidateCaps();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
/* 48 */     event.getEntity().getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(manager -> manager.makeInvulnerable(600));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\PlayerWitherStormDataEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */