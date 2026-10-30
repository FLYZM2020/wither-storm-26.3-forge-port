/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntitySelector;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobSpawnType;
/*     */ import net.minecraft.world.entity.MobType;
/*     */ import net.minecraft.world.entity.PathfinderMob;
/*     */ import net.minecraft.world.entity.SpawnGroupData;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.monster.RangedAttackMob;
/*     */ import net.minecraft.world.entity.monster.zombie.Zombie;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedZombie extends Zombie implements WitherSickened, RangedAttackMob {
/*  50 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedZombie.class, EntityDataSerializers.BOOLEAN);
/*  51 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedZombie(EntityType<? extends SickenedZombie> type, Level world) {
/*  55 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  61 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  66 */     return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.FOLLOW_RANGE, 48.0D).add(Attributes.MOVEMENT_SPEED, 0.2800000011920929D).add(Attributes.ATTACK_DAMAGE, 3.5D).add(Attributes.ARMOR, 2.2D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addBehaviourGoals() {
/*  72 */     super.addBehaviourGoals();
/*  73 */     this.goalSelector.addGoal(2, (Goal)new SickenedZombieTridentAttackGoal(this, 1.0D, 40, 10.0F));
/*  74 */     this.targetSelector.addGoal(1, (Goal)(new HurtByTargetGoal((PathfinderMob)this, new Class[0])).setAlertOthers(new Class[0]));
/*  75 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  76 */     this.targetSelector.addGoal(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  82 */     super.tick();
/*  83 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int decreaseAirSupply(int supply) {
/*  89 */     return supply;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSunSensitive() {
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/* 102 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult mobInteract(Player player, InteractionHand hand) {
/* 108 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 109 */     if (result != null) {
/* 110 */       return result;
/*     */     }
/* 112 */     return super.mobInteract(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 118 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(@NotNull MobEffectInstance effect, Entity entity) {
/* 124 */     return (sickenedAddEffect(effect, entity) && super.addEffect(effect, entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 130 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 136 */     super.addAdditionalSaveData(tag);
/* 137 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 143 */     super.readAdditionalSaveData(tag);
/* 144 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 150 */     super.defineSynchedData();
/* 151 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack getSkull() {
/* 157 */     return ItemStack.EMPTY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doUnderWaterConversion() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUnderWaterConverting() {
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 174 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 180 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 186 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 192 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 198 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttack(LivingEntity entity) {
/* 204 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance instance) {
/* 210 */     super.populateDefaultEquipmentSlots(random, instance);
/* 211 */     if (this.random.nextFloat() < ((level().getDifficulty() == Difficulty.HARD) ? 0.05F : 0.01F)) {
/*     */       
/* 213 */       int i = this.random.nextInt(4);
/* 214 */       if (i == 0) {
/* 215 */         setItemSlot(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.IRON_SWORD));
/* 216 */       } else if (i < 4) {
/* 217 */         setItemSlot(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.IRON_SHOVEL));
/* 218 */       } else if (i == 4) {
/* 219 */         setItemSlot(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.GOLDEN_SWORD));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doHurtTarget(Entity target) {
/* 226 */     boolean flag = super.doHurtTarget(target);
/* 227 */     if (flag)
/* 228 */       addWitherToTarget(target); 
/* 229 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 235 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag) {
/* 242 */     ItemStack head = getItemBySlot(EquipmentSlot.HEAD);
/* 243 */     setCanBreakDoors(supportsBreakDoorGoal());
/* 244 */     populateDefaultEquipmentSlots(this.random, difficulty);
/* 245 */     populateDefaultEquipmentEnchantments(this.random, difficulty);
/* 246 */     if (head.is(Items.JACK_O_LANTERN)) {
/*     */       
/* 248 */       setItemSlot(EquipmentSlot.HEAD, new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()));
/*     */     }
/* 250 */     else if (head.is(Items.CARVED_PUMPKIN)) {
/*     */       
/* 252 */       setItemSlot(EquipmentSlot.HEAD, new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()));
/*     */     } 
/* 254 */     if (isBaby()) {
/*     */       
/* 256 */       RandomSource random = level.getRandom();
/* 257 */       if (random.nextFloat() < 0.05D) {
/*     */         
/* 259 */         List<SickenedChicken> list = level.getEntitiesOfClass(SickenedChicken.class, getBoundingBox().inflate(5.0D, 3.0D, 5.0D), EntitySelector.ENTITY_NOT_BEING_RIDDEN);
/* 260 */         if (!list.isEmpty())
/*     */         {
/* 262 */           SickenedChicken sickenedChicken = list.get(0);
/* 263 */           sickenedChicken.setChickenJockey(true);
/* 264 */           startRiding((Entity)sickenedChicken);
/*     */         }
/*     */       
/* 267 */       } else if (random.nextFloat() < 0.05D) {
/*     */         
/* 269 */         SickenedChicken sickenedChicken = (SickenedChicken)((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get()).create(level());
/* 270 */         if (sickenedChicken != null) {
/*     */           
/* 272 */           sickenedChicken.moveTo(getX(), getY(), getZ(), getYRot(), 0.0F);
/* 273 */           sickenedChicken.finalizeSpawn(level, difficulty, MobSpawnType.JOCKEY, null, null);
/* 274 */           sickenedChicken.setChickenJockey(true);
/* 275 */           startRiding((Entity)sickenedChicken);
/* 276 */           level.addFreshEntity((Entity)sickenedChicken);
/*     */         } 
/*     */       } 
/*     */     } 
/* 280 */     return groupData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void performRangedAttack(LivingEntity zombie, float p_32357_) {
/* 285 */     ThrownTrident throwntrident = new ThrownTrident(level(), (LivingEntity)this, new ItemStack((ItemLike)Items.TRIDENT));
/* 286 */     double d0 = zombie.getX() - getX();
/* 287 */     double d1 = zombie.getY(0.3333333333333333D) - throwntrident.getY();
/* 288 */     double d2 = zombie.getZ() - getZ();
/* 289 */     double d3 = Math.sqrt(d0 * d0 + d2 * d2);
/* 290 */     throwntrident.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - level().getDifficulty().getId() * 4));
/* 291 */     playSound(SoundEvents.DROWNED_SHOOT, 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
/* 292 */     level().addFreshEntity((Entity)throwntrident);
/*     */   }
/*     */   
/*     */   static class SickenedZombieTridentAttackGoal
/*     */     extends RangedAttackGoal
/*     */   {
/*     */     private final SickenedZombie zombie;
/*     */     
/*     */     public SickenedZombieTridentAttackGoal(RangedAttackMob attackMob, double p_25769_, int p_25770_, float p_25771_) {
/* 301 */       super(attackMob, p_25769_, p_25770_, p_25771_);
/* 302 */       this.zombie = (SickenedZombie)attackMob;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canUse() {
/* 307 */       return (super.canUse() && this.zombie.getMainHandItem().is(Items.TRIDENT));
/*     */     }
/*     */ 
/*     */     
/*     */     public void start() {
/* 312 */       super.start();
/* 313 */       this.zombie.setAggressive(true);
/* 314 */       this.zombie.startUsingItem(InteractionHand.MAIN_HAND);
/*     */     }
/*     */ 
/*     */     
/*     */     public void stop() {
/* 319 */       super.stop();
/* 320 */       this.zombie.stopUsingItem();
/* 321 */       this.zombie.setAggressive(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hurt(DamageSource source, float amount) {
/* 327 */     if (!sickenedCanBeHurt(source, amount))
/* 328 */       return false; 
/* 329 */     return super.hurt(source, amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedZombie.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */