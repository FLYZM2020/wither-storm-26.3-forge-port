package nonamecrackers2.witherstormmod.common.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.TickEvent;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSounds;

/** Summoning pattern from the 1.20.1 mod, adapted to Forge's 26.3 placement event. */
public final class WitherStormPatternChecker {
    private static final Map<ServerLevel, List<BlockPos>> PENDING = new WeakHashMap<>();
    private static final BlockPattern PATTERN = BlockPatternBuilder.start()
            .aisle("^^^", "#$#", "~#~")
            .where('#', BlockInWorld.hasState(state -> state.is(WitherStormModBlockTags.WITHER_STORM_SUMMON_BASE_BLOCKS)))
            .where('$', BlockInWorld.hasState(state -> state.is(WitherStormModBlockTags.WITHER_STORM_SUMMON_COMMAND_BLOCKS)))
            .where('^', BlockInWorld.hasState(state -> state.is(Blocks.WITHER_SKELETON_SKULL) || state.is(Blocks.WITHER_SKELETON_WALL_SKULL)))
            .where('~', BlockInWorld.hasState(state -> state.isAir()))
            .build();

    private WitherStormPatternChecker() {}

    public static void onSkullPlaced(BlockEvent.EntityPlaceEvent event, boolean cancelled) {
        if (cancelled) return;
        if (!(event.getLevel() instanceof ServerLevel level) || level.getDifficulty() == Difficulty.PEACEFUL) return;
        if (!event.getPlacedBlock().is(Blocks.WITHER_SKELETON_SKULL)
                && !event.getPlacedBlock().is(Blocks.WITHER_SKELETON_WALL_SKULL)) return;
        PENDING.computeIfAbsent(level, ignored -> new ArrayList<>()).add(event.getPos().immutable());
    }

    /** Wait until the placement event has completed and any cancellation was applied. */
    public static void onLevelTick(TickEvent.LevelTickEvent.Post event) {
        if (!(event.level() instanceof ServerLevel level)) return;
        List<BlockPos> positions = PENDING.remove(level);
        if (positions == null || level.getDifficulty() == Difficulty.PEACEFUL) return;
        for (BlockPos pos : positions) {
            if (!level.hasChunkAt(pos)) continue;
            var state = level.getBlockState(pos);
            if (!state.is(Blocks.WITHER_SKELETON_SKULL) && !state.is(Blocks.WITHER_SKELETON_WALL_SKULL)) continue;
            trySummon(level, pos);
        }
    }

    private static void trySummon(ServerLevel level, BlockPos pos) {
        BlockPattern.BlockPatternMatch match = PATTERN.find(level, pos);
        if (match == null) return;
        WitherStormEntity storm = WitherStormMod.WITHER_STORM.get().create(level, EntitySpawnReason.TRIGGERED);
        if (storm == null) return;
        BlockPos base = match.getBlock(1, 2, 0).getPos();
        float rotation = Direction.getYRot(match.getForwards());
        storm.snapTo(base.getX() + 0.5, base.getY() + 0.55, base.getZ() + 0.5, rotation, 0.0F);
        storm.yBodyRot = rotation;
        storm.makeInvulnerable();
        // Only consume the structure if adding the entity succeeds.
        if (!level.addFreshEntity(storm)) return;
        storm.playSound(WitherStormModSounds.COMMAND_BLOCK_ACTIVATES.get(), 4.0F, 1.0F);
        for (int x = 0; x < PATTERN.getWidth(); x++) {
            for (int y = 0; y < PATTERN.getHeight(); y++) {
                BlockInWorld block = match.getBlock(x, y, 0);
                int oldState = Block.getId(block.getState());
                level.setBlock(block.getPos(), Blocks.AIR.defaultBlockState(), 2);
                level.levelEvent(2001, block.getPos(), oldState);
            }
        }
        for (ServerPlayer player : level.getEntitiesOfClass(ServerPlayer.class, storm.getBoundingBox().inflate(50.0))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(player, storm);
        }
        for (int x = 0; x < PATTERN.getWidth(); x++) {
            for (int y = 0; y < PATTERN.getHeight(); y++) {
                level.updateNeighborsAt(match.getBlock(x, y, 0).getPos(), Blocks.AIR);
            }
        }
    }
}
