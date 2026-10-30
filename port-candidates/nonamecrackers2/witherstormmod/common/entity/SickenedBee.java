/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.RandomSource;
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
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.animal.bee.Bee;
/*     */ import net.minecraft.world.entity.monster.Enemy;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedBee extends Bee implements WitherSickened, Enemy {
/*  47 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(SickenedBee.class, EntityDataSerializers.BOOLEAN); static {
/*  48 */     TAINTABLE = (state -> 
/*  49 */       (state.is(WitherStormModBlockTags.SICKENED_BEE_CAN_CONVERT) && !state.is(WitherStormModBlockTags.TAINTED_BLOCKS) && !state.is(Blocks.WITHER_ROSE)));
/*     */   }
/*  51 */   public static final Predicate<BlockState> TAINTABLE; private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */   
/*     */   private int underWaterTicks;
/*     */   
/*     */   public SickenedBee(EntityType<? extends SickenedBee> type, Level level) {
/*  56 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType getMobType() {
/*  62 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  68 */     super.registerGoals();
/*  69 */     this.goalSelector.removeAllGoals(g -> {
/*     */           String className = g.getClass().getName();
/*  71 */           return (className.equals("net.minecraft.world.entity.animal.Bee$BeeGrowCropGoal") || className.equals("net.minecraft.world.entity.animal.Bee$BeeLocateHiveGoal") || g instanceof Bee.BeeGoToHiveGoal);
/*     */         });
/*  73 */     this.goalSelector.addGoal(7, new TaintGoal());
/*  74 */     this.targetSelector.removeAllGoals(g -> true);
/*  75 */     this.targetSelector.addGoal(1, (Goal)(new HurtByTargetGoal((PathfinderMob)this, new Class[0])).setAlertOthers(new Class[0]));
/*  76 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  77 */     this.targetSelector.addGoal(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void customServerAiStep() {
/*  82 */     if (isInWaterOrBubble()) {
/*  83 */       this.underWaterTicks++;
/*     */     } else {
/*  85 */       this.underWaterTicks = 0;
/*  86 */     }  if (this.underWaterTicks > 200) {
/*  87 */       hurt(damageSources().drown(), 1.0F);
/*     */     }
/*  89 */     if (!(level()).isClientSide) {
/*  90 */       updatePersistentAnger((ServerLevel)level(), false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  95 */     return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D).add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.FLYING_SPEED, 1.2D).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasHive() {
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNectar() {
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aiStep() {
/* 113 */     super.aiStep();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 119 */     super.tick();
/* 120 */     sickenedTick();
/* 121 */     if (this.tickCount % 4 == 0) {
/*     */       
/* 123 */       double d0 = getX() + this.random.nextFloat();
/* 124 */       double d1 = getY() + this.random.nextFloat();
/* 125 */       double d2 = getZ() + this.random.nextFloat();
/* 126 */       level().addParticle((ParticleOptions)WitherStormModParticleTypes.PHLEGM.get(), d0 - 0.5D, d1, d2 - 0.5D, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T convertTo(EntityType<T> type, boolean loot) {
/* 133 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean killedEntity(ServerLevel level, LivingEntity entity) {
/* 139 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult mobInteract(Player player, InteractionHand hand) {
/* 145 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 146 */     if (result != null) {
/* 147 */       return result;
/*     */     }
/* 149 */     return super.mobInteract(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffectInstance effect, Entity entity) {
/* 155 */     return (sickenedAddEffect(effect, entity) && super.addEffect(effect, entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVoicePitch() {
/* 167 */     return sickenedGetVoicePitch(1.15F, 0.65F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag tag) {
/* 173 */     super.addAdditionalSaveData(tag);
/* 174 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag tag) {
/* 180 */     super.readAdditionalSaveData(tag);
/* 181 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 187 */     super.defineSynchedData();
/* 188 */     this.entityData.define(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 194 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 200 */     return ((Boolean)this.entityData.get(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 206 */     this.entityData.set(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 212 */     return getEquipmentDropChance(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackType(EntityType<?> type) {
/* 218 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.canAttackType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Bee getBreedOffspring(ServerLevel level, AgeableMob mob) {
/* 224 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFood(ItemStack stack) {
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttack(LivingEntity entity) {
/* 236 */     return (super.canAttack(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doHurtTarget(Entity target) {
/* 242 */     boolean flag = target.hurt(damageSources().sting((LivingEntity)this), (int)getAttributeValue(Attributes.ATTACK_DAMAGE));
/* 243 */     if (flag) {
/*     */       
/* 245 */       addPotentWitherToTarget(target);
/* 246 */       playSound(SoundEvents.BEE_STING, 1.0F, 0.8F);
/*     */     } 
/* 248 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hurt(DamageSource source, float amount) {
/* 254 */     if (!sickenedCanBeHurt(source, amount))
/* 255 */       return false; 
/* 256 */     return super.hurt(source, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   class TaintGoal
/*     */     extends Goal
/*     */   {
/*     */     private static final int MAX_USE_TICKS = 120;
/*     */     private int useTicks;
/*     */     
/*     */     public boolean canUse() {
/* 267 */       if (SickenedBee.this.isAngry())
/* 268 */         return false; 
/* 269 */       if (SickenedBee.this.random.nextFloat() < 0.3F) {
/* 270 */         return false;
/*     */       }
/* 272 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean canContinueToUse() {
/* 278 */       return (!SickenedBee.this.isAngry() && this.useTicks > 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void start() {
/* 284 */       this.useTicks = 120;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void tick() {
/* 290 */       this.useTicks--;
/*     */       
/* 292 */       if (SickenedBee.this.random.nextInt(adjustedTickDelay(30)) == 0) {
/*     */         
/* 294 */         BlockPos pos = SickenedBee.this.blockPosition();
/* 295 */         BlockPos saved = SickenedBee.this.getSavedFlowerPos();
/* 296 */         if (SickenedBee.TAINTABLE.test(SickenedBee.this.level().getBlockState(pos))) {
/* 297 */           WorldTainting.getInstance().convertBlock(pos, SickenedBee.this.level());
/* 298 */         } else if (saved != null && pos.distManhattan((Vec3i)saved) <= 1) {
/* 299 */           WorldTainting.getInstance().convertBlock(saved, SickenedBee.this.level());
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedBee.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */