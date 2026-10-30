package nonamecrackers2.witherstormmod.common.world.gen.feature.structure;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.Structure.GenerationContext;
import net.minecraft.world.level.levelgen.structure.Structure.GenerationStub;
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
import nonamecrackers2.witherstormmod.mixin.IMixinJigsawPlacement;

public class BowelsStructure extends Structure {
   public static final Codec<nonamecrackers2.witherstormmod.common.world.gen.feature.structure.BowelsStructure> CODEC = RecordCodecBuilder.create(
      builder -> builder.group(
            m_226567_(builder),
            StructureTemplatePool.f_210555_.fieldOf("start_pool").forGetter(structure -> structure.startPool),
            HeightProvider.f_161970_.fieldOf("start_height").forGetter(structure -> structure.startHeight)
         )
         .apply(builder, nonamecrackers2.witherstormmod.common.world.gen.feature.structure.BowelsStructure::new)
   );
   private final Holder<StructureTemplatePool> startPool;
   private final HeightProvider startHeight;

   public BowelsStructure(StructureSettings settings, Holder<StructureTemplatePool> startPool, HeightProvider heightProvider) {
      super(settings);
      this.startPool = startPool;
      this.startHeight = heightProvider;
   }

   public Optional<GenerationStub> m_214086_(GenerationContext context) {
      RegistryAccess access = context.f_226621_();
      ChunkGenerator generator = context.f_226622_();
      StructureTemplateManager manager = context.f_226625_();
      LevelHeightAccessor heightAccessor = context.f_226629_();
      WorldgenRandom random = context.f_226626_();
      Registry<StructureTemplatePool> registry = access.m_175515_(Registries.f_256948_);
      Rotation rotation = Rotation.NONE;
      StructureTemplatePool pool = (StructureTemplatePool)this.startPool.m_203334_();
      StructurePoolElement start = pool.m_227355_(random);
      if (start == EmptyPoolElement.f_210175_) {
         return Optional.empty();
      }

      ChunkPos chunk = context.f_226628_();
      int i = this.startHeight.m_213859_(random, new WorldGenerationContext(generator, heightAccessor));
      BlockPos pos = new BlockPos(chunk.m_45604_(), i, chunk.m_45605_());
      PoolElementStructurePiece startPiece = new PoolElementStructurePiece(
         manager, start, pos, start.m_210540_(), rotation, start.m_214015_(manager, pos, rotation)
      );
      BoundingBox box = startPiece.m_73547_();
      int x = pos.m_123341_() + box.m_71056_() / 2;
      int z = pos.m_123343_() + box.m_71058_() / 2;
      int y = pos.m_123342_();
      int l = box.m_162396_() + startPiece.m_72647_();
      startPiece.m_6324_(0, y - l, 0);
      return Optional.of(new GenerationStub(new BlockPos(x, y, z), builder -> {
         List<PoolElementStructurePiece> pieces = Lists.newArrayList();
         pieces.add(startPiece);
         AABB maxDistBox = new AABB(x - 128, y - 128, z - 128, x + 128 + 1, y + 128 + 1, z + 128 + 1);
         VoxelShape shape = Shapes.m_83113_(Shapes.m_83064_(maxDistBox), Shapes.m_83064_(AABB.m_82321_(box)), BooleanOp.f_82685_);
         IMixinJigsawPlacement.invokeAddPieces(context.f_226624_(), 8, false, generator, manager, heightAccessor, random, registry, startPiece, pieces, shape);
         pieces.forEach(piece -> {
            piece.m_6324_(box.m_71056_() / -2, 0, box.m_71058_() / -2);
            builder.m_142679_(piece);
         });
      }));
   }

   public StructureType<?> m_213658_() {
      return (StructureType<?>)WitherStormModStructures.BOWELS.get();
   }
}
