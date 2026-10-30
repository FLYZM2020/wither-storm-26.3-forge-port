package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.fish.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.squid.Squid;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import nonamecrackers2.witherstormmod.common.evolution.StormEvolutionRules;
import nonamecrackers2.witherstormmod.common.beam.TractorBeamGeometry;
import nonamecrackers2.witherstormmod.common.config.StormConfig;
import nonamecrackers2.witherstormmod.common.sickness.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSounds;
import nonamecrackers2.witherstormmod.common.init.WitherStormModTickets;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
import nonamecrackers2.witherstormmod.common.terrain.TemporaryBlockAbsorption;
import net.minecraftforge.event.ForgeEventFactory;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormConsumeEvent;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormChangePhaseEvent;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormEvolveEvent;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormModifyEvolutionSpeedEvent;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Comparator;
import java.util.List;

/**
 * Early 26.3 entity shell. Uses vanilla Wither AI temporarily;
 * original tractor beams, head management and later phases are not yet ported.
 */
public final class WitherStormEntity extends WitherBoss {
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(WitherStormEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CONSUMED = SynchedEntityData.defineId(WitherStormEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAM_TARGET = SynchedEntityData.defineId(WitherStormEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> LEFT_BEAM_TARGET = SynchedEntityData.defineId(WitherStormEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> RIGHT_BEAM_TARGET = SynchedEntityData.defineId(WitherStormEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> VISUAL_EVOLUTION_SPEED = SynchedEntityData.defineId(WitherStormEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> COLLAPSE_STATE = SynchedEntityData.defineId(WitherStormEntity.class, EntityDataSerializers.INT);
    private static final int NORMAL = 0, FALLING = 1, PLAYING_DEAD = 2, REVIVING = 3;
    private static final DustParticleOptions BEAM_DUST = new DustParticleOptions(0xA448E8, 1.0F);
    private final Set<UUID> trackedItems = new HashSet<>();
    private final Map<UUID, Integer> missingItemsSince = new HashMap<>();
    private final TemporaryBlockAbsorption temporaryBlocks = new TemporaryBlockAbsorption();
    private int collapseTicks;
    private boolean noAiBeforeCollapse;
    private int symbiontCooldown;
    private final UUID[] segmentIds = new UUID[2];

    public WitherStormEntity(EntityType<? extends WitherBoss> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(PHASE, 0);
        builder.define(CONSUMED, 0);
        builder.define(BEAM_TARGET, 0);
        builder.define(LEFT_BEAM_TARGET, 0);
        builder.define(RIGHT_BEAM_TARGET, 0);
        builder.define(VISUAL_EVOLUTION_SPEED, 1.0F);
        builder.define(COLLAPSE_STATE, NORMAL);
    }

    public int getPhase() { return this.entityData.get(PHASE); }
    public int getConsumedEntities() { return this.entityData.get(CONSUMED); }
    public float getVisualEvolutionSpeed() { return this.entityData.get(VISUAL_EVOLUTION_SPEED); }
    public int getCollapseState() { return this.entityData.get(COLLAPSE_STATE); }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        EntityDimensions dimensions = switch (getPhase()) {
            case 4 -> EntityDimensions.scalable(10.0F, 30.0F);
            case 5 -> EntityDimensions.scalable(10.0F, 60.0F);
            case 6 -> EntityDimensions.scalable(15.0F, 90.0F);
            case 7 -> EntityDimensions.scalable(15.0F, 120.0F);
            default -> null;
        };
        if (dimensions == null) return super.getDefaultDimensions(pose);
        if (getCollapseState() == PLAYING_DEAD) return EntityDimensions.scalable(dimensions.width(), 0.1F).withEyeHeight(0.085F);
        return dimensions.withEyeHeight(17.5F);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        super.onSyncedDataUpdated(accessor);
        if (PHASE.equals(accessor) || COLLAPSE_STATE.equals(accessor)) refreshDimensions();
    }

    @Override
    public void refreshDimensions() {
        Vec3 previous = position();
        super.refreshDimensions();
        setPos(previous.x, previous.y, previous.z);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double size = this.getBoundingBox().getSize();
        if (Double.isNaN(size)) size = 1.0;
        double range = size * 248.0 * Entity.getViewScale();
        return distance < range * range;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level().isClientSide()) {
            showEarlyBeam();
            return;
        }
        if (!this.isAlive()) return;
        if (this.tickCount % 20 == 1) this.entityData.set(VISUAL_EVOLUTION_SPEED, (float) getEvolutionSpeedModifier());
        if (this.tickCount % 20 == 1 && StormConfig.TEMPORARY_CHUNK_LOADING.get()
                && this.level() instanceof ServerLevel server) {
            // No explicit removal: shared same-chunk tickets safely expire if no storm refreshes them.
            server.getChunkSource().addTicketWithRadius(WitherStormModTickets.STORM.get(),
                    this.chunkPosition(), StormConfig.TEMPORARY_CHUNK_RADIUS.get());
        }
        if (getPhase() >= 6 && tickCount % 40 == 1 && this.level() instanceof ServerLevel server) ensureSegments(server);
        if (getCollapseState() != NORMAL && this.level() instanceof ServerLevel server) {
            tickCollapse(server);
            return;
        }
        if (getPhase() >= 4 && StormConfig.STORM_INVULNERABILITY.get() && this.tickCount % 20 == 0) this.heal(10.0F);
        if (getPhase() >= 4 && this.level() instanceof ServerLevel server) flyLargeStorm(server);
        if (this.level() instanceof ServerLevel server) tickSymbiontSummoning(server);
        if (this.tickCount % 20 == 1 && getPhase() > 1 && StormConfig.SICKNESS_ENABLED.get()
                && this.level() instanceof ServerLevel server) {
            double range = getPhase() > 3 ? getAttributeValue(Attributes.FOLLOW_RANGE)
                    : getAttributeValue(WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.getHolder().orElseThrow());
            double vertical = getPhase() > 3 ? range + 255.0 : range * 2.0;
            for (LivingEntity entity : server.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(range, vertical, range))) {
                if (entity != this) WitherSicknessTracker.markNear(entity, server.getLevelData().getGameTime());
            }
        }
        int previous = getConsumedEntities();
        if (this.tickCount % 20 == 1 && this.level() instanceof ServerLevel server) updateTemporaryTargets(server);
        pullTargets();
        if (this.level() instanceof ServerLevel server) {
            pullItems(server);
            temporaryBlocks.tick(this, server);
        }
        checkEvolution(previous);
    }

    /** Original segment offsets, with a closer resting formation during the collapsed state. */
    public Vec3 segmentDestination(int index) {
        if (index < 0 || index > 1) throw new IllegalArgumentException("Invalid segment: " + index);
        boolean resting = getCollapseState() == PLAYING_DEAD;
        double x = resting ? 45.0 : 75.0;
        double z = resting ? 0.0 : index == 0 ? 50.0 : -50.0;
        double angle = (this.yBodyRot + 180.0 * index) * Mth.DEG_TO_RAD + Math.atan2(z, x);
        double length = Math.sqrt(x * x + z * z);
        double y = resting ? getBoundingBox().getCenter().y + 10.0 : getEyeY();
        return new Vec3(getX() + Math.cos(angle) * length, y, getZ() + Math.sin(angle) * length);
    }

    private void ensureSegments(ServerLevel level) {
        for (int index = 0; index < segmentIds.length; index++) {
            // A saved UUID may refer to an unloaded segment; do not create a duplicate.
            if (segmentIds[index] != null) continue;
            Vec3 destination = segmentDestination(index);
            if (!level.hasChunkAt(BlockPos.containing(destination))) continue;
            WitherStormSegmentEntity segment = WitherStormMod.WITHER_STORM_SEGMENT.get().create(level, EntitySpawnReason.TRIGGERED);
            if (segment == null) continue;
            segment.setPos(destination);
            segment.yBodyRot = this.yBodyRot;
            segment.attachTo(this, index);
            segment.setPersistenceRequired();
            if (level.addFreshEntity(segment)) segmentIds[index] = segment.getUUID();
        }
    }

    private void removeLoadedSegments(ServerLevel level) {
        for (int i = 0; i < segmentIds.length; i++) {
            if (segmentIds[i] != null && level.getEntity(segmentIds[i]) instanceof WitherStormSegmentEntity segment) {
                segment.discard();
                segmentIds[i] = null;
            }
        }
    }

    public void onSegmentRemoved(int index, UUID id) {
        if (index >= 0 && index < segmentIds.length && id.equals(segmentIds[index])) segmentIds[index] = null;
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        if (reason.shouldDestroy() && this.level() instanceof ServerLevel server) removeLoadedSegments(server);
        super.remove(reason);
    }

    /** Approximate original spawn conditions; the spawned mob is a temporary melee stand-in. */
    private void tickSymbiontSummoning(ServerLevel level) {
        if (symbiontCooldown > 0) { symbiontCooldown--; return; }
        if (tickCount % 1200 != 0 || getPhase() < 5
                || getConsumedEntities() < StormEvolutionRules.consumptionRequirement(5, getEvolutionSpeedModifier())) return;
        double radius = getAttributeValue(Attributes.FOLLOW_RANGE) + 50.0;
        AABB area = getBoundingBox().inflate(radius);
        if (!level.getEntitiesOfClass(WitheredSymbiontEntity.class, area, Entity::isAlive).isEmpty()
                || !level.getEntitiesOfClass(FormidibombEntity.class, area, Entity::isAlive).isEmpty()) return;
        ServerPlayer player = level.players().stream().filter(p -> p.isAlive() && !p.isSpectator() && !p.isCreative()
                && p.distanceToSqr(this) <= radius * radius
                && !p.getInventory().contains(new ItemStack(WitherStormModItems.COMMAND_BLOCK_BOOK.get()))
                && !p.getInventory().contains(new ItemStack(WitherStormModItems.FORMIDIBOMB.get())))
                .min(Comparator.comparingDouble(p -> p.distanceToSqr(this))).orElse(null);
        if (player == null) return;
        for (int attempt = 0; attempt < 10; attempt++) {
            int x = player.getBlockX() + random.nextInt(25) - 12;
            int z = player.getBlockZ() + random.nextInt(25) - 12;
            if (!level.hasChunkAt(new BlockPos(x, player.getBlockY(), z))) continue;
            int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
            BlockPos pos = new BlockPos(x, y, z);
            if (!level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) continue;
            WitheredSymbiontEntity mob = WitherStormMod.WITHERED_SYMBIONT.get().create(level, EntitySpawnReason.TRIGGERED);
            if (mob == null) return;
            mob.setPos(x + 0.5, y, z + 0.5);
            if (!level.noCollision(mob)) continue;
            mob.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
            mob.setTarget(player);
            mob.setPersistenceRequired();
            if (level.addFreshEntity(mob)) {
                symbiontCooldown = 12000 + random.nextInt(12000);
                return;
            }
        }
    }

    /** Begin the original fall/play-dead sequence, without the not-yet-ported segments or podium. */
    public boolean beginCollapse() {
        if (!(this.level() instanceof ServerLevel server) || !isAlive() || getPhase() < 4 || getCollapseState() != NORMAL) return false;
        noAiBeforeCollapse = isNoAi();
        setNoAi(true);
        setTarget(null);
        for (int head = 0; head < 3; head++) {
            setAlternativeTarget(head, 0);
            this.entityData.set(beamTargetData(head), 0);
        }
        trackedItems.clear();
        missingItemsSince.clear();
        temporaryBlocks.release(server, this.tickCount);
        collapseTicks = 0;
        this.entityData.set(COLLAPSE_STATE, FALLING);
        return true;
    }

    /** The command block encounter is not ported; revival is currently explicit. */
    public boolean beginRevival() {
        if (this.level().isClientSide() || getCollapseState() != PLAYING_DEAD) return false;
        collapseTicks = 0;
        this.entityData.set(COLLAPSE_STATE, REVIVING);
        return true;
    }

    private void tickCollapse(ServerLevel level) {
        collapseTicks++;
        if (this.tickCount % 20 == 0) temporaryBlocks.release(level, this.tickCount);
        if (getCollapseState() == FALLING) {
            move(MoverType.SELF, new Vec3(0.0, -Math.min(1.0, 0.08 + collapseTicks * 0.01), 0.0));
            setDeltaMovement(Vec3.ZERO);
            if (getPhase() == 5 && (collapseTicks >= 201 || onGround())) setPhase(6);
            if (onGround()) {
                collapseTicks = 0;
                this.entityData.set(COLLAPSE_STATE, PLAYING_DEAD);
            }
        } else if (getCollapseState() == PLAYING_DEAD) {
            setDeltaMovement(Vec3.ZERO);
            if (StormConfig.REVIVAL_TIMER_ENABLED.get() && collapseTicks > StormConfig.REVIVAL_TIME_MINUTES.get() * 1200) beginRevival();
        } else if (getCollapseState() == REVIVING && collapseTicks > 20) {
            collapseTicks = 0;
            this.entityData.set(COLLAPSE_STATE, NORMAL);
            setNoAi(noAiBeforeCollapse);
        }
    }

    /** Simplified late-stage hover/chase; the original terrain and ultimate-target controllers are not ported. */
    private void flyLargeStorm(ServerLevel level) {
        Vec3 velocity = getDeltaMovement();
        int ground = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, getBlockX(), getBlockZ());
        double desiredY = Math.min(ground + StormConfig.FLYING_HEIGHT.get(), level.getMaxY() - 1);
        double y = velocity.y;
        if (getY() < desiredY) y = Mth.clamp((desiredY - getY()) * 0.005, 0.0, 0.4);
        double x = velocity.x * 0.6, z = velocity.z * 0.6;
        LivingEntity target = getTarget();
        if (target != null && target.isAlive()) {
            Vec3 horizontal = new Vec3(target.getX() - getX(), 0.0, target.getZ() - getZ());
            if (horizontal.horizontalDistanceSqr() > 6000.0) {
                double speed = getAttributeValue(WitherStormModAttributes.SLOW_FLYING_SPEED.getHolder().orElseThrow());
                Vec3 direction = horizontal.normalize();
                x += direction.x * speed;
                z += direction.z * speed;
            }
        }
        setDeltaMovement(x, y, z);
    }

    /** Temporary target acquisition using the old blacklist and basic entity exclusions. */
    private void updateTemporaryTargets(ServerLevel level) {
        double range = getPhase() >= 4 ? getAttributeValue(Attributes.FOLLOW_RANGE)
                : getAttributeValue(WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.getHolder().orElseThrow());
        LivingEntity current = getTarget();
        if (current != null && (!canSelectTarget(current) || current.distanceToSqr(this) > range * range)) {
            setTarget(null);
            current = null;
        }
        List<LivingEntity> candidates = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(range),
                target -> canSelectTarget(target) && target.distanceToSqr(this) <= range * range);
        candidates.sort(Comparator.<LivingEntity>comparingInt(target -> target instanceof Player ? 0 : 1)
                .thenComparingDouble(target -> target.distanceToSqr(this)));
        Set<Integer> chosen = new HashSet<>();
        if (current == null) {
            current = candidates.stream().filter(this::hasLineOfSight).findFirst().orElse(null);
            setTarget(current);
        }
        if (current != null) chosen.add(current.getId());
        if (getPhase() < 4) return;
        for (int head = 1; head < 3; head++) {
            Entity entity = level.getEntity(getAlternativeTarget(head));
            if (entity instanceof LivingEntity living && canSelectTarget(living)
                    && living.distanceToSqr(this) <= range * range && chosen.add(living.getId())) continue;
            int next = candidates.stream().filter(candidate -> !chosen.contains(candidate.getId()) && hasLineOfSight(candidate))
                    .findFirst().map(Entity::getId).orElse(0);
            setAlternativeTarget(head, next);
            if (next != 0) chosen.add(next);
        }
    }

    public boolean canSelectTarget(LivingEntity target) {
        return target != this && target.isAlive() && target.isAttackable() && !target.isSpectator()
                && !(target instanceof Player player && player.isCreative())
                && !(target instanceof WitherBoss || target instanceof WitheredSymbiontEntity || target instanceof EnderDragon || target instanceof AmbientCreature || target instanceof ArmorStand)
                && !(getPhase() < 4 && (target instanceof AbstractSchoolingFish || target instanceof Squid))
                && !target.typeHolder().is(WitherStormModEntityTags.WITHER_STORM_TARGETING_BLACKLIST);
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return super.hurtServer(level, source, damage);
        if (source.is(DamageTypes.DROWN) || source.getEntity() instanceof WitherStormEntity
                || getPhase() >= 4 && StormConfig.STORM_INVULNERABILITY.get()) return false;
        return super.hurtServer(level, source, damage);
    }

    /** Approximate item tracking for all stages; cluster tracking remains unported. */
    private void pullItems(ServerLevel level) {
        double radius = getPhase() >= 4 ? 80.0 : Math.min(48, 12 + Math.round(getConsumedEntities() * 0.00445));
        if (this.tickCount % 10 == 0 && trackedItems.size() < 64 && ForgeEventFactory.getMobGriefingEvent(level, this)) {
            for (ItemEntity item : level.getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(radius))) {
                if (trackedItems.size() >= 64) break;
                if (!item.isAlive() || item.getItem().isEmpty() || item.distanceToSqr(this) > radius * radius) continue;
                double unappetizingRadius = getPhase() >= 4 ? 35.0 : 2.0;
                if (item.getItem().is(WitherStormModItemTags.COMMAND_BLOCK_TOOLS)
                        || item.getItem().is(WitherStormModItemTags.UNAPPETIZING)
                        && item.distanceToSqr(this) >= unappetizingRadius * unappetizingRadius) continue;
                var id = BuiltInRegistries.ITEM.getKey(item.getItem().getItem());
                if (id.equals(nonamecrackers2.witherstormmod.WitherStormMod.id("command_block_book"))
                        || id.equals(nonamecrackers2.witherstormmod.WitherStormMod.id("withered_nether_star"))) continue;
                trackedItems.add(item.getUUID());
            }
        }
        Vec3 absorption = getBoundingBox().getCenter();
        trackedItems.removeIf(id -> {
            Entity found = level.getEntity(id);
            if (found == null) {
                int firstMissingTick = missingItemsSince.computeIfAbsent(id, unused -> this.tickCount);
                if (this.tickCount - firstMissingTick < 1200) return false;
                missingItemsSince.remove(id);
                return true;
            }
            missingItemsSince.remove(id);
            if (!(found instanceof ItemEntity item) || !item.isAlive() || item.getItem().isEmpty()) return true;
            if (getBoundingBox().contains(item.position())) {
                recordConsumption(item, item.getItem().getCount());
                item.discard();
                return true;
            }
            item.setDeltaMovement(absorption.subtract(item.position()).normalize().scale(0.5));
            if (this.tickCount % 5 == 0) {
                level.getChunkSource().sendToTrackingPlayers(item, new ClientboundSetEntityMotionPacket(item));
            }
            return false;
        });
    }

    /** Uses vanilla Wither target selection until the original head manager is ported. */
    private void pullTargets() {
        double range = getPhase() >= 4 ? getAttributeValue(Attributes.FOLLOW_RANGE)
                : getAttributeValue(WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.getHolder().orElseThrow());
        Set<Integer> pulled = new HashSet<>();
        for (int head = 0; head < 3; head++) {
            Entity candidate = head == 0 ? getTarget() : this.level().getEntity(getAlternativeTarget(head));
            // Before stage 4, only the central beam is active in stages 2 and 3.
            if (getPhase() < 2 || getPhase() < 4 && head != 0
                    || !(candidate instanceof LivingEntity target) || !canSelectTarget(target)
                    || target.distanceToSqr(this) > range * range || !pulled.add(target.getId())) {
                this.entityData.set(beamTargetData(head), 0);
                continue;
            }
            if (this.entityData.get(beamTargetData(head)) != target.getId()) WitherSicknessTracker.countContact(target);
            this.entityData.set(beamTargetData(head), target.getId());
            Vec3 headPosition = headPosition(head);
            Vec3 direction = target.getBoundingBox().getCenter().subtract(headPosition).normalize();
            Vec3 velocity = TractorBeamGeometry.pullVelocity(target.position(), headPosition, direction,
                    -1.0, StormConfig.TRACTOR_PULL_SPEED.get(), target instanceof Player);
            target.setDeltaMovement(velocity);
            if (target instanceof ServerPlayer player) {
                player.connection.send(new ClientboundSetEntityMotionPacket(player));
            }
            if (this.level() instanceof ServerLevel server) applyHeadContact(server, target, headPosition);
        }
    }

    private static EntityDataAccessor<Integer> beamTargetData(int head) {
        return switch (head) {
            case 0 -> BEAM_TARGET;
            case 1 -> LEFT_BEAM_TARGET;
            case 2 -> RIGHT_BEAM_TARGET;
            default -> throw new IllegalArgumentException("Invalid head: " + head);
        };
    }

    private Vec3 headPosition(int head) {
        float yaw = this.yBodyRot * Mth.DEG_TO_RAD;
        if (getPhase() >= 4) {
            double x = head == 1 ? -12.0 : head == 2 ? 8.5 : 0.0;
            double y = head == 1 ? 22.5 : head == 2 ? 24.5 : 12.0;
            double z = head == 2 ? 16.0 : 10.0;
            float xAngle = yaw + Mth.PI;
            float zAngle = yaw + 1.5F * Mth.PI;
            return position().add(Mth.cos(xAngle) * x + Mth.cos(zAngle) * z, y,
                    Mth.sin(xAngle) * x + Mth.sin(zAngle) * z);
        }
        if (head == 0) {
            double forward = getPhase() >= 2 ? 0.5 * getScale() : 0.0;
            double height = (getPhase() >= 2 ? 2.75 : 3.0) * getScale();
            return position().add(-Mth.sin(yaw) * forward, height, Mth.cos(yaw) * forward);
        }
        float angle = yaw + (head - 1) * Mth.PI;
        return position().add(Mth.cos(angle) * 1.3 * getScale(), 2.2 * getScale(),
                Mth.sin(angle) * 1.3 * getScale());
    }

    public Vec3 getTractorHeadPosition(int head) {
        if (head < 0 || head > 2) throw new IllegalArgumentException("Invalid head: " + head);
        return headPosition(head);
    }

    /** Early head contact, based on the old head's collision box and damage. */
    private void applyHeadContact(ServerLevel level, LivingEntity target, Vec3 head) {
        AABB contact = new AABB(head.x - 2.0, head.y - 4.0, head.z - 2.0,
                head.x + 2.0, head.y + 2.0, head.z + 2.0);
        if (!contact.intersects(target.getBoundingBox()) || target.isDeadOrDying()) return;
        if (target instanceof Player player) {
            player.hurtServer(level, WitherStormModDamageTypes.source(level, WitherStormModDamageTypes.WITHER_STORM_ATTACK, this),
                    (float) getAttributeValue(Attributes.ATTACK_DAMAGE));
            if (player.isDeadOrDying()) recordConsumption(player, 1);
        } else {
            recordConsumption(target, 1);
            target.hurtServer(level, WitherStormModDamageTypes.source(level, WitherStormModDamageTypes.WITHER_STORM_ATTACK_MOB, this),
                    Float.MAX_VALUE);
        }
    }

    /** Low-cost temporary visual; the original translucent beam renderer is not ported yet. */
    private void showEarlyBeam() {
        if (this.tickCount % 3 != 0) return;
        for (int head = 0; head < 3; head++) {
            int targetId = this.entityData.get(beamTargetData(head));
            Entity target = targetId == 0 ? null : this.level().getEntity(targetId);
            if (target == null || !target.isAlive()) continue;
            Vec3 start = headPosition(head);
            Vec3 delta = target.getBoundingBox().getCenter().subtract(start);
            int samples = Math.min(24, Math.max(4, (int) (delta.length() * 0.6)));
            for (int i = 1; i < samples; i++) {
                Vec3 point = start.add(delta.scale((double) i / samples));
                this.level().addParticle(BEAM_DUST, point.x, point.y, point.z, 0.0, 0.0, 0.0);
            }
        }
    }

    /** Called by server-side consumption logic. */
    public void recordConsumption(Entity consumedEntity, int amount) {
        if (this.level().isClientSide() || amount <= 0) return;
        WitherStormConsumeEvent event = new WitherStormConsumeEvent(this, consumedEntity, amount);
        if (WitherStormConsumeEvent.BUS.post(event)) return;
        amount = event.getConsumedAmount();
        if (amount <= 0) return;
        int consumed = (int) Math.min(Integer.MAX_VALUE, (long) getConsumedEntities() + amount);
        this.entityData.set(CONSUMED, consumed);
    }

    /** Segments tick independently, so their consumption needs an immediate evolution check. */
    public void recordSegmentConsumption(Entity consumedEntity, int amount) {
        int previous = getConsumedEntities();
        recordConsumption(consumedEntity, amount);
        checkEvolution(previous);
    }

    private void checkEvolution(int previous) {
        int phase = getPhase();
        double speed = getEvolutionSpeedModifier();
        if (StormEvolutionRules.shouldCheckEvolution(!this.isAlive(), getConsumedEntities(), previous, phase, speed)) {
            evolve(false);
        }
    }

    public double getEvolutionSpeedModifier() {
        var holder = WitherStormModAttributes.EVOLUTION_SPEED.getHolder().orElseThrow();
        double value = getAttributeValue(holder);
        double modifier = value != holder.value().getDefaultValue() ? value : StormConfig.EVOLUTION_SPEED.get();
        var event = new WitherStormModifyEvolutionSpeedEvent(this, modifier);
        WitherStormModifyEvolutionSpeedEvent.BUS.post(event);
        return event.getOriginalEvolutionSpeedModifier();
    }

