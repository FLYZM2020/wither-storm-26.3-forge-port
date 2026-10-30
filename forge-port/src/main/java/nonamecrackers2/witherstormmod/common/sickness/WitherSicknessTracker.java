package nonamecrackers2.witherstormmod.common.sickness;

import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import nonamecrackers2.witherstormmod.common.config.StormConfig;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;

/**
 * Server-side, persistent early tracker. This ports proximity, application and
 * cure timers, target contact infections and reinfection strength. Random offsets, syncing
 * and sickened-mob conversion have not yet been migrated.
 */
public final class WitherSicknessTracker {
    private static final String KEY = "witherstormmod:sickness";
    private static final int STEP = 20;
    // At level 13 the effect already ticks every game tick; higher values add no speed.
    private static final int MAX_AMPLIFIER = 13;
    private WitherSicknessTracker() {}

    private static boolean immune(LivingEntity entity) {
        return entity.typeHolder().is(WitherStormModEntityTags.WITHER_SICKNESS_IMMUNE)
                || entity.isSpectator() || entity instanceof Player player && player.isCreative();
    }

    private static CompoundTag existing(LivingEntity entity) {
        return entity.getPersistentData().getCompound(KEY).orElse(null);
    }

    private static CompoundTag getOrCreate(LivingEntity entity) {
        CompoundTag data = existing(entity);
        if (data == null) {
            data = new CompoundTag();
            entity.getPersistentData().put(KEY, data);
        }
        return data;
    }

    /** Called periodically by each eligible nearby storm on the server. */
    public static void markNear(LivingEntity entity, long gameTime) {
        if (!immune(entity)) getOrCreate(entity).putLong("NearUntil", gameTime + 40);
    }

    /** Count a new target acquisition once, rather than once per beam tick. */
    public static void countContact(LivingEntity entity) {
        if (entity.level().isClientSide() || !StormConfig.SICKNESS_ENABLED.get() || immune(entity)) return;
        CompoundTag data = getOrCreate(entity);
        int contacts = Math.min(StormConfig.REQUIRED_CONTACTS.get() + 1, data.getIntOr("Contacts", 0) + 1);
        data.putInt("Contacts", contacts);
        if (contacts > StormConfig.REQUIRED_CONTACTS.get()) {
            data.putBoolean("IsInfected", true);
            data.putBoolean("IsBeingCured", false);
            data.putInt("CureTicks", 0);
        }
    }

    public static boolean isBeingCured(LivingEntity entity) {
        CompoundTag data = existing(entity);
        return data != null && data.getBooleanOr("IsBeingCured", false);
    }

    public static boolean beginCure(LivingEntity entity) {
        if (entity.level().isClientSide() || immune(entity)) return false;
        CompoundTag data = existing(entity);
        if (data == null || !data.getBooleanOr("IsInfected", false) || data.getBooleanOr("IsBeingCured", false)) return false;
        data.putBoolean("IsBeingCured", true);
        data.putInt("CureTicks", 0);
        return true;
    }

