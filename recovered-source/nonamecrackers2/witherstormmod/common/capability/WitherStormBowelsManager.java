package nonamecrackers2.witherstormmod.common.capability;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.level.LevelEvent.Load;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.Mode;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity.State;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModFeatures;
import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
import nonamecrackers2.witherstormmod.common.util.BowelsTeleporter;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitherStormBowelsManager {
   private static final Logger LOGGER = LogManager.getLogger("witherstormmod/WitherStormBowelsManager");
   private static final int ENTRANCE_SEARCH_RADIUS = 96;
   private static final int ENTRANCE_SEARCH_RADIUS_MIN = 35;
   private static BlockPos NORTH_HEAD_POS = new BlockPos(-2, 128, 27);
   private static BlockPos SOUTH_HEAD_POS = new BlockPos(-3, 128, -23);
   private final Map<UUID, Entity> queuedEnter = new HashMap<>();
   private final Map<Entity, Runnable> queuedLeave = new HashMap<>();
   private final List<nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance> instances = new ArrayList<>();
   private final ServerLevel world;
   private final Random random = new Random();
   @Nullable
   private Pair<BlockPos, StructureStart> nextAvailableStructure;

   public WitherStormBowelsManager(ServerLevel world) {
      this.world = world;
   }

   public WitherStormBowelsManager() {
      this.world = null;
   }

   public void read(CompoundTag compound) {
      ListTag list = compound.m_128437_("Instances", 10);

      for (int i = 0; i < list.size(); i++) {
         CompoundTag instanceCompound = list.m_128728_(i);
         this.instances.add(nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance.read(instanceCompound));
      }
   }

   public CompoundTag write() {
      CompoundTag compound = new CompoundTag();
      ListTag list = new ListTag();

      for (nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance : this.instances) {
         list.add(instance.write());
      }

      compound.m_128365_("Instances", list);
      return compound;
   }

   @Nullable
   public nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance getOrCreateInstanceFor(WitherStormEntity storm) {
      nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance = this.get(storm.m_20148_());
      if (instance == null) {
         LOGGER.debug("Searching for available bowels arena for entity {}", storm);
         Pair<BlockPos, StructureStart> start;
         if (this.nextAvailableStructure != null) {
            LOGGER.debug("Using pre-located structure...");
            start = this.nextAvailableStructure;
            this.nextAvailableStructure = null;
         } else {
            start = this.getAvailableStructure();
         }

         if (start != null) {
            instance = new nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance(start, storm.m_20148_());
         } else {
            LOGGER.error("Could not find an available bowels structure for {}. This shouldn't happen!", storm);
         }
      }

      return instance;
   }

   @Nullable
   public Pair<BlockPos, StructureStart> getAvailableStructure() {
      if (!this.world.m_7654_().m_129910_().m_246337_().m_247749_()) {
         this.world
            .m_7654_()
            .m_6846_()
            .m_11314_()
            .forEach(player -> player.m_213846_(Component.m_237115_("chat.witherstormmod.bowels.structuresDisabled").m_130940_(ChatFormatting.RED)));
         LOGGER.warn("Structures are disabled, meaning the bowels cannot be accessed. Please enable structures to have access to the final boss battle.");
         return null;
      }

      Holder<Structure> holder = getBowels(this.world);
      StructureManager manager = this.world.m_215010_();
      BlockPos startPos = BlockPos.f_121853_;
      if (!this.instances.isEmpty()) {
         startPos = this.instances.get(this.instances.size() - 1).getPos();
      }

      RandomSpreadStructurePlacement placement = null;

      for (StructurePlacement structurePlacement : this.world.m_7726_().m_255415_().m_255260_(holder)) {
         if (structurePlacement instanceof RandomSpreadStructurePlacement p) {
            placement = p;
            break;
         }
      }

      if (placement == null) {
         LOGGER.error("The Bowels structure must be the random spread placement type!");
         return null;
      }

      int sectionX = SectionPos.m_123171_(startPos.m_123341_());
      int sectionZ = SectionPos.m_123171_(startPos.m_123343_());

      for (int area = 0; area <= 100; area++) {
         int i = placement.m_205003_();

         for (int x = -area; x <= area; x++) {
            boolean xFlag = x == -area || x == area;

            for (int z = -area; z <= area; z++) {
               boolean zFlag = z == -area || z == area;
               if (xFlag || zFlag) {
                  int finalX = sectionX + i * x;
                  int finalZ = sectionZ + i * z;
                  ChunkPos potential = placement.m_227008_(this.world.m_7328_(), finalX, finalZ);
                  StructureCheckResult result = manager.m_220473_(potential, (Structure)holder.m_203334_(), false);
                  if (result != StructureCheckResult.START_NOT_PRESENT) {
                     ChunkAccess chunk = this.world.m_46819_(potential.f_45578_, potential.f_45579_, ChunkStatus.f_62315_);
                     StructureStart start = manager.m_220512_(SectionPos.m_175562_(chunk), (Structure)holder.m_203334_(), chunk);
                     if (start != null && start.m_73603_()) {
                        BlockPos locatePos = placement.m_227039_(start.m_163625_());
                        if (this.instances.stream().filter(instance -> instance.getPos().equals(locatePos)).collect(Collectors.toList()).isEmpty()) {
                           return Pair.of(locatePos, start);
                        }
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   @Nullable
   public nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance get(UUID uuid) {
      for (nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance : this.instances) {
         if (instance.witherStorm.equals(uuid) && !instance.isCompleted()) {
            return instance;
         }
      }

      return null;
   }

   public void add(nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance) {
      if (!this.instances.contains(instance)) {
         this.instances.add(instance);
      }
   }

   public void onLoad() {
      for (nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance : this.instances) {
         instance.setup(this.world);
      }

      this.nextAvailableStructure = this.getAvailableStructure();
      LOGGER.debug("Pre-locating a bowels arena: {}", this.nextAvailableStructure);
   }

   @Nullable
   private WitherStormEntity findStorm(UUID uuid) {
      MinecraftServer server = this.world.m_7654_();

      for (Level world : server.m_129785_()) {
         ServerLevel serverWorld = (ServerLevel)world;
         Entity entity = serverWorld.m_8791_(uuid);
         if (entity != null && entity instanceof WitherStormEntity) {
            return (WitherStormEntity)entity;
         }
      }

      return null;
   }

   public BlockPos calculateEntrancePos(nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance) {
      BlockPos pos = instance.getPos();
      if (instance.getStart() != null) {
         boolean flag = true;
         if ((Boolean)WitherStormModConfig.SERVER.randomBowelsEntrace.get()) {
            label73:
            for (int startRadius = 96; startRadius > 35; startRadius -= 10) {
               int randomStartAngle = this.random.nextInt(360);
               int angleOffset = 20;

               for (int i = 0; i < 360 / angleOffset; i++) {
                  float angle = (float)randomStartAngle + angleOffset * i;
                  int x = (int)(Mth.m_14031_(angle * (float) (Math.PI / 180.0)) * startRadius) + pos.m_123341_();
                  int z = (int)(Mth.m_14089_(angle * (float) (Math.PI / 180.0)) * startRadius) + pos.m_123343_();
                  BlockPos currentPos = new BlockPos(x, 112, z);

                  for (int j = 0; j < 30 && this.world.m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); j++) {
                     currentPos = currentPos.m_7495_();
                  }

                  if (NaturalSpawner.m_47051_(Type.ON_GROUND, this.world, currentPos, EntityType.f_20532_)) {
                     int scanRadius = 10;
                     int totalAir = 0;

                     for (BlockState state : WorldUtil.getBlockStatesBetweenClosed(
                        this.world, currentPos.m_7918_(-scanRadius, -scanRadius, -scanRadius), currentPos.m_7918_(scanRadius, scanRadius, scanRadius)
                     )) {
                        if (state.m_60795_()) {
                           totalAir++;
                        }
                     }

                     float bias = totalAir / 8000.0F;
                     if (bias <= 0.2F) {
                        pos = currentPos;
                        flag = false;
                        break label73;
                     }
                  }
               }
            }
         }

         if (flag) {
            Rotation rotation = ((StructurePiece)instance.getStart().m_73602_().get(0)).m_6830_();
            BlockPos offset = BlockPos.f_121853_.m_5487_(Axis.X, -112).m_7954_(rotation);
            pos = pos.m_6630_(120);
            pos = pos.m_121955_(offset);
            BlockState state = this.world.m_8055_(pos);

            while (state.m_60713_(Blocks.f_50016_) && pos.m_123342_() > 0) {
               BlockPos below = pos.m_7495_();
               state = this.world.m_8055_(below);
               pos = below;
            }

            pos = pos.m_7494_();
         }
      } else {
         LOGGER.error("Structure start is null: {}", instance);
      }

      return pos;
   }

   public void setCompleted(WitherStormEntity entity, boolean completed) {
      nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance = this.get(entity.m_20148_());
      if (instance != null) {
         instance.setCompleted(completed);
      }
   }

   private void clean() {
      LOGGER.debug("Cleaning instances...");
      int successfullyCleaned = 0;

      for (nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance : this.instances) {
         WitherStormEntity storm = this.findStorm(instance.witherStorm);
         if (!instance.isCompleted()) {
            instance.setCompleted(storm == null || !storm.m_6084_());
            if (instance.isCompleted()) {
               if (instance.getStart() != null) {
                  instance.forceChunks(this.world, instance.getCenter(), false);
                  successfullyCleaned++;
               } else {
                  LOGGER.error("Bowels instance start is null! Cannot properly clean instance: {}", instance);
               }
            }
         }
      }

      LOGGER.debug("Finished cleaning; successfully cleaned {} instances", successfullyCleaned);
   }

   @Nullable
   public nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance getInstanceFromEntity(Entity entity) {
      double distance = -1.0;
      nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance finalInstance = null;

      for (nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance : this.instances) {
         double d0 = entity.m_20183_().m_123331_(instance.getPos());
         if (distance == -1.0 || d0 < distance) {
            distance = d0;
            finalInstance = instance;
         }
      }

      return finalInstance;
   }

   public void prepareArena(nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance, WitherStormEntity storm) {
      if (instance.getStart() != null) {
         Rotation rotation = ((StructurePiece)instance.getStart().m_73602_().get(0)).m_6830_();
         BlockPos pos = instance.getCenter().m_6630_(110);
         BlockPos offset = BlockPos.f_121853_.m_5487_(Axis.X, -3).m_7954_(rotation);
         pos = pos.m_121955_(offset);
         if (!instance.hasPreparedArena()) {
            ((ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(this.world, WitherStormModFeatures.BOWELS_PODIUM_FEATURE.getId()).m_203334_())
               .m_224953_(this.world, this.world.m_7726_().m_8481_(), RandomSource.m_216327_(), pos);
            int amount = 6 + this.random.nextInt(6);

            for (int i = 0; i < amount; i++) {
               this.trySpawnTentacle(50, pos);
            }

            this.spawnHeads(rotation, instance.getCenter());
            instance.setPreparedArena();
            LOGGER.debug("Prepared bowels arena for entity {}", storm);
         }

         if (instance.commandBlock == null) {
            CommandBlockEntity entity = this.createCommandBlock(pos, rotation, storm);
            instance.commandBlock = entity.m_20148_();
            this.world.m_7967_(entity);
         }
      } else {
         LOGGER.error("Structure start is null: {}", instance);
      }
   }

   private void trySpawnTentacle(int diameter, Vec3i center) {
      BlockPos pos = null;

      for (int i = 0; i < 10; i++) {
         int x = center.m_123341_() + this.random.nextInt(diameter) - diameter / 2;
         int z = center.m_123343_() + this.random.nextInt(diameter) - diameter / 2;
         int y = center.m_123342_();
         BlockPos currentPos = new BlockPos(x, y, z);

         for (int j = 0; j < 10 && this.world.m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); j++) {
            currentPos = currentPos.m_7495_();
         }

         List<TentacleEntity> nearbyTentacles = this.world.m_45976_(TentacleEntity.class, new AABB(currentPos).m_82400_(10.0));
         if (NaturalSpawner.m_47051_(Type.ON_GROUND, this.world, currentPos, (EntityType)WitherStormModEntityTypes.TENTACLE.get())
            && nearbyTentacles.isEmpty()
            && Math.sqrt(currentPos.m_123331_(center)) > 10.0) {
            pos = currentPos;
            break;
         }
      }

      if (pos != null) {
         TentacleEntity tentacle = (TentacleEntity)((EntityType)WitherStormModEntityTypes.TENTACLE.get())
            .m_262451_(this.world, null, null, pos, MobSpawnType.EVENT, false, false);
         if (tentacle != null && this.hasEnoughSpace(tentacle, pos)) {
            tentacle.setDormant(true);
            tentacle.lerpCurlTo(0.1F, 0.05F * (float)tentacle.m_217043_().m_188583_(), 8);
            this.world.m_47205_(tentacle);
         }
      }
   }

   private boolean hasEnoughSpace(Entity entity, BlockPos spawnPos) {
      for (BlockPos pos : BlockPos.m_121940_(spawnPos, spawnPos.m_7918_(1, 8, 1))) {
         if (!this.world.m_8055_(pos).m_60812_(this.world, pos).m_83281_()) {
            return false;
         }
      }

      return true;
   }

   private void spawnHeads(Rotation rotation, BlockPos structurePos) {
      BlockPos[] offsets = new BlockPos[]{NORTH_HEAD_POS, SOUTH_HEAD_POS};

      for (int i = 0; i < 2; i++) {
         BlockPos offset = offsets[i];
         offset = offset.m_7954_(rotation);
         BlockPos pos = structurePos.m_121955_(offset);
         WitherStormHeadEntity head = (WitherStormHeadEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM_HEAD.get())
            .m_262451_(this.world, null, null, pos, MobSpawnType.EVENT, false, false);
         float rot = rotate(rotation) + (i == 0 ? 180.0F : 0.0F);
         head.m_146922_(rot);
         head.m_146926_(60.0F);
         head.m_5618_(head.m_146908_());
         head.m_5616_(head.m_146908_());
         head.setActive(false);
         this.world.m_7967_(head);
      }
   }

   private CommandBlockEntity createCommandBlock(BlockPos pos, Rotation rotation, WitherStormEntity storm) {
      CommandBlockEntity entity = (CommandBlockEntity)((EntityType)WitherStormModEntityTypes.COMMAND_BLOCK.get()).m_20615_(this.world);
      entity.m_6034_(pos.m_123341_() + 0.5, pos.m_123342_(), pos.m_123343_() + 0.5);
      entity.setState(State.BOSSFIGHT);
      entity.setMode(Mode.TENTACLES);
      entity.setOwner(storm);
      entity.setOwnerUUID(storm.m_20148_());
      entity.m_146922_(rotate(rotation) + 90.0F);
      entity.m_5618_(entity.m_146908_());
      entity.m_5616_(entity.m_146908_());
      return entity;
   }

   private static float rotate(Rotation rotation) {
      switch (rotation) {
         case CLOCKWISE_180:
            return 180.0F;
         case COUNTERCLOCKWISE_90:
            return 270.0F;
         case CLOCKWISE_90:
            return 90.0F;
         default:
            return 0.0F;
      }
   }

   private void resetEmptyTimeIfNeeded() {
      for (nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance : this.instances) {
         if (!instance.isCompleted()) {
            this.world.m_8886_();
            break;
         }
      }
   }

   public static nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsEnterStatus enter(
      ServerLevel world, WitherStormEntity storm, Entity entity
   ) {
      nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsEnterStatus flag = nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsEnterStatus.ENTITY_CANNOT_CHANGE;
      if (entity.isAddedToWorld() && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_()) {
         ServerLevel bowels = WitherStormMod.bowels(world);
         nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager manager = (nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager)bowels.getCapability(
               WitherStormModCapabilities.BOWELS_MANAGER
            )
            .orElse(null);
         if (manager != null) {
            if (storm.m_6084_()) {
               nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance = manager.getOrCreateInstanceFor(storm);
               if (instance != null) {
                  manager.add(instance);
                  instance.setup(bowels);
                  manager.prepareArena(instance, storm);
                  BlockPos pos = manager.calculateEntrancePos(instance);
                  entity.changeDimension(bowels, new BowelsTeleporter(pos));
                  LOGGER.info("{} is entering the bowels of {}", entity, storm);
                  flag = nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsEnterStatus.SUCCESS;
               } else {
                  flag = nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsEnterStatus.CANT_SETUP_BOWELS;
                  LOGGER.error("Failed to setup bowels! It's likely the mod could not locate an available bowels arena or failed to do so.");
               }
            }

            manager.clean();
         }
      }

      return flag;
   }

   public static void leave(
      ServerLevel world, Entity entity, @Nullable nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance
   ) {
      if (entity.isAddedToWorld() && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_()) {
         world.getCapability(WitherStormModCapabilities.BOWELS_MANAGER)
            .ifPresent(
               manager -> {
                  nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance newInstance = instance;
                  if (newInstance == null) {
                     newInstance = manager.getInstanceFromEntity(entity);
                  }

                  if (newInstance != null) {
                     WitherStormEntity storm = manager.findStorm(newInstance.witherStorm);
                     if (storm != null) {
                        BlockPos pos = storm.m_20183_().m_6625_(5);
                        entity.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.makeInvulnerable(2400));
                        if (entity.changeDimension((ServerLevel)storm.m_9236_(), new BowelsTeleporter(pos)) instanceof LivingEntity living
                           && (Boolean)WitherStormModConfig.SERVER.bowelsFallResistance.get()) {
                           living.m_7292_(new MobEffectInstance(MobEffects.f_19606_, 120, 255, false, false, false));
                        }
                     } else {
                        ServerLevel overworld = world.m_7654_().m_129880_(Level.f_46428_);
                        entity.changeDimension(overworld, new BowelsTeleporter(overworld.m_220360_()));
                     }
                  } else {
                     ServerLevel overworld = world.m_7654_().m_129880_(Level.f_46428_);
                     entity.changeDimension(overworld, new BowelsTeleporter(overworld.m_220360_()));
                  }
               }
            );
      }
   }

   public static void queueEnter(ServerPlayer player, WitherStormEntity storm) {
      if (!player.m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation()) && player.m_6072_() && !player.m_20159_() && !player.m_20160_()) {
         ServerLevel bowels = WitherStormMod.bowels(player.m_284548_());
         bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.queuedEnter.putIfAbsent(storm.m_20148_(), player));
      }
   }

   public static void queueLeave(Entity entity) {
      queueLeave(entity, () -> {});
   }

   public static void queueLeave(Entity entity, Runnable action) {
      if (entity.m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation()) && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_()) {
         ServerLevel bowels = WitherStormMod.bowels((ServerLevel)entity.m_9236_());
         bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.queuedLeave.putIfAbsent(entity, action));
      }
   }

   public static Holder<Structure> getBowels(ServerLevel level) {
      return level.m_9598_().m_175515_(Registries.f_256944_).m_246971_(ResourceKey.m_135785_(Registries.f_256944_, WitherStormModStructures.BOWELS.getId()));
   }

   @Nullable
   private static StructureStart getAvailableStartAt(ServerLevel world, BlockPos pos) {
      ChunkAccess chunk = world.m_46819_(pos.m_123341_() >> 4, pos.m_123343_() >> 4, ChunkStatus.f_62315_);
      StructureStart start = world.m_215010_().m_220512_(SectionPos.m_175562_(chunk), (Structure)getBowels(world).m_203334_(), chunk);
      return start != null && start.m_73603_() ? start : null;
   }

   @SubscribeEvent
   public static void onDimensionLoad(Load event) {
      Level level = (Level)event.getLevel();
      if (!level.m_5776_() && level.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
         level.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.onLoad());
      }
   }

   @SubscribeEvent
   public static void onWorldTick(LevelTickEvent event) {
      if (event.level instanceof ServerLevel world && event.phase == Phase.START && world.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
         world.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
            manager.queuedEnter.forEach((uuid, player) -> {
               WitherStormEntity entityx = manager.findStorm(uuid);
               if (entityx != null) {
                  enter(world, entityx, player);
               }
            });
            manager.queuedEnter.clear();
            manager.queuedLeave.forEach((player, action) -> {
               leave(world, player, null);
               action.run();
            });
            manager.queuedLeave.clear();
            manager.resetEmptyTimeIfNeeded();
         });

         for (Entity entity : world.m_8583_()) {
            if (entity != null && entity.m_20186_() < 50.0) {
               queueLeave(entity);
            }
         }
      }
   }

   public enum BowelsEnterStatus {
      SUCCESS,
      CANT_SETUP_BOWELS,
      ENTITY_CANNOT_CHANGE;
   }

   public static class BowelsInstance {
      private static final TicketType<ChunkPos> BOWELS = TicketType.m_9462_("witherstormmod:bowels", Comparator.comparingLong(ChunkPos::m_45588_));
      @Nullable
      private Pair<BlockPos, StructureStart> start;
      private final UUID witherStorm;
      @Nullable
      private UUID commandBlock;
      private boolean hasPreparedArena;
      private boolean isCompleted;

      public BowelsInstance(Pair<BlockPos, StructureStart> start, UUID storm) {
         this.start = start;
         this.witherStorm = storm;
      }

      public BowelsInstance(BlockPos pos, UUID storm) {
         this.start = Pair.of(pos, null);
         this.witherStorm = storm;
      }

      public void setup(ServerLevel world) {
         if (!world.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
            nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.LOGGER.error("Cannot setup {} in {}", this, world.m_46472_());
         } else {
            if (this.getStart() == null) {
               StructureStart start = nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.getAvailableStartAt(world, this.getPos());
               if (start == null) {
                  nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.LOGGER
                     .error(
                        "Could not find saved structure start or start is not valid from {}. It is important that these values aren't modified!", this.getPos()
                     );
                  return;
               }

               this.start = Pair.of(this.getPos(), start);
            }

            nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.LOGGER.debug("Successfully setup this bowels instance {}", this);
         }
      }

      public void doChunkLoading(ServerLevel level) {
         if (!this.isCompleted()) {
            this.forceChunks(level, this.getCenter(), true);
         } else {
            this.forceChunks(level, this.getCenter(), false);
         }
      }

      private void forceChunks(ServerLevel world, BlockPos pos, boolean force) {
         ChunkPos chunkPos = new ChunkPos(pos);
         if (force) {
            nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.LOGGER.debug("Loaded chunks in arena");
            world.m_7726_().addRegionTicket(BOWELS, chunkPos, 3, chunkPos, true);
         } else {
            nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.LOGGER.debug("Unloaded chunks in arena");
            world.m_7726_().m_8438_(BOWELS, chunkPos, 3, chunkPos);
         }
      }

      public CompoundTag write() {
         CompoundTag compound = new CompoundTag();
         compound.m_128365_("Pos", NbtUtils.m_129224_(this.getPos()));
         compound.m_128362_("Storm", this.witherStorm);
         if (this.commandBlock != null) {
            compound.m_128362_("CommandBlock", this.commandBlock);
         }

         compound.m_128379_("HasPreparedArena", this.hasPreparedArena);
         compound.m_128379_("Completed", this.isCompleted);
         return compound;
      }

      public static nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance read(CompoundTag compound) {
         BlockPos pos = NbtUtils.m_129239_(compound.m_128469_("Pos"));
         UUID storm = compound.m_128342_("Storm");
         UUID commandBlock = null;
         if (compound.m_128441_("CommandBlock")) {
            commandBlock = compound.m_128342_("CommandBlock");
         }

         nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance instance = new nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance(
            pos, storm
         );
         instance.commandBlock = commandBlock;
         instance.hasPreparedArena = compound.m_128471_("HasPreparedArena");
         instance.isCompleted = compound.m_128471_("Completed");
         return instance;
      }

      public void setCompleted(boolean completed) {
         this.isCompleted = completed;
      }

      public boolean isCompleted() {
         return this.isCompleted;
      }

      public void setPreparedArena() {
         this.hasPreparedArena = true;
      }

      public boolean hasPreparedArena() {
         return this.hasPreparedArena;
      }

      public UUID getCommandBlockUUID() {
         return this.commandBlock;
      }

      public BlockPos getPos() {
         return (BlockPos)this.start.getFirst();
      }

      @Nullable
      public BlockPos getCenter() {
         StructureStart start = this.getStart();
         if (start != null) {
            BlockPos center = ((StructurePiece)start.m_73602_().get(0)).m_73547_().m_162394_();
            return new BlockPos(center.m_123341_(), 0, center.m_123343_());
         } else {
            return null;
         }
      }

      @Nullable
      public StructureStart getStart() {
         return (StructureStart)this.start.getSecond();
      }

      @Override
      public String toString() {
         return "BowelsInstance[start = "
            + this.start
            + ", pos = "
            + this.getPos()
            + ", storm = "
            + this.witherStorm
            + ", commandBlock = "
            + this.commandBlock
            + ", hasPreparedArena = "
            + this.hasPreparedArena
            + ", completed = "
            + this.isCompleted
            + "]";
      }
   }
}
