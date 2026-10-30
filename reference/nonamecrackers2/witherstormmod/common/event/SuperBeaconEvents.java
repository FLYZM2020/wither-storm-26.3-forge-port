/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraftforge.event.entity.player.PlayerContainerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.network.PacketDistributor;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.AbstractSuperBeaconMenu;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*    */ import nonamecrackers2.witherstormmod.common.packet.SuperBeaconValidEffectsMessage;
/*    */ 
/*    */ public class SuperBeaconEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerOpenContainer(PlayerContainerEvent.Open event) {
/* 16 */     AbstractContainerMenu abstractContainerMenu = event.getContainer(); if (abstractContainerMenu instanceof AbstractSuperBeaconMenu) { AbstractSuperBeaconMenu menu = (AbstractSuperBeaconMenu)abstractContainerMenu;
/* 17 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), new SuperBeaconValidEffectsMessage(menu.getValidEffects())); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\SuperBeaconEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */