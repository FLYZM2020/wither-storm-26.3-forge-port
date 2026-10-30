/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
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
/*     */ import net.minecraft.world.entity.ai.goal.FloatGoal;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
/*     */ import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.animal.SnowGolem;
/*     */ import net.minecraft.world.entity.monster.Enemy;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.monster.RangedAttackMob;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.Snowball;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.gameevent.GameEvent;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import nonamecrackers2.witherstormmod.common.util.SnowballAccessor;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedSnowGolem extends SnowGolem implements WitherSickened, Enemy {
/*  53 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedSnowGolem.class, EntityDataSerializers.f_135035_);
/*  54 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedSnowGolem(EntityType<? extends SickenedSnowGolem> type, Level world) {
/*  58 */     super(type, world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/*  64 */     this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
/*     */     
/*  66 */     this.f_21345_.m_25352_(1, (Goal)new RangedAttackGoal((RangedAttackMob)this, 1.25D, 12, 10.0F));
/*  67 */     this.f_21345_.m_25352_(2, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0D, 1.0000001E-5F));
/*  68 */     this.f_21345_.m_25352_(3, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0F));
/*  69 */     this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
/*  70 */     this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  71 */     this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/*  77 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder m_29934_() {
/*  82 */     return Monster.m_33035_().m_22268_(Attributes.f_22276_, 8.0D).m_22268_(Attributes.f_22279_, 0.24D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_7302_(int supply) {
/*  88 */     return supply;
/*     */   }
/*     */   
/*     */   public boolean m_6126_() {
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  97 */     super.m_8119_();
/*  98 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_5851_(SoundSource source) {
/* 104 */     m_9236_().m_6269_((Player)null, (Entity)this, SoundEvents.f_12480_, source, 1.0F, 1.0F);
/* 105 */     if (!m_9236_().m_5776_()) {
/*     */       
/* 107 */       m_29936_(false);
/* 108 */       m_5552_(new ItemStack((ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get()), 1.7F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6504_(LivingEntity entity, float p_29913_) {
/* 115 */     Snowball snowball = new Snowball(m_9236_(), (LivingEntity)this);
/* 116 */     if (this.f_19796_.m_188501_() < 0.1D)
/* 117 */       ((SnowballAccessor)snowball).setWitherEffect(true); 
/* 118 */     double d0 = entity.m_20188_() - 1.100000023841858D;
/* 119 */     double d1 = entity.m_20185_() - m_20185_();
/* 120 */     double d2 = d0 - snowball.m_20186_();
/* 121 */     double d3 = entity.m_20189_() - m_20189_();
/* 122 */     double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224D;
/* 123 */     snowball.m_6686_(d1, d2 + d4, d3, 1.6F, 12.0F);
/* 124 */     m_5496_(SoundEvents.f_12479_, 1.0F, 0.4F / (m_217043_().m_188501_() * 0.4F + 0.8F));
/* 125 */     m_9236_().m_7967_((Entity)snowball);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
/* 131 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult m_6071_(Player player, InteractionHand hand) {
/* 137 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 138 */     if (result != null) {
/* 139 */       return result;
/*     */     }
/* 141 */     return super.m_6071_(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
/* 147 */     world.m_6269_(null, (Entity)this, SoundEvents.f_12480_, (player == null) ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
/* 148 */     m_146852_(GameEvent.f_157781_, (Entity)player);
/* 149 */     if (!world.m_5776_()) {
/*     */       
/* 151 */       m_29936_(false);
/* 152 */       return Collections.singletonList(new ItemStack((ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get()));
/*     */     } 
/* 154 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(MobEffectInstance effect, Entity entity) {
/* 166 */     return sickenedAddEffect(effect, entity) ? super.m_147207_(effect, entity) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/* 172 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_6100_() {
/* 178 */     return sickenedGetVoicePitch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/* 184 */     super.m_7380_(tag);
/* 185 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 191 */     super.m_7378_(tag);
/* 192 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 198 */     super.m_8097_();
/* 199 */     this.f_19804_.m_135372_(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 205 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 211 */     return ((Boolean)this.f_19804_.m_135370_(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 217 */     this.f_19804_.m_135381_(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 223 */     return m_21519_(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_7515_() {
/* 229 */     return SoundEvents.f_12476_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_7975_(DamageSource source) {
/* 235 */     return SoundEvents.f_12478_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent m_5592_() {
/* 241 */     return SoundEvents.f_12477_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6549_(EntityType<?> type) {
/* 247 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/* 253 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7327_(Entity target) {
/* 259 */     boolean flag = super.m_7327_(target);
/* 260 */     if (flag)
/* 261 */       addWitherToTarget(target); 
/* 262 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 268 */     if (!sickenedCanBeHurt(source, amount))
/* 269 */       return false; 
/* 270 */     return super.m_6469_(source, amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedSnowGolem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */