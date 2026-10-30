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
/* 30 */     double x = this.entity.m_20185_() + this.entity.m_217043_().m_188583_() * 1.0D;
/* 31 */     double y = this.entity.m_20188_() + 1.0D;
/* 32 */     double z = this.entity.m_20189_() + this.entity.m_217043_().m_188583_() * 1.0D;
/* 33 */     SickenedCreeper livingProjectile = new SickenedCreeper((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), this.entity.m_9236_());
/* 34 */     livingProjectile.m_20242_(true);
/* 35 */     livingProjectile.m_6034_(x, y, z);
/* 36 */     livingProjectile.m_20334_(0.0D, this.entity.m_217043_().m_188500_() * 0.07D, 0.0D);
/* 37 */     this.entity.m_9236_().m_7967_((Entity)livingProjectile);
/* 38 */     this.projectiles.add(livingProjectile);
/* 39 */     livingProjectile.m_6710_(target);
/* 40 */     this.entity.m_5496_(SoundEvents.f_11836_, 4.0F, 0.75F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void cast(@NotNull LivingEntity target) {
/* 46 */     for (Entity livingProjectile : this.projectiles) {
/*    */       
/* 48 */       SickenedCreeper projectile = (SickenedCreeper)livingProjectile;
/* 49 */       LivingEntity targetEntity = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/* 50 */       double x = targetEntity.m_20185_() - projectile.m_20185_();
/* 51 */       double y = this.entity.m_217043_().m_188583_() * 4.0D + targetEntity.m_20227_(0.34D) - projectile.m_20186_();
/* 52 */       double z = targetEntity.m_20189_() - projectile.m_20189_();
/* 53 */       double distance = Math.sqrt(x * x + y * y + z * z);
/* 54 */       projectile.m_20334_(x / distance * 0.3D, y / distance * 0.25D + 1.0D, z / distance * 0.3D);
/* 55 */       projectile.m_20242_(false);
/* 56 */       projectile.m_6710_(targetEntity);
/* 57 */       projectile.m_32312_();
/*    */     } 
/* 59 */     this.entity.m_5496_(SoundEvents.f_11837_, 4.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doCasting(@NotNull LivingEntity target) {
/* 65 */     for (Entity livingProjectile : this.projectiles) {
/*    */       
/* 67 */       if (livingProjectile.m_6084_()) {
/*    */         
/* 69 */         double x = livingProjectile.m_20185_() + this.entity.m_217043_().m_188583_() * 1.0D;
/* 70 */         double y = livingProjectile.m_20188_() + this.entity.m_217043_().m_188583_() * 1.0D;
/* 71 */         double z = livingProjectile.m_20189_() + this.entity.m_217043_().m_188583_() * 1.0D;
/* 72 */         Vec3 delta = livingProjectile.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/* 73 */         ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0D);
/* 74 */         ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123755_, livingProjectile.m_20185_(), livingProjectile.m_20186_(), livingProjectile.m_20189_(), 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 0.125D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void finish() {
/* 82 */     if (this.entity.m_5448_() == null)
/*    */     {
/* 84 */       for (Entity livingProjectile : this.projectiles) {
/* 85 */         livingProjectile.m_146870_();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDelay(RandomSource random, float modifier) {
/* 92 */     return Math.max(360, random.m_188503_(600)) - Mth.m_14143_(modifier) * 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\BombingSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */