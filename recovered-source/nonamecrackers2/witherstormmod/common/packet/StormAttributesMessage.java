package nonamecrackers2.witherstormmod.common.packet;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket.AttributeSnapshot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;

public class StormAttributesMessage extends DistantRendererMessage {
   private int entityId;
   private List<AttributeSnapshot> attributes = Lists.newArrayList();

   public StormAttributesMessage(List<Integer> applicable, int id, Collection<AttributeInstance> attributes) {
      super(true, applicable);
      this.entityId = id;

      for (AttributeInstance attribute : attributes) {
         this.attributes.add(new AttributeSnapshot(attribute.m_22099_(), attribute.m_22115_(), attribute.m_22122_()));
      }
   }

   public StormAttributesMessage(List<Integer> applicable, int id, List<AttributeSnapshot> attributes) {
      super(true, applicable);
      this.entityId = id;
      this.attributes = attributes;
   }

   public StormAttributesMessage() {
      super(false, Lists.newArrayList());
   }

   public int getEntityID() {
      return this.entityId;
   }

   public List<AttributeSnapshot> getAttributes() {
      return this.attributes;
   }

   public void encode(FriendlyByteBuf buffer) {
      super.encode(buffer);
      buffer.m_130130_(this.entityId);
      buffer.m_236828_(this.attributes, (buffer1, snapshot) -> {
         buffer1.m_130085_(ForgeRegistries.ATTRIBUTES.getKey(snapshot.m_133601_()));
         buffer1.writeDouble(snapshot.m_133602_());
         buffer1.m_236828_(snapshot.m_133603_(), (buffer2, modifier) -> {
            buffer2.m_130077_(modifier.m_22209_());
            buffer2.writeDouble(modifier.m_22218_());
            buffer2.writeByte(modifier.m_22217_().m_22235_());
         });
      });
   }

   public void decode(FriendlyByteBuf buffer) {
      super.decode(buffer);
      this.entityId = buffer.m_130242_();
      this.attributes = buffer.m_236845_(
         buffer1 -> {
            ResourceLocation location = buffer1.m_130281_();
            Attribute attribute = (Attribute)ForgeRegistries.ATTRIBUTES.getValue(location);
            double base = buffer1.readDouble();
            List<AttributeModifier> list = buffer1.m_236845_(
               buffer2 -> new AttributeModifier(
                  buffer2.m_130259_(), "Unknown synced attribute modifier", buffer.readDouble(), Operation.m_22236_(buffer.readByte())
               )
            );
            return new AttributeSnapshot(attribute, base, list);
         }
      );
   }

   public Runnable getProcessor(Context context) {
      return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processStormAttributesMessage(this));
   }

   public String toString() {
      return "StormAttributesMessage[id=" + this.entityId + ", properties=" + this.attributes.toString() + "]";
   }
}
