package nonamecrackers2.witherstormmod.common.entity;

import java.util.UUID;
import java.util.HashSet;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.jspecify.annotations.Nullable;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.common.config.StormConfig;
import nonamecrackers2.witherstormmod.common.sickness.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.init.WitherStormModTickets;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.evolution.StormEvolutionRules;
import nonamecrackers2.witherstormmod.common.beam.TractorBeamGeometry;

/** Early segment shell: follows a persistent parent; independent heads and combat are unported. */
public final class WitherStormSegmentEntity extends WitherBoss {
    private @Nullable UUID parentId;
    private static final EntityDataAccessor<Integer> INDEX = SynchedEntityData.defineId(WitherStormSegmentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(WitherStormSegmentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COLLAPSE = SynchedEntityData.defineId(WitherStormSegmentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAM_0 = SynchedEntityData.defineId(WitherStormSegmentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAM_1 = SynchedEntityData.defineId(WitherStormSegmentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAM_2 = SynchedEntityData.defineId(WitherStormSegmentEntity.class, EntityDataSerializers.INT);
    private static final DustParticleOptions BEAM_DUST = new DustParticleOptions(0xA448E8, 1.0F);

    public WitherStormSegmentEntity(EntityType<? extends WitherBoss> type, Level level) {
        super(type, level);
        setNoAi(true);
        setNoGravity(true);
        noPhysics = true;
        refreshDimensions();
    }
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(INDEX, 0);
        builder.define(PHASE, 6);
        builder.define(COLLAPSE, 0);
        builder.define(BEAM_0, 0); builder.define(BEAM_1, 0); builder.define(BEAM_2, 0);
    }
    public void attachTo(WitherStormEntity parent, int index) {
        this.parentId = parent.getUUID();
        this.entityData.set(INDEX, Math.clamp(index, 0, 1));
        this.entityData.set(PHASE, parent.getPhase());
        this.entityData.set(COLLAPSE, parent.getCollapseState());
    }
    public int getSegmentIndex() { return this.entityData.get(INDEX); }
    public int getSegmentPhase() { return this.entityData.get(PHASE); }
    @Override protected EntityDimensions getDefaultDimensions(Pose pose) {
        return EntityDimensions.scalable(15.0F, 17.5F).withEyeHeight(10.0F);
    }

    @Override public void aiStep() {
        super.aiStep();
        if (level().isClientSide()) { showBeams(); return; }
        if (!(level() instanceof ServerLevel server) || !isAlive()) return;
        Entity found = parentId == null ? null : server.getEntity(parentId);
        if (!(found instanceof WitherStormEntity parent)) { clearBeams(); return; } // Parent may be unloaded.
        if (!parent.isAlive() || parent.getPhase() < 6) { discard(); return; }
        if (tickCount % 20 == 1 && StormConfig.TEMPORARY_CHUNK_LOADING.get()) {
            server.getChunkSource().addTicketWithRadius(WitherStormModTickets.STORM.get(), chunkPosition(),
                    Math.min(1, StormConfig.TEMPORARY_CHUNK_RADIUS.get()));
        }
        this.entityData.set(PHASE, parent.getPhase());
        this.entityData.set(COLLAPSE, parent.getCollapseState());
        Vec3 offset = parent.segmentDestination(getSegmentIndex());
        Vec3 delta = offset.subtract(position());
        if (delta.lengthSqr() > 0.01) move(MoverType.SELF, delta.normalize().scale(Math.min(0.5, delta.length() * 0.1)));
        setDeltaMovement(Vec3.ZERO);
        yBodyRot = parent.yBodyRot;
        if (parent.getCollapseState() != 0) { clearBeams(); return; }
        boolean otherHeads = parent.getPhase() > 6 || parent.getConsumedEntities() > StormEvolutionRules.subphaseRequirement(6, parent.getEvolutionSpeedModifier());
        if (tickCount % 20 == 1) selectTargets(server, parent, otherHeads);
        pullTargets(server, parent, otherHeads);
    }

    private static EntityDataAccessor<Integer> beamData(int head) {
        return switch (head) { case 0 -> BEAM_0; case 1 -> BEAM_1; case 2 -> BEAM_2; default -> throw new IllegalArgumentException(); };
    }

    private void clearBeams() {
        for (int i = 0; i < 3; i++) { this.entityData.set(beamData(i), 0); if (i > 0) setAlternativeTarget(i, 0); }
    }

    private void selectTargets(ServerLevel level, WitherStormEntity parent, boolean otherHeads) {
        double range = 160.0;
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(range),
                target -> parent.canSelectTarget(target) && target.distanceToSqr(this) <= range * range);
        targets.sort(Comparator.<LivingEntity>comparingInt(target -> target instanceof Player ? 0 : 1)
                .thenComparingDouble(target -> target.distanceToSqr(this)));
        Set<Integer> selected = new HashSet<>();
        for (int head = 0; head < 3; head++) {
            int id = head > 0 && !otherHeads ? 0 : targets.stream()
                    .filter(target -> !selected.contains(target.getId()) && hasLineOfSight(target))
                    .findFirst().map(Entity::getId).orElse(0);
            if (id != 0 && this.entityData.get(beamData(head)) != id && level.getEntity(id) instanceof LivingEntity target)
                WitherSicknessTracker.countContact(target);
            this.entityData.set(beamData(head), id);
            if (head > 0) setAlternativeTarget(head, id);
            if (id != 0) selected.add(id);
        }
    }

    private Vec3 headPosition(int head) {
        double x = head == 1 ? 6.0 : head == 2 ? -6.0 : 0.0;
        double y = head == 0 ? 9.0 : 8.0;
        double z = head == 0 ? 14.0 : 12.0;
        float yaw = yBodyRot * Mth.DEG_TO_RAD;
        return position().add(Mth.cos(yaw + Mth.PI) * x + Mth.cos(yaw + 1.5F * Mth.PI) * z,
                y, Mth.sin(yaw + Mth.PI) * x + Mth.sin(yaw + 1.5F * Mth.PI) * z);
    }

    private void pullTargets(ServerLevel level, WitherStormEntity parent, boolean otherHeads) {
        for (int head = 0; head < 3; head++) {
            int id = this.entityData.get(beamData(head));
            Entity entity = id == 0 ? null : level.getEntity(id);
            if (head > 0 && !otherHeads || !(entity instanceof LivingEntity target) || !parent.canSelectTarget(target)
                    || target.distanceToSqr(this) > 160.0 * 160.0) {
                this.entityData.set(beamData(head), 0);
                if (head > 0) setAlternativeTarget(head, 0);
                continue;
            }
            Vec3 start = headPosition(head);
            Vec3 direction = target.getBoundingBox().getCenter().subtract(start).normalize();
            target.setDeltaMovement(TractorBeamGeometry.pullVelocity(target.position(), start, direction, -1.0,
                    StormConfig.TRACTOR_PULL_SPEED.get(), target instanceof Player));
            if (target instanceof ServerPlayer player) player.connection.send(new ClientboundSetEntityMotionPacket(player));
            AABB contact = new AABB(start.x - 2, start.y - 4, start.z - 2, start.x + 2, start.y + 2, start.z + 2);
            if (!contact.intersects(target.getBoundingBox()) || target.isDeadOrDying()) continue;
            if (target instanceof Player player) {
                player.hurtServer(level, WitherStormModDamageTypes.source(level, WitherStormModDamageTypes.WITHER_STORM_ATTACK, this), 3.5F);
                if (player.isDeadOrDying()) parent.recordSegmentConsumption(player, 1);
            } else {
                parent.recordSegmentConsumption(target, 1);
                target.hurtServer(level, WitherStormModDamageTypes.source(level, WitherStormModDamageTypes.WITHER_STORM_ATTACK_MOB, this), Float.MAX_VALUE);
            }
        }
    }

    private void showBeams() {
        if (tickCount % 3 != 0) return;
        for (int head = 0; head < 3; head++) {
            int id = this.entityData.get(beamData(head));
            Entity target = id == 0 ? null : level().getEntity(id);
            if (target == null || !target.isAlive()) continue;
            Vec3 start = headPosition(head), delta = target.getBoundingBox().getCenter().subtract(start);
            int samples = Math.min(24, Math.max(4, (int) (delta.length() * 0.6)));
            for (int i = 1; i < samples; i++) {
                Vec3 point = start.add(delta.scale((double) i / samples));
                level().addParticle(BEAM_DUST, point.x, point.y, point.z, 0.0, 0.0, 0.0);
            }
        }
    }
    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (StormConfig.STORM_INVULNERABILITY.get() && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return false;
        return super.hurtServer(level, source, damage);
    }
    // The original displays the main storm's boss bar rather than another vanilla Wither bar.
    @Override public void startSeenByPlayer(ServerPlayer player) { }
    @Override public void stopSeenByPlayer(ServerPlayer player) { }
    @Override protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean killedByPlayer) { }
    @Override public void remove(Entity.RemovalReason reason) {
        if (reason.shouldDestroy() && parentId != null && level() instanceof ServerLevel server
                && server.getEntity(parentId) instanceof WitherStormEntity parent) {
            parent.onSegmentRemoved(getSegmentIndex(), getUUID());
        }
        super.remove(reason);
    }
    @Override protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        if (parentId != null) output.store("Parent", UUIDUtil.CODEC, parentId);
        output.putInt("SegmentIndex", getSegmentIndex());
        output.putInt("SegmentPhase", getSegmentPhase());
        output.putInt("SegmentCollapse", this.entityData.get(COLLAPSE));
    }
    @Override protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        parentId = input.read("Parent", UUIDUtil.CODEC).orElse(null);
        this.entityData.set(INDEX, Math.clamp(input.getIntOr("SegmentIndex", 0), 0, 1));
        this.entityData.set(PHASE, Math.clamp(input.getIntOr("SegmentPhase", 6), 6, 7));
        this.entityData.set(COLLAPSE, Math.clamp(input.getIntOr("SegmentCollapse", 0), 0, 3));
        setNoAi(true); setNoGravity(true); noPhysics = true;
    }
}
