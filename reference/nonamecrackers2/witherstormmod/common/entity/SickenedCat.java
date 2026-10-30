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
/*     */ import net.minecraft.world.entity.animal.Cat;
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
/*  42 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedCat.class, EntityDataSerializers.f_135035_);
/*  43 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedCat(EntityType<? extends SickenedCat> type, Level level) {
/*  47 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/*  53 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/*  59 */     this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
/*  60 */     this.f_21345_.m_25352_(1, (Goal)new LeapAtTargetGoal((Mob)this, 0.3F));
/*  61 */     this.f_21345_.m_25352_(1, (Goal)new OcelotAttackGoal((Mob)this));
/*  62 */     this.f_21345_.m_25352_(3, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0D));
/*  63 */     this.f_21345_.m_25352_(4, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0F));
/*  64 */     this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  65 */     this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder m_28168_() {
/*  70 */     return Monster.m_33035_().m_22268_(Attributes.f_22279_, 0.32D).m_22268_(Attributes.f_22276_, 20.0D).m_22268_(Attributes.f_22281_, 4.0D).m_22268_(Attributes.f_22277_, 24.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_21825_() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7307_(Entity entity) {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_21828_(Player player) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/*  97 */     if (entity instanceof Player) { Player player = (Player)entity; if (m_21830_((LivingEntity)player) && !player.m_7500_() && !player.m_5833_())
/*  98 */         return true;  }
/*     */     
/* 100 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8107_() {
/* 106 */     super.m_8107_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/* 112 */     super.m_8119_();
/* 113 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
/* 119 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult m_6071_(Player player, InteractionHand hand) {
/* 125 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 126 */     if (result != null) {
/* 127 */       return result;
/*     */     }
/* 129 */     return InteractionResult.FAIL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(MobEffectInstance effect, Entity entity) {
/* 141 */     return (sickenedAddEffect(effect, entity) && super.m_147207_(effect, entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/* 147 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_6100_() {
/* 153 */     return sickenedGetVoicePitch(1.15F, 0.65F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/* 159 */     super.m_7380_(tag);
/* 160 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 166 */     super.m_7378_(tag);
/* 167 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 173 */     super.m_8097_();
/* 174 */     this.f_19804_.m_135372_(CONVERTING, Boolean.valueOf(false));
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
/* 186 */     return ((Boolean)this.f_19804_.m_135370_(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 192 */     this.f_19804_.m_135381_(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 198 */     return m_21519_(slot);
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
/*     */   public boolean m_6898_(ItemStack stack) {
/* 210 */     return false;
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
/*     */   public void convertFrom(Mob mob) {
/* 225 */     if (mob instanceof Cat) { Cat cat = (Cat)mob;
/*     */       
/* 227 */       m_28464_(cat.m_28554_());
/* 228 */       m_28131_(cat.m_28166_());
/* 229 */       if (cat.m_21824_()) {
/*     */         
/* 231 */         m_7105_(true);
/* 232 */         m_21816_(cat.m_21805_());
/* 233 */         m_21051_(Attributes.f_22276_).m_22125_(new AttributeModifier("Sickened tamed mob health benefit", 1.4D, AttributeModifier.Operation.MULTIPLY_BASE));
/* 234 */         m_21153_(m_21233_());
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doExtraHandling(Mob mob) {
/* 242 */     if (mob instanceof Cat) { Cat cat = (Cat)mob;
/*     */       
/* 244 */       cat.m_28464_(m_28554_());
/* 245 */       cat.m_28131_(m_28166_());
/* 246 */       if (m_21824_()) {
/*     */         
/* 248 */         cat.m_7105_(true);
/* 249 */         cat.m_21816_(m_21805_());
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 257 */     if (!sickenedCanBeHurt(source, amount))
/* 258 */       return false; 
/* 259 */     return super.m_6469_(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Cat m_142606_(ServerLevel level, AgeableMob mob) {
/* 265 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedCat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */