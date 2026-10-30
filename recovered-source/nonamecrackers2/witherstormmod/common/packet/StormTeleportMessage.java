package nonamecrackers2.witherstormmod.common.packet;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent.Context;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class StormTeleportMessage extends DistantRendererMessage {
   private int entityId;
   private double x;
   private double y;
   private double z;
   private byte yRot;
   private byte xRot;
   private boolean onGround;

   @Deprecated
   public StormTeleportMessage(List<Integer> applicable, WitherStormEntity entity) {
      super(true, applicable);
      this.entityId = entity.m_19879_();
      Vec3 vec = entity.m_213870_();
      this.x = vec.f_82479_;
      this.y = vec.f_82480_;
      this.z = vec.f_82481_;
      this.yRot = (byte)(entity.m_146908_() * 256.0F / 360.0F);
      this.xRot = (byte)(entity.m_146909_() * 256.0F / 360.0F);
      this.onGround = entity.m_20096_();
   }

   public StormTeleportMessage(List<Integer> applicable, int entityId, double x, double y, double z, byte yRot, byte xRot, boolean onGround) {
      super(true, applicable);
      this.entityId = entityId;
      this.x = x;
      this.y = y;
      this.z = z;
      this.yRot = yRot;
      this.xRot = xRot;
      this.onGround = onGround;
   }

   public StormTeleportMessage() {
      super(false, Lists.newArrayList());
   }

   public int getEntityID() {
      return this.entityId;
   }

   public boolean onGround() {
      return this.onGround;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public byte getYRot() {
      return this.yRot;
   }

   public byte getXRot() {
      return this.xRot;
   }

   public void encode(FriendlyByteBuf buffer) {
      super.encode(buffer);
      buffer.m_130130_(this.entityId);
      buffer.writeDouble(this.x);
      buffer.writeDouble(this.y);
      buffer.writeDouble(this.z);
      buffer.writeByte(this.yRot);
      buffer.writeByte(this.xRot);
      buffer.writeBoolean(this.onGround);
   }

   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
      super.decode(buffer);
      this.entityId = buffer.m_130242_();
      this.x = buffer.readDouble();
      this.y = buffer.readDouble();
      this.z = buffer.readDouble();
      this.yRot = buffer.readByte();
      this.xRot = buffer.readByte();
      this.onGround = buffer.readBoolean();
   }

   public Runnable getProcessor(Context context) {
      return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processStormTeleportMessage(this));
   }

   public String toString() {
      return "StormTeleportMessage[id="
         + this.entityId
         + ", x="
         + this.x
         + ", y="
         + this.y
         + ", z="
         + this.z
         + ", yRot="
         + this.yRot
         + ", xRot="
         + this.xRot
         + "]";
   }
}
