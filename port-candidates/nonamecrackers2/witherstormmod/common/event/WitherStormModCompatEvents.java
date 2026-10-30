/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ 
/*    */ public class WitherStormModCompatEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
/* 16 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/*    */       
/* 18 */       ServerLevel level = serverPlayer.serverLevel();
/* 19 */       if (((Boolean)WitherStormModConfig.SERVER.flyingDisabledWarning.get()).booleanValue() && !level.m_7654_().m_129915_() && serverPlayer.hasPermissions(2)) {
/*    */         
/* 21 */         serverPlayer.sendSystemMessage((Component)Component.translatable("chat.witherstormmod.flyingDisabled.notice").withStyle(ChatFormatting.RED));
/* 22 */         WitherStormModConfig.SERVER.flyingDisabledWarning.set(Boolean.valueOf(false));
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherStormModCompatEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */