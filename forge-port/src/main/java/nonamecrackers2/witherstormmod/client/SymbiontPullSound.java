package nonamecrackers2.witherstormmod.client;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSounds;

/** Client-only sound follows the synced pull state and fades out when it ends. */
@Mod.EventBusSubscriber(modid = WitherStormMod.MOD_ID, value = Dist.CLIENT)
public final class SymbiontPullSound extends AbstractTickableSoundInstance {
    private static final Map<WitheredSymbiontEntity, SymbiontPullSound> ACTIVE = new WeakHashMap<>();
    private final WeakReference<WitheredSymbiontEntity> entity;
    private int fade;

    private SymbiontPullSound(WitheredSymbiontEntity entity) {
        super(WitherStormModSounds.SYMBIONT_PULL.get(), SoundSource.AMBIENT, RandomSource.create());
        this.entity = new WeakReference<>(entity);
        looping = true;
        volume = 0.0F;
        x = entity.getX(); y = entity.getY(); z = entity.getZ();
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof WitheredSymbiontEntity entity) || !entity.level().isClientSide()) return;
        SymbiontPullSound sound = ACTIVE.get(entity);
        if (entity.isPulling() && (sound == null || sound.isStopped())) {
            sound = new SymbiontPullSound(entity);
            ACTIVE.put(entity, sound);
            Minecraft.getInstance().getSoundManager().play(sound);
        } else if (!entity.isPulling() && sound != null && sound.isStopped()) ACTIVE.remove(entity);
    }

    @Override public void tick() {
        WitheredSymbiontEntity current = entity.get();
        if (current == null || current.isRemoved()) { stop(); return; }
        x = current.getX(); y = current.getY(); z = current.getZ();
        fade = Math.clamp(fade + (current.isAlive() && current.isPulling() ? 1 : -1), 0, 20);
        volume = fade / 20.0F;
        if (fade == 0 && !current.isPulling()) stop();
    }
}
