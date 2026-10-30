/*      */ package nonamecrackers2.witherstormmod.common.entity;
/*      */ 
/*      */ import com.google.common.base.Predicate;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.EnumSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.UUID;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.stream.Collectors;
/*      */ import java.util.stream.Stream;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.core.BlockPos;
/*      */ import net.minecraft.core.particles.BlockParticleOption;
/*      */ import net.minecraft.core.particles.ParticleOptions;
/*      */ import net.minecraft.core.particles.ParticleTypes;
/*      */ import net.minecraft.nbt.CompoundTag;
/*      */ import net.minecraft.nbt.ListTag;
/*      */ import net.minecraft.nbt.NbtUtils;
/*      */ import net.minecraft.nbt.Tag;
/*      */ import net.minecraft.network.FriendlyByteBuf;
/*      */ import net.minecraft.network.chat.Component;
/*      */ import net.minecraft.network.syncher.EntityDataAccessor;
/*      */ import net.minecraft.network.syncher.EntityDataSerializer;
/*      */ import net.minecraft.network.syncher.EntityDataSerializers;
/*      */ import net.minecraft.network.syncher.SynchedEntityData;
/*      */ import net.minecraft.resources.ResourceLocation;
/*      */ import net.minecraft.server.level.ServerBossEvent;
/*      */ import net.minecraft.server.level.ServerLevel;
/*      */ import net.minecraft.server.level.ServerPlayer;
/*      */ import net.minecraft.sounds.SoundEvent;
/*      */ import net.minecraft.tags.DamageTypeTags;
/*      */ import net.minecraft.util.Mth;
/*      */ import net.minecraft.util.random.SimpleWeightedRandomList;
/*      */ import net.minecraft.world.BossEvent;
/*      */ import net.minecraft.world.Difficulty;
/*      */ import net.minecraft.world.DifficultyInstance;
/*      */ import net.minecraft.world.damagesource.DamageSource;
/*      */ import net.minecraft.world.entity.Entity;
/*      */ import net.minecraft.world.entity.EntitySelector;
/*      */ import net.minecraft.world.entity.EntityType;
/*      */ import net.minecraft.world.entity.LivingEntity;
/*      */ import net.minecraft.world.entity.Mob;
/*      */ import net.minecraft.world.entity.MobSpawnType;
/*      */ import net.minecraft.world.entity.MobType;
/*      */ import net.minecraft.world.entity.PathfinderMob;
/*      */ import net.minecraft.world.entity.SpawnGroupData;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*      */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*      */ import net.minecraft.world.entity.ai.control.LookControl;
/*      */ import net.minecraft.world.entity.ai.goal.FloatGoal;
/*      */ import net.minecraft.world.entity.ai.goal.Goal;
/*      */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*      */ import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
/*      */ import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
/*      */ import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
/*      */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*      */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*      */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*      */ import net.minecraft.world.entity.item.ItemEntity;
/*      */ import net.minecraft.world.entity.monster.Monster;
/*      */ import net.minecraft.world.entity.player.Player;
/*      */ import net.minecraft.world.entity.projectile.Projectile;
/*      */ import net.minecraft.world.item.ItemStack;
/*      */ import net.minecraft.world.level.Level;
/*      */ import net.minecraft.world.level.ServerLevelAccessor;
/*      */ import net.minecraft.world.level.block.Blocks;
/*      */ import net.minecraft.world.level.block.state.BlockState;
/*      */ import net.minecraft.world.level.storage.loot.LootParams;
/*      */ import net.minecraft.world.level.storage.loot.LootTable;
/*      */ import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
/*      */ import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
/*      */ import net.minecraft.world.phys.AABB;
/*      */ import net.minecraft.world.phys.Vec3;
/*      */ import net.minecraftforge.api.distmarker.Dist;
/*      */ import net.minecraftforge.common.util.LogicalSidedProvider;
/*      */ import net.minecraftforge.fml.DistExecutor;
/*      */ import net.minecraftforge.network.NetworkEvent;
/*      */ import net.minecraftforge.network.PacketDistributor;
/*      */ import net.minecraftforge.registries.IForgeRegistry;
/*      */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*      */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*      */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*      */ import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
/*      */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*      */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.symbiont.PrepareSpellGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.symbiont.SummonMobsGoal;
/*      */ import nonamecrackers2.witherstormmod.common.entity.goal.symbiont.UseSpellGoal;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSymbiontSpellTypes;
/*      */ import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
/*      */ import nonamecrackers2.witherstormmod.common.util.ConditionalLookController;
/*      */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*      */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WitheredSymbiontEntity
/*      */   extends Monster
/*      */   implements BossThemeEntity
/*      */ {
/*  118 */   private static final EntityDataAccessor<BossfightStage> BOSSFIGHT_STAGE = SynchedEntityData.m_135353_(WitheredSymbiontEntity.class, (EntityDataSerializer)WitherStormModDataSerializers.BOSSFIGHT_STAGE_ENUM);
/*  119 */   private static final EntityDataAccessor<SpellType> SPELL_TYPE = SynchedEntityData.m_135353_(WitheredSymbiontEntity.class, WitherStormModDataSerializers.SPELL_TYPE);
/*  120 */   private static final EntityDataAccessor<Boolean> NON_BOSS_MODE = SynchedEntityData.m_135353_(WitheredSymbiontEntity.class, EntityDataSerializers.f_135035_);
/*  121 */   private static final EntityDataAccessor<Boolean> RUSH_MODE = SynchedEntityData.m_135353_(WitheredSymbiontEntity.class, EntityDataSerializers.f_135035_);
/*  122 */   private static final EntityDataAccessor<Boolean> SHOULD_NOT_GO_OVER_HALF = SynchedEntityData.m_135353_(WitheredSymbiontEntity.class, EntityDataSerializers.f_135035_); public static final Predicate<LivingEntity> TARGET_PREDICATE; public static final Predicate<LivingEntity> MOB_TARGET_PREDICATE; public static final Predicate<LivingEntity> PULSE_PREDICATE; static {
/*  123 */     TARGET_PREDICATE = (entity -> (entity.m_6097_() && entity instanceof Player));
/*  124 */     MOB_TARGET_PREDICATE = (entity -> (entity.m_6097_() && !(entity instanceof WitherSickened) && !(entity instanceof WitheredSymbiontEntity) && !(entity instanceof WitherStormEntity) && !(entity instanceof WitherStormHeadEntity) && !(entity instanceof TentacleEntity) && !(entity instanceof net.minecraft.world.entity.monster.EnderMan) && !(entity instanceof net.minecraft.world.entity.boss.enderdragon.EnderDragon) && !(entity instanceof net.minecraft.world.entity.boss.wither.WitherBoss) && !(entity instanceof net.minecraft.world.entity.monster.WitherSkeleton) && (entity instanceof net.minecraft.world.entity.npc.AbstractVillager || entity instanceof net.minecraft.world.entity.animal.AbstractGolem || entity instanceof Monster || entity instanceof net.minecraft.world.entity.animal.Animal || entity instanceof net.minecraft.world.entity.NeutralMob || entity instanceof Player)));
/*      */     
/*  126 */     PULSE_PREDICATE = (entity -> (entity.m_6097_() && !(entity instanceof WitheredSymbiontEntity) && !(entity instanceof WitherStormEntity) && !(entity instanceof WitherStormHeadEntity) && !(entity instanceof TentacleEntity) && !(entity instanceof CommandBlockEntity)));
/*  127 */   } private static final SimpleWeightedRandomList<EntityType<? extends Mob>> SYMBIONT_NORMAL_MOBS = SimpleWeightedRandomList.m_146263_()
/*  128 */     .m_146271_(WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 8)
/*  129 */     .m_146271_(WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 4)
/*  130 */     .m_146271_(WitherStormModEntityTypes.SICKENED_SKELETON.get(), 8)
/*  131 */     .m_146271_(WitherStormModEntityTypes.SICKENED_SPIDER.get(), 4)
/*  132 */     .m_146271_(WitherStormModEntityTypes.SICKENED_CREEPER.get(), 1)
/*      */     
/*  134 */     .m_146271_(WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), 2)
/*      */     
/*  136 */     .m_146271_(WitherStormModEntityTypes.SICKENED_CHICKEN.get(), 3)
/*  137 */     .m_146271_(WitherStormModEntityTypes.SICKENED_COW.get(), 3)
/*  138 */     .m_146271_(WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), 1)
/*  139 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PIG.get(), 3)
/*  140 */     .m_146271_(WitherStormModEntityTypes.SICKENED_BEE.get(), 4)
/*      */     
/*  142 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PARROT.get(), 4)
/*  143 */     .m_146271_(WitherStormModEntityTypes.SICKENED_WOLF.get(), 4)
/*  144 */     .m_146271_(WitherStormModEntityTypes.SICKENED_CAT.get(), 4)
/*      */     
/*  146 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 3)
/*  147 */     .m_146271_(WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 3)
/*  148 */     .m_146270_();
/*      */   
/*  150 */   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> SYMBIONT_HARDER_MOBS = SimpleWeightedRandomList.m_146263_()
/*  151 */     .m_146271_(WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), 8)
/*  152 */     .m_146271_(WitherStormModEntityTypes.SICKENED_VILLAGER.get(), 6)
/*  153 */     .m_146271_(WitherStormModEntityTypes.SICKENED_SKELETON.get(), 8)
/*  154 */     .m_146271_(WitherStormModEntityTypes.SICKENED_SPIDER.get(), 6)
/*  155 */     .m_146271_(WitherStormModEntityTypes.SICKENED_CREEPER.get(), 1)
/*      */     
/*  157 */     .m_146271_(WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), 1)
/*      */     
/*  159 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PHANTOM.get(), 3)
/*  160 */     .m_146271_(WitherStormModEntityTypes.SICKENED_BEE.get(), 6)
/*      */     
/*  162 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PARROT.get(), 1)
/*  163 */     .m_146271_(WitherStormModEntityTypes.SICKENED_WOLF.get(), 2)
/*  164 */     .m_146271_(WitherStormModEntityTypes.SICKENED_CAT.get(), 2)
/*      */     
/*  166 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 3)
/*  167 */     .m_146271_(WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 6)
/*  168 */     .m_146270_();
/*      */   private List<Goal> bossFightGoals;
/*      */   private MeleeAttackGoal attackGoal;
/*      */   private PrepareSpellGoal prepareSpellGoal;
/*      */   private UseSpellGoal useSpellGoal;
/*      */   private SummonMobsGoal summonMobsGoal;
/*      */   private DoNothingGoal doNothingGoal;
/*      */   private int stageTicks;
/*      */   private int spellCastingTime;
/*      */   private int nextSpellPickCount;
/*      */   private boolean isDoingSmash;
/*      */   private int smashAirTime;
/*  180 */   private List<LivingEntity> entitiesToThrow = new ArrayList<>();
/*      */   private int spellsUsed;
/*      */   private float crouchAnim;
/*      */   private float crouchAnimO;
/*  184 */   private final ServerBossEvent bossInfo = new ServerBossEvent(m_5446_(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS); private int specialDeathTime;
/*      */   @Nullable
/*      */   private UUID summoner;
/*      */   private int attackDelay;
/*  188 */   private List<ItemStack> dropItems = Lists.newArrayList();
/*      */   private float tearAlpha;
/*      */   private float tearAlphaO;
/*  191 */   private List<UUID> fightContributors = Lists.newArrayList();
/*      */   @Nullable
/*      */   private SymbiontSpell spellInstance;
/*      */   
/*      */   public WitheredSymbiontEntity(EntityType<? extends WitheredSymbiontEntity> type, Level world) {
/*  196 */     super(type, world);
/*  197 */     this.f_21364_ = 150;
/*  198 */     this.f_21365_ = (LookControl)new ConditionalLookController((Mob)this, entity -> (!entity.isVulnerable() && !entity.m_21224_()));
/*      */   }
/*      */ 
/*      */   
/*      */   private static TargetingConditions protectPredicate(double radius) {
/*  203 */     return TargetingConditions.m_148353_().m_26883_(radius).m_26888_(entity -> entity instanceof Player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStepHeight() {
/*  209 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_8097_() {
/*  215 */     super.m_8097_();
/*  216 */     this.f_19804_.m_135372_(BOSSFIGHT_STAGE, BossfightStage.ATTACKING);
/*  217 */     this.f_19804_.m_135372_(SPELL_TYPE, WitherStormModSymbiontSpellTypes.EMPTY.get());
/*  218 */     this.f_19804_.m_135372_(NON_BOSS_MODE, Boolean.valueOf(false));
/*  219 */     this.f_19804_.m_135372_(RUSH_MODE, Boolean.valueOf(false));
/*  220 */     this.f_19804_.m_135372_(SHOULD_NOT_GO_OVER_HALF, Boolean.valueOf(true));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_8099_() {
/*  226 */     this.bossFightGoals = new ArrayList<>();
/*  227 */     this.attackGoal = new MeleeAttackGoal((PathfinderMob)this, 1.0D, true);
/*  228 */     this.prepareSpellGoal = new PrepareSpellGoal(this);
/*  229 */     this.useSpellGoal = new UseSpellGoal(this);
/*  230 */     this.summonMobsGoal = new SummonMobsGoal(this, SYMBIONT_NORMAL_MOBS, SYMBIONT_HARDER_MOBS);
/*  231 */     this.doNothingGoal = new DoNothingGoal(this);
/*  232 */     this.bossFightGoals.add(this.attackGoal);
/*  233 */     this.bossFightGoals.add(this.prepareSpellGoal);
/*  234 */     this.bossFightGoals.add(this.useSpellGoal);
/*  235 */     this.bossFightGoals.add(this.summonMobsGoal);
/*  236 */     this.bossFightGoals.add(this.doNothingGoal);
/*      */     
/*  238 */     this.f_21345_.m_25352_(1, (Goal)this.prepareSpellGoal);
/*  239 */     this.f_21345_.m_25352_(2, (Goal)this.useSpellGoal);
/*  240 */     this.f_21345_.m_25352_(3, (Goal)this.attackGoal);
/*  241 */     this.f_21345_.m_25352_(4, (Goal)new FloatGoal((Mob)this));
/*  242 */     this.f_21345_.m_25352_(5, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 0.699999988079071D));
/*  243 */     this.f_21345_.m_25352_(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0F));
/*  244 */     this.f_21345_.m_25352_(7, (Goal)new RandomLookAroundGoal((Mob)this));
/*  245 */     this.f_21346_.m_25352_(1, (Goal)(new HurtByTargetGoal((PathfinderMob)this, new Class[0])).m_26044_(new Class[0]));
/*  246 */     this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, 10, true, false, (Predicate)TARGET_PREDICATE));
/*  247 */     if (((Boolean)WitherStormModConfig.SERVER.shouldSymbiontAttackMobs.get()).booleanValue()) {
/*  248 */       this.f_21346_.m_25352_(3, (Goal)new NearestAttackableTargetGoal((Mob)this, Mob.class, 10, true, false, (Predicate)MOB_TARGET_PREDICATE));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void m_7380_(@NotNull CompoundTag compound) {
/*  254 */     super.m_7380_(compound);
/*  255 */     compound.m_128379_("IsNonBossMode", isNonBossMode());
/*  256 */     compound.m_128379_("IsRushMode", isRushMode());
/*  257 */     compound.m_128405_("Stage", getStage().ordinal());
/*  258 */     compound.m_128405_("StageTicks", getStageTicks());
/*  259 */     compound.m_128359_("Spell", ((ResourceLocation)Objects.<ResourceLocation>requireNonNull(((IForgeRegistry)WitherStormModRegistries.SPELL_TYPES.get()).getKey(getSpell()), "Unregistered spell")).toString());
/*  260 */     compound.m_128405_("SpellCastingTicks", this.spellCastingTime);
/*  261 */     compound.m_128405_("NextSpellPick", this.nextSpellPickCount);
/*  262 */     compound.m_128379_("Smashing", isSmashing());
/*  263 */     compound.m_128405_("SmashAirTime", this.smashAirTime);
/*  264 */     compound.m_128405_("SpellsUsed", getSpellsUsed());
/*  265 */     if (this.summoner != null)
/*  266 */       compound.m_128362_("Summoner", this.summoner); 
/*  267 */     compound.m_128405_("AttackDelay", this.attackDelay);
/*  268 */     if (!this.dropItems.isEmpty()) {
/*      */       
/*  270 */       ListTag dropItems = new ListTag();
/*  271 */       for (ItemStack stack : this.dropItems) {
/*      */         
/*  273 */         if (!stack.m_41619_()) {
/*      */           
/*  275 */           CompoundTag tag = new CompoundTag();
/*  276 */           stack.m_41739_(tag);
/*  277 */           dropItems.add(tag);
/*      */         } 
/*      */       } 
/*  280 */       compound.m_128365_("DropItems", (Tag)dropItems);
/*      */     } 
/*  282 */     compound.m_128379_("ShouldNotGoOverHalf", ((Boolean)this.f_19804_.m_135370_(SHOULD_NOT_GO_OVER_HALF)).booleanValue());
/*  283 */     ListTag fightContributors = new ListTag();
/*  284 */     for (UUID id : this.fightContributors)
/*  285 */       fightContributors.add(NbtUtils.m_129226_(id)); 
/*  286 */     compound.m_128365_("FightContributors", (Tag)fightContributors);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7378_(@NotNull CompoundTag compound) {
/*  292 */     super.m_7378_(compound);
/*  293 */     if (compound.m_128441_("IsNonBossMode"))
/*  294 */       setNonBossMode(compound.m_128471_("IsNonBossMode")); 
/*  295 */     if (compound.m_128441_("IsRushMode"))
/*  296 */       setRushMode(compound.m_128471_("IsRushMode")); 
/*  297 */     if (compound.m_128441_("Stage")) {
/*      */       
/*  299 */       int ordinal = compound.m_128451_("Stage");
/*  300 */       if (ordinal >= 0 && ordinal < (BossfightStage.values()).length)
/*  301 */         setStage(BossfightStage.values()[ordinal]); 
/*      */     } 
/*  303 */     setStageTicks(compound.m_128451_("StageTicks"));
/*  304 */     if (compound.m_128425_("Spell", 8)) {
/*      */       
/*  306 */       String rawId = compound.m_128461_("Spell");
/*  307 */       ResourceLocation loc = ResourceLocation.m_135820_(rawId);
/*  308 */       if (loc != null) {
/*      */         
/*  310 */         SpellType type = (SpellType)((IForgeRegistry)WitherStormModRegistries.SPELL_TYPES.get()).getValue(loc);
/*  311 */         if (type != null)
/*  312 */           setSpell(type); 
/*      */       } 
/*      */     } 
/*  315 */     this.spellCastingTime = compound.m_128451_("SpellCastingTicks");
/*  316 */     this.nextSpellPickCount = compound.m_128451_("NextSpellPick");
/*  317 */     setSmashing(compound.m_128471_("Smashing"));
/*  318 */     this.smashAirTime = compound.m_128451_("SmashAirTime");
/*  319 */     this.spellsUsed = compound.m_128451_("SpellsUsed");
/*  320 */     if (compound.m_128441_("Summoner"))
/*  321 */       this.summoner = compound.m_128342_("Summoner"); 
/*  322 */     this.attackDelay = compound.m_128451_("AttackDelay");
/*  323 */     if (compound.m_128441_("DropItems")) {
/*      */       
/*  325 */       ListTag dropItems = compound.m_128437_("DropItems", 10);
/*  326 */       for (int i = 0; i < dropItems.size(); i++)
/*  327 */         this.dropItems.add(ItemStack.m_41712_(dropItems.m_128728_(i))); 
/*      */     } 
/*  329 */     if (compound.m_128441_("ShouldNotGoOverHalf"))
/*  330 */       this.f_19804_.m_135381_(SHOULD_NOT_GO_OVER_HALF, Boolean.valueOf(compound.m_128471_("ShouldNotGoOverHalf"))); 
/*  331 */     this.fightContributors.clear();
/*  332 */     ListTag fightContributors = compound.m_128437_("FightContributors", 11);
/*  333 */     for (Tag tag : fightContributors) {
/*  334 */       this.fightContributors.add(NbtUtils.m_129233_(tag));
/*      */     }
/*      */   }
/*      */   
/*      */   public static AttributeSupplier.Builder createAttributes() {
/*  339 */     return Monster.m_33035_().m_22268_(Attributes.f_22276_, 60.0D).m_22268_(Attributes.f_22279_, 0.15D).m_22268_(Attributes.f_22278_, 1.0D).m_22268_(Attributes.f_22281_, 16.0D).m_22268_(Attributes.f_22277_, 45.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int m_7302_(int supply) {
/*  345 */     return supply;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_7324_(@NotNull Entity entity) {
/*  351 */     if (EntitySelector.f_20406_.test(entity) && entity instanceof LivingEntity && TARGET_PREDICATE.test(entity) && m_217043_().m_188503_(20) == 0) {
/*  352 */       m_6710_((LivingEntity)entity);
/*      */     }
/*  354 */     super.m_7324_(entity);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_8107_() {
/*  360 */     super.m_8107_();
/*      */     
/*  362 */     this.stageTicks++;
/*      */     
/*  364 */     if (this.spellCastingTime > 0) {
/*      */       
/*  366 */       this.spellCastingTime--;
/*  367 */       doSpellCasting();
/*  368 */       if (this.spellCastingTime <= 0) {
/*  369 */         castSpell();
/*      */       }
/*  371 */       SpellType type = getSpell();
/*  372 */       if (type.doProtection()) {
/*      */         
/*  374 */         double radius = type.protectionRadius();
/*  375 */         List<Player> entities = m_9236_().m_45976_(Player.class, m_20191_().m_82400_(radius));
/*  376 */         for (Player player : entities) {
/*      */           
/*  378 */           if (!this.entitiesToThrow.contains(player) && protectPredicate(radius).m_26885_((LivingEntity)this, (LivingEntity)player)) {
/*      */             
/*  380 */             this.entitiesToThrow.add(player);
/*  381 */             if (!(m_9236_()).f_46443_) {
/*  382 */               m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_LAUNCH_MOB.get(), 16.0F, 1.0F);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*  387 */       for (int i = 0; i < this.entitiesToThrow.size(); i++) {
/*      */         
/*  389 */         LivingEntity entity = this.entitiesToThrow.get(i);
/*  390 */         if (protectPredicate(type.protectionRadius()).m_26885_((LivingEntity)this, entity)) {
/*      */           
/*  392 */           Vec3 delta = m_20182_().m_82546_(entity.m_20182_()).m_82541_().m_82520_(0.0D, -0.5D, 0.0D).m_82490_(-type.protectionThrowStrength());
/*  393 */           entity.m_20256_(delta);
/*      */         }
/*      */         else {
/*      */           
/*  397 */           this.entitiesToThrow.remove(i);
/*      */         } 
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
/*  432 */     if (this.nextSpellPickCount > 0) {
/*  433 */       this.nextSpellPickCount--;
/*      */     }
/*  435 */     if (isSmashing())
/*      */     {
/*  437 */       if (this.smashAirTime > 0) {
/*      */         
/*  439 */         this.smashAirTime--;
/*  440 */         if (this.smashAirTime <= 0) {
/*  441 */           m_20334_(m_20184_().m_7096_(), -5.0D, m_20184_().m_7094_());
/*      */         
/*      */         }
/*      */       }
/*  445 */       else if (m_20096_()) {
/*      */         
/*  447 */         setSmashing(false);
/*  448 */         if (!m_9236_().m_5776_()) {
/*      */           
/*  450 */           float strength = 1.5F;
/*  451 */           if (shouldIncreaseDifficulty())
/*  452 */             strength = 2.5F; 
/*  453 */           m_9236_().m_254849_((Entity)this, m_20185_(), m_20186_(), m_20189_(), strength, Level.ExplosionInteraction.MOB);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  459 */     if (isCastingSpell() || isSummoningMobs())
/*      */     {
/*  461 */       for (int i = 0; i < 5; i++) {
/*      */         
/*  463 */         double x = m_20185_() + this.f_19796_.m_188583_() * 2.0D;
/*  464 */         double y = m_20188_() + this.f_19796_.m_188583_() * 2.0D;
/*  465 */         double z = m_20189_() + this.f_19796_.m_188583_() * 2.0D;
/*  466 */         Vec3 delta = m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.2D, 0.2D, 0.2D);
/*  467 */         m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
/*      */       } 
/*      */     }
/*      */     
/*  471 */     if (m_20184_().m_165925_() > 2.500000277905201E-7D && this.f_19796_.m_188503_(5) == 0) {
/*      */       
/*  473 */       int i = Mth.m_14107_(m_20185_());
/*  474 */       int j = Mth.m_14107_(m_20186_() - 0.20000000298023224D);
/*  475 */       int k = Mth.m_14107_(m_20189_());
/*  476 */       BlockPos pos = new BlockPos(i, j, k);
/*  477 */       BlockState state = m_9236_().m_8055_(pos);
/*  478 */       if (!state.m_60713_(Blocks.f_50016_)) {
/*  479 */         m_9236_().m_7106_((ParticleOptions)(new BlockParticleOption(ParticleTypes.f_123794_, state)).setPos(pos), m_20185_() + (this.f_19796_.m_188501_() - 0.5D) * m_20205_(), m_20186_() + 0.1D, m_20189_() + (this.f_19796_.m_188501_() - 0.5D) * m_20205_(), 4.0D * (this.f_19796_.m_188501_() - 0.5D), 0.5D, (this.f_19796_.m_188501_() - 0.5D) * 4.0D);
/*      */       }
/*      */     } 
/*  482 */     if (!m_9236_().m_5776_())
/*      */     {
/*  484 */       if (getStage().shouldMoveToNextStage(this)) {
/*  485 */         nextStage();
/*      */       }
/*      */     }
/*  488 */     if (this.attackDelay > 0) {
/*      */       
/*  490 */       this.attackDelay--;
/*  491 */       if (this.attackDelay <= 0 && isVulnerable()) {
/*  492 */         setStage(BossfightStage.ATTACKING);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void m_8119_() {
/*  499 */     super.m_8119_();
/*      */     
/*  501 */     this.crouchAnimO = this.crouchAnim;
/*  502 */     if (isVulnerable()) {
/*      */       
/*  504 */       this.crouchAnim += (1.0F - this.crouchAnim) * 0.1F + 0.02F;
/*  505 */       if (this.crouchAnim > 0.6F) {
/*  506 */         this.crouchAnim = 0.6F;
/*      */       }
/*      */     } else {
/*      */       
/*  510 */       this.crouchAnim += -this.crouchAnim * 0.4F - 0.1F;
/*  511 */       if (this.crouchAnim < 0.0F) {
/*  512 */         this.crouchAnim = 0.0F;
/*      */       }
/*      */     } 
/*  515 */     this.tearAlphaO = this.tearAlpha;
/*  516 */     if (((Boolean)WitherStormModConfig.SERVER.attackableWhenNotVulnerable.get()).booleanValue() || isVulnerable()) {
/*      */       
/*  518 */       if (this.tearAlpha < 1.0F) {
/*  519 */         this.tearAlpha += 0.05F;
/*      */       }
/*  521 */     } else if (this.tearAlpha > 0.0F) {
/*      */       
/*  523 */       this.tearAlpha -= 0.05F;
/*      */     } 
/*      */     
/*  526 */     if (!m_9236_().m_5776_()) {
/*  527 */       this.bossInfo.m_142711_(m_21223_() / m_21233_());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean m_7327_(Entity entity) {
/*  533 */     float f = getAttackDamage();
/*  534 */     float f1 = ((int)f > 0) ? (f / 2.0F + this.f_19796_.m_188503_((int)f)) : f;
/*  535 */     boolean flag = entity.m_6469_(m_269291_().m_269333_((LivingEntity)this), f1);
/*  536 */     if (flag) {
/*      */       
/*  538 */       entity.m_20256_(entity.m_20184_().m_82520_(0.0D, 0.8D, 0.0D));
/*  539 */       m_19970_((LivingEntity)this, entity);
/*      */     } 
/*      */     
/*  542 */     return flag;
/*      */   }
/*      */ 
/*      */   
/*      */   private float getAttackDamage() {
/*  547 */     return (float)m_21133_(Attributes.f_22281_);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent m_7515_() {
/*  553 */     return isVulnerable() ? null : (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_AMBIENT.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/*  559 */     if (entity instanceof Mob) { Mob mob = (Mob)entity; if (WorldTainting.getInstance().convertMob(mob, false)); }  return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent m_7975_(@NotNull DamageSource source) {
/*  565 */     return (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_HURT.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent m_5592_() {
/*  571 */     if (isNonBossMode()) {
/*  572 */       return (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_NORMAL_DEATH.get();
/*      */     }
/*  574 */     return (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_DEATH.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float m_6100_() {
/*  580 */     return m_21224_() ? 1.0F : super.m_6100_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_7355_(@NotNull BlockPos pos, @NotNull BlockState state) {
/*  586 */     m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_STEP.get(), 0.3F, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6573_(@NotNull Player player) {
/*  592 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6043_() {
/*  598 */     if (m_9236_().m_46791_() == Difficulty.PEACEFUL && m_8028_()) {
/*      */       
/*  600 */       m_146870_();
/*      */     }
/*  602 */     else if (!m_21532_() && !m_8023_()) {
/*      */       
/*  604 */       List<WitherStormEntity> entities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)m_9236_(), WitherStormEntity.class, m_20191_().m_82400_(400.0D));
/*  605 */       if (entities.isEmpty()) {
/*  606 */         super.m_6043_();
/*      */       } else {
/*  608 */         this.f_20891_ = 0;
/*      */       } 
/*      */     } else {
/*      */       
/*  612 */       this.f_20891_ = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_142535_(float p_225503_1_, float p_225503_2_, @NotNull DamageSource source) {
/*  619 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public MobType m_6336_() {
/*  625 */     return WitherStormModMobTypes.SICKENED;
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public AABB m_6921_() {
/*  631 */     return super.m_6921_().m_82400_(3.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6469_(DamageSource source, float amount) {
/*  637 */     if (source.m_7640_() instanceof net.minecraft.world.entity.projectile.AbstractHurtingProjectile || source.m_7640_() instanceof net.minecraft.world.entity.projectile.AbstractArrow) {
/*      */       
/*  639 */       Projectile projectile = (Projectile)source.m_7640_();
/*  640 */       if (projectile.m_19749_() instanceof WitherSickened || projectile.m_19749_() instanceof WitheredSymbiontEntity)
/*      */       {
/*  642 */         return false;
/*      */       }
/*      */     } 
/*  645 */     if (source.m_269533_(DamageTypeTags.f_268738_))
/*      */     {
/*  647 */       return super.m_6469_(source, amount);
/*      */     }
/*      */ 
/*      */     
/*  651 */     if ((!isCastingSpell() && ((Boolean)WitherStormModConfig.SERVER.attackableWhenNotVulnerable.get()).booleanValue()) || isVulnerable()) {
/*      */       
/*  653 */       Entity entity = source.m_7639_();
/*  654 */       if (entity != null) {
/*      */         
/*  656 */         double angle = Math.atan2(entity.m_20185_() - m_20185_(), entity.m_20189_() - m_20189_()) * 57.29577951308232D;
/*  657 */         double angleDiff = (-this.f_20883_ - angle + 180.0D + 360.0D) % 360.0D;
/*  658 */         if (angleDiff <= 40.0D || angleDiff >= 320.0D) {
/*      */           
/*  660 */           if (isVulnerable() && this.attackDelay <= 0) {
/*  661 */             this.attackDelay = 20;
/*      */           }
/*  663 */           if (!isVulnerable() && entity instanceof LivingEntity && !m_21224_()) {
/*  664 */             ((SpellType)WitherStormModSymbiontSpellTypes.SMASH.get()).makeSpell(this).cast((LivingEntity)entity);
/*      */           }
/*  666 */           if (source.m_269533_(DamageTypeTags.f_268415_)) {
/*  667 */             amount /= 4.0F;
/*      */           }
/*  669 */           if (shouldNotGoOverHalfHealth()) {
/*      */             
/*  671 */             float predictedHealth = m_21223_() - amount;
/*  672 */             float maxHealthHalf = m_21233_() / 2.0F;
/*  673 */             amount = Math.min(amount - maxHealthHalf - predictedHealth, amount);
/*      */           } 
/*      */           
/*  676 */           float healthCurrent = m_21223_();
/*  677 */           boolean flag = super.m_6469_(source, amount);
/*  678 */           float damageDealt = healthCurrent - m_21223_();
/*  679 */           if (damageDealt >= 5.0F && entity instanceof Player && !this.fightContributors.contains(entity.m_20148_())) {
/*  680 */             this.fightContributors.add(entity.m_20148_());
/*      */           }
/*  682 */           return flag;
/*      */         } 
/*      */       } 
/*      */       
/*  686 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  690 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStageTicks() {
/*  697 */     return this.stageTicks;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStageTicks(int ticks) {
/*  702 */     this.stageTicks = ticks;
/*      */   }
/*      */ 
/*      */   
/*      */   public BossfightStage getStage() {
/*  707 */     return (BossfightStage)this.f_19804_.m_135370_(BOSSFIGHT_STAGE);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void clearBossFightGoals() {
/*  712 */     Objects.requireNonNull(this.f_21345_); this.bossFightGoals.forEach(this.f_21345_::m_25363_);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addBossFightGoal(int level, Goal goal) {
/*  717 */     this.f_21345_.m_25352_(level, goal);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStage(BossfightStage stage) {
/*  722 */     if (getStage() != stage)
/*  723 */       getStage().finish(this); 
/*  724 */     this.f_19804_.m_135381_(BOSSFIGHT_STAGE, stage);
/*  725 */     getStage().init(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void nextStage() {
/*  730 */     BossfightStage nextStage = getNextStage(1);
/*  731 */     if (nextStage == BossfightStage.SUMMONING && isNonBossMode()) {
/*  732 */       setStage(getNextStage(2));
/*      */     } else {
/*  734 */       setStage(nextStage);
/*      */     } 
/*      */   }
/*      */   
/*      */   private BossfightStage getNextStage(int advance) {
/*  739 */     int next = getStage().ordinal() + advance;
/*  740 */     if (next < (BossfightStage.values()).length) {
/*  741 */       return BossfightStage.values()[next];
/*      */     }
/*  743 */     return BossfightStage.values()[0];
/*      */   }
/*      */ 
/*      */   
/*      */   public SpellType getSpell() {
/*  748 */     return (SpellType)this.f_19804_.m_135370_(SPELL_TYPE);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSpell(SpellType spell) {
/*  753 */     this.f_19804_.m_135381_(SPELL_TYPE, spell);
/*  754 */     if (!m_9236_().m_5776_())
/*  755 */       this.spellInstance = spell.makeSpell(this); 
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public SymbiontSpell getSpellInstance() {
/*  760 */     return this.spellInstance;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7350_(@NotNull EntityDataAccessor<?> parameter) {
/*  766 */     super.m_7350_(parameter);
/*  767 */     if (BOSSFIGHT_STAGE.equals(parameter)) {
/*  768 */       getStage().init(this);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean hasSpell() {
/*  773 */     return (getSpell() != WitherStormModSymbiontSpellTypes.EMPTY.get());
/*      */   }
/*      */ 
/*      */   
/*      */   public void beginSpellCasting() {
/*  778 */     if (!m_9236_().m_5776_() && this.spellInstance != null) {
/*      */       
/*  780 */       this.spellInstance.start(m_5448_());
/*  781 */       this.spellCastingTime = getSpell().spellTime();
/*  782 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SetSpellTimeMessage(m_19879_(), this.spellCastingTime));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCastingSpell() {
/*  788 */     return (this.spellCastingTime > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSummoningMobs() {
/*  793 */     return (getStage() == BossfightStage.SUMMONING);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isVulnerable() {
/*  798 */     return (getStage() == BossfightStage.VULNERABLE);
/*      */   }
/*      */ 
/*      */   
/*      */   public void breakSpell() {
/*  803 */     if (isCastingSpell()) {
/*      */       
/*  805 */       this.spellCastingTime = 0;
/*  806 */       if (!m_9236_().m_5776_() && this.spellInstance != null)
/*  807 */         this.spellInstance.finish(); 
/*  808 */       m_9236_().m_7605_((Entity)this, (byte)11);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7822_(byte event) {
/*  815 */     if (event == 11) {
/*  816 */       breakSpell();
/*  817 */     } else if (event == 12) {
/*  818 */       activateAttackDelay();
/*      */     } else {
/*  820 */       super.m_7822_(event);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void castSpell() {
/*  825 */     if (!(m_9236_()).f_46443_) {
/*      */       
/*  827 */       if (this.spellInstance != null)
/*      */       {
/*  829 */         if (m_5448_() != null)
/*  830 */           this.spellInstance.cast(m_5448_()); 
/*  831 */         this.spellInstance.finish();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  836 */       for (int i = 0; i < 10; i++) {
/*  837 */         m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), m_20185_(), m_20188_(), m_20189_(), this.f_19796_.m_188583_() * 0.5D, this.f_19796_.m_188583_() * 0.5D, this.f_19796_.m_188583_() * 0.5D);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void doSpellCasting() {
/*  843 */     if (!m_9236_().m_5776_() && this.spellInstance != null) {
/*      */       
/*  845 */       int spellCastingTime = getSpell().spellTime() - this.spellCastingTime;
/*  846 */       if (m_5448_() != null && m_5448_().m_6084_()) {
/*  847 */         this.spellInstance.doCasting(m_5448_());
/*  848 */       } else if (spellCastingTime % 20 == 0) {
/*  849 */         breakSpell();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean canPickSpell() {
/*  855 */     return (!hasSpell() || this.nextSpellPickCount <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAndCastSpell(SpellType type) {
/*  860 */     if (type != WitherStormModSymbiontSpellTypes.EMPTY.get() && !isVulnerable()) {
/*      */       
/*  862 */       this.nextSpellPickCount = 0;
/*  863 */       setSpell(type);
/*  864 */       this.useSpellGoal.nextAttackTickCount = this.f_19797_ + 1;
/*  865 */       m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_PREPARE_SPELL.get(), 4.0F, 1.0F);
/*  866 */       this.nextSpellPickCount = 400 + m_217043_().m_188503_(400) - (shouldIncreaseDifficulty() ? 320 : 0);
/*  867 */       if (shouldNotGoOverHalfHealth() && m_21223_() / m_21233_() <= 0.5F) {
/*  868 */         this.f_19804_.m_135381_(SHOULD_NOT_GO_OVER_HALF, Boolean.valueOf(false));
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setSmashing(boolean flag) {
/*  874 */     this.isDoingSmash = flag;
/*  875 */     if (flag) {
/*  876 */       this.smashAirTime = 20;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isSmashing() {
/*  881 */     return this.isDoingSmash;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSpellsUsed() {
/*  886 */     return this.spellsUsed;
/*      */   }
/*      */ 
/*      */   
/*      */   public void spellUsed() {
/*  891 */     this.spellsUsed++;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getVulnerableAnim(float partialTicks) {
/*  896 */     return Mth.m_14179_(partialTicks, this.crouchAnimO, this.crouchAnim);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6457_(@NotNull ServerPlayer player) {
/*  902 */     super.m_6457_(player);
/*  903 */     this.bossInfo.m_6543_(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6452_(@NotNull ServerPlayer player) {
/*  909 */     super.m_6452_(player);
/*  910 */     this.bossInfo.m_6539_(player);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldIncreaseDifficulty() {
/*  915 */     return (isRushMode() || m_21223_() / m_21233_() <= 0.5F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldNotGoOverHalfHealth() {
/*  920 */     return (!isNonBossMode() && ((Boolean)this.f_19804_.m_135370_(SHOULD_NOT_GO_OVER_HALF)).booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_6153_() {
/*  926 */     if (isNonBossMode()) {
/*      */       
/*  928 */       super.m_6153_();
/*      */     }
/*      */     else {
/*      */       
/*  932 */       int totalTime = 320;
/*  933 */       this.specialDeathTime++;
/*      */       
/*  935 */       for (int i = 0; i < (totalTime - this.specialDeathTime) / 40; i++) {
/*  936 */         m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123755_, m_20208_(1.0D), m_20187_(), m_20262_(1.0D), this.f_19796_.m_188583_() * 0.02D, this.f_19796_.m_188583_() * 0.02D, this.f_19796_.m_188583_() * 0.02D);
/*      */       }
/*  938 */       float speed = 3.0F;
/*  939 */       float f = Mth.m_14118_(m_146909_(), -50.0F);
/*  940 */       float f1 = Mth.m_14036_(f, -speed, speed);
/*  941 */       m_146926_(m_146909_() + f1);
/*      */       
/*  943 */       if (this.specialDeathTime == totalTime) {
/*      */         
/*  945 */         m_142687_(Entity.RemovalReason.KILLED);
/*      */         
/*  947 */         if (!m_9236_().m_5776_())
/*      */         {
/*  949 */           if (this.dropItems != null) {
/*      */             
/*  951 */             List<Player> players = m_9236_().m_6443_(Player.class, m_20191_().m_82400_(20.0D), EntitySelector.f_20408_);
/*  952 */             if (players.size() > 1 && !this.fightContributors.isEmpty()) {
/*      */               
/*  954 */               for (Iterator<UUID> iterator = this.fightContributors.iterator(); iterator.hasNext(); ) { UUID id = iterator.next();
/*      */                 
/*  956 */                 players.stream().filter(p -> p.m_20148_().equals(id)).findFirst().ifPresent(player -> {
/*      */                       for (ItemStack stack : this.dropItems) {
/*      */                         if (!stack.m_41619_()) {
/*      */                           ItemStack copy = stack.m_41777_();
/*      */ 
/*      */                           
/*      */                           if (!player.m_150109_().m_36054_(copy)) {
/*      */                             ItemEntity entity = m_19983_(copy);
/*      */ 
/*      */                             
/*      */                             assert entity != null;
/*      */                             
/*      */                             entity.m_20219_(player.m_20182_());
/*      */                             
/*      */                             entity.m_266426_(id);
/*      */                           } 
/*      */                         } 
/*      */                       } 
/*      */                     }); }
/*      */             
/*      */             } else {
/*  977 */               dropDrops();
/*      */             } 
/*      */           } 
/*      */         }
/*      */         
/*  982 */         for (int j = 0; j < 20; j++) {
/*  983 */           m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123759_, m_20208_(1.0D), m_20187_(), m_20262_(1.0D), this.f_19796_.m_188583_() * 0.02D, this.f_19796_.m_188583_() * 0.02D, this.f_19796_.m_188583_() * 0.02D);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void dropDrops() {
/*  990 */     for (ItemStack stack : this.dropItems) {
/*      */       
/*  992 */       if (!stack.m_41619_()) {
/*      */         
/*  994 */         ItemEntity entity = m_5552_(stack, 8.0F);
/*  995 */         assert entity != null;
/*  996 */         entity.m_20334_(0.0D, -0.08D, 0.0D);
/*  997 */         entity.m_20242_(true);
/*      */       } 
/*      */     } 
/* 1000 */     this.dropItems.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6667_(@NotNull DamageSource source) {
/* 1006 */     super.m_6667_(source);
/* 1007 */     LivingEntity livingEntity = m_21232_(); if (livingEntity instanceof Player) { Player player = (Player)livingEntity;
/*      */       
/* 1009 */       player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> {
/*      */             WitherStormEntity owner = getOwner();
/*      */             
/*      */             if (owner != null) {
/*      */               data.markKilledSymbiont(owner);
/*      */             }
/*      */           }); }
/*      */     
/* 1017 */     for (Player player : m_9236_().m_45976_(Player.class, m_20191_().m_82400_(20.0D)))
/*      */     {
/* 1019 */       player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.makeInvulnerable(Mth.m_14045_(((Integer)WitherStormModConfig.SERVER.playerInvulnerableTime.get()).intValue(), 1, 10) * 1200 + player.m_217043_().m_188503_(1200)));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOwner(WitherStormEntity entity) {
/* 1028 */     this.summoner = entity.m_20148_();
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public WitherStormEntity getOwner() {
/* 1033 */     if (!m_9236_().m_5776_()) {
/*      */       
/* 1035 */       ServerLevel world = (ServerLevel)m_9236_();
/* 1036 */       for (Entity entity : world.m_8583_()) {
/*      */         
/* 1038 */         if (entity.m_20148_().equals(this.summoner) && entity instanceof WitherStormEntity)
/* 1039 */           return (WitherStormEntity)entity; 
/*      */       } 
/*      */     } 
/* 1042 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public SoundEvent getBossTheme() {
/* 1048 */     if (!shouldIncreaseDifficulty()) {
/* 1049 */       return (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_THEME.get();
/*      */     }
/* 1051 */     return (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_INTENSE_THEME.get();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStillAlive() {
/* 1057 */     return m_6084_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getPosition() {
/* 1063 */     return m_20182_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double distanceToPlay() {
/* 1069 */     return 45.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int priority() {
/* 1075 */     return 2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFadeTime() {
/* 1081 */     return 120;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkConfig() {
/* 1087 */     return ((Boolean)WitherStormModConfig.CLIENT.playSymbiontTheme.get()).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void activateAttackDelay() {
/* 1092 */     this.attackDelay = 20;
/* 1093 */     if (!m_9236_().m_5776_()) {
/* 1094 */       m_9236_().m_7605_((Entity)this, (byte)12);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean hasAttackDelay() {
/* 1099 */     return (this.attackDelay > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNonBossMode() {
/* 1104 */     return ((Boolean)this.f_19804_.m_135370_(NON_BOSS_MODE)).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNonBossMode(boolean mode) {
/* 1109 */     this.f_19804_.m_135381_(NON_BOSS_MODE, Boolean.valueOf(mode));
/* 1110 */     if (mode) {
/* 1111 */       this.f_21364_ = 25;
/*      */     } else {
/* 1113 */       this.f_21364_ = 150;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isRushMode() {
/* 1118 */     return ((Boolean)this.f_19804_.m_135370_(RUSH_MODE)).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRushMode(boolean mode) {
/* 1123 */     this.f_19804_.m_135381_(RUSH_MODE, Boolean.valueOf(mode));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_7625_(@NotNull DamageSource source, boolean player) {
/* 1129 */     ResourceLocation id = m_5743_();
/* 1130 */     LootTable table = m_9236_().m_7654_().m_278653_().m_278676_(id);
/* 1131 */     LootParams.Builder builder = (new LootParams.Builder((ServerLevel)m_9236_())).m_287286_(LootContextParams.f_81455_, this).m_287286_(LootContextParams.f_81460_, m_20182_()).m_287286_(LootContextParams.f_81457_, source).m_287289_(LootContextParams.f_81458_, source.m_7639_()).m_287289_(LootContextParams.f_81459_, source.m_7640_());
/* 1132 */     if (player && this.f_20888_ != null) {
/* 1133 */       builder = builder.m_287286_(LootContextParams.f_81456_, this.f_20888_).m_287239_(this.f_20888_.m_36336_());
/*      */     }
/*      */     
/* 1136 */     LootParams params = builder.m_287235_(LootContextParamSets.f_81415_);
/* 1137 */     this.dropItems = (List<ItemStack>)table.m_287214_(params, m_287233_());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_7341_(@NotNull Entity entity) {
/* 1143 */     return (super.m_7341_(entity) && !(entity instanceof net.minecraft.world.entity.vehicle.Boat) && !(entity instanceof net.minecraft.world.entity.vehicle.AbstractMinecart));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Component getWatermark() {
/* 1149 */     return (Component)Component.m_237115_("witherstormmod.watermark.withered_symbiont_theme");
/*      */   }
/*      */ 
/*      */   
/*      */   public float getTearAlpha(float partialTicks) {
/* 1154 */     return Mth.m_14179_(partialTicks, this.tearAlphaO, this.tearAlpha);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SpawnGroupData m_6518_(ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag) {
/* 1161 */     List<Player> nearbyPlayers = level.m_6443_(Player.class, m_20191_().m_82400_(150.0D), e -> (e.m_6084_() && !e.m_5833_()));
/* 1162 */     if (nearbyPlayers.size() > 1) {
/*      */       
/* 1164 */       double healthAddition = nearbyPlayers.size() * ((Double)WitherStormModConfig.SERVER.healthScalePerPlayer.get()).doubleValue();
/* 1165 */       if (healthAddition > 0.0D) {
/*      */         
/* 1167 */         ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(m_21051_(Attributes.f_22276_))).m_22125_(new AttributeModifier("Health scaling", healthAddition, AttributeModifier.Operation.ADDITION));
/* 1168 */         m_21153_(m_21233_());
/*      */       } 
/*      */     } 
/* 1171 */     return super.m_6518_(level, difficulty, spawnType, groupData, tag);
/*      */   }
/*      */ 
/*      */   
/*      */   public LivingEntity getRandomNearbyTargetOrFallback(LivingEntity entity, Predicate<LivingEntity> selector) {
/* 1176 */     List<LivingEntity> entities = (List<LivingEntity>)getNearbyTargets(selector).filter(e -> (e != entity)).collect(Collectors.toList());
/* 1177 */     if (!entities.isEmpty() && this.f_19796_.m_188503_(entities.size() + 1) != 0) {
/*      */       
/* 1179 */       Collections.shuffle(entities);
/* 1180 */       return entities.get(0);
/*      */     } 
/*      */ 
/*      */     
/* 1184 */     return entity;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Stream<LivingEntity> getNearbyTargets(Predicate<LivingEntity> selector) {
/* 1190 */     double range = m_21133_(Attributes.f_22277_);
/* 1191 */     TargetingConditions conditions = TargetingConditions.m_148352_().m_26883_(m_21133_(Attributes.f_22277_)).m_26888_((Predicate)selector);
/* 1192 */     return m_9236_().m_45971_(LivingEntity.class, conditions, (LivingEntity)this, m_20191_().m_82400_(range)).stream().filter(e -> (e != this));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSpellCastingTime() {
/* 1197 */     return this.spellCastingTime;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float m_6118_() {
/* 1203 */     return super.m_6118_();
/*      */   }
/*      */ 
/*      */   
/*      */   public UseSpellGoal getUseSpellGoal() {
/* 1208 */     return this.useSpellGoal;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHalfHealthLimit(boolean flag) {
/* 1213 */     this.f_19804_.m_135381_(SHOULD_NOT_GO_OVER_HALF, Boolean.valueOf(flag));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNextSpellPickCount() {
/* 1218 */     return this.nextSpellPickCount;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNextSpellPickCount(int count) {
/* 1223 */     this.nextSpellPickCount = count;
/*      */   }
/*      */   
/*      */   public enum BossfightStage
/*      */   {
/* 1228 */     ATTACKING
/*      */     {
/*      */       
/*      */       public void init(WitheredSymbiontEntity entity)
/*      */       {
/* 1233 */         super.init(entity);
/* 1234 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1236 */           entity.spellsUsed = 0;
/* 1237 */           entity.clearBossFightGoals();
/* 1238 */           entity.addBossFightGoal(1, (Goal)entity.prepareSpellGoal);
/* 1239 */           entity.addBossFightGoal(2, (Goal)entity.useSpellGoal);
/* 1240 */           entity.addBossFightGoal(3, (Goal)entity.attackGoal);
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void finish(WitheredSymbiontEntity entity) {
/* 1247 */         super.finish(entity);
/* 1248 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1250 */           entity.spellsUsed = 0;
/* 1251 */           entity.setSpell((SpellType)WitherStormModSymbiontSpellTypes.EMPTY.get());
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean shouldMoveToNextStage(WitheredSymbiontEntity entity) {
/* 1258 */         return (entity.getSpellsUsed() > 5 && !entity.isCastingSpell() && entity.getStageTicks() % 80 == 0 && entity.m_5448_() != null);
/*      */       }
/*      */     },
/* 1261 */     SUMMONING
/*      */     {
/*      */       
/*      */       public void init(WitheredSymbiontEntity entity)
/*      */       {
/* 1266 */         super.init(entity);
/* 1267 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1269 */           entity.clearBossFightGoals();
/* 1270 */           entity.addBossFightGoal(1, (Goal)entity.summonMobsGoal);
/*      */         } 
/*      */       }
/*      */     },
/* 1274 */     VULNERABLE
/*      */     {
/*      */       
/*      */       public void init(WitheredSymbiontEntity entity)
/*      */       {
/* 1279 */         super.init(entity);
/* 1280 */         if (!(entity.m_9236_()).f_46443_) {
/*      */           
/* 1282 */           entity.clearBossFightGoals();
/* 1283 */           entity.addBossFightGoal(1, entity.doNothingGoal);
/* 1284 */           entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_POWER_DOWN.get(), 4.0F, 1.0F);
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void finish(WitheredSymbiontEntity entity) {
/* 1291 */         super.finish(entity);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean shouldMoveToNextStage(WitheredSymbiontEntity entity) {
/* 1297 */         return (entity.getStageTicks() > 4800);
/*      */       }
/*      */     };
/*      */ 
/*      */     
/*      */     public boolean shouldDoNothing() {
/* 1303 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void init(WitheredSymbiontEntity entity) {
/* 1308 */       entity.setStageTicks(0);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void finish(WitheredSymbiontEntity entity) {}
/*      */ 
/*      */     
/*      */     public boolean shouldMoveToNextStage(WitheredSymbiontEntity entity) {
/* 1317 */       return false;
/*      */     } }
/*      */   enum null {
/*      */     public void init(WitheredSymbiontEntity entity) { super.init(entity); if (!(entity.m_9236_()).f_46443_) { entity.spellsUsed = 0; entity.clearBossFightGoals(); entity.addBossFightGoal(1, (Goal)entity.prepareSpellGoal); entity.addBossFightGoal(2, (Goal)entity.useSpellGoal); entity.addBossFightGoal(3, (Goal)entity.attackGoal); }  }
/*      */     public void finish(WitheredSymbiontEntity entity) { super.finish(entity); if (!(entity.m_9236_()).f_46443_) { entity.spellsUsed = 0; entity.setSpell((SpellType)WitherStormModSymbiontSpellTypes.EMPTY.get()); }  }
/*      */     public boolean shouldMoveToNextStage(WitheredSymbiontEntity entity) { return (entity.getSpellsUsed() > 5 && !entity.isCastingSpell() && entity.getStageTicks() % 80 == 0 && entity.m_5448_() != null); }
/*      */   } enum null {
/*      */     public void init(WitheredSymbiontEntity entity) { super.init(entity); if (!(entity.m_9236_()).f_46443_) { entity.clearBossFightGoals(); entity.addBossFightGoal(1, (Goal)entity.summonMobsGoal); }  }
/*      */   } enum null {
/*      */     public void init(WitheredSymbiontEntity entity) { super.init(entity); if (!(entity.m_9236_()).f_46443_) { entity.clearBossFightGoals(); entity.addBossFightGoal(1, entity.doNothingGoal); entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_POWER_DOWN.get(), 4.0F, 1.0F); }  } public void finish(WitheredSymbiontEntity entity) { super.finish(entity); } public boolean shouldMoveToNextStage(WitheredSymbiontEntity entity) { return (entity.getStageTicks() > 4800); }
/* 1327 */   } public static class DoNothingGoal extends Goal { public DoNothingGoal(WitheredSymbiontEntity entity) { this.entity = entity;
/* 1328 */       m_7021_(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.TARGET, Goal.Flag.MOVE, Goal.Flag.JUMP)); }
/*      */ 
/*      */     
/*      */     protected final WitheredSymbiontEntity entity;
/*      */     
/*      */     public boolean m_8036_() {
/* 1334 */       return this.entity.isVulnerable();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void m_8037_() {
/* 1340 */       float speed = 3.0F;
/* 1341 */       float f = Mth.m_14118_(this.entity.m_146909_(), 55.0F);
/* 1342 */       float f1 = Mth.m_14036_(f, -speed, speed);
/* 1343 */       this.entity.m_146926_(this.entity.m_146909_() + f1);
/*      */     } }
/*      */ 
/*      */   
/*      */   public static class SetSpellTimeMessage
/*      */     extends Packet
/*      */   {
/*      */     private int id;
/*      */     private int time;
/*      */     
/*      */     public SetSpellTimeMessage(int id, int time) {
/* 1354 */       super(true);
/* 1355 */       this.id = id;
/* 1356 */       this.time = time;
/*      */     }
/*      */ 
/*      */     
/*      */     public SetSpellTimeMessage() {
/* 1361 */       super(false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void decode(FriendlyByteBuf buffer) {
/* 1367 */       this.id = buffer.m_130242_();
/* 1368 */       this.time = buffer.readInt();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void encode(FriendlyByteBuf buffer) {
/* 1374 */       buffer.m_130130_(this.id);
/* 1375 */       buffer.writeInt(this.time);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Runnable getProcessor(NetworkEvent.Context context) {
/* 1381 */       return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\WitheredSymbiontEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */