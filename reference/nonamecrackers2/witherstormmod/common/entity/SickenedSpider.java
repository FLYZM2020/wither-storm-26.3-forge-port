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
/*     */ import net.minecraft.world.entity.monster.Spider;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedSpider extends Spider implements WitherSickened {
/*  47 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedSpider.class, EntityDataSerializers.f_135035_);
/*  48 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedSpider(EntityType<? extends SickenedSpider> type, Level world) {
/*  52 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/*  58 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder m_33815_() {
/*  63 */     return Monster.m_33035_().m_22268_(Attributes.f_22276_, 20.0D).m_22268_(Attributes.f_22279_, 0.3400000035762787D).m_22268_(Attributes.f_22281_, 3.0D).m_22268_(Attributes.f_22277_, 32.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/*  69 */     this.f_21345_.m_25352_(1, (Goal)new FloatGoal((Mob)this));
/*  70 */     this.f_21345_.m_25352_(3, (Goal)new LeapAtTargetGoal((Mob)this, 0.45F));
/*  71 */     this.f_21345_.m_25352_(4, (Goal)new AttackGoal(this));
/*  72 */     this.f_21345_.m_25352_(5, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 0.8D));
/*  73 */     this.f_21345_.m_25352_(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0F));
/*  74 */     this.f_21345_.m_25352_(6, (Goal)new RandomLookAroundGoal((Mob)this));
/*  75 */     this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
/*  76 */     this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  77 */     this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  83 */     super.m_8119_();
/*  84 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
/*  90 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult m_6071_(Player player, InteractionHand hand) {
/*  96 */     InteractionResult result = sickenedMobInteract(player, hand);
/*  97 */     if (result != null) {
/*  98 */       return result;
/*     */     }
/* 100 */     return super.m_6071_(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/* 106 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(MobEffectInstance effect, Entity entity) {
/* 112 */     return sickenedAddEffect(effect, entity) ? super.m_147207_(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/* 118 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_6100_() {
/* 124 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/* 130 */     super.m_7380_(tag);
/* 131 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 137 */     super.m_7378_(tag);
/* 138 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 144 */     super.m_8097_();
/* 145 */     this.f_19804_.m_135372_(CONVERTING, Boolean.valueOf(false));
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
/* 157 */     return ((Boolean)this.f_19804_.m_135370_(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 163 */     this.f_19804_.m_135381_(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 169 */     return m_21519_(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6549_(EntityType<?> type) {
/* 175 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/* 181 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7327_(Entity target) {
/* 187 */     boolean flag = super.m_7327_(target);
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
/*     */     public boolean m_8036_() {
/* 203 */       return (super.m_8036_() && !this.f_25540_.m_20160_());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected double m_6639_(LivingEntity target) {
/* 209 */       return (4.0F + target.m_20205_());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SpawnGroupData m_6518_(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag) {
/*     */     Spider.SpiderEffectsGroupData spiderEffectsGroupData;
/* 217 */     RandomSource random = level.m_213780_();
/* 218 */     if (random.m_188503_(100) == 0) {
/*     */       
/* 220 */       SickenedSkeleton sickenedSkeleton = (SickenedSkeleton)((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get()).m_20615_(m_9236_());
/* 221 */       if (sickenedSkeleton != null) {
/*     */         
/* 223 */         sickenedSkeleton.m_7678_(m_20185_(), m_20186_(), m_20189_(), m_146908_(), 0.0F);
/* 224 */         sickenedSkeleton.m_6518_(level, difficulty, MobSpawnType.JOCKEY, (SpawnGroupData)null, (CompoundTag)null);
/* 225 */         sickenedSkeleton.m_20329_((Entity)this);
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     if (groupData == null) {
/*     */       
/* 231 */       spiderEffectsGroupData = new Spider.SpiderEffectsGroupData();
/* 232 */       if (level.m_46791_() == Difficulty.HARD && random.m_188501_() < 0.1F * difficulty.m_19057_()) {
/* 233 */         spiderEffectsGroupData.m_219118_(random);
/*     */       }
/*     */     } 
/* 236 */     if (spiderEffectsGroupData instanceof Spider.SpiderEffectsGroupData) { Spider.SpiderEffectsGroupData sickenedSpider$spidereffectsgroupdata = spiderEffectsGroupData;
/*     */       
/* 238 */       MobEffect mobEffect = sickenedSpider$spidereffectsgroupdata.f_33827_;
/* 239 */       if (mobEffect != null) {
/* 240 */         m_7292_(new MobEffectInstance(mobEffect, -1));
/*     */       } }
/*     */     
/* 243 */     return (SpawnGroupData)spiderEffectsGroupData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 249 */     if (!sickenedCanBeHurt(source, amount))
/* 250 */       return false; 
/* 251 */     return super.m_6469_(source, amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedSpider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */