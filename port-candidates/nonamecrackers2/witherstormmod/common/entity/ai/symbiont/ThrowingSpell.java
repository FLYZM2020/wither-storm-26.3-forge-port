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
/*     */ import net.minecraft.world.entity.projectile.arrow.Arrow;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
/*     */ import net.minecraft.world.entity.projectile.SpectralArrow;
/*     */ import net.minecraft.world.entity.projectile.ThrownPotion;
/*     */ import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
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
/*  51 */     if (this.entity.tickCount % Math.max(2, this.entity.getRandom().nextInt(24 / this.entity.level().getDifficulty().getId())) == 0) {
/*     */       
/*  53 */       int randomProjectile = this.entity.getRandom().nextInt(5);
/*  54 */       int randomPotion = this.entity.getRandom().nextInt(8);
/*  55 */       int randomArrow = this.entity.getRandom().nextInt(12);
/*  56 */       Projectile projectile = getRandomProjectile(randomProjectile);
/*  57 */       if (projectile instanceof ThrownPotion) { ThrownPotion thrownPotion = (ThrownPotion)projectile;
/*     */         
/*  59 */         projectile.setXRot(projectile.getXRot() - -20.0F);
/*  60 */         MobEffectInstance potion = getPotion(randomPotion);
/*  61 */         ItemStack stack = new ItemStack((ItemLike)Items.SPLASH_POTION);
/*  62 */         PotionUtils.setPotion(stack, Potions.WATER);
/*  63 */         if (potion != null)
/*  64 */           PotionUtils.setCustomEffects(stack, Lists.newArrayList((Object[])new MobEffectInstance[] { potion })); 
/*  65 */         thrownPotion.setItem(stack); }
/*     */       
/*  67 */       if (projectile instanceof Arrow) { Arrow arrow = (Arrow)projectile; if (randomArrow < 6)
/*  68 */           arrow.addEffect(getArrowTip(randomPotion));  }
/*  69 */        projectile.setNoGravity(true);
/*  70 */       double offsetX = this.entity.getRandom().nextGaussian() * 5.0D;
/*  71 */       double offsetY = this.entity.getRandom().nextDouble() * 10.0D;
/*  72 */       double offsetZ = this.entity.getRandom().nextGaussian() * 5.0D;
/*  73 */       projectile.setPos(this.entity.getX() + offsetX, this.entity.getEyeY() + offsetY + 2.0D, this.entity.getZ() + offsetZ);
/*  74 */       this.entity.level().addFreshEntity((Entity)projectile);
/*  75 */       this.projectiles.add(projectile);
/*     */     } 
/*  77 */     for (Entity entity : this.projectiles) {
/*     */       
/*  79 */       if (entity instanceof Projectile) { Projectile projectile = (Projectile)entity;
/*     */         
/*  81 */         LivingEntity nearestTarget = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/*  82 */         double x = projectile.getX() + this.entity.getRandom().nextGaussian() * 0.5D;
/*  83 */         double y = projectile.getEyeY() + this.entity.getRandom().nextGaussian() * 0.5D;
/*  84 */         double z = projectile.getZ() + this.entity.getRandom().nextGaussian() * 0.5D;
/*  85 */         Vec3 delta = projectile.getEyePosition(1.0F).subtract(x, y, z).normalize().multiply(0.1D, 0.1D, 0.1D);
/*  86 */         ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, delta.x(), delta.y(), delta.z(), 1.0D);
/*  87 */         if (projectile.isAlive() && projectile.isNoGravity() && projectile instanceof net.minecraft.world.entity.projectile.AbstractArrow) {
/*     */           
/*  89 */           Vec3 motion = nearestTarget.position().subtract(projectile.position()).normalize().multiply(0.005D, 0.005D, 0.005D);
/*  90 */           projectile.setDeltaMovement(motion);
/*  91 */           projectile.tick();
/*     */         } 
/*  93 */         if (projectile.tickCount == 40 && nearestTarget != null) {
/*     */           
/*  95 */           projectile.setDeltaMovement(Vec3.ZERO);
/*  96 */           double x1 = nearestTarget.getX() - projectile.getX();
/*  97 */           double y1 = nearestTarget.getY(0.3333333333333333D) - projectile.getY();
/*  98 */           double z1 = nearestTarget.getZ() - projectile.getZ();
/*  99 */           double distance = Math.sqrt(x1 * x1 + z1 * z1);
/* 100 */           projectile.setNoGravity(false);
/* 101 */           projectile.playSound(SoundEvents.ARROW_SHOOT, 4.0F, 1.0F);
/* 102 */           if (projectile instanceof ThrownPotion) {
/* 103 */             projectile.shoot(x1, y1 + distance * 0.2D, z1, (float)(0.75D + distance * 0.02D), 8.0F); continue;
/*     */           } 
/* 105 */           projectile.shoot(x1, y1 + distance * 0.2D, z1, (float)(1.600000023841858D + distance * 0.02D), (14 - this.entity.level().getDifficulty().getId() * 4));
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
/* 117 */         return new MobEffectInstance(MobEffects.WITHER, 60, 2);
/*     */ 
/*     */       
/*     */       case 1:
/* 121 */         return new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 400, 2);
/*     */ 
/*     */       
/*     */       case 2:
/* 125 */         return new MobEffectInstance(MobEffects.HUNGER, 100, 1);
/*     */ 
/*     */       
/*     */       case 3:
/* 129 */         return new MobEffectInstance(MobEffects.UNLUCK, 800, 2);
/*     */ 
/*     */       
/*     */       case 4:
/* 133 */         return new MobEffectInstance(MobEffects.WEAKNESS, 100, 1);
/*     */ 
/*     */       
/*     */       case 5:
/* 137 */         return new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1);
/*     */ 
/*     */       
/*     */       case 6:
/* 141 */         return new MobEffectInstance(MobEffects.BLINDNESS, 60);
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
/* 153 */         return (Projectile)new ThrownPotion(this.entity.level(), (LivingEntity)this.entity);
/* 154 */       case 1: return (Projectile)new Snowball(this.entity.level(), (LivingEntity)this.entity);
/* 155 */       case 2: return (Projectile)new Arrow(this.entity.level(), (LivingEntity)this.entity);
/* 156 */       case 3: return (Projectile)new SpectralArrow(this.entity.level(), (LivingEntity)this.entity);
/* 157 */     }  return (Projectile)new ThrownTrident(this.entity.level(), (LivingEntity)this.entity, new ItemStack((ItemLike)Items.TRIDENT));
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
/* 168 */         arrow = new MobEffectInstance(MobEffects.BLINDNESS, 10, 5);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 175 */         return arrow;case 1: arrow = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 8); return arrow;case 2: arrow = new MobEffectInstance(MobEffects.WEAKNESS, 10, 1); return arrow;case 3: arrow = new MobEffectInstance(MobEffects.HUNGER, 10, 2); return arrow;case 4: arrow = new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 3); return arrow; }  MobEffectInstance arrow = new MobEffectInstance(MobEffects.CONFUSION, 10); return arrow;
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
/* 188 */       double x = projectile.getX() + this.entity.getRandom().nextGaussian() * 0.5D;
/* 189 */       double y = projectile.getEyeY() + this.entity.getRandom().nextGaussian() * 0.5D;
/* 190 */       double z = projectile.getZ() + this.entity.getRandom().nextGaussian() * 0.5D;
/* 191 */       Vec3 delta = projectile.getEyePosition(1.0F).subtract(x, y, z).normalize().multiply(0.1D, 0.1D, 0.1D);
/* 192 */       ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)ParticleTypes.LARGE_SMOKE, x, y, z, 0, delta.x(), delta.y(), delta.z(), 1.0D);
/* 193 */       projectile.discard();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 200 */     return Math.max(360, random.nextInt(480)) - Mth.floor(modifier) * 10;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\ThrowingSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */