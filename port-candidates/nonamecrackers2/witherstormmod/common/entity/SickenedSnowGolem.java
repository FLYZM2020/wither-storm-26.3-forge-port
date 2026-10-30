/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobType;
/*     */ import net.minecraft.world.entity.PathfinderMob;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.FloatGoal;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.animal.SnowGolem;
/*     */ import net.minecraft.world.entity.monster.Enemy;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.monster.RangedAttackMob;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.gameevent.GameEvent;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import nonamecrackers2.witherstormmod.common.util.SnowballAccessor;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedSnowGolem extends SnowGolem implements WitherSickened, Enemy {
/*  53 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedSnowGolem.class, EntityDataSerializers.BOOLEAN);
/*  54 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedSnowGolem(EntityType<? extends SickenedSnowGolem> type, Level world) {
/*  58 */     super(type, world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  64 */     this.goalSelector.addGoal(0, (Goal)new FloatGoal((Mob)this));
/*     */     
/*  66 */     this.goalSelector.addGoal(1, (Goal)new RangedAttackGoal((RangedAttackMob)this, 1.25D, 12, 10.0F));
/*  67 */     this.goalSelector.addGoal(2, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0D, 1.0000001E-5F));
/*  68 */     this.goalSelector.addGoal(3, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0F));
/*  69 */     this.goalSelector.addGoal(4, (Goal)new RandomLookAroundGoal((Mob)this));
/*  70 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  71 */     this.targetSelector.addGoal(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  77 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  82 */     return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.24D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int decreaseAirSupply(int supply) {
/*  88 */     return supply;
/*     */   }
/*     */   
/*     */   public boolean isSensitiveToWater() {
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  97 */     super.tick();
/*  98 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shear(SoundSource source) {
/* 104 */     level().playSound((Player)null, (Entity)this, SoundEvents.SNOW_GOLEM_SHEAR, source, 1.0F, 1.0F);
/* 105 */     if (!level().isClientSide()) {
/*     */       
/* 107 */       setPumpkin(false);
/* 108 */       spawnAtLocation(new ItemStack((ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get()), 1.7F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void performRangedAttack(LivingEntity entity, float p_29913_) {
/* 115 */     Snowball snowball = new Snowball(level(), (LivingEntity)this);
/* 116 */     if (this.random.nextFloat() < 0.1D)
/* 117 */       ((SnowballAccessor)snowball).setWitherEffect(true); 
/* 118 */     double d0 = entity.getEyeY() - 1.100000023841858D;
/* 119 */     double d1 = entity.getX() - getX();
/* 120 */     double d2 = d0 - snowball.getY();
/* 121 */     double d3 = entity.getZ() - getZ();
/* 122 */     double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224D;
/* 123 */     snowball.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
/* 124 */     playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (getRandom().nextFloat() * 0.4F + 0.8F));
/* 125 */     level().addFreshEntity((Entity)snowball);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/* 131 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult mobInteract(Player player, InteractionHand hand) {
/* 137 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 138 */     if (result != null) {
/* 139 */       return result;
/*     */     }
/* 141 */     return super.mobInteract(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
/* 147 */     world.playSound(null, (Entity)this, SoundEvents.SNOW_GOLEM_SHEAR, (player == null) ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
/* 148 */     gameEvent(GameEvent.SHEAR, (Entity)player);
/* 149 */     if (!world.isClientSide()) {
/*     */       
/* 151 */       setPumpkin(false);
/* 152 */       return Collections.singletonList(new ItemStack((ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get()));
/*     */     } 
/* 154 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffectInstance effect, Entity entity) {
/* 166 */     return sickenedAddEffect(effect, entity) ? super.addEffect(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 172 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 178 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 184 */     super.addAdditionalSaveData(tag);
/* 185 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 191 */     super.readAdditionalSaveData(tag);
/* 192 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 198 */     super.defineSynchedData();
/* 199 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 205 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 211 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 217 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 223 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 229 */     return SoundEvents.SNOW_GOLEM_AMBIENT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 235 */     return SoundEvents.SNOW_GOLEM_HURT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 241 */     return SoundEvents.SNOW_GOLEM_DEATH;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 247 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttack(LivingEntity entity) {
/* 253 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doHurtTarget(Entity target) {
/* 259 */     boolean flag = super.doHurtTarget(target);
/* 260 */     if (flag)
/* 261 */       addWitherToTarget(target); 
/* 262 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hurt(DamageSource source, float amount) {
/* 268 */     if (!sickenedCanBeHurt(source, amount))
/* 269 */       return false; 
/* 270 */     return super.hurt(source, amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedSnowGolem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */