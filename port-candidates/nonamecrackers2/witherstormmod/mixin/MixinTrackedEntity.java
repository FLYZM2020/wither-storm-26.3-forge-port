/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.network.protocol.Packet;
/*    */ import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
/*    */ import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
/*    */ import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
/*    */ import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
/*    */ import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
/*    */ import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.network.PacketDistributor;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*    */ import nonamecrackers2.witherstormmod.common.packet.StormAttributesMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.StormMetadataMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.StormTeleportMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormHeadLookMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormPositionMessage;
/*    */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormVelocityMessage;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(targets = {"net.minecraft.server.level.ChunkMap$TrackedEntity"})
/*    */ public class MixinTrackedEntity
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private Entity entity;
/*    */   
/*    */   @Inject(method = {"broadcast"}, at = {@At("TAIL")})
/*    */   public void witherstormmod$copyVanillaAndSendToDistantRenderer_broadcast(Packet<?> packet, CallbackInfo ci) {
/* 43 */     Entity entity = this.entity; if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*    */       
/* 45 */       Objects.requireNonNull(storm.level()); PacketDistributor.PacketTarget target = PacketDistributor.DIMENSION.with(storm.level()::dimension);
/* 46 */       List<Integer> applicable = WorldUtil.getStormIds(storm);
/* 47 */       if (packet instanceof ClientboundMoveEntityPacket.Rot) { ClientboundMoveEntityPacket.Rot rotPacket = (ClientboundMoveEntityPacket.Rot)packet;
/* 48 */         WitherStormModPacketHandlers.MAIN.send(target, new UpdateStormPositionMessage(applicable, this.entity.getId(), rotPacket.getyRot(), rotPacket.getxRot(), rotPacket.isOnGround())); }
/* 49 */       else if (packet instanceof ClientboundMoveEntityPacket.Pos) { ClientboundMoveEntityPacket.Pos posPacket = (ClientboundMoveEntityPacket.Pos)packet;
/* 50 */         WitherStormModPacketHandlers.MAIN.send(target, new UpdateStormPositionMessage(applicable, this.entity.getId(), posPacket.getXa(), posPacket.getYa(), posPacket.getZa(), posPacket.isOnGround())); }
/* 51 */       else if (packet instanceof ClientboundMoveEntityPacket.PosRot) { ClientboundMoveEntityPacket.PosRot posRotPacket = (ClientboundMoveEntityPacket.PosRot)packet;
/* 52 */         WitherStormModPacketHandlers.MAIN.send(target, new UpdateStormPositionMessage(applicable, this.entity.getId(), posRotPacket.getXa(), posRotPacket.getYa(), posRotPacket.getZa(), posRotPacket.getyRot(), posRotPacket.getxRot(), posRotPacket.isOnGround())); }
/* 53 */       else if (packet instanceof ClientboundTeleportEntityPacket) { ClientboundTeleportEntityPacket teleportPacket = (ClientboundTeleportEntityPacket)packet;
/* 54 */         WitherStormModPacketHandlers.MAIN.send(target, new StormTeleportMessage(applicable, teleportPacket.getId(), teleportPacket.getX(), teleportPacket.getY(), teleportPacket.getZ(), teleportPacket.getyRot(), teleportPacket.getxRot(), teleportPacket.isOnGround())); }
/* 55 */       else if (packet instanceof ClientboundSetEntityMotionPacket) { ClientboundSetEntityMotionPacket motionPacket = (ClientboundSetEntityMotionPacket)packet;
/* 56 */         WitherStormModPacketHandlers.MAIN.send(target, new UpdateStormVelocityMessage(applicable, motionPacket.getId(), motionPacket.getXa(), motionPacket.getYa(), motionPacket.getZa())); }
/* 57 */       else if (packet instanceof ClientboundRotateHeadPacket) { ClientboundRotateHeadPacket headPacket = (ClientboundRotateHeadPacket)packet;
/* 58 */         WitherStormModPacketHandlers.MAIN.send(target, new UpdateStormHeadLookMessage(applicable, storm.getId(), headPacket.getYHeadRot())); }
/* 59 */       else if (packet instanceof ClientboundSetEntityDataPacket) { ClientboundSetEntityDataPacket dataPacket = (ClientboundSetEntityDataPacket)packet;
/* 60 */         WitherStormModPacketHandlers.MAIN.send(target, new StormMetadataMessage(applicable, dataPacket.id(), dataPacket.packedItems())); }
/* 61 */       else if (packet instanceof ClientboundUpdateAttributesPacket) { ClientboundUpdateAttributesPacket attributesPacket = (ClientboundUpdateAttributesPacket)packet;
/* 62 */         WitherStormModPacketHandlers.MAIN.send(target, new StormAttributesMessage(applicable, attributesPacket.getEntityId(), attributesPacket.getValues())); }
/*    */        }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinTrackedEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */