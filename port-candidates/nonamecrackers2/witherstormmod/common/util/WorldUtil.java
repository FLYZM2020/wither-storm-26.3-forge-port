/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArraySet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.HolderSet;
/*     */ import net.minecraft.core.SectionPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.particles.BlockParticleOption;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.server.level.FullChunkStatus;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.util.random.SimpleWeightedRandomList;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityDimensions;
/*     */ import net.minecraft.world.entity.EntitySelector;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobSpawnType;
/*     */ import net.minecraft.world.entity.SpawnPlacements;
/*     */ import net.minecraft.world.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.NaturalSpawner;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import net.minecraft.world.level.StructureManager;
/*     */ import net.minecraft.world.level.block.BellBlock;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.ButtonBlock;
/*     */ import net.minecraft.world.level.block.CaveVines;
/*     */ import net.minecraft.world.level.block.FallingBlock;
/*     */ import net.minecraft.world.level.block.LeverBlock;
/*     */ import net.minecraft.world.level.block.NoteBlock;
/*     */ import net.minecraft.world.level.block.PressurePlateBlock;
/*     */ import net.minecraft.world.level.block.RedStoneOreBlock;
/*     */ import net.minecraft.world.level.block.RedstoneLampBlock;
/*     */ import net.minecraft.world.level.block.SculkSensorBlock;
/*     */ import net.minecraft.world.level.block.TrapDoorBlock;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.Half;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.chunk.ChunkAccess;
/*     */ import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
/*     */ import net.minecraft.world.level.chunk.ChunkSource;
/*     */ import net.minecraft.world.level.chunk.status.ChunkStatus;
/*     */ import net.minecraft.world.level.chunk.LevelChunk;
/*     */ import net.minecraft.world.level.chunk.StructureAccess;
/*     */ import net.minecraft.world.level.levelgen.Heightmap;
/*     */ import net.minecraft.world.level.levelgen.structure.Structure;
/*     */ import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
/*     */ import net.minecraft.world.level.levelgen.structure.StructureStart;
/*     */ import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
/*     */ import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
/*     */ import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ import nonamecrackers2.witherstormmod.mixin.MixinFallingBlock;
/*     */ import nonamecrackers2.witherstormmod.mixin.MixinPointedDripstoneBlock;
/*     */ 
/*     */ public class WorldUtil {
/*     */   public static <T extends Entity> List<T> getPerformantEntitiesOfClass(ServerLevel world, Class<T> clazz, AABB box, @Nullable Predicate<? super T> predicate) {
/* 106 */     return world.getEntitiesOfClass(clazz, box, predicate);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> List<T> getPerformantEntitiesOfClass(ServerLevel world, Class<T> clazz, AABB box) {
/* 111 */     return getPerformantEntitiesOfClass(world, clazz, box, EntitySelector.NO_SPECTATORS);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockEntity> getBlockEntitiesInAABB(Level world, AABB box) {
/* 116 */     List<BlockEntity> blockEntities = new ArrayList<>();
/* 117 */     ChunkSource source = world.getChunkSource();
/* 118 */     int minChunkX = Mth.floor(box.minX / 16.0D);
/* 119 */     int minChunkZ = Mth.floor(box.minZ / 16.0D);
/* 120 */     int maxChunkX = Mth.ceil(box.maxX / 16.0D);
/* 121 */     int maxChunkZ = Mth.ceil(box.maxZ / 16.0D);
/* 122 */     for (int x = minChunkX; x < maxChunkX; x++) {
/*     */       
/* 124 */       for (int z = minChunkZ; z < maxChunkZ; z++) {
/*     */         
/* 126 */         LevelChunk levelChunk = source.getChunk(x, z, false);
/* 127 */         if (levelChunk != null)
/*     */         {
/* 129 */           for (BlockPos pos : levelChunk.getBlockEntitiesPos()) {
/*     */             
/* 131 */             if (box.contains(Vec3.atBottomCenterOf((Vec3i)pos)))
/* 132 */               blockEntities.add(levelChunk.getBlockEntity(pos)); 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 137 */     return blockEntities;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static <T> T getNearest(List<T> objects, Vec3 center, Function<T, Vec3> toVec) {
/* 143 */     double distance = -1.0D;
/* 144 */     T toReturn = null;
/* 145 */     for (T t : objects) {
/*     */       
/* 147 */       Vec3 vec = toVec.apply(t);
/* 148 */       double d0 = vec.distanceToSqr(center);
/* 149 */       if (distance == -1.0D || d0 < distance) {
/*     */         
/* 151 */         distance = d0;
/* 152 */         toReturn = t;
/*     */       } 
/*     */     } 
/* 155 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<WitherStormEntity> getAllStorms(ServerLevel level) {
/* 160 */     return (List<WitherStormEntity>)Lists.newArrayList(level.getAllEntities()).stream().filter(e -> e instanceof WitherStormEntity).collect(Collectors.mapping(e -> (WitherStormEntity)e, Collectors.toList()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean checkForIntersect(AABB box, Vec3 start, Vec3 end) {
/* 165 */     double d0 = Double.MAX_VALUE;
/* 166 */     boolean flag = false;
/* 167 */     Optional<Vec3> optional = box.clip(start, end);
/* 168 */     if (optional.isPresent()) {
/*     */       
/* 170 */       double d1 = start.distanceToSqr(optional.get());
/* 171 */       if (d1 < d0) {
/*     */         
/* 173 */         flag = true;
/* 174 */         d0 = d1;
/*     */       } 
/*     */     } 
/*     */     
/* 178 */     if (box.contains(start)) {
/* 179 */       flag = true;
/*     */     }
/* 181 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areaLoaded(Level level, BlockPos center, int radius) {
/* 186 */     for (int x = -radius; x <= radius; x++) {
/*     */       
/* 188 */       for (int z = -radius; z <= radius; z++) {
/*     */         
/* 190 */         ChunkPos chunkPos = new ChunkPos(center);
/* 191 */         ChunkAccess chunk = level.getChunk(chunkPos.x + x, chunkPos.z + z, ChunkStatus.FULL, false);
/*     */         
/* 193 */         if (!(chunk instanceof LevelChunk)) {
/* 194 */           return false;
/*     */         }
/* 196 */         FullChunkStatus type = ((LevelChunk)chunk).getFullStatus();
/* 197 */         if (!type.isOrAfter(FullChunkStatus.BLOCK_TICKING)) {
/* 198 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 202 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isLoaded(ServerLevel level, BlockPos pos) {
/* 207 */     ChunkAccess chunk = level.getChunk(SectionPos.blockToSectionCoord(pos.getX()), SectionPos.blockToSectionCoord(pos.getZ()), ChunkStatus.FULL, false);
/*     */     
/* 209 */     if (!(chunk instanceof LevelChunk)) {
/* 210 */       return false;
/*     */     }
/* 212 */     FullChunkStatus type = ((LevelChunk)chunk).getFullStatus();
/* 213 */     if (!type.isOrAfter(FullChunkStatus.ENTITY_TICKING)) {
/* 214 */       return false;
/*     */     }
/* 216 */     return (level.getChunkSource()).chunkMap.getDistanceManager().inEntityTickingRange(ChunkPos.asLong(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInAnOpenArea(Entity entity) {
/* 221 */     int radius = 5;
/* 222 */     Integer lowest = null;
/* 223 */     for (int x = -radius; x < radius; x++) {
/*     */       
/* 225 */       for (int z = -radius; z < radius; z++) {
/*     */         
/* 227 */         int height = entity.level().getHeight(Heightmap.Types.WORLD_SURFACE, entity.getBlockX() + x, entity.getBlockZ() + z);
/* 228 */         if (lowest == null || lowest.intValue() > height)
/* 229 */           lowest = Integer.valueOf(height); 
/*     */       } 
/*     */     } 
/* 232 */     return (entity.getY() >= lowest.intValue() - 10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasLineOfSight(Entity caster, Entity target) {
/* 237 */     return (raycast(caster, target, 300.0D).getType() == HitResult.Type.MISS);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canSeeOrIsNotInASmallArea(Entity entity, Entity target) {
/* 242 */     PlayerWitherStormData data = (PlayerWitherStormData)target.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).orElse(null);
/* 243 */     if (data != null) {
/* 244 */       return (data.isInAnOpenArea() || hasLineOfSight(entity, target));
/*     */     }
/* 246 */     return (isInAnOpenArea(target) || hasLineOfSight(entity, target));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockState> getBlockStatesBetweenClosed(Level level, BlockPos min, BlockPos max) {
/* 251 */     List<BlockState> states = Lists.newArrayList();
/* 252 */     for (BlockPos pos : BlockPos.betweenClosed(min, max))
/* 253 */       states.add(level.getBlockState(pos)); 
/* 254 */     return states;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getHeightStartingAt(Level level, int height, int x, int z) {
/* 259 */     BlockPos pos = new BlockPos(x, height, z);
/* 260 */     LevelChunk chunk = level.getChunkAt(pos);
/* 261 */     while (pos.getY() > level.getMinBuildHeight() && chunk.getBlockState(pos).isAir())
/* 262 */       pos = pos.below(); 
/* 263 */     return pos.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCeilingStartingAt(Level level, int height, int x, int z) {
/* 268 */     BlockPos pos = new BlockPos(x, height, z);
/* 269 */     while (pos.getY() < level.getMaxBuildHeight() && level.getBlockState(pos).isAir())
/* 270 */       pos = pos.above(); 
/* 271 */     return pos.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public static BlockHitResult raycast(Entity caster, Entity entity, double maxDist) {
/* 276 */     Vec3 pos = caster.getEyePosition();
/* 277 */     Vec3 entityPos = entity.getEyePosition();
/* 278 */     Vec3 delta = entityPos.subtract(pos);
/* 279 */     double dist = Math.sqrt(delta.x * delta.x + delta.y * delta.y + delta.z * delta.z);
/* 280 */     delta = delta.scale((dist > maxDist) ? (maxDist / dist) : 1.0D);
/* 281 */     Vec3 end = pos.add(delta);
/* 282 */     return caster.level().clip(new ClipContext(pos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, caster));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static BlockPos forEachBlockSpiralOutwards(Level level, BlockPos starting, int radius, Predicate<BlockPos> consumer) {
/* 287 */     if (consumer.test(starting)) {
/* 288 */       return starting;
/*     */     }
/* 290 */     for (int i = 0; i <= radius; i++) {
/*     */       
/* 292 */       for (int x = -i; x <= i; x++) {
/*     */         
/* 294 */         for (int z = -i; z <= i; z++) {
/*     */           
/* 296 */           BlockPos first = (new BlockPos(x, i, z)).offset((Vec3i)starting);
/* 297 */           BlockPos second = (new BlockPos(x, -i, z)).offset((Vec3i)starting);
/* 298 */           if (consumer.test(first))
/* 299 */             return first; 
/* 300 */           if (consumer.test(second)) {
/* 301 */             return second;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       int y;
/* 306 */       for (y = -(i - 1); y <= i - 1; y++) {
/*     */         
/* 308 */         for (int j = -i; j <= i; j++) {
/*     */           
/* 310 */           BlockPos first = (new BlockPos(j, y, i)).offset((Vec3i)starting);
/* 311 */           BlockPos second = (new BlockPos(j, y, -i)).offset((Vec3i)starting);
/* 312 */           if (consumer.test(first))
/* 313 */             return first; 
/* 314 */           if (consumer.test(second)) {
/* 315 */             return second;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 320 */       for (y = -(i - 1); y <= i - 1; y++) {
/*     */         
/* 322 */         for (int z = -(i - 1); z <= i - 1; z++) {
/*     */           
/* 324 */           BlockPos first = (new BlockPos(i, y, z)).offset((Vec3i)starting);
/* 325 */           BlockPos second = (new BlockPos(-i, -y, z)).offset((Vec3i)starting);
/* 326 */           if (consumer.test(first))
/* 327 */             return first; 
/* 328 */           if (consumer.test(second)) {
/* 329 */             return second;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 334 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Pair<BlockPos, StructureStart> findNearestMapStructure(ServerLevel level, TagKey<Structure> tag, BlockPos pos, int radius, boolean addReference) {
/* 339 */     if (!level.m_7654_().m_129910_().worldGenOptions().generateStructures())
/*     */     {
/* 341 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 345 */     Optional<HolderSet.Named<Structure>> optional = level.registryAccess().registryOrThrow(Registries.STRUCTURE).getTag(tag);
/* 346 */     if (optional.isEmpty())
/*     */     {
/* 348 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 352 */     return findNearestMapStructure(level, (HolderSet<Structure>)optional.get(), pos, radius, addReference);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static Pair<BlockPos, StructureStart> findNearestMapStructure(ServerLevel level, HolderSet<Structure> set, BlockPos pos, int radius, boolean addReference) {
/* 359 */     ChunkGeneratorStructureState chunkgeneratorstructurestate = level.getChunkSource().getGeneratorState();
/* 360 */     Object2ObjectArrayMap<StructurePlacement, Set<Holder<Structure>>> object2ObjectArrayMap = new Object2ObjectArrayMap();
/*     */     
/* 362 */     for (Holder<Structure> holder : set) {
/*     */       
/* 364 */       for (StructurePlacement structureplacement : chunkgeneratorstructurestate.getPlacementsForStructure(holder))
/*     */       {
/* 366 */         ((Set<Holder<Structure>>)object2ObjectArrayMap.computeIfAbsent(structureplacement, p -> new ObjectArraySet()))
/*     */           
/* 368 */           .add(holder);
/*     */       }
/*     */     } 
/*     */     
/* 372 */     if (object2ObjectArrayMap.isEmpty())
/*     */     {
/* 374 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 378 */     Pair<BlockPos, StructureStart> pair2 = null;
/* 379 */     double d2 = Double.MAX_VALUE;
/* 380 */     StructureManager structuremanager = level.structureManager();
/* 381 */     List<Map.Entry<StructurePlacement, Set<Holder<Structure>>>> list = new ArrayList<>(object2ObjectArrayMap.size());
/*     */     
/* 383 */     for (Map.Entry<StructurePlacement, Set<Holder<Structure>>> entry : object2ObjectArrayMap.entrySet()) {
/*     */       
/* 385 */       StructurePlacement structureplacement1 = entry.getKey();
/* 386 */       if (structureplacement1 instanceof ConcentricRingsStructurePlacement) {
/*     */         
/* 388 */         ConcentricRingsStructurePlacement concentricringsstructureplacement = (ConcentricRingsStructurePlacement)structureplacement1;
/* 389 */         Pair<BlockPos, StructureStart> pair = getNearestGeneratedConcentricRingStructure(entry.getValue(), level, structuremanager, pos, addReference, concentricringsstructureplacement);
/* 390 */         if (pair != null) {
/*     */           
/* 392 */           BlockPos blockpos = (BlockPos)pair.getFirst();
/* 393 */           double d0 = pos.distSqr((Vec3i)blockpos);
/* 394 */           if (d0 < d2) {
/*     */             
/* 396 */             d2 = d0;
/* 397 */             pair2 = pair;
/*     */           } 
/*     */         }  continue;
/*     */       } 
/* 401 */       if (structureplacement1 instanceof RandomSpreadStructurePlacement)
/*     */       {
/* 403 */         list.add(entry);
/*     */       }
/*     */     } 
/*     */     
/* 407 */     if (!list.isEmpty()) {
/*     */       
/* 409 */       int i = SectionPos.blockToSectionCoord(pos.getX());
/* 410 */       int j = SectionPos.blockToSectionCoord(pos.getZ());
/*     */       
/* 412 */       for (int k = 0; k <= radius; k++) {
/*     */         
/* 414 */         boolean flag = false;
/*     */         
/* 416 */         for (Map.Entry<StructurePlacement, Set<Holder<Structure>>> entry1 : list) {
/*     */           
/* 418 */           RandomSpreadStructurePlacement randomspreadstructureplacement = (RandomSpreadStructurePlacement)entry1.getKey();
/* 419 */           Pair<BlockPos, StructureStart> pair1 = getNearestGeneratedRandomPlacementStructure(entry1.getValue(), (LevelReader)level, structuremanager, i, j, k, addReference, chunkgeneratorstructurestate.getLevelSeed(), randomspreadstructureplacement);
/* 420 */           if (pair1 != null) {
/*     */             
/* 422 */             flag = true;
/* 423 */             double d1 = pos.distSqr((Vec3i)pair1.getFirst());
/* 424 */             if (d1 < d2) {
/*     */               
/* 426 */               d2 = d1;
/* 427 */               pair2 = pair1;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 432 */         if (flag)
/*     */         {
/* 434 */           return pair2;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 439 */     return pair2;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static Pair<BlockPos, StructureStart> getNearestGeneratedConcentricRingStructure(Set<Holder<Structure>> set, ServerLevel level, StructureManager manager, BlockPos pos, boolean addReference, ConcentricRingsStructurePlacement placement) {
/* 445 */     List<ChunkPos> list = level.getChunkSource().getGeneratorState().getRingPositionsFor(placement);
/* 446 */     if (list == null)
/*     */     {
/* 448 */       throw new IllegalStateException("Somehow tried to find structures for a placement that doesn't exist");
/*     */     }
/*     */ 
/*     */     
/* 452 */     Pair<BlockPos, StructureStart> pair = null;
/* 453 */     double d0 = Double.MAX_VALUE;
/* 454 */     BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
/*     */     
/* 456 */     for (ChunkPos chunkpos : list) {
/*     */       
/* 458 */       blockpos$mutableblockpos.set(SectionPos.sectionToBlockCoord(chunkpos.x, 8), 32, SectionPos.sectionToBlockCoord(chunkpos.z, 8));
/* 459 */       double d1 = blockpos$mutableblockpos.distSqr((Vec3i)pos);
/* 460 */       boolean flag = (pair == null || d1 < d0);
/* 461 */       if (flag) {
/*     */         
/* 463 */         Pair<BlockPos, StructureStart> pair1 = getStructureGeneratingAt(set, (LevelReader)level, manager, addReference, (StructurePlacement)placement, chunkpos);
/* 464 */         if (pair1 != null) {
/*     */           
/* 466 */           pair = pair1;
/* 467 */           d0 = d1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 472 */     return pair;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static Pair<BlockPos, StructureStart> getNearestGeneratedRandomPlacementStructure(Set<Holder<Structure>> set, LevelReader level, StructureManager manager, int xSectionCoord, int zSectionCoord, int radius, boolean addReference, long seed, RandomSpreadStructurePlacement placement) {
/* 478 */     int i = placement.spacing();
/*     */     
/* 480 */     for (int j = -radius; j <= radius; j++) {
/*     */       
/* 482 */       boolean flag = (j == -radius || j == radius);
/*     */       
/* 484 */       for (int k = -radius; k <= radius; k++) {
/*     */         
/* 486 */         boolean flag1 = (k == -radius || k == radius);
/* 487 */         if (flag || flag1) {
/*     */           
/* 489 */           int l = xSectionCoord + i * j;
/* 490 */           int i1 = zSectionCoord + i * k;
/* 491 */           ChunkPos chunkpos = placement.getPotentialStructureChunk(seed, l, i1);
/* 492 */           Pair<BlockPos, StructureStart> pair = getStructureGeneratingAt(set, level, manager, addReference, (StructurePlacement)placement, chunkpos);
/* 493 */           if (pair != null)
/*     */           {
/* 495 */             return pair;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 501 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Pair<BlockPos, StructureStart> getStructureGeneratingAt(Set<Holder<Structure>> set, LevelReader level, StructureManager manager, boolean addReference, StructurePlacement placement, ChunkPos pos) {
/* 506 */     for (Holder<Structure> holder : set) {
/*     */       
/* 508 */       StructureCheckResult structurecheckresult = manager.checkStructurePresence(pos, (Structure)holder.value(), addReference);
/* 509 */       if (structurecheckresult != StructureCheckResult.START_NOT_PRESENT) {
/*     */         
/* 511 */         ChunkAccess chunkaccess = level.getChunk(pos.x, pos.z, ChunkStatus.STRUCTURE_STARTS);
/* 512 */         StructureStart structurestart = manager.getStartForStructure(SectionPos.bottomOf(chunkaccess), (Structure)holder.value(), (StructureAccess)chunkaccess);
/* 513 */         if (structurestart != null && structurestart.isValid() && (!addReference || tryAddReference(manager, structurestart)))
/*     */         {
/* 515 */           return Pair.of(placement.getLocatePos(structurestart.getChunkPos()), structurestart);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 520 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean tryAddReference(StructureManager manager, StructureStart start) {
/* 525 */     if (start.canBeReferenced()) {
/*     */       
/* 527 */       manager.addReference(start);
/* 528 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 532 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBlockExposed(Level level, BlockPos pos) {
/* 538 */     BlockState posState = level.getBlockState(pos);
/*     */     
/* 540 */     if (posState.hasProperty((Property)BlockStateProperties.WATERLOGGED))
/*     */     {
/* 542 */       return true;
/*     */     }
/*     */     
/* 545 */     if (level.isEmptyBlock(pos) || level.isWaterAt(pos))
/*     */     {
/* 547 */       return false;
/*     */     }
/*     */     
/* 550 */     for (Direction direction : Direction.values()) {
/*     */       
/* 552 */       BlockPos neighborPos = pos.relative(direction);
/* 553 */       if (level.isEmptyBlock(neighborPos) || level.isWaterAt(neighborPos))
/*     */       {
/* 555 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 559 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void doCaveRumble(ServerLevel level, ServerPlayer player, double rumbleIntensity, RandomSource random) {
/* 564 */     int ceiling = getCeilingStartingAt((Level)level, player.getBlockY(), player.getBlockX(), player.getBlockZ());
/* 565 */     int chance = (int)(10.0D * (1.0D - Math.sqrt(rumbleIntensity)));
/* 566 */     int caveVinesBerryDropChance = (int)(4.0D * (1.0D - Math.sqrt(rumbleIntensity)));
/* 567 */     int caveInChance = (int)(12.0D * (1.0D - Math.sqrt(rumbleIntensity)));
/* 568 */     int dripStoneLengthChance = (int)(6.0D * (1.0D - Math.sqrt(rumbleIntensity)));
/* 569 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> player), new ShakeScreenMessage(180.0F, (float)(12.0D * Math.sqrt(((Double)WitherStormModConfig.SERVER.caveRumbleIntensity.get()).doubleValue()))));
/* 570 */     player.playNotifySound((SoundEvent)WitherStormModSoundEvents.EARTH_RUMBLE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
/* 571 */     if (ceiling < level.getMaxBuildHeight()) {
/*     */       
/* 573 */       int i = 64;
/* 574 */       for (int j = -i; j < i; j++) {
/*     */         
/* 576 */         for (int z = -i; z < i; z++) {
/*     */           
/* 578 */           if (chance < 1 || random.nextInt(chance) == 0) {
/*     */             
/* 580 */             int finalX = player.getBlockX() + j;
/* 581 */             int finalZ = player.getBlockZ() + z;
/* 582 */             int y = getCeilingStartingAt((Level)level, player.getBlockY() + 4, finalX, finalZ);
/* 583 */             BlockPos pos = new BlockPos(finalX, y, finalZ);
/* 584 */             BlockState state = level.getBlockState(pos);
/* 585 */             boolean flag = true;
/* 586 */             for (int k = 0; k < 20; k++) {
/*     */               
/* 588 */               BlockState current = level.getBlockState(pos);
/* 589 */               if (current.is(Blocks.POINTED_DRIPSTONE) && (dripStoneLengthChance < 1 || random.nextInt(dripStoneLengthChance) == 0)) {
/*     */                 
/* 591 */                 if (!MixinPointedDripstoneBlock.callIsStalagmite(current))
/* 592 */                   MixinPointedDripstoneBlock.callSpawnFallingStalactite(current, level, pos); 
/* 593 */                 flag = true;
/*     */                 break;
/*     */               } 
/* 596 */               if (current.getBlock() instanceof CaveVines && (caveVinesBerryDropChance < 1 || random.nextInt(caveVinesBerryDropChance) == 0))
/*     */               {
/* 598 */                 if (CaveVines.hasGlowBerries(current)) {
/*     */                   
/* 600 */                   CaveVines.use(null, current, (Level)level, pos);
/* 601 */                   flag = true;
/*     */                 } 
/*     */               }
/* 604 */               pos = pos.above();
/*     */             } 
/* 606 */             if (flag) {
/*     */               
/* 608 */               pos = pos.atY(y);
/* 609 */               BlockState below = level.getBlockState(pos.below());
/* 610 */               boolean canDropBlock = (FallingBlock.isFree(below) && pos.getY() >= level.getMinBuildHeight());
/* 611 */               Block block = state.getBlock(); if (block instanceof FallingBlock) { FallingBlock fallingBlock = (FallingBlock)block; if (!state.is(Blocks.POINTED_DRIPSTONE) && canDropBlock) {
/*     */                   
/* 613 */                   FallingBlockEntity entity = FallingBlockEntity.fall((Level)level, pos, state);
/* 614 */                   ((MixinFallingBlock)fallingBlock).callFalling(entity);
/*     */                 }  }
/* 616 */                if (!state.is(WitherStormModBlockTags.CAVE_IN_BLACKLIST) && canDropBlock && (caveInChance < 1 || random.nextInt(caveInChance) == 0)) {
/*     */                 
/* 618 */                 double randomMomentum = 0.25D * random.nextDouble();
/* 619 */                 int rotationDelta = random.nextInt(257) - 128;
/* 620 */                 BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).create((Level)level);
/* 621 */                 cluster.populateWithRadius(pos, 1.0F, blockstate -> !blockstate.is(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
/* 622 */                 cluster.setTime(50);
/* 623 */                 cluster.setShouldCrumble(false);
/* 624 */                 cluster.setRotationDelta(new Vec2(rotationDelta * 0.0625F, rotationDelta * 0.0625F));
/* 625 */                 cluster.setNoGravity(false);
/* 626 */                 cluster.setDeltaMovement(0.0D, randomMomentum, 0.0D);
/* 627 */                 cluster.setPhysics(true);
/* 628 */                 level.addFreshEntity((Entity)cluster);
/*     */               } 
/* 630 */               if (isBlockExposed((Level)level, pos)) {
/*     */                 
/* 632 */                 level.sendParticles((ParticleOptions)new BlockParticleOption(ParticleTypes.FALLING_DUST, state), finalX + 0.5D, y, finalZ + 0.5D, 2, 0.5D, 0.5D, 0.5D, 0.0D);
/* 633 */                 level.sendParticles((ParticleOptions)new BlockParticleOption(ParticleTypes.BLOCK, state), finalX + 0.5D, y, finalZ + 0.5D, 4, 0.5D, 0.5D, 0.5D, 1.0D);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 640 */     int radius = 16;
/* 641 */     for (int x = -radius; x < radius; x++) {
/*     */       
/* 643 */       for (int y = -radius; y < radius; y++) {
/*     */         
/* 645 */         for (int z = -radius; z < radius; z++) {
/*     */           
/* 647 */           if (chance < 1 || random.nextInt(chance) == 0) {
/*     */             
/* 649 */             int finalX = player.getBlockX() + x;
/* 650 */             int finalY = player.getBlockY() + y;
/* 651 */             int finalZ = player.getBlockZ() + z;
/* 652 */             BlockPos pos = new BlockPos(finalX, finalY, finalZ);
/* 653 */             BlockState state = level.getBlockState(pos);
/* 654 */             Block block = state.getBlock();
/* 655 */             if (block instanceof RedstoneLampBlock)
/*     */             
/* 657 */             { if (((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue() && !((Boolean)state.getValue((Property)RedstoneLampBlock.LIT)).booleanValue())
/*     */               {
/* 659 */                 level.setBlock(pos, (BlockState)state.setValue((Property)RedstoneLampBlock.LIT, Boolean.TRUE), 2, 0);
/* 660 */                 level.scheduleTick(pos, block, 20 + random.nextInt(10));
/*     */               }
/*     */                }
/* 663 */             else if (block instanceof TrapDoorBlock)
/*     */             
/* 665 */             { boolean isOpen = ((Boolean)state.getValue((Property)TrapDoorBlock.OPEN)).booleanValue();
/* 666 */               boolean isBottom = (state.getValue((Property)TrapDoorBlock.HALF) == Half.BOTTOM);
/* 667 */               if (isOpen && isBottom)
/*     */               {
/* 669 */                 level.setBlockAndUpdate(pos, (BlockState)state.setValue((Property)TrapDoorBlock.OPEN, Boolean.FALSE));
/*     */               }
/* 671 */               if (!isOpen && !isBottom)
/*     */               {
/* 673 */                 level.setBlockAndUpdate(pos, (BlockState)state.setValue((Property)TrapDoorBlock.OPEN, Boolean.TRUE));
/*     */               } }
/*     */             
/* 676 */             else if (block instanceof RedStoneOreBlock)
/*     */             
/* 678 */             { int randomized = random.nextInt(70);
/* 679 */               level.setBlock(pos, (BlockState)state.setValue((Property)RedStoneOreBlock.LIT, Boolean.TRUE), 2);
/* 680 */               level.scheduleTick(pos, block, 10 + randomized); }
/*     */             
/* 682 */             else if (block instanceof LeverBlock)
/*     */             
/* 684 */             { if (((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue()) {
/* 685 */                 level.setBlockAndUpdate(pos, (BlockState)state.cycle((Property)LeverBlock.POWERED));
/*     */               } }
/* 687 */             else if (block instanceof ButtonBlock) { ButtonBlock buttonBlock = (ButtonBlock)block;
/*     */               
/* 689 */               if (((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue()) {
/* 690 */                 buttonBlock.press(state, (Level)level, pos);
/*     */               } }
/* 692 */             else if (block instanceof PressurePlateBlock)
/*     */             
/* 694 */             { if (((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue())
/*     */               {
/* 696 */                 int randomized = random.nextInt(60);
/* 697 */                 level.setBlock(pos, (BlockState)state.setValue((Property)PressurePlateBlock.POWERED, Boolean.TRUE), 2);
/* 698 */                 level.scheduleTick(pos, block, randomized);
/*     */               }
/*     */                }
/* 701 */             else if (block instanceof SculkSensorBlock) { SculkSensorBlock sculkSensor = (SculkSensorBlock)block;
/*     */               
/* 703 */               sculkSensor.stepOn((Level)level, pos, state, (Entity)player); }
/*     */             
/* 705 */             else if (block instanceof NoteBlock)
/*     */             
/* 707 */             { if (((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue()) {
/* 708 */                 level.setBlockAndUpdate(pos, (BlockState)state.cycle((Property)NoteBlock.NOTE));
/*     */               } }
/* 710 */             else if (block instanceof BellBlock) { BellBlock bellBlock = (BellBlock)block;
/*     */               
/* 712 */               bellBlock.attemptToRing((Level)level, pos, null); }
/*     */           
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Integer> getStormIds(WitherStormEntity entity) {
/* 722 */     return (List<Integer>)getAllStorms((ServerLevel)entity.level()).stream().collect(Collectors.mapping(Entity::getId, Collectors.toList()));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Mob summonRandomMob(ServerLevel world, BlockPos start, RandomSource random, int diameter, SimpleWeightedRandomList<EntityType<? extends Mob>> types, boolean advancedMobs) {
/* 727 */     EntityType<? extends Mob> type = types.getRandomValue(random).get();
/* 728 */     BlockPos pos = getRandomNearbyPos((Level)world, start, random, type, diameter, 5);
/* 729 */     if (pos != null && hasEnoughSpace((Level)world, type.getDimensions(), pos)) {
/*     */       
/* 731 */       Mob entity = (Mob)type.create((Level)world);
/* 732 */       DifficultyInstance difficulty = world.getCurrentDifficultyAt(entity.blockPosition());
/* 733 */       ForgeEventFactory.onFinalizeSpawn(entity, (ServerLevelAccessor)world, difficulty, MobSpawnType.TRIGGERED, null, null);
/* 734 */       if (WitherSickened.CAN_WEAR_ARMOR.test(entity) && entity instanceof Monster) { Monster monster = (Monster)entity;
/* 735 */         EquipmentHelper.applyEquipment(monster, difficulty, advancedMobs); }
/* 736 */        entity.setPos(pos.getX() + 0.5D, (pos.getY() + 1), pos.getZ() + 0.5D);
/* 737 */       entity.playAmbientSound();
/* 738 */       entity.spawnAnim();
/* 739 */       entity.setPersistenceRequired();
/* 740 */       world.sendParticles((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), entity.getX(), entity.getEyeY(), entity.getZ(), 20, random.nextGaussian(), random.nextGaussian(), random.nextGaussian(), 0.2D);
/* 741 */       world.sendParticles((ParticleOptions)ParticleTypes.LARGE_SMOKE, entity.getX(), entity.getEyeY(), entity.getZ(), 20, random.nextGaussian(), random.nextGaussian(), random.nextGaussian(), 0.01D);
/* 742 */       world.addFreshEntityWithPassengers((Entity)entity);
/* 743 */       return entity;
/*     */     } 
/* 745 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static BlockPos getRandomNearbyPos(Level level, BlockPos start, RandomSource random, EntityType<?> type, int diameter, int attempts) {
/* 750 */     for (int i = 0; i < attempts; i++) {
/*     */       
/* 752 */       int x = start.getX() + random.nextInt(diameter) - diameter / 2;
/* 753 */       int z = start.getZ() + random.nextInt(diameter) - diameter / 2;
/* 754 */       int y = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, 0, z)).getY();
/* 755 */       BlockPos pos = new BlockPos(x, y, z);
/*     */       
/* 757 */       if (NaturalSpawner.isSpawnPositionOk(SpawnPlacements.Type.ON_GROUND, (LevelReader)level, pos, type) && pos.distSqr((Vec3i)start) > 6.0D)
/* 758 */         return pos; 
/*     */     } 
/* 760 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean hasEnoughSpace(Level level, EntityDimensions dimensions, BlockPos spawnPos) {
/* 765 */     for (BlockPos pos : BlockPos.betweenClosed(spawnPos, spawnPos.offset((Vec3i)BlockPos.containing(dimensions.width, dimensions.height, dimensions.width)))) {
/*     */       
/* 767 */       if (!level.getBlockState(pos).getCollisionShape((BlockGetter)level, pos).isEmpty())
/* 768 */         return false; 
/*     */     } 
/* 770 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\WorldUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */