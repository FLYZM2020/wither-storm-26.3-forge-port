/*     */ package nonamecrackers2.witherstormmod.common.world.gen.feature.structure;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Optional;
/*     */ import java.util.function.BiFunction;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.QuartPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import net.minecraft.world.level.biome.Biome;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.Mirror;
/*     */ import net.minecraft.world.level.block.Rotation;
/*     */ import net.minecraft.world.level.levelgen.Heightmap;
/*     */ import net.minecraft.world.level.levelgen.structure.Structure;
/*     */ import net.minecraft.world.level.levelgen.structure.StructurePiece;
/*     */ import net.minecraft.world.level.levelgen.structure.StructureType;
/*     */ import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
/*     */ import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
/*     */ import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBiomeTags;
/*     */ 
/*     */ public class StormSpawnPlatformStructure extends Structure {
/*     */   static {
/*  40 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)m_226567_(instance), (App)BlockPos.f_121852_.fieldOf("position").forGetter(())).apply((Applicative)instance, StormSpawnPlatformStructure::new));
/*     */   }
/*     */   
/*     */   public static final Codec<StormSpawnPlatformStructure> CODEC;
/*  44 */   private static final ResourceLocation DESERT = new ResourceLocation("witherstormmod", "desert_storm_spawn_platform");
/*  45 */   private static final ResourceLocation JUNGLE = new ResourceLocation("witherstormmod", "jungle_storm_spawn_platform");
/*  46 */   private static final ResourceLocation SAVANNA = new ResourceLocation("witherstormmod", "savanna_storm_spawn_platform");
/*  47 */   private static final ResourceLocation TAIGA = new ResourceLocation("witherstormmod", "taiga_storm_spawn_platform");
/*  48 */   private static final ResourceLocation SNOWY = new ResourceLocation("witherstormmod", "snowy_storm_spawn_platform");
/*     */   
/*  50 */   private static final ResourceLocation RUINS = new ResourceLocation("witherstormmod", "ruins_storm_spawn_platform");
/*  51 */   private static final ResourceLocation ORDER_TEMPLE = new ResourceLocation("witherstormmod", "order_temple_storm_spawn_platform");
/*  52 */   private static final ResourceLocation FOREST = new ResourceLocation("witherstormmod", "forest_storm_spawn_platform");
/*     */   
/*  54 */   private static final ResourceLocation AUTO_SPAWN = WitherStormMod.id("auto_spawn_platform");
/*     */   
/*     */   private BlockPos spawnPos;
/*     */ 
/*     */   
/*     */   public StormSpawnPlatformStructure(Structure.StructureSettings settings, BlockPos pos) {
/*  60 */     super(settings);
/*  61 */     this.spawnPos = pos;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isInChunk(ChunkPos pos) {
/*  66 */     return (this.spawnPos.m_123341_() >= pos.m_45604_() && this.spawnPos.m_123341_() <= pos.m_45608_() && this.spawnPos.m_123343_() >= pos.m_45605_() && this.spawnPos.m_123343_() <= pos.m_45609_());
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isFeatureChunk(Structure.GenerationContext context, StormSpawnPlatformStructure structure) {
/*  71 */     return structure.isInChunk(context.f_226628_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<Structure.GenerationStub> m_214086_(Structure.GenerationContext context) {
/*  77 */     if (isFeatureChunk(context, this)) {
/*     */       
/*  79 */       Rotation rotation = Rotation.m_221990_((RandomSource)context.f_226626_());
/*  80 */       ChunkPos chunk = context.f_226628_();
/*  81 */       BlockPos prePos = new BlockPos(chunk.m_45604_(), 0, chunk.m_45605_());
/*  82 */       BlockPos pos = prePos.m_6630_(context.f_226622_().m_214096_(prePos.m_123341_(), prePos.m_123343_(), Heightmap.Types.WORLD_SURFACE_WG, context.f_226629_(), context.f_226624_()));
/*  83 */       Holder<Biome> biome = context.f_226623_().m_203407_(QuartPos.m_175400_(pos.m_123341_()), QuartPos.m_175400_(pos.m_123342_()), QuartPos.m_175400_(pos.m_123343_()), context.f_226624_().m_224579_());
/*  84 */       ResourceLocation structure = getStructureForBiome(biome, (RandomSource)context.f_226626_());
/*  85 */       return Optional.of(new Structure.GenerationStub(pos, builder -> builder.m_142679_((StructurePiece)new Piece(context.f_226625_(), structure, pos, rotation, 0))));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ResourceLocation getStructureForBiome(Holder<Biome> biome, RandomSource random) {
/*  97 */     if (((Boolean)WitherStormModConfig.COMMON.autoSpawnWitherStorm.get()).booleanValue()) {
/*  98 */       return AUTO_SPAWN;
/*     */     }
/* 100 */     if (biome.m_203656_(WitherStormModBiomeTags.HAS_RUINS_STORM_SPAWN_PLATFORM) && random.m_188501_() <= 0.2F)
/* 101 */       return RUINS; 
/* 102 */     if (biome.m_203656_(WitherStormModBiomeTags.HAS_ORDER_TEMPLE_STORM_SPAWN_PLATFORM) && random.m_188501_() <= 0.25F)
/* 103 */       return ORDER_TEMPLE; 
/* 104 */     if (biome.m_203656_(WitherStormModBiomeTags.HAS_FOREST_STORM_SPAWN_PLATFORM) && random.m_188501_() <= 0.2F)
/* 105 */       return FOREST; 
/* 106 */     if (biome.m_203656_(WitherStormModBiomeTags.HAS_DESERT_STORM_SPAWN_PLATFORM))
/* 107 */       return DESERT; 
/* 108 */     if (biome.m_203656_(WitherStormModBiomeTags.HAS_JUNGLE_STORM_SPAWN_PLATFORM))
/* 109 */       return JUNGLE; 
/* 110 */     if (biome.m_203656_(WitherStormModBiomeTags.HAS_SAVANNA_STORM_SPAWN_PLATFORM))
/* 111 */       return SAVANNA; 
/* 112 */     if (biome.m_203656_(WitherStormModBiomeTags.HAS_SNOWY_STORM_SPAWN_PLATFORM))
/* 113 */       return SNOWY; 
/* 114 */     if (biome.m_203656_(WitherStormModBiomeTags.HAS_TAIGA_STORM_SPAWN_PLATFORM)) {
/* 115 */       return TAIGA;
/*     */     }
/* 117 */     return WitherStormModStructures.STORM_SPAWN_PLATFORM.getId();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StructureType<?> m_213658_() {
/* 123 */     return (StructureType)WitherStormModStructures.STORM_SPAWN_PLATFORM.get();
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece {
/*     */     @Nullable
/*     */     private BlockPos spawnPos;
/*     */     
/*     */     public Piece(StructureTemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation, int offsetY) {
/* 132 */       super(WitherStormModStructures.PLATFORM, 0, manager, location, location.toString(), makeSettings(rotation, pos), pos);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Piece(StructureTemplateManager manager, CompoundTag nbt) {
/* 138 */       super(WitherStormModStructures.PLATFORM, nbt, manager, id -> {
/*     */             StructureTemplate template = manager.m_230359_(id);
/*     */             
/*     */             return makeSettings(Rotation.valueOf(nbt.m_128461_("Rot")), new BlockPos(template.m_163801_().m_123341_() / 2, 0, template.m_163801_().m_123343_() / 2));
/*     */           });
/* 143 */       if (nbt.m_128425_("SpawnPos", 10)) {
/* 144 */         this.spawnPos = NbtUtils.m_129239_(nbt.m_128469_("SpawnPos"));
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void m_183620_(StructurePieceSerializationContext context, CompoundTag tag) {
/* 162 */       super.m_183620_(context, tag);
/* 163 */       tag.m_128359_("Rot", this.f_73657_.m_74404_().name());
/* 164 */       if (this.spawnPos != null) {
/* 165 */         tag.m_128365_("SpawnPos", (Tag)NbtUtils.m_129224_(this.spawnPos));
/*     */       }
/*     */     }
/*     */     
/*     */     private static StructurePlaceSettings makeSettings(Rotation rotation, BlockPos pos) {
/* 170 */       return (new StructurePlaceSettings()).m_74379_(rotation).m_74377_(Mirror.NONE).m_74383_((StructureProcessor)BlockIgnoreProcessor.f_74046_);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void m_213704_(String metadata, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
/* 176 */       if (metadata.equals("spawn_position")) {
/*     */         
/* 178 */         this.spawnPos = pos;
/* 179 */         level.m_7731_(pos, Blocks.f_50016_.m_49966_(), 3);
/*     */       } 
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public BlockPos getSpawnPos() {
/* 185 */       return this.spawnPos;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\structure\StormSpawnPlatformStructure.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */