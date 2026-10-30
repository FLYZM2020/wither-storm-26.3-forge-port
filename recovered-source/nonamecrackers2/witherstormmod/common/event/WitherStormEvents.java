package nonamecrackers2.witherstormmod.common.event;

import java.util.UUID;
import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent.Start;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.event.level.ExplosionEvent.Detonate;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase.DistractionType;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;

public class WitherStormEvents {
   @SubscribeEvent
   public static void onLivingDeath(LivingDeathEvent event) {
      LivingEntity entity = event.getEntity();
      LivingEntity attacker = entity.m_21232_();
      if (!entity.m_9236_().f_46443_) {
         boolean flag = false;
         if (attacker instanceof WitherStormEntity storm) {
            if (ForgeEventFactory.getMobGriefingEvent(entity.m_9236_(), entity)) {
               BlockPos pos = BlockPos.m_274446_(entity.m_20182_());
               BlockState state = Blocks.f_50070_.m_49966_();
               if (entity.m_9236_().m_46859_(pos) && state.m_60710_(entity.m_9236_(), pos)) {
                  entity.m_9236_().m_7731_(pos, state, 3);
                  flag = true;
               }
            }

            if (!flag) {
               ItemEntity item = new ItemEntity(entity.m_9236_(), entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), new ItemStack(Items.f_41951_));
               entity.m_9236_().m_7967_(item);
            }

            if (entity instanceof ServerPlayer player) {
               player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.setKilledByStorm(storm.m_20148_()));
            }
         }
      }
   }

   @SubscribeEvent
   public static void onPlayerRespawn(PlayerRespawnEvent event) {
      if (event.getEntity() instanceof ServerPlayer player && !event.isEndConquered()) {
         player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA)
            .ifPresent(
               data -> {
                  UUID id = data.getKilledByStorm();
                  if ((Boolean)WitherStormModConfig.SERVER.preventWitherStormCamping.get()
                     && id != null
                     && player.m_284548_().m_8791_(id) instanceof WitherStormEntity storm
                     && storm.getPhase() > 3
                     && storm.m_20270_(player) < 300.0) {
                     ServerLevel level = player.m_284548_();

                     for (int i = 0; i < 10; i++) {
                        float angle = storm.m_217043_().m_188501_() * (float) (Math.PI * 2);
                        int x = (int)(Mth.m_14089_(angle) * (storm.m_217043_().m_188503_(200) + 300.0F)) + storm.m_146903_();
                        int z = (int)(Mth.m_14031_(angle) * (storm.m_217043_().m_188503_(200) + 300.0F)) + storm.m_146907_();
                        level.m_46745_(new BlockPos(x, 0, z));
                        int y = level.m_6924_(Types.MOTION_BLOCKING_NO_LEAVES, x, z);
                        BlockPos pos = new BlockPos(x, y, z);
                        if (Level.m_46741_(pos)) {
                           Vec3 vec = (Vec3)Player.m_36130_(level, pos, 0.0F, true, true).orElse(null);
                           if (vec != null) {
                              player.m_7618_(Anchor.EYES, vec);
                              player.m_6027_(vec.f_82479_, vec.f_82480_, vec.f_82481_);
                              break;
                           }
                        }
                     }
                  }

                  data.setKilledByStorm(null);
               }
            );
      }
   }

   @SubscribeEvent
   public static void onExplosionDetonate(Detonate event) {
      Level world = event.getLevel();
      if (!world.f_46443_) {
         Entity source = event.getExplosion().getExploder();
         if (source != null && !(source instanceof WitherStormEntity)) {
            for (Entity entity : world.m_45971_(WitherStormEntity.class, TargetingConditions.f_26872_, null, source.m_20191_().m_82400_(100.0))) {
               if (entity instanceof WitherStormEntity storm) {
                  for (WitherStormHead head : storm.getHeadManager().getHeads()) {
                     if (storm.tractorBeamActive(head.getIndex())) {
                        if (source instanceof PrimedTnt
                           && !(source instanceof FormidibombEntity)
                           && storm.canBeDistracted(head.getIndex(), DistractionType.ENTITY_BASED)) {
                           int chance = Math.max(1, Mth.m_14143_(storm.m_20270_(source) / 30.0F));
                           if (head.canSee(source) && storm.m_217043_().m_188503_(chance) == 0) {
                              head.makeDistracted(source.m_20182_(), storm.m_217043_().m_188503_(60) + 120);
                           }
                        }

                        Entity exploder = event.getExplosion().getExploder();
                        if (!(exploder != null && exploder instanceof Projectile projectile) || projectile.m_19749_() != storm) {
                           Vec3 headPos = head.getHeadPos();
                           if (event.getExplosion().getPosition().m_82554_(headPos) < (storm.getPhase() < 4 ? 5.0 : 12.0)
                              && !storm.isDeadOrPlayingDead()
                              && !head.isHeadInjured()
                              && head.checkAndCountAttack()) {
                              head.hurt(source, storm.getHeadManager().getHeadInjuryTime());
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public static void onEntityMount(EntityMountEvent event) {
      if (event.getEntityMounting() instanceof WitherStormEntity) {
         event.setCanceled(true);
      }
   }

   @SubscribeEvent
   public static void onEntityUseItem(Start event) {
      ItemStack item = event.getItem();
      if (item.m_150930_(Items.f_220219_)) {
         for (WitherStormEntity storm : event.getEntity()
            .m_9236_()
            .m_45976_(WitherStormEntity.class, event.getEntity().m_20191_().m_82377_(128.0, 256.0, 128.0))) {
            if (!storm.isDeadOrPlayingDead()) {
               for (WitherStormHead head : storm.getHeadManager().getHeads()) {
                  if (storm.tractorBeamActive(head.getIndex())
                     && storm.canBeDistracted(head.getIndex(), DistractionType.ENTITY_BASED)
                     && storm.m_217043_().m_188503_(3) == 0) {
                     head.makeDistracted(event.getEntity().m_146892_(), 80);
                  }
               }
            }
         }
      }
   }
}
