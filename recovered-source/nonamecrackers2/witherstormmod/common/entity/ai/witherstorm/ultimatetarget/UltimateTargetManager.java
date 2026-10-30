package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormFindUltimateTargetEvent;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModStructureTags;
import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UltimateTargetManager {
   private static final Logger LOGGER = LogManager.getLogger("witherstormmod/UltimateTargetManager");
   private final WitherStormEntity entity;
   @Nullable
   protected LivingEntity ultimateTarget;
   @Nullable
   protected Vec3 ultimateTargetO;
   @Nullable
   protected UUID targetOverride;
   @Nullable
   protected BlockPos blockTargetOverride;
   @Nullable
   protected BlockPos alternativeUltimateTarget;
   @Nullable
   protected BlockPos distractedPos;
   @Nullable
   protected BlockPos randomStrollPos;
   @Nullable
   protected ChunkPos center;
   protected boolean ultimateTargetStationary;
   protected int ticksSinceStationary;
   protected int runawayDiminishTicks;
   protected final int chunkBoundaryRadius;
   protected int runawayAttempts;
   protected boolean canCountRunawayAttempt;
   protected boolean canBeDistracted;
   protected boolean isDistracted;
   protected int ticksSinceDistracted;
   protected int canBeDistractedFor;
   protected int distractionWait;
   protected int tillShowHole;
   protected int tillRandomStroll;
   protected int cannotSeeTargetFor;
   @Nullable
   protected nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason distractionReason;
   protected int tiredOfChasingTicks;
   @Nullable
   protected UUID ignoredTarget;
   protected int ignoringTargetFor;
   protected int cannotReachTargetFor;
   protected int timeTillIgnoreTarget;
   @Nullable
   private UUID farthestPlayer;
   @Nullable
   private UUID randomPlayer;
   private long farthestLastSwitchTime;
   private long randomPlayerLastSwitchTime;
   @Nullable
   private nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType randomizedType;
   private long randomizedLastSwitchTime;

   public UltimateTargetManager(WitherStormEntity entity) {
      this.entity = entity;
      this.chunkBoundaryRadius = (Integer)WitherStormModConfig.SERVER.targetStationaryChunkRadius.get();
   }

   public void tick() {
      List<ServerPlayer> players = ((ServerLevel)this.entity.m_9236_())
         .m_6907_()
         .stream()
         .filter(p -> !p.m_20148_().equals(this.ignoredTarget))
         .collect(Collectors.toList());
      LivingEntity ultimateTarget = this.findUltimateTarget(players);
      if (ultimateTarget != this.ultimateTarget) {
         this.cannotReachTargetFor = 0;
         this.timeTillIgnoreTarget = 1200 + this.entity.m_217043_().m_188503_(600);
      }

      this.setUltimateTarget(ultimateTarget);
      if (ultimateTarget != null) {
         this.setAlternativeUltimateTarget(ultimateTarget.m_20183_());
         if ((Boolean)WitherStormModConfig.SERVER.ignoreUltimateTargetIfHidden.get()
            && !this.isDistracted()
            && players.size() > 1
            && this.ignoredTarget == null
            && !WorldUtil.canSeeOrIsNotInASmallArea(this.entity, ultimateTarget)
            && this.entity.m_20182_().m_82546_(ultimateTarget.m_20182_()).m_165924_() < 150.0) {
            this.cannotReachTargetFor++;
            if (this.cannotReachTargetFor > this.timeTillIgnoreTarget) {
               this.ignoredTarget = ultimateTarget.m_20148_();
               this.ignoringTargetFor = 12000 + this.entity.m_217043_().m_188503_(6000);
            }
         } else if (this.cannotReachTargetFor > 0) {
            this.cannotReachTargetFor--;
         }
      }

      if (this.ignoringTargetFor > 0) {
         this.ignoringTargetFor--;
         if (this.ignoringTargetFor == 0) {
            this.ignoredTarget = null;
         }
      }

      nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType targetingType = (nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType)WitherStormModConfig.SERVER
         .ultimateTargetingType
         .get();
      ServerPlayer randomStrollPlayer = nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType.NEAREST
         .getPlayer(this, this.entity, players, Predicate.not(Entity::m_5833_));
      boolean randomStrollNearPlayer = targetingType
            == nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType.RANDOM_STROLL_NEAR_PLAYER
         && randomStrollPlayer != null;
      if (targetingType == nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType.RANDOM_STROLL
         || randomStrollNearPlayer) {
         int maxRadius = (Integer)WitherStormModConfig.SERVER.maxRandomStrollTargetingTypeRadius.get();
         BlockPos alt = this.getAlternativeUltimateTarget();
         if (alt == null || this.entity.m_20182_().m_82546_(Vec3.m_82512_(alt)).m_165924_() < 100.0) {
            BlockPos newPos = null;

            for (int i = 0; i < 10; i++) {
               int x = this.entity.m_217043_().m_188503_(maxRadius * 2) - maxRadius;
               int z = this.entity.m_217043_().m_188503_(maxRadius * 2) - maxRadius;
               BlockPos entityPos = this.entity.m_20183_();
               if (randomStrollNearPlayer) {
                  entityPos = randomStrollPlayer.m_20183_();
               }

               BlockPos current = entityPos.m_7918_(x, 0, z);
               if ((
                     alt == null
                        || Math.sqrt(alt.m_123331_(current)) > maxRadius / 2.0
                           && this.entity.m_20182_().m_82546_(Vec3.m_82512_(current)).m_165924_() > maxRadius / 2.0
                  )
                  && this.entity.m_9236_().m_46739_(current)
                  && this.entity.m_9236_().m_6857_().m_61937_(current)) {
                  newPos = current;
                  break;
               }
            }

            if (newPos != null) {
               this.setAlternativeUltimateTarget(newPos);
            }
         }
      }

      for (ServerPlayer player : players) {
         for (ItemStack stack : player.m_150109_().f_35974_) {
            if (stack.m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS)
               && this.tillShowHole() <= 0
               && !this.entity.isBeingTornApart()
               && this.entity.getPhase() > 6) {
               this.tillShowHole = (Integer)WitherStormModConfig.SERVER.tillShouldShowHole.get() * 1200 + this.entity.m_217043_().m_188503_(4800);
            }
         }
      }

      Vec3 ultimateTargetPos = this.getUltimateTargetPos();
      if (ultimateTargetPos != null) {
         BlockPos blockPos = BlockPos.m_274446_(ultimateTargetPos);
         if (this.getCenter() == null) {
            this.setCenter(new ChunkPos(blockPos));
         }

         int distance = (int)(this.entity.m_20182_().m_82554_(ultimateTargetPos) * (Double)WitherStormModConfig.SERVER.distanceMultiplier.get());
         if ((Boolean)WitherStormModConfig.SERVER.usePhaseAsDistanceMultiplier.get()) {
            distance = (int)(distance * (this.entity.getPhase() * 0.2 + 1.0));
         }

         int stationaryTicks = (Integer)WitherStormModConfig.SERVER.targetStationaryMinutes.get() * 1200;
         int runawayTicks = stationaryTicks - (Integer)WitherStormModConfig.SERVER.targetRunawayMinutes.get() * 1200;
         int runawayAttemptsDiminish = (Integer)WitherStormModConfig.SERVER.minutesTillRunawayAttemptDiminish.get() * 1200;
         if (this.isDistracted()) {
            this.ticksSinceDistracted++;
            if (this.getTicksSinceDistracted() > this.canBeDistractedFor) {
               this.makeFocused();
            }

            if (this.distractionReason
                  != nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason.TIRED_OF_CHASING
               && this.entity.m_20182_().m_82554_(ultimateTargetPos) < this.entity.m_21133_(Attributes.f_22277_) * 2.5) {
               this.makeFocused();
            }

            if (this.distractionReason
                  == nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason.TIRED_OF_CHASING
               && ultimateTarget != null
               && this.ultimateTargetO != null) {
               double speed = this.ultimateTargetO.m_82546_(ultimateTarget.m_20182_()).m_165924_();
               if (speed >= 0.39) {
                  if (this.tiredOfChasingTicks < (Integer)WitherStormModConfig.SERVER.boatingForTooLongSeconds.get() * 20) {
                     this.tiredOfChasingTicks++;
                  }
               } else if (this.tiredOfChasingTicks > 0) {
                  this.tiredOfChasingTicks--;
                  if (this.tiredOfChasingTicks == 0) {
                     this.makeFocused();
                  }
               }
            }
         } else {
            if (this.ultimateTarget != null && WorldUtil.hasLineOfSight(this.entity, this.ultimateTarget)) {
               this.cannotSeeTargetFor = 0;
            } else {
               this.cannotSeeTargetFor++;
            }

            Vec3 pos = this.getUltimateTargetPos();
            if (!(Boolean)WitherStormModConfig.SERVER.randomStrollingWhenTargetHidden.get() || pos == null || !(pos.m_82554_(this.entity.m_20182_()) < 300.0)) {
               this.randomStrollPos = null;
               this.tillRandomStroll = 0;
            } else if (this.cannotSeeTarget()) {
               if (this.tillRandomStroll == 0) {
                  this.tillRandomStroll = this.randomStrollPos == null ? 600 : 1200 + this.entity.m_217043_().m_188503_(600);
               }
            } else {
               this.randomStrollPos = null;
               this.tillRandomStroll = 0;
            }

            if (this.isPosInChunkRadius(blockPos)) {
               if (this.ticksSinceStationary > Math.max(2400, stationaryTicks - distance)) {
                  this.ultimateTargetStationary = true;
               }

               if (this.ticksSinceStationary <= Math.max(2400, stationaryTicks)) {
                  this.ticksSinceStationary++;
                  if (this.ticksSinceStationary > (Integer)WitherStormModConfig.SERVER.targetRunawayAttemptMinutes.get() * 1200
                     && (Boolean)WitherStormModConfig.SERVER.targetRunawayAttempts.get()) {
                     this.canCountRunawayAttempt = true;
                  }
               }

               if (this.runawayDiminishTicks > runawayAttemptsDiminish) {
                  this.reduceRunawayAttempts();
                  this.runawayDiminishTicks = 0;
               } else {
                  this.runawayDiminishTicks++;
               }
            } else if (this.ticksSinceStationary <= Math.max(2400, runawayTicks - distance)) {
               if (this.ultimateTargetStationary) {
                  boolean flag1 = true;
                  CommandBlockEntity commandBlock = this.entity.getBowelsCommandBlock();
                  if (commandBlock != null && commandBlock.m_21223_() < commandBlock.m_21233_()) {
                     flag1 = false;
                  }

                  if (this.entity.getPhase() > 3 && flag1) {
                     boolean flag = (Boolean)WitherStormModConfig.SERVER.randomDistractionChances.get();
                     boolean shouldNotBeDistracted = flag && this.entity.m_217043_().m_188503_(30) == 1;
                     boolean shouldActuallyBeDistracted = flag && this.entity.m_217043_().m_188503_(10) == 1;
                     if ((this.canBeDistracted() || shouldActuallyBeDistracted) && !shouldNotBeDistracted) {
                        int minimumDistance = (Integer)WitherStormModConfig.SERVER.minimumDistractionDistance.get();
                        if (this.entity.m_20182_().m_82554_(ultimateTargetPos) < this.entity.m_21133_(Attributes.f_22277_) + minimumDistance
                           && minimumDistance != 0) {
                           this.setDistractionWait(
                              (Integer)WitherStormModConfig.SERVER.distractionWaitTime.get() * 1200 + this.entity.m_217043_().m_188503_(1200)
                           );
                        }

                        this.makeDistracted(
                           nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason.FINISHED_CHASING
                        );
                     }
                  }
               }

               this.ultimateTargetStationary = false;
               this.setCenter(new ChunkPos(blockPos));
               this.ticksSinceStationary = 0;
            } else {
               this.ticksSinceStationary--;
               this.ultimateTargetStationary = true;
            }

            if (this.isTargetStationary()
               && !this.canBeDistracted
               && this.entity.m_20182_().m_82554_(ultimateTargetPos)
                  < this.entity.m_21133_(Attributes.f_22277_) + ((Integer)WitherStormModConfig.SERVER.maximumDistractionDistance.get()).intValue()) {
               this.canBeDistracted = true;
            }

            if (this.getDistractionWait() > 0) {
               this.distractionWait--;
               if (this.distractionWait <= 0
                  && this.canBeDistracted()
                  && this.entity.m_20182_().m_82554_(ultimateTargetPos) >= this.entity.m_21133_(Attributes.f_22277_) + 50.0
                  && !this.ultimateTargetStationary) {
                  this.makeDistracted(
                     nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason.FINISHED_CHASING_DELAYED
                  );
               }
            }

            if ((Boolean)WitherStormModConfig.SERVER.boatingForTooLongDistractions.get()
               && this.isTargetStationary()
               && this.canBeDistracted()
               && ultimateTarget != null
               && this.ultimateTargetO != null) {
               double speed = this.ultimateTargetO.m_82546_(ultimateTarget.m_20182_()).m_165924_();
               if (speed >= 0.39) {
                  this.tiredOfChasingTicks++;
               } else if (this.tiredOfChasingTicks > 0) {
                  this.tiredOfChasingTicks--;
               }

               if (this.tiredOfChasingTicks > (Integer)WitherStormModConfig.SERVER.boatingForTooLongSeconds.get() * 20) {
                  this.makeDistracted(
                     nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason.TIRED_OF_CHASING
                  );
               }
            }
         }

         if (this.runawayAttempts >= (Integer)WitherStormModConfig.SERVER.targetRunawayAttemptsRequired.get()
            && (Boolean)WitherStormModConfig.SERVER.targetRunawayAttempts.get()
            && !this.isDistracted()) {
            this.accelerate();
            this.canCountRunawayAttempt = false;
            this.setRunawayAttempts(0);
         }
      }

      if (this.tillRandomStroll > 0) {
         this.tillRandomStroll--;
         if (this.tillRandomStroll == 0) {
            this.findAndSetRandomNearbyStrollPos();
         }
      }

      if (this.tillShowHole > 0) {
         this.tillShowHole--;
         if (this.tillShowHole == 0 && this.entity.getPhase() > 6) {
            this.entity.setShouldShowHole(true);
         }
      }

      if (ultimateTarget != null) {
         assert this.ultimateTarget != null;
         this.ultimateTargetO = this.ultimateTarget.m_20182_();
      }
   }

   @Nullable
   public LivingEntity findUltimateTarget(List<ServerPlayer> players) {
      LivingEntity override = null;
      Predicate<Entity> predicate = entityx -> !entityx.m_5833_();
      if ((Boolean)WitherStormModConfig.SERVER.amuletOverride.get()) {
         double d0 = -1.0;

         for (ServerPlayer player : players) {
            if (predicate.test(player)) {
               for (ItemStack stack : player.m_150109_().f_35974_) {
                  if (stack.m_150930_((Item)WitherStormModItems.AMULET.get())) {
                     double d1 = player.m_20280_(this.entity);
                     if (d1 < d0 || d0 == -1.0) {
                        d0 = player.m_20280_(this.entity);
                        override = player;
                     }
                  }
               }
            }
         }
      }

      ServerLevel level = (ServerLevel)this.entity.m_9236_();
      if (this.getTargetOverride() != null) {
         Entity found = level.m_8791_(this.getTargetOverride());
         if (found instanceof LivingEntity living && !found.equals(this.entity) && !(found instanceof WitherStormSegmentEntity)) {
            override = living;
         }
      }

      if ((Boolean)WitherStormModConfig.SERVER.witherStormsFollowBiggerStorms.get()) {
         double distance = -1.0;
         WitherStormEntity closest = null;

         for (Entity entity : level.m_8583_()) {
            if (entity instanceof WitherStormEntity storm
               && storm != this.entity
               && storm.m_20270_(this.entity) < 1000.0
               && !storm.isDeadOrPlayingDead()
               && storm.getConsumedEntities() > this.entity.getConsumedEntities()) {
               double dist = storm.m_20270_(this.entity);
               if (distance == -1.0 || dist < distance) {
                  distance = dist;
                  closest = storm;
               }
            }
         }

         if (closest != null) {
            override = closest;
         }
      }

      LivingEntity finalTarget = (LivingEntity)(override != null
         ? override
         : ((nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType)WitherStormModConfig.SERVER
               .ultimateTargetingType
               .get())
            .getPlayer(this, this.entity, players, predicate));
      WitherStormFindUltimateTargetEvent event = new WitherStormFindUltimateTargetEvent(this.entity, finalTarget);
      MinecraftForge.EVENT_BUS.post(event);
      return event.getOriginalUltimateTarget();
   }

   public void accelerate() {
      if (this.isDistracted()) {
         this.makeFocused();
      }

      this.ticksSinceStationary = (Integer)WitherStormModConfig.SERVER.targetStationaryMinutes.get() * 1200;
      this.ultimateTargetStationary = true;
   }

   public void deaccelerate() {
      this.ticksSinceStationary = 0;
      this.ultimateTargetStationary = false;
   }

   public void setUltimateTarget(@Nullable LivingEntity player) {
      this.ultimateTarget = player;
   }

   public void setAlternativeUltimateTarget(@Nullable BlockPos pos) {
      this.alternativeUltimateTarget = pos;
   }

   @Nullable
   public LivingEntity getUltimateTarget() {
      return this.ultimateTarget;
   }

   @Nullable
   public BlockPos getAlternativeUltimateTarget() {
      return this.alternativeUltimateTarget;
   }

   @Nullable
   public Vec3 getUltimateTargetPos() {
      if (this.blockTargetOverride != null) {
         return Vec3.m_82512_(this.blockTargetOverride);
      } else if (this.ultimateTarget != null) {
         return this.ultimateTarget.m_20182_();
      } else {
         return this.alternativeUltimateTarget != null ? Vec3.m_82512_(this.alternativeUltimateTarget) : null;
      }
   }

   @Nullable
   public ChunkPos getCenter() {
      return this.center;
   }

   public void setCenter(ChunkPos pos) {
      this.center = pos;
      if (this.canCountRunawayAttempt) {
         this.countRunawayAttempt();
         this.canCountRunawayAttempt = false;
      }
   }

   public boolean isPosInChunkRadius(BlockPos pos) {
      if (pos != null && this.center != null) {
         for (int x = -this.chunkBoundaryRadius; x <= this.chunkBoundaryRadius; x++) {
            for (int z = -this.chunkBoundaryRadius; z <= this.chunkBoundaryRadius; z++) {
               ChunkPos chunk = new ChunkPos(this.center.f_45578_ + x, this.center.f_45579_ + z);
               if (chunk.equals(new ChunkPos(pos))) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean isTargetStationary() {
      return this.ultimateTargetStationary;
   }

   public int targetStationaryTicks() {
      return this.ticksSinceStationary;
   }

   public void setTargetStationaryTicks(int amount) {
      this.ticksSinceStationary = amount;
   }

   public int getRunawayAttempts() {
      return this.runawayAttempts;
   }

   public void setRunawayAttempts(int attempts) {
      this.runawayAttempts = attempts;
   }

   public void countRunawayAttempt() {
      this.runawayAttempts++;
   }

   public void reduceRunawayAttempts() {
      double amount = this.runawayAttempts - 1;
      if (amount >= 0.0) {
         this.runawayAttempts--;
      }
   }

   public int getRunawayDiminishTicks() {
      return this.runawayDiminishTicks;
   }

   @Nullable
   public BlockPos findDistractPos() {
      ServerLevel level = (ServerLevel)this.entity.m_9236_();
      BlockPos pos = level.m_215011_(WitherStormModStructureTags.WITHER_STORM_DISTRACTABLE, this.entity.m_20183_(), 100, false);
      if (pos == null) {
         int times = 10;

         for (int i = 0; i < times && pos == null; i++) {
            int multiplier = (Integer)WitherStormModConfig.SERVER.searchRangeMultiplier.get();
            double range = this.entity.m_21133_(Attributes.f_22277_) * 2.0 * multiplier;
            BlockPos current = BlockPos.m_274561_(this.entity.m_20208_(range), this.entity.m_20186_(), this.entity.m_20262_(range));
            if (this.entity.m_9236_().m_8055_(current).m_60713_(Blocks.f_50016_)
               && this.entity.distanceTo(current) > 2000.0 * multiplier
               && Math.sqrt(current.m_203193_(Objects.requireNonNull(this.getUltimateTargetPos()))) > 500.0) {
               pos = current;
            }
         }
      }

      return pos;
   }

   public boolean canBeDistracted() {
      return this.canBeDistracted && (Boolean)WitherStormModConfig.SERVER.targettingDistractionsEnabled.get();
   }

   public void setCanBeDistracted(boolean distractable) {
      this.canBeDistracted = distractable;
   }

   public boolean isDistracted() {
      return this.isDistracted;
   }

   public void markDistracted(boolean distracted) {
      this.isDistracted = distracted;
   }

   public int getTicksSinceDistracted() {
      return this.ticksSinceDistracted;
   }

   public void setTicksSinceDistracted(int ticks) {
      this.ticksSinceDistracted = ticks;
   }

   public void makeDistracted(nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason reason) {
      if ((Boolean)WitherStormModConfig.SERVER.targettingDistractionsEnabled.get() && this.distractionWait <= 0) {
         this.distractedPos = this.findDistractPos();
         if (this.distractedPos != null) {
            this.isDistracted = true;
            this.canBeDistractedFor = Math.max(
               4800,
               (Integer)WitherStormModConfig.SERVER.distractionTimeMinutes.get() * 1200
                  + this.entity.m_217043_().m_188503_(12000)
                  - (int)this.entity.distanceTo(this.distractedPos) * 2
            );
            this.canBeDistracted = false;
            this.distractionReason = reason;
         }
      }
   }

   public void makeFocused() {
      this.isDistracted = false;
      this.ticksSinceDistracted = 0;
      this.canBeDistracted = false;
      this.distractedPos = null;
      this.canBeDistractedFor = 0;
      this.distractionWait = 0;
      this.distractionReason = null;
   }

   @Nullable
   public BlockPos getDistractedPos() {
      return this.distractedPos;
   }

   public void setDistractedPos(@Nullable BlockPos pos) {
      this.distractedPos = pos;
   }

   public int getDistractedTickTime() {
      return this.canBeDistractedFor;
   }

   public void setDistractedTickTime(int ticks) {
      this.canBeDistractedFor = ticks;
   }

   public int getDistractionWait() {
      return this.distractionWait;
   }

   public void setDistractionWait(int ticks) {
      this.distractionWait = ticks;
   }

   public int tillShowHole() {
      return this.tillShowHole;
   }

   public void setTillShowHole(int ticks) {
      this.tillShowHole = ticks;
   }

   public void setTargetOverride(@Nullable UUID uuid) {
      this.targetOverride = uuid;
   }

   @Nullable
   public UUID getTargetOverride() {
      return this.targetOverride;
   }

   public void setBlockTargetOverride(@Nullable BlockPos pos) {
      this.blockTargetOverride = pos;
   }

   @Nullable
   public BlockPos getBlockTargetOverride() {
      return this.blockTargetOverride;
   }

   public void findAndSetRandomNearbyStrollPos() {
      Vec3 pos = this.getUltimateTargetPos();
      if (pos != null) {
         BlockPos newPos = null;

         for (int i = 0; i < 10; i++) {
            float angle = this.entity.m_217043_().m_188501_() * (float) (Math.PI * 2);
            int x = (int)(Mth.m_14089_(angle) * (this.entity.m_217043_().m_188503_(50) + 150.0F));
            int z = (int)(Mth.m_14031_(angle) * (this.entity.m_217043_().m_188503_(50) + 150.0F));
            BlockPos current = new BlockPos(x + Mth.m_14107_(pos.f_82479_), Mth.m_14107_(pos.f_82480_), z + Mth.m_14107_(pos.f_82481_));
            if (this.randomStrollPos == null
               || Math.sqrt(this.randomStrollPos.m_123331_(current)) > 100.0 && this.entity.m_20182_().m_82546_(Vec3.m_82512_(current)).m_165924_() > 100.0) {
               newPos = current;
               break;
            }
         }

         this.randomStrollPos = newPos;
      } else {
         this.randomStrollPos = null;
      }
   }

   @Nullable
   public BlockPos getRandomStrollPos() {
      return this.randomStrollPos;
   }

   public boolean isRandomStrolling() {
      return this.randomStrollPos != null;
   }

   public boolean cannotSeeTarget() {
      return this.cannotSeeTargetFor > 600;
   }

   public void save(CompoundTag compound) {
      if (this.getAlternativeUltimateTarget() != null) {
         compound.m_128365_("AlternativeUltimateTarget", NbtUtils.m_129224_(this.getAlternativeUltimateTarget()));
      }

      if (this.getCenter() != null) {
         compound.m_128365_("UltimateTargetChunkPos", WitherStormModNBTUtil.writeChunkPos(this.getCenter()));
      }

      compound.m_128405_("TargetStationaryTicks", this.targetStationaryTicks());
      compound.m_128405_("TargetRunawayAttempts", this.getRunawayAttempts());
      if (this.getTargetOverride() != null) {
         compound.m_128362_("TargetOverride", this.getTargetOverride());
      }

      if (this.getBlockTargetOverride() != null) {
         compound.m_128365_("BlockTargetOverride", NbtUtils.m_129224_(this.getBlockTargetOverride()));
      }

      CompoundTag ultimateTargetDistractions = new CompoundTag();
      if (this.getDistractedPos() != null) {
         ultimateTargetDistractions.m_128365_("DistractedPos", NbtUtils.m_129224_(this.getDistractedPos()));
      }

      ultimateTargetDistractions.m_128379_("CanBeDistracted", this.canBeDistracted());
      ultimateTargetDistractions.m_128379_("IsDistracted", this.isDistracted());
      ultimateTargetDistractions.m_128405_("TicksSinceDistracted", this.getTicksSinceDistracted());
      ultimateTargetDistractions.m_128405_("CanBeDistractedFor", this.getDistractedTickTime());
      ultimateTargetDistractions.m_128405_("DistractionWait", this.getDistractionWait());
      if (this.distractionReason != null) {
         ultimateTargetDistractions.m_128405_("DistractionReason", this.distractionReason.ordinal());
      }

      ultimateTargetDistractions.m_128405_("TiredOfChasingTicks", this.tiredOfChasingTicks);
      compound.m_128365_("UltimateTargetDistraction", ultimateTargetDistractions);
      if (this.randomStrollPos != null) {
         compound.m_128365_("RandomStrollPos", NbtUtils.m_129224_(this.randomStrollPos));
      }

      compound.m_128405_("RandomStrollTimer", this.tillRandomStroll);
      if (this.ignoredTarget != null) {
         compound.m_128362_("IgnoredTarget", this.ignoredTarget);
      }

      compound.m_128405_("IgnoringTargetFor", this.ignoringTargetFor);
      compound.m_128405_("CannotReachTargetFor", this.cannotReachTargetFor);
      compound.m_128405_("TimeTillIgnoreTarget", this.timeTillIgnoreTarget);
      compound.m_128405_("CannotSeeTargetFor", this.cannotSeeTargetFor);
   }

   public void read(CompoundTag compound) {
      if (compound.m_128441_("AlternativeUltimateTarget")) {
         this.setAlternativeUltimateTarget(NbtUtils.m_129239_(compound.m_128469_("AlternativeUltimateTarget")));
      }

      if (compound.m_128441_("UltimateTargetChunkPos")) {
         this.center = WitherStormModNBTUtil.readChunkPos(compound.m_128469_("UltimateTargetChunkPos"));
      }

      this.setTargetStationaryTicks(compound.m_128451_("TargetStationaryTicks"));
      this.setRunawayAttempts(compound.m_128451_("TargetRunawayAttempts"));
      if (compound.m_128441_("TargetOverride")) {
         this.setTargetOverride(compound.m_128342_("TargetOverride"));
      }

      if (compound.m_128441_("BlockTargetOverride")) {
         this.setBlockTargetOverride(NbtUtils.m_129239_(compound.m_128469_("BlockTargetOverride")));
      }

      CompoundTag ultimateTargetDistractions = compound.m_128469_("UltimateTargetDistraction");
      if (ultimateTargetDistractions.m_128441_("DistractedPos")) {
         this.setDistractedPos(NbtUtils.m_129239_(ultimateTargetDistractions.m_128469_("DistractedPos")));
      }

      this.setCanBeDistracted(ultimateTargetDistractions.m_128471_("CanBeDistracted"));
      this.markDistracted(ultimateTargetDistractions.m_128471_("IsDistracted"));
      this.setTicksSinceDistracted(ultimateTargetDistractions.m_128451_("TicksSinceDistracted"));
      this.setDistractedTickTime(ultimateTargetDistractions.m_128451_("CanBeDistractedFor"));
      this.setDistractionWait(ultimateTargetDistractions.m_128451_("DistractionWait"));
      if (ultimateTargetDistractions.m_128425_("DistractionReason", 3)) {
         int ordinal = ultimateTargetDistractions.m_128451_("DistractionReason");
         if (ordinal >= 0
            && ordinal < nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason.values().length) {
            this.distractionReason = nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason.values()[ordinal];
         }
      }

      this.tiredOfChasingTicks = ultimateTargetDistractions.m_128451_("TiredOfChasingTicks");
      if (compound.m_128441_("RandomStrollPos")) {
         this.randomStrollPos = NbtUtils.m_129239_(compound.m_128469_("RandomStrollPos"));
      }

      this.tillRandomStroll = compound.m_128451_("RandomStrollTimer");
      if (compound.m_128403_("IgnoredTarget")) {
         this.ignoredTarget = compound.m_128342_("IgnoredTarget");
      }

      this.ignoringTargetFor = compound.m_128451_("IgnoringTargetFor");
      this.cannotReachTargetFor = compound.m_128451_("CannotReachTargetFor");
      this.timeTillIgnoreTarget = compound.m_128451_("TimeTillIgnoreTarget");
      this.cannotSeeTargetFor = compound.m_128451_("CannotSeeTargetFor");
   }

   public enum DistractionReason {
      FINISHED_CHASING,
      FINISHED_CHASING_DELAYED,
      TIRED_OF_CHASING,
      FORCED;
   }

   public enum TargetingType {
      NEAREST {
         @Nullable
         @Override
         public ServerPlayer getPlayer(
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager manager,
            WitherStormEntity storm,
            List<ServerPlayer> players,
            Predicate<Entity> predicate
         ) {
            double d0 = -1.0;
            ServerPlayer closest = null;

            for (ServerPlayer player : players) {
               if (predicate.test(player)) {
                  double d1 = player.m_20280_(storm);
                  if (d1 < d0 || d0 == -1.0) {
                     d0 = player.m_20280_(storm);
                     closest = player;
                  }
               }
            }

            return closest;
         }
      },
      FARTHEST {
         @Nullable
         @Override
         public ServerPlayer getPlayer(
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager manager,
            WitherStormEntity storm,
            List<ServerPlayer> players,
            Predicate<Entity> predicate
         ) {
            long currentTime = System.currentTimeMillis();
            long timeThreshold = (Integer)WitherStormModConfig.SERVER.farthestTargetingTime.get() * 60 * 1000;
            ServerPlayer farthestPlayer = manager.farthestPlayer != null ? (ServerPlayer)storm.m_9236_().m_46003_(manager.farthestPlayer) : null;
            if (currentTime - manager.farthestLastSwitchTime >= timeThreshold || farthestPlayer == null) {
               double d0 = -1.0;

               for (ServerPlayer player : players) {
                  if (predicate.test(player)) {
                     double d1 = player.m_20280_(storm);
                     if (d1 > d0 || d0 == -1.0) {
                        d0 = player.m_20280_(storm);
                        manager.farthestPlayer = player.m_20148_();
                        farthestPlayer = player;
                        nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.LOGGER
                           .info(
                              "FARTHEST: Farthest Player was "
                                 + player
                                 + ", Going to them for "
                                 + WitherStormModConfig.SERVER.farthestTargetingTime.get()
                                 + " minute(s)"
                           );
                     }
                  }
               }

               manager.farthestLastSwitchTime = currentTime;
            }

            return farthestPlayer;
         }
      },
      GROUP {
         @Nullable
         @Override
         public ServerPlayer getPlayer(
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager manager,
            WitherStormEntity storm,
            List<ServerPlayer> players,
            Predicate<Entity> predicate
         ) {
            double size = -1.0;
            ServerPlayer toTarget = null;

            for (ServerPlayer player : players) {
               if (predicate.test(player)) {
                  AABB box = player.m_20191_().m_82400_(20.0);
                  List<ServerPlayer> nearby = storm.m_9236_().m_6443_(ServerPlayer.class, box, EntitySelector.f_20403_.and(EntitySelector.f_20408_));
                  if (nearby.size() > size) {
                     size = nearby.size();
                     toTarget = player;
                  }
               }
            }

            return toTarget;
         }
      },
      NONE {
         @Nullable
         @Override
         public ServerPlayer getPlayer(
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager manager,
            WitherStormEntity storm,
            List<ServerPlayer> players,
            Predicate<Entity> predicate
         ) {
            return null;
         }
      },
      RANDOM_STROLL {
         @Nullable
         @Override
         public ServerPlayer getPlayer(
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager manager,
            WitherStormEntity storm,
            List<ServerPlayer> players,
            Predicate<Entity> predicate
         ) {
            return null;
         }
      },
      RANDOM_PLAYER {
         @Nullable
         @Override
         public ServerPlayer getPlayer(
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager manager,
            WitherStormEntity storm,
            List<ServerPlayer> players,
            Predicate<Entity> predicate
         ) {
            long currentTime = System.currentTimeMillis();
            long timeThreshold = 300000L;
            ServerPlayer randomPlayer = manager.randomPlayer != null ? (ServerPlayer)storm.m_9236_().m_46003_(manager.randomPlayer) : null;
            if (currentTime - manager.randomPlayerLastSwitchTime >= timeThreshold || randomPlayer == null) {
               Random random = new Random();
               List<ServerPlayer> survivalPlayers = players.stream().filter(player -> player.f_8941_.m_9294_()).filter(predicate).toList();
               if (!survivalPlayers.isEmpty()) {
                  int randomizer = random.nextInt(survivalPlayers.size());
                  randomPlayer = survivalPlayers.get(randomizer);
                  manager.randomPlayer = randomPlayer.m_20148_();
                  nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.LOGGER
                     .info("RANDOM_PLAYER: Chose a player: " + randomPlayer + ", Going to them for 5 minutes");
               } else {
                  ServerPlayer nearestPlayer = NEAREST.getPlayer(manager, storm, players, predicate);
                  if (nearestPlayer != null) {
                     manager.randomPlayer = nearestPlayer.m_20148_();
                     randomPlayer = nearestPlayer;
                     nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.LOGGER
                        .info("RANDOM_PLAYER: Couldn't find a valid player in Survival, changing to NEAREST for 5 minutes");
                  }
               }

               manager.randomPlayerLastSwitchTime = currentTime;
            }

            return randomPlayer;
         }
      },
      RANDOMIZED {
         @Nullable
         @Override
         public ServerPlayer getPlayer(
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager manager,
            WitherStormEntity storm,
            List<ServerPlayer> players,
            Predicate<Entity> predicate
         ) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - manager.randomizedLastSwitchTime >= (Integer)WitherStormModConfig.SERVER.randomizedTargetingTime.get() * 60 * 1000) {
               List<nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType> availableTypes;
               if (players.size() <= 1) {
                  availableTypes = Arrays.asList(NEAREST, RANDOM_STROLL, RANDOM_STROLL_NEAR_PLAYER);
               } else {
                  availableTypes = Arrays.asList(NEAREST, FARTHEST, GROUP, RANDOM_STROLL, RANDOM_PLAYER);
               }

               nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.TargetingType lastType = manager.randomizedType;

               do {
                  manager.randomizedType = availableTypes.get(storm.m_217043_().m_188503_(availableTypes.size()));
               } while (manager.randomizedType == lastType);

               manager.randomizedLastSwitchTime = currentTime;
               nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.LOGGER
                  .info("RANDOMIZED: Targeting Type has been set to: " + manager.randomizedType);
               if (storm.m_217043_().m_216339_(1, 11) == 1
                  && storm.getPhase() >= 4
                  && (Boolean)WitherStormModConfig.SERVER.randomlySpeedUpWithTargetChange.get()) {
                  manager.accelerate();
                  nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.LOGGER
                     .info("RANDOMIZED: The Wither Storm will be speeding up!!!");
               }
            }

            assert manager.randomizedType != null;
            return manager.randomizedType.getPlayer(manager, storm, players, predicate);
         }
      },
      RANDOM_STROLL_NEAR_PLAYER {
         @Nullable
         @Override
         public ServerPlayer getPlayer(
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager manager,
            WitherStormEntity storm,
            List<ServerPlayer> players,
            Predicate<Entity> predicate
         ) {
            return null;
         }
      };

      @Nullable
      public abstract ServerPlayer getPlayer(
         nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager var1,
         WitherStormEntity var2,
         List<ServerPlayer> var3,
         Predicate<Entity> var4
      );
   }
}
