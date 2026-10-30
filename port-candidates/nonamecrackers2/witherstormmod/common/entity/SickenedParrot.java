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
/*     */ import net.minecraft.world.entity.ai.goal.FollowMobGoal;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.animal.parrot.Parrot;
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
/*     */ public class SickenedParrot extends Parrot implements WitherSickened, Enemy {
/*  40 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedParrot.class, EntityDataSerializers.BOOLEAN);
/*  41 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedParrot(EntityType<? extends SickenedParrot> type, Level level) {
/*  45 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  51 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  57 */     this.goalSelector.addGoal(0, (Goal)new FloatGoal((Mob)this));
/*  58 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.1D, false));
/*  59 */     this.goalSelector.addGoal(2, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0F));
/*  60 */     this.goalSelector.addGoal(3, (Goal)new FollowMobGoal((Mob)this, 1.0D, 3.0F, 7.0F));
/*  61 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  62 */     this.targetSelector.addGoal(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  67 */     return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.4D).add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.FLYING_SPEED, 0.8999999761581421D).add(Attributes.FOLLOW_RANGE, 24.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInSittingPose() {
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAlliedTo(Entity entity) {
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tame(Player player) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttack(LivingEntity entity) {
/*  94 */     if (entity instanceof Player) { Player player = (Player)entity; if (isOwnedBy((LivingEntity)player) && !player.isCreative() && !player.isSpectator())
/*  95 */         return true;  }
/*     */     
/*  97 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aiStep() {
/* 103 */     super.aiStep();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 109 */     super.tick();
/* 110 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/* 116 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult mobInteract(Player player, InteractionHand hand) {
/* 122 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 123 */     if (result != null) {
/* 124 */       return result;
/*     */     }
/* 126 */     return InteractionResult.FAIL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffectInstance effect, Entity entity) {
/* 138 */     return sickenedAddEffect(effect, entity) ? super.addEffect(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 144 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 150 */     return sickenedGetVoicePitch(1.15F, 0.65F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 156 */     super.addAdditionalSaveData(tag);
/* 157 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 163 */     super.readAdditionalSaveData(tag);
/* 164 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 170 */     super.defineSynchedData();
/* 171 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 177 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 183 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 189 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 195 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 201 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFood(ItemStack stack) {
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doHurtTarget(Entity target) {
/* 213 */     boolean flag = super.doHurtTarget(target);
/* 214 */     if (flag)
/* 215 */       addWitherToTarget(target); 
/* 216 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void convertFrom(Mob mob) {
/* 222 */     if (mob instanceof Parrot) { Parrot parrot = (Parrot)mob;
/*     */       
/* 224 */       setVariant(parrot.getVariant());
/* 225 */       if (parrot.isTame()) {
/*     */         
/* 227 */         setTame(true);
/* 228 */         setOwnerUUID(parrot.getOwnerUUID());
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doExtraHandling(Mob mob) {
/* 236 */     if (mob instanceof Parrot) { Parrot parrot = (Parrot)mob;
/*     */       
/* 238 */       parrot.setVariant(getVariant());
/* 239 */       if (isTame()) {
/*     */         
/* 241 */         parrot.setTame(true);
/* 242 */         parrot.setOwnerUUID(getOwnerUUID());
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hurt(DamageSource source, float amount) {
/* 250 */     if (!sickenedCanBeHurt(source, amount))
/* 251 */       return false; 
/* 252 */     return super.hurt(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
/* 258 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedParrot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */