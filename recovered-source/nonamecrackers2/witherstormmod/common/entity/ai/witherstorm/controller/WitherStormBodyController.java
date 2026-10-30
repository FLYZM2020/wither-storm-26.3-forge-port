package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent.Context;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;

public class WitherStormBodyController extends BodyRotationControl {
   protected final WitherStormEntity storm;
   protected float maxRotSpeed;
   protected boolean hasWanted;
   protected double wantedX;
   protected double wantedZ;
   protected int headStableTime;
   protected float lastStableYHeadRot;

   public WitherStormBodyController(WitherStormEntity storm) {
      super(storm);
      this.storm = storm;
   }

   public void m_8121_() {
      if (!this.storm.m_9236_().f_46443_) {
         if (!this.storm.m_21525_() && !this.storm.isDeadOrPlayingDead()) {
            boolean ultimateTargetInUseBySegment = this.storm.getUltimateTarget() == null
               ? false
               : this.storm.targetInUseBySegment(this.storm.getUltimateTarget());
            boolean ultimateTargetInUseByHeads = this.storm.getUltimateTarget() == null
               ? false
               : this.storm.alreadyATarget(this.storm.getUltimateTarget(), false);
            FormidibombEntity formidibomb = this.storm.getFormidibomb();
            boolean formidibombNearby = this.storm.isNearbyTickingFormidibomb();
            if (this.storm.getPhase() > 3) {
               if (!ultimateTargetInUseBySegment && !ultimateTargetInUseByHeads && !formidibombNearby && this.storm.shouldRotateTowardsUltimateTarget()) {
                  Vec3 pos = this.storm.getUltimateTargetPos();
                  if (pos != null) {
                     this.setBodyAt(pos.m_7096_(), pos.m_7094_(), ((Double)WitherStormModConfig.SERVER.rotationSpeed.get()).floatValue());
                  }

                  if (this.hasWanted) {
                     this.storm.f_20883_ = this.rotateTowards(this.storm.f_20883_, this.getYRotD(), this.maxRotSpeed);
                     this.m_24881_();
                  }
               } else if (formidibombNearby) {
                  this.setBodyAt(formidibomb.m_20185_(), formidibomb.m_20189_(), 0.1F);
                  if (this.hasWanted) {
                     this.storm.f_20883_ = this.rotateTowards(this.storm.f_20883_, this.getYRotD(), this.maxRotSpeed);
                     this.m_24881_();
                  }
               }
            } else {
               boolean flag = true;

               for (AdditionalHead head : this.storm.getHeadManager().getOtherHeads()) {
                  if (head.getTarget() != null) {
                     flag = false;
                     break;
                  }
               }

               if (flag && this.storm.m_5448_() == null && this.m_24884_()) {
                  Vec3 pos = this.storm.getUltimateTargetPos();
                  if (pos != null) {
                     this.setBodyAt(pos.m_7096_(), pos.m_7094_(), 5.0F);
                  }

                  if (this.hasWanted) {
                     this.storm.f_20883_ = this.rotateTowards(this.storm.f_20883_, this.getYRotD(), this.maxRotSpeed);
                     this.m_24881_();
                  }
               } else {
                  super.m_8121_();
               }
            }
         }

         if (this.storm.canFallOnBack()) {
            if (this.storm.getPlayDeadManager().getState() == State.PLAYING_DEAD) {
               if (this.storm.xBodyRot < 90.0F) {
                  this.storm.xBodyRot = this.storm.xBodyRot + (this.storm.xBodyRot * 0.04F + 0.05F);
               }

               if (this.storm.xBodyRot > 90.0F) {
                  this.storm.xBodyRot = 90.0F;
                  this.storm.onFallOnBack();
               }
            } else if (!this.storm.isPlayingDead()) {
               WitherStormEntity var8 = this.storm;
               var8.xBodyRot = var8.xBodyRot + (-this.storm.xBodyRot * 0.015F - 0.02F);
               if (this.storm.xBodyRot < 0.0F) {
                  this.storm.xBodyRot = 0.0F;
               }
            }
         }

         nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController.UpdateBodyRotMessage message = new nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController.UpdateBodyRotMessage(
            this.storm
         );
         ResourceKey<Level> dimension = this.storm.m_9236_().m_46472_();
         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> dimension), message);
      }
   }

   private void setBodyAt(double x, double z, float speed) {
      this.wantedX = x;
      this.wantedZ = z;
      this.maxRotSpeed = speed;
      this.hasWanted = true;
   }

   protected void m_24881_() {
      this.storm.f_20885_ = Mth.m_14094_(this.storm.f_20885_, this.storm.f_20883_, this.storm.m_8085_());
   }

   protected float rotateTowards(float yRot, float newYRot, float speed) {
      float f = Mth.m_14118_(yRot, newYRot);
      float f1 = Mth.m_14036_(f, -speed, speed);
      return yRot + f1;
   }

   protected float getYRotD() {
      double d0 = this.wantedX - this.storm.m_20185_();
      double d1 = this.wantedZ - this.storm.m_20189_();
      return (float)(Mth.m_14136_(d1, d0) * 180.0F / (float)Math.PI) - 90.0F;
   }

   protected boolean m_24884_() {
      double d0 = this.storm.m_20185_() - this.storm.f_19854_;
      double d1 = this.storm.m_20189_() - this.storm.f_19856_;
      return d0 * d0 + d1 * d1 > 2.5000003E-7F;
   }

   public static class UpdateBodyRotMessage extends Packet {
      private float yBodyRot;
      private float xBodyRot;
      private int entityId;

      private UpdateBodyRotMessage(WitherStormEntity entity) {
         super(true);
         this.yBodyRot = entity.f_20883_;
         this.xBodyRot = entity.xBodyRot;
         this.entityId = entity.m_19879_();
      }

      public UpdateBodyRotMessage() {
         super(false);
      }

      public void decode(FriendlyByteBuf buffer) {
         this.yBodyRot = buffer.readFloat();
         this.xBodyRot = buffer.readFloat();
         this.entityId = buffer.m_130242_();
      }

      public void encode(FriendlyByteBuf buffer) {
         buffer.writeFloat(this.yBodyRot);
         buffer.writeFloat(this.xBodyRot);
         buffer.m_130130_(this.entityId);
      }

      public Runnable getProcessor(Context context) {
         return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> processMessage(this));
      }

      public String toString() {
         return "UpdateBodyRotMessage[yBodyRot=" + this.yBodyRot + ", xBodyRot=" + this.xBodyRot + "]";
      }

      public static void processMessage(
         nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController.UpdateBodyRotMessage message
      ) {
         Minecraft mc = Minecraft.m_91087_();
         ClientLevel world = mc.f_91073_;
         WitherStormEntity entity = (WitherStormEntity)mc.f_91073_.m_6815_(message.entityId);
         if (entity != null) {
            entity.lerpBodyRotationTo(message.xBodyRot, message.yBodyRot, 3);
         }

         world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
            WitherStormEntity distantEntity = distantRenderer.get(message.entityId);
            if (distantEntity != null) {
               distantEntity.lerpBodyRotationTo(message.xBodyRot, message.yBodyRot, 3);
            }
         });
      }
   }
}
