package nonamecrackers2.witherstormmod.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent.Context;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;

public class PlayerMotionMessage extends Packet {
   private Vec3 motion;

   public PlayerMotionMessage(Vec3 motion) {
      super(true);
      this.motion = motion;
   }

   public PlayerMotionMessage() {
      super(false);
   }

   public Vec3 getMotion() {
      return this.motion;
   }

   public void encode(FriendlyByteBuf buffer) {
      buffer.writeDouble(this.motion.f_82479_);
      buffer.writeDouble(this.motion.f_82480_);
      buffer.writeDouble(this.motion.f_82481_);
   }

   public void decode(FriendlyByteBuf buffer) {
      double x = buffer.readDouble();
      double y = buffer.readDouble();
      double z = buffer.readDouble();
      this.motion = new Vec3(x, y, z);
   }

   public Runnable getProcessor(Context context) {
      return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processPlayerMotionMessage(this));
   }

   public String toString() {
      return "PlayerMotionMessageToClient[motion=" + this.motion + "]";
   }
}
