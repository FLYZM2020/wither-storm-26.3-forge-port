package nonamecrackers2.witherstormmod.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import nonamecrackers2.witherstormmod.common.config.StormConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntities;
import org.jspecify.annotations.Nullable;

/** Keeps the crafted fuse running after placement. */
public final class FormidibombBlockEntity extends BlockEntity {
    private int fuse;
    private int startingFuse;
    private @Nullable EntityReference<LivingEntity> owner;

    public FormidibombBlockEntity(BlockPos pos, BlockState state) {
        super(WitherStormModBlockEntities.FORMIDIBOMB.get(), pos, state);
    }
    public int getFuse() { return fuse; }
    public int getStartingFuse() { return startingFuse; }
    public void setOwner(@Nullable LivingEntity owner) { this.owner = EntityReference.of(owner); setChanged(); }
    public @Nullable LivingEntity getOwner(ServerLevel level) { return EntityReference.getLivingEntity(owner, level); }
    public void setFuse(int remaining, int start) {
        startingFuse = Math.max(0, start);
        fuse = startingFuse == 0 ? 0 : Math.clamp(remaining, 0, startingFuse);
        setChanged();
    }
    public static void tick(ServerLevel level, BlockPos pos, BlockState state, FormidibombBlockEntity entity) {
        if (entity.startingFuse <= 0 || !StormConfig.FORMIDIBOMB_AUTO_FUSE.get()) return;
        entity.fuse = Math.max(0, entity.fuse - 1);
        if (entity.fuse == 0) {
            FormidibombEntity bomb = FormidibombEntity.create(level, pos, state, entity.getOwner(level), 1);
            if (level.addFreshEntity(bomb)) level.removeBlock(pos, false);
        } else if (entity.fuse % 20 == 0) entity.setChanged();
    }
    @Override protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Fuse", fuse);
        output.putInt("StartFuse", startingFuse);
        EntityReference.store(owner, output, "Owner");
    }
    @Override protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        startingFuse = Math.max(0, input.getIntOr("StartFuse", 0));
        fuse = Math.clamp(input.getIntOr("Fuse", 0), 0, startingFuse);
        owner = EntityReference.read(input, "Owner");
    }
}
