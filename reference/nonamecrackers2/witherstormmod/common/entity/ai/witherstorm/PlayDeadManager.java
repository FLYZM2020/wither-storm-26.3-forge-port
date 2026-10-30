/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.server.level.FullChunkStatus;
/*     */ import net.minecraft.server.level.ServerBossEvent;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.WorldGenLevel;
/*     */ import net.minecraft.world.level.chunk.ChunkAccess;
/*     */ import net.minecraft.world.level.chunk.ChunkStatus;
/*     */ import net.minecraft.world.level.chunk.LevelChunk;
/*     */ import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
/*     */ import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModFeatures;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.PlayAdditionalLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveAdditionalLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdatePlayDeadManagerMessage;
/*     */ import nonamecrackers2.witherstormmod.common.util.DebrisCluster;
/*     */ import nonamecrackers2.witherstormmod.common.util.DebrisRingSettings;
/*     */ import nonamecrackers2.witherstormmod.common.world.gen.feature.CommandBlockPodiumFeature;
/*     */ 
/*     */ public class PlayDeadManager {
/*  48 */   protected State state = State.NORMAL_BEHAVIOR; protected final WitherStormEntity entity;
/*     */   protected int ticksSinceInit;
/*     */   protected int ticksSinceInitO;
/*  51 */   protected final int updateInterval = 120; protected int totalTickCount; @Nullable
/*     */   protected FormidibombEntity formidibomb; @Nullable
/*     */   protected BlockPos podiumPos;
/*     */   protected boolean podiumPlaced;
/*     */   @Nullable
/*     */   protected CommandBlockEntity commandBlock;
/*     */   protected int revivalTicks;
/*     */   protected boolean hasRecentlyBeenRevived;
/*  59 */   protected int revivalPlayerProtection = ((Integer)WitherStormModConfig.SERVER.revivalPlayerProtection.get()).intValue();
/*     */   
/*     */   protected int timeSinceCommandBlockMissing;
/*     */   
/*     */   public PlayDeadManager(WitherStormEntity entity) {
/*  64 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  69 */     Level world = this.entity.m_9236_();
/*  70 */     getState().tick(world, this.entity, this);
/*  71 */     this.totalTickCount++;
/*  72 */     Objects.requireNonNull(this); if (this.totalTickCount % 120 == 0) {
/*  73 */       sendChanges(PacketDistributor.DIMENSION.with(() -> this.entity.m_9236_().m_46472_()), true);
/*     */     }
/*  75 */     if (this.revivalTicks > this.revivalPlayerProtection * 1200) {
/*  76 */       this.hasRecentlyBeenRevived = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setState(State state) {
/*  81 */     if (this.state != state) {
/*     */       
/*  83 */       this.state.finish(this.entity.m_9236_(), this.entity, this, state);
/*  84 */       this.state = state;
/*  85 */       this.state.init(this.entity.m_9236_(), this.entity, this);
/*  86 */       updateSegments();
/*  87 */       sendChanges(PacketDistributor.DIMENSION.with(() -> this.entity.m_9236_().m_46472_()), false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStateRaw(State state) {
/*  93 */     if (this.state != state) {
/*     */       
/*  95 */       this.state = state;
/*  96 */       sendChanges(PacketDistributor.DIMENSION.with(() -> this.entity.m_9236_().m_46472_()), false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void nextState() {
/* 102 */     State next = null;
/* 103 */     if (this.state.ordinal() + 1 < (State.values()).length)
/* 104 */       next = State.values()[this.state.ordinal() + 1]; 
/* 105 */     if (next != null) {
/*     */       
/* 107 */       this.state.finish(this.entity.m_9236_(), this.entity, this, next);
/* 108 */       this.state = next;
/* 109 */       this.state.init(this.entity.m_9236_(), this.entity, this);
/* 110 */       updateSegments();
/* 111 */       sendChanges(PacketDistributor.DIMENSION.with(() -> this.entity.m_9236_().m_46472_()), false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateSegments() {
/* 117 */     this.entity.getSegmentsManager().ifPresent(segments -> {
/*     */           for (WitherStormSegmentEntity segment : segments.getSegments()) {
/*     */             if (segment != null) {
/*     */               segment.getPlayDeadManager().setState(getState());
/*     */             }
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public State getState() {
/* 129 */     return this.state;
/*     */   }
/*     */ 
/*     */   
/*     */   public void explode() {
/* 134 */     setState(State.FALLING);
/* 135 */     if (!(this.entity.m_9236_()).f_46443_)
/*     */     {
/* 137 */       for (Player player : this.entity.m_9236_().m_45955_(TargetingConditions.m_148353_(), null, this.entity.getSearchBox().m_82400_(100.0D))) {
/* 138 */         WitherStormModCriteriaTriggers.PLAY_DEAD_TRIGGER.trigger((ServerPlayer)player, this.entity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void revive() {
/* 144 */     setState(State.REVIVING);
/* 145 */     if (!(this.entity.m_9236_()).f_46443_)
/*     */     {
/* 147 */       for (Player player : this.entity.m_9236_().m_45955_(TargetingConditions.m_148353_(), null, this.entity.getSearchBox().m_82400_(100.0D))) {
/* 148 */         WitherStormModCriteriaTriggers.REVIVAL_TRIGGER.trigger((ServerPlayer)player, this.entity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendChanges(PacketDistributor.PacketTarget target, boolean updateTick) {
/* 154 */     if (!(this.entity.m_9236_()).f_46443_) {
/*     */       
/* 156 */       UpdatePlayDeadManagerMessage message = new UpdatePlayDeadManagerMessage(this.entity.m_19879_(), this, updateTick);
/* 157 */       WitherStormModPacketHandlers.MAIN.send(target, message);
/* 158 */       getState().sendAdditionalPackets(this.entity.m_9236_(), this.entity, this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicks() {
/* 164 */     return this.ticksSinceInit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTickAmount(int amount) {
/* 169 */     this.ticksSinceInit = amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTickAmountAndO(int amount) {
/* 174 */     this.ticksSinceInit = amount;
/* 175 */     this.ticksSinceInitO = amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormidibomb(@Nullable FormidibombEntity entity) {
/* 180 */     this.formidibomb = entity;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public FormidibombEntity getFormidibomb() {
/* 185 */     return this.formidibomb;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPos getPodiumPos() {
/* 190 */     return this.podiumPos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPodiumPos(@Nullable BlockPos pos) {
/* 195 */     this.podiumPos = pos;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPodiumPlaced() {
/* 200 */     return this.podiumPlaced;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPodiumPlaced(boolean placed) {
/* 205 */     this.podiumPlaced = placed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPodiumAreaLoaded(BlockPos pos) {
/* 210 */     for (int x = -3; x <= 3; x++) {
/*     */       
/* 212 */       for (int z = -3; z <= 3; z++) {
/*     */         
/* 214 */         ChunkPos chunkPos = new ChunkPos(pos);
/* 215 */         ChunkAccess chunk = this.entity.m_9236_().m_6522_(chunkPos.f_45578_ + x, chunkPos.f_45579_ + z, ChunkStatus.f_62326_, false);
/*     */         
/* 217 */         if (!(chunk instanceof LevelChunk)) {
/* 218 */           return false;
/*     */         }
/* 220 */         FullChunkStatus type = ((LevelChunk)chunk).m_287138_();
/* 221 */         if (!type.m_287205_(FullChunkStatus.BLOCK_TICKING)) {
/* 222 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 226 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void placePodium() {
/* 231 */     if (!(this.entity.m_9236_()).f_46443_) {
/*     */       
/* 233 */       float yBodyRot = (this.entity.f_20883_ - 90.0F) * 0.017453292F;
/* 234 */       float x = Mth.m_14089_(yBodyRot) * 5.0F;
/* 235 */       float z = Mth.m_14031_(yBodyRot) * 5.0F;
/* 236 */       BlockPos pos = this.entity.m_20183_().m_7918_((int)x, -4, (int)z);
/* 237 */       if (!isPodiumPlaced() && isPodiumAreaLoaded(pos)) {
/*     */         
/* 239 */         ServerLevel world = (ServerLevel)this.entity.m_9236_();
/* 240 */         if (((ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).m_203334_()).m_224953_((WorldGenLevel)world, world.m_7726_().m_8481_(), this.entity.m_217043_(), pos)) {
/*     */           
/* 242 */           setPodiumPos(pos);
/* 243 */           setPodiumPlaced(true);
/* 244 */           if (getCommandBlock() == null || !getCommandBlock().m_6084_()) {
/*     */             
/* 246 */             CommandBlockEntity entity = new CommandBlockEntity((Level)world, this.entity, pos.m_123341_() + 0.5D, pos.m_123342_() + 11.0D, pos.m_123343_() + 0.5D);
/* 247 */             setCommandBlock(entity);
/* 248 */             world.m_7967_((Entity)entity);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePodium() {
/* 257 */     if (!(this.entity.m_9236_()).f_46443_) {
/*     */       
/* 259 */       BlockPos pos = getPodiumPos();
/* 260 */       if (pos != null && isPodiumPlaced() && isPodiumAreaLoaded(pos)) {
/*     */         
/* 262 */         ServerLevel world = (ServerLevel)this.entity.m_9236_();
/*     */         
/* 264 */         ConfiguredFeature<NoneFeatureConfiguration, CommandBlockPodiumFeature> feature = (ConfiguredFeature<NoneFeatureConfiguration, CommandBlockPodiumFeature>)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).m_203334_();
/* 265 */         if (((CommandBlockPodiumFeature)feature.f_65377_()).remove((WorldGenLevel)world, world.m_7726_().m_8481_(), this.entity.m_217043_(), pos, feature.f_65378_())) {
/*     */           
/* 267 */           setPodiumPos(null);
/* 268 */           setPodiumPlaced(false);
/*     */         } 
/*     */         
/* 271 */         if (getCommandBlock() != null) {
/*     */           
/* 273 */           CommandBlockEntity entity = getCommandBlock();
/* 274 */           entity.m_146870_();
/* 275 */           setCommandBlock(null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CommandBlockEntity getCommandBlock() {
/* 283 */     return this.commandBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCommandBlock(@Nullable CommandBlockEntity entity) {
/* 288 */     this.commandBlock = entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicksSinceRevival() {
/* 293 */     return this.revivalTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTicksSinceRevival(int ticks) {
/* 298 */     this.revivalTicks = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRecentlyRevived(boolean flag) {
/* 303 */     this.hasRecentlyBeenRevived = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRecentlyBeenRevived() {
/* 308 */     return this.hasRecentlyBeenRevived;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRevivalPlayerProtectionTime() {
/* 313 */     return this.revivalPlayerProtection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRevivalPlayerProtectionTime(int time) {
/* 318 */     this.revivalPlayerProtection = time;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicksSinceCommandBlockMissing() {
/* 323 */     return this.timeSinceCommandBlockMissing;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTicksSinceCommandBlockMissing(int time) {
/* 328 */     this.timeSinceCommandBlockMissing = time;
/*     */   }
/*     */   
/*     */   public enum State
/*     */   {
/* 333 */     NORMAL_BEHAVIOR
/*     */     {
/*     */       
/*     */       public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager)
/*     */       {
/* 338 */         super.tick(world, entity, manager);
/*     */         
/* 340 */         float percentage = Math.min(manager.ticksSinceInit, 80) / 80.0F;
/* 341 */         for (DebrisRingSettings settings : entity.getDebrisRings())
/* 342 */           settings.setAlpha(percentage); 
/* 343 */         entity.setShineAlpha(percentage);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, State next) {
/* 349 */         List<Mob> entities = world.m_45976_(Mob.class, entity.getSearchBox());
/* 350 */         for (Mob mob : entities) {
/*     */           
/* 352 */           if (mob.m_5448_() == entity)
/* 353 */             mob.m_6710_(null); 
/*     */         } 
/*     */       }
/*     */     },
/* 357 */     FALLING
/*     */     {
/*     */       
/*     */       public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager)
/*     */       {
/* 362 */         super.tick(world, entity, manager);
/*     */         
/* 364 */         if (!world.f_46443_) {
/*     */ 
/*     */           
/* 367 */           if (manager.getTicks() % 8 == 0 && !(entity instanceof WitherStormSegmentEntity))
/*     */           {
/* 369 */             for (int i = 0; i < 3; i++) {
/* 370 */               entity.dropSmallMassCluster(1);
/*     */             }
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 379 */           if (entity.getSegmentsManager().isPresent() && manager.getTicks() == 201) {
/*     */             
/* 381 */             SegmentsManager segmentsManager = entity.getSegmentsManager().get();
/* 382 */             if (entity.getPhase() == 5) {
/*     */               
/* 384 */               entity.setPhase(6);
/* 385 */               entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SPLITS.get(), 1.0F, 1.0F);
/* 386 */               entity.setOtherHeadsDisabled(true);
/* 387 */               segmentsManager.createSegments();
/* 388 */               segmentsManager.addSegments();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 393 */         if (!entity.m_20096_()) {
/*     */           
/* 395 */           float xOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 396 */           float yOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 397 */           float zOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 398 */           world.m_7106_((ParticleOptions)ParticleTypes.f_123813_, entity.m_20185_() + xOffset, entity.m_20188_() + yOffset, entity.m_20189_() + zOffset, -5.0D, 0.0D, 0.0D);
/*     */         } 
/*     */         
/* 401 */         float percentage = (120.0F - Math.min(manager.ticksSinceInit, 120)) / 120.0F;
/* 402 */         for (DebrisRingSettings settings : entity.getDebrisRings())
/* 403 */           settings.setAlpha(percentage); 
/* 404 */         entity.setShineAlpha(percentage);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 410 */         super.init(world, entity, manager);
/*     */         
/* 412 */         if (!world.f_46443_) {
/*     */           
/* 414 */           for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 415 */             head.doRoar(entity.m_217043_().m_188499_());
/*     */           }
/* 417 */           if (entity.shouldPlaySoundLoop) {
/*     */             
/* 419 */             PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get());
/* 420 */             WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*     */           } 
/*     */         } 
/*     */         
/* 424 */         for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 425 */           head.lerpHeadTo(-50.0F, entity.f_20883_, 64.0F);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, State next) {
/* 431 */         super.finish(world, entity, manager, next);
/* 432 */         if (!world.f_46443_ && entity.shouldPlaySoundLoop) {
/*     */           
/* 434 */           RemoveAdditionalLoopingSoundMessage message = new RemoveAdditionalLoopingSoundMessage(entity);
/* 435 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> world.m_46472_()), message);
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean disablesAi() {
/* 442 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 448 */         return entity.m_20096_();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void sendAdditionalPackets(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 454 */         super.sendAdditionalPackets(world, entity, manager);
/*     */         
/* 456 */         if (entity.shouldPlaySoundLoop) {
/*     */           
/* 458 */           PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get());
/* 459 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*     */         } 
/*     */       }
/*     */     },
/* 463 */     PLAYING_DEAD
/*     */     {
/*     */       
/*     */       public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager)
/*     */       {
/* 468 */         super.tick(world, entity, manager);
/*     */         
/* 470 */         if (entity.isOnBack() && entity.m_6084_() && !entity.m_21224_()) {
/*     */           
/* 472 */           manager.placePodium();
/* 473 */           BlockPos pos = manager.getPodiumPos();
/* 474 */           if (pos != null) {
/* 475 */             entity.spawnConsumedPets(new Vec3(pos.m_123341_() + 0.5D, pos.m_123342_() + 12.0D, pos.m_123343_() + 0.5D));
/*     */           }
/*     */         } 
/* 478 */         for (DebrisRingSettings settings : entity.getDebrisRings())
/* 479 */           settings.setAlpha(0.0F); 
/* 480 */         entity.setShineAlpha(0.0F);
/*     */         
/* 482 */         if (!world.f_46443_) {
/*     */           
/* 484 */           CommandBlockEntity commandBlock = manager.getCommandBlock();
/* 485 */           if (commandBlock != null && (!commandBlock.m_6084_() || commandBlock.m_20270_((Entity)entity) > 64.0D)) {
/* 486 */             manager.timeSinceCommandBlockMissing++;
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 493 */         return (manager.timeSinceCommandBlockMissing > 200);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean disablesAi() {
/* 499 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 505 */         super.init(world, entity, manager);
/*     */         
/* 507 */         manager.timeSinceCommandBlockMissing = 0;
/*     */         
/* 509 */         for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 510 */           head.lerpHeadTo(40.0F, entity.f_20883_, 16.0F);
/*     */         }
/* 512 */         if (!world.f_46443_)
/*     */         {
/* 514 */           if (entity.getSegmentsManager().isPresent()) {
/*     */             
/* 516 */             SegmentsManager segmentsManager = entity.getSegmentsManager().get();
/* 517 */             if (entity.getPhase() == 5) {
/*     */               
/* 519 */               entity.setPhase(6);
/* 520 */               entity.setOtherHeadsDisabled(true);
/* 521 */               segmentsManager.createSegments();
/* 522 */               segmentsManager.addSegments();
/*     */             } 
/*     */           } 
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, State next) {
/* 531 */         manager.timeSinceCommandBlockMissing = 0;
/* 532 */         if (next != State.REVIVING)
/* 533 */           manager.removePodium(); 
/*     */       }
/*     */     },
/* 536 */     REVIVING
/*     */     {
/*     */       
/*     */       public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager)
/*     */       {
/* 541 */         super.tick(world, entity, manager);
/*     */         
/* 543 */         if (!world.f_46443_ && manager.getTicks() > 20) {
/*     */           
/* 545 */           entity.shake(60.0F, 4.0F);
/* 546 */           BlockPos pos = manager.getPodiumPos();
/* 547 */           if (pos != null)
/* 548 */             world.m_254849_((Entity)entity, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), 16.0F, Level.ExplosionInteraction.NONE); 
/* 549 */           world.m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
/* 550 */           manager.setState(State.NORMAL_BEHAVIOR);
/* 551 */           manager.removePodium();
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 558 */         manager.setRecentlyRevived(true);
/*     */         
/* 560 */         super.init(world, entity, manager);
/*     */         
/* 562 */         if (!world.f_46443_ && entity.shouldPlayGlobalSounds) {
/* 563 */           entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_REACTIVATES.get(), 10.0F, 1.0F);
/*     */         }
/*     */       }
/*     */     };
/*     */     
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 569 */       manager.ticksSinceInitO = manager.ticksSinceInit;
/* 570 */       manager.ticksSinceInit++;
/*     */       
/* 572 */       if (!disablesAi()) {
/* 573 */         manager.revivalTicks++;
/*     */       }
/* 575 */       if (isPastInterval(world, entity, manager)) {
/* 576 */         manager.nextState();
/*     */       }
/* 578 */       if (disablesAi()) {
/*     */         
/* 580 */         if (manager.ticksSinceInit % 5 == 0) {
/*     */           
/* 582 */           int size = Math.max(10, entity.m_217043_().m_188503_(15));
/* 583 */           for (int i = 0; i < size; i++)
/*     */           {
/* 585 */             for (DebrisCluster cluster : entity.getDebrisClusters())
/*     */             {
/* 587 */               if (!cluster.isDisabled()) {
/* 588 */                 cluster.setDisabled((entity.m_217043_().m_188503_(entity.getDebrisClusters().size()) == 0));
/*     */               }
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         } 
/* 595 */       } else if (manager.ticksSinceInit % 10 == 0) {
/*     */         
/* 597 */         int size = Math.max(10, entity.m_217043_().m_188503_(15));
/* 598 */         for (int i = 0; i < size; i++) {
/*     */           
/* 600 */           for (DebrisCluster cluster : entity.getDebrisClusters()) {
/*     */             
/* 602 */             if (cluster.isDisabled() && entity.m_217043_().m_188503_(entity.getDebrisClusters().size()) == 0) {
/* 603 */               cluster.setDisabled(false);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 612 */       manager.ticksSinceInit = 0;
/* 613 */       manager.ticksSinceInitO = 0;
/* 614 */       if (disablesAi()) {
/*     */         
/* 616 */         manager.revivalTicks = 0;
/* 617 */         manager.setRecentlyRevived(false);
/*     */       } 
/*     */       
/* 620 */       if (!world.f_46443_) {
/*     */         
/* 622 */         if (disablesAi()) {
/*     */           
/* 624 */           entity.getTrackedEntities().clearAndMakeAllFall();
/*     */           
/* 626 */           if (entity.shouldPlaySoundLoop)
/*     */           {
/* 628 */             RemoveSoundLoopMessage message = new RemoveSoundLoopMessage(entity);
/* 629 */             WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> world.m_46472_()), message);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 634 */           for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 635 */             head.doRoar(entity.m_217043_().m_188499_());
/*     */           }
/*     */         } 
/* 638 */         entity.getBossInfo().ifPresent(info -> info.m_8321_(!disablesAi()));
/*     */       } 
/*     */       
/* 641 */       entity.m_6210_();
/*     */     }
/*     */ 
/*     */     
/*     */     public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, State next) {}
/*     */     
/*     */     public boolean disablesAi() {
/* 648 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 653 */       return false;
/*     */     }
/*     */     
/*     */     public void sendAdditionalPackets(Level world, WitherStormEntity entity, PlayDeadManager manager) {}
/*     */   }
/*     */   
/*     */   enum null {
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       super.tick(world, entity, manager);
/*     */       float percentage = Math.min(manager.ticksSinceInit, 80) / 80.0F;
/*     */       for (DebrisRingSettings settings : entity.getDebrisRings())
/*     */         settings.setAlpha(percentage); 
/*     */       entity.setShineAlpha(percentage);
/*     */     }
/*     */     
/*     */     public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, PlayDeadManager.State next) {
/*     */       List<Mob> entities = world.m_45976_(Mob.class, entity.getSearchBox());
/*     */       for (Mob mob : entities) {
/*     */         if (mob.m_5448_() == entity)
/*     */           mob.m_6710_(null); 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       super.tick(world, entity, manager);
/*     */       if (!world.f_46443_) {
/*     */         if (manager.getTicks() % 8 == 0 && !(entity instanceof WitherStormSegmentEntity))
/*     */           for (int i = 0; i < 3; i++)
/*     */             entity.dropSmallMassCluster(1);  
/*     */         if (entity.getSegmentsManager().isPresent() && manager.getTicks() == 201) {
/*     */           SegmentsManager segmentsManager = entity.getSegmentsManager().get();
/*     */           if (entity.getPhase() == 5) {
/*     */             entity.setPhase(6);
/*     */             entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SPLITS.get(), 1.0F, 1.0F);
/*     */             entity.setOtherHeadsDisabled(true);
/*     */             segmentsManager.createSegments();
/*     */             segmentsManager.addSegments();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       if (!entity.m_20096_()) {
/*     */         float xOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/*     */         float yOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/*     */         float zOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/*     */         world.m_7106_((ParticleOptions)ParticleTypes.f_123813_, entity.m_20185_() + xOffset, entity.m_20188_() + yOffset, entity.m_20189_() + zOffset, -5.0D, 0.0D, 0.0D);
/*     */       } 
/*     */       float percentage = (120.0F - Math.min(manager.ticksSinceInit, 120)) / 120.0F;
/*     */       for (DebrisRingSettings settings : entity.getDebrisRings())
/*     */         settings.setAlpha(percentage); 
/*     */       entity.setShineAlpha(percentage);
/*     */     }
/*     */     
/*     */     public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       super.init(world, entity, manager);
/*     */       if (!world.f_46443_) {
/*     */         for (WitherStormHead head : entity.getHeadManager().getHeads())
/*     */           head.doRoar(entity.m_217043_().m_188499_()); 
/*     */         if (entity.shouldPlaySoundLoop) {
/*     */           PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get());
/*     */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*     */         } 
/*     */       } 
/*     */       for (WitherStormHead head : entity.getHeadManager().getHeads())
/*     */         head.lerpHeadTo(-50.0F, entity.f_20883_, 64.0F); 
/*     */     }
/*     */     
/*     */     public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, PlayDeadManager.State next) {
/*     */       super.finish(world, entity, manager, next);
/*     */       if (!world.f_46443_ && entity.shouldPlaySoundLoop) {
/*     */         RemoveAdditionalLoopingSoundMessage message = new RemoveAdditionalLoopingSoundMessage(entity);
/*     */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> world.m_46472_()), message);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean disablesAi() {
/*     */       return true;
/*     */     }
/*     */     
/*     */     public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       return entity.m_20096_();
/*     */     }
/*     */     
/*     */     public void sendAdditionalPackets(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       super.sendAdditionalPackets(world, entity, manager);
/*     */       if (entity.shouldPlaySoundLoop) {
/*     */         PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get());
/*     */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       super.tick(world, entity, manager);
/*     */       if (entity.isOnBack() && entity.m_6084_() && !entity.m_21224_()) {
/*     */         manager.placePodium();
/*     */         BlockPos pos = manager.getPodiumPos();
/*     */         if (pos != null)
/*     */           entity.spawnConsumedPets(new Vec3(pos.m_123341_() + 0.5D, pos.m_123342_() + 12.0D, pos.m_123343_() + 0.5D)); 
/*     */       } 
/*     */       for (DebrisRingSettings settings : entity.getDebrisRings())
/*     */         settings.setAlpha(0.0F); 
/*     */       entity.setShineAlpha(0.0F);
/*     */       if (!world.f_46443_) {
/*     */         CommandBlockEntity commandBlock = manager.getCommandBlock();
/*     */         if (commandBlock != null && (!commandBlock.m_6084_() || commandBlock.m_20270_((Entity)entity) > 64.0D))
/*     */           manager.timeSinceCommandBlockMissing++; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       return (manager.timeSinceCommandBlockMissing > 200);
/*     */     }
/*     */     
/*     */     public boolean disablesAi() {
/*     */       return true;
/*     */     }
/*     */     
/*     */     public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       super.init(world, entity, manager);
/*     */       manager.timeSinceCommandBlockMissing = 0;
/*     */       for (WitherStormHead head : entity.getHeadManager().getHeads())
/*     */         head.lerpHeadTo(40.0F, entity.f_20883_, 16.0F); 
/*     */       if (!world.f_46443_)
/*     */         if (entity.getSegmentsManager().isPresent()) {
/*     */           SegmentsManager segmentsManager = entity.getSegmentsManager().get();
/*     */           if (entity.getPhase() == 5) {
/*     */             entity.setPhase(6);
/*     */             entity.setOtherHeadsDisabled(true);
/*     */             segmentsManager.createSegments();
/*     */             segmentsManager.addSegments();
/*     */           } 
/*     */         }  
/*     */     }
/*     */     
/*     */     public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, PlayDeadManager.State next) {
/*     */       manager.timeSinceCommandBlockMissing = 0;
/*     */       if (next != PlayDeadManager.State.REVIVING)
/*     */         manager.removePodium(); 
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       super.tick(world, entity, manager);
/*     */       if (!world.f_46443_ && manager.getTicks() > 20) {
/*     */         entity.shake(60.0F, 4.0F);
/*     */         BlockPos pos = manager.getPodiumPos();
/*     */         if (pos != null)
/*     */           world.m_254849_((Entity)entity, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), 16.0F, Level.ExplosionInteraction.NONE); 
/*     */         world.m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
/*     */         manager.setState(PlayDeadManager.State.NORMAL_BEHAVIOR);
/*     */         manager.removePodium();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/*     */       manager.setRecentlyRevived(true);
/*     */       super.init(world, entity, manager);
/*     */       if (!world.f_46443_ && entity.shouldPlayGlobalSounds)
/*     */         entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_REACTIVATES.get(), 10.0F, 1.0F); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\PlayDeadManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */