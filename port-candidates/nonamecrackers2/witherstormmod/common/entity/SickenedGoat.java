/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.AgeableMob;
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
/*     */ import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
/*     */ import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.animal.goat.Goat;
/*     */ import net.minecraft.world.entity.monster.Enemy;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedGoat extends Goat implements WitherSickened, Enemy {
/*  41 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedGoat.class, EntityDataSerializers.BOOLEAN);
/*  42 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedGoat(EntityType<? extends SickenedGoat> type, Level level) {
/*  46 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  52 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  59 */     this.goalSelector.addGoal(0, (Goal)new FloatGoal((Mob)this));
/*  60 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.125D, false));
/*  61 */     this.goalSelector.addGoal(2, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0F));
/*  62 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0D));
/*  63 */     this.goalSelector.addGoal(4, (Goal)new RandomLookAroundGoal((Mob)this));
/*  64 */     this.targetSelector.addGoal(1, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  65 */     this.targetSelector.addGoal(2, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  70 */     return Monster.createMonsterAttributes()
/*  71 */       .add(Attributes.MOVEMENT_SPEED, 0.2D)
/*  72 */       .add(Attributes.MAX_HEALTH, 20.0D)
/*  73 */       .add(Attributes.ATTACK_DAMAGE, 2.0D)
/*  74 */       .add(Attributes.FOLLOW_RANGE, 24.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aiStep() {
/*  80 */     super.aiStep();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  86 */     super.tick();
/*  87 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/*  94 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult mobInteract(Player player, InteractionHand hand) {
/* 100 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 101 */     if (result != null) {
/* 102 */       return result;
/*     */     }
/* 104 */     return super.mobInteract(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffectInstance effect, Entity entity) {
/* 117 */     return sickenedAddEffect(effect, entity) ? super.addEffect(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 123 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 129 */     return sickenedGetVoicePitch(1.15F, 0.65F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 135 */     super.addAdditionalSaveData(tag);
/* 136 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 142 */     super.readAdditionalSaveData(tag);
/* 143 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 149 */     super.defineSynchedData();
/* 150 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 156 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 162 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 168 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 174 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 180 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFood(ItemStack stack) {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttack(LivingEntity entity) {
/* 192 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doHurtTarget(Entity target) {
/* 198 */     boolean flag = super.doHurtTarget(target);
/* 199 */     if (flag)
/* 200 */       addWitherToTarget(target); 
/* 201 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hurt(DamageSource source, float amount) {
/* 207 */     if (!sickenedCanBeHurt(source, amount))
/* 208 */       return false; 
/* 209 */     return super.hurt(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Goat getBreedOffspring(ServerLevel level, AgeableMob mob) {
/* 215 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedGoat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */