package nonamecrackers2.witherstormmod.common.world.gen.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;

public interface RemovableFeature<C extends net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration> {
  boolean remove(WorldGenLevel paramWorldGenLevel, ChunkGenerator paramChunkGenerator, RandomSource paramRandomSource, BlockPos paramBlockPos, C paramC);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\RemovableFeature.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */