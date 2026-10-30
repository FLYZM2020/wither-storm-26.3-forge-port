package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.BossEvent.BossBarColor;
import net.minecraft.world.BossEvent.BossBarOverlay;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.registries.IForgeRegistry;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.goal.symbiont.PrepareSpellGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.symbiont.SummonMobsGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.symbiont.UseSpellGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSymbiontSpellTypes;
import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
import nonamecrackers2.witherstormmod.common.util.ConditionalLookController;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
import org.jetbrains.annotations.NotNull;

public class WitheredSymbiontEntity extends Monster implements BossThemeEntity {
   private static final EntityDataAccessor<nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage> BOSSFIGHT_STAGE = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.class, WitherStormModDataSerializers.BOSSFIGHT_STAGE_ENUM
   );
   private static final EntityDataAccessor<SpellType> SPELL_TYPE = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.class, WitherStormModDataSerializers.SPELL_TYPE
   );
   private static final EntityDataAccessor<Boolean> NON_BOSS_MODE = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.class, EntityDataSerializers.f_135035_
   );
   private static final EntityDataAccessor<Boolean> RUSH_MODE = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.class, EntityDataSerializers.f_135035_
   );
   private static final EntityDataAccessor<Boolean> SHOULD_NOT_GO_OVER_HALF = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.class, EntityDataSerializers.f_135035_
   );
   public static final Predicate<LivingEntity> TARGET_PREDICATE = entity -> entity.m_6097_() && entity instanceof Player;
   public static final Predicate<LivingEntity> MOB_TARGET_PREDICATE = entity -> entity.m_6097_()
      && !(entity instanceof WitherSickened)
      && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity)
      && !(entity instanceof WitherStormEntity)
      && !(entity instanceof WitherStormHeadEntity)
      && !(entity instanceof TentacleEntity)
      && !(entity instanceof EnderMan)
      && !(entity instanceof EnderDragon)
      && !(entity instanceof WitherBoss)
      && !(entity instanceof WitherSkeleton)
      && (
         entity instanceof AbstractVillager
            || entity instanceof AbstractGolem
            || entity instanceof Monster
            || entity instanceof Animal
            || entity instanceof NeutralMob
            || entity instanceof Player
      );
   public static final Predicate<LivingEntity> PULSE_PREDICATE = entity -> entity.m_6097_()
      && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity)
      && !(entity instanceof WitherStormEntity)
      && !(entity instanceof WitherStormHeadEntity)
      && !(entity instanceof TentacleEntity)
      && !(entity instanceof CommandBlockEntity);
   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> SYMBIONT_NORMAL_MOBS = SimpleWeightedRandomList.m_146263_()
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 8)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), 8)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get(), 4)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 3)
      .m_146270_();
   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> SYMBIONT_HARDER_MOBS = SimpleWeightedRandomList.m_146263_()
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 8)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), 8)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get(), 6)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get(), 1)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get(), 2)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 3)
      .m_146271_((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 6)
      .m_146270_();
   private List<Goal> bossFightGoals;
   private MeleeAttackGoal attackGoal;
   private PrepareSpellGoal prepareSpellGoal;
   private UseSpellGoal useSpellGoal;
   private SummonMobsGoal summonMobsGoal;
   private nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.DoNothingGoal doNothingGoal;
   private int stageTicks;
   private int spellCastingTime;
   private int nextSpellPickCount;
   private boolean isDoingSmash;
   private int smashAirTime;
   private List<LivingEntity> entitiesToThrow = new ArrayList<>();
   private int spellsUsed;
   private float crouchAnim;
   private float crouchAnimO;
   private final ServerBossEvent bossInfo = new ServerBossEvent(this.m_5446_(), BossBarColor.PURPLE, BossBarOverlay.PROGRESS);
   private int specialDeathTime;
   @Nullable
   private UUID summoner;
   private int attackDelay;
   private List<ItemStack> dropItems = Lists.newArrayList();
   private float tearAlpha;
   private float tearAlphaO;
   private List<UUID> fightContributors = Lists.newArrayList();
   @Nullable
   private SymbiontSpell spellInstance;

   public WitheredSymbiontEntity(EntityType<? extends nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity> type, Level world) {
      super(type, world);
      this.f_21364_ = 150;
      this.f_21365_ = new ConditionalLookController(this, entity -> !entity.isVulnerable() && !entity.m_21224_());
   }

   private static TargetingConditions protectPredicate(double radius) {
      return TargetingConditions.m_148353_().m_26883_(radius).m_26888_(entity -> entity instanceof Player);
   }

   public float getStepHeight() {
      return 1.0F;
   }

   protected void m_8097_() {
      super.m_8097_();
      this.f_19804_.m_135372_(BOSSFIGHT_STAGE, nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.ATTACKING);
      this.f_19804_.m_135372_(SPELL_TYPE, (SpellType)WitherStormModSymbiontSpellTypes.EMPTY.get());
      this.f_19804_.m_135372_(NON_BOSS_MODE, false);
      this.f_19804_.m_135372_(RUSH_MODE, false);
      this.f_19804_.m_135372_(SHOULD_NOT_GO_OVER_HALF, true);
   }

   protected void m_8099_() {
      this.bossFightGoals = new ArrayList<>();
      this.attackGoal = new MeleeAttackGoal(this, 1.0, true);
      this.prepareSpellGoal = new PrepareSpellGoal(this);
      this.useSpellGoal = new UseSpellGoal(this);
      this.summonMobsGoal = new SummonMobsGoal(this, SYMBIONT_NORMAL_MOBS, SYMBIONT_HARDER_MOBS);
      this.doNothingGoal = new nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.DoNothingGoal(this);
      this.bossFightGoals.add(this.attackGoal);
      this.bossFightGoals.add(this.prepareSpellGoal);
      this.bossFightGoals.add(this.useSpellGoal);
      this.bossFightGoals.add(this.summonMobsGoal);
      this.bossFightGoals.add(this.doNothingGoal);
      this.f_21345_.m_25352_(1, this.prepareSpellGoal);
      this.f_21345_.m_25352_(2, this.useSpellGoal);
      this.f_21345_.m_25352_(3, this.attackGoal);
      this.f_21345_.m_25352_(4, new FloatGoal(this));
      this.f_21345_.m_25352_(5, new WaterAvoidingRandomStrollGoal(this, 0.7F));
      this.f_21345_.m_25352_(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
      this.f_21345_.m_25352_(7, new RandomLookAroundGoal(this));
      this.f_21346_.m_25352_(1, new HurtByTargetGoal(this, new Class[0]).m_26044_(new Class[0]));
      this.f_21346_.m_25352_(2, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, TARGET_PREDICATE));
      if ((Boolean)WitherStormModConfig.SERVER.shouldSymbiontAttackMobs.get()) {
         this.f_21346_.m_25352_(3, new NearestAttackableTargetGoal(this, Mob.class, 10, true, false, MOB_TARGET_PREDICATE));
      }
   }

   public void m_7380_(@NotNull CompoundTag compound) {
      super.m_7380_(compound);
      compound.m_128379_("IsNonBossMode", this.isNonBossMode());
      compound.m_128379_("IsRushMode", this.isRushMode());
      compound.m_128405_("Stage", this.getStage().ordinal());
      compound.m_128405_("StageTicks", this.getStageTicks());
      compound.m_128359_(
         "Spell", Objects.requireNonNull(((IForgeRegistry)WitherStormModRegistries.SPELL_TYPES.get()).getKey(this.getSpell()), "Unregistered spell").toString()
      );
      compound.m_128405_("SpellCastingTicks", this.spellCastingTime);
      compound.m_128405_("NextSpellPick", this.nextSpellPickCount);
      compound.m_128379_("Smashing", this.isSmashing());
      compound.m_128405_("SmashAirTime", this.smashAirTime);
      compound.m_128405_("SpellsUsed", this.getSpellsUsed());
      if (this.summoner != null) {
         compound.m_128362_("Summoner", this.summoner);
      }

      compound.m_128405_("AttackDelay", this.attackDelay);
      if (!this.dropItems.isEmpty()) {
         ListTag dropItems = new ListTag();

         for (ItemStack stack : this.dropItems) {
            if (!stack.m_41619_()) {
               CompoundTag tag = new CompoundTag();
               stack.m_41739_(tag);
               dropItems.add(tag);
            }
         }

         compound.m_128365_("DropItems", dropItems);
      }

      compound.m_128379_("ShouldNotGoOverHalf", (Boolean)this.f_19804_.m_135370_(SHOULD_NOT_GO_OVER_HALF));
      ListTag fightContributors = new ListTag();

      for (UUID id : this.fightContributors) {
         fightContributors.add(NbtUtils.m_129226_(id));
      }

      compound.m_128365_("FightContributors", fightContributors);
   }

   public void m_7378_(@NotNull CompoundTag compound) {
      super.m_7378_(compound);
      if (compound.m_128441_("IsNonBossMode")) {
         this.setNonBossMode(compound.m_128471_("IsNonBossMode"));
      }

      if (compound.m_128441_("IsRushMode")) {
         this.setRushMode(compound.m_128471_("IsRushMode"));
      }

      if (compound.m_128441_("Stage")) {
         int ordinal = compound.m_128451_("Stage");
         if (ordinal >= 0 && ordinal < nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.values().length) {
            this.setStage(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.values()[ordinal]);
         }
      }

      this.setStageTicks(compound.m_128451_("StageTicks"));
      if (compound.m_128425_("Spell", 8)) {
         String rawId = compound.m_128461_("Spell");
         ResourceLocation loc = ResourceLocation.m_135820_(rawId);
         if (loc != null) {
            SpellType type = (SpellType)((IForgeRegistry)WitherStormModRegistries.SPELL_TYPES.get()).getValue(loc);
            if (type != null) {
               this.setSpell(type);
            }
         }
      }

      this.spellCastingTime = compound.m_128451_("SpellCastingTicks");
      this.nextSpellPickCount = compound.m_128451_("NextSpellPick");
      this.setSmashing(compound.m_128471_("Smashing"));
      this.smashAirTime = compound.m_128451_("SmashAirTime");
      this.spellsUsed = compound.m_128451_("SpellsUsed");
      if (compound.m_128441_("Summoner")) {
         this.summoner = compound.m_128342_("Summoner");
      }

      this.attackDelay = compound.m_128451_("AttackDelay");
      if (compound.m_128441_("DropItems")) {
         ListTag dropItems = compound.m_128437_("DropItems", 10);

         for (int i = 0; i < dropItems.size(); i++) {
            this.dropItems.add(ItemStack.m_41712_(dropItems.m_128728_(i)));
         }
      }

      if (compound.m_128441_("ShouldNotGoOverHalf")) {
         this.f_19804_.m_135381_(SHOULD_NOT_GO_OVER_HALF, compound.m_128471_("ShouldNotGoOverHalf"));
      }

      this.fightContributors.clear();

      for (Tag tag : compound.m_128437_("FightContributors", 11)) {
         this.fightContributors.add(NbtUtils.m_129233_(tag));
      }
   }

   public static Builder createAttributes() {
      return Monster.m_33035_()
         .m_22268_(Attributes.f_22276_, 60.0)
         .m_22268_(Attributes.f_22279_, 0.15)
         .m_22268_(Attributes.f_22278_, 1.0)
         .m_22268_(Attributes.f_22281_, 16.0)
         .m_22268_(Attributes.f_22277_, 45.0);
   }

   protected int m_7302_(int supply) {
      return supply;
   }

   protected void m_7324_(@NotNull Entity entity) {
      if (EntitySelector.f_20406_.test(entity)
         && entity instanceof LivingEntity
         && TARGET_PREDICATE.test((LivingEntity)entity)
         && this.m_217043_().m_188503_(20) == 0) {
         this.m_6710_((LivingEntity)entity);
      }

      super.m_7324_(entity);
   }

   public void m_8107_() {
      super.m_8107_();
      this.stageTicks++;
      if (this.spellCastingTime > 0) {
         this.spellCastingTime--;
         this.doSpellCasting();
         if (this.spellCastingTime <= 0) {
            this.castSpell();
         }

         SpellType type = this.getSpell();
         if (type.doProtection()) {
            double radius = type.protectionRadius();

            for (Player player : this.m_9236_().m_45976_(Player.class, this.m_20191_().m_82400_(radius))) {
               if (!this.entitiesToThrow.contains(player) && protectPredicate(radius).m_26885_(this, player)) {
                  this.entitiesToThrow.add(player);
                  if (!this.m_9236_().f_46443_) {
                     this.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_LAUNCH_MOB.get(), 16.0F, 1.0F);
                  }
               }
            }
         }

         for (int i = 0; i < this.entitiesToThrow.size(); i++) {
            LivingEntity entity = this.entitiesToThrow.get(i);
            if (protectPredicate(type.protectionRadius()).m_26885_(this, entity)) {
               Vec3 delta = this.m_20182_().m_82546_(entity.m_20182_()).m_82541_().m_82520_(0.0, -0.5, 0.0).m_82490_(-type.protectionThrowStrength());
               entity.m_20256_(delta);
            } else {
               this.entitiesToThrow.remove(i);
            }
         }
      }

      if (this.nextSpellPickCount > 0) {
         this.nextSpellPickCount--;
      }

      if (this.isSmashing()) {
         if (this.smashAirTime > 0) {
            this.smashAirTime--;
            if (this.smashAirTime <= 0) {
               this.m_20334_(this.m_20184_().m_7096_(), -5.0, this.m_20184_().m_7094_());
            }
         } else if (this.m_20096_()) {
            this.setSmashing(false);
            if (!this.m_9236_().m_5776_()) {
               float strength = 1.5F;
               if (this.shouldIncreaseDifficulty()) {
                  strength = 2.5F;
               }

               this.m_9236_().m_254849_(this, this.m_20185_(), this.m_20186_(), this.m_20189_(), strength, ExplosionInteraction.MOB);
            }
         }
      }

      if (this.isCastingSpell() || this.isSummoningMobs()) {
         for (int i = 0; i < 5; i++) {
            double x = this.m_20185_() + this.f_19796_.m_188583_() * 2.0;
            double y = this.m_20188_() + this.f_19796_.m_188583_() * 2.0;
            double z = this.m_20189_() + this.f_19796_.m_188583_() * 2.0;
            Vec3 delta = this.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.2, 0.2, 0.2);
            this.m_9236_()
               .m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
         }
      }

      if (this.m_20184_().m_165925_() > 2.5000003E-7F && this.f_19796_.m_188503_(5) == 0) {
         int i = Mth.m_14107_(this.m_20185_());
         int j = Mth.m_14107_(this.m_20186_() - 0.2F);
         int k = Mth.m_14107_(this.m_20189_());
         BlockPos pos = new BlockPos(i, j, k);
         BlockState state = this.m_9236_().m_8055_(pos);
         if (!state.m_60713_(Blocks.f_50016_)) {
            this.m_9236_()
               .m_7106_(
                  new BlockParticleOption(ParticleTypes.f_123794_, state).setPos(pos),
                  this.m_20185_() + (this.f_19796_.m_188501_() - 0.5) * this.m_20205_(),
                  this.m_20186_() + 0.1,
                  this.m_20189_() + (this.f_19796_.m_188501_() - 0.5) * this.m_20205_(),
                  4.0 * (this.f_19796_.m_188501_() - 0.5),
                  0.5,
                  (this.f_19796_.m_188501_() - 0.5) * 4.0
               );
         }
      }

      if (!this.m_9236_().m_5776_() && this.getStage().shouldMoveToNextStage(this)) {
         this.nextStage();
      }

      if (this.attackDelay > 0) {
         this.attackDelay--;
         if (this.attackDelay <= 0 && this.isVulnerable()) {
            this.setStage(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.ATTACKING);
         }
      }
   }

   public void m_8119_() {
      super.m_8119_();
      this.crouchAnimO = this.crouchAnim;
      if (this.isVulnerable()) {
         this.crouchAnim = this.crouchAnim + ((1.0F - this.crouchAnim) * 0.1F + 0.02F);
         if (this.crouchAnim > 0.6F) {
            this.crouchAnim = 0.6F;
         }
      } else {
         this.crouchAnim = this.crouchAnim + (-this.crouchAnim * 0.4F - 0.1F);
         if (this.crouchAnim < 0.0F) {
            this.crouchAnim = 0.0F;
         }
      }

      this.tearAlphaO = this.tearAlpha;
      if (!(Boolean)WitherStormModConfig.SERVER.attackableWhenNotVulnerable.get() && !this.isVulnerable()) {
         if (this.tearAlpha > 0.0F) {
            this.tearAlpha -= 0.05F;
         }
      } else if (this.tearAlpha < 1.0F) {
         this.tearAlpha += 0.05F;
      }

      if (!this.m_9236_().m_5776_()) {
         this.bossInfo.m_142711_(this.m_21223_() / this.m_21233_());
      }
   }

   public boolean m_7327_(Entity entity) {
      float f = this.getAttackDamage();
      float f1 = (int)f > 0 ? f / 2.0F + this.f_19796_.m_188503_((int)f) : f;
      boolean flag = entity.m_6469_(this.m_269291_().m_269333_(this), f1);
      if (flag) {
         entity.m_20256_(entity.m_20184_().m_82520_(0.0, 0.8, 0.0));
         this.m_19970_(this, entity);
      }

      return flag;
   }

   private float getAttackDamage() {
      return (float)this.m_21133_(Attributes.f_22281_);
   }

   protected SoundEvent m_7515_() {
      return this.isVulnerable() ? null : (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_AMBIENT.get();
   }

   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
      return entity instanceof Mob mob && WorldTainting.getInstance().convertMob(mob, false);
   }

   protected SoundEvent m_7975_(@NotNull DamageSource source) {
      return (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_HURT.get();
   }

   protected SoundEvent m_5592_() {
      return this.isNonBossMode()
         ? (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_NORMAL_DEATH.get()
         : (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_DEATH.get();
   }

   public float m_6100_() {
      return this.m_21224_() ? 1.0F : super.m_6100_();
   }

   protected void m_7355_(@NotNull BlockPos pos, @NotNull BlockState state) {
      this.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_STEP.get(), 0.3F, 1.0F);
   }

   public boolean m_6573_(@NotNull Player player) {
      return false;
   }

   public void m_6043_() {
      if (this.m_9236_().m_46791_() == Difficulty.PEACEFUL && this.m_8028_()) {
         this.m_146870_();
      } else if (!this.m_21532_() && !this.m_8023_()) {
         List<WitherStormEntity> entities = WorldUtil.getPerformantEntitiesOfClass(
            (ServerLevel)this.m_9236_(), WitherStormEntity.class, this.m_20191_().m_82400_(400.0)
         );
         if (entities.isEmpty()) {
            super.m_6043_();
         } else {
            this.f_20891_ = 0;
         }
      } else {
         this.f_20891_ = 0;
      }
   }

   public boolean m_142535_(float p_225503_1_, float p_225503_2_, @NotNull DamageSource source) {
      return false;
   }

   @NotNull
   public MobType m_6336_() {
      return WitherStormModMobTypes.SICKENED;
   }

   @NotNull
   public AABB m_6921_() {
      return super.m_6921_().m_82400_(3.0);
   }

   public boolean m_6469_(DamageSource source, float amount) {
      if (source.m_7640_() instanceof AbstractHurtingProjectile || source.m_7640_() instanceof AbstractArrow) {
         Projectile projectile = (Projectile)source.m_7640_();
         if (projectile.m_19749_() instanceof WitherSickened
            || projectile.m_19749_() instanceof nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity) {
            return false;
         }
      }

      if (source.m_269533_(DamageTypeTags.f_268738_)) {
         return super.m_6469_(source, amount);
      }

      if ((this.isCastingSpell() || !(Boolean)WitherStormModConfig.SERVER.attackableWhenNotVulnerable.get()) && !this.isVulnerable()) {
         return false;
      }

      Entity entity = source.m_7639_();
      if (entity != null) {
         double angle = Math.atan2(entity.m_20185_() - this.m_20185_(), entity.m_20189_() - this.m_20189_()) * (180.0 / Math.PI);
         double angleDiff = (-this.f_20883_ - angle + 180.0 + 360.0) % 360.0;
         if (angleDiff <= 40.0 || angleDiff >= 320.0) {
            if (this.isVulnerable() && this.attackDelay <= 0) {
               this.attackDelay = 20;
            }

            if (!this.isVulnerable() && entity instanceof LivingEntity && !this.m_21224_()) {
               ((SpellType)WitherStormModSymbiontSpellTypes.SMASH.get()).makeSpell(this).cast((LivingEntity)entity);
            }

            if (source.m_269533_(DamageTypeTags.f_268415_)) {
               amount /= 4.0F;
            }

            if (this.shouldNotGoOverHalfHealth()) {
               float predictedHealth = this.m_21223_() - amount;
               float maxHealthHalf = this.m_21233_() / 2.0F;
               amount = Math.min(amount - (maxHealthHalf - predictedHealth), amount);
            }

            float healthCurrent = this.m_21223_();
            boolean flag = super.m_6469_(source, amount);
            float damageDealt = healthCurrent - this.m_21223_();
            if (damageDealt >= 5.0F && entity instanceof Player && !this.fightContributors.contains(entity.m_20148_())) {
               this.fightContributors.add(entity.m_20148_());
            }

            return flag;
         }
      }

      return false;
   }

   public int getStageTicks() {
      return this.stageTicks;
   }

   public void setStageTicks(int ticks) {
      this.stageTicks = ticks;
   }

   public nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage getStage() {
      return (nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage)this.f_19804_.m_135370_(BOSSFIGHT_STAGE);
   }

   protected void clearBossFightGoals() {
      this.bossFightGoals.forEach(this.f_21345_::m_25363_);
   }

   protected void addBossFightGoal(int level, Goal goal) {
      this.f_21345_.m_25352_(level, goal);
   }

   public void setStage(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage stage) {
      if (this.getStage() != stage) {
         this.getStage().finish(this);
      }

      this.f_19804_.m_135381_(BOSSFIGHT_STAGE, stage);
      this.getStage().init(this);
   }

   public void nextStage() {
      nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage nextStage = this.getNextStage(1);
      if (nextStage == nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.SUMMONING && this.isNonBossMode()) {
         this.setStage(this.getNextStage(2));
      } else {
         this.setStage(nextStage);
      }
   }

   private nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage getNextStage(int advance) {
      int next = this.getStage().ordinal() + advance;
      return next < nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.values().length
         ? nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.values()[next]
         : nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.values()[0];
   }

   public SpellType getSpell() {
      return (SpellType)this.f_19804_.m_135370_(SPELL_TYPE);
   }

   public void setSpell(SpellType spell) {
      this.f_19804_.m_135381_(SPELL_TYPE, spell);
      if (!this.m_9236_().m_5776_()) {
         this.spellInstance = spell.makeSpell(this);
      }
   }

   @Nullable
   public SymbiontSpell getSpellInstance() {
      return this.spellInstance;
   }

   public void m_7350_(@NotNull EntityDataAccessor<?> parameter) {
      super.m_7350_(parameter);
      if (BOSSFIGHT_STAGE.equals(parameter)) {
         this.getStage().init(this);
      }
   }

   public boolean hasSpell() {
      return this.getSpell() != WitherStormModSymbiontSpellTypes.EMPTY.get();
   }

   public void beginSpellCasting() {
      if (!this.m_9236_().m_5776_() && this.spellInstance != null) {
         this.spellInstance.start(this.m_5448_());
         this.spellCastingTime = this.getSpell().spellTime();
         WitherStormModPacketHandlers.MAIN
            .send(
               PacketDistributor.TRACKING_ENTITY.with(() -> this),
               new nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.SetSpellTimeMessage(this.m_19879_(), this.spellCastingTime)
            );
      }
   }

   public boolean isCastingSpell() {
      return this.spellCastingTime > 0;
   }

   public boolean isSummoningMobs() {
      return this.getStage() == nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.SUMMONING;
   }

   public boolean isVulnerable() {
      return this.getStage() == nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity.BossfightStage.VULNERABLE;
   }

   public void breakSpell() {
      if (this.isCastingSpell()) {
         this.spellCastingTime = 0;
         if (!this.m_9236_().m_5776_() && this.spellInstance != null) {
            this.spellInstance.finish();
         }

         this.m_9236_().m_7605_(this, (byte)11);
      }
   }

   public void m_7822_(byte event) {
      if (event == 11) {
         this.breakSpell();
      } else if (event == 12) {
         this.activateAttackDelay();
      } else {
         super.m_7822_(event);
      }
   }

   public void castSpell() {
      if (!this.m_9236_().f_46443_) {
         if (this.spellInstance != null) {
            if (this.m_5448_() != null) {
               this.spellInstance.cast(this.m_5448_());
            }

            this.spellInstance.finish();
         }
      } else {
         for (int i = 0; i < 10; i++) {
            this.m_9236_()
               .m_7106_(
                  (ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(),
                  this.m_20185_(),
                  this.m_20188_(),
                  this.m_20189_(),
                  this.f_19796_.m_188583_() * 0.5,
                  this.f_19796_.m_188583_() * 0.5,
                  this.f_19796_.m_188583_() * 0.5
               );
         }
      }
   }

   public void doSpellCasting() {
      if (!this.m_9236_().m_5776_() && this.spellInstance != null) {
         int spellCastingTime = this.getSpell().spellTime() - this.spellCastingTime;
         if (this.m_5448_() != null && this.m_5448_().m_6084_()) {
            this.spellInstance.doCasting(this.m_5448_());
         } else if (spellCastingTime % 20 == 0) {
            this.breakSpell();
         }
      }
   }

   public boolean canPickSpell() {
      return !this.hasSpell() || this.nextSpellPickCount <= 0;
   }

   public void setAndCastSpell(SpellType type) {
      if (type != WitherStormModSymbiontSpellTypes.EMPTY.get() && !this.isVulnerable()) {
         this.nextSpellPickCount = 0;
         this.setSpell(type);
         this.useSpellGoal.nextAttackTickCount = this.f_19797_ + 1;
         this.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_PREPARE_SPELL.get(), 4.0F, 1.0F);
         this.nextSpellPickCount = 400 + this.m_217043_().m_188503_(400) - (this.shouldIncreaseDifficulty() ? 320 : 0);
         if (this.shouldNotGoOverHalfHealth() && this.m_21223_() / this.m_21233_() <= 0.5F) {
            this.f_19804_.m_135381_(SHOULD_NOT_GO_OVER_HALF, false);
         }
      }
   }

   public void setSmashing(boolean flag) {
      this.isDoingSmash = flag;
      if (flag) {
         this.smashAirTime = 20;
      }
   }

   public boolean isSmashing() {
      return this.isDoingSmash;
   }

   public int getSpellsUsed() {
      return this.spellsUsed;
   }

   public void spellUsed() {
      this.spellsUsed++;
   }

   public float getVulnerableAnim(float partialTicks) {
      return Mth.m_14179_(partialTicks, this.crouchAnimO, this.crouchAnim);
   }

   public void m_6457_(@NotNull ServerPlayer player) {
      super.m_6457_(player);
      this.bossInfo.m_6543_(player);
   }

   public void m_6452_(@NotNull ServerPlayer player) {
      super.m_6452_(player);
      this.bossInfo.m_6539_(player);
   }

   public boolean shouldIncreaseDifficulty() {
      return this.isRushMode() || this.m_21223_() / this.m_21233_() <= 0.5F;
   }

   public boolean shouldNotGoOverHalfHealth() {
      return !this.isNonBossMode() && (Boolean)this.f_19804_.m_135370_(SHOULD_NOT_GO_OVER_HALF);
   }

   protected void m_6153_() {
      if (this.isNonBossMode()) {
         super.m_6153_();
      } else {
         int totalTime = 320;
         this.specialDeathTime++;

         for (int i = 0; i < (totalTime - this.specialDeathTime) / 40; i++) {
            this.m_9236_()
               .m_7106_(
                  ParticleTypes.f_123755_,
                  this.m_20208_(1.0),
                  this.m_20187_(),
                  this.m_20262_(1.0),
                  this.f_19796_.m_188583_() * 0.02,
                  this.f_19796_.m_188583_() * 0.02,
                  this.f_19796_.m_188583_() * 0.02
               );
         }

         float speed = 3.0F;
         float f = Mth.m_14118_(this.m_146909_(), -50.0F);
         float f1 = Mth.m_14036_(f, -speed, speed);
         this.m_146926_(this.m_146909_() + f1);
         if (this.specialDeathTime == totalTime) {
            this.m_142687_(RemovalReason.KILLED);
            if (!this.m_9236_().m_5776_() && this.dropItems != null) {
               List<Player> players = this.m_9236_().m_6443_(Player.class, this.m_20191_().m_82400_(20.0), EntitySelector.f_20408_);
               if (players.size() > 1 && !this.fightContributors.isEmpty()) {
                  for (UUID id : this.fightContributors) {
                     players.stream().filter(p -> p.m_20148_().equals(id)).findFirst().ifPresent(player -> {
                        for (ItemStack stack : this.dropItems) {
                           if (!stack.m_41619_()) {
                              ItemStack copy = stack.m_41777_();
                              if (!player.m_150109_().m_36054_(copy)) {
                                 ItemEntity entity = this.m_19983_(copy);
                                 assert entity != null;
                                 entity.m_20219_(player.m_20182_());
                                 entity.m_266426_(id);
                              }
                           }
                        }
                     });
                  }
               } else {
                  this.dropDrops();
               }
            }

            for (int i = 0; i < 20; i++) {
               this.m_9236_()
                  .m_7106_(
                     ParticleTypes.f_123759_,
                     this.m_20208_(1.0),
                     this.m_20187_(),
                     this.m_20262_(1.0),
                     this.f_19796_.m_188583_() * 0.02,
                     this.f_19796_.m_188583_() * 0.02,
                     this.f_19796_.m_188583_() * 0.02
                  );
            }
         }
      }
   }

   private void dropDrops() {
      for (ItemStack stack : this.dropItems) {
         if (!stack.m_41619_()) {
            ItemEntity entity = this.m_5552_(stack, 8.0F);
            assert entity != null;
            entity.m_20334_(0.0, -0.08, 0.0);
            entity.m_20242_(true);
         }
      }

      this.dropItems.clear();
   }

   public void m_6667_(@NotNull DamageSource source) {
      super.m_6667_(source);
      if (this.m_21232_() instanceof Player player) {
         player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> {
            WitherStormEntity owner = this.getOwner();
            if (owner != null) {
               data.markKilledSymbiont(owner);
            }
         });
      }

      for (Player player : this.m_9236_().m_45976_(Player.class, this.m_20191_().m_82400_(20.0))) {
         player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA)
            .ifPresent(
               data -> data.makeInvulnerable(
                  Mth.m_14045_((Integer)WitherStormModConfig.SERVER.playerInvulnerableTime.get(), 1, 10) * 1200 + player.m_217043_().m_188503_(1200)
               )
            );
      }
   }

   public void setOwner(WitherStormEntity entity) {
      this.summoner = entity.m_20148_();
   }

   @Nullable
   public WitherStormEntity getOwner() {
      if (!this.m_9236_().m_5776_()) {
         ServerLevel world = (ServerLevel)this.m_9236_();

         for (Entity entity : world.m_8583_()) {
            if (entity.m_20148_().equals(this.summoner) && entity instanceof WitherStormEntity) {
               return (WitherStormEntity)entity;
            }
         }
      }

      return null;
   }

   public SoundEvent getBossTheme() {
      return !this.shouldIncreaseDifficulty()
         ? (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_THEME.get()
         : (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_INTENSE_THEME.get();
   }

   public boolean isStillAlive() {
      return this.m_6084_();
   }

   public Vec3 getPosition() {
      return this.m_20182_();
   }

   public double distanceToPlay() {
      return 45.0;
   }

   public int priority() {
      return 2;
   }

   public int getFadeTime() {
      return 120;
   }

   public boolean checkConfig() {
      return (Boolean)WitherStormModConfig.CLIENT.playSymbiontTheme.get();
   }

   public void activateAttackDelay() {
      this.attackDelay = 20;
      if (!this.m_9236_().m_5776_()) {
         this.m_9236_().m_7605_(this, (byte)12);
      }
   }

   public boolean hasAttackDelay() {
      return this.attackDelay > 0;
   }

   public boolean isNonBossMode() {
      return (Boolean)this.f_19804_.m_135370_(NON_BOSS_MODE);
   }

   public void setNonBossMode(boolean mode) {
      this.f_19804_.m_135381_(NON_BOSS_MODE, mode);
      if (mode) {
         this.f_21364_ = 25;
      } else {
         this.f_21364_ = 150;
      }
   }

   public boolean isRushMode() {
      return (Boolean)this.f_19804_.m_135370_(RUSH_MODE);
   }

   public void setRushMode(boolean mode) {
      this.f_19804_.m_135381_(RUSH_MODE, mode);
   }

   protected void m_7625_(@NotNull DamageSource source, boolean player) {
      ResourceLocation id = this.m_5743_();
      LootTable table = this.m_9236_().m_7654_().m_278653_().m_278676_(id);
      net.minecraft.world.level.storage.loot.LootParams.Builder builder = new net.minecraft.world.level.storage.loot.LootParams.Builder(
            (ServerLevel)this.m_9236_()
         )
         .m_287286_(LootContextParams.f_81455_, this)
         .m_287286_(LootContextParams.f_81460_, this.m_20182_())
         .m_287286_(LootContextParams.f_81457_, source)
         .m_287289_(LootContextParams.f_81458_, source.m_7639_())
         .m_287289_(LootContextParams.f_81459_, source.m_7640_());
      if (player && this.f_20888_ != null) {
         builder = builder.m_287286_(LootContextParams.f_81456_, this.f_20888_).m_287239_(this.f_20888_.m_36336_());
      }

      LootParams params = builder.m_287235_(LootContextParamSets.f_81415_);
      this.dropItems = table.m_287214_(params, this.m_287233_());
   }

   protected boolean m_7341_(@NotNull Entity entity) {
      return super.m_7341_(entity) && !(entity instanceof Boat) && !(entity instanceof AbstractMinecart);
   }

   public Component getWatermark() {
      return Component.m_237115_("witherstormmod.watermark.withered_symbiont_theme");
   }

   public float getTearAlpha(float partialTicks) {
      return Mth.m_14179_(partialTicks, this.tearAlphaO, this.tearAlpha);
   }

   public SpawnGroupData m_6518_(
      ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag
   ) {
      List<Player> nearbyPlayers = level.m_6443_(Player.class, this.m_20191_().m_82400_(150.0), e -> e.m_6084_() && !e.m_5833_());
      if (nearbyPlayers.size() > 1) {
         double healthAddition = nearbyPlayers.size() * (Double)WitherStormModConfig.SERVER.healthScalePerPlayer.get();
         if (healthAddition > 0.0) {
            Objects.requireNonNull(this.m_21051_(Attributes.f_22276_)).m_22125_(new AttributeModifier("Health scaling", healthAddition, Operation.ADDITION));
            this.m_21153_(this.m_21233_());
         }
      }

      return super.m_6518_(level, difficulty, spawnType, groupData, tag);
   }

   public LivingEntity getRandomNearbyTargetOrFallback(LivingEntity entity, Predicate<LivingEntity> selector) {
      List<LivingEntity> entities = this.getNearbyTargets(selector).filter(e -> e != entity).collect(Collectors.toList());
      if (!entities.isEmpty() && this.f_19796_.m_188503_(entities.size() + 1) != 0) {
         Collections.shuffle(entities);
         return entities.get(0);
      } else {
         return entity;
      }
   }

   public Stream<LivingEntity> getNearbyTargets(Predicate<LivingEntity> selector) {
      double range = this.m_21133_(Attributes.f_22277_);
      TargetingConditions conditions = TargetingConditions.m_148352_().m_26883_(this.m_21133_(Attributes.f_22277_)).m_26888_(selector);
      return this.m_9236_().m_45971_(LivingEntity.class, conditions, this, this.m_20191_().m_82400_(range)).stream().filter(e -> e != this);
   }

   public int getSpellCastingTime() {
      return this.spellCastingTime;
   }

   public float m_6118_() {
      return super.m_6118_();
   }

   public UseSpellGoal getUseSpellGoal() {
      return this.useSpellGoal;
   }

   public void setHalfHealthLimit(boolean flag) {
      this.f_19804_.m_135381_(SHOULD_NOT_GO_OVER_HALF, flag);
   }

   public int getNextSpellPickCount() {
      return this.nextSpellPickCount;
   }

   public void setNextSpellPickCount(int count) {
      this.nextSpellPickCount = count;
   }

   public enum BossfightStage {
      ATTACKING {
         @Override
         public void init(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
            super.init(entity);
            if (!entity.m_9236_().f_46443_) {
               entity.spellsUsed = 0;
               entity.clearBossFightGoals();
               entity.addBossFightGoal(1, entity.prepareSpellGoal);
               entity.addBossFightGoal(2, entity.useSpellGoal);
               entity.addBossFightGoal(3, entity.attackGoal);
            }
         }

         @Override
         public void finish(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
            super.finish(entity);
            if (!entity.m_9236_().f_46443_) {
               entity.spellsUsed = 0;
               entity.setSpell((SpellType)WitherStormModSymbiontSpellTypes.EMPTY.get());
            }
         }

         @Override
         public boolean shouldMoveToNextStage(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
            return entity.getSpellsUsed() > 5 && !entity.isCastingSpell() && entity.getStageTicks() % 80 == 0 && entity.m_5448_() != null;
         }
      },
      SUMMONING {
         @Override
         public void init(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
            super.init(entity);
            if (!entity.m_9236_().f_46443_) {
               entity.clearBossFightGoals();
               entity.addBossFightGoal(1, entity.summonMobsGoal);
            }
         }
      },
      VULNERABLE {
         @Override
         public void init(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
            super.init(entity);
            if (!entity.m_9236_().f_46443_) {
               entity.clearBossFightGoals();
               entity.addBossFightGoal(1, entity.doNothingGoal);
               entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_POWER_DOWN.get(), 4.0F, 1.0F);
            }
         }

         @Override
         public void finish(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
            super.finish(entity);
         }

         @Override
         public boolean shouldMoveToNextStage(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
            return entity.getStageTicks() > 4800;
         }
      };

      public boolean shouldDoNothing() {
         return false;
      }

      public void init(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
         entity.setStageTicks(0);
      }

      public void finish(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
      }

      public boolean shouldMoveToNextStage(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
         return false;
      }
   }

   public static class DoNothingGoal extends Goal {
      protected final nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity;

      public DoNothingGoal(nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity entity) {
         this.entity = entity;
         this.m_7021_(EnumSet.of(Flag.LOOK, Flag.TARGET, Flag.MOVE, Flag.JUMP));
      }

      public boolean m_8036_() {
         return this.entity.isVulnerable();
      }

      public void m_8037_() {
         float speed = 3.0F;
         float f = Mth.m_14118_(this.entity.m_146909_(), 55.0F);
         float f1 = Mth.m_14036_(f, -speed, speed);
         this.entity.m_146926_(this.entity.m_146909_() + f1);
      }
   }

   public static class SetSpellTimeMessage extends Packet {
      private int id;
      private int time;

      public SetSpellTimeMessage(int id, int time) {
         super(true);
         this.id = id;
         this.time = time;
      }

      public SetSpellTimeMessage() {
         super(false);
      }

      public void decode(FriendlyByteBuf buffer) {
         this.id = buffer.m_130242_();
         this.time = buffer.readInt();
      }

      public void encode(FriendlyByteBuf buffer) {
         buffer.m_130130_(this.id);
         buffer.writeInt(this.time);
      }

      public Runnable getProcessor(Context context) {
         return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            Optional<Level> optional = (Optional<Level>)LogicalSidedProvider.CLIENTWORLD.get(context.getDirection().getReceptionSide());
            optional.ifPresent(world -> {
               if (world.m_6815_(this.id) instanceof nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity symbiont) {
                  symbiont.spellCastingTime = this.time;
               }
            });
         });
      }
   }
}
