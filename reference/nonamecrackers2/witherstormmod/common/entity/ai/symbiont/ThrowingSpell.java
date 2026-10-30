/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.projectile.Arrow;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.entity.projectile.Snowball;
/*     */ import net.minecraft.world.entity.projectile.SpectralArrow;
/*     */ import net.minecraft.world.entity.projectile.ThrownPotion;
/*     */ import net.minecraft.world.entity.projectile.ThrownTrident;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.alchemy.PotionUtils;
/*     */ import net.minecraft.world.item.alchemy.Potions;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThrowingSpell
/*     */   extends SymbiontSpell
/*     */ {
/*     */   public ThrowingSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/*  39 */     super(symbiont, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(LivingEntity target) {
/*  45 */     this.projectiles.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCasting(LivingEntity target) {
/*  51 */     if (this.entity.f_19797_ % Math.max(2, this.entity.m_217043_().m_188503_(24 / this.entity.m_9236_().m_46791_().m_19028_())) == 0) {
/*     */       
/*  53 */       int randomProjectile = this.entity.m_217043_().m_188503_(5);
/*  54 */       int randomPotion = this.entity.m_217043_().m_188503_(8);
/*  55 */       int randomArrow = this.entity.m_217043_().m_188503_(12);
/*  56 */       Projectile projectile = getRandomProjectile(randomProjectile);
/*  57 */       if (projectile instanceof ThrownPotion) { ThrownPotion thrownPotion = (ThrownPotion)projectile;
/*     */         
/*  59 */         projectile.m_146926_(projectile.m_146909_() - -20.0F);
/*  60 */         MobEffectInstance potion = getPotion(randomPotion);
/*  61 */         ItemStack stack = new ItemStack((ItemLike)Items.f_42736_);
/*  62 */         PotionUtils.m_43549_(stack, Potions.f_43599_);
/*  63 */         if (potion != null)
/*  64 */           PotionUtils.m_43552_(stack, Lists.newArrayList((Object[])new MobEffectInstance[] { potion })); 
/*  65 */         thrownPotion.m_37446_(stack); }
/*     */       
/*  67 */       if (projectile instanceof Arrow) { Arrow arrow = (Arrow)projectile; if (randomArrow < 6)
/*  68 */           arrow.m_36870_(getArrowTip(randomPotion));  }
/*  69 */        projectile.m_20242_(true);
/*  70 */       double offsetX = this.entity.m_217043_().m_188583_() * 5.0D;
/*  71 */       double offsetY = this.entity.m_217043_().m_188500_() * 10.0D;
/*  72 */       double offsetZ = this.entity.m_217043_().m_188583_() * 5.0D;
/*  73 */       projectile.m_6034_(this.entity.m_20185_() + offsetX, this.entity.m_20188_() + offsetY + 2.0D, this.entity.m_20189_() + offsetZ);
/*  74 */       this.entity.m_9236_().m_7967_((Entity)projectile);
/*  75 */       this.projectiles.add(projectile);
/*     */     } 
/*  77 */     for (Entity entity : this.projectiles) {
/*     */       
/*  79 */       if (entity instanceof Projectile) { Projectile projectile = (Projectile)entity;
/*     */         
/*  81 */         LivingEntity nearestTarget = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/*  82 */         double x = projectile.m_20185_() + this.entity.m_217043_().m_188583_() * 0.5D;
/*  83 */         double y = projectile.m_20188_() + this.entity.m_217043_().m_188583_() * 0.5D;
/*  84 */         double z = projectile.m_20189_() + this.entity.m_217043_().m_188583_() * 0.5D;
/*  85 */         Vec3 delta = projectile.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/*  86 */         ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0D);
/*  87 */         if (projectile.m_6084_() && projectile.m_20068_() && projectile instanceof net.minecraft.world.entity.projectile.AbstractArrow) {
/*     */           
/*  89 */           Vec3 motion = nearestTarget.m_20182_().m_82546_(projectile.m_20182_()).m_82541_().m_82542_(0.005D, 0.005D, 0.005D);
/*  90 */           projectile.m_20256_(motion);
/*  91 */           projectile.m_8119_();
/*     */         } 
/*  93 */         if (projectile.f_19797_ == 40 && nearestTarget != null) {
/*     */           
/*  95 */           projectile.m_20256_(Vec3.f_82478_);
/*  96 */           double x1 = nearestTarget.m_20185_() - projectile.m_20185_();
/*  97 */           double y1 = nearestTarget.m_20227_(0.3333333333333333D) - projectile.m_20186_();
/*  98 */           double z1 = nearestTarget.m_20189_() - projectile.m_20189_();
/*  99 */           double distance = Math.sqrt(x1 * x1 + z1 * z1);
/* 100 */           projectile.m_20242_(false);
/* 101 */           projectile.m_5496_(SoundEvents.f_11687_, 4.0F, 1.0F);
/* 102 */           if (projectile instanceof ThrownPotion) {
/* 103 */             projectile.m_6686_(x1, y1 + distance * 0.2D, z1, (float)(0.75D + distance * 0.02D), 8.0F); continue;
/*     */           } 
/* 105 */           projectile.m_6686_(x1, y1 + distance * 0.2D, z1, (float)(1.600000023841858D + distance * 0.02D), (14 - this.entity.m_9236_().m_46791_().m_19028_() * 4));
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static MobEffectInstance getPotion(int randomPotion) {
/* 113 */     switch (randomPotion) {
/*     */ 
/*     */       
/*     */       case 0:
/* 117 */         return new MobEffectInstance(MobEffects.f_19615_, 60, 2);
/*     */ 
/*     */       
/*     */       case 1:
/* 121 */         return new MobEffectInstance(MobEffects.f_19599_, 400, 2);
/*     */ 
/*     */       
/*     */       case 2:
/* 125 */         return new MobEffectInstance(MobEffects.f_19612_, 100, 1);
/*     */ 
/*     */       
/*     */       case 3:
/* 129 */         return new MobEffectInstance(MobEffects.f_19590_, 800, 2);
/*     */ 
/*     */       
/*     */       case 4:
/* 133 */         return new MobEffectInstance(MobEffects.f_19613_, 100, 1);
/*     */ 
/*     */       
/*     */       case 5:
/* 137 */         return new MobEffectInstance(MobEffects.f_19597_, 40, 1);
/*     */ 
/*     */       
/*     */       case 6:
/* 141 */         return new MobEffectInstance(MobEffects.f_19610_, 60);
/*     */     } 
/*     */     
/* 144 */     return null;
/*     */   }
/*     */   @NotNull
/*     */   private Projectile getRandomProjectile(int randomProjectile) {
/*     */     ThrownPotion thrownPotion;
/*     */     Snowball snowball;
/*     */     Arrow arrow;
/* 151 */     switch (randomProjectile) {
/*     */       case 0:
/* 153 */         return (Projectile)new ThrownPotion(this.entity.m_9236_(), (LivingEntity)this.entity);
/* 154 */       case 1: return (Projectile)new Snowball(this.entity.m_9236_(), (LivingEntity)this.entity);
/* 155 */       case 2: return (Projectile)new Arrow(this.entity.m_9236_(), (LivingEntity)this.entity);
/* 156 */       case 3: return (Projectile)new SpectralArrow(this.entity.m_9236_(), (LivingEntity)this.entity);
/* 157 */     }  return (Projectile)new ThrownTrident(this.entity.m_9236_(), (LivingEntity)this.entity, new ItemStack((ItemLike)Items.f_42713_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private static MobEffectInstance getArrowTip(int randomArrow) {
/* 166 */     switch (randomArrow)
/*     */     { case 0:
/* 168 */         arrow = new MobEffectInstance(MobEffects.f_19610_, 10, 5);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 175 */         return arrow;case 1: arrow = new MobEffectInstance(MobEffects.f_19597_, 5, 8); return arrow;case 2: arrow = new MobEffectInstance(MobEffects.f_19613_, 10, 1); return arrow;case 3: arrow = new MobEffectInstance(MobEffects.f_19612_, 10, 2); return arrow;case 4: arrow = new MobEffectInstance(MobEffects.f_19599_, 10, 3); return arrow; }  MobEffectInstance arrow = new MobEffectInstance(MobEffects.f_19604_, 10); return arrow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cast(LivingEntity target) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void finish() {
/* 186 */     for (Entity projectile : this.projectiles) {
/*     */       
/* 188 */       double x = projectile.m_20185_() + this.entity.m_217043_().m_188583_() * 0.5D;
/* 189 */       double y = projectile.m_20188_() + this.entity.m_217043_().m_188583_() * 0.5D;
/* 190 */       double z = projectile.m_20189_() + this.entity.m_217043_().m_188583_() * 0.5D;
/* 191 */       Vec3 delta = projectile.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/* 192 */       ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123755_, x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0D);
/* 193 */       projectile.m_146870_();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 200 */     return Math.max(360, random.m_188503_(480)) - Mth.m_14143_(modifier) * 10;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\ThrowingSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */