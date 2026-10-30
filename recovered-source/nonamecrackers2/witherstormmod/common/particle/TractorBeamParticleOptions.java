package nonamecrackers2.witherstormmod.common.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleOptions.Deserializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;

public record TractorBeamParticleOptions(int storm, int head) implements ParticleOptions {
   public static final Codec<nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(
            Codec.INT.fieldOf("storm").forGetter(nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions::storm),
            Codec.INT.fieldOf("head").forGetter(nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions::head)
         )
         .apply(instance, nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions::new)
   );
   public static final Deserializer<nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions> DESERIALIZIER = new Deserializer<nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions>() {
      public nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions fromCommand(
         ParticleType<nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions> type, StringReader reader
      ) throws CommandSyntaxException {
         return new nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions(reader.readInt(), reader.readInt());
      }

      public nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions fromNetwork(
         ParticleType<nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions> type, FriendlyByteBuf buffer
      ) {
         return new nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions(buffer.m_130242_(), buffer.m_130242_());
      }
   };

   public ParticleType<?> m_6012_() {
      return (ParticleType<?>)WitherStormModParticleTypes.TRACTOR_BEAM.get();
   }

   public void m_7711_(FriendlyByteBuf buffer) {
      buffer.m_130130_(this.head);
      buffer.m_130130_(this.head);
   }

   public String m_5942_() {
      return ForgeRegistries.PARTICLE_TYPES.getKey(this.m_6012_()) + " " + this.storm + " " + this.head;
   }
}
