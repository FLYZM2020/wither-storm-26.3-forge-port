/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.HolderGetter;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityDimensions;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.MoverType;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.GameRules;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
/*     */ 
/*     */ public class BlockClusterEntity
/*     */   extends Entity {
/*  55 */   protected static final EntityDataAccessor<Map<BlockPos, BlockState>> BLOCKS = SynchedEntityData.defineId(BlockClusterEntity.class, WitherStormModDataSerializers.BLOCK_STATE_POS_MAP);
/*  56 */   private static final EntityDataAccessor<List<CompoundTag>> TILE_DATA = SynchedEntityData.defineId(BlockClusterEntity.class, WitherStormModDataSerializers.COMPOUND_LIST);
/*  57 */   private static final EntityDataAccessor<BlockPos> START_POS = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.BLOCK_POS);
/*  58 */   private static final EntityDataAccessor<Vec2> ROTATION_DELTA = SynchedEntityData.defineId(BlockClusterEntity.class, WitherStormModDataSerializers.VECTOR_2F);
/*  59 */   private static final EntityDataAccessor<Boolean> PHYSICS = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.BOOLEAN);
/*  60 */   private static final EntityDataAccessor<Boolean> FORCE_RENDER = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.BOOLEAN);
/*  61 */   private static final EntityDataAccessor<Float> X_SIZE = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.FLOAT);
/*  62 */   private static final EntityDataAccessor<Float> Y_SIZE = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.FLOAT);
/*  63 */   private static final EntityDataAccessor<Float> Z_SIZE = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.FLOAT);
/*  64 */   private static final EntityDataAccessor<Integer> SHAKE_TIME = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.INT);
/*  65 */   protected static final EntityDataAccessor<Optional<BlockPos>> FADE_POINT = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
/*  66 */   private static final EntityDataAccessor<Float> FADE_STRENGTH = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.FLOAT);
/*  67 */   private static final EntityDataAccessor<Integer> FADE_DISTANCE_OFFSET = SynchedEntityData.defineId(BlockClusterEntity.class, EntityDataSerializers.INT); public int time;
/*     */   public boolean resetGravityOnLoad = true;
/*  69 */   public boolean dropItems = ((Boolean)WitherStormModConfig.COMMON.blockClustersDropItems.get()).booleanValue();
/*     */   private int shakeTime;
/*     */   @Nonnull
/*  72 */   public Vec2 shakeO = Vec2.ZERO; @Nonnull
/*  73 */   public Vec2 shake = Vec2.ZERO;
/*     */   
/*     */   private int sink;
/*     */   
/*     */   private boolean antiStacking;
/*     */   private boolean shouldCrumble;
/*     */   private boolean shouldntCountToConsumedEntities;
/*     */   private float xClusterRot;
/*     */   private float xClusterRotO;
/*     */   private float yClusterRot;
/*     */   private float yClusterRotO;
/*     */   private boolean createdFromBeam;
/*     */   private boolean createdFromFallingBlock;
/*  86 */   private int headCreatedFrom = -1;
/*     */   
/*     */   private double tractorBeamDistanceThreshold;
/*     */ 
/*     */   
/*     */   public BlockClusterEntity(EntityType<? extends BlockClusterEntity> entityType, Level world) {
/*  92 */     super(entityType, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void populate(Map<BlockPos, BlockState> states) {
/*  97 */     if (states.size() > 0) {
/*     */       
/*  99 */       int minX = 0;
/* 100 */       int minY = 0;
/* 101 */       int minZ = 0;
/* 102 */       int maxX = 0;
/* 103 */       int maxY = 0;
/* 104 */       int maxZ = 0;
/* 105 */       for (Map.Entry<BlockPos, BlockState> entry : states.entrySet()) {
/*     */         
/* 107 */         BlockPos pos = entry.getKey();
/* 108 */         if (pos.getX() < minX)
/* 109 */           minX = pos.getX(); 
/* 110 */         if (pos.getY() < minY)
/* 111 */           minY = pos.getY(); 
/* 112 */         if (pos.getZ() < minZ)
/* 113 */           minZ = pos.getZ(); 
/* 114 */         if (pos.getX() > maxX)
/* 115 */           maxX = pos.getX(); 
/* 116 */         if (pos.getY() > maxY)
/* 117 */           maxY = pos.getY(); 
/* 118 */         if (pos.getZ() > maxZ)
/* 119 */           maxZ = pos.getZ(); 
/*     */       } 
/* 121 */       float x = (maxX - minX);
/* 122 */       float y = (maxY - minY);
/* 123 */       float z = (maxZ - minZ);
/* 124 */       setSize(Math.abs(x) + 1.0F, Math.abs(y) + 1.0F, Math.abs(z) + 1.0F);
/* 125 */       setStartPos(BlockPos.containing(minX + x / 2.0D, minY + y / 2.0D, minZ + z / 2.0D));
/* 126 */       setBlocks(states);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void populate(BlockPos start, BlockPos end, Predicate<BlockState> filter) {
/* 132 */     float x = Mth.floor((end.getX() - start.getX()));
/* 133 */     float y = Mth.floor((end.getY() - start.getY()));
/* 134 */     float z = Mth.floor((end.getZ() - start.getZ()));
/* 135 */     Vec3 clusterPos = Vec3.atLowerCornerOf((Vec3i)start).add(x / 2.0D + 0.5D, Math.min(y, 0.0D), z / 2.0D + 0.5D);
/* 136 */     setPos(clusterPos.x, clusterPos.y, clusterPos.z);
/* 137 */     setSize(Math.abs(x) + 1.0F, Math.abs(y) + 1.0F, Math.abs(z) + 1.0F);
/* 138 */     setStartPos(start.offset((int)(x / 2.0D), (int)(y / 2.0D), (int)(z / 2.0D)));
/* 139 */     Iterable<BlockPos> blocks = BlockPos.betweenClosed(start, end);
/* 140 */     for (BlockPos pos : blocks) {
/*     */       
/* 142 */       BlockState state = level().getBlockState(pos);
/* 143 */       if (!state.isAir() && filter.test(state)) {
/*     */         
/* 145 */         if (state.hasBlockEntity()) {
/*     */           
/* 147 */           BlockEntity tile = level().getBlockEntity(pos);
/* 148 */           if (tile != null) {
/*     */             
/* 150 */             addTileData(tile.serializeNBT());
/* 151 */             level().removeBlockEntity(pos);
/*     */           } 
/*     */         } 
/* 154 */         BlockPos relative = pos.subtract((Vec3i)getStartPos());
/* 155 */         addBlock(state, relative);
/*     */       } 
/*     */     } 
/* 158 */     for (Map.Entry<BlockPos, BlockState> entry : getBlocks().entrySet()) {
/*     */       
/* 160 */       BlockPos pos = getStartPos().offset((Vec3i)entry.getKey());
/* 161 */       level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void populateWithRadius(BlockPos start, float radius, Predicate<BlockState> filter) {
/* 167 */     setSize((Mth.ceil(radius) * 2 - 1), (Mth.ceil(radius) * 2 - 1), (Mth.ceil(radius) * 2 - 1));
/* 168 */     setStartPos(start);
/* 169 */     setPos((start.getX() + 0.5F), start.getY() - (getBoundingBox().getCenter()).y + 0.5D, (start.getZ() + 0.5F));
/* 170 */     for (int x = -Mth.floor(radius); x < Mth.ceil(radius); x++) {
/*     */       
/* 172 */       for (int y = -Mth.floor(radius); y < Mth.ceil(radius); y++) {
/*     */         
/* 174 */         for (int z = -Mth.floor(radius); z < Mth.ceil(radius); z++) {
/*     */           
/* 176 */           if (Mth.sqrt((x * x + y * y + z * z)) < radius) {
/*     */             
/* 178 */             BlockPos currentPos = new BlockPos(x + start.getX(), y + start.getY(), z + start.getZ());
/* 179 */             BlockPos relativePos = new BlockPos(x, y, z);
/* 180 */             BlockState state = level().getBlockState(currentPos);
/* 181 */             if (!state.isAir() && filter.test(state)) {
/*     */               
/* 183 */               if (state.hasBlockEntity()) {
/*     */                 
/* 185 */                 BlockEntity tile = level().getBlockEntity(currentPos);
/* 186 */                 if (tile != null) {
/*     */                   
/* 188 */                   addTileData(tile.serializeNBT());
/* 189 */                   level().removeBlockEntity(currentPos);
/*     */                 } 
/*     */               } 
/* 192 */               addBlock(state, relativePos);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 198 */     for (Map.Entry<BlockPos, BlockState> entry : getBlocks().entrySet()) {
/*     */       
/* 200 */       BlockPos pos = start.offset((Vec3i)entry.getKey());
/* 201 */       level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTime(int time) {
/* 207 */     this.time = time;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 213 */     this.entityData.define(START_POS, BlockPos.ZERO);
/* 214 */     this.entityData.define(BLOCKS, new HashMap<>());
/* 215 */     this.entityData.define(TILE_DATA, new ArrayList());
/* 216 */     this.entityData.define(ROTATION_DELTA, new Vec2(0.0F, 0.0F));
/* 217 */     this.entityData.define(PHYSICS, Boolean.valueOf(true));
/* 218 */     this.entityData.define(FORCE_RENDER, Boolean.valueOf(false));
/* 219 */     this.entityData.define(X_SIZE, Float.valueOf(1.0F));
/* 220 */     this.entityData.define(Y_SIZE, Float.valueOf(1.0F));
/* 221 */     this.entityData.define(Z_SIZE, Float.valueOf(1.0F));
/* 222 */     this.entityData.define(SHAKE_TIME, Integer.valueOf(0));
/* 223 */     this.entityData.define(FADE_POINT, Optional.empty());
/* 224 */     this.entityData.define(FADE_STRENGTH, Float.valueOf(10.0F));
/* 225 */     this.entityData.define(FADE_DISTANCE_OFFSET, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditionalSaveData(CompoundTag compound) {
/* 231 */     if (compound.contains("StartPos"))
/* 232 */       setStartPos(NbtUtils.readBlockPos(compound.getCompound("StartPos"))); 
/* 233 */     if (compound.contains("Blocks"))
/* 234 */       setBlocks(WitherStormModNBTUtil.readBlockStatePosMap((HolderGetter)level().holderLookup(Registries.BLOCK), compound.getList("Blocks", 10))); 
/* 235 */     if (compound.contains("TileData"))
/* 236 */       setTileData(WitherStormModNBTUtil.readCompoundList(compound.getList("TileData", 10))); 
/* 237 */     if (compound.contains("RotationDelta")) {
/*     */       
/* 239 */       CompoundTag deltaCompound = compound.getCompound("RotationDelta");
/* 240 */       setRotationDelta(WitherStormModNBTUtil.readVector2f(deltaCompound));
/*     */     } 
/* 242 */     if (compound.contains("Width")) {
/*     */       
/* 244 */       this.entityData.set(X_SIZE, Float.valueOf(compound.getFloat("Width")));
/* 245 */       this.entityData.set(Z_SIZE, Float.valueOf(compound.getFloat("Width")));
/*     */     }
/*     */     else {
/*     */       
/* 249 */       this.entityData.set(X_SIZE, Float.valueOf(compound.getFloat("XSize")));
/* 250 */       this.entityData.set(Z_SIZE, Float.valueOf(compound.getFloat("ZSize")));
/*     */     } 
/* 252 */     if (compound.contains("Height")) {
/* 253 */       this.entityData.set(Y_SIZE, Float.valueOf(compound.getFloat("Height")));
/*     */     } else {
/* 255 */       this.entityData.set(Y_SIZE, Float.valueOf(compound.getFloat("YSize")));
/* 256 */     }  refreshDimensions();
/* 257 */     this.time = compound.getInt("Time");
/* 258 */     this.dropItems = compound.getBoolean("DropItems");
/* 259 */     this.resetGravityOnLoad = compound.getBoolean("ResetGravity");
/* 260 */     if (this.resetGravityOnLoad)
/* 261 */       setNoGravity(false); 
/* 262 */     setForceRender(compound.getBoolean("ForceRender"));
/* 263 */     setShakeTime(compound.getInt("ShakeTime"));
/* 264 */     setSink(compound.getInt("GroundSink"));
/* 265 */     setAntiStacking(compound.getBoolean("AntiStacking"));
/* 266 */     if (compound.contains("StaticFadePos"))
/* 267 */       this.entityData.set(FADE_POINT, Optional.of(NbtUtils.readBlockPos(compound.getCompound("StaticFadePos")))); 
/* 268 */     this.shouldCrumble = compound.getBoolean("ShouldCrumble");
/* 269 */     this.shouldntCountToConsumedEntities = compound.getBoolean("ShouldntCountToConsumedEntities");
/* 270 */     this.createdFromBeam = compound.getBoolean("CreatedFromBeam");
/* 271 */     this.createdFromFallingBlock = compound.getBoolean("CreatedFromFallingBlock");
/* 272 */     this.headCreatedFrom = compound.getInt("HeadCreatedFrom");
/*     */ 
/*     */     
/* 275 */     this.tractorBeamDistanceThreshold = compound.getDouble("TractorBeamDistanceThreshold");
/* 276 */     if (compound.contains("HasPhysics")) {
/* 277 */       setPhysics(compound.getBoolean("HasPhysics"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAdditionalSaveData(CompoundTag compound) {
/* 283 */     compound.put("StartPos", (Tag)NbtUtils.writeBlockPos(getStartPos()));
/* 284 */     compound.put("Blocks", (Tag)WitherStormModNBTUtil.writeBlockStatePosMap(getBlocks()));
/* 285 */     compound.put("TileData", (Tag)WitherStormModNBTUtil.writeCompoundList(getTileData()));
/* 286 */     compound.putFloat("XSize", ((Float)this.entityData.get(X_SIZE)).floatValue());
/* 287 */     compound.putFloat("YSize", ((Float)this.entityData.get(Y_SIZE)).floatValue());
/* 288 */     compound.putFloat("ZSize", ((Float)this.entityData.get(Z_SIZE)).floatValue());
/* 289 */     compound.putInt("Time", this.time);
/* 290 */     compound.putBoolean("DropItems", this.dropItems);
/* 291 */     compound.put("RotationDelta", (Tag)WitherStormModNBTUtil.writeVector2f(getRotationDelta()));
/* 292 */     compound.putBoolean("ResetGravity", this.resetGravityOnLoad);
/* 293 */     compound.putBoolean("ForceRender", forceRender());
/* 294 */     compound.putInt("ShakeTime", this.shakeTime);
/* 295 */     compound.putInt("GroundSink", getSink());
/* 296 */     compound.putBoolean("AntiStacking", antiStacking());
/* 297 */     ((Optional)this.entityData.get(FADE_POINT)).ifPresent(pos -> compound.put("StaticFadePos", (Tag)NbtUtils.writeBlockPos(pos)));
/* 298 */     compound.putBoolean("ShouldCrumble", this.shouldCrumble);
/* 299 */     compound.putBoolean("ShouldntCountToConsumedEntities", this.shouldntCountToConsumedEntities);
/* 300 */     compound.putBoolean("CreatedFromBeam", this.createdFromBeam);
/* 301 */     compound.putBoolean("CreatedFromFallingBlock", this.createdFromFallingBlock);
/* 302 */     compound.putInt("HeadCreatedFrom", this.headCreatedFrom);
/*     */ 
/*     */     
/* 305 */     compound.putDouble("TractorBeamDistanceThreshold", this.tractorBeamDistanceThreshold);
/* 306 */     compound.putBoolean("HasPhysics", physicsEnabled());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockClusterEntity splitAt(Direction.Axis axis) {
/* 311 */     Map<BlockPos, BlockState> split = Maps.newHashMap();
/* 312 */     Map<BlockPos, BlockState> current = Maps.newHashMap(getBlocks());
/* 313 */     Iterator<Map.Entry<BlockPos, BlockState>> iterator = current.entrySet().iterator();
/* 314 */     while (iterator.hasNext()) {
/*     */       
/* 316 */       Map.Entry<BlockPos, BlockState> entry = iterator.next();
/* 317 */       BlockPos pos = entry.getKey();
/* 318 */       switch (axis) {
/*     */ 
/*     */         
/*     */         case Y:
/* 322 */           if (pos.getY() < 0) {
/*     */             
/* 324 */             split.put(pos, entry.getValue());
/* 325 */             iterator.remove();
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         case X:
/* 331 */           if (pos.getX() < 0) {
/*     */             
/* 333 */             split.put(pos, entry.getValue());
/* 334 */             iterator.remove();
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         case Z:
/* 340 */           if (pos.getZ() < 0) {
/*     */             
/* 342 */             split.put(pos, entry.getValue());
/* 343 */             iterator.remove();
/*     */           } 
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 349 */     if (!split.isEmpty() && !current.isEmpty()) {
/*     */       
/* 351 */       if (getSize() < 10)
/* 352 */         setShouldCrumble(false); 
/* 353 */       setBlocks(current);
/* 354 */       BlockClusterEntity splitCluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).create(level());
/* 355 */       splitCluster.setSize(((Float)this.entityData.get(X_SIZE)).floatValue(), ((Float)this.entityData.get(Y_SIZE)).floatValue(), ((Float)this.entityData.get(Z_SIZE)).floatValue());
/* 356 */       splitCluster.setStartPos(getStartPos());
/* 357 */       splitCluster.setBlocks(split);
/* 358 */       splitCluster.moveTo(position());
/* 359 */       splitCluster.setNoGravity(isNoGravity());
/* 360 */       splitCluster.setPhysics(physicsEnabled());
/* 361 */       splitCluster.setRotationDelta(getRotationDelta());
/* 362 */       splitCluster.setDeltaMovement(getDeltaMovement().scale(0.8D));
/* 363 */       splitCluster.setShouldCrumble(shouldCrumble());
/* 364 */       splitCluster.setFadePos(getFadePos());
/* 365 */       splitCluster.setFadeDistanceOffset(getFadeDistanceOffset());
/* 366 */       splitCluster.setFadeStrength(getFadeStrength());
/* 367 */       return splitCluster;
/*     */     } 
/*     */ 
/*     */     
/* 371 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 378 */     this.shakeO = new Vec2(this.shake.x, this.shake.y);
/* 379 */     if (this.shakeTime > 0) {
/*     */       
/* 381 */       float shakeTime = getShakeTime();
/* 382 */       float x = Mth.cos(shakeTime * 4.5F) * 0.05F + (this.random.nextFloat() - 0.5F) * 0.05F;
/* 383 */       float z = Mth.sin(shakeTime * 3.5F) * 0.15F + (this.random.nextFloat() - 0.5F) * 0.2F;
/* 384 */       this.shake = new Vec2(x, z);
/*     */       
/* 386 */       this.shakeTime--;
/* 387 */       if (this.shakeTime == 0) {
/* 388 */         setShakeTime(0);
/*     */       }
/*     */     } else {
/*     */       
/* 392 */       this.shake = new Vec2(0.0F, 0.0F);
/*     */     } 
/*     */ 
/*     */     
/* 396 */     if (!isNoGravity()) {
/* 397 */       setDeltaMovement(getDeltaMovement().add(0.0D, -0.04D, 0.0D));
/*     */     }
/* 399 */     move(MoverType.SELF, getDeltaMovement());
/*     */     
/* 401 */     this.time++;
/*     */     
/* 403 */     this.noPhysics = !physicsEnabled();
/*     */     
/* 405 */     super.tick();
/*     */     
/* 407 */     this.xClusterRotO = this.xClusterRot;
/* 408 */     this.yClusterRotO = this.yClusterRot;
/* 409 */     if (getShakeTime() <= 0) {
/*     */       
/* 411 */       this.xClusterRot += (getRotationDelta()).x;
/* 412 */       this.yClusterRot += (getRotationDelta()).y;
/*     */     } 
/*     */     
/* 415 */     if (!(level()).isClientSide) {
/*     */       
/* 417 */       BlockPos pos = blockPosition();
/* 418 */       if (getBlocks().isEmpty()) {
/* 419 */         discard();
/*     */       }
/* 421 */       Map<BlockPos, BlockState> blocks = getBlocks();
/* 422 */       boolean isAir = true;
/* 423 */       for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
/*     */         
/* 425 */         BlockState state = entry.getValue();
/* 426 */         if (isAir)
/* 427 */           isAir = state.isAir(); 
/*     */       } 
/* 429 */       if (isAir)
/* 430 */         discard(); 
/* 431 */       if (!onGround()) {
/*     */         
/* 433 */         if (pos.getY() + getBbHeight() <= level().getMinBuildHeight() || this.time > 600)
/*     */         {
/* 435 */           if (this.dropItems && level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
/*     */           {
/* 437 */             for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
/*     */               
/* 439 */               BlockState state = entry.getValue();
/* 440 */               BlockPos position = pos.offset((Vec3i)entry.getKey());
/* 441 */               spawnAtSpecificLocation((ItemLike)state.getBlock().asItem(), position);
/*     */             } 
/*     */           }
/* 444 */           discard();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 449 */         place();
/*     */       } 
/*     */       
/* 452 */       if (((Boolean)WitherStormModConfig.SERVER.clustersRemoveItems.get()).booleanValue() && getSize() != 1 && !createdFromTractorBeam()) {
/*     */         
/* 454 */         List<ItemEntity> items = level().getEntitiesOfClass(ItemEntity.class, getBoundingBox());
/* 455 */         for (ItemEntity entity : items) {
/*     */           
/* 457 */           if (entity.isAlive() && entity.getOwner() == null) {
/* 458 */             entity.discard();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 464 */       refreshDimensions();
/* 465 */       reapplyPosition();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void place() {
/* 471 */     discard();
/* 472 */     BlockPos pos = blockPosition();
/* 473 */     if (antiStacking()) {
/*     */       
/* 475 */       BlockPos currentPos = blockPosition();
/* 476 */       BlockState current = level().getBlockState(currentPos);
/* 477 */       for (int i = 0; i < 50 && current.isAir(); i++) {
/*     */         
/* 479 */         currentPos = currentPos.below();
/* 480 */         current = level().getBlockState(currentPos);
/*     */       } 
/* 482 */       pos = pos.atY(currentPos.getY());
/*     */     } 
/* 484 */     for (Map.Entry<BlockPos, BlockState> entry : getBlocks().entrySet()) {
/*     */       
/* 486 */       BlockState state = entry.getValue();
/* 487 */       BlockPos relativePos = entry.getKey();
/* 488 */       BlockPos basePos = pos.offset(relativePos.getX(), relativePos.getY() - getSink(), relativePos.getZ());
/* 489 */       BlockPos placementPos = basePos.above(Mth.floor(getBoundingBox().getYsize() / 2.0D - 0.5D));
/* 490 */       if (level().getBlockEntity(placementPos) == null && !level().getBlockState(placementPos).is(BlockTags.DRAGON_IMMUNE) && !state.is(WitherStormModBlockTags.BLOCK_CLUSTERS_CANNOT_PLACE) && level().setBlock(placementPos, state, 3)) {
/*     */         
/* 492 */         if (state.hasBlockEntity()) {
/*     */           
/* 494 */           CompoundTag tileData = getTileDataFromOffsetPos(relativePos);
/* 495 */           if (tileData != null) {
/*     */             
/* 497 */             BlockEntity tile = level().getBlockEntity(placementPos);
/* 498 */             if (tile != null) {
/*     */               
/* 500 */               tileData.putInt("x", placementPos.getX());
/* 501 */               tileData.putInt("y", placementPos.getY());
/* 502 */               tileData.putInt("z", placementPos.getZ());
/* 503 */               tile.load(tileData);
/* 504 */               tile.setChanged();
/*     */             } 
/*     */           } 
/*     */         } 
/* 508 */         level().updateNeighborsAt(placementPos, state.getBlock()); continue;
/*     */       } 
/* 510 */       if (this.dropItems && level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
/*     */       {
/* 512 */         spawnAtSpecificLocation((ItemLike)state.getBlock().asItem(), placementPos);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawnAtSpecificLocation(ItemLike item, BlockPos position) {
/* 519 */     ItemStack stack = new ItemStack(item);
/* 520 */     ItemEntity itemEntity = new ItemEntity(level(), position.getX(), position.getY(), position.getZ(), stack);
/* 521 */     itemEntity.setDefaultPickUpDelay();
/* 522 */     if (captureDrops() != null) {
/* 523 */       captureDrops().add(itemEntity);
/*     */     } else {
/* 525 */       level().addFreshEntity((Entity)itemEntity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRotationDelta(Vec2 rotation) {
/* 530 */     this.entityData.set(ROTATION_DELTA, rotation);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec2 getRotationDelta() {
/* 535 */     return (Vec2)this.entityData.get(ROTATION_DELTA);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<BlockPos, BlockState> getBlocks() {
/* 540 */     return (Map<BlockPos, BlockState>)this.entityData.get(BLOCKS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBlock(BlockState state, BlockPos relativePosition) {
/* 545 */     put(BLOCKS, state, relativePosition);
/*     */   }
/*     */ 
/*     */   
/*     */   private void put(EntityDataAccessor<Map<BlockPos, BlockState>> param, BlockState state, BlockPos pos) {
/* 550 */     Map<BlockPos, BlockState> map = Maps.newHashMap(getBlocks());
/* 551 */     map.put(pos, state);
/* 552 */     this.entityData.set(param, map);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlocks(Map<BlockPos, BlockState> blocks) {
/* 557 */     this.entityData.set(BLOCKS, blocks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStartPos(BlockPos pos) {
/* 562 */     this.entityData.set(START_POS, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getStartPos() {
/* 567 */     return (BlockPos)this.entityData.get(START_POS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<CompoundTag> getTileData() {
/* 572 */     return (List<CompoundTag>)this.entityData.get(TILE_DATA);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CompoundTag getTileDataFromOffsetPos(BlockPos pos) {
/* 577 */     BlockPos actualPos = getStartPos().offset((Vec3i)pos);
/* 578 */     for (CompoundTag data : getTileData()) {
/*     */       
/* 580 */       if (data.getInt("x") == actualPos.getX() && data.getInt("y") == actualPos.getY() && data.getInt("z") == actualPos.getZ())
/* 581 */         return data; 
/*     */     } 
/* 583 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTileData(CompoundTag compound) {
/* 588 */     List<CompoundTag> list = Lists.newArrayList(getTileData());
/* 589 */     list.add(compound);
/* 590 */     this.entityData.set(TILE_DATA, list, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTileData(List<CompoundTag> list) {
/* 595 */     this.entityData.set(TILE_DATA, list);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDimensions getDimensions(Pose pose) {
/* 601 */     return EntityDimensions.scalable(Math.max(((Float)this.entityData.get(X_SIZE)).floatValue(), ((Float)this.entityData.get(Z_SIZE)).floatValue()), ((Float)this.entityData.get(Y_SIZE)).floatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet<ClientGamePacketListener> getAddEntityPacket() {
/* 607 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 612 */     return getBlocks().size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean physicsEnabled() {
/* 617 */     return ((Boolean)this.entityData.get(PHYSICS)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhysics(boolean physics) {
/* 622 */     this.entityData.set(PHYSICS, Boolean.valueOf(physics));
/* 623 */     this.noPhysics = !physics;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsBlock(Block block) {
/* 628 */     for (Map.Entry<BlockPos, BlockState> entry : getBlocks().entrySet()) {
/*     */       
/* 630 */       if (((BlockState)entry.getValue()).is(block))
/* 631 */         return true; 
/*     */     } 
/* 633 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResetGravityOnLoad(boolean shouldReset) {
/* 638 */     this.resetGravityOnLoad = shouldReset;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSyncedDataUpdated(EntityDataAccessor<?> parameter) {
/* 644 */     super.onSyncedDataUpdated(parameter);
/* 645 */     if (parameter.equals(X_SIZE) || parameter.equals(Y_SIZE) || parameter.equals(Z_SIZE)) {
/*     */       
/* 647 */       refreshDimensions();
/*     */     }
/* 649 */     else if (parameter.equals(SHAKE_TIME)) {
/*     */       
/* 651 */       this.shakeTime = ((Integer)this.entityData.get(SHAKE_TIME)).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean forceRender() {
/* 657 */     return ((Boolean)this.entityData.get(FORCE_RENDER)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceRender(boolean flag) {
/* 662 */     this.entityData.set(FORCE_RENDER, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(float x, float y, float z) {
/* 667 */     this.entityData.set(X_SIZE, Float.valueOf(x));
/* 668 */     this.entityData.set(Y_SIZE, Float.valueOf(y));
/* 669 */     this.entityData.set(Z_SIZE, Float.valueOf(z));
/* 670 */     refreshDimensions();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected AABB makeBoundingBox() {
/* 676 */     float x = ((Float)this.entityData.get(X_SIZE)).floatValue();
/* 677 */     float y = ((Float)this.entityData.get(Y_SIZE)).floatValue();
/* 678 */     float z = ((Float)this.entityData.get(Z_SIZE)).floatValue();
/* 679 */     return new AABB(getX() - (x / 2.0F), getY(), getZ() - z / 2.0D, getX() + x / 2.0D, getY() + y, getZ() + z / 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShakeTime(int time) {
/* 684 */     this.shakeTime = time;
/* 685 */     this.entityData.set(SHAKE_TIME, Integer.valueOf(time));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getShakeTime() {
/* 690 */     return this.shakeTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSink(int sink) {
/* 695 */     this.sink = sink;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSink() {
/* 700 */     return this.sink;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAntiStacking(boolean flag) {
/* 705 */     this.antiStacking = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean antiStacking() {
/* 710 */     return this.antiStacking;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPos getFadePos() {
/* 715 */     return ((Optional<BlockPos>)this.entityData.get(FADE_POINT)).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFadePos(@Nullable BlockPos pos) {
/* 720 */     this.entityData.set(FADE_POINT, Optional.ofNullable(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFadeStrength(float strength) {
/* 725 */     this.entityData.set(FADE_STRENGTH, Float.valueOf(strength));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFadeStrength() {
/* 730 */     return ((Float)this.entityData.get(FADE_STRENGTH)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFadeDistanceOffset() {
/* 735 */     return ((Integer)this.entityData.get(FADE_DISTANCE_OFFSET)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFadeDistanceOffset(int offset) {
/* 740 */     this.entityData.set(FADE_DISTANCE_OFFSET, Integer.valueOf(offset));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShouldCrumble(boolean flag) {
/* 745 */     this.shouldCrumble = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldCrumble() {
/* 750 */     return this.shouldCrumble;
/*     */   }
/*     */   
/*     */   public void setShouldntCountToConsumedEntities(boolean flag) {
/* 754 */     this.shouldntCountToConsumedEntities = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldntCountToConsumedEntities() {
/* 759 */     return this.shouldntCountToConsumedEntities;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean createdFromTractorBeam() {
/* 764 */     return this.createdFromBeam;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreatedFromTractorBeam(boolean flag) {
/* 769 */     this.createdFromBeam = flag;
/*     */   }
/*     */   
/*     */   public boolean createdFromFallingBlock() {
/* 773 */     return this.createdFromFallingBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreatedFromFallingBlock(boolean flag) {
/* 778 */     this.createdFromFallingBlock = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeadCreatedFrom() {
/* 783 */     return this.headCreatedFrom;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadCreatedFrom(int head) {
/* 788 */     this.headCreatedFrom = head;
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
/*     */   public float getClusterXRot(float partialTicks) {
/* 803 */     return Mth.lerp(partialTicks, this.xClusterRotO, this.xClusterRot);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getClusterYRot(float partialTicks) {
/* 808 */     return Mth.lerp(partialTicks, this.yClusterRotO, this.yClusterRot);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTractorBeamDistanceThreshold(double distance) {
/* 813 */     this.tractorBeamDistanceThreshold = distance;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTractorBeamDistanceThreshold() {
/* 818 */     return this.tractorBeamDistanceThreshold;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canChangeDimensions() {
/* 824 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean updateInWaterStateAndDoFluidPushing() {
/* 831 */     this.fluidHeight.clear();
/* 832 */     this.forgeFluidTypeHeight.clear();
/* 833 */     return isInFluidType();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Entity.MovementEmission getMovementEmission() {
/* 839 */     return Entity.MovementEmission.NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSwimming() {}
/*     */ 
/*     */   
/*     */   public boolean canSpawnSprintParticle() {
/* 848 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void baseTick() {
/* 854 */     level().getProfiler().push("entityBaseTick");
/* 855 */     this.walkDistO = this.walkDist;
/* 856 */     this.xRotO = getXRot();
/* 857 */     this.yRotO = getYRot();
/*     */     
/* 859 */     checkBelowWorld();
/*     */     
/* 861 */     this.firstTick = false;
/* 862 */     level().getProfiler().pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\BlockClusterEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */