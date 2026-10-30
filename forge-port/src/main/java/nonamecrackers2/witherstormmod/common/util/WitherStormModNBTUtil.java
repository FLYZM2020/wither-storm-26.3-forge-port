package nonamecrackers2.witherstormmod.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

/** Reads and writes the old mod's NBT shapes using the 26.3 accessors. */
public final class WitherStormModNBTUtil {
    private WitherStormModNBTUtil() {}

    public static ListTag writeBlockStatePosMap(Map<BlockPos, BlockState> map) {
        ListTag list = new ListTag();
        for (Map.Entry<BlockPos, BlockState> entry : map.entrySet()) {
            CompoundTag state = NbtUtils.writeBlockState(entry.getValue());
            CompoundTag position = new CompoundTag();
            position.putInt("X", entry.getKey().getX());
            position.putInt("Y", entry.getKey().getY());
            position.putInt("Z", entry.getKey().getZ());
            state.put("RelativePos", position);
            list.add(state);
        }
        return list;
    }

    public static Map<BlockPos, BlockState> readBlockStatePosMap(HolderGetter<Block> getter, ListTag list) {
        Map<BlockPos, BlockState> blocks = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            CompoundTag state = list.getCompoundOrEmpty(i);
            CompoundTag position = state.getCompoundOrEmpty("RelativePos");
            BlockPos pos = new BlockPos(position.getIntOr("X", 0), position.getIntOr("Y", 0), position.getIntOr("Z", 0));
            blocks.put(pos, NbtUtils.readBlockState(getter, state));
        }
        return blocks;
    }

    public static ListTag writeCompoundList(List<CompoundTag> compounds) {
        ListTag list = new ListTag();
        compounds.forEach(list::add);
        return list;
    }

    public static List<CompoundTag> readCompoundList(ListTag list) {
        List<CompoundTag> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) result.add(list.getCompoundOrEmpty(i));
        return result;
    }

    public static CompoundTag writeChunkPosList(List<ChunkPos> chunks) {
        CompoundTag compound = new CompoundTag();
        for (int i = 0; i < chunks.size(); i++) compound.put("Chunk" + i, writeChunkPos(chunks.get(i)));
        return compound;
    }

    public static List<ChunkPos> readChunkPosList(CompoundTag compound) {
        List<ChunkPos> result = new ArrayList<>();
        for (int i = 0; i < compound.keySet().size(); i++) {
            if (compound.contains("Chunk" + i)) result.add(readChunkPos(compound.getCompoundOrEmpty("Chunk" + i)));
        }
        return result;
    }

    public static CompoundTag writeChunkPos(ChunkPos pos) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", pos.x());
        tag.putInt("z", pos.z());
        return tag;
    }

    public static ChunkPos readChunkPos(CompoundTag tag) {
        return new ChunkPos(tag.getIntOr("x", 0), tag.getIntOr("z", 0));
    }

    public static CompoundTag writeVector2f(Vec2 vector) {
        CompoundTag tag = new CompoundTag();
        tag.putFloat("x", vector.x);
        tag.putFloat("y", vector.y);
        return tag;
    }

    public static Vec2 readVector2f(CompoundTag tag) {
        return new Vec2(tag.getFloatOr("x", 0.0F), tag.getFloatOr("y", 0.0F));
    }

    public static CompoundTag writeVector3d(Vec3 vector) {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("X", vector.x());
        tag.putDouble("Y", vector.y());
        tag.putDouble("Z", vector.z());
        return tag;
    }

    public static Vec3 readVector3d(CompoundTag tag) {
        return new Vec3(tag.getDoubleOr("X", 0.0), tag.getDoubleOr("Y", 0.0), tag.getDoubleOr("Z", 0.0));
    }
}
