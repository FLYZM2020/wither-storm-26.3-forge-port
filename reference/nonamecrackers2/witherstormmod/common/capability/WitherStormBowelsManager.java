/*     */ package nonamecrackers2.witherstormmod.common.capability;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.SectionPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.server.level.TicketType;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.MobSpawnType;
/*     */ import net.minecraft.world.entity.SpawnPlacements;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.NaturalSpawner;
/*     */ import net.minecraft.world.level.StructureManager;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.Rotation;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.chunk.ChunkAccess;
/*     */ import net.minecraft.world.level.chunk.ChunkStatus;
/*     */ import net.minecraft.world.level.chunk.StructureAccess;
/*     */ import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
/*     */ import net.minecraft.world.level.levelgen.structure.Structure;
/*     */ import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
/*     */ import net.minecraft.world.level.levelgen.structure.StructurePiece;
/*     */ import net.minecraft.world.level.levelgen.structure.StructureStart;
/*     */ import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
/*     */ import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
/*     */ import net.minecraftforge.common.util.ITeleporter;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.level.LevelEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModFeatures;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
/*     */ import nonamecrackers2.witherstormmod.common.util.BowelsTeleporter;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class WitherStormBowelsManager {
/*  77 */   private static final Logger LOGGER = LogManager.getLogger("witherstormmod/WitherStormBowelsManager");
/*     */   private static final int ENTRANCE_SEARCH_RADIUS = 96;
/*     */   private static final int ENTRANCE_SEARCH_RADIUS_MIN = 35;
/*  80 */   private static BlockPos NORTH_HEAD_POS = new BlockPos(-2, 128, 27);
/*  81 */   private static BlockPos SOUTH_HEAD_POS = new BlockPos(-3, 128, -23);
/*  82 */   private final Map<UUID, Entity> queuedEnter = new HashMap<>();
/*  83 */   private final Map<Entity, Runnable> queuedLeave = new HashMap<>();
/*  84 */   private final List<BowelsInstance> instances = new ArrayList<>();
/*     */   private final ServerLevel world;
/*  86 */   private final Random random = new Random();
/*     */   @Nullable
/*     */   private Pair<BlockPos, StructureStart> nextAvailableStructure;
/*     */   
/*     */   public WitherStormBowelsManager(ServerLevel world) {
/*  91 */     this.world = world;
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormBowelsManager() {
/*  96 */     this.world = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundTag compound) {
/* 101 */     ListTag list = compound.m_128437_("Instances", 10);
/* 102 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 104 */       CompoundTag instanceCompound = list.m_128728_(i);
/* 105 */       this.instances.add(BowelsInstance.read(instanceCompound));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag write() {
/* 111 */     CompoundTag compound = new CompoundTag();
/* 112 */     ListTag list = new ListTag();
/* 113 */     for (BowelsInstance instance : this.instances)
/* 114 */       list.add(instance.write()); 
/* 115 */     compound.m_128365_("Instances", (Tag)list);
/* 116 */     return compound;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BowelsInstance getOrCreateInstanceFor(WitherStormEntity storm) {
/* 121 */     BowelsInstance instance = get(storm.m_20148_());
/* 122 */     if (instance == null) {
/*     */       Pair<BlockPos, StructureStart> start;
/* 124 */       LOGGER.debug("Searching for available bowels arena for entity {}", storm);
/*     */       
/* 126 */       if (this.nextAvailableStructure != null) {
/*     */         
/* 128 */         LOGGER.debug("Using pre-located structure...");
/* 129 */         start = this.nextAvailableStructure;
/* 130 */         this.nextAvailableStructure = null;
/*     */       }
/*     */       else {
/*     */         
/* 134 */         start = getAvailableStructure();
/*     */       } 
/* 136 */       if (start != null) {
/* 137 */         instance = new BowelsInstance(start, storm.m_20148_());
/*     */       } else {
/* 139 */         LOGGER.error("Could not find an available bowels structure for {}. This shouldn't happen!", storm);
/*     */       } 
/* 141 */     }  return instance;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Pair<BlockPos, StructureStart> getAvailableStructure() {
/* 146 */     if (!this.world.m_7654_().m_129910_().m_246337_().m_247749_()) {
/*     */       
/* 148 */       this.world.m_7654_().m_6846_().m_11314_().forEach(player -> player.m_213846_((Component)Component.m_237115_("chat.witherstormmod.bowels.structuresDisabled").m_130940_(ChatFormatting.RED)));
/* 149 */       LOGGER.warn("Structures are disabled, meaning the bowels cannot be accessed. Please enable structures to have access to the final boss battle.");
/* 150 */       return null;
/*     */     } 
/* 152 */     Holder<Structure> holder = getBowels(this.world);
/* 153 */     StructureManager manager = this.world.m_215010_();
/*     */     
/* 155 */     BlockPos startPos = BlockPos.f_121853_;
/* 156 */     if (!this.instances.isEmpty()) {
/* 157 */       startPos = ((BowelsInstance)this.instances.get(this.instances.size() - 1)).getPos();
/*     */     }
/* 159 */     RandomSpreadStructurePlacement placement = null;
/* 160 */     for (StructurePlacement structurePlacement : this.world.m_7726_().m_255415_().m_255260_(holder)) {
/*     */       
/* 162 */       if (structurePlacement instanceof RandomSpreadStructurePlacement) { RandomSpreadStructurePlacement p = (RandomSpreadStructurePlacement)structurePlacement;
/*     */         
/* 164 */         placement = p;
/*     */         
/*     */         break; }
/*     */     
/*     */     } 
/* 169 */     if (placement == null) {
/*     */       
/* 171 */       LOGGER.error("The Bowels structure must be the random spread placement type!");
/* 172 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 176 */     int sectionX = SectionPos.m_123171_(startPos.m_123341_());
/* 177 */     int sectionZ = SectionPos.m_123171_(startPos.m_123343_());
/*     */     
/* 179 */     for (int area = 0; area <= 100; area++) {
/*     */       
/* 181 */       int i = placement.m_205003_();
/*     */       
/* 183 */       for (int x = -area; x <= area; x++) {
/*     */         
/* 185 */         boolean xFlag = (x == -area || x == area);
/*     */         
/* 187 */         for (int z = -area; z <= area; z++) {
/*     */           
/* 189 */           boolean zFlag = (z == -area || z == area);
/* 190 */           if (xFlag || zFlag) {
/*     */             
/* 192 */             int finalX = sectionX + i * x;
/* 193 */             int finalZ = sectionZ + i * z;
/* 194 */             ChunkPos potential = placement.m_227008_(this.world.m_7328_(), finalX, finalZ);
/*     */             
/* 196 */             StructureCheckResult result = manager.m_220473_(potential, (Structure)holder.m_203334_(), false);
/* 197 */             if (result != StructureCheckResult.START_NOT_PRESENT) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 205 */               ChunkAccess chunk = this.world.m_46819_(potential.f_45578_, potential.f_45579_, ChunkStatus.f_62315_);
/* 206 */               StructureStart start = manager.m_220512_(SectionPos.m_175562_(chunk), (Structure)holder.m_203334_(), (StructureAccess)chunk);
/* 207 */               if (start != null && start.m_73603_()) {
/*     */                 
/* 209 */                 BlockPos locatePos = placement.m_227039_(start.m_163625_());
/* 210 */                 if (((List)this.instances.stream().filter(instance -> instance.getPos().equals(locatePos)).collect(Collectors.toList())).isEmpty()) {
/* 211 */                   return Pair.of(locatePos, start);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 219 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BowelsInstance get(UUID uuid) {
/* 224 */     for (BowelsInstance instance : this.instances) {
/*     */       
/* 226 */       if (instance.witherStorm.equals(uuid) && !instance.isCompleted())
/* 227 */         return instance; 
/*     */     } 
/* 229 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(BowelsInstance instance) {
/* 234 */     if (!this.instances.contains(instance)) {
/* 235 */       this.instances.add(instance);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onLoad() {
/* 240 */     for (BowelsInstance instance : this.instances) {
/* 241 */       instance.setup(this.world);
/*     */     }
/* 243 */     this.nextAvailableStructure = getAvailableStructure();
/* 244 */     LOGGER.debug("Pre-locating a bowels arena: {}", this.nextAvailableStructure);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private WitherStormEntity findStorm(UUID uuid) {
/* 249 */     MinecraftServer server = this.world.m_7654_();
/* 250 */     for (Level world : server.m_129785_()) {
/*     */       
/* 252 */       ServerLevel serverWorld = (ServerLevel)world;
/* 253 */       Entity entity = serverWorld.m_8791_(uuid);
/* 254 */       if (entity != null && entity instanceof WitherStormEntity)
/* 255 */         return (WitherStormEntity)entity; 
/*     */     } 
/* 257 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos calculateEntrancePos(BowelsInstance instance) {
/* 262 */     BlockPos pos = instance.getPos();
/* 263 */     if (instance.getStart() != null) {
/*     */       
/* 265 */       boolean flag = true;
/* 266 */       if (((Boolean)WitherStormModConfig.SERVER.randomBowelsEntrace.get()).booleanValue())
/*     */       {
/*     */         
/* 269 */         for (int startRadius = 96; startRadius > 35; startRadius -= 10) {
/*     */           
/* 271 */           int randomStartAngle = this.random.nextInt(360);
/* 272 */           int angleOffset = 20;
/* 273 */           for (int i = 0; i < 360 / angleOffset; i++) {
/*     */             
/* 275 */             float angle = randomStartAngle + (angleOffset * i);
/* 276 */             int x = (int)(Mth.m_14031_(angle * 0.017453292F) * startRadius) + pos.m_123341_();
/* 277 */             int z = (int)(Mth.m_14089_(angle * 0.017453292F) * startRadius) + pos.m_123343_();
/* 278 */             BlockPos currentPos = new BlockPos(x, 112, z);
/* 279 */             for (int j = 0; j < 30 && this.world.m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); j++)
/* 280 */               currentPos = currentPos.m_7495_(); 
/* 281 */             if (NaturalSpawner.m_47051_(SpawnPlacements.Type.ON_GROUND, (LevelReader)this.world, currentPos, EntityType.f_20532_)) {
/*     */               
/* 283 */               int scanRadius = 10;
/* 284 */               int totalAir = 0;
/* 285 */               for (BlockState state : WorldUtil.getBlockStatesBetweenClosed((Level)this.world, currentPos.m_7918_(-scanRadius, -scanRadius, -scanRadius), currentPos.m_7918_(scanRadius, scanRadius, scanRadius))) {
/*     */                 
/* 287 */                 if (state.m_60795_())
/* 288 */                   totalAir++; 
/*     */               } 
/* 290 */               float bias = totalAir / 8000.0F;
/* 291 */               if (bias <= 0.2F) {
/*     */                 
/* 293 */                 pos = currentPos;
/* 294 */                 flag = false;
/*     */                 
/*     */                 // Byte code: goto -> 324
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/* 302 */       if (flag)
/*     */       {
/* 304 */         Rotation rotation = ((StructurePiece)instance.getStart().m_73602_().get(0)).m_6830_();
/* 305 */         BlockPos offset = BlockPos.f_121853_.m_5487_(Direction.Axis.X, -112).m_7954_(rotation);
/* 306 */         pos = pos.m_6630_(120);
/* 307 */         pos = pos.m_121955_((Vec3i)offset);
/* 308 */         BlockState state = this.world.m_8055_(pos);
/* 309 */         while (state.m_60713_(Blocks.f_50016_) && pos.m_123342_() > 0) {
/*     */           
/* 311 */           BlockPos below = pos.m_7495_();
/* 312 */           state = this.world.m_8055_(below);
/* 313 */           pos = below;
/*     */         } 
/* 315 */         pos = pos.m_7494_();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 320 */       LOGGER.error("Structure start is null: {}", instance);
/*     */     } 
/* 322 */     return pos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCompleted(WitherStormEntity entity, boolean completed) {
/* 327 */     BowelsInstance instance = get(entity.m_20148_());
/* 328 */     if (instance != null) {
/* 329 */       instance.setCompleted(completed);
/*     */     }
/*     */   }
/*     */   
/*     */   private void clean() {
/* 334 */     LOGGER.debug("Cleaning instances...");
/* 335 */     int successfullyCleaned = 0;
/* 336 */     for (BowelsInstance instance : this.instances) {
/*     */       
/* 338 */       WitherStormEntity storm = findStorm(instance.witherStorm);
/* 339 */       if (!instance.isCompleted()) {
/*     */         
/* 341 */         instance.setCompleted((storm == null || !storm.m_6084_()));
/* 342 */         if (instance.isCompleted()) {
/*     */           
/* 344 */           if (instance.getStart() != null) {
/*     */             
/* 346 */             instance.forceChunks(this.world, instance.getCenter(), false);
/* 347 */             successfullyCleaned++;
/*     */             
/*     */             continue;
/*     */           } 
/* 351 */           LOGGER.error("Bowels instance start is null! Cannot properly clean instance: {}", instance);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 356 */     LOGGER.debug("Finished cleaning; successfully cleaned {} instances", Integer.valueOf(successfullyCleaned));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BowelsInstance getInstanceFromEntity(Entity entity) {
/* 361 */     double distance = -1.0D;
/* 362 */     BowelsInstance finalInstance = null;
/*     */     
/* 364 */     for (BowelsInstance instance : this.instances) {
/*     */       
/* 366 */       double d0 = entity.m_20183_().m_123331_((Vec3i)instance.getPos());
/* 367 */       if (distance == -1.0D || d0 < distance) {
/*     */         
/* 369 */         distance = d0;
/* 370 */         finalInstance = instance;
/*     */       } 
/*     */     } 
/*     */     
/* 374 */     return finalInstance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void prepareArena(BowelsInstance instance, WitherStormEntity storm) {
/* 379 */     if (instance.getStart() != null) {
/*     */ 
/*     */       
/* 382 */       Rotation rotation = ((StructurePiece)instance.getStart().m_73602_().get(0)).m_6830_();
/* 383 */       BlockPos pos = instance.getCenter().m_6630_(110);
/* 384 */       BlockPos offset = BlockPos.f_121853_.m_5487_(Direction.Axis.X, -3).m_7954_(rotation);
/* 385 */       pos = pos.m_121955_((Vec3i)offset);
/* 386 */       if (!instance.hasPreparedArena()) {
/*     */         
/* 388 */         ((ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(this.world, WitherStormModFeatures.BOWELS_PODIUM_FEATURE.getId()).m_203334_()).m_224953_((WorldGenLevel)this.world, this.world.m_7726_().m_8481_(), RandomSource.m_216327_(), pos);
/*     */         
/* 390 */         int amount = 6 + this.random.nextInt(6);
/* 391 */         for (int i = 0; i < amount; i++)
/* 392 */           trySpawnTentacle(50, (Vec3i)pos); 
/* 393 */         spawnHeads(rotation, instance.getCenter());
/*     */         
/* 395 */         instance.setPreparedArena();
/*     */         
/* 397 */         LOGGER.debug("Prepared bowels arena for entity {}", storm);
/*     */       } 
/* 399 */       if (instance.commandBlock == null)
/*     */       {
/* 401 */         CommandBlockEntity entity = createCommandBlock(pos, rotation, storm);
/* 402 */         instance.commandBlock = entity.m_20148_();
/* 403 */         this.world.m_7967_((Entity)entity);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 408 */       LOGGER.error("Structure start is null: {}", instance);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void trySpawnTentacle(int diameter, Vec3i center) {
/* 414 */     BlockPos pos = null;
/*     */     
/* 416 */     for (int i = 0; i < 10; i++) {
/*     */       
/* 418 */       int x = center.m_123341_() + this.random.nextInt(diameter) - diameter / 2;
/* 419 */       int z = center.m_123343_() + this.random.nextInt(diameter) - diameter / 2;
/* 420 */       int y = center.m_123342_();
/* 421 */       BlockPos currentPos = new BlockPos(x, y, z);
/* 422 */       for (int j = 0; j < 10 && this.world.m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); j++)
/* 423 */         currentPos = currentPos.m_7495_(); 
/* 424 */       List<TentacleEntity> nearbyTentacles = this.world.m_45976_(TentacleEntity.class, (new AABB(currentPos)).m_82400_(10.0D));
/* 425 */       if (NaturalSpawner.m_47051_(SpawnPlacements.Type.ON_GROUND, (LevelReader)this.world, currentPos, (EntityType)WitherStormModEntityTypes.TENTACLE.get()) && nearbyTentacles.isEmpty() && Math.sqrt(currentPos.m_123331_(center)) > 10.0D) {
/*     */         
/* 427 */         pos = currentPos;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 432 */     if (pos != null) {
/*     */       
/* 434 */       TentacleEntity tentacle = (TentacleEntity)((EntityType)WitherStormModEntityTypes.TENTACLE.get()).m_262451_(this.world, null, null, pos, MobSpawnType.EVENT, false, false);
/* 435 */       if (tentacle != null && hasEnoughSpace((Entity)tentacle, pos)) {
/*     */         
/* 437 */         tentacle.setDormant(true);
/* 438 */         tentacle.lerpCurlTo(0.1F, 0.05F * (float)tentacle.m_217043_().m_188583_(), 8);
/* 439 */         this.world.m_47205_((Entity)tentacle);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasEnoughSpace(Entity entity, BlockPos spawnPos) {
/* 446 */     for (BlockPos pos : BlockPos.m_121940_(spawnPos, spawnPos.m_7918_(1, 8, 1))) {
/*     */       
/* 448 */       if (!this.world.m_8055_(pos).m_60812_((BlockGetter)this.world, pos).m_83281_())
/* 449 */         return false; 
/*     */     } 
/* 451 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawnHeads(Rotation rotation, BlockPos structurePos) {
/* 456 */     BlockPos[] offsets = { NORTH_HEAD_POS, SOUTH_HEAD_POS };
/* 457 */     for (int i = 0; i < 2; i++) {
/*     */       
/* 459 */       BlockPos offset = offsets[i];
/* 460 */       offset = offset.m_7954_(rotation);
/* 461 */       BlockPos pos = structurePos.m_121955_((Vec3i)offset);
/* 462 */       WitherStormHeadEntity head = (WitherStormHeadEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM_HEAD.get()).m_262451_(this.world, null, null, pos, MobSpawnType.EVENT, false, false);
/* 463 */       float rot = rotate(rotation) + ((i == 0) ? 180.0F : 0.0F);
/* 464 */       head.m_146922_(rot);
/* 465 */       head.m_146926_(60.0F);
/* 466 */       head.m_5618_(head.m_146908_());
/* 467 */       head.m_5616_(head.m_146908_());
/* 468 */       head.setActive(false);
/* 469 */       this.world.m_7967_((Entity)head);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private CommandBlockEntity createCommandBlock(BlockPos pos, Rotation rotation, WitherStormEntity storm) {
/* 475 */     CommandBlockEntity entity = (CommandBlockEntity)((EntityType)WitherStormModEntityTypes.COMMAND_BLOCK.get()).m_20615_((Level)this.world);
/* 476 */     entity.m_6034_(pos.m_123341_() + 0.5D, pos.m_123342_(), pos.m_123343_() + 0.5D);
/* 477 */     entity.setState(CommandBlockEntity.State.BOSSFIGHT);
/* 478 */     entity.setMode(CommandBlockEntity.Mode.TENTACLES);
/* 479 */     entity.setOwner(storm);
/* 480 */     entity.setOwnerUUID(storm.m_20148_());
/* 481 */     entity.m_146922_(rotate(rotation) + 90.0F);
/* 482 */     entity.m_5618_(entity.m_146908_());
/* 483 */     entity.m_5616_(entity.m_146908_());
/* 484 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   private static float rotate(Rotation rotation) {
/* 489 */     switch (rotation) {
/*     */       case CLOCKWISE_180:
/* 491 */         return 180.0F;
/*     */       case COUNTERCLOCKWISE_90:
/* 493 */         return 270.0F;
/*     */       case CLOCKWISE_90:
/* 495 */         return 90.0F;
/*     */     } 
/* 497 */     return 0.0F;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetEmptyTimeIfNeeded() {
/* 513 */     for (BowelsInstance instance : this.instances) {
/*     */       
/* 515 */       if (!instance.isCompleted()) {
/*     */         
/* 517 */         this.world.m_8886_();
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static BowelsEnterStatus enter(ServerLevel world, WitherStormEntity storm, Entity entity) {
/* 525 */     BowelsEnterStatus flag = BowelsEnterStatus.ENTITY_CANNOT_CHANGE;
/* 526 */     if (entity.isAddedToWorld() && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_()) {
/*     */       
/* 528 */       ServerLevel bowels = WitherStormMod.bowels(world);
/* 529 */       WitherStormBowelsManager manager = (WitherStormBowelsManager)bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).orElse(null);
/* 530 */       if (manager != null) {
/*     */         
/* 532 */         if (storm.m_6084_()) {
/*     */           
/* 534 */           BowelsInstance instance = manager.getOrCreateInstanceFor(storm);
/* 535 */           if (instance != null) {
/*     */             
/* 537 */             manager.add(instance);
/* 538 */             instance.setup(bowels);
/* 539 */             manager.prepareArena(instance, storm);
/* 540 */             BlockPos pos = manager.calculateEntrancePos(instance);
/* 541 */             entity.changeDimension(bowels, (ITeleporter)new BowelsTeleporter(pos));
/* 542 */             LOGGER.info("{} is entering the bowels of {}", entity, storm);
/* 543 */             flag = BowelsEnterStatus.SUCCESS;
/*     */           }
/*     */           else {
/*     */             
/* 547 */             flag = BowelsEnterStatus.CANT_SETUP_BOWELS;
/* 548 */             LOGGER.error("Failed to setup bowels! It's likely the mod could not locate an available bowels arena or failed to do so.");
/*     */           } 
/*     */         } 
/* 551 */         manager.clean();
/*     */       } 
/*     */     } 
/* 554 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void leave(ServerLevel world, Entity entity, @Nullable BowelsInstance instance) {
/* 559 */     if (entity.isAddedToWorld() && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_())
/*     */     {
/* 561 */       world.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
/*     */             BowelsInstance newInstance = instance;
/*     */             if (newInstance == null) {
/*     */               newInstance = manager.getInstanceFromEntity(entity);
/*     */             }
/*     */             if (newInstance != null) {
/*     */               WitherStormEntity storm = manager.findStorm(newInstance.witherStorm);
/*     */               if (storm != null) {
/*     */                 BlockPos pos = storm.m_20183_().m_6625_(5);
/*     */                 entity.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(());
/*     */                 Entity teleported = entity.changeDimension((ServerLevel)storm.m_9236_(), (ITeleporter)new BowelsTeleporter(pos));
/*     */                 if (teleported instanceof LivingEntity) {
/*     */                   LivingEntity living = (LivingEntity)teleported;
/*     */                   if (((Boolean)WitherStormModConfig.SERVER.bowelsFallResistance.get()).booleanValue()) {
/*     */                     living.m_7292_(new MobEffectInstance(MobEffects.f_19606_, 120, 255, false, false, false));
/*     */                   }
/*     */                 } 
/*     */               } else {
/*     */                 ServerLevel overworld = world.m_7654_().m_129880_(Level.f_46428_);
/*     */                 entity.changeDimension(overworld, (ITeleporter)new BowelsTeleporter(overworld.m_220360_()));
/*     */               } 
/*     */             } else {
/*     */               ServerLevel overworld = world.m_7654_().m_129880_(Level.f_46428_);
/*     */               entity.changeDimension(overworld, (ITeleporter)new BowelsTeleporter(overworld.m_220360_()));
/*     */             } 
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void queueEnter(ServerPlayer player, WitherStormEntity storm) {
/* 597 */     if (!player.m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation()) && player.m_6072_() && !player.m_20159_() && !player.m_20160_()) {
/*     */       
/* 599 */       ServerLevel bowels = WitherStormMod.bowels(player.m_284548_());
/* 600 */       bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.queuedEnter.putIfAbsent(storm.m_20148_(), player));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void queueLeave(Entity entity) {
/* 608 */     queueLeave(entity, () -> {
/*     */         
/*     */         });
/*     */   }
/*     */   public static void queueLeave(Entity entity, Runnable action) {
/* 613 */     if (entity.m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation()) && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_()) {
/*     */       
/* 615 */       ServerLevel bowels = WitherStormMod.bowels((ServerLevel)entity.m_9236_());
/* 616 */       bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.queuedLeave.putIfAbsent(entity, action));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Holder<Structure> getBowels(ServerLevel level) {
/* 624 */     return (Holder<Structure>)level.m_9598_().m_175515_(Registries.f_256944_).m_246971_(ResourceKey.m_135785_(Registries.f_256944_, WitherStormModStructures.BOWELS.getId()));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static StructureStart getAvailableStartAt(ServerLevel world, BlockPos pos) {
/* 629 */     ChunkAccess chunk = world.m_46819_(pos.m_123341_() >> 4, pos.m_123343_() >> 4, ChunkStatus.f_62315_);
/* 630 */     StructureStart start = world.m_215010_().m_220512_(SectionPos.m_175562_(chunk), (Structure)getBowels(world).m_203334_(), (StructureAccess)chunk);
/* 631 */     if (start != null && start.m_73603_()) {
/* 632 */       return start;
/*     */     }
/* 634 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDimensionLoad(LevelEvent.Load event) {
/* 640 */     Level level = (Level)event.getLevel();
/* 641 */     if (!level.m_5776_())
/*     */     {
/* 643 */       if (level.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation()))
/*     */       {
/* 645 */         level.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.onLoad());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onWorldTick(TickEvent.LevelTickEvent event) {
/* 655 */     Level level = event.level; if (level instanceof ServerLevel) { ServerLevel world = (ServerLevel)level;
/*     */       
/* 657 */       if (event.phase == TickEvent.Phase.START && world.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
/*     */         
/* 659 */         world.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
/*     */               manager.queuedEnter.forEach(());
/*     */ 
/*     */ 
/*     */               
/*     */               manager.queuedEnter.clear();
/*     */ 
/*     */ 
/*     */               
/*     */               manager.queuedLeave.forEach(());
/*     */ 
/*     */               
/*     */               manager.queuedLeave.clear();
/*     */ 
/*     */               
/*     */               manager.resetEmptyTimeIfNeeded();
/*     */             });
/*     */ 
/*     */         
/* 678 */         for (Entity entity : world.m_8583_()) {
/*     */           
/* 680 */           if (entity != null && entity.m_20186_() < 50.0D)
/* 681 */             queueLeave(entity); 
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public static class BowelsInstance
/*     */   {
/* 689 */     private static final TicketType<ChunkPos> BOWELS = TicketType.m_9462_("witherstormmod:bowels", Comparator.comparingLong(ChunkPos::m_45588_)); @Nullable
/*     */     private Pair<BlockPos, StructureStart> start;
/*     */     private final UUID witherStorm;
/*     */     @Nullable
/*     */     private UUID commandBlock;
/*     */     private boolean hasPreparedArena;
/*     */     private boolean isCompleted;
/*     */     
/*     */     public BowelsInstance(Pair<BlockPos, StructureStart> start, UUID storm) {
/* 698 */       this.start = start;
/* 699 */       this.witherStorm = storm;
/*     */     }
/*     */ 
/*     */     
/*     */     public BowelsInstance(BlockPos pos, UUID storm) {
/* 704 */       this.start = Pair.of(pos, null);
/* 705 */       this.witherStorm = storm;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setup(ServerLevel world) {
/* 712 */       if (!world.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
/*     */         
/* 714 */         WitherStormBowelsManager.LOGGER.error("Cannot setup {} in {}", this, world.m_46472_());
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 719 */       if (getStart() == null) {
/*     */         
/* 721 */         StructureStart start = WitherStormBowelsManager.getAvailableStartAt(world, getPos());
/* 722 */         if (start != null) {
/*     */           
/* 724 */           this.start = Pair.of(getPos(), start);
/*     */         }
/*     */         else {
/*     */           
/* 728 */           WitherStormBowelsManager.LOGGER.error("Could not find saved structure start or start is not valid from {}. It is important that these values aren't modified!", getPos());
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 733 */       WitherStormBowelsManager.LOGGER.debug("Successfully setup this bowels instance {}", this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void doChunkLoading(ServerLevel level) {
/* 739 */       if (!isCompleted()) {
/* 740 */         forceChunks(level, getCenter(), true);
/*     */       } else {
/* 742 */         forceChunks(level, getCenter(), false);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void forceChunks(ServerLevel world, BlockPos pos, boolean force) {
/* 747 */       ChunkPos chunkPos = new ChunkPos(pos);
/* 748 */       if (force) {
/*     */         
/* 750 */         WitherStormBowelsManager.LOGGER.debug("Loaded chunks in arena");
/* 751 */         world.m_7726_().addRegionTicket(BOWELS, chunkPos, 3, chunkPos, true);
/*     */       }
/*     */       else {
/*     */         
/* 755 */         WitherStormBowelsManager.LOGGER.debug("Unloaded chunks in arena");
/* 756 */         world.m_7726_().m_8438_(BOWELS, chunkPos, 3, chunkPos);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public CompoundTag write() {
/* 762 */       CompoundTag compound = new CompoundTag();
/* 763 */       compound.m_128365_("Pos", (Tag)NbtUtils.m_129224_(getPos()));
/* 764 */       compound.m_128362_("Storm", this.witherStorm);
/* 765 */       if (this.commandBlock != null)
/* 766 */         compound.m_128362_("CommandBlock", this.commandBlock); 
/* 767 */       compound.m_128379_("HasPreparedArena", this.hasPreparedArena);
/* 768 */       compound.m_128379_("Completed", this.isCompleted);
/* 769 */       return compound;
/*     */     }
/*     */ 
/*     */     
/*     */     public static BowelsInstance read(CompoundTag compound) {
/* 774 */       BlockPos pos = NbtUtils.m_129239_(compound.m_128469_("Pos"));
/* 775 */       UUID storm = compound.m_128342_("Storm");
/* 776 */       UUID commandBlock = null;
/* 777 */       if (compound.m_128441_("CommandBlock"))
/* 778 */         commandBlock = compound.m_128342_("CommandBlock"); 
/* 779 */       BowelsInstance instance = new BowelsInstance(pos, storm);
/* 780 */       instance.commandBlock = commandBlock;
/* 781 */       instance.hasPreparedArena = compound.m_128471_("HasPreparedArena");
/* 782 */       instance.isCompleted = compound.m_128471_("Completed");
/* 783 */       return instance;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setCompleted(boolean completed) {
/* 788 */       this.isCompleted = completed;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isCompleted() {
/* 793 */       return this.isCompleted;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setPreparedArena() {
/* 798 */       this.hasPreparedArena = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasPreparedArena() {
/* 803 */       return this.hasPreparedArena;
/*     */     }
/*     */ 
/*     */     
/*     */     public UUID getCommandBlockUUID() {
/* 808 */       return this.commandBlock;
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPos getPos() {
/* 813 */       return (BlockPos)this.start.getFirst();
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public BlockPos getCenter() {
/* 818 */       StructureStart start = getStart();
/* 819 */       if (start != null) {
/*     */         
/* 821 */         BlockPos center = ((StructurePiece)start.m_73602_().get(0)).m_73547_().m_162394_();
/* 822 */         return new BlockPos(center.m_123341_(), 0, center.m_123343_());
/*     */       } 
/*     */ 
/*     */       
/* 826 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public StructureStart getStart() {
/* 832 */       return (StructureStart)this.start.getSecond();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 838 */       return "BowelsInstance[start = " + this.start + ", pos = " + 
/* 839 */         getPos() + ", storm = " + this.witherStorm + ", commandBlock = " + this.commandBlock + ", hasPreparedArena = " + this.hasPreparedArena + ", completed = " + this.isCompleted + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum BowelsEnterStatus
/*     */   {
/* 849 */     SUCCESS,
/* 850 */     CANT_SETUP_BOWELS,
/* 851 */     ENTITY_CANNOT_CHANGE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\WitherStormBowelsManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */