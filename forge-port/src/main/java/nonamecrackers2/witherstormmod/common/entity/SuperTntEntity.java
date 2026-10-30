package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import org.jspecify.annotations.Nullable;

/** Primed Super TNT: original 320-tick fuse and explosion power 32. */
public final class SuperTntEntity extends PrimedTnt {
    private @Nullable EntityReference<LivingEntity> igniter;

    public SuperTntEntity(EntityType<? extends SuperTntEntity> type, Level level) {
        super(type, level);
        setFuse(320);
    }

    public static SuperTntEntity create(ServerLevel level, BlockPos pos, BlockState state, @Nullable LivingEntity owner, int fuse) {
        SuperTntEntity tnt = new SuperTntEntity(WitherStormMod.SUPER_TNT.get(), level);
        tnt.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        tnt.setBlockState(state);
        tnt.setFuse(fuse);
        tnt.igniter = EntityReference.of(owner);
        double angle = level.getRandom().nextDouble() * Math.PI * 2.0;
        tnt.setDeltaMovement(-Math.sin(angle) * 0.02, 0.2, -Math.cos(angle) * 0.02);
        return tnt;
    }

    @Override
    protected void explode() {
        if (level() instanceof ServerLevel level && level.getGameRules().get(GameRules.TNT_EXPLODES)) {
            level.explode(this, WitherStormModDamageTypes.source(level, WitherStormModDamageTypes.SUPER_TNT_EXPLOSION), null,
                    getX(), getY(0.0625), getZ(), 32.0F, false, Level.ExplosionInteraction.TNT);
        }
    }

    @Override
    public @Nullable LivingEntity getOwner() { return EntityReference.getLivingEntity(igniter, level()); }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        EntityReference.store(igniter, output, "super_tnt_owner");
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        igniter = EntityReference.read(input, "super_tnt_owner");
    }
}
