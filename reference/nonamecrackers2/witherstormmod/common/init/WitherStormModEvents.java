/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherStormModChunkLoader;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.WitherStormSyncHelper;
/*    */ import nonamecrackers2.witherstormmod.common.event.AnvilRecipeEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.ChunkLoadingBlockEntitiesEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.EntityConversionEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.EntitySyncableDataEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.InjectCustomGoalsEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.PlayerWitherStormDataEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.SuperBeaconEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.WitherSicknessEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.WitherStormEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.WitherStormModCompatEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.WitherStormModRegisterCommands;
/*    */ import nonamecrackers2.witherstormmod.common.event.WitherStormModUtilEvents;
/*    */ import nonamecrackers2.witherstormmod.common.event.WitherStormPatternChecker;
/*    */ 
/*    */ 
/*    */ public class WitherStormModEvents
/*    */ {
/*    */   public static void registerEvents() {
/* 25 */     MinecraftForge.EVENT_BUS.register(WitherStormPatternChecker.class);
/* 26 */     MinecraftForge.EVENT_BUS.register(WitherStormModRegisterCommands.class);
/* 27 */     MinecraftForge.EVENT_BUS.register(WitherSicknessEvents.class);
/* 28 */     MinecraftForge.EVENT_BUS.register(WitherStormEvents.class);
/* 29 */     MinecraftForge.EVENT_BUS.register(EntityConversionEvents.class);
/* 30 */     MinecraftForge.EVENT_BUS.register(EntitySyncableDataEvents.class);
/* 31 */     MinecraftForge.EVENT_BUS.register(PlayerWitherStormDataEvents.class);
/* 32 */     MinecraftForge.EVENT_BUS.register(WitherStormBowelsManager.class);
/* 33 */     MinecraftForge.EVENT_BUS.register(InjectCustomGoalsEvents.class);
/* 34 */     MinecraftForge.EVENT_BUS.register(WitherStormModUtilEvents.class);
/* 35 */     MinecraftForge.EVENT_BUS.register(SuperBeaconEvents.class);
/* 36 */     MinecraftForge.EVENT_BUS.register(ChunkLoadingBlockEntitiesEvents.class);
/* 37 */     MinecraftForge.EVENT_BUS.register(WitherStormModCompatEvents.class);
/* 38 */     MinecraftForge.EVENT_BUS.register(AnvilRecipeEvents.class);
/* 39 */     MinecraftForge.EVENT_BUS.register(WitherStormModChunkLoader.Events.class);
/* 40 */     MinecraftForge.EVENT_BUS.register(WitherStormSyncHelper.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */