/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.capability.ChunkLoadingBlockEntities;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*    */ 
/*    */ public class ChunkLoadingBlockEntitiesEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onWorldTick(TickEvent.LevelTickEvent event) {
/* 12 */     event.level.getCapability(WitherStormModCapabilities.CHUNK_LOADING_BLOCK_ENTITIES).ifPresent(cap -> cap.tick());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\ChunkLoadingBlockEntitiesEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */