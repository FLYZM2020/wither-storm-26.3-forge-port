/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.BlockParticleOption;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.Mth;
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
/*     */ import net.minecraft.world.entity.PathfinderMob;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
/*     */ import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.animal.AbstractGolem;
/*     */ import net.minecraft.world.entity.animal.IronGolem;
/*     */ import net.minecraft.world.entity.monster.Enemy;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedIronGolem extends AbstractGolem implements WitherSickened, Enemy {
/*  51 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedIronGolem.class, EntityDataSerializers.f_135035_);
/*  52 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */   
/*     */   private int attackAnimationTick;
/*     */   
/*     */   public SickenedIronGolem(EntityType<? extends SickenedIronGolem> type, Level level) {
/*  57 */     super(type, level);
/*  58 */     m_274367_(1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/*  64 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder createAttributes() {
/*  69 */     return Monster.m_33035_().m_22268_(Attributes.f_22276_, 60.0D).m_22268_(Attributes.f_22277_, 48.0D).m_22268_(Attributes.f_22279_, 0.25D).m_22268_(Attributes.f_22281_, 10.0D).m_22268_(Attributes.f_22278_, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/*  75 */     this.f_21345_.m_25352_(0, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.1D, true));
/*  76 */     this.f_21345_.m_25352_(1, (Goal)new MoveTowardsTargetGoal((PathfinderMob)this, 1.0D, 32.0F));
/*  77 */     this.f_21345_.m_25352_(2, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0D));
/*  78 */     this.f_21345_.m_25352_(3, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0F));
/*  79 */     this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
/*  80 */     this.f_21346_.m_25352_(0, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
/*  81 */     this.f_21346_.m_25352_(1, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  82 */     this.f_21346_.m_25352_(2, (Goal)new SickenedMobsAttackGoal((Mob)this));
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
/* 102 */     if (result != null)
/*     */     {
/* 104 */       return result;
/*     */     }
/*     */ 
/*     */     
/* 108 */     ItemStack itemstack = player.m_21120_(hand);
/* 109 */     if (!itemstack.m_150930_(Items.f_42416_))
/*     */     {
/* 111 */       return InteractionResult.PASS;
/*     */     }
/*     */ 
/*     */     
/* 115 */     float f = m_21223_();
/* 116 */     m_5634_(m_21233_() / 4.0F);
/* 117 */     if (m_21223_() == f)
/*     */     {
/* 119 */       return InteractionResult.PASS;
/*     */     }
/*     */ 
/*     */     
/* 123 */     float f1 = 1.0F + (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2F;
/* 124 */     m_5496_(SoundEvents.f_12009_, 1.0F, f1);
/* 125 */     if (!(player.m_150110_()).f_35937_)
/* 126 */       itemstack.m_41774_(1); 
/* 127 */     return InteractionResult.m_19078_((m_9236_()).f_46443_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/* 136 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(MobEffectInstance effect, Entity entity) {
/* 142 */     return sickenedAddEffect(effect, entity) ? super.m_147207_(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/* 148 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInfectedHealAmount() {
/* 154 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_6100_() {
/* 160 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/* 166 */     super.m_7380_(tag);
/* 167 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 173 */     super.m_7378_(tag);
/* 174 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 180 */     super.m_8097_();
/* 181 */     this.f_19804_.m_135372_(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 187 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 193 */     return ((Boolean)this.f_19804_.m_135370_(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 199 */     this.f_19804_.m_135381_(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 205 */     return m_21519_(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6549_(EntityType<?> type) {
/* 211 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/* 217 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_7302_(int supply) {
/* 223 */     return supply;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8107_() {
/* 229 */     super.m_8107_();
/*     */     
/* 231 */     if (this.attackAnimationTick > 0) {
/* 232 */       this.attackAnimationTick--;
/*     */     }
/* 234 */     if (m_20184_().m_165925_() > 2.500000277905201E-7D && this.f_19796_.m_188503_(5) == 0) {
/*     */       
/* 236 */       int i = Mth.m_14107_(m_20185_());
/* 237 */       int j = Mth.m_14107_(m_20186_() - 0.2D);
/* 238 */       int k = Mth.m_14107_(m_20189_());
/* 239 */       BlockPos pos = new BlockPos(i, j, k);
/* 240 */       BlockState blockstate = m_9236_().m_8055_(pos);
/* 241 */       if (!blockstate.m_60795_()) {
/* 242 */         m_9236_().m_7106_((ParticleOptions)(new BlockParticleOption(ParticleTypes.f_123794_, blockstate)).setPos(pos), m_20185_() + (this.f_19796_.m_188501_() - 0.5D) * m_20205_(), m_20186_() + 0.1D, m_20189_() + (this.f_19796_.m_188501_() - 0.5D) * m_20205_(), 4.0D * (this.f_19796_.m_188501_() - 0.5D), 0.5D, (this.f_19796_.m_188501_() - 0.5D) * 4.0D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_7327_(Entity target) {
/* 249 */     this.attackAnimationTick = 10;
/* 250 */     m_9236_().m_7605_((Entity)this, (byte)4);
/* 251 */     float damage = (float)m_21133_(Attributes.f_22281_);
/* 252 */     float damageModified = ((int)damage > 0) ? (damage / 2.0F + this.f_19796_.m_188503_((int)damage)) : damage;
/* 253 */     boolean flag = target.m_6469_(m_269291_().m_269333_((LivingEntity)this), damageModified);
/* 254 */     if (flag) {
/*     */       double d2;
/*     */       
/* 257 */       if (target instanceof LivingEntity) { LivingEntity living = (LivingEntity)target;
/* 258 */         d2 = living.m_21133_(Attributes.f_22278_); }
/*     */       else
/* 260 */       { d2 = 0.0D; }
/* 261 */        double d0 = d2;
/* 262 */       double d1 = Math.max(0.0D, 1.0D - d0);
/* 263 */       target.m_20256_(target.m_20184_().m_82520_(0.0D, 0.4D * d1, 0.0D));
/* 264 */       m_19970_((LivingEntity)this, target);
/* 265 */       addWitherToTarget(target);
/*     */     } 
/* 267 */     m_5496_(SoundEvents.f_12057_, 1.0F, 1.0F);
/* 268 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 274 */     if (!sickenedCanBeHurt(source, amount))
/* 275 */       return false; 
/* 276 */     IronGolem.Crackiness crackiness = getCrackiness();
/* 277 */     boolean flag = super.m_6469_(source, amount);
/* 278 */     if (flag && getCrackiness() != crackiness)
/* 279 */       m_216990_(SoundEvents.f_12058_); 
/* 280 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public IronGolem.Crackiness getCrackiness() {
/* 285 */     return IronGolem.Crackiness.m_28901_(m_21223_() / m_21233_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7822_(byte event) {
/* 291 */     if (event == 4) {
/*     */       
/* 293 */       this.attackAnimationTick = 10;
/* 294 */       m_5496_(SoundEvents.f_12057_, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 298 */       super.m_7822_(event);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAttackAnimationTick() {
/* 304 */     return this.attackAnimationTick;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_7975_(DamageSource source) {
/* 310 */     return SoundEvents.f_12008_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_5592_() {
/* 316 */     return SoundEvents.f_12059_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7355_(BlockPos pos, BlockState state) {
/* 322 */     m_5496_(SoundEvents.f_12010_, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Vec3 m_7939_() {
/* 328 */     return new Vec3(0.0D, (0.875F * m_20192_()), (m_20205_() * 0.4F));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedIronGolem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */