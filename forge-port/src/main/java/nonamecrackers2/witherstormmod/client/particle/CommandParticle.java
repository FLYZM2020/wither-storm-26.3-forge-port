package nonamecrackers2.witherstormmod.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public final class CommandParticle extends SimpleAnimatedParticle {
    private CommandParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0F);
        xd = dx; yd = dy; zd = dz;
        lifetime = 10 + random.nextInt(12);
        quadSize = 0.03F;
        hasPhysics = false;
        setSpriteFromAge(sprites);
    }

    public static final class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;
        public Provider(SpriteSet sprites) { this.sprites = sprites; }
        @Override public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            return new CommandParticle(level, x, y, z, dx, dy, dz, sprites);
        }
    }
}
