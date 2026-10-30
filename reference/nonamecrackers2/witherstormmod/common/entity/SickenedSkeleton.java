/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobSpawnType;
/*     */ import net.minecraft.world.entity.MobType;
/*     */ import net.minecraft.world.entity.SpawnGroupData;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.monster.AbstractSkeleton;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.AbstractArrow;
/*     */ import net.minecraft.world.entity.projectile.Arrow;
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
/*     */ public class SickenedSkeleton extends AbstractSkeleton implements WitherSickened {
/*  45 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedSkeleton.class, EntityDataSerializers.f_135035_);
/*  46 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedSkeleton(EntityType<? extends SickenedSkeleton> type, Level world) {
/*  50 */     super(type, world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/*  56 */     super.m_8099_();
/*  57 */     this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  58 */     this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/*  64 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder m_32166_() {
/*  69 */     return Monster.m_33035_().m_22268_(Attributes.f_22276_, 24.0D).m_22268_(Attributes.f_22279_, 0.28D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_7302_(int supply) {
/*  75 */     return supply;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_21527_() {
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  88 */     super.m_8119_();
/*  89 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
/*  95 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult m_6071_(Player player, InteractionHand hand) {
/* 101 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 102 */     if (result != null) {
/* 103 */       return result;
/*     */     }
/* 105 */     return super.m_6071_(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/* 111 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(MobEffectInstance effect, Entity entity) {
/* 117 */     return sickenedAddEffect(effect, entity) ? super.m_147207_(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/* 123 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_6100_() {
/* 129 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/* 135 */     super.m_7380_(tag);
/* 136 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 142 */     super.m_7378_(tag);
/* 143 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 149 */     super.m_8097_();
/* 150 */     this.f_19804_.m_135372_(CONVERTING, Boolean.valueOf(false));
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
/* 162 */     return ((Boolean)this.f_19804_.m_135370_(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 168 */     this.f_19804_.m_135381_(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 174 */     return m_21519_(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_7515_() {
/* 180 */     return SoundEvents.f_12423_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_7975_(DamageSource source) {
/* 186 */     return SoundEvents.f_12381_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_5592_() {
/* 192 */     return SoundEvents.f_12424_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_7878_() {
/* 198 */     return SoundEvents.f_12383_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6549_(EntityType<?> type) {
/* 204 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/* 210 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7327_(Entity target) {
/* 216 */     boolean flag = super.m_7327_(target);
/* 217 */     if (flag)
/* 218 */       addWitherToTarget(target); 
/* 219 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractArrow m_7932_(ItemStack stack, float damage) {
/* 225 */     AbstractArrow abstractArrow = super.m_7932_(stack, damage);
/* 226 */     if (abstractArrow instanceof Arrow) { Arrow arrow = (Arrow)abstractArrow; if (this.f_19796_.m_188501_() < 0.25D)
/* 227 */         arrow.m_36870_(new MobEffectInstance(MobEffects.f_19615_, 40, 1));  }
/* 228 */      return abstractArrow;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SpawnGroupData m_6518_(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag) {
/* 234 */     groupData = super.m_6518_(level, difficulty, spawnType, groupData, tag);
/* 235 */     ItemStack head = m_6844_(EquipmentSlot.HEAD);
/* 236 */     if (head.m_150930_(Items.f_42055_)) {
/* 237 */       m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()));
/* 238 */     } else if (head.m_150930_(Items.f_42047_)) {
/* 239 */       m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()));
/* 240 */     }  return groupData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 246 */     if (!sickenedCanBeHurt(source, amount))
/* 247 */       return false; 
/* 248 */     return super.m_6469_(source, amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedSkeleton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */