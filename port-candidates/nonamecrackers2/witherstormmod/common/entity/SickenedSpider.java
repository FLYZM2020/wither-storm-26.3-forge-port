/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
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
/*     */ import net.minecraft.world.entity.ai.goal.FloatGoal;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
/*     */ import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.monster.spider.Spider;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedSpider extends Spider implements WitherSickened {
/*  47 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedSpider.class, EntityDataSerializers.BOOLEAN);
/*  48 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedSpider(EntityType<? extends SickenedSpider> type, Level world) {
/*  52 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  58 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  63 */     return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.3400000035762787D).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.FOLLOW_RANGE, 32.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  69 */     this.goalSelector.addGoal(1, (Goal)new FloatGoal((Mob)this));
/*  70 */     this.goalSelector.addGoal(3, (Goal)new LeapAtTargetGoal((Mob)this, 0.45F));
/*  71 */     this.goalSelector.addGoal(4, (Goal)new AttackGoal(this));
/*  72 */     this.goalSelector.addGoal(5, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 0.8D));
/*  73 */     this.goalSelector.addGoal(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0F));
/*  74 */     this.goalSelector.addGoal(6, (Goal)new RandomLookAroundGoal((Mob)this));
/*  75 */     this.targetSelector.addGoal(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
/*  76 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  77 */     this.targetSelector.addGoal(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  83 */     super.tick();
/*  84 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/*  90 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult mobInteract(Player player, InteractionHand hand) {
/*  96 */     InteractionResult result = sickenedMobInteract(player, hand);
/*  97 */     if (result != null) {
/*  98 */       return result;
/*     */     }
/* 100 */     return super.mobInteract(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 106 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffectInstance effect, Entity entity) {
/* 112 */     return sickenedAddEffect(effect, entity) ? super.addEffect(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 118 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 124 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 130 */     super.addAdditionalSaveData(tag);
/* 131 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 137 */     super.readAdditionalSaveData(tag);
/* 138 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 144 */     super.defineSynchedData();
/* 145 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 151 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 157 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 163 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 169 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 175 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttack(LivingEntity entity) {
/* 181 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doHurtTarget(Entity target) {
/* 187 */     boolean flag = super.doHurtTarget(target);
/* 188 */     if (flag)
/* 189 */       addWitherToTarget(target); 
/* 190 */     return flag;
/*     */   }
/*     */   
/*     */   private static class AttackGoal
/*     */     extends MeleeAttackGoal
/*     */   {
/*     */     public AttackGoal(SickenedSpider entity) {
/* 197 */       super((PathfinderMob)entity, 1.0D, true);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean canUse() {
/* 203 */       return (super.canUse() && !this.mob.isVehicle());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected double getAttackReachSqr(LivingEntity target) {
/* 209 */       return (4.0F + target.getBbWidth());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag) {
/*     */     Spider.SpiderEffectsGroupData spiderEffectsGroupData;
/* 217 */     RandomSource random = level.getRandom();
/* 218 */     if (random.nextInt(100) == 0) {
/*     */       
/* 220 */       SickenedSkeleton sickenedSkeleton = (SickenedSkeleton)((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get()).create(level());
/* 221 */       if (sickenedSkeleton != null) {
/*     */         
/* 223 */         sickenedSkeleton.moveTo(getX(), getY(), getZ(), getYRot(), 0.0F);
/* 224 */         sickenedSkeleton.finalizeSpawn(level, difficulty, MobSpawnType.JOCKEY, (SpawnGroupData)null, (CompoundTag)null);
/* 225 */         sickenedSkeleton.startRiding((Entity)this);
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     if (groupData == null) {
/*     */       
/* 231 */       spiderEffectsGroupData = new Spider.SpiderEffectsGroupData();
/* 232 */       if (level.getDifficulty() == Difficulty.HARD && random.nextFloat() < 0.1F * difficulty.getSpecialMultiplier()) {
/* 233 */         spiderEffectsGroupData.setRandomEffect(random);
/*     */       }
/*     */     } 
/* 236 */     if (spiderEffectsGroupData instanceof Spider.SpiderEffectsGroupData) { Spider.SpiderEffectsGroupData sickenedSpider$spidereffectsgroupdata = spiderEffectsGroupData;
/*     */       
/* 238 */       MobEffect mobEffect = sickenedSpider$spidereffectsgroupdata.effect;
/* 239 */       if (mobEffect != null) {
/* 240 */         addEffect(new MobEffectInstance(mobEffect, -1));
/*     */       } }
/*     */     
/* 243 */     return (SpawnGroupData)spiderEffectsGroupData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hurt(DamageSource source, float amount) {
/* 249 */     if (!sickenedCanBeHurt(source, amount))
/* 250 */       return false; 
/* 251 */     return super.hurt(source, amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedSpider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */