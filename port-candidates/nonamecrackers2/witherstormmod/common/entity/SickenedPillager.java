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
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobType;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.monster.illager.Pillager;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.Level;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedPillager
/*     */   extends Pillager implements WitherSickened {
/*  33 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedPillager.class, EntityDataSerializers.BOOLEAN);
/*  34 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedPillager(EntityType<? extends SickenedPillager> type, Level level) {
/*  38 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  44 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  49 */     return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.37D).add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.ATTACK_DAMAGE, 6.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  55 */     super.registerGoals();
/*  56 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  57 */     this.targetSelector.addGoal(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAlliedTo(Entity entity) {
/*  63 */     if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity; if (living.getMobType() == MobType.ILLAGER)
/*  64 */         return false;  }
/*  65 */      return super.isAlliedTo(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  71 */     super.tick();
/*  72 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/*  78 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult mobInteract(Player player, InteractionHand hand) {
/*  84 */     InteractionResult result = sickenedMobInteract(player, hand);
/*  85 */     if (result != null) {
/*  86 */       return result;
/*     */     }
/*  88 */     return super.mobInteract(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/*  94 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffectInstance effect, Entity entity) {
/* 100 */     return (sickenedAddEffect(effect, entity) && super.addEffect(effect, entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 106 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 112 */     return sickenedGetVoicePitch(1.15F, 0.65F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 118 */     super.addAdditionalSaveData(tag);
/* 119 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 125 */     super.readAdditionalSaveData(tag);
/* 126 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 132 */     super.defineSynchedData();
/* 133 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 139 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 145 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 151 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 157 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 163 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttack(LivingEntity entity) {
/* 169 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canJoinRaid() {
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanJoinRaid(boolean flag) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canJoinPatrol() {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPatrolLeader() {
/* 192 */     return false;
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
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedPillager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */