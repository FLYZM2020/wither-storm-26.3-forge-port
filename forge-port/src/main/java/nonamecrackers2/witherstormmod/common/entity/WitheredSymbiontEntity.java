package nonamecrackers2.witherstormmod.common.entity;

import java.util.UUID;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent.BossBarColor;
import net.minecraft.world.BossEvent.BossBarOverlay;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

/** Temporary melee stand-in; original symbiont model, spells and boss behavior remain unported. */
public final class WitheredSymbiontEntity extends WitherSkeleton {
    private final ServerBossEvent bossBar = new ServerBossEvent(UUID.randomUUID(), getDisplayName(), BossBarColor.PURPLE, BossBarOverlay.PROGRESS);
    private int pulseCooldown = 200;
    private int pulseCharge;

    public WitheredSymbiontEntity(EntityType<? extends WitherSkeleton> type, Level level) {
        super(type, level);
    }

    @Override public void aiStep() {
        super.aiStep();
        if (!level().isClientSide()) {
            bossBar.setProgress(Math.clamp(getHealth() / getMaxHealth(), 0.0F, 1.0F));
            bossBar.setName(getDisplayName());
            if (level() instanceof ServerLevel server) tickPulse(server);
        }
    }

    /** A small first step toward the original pulse spell; terrain effects are unported. */
    private void tickPulse(ServerLevel level) {
        if (getTarget() == null || !getTarget().isAlive()) {
            if (pulseCharge > 0) { pulseCharge = 0; pulseCooldown = 100; }
            return;
        }
        if (pulseCharge > 0) {
            if (--pulseCharge % 5 == 0)
                level.sendParticles(ParticleTypes.PORTAL, getX(), getY() + 1.2, getZ(), 12, 0.6, 0.8, 0.6, 0.1);
            if (pulseCharge == 0) {
                playSound(SoundEvents.GENERIC_EXPLODE.value(), 2.0F, 1.4F);
                for (LivingEntity victim : level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(12.0),
                        e -> e != this && e.isAlive() && e.isAttackable() && !(e instanceof WitherBoss)
                                && !(e instanceof WitheredSymbiontEntity) && !e.isSpectator()
                                && !(e instanceof Player p && p.isCreative()))) {
                    Vec3 away = victim.position().subtract(position()).add(0.0, 1.0, 0.0).normalize().scale(2.0);
                    victim.setDeltaMovement(away);
                    if (victim instanceof ServerPlayer player) player.connection.send(new ClientboundSetEntityMotionPacket(player));
                }
                pulseCooldown = 300 + random.nextInt(200);
            }
        } else if (--pulseCooldown <= 0) {
            pulseCharge = 40;
            playSound(SoundEvents.EVOKER_CAST_SPELL, 2.0F, 1.0F);
        }
    }

    @Override protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("PulseCooldown", pulseCooldown);
        output.putInt("PulseCharge", pulseCharge);
    }

    @Override protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        pulseCooldown = Math.clamp(input.getIntOr("PulseCooldown", 200), 0, 500);
        pulseCharge = Math.clamp(input.getIntOr("PulseCharge", 0), 0, 40);
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
