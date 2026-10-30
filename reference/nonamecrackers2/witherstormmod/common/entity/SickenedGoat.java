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
/*  41 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedGoat.class, EntityDataSerializers.f_135035_);
/*  42 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedGoat(EntityType<? extends SickenedGoat> type, Level level) {
/*  46 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/*  52 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/*  59 */     this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
/*  60 */     this.f_21345_.m_25352_(1, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.125D, false));
/*  61 */     this.f_21345_.m_25352_(2, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0F));
/*  62 */     this.f_21345_.m_25352_(3, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0D));
/*  63 */     this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
/*  64 */     this.f_21346_.m_25352_(1, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  65 */     this.f_21346_.m_25352_(2, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder m_149401_() {
/*  70 */     return Monster.m_33035_()
/*  71 */       .m_22268_(Attributes.f_22279_, 0.2D)
/*  72 */       .m_22268_(Attributes.f_22276_, 20.0D)
/*  73 */       .m_22268_(Attributes.f_22281_, 2.0D)
/*  74 */       .m_22268_(Attributes.f_22277_, 24.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8107_() {
/*  80 */     super.m_8107_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  86 */     super.m_8119_();
/*  87 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
/*  94 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult m_6071_(Player player, InteractionHand hand) {
/* 100 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 101 */     if (result != null) {
/* 102 */       return result;
/*     */     }
/* 104 */     return super.m_6071_(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/* 111 */     return false;
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
/* 129 */     return sickenedGetVoicePitch(1.15F, 0.65F);
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
/*     */   public boolean m_6549_(EntityType<?> type) {
/* 180 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6898_(ItemStack stack) {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/* 192 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7327_(Entity target) {
/* 198 */     boolean flag = super.m_7327_(target);
/* 199 */     if (flag)
/* 200 */       addWitherToTarget(target); 
/* 201 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 207 */     if (!sickenedCanBeHurt(source, amount))
/* 208 */       return false; 
/* 209 */     return super.m_6469_(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Goat m_142606_(ServerLevel level, AgeableMob mob) {
/* 215 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedGoat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */