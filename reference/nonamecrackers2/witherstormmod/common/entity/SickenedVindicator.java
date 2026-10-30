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
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobType;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.entity.monster.Vindicator;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.Level;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SickenedVindicator
/*     */   extends Vindicator implements WitherSickened {
/*  33 */   private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedVindicator.class, EntityDataSerializers.f_135035_);
/*  34 */   private final WitherSickened.Data sickenedData = new WitherSickened.Data();
/*     */ 
/*     */   
/*     */   public SickenedVindicator(EntityType<? extends SickenedVindicator> type, Level level) {
/*  38 */     super(type, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MobType m_6336_() {
/*  44 */     return WitherStormModMobTypes.SICKENED;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeSupplier.Builder m_34104_() {
/*  49 */     return Monster.m_33035_().m_22268_(Attributes.f_22279_, 0.35D).m_22268_(Attributes.f_22276_, 30.0D).m_22268_(Attributes.f_22281_, 6.0D).m_22268_(Attributes.f_22277_, 48.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8099_() {
/*  55 */     super.m_8099_();
/*  56 */     this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
/*  57 */     this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7307_(Entity entity) {
/*  63 */     if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity; if (living.m_6336_() == MobType.f_21643_)
/*  64 */         return false;  }
/*  65 */      return super.m_7307_(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  71 */     super.m_8119_();
/*  72 */     sickenedTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
/*  78 */     return sickenedConvertTo(type, loot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InteractionResult m_6071_(Player player, InteractionHand hand) {
/*  84 */     InteractionResult result = sickenedMobInteract(player, hand);
/*  85 */     if (result != null) {
/*  86 */       return result;
/*     */     }
/*  88 */     return super.m_6071_(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6785_(double dist) {
/*  94 */     return sickenedRemoveWhenFarAway(dist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_147207_(@NotNull MobEffectInstance effect, Entity entity) {
/* 100 */     return (sickenedAddEffect(effect, entity) && super.m_147207_(effect, entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/* 106 */     return sickenedInfect(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_6100_() {
/* 112 */     return sickenedGetVoicePitch(1.15F, 0.65F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag tag) {
/* 118 */     super.m_7380_(tag);
/* 119 */     sickenedSave(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag tag) {
/* 125 */     super.m_7378_(tag);
/* 126 */     sickenedRead(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/* 132 */     super.m_8097_();
/* 133 */     this.f_19804_.m_135372_(CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 139 */     return this.sickenedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 145 */     return ((Boolean)this.f_19804_.m_135370_(CONVERTING)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConverting(boolean flag) {
/* 151 */     this.f_19804_.m_135381_(CONVERTING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
/* 157 */     return m_21519_(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6549_(EntityType<?> type) {
/* 163 */     return (type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6779_(LivingEntity entity) {
/* 169 */     return (super.m_6779_(entity) && sickenedCanAttack(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_37882_() {
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_37897_(boolean flag) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7492_() {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_33067_() {
/* 192 */     return false;
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
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedVindicator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */