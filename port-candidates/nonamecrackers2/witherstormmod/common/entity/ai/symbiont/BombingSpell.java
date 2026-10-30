/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*    */ 
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedCreeper;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class BombingSpell
/*    */   extends SymbiontSpell {
/*    */   public BombingSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/* 24 */     super(symbiont, type);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void start(LivingEntity target) {
/* 30 */     double x = this.entity.getX() + this.entity.getRandom().nextGaussian() * 1.0D;
/* 31 */     double y = this.entity.getEyeY() + 1.0D;
/* 32 */     double z = this.entity.getZ() + this.entity.getRandom().nextGaussian() * 1.0D;
/* 33 */     SickenedCreeper livingProjectile = new SickenedCreeper((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), this.entity.level());
/* 34 */     livingProjectile.setNoGravity(true);
/* 35 */     livingProjectile.setPos(x, y, z);
/* 36 */     livingProjectile.setDeltaMovement(0.0D, this.entity.getRandom().nextDouble() * 0.07D, 0.0D);
/* 37 */     this.entity.level().addFreshEntity((Entity)livingProjectile);
/* 38 */     this.projectiles.add(livingProjectile);
/* 39 */     livingProjectile.setTarget(target);
/* 40 */     this.entity.playSound(SoundEvents.CREEPER_HURT, 4.0F, 0.75F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void cast(@NotNull LivingEntity target) {
/* 46 */     for (Entity livingProjectile : this.projectiles) {
/*    */       
/* 48 */       SickenedCreeper projectile = (SickenedCreeper)livingProjectile;
/* 49 */       LivingEntity targetEntity = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/* 50 */       double x = targetEntity.getX() - projectile.getX();
/* 51 */       double y = this.entity.getRandom().nextGaussian() * 4.0D + targetEntity.getY(0.34D) - projectile.getY();
/* 52 */       double z = targetEntity.getZ() - projectile.getZ();
/* 53 */       double distance = Math.sqrt(x * x + y * y + z * z);
/* 54 */       projectile.setDeltaMovement(x / distance * 0.3D, y / distance * 0.25D + 1.0D, z / distance * 0.3D);
/* 55 */       projectile.setNoGravity(false);
/* 56 */       projectile.setTarget(targetEntity);
/* 57 */       projectile.ignite();
/*    */     } 
/* 59 */     this.entity.playSound(SoundEvents.CREEPER_PRIMED, 4.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doCasting(@NotNull LivingEntity target) {
/* 65 */     for (Entity livingProjectile : this.projectiles) {
/*    */       
/* 67 */       if (livingProjectile.isAlive()) {
/*    */         
/* 69 */         double x = livingProjectile.getX() + this.entity.getRandom().nextGaussian() * 1.0D;
/* 70 */         double y = livingProjectile.getEyeY() + this.entity.getRandom().nextGaussian() * 1.0D;
/* 71 */         double z = livingProjectile.getZ() + this.entity.getRandom().nextGaussian() * 1.0D;
/* 72 */         Vec3 delta = livingProjectile.getEyePosition(1.0F).subtract(x, y, z).normalize().multiply(0.1D, 0.1D, 0.1D);
/* 73 */         ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, delta.x(), delta.y(), delta.z(), 1.0D);
/* 74 */         ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)ParticleTypes.LARGE_SMOKE, livingProjectile.getX(), livingProjectile.getY(), livingProjectile.getZ(), 0, delta.x(), delta.y(), delta.z(), 0.125D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void finish() {
/* 82 */     if (this.entity.getTarget() == null)
/*    */     {
/* 84 */       for (Entity livingProjectile : this.projectiles) {
/* 85 */         livingProjectile.discard();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDelay(RandomSource random, float modifier) {
/* 92 */     return Math.max(360, random.nextInt(600)) - Mth.floor(modifier) * 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\BombingSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */