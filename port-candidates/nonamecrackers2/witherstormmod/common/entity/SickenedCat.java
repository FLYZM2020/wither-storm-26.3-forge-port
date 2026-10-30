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
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.FloatGoal;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.OcelotAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.animal.feline.Cat;
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
/*     */ public class SickenedCat extends Cat implements WitherSickened, Enemy {
/*  42 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedCat.class, EntityDataSerializers.BOOLEAN);
/*  43 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedCat(EntityType<? extends SickenedCat> type, Level level) {
/*  47 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  53 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  59 */     this.goalSelector.addGoal(0, (Goal)new FloatGoal((Mob)this));
/*  60 */     this.goalSelector.addGoal(1, (Goal)new LeapAtTargetGoal((Mob)this, 0.3F));
/*  61 */     this.goalSelector.addGoal(1, (Goal)new OcelotAttackGoal((Mob)this));
/*  62 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0D));
/*  63 */     this.goalSelector.addGoal(4, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0F));
/*  64 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  65 */     this.targetSelector.addGoal(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  70 */     return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.32D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.FOLLOW_RANGE, 24.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInSittingPose() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAlliedTo(Entity entity) {
/*  84 */     return false;
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
/*  97 */     if (entity instanceof Player) { Player player = (Player)entity; if (isOwnedBy((LivingEntity)player) && !player.isCreative() && !player.isSpectator())
/*  98 */         return true;  }
/*     */     
/* 100 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aiStep() {
/* 106 */     super.aiStep();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 112 */     super.tick();
/* 113 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/* 119 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult mobInteract(Player player, InteractionHand hand) {
/* 125 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 126 */     if (result != null) {
/* 127 */       return result;
/*     */     }
/* 129 */     return InteractionResult.FAIL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffectInstance effect, Entity entity) {
/* 141 */     return (sickenedAddEffect(effect, entity) && super.addEffect(effect, entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 147 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 153 */     return sickenedGetVoicePitch(1.15F, 0.65F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 159 */     super.addAdditionalSaveData(tag);
/* 160 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 166 */     super.readAdditionalSaveData(tag);
/* 167 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 173 */     super.defineSynchedData();
/* 174 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 180 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 186 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 192 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 198 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 204 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFood(ItemStack stack) {
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doHurtTarget(Entity target) {
/* 216 */     boolean flag = super.doHurtTarget(target);
/* 217 */     if (flag)
/* 218 */       addWitherToTarget(target); 
/* 219 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void convertFrom(Mob mob) {
/* 225 */     if (mob instanceof Cat) { Cat cat = (Cat)mob;
/*     */       
/* 227 */       setVariant(cat.getVariant());
/* 228 */       setCollarColor(cat.getCollarColor());
/* 229 */       if (cat.isTame()) {
/*     */         
/* 231 */         setTame(true);
/* 232 */         setOwnerUUID(cat.getOwnerUUID());
/* 233 */         getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Sickened tamed mob health benefit", 1.4D, AttributeModifier.Operation.MULTIPLY_BASE));
/* 234 */         setHealth(getMaxHealth());
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doExtraHandling(Mob mob) {
/* 242 */     if (mob instanceof Cat) { Cat cat = (Cat)mob;
/*     */       
/* 244 */       cat.setVariant(getVariant());
/* 245 */       cat.setCollarColor(getCollarColor());
/* 246 */       if (isTame()) {
/*     */         
/* 248 */         cat.setTame(true);
/* 249 */         cat.setOwnerUUID(getOwnerUUID());
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hurt(DamageSource source, float amount) {
/* 257 */     if (!sickenedCanBeHurt(source, amount))
/* 258 */       return false; 
/* 259 */     return super.hurt(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Cat getBreedOffspring(ServerLevel level, AgeableMob mob) {
/* 265 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedCat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */