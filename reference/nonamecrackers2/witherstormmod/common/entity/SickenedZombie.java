/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntitySelector;
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
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.monster.RangedAttackMob;
/*     */ import net.minecraft.world.entity.monster.Zombie;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.ThrownTrident;
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
/*     */ public class SickenedZombie extends Zombie implements WitherSickened, RangedAttackMob {
/*  50 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedZombie.class, EntityDataSerializers.f_135035_);
/*  51 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedZombie(EntityType<? extends SickenedZombie> type, Level world) {
/*  55 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/*  61 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder m_34328_() {
/*  66 */     return Monster.m_33035_().m_22268_(Attributes.f_22276_, 24.0D).m_22268_(Attributes.f_22277_, 48.0D).m_22268_(Attributes.f_22279_, 0.2800000011920929D).m_22268_(Attributes.f_22281_, 3.5D).m_22268_(Attributes.f_22284_, 2.2D).m_22266_(Attributes.f_22287_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_6878_() {
/*  72 */     super.m_6878_();
/*  73 */     this.f_21345_.m_25352_(2, (Goal)new SickenedZombieTridentAttackGoal(this, 1.0D, 40, 10.0F));
/*  74 */     this.f_21346_.m_25352_(1, (Goal)(new HurtByTargetGoal((PathfinderMob)this, new Class[0])).m_26044_(new Class[0]));
/*  75 */     this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  76 */     this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  82 */     super.m_8119_();
/*  83 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_7302_(int supply) {
/*  89 */     return supply;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_5884_() {
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
/* 102 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult m_6071_(Player player, InteractionHand hand) {
/* 108 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 109 */     if (result != null) {
/* 110 */       return result;
/*     */     }
/* 112 */     return super.m_6071_(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/* 118 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(@NotNull MobEffectInstance effect, Entity entity) {
/* 124 */     return (sickenedAddEffect(effect, entity) && super.m_147207_(effect, entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_6100_() {
/* 130 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/* 136 */     super.m_7380_(tag);
/* 137 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 143 */     super.m_7378_(tag);
/* 144 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 150 */     super.m_8097_();
/* 151 */     this.f_19804_.m_135372_(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack m_5728_() {
/* 157 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7595_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_34329_() {
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 174 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 180 */     return ((Boolean)this.f_19804_.m_135370_(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 186 */     this.f_19804_.m_135381_(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 192 */     return m_21519_(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6549_(EntityType<?> type) {
/* 198 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/* 204 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_213945_(RandomSource random, DifficultyInstance instance) {
/* 210 */     super.m_213945_(random, instance);
/* 211 */     if (this.f_19796_.m_188501_() < ((m_9236_().m_46791_() == Difficulty.HARD) ? 0.05F : 0.01F)) {
/*     */       
/* 213 */       int i = this.f_19796_.m_188503_(4);
/* 214 */       if (i == 0) {
/* 215 */         m_8061_(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.f_42383_));
/* 216 */       } else if (i < 4) {
/* 217 */         m_8061_(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.f_42384_));
/* 218 */       } else if (i == 4) {
/* 219 */         m_8061_(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.f_42430_));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_7327_(Entity target) {
/* 226 */     boolean flag = super.m_7327_(target);
/* 227 */     if (flag)
/* 228 */       addWitherToTarget(target); 
/* 229 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/* 235 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpawnGroupData m_6518_(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag) {
/* 242 */     ItemStack head = m_6844_(EquipmentSlot.HEAD);
/* 243 */     m_34336_(m_7586_());
/* 244 */     m_213945_(this.f_19796_, difficulty);
/* 245 */     m_213946_(this.f_19796_, difficulty);
/* 246 */     if (head.m_150930_(Items.f_42055_)) {
/*     */       
/* 248 */       m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()));
/*     */     }
/* 250 */     else if (head.m_150930_(Items.f_42047_)) {
/*     */       
/* 252 */       m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()));
/*     */     } 
/* 254 */     if (m_6162_()) {
/*     */       
/* 256 */       RandomSource random = level.m_213780_();
/* 257 */       if (random.m_188501_() < 0.05D) {
/*     */         
/* 259 */         List<SickenedChicken> list = level.m_6443_(SickenedChicken.class, m_20191_().m_82377_(5.0D, 3.0D, 5.0D), EntitySelector.f_20404_);
/* 260 */         if (!list.isEmpty())
/*     */         {
/* 262 */           SickenedChicken sickenedChicken = list.get(0);
/* 263 */           sickenedChicken.m_28273_(true);
/* 264 */           m_20329_((Entity)sickenedChicken);
/*     */         }
/*     */       
/* 267 */       } else if (random.m_188501_() < 0.05D) {
/*     */         
/* 269 */         SickenedChicken sickenedChicken = (SickenedChicken)((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get()).m_20615_(m_9236_());
/* 270 */         if (sickenedChicken != null) {
/*     */           
/* 272 */           sickenedChicken.m_7678_(m_20185_(), m_20186_(), m_20189_(), m_146908_(), 0.0F);
/* 273 */           sickenedChicken.m_6518_(level, difficulty, MobSpawnType.JOCKEY, null, null);
/* 274 */           sickenedChicken.m_28273_(true);
/* 275 */           m_20329_((Entity)sickenedChicken);
/* 276 */           level.m_7967_((Entity)sickenedChicken);
/*     */         } 
/*     */       } 
/*     */     } 
/* 280 */     return groupData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6504_(LivingEntity zombie, float p_32357_) {
/* 285 */     ThrownTrident throwntrident = new ThrownTrident(m_9236_(), (LivingEntity)this, new ItemStack((ItemLike)Items.f_42713_));
/* 286 */     double d0 = zombie.m_20185_() - m_20185_();
/* 287 */     double d1 = zombie.m_20227_(0.3333333333333333D) - throwntrident.m_20186_();
/* 288 */     double d2 = zombie.m_20189_() - m_20189_();
/* 289 */     double d3 = Math.sqrt(d0 * d0 + d2 * d2);
/* 290 */     throwntrident.m_6686_(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - m_9236_().m_46791_().m_19028_() * 4));
/* 291 */     m_5496_(SoundEvents.f_11821_, 1.0F, 1.0F / (m_217043_().m_188501_() * 0.4F + 0.8F));
/* 292 */     m_9236_().m_7967_((Entity)throwntrident);
/*     */   }
/*     */   
/*     */   static class SickenedZombieTridentAttackGoal
/*     */     extends RangedAttackGoal
/*     */   {
/*     */     private final SickenedZombie zombie;
/*     */     
/*     */     public SickenedZombieTridentAttackGoal(RangedAttackMob attackMob, double p_25769_, int p_25770_, float p_25771_) {
/* 301 */       super(attackMob, p_25769_, p_25770_, p_25771_);
/* 302 */       this.zombie = (SickenedZombie)attackMob;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean m_8036_() {
/* 307 */       return (super.m_8036_() && this.zombie.m_21205_().m_150930_(Items.f_42713_));
/*     */     }
/*     */ 
/*     */     
/*     */     public void m_8056_() {
/* 312 */       super.m_8056_();
/* 313 */       this.zombie.m_21561_(true);
/* 314 */       this.zombie.m_6672_(InteractionHand.MAIN_HAND);
/*     */     }
/*     */ 
/*     */     
/*     */     public void m_8041_() {
/* 319 */       super.m_8041_();
/* 320 */       this.zombie.m_5810_();
/* 321 */       this.zombie.m_21561_(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 327 */     if (!sickenedCanBeHurt(source, amount))
/* 328 */       return false; 
/* 329 */     return super.m_6469_(source, amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedZombie.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */