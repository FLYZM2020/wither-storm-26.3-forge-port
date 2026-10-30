package nonamecrackers2.witherstormmod.common.blockentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;
import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity.Color;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.SuperBeaconMenu;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;
import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;
import nonamecrackers2.witherstormmod.common.packet.GlobalSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class SuperBeaconBlockEntity extends AbstractSuperBeaconBlockEntity implements WorldlyContainer {
   public static final int MAX_ITEMS = 16;
   public static final int RESUMMON_START = 60;
   public static final int RESUMMON_TIME = 372;
   public static final int EFFECT_RADIUS = 128;
   private static final int[] COLOR = new int[]{14, 62, 232};
   private final Map<Color, BlockPos> connected = Maps.newHashMap();
   private NonNullList<ItemStack> items = NonNullList.m_122779_();
   private int resummonTicks;
   private boolean isDoingResummoning;
   @Nullable
   private EntityType<?> resummoningEntity;
   @Nullable
   private CompoundTag resummonNbt;
   public Vec2 shake = Vec2.f_82462_;
   public Vec2 shakeO = Vec2.f_82462_;
   private List<BlockClusterEntity> clusters = Lists.newArrayList();

   public SuperBeaconBlockEntity(BlockPos pos, BlockState state) {
      super((BlockEntityType)WitherStormModBlockEntityTypes.SUPER_BEACON.get(), pos, state);
   }

   public void tick() {
      super.tick();
      int prevLevel = this.beaconLevel;
      this.beaconLevel = this.getBeaconLevel();
      if (prevLevel != this.beaconLevel) {
         if (this.beaconLevel > 0) {
            this.isActive = true;
            if (prevLevel == 0) {
               this.activate();
            }
         } else {
            this.isActive = false;
            this.deactivate();
         }

         this.markUpdated();
      }

      this.findNearbySupportBeacons();
      if (!this.f_58857_.f_46443_ && !this.isDoingResummonAnimation()) {
         List<ResummonSuperBeaconRecipe> recipes = this.f_58857_
            .m_7465_()
            .m_44056_((RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_RESUMMON.get(), this, this.f_58857_);
         if (!recipes.isEmpty()) {
            ResummonSuperBeaconRecipe recipe = recipes.get(0);
            if (recipe.getCondition().canCraft(this)) {
               this.resummoningEntity = recipe.getResummonEntity();
               this.resummonNbt = recipe.getResummonEntityNBT();
               this.activateResummonAnimation();
               this.isDoingResummoning = true;
               this.markUpdated();
            }
         }

         List<ItemCraftSuperBeaconRecipe> craftingRecipes = this.f_58857_
            .m_7465_()
            .m_44056_((RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_ITEM.get(), this, this.f_58857_);
         if (!craftingRecipes.isEmpty()) {
            ItemCraftSuperBeaconRecipe recipe = craftingRecipes.get(0);
            if (recipe.getCondition().canCraft(this)) {
               Vec3 pos = Vec3.m_82512_(this.m_58899_());
               ServerLevel level = (ServerLevel)this.f_58857_;
               level.m_8767_(ParticleTypes.f_123755_, pos.f_82479_, pos.f_82480_ + 2.0, pos.f_82481_, 20, 1.0, 1.0, 1.0, 0.01);
               level.m_8767_(
                  (SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(), pos.f_82479_, pos.f_82480_ + 2.0, pos.f_82481_, 50, 1.0, 1.0, 1.0, 0.015
               );
               this.f_58857_
                  .m_5594_(null, this.m_58899_(), (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.BLOCKS, 10.0F, 1.0F);
               ItemStack stack = recipe.assemble(this, this.f_58857_.m_9598_());
               ItemEntity item = new ItemEntity(this.f_58857_, pos.f_82479_, pos.f_82480_ + 2.0, pos.f_82481_, stack);
               item.m_146915_(true);
               this.f_58857_.m_7967_(item);
               this.items.clear();
               this.markUpdated();
            }
         }
      }

      if (this.isDoingResummoning) {
         this.resummonTicks++;
         BlockPos commandBlockPos = this.m_58899_().m_6630_(3);
         if (this.getResummonTicks() > 60) {
            Vec3 cmdBlockVec = Vec3.m_82512_(commandBlockPos);
            double x = cmdBlockVec.f_82479_ + this.random.nextGaussian();
            double y = cmdBlockVec.f_82480_ + this.random.nextGaussian();
            double z = cmdBlockVec.f_82481_ + this.random.nextGaussian();
            Vec3 delta = cmdBlockVec.m_82492_(x, y, z).m_82541_().m_82490_(0.1);
            this.f_58857_.m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.f_82479_, delta.f_82480_, delta.f_82481_);
         }

         if (!this.f_58857_.f_46443_) {
            Vec3 pos = Vec3.m_82512_(this.m_58899_());
            if (this.getResummonTicks() == 60) {
               this.items.clear();
               if (this.f_58857_ instanceof ServerLevel level) {
                  level.m_8767_(ParticleTypes.f_123755_, pos.f_82479_, pos.f_82480_, pos.f_82481_, 20, 1.0, 1.0, 1.0, 0.01);
                  level.m_8767_(
                     (SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
                     pos.f_82479_,
                     pos.f_82480_ + 3.0,
                     pos.f_82481_,
                     50,
                     1.0,
                     1.0,
                     1.0,
                     0.015
                  );
                  this.f_58857_
                     .m_5594_(null, commandBlockPos, (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.BLOCKS, 10.0F, 1.0F);
                  boolean flag = this.resummoningEntity == WitherStormModEntityTypes.WITHER_STORM.get();
                  if (flag) {
                     this.f_58857_
                        .m_5594_(null, commandBlockPos, (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_BUILD.get(), SoundSource.BLOCKS, 10.0F, 1.0F);
                  }

                  if (this.resummoningEntity != null && this.resummonNbt != null && !flag) {
                     Entity entity = this.resummoningEntity.m_262496_(level, commandBlockPos, MobSpawnType.TRIGGERED);

                     for (ServerPlayer player : this.f_58857_.m_45976_(ServerPlayer.class, new AABB(this.m_58899_()).m_82400_(100.0))) {
                        WitherStormModCriteriaTriggers.SUMMON_MOB_SUPER_BEACON.trigger(player, entity);
                     }

                     if (!this.resummonNbt.m_128456_()) {
                        CompoundTag current = entity.m_20240_(new CompoundTag());
                        current.m_128391_(this.resummonNbt);
                        entity.m_20258_(current);
                     }

                     this.resummoningEntity = null;
                     this.resummonNbt = null;
                     this.resummonTicks = 0;
                     this.isDoingResummoning = false;
                  }
               }

               this.markUpdated();
            }

            if (this.resummoningEntity == WitherStormModEntityTypes.WITHER_STORM.get()) {
               if (this.getResummonTicks() > 60) {
                  if (this.getResummonTicks() % 40 == 0) {
                     this.playSound((SoundEvent)WitherStormModSoundEvents.BOWELS_LOUD_HURT.get(), 10.0F, 1.0F);
                     WitherStormModPacketHandlers.MAIN
                        .send(
                           PacketDistributor.NEAR.with(TargetPoint.p(pos.f_82479_, pos.f_82480_, pos.f_82481_, 20.0, this.f_58857_.m_46472_())),
                           new ShakeScreenMessage(80.0F, 4.0F)
                        );
                  }

                  int interval = Math.max(1, 372 / this.getResummonTicks());
                  if (this.getResummonTicks() % interval == 0 && this.getResummonTicks() < 352 && this.f_58857_.m_46469_().m_46207_(GameRules.f_46132_)) {
                     for (int b = 0; b < 2; b++) {
                        int x = this.random.nextInt(97) - 48 + this.m_58899_().m_123341_();
                        int z = this.random.nextInt(97) - 48 + this.m_58899_().m_123343_();
                        BlockPos currentPos = new BlockPos(x, this.m_58899_().m_123342_() + 3, z);

                        for (int i = 0; i < 30 && this.f_58857_.m_8055_(currentPos.m_7495_()).m_60795_(); i++) {
                           currentPos = currentPos.m_7495_();
                        }

                        currentPos = currentPos.m_7495_();
                        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.f_58857_);
                        cluster.populateWithRadius(
                           currentPos,
                           1.0F,
                           state -> {
                              if (state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST)) {
                                 return false;
                              }

                              for (Color color : Color.values()) {
                                 if (color.isValidBaseBlock(state)) {
                                    return false;
                                 }
                              }

                              return !state.m_204336_(WitherStormModBlockTags.WITHERED_BEACON_BASE)
                                 && !state.m_204336_(BlockTags.f_13079_)
                                 && !state.m_204336_(WitherStormModBlockTags.BEACONS);
                           }
                        );
                        cluster.setFadePos(this.m_58899_());
                        cluster.setTime(200);
                        if (this.getResummonTicks() % 40 == 0) {
                           cluster.m_5496_((SoundEvent)WitherStormModSoundEvents.BLOCK_CLUSTER_SHAKE.get(), 2.0F, 1.0F);
                        }

                        cluster.setRotationDelta(new Vec2(this.random.nextInt(20) * 0.1F / 2.0F, this.random.nextInt(20) * 0.1F / 2.0F));
                        cluster.m_20242_(true);
                        cluster.setPhysics(false);
                        this.f_58857_.m_7967_(cluster);
                        this.clusters.add(cluster);
                     }
                  }

                  Iterator<BlockClusterEntity> iterator = this.clusters.iterator();

                  while (iterator.hasNext()) {
                     BlockClusterEntity cluster = iterator.next();
                     if (cluster.m_6084_()) {
                        if (cluster.getShakeTime() <= 0) {
                           Vec3 cmdPos = pos.m_82520_(0.0, 3.0, 0.0);
                           Vec3 delta = cmdPos.m_82546_(cluster.m_20182_()).m_82541_().m_82490_(0.5);
                           cluster.m_20256_(delta);
                           if (new AABB(cluster.m_20183_()).m_82390_(cmdPos)) {
                              cluster.m_146870_();
                           }
                        }
                     } else {
                        iterator.remove();
                     }
                  }
               }

               if (this.getResummonTicks() > 372) {
                  this.resummonTicks = 0;
                  this.isDoingResummoning = false;
                  this.markUpdated();

                  for (BlockClusterEntity cluster : this.clusters) {
                     cluster.m_146870_();
                  }

                  this.f_58857_.m_7471_(this.m_58899_(), false);

                  for (BlockPos connected : this.getConnected().values()) {
                     this.f_58857_.m_7471_(connected, false);
                  }

                  this.f_58857_.m_254849_(null, pos.f_82479_, pos.f_82480_, pos.f_82481_, 8.0F, ExplosionInteraction.BLOCK);
                  WitherStormEntity storm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).m_20615_(this.f_58857_);
                  storm.m_21051_((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get())
                     .m_22125_(new AttributeModifier("resummonedModifier", -0.5, Operation.ADDITION));
                  storm.setPhase((Integer)WitherStormModConfig.SERVER.resummonedPhase.get());
                  storm.m_20219_(pos);
                  storm.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_EVOLVES.get(), 1.0F, 1.0F);
                  storm.getPlayDeadManager().setRecentlyRevived(true);
                  storm.setResummoned(true);

                  for (ServerPlayer player : this.f_58857_.m_45976_(ServerPlayer.class, new AABB(this.m_58899_()).m_82400_(100.0))) {
                     CriteriaTriggers.f_10580_.m_68256_(player, storm);
                  }

                  this.f_58857_.m_7967_(storm);
               }
            }
         }
      }

      this.shakeO = this.shake;
      if (this.isDoingResummonAnimation()) {
         float x = Mth.m_14089_(this.resummonTicks * 4.0F) * 0.1F + (this.random.nextFloat() - 0.5F) * 0.05F;
         float z = Mth.m_14089_(this.resummonTicks * 3.0F) * 0.1F + (this.random.nextFloat() - 0.5F) * 0.05F;
         this.shake = new Vec2(x, z);
      }

      if (!this.f_58857_.f_46443_ && this.getResummonTicks() == this.getResummonThreshold()) {
         this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_ACTIVATE.get(), 1.0F, 1.0F);
         this.playSound((SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), 10.0F, 1.0F);
         Vec3 pos = Vec3.m_82512_(this.m_58899_());
         WitherStormModPacketHandlers.MAIN
            .send(
               PacketDistributor.NEAR.with(TargetPoint.p(pos.f_82479_, pos.f_82480_, pos.f_82481_, 20.0, this.f_58857_.m_46472_())),
               new ShakeScreenMessage(80.0F, 10.0F)
            );
      }
   }

   public void m_7651_() {
      super.m_7651_();
      this.clusters.forEach(c -> {
         c.m_20242_(false);
         c.setPhysics(true);
      });
   }

   public void m_142466_(CompoundTag tag) {
      super.m_142466_(tag);
      NonNullList<ItemStack> items = NonNullList.m_122779_();
      ListTag list = tag.m_128437_("ResummonItems", 10);

      for (int i = 0; i < list.size(); i++) {
         CompoundTag item = list.m_128728_(i);
         items.add(ItemStack.m_41712_(item));
      }

      this.items = items;
      this.resummonTicks = tag.m_128451_("ResummonTicks");
      this.isDoingResummoning = tag.m_128471_("Resummoning");
      if (tag.m_128441_("ResummoningEntity")) {
         String rawId = tag.m_128461_("ResummoningEntity");
         this.resummoningEntity = (EntityType<?>)EntityType.m_20632_(rawId).orElse(null);
      }

      if (tag.m_128441_("ResummonNBT")) {
         this.resummonNbt = tag.m_128469_("ResummonNBT");
      }
   }

   protected void m_183515_(CompoundTag tag) {
      super.m_183515_(tag);
      ListTag list = new ListTag();

      for (ItemStack stack : this.items) {
         CompoundTag item = new CompoundTag();
         list.add(stack.m_41739_(item));
      }

      tag.m_128365_("ResummonItems", list);
      tag.m_128405_("ResummonTicks", this.resummonTicks);
      tag.m_128379_("Resummoning", this.isDoingResummoning);
      if (this.resummoningEntity != null) {
         ResourceLocation id = EntityType.m_20613_(this.resummoningEntity);
         tag.m_128359_("ResummoningEntity", id.toString());
      }

      if (this.resummonNbt != null) {
         tag.m_128365_("ResummonNBT", this.resummonNbt);
      }
   }

   protected void applyEffect(ServerLevel level) {
      AABB box = new AABB(this.m_58899_()).m_82400_(128.0).m_82363_(0.0, level.m_141928_(), 0.0);
      level.m_45976_(Player.class, box).forEach(player -> {
         if (Math.sqrt(player.m_20238_(Vec3.m_82512_(this.m_58899_()))) <= 128.0) {
            player.m_7292_(new MobEffectInstance(this.effect, 505, this.beaconLevel - 1, true, true));
         }
      });
   }

   private void findNearbySupportBeacons() {
      AABB box = new AABB(this.m_58899_()).m_82400_(5.0);
      List<BlockEntity> entities = WorldUtil.getBlockEntitiesInAABB(this.f_58857_, box);
      Iterator<BlockPos> iterator = this.connected.values().iterator();

      while (iterator.hasNext()) {
         BlockPos pos = iterator.next();
         BlockEntity entity = this.f_58857_.m_7702_(pos);
         if (!this.isValidSupportBeacon.test(entity)) {
            iterator.remove();
         }
      }

      for (BlockEntity entity : entities) {
         if (this.isValidSupportBeacon.test(entity) && Math.sqrt(entity.m_58899_().m_123331_(this.m_58899_())) <= 5.0) {
            Color color = ((SuperSupportBeaconBlockEntity)entity).getColor();
            this.connected.putIfAbsent(color, entity.m_58899_());
         }
      }
   }

   private int getBeaconLevel() {
      BlockPos pos = this.m_58899_();
      int level = 0;

      for (int i = 1; i <= 4; level = i++) {
         int y = pos.m_123342_() - i;
         if (i < this.f_58857_.m_141937_()) {
            break;
         }

         boolean flag = true;

         for (int x = pos.m_123341_() - i; x <= pos.m_123341_() + i && flag; x++) {
            for (int z = pos.m_123343_() - i; z <= pos.m_123343_() + i; z++) {
               BlockState state = this.f_58857_.m_8055_(new BlockPos(x, y, z));
               if (i == 1 ? !state.m_204336_(WitherStormModBlockTags.WITHERED_BEACON_BASE) : !state.m_204336_(BlockTags.f_13079_)) {
                  flag = false;
                  break;
               }
            }
         }

         if (!flag) {
            break;
         }
      }

      return level;
   }

   public boolean isConnected(BlockPos pos) {
      return this.connected.containsValue(pos);
   }

   public float getThickness() {
      return 0.25F;
   }

   public float getOuterThickness() {
      return 0.45F;
   }

   public Map<Color, BlockPos> getConnected() {
      return this.connected;
   }

   public void doPowerUp(ServerPlayer player) {
      super.doPowerUp(player);
      player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA)
         .ifPresent(
            data -> {
               if (!data.hasActivatedSuperBeacon() && this.connected.size() >= Color.values().length) {
                  this.poweringUpAnimation = 80;
                  WitherStormModPacketHandlers.MAIN
                     .send(
                        PacketDistributor.ALL.noArg(), new GlobalSoundMessage((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_POWER_UP.get(), 1.0F, 1.0F)
                     );
                  this.f_58857_
                     .m_45976_(ServerPlayer.class, new AABB(this.m_58899_()).m_82400_(64.0))
                     .forEach(
                        p -> p.f_8906_.m_9829_(new ClientboundStopSoundPacket(WitherStormModSoundEvents.WITHERED_BEACON_AMBIENT.getId(), SoundSource.BLOCKS))
                     );

                  for (BlockPos pos : this.connected.values()) {
                     if (this.f_58857_.m_7702_(pos) instanceof AbstractSuperBeaconBlockEntity superBeacon) {
                        superBeacon.poweringUpAnimation = 80;
                        superBeacon.markUpdated();
                        superBeacon.doActivationSequence();
                        superBeacon.activateAnim = 0.0F;
                     }
                  }

                  this.markUpdated();
                  this.doActivationSequence();
                  this.activateAnim = 0.0F;
               }
            }
         );
   }

   protected void doPoweringUpAnimation() {
      if (!this.f_58857_.f_46443_ && this.poweringUpAnimation == 40) {
         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.ALL.noArg(), new ShakeScreenMessage(120.0F, 12.0F));
         if (this.f_58857_ instanceof ServerLevel level) {
            Vec3 pos = Vec3.m_82512_(this.m_58899_());
            level.m_8767_(
               ParticleTypes.f_123799_,
               pos.f_82479_,
               pos.f_82480_,
               pos.f_82481_,
               200,
               this.random.nextGaussian(),
               this.random.nextGaussian(),
               this.random.nextGaussian(),
               0.2
            );
         }

         this.f_58857_.m_45976_(ServerPlayer.class, new AABB(this.m_58899_()).m_82400_(64.0)).forEach(p -> {
            p.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(d -> d.setActivatedSuperBeacon(true));
            WitherStormModCriteriaTriggers.ACTIVATE_SUPER_BEACON.trigger(p, this.connected.size());
         });
      }
   }

   public Set<MobEffect> getValidEffects() {
      return VALID_EFFECTS;
   }

   public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
      return BaseContainerBlockEntity.m_58629_(player, this.lockKey, this.m_5446_())
         ? new SuperBeaconMenu(id, inventory, this.data, ContainerLevelAccess.m_39289_(this.f_58857_, this.m_58899_()), this::doPowerUp, this.getValidEffects())
         : null;
   }

   public int[] getBeamColor() {
      return COLOR;
   }

   public void addItem(ItemStack stack) {
      this.m_6836_(this.m_6643_(), stack);
   }

   public ItemStack takeItem() {
      return this.m_7407_(this.m_6643_() - 1, 1);
   }

   public int getResummonTicks() {
      return this.resummonTicks;
   }

   public Vec2 getShake(float partialTicks) {
      float x = Mth.m_14179_(partialTicks, this.shakeO.f_82470_, this.shake.f_82470_);
      float z = Mth.m_14179_(partialTicks, this.shakeO.f_82471_, this.shake.f_82471_);
      return new Vec2(x, z);
   }

   private void activateResummonAnimation() {
      this.playSound((SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), 10.0F, 1.0F);
      this.playSound((SoundEvent)WitherStormModSoundEvents.BOWELS_LOUD_HURT.get(), 10.0F, 1.0F);
      Vec3 pos = Vec3.m_82512_(this.m_58899_());
      WitherStormModPacketHandlers.MAIN
         .send(
            PacketDistributor.NEAR.with(TargetPoint.p(pos.f_82479_, pos.f_82480_, pos.f_82481_, 20.0, this.f_58857_.m_46472_())),
            new ShakeScreenMessage(80.0F, 10.0F)
         );
   }

   public boolean isDoingResummonAnimation() {
      return this.isDoingResummoning;
   }

   public boolean isActive() {
      return super.isActive() && !this.isDoingResummonAnimation();
   }

   protected boolean shouldDoActivatedAnim() {
      return super.shouldDoActivatedAnim() || this.getResummonTicks() > this.getResummonThreshold();
   }

   public void m_6211_() {
      this.items.clear();
   }

   public int m_6643_() {
      return this.items.size();
   }

   public boolean m_7983_() {
      for (ItemStack itemstack : this.items) {
         if (!itemstack.m_41619_()) {
            return false;
         }
      }

      return true;
   }

   public ItemStack m_8020_(int slot) {
      return slot >= 0 && slot < this.items.size() ? (ItemStack)this.items.get(slot) : ItemStack.f_41583_;
   }

   public ItemStack m_7407_(int slot, int amount) {
      if (!this.items.isEmpty()) {
         ItemStack stack = ((ItemStack)this.items.get(slot)).m_41620_(amount);
         if (((ItemStack)this.items.get(slot)).m_41619_()) {
            this.items.remove(slot);
         }

         this.markUpdated();
         return stack;
      } else {
         return ItemStack.f_41583_;
      }
   }

   public ItemStack m_8016_(int slot) {
      return ContainerHelper.m_18966_(this.items, slot);
   }

   public void m_6836_(int slot, ItemStack stack) {
      if (slot >= 0 && slot <= 16) {
         this.items.add(slot, stack);
         this.markUpdated();
      }
   }

   public boolean m_6542_(Player player) {
      return Container.m_272074_(this, player);
   }

   public boolean canPlayerUseItems(Player player) {
      return BaseContainerBlockEntity.m_58629_(player, this.lockKey, this.m_5446_());
   }

   public int[] m_7071_(Direction directions) {
      return new int[]{this.m_6643_()};
   }

   public boolean m_7155_(int slot, ItemStack stack, Direction direction) {
      return direction != Direction.DOWN && slot < 16 && !this.isDoingResummonAnimation();
   }

   public boolean m_7157_(int slot, ItemStack stack, Direction direction) {
      return false;
   }

   @Nullable
   public EntityType<?> getResummonEntity() {
      return this.resummoningEntity;
   }
}
