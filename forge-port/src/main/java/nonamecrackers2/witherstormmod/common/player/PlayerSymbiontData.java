package nonamecrackers2.witherstormmod.common.player;

import java.util.ArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import nonamecrackers2.witherstormmod.common.config.StormConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

/** Persistent online-time cooldowns for storms that summoned a symbiont for a player. */
public final class PlayerSymbiontData {
    private static final String KEY = "witherstormmod:symbiont_player";
    private static final int STEP = 20;
    private PlayerSymbiontData() {}

    private static CompoundTag get(ServerPlayer player) {
        return player.getPersistentData().getCompound(KEY).orElse(null);
    }

    private static CompoundTag create(ServerPlayer player) {
        CompoundTag data = get(player);
        if (data == null) {
            data = new CompoundTag();
            player.getPersistentData().put(KEY, data);
        }
        return data;
    }

    private static CompoundTag storms(CompoundTag data) {
        CompoundTag storms = data.getCompound("Storms").orElse(null);
        if (storms == null) {
            storms = new CompoundTag();
            data.put("Storms", storms);
        }
        return storms;
    }

    public static boolean protectedFromStorm(ServerPlayer player) {
        CompoundTag data = get(player);
        return data != null && data.getIntOr("ProtectionTicks", 0) > 0;
    }

    public static boolean summonBlocked(ServerPlayer player, WitherStormEntity storm) {
        CompoundTag data = get(player);
        if (data == null) return false;
        if (data.getIntOr("ProtectionTicks", 0) > 0) return true;
        CompoundTag snapshot = storms(data).getCompound(storm.getUUID().toString()).orElse(null);
        return snapshot != null && snapshot.getIntOr("Remaining", 0) > 0
                && snapshot.getIntOr("Phase", -1) == storm.getPhase();
    }

    public static void markSummoned(ServerPlayer player, WitherStormEntity storm) {
        CompoundTag snapshot = new CompoundTag();
        snapshot.putInt("Phase", storm.getPhase());
        snapshot.putInt("Remaining", StormConfig.SYMBIONT_PLAYER_DELAY_MINUTES.get() * 1200 + player.getRandom().nextInt(2400));
        storms(create(player)).put(storm.getUUID().toString(), snapshot);
    }

    public static void markKilled(ServerPlayer player, WitherStormEntity storm) {
        CompoundTag storms = storms(create(player));
        String id = storm.getUUID().toString();
        CompoundTag snapshot = storms.getCompound(id).orElse(null);
        if (snapshot == null) {
            snapshot = new CompoundTag();
            snapshot.putInt("Phase", storm.getPhase());
            storms.put(id, snapshot);
        }
        snapshot.putInt("Remaining", StormConfig.SYMBIONT_PLAYER_KILL_DELAY_MINUTES.get() * 1200 + player.getRandom().nextInt(24000));
    }

    public static void protect(ServerPlayer player) {
        create(player).putInt("ProtectionTicks", StormConfig.SYMBIONT_PLAYER_PROTECTION_MINUTES.get() * 1200 + player.getRandom().nextInt(1200));
    }

    /** Timers advance only while the player is online, at one-second resolution. */
    public static void onLivingTick(LivingEvent.LivingTickEvent event, boolean cancelled) {
        if (cancelled || !(event.getEntity() instanceof ServerPlayer player) || player.tickCount % STEP != 0 || !player.isAlive()) return;
        CompoundTag data = get(player);
        if (data == null) return;
        int protection = data.getIntOr("ProtectionTicks", 0);
        if (protection > 0) data.putInt("ProtectionTicks", Math.max(0, protection - STEP));
        CompoundTag storms = data.getCompound("Storms").orElse(null);
        if (storms == null) return;
        for (String id : new ArrayList<>(storms.keySet())) {
            CompoundTag snapshot = storms.getCompound(id).orElse(null);
            if (snapshot == null) continue;
            int remaining = snapshot.getIntOr("Remaining", 0) - STEP;
            if (remaining <= 0) storms.remove(id); else snapshot.putInt("Remaining", remaining);
        }
    }

    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getEntity().level().isClientSide()) return;
        CompoundTag data = event.getOriginal().getPersistentData().getCompound(KEY).orElse(null);
        if (data != null) event.getEntity().getPersistentData().put(KEY, data.copy());
    }
}
