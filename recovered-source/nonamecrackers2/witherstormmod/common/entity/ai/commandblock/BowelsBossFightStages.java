package nonamecrackers2.witherstormmod.common.entity.ai.commandblock;

import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightPhase;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.BlindScreenMessage;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.util.EquipmentHelper;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class BowelsBossFightStages {
   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> WAVE_1_MOBS = SimpleWeightedRandomList.m_146263_()
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 15)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), 10)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), 8)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 2)
      .m_146270_();
   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> WAVE_2_MOBS = SimpleWeightedRandomList.m_146263_()
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 10)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), 10)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), 8)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 8)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 4)
      .m_146270_();
   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> WAVE_3_MOBS = SimpleWeightedRandomList.m_146263_()
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 10)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), 10)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get(), 8)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get(), 5)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get(), 5)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 10)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 5)
      .m_146270_();
   public static final BossfightPhase<CommandBlockEntity> IDLE = BossfightPhase.blank();
   public static final BossfightPhase<CommandBlockEntity> HIT = new BossfightPhase(entity -> {
      WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new ShakeScreenMessage(240.0F, 12.0F));
      entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.LOUD_TREMBLE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
      entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.BOWELS_LOUD_HURT.get(), SoundSource.HOSTILE, 1.0F, 1.0F);

      for (TentacleEntity tentacle : entity.getTentacleStructure().tentacleStructure) {
         if (tentacle != null && tentacle.m_6084_()) {
            tentacle.setDormant(false);
            tentacle.doAwakeAnimation();
         }
      }

      if (entity.m_21223_() / entity.m_21233_() >= 0.75F) {
         entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_REACTIVATES.get(), 64.0F, 1.0F);
      }
   }, 60);
   public static final BossfightPhase<CommandBlockEntity> MOVE_PODIUM = new BossfightPhase(entity -> {
      entity.createPodiumCluster();
      WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new ShakeScreenMessage(120.0F, 12.0F));
      entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.LOUD_TREMBLE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
   }, 100).setTickAction((time, entity) -> {
      entity.findPodiumCluster();
      Vec3 delta = new Vec3(0.0, 0.05, 0.0);
      entity.movePodiumCluster(delta);
      entity.m_6478_(MoverType.SELF, delta);
   }).setFinishAction(entity -> {
      BlockClusterEntity cluster = entity.getPodiumCluster();
      Vec3 pos = Vec3.m_82539_(entity.m_20183_());
      if (cluster != null && cluster.m_6084_()) {
         Vec3 clusterPos = pos.m_82520_(0.0, cluster.m_20186_() - entity.m_20186_() + 1.0, 0.0);
         cluster.m_6034_(clusterPos.f_82479_, clusterPos.f_82480_, clusterPos.f_82481_);
         cluster.place();
      }

      entity.m_6034_(pos.f_82479_, pos.f_82480_, pos.f_82481_);
      entity.podiumCluster = null;
      entity.podiumClusterUUID = null;
   });
   public static final BossfightPhase<CommandBlockEntity> WAIT = BossfightPhase.blank().setFixedTime(20);
   public static final BossfightPhase<CommandBlockEntity> MOB_WAVE_1 = new BossfightPhase(
         entity -> {
            entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 5.0F, 1.0F);
            ((ServerLevel)entity.m_9236_())
               .m_8767_(
                  (SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
                  entity.m_20185_(),
                  entity.m_20188_(),
                  entity.m_20189_(),
                  60,
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  0.2
               );
         },
         100
      )
      .setTickAction((time, entity) -> {
         if (time % 8 == 0) {
            Mob mob = entity.summonRandomMob(50, WAVE_1_MOBS);
            if (mob != null) {
               ServerLevel serverLevel = (ServerLevel)entity.m_9236_();
               DifficultyInstance difficulty = serverLevel.m_6436_(mob.m_20183_());
               mob.m_21051_(Attributes.f_22276_).m_22125_(new AttributeModifier("Extra health final bossfight", 2.0, Operation.ADDITION));
               if (WitherSickened.CAN_WEAR_ARMOR.test(mob) && mob instanceof Monster monster && entity.m_217043_().m_188500_() >= 0.5) {
                  EquipmentHelper.applyEquipment(monster, difficulty, false);
               }
            }
         }
      })
      .setFinishAction(entity -> entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_POWER_DOWN.get(), 5.0F, 1.0F));
   public static final BossfightPhase<CommandBlockEntity> MOB_WAVE_2 = new BossfightPhase(
         entity -> {
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new ShakeScreenMessage(120.0F, 8.0F));
            entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 5.0F, 1.0F);
            ((ServerLevel)entity.m_9236_())
               .m_8767_(
                  (SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
                  entity.m_20185_(),
                  entity.m_20188_(),
                  entity.m_20189_(),
                  60,
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  0.2
               );

            for (Entity nearby : entity.m_9236_().m_45976_(TentacleEntity.class, entity.m_20191_().m_82400_(50.0))) {
               TentacleEntity tentacle = (TentacleEntity)nearby;
               tentacle.setDormant(false);
               tentacle.doAwakeAnimation();
            }
         },
         100
      )
      .setTickAction((time, entity) -> {
         if (time % 10 == 0) {
            Mob mob = entity.summonRandomMob(50, WAVE_2_MOBS);
            if (mob != null) {
               ServerLevel serverLevel = (ServerLevel)entity.m_9236_();
               DifficultyInstance difficulty = serverLevel.m_6436_(mob.m_20183_());
               mob.m_21051_(Attributes.f_22276_).m_22125_(new AttributeModifier("Extra health final bossfight", 4.0, Operation.ADDITION));
               if (WitherSickened.CAN_WEAR_ARMOR.test(mob) && mob instanceof Monster monster) {
                  EquipmentHelper.applyEquipment(monster, difficulty, false);
               }
            }
         }
      })
      .setFinishAction(
         entity -> {
            entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_POWER_DOWN.get(), 5.0F, 1.0F);
            BlockPos pos = entity.getRandomNearbyPos((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get(), 50, 20);
            if (pos != null) {
               ServerLevel world = (ServerLevel)entity.m_9236_();
               WitheredSymbiontEntity symbiont = (WitheredSymbiontEntity)((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get())
                  .m_262455_(world, (CompoundTag)null, null, pos, MobSpawnType.EVENT, false, false);
               symbiont.setNonBossMode(true);
               symbiont.setRushMode(true);
               symbiont.m_21051_(Attributes.f_22276_)
                  .m_22125_(new AttributeModifier("Withered symbiont final boss battle low health", -0.5, Operation.MULTIPLY_BASE));
               symbiont.m_21530_();
               symbiont.m_21153_(symbiont.m_21233_());
               world.m_8767_(
                  (SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
                  symbiont.m_20185_(),
                  symbiont.m_20188_(),
                  symbiont.m_20189_(),
                  40,
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  0.2
               );
               world.m_8767_(
                  ParticleTypes.f_123755_,
                  symbiont.m_20185_(),
                  symbiont.m_20188_(),
                  symbiont.m_20189_(),
                  40,
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  0.01
               );
               symbiont.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_SPAWN.get(), 4.0F, 1.0F);
            }
         }
      );
   public static final BossfightPhase<CommandBlockEntity> MOB_WAVE_3 = new BossfightPhase(
         entity -> {
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new ShakeScreenMessage(120.0F, 16.0F));
            entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 6.0F, 1.0F);
            ((ServerLevel)entity.m_9236_())
               .m_8767_(
                  (SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
                  entity.m_20185_(),
                  entity.m_20188_(),
                  entity.m_20189_(),
                  80,
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  entity.m_217043_().m_188583_(),
                  0.2
               );

            for (Entity nearby : entity.m_9236_().m_45976_(Entity.class, entity.m_20191_().m_82400_(50.0))) {
               if (nearby instanceof TentacleEntity tentacle) {
                  tentacle.setDormant(false);
                  tentacle.doAwakeAnimation();
               } else if (nearby instanceof WitherStormHeadEntity head) {
                  head.setActive(true);
                  head.setRoar(false);
                  head.setRoarTime(40);
               }
            }
         },
         120
      )
      .setTickAction((time, entity) -> {
         if (time % 5 == 0) {
            Mob mob = entity.summonRandomMob(50, WAVE_3_MOBS);
            if (mob != null) {
               ServerLevel serverLevel = (ServerLevel)entity.m_9236_();
               DifficultyInstance difficulty = serverLevel.m_6436_(mob.m_20183_());
               mob.m_21051_(Attributes.f_22276_).m_22125_(new AttributeModifier("Extra health final bossfight", 8.0, Operation.ADDITION));
               if (WitherSickened.CAN_WEAR_ARMOR.test(mob) && mob instanceof Monster monster) {
                  EquipmentHelper.applyEquipment(monster, difficulty, true);
               }
            }
         }
      })
      .setFinishAction(entity -> entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_POWER_DOWN.get(), 6.0F, 1.0F));
   public static final BossfightPhase<CommandBlockEntity> PROTECT_IDLE = BossfightPhase.copyOf(IDLE, entity -> {
      for (TentacleEntity tentacle : entity.getTentacleStructure().tentacleStructure) {
         if (tentacle != null && tentacle.m_6084_()) {
            tentacle.curlAround(entity.m_20182_());
         }
      }
   }, entity -> {
      if (!WorldUtil.areaLoaded(entity.m_9236_(), entity.m_20183_(), 2)) {
         return false;
      }

      boolean flag = true;

      for (Mob nearby : entity.m_9236_().m_45976_(Mob.class, entity.m_20191_().m_82400_(50.0))) {
         if (nearby instanceof WitherStormHeadEntity head) {
            if (head.m_6084_() && !head.isPlayingDead() && !head.isHurt()) {
               flag = false;
            }
         } else if (nearby instanceof WitheredSymbiontEntity symbiont && !symbiont.m_21224_()) {
            flag = false;
         }
      }

      return flag;
   }).setTickAction((time, entity) -> {
      if (time % 40 == 0) {
         for (TentacleEntity tentacle : entity.getTentacleStructure().tentacleStructure) {
            if (tentacle != null && tentacle.m_6084_() && !tentacle.isDoingSwingAttack()) {
               tentacle.curlAround(entity.m_20182_());
            }
         }
      }
   }).setFinishAction(entity -> {
      for (TentacleEntity tentacle : entity.getTentacleStructure().tentacleStructure) {
         if (tentacle != null && tentacle.m_6084_()) {
            tentacle.stopCurlingAround();
         }
      }
   });
   public static final BossfightPhase<CommandBlockEntity> DEATH = new BossfightPhase(entity -> {
         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new ShakeScreenMessage(240.0F, 14.0F));
         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new BlindScreenMessage(240, 120, 80));
         entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.LOUD_TREMBLE.get(), SoundSource.AMBIENT, 5.0F, 1.0F);
         entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.BOWELS_LOUD_HURT.get(), SoundSource.HOSTILE, 5.0F, 1.0F);
         entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DESTRUCT.get(), 64.0F, 1.0F);

         for (Entity nearby : entity.m_9236_().m_45976_(Entity.class, entity.m_20191_().m_82400_(50.0))) {
            if (nearby.m_6084_()) {
               if (nearby instanceof TentacleEntity tentacle) {
                  tentacle.doIndefiniteAwakeAnimation();
                  tentacle.setCanSwing(false);
                  tentacle.setCanStrangle(false);
               } else if (nearby instanceof WitherStormHeadEntity head) {
                  head.m_6074_();
               } else if (nearby instanceof WitheredSymbiontEntity || nearby instanceof WitherSickened) {
                  nearby.m_6074_();
               }
            }
         }
      }, entity -> false)
      .setFinishAction(
         entity -> {
            WitherStormEntity storm = entity.getOwner();
            if (storm != null && !storm.m_213877_()) {
               if (entity.killer != null) {
                  if (entity.killer instanceof Player) {
                     storm.m_6469_(WitherStormModDamageTypes.playerAttackWitherStorm((Player)entity.killer), Float.MAX_VALUE);
                  } else {
                     storm.m_6469_(WitherStormModDamageTypes.mobAttackWitherStorm(entity.killer), Float.MAX_VALUE);
                  }
               } else {
                  storm.m_6469_(storm.m_269291_().m_269341_(), Float.MAX_VALUE);
               }

               for (ServerPlayer player : entity.m_9236_().m_45976_(ServerPlayer.class, entity.m_20191_().m_82400_(150.0))) {
                  if (player != entity.killer) {
                     player.m_36246_(Stats.f_12986_.m_12902_(storm.m_6095_()));
                  }

                  if (player.m_8963_().equals(WitherStormMod.bowels(player.m_284548_()).m_46472_())) {
                     player.m_9158_(null, null, 0.0F, false, false);
                  }

                  player.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(WitherSicknessTracker::cure);
                  List<TamableAnimal> playersPets = player.m_9236_()
                     .m_45976_(TamableAnimal.class, entity.m_20191_().m_82400_(150.0))
                     .stream()
                     .filter(animal -> player.m_20148_().equals(animal.m_21805_()))
                     .collect(Collectors.toList());
                  playersPets.forEach(animal -> WitherStormBowelsManager.leave(player.m_284548_(), animal, null));
                  WitherStormBowelsManager.queueLeave(
                     player,
                     () -> {
                        player.f_8906_
                           .m_9829_(
                              new ClientboundSoundPacket(
                                 (Holder)ForgeRegistries.SOUND_EVENTS.getHolder((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_DEATH.get()).get(),
                                 SoundSource.HOSTILE,
                                 player.m_20185_(),
                                 player.m_20186_(),
                                 player.m_20189_(),
                                 1.0F,
                                 1.0F,
                                 entity.m_217043_().m_188505_()
                              )
                           );
                        if (storm != null) {
                           for (int i = 0; i < 10; i++) {
                              float angle = (storm.f_20883_ + 90.0F) * (float) (Math.PI / 180.0);
                              double x = Mth.m_14089_(angle) * 100.0 + storm.m_20185_();
                              double z = Mth.m_14031_(angle) * 100.0 + storm.m_20189_();
                              x += storm.m_217043_().m_188583_() * 5.0;
                              z += storm.m_217043_().m_188583_() * 5.0;
                              BlockPos pos = BlockPos.m_274561_(x, 0.0, z);
                              pos = storm.m_9236_().m_5452_(Types.MOTION_BLOCKING, pos).m_7495_();
                              BlockState state = storm.m_9236_().m_8055_(pos);
                              if (state.m_60783_(storm.m_9236_(), pos, Direction.UP)) {
                                 Vec3 finalPos = Vec3.m_82514_(pos, 1.0);
                                 player.m_20219_(finalPos);
                                 player.m_7618_(Anchor.EYES, storm.m_20299_(1.0F));
                                 break;
                              }
                           }
                        }
                     }
                  );
               }
            }
         }
      );
}
