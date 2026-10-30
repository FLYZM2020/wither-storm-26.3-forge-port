package nonamecrackers2.witherstormmod.common.packet;

import com.google.common.collect.Lists;
import io.netty.buffer.Unpooled;
import java.util.List;
import java.util.UUID;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket.AttributeSnapshot;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.DataValue;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager.PackedHeadRots;

public class WitherStormToDistantRendererMessage extends DistantRendererMessage {
   private final WitherStormEntity entity;
   private int id;
   private UUID uuid;
   private ResourceLocation type;
   private Vec3 pos;
   private Vec3i delta;
   private byte yRot;
   private byte xRot;
   private byte yHeadRot;
   private PackedHeadRots rots;
   private List<DataValue<?>> packedItems;
   private final List<AttributeSnapshot> attributes = Lists.newArrayList();
   private FriendlyByteBuf buffer;

   public WitherStormToDistantRendererMessage(List<Integer> applicable, WitherStormEntity entity) {
      super(true, applicable);
      this.entity = entity;
      this.id = entity.m_19879_();
      this.uuid = entity.m_20148_();
      this.type = ForgeRegistries.ENTITY_TYPES.getKey(entity.m_6095_());
      this.pos = new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
      this.yRot = (byte)(entity.m_146908_() * 256.0F / 360.0F);
      this.xRot = (byte)(entity.m_146909_() * 256.0F / 360.0F);
      this.yHeadRot = (byte)(entity.f_20885_ * 256.0F / 360.0F);
      this.rots = entity.getHeadManager().packHeadRotations();
      Vec3 vector3d = entity.m_20184_();
      Vec3i delta = new Vec3i(
         Mth.m_14107_(Mth.m_14008_(vector3d.f_82479_, -3.9, 3.9)),
         Mth.m_14107_(Mth.m_14008_(vector3d.f_82480_, -3.9, 3.9)),
         Mth.m_14107_(Mth.m_14008_(vector3d.f_82481_, -3.9, 3.9))
      );
      this.delta = delta;
      SynchedEntityData data = entity.m_20088_();
      this.packedItems = getPackedData(data);

      for (AttributeInstance attribute : entity.m_21204_().m_22170_()) {
         this.attributes.add(new AttributeSnapshot(attribute.m_22099_(), attribute.m_22115_(), attribute.m_22122_()));
      }

      this.buffer = null;
   }

   public WitherStormToDistantRendererMessage() {
      super(false, Lists.newArrayList());
      this.entity = null;
   }

   public int getId() {
      return this.id;
   }

   public UUID getUUID() {
      return this.uuid;
   }

   public ResourceLocation getType() {
      return this.type;
   }

   public Vec3 getPos() {
      return this.pos;
   }

   public Vec3i getDeltaMovement() {
      return this.delta;
   }

   public byte getYRot() {
      return this.yRot;
   }

   public byte getXRot() {
      return this.xRot;
   }

   public byte getHeadYRot() {
      return this.yHeadRot;
   }

   public PackedHeadRots getRots() {
      return this.rots;
   }

   public List<DataValue<?>> getUnpackedData() {
      return this.packedItems;
   }

   public FriendlyByteBuf getBuffer() {
      return this.buffer;
   }

   public void decode(FriendlyByteBuf buffer) {
      super.decode(buffer);
      this.id = buffer.m_130242_();
      this.uuid = buffer.m_130259_();
      this.type = buffer.m_130281_();
      double x = buffer.readDouble();
      double y = buffer.readDouble();
      double z = buffer.readDouble();
      this.pos = new Vec3(x, y, z);
      this.yRot = buffer.readByte();
      this.xRot = buffer.readByte();
      this.yHeadRot = buffer.readByte();
      this.rots = PackedHeadRots.fromPacket(buffer);
      int xd = buffer.readShort();
      int yd = buffer.readShort();
      int zd = buffer.readShort();
      this.delta = new Vec3i(xd, yd, zd);
      List<DataValue<?>> packedData = Lists.newArrayList();

      int j;
      while ((j = buffer.readUnsignedByte()) != 255) {
         packedData.add(DataValue.m_252860_(buffer, j));
      }

      this.packedItems = packedData;
      int attributeSize = buffer.readInt();

      for (int i = 0; i < attributeSize; i++) {
         ResourceLocation location = buffer.m_130281_();
         Attribute attribute = (Attribute)ForgeRegistries.ATTRIBUTES.getValue(location);
         double base = buffer.readDouble();
         List<AttributeModifier> list = Lists.newArrayList();
         int modifierSize = buffer.m_130242_();

         for (int l = 0; l < modifierSize; l++) {
            UUID uuid = buffer.m_130259_();
            list.add(new AttributeModifier(uuid, "Unknown synced attribute modifier", buffer.readDouble(), Operation.m_22236_(buffer.readByte())));
         }

         this.attributes.add(new AttributeSnapshot(attribute, base, list));
      }

      int count = buffer.m_130242_();
      if (count > 0) {
         FriendlyByteBuf extra = new FriendlyByteBuf(Unpooled.buffer());
         extra.writeBytes(buffer, count);
         this.buffer = extra;
      } else {
         this.buffer = new FriendlyByteBuf(Unpooled.buffer());
      }
   }

   public void encode(FriendlyByteBuf buffer) {
      super.encode(buffer);
      buffer.m_130130_(this.id);
      buffer.m_130077_(this.uuid);
      buffer.m_130085_(this.type);
      buffer.writeDouble(this.pos.f_82479_);
      buffer.writeDouble(this.pos.f_82480_);
      buffer.writeDouble(this.pos.f_82481_);
      buffer.writeByte(this.yRot);
      buffer.writeByte(this.xRot);
      buffer.writeByte(this.yHeadRot);
      this.rots.toPacket(buffer);
      buffer.writeShort(this.delta.m_123341_());
      buffer.writeShort(this.delta.m_123342_());
      buffer.writeShort(this.delta.m_123343_());

      for (DataValue<?> value : this.packedItems) {
         value.m_252897_(buffer);
      }

      buffer.writeByte(255);
      buffer.writeInt(this.attributes.size());

      for (AttributeSnapshot snapshot : this.attributes) {
         buffer.m_130085_(ForgeRegistries.ATTRIBUTES.getKey(snapshot.m_133601_()));
         buffer.writeDouble(snapshot.m_133602_());
         buffer.m_130130_(snapshot.m_133603_().size());

         for (AttributeModifier modifier : snapshot.m_133603_()) {
            buffer.m_130077_(modifier.m_22209_());
            buffer.writeDouble(modifier.m_22218_());
            buffer.writeByte(modifier.m_22217_().m_22235_());
         }
      }

      FriendlyByteBuf extra = new FriendlyByteBuf(Unpooled.buffer());
      if (this.entity instanceof IEntityAdditionalSpawnData e) {
         e.writeSpawnData(extra);
      }

      this.entity.writeData(extra);
      buffer.m_130130_(extra.readableBytes());
      buffer.writeBytes(extra);
      extra.release();
   }

   public Runnable getProcessor(Context context) {
      return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processWitherStormToDistantRendererMessage(this));
   }

   public String toString() {
      return "WitherStormToDistantRendererMessage[id="
         + this.id
         + ", uuid="
         + this.uuid
         + ", type="
         + this.type
         + ", x="
         + this.pos.f_82479_
         + ", y="
         + this.pos.f_82480_
         + ", z="
         + this.pos.f_82481_
         + ", yRot= "
         + this.yRot
         + ", xRot= "
         + this.xRot
         + ", yHeadRot= "
         + this.yHeadRot
         + ", headRots= "
         + this.rots.toString()
         + ", xd="
         + this.delta.m_123341_()
         + ", yd="
         + this.delta.m_123342_()
         + ", zd="
         + this.delta.m_123343_()
         + "]";
   }
}
