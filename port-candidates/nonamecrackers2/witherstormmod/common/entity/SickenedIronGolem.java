/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.BlockParticleOption;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.Mth;
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
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
/*     */ import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.animal.golem.AbstractGolem;
/*     */ import net.minecraft.world.entity.animal.golem.IronGolem;
/*     */ import net.minecraft.world.entity.monster.Enemy;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedIronGolem extends AbstractGolem implements WitherSickened, Enemy {
/*  51 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedIronGolem.class, EntityDataSerializers.BOOLEAN);
/*  52 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */   
/*     */   private int attackAnimationTick;
/*     */   
/*     */   public SickenedIronGolem(EntityType<? extends SickenedIronGolem> type, Level level) {
/*  57 */     super(type, level);
/*  58 */     setMaxUpStep(1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  64 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  69 */     return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 60.0D).add(Attributes.FOLLOW_RANGE, 48.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  75 */     this.goalSelector.addGoal(0, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.1D, true));
/*  76 */     this.goalSelector.addGoal(1, (Goal)new MoveTowardsTargetGoal((PathfinderMob)this, 1.0D, 32.0F));
/*  77 */     this.goalSelector.addGoal(2, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0D));
/*  78 */     this.goalSelector.addGoal(3, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0F));
/*  79 */     this.goalSelector.addGoal(4, (Goal)new RandomLookAroundGoal((Mob)this));
/*  80 */     this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
/*  81 */     this.targetSelector.addGoal(1, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  82 */     this.targetSelector.addGoal(2, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  88 */     super.tick();
/*  89 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/*  95 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult mobInteract(Player player, InteractionHand hand) {
/* 101 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 102 */     if (result != null)
/*     */     {
/* 104 */       return result;
/*     */     }
/*     */ 
/*     */     
/* 108 */     ItemStack itemstack = player.getItemInHand(hand);
/* 109 */     if (!itemstack.is(Items.IRON_INGOT))
/*     */     {
/* 111 */       return InteractionResult.PASS;
/*     */     }
/*     */ 
/*     */     
/* 115 */     float f = getHealth();
/* 116 */     heal(getMaxHealth() / 4.0F);
/* 117 */     if (getHealth() == f)
/*     */     {
/* 119 */       return InteractionResult.PASS;
/*     */     }
/*     */ 
/*     */     
/* 123 */     float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
/* 124 */     playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
/* 125 */     if (!(player.getAbilities()).instabuild)
/* 126 */       itemstack.shrink(1); 
/* 127 */     return InteractionResult.sidedSuccess((level()).isClientSide);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 136 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffectInstance effect, Entity entity) {
/* 142 */     return sickenedAddEffect(effect, entity) ? super.addEffect(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 148 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInfectedHealAmount() {
/* 154 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 160 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 166 */     super.addAdditionalSaveData(tag);
/* 167 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 173 */     super.readAdditionalSaveData(tag);
/* 174 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 180 */     super.defineSynchedData();
/* 181 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 187 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 193 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 199 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 205 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 211 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttack(LivingEntity entity) {
/* 217 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int decreaseAirSupply(int supply) {
/* 223 */     return supply;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aiStep() {
/* 229 */     super.aiStep();
/*     */     
/* 231 */     if (this.attackAnimationTick > 0) {
/* 232 */       this.attackAnimationTick--;
/*     */     }
/* 234 */     if (getDeltaMovement().horizontalDistanceSqr() > 2.500000277905201E-7D && this.random.nextInt(5) == 0) {
/*     */       
/* 236 */       int i = Mth.floor(getX());
/* 237 */       int j = Mth.floor(getY() - 0.2D);
/* 238 */       int k = Mth.floor(getZ());
/* 239 */       BlockPos pos = new BlockPos(i, j, k);
/* 240 */       BlockState blockstate = level().getBlockState(pos);
/* 241 */       if (!blockstate.isAir()) {
/* 242 */         level().addParticle((ParticleOptions)(new BlockParticleOption(ParticleTypes.BLOCK, blockstate)).setPos(pos), getX() + (this.random.nextFloat() - 0.5D) * getBbWidth(), getY() + 0.1D, getZ() + (this.random.nextFloat() - 0.5D) * getBbWidth(), 4.0D * (this.random.nextFloat() - 0.5D), 0.5D, (this.random.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doHurtTarget(Entity target) {
/* 249 */     this.attackAnimationTick = 10;
/* 250 */     level().broadcastEntityEvent((Entity)this, (byte)4);
/* 251 */     float damage = (float)getAttributeValue(Attributes.ATTACK_DAMAGE);
/* 252 */     float damageModified = ((int)damage > 0) ? (damage / 2.0F + this.random.nextInt((int)damage)) : damage;
/* 253 */     boolean flag = target.hurt(damageSources().mobAttack((LivingEntity)this), damageModified);
/* 254 */     if (flag) {
/*     */       double d2;
/*     */       
/* 257 */       if (target instanceof LivingEntity) { LivingEntity living = (LivingEntity)target;
/* 258 */         d2 = living.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE); }
/*     */       else
/* 260 */       { d2 = 0.0D; }
/* 261 */        double d0 = d2;
/* 262 */       double d1 = Math.max(0.0D, 1.0D - d0);
/* 263 */       target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.4D * d1, 0.0D));
/* 264 */       doEnchantDamageEffects((LivingEntity)this, target);
/* 265 */       addWitherToTarget(target);
/*     */     } 
/* 267 */     playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
/* 268 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hurt(DamageSource source, float amount) {
/* 274 */     if (!sickenedCanBeHurt(source, amount))
/* 275 */       return false; 
/* 276 */     IronGolem.Crackiness crackiness = getCrackiness();
/* 277 */     boolean flag = super.hurt(source, amount);
/* 278 */     if (flag && getCrackiness() != crackiness)
/* 279 */       playSound(SoundEvents.IRON_GOLEM_DAMAGE); 
/* 280 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public IronGolem.Crackiness getCrackiness() {
/* 285 */     return IronGolem.Crackiness.byFraction(getHealth() / getMaxHealth());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEntityEvent(byte event) {
/* 291 */     if (event == 4) {
/*     */       
/* 293 */       this.attackAnimationTick = 10;
/* 294 */       playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 298 */       super.handleEntityEvent(event);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAttackAnimationTick() {
/* 304 */     return this.attackAnimationTick;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 310 */     return SoundEvents.IRON_GOLEM_HURT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 316 */     return SoundEvents.IRON_GOLEM_DEATH;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(BlockPos pos, BlockState state) {
/* 322 */     playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Vec3 getLeashOffset() {
/* 328 */     return new Vec3(0.0D, (0.875F * getEyeHeight()), (getBbWidth() * 0.4F));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedIronGolem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */