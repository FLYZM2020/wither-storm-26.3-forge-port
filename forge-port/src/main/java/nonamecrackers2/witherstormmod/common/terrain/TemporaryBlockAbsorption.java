package nonamecrackers2.witherstormmod.common.terrain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;

/** A bounded, single-block approximation until the original cluster system is ported. */
public final class TemporaryBlockAbsorption {
    private final Set<UUID> tracked = new HashSet<>();
    private final Map<UUID, Integer> missingSince = new HashMap<>();
    private boolean releasing;

    public void tick(WitherStormEntity storm, ServerLevel level) {
        if (releasing) {
            release(level, storm.tickCount);
            if (releasing) return;
        }
        int phase = storm.getPhase();
        int radius = phase >= 4 ? 80 : (int) Math.min(48, 12 + Math.round(storm.getConsumedEntities() * 0.00445));
        int interval = Math.max(1, 60 - Math.round(storm.getConsumedEntities() * 0.00375F));
        if (tracked.size() < 64 && storm.tickCount % interval == 0 && ForgeEventFactory.getMobGriefingEvent(level, storm)) {
            for (int attempt = 0; attempt < 8; attempt++) {
                int dx = storm.getRandom().nextInt(radius * 2 + 1) - radius;
                int dz = storm.getRandom().nextInt(radius * 2 + 1) - radius;
                if (dx * dx + dz * dz > radius * radius) continue;
                int x = storm.getBlockX() + dx, z = storm.getBlockZ() + dz;
                if (!level.hasChunkAt(new BlockPos(x, storm.getBlockY(), z))) continue;
                int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z) - 1;
                if (y > storm.getY() + 8 || y < level.getMinY()) continue;
                BlockPos pos = new BlockPos(x, y, z);
                BlockState state = level.getBlockState(pos);
                if (state.isAir() || state.hasBlockEntity() || !state.getFluidState().isEmpty()
                        || state.is(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST)
                        || state.getDestroySpeed(level, pos) < 0 || !ForgeEventFactory.onEntityDestroyBlock(storm, pos, state)) continue;
                if (!level.getBlockState(pos).equals(state)) continue;
                FallingBlockEntity block = FallingBlockEntity.fall(level, pos, state);
                block.setNoGravity(true);
                block.noPhysics = true;
                block.dropItem = false;
                tracked.add(block.getUUID());
                break;
            }
        }
        Vec3 destination = storm.getBoundingBox().getCenter();
        tracked.removeIf(id -> {
            Entity entity = level.getEntity(id);
            if (entity == null) {
                int first = missingSince.computeIfAbsent(id, unused -> storm.tickCount);
                if (storm.tickCount - first < 1200) return false;
                missingSince.remove(id);
                return true;
            }
            missingSince.remove(id);
            if (!(entity instanceof FallingBlockEntity block) || !block.isAlive()) return true;
            if (storm.getBoundingBox().contains(block.position())) {
                storm.recordConsumption(block, 1);
                block.discard();
                return true;
            }
            block.noPhysics = true;
            block.setNoGravity(true);
            block.time = 1;
            block.setDeltaMovement(destination.subtract(block.position()).normalize().scale(0.5));
            if (storm.tickCount % 5 == 0) {
                level.getChunkSource().sendToTrackingPlayers(block, new ClientboundSetEntityMotionPacket(block));
            }
            return false;
        });
    }

    public void save(ValueOutput output) {
        if (!tracked.isEmpty()) output.store("TemporaryTrackedBlocks", UUIDUtil.CODEC_SET, tracked);
        if (releasing) output.putBoolean("TemporaryBlocksReleasing", true);
    }

    /** Release currently loaded debris when the storm stops pulling. */
    public void release(ServerLevel level, int tickCount) {
        releasing = true;
        tracked.removeIf(id -> {
            Entity found = level.getEntity(id);
            if (found == null) {
                int first = missingSince.computeIfAbsent(id, unused -> tickCount);
                if (tickCount - first < 1200) return false;
            } else if (found instanceof FallingBlockEntity block) {
                block.noPhysics = false;
                block.setNoGravity(false);
                block.dropItem = true;
            }
            missingSince.remove(id);
            return true;
        });
        releasing = !tracked.isEmpty();
    }

    public void load(ValueInput input) {
        tracked.clear();
        input.read("TemporaryTrackedBlocks", UUIDUtil.CODEC_SET).ifPresent(ids -> ids.stream().limit(64).forEach(tracked::add));
        releasing = input.getBooleanOr("TemporaryBlocksReleasing", false);
        missingSince.clear();
    }
}
