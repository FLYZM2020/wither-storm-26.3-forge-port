package nonamecrackers2.witherstormmod.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public abstract class TemplateFeature<C extends FeatureConfiguration> extends Feature<C> {
   public TemplateFeature(Codec<C> codec) {
      super(codec);
   }

   public boolean m_142674_(FeaturePlaceContext<C> context) {
      if (context.m_159774_() instanceof ServerLevel world) {
         StructureTemplateManager manager = world.m_215082_();
         nonamecrackers2.witherstormmod.common.world.gen.feature.TemplateFeature.TemplateFeaturePiece piece = this.setupTemplate(
            context.m_159777_(), context.m_225041_(), manager
         );
         return this.placeWithTemplate(world, context.m_159775_(), context.m_225041_(), context.m_159777_(), (C)context.m_159778_(), piece);
      } else {
         return false;
      }
   }

   protected abstract nonamecrackers2.witherstormmod.common.world.gen.feature.TemplateFeature.TemplateFeaturePiece setupTemplate(
      BlockPos var1, RandomSource var2, StructureTemplateManager var3
   );

   protected abstract boolean placeWithTemplate(
      ServerLevel var1,
      ChunkGenerator var2,
      RandomSource var3,
      BlockPos var4,
      C var5,
      nonamecrackers2.witherstormmod.common.world.gen.feature.TemplateFeature.TemplateFeaturePiece var6
   );

   public record TemplateFeaturePiece(StructureTemplate template, StructurePlaceSettings settings) {
   }
}
