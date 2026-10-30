/*    */ package nonamecrackers2.witherstormmod.client.init;
/*    */ 
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import nonamecrackers2.witherstormmod.client.capability.BowelsEffectsManager;
/*    */ import nonamecrackers2.witherstormmod.client.capability.PlayerTractorBeamEffects;
/*    */ import nonamecrackers2.witherstormmod.client.capability.SoundManagersHolder;
/*    */ import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
/*    */ import nonamecrackers2.witherstormmod.client.event.CheckForHeadHit;
/*    */ import nonamecrackers2.witherstormmod.client.event.ClientWitherSicknessEvents;
/*    */ import nonamecrackers2.witherstormmod.client.event.ParticleEvents;
/*    */ import nonamecrackers2.witherstormmod.client.event.PlaySoundEvents;
/*    */ import nonamecrackers2.witherstormmod.client.event.PlayTractorBeamLoopEvents;
/*    */ import nonamecrackers2.witherstormmod.client.event.PlayerCameraShakerTicker;
/*    */ import nonamecrackers2.witherstormmod.client.event.PlayerCosmeticsEvents;
/*    */ import nonamecrackers2.witherstormmod.client.event.RenderWitherSicknessOverlay;
/*    */ import nonamecrackers2.witherstormmod.client.event.ScreenBlinderEvents;
/*    */ import nonamecrackers2.witherstormmod.client.event.WitherStormAmbienceEffects;
/*    */ import nonamecrackers2.witherstormmod.client.event.WitherStormModClientConfigEvents;
/*    */ import nonamecrackers2.witherstormmod.client.event.WitherStormModGuiEvents;
/*    */ import nonamecrackers2.witherstormmod.client.event.WitherStormModRenderEvents;
/*    */ import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
/*    */ import nonamecrackers2.witherstormmod.client.util.AmuletAnimationHelper;
/*    */ 
/*    */ public class WitherStormModClientEvents
/*    */ {
/* 26 */   public static final RenderWitherSicknessOverlay WITHER_SICKNESS_OVERLAY = new RenderWitherSicknessOverlay();
/*    */ 
/*    */   
/*    */   public static void registerEvents() {
/* 30 */     MinecraftForge.EVENT_BUS.register(WitherStormModRenderEvents.class);
/* 31 */     MinecraftForge.EVENT_BUS.register(WitherStormModGuiEvents.class);
/* 32 */     MinecraftForge.EVENT_BUS.register(WitherStormDistantRenderer.Events.class);
/* 33 */     MinecraftForge.EVENT_BUS.register(PlayTractorBeamLoopEvents.class);
/* 34 */     MinecraftForge.EVENT_BUS.register(CheckForHeadHit.class);
/* 35 */     MinecraftForge.EVENT_BUS.register(WITHER_SICKNESS_OVERLAY);
/* 36 */     MinecraftForge.EVENT_BUS.register(ClientWitherSicknessEvents.class);
/* 37 */     MinecraftForge.EVENT_BUS.register(PlayerCameraShakerTicker.class);
/* 38 */     MinecraftForge.EVENT_BUS.register(ScreenBlinderEvents.class);
/* 39 */     MinecraftForge.EVENT_BUS.register(BowelsEffectsManager.Events.class);
/* 40 */     MinecraftForge.EVENT_BUS.register(SoundManagersHolder.Events.class);
/* 41 */     MinecraftForge.EVENT_BUS.register(WitherStormAmbienceEffects.Events.class);
/* 42 */     MinecraftForge.EVENT_BUS.register(RenderBufferer.Events.class);
/* 43 */     MinecraftForge.EVENT_BUS.register(PlayerCosmeticsEvents.class);
/* 44 */     MinecraftForge.EVENT_BUS.register(PlaySoundEvents.class);
/* 45 */     MinecraftForge.EVENT_BUS.register(WitherStormModClientConfigEvents.class);
/* 46 */     MinecraftForge.EVENT_BUS.addListener(ParticleEvents::onClientTick);
/* 47 */     MinecraftForge.EVENT_BUS.addListener(PlayerTractorBeamEffects::onPlayerTick);
/* 48 */     MinecraftForge.EVENT_BUS.addListener(AmuletAnimationHelper::onClientTick);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\init\WitherStormModClientEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */