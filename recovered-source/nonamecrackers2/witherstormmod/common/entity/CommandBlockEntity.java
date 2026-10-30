package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.BossEvent.BossBarColor;
import net.minecraft.world.BossEvent.BossBarOverlay;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent.Context;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.ai.commandblock.BowelsBossFightStages;
import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightManager;
import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightPhase;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.init.WitherStormModToolActions;
import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
import nonamecrackers2.witherstormmod.common.util.StructureAnimationHelper;
import nonamecrackers2.witherstormmod.common.util.TentacleOffsets;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.jetbrains.annotations.NotNull;

public class CommandBlockEntity extends LivingEntity implements EntitySyncableData, BossThemeEntity {
   private static final EntityDataAccessor<nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State> STATE = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.class, WitherStormModDataSerializers.STATE_ENUM
   );
   private static final EntityDataAccessor<nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode> MODE = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.class, WitherStormModDataSerializers.MODE_ENUM
   );
   private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.class, EntityDataSerializers.f_135041_
   );
   private static final EntityDataAccessor<Integer> PHASE_KEY = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.class, EntityDataSerializers.f_135028_
   );
   private static final Predicate<LivingEntity> SEARCHABLE_PLAYER_SELECTOR = living -> living instanceof Player player
      && player.m_6084_()
      && !player.m_150110_().f_35934_
      && !player.m_7500_()
      && !player.m_5833_()
      && player.m_6097_();
   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> IDLE_BOWELS_MOBS = SimpleWeightedRandomList.m_146263_()
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 10)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), 10)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 3)
      .m_146270_();
   public static final int HIT_GLARE_TIME = 60;
   private int modeAnim;
   private int modeAnimO;
   private int stateTicks;
   @Nullable
   private Player toLure;
   private final List<StructureAnimationHelper> ribStructure = new ArrayList<>();
   private final nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.TentacleManager tentacleStructure;
   private float protectionYOffset;
   private float protectionYOffsetO;
   @Nullable
   private WitherStormEntity owner;
   private final BossfightManager<nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity> bossfightManager;
   private final ServerBossEvent bossInfo = new ServerBossEvent(this.m_5446_(), BossBarColor.WHITE, BossBarOverlay.PROGRESS);
   private final List<ServerPlayer> outsideBossBarViewers = Lists.newArrayList();
   private final List<ServerPlayer> tracking = Lists.newArrayList();
   @Nullable
   public BlockClusterEntity podiumCluster;
   @Nullable
   public UUID podiumClusterUUID;
   private int specialDeathTime;
   @Nullable
   public LivingEntity killer;
   private int hitGlareTime;

   public CommandBlockEntity(EntityType<? extends nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity> type, Level world) {
      super(type, world);
      this.createStructureHelpers();
      this.tentacleStructure = new nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.TentacleManager(
         this,
         6,
         new TentacleOffsets[]{
            new TentacleOffsets(2.0, -2.0, 3.0, 1.45F, 1.0F, 40.0F, -70.0F),
            new TentacleOffsets(0.0, -2.0, 4.0, 1.4F, 1.0F, 35.0F, -90.0F),
            new TentacleOffsets(-2.0, -2.0, 3.0, 1.45F, 1.0F, 40.0F, -110.0F),
            new TentacleOffsets(2.0, -2.0, -3.0, 1.45F, 1.0F, 40.0F, 70.0F),
            new TentacleOffsets(0.0, -2.0, -4.0, 1.4F, 1.0F, 35.0F, 90.0F),
            new TentacleOffsets(-2.0, -2.0, -3.0, 1.45F, 1.0F, 40.0F, 110.0F)
         }
      );
      this.bossfightManager = new BossfightManager(BowelsBossFightStages.IDLE, this)
         .addPhase(1, BowelsBossFightStages.HIT)
         .addPhase(2, BowelsBossFightStages.MOVE_PODIUM)
         .addPhase(3, BowelsBossFightStages.WAIT)
         .addPhase(4, BowelsBossFightStages.MOB_WAVE_1)
         .addPhase(5, BowelsBossFightStages.IDLE)
         .addPhase(6, BowelsBossFightStages.HIT)
         .addPhase(7, BowelsBossFightStages.MOVE_PODIUM)
         .addPhase(8, BowelsBossFightStages.WAIT)
         .addPhase(9, BowelsBossFightStages.MOB_WAVE_2)
         .addPhase(10, BowelsBossFightStages.PROTECT_IDLE)
         .addPhase(11, BowelsBossFightStages.IDLE)
         .addPhase(12, BowelsBossFightStages.HIT)
         .addPhase(13, BowelsBossFightStages.MOVE_PODIUM)
         .addPhase(14, BowelsBossFightStages.MOB_WAVE_3)
         .addPhase(15, BowelsBossFightStages.PROTECT_IDLE)
         .addPhase(16, BowelsBossFightStages.IDLE)
         .addPhase(17, BowelsBossFightStages.DEATH)
         .addPhase(18, BowelsBossFightStages.IDLE);
   }

   public CommandBlockEntity(Level world, WitherStormEntity owner, double x, double y, double z) {
      this((EntityType<? extends nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity>)WitherStormModEntityTypes.COMMAND_BLOCK.get(), world);
      this.m_6034_(x, y, z);
      this.setState(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.PLAYING_DEAD);
      this.setMode(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode.RIBS);
      this.setOwner(owner);
      this.setOwnerUUID(owner.m_20148_());
      this.m_146922_(owner.m_146908_());
      this.m_5618_(owner.f_20883_);
      this.m_5616_(owner.m_6080_());
   }

   public static Builder createAttributes() {
      return LivingEntity.m_21183_()
         .m_22268_(Attributes.f_22276_, 64.0)
         .m_22268_(Attributes.f_22284_, 32.0)
         .m_22268_(Attributes.f_22285_, 32.0)
         .m_22268_(Attributes.f_22278_, 1024.0)
         .m_22268_(Attributes.f_22277_, 32.0);
   }

   private void createStructureHelpers() {
      this.ribStructure.add(new StructureAnimationHelper());
      this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0F, 180.0F));
      this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0F, 145.0F));
      this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0F, 35.0F));
      this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0F, -35.0F));
      this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0F, 215.0F));
   }

   public void m_8119_() {
      if (!this.m_9236_().f_46443_) {
         this.f_19804_.m_135381_(PHASE_KEY, this.getBossfightManager().getCurrentPhaseIndex());
         int interval = (int)(this.m_21223_() / this.m_21233_() * 80.0F);
         int secondaryInterval = (int)(this.m_21223_() / this.m_21233_() * 16.0F);
         if (!this.tracking.isEmpty() && this.m_21223_() < this.m_21233_() && interval > 0 && this.f_19797_ % interval == 0) {
            this.dropClusterFromCeiling();
         }

         if (!this.tracking.isEmpty() && this.m_21223_() < this.m_21233_() && secondaryInterval > 0 && this.f_19797_ % secondaryInterval == 0) {
            this.dropAdditionalClustersFromCeiling();
         }

         int mobSpawnerTimer = 100;
         if (WorldUtil.areaLoaded(this.m_9236_(), this.m_20183_(), 8)
            && this.getState().equals(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.BOSSFIGHT)
            && this.m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())
            && this.f_19797_ % mobSpawnerTimer == 0
            && this.f_19796_.m_188500_() <= 0.25) {
            this.summonRandomBowelsMobNearPlayer(8, 16, IDLE_BOWELS_MOBS);
         }
      }

      super.m_8119_();
      this.findOwner();
      this.getState().tick(this);
      this.modeAnimO = this.modeAnim;
      this.protectionYOffsetO = this.protectionYOffset;
      this.getStructures().forEach(StructureAnimationHelper::tick);
      if (!this.m_9236_().f_46443_) {
         this.tentacleStructure.findTentacles((ServerLevel)this.m_9236_());
         this.bossInfo.m_142711_(this.m_21223_() / this.m_21233_());

         for (ServerPlayer player : this.outsideBossBarViewers) {
            if (!this.m_21224_() && this.m_21223_() < this.m_21233_()) {
               this.bossInfo.m_6543_(player);
            } else {
               this.bossInfo.m_6539_(player);
            }
         }

         if (this.getOwner() == null || this.getOwner().getBowelsCommandBlock() != this) {
            for (ServerPlayer player : this.outsideBossBarViewers) {
               this.bossInfo.m_6539_(player);
            }

            this.outsideBossBarViewers.clear();
         }
      }

      if (this.hitGlareTime > 0) {
         this.hitGlareTime--;
      }
   }

   protected void m_8097_() {
      super.m_8097_();
      this.f_19804_.m_135372_(STATE, nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.IDLE);
      this.f_19804_.m_135372_(MODE, nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode.NONE);
      this.f_19804_.m_135372_(OWNER_UUID, Optional.empty());
      this.f_19804_.m_135372_(PHASE_KEY, 0);
   }

   public void m_7380_(@NotNull CompoundTag compound) {
      super.m_7380_(compound);
      compound.m_128405_("Mode", this.getMode().ordinal());
      compound.m_128405_("State", this.getState().ordinal());
      compound.m_128405_("StateTicks", this.stateTicks);
      compound.m_128405_("ModeAnim", this.modeAnim);
      compound.m_128350_("YOffset", this.protectionYOffset);
      compound.m_128350_("YBodyRot", this.f_20883_);
      compound.m_128365_("Structures", this.writeStructures());
      if (this.getOwnerUUID() != null) {
         compound.m_128362_("OwnerUUID", this.getOwnerUUID());
      }

      this.tentacleStructure.addSaveData(compound);
      if (this.podiumCluster != null && this.podiumCluster.m_6084_()) {
         compound.m_128362_("PodiumCluster", this.podiumCluster.m_20148_());
      }

      compound.m_128365_("BossfightManager", this.bossfightManager.write());
   }

   public void m_7378_(@NotNull CompoundTag compound) {
      super.m_7378_(compound);
      this.protectionYOffsetO = this.protectionYOffset;
      int mode = compound.m_128451_("Mode");
      if (mode >= 0 && mode < nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode.values().length) {
         this.setMode(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode.values()[mode]);
      }

      int state = compound.m_128451_("State");
      if (state >= 0 && state < nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.values().length) {
         this.setState(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.values()[state]);
      }

      this.stateTicks = compound.m_128451_("StateTicks");
      this.modeAnim = compound.m_128451_("ModeAnim");
      this.modeAnimO = this.modeAnim;
      this.protectionYOffset = compound.m_128457_("YOffset");
      this.m_5618_(compound.m_128457_("YBodyRot"));
      this.readStructures(compound.m_128437_("Structures", 10));
      if (compound.m_128441_("OwnerUUID")) {
         this.setOwnerUUID(compound.m_128342_("OwnerUUID"));
      }

      this.tentacleStructure.readSaveData(compound);
      if (compound.m_128441_("PodiumCluster")) {
         this.podiumClusterUUID = compound.m_128342_("PodiumCluster");
      }

      if (compound.m_128441_("BossfightManager")) {
         this.bossfightManager.read(compound.m_128469_("BossfightManager"));
      }
   }

   public nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode getMode() {
      return (nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode)this.f_19804_.m_135370_(MODE);
   }

   public void setMode(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode mode) {
      this.f_19804_.m_135381_(MODE, mode);
   }

   public nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State getState() {
      return (nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State)this.f_19804_.m_135370_(STATE);
   }

   public void setState(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State state) {
      this.f_19804_.m_135381_(STATE, state);
      this.initState();
   }

   private void initState() {
      this.getState().init(this);
   }

   public void nextState() {
      if (this.getState().ordinal() + 1 < nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.values().length) {
         this.setState(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.values()[this.getState().ordinal() + 1]);
      }
   }

   public void setLuringPlayer(@Nullable Player player) {
      this.toLure = player;
   }

   @Nullable
   public Player getLuringPlayer() {
      return this.toLure;
   }

   public void m_7350_(@NotNull EntityDataAccessor<?> parameter) {
      super.m_7350_(parameter);
      if (parameter.equals(STATE)) {
         this.initState();
      }

      if (parameter.equals(MODE)) {
         this.getMode().init(this, this.getState());
      }
   }

   public BlockState getBlockState() {
      return (BlockState)Blocks.f_50272_.m_49966_().m_61124_(BlockStateProperties.f_61372_, this.m_6350_());
   }

   public int m_213860_() {
      return 10;
   }

   @NotNull
   public AABB m_6921_() {
      return this.m_20191_().m_82400_(20.0);
   }

   public void m_147240_(double strength, double x, double z) {
   }

   public boolean m_6128_() {
      return true;
   }

   public boolean m_6469_(DamageSource source, float amount) {
      if (source.m_269533_(DamageTypeTags.f_268738_)) {
         return super.m_6469_(source, amount);
      }

      boolean flag = false;
      if (source.m_7640_() instanceof LivingEntity entity) {
         ItemStack stack = entity.m_21205_();
         if (stack.m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS) || stack.canPerformAction(WitherStormModToolActions.COMMAND_BLOCK_DAMAGE)) {
            this.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_HIT.get(), 4.0F, 1.0F);
            if (this.isVulnerable(source)) {
               boolean hurt = false;
               float health = this.m_21223_() - this.m_21233_() / 4.0F;
               if (health <= 0.0F) {
                  hurt = super.m_6469_(source, Float.MAX_VALUE);
               } else {
                  this.m_21153_(health);
                  hurt = true;
               }

               if (!this.m_9236_().f_46443_ && hurt && !this.m_21224_()) {
                  this.getBossfightManager().goToNextPhase();
                  WitherStormEntity owner = this.getOwner();
                  if (owner != null && owner.m_6084_()) {
                     owner.getSegmentsManager().ifPresent(manager -> {
                        for (WitherStormSegmentEntity segment : manager.getSegments()) {
                           if (segment != null && segment.m_6084_()) {
                              segment.getTrackedEntities().clearAndMakeAllFall();

                              for (int ix = 0; ix < segment.getTotalHeads(); ix++) {
                                 if (this.f_19796_.m_188501_() > 0.6F) {
                                    segment.getHeadManager().getHead(ix).hurt(null, segment.getHeadManager().getHeadInjuryTime());
                                 }
                              }
                           }
                        }
                     });
                     owner.getTrackedEntities().clearAndMakeAllFall();

                     for (int i = 0; i < owner.getTotalHeads(); i++) {
                        if (this.f_19796_.m_188501_() > 0.6F) {
                           owner.getHeadManager().getHead(i).hurt(null, owner.getHeadManager().getHeadInjuryTime());
                        }
                     }

                     this.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DAMAGE.get(), 16.0F, 1.0F);
                     this.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_CRACKS.get(), 16.0F, 1.0F);
                  }
               }

               if (this.m_9236_().f_46443_) {
                  for (int i = 0; i < 100; i++) {
                     double x = this.f_19796_.m_188501_() - 0.5F;
                     double y = this.f_19796_.m_188501_() - 0.5F;
                     double z = this.f_19796_.m_188501_() - 0.5F;
                     Vec3 pos = this.m_20191_().m_82399_();
                     this.m_9236_()
                        .m_7107_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), pos.f_82479_, pos.f_82480_, pos.f_82481_, x, y, z);
                  }

                  if (health > 0.0F) {
                     this.hitGlareTime = 60;
                  }
               }

               return hurt;
            }

            if (this.getState() != nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.BOSSFIGHT) {
               entity.m_147240_(1.0, this.m_20185_() - entity.m_20185_(), this.m_20189_() - entity.m_20189_());
            }

            flag = true;
         }
      }

      if ((source.m_269533_(DamageTypeTags.f_268415_) || source.m_269533_(DamageTypeTags.f_268415_) || flag)
         && this.getState().equals(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.PLAYING_DEAD)) {
         this.setState(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.REACTIVATING);
         return false;
      } else {
         return false;
      }
   }

   public boolean isVulnerable(DamageSource source) {
      BossfightPhase<nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity> currentPhase = this.getCurrentPhase();
      return this.getState() == nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.BOSSFIGHT
         && !this.m_6673_(source)
         && currentPhase.equals(BowelsBossFightStages.IDLE)
         && !currentPhase.equals(BowelsBossFightStages.PROTECT_IDLE);
   }

   @NotNull
   public Iterable<ItemStack> m_6168_() {
      return Collections.emptyList();
   }

   @NotNull
   public ItemStack m_6844_(@NotNull EquipmentSlot slot) {
      return ItemStack.f_41583_;
   }

   public void m_8061_(@NotNull EquipmentSlot slot, @NotNull ItemStack stack) {
   }

   @NotNull
   public HumanoidArm m_5737_() {
      return HumanoidArm.RIGHT;
   }

   public boolean m_20068_() {
      return true;
   }

   public boolean m_6094_() {
      return false;
   }

   public boolean m_5829_() {
      return true;
   }

   public void m_7334_(@NotNull Entity entity) {
   }

   public void m_5997_(double x, double y, double z) {
   }

   protected float m_6431_(@NotNull Pose pose, @NotNull EntityDimensions size) {
      return 0.5F;
   }

   public float getModeAnim(float partialTicks) {
      return Mth.m_14179_(partialTicks, this.modeAnimO, this.modeAnim);
   }

   public List<StructureAnimationHelper> getRibStructure() {
      return this.ribStructure;
   }

   public nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.TentacleManager getTentacleStructure() {
      return this.tentacleStructure;
   }

   public List<StructureAnimationHelper> getStructures() {
      return new ArrayList<>(this.getRibStructure());
   }

   public TargetingConditions searchablePlayersPredicate() {
      return TargetingConditions.m_148353_().m_26888_(SEARCHABLE_PLAYER_SELECTOR).m_26883_(6.0);
   }

   public void lerpStructureBaseRotTo(List<StructureAnimationHelper> structure, float xRot, float yRot, int steps) {
      for (StructureAnimationHelper helper : structure) {
         helper.lerpBaseTo(this, xRot, yRot, steps);
      }
   }

   public void lerpStructureRotTo(List<StructureAnimationHelper> structure, float xRot, float yRot, int steps) {
      for (StructureAnimationHelper helper : structure) {
         helper.lerpTo(this, xRot, yRot, steps);
      }
   }

   public void setProtectionYOffsetAndO(float offset) {
      this.protectionYOffset = offset;
      this.protectionYOffsetO = offset;
   }

   public void setProtectionYOffset(float offset) {
      this.protectionYOffset = offset;
   }

   public float getProtectionYOffset() {
      return this.protectionYOffset;
   }

   public float lerpProtectionYOffset(float partialTicks) {
      return Mth.m_14179_(partialTicks, this.protectionYOffsetO, this.protectionYOffset);
   }

   public int getStateTicks() {
      return this.stateTicks;
   }

   public Tag writeStructures() {
      ListTag structures = new ListTag();

      for (StructureAnimationHelper helper : this.getRibStructure()) {
         CompoundTag ribStructure = new CompoundTag();
         helper.write(ribStructure);
         structures.add(ribStructure);
      }

      return structures;
   }

   public void readStructures(ListTag list) {
      for (int i = 0; i < list.size(); i++) {
         CompoundTag ribStructure = list.m_128728_(i);
         if (i < this.getRibStructure().size()) {
            this.getRibStructure().get(i).read(ribStructure);
         }
      }
   }

   public void writeData(FriendlyByteBuf buffer) {
      buffer.writeInt(this.stateTicks);
      buffer.writeInt(this.modeAnim);
      buffer.writeFloat(this.protectionYOffset);

      for (StructureAnimationHelper helper : this.getStructures()) {
         helper.writeBuffer(buffer);
      }
   }

   public void readData(FriendlyByteBuf buffer) {
      this.stateTicks = buffer.readInt();
      this.modeAnim = buffer.readInt();
      this.modeAnimO = this.modeAnim;
      this.protectionYOffset = buffer.readFloat();
      this.protectionYOffsetO = this.protectionYOffset;

      for (StructureAnimationHelper helper : this.getStructures()) {
         helper.readBuffer(buffer);
      }
   }

   public void findOwner() {
      if (this.getOwnerUUID() != null && this.getOwner() == null) {
         if (this.getState() != nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.BOSSFIGHT) {
            for (WitherStormEntity entity : this.m_9236_().m_45976_(WitherStormEntity.class, this.m_20191_().m_82400_(100.0))) {
               if (entity.m_20148_().equals(this.getOwnerUUID())) {
                  this.setOwner(entity);
                  entity.getPlayDeadManager().setCommandBlock(this);
               }
            }
         } else if (!this.m_9236_().f_46443_) {
            MinecraftServer server = this.m_9236_().m_7654_();

            for (Level level : server.m_129785_()) {
               Entity entity = ((ServerLevel)level).m_8791_(this.getOwnerUUID());
               if (entity instanceof WitherStormEntity) {
                  this.setOwner((WitherStormEntity)entity);
                  break;
               }
            }
         }
      }
   }

   public void setOwner(@Nullable WitherStormEntity entity) {
      this.owner = entity;
      if (entity != null) {
         this.setOwnerUUID(entity.m_20148_());
         this.getState().initWithOwner(entity, this);
      } else {
         this.setOwnerUUID(null);
      }
   }

   @Nullable
   public WitherStormEntity getOwner() {
      return this.owner;
   }

   @Nullable
   public UUID getOwnerUUID() {
      return (UUID)((Optional)this.f_19804_.m_135370_(OWNER_UUID)).orElse(null);
   }

   public void setOwnerUUID(@Nullable UUID uuid) {
      this.f_19804_.m_135381_(OWNER_UUID, Optional.ofNullable(uuid));
   }

   @NotNull
   public Packet<ClientGamePacketListener> m_5654_() {
      WitherStormModPacketHandlers.MAIN
         .send(
            PacketDistributor.TRACKING_ENTITY.with(() -> this),
            new nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.ModeAnimationMessage(this.m_19879_(), this.modeAnim)
         );
      return super.m_5654_();
   }

   public void onAddedToWorld() {
      super.onAddedToWorld();
      if (!this.m_9236_().f_46443_) {
         this.tentacleStructure.createTentacles();
      }
   }

   public void onRemovedFromWorld() {
      super.onRemovedFromWorld();
   }

   public void m_6667_(@NotNull DamageSource source) {
      if (!this.m_9236_().f_46443_
         && this.getState() == nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.BOSSFIGHT
         && this.getBossfightManager().getCurrentPhase() != BowelsBossFightStages.DEATH) {
         this.getBossfightManager().setCurrentFromNextInOrder(BowelsBossFightStages.DEATH);
      }

      this.killer = this.m_21232_();
      super.m_6667_(source);
   }

   protected void m_6153_() {
      if (this.getCurrentPhase().equals(BowelsBossFightStages.DEATH)) {
         this.specialDeathTime++;
         if (!this.m_9236_().f_46443_) {
            if (this.specialDeathTime > 160) {
               this.getBossfightManager().goToNextPhase();
            } else {
               this.dropClusterFromCeiling();
            }

            if (this.specialDeathTime > 240) {
               this.tentacleStructure.killTentacles();
               this.m_142687_(RemovalReason.KILLED);
            }
         }
      } else {
         super.m_6153_();
         if (!this.m_9236_().f_46443_ && this.f_20919_ >= 20) {
            this.tentacleStructure.removeTentacles();
         }
      }
   }

   private void dropClusterFromCeiling() {
      if (WorldUtil.areaLoaded(this.m_9236_(), this.m_20183_(), 2)) {
         float angle = (float) (Math.PI * 2) * this.f_19796_.m_188501_();
         float dist = 8.0F + this.f_19796_.m_188501_() * 24.0F;
         int x = Mth.m_14143_(Mth.m_14089_(angle) * dist) + this.m_146903_();
         int z = Mth.m_14143_(Mth.m_14031_(angle) * dist) + this.m_146907_();
         int y = WorldUtil.getCeilingStartingAt(this.m_9236_(), this.m_146904_() + 10, x, z);
         BlockPos pos = new BlockPos(x, y, z);
         if (this.m_9236_().m_46859_(pos.m_7495_())) {
            BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
            assert cluster != null;
            cluster.populateWithRadius(pos, 1.0F, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
            cluster.setRotationDelta(new Vec2(10.0F * (this.f_19796_.m_188501_() - 0.5F), 10.0F * (this.f_19796_.m_188501_() - 0.5F)));
            cluster.setAntiStacking(true);
            this.m_9236_().m_7967_(cluster);
         }
      }
   }

   private void dropAdditionalClustersFromCeiling() {
      if (WorldUtil.areaLoaded(this.m_9236_(), this.m_20183_(), 8)) {
         for (int i = 0; i < 128; i++) {
            float angle = (float) (Math.PI * 2) * this.f_19796_.m_188501_();
            float dist = 32.0F + this.f_19796_.m_188501_() * 80.0F;
            int x = Mth.m_14143_(Mth.m_14089_(angle) * dist) + this.m_146903_();
            int z = Mth.m_14143_(Mth.m_14031_(angle) * dist) + this.m_146907_();
            int y = WorldUtil.getCeilingStartingAt(this.m_9236_(), this.m_146904_() + (this.f_19796_.m_188503_(49) - 24), x, z);
            BlockPos pos = new BlockPos(x, y, z);
            if (this.m_9236_().m_46859_(pos.m_7495_())) {
               BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
               assert cluster != null;
               cluster.populateWithRadius(pos, 1.0F, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
               cluster.setRotationDelta(new Vec2(10.0F * (this.f_19796_.m_188501_() - 0.5F), 10.0F * (this.f_19796_.m_188501_() - 0.5F)));
               cluster.setAntiStacking(true);
               this.m_9236_().m_7967_(cluster);
               break;
            }
         }
      }
   }

   public BossfightManager<nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity> getBossfightManager() {
      return this.bossfightManager;
   }

   public void m_6457_(@NotNull ServerPlayer player) {
      super.m_6457_(player);
      this.bossInfo.m_6543_(player);
      this.tracking.add(player);
   }

   public void m_6452_(@NotNull ServerPlayer player) {
      super.m_6452_(player);
      this.bossInfo.m_6539_(player);
      this.tracking.remove(player);
   }

   public SoundEvent getBossTheme() {
      return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_FINAL_BOSS_THEME.get();
   }

   public boolean shouldPlayBossTheme() {
      return super.shouldPlayBossTheme()
         && this.m_21223_() < this.m_21233_()
         && this.getState() == nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.BOSSFIGHT;
   }

   public Vec3 getPosition() {
      return this.m_20182_();
   }

   public boolean isStillAlive() {
      return this.m_6084_();
   }

   public int priority() {
      return 3;
   }

   public int getFadeTime() {
      return 40;
   }

   public boolean checkConfig() {
      return (Boolean)WitherStormModConfig.CLIENT.playWitherStormTheme.get();
   }

   public void createPodiumCluster() {
      if (this.podiumCluster == null && this.podiumClusterUUID == null) {
         BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
         BlockPos bottomCorner = new BlockPos(-5, -13, -5).m_121955_(this.m_20183_());
         assert cluster != null;
         cluster.populate(bottomCorner, bottomCorner.m_7918_(10, 19, 10), blockstate -> true);
         cluster.setResetGravityOnLoad(false);
         cluster.setPhysics(false);
         cluster.m_20242_(true);
         cluster.setForceRender(true);
         this.m_9236_().m_7967_(cluster);
         this.podiumCluster = cluster;
      }
   }

   public void movePodiumCluster(Vec3 delta) {
      if (this.podiumCluster != null) {
         this.podiumCluster.m_20256_(delta);
      }
   }

   public void findPodiumCluster() {
      if (!this.m_9236_().f_46443_ && this.podiumClusterUUID != null && this.podiumCluster == null) {
         ServerLevel serverWorld = (ServerLevel)this.m_9236_();
         Entity entity = serverWorld.m_8791_(this.podiumClusterUUID);
         if (entity instanceof BlockClusterEntity) {
            this.podiumCluster = (BlockClusterEntity)entity;
         }
      }
   }

   @Nullable
   public BlockClusterEntity getPodiumCluster() {
      return this.podiumCluster;
   }

   @Nullable
   public Mob summonRandomBowelsMobNearPlayer(int minRadius, int maxRadius, SimpleWeightedRandomList<EntityType<? extends Mob>> types) {
      ServerLevel world = (ServerLevel)this.m_9236_();

      for (ServerPlayer player : world.m_45976_(ServerPlayer.class, this.m_20191_().m_82400_(128.0))) {
         Mob entity = (Mob)((EntityType)Objects.requireNonNull((EntityType)types.m_216820_(this.f_19796_).orElse(null))).m_20615_(world);
         assert entity != null;
         BlockPos playerPos = player.m_20183_();
         BlockPos randomPos = this.getRandomPosAroundPlayer(entity.m_6095_(), playerPos, minRadius, maxRadius, 10);
         if (randomPos != null && this.hasEnoughSpace(entity, randomPos)) {
            entity.m_6034_(randomPos.m_123341_() + 0.5, randomPos.m_123342_() + 1, randomPos.m_123343_() + 0.5);
            ForgeEventFactory.onFinalizeSpawn(entity, world, world.m_6436_(randomPos), MobSpawnType.EVENT, null, null);
            if (entity instanceof Monster || entity instanceof AbstractGolem) {
               this.addSpeedAttribute(entity);
            }

            this.addHealthAttribute(entity);
            entity.m_8032_();
            entity.m_21373_();
            world.m_8767_(
               (SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
               entity.m_20185_(),
               entity.m_20188_(),
               entity.m_20189_(),
               20,
               this.f_19796_.m_188583_(),
               this.f_19796_.m_188583_(),
               this.f_19796_.m_188583_(),
               0.2
            );
            world.m_8767_(
               ParticleTypes.f_123755_,
               entity.m_20185_(),
               entity.m_20188_(),
               entity.m_20189_(),
               20,
               this.f_19796_.m_188583_(),
               this.f_19796_.m_188583_(),
               this.f_19796_.m_188583_(),
               0.01
            );
            world.m_47205_(entity);
            return entity;
         }
      }

      return null;
   }

   @Nullable
   public BlockPos getRandomPosAroundPlayer(EntityType<?> type, BlockPos playerPos, int minDistance, int maxDistance, int attempts) {
      for (int a = 0; a < attempts; a++) {
         int x = playerPos.m_123341_() + this.f_19796_.m_188503_(maxDistance * 2 + 1) - maxDistance;
         int y = playerPos.m_123342_() + this.f_19796_.m_188503_(maxDistance);
         int z = playerPos.m_123343_() + this.f_19796_.m_188503_(maxDistance * 2 + 1) - maxDistance;
         BlockPos pos = new BlockPos(x, y, z);
         double distanceSquared = playerPos.m_123331_(pos);
         if (distanceSquared >= minDistance * minDistance && distanceSquared <= maxDistance * maxDistance) {
            for (int i = 0; i < 30 && this.m_9236_().m_8055_(pos.m_7495_()).m_60713_(Blocks.f_50016_); i++) {
               pos = pos.m_7495_();
            }

            if (NaturalSpawner.m_47051_(Type.ON_GROUND, this.m_9236_(), pos, type) && Math.sqrt(playerPos.m_123331_(pos)) > 6.0) {
               return pos;
            }
         }
      }

      return null;
   }

   @Nullable
   public Mob summonRandomMob(int diameter, SimpleWeightedRandomList<EntityType<? extends Mob>> types) {
      ServerLevel world = (ServerLevel)this.m_9236_();
      Mob entity = (Mob)((EntityType)types.m_216820_(this.f_19796_).orElse(null)).m_20615_(world);
      BlockPos pos = this.getRandomNearbyPos(entity.m_6095_(), diameter, 5);
      if (pos != null && this.hasEnoughSpace(entity, pos)) {
         entity.m_6034_(pos.m_123341_() + 0.5, pos.m_123342_() + 1, pos.m_123343_() + 0.5);
         ForgeEventFactory.onFinalizeSpawn(entity, world, world.m_6436_(pos), MobSpawnType.EVENT, null, null);
         if (entity instanceof Monster || entity instanceof AbstractGolem) {
            this.addSpeedAttribute(entity);
         }

         this.addHealthAttribute(entity);
         entity.m_8032_();
         entity.m_21373_();
         entity.m_21530_();
         world.m_8767_(
            (SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
            entity.m_20185_(),
            entity.m_20188_(),
            entity.m_20189_(),
            20,
            this.f_19796_.m_188583_(),
            this.f_19796_.m_188583_(),
            this.f_19796_.m_188583_(),
            0.2
         );
         world.m_8767_(
            ParticleTypes.f_123755_,
            entity.m_20185_(),
            entity.m_20188_(),
            entity.m_20189_(),
            20,
            this.f_19796_.m_188583_(),
            this.f_19796_.m_188583_(),
            this.f_19796_.m_188583_(),
            0.01
         );
         world.m_47205_(entity);
         return entity;
      } else {
         return null;
      }
   }

   @Nullable
   public BlockPos getRandomNearbyPos(EntityType<?> type, int diameter, int attempts) {
      BlockPos pos = null;
      BlockPos start = this.m_20183_();

      for (int i = 0; i < attempts; i++) {
         int x = start.m_123341_() + this.f_19796_.m_188503_(diameter) - diameter / 2;
         int z = start.m_123343_() + this.f_19796_.m_188503_(diameter) - diameter / 2;
         int y = start.m_123342_();
         BlockPos currentPos = new BlockPos(x, y, z);

         for (int j = 0; j < 30 && this.m_9236_().m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); j++) {
            currentPos = currentPos.m_7495_();
         }

         if (NaturalSpawner.m_47051_(Type.ON_GROUND, this.m_9236_(), currentPos, type) && Math.sqrt(this.m_20183_().m_123331_(currentPos)) > 6.0) {
            pos = currentPos;
            break;
         }
      }

      return pos;
   }

   private void addHealthAttribute(Mob mob) {
      Objects.requireNonNull(mob.m_21051_(Attributes.f_22276_))
         .m_22125_(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.m_217043_().m_188500_() + 0.5) * 2.0), Operation.ADDITION));
   }

   private void addSpeedAttribute(Mob mob) {
      if (!(mob instanceof SickenedVindicator) && !(mob instanceof SickenedIronGolem)) {
         Objects.requireNonNull(mob.m_21051_(Attributes.f_22279_))
            .m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.06, Operation.ADDITION));
      } else {
         Objects.requireNonNull(mob.m_21051_(Attributes.f_22279_))
            .m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08, Operation.ADDITION));
      }
   }

   private boolean hasEnoughSpace(Entity entity, BlockPos spawnPos) {
      for (BlockPos pos : BlockPos.m_121940_(spawnPos, spawnPos.m_121955_(BlockPos.m_274561_(entity.m_20205_(), entity.m_20206_(), entity.m_20205_())))) {
         if (!this.m_9236_().m_8055_(pos).m_60812_(this.m_9236_(), pos).m_83281_()) {
            return false;
         }
      }

      return true;
   }

   public BossfightPhase<nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity> getCurrentPhase() {
      return this.m_9236_().f_46443_
         ? this.getBossfightManager().getPhase((Integer)this.f_19804_.m_135370_(PHASE_KEY))
         : this.getBossfightManager().getCurrentPhase();
   }

   public int getSpecialDeathTime() {
      return this.specialDeathTime;
   }

   protected float m_6121_() {
      return 4.0F;
   }

   protected SoundEvent m_7975_(@NotNull DamageSource source) {
      return (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DAMAGE.get();
   }

   protected SoundEvent m_5592_() {
      return (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DEATH.get();
   }

   public float m_6100_() {
      return 1.0F;
   }

   public boolean m_6149_() {
      return false;
   }

   protected boolean m_20073_() {
      return false;
   }

   public int getHitGlareTime() {
      return this.hitGlareTime;
   }

   public List<ServerPlayer> getOutsideBossBarViewers() {
      return this.outsideBossBarViewers;
   }

   public void removeOutsideBossBarViewer(ServerPlayer player) {
      this.outsideBossBarViewers.remove(player);
      this.bossInfo.m_6539_(player);
   }

   public boolean m_7301_(@NotNull MobEffectInstance instance) {
      return false;
   }

   @NotNull
   public PushReaction m_7752_() {
      return PushReaction.IGNORE;
   }

   public enum Mode {
      NONE,
      RIBS {
         @Override
         public void playingDeadTick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.playingDeadTick(entity);
            Random random = new Random(entity.m_20148_().getLeastSignificantBits());

            for (StructureAnimationHelper helper : this.getStructure(entity)) {
               helper.lerpBaseTo(entity, -50.0F, 0.0F, Math.max(4, random.nextInt(11)));
               helper.lerpTo(entity, Math.max(30.0F, random.nextInt(131)), random.nextInt(21) - 10.0F, Math.max(4, random.nextInt(11)));
            }
         }

         @Override
         public void idleTick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.idleTick(entity);
            entity.lerpStructureBaseRotTo(this.getStructure(entity), -50.0F, 0.0F, 10);
            entity.lerpStructureRotTo(this.getStructure(entity), 60.0F, 0.0F, 20);
         }

         @Override
         public void protectTick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.protectTick(entity);
            entity.lerpStructureBaseRotTo(this.getStructure(entity), 0.0F, 0.0F, 40);
            entity.lerpStructureRotTo(this.getStructure(entity), 70.0F, 0.0F, 20);
            if (entity.getProtectionYOffset() > -0.8F && entity.getStateTicks() > 20 + entity.getState().modeTickDelay()) {
               entity.protectionYOffset -= 0.05F;
            }
         }

         @Override
         public void playMovementSound(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            for (int i = 0; i < this.getStructure(entity).size(); i++) {
               double x = entity.f_19796_.m_188583_() * 3.0 + entity.m_20185_();
               double y = entity.m_20188_();
               double z = entity.f_19796_.m_188583_() * 3.0 + entity.m_20189_();
               entity.m_9236_().m_6263_(null, x, y, z, (SoundEvent)WitherStormModSoundEvents.RIB_BONE_CRACK.get(), SoundSource.AMBIENT, 0.2F, 0.8F);
            }
         }

         @Override
         public List<StructureAnimationHelper> getStructure(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            return entity.getRibStructure();
         }
      },
      TENTACLES {
         @Override
         public void tick(
            nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity, nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State state
         ) {
            super.tick(entity, state);
            if (!entity.m_9236_().f_46443_) {
               entity.tentacleStructure.addTentacles();
               entity.tentacleStructure.updateTentacles();
            }
         }
      };

      public void tick(
         nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity, nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State state
      ) {
         entity.modeAnim++;
         if (!entity.m_9236_().f_46443_ && entity.f_19797_ % 120 == 0) {
            WitherStormModPacketHandlers.MAIN
               .send(
                  PacketDistributor.TRACKING_ENTITY.with(() -> entity),
                  new nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.ModeAnimationMessage(entity.m_19879_(), entity.modeAnim)
               );
         }
      }

      public void idleTick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
      }

      public void playingDeadTick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
      }

      public void protectTick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
      }

      public void init(
         nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity, nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State state
      ) {
         entity.modeAnim = 0;
         if (!entity.m_9236_().f_46443_ && entity.isAddedToWorld()) {
            if (this == TENTACLES) {
               entity.tentacleStructure.readdTentacles();
            } else {
               entity.tentacleStructure.removeTentacles();
            }
         }
      }

      public List<StructureAnimationHelper> getStructure(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
         return Lists.newArrayList();
      }

      public void playMovementSound(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
      }
   }

   public static class ModeAnimationMessage extends nonamecrackers2.crackerslib.common.packet.Packet {
      private int id;
      private int anim;

      public ModeAnimationMessage(int entityId, int anim) {
         super(true);
         this.id = entityId;
         this.anim = anim;
      }

      public ModeAnimationMessage() {
         super(false);
      }

      public void encode(FriendlyByteBuf buffer) {
         buffer.m_130130_(this.id);
         buffer.writeInt(this.anim);
      }

      public void decode(FriendlyByteBuf buffer) {
         this.id = buffer.m_130242_();
         this.anim = buffer.readInt();
      }

      public Runnable getProcessor(Context context) {
         return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            Optional<Level> optional = (Optional<Level>)LogicalSidedProvider.CLIENTWORLD.get(context.getDirection().getReceptionSide());
            optional.ifPresent(world -> {
               if (world.m_6815_(this.id) instanceof nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity commandBlock) {
                  commandBlock.modeAnim = this.anim;
               }
            });
         });
      }
   }

   public enum State {
      IDLE {
         @Override
         public void tick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.tick(entity);
            if (this.modeTickDelay() < entity.getStateTicks()) {
               entity.getMode().idleTick(entity);
            }
         }
      },
      PLAYING_DEAD {
         @Override
         public void tick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.tick(entity);
            if (this.modeTickDelay() < entity.getStateTicks()) {
               entity.getMode().playingDeadTick(entity);
            }

            if (!entity.m_9236_().f_46443_) {
               Level world = entity.m_9236_();
               Player player = world.m_45946_(entity.searchablePlayersPredicate(), entity);
               if (player != null) {
                  entity.setLuringPlayer(player);
                  entity.nextState();
               }

               int time = (Integer)WitherStormModConfig.SERVER.revivalTimeMinutes.get();
               if ((Boolean)WitherStormModConfig.SERVER.revivalTimer.get() && time > 0 && entity.getStateTicks() > time * 1200) {
                  entity.setState(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State.REACTIVATING);
               }
            }
         }
      },
      LURING {
         @Override
         public void tick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.tick(entity);
            if (this.modeTickDelay() < entity.getStateTicks()) {
               entity.getMode().idleTick(entity);
            }

            if (!entity.m_9236_().f_46443_) {
               Player player = entity.getLuringPlayer();
               if (player != null && entity.searchablePlayersPredicate().m_26883_(12.0).m_26885_(entity, player) && entity.getStateTicks() < 240) {
                  double speed = 0.025;
                  Vec3 motion = entity.m_20182_().m_82546_(player.m_20182_()).m_82541_().m_82542_(speed, speed, speed);
                  player.m_20334_(motion.f_82479_, player.m_20184_().f_82480_, motion.f_82481_);
                  WitherStormModPacketHandlers.MAIN
                     .send(
                        PacketDistributor.PLAYER.with(() -> (ServerPlayer)player),
                        new PlayerMotionMessage(new Vec3(motion.f_82479_, player.m_20184_().f_82480_, motion.f_82481_))
                     );
                  if (player.m_20270_(entity) < 3.0) {
                     entity.setLuringPlayer(null);
                     entity.nextState();
                  }

                  for (int i = 0; i < 4; i++) {
                     double x = player.m_20185_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82362_() * 0.4;
                     double y = player.m_20191_().m_82399_().m_7098_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82376_() * 0.4;
                     double z = player.m_20189_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82385_() * 0.4;
                     Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82490_(0.1);
                     entity.m_9236_()
                        .m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
                  }
               } else {
                  entity.nextState();
               }
            }
         }

         @Override
         public void init(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.init(entity);
            entity.m_9236_()
               .m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.HOSTILE, 5.0F, 1.0F);
            entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
            if (!entity.m_9236_().f_46443_) {
               ShakeScreenMessage message = new ShakeScreenMessage(40.0F, 5.0F);
               WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
            }

            for (int i = 0; i < 10; i++) {
               entity.m_9236_()
                  .m_7106_(
                     (ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
                     entity.m_20185_(),
                     entity.m_20188_(),
                     entity.m_20189_(),
                     entity.f_19796_.m_188583_() * 0.5,
                     entity.f_19796_.m_188583_() * 0.5,
                     entity.f_19796_.m_188583_() * 0.5
                  );
            }
         }

         @Override
         public boolean shouldShowOwnerBossBar() {
            return true;
         }

         @Override
         public int modeTickDelay() {
            return 40;
         }
      },
      REACTIVATING {
         @Override
         public void tick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.tick(entity);
            if (this.modeTickDelay() < entity.getStateTicks()) {
               entity.getMode().protectTick(entity);
            }

            if (!entity.m_9236_().f_46443_ && entity.getOwner() != null && entity.getStateTicks() > 60) {
               WitherStormEntity owner = entity.getOwner();
               if (!owner.isReviving()) {
                  owner.reviveFromPlayingDead();
               }
            }
         }

         @Override
         public void init(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.init(entity);
            if (!entity.m_9236_().f_46443_) {
               ShakeScreenMessage message = new ShakeScreenMessage(120.0F, 5.0F);
               WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
            }

            entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
         }

         @Override
         public boolean shouldShowOwnerBossBar() {
            return true;
         }

         @Override
         public int modeTickDelay() {
            return 20;
         }
      },
      BOSSFIGHT {
         @Override
         public void tick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.tick(entity);
            if (!entity.m_9236_().f_46443_) {
               entity.getBossfightManager().tick();
               if (!entity.getCurrentPhase().equals(BowelsBossFightStages.IDLE) || entity.m_21223_() < entity.m_21233_()) {
                  entity.bossInfo.m_8321_(true);
               }
            }

            entity.getMode().idleTick(entity);
         }

         @Override
         public void init(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
            super.init(entity);
         }
      };

      public void tick(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
         entity.getMode().tick(entity, entity.getState());
         entity.stateTicks++;
         if (this != PLAYING_DEAD) {
            for (int i = 0; i < 5; i++) {
               double x = entity.m_20185_() + entity.f_19796_.m_188583_();
               double y = entity.m_20188_() + entity.f_19796_.m_188583_();
               double z = entity.m_20189_() + entity.f_19796_.m_188583_();
               float particleSpeed = (entity.m_21233_() - entity.m_21223_()) / entity.m_21233_() + 1.0F;
               Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82490_(0.1 * particleSpeed);
               entity.m_9236_()
                  .m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
            }
         }
      }

      public void init(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
         entity.stateTicks = 0;
         entity.getMode().init(entity, entity.getState());
         entity.setProtectionYOffset(0.0F);
         if (entity.getOwner() != null) {
            this.initWithOwner(entity.getOwner(), entity);
         }

         entity.bossInfo.m_8321_(false);
      }

      public int modeTickDelay() {
         return 0;
      }

      public boolean shouldShowOwnerBossBar() {
         return false;
      }

      public void initWithOwner(WitherStormEntity owner, nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity) {
         if (owner.m_6084_()) {
            if (this.shouldShowOwnerBossBar()) {
               owner.getBossInfo().ifPresent(info -> info.m_8321_(true));
            } else {
               owner.getBossInfo().ifPresent(info -> info.m_8321_(!owner.isPlayingDead()));
            }
         }
      }
   }

   public static class TentacleManager {
      private static final UUID KNOCKBACK_MODIFIER = UUID.fromString("72aeccbe-cdfe-41c9-9d21-261f29f6da60");
      private final nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity;
      public final int tentacles;
      public TentacleEntity[] tentacleStructure;
      private final UUID[] savedTentacleStructure;
      private final TentacleOffsets[] offsets;

      public TentacleManager(nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity entity, int amount, TentacleOffsets[] offsets) {
         this.entity = entity;
         this.tentacles = amount;
         this.tentacleStructure = new TentacleEntity[this.tentacles];
         this.savedTentacleStructure = new UUID[this.tentacles];
         this.offsets = offsets;
      }

      public void createTentacles() {
         for (int i = 0; i < this.tentacleStructure.length; i++) {
            this.createTentacle(i);
         }
      }

      private void createTentacle(int index) {
         if (this.tentacleStructure[index] == null || !this.tentacleStructure[index].m_6084_()) {
            TentacleEntity tentacle = (TentacleEntity)((EntityType)WitherStormModEntityTypes.TENTACLE.get()).m_20615_(this.entity.m_9236_());
            this.getOffsetsForTentacle(index).apply(this.entity, tentacle);
            assert tentacle != null;
            tentacle.setDormant(true);
            tentacle.lerpCurlTo(0.0F, 0.0F, 1);
            tentacle.m_20331_(true);
            tentacle.m_20242_(true);
            tentacle.setAnimationOffset(this.entity.f_19796_.m_188503_(35) * 10000);
            tentacle.setCanStrangle(false);
            Objects.requireNonNull(tentacle.m_21051_(Attributes.f_22282_))
               .m_22125_(new AttributeModifier(KNOCKBACK_MODIFIER, "Command block's tentacles knockback modifier", 5.0, Operation.ADDITION));
            this.tentacleStructure[index] = tentacle;
         }
      }

      public void removeTentacles() {
         for (TentacleEntity tentacle : this.tentacleStructure) {
            if (tentacle != null && tentacle.m_6084_()) {
               tentacle.m_146870_();
            }
         }
      }

      public void readdTentacles() {
         this.createTentacles();
         this.addTentacles();
      }

      public void addTentacles() {
         if (this.entity.isAddedToWorld() && this.entity.m_6084_()) {
            for (int i = 0; i < this.tentacleStructure.length; i++) {
               this.addTentacle(i);
            }
         }
      }

      private void addTentacle(int index) {
         TentacleEntity tentacle = this.tentacleStructure[index];
         if (tentacle != null && !tentacle.isAddedToWorld() && tentacle.m_6084_()) {
            this.getOffsetsForTentacle(index).apply(this.entity, tentacle);
            this.entity.m_9236_().m_7967_(tentacle);
         }
      }

      public void updateTentacles() {
         for (int i = 0; i < this.tentacleStructure.length; i++) {
            TentacleEntity tentacle = this.tentacleStructure[i];
            if (tentacle != null && tentacle.m_6084_()) {
               this.getOffsetsForTentacle(i).apply(this.entity, tentacle);
            }
         }
      }

      public void killTentacles() {
         for (TentacleEntity tentacle : this.tentacleStructure) {
            if (tentacle != null) {
               tentacle.m_6074_();
            }
         }
      }

      public void findTentacles(ServerLevel world) {
         for (int i = 0; i < this.savedTentacleStructure.length; i++) {
            UUID uuid = this.savedTentacleStructure[i];
            TentacleEntity preexisting = this.tentacleStructure[i];
            if (uuid != null && preexisting != null && !preexisting.m_20148_().equals(uuid) || preexisting == null) {
               assert uuid != null;
               if (world.m_8791_(uuid) instanceof TentacleEntity found) {
                  if (preexisting != null) {
                     preexisting.m_146870_();
                  }

                  this.tentacleStructure[i] = found;
                  this.savedTentacleStructure[i] = null;
               }
            }
         }
      }

      public TentacleOffsets getOffsetsForTentacle(int index) {
         return this.offsets[index];
      }

      public void addSaveData(CompoundTag compound) {
         ListTag list = new ListTag();

         for (TentacleEntity tentacle : this.tentacleStructure) {
            CompoundTag tentacleCompound = new CompoundTag();
            if (tentacle != null) {
               tentacleCompound.m_128362_("UUID", tentacle.m_20148_());
            }

            list.add(tentacleCompound);
         }

         compound.m_128365_("Tentacles", list);
      }

      public void readSaveData(CompoundTag compound) {
         ListTag list = compound.m_128437_("Tentacles", 10);

         for (int i = 0; i < this.tentacleStructure.length; i++) {
            CompoundTag tentacleCompound = list.m_128728_(i);
            if (tentacleCompound.m_128441_("UUID") && this.savedTentacleStructure[i] == null) {
               this.savedTentacleStructure[i] = tentacleCompound.m_128342_("UUID");
            }
         }
      }
   }
}
