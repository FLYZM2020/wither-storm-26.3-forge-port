package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import java.util.List;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.gameevent.GameEvent;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntities;
import nonamecrackers2.witherstormmod.common.item.FormidibombItem;
import org.jspecify.annotations.Nullable;

public final class FormidibombBlock extends TntBlock implements EntityBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public FormidibombBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        if (!(level instanceof ServerLevel server) || !server.getGameRules().get(GameRules.TNT_EXPLODES)) return false;
        BlockEntity entity = server.getBlockEntity(pos);
        int fuse = entity instanceof FormidibombBlockEntity bombData && bombData.getStartingFuse() > 0
                ? Math.min(1200, Math.max(1, bombData.getFuse())) : 1200;
        LivingEntity owner = entity instanceof FormidibombBlockEntity bombData && bombData.getStartingFuse() > 0
                && bombData.getOwner(server) != null ? bombData.getOwner(server) : igniter;
        FormidibombEntity bomb = FormidibombEntity.create(server, pos, state, owner, fuse);
        if (!server.addFreshEntity(bomb)) return false;
        server.playSound(null, bomb.getX(), bomb.getY(), bomb.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        server.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        return true;
    }

    @Override
    public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
        if (!level.getGameRules().get(GameRules.TNT_EXPLODES)) return;
        FormidibombEntity bomb = FormidibombEntity.create(level, pos, defaultBlockState(), explosion.getIndirectSourceEntity(), 20);
        level.addFreshEntity(bomb);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FormidibombBlockEntity(pos, state);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide() || type != WitherStormModBlockEntities.FORMIDIBOMB.get()) return null;
        return (BlockEntityTicker<T>) (BlockEntityTicker<FormidibombBlockEntity>)
                (world, pos, block, entity) -> { if (world instanceof ServerLevel server) FormidibombBlockEntity.tick(server, pos, block, entity); };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof FormidibombBlockEntity bomb) {
            bomb.setFuse(FormidibombItem.getFuse(stack), FormidibombItem.getStartFuse(stack));
            bomb.setOwner(placer);
        }
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> drops = super.getDrops(state, params);
        if (params.getOptionalParameter(LootContextParams.BLOCK_ENTITY) instanceof FormidibombBlockEntity bomb
                && bomb.getStartingFuse() > 0) {
            for (ItemStack stack : drops) {
                if (stack.getItem() instanceof FormidibombItem) FormidibombItem.setFuse(stack, bomb.getFuse(), bomb.getStartingFuse());
            }
        }
        return drops;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
