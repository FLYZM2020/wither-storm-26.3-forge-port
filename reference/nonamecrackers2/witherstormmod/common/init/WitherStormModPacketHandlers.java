/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.network.NetworkRegistry;
/*    */ import net.minecraftforge.network.simple.SimpleChannel;
/*    */ import nonamecrackers2.crackerslib.common.packet.PacketUtil;
/*    */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController;
/*    */ import nonamecrackers2.witherstormmod.common.packet.BlindScreenMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.CreateDebrisMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.CreateLoopingSoundMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.EntitySyncableDataMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.FormidibombExplosionMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.GlobalSoundMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.InjureHeadMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.NotifyHeadInjuryMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.OnHeadAttackedMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.PlayAdditionalLoopingSoundMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.RemoveAdditionalLoopingSoundMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.RemoveDistantSuperBeaconMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.RemoveStormFromDistantRendererMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.StormAttributesMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.StormMetadataMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.StormSoundPositionMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.StormTeleportMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.SuperBeaconSetEffectMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.SuperBeaconToggleAreaMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.SuperBeaconValidEffectsMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateDamagingProjectileMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateDistantSuperBeaconMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateEffectInstanceMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdatePlayDeadManagerMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormHeadLookMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormPositionMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormVelocityMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateWitherSicknessTrackerMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.WitherStormToDistantRendererMessage;
/*    */ 
/*    */ 
/*    */ public class WitherStormModPacketHandlers
/*    */ {
/*    */   private static final String PROTOCOL_VERSION = "4.0";
/* 48 */   public static final SimpleChannel MAIN = NetworkRegistry.newSimpleChannel(new ResourceLocation("witherstormmod", "main"), () -> "4.0", "4.0"::equals, "4.0"::equals);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerPackets() {
/* 57 */     PacketUtil.registerToClient(MAIN, PlayerMotionMessage.class);
/* 58 */     PacketUtil.registerToClient(MAIN, GlobalSoundMessage.class);
/* 59 */     PacketUtil.registerToClient(MAIN, WitherStormBodyController.UpdateBodyRotMessage.class);
/* 60 */     PacketUtil.registerToClient(MAIN, WitherStormToDistantRendererMessage.class);
/* 61 */     PacketUtil.registerToClient(MAIN, RemoveStormFromDistantRendererMessage.class);
/* 62 */     PacketUtil.registerToClient(MAIN, UpdateStormPositionMessage.class);
/* 63 */     PacketUtil.registerToClient(MAIN, StormTeleportMessage.class);
/* 64 */     PacketUtil.registerToClient(MAIN, UpdateStormVelocityMessage.class);
/* 65 */     PacketUtil.registerToClient(MAIN, UpdateStormHeadLookMessage.class);
/* 66 */     PacketUtil.registerToClient(MAIN, StormMetadataMessage.class);
/* 67 */     PacketUtil.registerToClient(MAIN, StormAttributesMessage.class);
/* 68 */     PacketUtil.registerToClient(MAIN, CreateLoopingSoundMessage.class);
/* 69 */     PacketUtil.registerToClient(MAIN, StormSoundPositionMessage.class);
/* 70 */     PacketUtil.registerToClient(MAIN, RemoveSoundLoopMessage.class);
/* 71 */     PacketUtil.registerToClient(MAIN, NotifyHeadInjuryMessage.class);
/* 72 */     PacketUtil.registerToClient(MAIN, UpdateEffectInstanceMessage.class);
/* 73 */     PacketUtil.registerToClient(MAIN, UpdateWitherSicknessTrackerMessage.class);
/* 74 */     PacketUtil.registerToClient(MAIN, UpdatePlayDeadManagerMessage.class);
/* 75 */     PacketUtil.registerToClient(MAIN, CreateDebrisMessage.class);
/* 76 */     PacketUtil.registerToClient(MAIN, EntitySyncableDataMessage.class);
/* 77 */     PacketUtil.registerToClient(MAIN, PlayAdditionalLoopingSoundMessage.class);
/* 78 */     PacketUtil.registerToClient(MAIN, RemoveAdditionalLoopingSoundMessage.class);
/* 79 */     PacketUtil.registerToClient(MAIN, ShakeScreenMessage.class);
/* 80 */     PacketUtil.registerToClient(MAIN, FormidibombExplosionMessage.class);
/* 81 */     PacketUtil.registerToClient(MAIN, UpdateDamagingProjectileMessage.class);
/* 82 */     PacketUtil.registerToClient(MAIN, WitheredSymbiontEntity.SetSpellTimeMessage.class);
/* 83 */     PacketUtil.registerToClient(MAIN, CommandBlockEntity.ModeAnimationMessage.class);
/* 84 */     PacketUtil.registerToClient(MAIN, TentacleEntity.UpdateAnimationMessage.class);
/* 85 */     PacketUtil.registerToClient(MAIN, BlindScreenMessage.class);
/* 86 */     PacketUtil.registerToClient(MAIN, SuperBeaconValidEffectsMessage.class);
/* 87 */     PacketUtil.registerToClient(MAIN, UpdateDistantSuperBeaconMessage.class);
/* 88 */     PacketUtil.registerToClient(MAIN, RemoveDistantSuperBeaconMessage.class);
/* 89 */     PacketUtil.registerToClient(MAIN, OnHeadAttackedMessage.class);
/*    */     
/* 91 */     PacketUtil.registerToServer(MAIN, InjureHeadMessage.class);
/* 92 */     PacketUtil.registerToServer(MAIN, SuperBeaconSetEffectMessage.class);
/* 93 */     PacketUtil.registerToServer(MAIN, SuperBeaconToggleAreaMessage.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModPacketHandlers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */