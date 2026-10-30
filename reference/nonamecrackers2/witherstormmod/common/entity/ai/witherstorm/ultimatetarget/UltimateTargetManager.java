/*      */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.core.BlockPos;
/*      */ import net.minecraft.core.Position;
/*      */ import net.minecraft.core.Vec3i;
/*      */ import net.minecraft.nbt.CompoundTag;
/*      */ import net.minecraft.nbt.NbtUtils;
/*      */ import net.minecraft.nbt.Tag;
/*      */ import net.minecraft.server.level.ServerLevel;
/*      */ import net.minecraft.server.level.ServerPlayer;
/*      */ import net.minecraft.util.Mth;
/*      */ import net.minecraft.world.entity.Entity;
/*      */ import net.minecraft.world.entity.EntitySelector;
/*      */ import net.minecraft.world.entity.LivingEntity;
/*      */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*      */ import net.minecraft.world.item.Item;
/*      */ import net.minecraft.world.item.ItemStack;
/*      */ import net.minecraft.world.level.ChunkPos;
/*      */ import net.minecraft.world.level.block.Blocks;
/*      */ import net.minecraft.world.phys.AABB;
/*      */ import net.minecraft.world.phys.Vec3;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ import net.minecraftforge.eventbus.api.Event;
/*      */ import nonamecrackers2.witherstormmod.api.common.event.WitherStormFindUltimateTargetEvent;
/*      */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*      */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*      */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*      */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
/*      */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModStructureTags;
/*      */ import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
/*      */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ public class UltimateTargetManager {
/*   45 */   private static final Logger LOGGER = LogManager.getLogger("witherstormmod/UltimateTargetManager"); private final WitherStormEntity entity; @Nullable
/*      */   protected LivingEntity ultimateTarget; @Nullable
/*      */   protected Vec3 ultimateTargetO; @Nullable
/*      */   protected UUID targetOverride; @Nullable
/*      */   protected BlockPos blockTargetOverride; @Nullable
/*      */   protected BlockPos alternativeUltimateTarget; @Nullable
/*      */   protected BlockPos distractedPos; @Nullable
/*      */   protected BlockPos randomStrollPos; @Nullable
/*      */   protected ChunkPos center; protected boolean ultimateTargetStationary; protected int ticksSinceStationary;
/*      */   protected int runawayDiminishTicks;
/*      */   protected final int chunkBoundaryRadius;
/*      */   protected int runawayAttempts;
/*      */   protected boolean canCountRunawayAttempt;
/*      */   protected boolean canBeDistracted;
/*      */   protected boolean isDistracted;
/*      */   protected int ticksSinceDistracted;
/*      */   protected int canBeDistractedFor;
/*      */   protected int distractionWait;
/*      */   protected int tillShowHole;
/*      */   protected int tillRandomStroll;
/*      */   protected int cannotSeeTargetFor;
/*      */   @Nullable
/*      */   protected DistractionReason distractionReason;
/*      */   protected int tiredOfChasingTicks;
/*      */   @Nullable
/*      */   protected UUID ignoredTarget;
/*      */   protected int ignoringTargetFor;
/*      */   protected int cannotReachTargetFor;
/*      */   protected int timeTillIgnoreTarget;
/*      */   @Nullable
/*      */   private UUID farthestPlayer;
/*      */   @Nullable
/*      */   private UUID randomPlayer;
/*      */   private long farthestLastSwitchTime;
/*      */   private long randomPlayerLastSwitchTime;
/*      */   @Nullable
/*      */   private TargetingType randomizedType;
/*      */   private long randomizedLastSwitchTime;
/*      */   
/*      */   public UltimateTargetManager(WitherStormEntity entity) {
/*   85 */     this.entity = entity;
/*   86 */     this.chunkBoundaryRadius = ((Integer)WitherStormModConfig.SERVER.targetStationaryChunkRadius.get()).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void tick() {
/*   91 */     List<ServerPlayer> players = (List<ServerPlayer>)((ServerLevel)this.entity.m_9236_()).m_6907_().stream().filter(p -> !p.m_20148_().equals(this.ignoredTarget)).collect(Collectors.toList());
/*      */     
/*   93 */     LivingEntity ultimateTarget = findUltimateTarget(players);
/*   94 */     if (ultimateTarget != this.ultimateTarget) {
/*      */       
/*   96 */       this.cannotReachTargetFor = 0;
/*   97 */       this.timeTillIgnoreTarget = 1200 + this.entity.m_217043_().m_188503_(600);
/*      */     } 
/*   99 */     setUltimateTarget(ultimateTarget);
/*  100 */     if (ultimateTarget != null) {
/*      */       
/*  102 */       setAlternativeUltimateTarget(ultimateTarget.m_20183_());
/*  103 */       if (((Boolean)WitherStormModConfig.SERVER.ignoreUltimateTargetIfHidden.get()).booleanValue() && !isDistracted() && players.size() > 1 && this.ignoredTarget == null && !WorldUtil.canSeeOrIsNotInASmallArea((Entity)this.entity, (Entity)ultimateTarget) && this.entity.m_20182_().m_82546_(ultimateTarget.m_20182_()).m_165924_() < 150.0D) {
/*      */         
/*  105 */         this.cannotReachTargetFor++;
/*  106 */         if (this.cannotReachTargetFor > this.timeTillIgnoreTarget)
/*      */         {
/*  108 */           this.ignoredTarget = ultimateTarget.m_20148_();
/*  109 */           this.ignoringTargetFor = 12000 + this.entity.m_217043_().m_188503_(6000);
/*      */         }
/*      */       
/*  112 */       } else if (this.cannotReachTargetFor > 0) {
/*      */         
/*  114 */         this.cannotReachTargetFor--;
/*      */       } 
/*      */     } 
/*  117 */     if (this.ignoringTargetFor > 0) {
/*      */       
/*  119 */       this.ignoringTargetFor--;
/*  120 */       if (this.ignoringTargetFor == 0) {
/*  121 */         this.ignoredTarget = null;
/*      */       }
/*      */     } 
/*  124 */     TargetingType targetingType = (TargetingType)WitherStormModConfig.SERVER.ultimateTargetingType.get();
/*  125 */     ServerPlayer randomStrollPlayer = TargetingType.NEAREST.getPlayer(this, this.entity, players, Predicate.not(Entity::m_5833_));
/*  126 */     boolean randomStrollNearPlayer = (targetingType == TargetingType.RANDOM_STROLL_NEAR_PLAYER && randomStrollPlayer != null);
/*      */     
/*  128 */     if (targetingType == TargetingType.RANDOM_STROLL || randomStrollNearPlayer) {
/*      */       
/*  130 */       int maxRadius = ((Integer)WitherStormModConfig.SERVER.maxRandomStrollTargetingTypeRadius.get()).intValue();
/*  131 */       BlockPos alt = getAlternativeUltimateTarget();
/*  132 */       if (alt == null || this.entity.m_20182_().m_82546_(Vec3.m_82512_((Vec3i)alt)).m_165924_() < 100.0D) {
/*      */         
/*  134 */         BlockPos newPos = null;
/*  135 */         for (int i = 0; i < 10; i++) {
/*      */           
/*  137 */           int x = this.entity.m_217043_().m_188503_(maxRadius * 2) - maxRadius;
/*  138 */           int z = this.entity.m_217043_().m_188503_(maxRadius * 2) - maxRadius;
/*  139 */           BlockPos entityPos = this.entity.m_20183_();
/*  140 */           if (randomStrollNearPlayer)
/*  141 */             entityPos = randomStrollPlayer.m_20183_(); 
/*  142 */           BlockPos current = entityPos.m_7918_(x, 0, z);
/*  143 */           if (alt == null || (Math.sqrt(alt.m_123331_((Vec3i)current)) > maxRadius / 2.0D && this.entity.m_20182_().m_82546_(Vec3.m_82512_((Vec3i)current)).m_165924_() > maxRadius / 2.0D))
/*      */           {
/*  145 */             if (this.entity.m_9236_().m_46739_(current) && this.entity.m_9236_().m_6857_().m_61937_(current)) {
/*      */               
/*  147 */               newPos = current;
/*      */               break;
/*      */             } 
/*      */           }
/*      */         } 
/*  152 */         if (newPos != null) {
/*  153 */           setAlternativeUltimateTarget(newPos);
/*      */         }
/*      */       } 
/*      */     } 
/*  157 */     for (ServerPlayer player : players) {
/*      */       
/*  159 */       for (ItemStack stack : (player.m_150109_()).f_35974_) {
/*      */         
/*  161 */         if (stack.m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS) && tillShowHole() <= 0 && !this.entity.isBeingTornApart() && this.entity.getPhase() > 6) {
/*  162 */           this.tillShowHole = ((Integer)WitherStormModConfig.SERVER.tillShouldShowHole.get()).intValue() * 1200 + this.entity.m_217043_().m_188503_(4800);
/*      */         }
/*      */       } 
/*      */     } 
/*  166 */     Vec3 ultimateTargetPos = getUltimateTargetPos();
/*      */     
/*  168 */     if (ultimateTargetPos != null) {
/*      */       
/*  170 */       BlockPos blockPos = BlockPos.m_274446_((Position)ultimateTargetPos);
/*      */       
/*  172 */       if (getCenter() == null) {
/*  173 */         setCenter(new ChunkPos(blockPos));
/*      */       }
/*      */       
/*  176 */       int distance = (int)(this.entity.m_20182_().m_82554_(ultimateTargetPos) * ((Double)WitherStormModConfig.SERVER.distanceMultiplier.get()).doubleValue());
/*  177 */       if (((Boolean)WitherStormModConfig.SERVER.usePhaseAsDistanceMultiplier.get()).booleanValue())
/*  178 */         distance = (int)(distance * (this.entity.getPhase() * 0.2D + 1.0D)); 
/*  179 */       int stationaryTicks = ((Integer)WitherStormModConfig.SERVER.targetStationaryMinutes.get()).intValue() * 1200;
/*  180 */       int runawayTicks = stationaryTicks - ((Integer)WitherStormModConfig.SERVER.targetRunawayMinutes.get()).intValue() * 1200;
/*  181 */       int runawayAttemptsDiminish = ((Integer)WitherStormModConfig.SERVER.minutesTillRunawayAttemptDiminish.get()).intValue() * 1200;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  186 */       if (!isDistracted()) {
/*      */ 
/*      */         
/*  189 */         if (this.ultimateTarget == null || !WorldUtil.hasLineOfSight((Entity)this.entity, (Entity)this.ultimateTarget)) {
/*  190 */           this.cannotSeeTargetFor++;
/*      */         } else {
/*  192 */           this.cannotSeeTargetFor = 0;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  197 */         Vec3 pos = getUltimateTargetPos();
/*  198 */         if (((Boolean)WitherStormModConfig.SERVER.randomStrollingWhenTargetHidden.get()).booleanValue() && pos != null && pos.m_82554_(this.entity.m_20182_()) < 300.0D) {
/*      */           
/*  200 */           if (cannotSeeTarget()) {
/*      */             
/*  202 */             if (this.tillRandomStroll == 0) {
/*  203 */               this.tillRandomStroll = (this.randomStrollPos == null) ? 600 : (1200 + this.entity.m_217043_().m_188503_(600));
/*      */             }
/*      */           } else {
/*      */             
/*  207 */             this.randomStrollPos = null;
/*  208 */             this.tillRandomStroll = 0;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  213 */           this.randomStrollPos = null;
/*  214 */           this.tillRandomStroll = 0;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  220 */         if (isPosInChunkRadius(blockPos)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  226 */           if (this.ticksSinceStationary > Math.max(2400, stationaryTicks - distance)) {
/*  227 */             this.ultimateTargetStationary = true;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  232 */           if (this.ticksSinceStationary <= Math.max(2400, stationaryTicks)) {
/*      */             
/*  234 */             this.ticksSinceStationary++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  240 */             if (this.ticksSinceStationary > ((Integer)WitherStormModConfig.SERVER.targetRunawayAttemptMinutes.get()).intValue() * 1200 && ((Boolean)WitherStormModConfig.SERVER.targetRunawayAttempts.get()).booleanValue()) {
/*  241 */               this.canCountRunawayAttempt = true;
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  247 */           if (this.runawayDiminishTicks > runawayAttemptsDiminish)
/*      */           {
/*  249 */             reduceRunawayAttempts();
/*  250 */             this.runawayDiminishTicks = 0;
/*      */           }
/*      */           else
/*      */           {
/*  254 */             this.runawayDiminishTicks++;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  266 */         else if (this.ticksSinceStationary <= Math.max(2400, runawayTicks - distance)) {
/*      */           
/*  268 */           if (this.ultimateTargetStationary) {
/*      */             
/*  270 */             boolean flag1 = true;
/*  271 */             CommandBlockEntity commandBlock = this.entity.getBowelsCommandBlock();
/*  272 */             if (commandBlock != null && commandBlock.m_21223_() < commandBlock.m_21233_()) {
/*  273 */               flag1 = false;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  278 */             if (this.entity.getPhase() > 3 && flag1) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  283 */               boolean flag = ((Boolean)WitherStormModConfig.SERVER.randomDistractionChances.get()).booleanValue();
/*  284 */               boolean shouldNotBeDistracted = (flag && this.entity.m_217043_().m_188503_(30) == 1);
/*  285 */               boolean shouldActuallyBeDistracted = (flag && this.entity.m_217043_().m_188503_(10) == 1);
/*  286 */               if ((canBeDistracted() || shouldActuallyBeDistracted) && !shouldNotBeDistracted) {
/*      */                 
/*  288 */                 int minimumDistance = ((Integer)WitherStormModConfig.SERVER.minimumDistractionDistance.get()).intValue();
/*      */ 
/*      */ 
/*      */                 
/*  292 */                 if (this.entity.m_20182_().m_82554_(ultimateTargetPos) < this.entity.m_21133_(Attributes.f_22277_) + minimumDistance && minimumDistance != 0)
/*  293 */                   setDistractionWait(((Integer)WitherStormModConfig.SERVER.distractionWaitTime.get()).intValue() * 1200 + this.entity.m_217043_().m_188503_(1200)); 
/*  294 */                 makeDistracted(DistractionReason.FINISHED_CHASING);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/*  299 */           this.ultimateTargetStationary = false;
/*      */           
/*  301 */           setCenter(new ChunkPos(blockPos));
/*  302 */           this.ticksSinceStationary = 0;
/*      */         }
/*      */         else {
/*      */           
/*  306 */           this.ticksSinceStationary--;
/*  307 */           this.ultimateTargetStationary = true;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  314 */         if (isTargetStationary() && !this.canBeDistracted)
/*      */         {
/*  316 */           if (this.entity.m_20182_().m_82554_(ultimateTargetPos) < this.entity.m_21133_(Attributes.f_22277_) + ((Integer)WitherStormModConfig.SERVER.maximumDistractionDistance.get()).intValue()) {
/*  317 */             this.canBeDistracted = true;
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  323 */         if (getDistractionWait() > 0) {
/*      */           
/*  325 */           this.distractionWait--;
/*  326 */           if (this.distractionWait <= 0 && canBeDistracted() && this.entity.m_20182_().m_82554_(ultimateTargetPos) >= this.entity.m_21133_(Attributes.f_22277_) + 50.0D && !this.ultimateTargetStationary) {
/*  327 */             makeDistracted(DistractionReason.FINISHED_CHASING_DELAYED);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  334 */         if (((Boolean)WitherStormModConfig.SERVER.boatingForTooLongDistractions.get()).booleanValue() && isTargetStationary() && canBeDistracted() && ultimateTarget != null && this.ultimateTargetO != null)
/*      */         {
/*  336 */           double speed = this.ultimateTargetO.m_82546_(ultimateTarget.m_20182_()).m_165924_();
/*  337 */           if (speed >= 0.39D) {
/*  338 */             this.tiredOfChasingTicks++;
/*  339 */           } else if (this.tiredOfChasingTicks > 0) {
/*  340 */             this.tiredOfChasingTicks--;
/*      */           } 
/*  342 */           if (this.tiredOfChasingTicks > ((Integer)WitherStormModConfig.SERVER.boatingForTooLongSeconds.get()).intValue() * 20) {
/*  343 */             makeDistracted(DistractionReason.TIRED_OF_CHASING);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  354 */         this.ticksSinceDistracted++;
/*      */         
/*  356 */         if (getTicksSinceDistracted() > this.canBeDistractedFor) {
/*  357 */           makeFocused();
/*      */         }
/*  359 */         if (this.distractionReason != DistractionReason.TIRED_OF_CHASING && this.entity.m_20182_().m_82554_(ultimateTargetPos) < this.entity.m_21133_(Attributes.f_22277_) * 2.5D) {
/*  360 */           makeFocused();
/*      */         }
/*  362 */         if (this.distractionReason == DistractionReason.TIRED_OF_CHASING && ultimateTarget != null && this.ultimateTargetO != null) {
/*      */           
/*  364 */           double speed = this.ultimateTargetO.m_82546_(ultimateTarget.m_20182_()).m_165924_();
/*  365 */           if (speed >= 0.39D) {
/*      */             
/*  367 */             if (this.tiredOfChasingTicks < ((Integer)WitherStormModConfig.SERVER.boatingForTooLongSeconds.get()).intValue() * 20) {
/*  368 */               this.tiredOfChasingTicks++;
/*      */             }
/*  370 */           } else if (this.tiredOfChasingTicks > 0) {
/*      */             
/*  372 */             this.tiredOfChasingTicks--;
/*  373 */             if (this.tiredOfChasingTicks == 0) {
/*  374 */               makeFocused();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  382 */       if (this.runawayAttempts >= ((Integer)WitherStormModConfig.SERVER.targetRunawayAttemptsRequired.get()).intValue() && ((Boolean)WitherStormModConfig.SERVER.targetRunawayAttempts.get()).booleanValue() && !isDistracted()) {
/*      */         
/*  384 */         accelerate();
/*  385 */         this.canCountRunawayAttempt = false;
/*  386 */         setRunawayAttempts(0);
/*      */       } 
/*      */     } 
/*      */     
/*  390 */     if (this.tillRandomStroll > 0) {
/*      */       
/*  392 */       this.tillRandomStroll--;
/*  393 */       if (this.tillRandomStroll == 0) {
/*  394 */         findAndSetRandomNearbyStrollPos();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  416 */     if (this.tillShowHole > 0) {
/*      */       
/*  418 */       this.tillShowHole--;
/*  419 */       if (this.tillShowHole == 0 && this.entity.getPhase() > 6) {
/*  420 */         this.entity.setShouldShowHole(true);
/*      */       }
/*      */     } 
/*  423 */     if (ultimateTarget != null) {
/*  424 */       assert this.ultimateTarget != null;
/*  425 */       this.ultimateTargetO = this.ultimateTarget.m_20182_();
/*      */     } 
/*      */   }
/*      */   @Nullable
/*      */   public LivingEntity findUltimateTarget(List<ServerPlayer> players) {
/*      */     WitherStormEntity witherStormEntity;
/*  431 */     LivingEntity override = null;
/*      */     
/*  433 */     Predicate<Entity> predicate = entity -> !entity.m_5833_();
/*      */     
/*  435 */     if (((Boolean)WitherStormModConfig.SERVER.amuletOverride.get()).booleanValue()) {
/*      */       
/*  437 */       double d0 = -1.0D;
/*  438 */       for (ServerPlayer player : players) {
/*      */         
/*  440 */         if (predicate.test(player))
/*      */         {
/*  442 */           for (ItemStack stack : (player.m_150109_()).f_35974_) {
/*      */             
/*  444 */             if (stack.m_150930_((Item)WitherStormModItems.AMULET.get())) {
/*      */               
/*  446 */               double d1 = player.m_20280_((Entity)this.entity);
/*  447 */               if (d1 < d0 || d0 == -1.0D) {
/*      */                 
/*  449 */                 d0 = player.m_20280_((Entity)this.entity);
/*  450 */                 ServerPlayer serverPlayer = player;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  458 */     ServerLevel level = (ServerLevel)this.entity.m_9236_();
/*  459 */     if (getTargetOverride() != null) {
/*      */       
/*  461 */       Entity found = level.m_8791_(getTargetOverride());
/*  462 */       if (found instanceof LivingEntity) { LivingEntity living = (LivingEntity)found; if (!found.equals(this.entity) && !(found instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity))
/*  463 */           override = living;  }
/*      */     
/*      */     } 
/*  466 */     if (((Boolean)WitherStormModConfig.SERVER.witherStormsFollowBiggerStorms.get()).booleanValue()) {
/*      */       
/*  468 */       double distance = -1.0D;
/*  469 */       WitherStormEntity closest = null;
/*  470 */       for (Entity entity : level.m_8583_()) {
/*      */         
/*  472 */         if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity; if (storm != this.entity && storm.m_20270_((Entity)this.entity) < 1000.0D && !storm.isDeadOrPlayingDead() && storm.getConsumedEntities() > this.entity.getConsumedEntities()) {
/*      */             
/*  474 */             double dist = storm.m_20270_((Entity)this.entity);
/*  475 */             if (distance == -1.0D || dist < distance) {
/*      */               
/*  477 */               distance = dist;
/*  478 */               closest = storm;
/*      */             } 
/*      */           }  }
/*      */       
/*  482 */       }  if (closest != null) {
/*  483 */         witherStormEntity = closest;
/*      */       }
/*      */     } 
/*  486 */     LivingEntity finalTarget = (witherStormEntity != null) ? (LivingEntity)witherStormEntity : (LivingEntity)((TargetingType)WitherStormModConfig.SERVER.ultimateTargetingType.get()).getPlayer(this, this.entity, players, predicate);
/*  487 */     WitherStormFindUltimateTargetEvent event = new WitherStormFindUltimateTargetEvent(this.entity, finalTarget);
/*  488 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*  489 */     return event.getOriginalUltimateTarget();
/*      */   }
/*      */ 
/*      */   
/*      */   public void accelerate() {
/*  494 */     if (isDistracted())
/*  495 */       makeFocused(); 
/*  496 */     this.ticksSinceStationary = ((Integer)WitherStormModConfig.SERVER.targetStationaryMinutes.get()).intValue() * 1200;
/*  497 */     this.ultimateTargetStationary = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void deaccelerate() {
/*  502 */     this.ticksSinceStationary = 0;
/*  503 */     this.ultimateTargetStationary = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUltimateTarget(@Nullable LivingEntity player) {
/*  508 */     this.ultimateTarget = player;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAlternativeUltimateTarget(@Nullable BlockPos pos) {
/*  513 */     this.alternativeUltimateTarget = pos;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public LivingEntity getUltimateTarget() {
/*  518 */     return this.ultimateTarget;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPos getAlternativeUltimateTarget() {
/*  523 */     return this.alternativeUltimateTarget;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Vec3 getUltimateTargetPos() {
/*  528 */     if (this.blockTargetOverride != null)
/*  529 */       return Vec3.m_82512_((Vec3i)this.blockTargetOverride); 
/*  530 */     if (this.ultimateTarget != null) {
/*  531 */       return this.ultimateTarget.m_20182_();
/*      */     }
/*  533 */     return (this.alternativeUltimateTarget != null) ? Vec3.m_82512_((Vec3i)this.alternativeUltimateTarget) : null;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public ChunkPos getCenter() {
/*  538 */     return this.center;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCenter(ChunkPos pos) {
/*  543 */     this.center = pos;
/*  544 */     if (this.canCountRunawayAttempt) {
/*      */       
/*  546 */       countRunawayAttempt();
/*  547 */       this.canCountRunawayAttempt = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPosInChunkRadius(BlockPos pos) {
/*  553 */     if (pos != null && this.center != null)
/*      */     {
/*  555 */       for (int x = -this.chunkBoundaryRadius; x <= this.chunkBoundaryRadius; x++) {
/*      */         
/*  557 */         for (int z = -this.chunkBoundaryRadius; z <= this.chunkBoundaryRadius; z++) {
/*      */           
/*  559 */           ChunkPos chunk = new ChunkPos(this.center.f_45578_ + x, this.center.f_45579_ + z);
/*  560 */           if (chunk.equals(new ChunkPos(pos)))
/*  561 */             return true; 
/*      */         } 
/*      */       } 
/*      */     }
/*  565 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTargetStationary() {
/*  570 */     return this.ultimateTargetStationary;
/*      */   }
/*      */ 
/*      */   
/*      */   public int targetStationaryTicks() {
/*  575 */     return this.ticksSinceStationary;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTargetStationaryTicks(int amount) {
/*  580 */     this.ticksSinceStationary = amount;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRunawayAttempts() {
/*  585 */     return this.runawayAttempts;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRunawayAttempts(int attempts) {
/*  590 */     this.runawayAttempts = attempts;
/*      */   }
/*      */ 
/*      */   
/*      */   public void countRunawayAttempt() {
/*  595 */     this.runawayAttempts++;
/*      */   }
/*      */ 
/*      */   
/*      */   public void reduceRunawayAttempts() {
/*  600 */     double amount = (this.runawayAttempts - 1);
/*  601 */     if (amount >= 0.0D) {
/*  602 */       this.runawayAttempts--;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getRunawayDiminishTicks() {
/*  607 */     return this.runawayDiminishTicks;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPos findDistractPos() {
/*  612 */     ServerLevel level = (ServerLevel)this.entity.m_9236_();
/*  613 */     BlockPos pos = level.m_215011_(WitherStormModStructureTags.WITHER_STORM_DISTRACTABLE, this.entity.m_20183_(), 100, false);
/*  614 */     if (pos == null) {
/*      */       
/*  616 */       int times = 10;
/*  617 */       for (int i = 0; i < times && pos == null; i++) {
/*      */         
/*  619 */         int multiplier = ((Integer)WitherStormModConfig.SERVER.searchRangeMultiplier.get()).intValue();
/*  620 */         double range = this.entity.m_21133_(Attributes.f_22277_) * 2.0D * multiplier;
/*  621 */         BlockPos current = BlockPos.m_274561_(this.entity.m_20208_(range), this.entity.m_20186_(), this.entity.m_20262_(range));
/*  622 */         if (this.entity.m_9236_().m_8055_(current).m_60713_(Blocks.f_50016_) && this.entity.distanceTo(current) > 2000.0D * multiplier && Math.sqrt(current.m_203193_((Position)Objects.requireNonNull(getUltimateTargetPos()))) > 500.0D)
/*  623 */           pos = current; 
/*      */       } 
/*      */     } 
/*  626 */     return pos;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeDistracted() {
/*  631 */     return (this.canBeDistracted && ((Boolean)WitherStormModConfig.SERVER.targettingDistractionsEnabled.get()).booleanValue());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCanBeDistracted(boolean distractable) {
/*  636 */     this.canBeDistracted = distractable;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDistracted() {
/*  641 */     return this.isDistracted;
/*      */   }
/*      */ 
/*      */   
/*      */   public void markDistracted(boolean distracted) {
/*  646 */     this.isDistracted = distracted;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTicksSinceDistracted() {
/*  651 */     return this.ticksSinceDistracted;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTicksSinceDistracted(int ticks) {
/*  656 */     this.ticksSinceDistracted = ticks;
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeDistracted(DistractionReason reason) {
/*  661 */     if (((Boolean)WitherStormModConfig.SERVER.targettingDistractionsEnabled.get()).booleanValue() && this.distractionWait <= 0) {
/*      */       
/*  663 */       this.distractedPos = findDistractPos();
/*  664 */       if (this.distractedPos != null) {
/*      */         
/*  666 */         this.isDistracted = true;
/*  667 */         this.canBeDistractedFor = Math.max(4800, ((Integer)WitherStormModConfig.SERVER.distractionTimeMinutes.get()).intValue() * 1200 + this.entity.m_217043_().m_188503_(12000) - (int)this.entity.distanceTo(this.distractedPos) * 2);
/*  668 */         this.canBeDistracted = false;
/*  669 */         this.distractionReason = reason;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeFocused() {
/*  676 */     this.isDistracted = false;
/*  677 */     this.ticksSinceDistracted = 0;
/*  678 */     this.canBeDistracted = false;
/*  679 */     this.distractedPos = null;
/*  680 */     this.canBeDistractedFor = 0;
/*  681 */     this.distractionWait = 0;
/*  682 */     this.distractionReason = null;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPos getDistractedPos() {
/*  687 */     return this.distractedPos;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDistractedPos(@Nullable BlockPos pos) {
/*  692 */     this.distractedPos = pos;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDistractedTickTime() {
/*  697 */     return this.canBeDistractedFor;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDistractedTickTime(int ticks) {
/*  702 */     this.canBeDistractedFor = ticks;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDistractionWait() {
/*  707 */     return this.distractionWait;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDistractionWait(int ticks) {
/*  712 */     this.distractionWait = ticks;
/*      */   }
/*      */ 
/*      */   
/*      */   public int tillShowHole() {
/*  717 */     return this.tillShowHole;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTillShowHole(int ticks) {
/*  722 */     this.tillShowHole = ticks;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTargetOverride(@Nullable UUID uuid) {
/*  727 */     this.targetOverride = uuid;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public UUID getTargetOverride() {
/*  732 */     return this.targetOverride;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBlockTargetOverride(@Nullable BlockPos pos) {
/*  737 */     this.blockTargetOverride = pos;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPos getBlockTargetOverride() {
/*  742 */     return this.blockTargetOverride;
/*      */   }
/*      */ 
/*      */   
/*      */   public void findAndSetRandomNearbyStrollPos() {
/*  747 */     Vec3 pos = getUltimateTargetPos();
/*  748 */     if (pos != null) {
/*      */       
/*  750 */       BlockPos newPos = null;
/*  751 */       for (int i = 0; i < 10; i++) {
/*      */         
/*  753 */         float angle = this.entity.m_217043_().m_188501_() * 6.2831855F;
/*  754 */         int x = (int)(Mth.m_14089_(angle) * (this.entity.m_217043_().m_188503_(50) + 150.0F));
/*  755 */         int z = (int)(Mth.m_14031_(angle) * (this.entity.m_217043_().m_188503_(50) + 150.0F));
/*  756 */         BlockPos current = new BlockPos(x + Mth.m_14107_(pos.f_82479_), Mth.m_14107_(pos.f_82480_), z + Mth.m_14107_(pos.f_82481_));
/*  757 */         if (this.randomStrollPos == null || (Math.sqrt(this.randomStrollPos.m_123331_((Vec3i)current)) > 100.0D && this.entity.m_20182_().m_82546_(Vec3.m_82512_((Vec3i)current)).m_165924_() > 100.0D)) {
/*      */           
/*  759 */           newPos = current;
/*      */           break;
/*      */         } 
/*      */       } 
/*  763 */       this.randomStrollPos = newPos;
/*      */     }
/*      */     else {
/*      */       
/*  767 */       this.randomStrollPos = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPos getRandomStrollPos() {
/*  773 */     return this.randomStrollPos;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRandomStrolling() {
/*  778 */     return (this.randomStrollPos != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean cannotSeeTarget() {
/*  783 */     return (this.cannotSeeTargetFor > 600);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void save(CompoundTag compound) {
/*  796 */     if (getAlternativeUltimateTarget() != null)
/*  797 */       compound.m_128365_("AlternativeUltimateTarget", (Tag)NbtUtils.m_129224_(getAlternativeUltimateTarget())); 
/*  798 */     if (getCenter() != null)
/*  799 */       compound.m_128365_("UltimateTargetChunkPos", (Tag)WitherStormModNBTUtil.writeChunkPos(getCenter())); 
/*  800 */     compound.m_128405_("TargetStationaryTicks", targetStationaryTicks());
/*  801 */     compound.m_128405_("TargetRunawayAttempts", getRunawayAttempts());
/*  802 */     if (getTargetOverride() != null)
/*  803 */       compound.m_128362_("TargetOverride", getTargetOverride()); 
/*  804 */     if (getBlockTargetOverride() != null)
/*  805 */       compound.m_128365_("BlockTargetOverride", (Tag)NbtUtils.m_129224_(getBlockTargetOverride())); 
/*  806 */     CompoundTag ultimateTargetDistractions = new CompoundTag();
/*  807 */     if (getDistractedPos() != null)
/*  808 */       ultimateTargetDistractions.m_128365_("DistractedPos", (Tag)NbtUtils.m_129224_(getDistractedPos())); 
/*  809 */     ultimateTargetDistractions.m_128379_("CanBeDistracted", canBeDistracted());
/*  810 */     ultimateTargetDistractions.m_128379_("IsDistracted", isDistracted());
/*  811 */     ultimateTargetDistractions.m_128405_("TicksSinceDistracted", getTicksSinceDistracted());
/*  812 */     ultimateTargetDistractions.m_128405_("CanBeDistractedFor", getDistractedTickTime());
/*  813 */     ultimateTargetDistractions.m_128405_("DistractionWait", getDistractionWait());
/*  814 */     if (this.distractionReason != null)
/*  815 */       ultimateTargetDistractions.m_128405_("DistractionReason", this.distractionReason.ordinal()); 
/*  816 */     ultimateTargetDistractions.m_128405_("TiredOfChasingTicks", this.tiredOfChasingTicks);
/*  817 */     compound.m_128365_("UltimateTargetDistraction", (Tag)ultimateTargetDistractions);
/*  818 */     if (this.randomStrollPos != null)
/*  819 */       compound.m_128365_("RandomStrollPos", (Tag)NbtUtils.m_129224_(this.randomStrollPos)); 
/*  820 */     compound.m_128405_("RandomStrollTimer", this.tillRandomStroll);
/*  821 */     if (this.ignoredTarget != null)
/*  822 */       compound.m_128362_("IgnoredTarget", this.ignoredTarget); 
/*  823 */     compound.m_128405_("IgnoringTargetFor", this.ignoringTargetFor);
/*  824 */     compound.m_128405_("CannotReachTargetFor", this.cannotReachTargetFor);
/*  825 */     compound.m_128405_("TimeTillIgnoreTarget", this.timeTillIgnoreTarget);
/*  826 */     compound.m_128405_("CannotSeeTargetFor", this.cannotSeeTargetFor);
/*      */   }
/*      */ 
/*      */   
/*      */   public void read(CompoundTag compound) {
/*  831 */     if (compound.m_128441_("AlternativeUltimateTarget"))
/*  832 */       setAlternativeUltimateTarget(NbtUtils.m_129239_(compound.m_128469_("AlternativeUltimateTarget"))); 
/*  833 */     if (compound.m_128441_("UltimateTargetChunkPos"))
/*  834 */       this.center = WitherStormModNBTUtil.readChunkPos(compound.m_128469_("UltimateTargetChunkPos")); 
/*  835 */     setTargetStationaryTicks(compound.m_128451_("TargetStationaryTicks"));
/*  836 */     setRunawayAttempts(compound.m_128451_("TargetRunawayAttempts"));
/*  837 */     if (compound.m_128441_("TargetOverride"))
/*  838 */       setTargetOverride(compound.m_128342_("TargetOverride")); 
/*  839 */     if (compound.m_128441_("BlockTargetOverride"))
/*  840 */       setBlockTargetOverride(NbtUtils.m_129239_(compound.m_128469_("BlockTargetOverride"))); 
/*  841 */     CompoundTag ultimateTargetDistractions = compound.m_128469_("UltimateTargetDistraction");
/*  842 */     if (ultimateTargetDistractions.m_128441_("DistractedPos"))
/*  843 */       setDistractedPos(NbtUtils.m_129239_(ultimateTargetDistractions.m_128469_("DistractedPos"))); 
/*  844 */     setCanBeDistracted(ultimateTargetDistractions.m_128471_("CanBeDistracted"));
/*  845 */     markDistracted(ultimateTargetDistractions.m_128471_("IsDistracted"));
/*  846 */     setTicksSinceDistracted(ultimateTargetDistractions.m_128451_("TicksSinceDistracted"));
/*  847 */     setDistractedTickTime(ultimateTargetDistractions.m_128451_("CanBeDistractedFor"));
/*  848 */     setDistractionWait(ultimateTargetDistractions.m_128451_("DistractionWait"));
/*  849 */     if (ultimateTargetDistractions.m_128425_("DistractionReason", 3)) {
/*      */       
/*  851 */       int ordinal = ultimateTargetDistractions.m_128451_("DistractionReason");
/*  852 */       if (ordinal >= 0 && ordinal < (DistractionReason.values()).length)
/*  853 */         this.distractionReason = DistractionReason.values()[ordinal]; 
/*      */     } 
/*  855 */     this.tiredOfChasingTicks = ultimateTargetDistractions.m_128451_("TiredOfChasingTicks");
/*  856 */     if (compound.m_128441_("RandomStrollPos"))
/*  857 */       this.randomStrollPos = NbtUtils.m_129239_(compound.m_128469_("RandomStrollPos")); 
/*  858 */     this.tillRandomStroll = compound.m_128451_("RandomStrollTimer");
/*  859 */     if (compound.m_128403_("IgnoredTarget"))
/*  860 */       this.ignoredTarget = compound.m_128342_("IgnoredTarget"); 
/*  861 */     this.ignoringTargetFor = compound.m_128451_("IgnoringTargetFor");
/*  862 */     this.cannotReachTargetFor = compound.m_128451_("CannotReachTargetFor");
/*  863 */     this.timeTillIgnoreTarget = compound.m_128451_("TimeTillIgnoreTarget");
/*  864 */     this.cannotSeeTargetFor = compound.m_128451_("CannotSeeTargetFor");
/*      */   }
/*      */   
/*      */   public enum TargetingType
/*      */   {
/*  869 */     NEAREST
/*      */     {
/*      */       @Nullable
/*      */       public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */       {
/*  874 */         double d0 = -1.0D;
/*  875 */         ServerPlayer closest = null;
/*  876 */         for (ServerPlayer player : players) {
/*      */           
/*  878 */           if (predicate.test(player)) {
/*      */             
/*  880 */             double d1 = player.m_20280_((Entity)storm);
/*  881 */             if (d1 < d0 || d0 == -1.0D) {
/*      */               
/*  883 */               d0 = player.m_20280_((Entity)storm);
/*  884 */               closest = player;
/*      */             } 
/*      */           } 
/*      */         } 
/*  888 */         return closest;
/*      */       }
/*      */     },
/*  891 */     FARTHEST
/*      */     {
/*      */       @Nullable
/*      */       public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */       {
/*  896 */         long currentTime = System.currentTimeMillis();
/*  897 */         long timeThreshold = (((Integer)WitherStormModConfig.SERVER.farthestTargetingTime.get()).intValue() * 60 * 1000);
/*  898 */         ServerPlayer farthestPlayer = (manager.farthestPlayer != null) ? (ServerPlayer)storm.m_9236_().m_46003_(manager.farthestPlayer) : null;
/*  899 */         if (currentTime - manager.farthestLastSwitchTime >= timeThreshold || farthestPlayer == null) {
/*      */           
/*  901 */           double d0 = -1.0D;
/*      */           
/*  903 */           for (ServerPlayer player : players) {
/*      */             
/*  905 */             if (predicate.test(player)) {
/*      */               
/*  907 */               double d1 = player.m_20280_((Entity)storm);
/*  908 */               if (d1 > d0 || d0 == -1.0D) {
/*      */                 
/*  910 */                 d0 = player.m_20280_((Entity)storm);
/*  911 */                 manager.farthestPlayer = player.m_20148_();
/*  912 */                 farthestPlayer = player;
/*  913 */                 UltimateTargetManager.LOGGER.info("FARTHEST: Farthest Player was " + player + ", Going to them for " + WitherStormModConfig.SERVER.farthestTargetingTime.get() + " minute(s)");
/*      */               } 
/*      */             } 
/*      */           } 
/*  917 */           manager.farthestLastSwitchTime = currentTime;
/*      */         } 
/*  919 */         return farthestPlayer;
/*      */       }
/*      */     },
/*  922 */     GROUP
/*      */     {
/*      */       @Nullable
/*      */       public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */       {
/*  927 */         double size = -1.0D;
/*  928 */         ServerPlayer toTarget = null;
/*  929 */         for (ServerPlayer player : players) {
/*      */           
/*  931 */           if (predicate.test(player)) {
/*      */             
/*  933 */             AABB box = player.m_20191_().m_82400_(20.0D);
/*  934 */             List<ServerPlayer> nearby = storm.m_9236_().m_6443_(ServerPlayer.class, box, EntitySelector.f_20403_.and(EntitySelector.f_20408_));
/*  935 */             if (nearby.size() > size) {
/*      */               
/*  937 */               size = nearby.size();
/*  938 */               toTarget = player;
/*      */             } 
/*      */           } 
/*      */         } 
/*  942 */         return toTarget;
/*      */       }
/*      */     },
/*  945 */     NONE
/*      */     {
/*      */       @Nullable
/*      */       public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */       {
/*  950 */         return null;
/*      */       }
/*      */     },
/*  953 */     RANDOM_STROLL
/*      */     {
/*      */       @Nullable
/*      */       public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */       {
/*  958 */         return null;
/*      */       }
/*      */     },
/*      */     
/*  962 */     RANDOM_PLAYER
/*      */     {
/*      */       @Nullable
/*      */       public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */       {
/*  967 */         long currentTime = System.currentTimeMillis();
/*  968 */         long timeThreshold = 300000L;
/*  969 */         ServerPlayer randomPlayer = (manager.randomPlayer != null) ? (ServerPlayer)storm.m_9236_().m_46003_(manager.randomPlayer) : null;
/*  970 */         if (currentTime - manager.randomPlayerLastSwitchTime >= timeThreshold || randomPlayer == null) {
/*      */           
/*  972 */           Random random = new Random();
/*      */ 
/*      */ 
/*      */           
/*  976 */           List<ServerPlayer> survivalPlayers = players.stream().filter(player -> player.f_8941_.m_9294_()).filter(predicate).toList();
/*  977 */           if (!survivalPlayers.isEmpty()) {
/*      */             
/*  979 */             int randomizer = random.nextInt(survivalPlayers.size());
/*  980 */             randomPlayer = survivalPlayers.get(randomizer);
/*  981 */             manager.randomPlayer = randomPlayer.m_20148_();
/*  982 */             UltimateTargetManager.LOGGER.info("RANDOM_PLAYER: Chose a player: " + randomPlayer + ", Going to them for 5 minutes");
/*      */           }
/*      */           else {
/*      */             
/*  986 */             ServerPlayer nearestPlayer = NEAREST.getPlayer(manager, storm, players, predicate);
/*  987 */             if (nearestPlayer != null) {
/*      */               
/*  989 */               manager.randomPlayer = nearestPlayer.m_20148_();
/*  990 */               randomPlayer = nearestPlayer;
/*  991 */               UltimateTargetManager.LOGGER.info("RANDOM_PLAYER: Couldn't find a valid player in Survival, changing to NEAREST for 5 minutes");
/*      */             } 
/*      */           } 
/*  994 */           manager.randomPlayerLastSwitchTime = currentTime;
/*      */         } 
/*  996 */         return randomPlayer;
/*      */       }
/*      */     },
/*  999 */     RANDOMIZED
/*      */     {
/*      */       @Nullable
/*      */       public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */       {
/* 1004 */         long currentTime = System.currentTimeMillis();
/* 1005 */         if (currentTime - manager.randomizedLastSwitchTime >= (((Integer)WitherStormModConfig.SERVER.randomizedTargetingTime.get()).intValue() * 60 * 1000)) {
/*      */           List<TargetingType> availableTypes;
/*      */           
/* 1008 */           if (players.size() <= 1) {
/* 1009 */             availableTypes = Arrays.asList(new TargetingType[] { NEAREST, RANDOM_STROLL, RANDOM_STROLL_NEAR_PLAYER });
/*      */           } else {
/* 1011 */             availableTypes = Arrays.asList(new TargetingType[] { NEAREST, FARTHEST, GROUP, RANDOM_STROLL, RANDOM_PLAYER });
/* 1012 */           }  TargetingType lastType = manager.randomizedType;
/*      */           
/*      */           do {
/* 1015 */             manager.randomizedType = availableTypes.get(storm.m_217043_().m_188503_(availableTypes.size()));
/*      */           }
/* 1017 */           while (manager.randomizedType == lastType);
/*      */           
/* 1019 */           manager.randomizedLastSwitchTime = currentTime;
/* 1020 */           UltimateTargetManager.LOGGER.info("RANDOMIZED: Targeting Type has been set to: " + manager.randomizedType);
/* 1021 */           if (storm.m_217043_().m_216339_(1, 11) == 1 && storm.getPhase() >= 4 && ((Boolean)WitherStormModConfig.SERVER.randomlySpeedUpWithTargetChange.get()).booleanValue()) {
/*      */             
/* 1023 */             manager.accelerate();
/* 1024 */             UltimateTargetManager.LOGGER.info("RANDOMIZED: The Wither Storm will be speeding up!!!");
/*      */           } 
/*      */         } 
/* 1027 */         assert manager.randomizedType != null;
/* 1028 */         return manager.randomizedType.getPlayer(manager, storm, players, predicate);
/*      */       }
/*      */     },
/* 1031 */     RANDOM_STROLL_NEAR_PLAYER
/*      */     {
/*      */       @Nullable
/*      */       public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */       {
/* 1036 */         return null; } }; @Nullable public abstract ServerPlayer getPlayer(UltimateTargetManager param1UltimateTargetManager, WitherStormEntity param1WitherStormEntity, List<ServerPlayer> param1List, Predicate<Entity> param1Predicate); } enum null { @Nullable public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate) { double d0 = -1.0D; ServerPlayer closest = null; for (ServerPlayer player : players) { if (predicate.test(player)) { double d1 = player.m_20280_((Entity)storm); if (d1 < d0 || d0 == -1.0D) { d0 = player.m_20280_((Entity)storm); closest = player; }  }  }  return closest; } } enum null { @Nullable public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate) { long currentTime = System.currentTimeMillis(); long timeThreshold = (((Integer)WitherStormModConfig.SERVER.farthestTargetingTime.get()).intValue() * 60 * 1000); ServerPlayer farthestPlayer = (manager.farthestPlayer != null) ? (ServerPlayer)storm.m_9236_().m_46003_(manager.farthestPlayer) : null; if (currentTime - manager.farthestLastSwitchTime >= timeThreshold || farthestPlayer == null) { double d0 = -1.0D; for (ServerPlayer player : players) { if (predicate.test(player)) { double d1 = player.m_20280_((Entity)storm); if (d1 > d0 || d0 == -1.0D) { d0 = player.m_20280_((Entity)storm); manager.farthestPlayer = player.m_20148_(); farthestPlayer = player; UltimateTargetManager.LOGGER.info("FARTHEST: Farthest Player was " + player + ", Going to them for " + WitherStormModConfig.SERVER.farthestTargetingTime.get() + " minute(s)"); }  }  }  manager.farthestLastSwitchTime = currentTime; }  return farthestPlayer; } } enum null { @Nullable public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate) { double size = -1.0D; ServerPlayer toTarget = null; for (ServerPlayer player : players) { if (predicate.test(player)) { AABB box = player.m_20191_().m_82400_(20.0D); List<ServerPlayer> nearby = storm.m_9236_().m_6443_(ServerPlayer.class, box, EntitySelector.f_20403_.and(EntitySelector.f_20408_)); if (nearby.size() > size) { size = nearby.size(); toTarget = player; }  }  }  return toTarget; } } enum null { @Nullable public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate) { return null; } } enum null { @Nullable public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate) { return null; } } enum null { @Nullable public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate) { long currentTime = System.currentTimeMillis(); long timeThreshold = 300000L; ServerPlayer randomPlayer = (manager.randomPlayer != null) ? (ServerPlayer)storm.m_9236_().m_46003_(manager.randomPlayer) : null; if (currentTime - manager.randomPlayerLastSwitchTime >= timeThreshold || randomPlayer == null) { Random random = new Random(); List<ServerPlayer> survivalPlayers = players.stream().filter(player -> player.f_8941_.m_9294_()).filter(predicate).toList(); if (!survivalPlayers.isEmpty()) { int randomizer = random.nextInt(survivalPlayers.size()); randomPlayer = survivalPlayers.get(randomizer); manager.randomPlayer = randomPlayer.m_20148_(); UltimateTargetManager.LOGGER.info("RANDOM_PLAYER: Chose a player: " + randomPlayer + ", Going to them for 5 minutes"); } else { ServerPlayer nearestPlayer = NEAREST.getPlayer(manager, storm, players, predicate); if (nearestPlayer != null) { manager.randomPlayer = nearestPlayer.m_20148_(); randomPlayer = nearestPlayer; UltimateTargetManager.LOGGER.info("RANDOM_PLAYER: Couldn't find a valid player in Survival, changing to NEAREST for 5 minutes"); }  }  manager.randomPlayerLastSwitchTime = currentTime; }  return randomPlayer; } } enum null { @Nullable public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate) { long currentTime = System.currentTimeMillis(); if (currentTime - manager.randomizedLastSwitchTime >= (((Integer)WitherStormModConfig.SERVER.randomizedTargetingTime.get()).intValue() * 60 * 1000)) { List<UltimateTargetManager.TargetingType> availableTypes; if (players.size() <= 1) { availableTypes = Arrays.asList(new UltimateTargetManager.TargetingType[] { NEAREST, RANDOM_STROLL, RANDOM_STROLL_NEAR_PLAYER }); } else { availableTypes = Arrays.asList(new UltimateTargetManager.TargetingType[] { NEAREST, FARTHEST, GROUP, RANDOM_STROLL, RANDOM_PLAYER }); }  UltimateTargetManager.TargetingType lastType = manager.randomizedType; do { manager.randomizedType = availableTypes.get(storm.m_217043_().m_188503_(availableTypes.size())); } while (manager.randomizedType == lastType); manager.randomizedLastSwitchTime = currentTime; UltimateTargetManager.LOGGER.info("RANDOMIZED: Targeting Type has been set to: " + manager.randomizedType); if (storm.m_217043_().m_216339_(1, 11) == 1 && storm.getPhase() >= 4 && ((Boolean)WitherStormModConfig.SERVER.randomlySpeedUpWithTargetChange.get()).booleanValue()) { manager.accelerate(); UltimateTargetManager.LOGGER.info("RANDOMIZED: The Wither Storm will be speeding up!!!"); }  }  assert manager.randomizedType != null; return manager.randomizedType.getPlayer(manager, storm, players, predicate); } } enum null { @Nullable public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate) { return null; }
/*      */      }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum DistractionReason
/*      */   {
/* 1045 */     FINISHED_CHASING,
/* 1046 */     FINISHED_CHASING_DELAYED,
/* 1047 */     TIRED_OF_CHASING,
/* 1048 */     FORCED;
/*      */   }
/*      */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstor\\ultimatetarget\UltimateTargetManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */