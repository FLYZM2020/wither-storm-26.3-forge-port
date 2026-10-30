/*     */ package nonamecrackers2.witherstormmod.common.world.gen.feature.structure;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.Registry;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.LevelHeightAccessor;
/*     */ import net.minecraft.world.level.block.Rotation;
/*     */ import net.minecraft.world.level.chunk.ChunkGenerator;
/*     */ import net.minecraft.world.level.levelgen.WorldgenRandom;
/*     */ import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
/*     */ import net.minecraft.world.level.levelgen.structure.BoundingBox;
/*     */ import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
/*     */ import net.minecraft.world.level.levelgen.structure.Structure;
/*     */ import net.minecraft.world.level.levelgen.structure.StructureType;
/*     */ import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
/*     */ import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
/*     */ import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
/*     */ import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.shapes.BooleanOp;
/*     */ import net.minecraft.world.phys.shapes.Shapes;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinJigsawPlacement;
/*     */ 
/*     */ public class BowelsStructure extends Structure {
/*     */   static {
/*  39 */     CODEC = RecordCodecBuilder.create(builder -> builder.group((App)m_226567_(builder), (App)StructureTemplatePool.f_210555_.fieldOf("start_pool").forGetter(()), (App)HeightProvider.f_161970_.fieldOf("start_height").forGetter(())).apply((Applicative)builder, BowelsStructure::new));
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Codec<BowelsStructure> CODEC;
/*     */   
/*     */   private final Holder<StructureTemplatePool> startPool;
/*     */   
/*     */   private final HeightProvider startHeight;
/*     */ 
/*     */   
/*     */   public BowelsStructure(Structure.StructureSettings settings, Holder<StructureTemplatePool> startPool, HeightProvider heightProvider) {
/*  51 */     super(settings);
/*  52 */     this.startPool = startPool;
/*  53 */     this.startHeight = heightProvider;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<Structure.GenerationStub> m_214086_(Structure.GenerationContext context) {
/*  59 */     RegistryAccess access = context.f_226621_();
/*  60 */     ChunkGenerator generator = context.f_226622_();
/*  61 */     StructureTemplateManager manager = context.f_226625_();
/*  62 */     LevelHeightAccessor heightAccessor = context.f_226629_();
/*  63 */     WorldgenRandom random = context.f_226626_();
/*  64 */     Registry<StructureTemplatePool> registry = access.m_175515_(Registries.f_256948_);
/*  65 */     Rotation rotation = Rotation.NONE;
/*  66 */     StructureTemplatePool pool = (StructureTemplatePool)this.startPool.m_203334_();
/*  67 */     StructurePoolElement start = pool.m_227355_((RandomSource)random);
/*  68 */     if (start == EmptyPoolElement.f_210175_)
/*     */     {
/*  70 */       return Optional.empty();
/*     */     }
/*     */ 
/*     */     
/*  74 */     ChunkPos chunk = context.f_226628_();
/*  75 */     int i = this.startHeight.m_213859_((RandomSource)random, new WorldGenerationContext(generator, heightAccessor));
/*  76 */     BlockPos pos = new BlockPos(chunk.m_45604_(), i, chunk.m_45605_());
/*     */     
/*  78 */     PoolElementStructurePiece startPiece = new PoolElementStructurePiece(manager, start, pos, start.m_210540_(), rotation, start.m_214015_(manager, pos, rotation));
/*  79 */     BoundingBox box = startPiece.m_73547_();
/*  80 */     int x = pos.m_123341_() + box.m_71056_() / 2;
/*  81 */     int z = pos.m_123343_() + box.m_71058_() / 2;
/*  82 */     int y = pos.m_123342_();
/*     */     
/*  84 */     int l = box.m_162396_() + startPiece.m_72647_();
/*  85 */     startPiece.m_6324_(0, y - l, 0);
/*  86 */     return Optional.of(new Structure.GenerationStub(new BlockPos(x, y, z), builder -> {
/*     */             List<PoolElementStructurePiece> pieces = Lists.newArrayList();
/*     */             pieces.add(startPiece);
/*     */             AABB maxDistBox = new AABB((x - 128), (y - 128), (z - 128), (x + 128 + 1), (y + 128 + 1), (z + 128 + 1));
/*     */             VoxelShape shape = Shapes.m_83113_(Shapes.m_83064_(maxDistBox), Shapes.m_83064_(AABB.m_82321_(box)), BooleanOp.f_82685_);
/*     */             IMixinJigsawPlacement.invokeAddPieces(context.f_226624_(), 8, false, generator, manager, heightAccessor, (RandomSource)random, registry, startPiece, pieces, shape);
/*     */             pieces.forEach(());
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StructureType<?> m_213658_() {
/* 105 */     return (StructureType)WitherStormModStructures.BOWELS.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\structure\BowelsStructure.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */