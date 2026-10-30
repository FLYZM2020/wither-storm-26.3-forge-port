package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.FullChunkStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.PacketTarget;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModFeatures;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.PlayAdditionalLoopingSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveAdditionalLoopingSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdatePlayDeadManagerMessage;
import nonamecrackers2.witherstormmod.common.util.DebrisCluster;
import nonamecrackers2.witherstormmod.common.util.DebrisRingSettings;
import nonamecrackers2.witherstormmod.common.world.gen.feature.CommandBlockPodiumFeature;

public class PlayDeadManager {
   protected final WitherStormEntity entity;
   protected nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State state = nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State.NORMAL_BEHAVIOR;
   protected int ticksSinceInit;
   protected int ticksSinceInitO;
   protected final int updateInterval = 120;
   protected int totalTickCount;
   @Nullable
   protected FormidibombEntity formidibomb;
   @Nullable
   protected BlockPos podiumPos;
   protected boolean podiumPlaced;
   @Nullable
   protected CommandBlockEntity commandBlock;
   protected int revivalTicks;
   protected boolean hasRecentlyBeenRevived;
   protected int revivalPlayerProtection = (Integer)WitherStormModConfig.SERVER.revivalPlayerProtection.get();
   protected int timeSinceCommandBlockMissing;

   public PlayDeadManager(WitherStormEntity entity) {
      this.entity = entity;
   }

   public void tick() {
      Level world = this.entity.m_9236_();
      this.getState().tick(world, this.entity, this);
      this.totalTickCount++;
      if (this.totalTickCount % 120 == 0) {
         this.sendChanges(PacketDistributor.DIMENSION.with(() -> this.entity.m_9236_().m_46472_()), true);
      }

      if (this.revivalTicks > this.revivalPlayerProtection * 1200) {
         this.hasRecentlyBeenRevived = false;
      }
   }

   public void setState(nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State state) {
      if (this.state != state) {
         this.state.finish(this.entity.m_9236_(), this.entity, this, state);
         this.state = state;
         this.state.init(this.entity.m_9236_(), this.entity, this);
         this.updateSegments();
         this.sendChanges(PacketDistributor.DIMENSION.with(() -> this.entity.m_9236_().m_46472_()), false);
      }
   }

   public void setStateRaw(nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State state) {
      if (this.state != state) {
         this.state = state;
         this.sendChanges(PacketDistributor.DIMENSION.with(() -> this.entity.m_9236_().m_46472_()), false);
      }
   }

   public void nextState() {
      nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State next = null;
      if (this.state.ordinal() + 1 < nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State.values().length) {
         next = nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State.values()[this.state.ordinal() + 1];
      }

      if (next != null) {
         this.state.finish(this.entity.m_9236_(), this.entity, this, next);
         this.state = next;
         this.state.init(this.entity.m_9236_(), this.entity, this);
         this.updateSegments();
         this.sendChanges(PacketDistributor.DIMENSION.with(() -> this.entity.m_9236_().m_46472_()), false);
      }
   }

   public void updateSegments() {
      this.entity.getSegmentsManager().ifPresent(segments -> {
         for (WitherStormSegmentEntity segment : segments.getSegments()) {
            if (segment != null) {
               segment.getPlayDeadManager().setState(this.getState());
            }
         }
      });
   }

   public nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State getState() {
      return this.state;
   }

   public void explode() {
      this.setState(nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State.FALLING);
      if (!this.entity.m_9236_().f_46443_) {
         for (Player player : this.entity.m_9236_().m_45955_(TargetingConditions.m_148353_(), null, this.entity.getSearchBox().m_82400_(100.0))) {
            WitherStormModCriteriaTriggers.PLAY_DEAD_TRIGGER.trigger((ServerPlayer)player, this.entity);
         }
      }
   }

   public void revive() {
      this.setState(nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State.REVIVING);
      if (!this.entity.m_9236_().f_46443_) {
         for (Player player : this.entity.m_9236_().m_45955_(TargetingConditions.m_148353_(), null, this.entity.getSearchBox().m_82400_(100.0))) {
            WitherStormModCriteriaTriggers.REVIVAL_TRIGGER.trigger((ServerPlayer)player, this.entity);
         }
      }
   }

   public void sendChanges(PacketTarget target, boolean updateTick) {
      if (!this.entity.m_9236_().f_46443_) {
         UpdatePlayDeadManagerMessage message = new UpdatePlayDeadManagerMessage(this.entity.m_19879_(), this, updateTick);
         WitherStormModPacketHandlers.MAIN.send(target, message);
         this.getState().sendAdditionalPackets(this.entity.m_9236_(), this.entity, this);
      }
   }

   public int getTicks() {
      return this.ticksSinceInit;
   }

   public void setTickAmount(int amount) {
      this.ticksSinceInit = amount;
   }

   public void setTickAmountAndO(int amount) {
      this.ticksSinceInit = amount;
      this.ticksSinceInitO = amount;
   }

   public void setFormidibomb(@Nullable FormidibombEntity entity) {
      this.formidibomb = entity;
   }

   @Nullable
   public FormidibombEntity getFormidibomb() {
      return this.formidibomb;
   }

   @Nullable
   public BlockPos getPodiumPos() {
      return this.podiumPos;
   }

   public void setPodiumPos(@Nullable BlockPos pos) {
      this.podiumPos = pos;
   }

   public boolean isPodiumPlaced() {
      return this.podiumPlaced;
   }

   public void setPodiumPlaced(boolean placed) {
      this.podiumPlaced = placed;
   }

   public boolean isPodiumAreaLoaded(BlockPos pos) {
      for (int x = -3; x <= 3; x++) {
         for (int z = -3; z <= 3; z++) {
            ChunkPos chunkPos = new ChunkPos(pos);
            ChunkAccess chunk = this.entity.m_9236_().m_6522_(chunkPos.f_45578_ + x, chunkPos.f_45579_ + z, ChunkStatus.f_62326_, false);
            if (!(chunk instanceof LevelChunk)) {
               return false;
            }

            FullChunkStatus type = ((LevelChunk)chunk).m_287138_();
            if (!type.m_287205_(FullChunkStatus.BLOCK_TICKING)) {
               return false;
            }
         }
      }

      return true;
   }

   public void placePodium() {
      if (!this.entity.m_9236_().f_46443_) {
         float yBodyRot = (this.entity.f_20883_ - 90.0F) * (float) (Math.PI / 180.0);
         float x = Mth.m_14089_(yBodyRot) * 5.0F;
         float z = Mth.m_14031_(yBodyRot) * 5.0F;
         BlockPos pos = this.entity.m_20183_().m_7918_((int)x, -4, (int)z);
         if (!this.isPodiumPlaced() && this.isPodiumAreaLoaded(pos)) {
            ServerLevel world = (ServerLevel)this.entity.m_9236_();
            if (((ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).m_203334_())
               .m_224953_(world, world.m_7726_().m_8481_(), this.entity.m_217043_(), pos)) {
               this.setPodiumPos(pos);
               this.setPodiumPlaced(true);
               if (this.getCommandBlock() == null || !this.getCommandBlock().m_6084_()) {
                  CommandBlockEntity entity = new CommandBlockEntity(world, this.entity, pos.m_123341_() + 0.5, pos.m_123342_() + 11.0, pos.m_123343_() + 0.5);
                  this.setCommandBlock(entity);
                  world.m_7967_(entity);
               }
            }
         }
      }
   }

   public void removePodium() {
      if (!this.entity.m_9236_().f_46443_) {
         BlockPos pos = this.getPodiumPos();
         if (pos != null && this.isPodiumPlaced() && this.isPodiumAreaLoaded(pos)) {
            ServerLevel world = (ServerLevel)this.entity.m_9236_();
            ConfiguredFeature<NoneFeatureConfiguration, CommandBlockPodiumFeature> feature = (ConfiguredFeature<NoneFeatureConfiguration, CommandBlockPodiumFeature>)WitherStormModFeatures.getConfiguredFeature(
                  world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()
               )
               .m_203334_();
            if (((CommandBlockPodiumFeature)feature.f_65377_())
               .remove(world, world.m_7726_().m_8481_(), this.entity.m_217043_(), pos, (NoneFeatureConfiguration)feature.f_65378_())) {
               this.setPodiumPos(null);
               this.setPodiumPlaced(false);
            }

            if (this.getCommandBlock() != null) {
               CommandBlockEntity entity = this.getCommandBlock();
               entity.m_146870_();
               this.setCommandBlock(null);
            }
         }
      }
   }

   @Nullable
   public CommandBlockEntity getCommandBlock() {
      return this.commandBlock;
   }

   public void setCommandBlock(@Nullable CommandBlockEntity entity) {
      this.commandBlock = entity;
   }

   public int getTicksSinceRevival() {
      return this.revivalTicks;
   }

   public void setTicksSinceRevival(int ticks) {
      this.revivalTicks = ticks;
   }

   public void setRecentlyRevived(boolean flag) {
      this.hasRecentlyBeenRevived = flag;
   }

   public boolean hasRecentlyBeenRevived() {
      return this.hasRecentlyBeenRevived;
   }

   public int getRevivalPlayerProtectionTime() {
      return this.revivalPlayerProtection;
   }

   public void setRevivalPlayerProtectionTime(int time) {
      this.revivalPlayerProtection = time;
   }

   public int getTicksSinceCommandBlockMissing() {
      return this.timeSinceCommandBlockMissing;
   }

   public void setTicksSinceCommandBlockMissing(int time) {
      this.timeSinceCommandBlockMissing = time;
   }

   public enum State {
      NORMAL_BEHAVIOR {
         @Override
         public void tick(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
            super.tick(world, entity, manager);
            float percentage = Math.min(manager.ticksSinceInit, 80) / 80.0F;

            for (DebrisRingSettings settings : entity.getDebrisRings()) {
               settings.setAlpha(percentage);
            }

            entity.setShineAlpha(percentage);
         }

         @Override
         public void finish(
            Level world,
            WitherStormEntity entity,
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager,
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State next
         ) {
            for (Mob mob : world.m_45976_(Mob.class, entity.getSearchBox())) {
               if (mob.m_5448_() == entity) {
                  mob.m_6710_(null);
               }
            }
         }
      },
      FALLING {
         @Override
         public void tick(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
            super.tick(world, entity, manager);
            if (!world.f_46443_) {
               if (manager.getTicks() % 8 == 0 && !(entity instanceof WitherStormSegmentEntity)) {
                  for (int i = 0; i < 3; i++) {
                     entity.dropSmallMassCluster(1);
                  }
               }

               if (entity.getSegmentsManager().isPresent() && manager.getTicks() == 201) {
                  SegmentsManager segmentsManager = (SegmentsManager)entity.getSegmentsManager().get();
                  if (entity.getPhase() == 5) {
                     entity.setPhase(6);
                     entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SPLITS.get(), 1.0F, 1.0F);
                     entity.setOtherHeadsDisabled(true);
                     segmentsManager.createSegments();
                     segmentsManager.addSegments();
                  }
               }
            }

            if (!entity.m_20096_()) {
               float xOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
               float yOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
               float zOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
               world.m_7106_(ParticleTypes.f_123813_, entity.m_20185_() + xOffset, entity.m_20188_() + yOffset, entity.m_20189_() + zOffset, -5.0, 0.0, 0.0);
            }

            float percentage = (120.0F - Math.min(manager.ticksSinceInit, 120)) / 120.0F;

            for (DebrisRingSettings settings : entity.getDebrisRings()) {
               settings.setAlpha(percentage);
            }

            entity.setShineAlpha(percentage);
         }

         @Override
         public void init(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
            super.init(world, entity, manager);
            if (!world.f_46443_) {
               for (WitherStormHead head : entity.getHeadManager().getHeads()) {
                  head.doRoar(entity.m_217043_().m_188499_());
               }

               if (entity.shouldPlaySoundLoop) {
                  PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(
                     entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get()
                  );
                  WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
               }
            }

            for (WitherStormHead head : entity.getHeadManager().getHeads()) {
               head.lerpHeadTo(-50.0F, entity.f_20883_, 64.0F);
            }
         }

         @Override
         public void finish(
            Level world,
            WitherStormEntity entity,
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager,
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State next
         ) {
            super.finish(world, entity, manager, next);
            if (!world.f_46443_ && entity.shouldPlaySoundLoop) {
               RemoveAdditionalLoopingSoundMessage message = new RemoveAdditionalLoopingSoundMessage(entity);
               WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> world.m_46472_()), message);
            }
         }

         @Override
         public boolean disablesAi() {
            return true;
         }

         @Override
         public boolean isPastInterval(
            Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager
         ) {
            return entity.m_20096_();
         }

         @Override
         public void sendAdditionalPackets(
            Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager
         ) {
            super.sendAdditionalPackets(world, entity, manager);
            if (entity.shouldPlaySoundLoop) {
               PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(
                  entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get()
               );
               WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
            }
         }
      },
      PLAYING_DEAD {
         @Override
         public void tick(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
            super.tick(world, entity, manager);
            if (entity.isOnBack() && entity.m_6084_() && !entity.m_21224_()) {
               manager.placePodium();
               BlockPos pos = manager.getPodiumPos();
               if (pos != null) {
                  entity.spawnConsumedPets(new Vec3(pos.m_123341_() + 0.5, pos.m_123342_() + 12.0, pos.m_123343_() + 0.5));
               }
            }

            for (DebrisRingSettings settings : entity.getDebrisRings()) {
               settings.setAlpha(0.0F);
            }

            entity.setShineAlpha(0.0F);
            if (!world.f_46443_) {
               CommandBlockEntity commandBlock = manager.getCommandBlock();
               if (commandBlock != null && (!commandBlock.m_6084_() || commandBlock.m_20270_(entity) > 64.0)) {
                  manager.timeSinceCommandBlockMissing++;
               }
            }
         }

         @Override
         public boolean isPastInterval(
            Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager
         ) {
            return manager.timeSinceCommandBlockMissing > 200;
         }

         @Override
         public boolean disablesAi() {
            return true;
         }

         @Override
         public void init(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
            super.init(world, entity, manager);
            manager.timeSinceCommandBlockMissing = 0;

            for (WitherStormHead head : entity.getHeadManager().getHeads()) {
               head.lerpHeadTo(40.0F, entity.f_20883_, 16.0F);
            }

            if (!world.f_46443_ && entity.getSegmentsManager().isPresent()) {
               SegmentsManager segmentsManager = (SegmentsManager)entity.getSegmentsManager().get();
               if (entity.getPhase() == 5) {
                  entity.setPhase(6);
                  entity.setOtherHeadsDisabled(true);
                  segmentsManager.createSegments();
                  segmentsManager.addSegments();
               }
            }
         }

         @Override
         public void finish(
            Level world,
            WitherStormEntity entity,
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager,
            nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State next
         ) {
            manager.timeSinceCommandBlockMissing = 0;
            if (next != nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State.REVIVING) {
               manager.removePodium();
            }
         }
      },
      REVIVING {
         @Override
         public void tick(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
            super.tick(world, entity, manager);
            if (!world.f_46443_ && manager.getTicks() > 20) {
               entity.shake(60.0F, 4.0F);
               BlockPos pos = manager.getPodiumPos();
               if (pos != null) {
                  world.m_254849_(entity, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), 16.0F, ExplosionInteraction.NONE);
               }

               world.m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
               manager.setState(nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State.NORMAL_BEHAVIOR);
               manager.removePodium();
            }
         }

         @Override
         public void init(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
            manager.setRecentlyRevived(true);
            super.init(world, entity, manager);
            if (!world.f_46443_ && entity.shouldPlayGlobalSounds) {
               entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_REACTIVATES.get(), 10.0F, 1.0F);
            }
         }
      };

      public void tick(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
         manager.ticksSinceInitO = manager.ticksSinceInit++;
         if (!this.disablesAi()) {
            manager.revivalTicks++;
         }

         if (this.isPastInterval(world, entity, manager)) {
            manager.nextState();
         }

         if (this.disablesAi()) {
            if (manager.ticksSinceInit % 5 == 0) {
               int size = Math.max(10, entity.m_217043_().m_188503_(15));

               for (int i = 0; i < size; i++) {
                  for (DebrisCluster cluster : entity.getDebrisClusters()) {
                     if (!cluster.isDisabled()) {
                        cluster.setDisabled(entity.m_217043_().m_188503_(entity.getDebrisClusters().size()) == 0);
                     }
                  }
               }
            }
         } else if (manager.ticksSinceInit % 10 == 0) {
            int size = Math.max(10, entity.m_217043_().m_188503_(15));

            for (int i = 0; i < size; i++) {
               for (DebrisCluster cluster : entity.getDebrisClusters()) {
                  if (cluster.isDisabled() && entity.m_217043_().m_188503_(entity.getDebrisClusters().size()) == 0) {
                     cluster.setDisabled(false);
                  }
               }
            }
         }
      }

      public void init(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
         manager.ticksSinceInit = 0;
         manager.ticksSinceInitO = 0;
         if (this.disablesAi()) {
            manager.revivalTicks = 0;
            manager.setRecentlyRevived(false);
         }

         if (!world.f_46443_) {
            if (this.disablesAi()) {
               entity.getTrackedEntities().clearAndMakeAllFall();
               if (entity.shouldPlaySoundLoop) {
                  RemoveSoundLoopMessage message = new RemoveSoundLoopMessage(entity);
                  WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> world.m_46472_()), message);
               }
            } else {
               for (WitherStormHead head : entity.getHeadManager().getHeads()) {
                  head.doRoar(entity.m_217043_().m_188499_());
               }
            }

            entity.getBossInfo().ifPresent(info -> info.m_8321_(!this.disablesAi()));
         }

         entity.m_6210_();
      }

      public void finish(
         Level world,
         WitherStormEntity entity,
         nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager,
         nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager.State next
      ) {
      }

      public boolean disablesAi() {
         return false;
      }

      public boolean isPastInterval(Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager) {
         return false;
      }

      public void sendAdditionalPackets(
         Level world, WitherStormEntity entity, nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager manager
      ) {
      }
   }
}
