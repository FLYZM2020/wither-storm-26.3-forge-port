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
/*  55 */   protected static final EntityDataAccessor<Map<BlockPos, BlockState>> BLOCKS = SynchedEntityData.m_135353_(BlockClusterEntity.class, WitherStormModDataSerializers.BLOCK_STATE_POS_MAP);
/*  56 */   private static final EntityDataAccessor<List<CompoundTag>> TILE_DATA = SynchedEntityData.m_135353_(BlockClusterEntity.class, WitherStormModDataSerializers.COMPOUND_LIST);
/*  57 */   private static final EntityDataAccessor<BlockPos> START_POS = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135038_);
/*  58 */   private static final EntityDataAccessor<Vec2> ROTATION_DELTA = SynchedEntityData.m_135353_(BlockClusterEntity.class, WitherStormModDataSerializers.VECTOR_2F);
/*  59 */   private static final EntityDataAccessor<Boolean> PHYSICS = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135035_);
/*  60 */   private static final EntityDataAccessor<Boolean> FORCE_RENDER = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135035_);
/*  61 */   private static final EntityDataAccessor<Float> X_SIZE = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135029_);
/*  62 */   private static final EntityDataAccessor<Float> Y_SIZE = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135029_);
/*  63 */   private static final EntityDataAccessor<Float> Z_SIZE = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135029_);
/*  64 */   private static final EntityDataAccessor<Integer> SHAKE_TIME = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135028_);
/*  65 */   protected static final EntityDataAccessor<Optional<BlockPos>> FADE_POINT = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135039_);
/*  66 */   private static final EntityDataAccessor<Float> FADE_STRENGTH = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135029_);
/*  67 */   private static final EntityDataAccessor<Integer> FADE_DISTANCE_OFFSET = SynchedEntityData.m_135353_(BlockClusterEntity.class, EntityDataSerializers.f_135028_); public int time;
/*     */   public boolean resetGravityOnLoad = true;
/*  69 */   public boolean dropItems = ((Boolean)WitherStormModConfig.COMMON.blockClustersDropItems.get()).booleanValue();
/*     */   private int shakeTime;
/*     */   @Nonnull
/*  72 */   public Vec2 shakeO = Vec2.f_82462_; @Nonnull
/*  73 */   public Vec2 shake = Vec2.f_82462_;
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
/* 108 */         if (pos.m_123341_() < minX)
/* 109 */           minX = pos.m_123341_(); 
/* 110 */         if (pos.m_123342_() < minY)
/* 111 */           minY = pos.m_123342_(); 
/* 112 */         if (pos.m_123343_() < minZ)
/* 113 */           minZ = pos.m_123343_(); 
/* 114 */         if (pos.m_123341_() > maxX)
/* 115 */           maxX = pos.m_123341_(); 
/* 116 */         if (pos.m_123342_() > maxY)
/* 117 */           maxY = pos.m_123342_(); 
/* 118 */         if (pos.m_123343_() > maxZ)
/* 119 */           maxZ = pos.m_123343_(); 
/*     */       } 
/* 121 */       float x = (maxX - minX);
/* 122 */       float y = (maxY - minY);
/* 123 */       float z = (maxZ - minZ);
/* 124 */       setSize(Math.abs(x) + 1.0F, Math.abs(y) + 1.0F, Math.abs(z) + 1.0F);
/* 125 */       setStartPos(BlockPos.m_274561_(minX + x / 2.0D, minY + y / 2.0D, minZ + z / 2.0D));
/* 126 */       setBlocks(states);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void populate(BlockPos start, BlockPos end, Predicate<BlockState> filter) {
/* 132 */     float x = Mth.m_14143_((end.m_123341_() - start.m_123341_()));
/* 133 */     float y = Mth.m_14143_((end.m_123342_() - start.m_123342_()));
/* 134 */     float z = Mth.m_14143_((end.m_123343_() - start.m_123343_()));
/* 135 */     Vec3 clusterPos = Vec3.m_82528_((Vec3i)start).m_82520_(x / 2.0D + 0.5D, Math.min(y, 0.0D), z / 2.0D + 0.5D);
/* 136 */     m_6034_(clusterPos.f_82479_, clusterPos.f_82480_, clusterPos.f_82481_);
/* 137 */     setSize(Math.abs(x) + 1.0F, Math.abs(y) + 1.0F, Math.abs(z) + 1.0F);
/* 138 */     setStartPos(start.m_7918_((int)(x / 2.0D), (int)(y / 2.0D), (int)(z / 2.0D)));
/* 139 */     Iterable<BlockPos> blocks = BlockPos.m_121940_(start, end);
/* 140 */     for (BlockPos pos : blocks) {
/*     */       
/* 142 */       BlockState state = m_9236_().m_8055_(pos);
/* 143 */       if (!state.m_60795_() && filter.test(state)) {
/*     */         
/* 145 */         if (state.m_155947_()) {
/*     */           
/* 147 */           BlockEntity tile = m_9236_().m_7702_(pos);
/* 148 */           if (tile != null) {
/*     */             
/* 150 */             addTileData(tile.serializeNBT());
/* 151 */             m_9236_().m_46747_(pos);
/*     */           } 
/*     */         } 
/* 154 */         BlockPos relative = pos.m_121996_((Vec3i)getStartPos());
/* 155 */         addBlock(state, relative);
/*     */       } 
/*     */     } 
/* 158 */     for (Map.Entry<BlockPos, BlockState> entry : getBlocks().entrySet()) {
/*     */       
/* 160 */       BlockPos pos = getStartPos().m_121955_((Vec3i)entry.getKey());
/* 161 */       m_9236_().m_7731_(pos, Blocks.f_50016_.m_49966_(), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void populateWithRadius(BlockPos start, float radius, Predicate<BlockState> filter) {
/* 167 */     setSize((Mth.m_14167_(radius) * 2 - 1), (Mth.m_14167_(radius) * 2 - 1), (Mth.m_14167_(radius) * 2 - 1));
/* 168 */     setStartPos(start);
/* 169 */     m_6034_((start.m_123341_() + 0.5F), start.m_123342_() - (m_20191_().m_82399_()).f_82480_ + 0.5D, (start.m_123343_() + 0.5F));
/* 170 */     for (int x = -Mth.m_14143_(radius); x < Mth.m_14167_(radius); x++) {
/*     */       
/* 172 */       for (int y = -Mth.m_14143_(radius); y < Mth.m_14167_(radius); y++) {
/*     */         
/* 174 */         for (int z = -Mth.m_14143_(radius); z < Mth.m_14167_(radius); z++) {
/*     */           
/* 176 */           if (Mth.m_14116_((x * x + y * y + z * z)) < radius) {
/*     */             
/* 178 */             BlockPos currentPos = new BlockPos(x + start.m_123341_(), y + start.m_123342_(), z + start.m_123343_());
/* 179 */             BlockPos relativePos = new BlockPos(x, y, z);
/* 180 */             BlockState state = m_9236_().m_8055_(currentPos);
/* 181 */             if (!state.m_60795_() && filter.test(state)) {
/*     */               
/* 183 */               if (state.m_155947_()) {
/*     */                 
/* 185 */                 BlockEntity tile = m_9236_().m_7702_(currentPos);
/* 186 */                 if (tile != null) {
/*     */                   
/* 188 */                   addTileData(tile.serializeNBT());
/* 189 */                   m_9236_().m_46747_(currentPos);
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
/* 200 */       BlockPos pos = start.m_121955_((Vec3i)entry.getKey());
/* 201 */       m_9236_().m_7731_(pos, Blocks.f_50016_.m_49966_(), 3);
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
/*     */   protected void m_8097_() {
/* 213 */     this.f_19804_.m_135372_(START_POS, BlockPos.f_121853_);
/* 214 */     this.f_19804_.m_135372_(BLOCKS, new HashMap<>());
/* 215 */     this.f_19804_.m_135372_(TILE_DATA, new ArrayList());
/* 216 */     this.f_19804_.m_135372_(ROTATION_DELTA, new Vec2(0.0F, 0.0F));
/* 217 */     this.f_19804_.m_135372_(PHYSICS, Boolean.valueOf(true));
/* 218 */     this.f_19804_.m_135372_(FORCE_RENDER, Boolean.valueOf(false));
/* 219 */     this.f_19804_.m_135372_(X_SIZE, Float.valueOf(1.0F));
/* 220 */     this.f_19804_.m_135372_(Y_SIZE, Float.valueOf(1.0F));
/* 221 */     this.f_19804_.m_135372_(Z_SIZE, Float.valueOf(1.0F));
/* 222 */     this.f_19804_.m_135372_(SHAKE_TIME, Integer.valueOf(0));
/* 223 */     this.f_19804_.m_135372_(FADE_POINT, Optional.empty());
/* 224 */     this.f_19804_.m_135372_(FADE_STRENGTH, Float.valueOf(10.0F));
/* 225 */     this.f_19804_.m_135372_(FADE_DISTANCE_OFFSET, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7378_(CompoundTag compound) {
/* 231 */     if (compound.m_128441_("StartPos"))
/* 232 */       setStartPos(NbtUtils.m_129239_(compound.m_128469_("StartPos"))); 
/* 233 */     if (compound.m_128441_("Blocks"))
/* 234 */       setBlocks(WitherStormModNBTUtil.readBlockStatePosMap((HolderGetter)m_9236_().m_246945_(Registries.f_256747_), compound.m_128437_("Blocks", 10))); 
/* 235 */     if (compound.m_128441_("TileData"))
/* 236 */       setTileData(WitherStormModNBTUtil.readCompoundList(compound.m_128437_("TileData", 10))); 
/* 237 */     if (compound.m_128441_("RotationDelta")) {
/*     */       
/* 239 */       CompoundTag deltaCompound = compound.m_128469_("RotationDelta");
/* 240 */       setRotationDelta(WitherStormModNBTUtil.readVector2f(deltaCompound));
/*     */     } 
/* 242 */     if (compound.m_128441_("Width")) {
/*     */       
/* 244 */       this.f_19804_.m_135381_(X_SIZE, Float.valueOf(compound.m_128457_("Width")));
/* 245 */       this.f_19804_.m_135381_(Z_SIZE, Float.valueOf(compound.m_128457_("Width")));
/*     */     }
/*     */     else {
/*     */       
/* 249 */       this.f_19804_.m_135381_(X_SIZE, Float.valueOf(compound.m_128457_("XSize")));
/* 250 */       this.f_19804_.m_135381_(Z_SIZE, Float.valueOf(compound.m_128457_("ZSize")));
/*     */     } 
/* 252 */     if (compound.m_128441_("Height")) {
/* 253 */       this.f_19804_.m_135381_(Y_SIZE, Float.valueOf(compound.m_128457_("Height")));
/*     */     } else {
/* 255 */       this.f_19804_.m_135381_(Y_SIZE, Float.valueOf(compound.m_128457_("YSize")));
/* 256 */     }  m_6210_();
/* 257 */     this.time = compound.m_128451_("Time");
/* 258 */     this.dropItems = compound.m_128471_("DropItems");
/* 259 */     this.resetGravityOnLoad = compound.m_128471_("ResetGravity");
/* 260 */     if (this.resetGravityOnLoad)
/* 261 */       m_20242_(false); 
/* 262 */     setForceRender(compound.m_128471_("ForceRender"));
/* 263 */     setShakeTime(compound.m_128451_("ShakeTime"));
/* 264 */     setSink(compound.m_128451_("GroundSink"));
/* 265 */     setAntiStacking(compound.m_128471_("AntiStacking"));
/* 266 */     if (compound.m_128441_("StaticFadePos"))
/* 267 */       this.f_19804_.m_135381_(FADE_POINT, Optional.of(NbtUtils.m_129239_(compound.m_128469_("StaticFadePos")))); 
/* 268 */     this.shouldCrumble = compound.m_128471_("ShouldCrumble");
/* 269 */     this.shouldntCountToConsumedEntities = compound.m_128471_("ShouldntCountToConsumedEntities");
/* 270 */     this.createdFromBeam = compound.m_128471_("CreatedFromBeam");
/* 271 */     this.createdFromFallingBlock = compound.m_128471_("CreatedFromFallingBlock");
/* 272 */     this.headCreatedFrom = compound.m_128451_("HeadCreatedFrom");
/*     */ 
/*     */     
/* 275 */     this.tractorBeamDistanceThreshold = compound.m_128459_("TractorBeamDistanceThreshold");
/* 276 */     if (compound.m_128441_("HasPhysics")) {
/* 277 */       setPhysics(compound.m_128471_("HasPhysics"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7380_(CompoundTag compound) {
/* 283 */     compound.m_128365_("StartPos", (Tag)NbtUtils.m_129224_(getStartPos()));
/* 284 */     compound.m_128365_("Blocks", (Tag)WitherStormModNBTUtil.writeBlockStatePosMap(getBlocks()));
/* 285 */     compound.m_128365_("TileData", (Tag)WitherStormModNBTUtil.writeCompoundList(getTileData()));
/* 286 */     compound.m_128350_("XSize", ((Float)this.f_19804_.m_135370_(X_SIZE)).floatValue());
/* 287 */     compound.m_128350_("YSize", ((Float)this.f_19804_.m_135370_(Y_SIZE)).floatValue());
/* 288 */     compound.m_128350_("ZSize", ((Float)this.f_19804_.m_135370_(Z_SIZE)).floatValue());
/* 289 */     compound.m_128405_("Time", this.time);
/* 290 */     compound.m_128379_("DropItems", this.dropItems);
/* 291 */     compound.m_128365_("RotationDelta", (Tag)WitherStormModNBTUtil.writeVector2f(getRotationDelta()));
/* 292 */     compound.m_128379_("ResetGravity", this.resetGravityOnLoad);
/* 293 */     compound.m_128379_("ForceRender", forceRender());
/* 294 */     compound.m_128405_("ShakeTime", this.shakeTime);
/* 295 */     compound.m_128405_("GroundSink", getSink());
/* 296 */     compound.m_128379_("AntiStacking", antiStacking());
/* 297 */     ((Optional)this.f_19804_.m_135370_(FADE_POINT)).ifPresent(pos -> compound.m_128365_("StaticFadePos", (Tag)NbtUtils.m_129224_(pos)));
/* 298 */     compound.m_128379_("ShouldCrumble", this.shouldCrumble);
/* 299 */     compound.m_128379_("ShouldntCountToConsumedEntities", this.shouldntCountToConsumedEntities);
/* 300 */     compound.m_128379_("CreatedFromBeam", this.createdFromBeam);
/* 301 */     compound.m_128379_("CreatedFromFallingBlock", this.createdFromFallingBlock);
/* 302 */     compound.m_128405_("HeadCreatedFrom", this.headCreatedFrom);
/*     */ 
/*     */     
/* 305 */     compound.m_128347_("TractorBeamDistanceThreshold", this.tractorBeamDistanceThreshold);
/* 306 */     compound.m_128379_("HasPhysics", physicsEnabled());
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
/* 322 */           if (pos.m_123342_() < 0) {
/*     */             
/* 324 */             split.put(pos, entry.getValue());
/* 325 */             iterator.remove();
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         case X:
/* 331 */           if (pos.m_123341_() < 0) {
/*     */             
/* 333 */             split.put(pos, entry.getValue());
/* 334 */             iterator.remove();
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         case Z:
/* 340 */           if (pos.m_123343_() < 0) {
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
/* 354 */       BlockClusterEntity splitCluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(m_9236_());
/* 355 */       splitCluster.setSize(((Float)this.f_19804_.m_135370_(X_SIZE)).floatValue(), ((Float)this.f_19804_.m_135370_(Y_SIZE)).floatValue(), ((Float)this.f_19804_.m_135370_(Z_SIZE)).floatValue());
/* 356 */       splitCluster.setStartPos(getStartPos());
/* 357 */       splitCluster.setBlocks(split);
/* 358 */       splitCluster.m_20219_(m_20182_());
/* 359 */       splitCluster.m_20242_(m_20068_());
/* 360 */       splitCluster.setPhysics(physicsEnabled());
/* 361 */       splitCluster.setRotationDelta(getRotationDelta());
/* 362 */       splitCluster.m_20256_(m_20184_().m_82490_(0.8D));
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
/*     */   public void m_8119_() {
/* 378 */     this.shakeO = new Vec2(this.shake.f_82470_, this.shake.f_82471_);
/* 379 */     if (this.shakeTime > 0) {
/*     */       
/* 381 */       float shakeTime = getShakeTime();
/* 382 */       float x = Mth.m_14089_(shakeTime * 4.5F) * 0.05F + (this.f_19796_.m_188501_() - 0.5F) * 0.05F;
/* 383 */       float z = Mth.m_14031_(shakeTime * 3.5F) * 0.15F + (this.f_19796_.m_188501_() - 0.5F) * 0.2F;
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
/* 396 */     if (!m_20068_()) {
/* 397 */       m_20256_(m_20184_().m_82520_(0.0D, -0.04D, 0.0D));
/*     */     }
/* 399 */     m_6478_(MoverType.SELF, m_20184_());
/*     */     
/* 401 */     this.time++;
/*     */     
/* 403 */     this.f_19794_ = !physicsEnabled();
/*     */     
/* 405 */     super.m_8119_();
/*     */     
/* 407 */     this.xClusterRotO = this.xClusterRot;
/* 408 */     this.yClusterRotO = this.yClusterRot;
/* 409 */     if (getShakeTime() <= 0) {
/*     */       
/* 411 */       this.xClusterRot += (getRotationDelta()).f_82470_;
/* 412 */       this.yClusterRot += (getRotationDelta()).f_82471_;
/*     */     } 
/*     */     
/* 415 */     if (!(m_9236_()).f_46443_) {
/*     */       
/* 417 */       BlockPos pos = m_20183_();
/* 418 */       if (getBlocks().isEmpty()) {
/* 419 */         m_146870_();
/*     */       }
/* 421 */       Map<BlockPos, BlockState> blocks = getBlocks();
/* 422 */       boolean isAir = true;
/* 423 */       for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
/*     */         
/* 425 */         BlockState state = entry.getValue();
/* 426 */         if (isAir)
/* 427 */           isAir = state.m_60795_(); 
/*     */       } 
/* 429 */       if (isAir)
/* 430 */         m_146870_(); 
/* 431 */       if (!m_20096_()) {
/*     */         
/* 433 */         if (pos.m_123342_() + m_20206_() <= m_9236_().m_141937_() || this.time > 600)
/*     */         {
/* 435 */           if (this.dropItems && m_9236_().m_46469_().m_46207_(GameRules.f_46137_))
/*     */           {
/* 437 */             for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
/*     */               
/* 439 */               BlockState state = entry.getValue();
/* 440 */               BlockPos position = pos.m_121955_((Vec3i)entry.getKey());
/* 441 */               spawnAtSpecificLocation((ItemLike)state.m_60734_().m_5456_(), position);
/*     */             } 
/*     */           }
/* 444 */           m_146870_();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 449 */         place();
/*     */       } 
/*     */       
/* 452 */       if (((Boolean)WitherStormModConfig.SERVER.clustersRemoveItems.get()).booleanValue() && getSize() != 1 && !createdFromTractorBeam()) {
/*     */         
/* 454 */         List<ItemEntity> items = m_9236_().m_45976_(ItemEntity.class, m_20191_());
/* 455 */         for (ItemEntity entity : items) {
/*     */           
/* 457 */           if (entity.m_6084_() && entity.m_19749_() == null) {
/* 458 */             entity.m_146870_();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 464 */       m_6210_();
/* 465 */       m_20090_();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void place() {
/* 471 */     m_146870_();
/* 472 */     BlockPos pos = m_20183_();
/* 473 */     if (antiStacking()) {
/*     */       
/* 475 */       BlockPos currentPos = m_20183_();
/* 476 */       BlockState current = m_9236_().m_8055_(currentPos);
/* 477 */       for (int i = 0; i < 50 && current.m_60795_(); i++) {
/*     */         
/* 479 */         currentPos = currentPos.m_7495_();
/* 480 */         current = m_9236_().m_8055_(currentPos);
/*     */       } 
/* 482 */       pos = pos.m_175288_(currentPos.m_123342_());
/*     */     } 
/* 484 */     for (Map.Entry<BlockPos, BlockState> entry : getBlocks().entrySet()) {
/*     */       
/* 486 */       BlockState state = entry.getValue();
/* 487 */       BlockPos relativePos = entry.getKey();
/* 488 */       BlockPos basePos = pos.m_7918_(relativePos.m_123341_(), relativePos.m_123342_() - getSink(), relativePos.m_123343_());
/* 489 */       BlockPos placementPos = basePos.m_6630_(Mth.m_14107_(m_20191_().m_82376_() / 2.0D - 0.5D));
/* 490 */       if (m_9236_().m_7702_(placementPos) == null && !m_9236_().m_8055_(placementPos).m_204336_(BlockTags.f_13069_) && !state.m_204336_(WitherStormModBlockTags.BLOCK_CLUSTERS_CANNOT_PLACE) && m_9236_().m_7731_(placementPos, state, 3)) {
/*     */         
/* 492 */         if (state.m_155947_()) {
/*     */           
/* 494 */           CompoundTag tileData = getTileDataFromOffsetPos(relativePos);
/* 495 */           if (tileData != null) {
/*     */             
/* 497 */             BlockEntity tile = m_9236_().m_7702_(placementPos);
/* 498 */             if (tile != null) {
/*     */               
/* 500 */               tileData.m_128405_("x", placementPos.m_123341_());
/* 501 */               tileData.m_128405_("y", placementPos.m_123342_());
/* 502 */               tileData.m_128405_("z", placementPos.m_123343_());
/* 503 */               tile.m_142466_(tileData);
/* 504 */               tile.m_6596_();
/*     */             } 
/*     */           } 
/*     */         } 
/* 508 */         m_9236_().m_46672_(placementPos, state.m_60734_()); continue;
/*     */       } 
/* 510 */       if (this.dropItems && m_9236_().m_46469_().m_46207_(GameRules.f_46137_))
/*     */       {
/* 512 */         spawnAtSpecificLocation((ItemLike)state.m_60734_().m_5456_(), placementPos);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawnAtSpecificLocation(ItemLike item, BlockPos position) {
/* 519 */     ItemStack stack = new ItemStack(item);
/* 520 */     ItemEntity itemEntity = new ItemEntity(m_9236_(), position.m_123341_(), position.m_123342_(), position.m_123343_(), stack);
/* 521 */     itemEntity.m_32060_();
/* 522 */     if (captureDrops() != null) {
/* 523 */       captureDrops().add(itemEntity);
/*     */     } else {
/* 525 */       m_9236_().m_7967_((Entity)itemEntity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRotationDelta(Vec2 rotation) {
/* 530 */     this.f_19804_.m_135381_(ROTATION_DELTA, rotation);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec2 getRotationDelta() {
/* 535 */     return (Vec2)this.f_19804_.m_135370_(ROTATION_DELTA);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<BlockPos, BlockState> getBlocks() {
/* 540 */     return (Map<BlockPos, BlockState>)this.f_19804_.m_135370_(BLOCKS);
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
/* 552 */     this.f_19804_.m_135381_(param, map);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlocks(Map<BlockPos, BlockState> blocks) {
/* 557 */     this.f_19804_.m_135381_(BLOCKS, blocks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStartPos(BlockPos pos) {
/* 562 */     this.f_19804_.m_135381_(START_POS, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getStartPos() {
/* 567 */     return (BlockPos)this.f_19804_.m_135370_(START_POS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<CompoundTag> getTileData() {
/* 572 */     return (List<CompoundTag>)this.f_19804_.m_135370_(TILE_DATA);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CompoundTag getTileDataFromOffsetPos(BlockPos pos) {
/* 577 */     BlockPos actualPos = getStartPos().m_121955_((Vec3i)pos);
/* 578 */     for (CompoundTag data : getTileData()) {
/*     */       
/* 580 */       if (data.m_128451_("x") == actualPos.m_123341_() && data.m_128451_("y") == actualPos.m_123342_() && data.m_128451_("z") == actualPos.m_123343_())
/* 581 */         return data; 
/*     */     } 
/* 583 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTileData(CompoundTag compound) {
/* 588 */     List<CompoundTag> list = Lists.newArrayList(getTileData());
/* 589 */     list.add(compound);
/* 590 */     this.f_19804_.m_276349_(TILE_DATA, list, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTileData(List<CompoundTag> list) {
/* 595 */     this.f_19804_.m_135381_(TILE_DATA, list);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDimensions m_6972_(Pose pose) {
/* 601 */     return EntityDimensions.m_20395_(Math.max(((Float)this.f_19804_.m_135370_(X_SIZE)).floatValue(), ((Float)this.f_19804_.m_135370_(Z_SIZE)).floatValue()), ((Float)this.f_19804_.m_135370_(Y_SIZE)).floatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
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
/* 617 */     return ((Boolean)this.f_19804_.m_135370_(PHYSICS)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhysics(boolean physics) {
/* 622 */     this.f_19804_.m_135381_(PHYSICS, Boolean.valueOf(physics));
/* 623 */     this.f_19794_ = !physics;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsBlock(Block block) {
/* 628 */     for (Map.Entry<BlockPos, BlockState> entry : getBlocks().entrySet()) {
/*     */       
/* 630 */       if (((BlockState)entry.getValue()).m_60713_(block))
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
/*     */   public void m_7350_(EntityDataAccessor<?> parameter) {
/* 644 */     super.m_7350_(parameter);
/* 645 */     if (parameter.equals(X_SIZE) || parameter.equals(Y_SIZE) || parameter.equals(Z_SIZE)) {
/*     */       
/* 647 */       m_6210_();
/*     */     }
/* 649 */     else if (parameter.equals(SHAKE_TIME)) {
/*     */       
/* 651 */       this.shakeTime = ((Integer)this.f_19804_.m_135370_(SHAKE_TIME)).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean forceRender() {
/* 657 */     return ((Boolean)this.f_19804_.m_135370_(FORCE_RENDER)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceRender(boolean flag) {
/* 662 */     this.f_19804_.m_135381_(FORCE_RENDER, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(float x, float y, float z) {
/* 667 */     this.f_19804_.m_135381_(X_SIZE, Float.valueOf(x));
/* 668 */     this.f_19804_.m_135381_(Y_SIZE, Float.valueOf(y));
/* 669 */     this.f_19804_.m_135381_(Z_SIZE, Float.valueOf(z));
/* 670 */     m_6210_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected AABB m_142242_() {
/* 676 */     float x = ((Float)this.f_19804_.m_135370_(X_SIZE)).floatValue();
/* 677 */     float y = ((Float)this.f_19804_.m_135370_(Y_SIZE)).floatValue();
/* 678 */     float z = ((Float)this.f_19804_.m_135370_(Z_SIZE)).floatValue();
/* 679 */     return new AABB(m_20185_() - (x / 2.0F), m_20186_(), m_20189_() - z / 2.0D, m_20185_() + x / 2.0D, m_20186_() + y, m_20189_() + z / 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShakeTime(int time) {
/* 684 */     this.shakeTime = time;
/* 685 */     this.f_19804_.m_135381_(SHAKE_TIME, Integer.valueOf(time));
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
/* 715 */     return ((Optional<BlockPos>)this.f_19804_.m_135370_(FADE_POINT)).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFadePos(@Nullable BlockPos pos) {
/* 720 */     this.f_19804_.m_135381_(FADE_POINT, Optional.ofNullable(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFadeStrength(float strength) {
/* 725 */     this.f_19804_.m_135381_(FADE_STRENGTH, Float.valueOf(strength));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFadeStrength() {
/* 730 */     return ((Float)this.f_19804_.m_135370_(FADE_STRENGTH)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFadeDistanceOffset() {
/* 735 */     return ((Integer)this.f_19804_.m_135370_(FADE_DISTANCE_OFFSET)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFadeDistanceOffset(int offset) {
/* 740 */     this.f_19804_.m_135381_(FADE_DISTANCE_OFFSET, Integer.valueOf(offset));
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
/* 803 */     return Mth.m_14179_(partialTicks, this.xClusterRotO, this.xClusterRot);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getClusterYRot(float partialTicks) {
/* 808 */     return Mth.m_14179_(partialTicks, this.yClusterRotO, this.yClusterRot);
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
/*     */   public boolean m_6072_() {
/* 824 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_20073_() {
/* 831 */     this.f_19799_.clear();
/* 832 */     this.forgeFluidTypeHeight.clear();
/* 833 */     return isInFluidType();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Entity.MovementEmission m_142319_() {
/* 839 */     return Entity.MovementEmission.NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_5844_() {}
/*     */ 
/*     */   
/*     */   public boolean m_5843_() {
/* 848 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6075_() {
/* 854 */     m_9236_().m_46473_().m_6180_("entityBaseTick");
/* 855 */     this.f_19867_ = this.f_19787_;
/* 856 */     this.f_19860_ = m_146909_();
/* 857 */     this.f_19859_ = m_146908_();
/*     */     
/* 859 */     m_146871_();
/*     */     
/* 861 */     this.f_19803_ = false;
/* 862 */     m_9236_().m_46473_().m_7238_();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\BlockClusterEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */