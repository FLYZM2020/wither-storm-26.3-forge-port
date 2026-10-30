package nonamecrackers2.witherstormmod.common.entity;

import java.util.UUID;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent.BossBarColor;
import net.minecraft.world.BossEvent.BossBarOverlay;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.level.Level;

/** Temporary melee stand-in; original symbiont model, spells and boss behavior remain unported. */
public final class WitheredSymbiontEntity extends WitherSkeleton {
    private final ServerBossEvent bossBar = new ServerBossEvent(UUID.randomUUID(), getDisplayName(), BossBarColor.PURPLE, BossBarOverlay.PROGRESS);

    public WitheredSymbiontEntity(EntityType<? extends WitherSkeleton> type, Level level) {
        super(type, level);
    }

    @Override public void aiStep() {
        super.aiStep();
        if (!level().isClientSide()) {
            bossBar.setProgress(Math.clamp(getHealth() / getMaxHealth(), 0.0F, 1.0F));
            bossBar.setName(getDisplayName());
        }
    }

    @Override public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossBar.addPlayer(player);
    }

    @Override public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossBar.removePlayer(player);
    }
}
