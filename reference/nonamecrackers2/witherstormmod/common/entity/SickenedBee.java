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
/*     */ import net.minecraft.world.entity.animal.Bee;
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
/*  47 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedBee.class, EntityDataSerializers.f_135035_); static {
/*  48 */     TAINTABLE = (state -> 
/*  49 */       (state.m_204336_(WitherStormModBlockTags.SICKENED_BEE_CAN_CONVERT) && !state.m_204336_(WitherStormModBlockTags.TAINTED_BLOCKS) && !state.m_60713_(Blocks.f_50070_)));
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
/*     */   public MobType m_6336_() {
/*  62 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/*  68 */     super.m_8099_();
/*  69 */     this.f_21345_.m_262460_(g -> {
/*     */           String className = g.getClass().getName();
/*  71 */           return (className.equals("net.minecraft.world.entity.animal.Bee$BeeGrowCropGoal") || className.equals("net.minecraft.world.entity.animal.Bee$BeeLocateHiveGoal") || g instanceof Bee.BeeGoToHiveGoal);
/*     */         });
/*  73 */     this.f_21345_.m_25352_(7, new TaintGoal());
/*  74 */     this.f_21346_.m_262460_(g -> true);
/*  75 */     this.f_21346_.m_25352_(1, (Goal)(new HurtByTargetGoal((PathfinderMob)this, new Class[0])).m_26044_(new Class[0]));
/*  76 */     this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  77 */     this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_8024_() {
/*  82 */     if (m_20072_()) {
/*  83 */       this.underWaterTicks++;
/*     */     } else {
/*  85 */       this.underWaterTicks = 0;
/*  86 */     }  if (this.underWaterTicks > 200) {
/*  87 */       m_6469_(m_269291_().m_269063_(), 1.0F);
/*     */     }
/*  89 */     if (!(m_9236_()).f_46443_) {
/*  90 */       m_21666_((ServerLevel)m_9236_(), false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static AttributeSupplier.Builder m_27858_() {
/*  95 */     return Monster.m_33035_().m_22268_(Attributes.f_22279_, 0.3D).m_22268_(Attributes.f_22276_, 15.0D).m_22268_(Attributes.f_22280_, 1.2D).m_22268_(Attributes.f_22281_, 2.0D).m_22268_(Attributes.f_22277_, 48.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_27854_() {
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_27856_() {
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8107_() {
/* 113 */     super.m_8107_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/* 119 */     super.m_8119_();
/* 120 */     sickenedTick();
/* 121 */     if (this.f_19797_ % 4 == 0) {
/*     */       
/* 123 */       double d0 = m_20185_() + this.f_19796_.m_188501_();
/* 124 */       double d1 = m_20186_() + this.f_19796_.m_188501_();
/* 125 */       double d2 = m_20189_() + this.f_19796_.m_188501_();
/* 126 */       m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.PHLEGM.get(), d0 - 0.5D, d1, d2 - 0.5D, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
/* 133 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/* 139 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6071_(Player player, InteractionHand hand) {
/* 145 */     InteractionResult result = sickenedMobInteract(player, hand);
/* 146 */     if (result != null) {
/* 147 */       return result;
/*     */     }
/* 149 */     return super.m_6071_(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(MobEffectInstance effect, Entity entity) {
/* 155 */     return (sickenedAddEffect(effect, entity) && super.m_147207_(effect, entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_6100_() {
/* 167 */     return sickenedGetVoicePitch(1.15F, 0.65F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/* 173 */     super.m_7380_(tag);
/* 174 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 180 */     super.m_7378_(tag);
/* 181 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 187 */     super.m_8097_();
/* 188 */     this.f_19804_.m_135372_(CONVERTING, Boolean.valueOf(false));
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
/* 200 */     return ((Boolean)this.f_19804_.m_135370_(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 206 */     this.f_19804_.m_135381_(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 212 */     return m_21519_(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6549_(EntityType<?> type) {
/* 218 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Bee m_142606_(ServerLevel level, AgeableMob mob) {
/* 224 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6898_(ItemStack stack) {
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/* 236 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7327_(Entity target) {
/* 242 */     boolean flag = target.m_6469_(m_269291_().m_269396_((LivingEntity)this), (int)m_21133_(Attributes.f_22281_));
/* 243 */     if (flag) {
/*     */       
/* 245 */       addPotentWitherToTarget(target);
/* 246 */       m_5496_(SoundEvents.f_11692_, 1.0F, 0.8F);
/*     */     } 
/* 248 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float amount) {
/* 254 */     if (!sickenedCanBeHurt(source, amount))
/* 255 */       return false; 
/* 256 */     return super.m_6469_(source, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   class TaintGoal
/*     */     extends Goal
/*     */   {
/*     */     private static final int MAX_USE_TICKS = 120;
/*     */     private int useTicks;
/*     */     
/*     */     public boolean m_8036_() {
/* 267 */       if (SickenedBee.this.m_21660_())
/* 268 */         return false; 
/* 269 */       if (SickenedBee.this.f_19796_.m_188501_() < 0.3F) {
/* 270 */         return false;
/*     */       }
/* 272 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean m_8045_() {
/* 278 */       return (!SickenedBee.this.m_21660_() && this.useTicks > 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void m_8056_() {
/* 284 */       this.useTicks = 120;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void m_8037_() {
/* 290 */       this.useTicks--;
/*     */       
/* 292 */       if (SickenedBee.this.f_19796_.m_188503_(m_183277_(30)) == 0) {
/*     */         
/* 294 */         BlockPos pos = SickenedBee.this.m_20183_();
/* 295 */         BlockPos saved = SickenedBee.this.m_27851_();
/* 296 */         if (SickenedBee.TAINTABLE.test(SickenedBee.this.m_9236_().m_8055_(pos))) {
/* 297 */           WorldTainting.getInstance().convertBlock(pos, SickenedBee.this.m_9236_());
/* 298 */         } else if (saved != null && pos.m_123333_((Vec3i)saved) <= 1) {
/* 299 */           WorldTainting.getInstance().convertBlock(saved, SickenedBee.this.m_9236_());
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedBee.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */