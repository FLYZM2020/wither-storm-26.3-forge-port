package nonamecrackers2.witherstormmod.common.entity;

import java.util.UUID;
import java.util.EnumSet;
import java.util.List;
import java.util.ArrayList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import nonamecrackers2.witherstormmod.WitherStormMod;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent.BossBarColor;
import net.minecraft.world.BossEvent.BossBarOverlay;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSounds;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Enderman;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.projectile.hurtingprojectile.WitherSkull;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

/** Early symbiont with melee and simplified versions of three spells. */
public final class WitheredSymbiontEntity extends WitherSkeleton {
    private static final EntityDataAccessor<Boolean> PULLING = SynchedEntityData.defineId(WitheredSymbiontEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CASTING = SynchedEntityData.defineId(WitheredSymbiontEntity.class, EntityDataSerializers.BOOLEAN);
    private final ServerBossEvent bossBar = new ServerBossEvent(UUID.randomUUID(), getDisplayName(), BossBarColor.PURPLE, BossBarOverlay.PROGRESS);
    private int pulseCooldown = 200;
    private int pulseCharge;
    private int skullTicks;
    private boolean preparingSkulls;
    private int previousSpell = -1;
    private int pendingSpell;
    private int pullTicks;
    private int fangsTicks;
    private int arrowTicks;
    private final List<Arrow> spellArrows = new ArrayList<>();

    public WitheredSymbiontEntity(EntityType<? extends WitherSkeleton> type, Level level) {
        super(type, level);
    }

    @Override protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new Goal() {
            { setFlags(EnumSet.of(Flag.MOVE)); }
            @Override public boolean canUse() { return skullTicks > 0 || pullTicks > 0 || fangsTicks > 0 || arrowTicks > 0; }
            @Override public boolean canContinueToUse() { return canUse(); }
            @Override public void start() { getNavigation().stop(); }
            @Override public void tick() { getNavigation().stop(); }
        });
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(PULLING, false);
        builder.define(CASTING, false);
    }
    public boolean isPulling() { return entityData.get(PULLING); }
    public boolean isCasting() { return entityData.get(CASTING); }

    @Override public void aiStep() {
        super.aiStep();
        if (!level().isClientSide()) {
            bossBar.setProgress(Math.clamp(getHealth() / getMaxHealth(), 0.0F, 1.0F));
            bossBar.setName(getDisplayName());
            if (isAlive() && level() instanceof ServerLevel server) tickPulse(server);
            entityData.set(PULLING, isAlive() && pullTicks > 0);
            entityData.set(CASTING, isAlive() && (pullTicks > 0 || skullTicks > 0 || fangsTicks > 0 || arrowTicks > 0));
            if (isCasting() && level() instanceof ServerLevel server) showCastingParticles(server);
        }
    }

    /** The original emits command particles towards the symbiont during each spell. */
    private void showCastingParticles(ServerLevel level) {
        for (int i = 0; i < 5; i++) {
            double x = getX() + random.nextGaussian() * 2.0;
            double y = getEyeY() + random.nextGaussian() * 2.0;
            double z = getZ() + random.nextGaussian() * 2.0;
            Vec3 direction = getEyePosition().subtract(x, y, z).normalize().scale(0.2);
            level.sendParticles(WitherStormMod.COMMAND_PARTICLE.get(), x, y, z, 0, direction.x, direction.y, direction.z, 1.0);
        }
    }

    /** A small first step toward the original pulse spell; terrain effects are unported. */
    private void tickPulse(ServerLevel level) {
        if (getTarget() == null || !getTarget().isAlive()) {
            if (pulseCharge > 0 || skullTicks > 0 || pullTicks > 0 || fangsTicks > 0 || arrowTicks > 0) { pulseCharge = 0; skullTicks = 0; pullTicks = 0; fangsTicks = 0; arrowTicks = 0; clearSpellArrows(); pulseCooldown = 100; }
            return;
        }
        if (arrowTicks > 0) {
            tickArrows(level, getTarget());
            if (--arrowTicks == 0) { clearSpellArrows(); pulseCooldown = 160 + random.nextInt(40); }
            return;
        }
        if (fangsTicks > 0) {
            if (--fangsTicks == 0) {
                castFangs(level, getTarget());
                pulseCooldown = 60 + random.nextInt(40);
            }
            return;
        }
        if (pullTicks > 0) {
            double range = getAttributeValue(Attributes.FOLLOW_RANGE);
            TargetingConditions conditions = TargetingConditions.forCombat().range(range);
            for (LivingEntity victim : level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(range),
                    e -> e != this && conditions.test(level, this, e)
                            && !(e instanceof WitherBoss || e instanceof WitherSkeleton || e instanceof Enderman || e instanceof EnderDragon)
                            && !e.typeHolder().is(WitherStormModEntityTags.SICKENED_MOBS)
                            && (e instanceof AbstractVillager || e instanceof AbstractGolem || e instanceof Monster || e instanceof Animal || e instanceof NeutralMob || e instanceof Player)
                            && !(e instanceof Player p && p.isCreative()))) {
                if (victim.distanceToSqr(this) < 9.0) { pullTicks = 0; break; }
                Vec3 pull = position().subtract(victim.position()).normalize().scale(0.1);
                victim.setDeltaMovement(pull.x, victim.getDeltaMovement().y, pull.z);
                if (victim instanceof ServerPlayer player) player.connection.send(new ClientboundSetEntityMotionPacket(player));
                double x = victim.getX() + random.nextGaussian() * victim.getBoundingBox().getXsize() * 0.4;
                double y = victim.getBoundingBox().getCenter().y + random.nextGaussian() * victim.getBoundingBox().getYsize() * 0.4;
                double z = victim.getZ() + random.nextGaussian() * victim.getBoundingBox().getZsize() * 0.4;
                Vec3 direction = getEyePosition().subtract(x, y, z).normalize().scale(0.1);
                level.sendParticles(WitherStormMod.COMMAND_PARTICLE.get(), x, y, z, 0, direction.x, direction.y, direction.z, 1.0);
            }
            if (pullTicks > 0) pullTicks--;
            if (pullTicks == 0) pulseCooldown = 340 + random.nextInt(80);
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
                playSound(WitherStormModSounds.SYMBIONT_CAST.get(), 4.0F, 1.0F);
                if (pendingSpell == 4) {
                    previousSpell = 4;
                    arrowTicks = 120;
                    return;
                }
                if (pendingSpell == 3) {
                    previousSpell = 3;
                    fangsTicks = 20;
                    return;
                }
                if (pendingSpell == 2) {
                    previousSpell = 2;
                    pullTicks = 240;
                    return;
                }
                if (pendingSpell == 1) {
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
            pendingSpell = random.nextInt(previousSpell < 0 ? 5 : 4);
            if (previousSpell >= 0 && pendingSpell >= previousSpell) pendingSpell++;
            preparingSkulls = pendingSpell == 1;
            pulseCharge = 40 + random.nextInt(20);
            playSound(WitherStormModSounds.SYMBIONT_PREPARE.get(), 4.0F, 1.0F);
        }
    }

    private void tickArrows(ServerLevel level, LivingEntity target) {
        boolean difficult = getHealth() <= getMaxHealth() * 0.5F;
        int delay = difficult ? 10 : 20;
        if (tickCount % 4 == 0) {
            for (int i = 0; i < (difficult ? 6 : 3); i++) {
                Arrow arrow = new Arrow(level, this, new ItemStack(Items.ARROW), null);
                arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
                arrow.setNoGravity(true);
                Vec3 velocity = new Vec3(random.nextGaussian() * 0.55, random.nextDouble() * 0.75, random.nextGaussian() * 0.55);
                arrow.setDeltaMovement(velocity);
                arrow.setXRot((float) Math.toDegrees(Math.atan2(velocity.y, Math.sqrt(velocity.horizontalDistanceSqr()))));
                arrow.setYRot((float) Math.toDegrees(Math.atan2(velocity.x, velocity.z)));
                if (level.addFreshEntity(arrow)) spellArrows.add(arrow);
            }
            playSound(SoundEvents.DISPENSER_DISPENSE, 4.0F, 0.5F + (random.nextFloat() - 0.5F) * 0.1F);
        }
        for (Arrow arrow : spellArrows) {
            if (!arrow.isAlive()) continue;
            if (arrow.tickCount == delay) {
                LivingEntity selected = randomPlayerTarget(level, target);
                double x = selected.getX() + random.nextGaussian() * 2.0 - arrow.getX();
                double y = selected.getY(1.0 / 3.0) + random.nextGaussian() * 0.5 - arrow.getY();
                double z = selected.getZ() + random.nextGaussian() * 2.0 - arrow.getZ();
                arrow.shoot(x, y + Math.sqrt(x * x + z * z) * 0.2, z, 2.5F, 14 - level.getDifficulty().getId() * 4);
                arrow.setNoGravity(false);
                arrow.playSound(SoundEvents.ARROW_SHOOT, 2.0F, 1.0F);
            } else if (arrow.tickCount < delay) {
                double x = arrow.getX() + random.nextGaussian() * 0.5;
                double y = arrow.getEyeY() + random.nextGaussian() * 0.5;
                double z = arrow.getZ() + random.nextGaussian() * 0.5;
                Vec3 direction = arrow.getEyePosition().subtract(x, y, z).normalize().scale(0.1);
                level.sendParticles(WitherStormMod.COMMAND_PARTICLE.get(), x, y, z, 0, direction.x, direction.y, direction.z, 1.0);
            }
        }
        spellArrows.removeIf(Entity::isRemoved);
    }

    private void clearSpellArrows() {
        for (Arrow arrow : spellArrows) {
            if (arrow.isRemoved()) continue;
            if (level() instanceof ServerLevel server) server.sendParticles(ParticleTypes.LARGE_SMOKE, arrow.getX(), arrow.getY(), arrow.getZ(), 1, 0, 0, 0, 0);
            arrow.discard();
        }
        spellArrows.clear();
    }

    @Override public void remove(Entity.RemovalReason reason) {
        if (!level().isClientSide()) clearSpellArrows();
        super.remove(reason);
    }

    /** Original expanding fang rings; conversion-mob summons are not yet available. */
    private void castFangs(ServerLevel level, LivingEntity target) {
        target = randomPlayerTarget(level, target);
        double minHeight = Math.min(target.getY(), getY()) - 2.0;
        double maxHeight = Math.max(target.getY(), getY()) + 2.0;
        double expansion = 3.0 + random.nextDouble();
        for (int ring = 0; ring < 16; ring++) {
            int count = 12 + ring * 4;
            double radius = 1.5 + ring * expansion;
            for (int i = 0; i < count; i++) {
                double angle = i * Math.PI * 2.0 / count;
                double x = getX() + Math.cos(angle) * radius;
                double z = getZ() + Math.sin(angle) * radius;
                BlockPos pos = BlockPos.containing(x, maxHeight, z);
                if (!level.hasChunkAt(pos)) continue;
                while (pos.getY() >= Math.floor(minHeight) - 1) {
                    BlockPos below = pos.below();
                    if (level.getBlockState(below).isFaceSturdy(level, below, Direction.UP)) {
                        var shape = level.getBlockState(pos).getCollisionShape(level, pos);
                        double height = shape.isEmpty() ? 0.0 : shape.max(Direction.Axis.Y);
                        level.addFreshEntity(new EvokerFangs(level, x, pos.getY() + height, z, (float) angle, i + 2, this));
                        break;
                    }
                    pos = below;
                }
            }
        }
    }

    private LivingEntity randomPlayerTarget(ServerLevel level, LivingEntity target) {
        double range = getAttributeValue(Attributes.FOLLOW_RANGE);
        TargetingConditions targeting = TargetingConditions.forCombat().range(range);
        List<Player> alternatives = level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(range),
                player -> player != target && targeting.test(level, this, player));
        if (!alternatives.isEmpty() && random.nextInt(alternatives.size() + 1) != 0)
            return alternatives.get(random.nextInt(alternatives.size()));
        return target;
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
            level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 20, random.nextGaussian(), random.nextGaussian(), random.nextGaussian(), 0.01);
            level.sendParticles(WitherStormMod.COMMAND_PARTICLE.get(), x, y, z, 20, random.nextGaussian(), random.nextGaussian(), random.nextGaussian(), 0.2);
        }
    }

    @Override protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("PulseCooldown", pulseCooldown);
        output.putInt("PulseCharge", pulseCharge);
        output.putInt("SkullTicks", skullTicks);
        output.putBoolean("PreparingSkulls", preparingSkulls);
        output.putInt("PreviousSpell", previousSpell);
        output.putInt("PendingSpell", pendingSpell);
        output.putInt("PullTicks", pullTicks);
        output.putInt("FangsTicks", fangsTicks);
        output.putInt("ArrowTicks", arrowTicks);
    }

    @Override protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        pulseCooldown = Math.clamp(input.getIntOr("PulseCooldown", 200), 0, 500);
        pulseCharge = Math.clamp(input.getIntOr("PulseCharge", 0), 0, 59);
        skullTicks = Math.clamp(input.getIntOr("SkullTicks", 0), 0, 220);
        preparingSkulls = input.getBooleanOr("PreparingSkulls", false);
        previousSpell = Math.clamp(input.getIntOr("PreviousSpell", -1), -1, 4);
        pendingSpell = Math.clamp(input.getIntOr("PendingSpell", preparingSkulls ? 1 : 0), 0, 4);
        pullTicks = Math.clamp(input.getIntOr("PullTicks", 0), 0, 240);
        fangsTicks = Math.clamp(input.getIntOr("FangsTicks", 0), 0, 20);
        arrowTicks = Math.clamp(input.getIntOr("ArrowTicks", 0), 0, 120);
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
