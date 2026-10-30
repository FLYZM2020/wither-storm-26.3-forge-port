/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*     */ 
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
/*     */ import net.minecraft.world.entity.projectile.arrow.Arrow;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ 
/*     */ public class ArrowsSpell
/*     */   extends SymbiontSpell {
/*     */   public ArrowsSpell(WitheredSymbiontEntity entity, SpellType type) {
/*  23 */     super(entity, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(LivingEntity target) {
/*  30 */     for (Entity projectile : this.projectiles) {
/*  31 */       projectile.discard();
/*     */     }
/*  33 */     this.projectiles.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCasting(LivingEntity target) {
/*  39 */     int count = this.entity.shouldIncreaseDifficulty() ? 6 : 3;
/*  40 */     int timer = this.entity.shouldIncreaseDifficulty() ? 10 : 20;
/*  41 */     if (this.entity.tickCount % 4 == 0) {
/*     */       
/*  43 */       for (int i = 0; i < count; i++) {
/*     */         
/*  45 */         Arrow arrow = new Arrow(this.entity.level(), (LivingEntity)this.entity);
/*  46 */         arrow.setNoGravity(true);
/*  47 */         double deltaX = this.entity.getRandom().nextGaussian() * 0.55D;
/*  48 */         double deltaY = this.entity.getRandom().nextDouble() * 0.75D;
/*  49 */         double deltaZ = this.entity.getRandom().nextGaussian() * 0.55D;
/*  50 */         arrow.setDeltaMovement(deltaX, deltaY, deltaZ);
/*  51 */         Vec2 rot = getRot(new Vec3(deltaX, deltaY, deltaZ));
/*  52 */         arrow.setXRot(rot.x);
/*  53 */         arrow.setYRot(rot.y);
/*  54 */         this.entity.level().addFreshEntity((Entity)arrow);
/*  55 */         this.projectiles.add(arrow);
/*     */       } 
/*  57 */       this.entity.playSound(SoundEvents.DISPENSER_DISPENSE, 4.0F, 0.5F + (this.entity.getRandom().nextFloat() - 0.5F) * 0.1F);
/*     */     } 
/*     */     
/*  60 */     for (Entity projectile : this.projectiles) {
/*     */       
/*  62 */       AbstractArrow arrow = (AbstractArrow)projectile;
/*  63 */       if (projectile.isAlive() && projectile.tickCount == timer) {
/*     */         
/*  65 */         LivingEntity nearestTarget = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/*  66 */         if (nearestTarget != null) {
/*     */           
/*  68 */           double x = nearestTarget.getX() + this.entity.getRandom().nextGaussian() * 2.0D - arrow.getX();
/*  69 */           double y = nearestTarget.getY(0.3333333333333333D) + this.entity.getRandom().nextGaussian() * 0.5D - arrow.getY();
/*  70 */           double z = nearestTarget.getZ() + this.entity.getRandom().nextGaussian() * 2.0D - arrow.getZ();
/*  71 */           double d0 = Math.sqrt(x * x + z * z);
/*  72 */           arrow.shoot(x, y + d0 * 0.2D, z, 2.5F, (14 - this.entity.level().getDifficulty().getId() * 4));
/*  73 */           arrow.setNoGravity(false);
/*  74 */           arrow.playSound(SoundEvents.ARROW_SHOOT, 2.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */       
/*  78 */       if (projectile.isAlive() && projectile.tickCount < timer) {
/*     */         
/*  80 */         double x = projectile.getX() + this.entity.getRandom().nextGaussian() * 0.5D;
/*  81 */         double y = projectile.getEyeY() + this.entity.getRandom().nextGaussian() * 0.5D;
/*  82 */         double z = projectile.getZ() + this.entity.getRandom().nextGaussian() * 0.5D;
/*  83 */         Vec3 delta = projectile.getEyePosition(1.0F).subtract(x, y, z).normalize().multiply(0.1D, 0.1D, 0.1D);
/*  84 */         ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, delta.x(), delta.y(), delta.z(), 1.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void cast(LivingEntity target) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void finish() {
/*  96 */     for (Entity projectile : this.projectiles) {
/*     */       
/*  98 */       double x = projectile.getX() + this.entity.getRandom().nextGaussian() * 0.5D;
/*  99 */       double y = projectile.getEyeY() + this.entity.getRandom().nextGaussian() * 0.5D;
/* 100 */       double z = projectile.getZ() + this.entity.getRandom().nextGaussian() * 0.5D;
/* 101 */       Vec3 delta = projectile.getEyePosition(1.0F).subtract(x, y, z).normalize().multiply(0.1D, 0.1D, 0.1D);
/* 102 */       ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)ParticleTypes.LARGE_SMOKE, x, y, z, 0, delta.x(), delta.y(), delta.z(), 1.0D);
/* 103 */       projectile.discard();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 110 */     return Math.max(160, random.nextInt(200)) - Mth.floor(modifier) * 10;
/*     */   }
/*     */ 
/*     */   
/*     */   private Vec2 getRot(Vec3 delta) {
/* 115 */     float f = Mth.sqrt((float)delta.horizontalDistanceSqr());
/* 116 */     float xRot = (float)(Mth.atan2(delta.y, f) * 57.2957763671875D);
/* 117 */     float yRot = (float)(Mth.atan2(delta.x, delta.z) * 57.2957763671875D);
/* 118 */     return new Vec2(xRot, yRot);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\ArrowsSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */