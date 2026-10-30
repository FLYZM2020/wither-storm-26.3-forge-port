package nonamecrackers2.witherstormmod.common.entity;

import java.util.Comparator;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.config.StormConfig;
import org.jspecify.annotations.Nullable;

/** Primed bomb with a simplified storm-attraction and collapse trigger. */
public final class FormidibombEntity extends PrimedTnt {
    private @Nullable EntityReference<LivingEntity> igniter;
    private @Nullable EntityReference<WitherStormEntity> attractedBy;
    private boolean attractionApplied;

    public FormidibombEntity(EntityType<? extends FormidibombEntity> type, Level level) {
        super(type, level);
        setFuse(1200);
    }

    public static FormidibombEntity create(ServerLevel level, BlockPos pos, BlockState state, @Nullable LivingEntity igniter, int fuse) {
        FormidibombEntity bomb = new FormidibombEntity(WitherStormMod.FORMIDIBOMB.get(), level);
        bomb.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        bomb.setBlockState(state);
        bomb.setFuse(fuse);
        bomb.igniter = EntityReference.of(igniter);
        double angle = level.getRandom().nextDouble() * Math.PI * 2.0;
        bomb.setDeltaMovement(-Math.sin(angle) * 0.02, 0.2, -Math.cos(angle) * 0.02);
        return bomb;
    }

    @Override
    public void tick() {
        if (level() instanceof ServerLevel level && getFuse() > 0) updateAttraction(level);
        super.tick();
    }

    private void updateAttraction(ServerLevel level) {
        WitherStormEntity storm = attractedBy == null ? null : attractedBy.getEntity(level, WitherStormEntity.class);
        if (!isEligible(storm) || storm.distanceToSqr(this) > 170.0 * 170.0) {
            storm = null;
            if (tickCount % 10 == 0) {
                storm = level.getEntitiesOfClass(WitherStormEntity.class, getBoundingBox().inflate(170.0), this::isEligible).stream()
                        .filter(candidate -> candidate.distanceToSqr(this) <= 170.0 * 170.0 && candidate.hasLineOfSight(this))
                        .min(Comparator.comparingDouble(candidate -> candidate.distanceToSqr(this))).orElse(null);
            }
            attractedBy = EntityReference.of(storm);
        }
        boolean active = storm != null && getFuse() <= 600.0 + storm.distanceTo(this);
        if (active) {
            Vec3 destination = storm.getTractorHeadPosition(0);
            setNoGravity(true);
            setDeltaMovement(destination.subtract(position()).normalize().scale(0.1));
            attractionApplied = true;
            if (tickCount % 5 == 0) level.getChunkSource().sendToTrackingPlayers(this, new ClientboundSetEntityMotionPacket(this));
        } else if (attractionApplied) {
            setNoGravity(false);
            attractionApplied = false;
        }
    }

    private boolean isEligible(@Nullable WitherStormEntity storm) {
        return storm != null && storm.isAlive() && storm.level() == level() && storm.getPhase() >= 5 && storm.getCollapseState() == 0;
    }

    @Override
    protected void explode() {
        if (!(level() instanceof ServerLevel level) || !level.getGameRules().get(GameRules.TNT_EXPLODES)) return;
        WitherStormEntity storm = attractedBy == null ? null : attractedBy.getEntity(level, WitherStormEntity.class);
        if (isEligible(storm) && storm.distanceToSqr(this) <= 170.0 * 170.0) storm.beginCollapse();
        level.explode(this, Explosion.getDefaultDamageSource(level, this), null,
                getX(), getY(0.0625), getZ(), StormConfig.FORMIDIBOMB_POWER.get().floatValue(), true, Level.ExplosionInteraction.TNT);
    }

    @Override
    public @Nullable LivingEntity getOwner() {
        return EntityReference.getLivingEntity(igniter, level());
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        EntityReference.store(igniter, output, "formidibomb_owner");
        EntityReference.store(attractedBy, output, "attracting_storm");
        output.putBoolean("attraction_applied", attractionApplied);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        igniter = EntityReference.read(input, "formidibomb_owner");
        attractedBy = EntityReference.read(input, "attracting_storm");
        attractionApplied = input.getBooleanOr("attraction_applied", false);
    }
}