    public boolean evolve(boolean force) {
        if (this.level().isClientSide() || getCollapseState() != NORMAL || !StormEvolutionRules.canEvolve(getPhase(), force, false)) return false;
        int nextPhase = getPhase() + 1;
        WitherStormEvolveEvent.BUS.post(new WitherStormEvolveEvent(this, nextPhase));
        return setPhase(nextPhase);
    }

    public boolean setPhase(int phase) {
        int consumed = StormEvolutionRules.consumptionRequirement(phase - 1, getEvolutionSpeedModifier());
        return setPhase(phase, consumed);
    }

    public void setConsumedEntities(int amount) {
        if (this.level().isClientSide()) return;
        int previous = getConsumedEntities();
        this.entityData.set(CONSUMED, Math.max(0, amount));
        checkEvolution(previous);
    }

    /** Applies phase data; original model, head and segment updates are not yet ported. */
    public boolean setPhase(int phase, int consumed) {
        if (this.level().isClientSide() || phase < 0 || phase > 7) return false;
        WitherStormChangePhaseEvent.BUS.post(new WitherStormChangePhaseEvent(this, phase));
        this.entityData.set(PHASE, phase);
        this.entityData.set(CONSUMED, Math.max(0, consumed));
        if (phase < 6 && this.level() instanceof ServerLevel server) removeLoadedSegments(server);
        if (phase == 4) this.playSound(WitherStormModSounds.WITHER_STORM_EVOLVES.get(), 4.0F, 1.0F);
        return true;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Phase", getPhase());
        output.putInt("ConsumedEntities", getConsumedEntities());
        if (!trackedItems.isEmpty()) output.store("EarlyTrackedItems", UUIDUtil.CODEC_SET, trackedItems);
        temporaryBlocks.save(output);
        output.putInt("CollapseState", getCollapseState());
        output.putInt("CollapseTicks", collapseTicks);
        output.putBoolean("NoAiBeforeCollapse", noAiBeforeCollapse);
        output.putInt("SymbiontSummoningCooldown", symbiontCooldown);
        for (int i = 0; i < segmentIds.length; i++) if (segmentIds[i] != null) output.store("Segment" + i, UUIDUtil.CODEC, segmentIds[i]);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(PHASE, Math.clamp(input.getIntOr("Phase", 0), 0, 7));
        this.entityData.set(CONSUMED, Math.max(0, input.getIntOr("ConsumedEntities", 0)));
        trackedItems.clear();
        input.read("EarlyTrackedItems", UUIDUtil.CODEC_SET).ifPresent(ids -> ids.stream().limit(64).forEach(trackedItems::add));
        missingItemsSince.clear();
        temporaryBlocks.load(input);
        collapseTicks = Math.max(0, input.getIntOr("CollapseTicks", 0));
        noAiBeforeCollapse = input.getBooleanOr("NoAiBeforeCollapse", false);
        symbiontCooldown = Math.max(0, input.getIntOr("SymbiontSummoningCooldown", 0));
        for (int i = 0; i < segmentIds.length; i++) segmentIds[i] = input.read("Segment" + i, UUIDUtil.CODEC).orElse(null);
        this.entityData.set(COLLAPSE_STATE, Math.clamp(input.getIntOr("CollapseState", NORMAL), NORMAL, REVIVING));
        if (getCollapseState() != NORMAL) setNoAi(true);
    }
}
