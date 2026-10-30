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
import net.minecraft.world.entity.projectile.hurtingprojectile.WitherSkull;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

/** Early symbiont with melee and simplified versions of two spells. */
public final class WitheredSymbiontEntity extends WitherSkeleton {
    private final ServerBossEvent bossBar = new ServerBossEvent(UUID.randomUUID(), getDisplayName(), BossBarColor.PURPLE, BossBarOverlay.PROGRESS);
    private int pulseCooldown = 200;
    private int pulseCharge;
    private int skullTicks;
    private boolean preparingSkulls;
    private int previousSpell = -1;

    public WitheredSymbiontEntity(EntityType<? extends WitherSkeleton> type, Level level) {
        super(type, level);
    }

    @Override public void aiStep() {
        super.aiStep();
        if (!level().isClientSide()) {
            bossBar.setProgress(Math.clamp(getHealth() / getMaxHealth(), 0.0F, 1.0F));
            bossBar.setName(getDisplayName());
            if (isAlive() && level() instanceof ServerLevel server) tickPulse(server);
        }
    }

    /** A small first step toward the original pulse spell; terrain effects are unported. */
    private void tickPulse(ServerLevel level) {
        if (getTarget() == null || !getTarget().isAlive()) {
            if (pulseCharge > 0 || skullTicks > 0) { pulseCharge = 0; skullTicks = 0; pulseCooldown = 100; }
            return;
        }
        if (skullTicks > 0) {
            int interval = getHealth() <= getMaxHealth() * 0.5F ? 10 : 20;
            if (skullTicks % interval == 0) fireSkull(level, getTarget(), 220 - skullTicks);
            if (--skullTicks == 0) pulseCooldown = 300 + random.nextInt(200);
            return;
        }
        if (pulseCharge > 0) {
            if (--pulseCharge % 5 == 0)
                level.sendParticles(ParticleTypes.PORTAL, getX(), getY() + 1.2, getZ(), 12, 0.6, 0.8, 0.6, 0.1);
            if (pulseCharge == 0) {
                if (preparingSkulls) {
                    preparingSkulls = false;
                    previousSpell = 1;
                    skullTicks = 220;
                    return;
                }
                previousSpell = 0;
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
            preparingSkulls = previousSpell < 0 ? random.nextBoolean() : previousSpell == 0;
            pulseCharge = 40 + random.nextInt(20);
            playSound(SoundEvents.EVOKER_CAST_SPELL, 2.0F, 1.0F);
        }
    }

    /** Skulls circle the caster before firing toward the target, as in the old spell. */
    private void fireSkull(ServerLevel level, LivingEntity target, int elapsed) {
        double facing = Math.atan2(target.getX() - getX(), target.getZ() - getZ());
        double angle = elapsed * 0.08;
        double x = getX() + Math.cos(Math.PI / 2.0 - facing) * 2.0 + Math.cos(angle) * Math.cos(Math.PI - facing) * 2.0;
        double y = getEyeY() + Math.sin(angle) * 2.0;
        double z = getZ() + Math.sin(Math.PI / 2.0 - facing) * 2.0 + Math.cos(angle) * Math.sin(Math.PI - facing) * 2.0;
        Vec3 origin = new Vec3(x, y, z);
        Vec3 direction = target.getEyePosition().subtract(origin).normalize();
        WitherSkull skull = new WitherSkull(level, this, direction);
        skull.setPos(origin);
        skull.setDangerous(random.nextInt(11) == 1);
        if (level.addFreshEntity(skull)) {
            skull.playSound(SoundEvents.WITHER_SHOOT, 4.0F, 1.0F);
            level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 8, 0.3, 0.3, 0.3, 0.01);
        }
    }

    @Override protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("PulseCooldown", pulseCooldown);
        output.putInt("PulseCharge", pulseCharge);
        output.putInt("SkullTicks", skullTicks);
        output.putBoolean("PreparingSkulls", preparingSkulls);
        output.putInt("PreviousSpell", previousSpell);
    }

    @Override protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        pulseCooldown = Math.clamp(input.getIntOr("PulseCooldown", 200), 0, 500);
        pulseCharge = Math.clamp(input.getIntOr("PulseCharge", 0), 0, 59);
        skullTicks = Math.clamp(input.getIntOr("SkullTicks", 0), 0, 220);
        preparingSkulls = input.getBooleanOr("PreparingSkulls", false);
        previousSpell = Math.clamp(input.getIntOr("PreviousSpell", -1), -1, 1);
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