    /** Preserve infection across player respawn, matching the original default. */
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath() || event.getEntity().level().isClientSide()) return;
        CompoundTag prior = existing(event.getOriginal());
        if (prior == null) return;
        CompoundTag copy = prior.copy();
        copy.remove("NearUntil");
        if (!StormConfig.KEEP_SICKNESS_AFTER_RESPAWN.get()) {
            copy.putBoolean("IsInfected", false);
            copy.putBoolean("IsBeingCured", false);
            copy.putInt("ProximityTicks", 0);
            copy.putInt("ApplicationTicks", 0);
            copy.putInt("CureTicks", 0);
            copy.putInt("Contacts", 0);
            copy.putInt("ContactDecayTicks", 0);
        }
        event.getEntity().getPersistentData().put(KEY, copy);
        if (StormConfig.KEEP_SICKNESS_AFTER_RESPAWN.get()) {
            var holder = WitherStormModEffects.WITHER_SICKNESS.getHolder().orElseThrow();
            MobEffectInstance effect = event.getOriginal().getEffect(holder);
            if (effect != null) event.getEntity().addEffect(new MobEffectInstance(effect));
        }
    }

    /** One-second timer resolution bounds per-entity work; timers persist with the entity. */
    public static void onLivingTick(LivingEvent.LivingTickEvent event, boolean cancelled) {
        LivingEntity entity = event.getEntity();
        if (cancelled || entity.level().isClientSide() || entity.tickCount % STEP != 0 || !entity.isAlive()) return;
        CompoundTag data = existing(entity);
        if (data == null || immune(entity) || !(entity.level() instanceof ServerLevel level)) return;
        boolean highImmunity = entity.typeHolder().is(WitherStormModEntityTags.HIGH_IMMUNITY);
        int required = (highImmunity ? StormConfig.HIGH_PROXIMITY_SECONDS.get() : StormConfig.LOW_PROXIMITY_SECONDS.get()) * 20;
        int application = (highImmunity ? StormConfig.HIGH_APPLICATION_SECONDS.get() : StormConfig.LOW_APPLICATION_SECONDS.get()) * 20;
        int proximity = data.getIntOr("ProximityTicks", 0);
        boolean nearby = StormConfig.SICKNESS_ENABLED.get() && level.getLevelData().getGameTime() <= data.getLongOr("NearUntil", -1L);
        proximity = nearby ? Math.min(required, proximity + STEP) : Math.max(0, proximity - STEP);
        data.putInt("ProximityTicks", proximity);
        if (!nearby && data.getIntOr("Contacts", 0) > 0) {
            int elapsed = data.getIntOr("ContactDecayTicks", 0) + STEP;
            if (elapsed >= 9600) {
                data.putInt("Contacts", data.getIntOr("Contacts", 0) - 1);
                elapsed = 0;
            }
            data.putInt("ContactDecayTicks", elapsed);
        }
        boolean infected = data.getBooleanOr("IsInfected", false);
        if (!nearby && !infected && data.getIntOr("Amplifier", 0) > 0) {
            int elapsed = data.getIntOr("AmplifierDecayTicks", 0) + STEP;
            if (elapsed >= 7200) {
                data.putInt("Amplifier", data.getIntOr("Amplifier", 0) - 1);
                elapsed = 0;
            }
            data.putInt("AmplifierDecayTicks", elapsed);
        }
        if (!infected && proximity >= required) {
            infected = true;
            data.putBoolean("IsInfected", true);
            data.putBoolean("IsBeingCured", false);
        }
        if (!infected) {
            data.putInt("ApplicationTicks", 0);
            return;
        }
        boolean curing = data.getBooleanOr("IsBeingCured", false);
        int delay = data.getIntOr("ApplicationTicks", 0);
        int previousDelay = delay;
        if (!curing && delay < application) {
            delay = Math.min(application, delay + STEP);
            data.putInt("ApplicationTicks", delay);
        }
        Holder<MobEffect> effect = WitherStormModEffects.WITHER_SICKNESS.getHolder().orElseThrow();
        if (delay >= application) {
            if (!curing && previousDelay < application) {
                int amplifier = Math.clamp(data.getIntOr("Amplifier", 0), 0, MAX_AMPLIFIER);
                entity.addEffect(new MobEffectInstance(effect, 12000, amplifier, false, false, true));
                data.putInt("TotalInfections", data.getIntOr("TotalInfections", 0) + 1);
                if (StormConfig.INCREASE_SICKNESS_AMPLIFIER.get())
                    data.putInt("Amplifier", Math.min(MAX_AMPLIFIER, amplifier + 1));
            }
            MobEffectInstance current = entity.getEffect(effect);
            if (current == null) {
                data.putBoolean("IsInfected", false);
                data.putBoolean("IsBeingCured", false);
                data.putInt("ProximityTicks", 0);
                data.putInt("ApplicationTicks", 0);
                data.putInt("CureTicks", 0);
                return;
            }
            if (current.getDuration() < 7200) {
                entity.addEffect(new MobEffectInstance(effect, 12000, current.getAmplifier(), false, false, true));
            }
        }
        if (curing) {
            int cureTicks = data.getIntOr("CureTicks", 0) + STEP;
            data.putInt("CureTicks", cureTicks);
            if (cureTicks >= StormConfig.CURE_SECONDS.get() * 20) {
                entity.removeEffect(effect);
                data.putBoolean("IsInfected", false);
                data.putBoolean("IsBeingCured", false);
                data.putInt("ProximityTicks", 0);
                data.putInt("ApplicationTicks", 0);
                data.putInt("CureTicks", 0);
            }
        }
    }
}
