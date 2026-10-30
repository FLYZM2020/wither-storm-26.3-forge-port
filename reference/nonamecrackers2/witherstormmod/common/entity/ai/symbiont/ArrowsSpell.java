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
/*     */ import net.minecraft.world.entity.projectile.AbstractArrow;
/*     */ import net.minecraft.world.entity.projectile.Arrow;
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
/*  31 */       projectile.m_146870_();
/*     */     }
/*  33 */     this.projectiles.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCasting(LivingEntity target) {
/*  39 */     int count = this.entity.shouldIncreaseDifficulty() ? 6 : 3;
/*  40 */     int timer = this.entity.shouldIncreaseDifficulty() ? 10 : 20;
/*  41 */     if (this.entity.f_19797_ % 4 == 0) {
/*     */       
/*  43 */       for (int i = 0; i < count; i++) {
/*     */         
/*  45 */         Arrow arrow = new Arrow(this.entity.m_9236_(), (LivingEntity)this.entity);
/*  46 */         arrow.m_20242_(true);
/*  47 */         double deltaX = this.entity.m_217043_().m_188583_() * 0.55D;
/*  48 */         double deltaY = this.entity.m_217043_().m_188500_() * 0.75D;
/*  49 */         double deltaZ = this.entity.m_217043_().m_188583_() * 0.55D;
/*  50 */         arrow.m_20334_(deltaX, deltaY, deltaZ);
/*  51 */         Vec2 rot = getRot(new Vec3(deltaX, deltaY, deltaZ));
/*  52 */         arrow.m_146926_(rot.f_82470_);
/*  53 */         arrow.m_146922_(rot.f_82471_);
/*  54 */         this.entity.m_9236_().m_7967_((Entity)arrow);
/*  55 */         this.projectiles.add(arrow);
/*     */       } 
/*  57 */       this.entity.m_5496_(SoundEvents.f_11796_, 4.0F, 0.5F + (this.entity.m_217043_().m_188501_() - 0.5F) * 0.1F);
/*     */     } 
/*     */     
/*  60 */     for (Entity projectile : this.projectiles) {
/*     */       
/*  62 */       AbstractArrow arrow = (AbstractArrow)projectile;
/*  63 */       if (projectile.m_6084_() && projectile.f_19797_ == timer) {
/*     */         
/*  65 */         LivingEntity nearestTarget = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/*  66 */         if (nearestTarget != null) {
/*     */           
/*  68 */           double x = nearestTarget.m_20185_() + this.entity.m_217043_().m_188583_() * 2.0D - arrow.m_20185_();
/*  69 */           double y = nearestTarget.m_20227_(0.3333333333333333D) + this.entity.m_217043_().m_188583_() * 0.5D - arrow.m_20186_();
/*  70 */           double z = nearestTarget.m_20189_() + this.entity.m_217043_().m_188583_() * 2.0D - arrow.m_20189_();
/*  71 */           double d0 = Math.sqrt(x * x + z * z);
/*  72 */           arrow.m_6686_(x, y + d0 * 0.2D, z, 2.5F, (14 - this.entity.m_9236_().m_46791_().m_19028_() * 4));
/*  73 */           arrow.m_20242_(false);
/*  74 */           arrow.m_5496_(SoundEvents.f_11687_, 2.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */       
/*  78 */       if (projectile.m_6084_() && projectile.f_19797_ < timer) {
/*     */         
/*  80 */         double x = projectile.m_20185_() + this.entity.m_217043_().m_188583_() * 0.5D;
/*  81 */         double y = projectile.m_20188_() + this.entity.m_217043_().m_188583_() * 0.5D;
/*  82 */         double z = projectile.m_20189_() + this.entity.m_217043_().m_188583_() * 0.5D;
/*  83 */         Vec3 delta = projectile.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/*  84 */         ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0D);
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
/*  98 */       double x = projectile.m_20185_() + this.entity.m_217043_().m_188583_() * 0.5D;
/*  99 */       double y = projectile.m_20188_() + this.entity.m_217043_().m_188583_() * 0.5D;
/* 100 */       double z = projectile.m_20189_() + this.entity.m_217043_().m_188583_() * 0.5D;
/* 101 */       Vec3 delta = projectile.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/* 102 */       ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123755_, x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0D);
/* 103 */       projectile.m_146870_();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 110 */     return Math.max(160, random.m_188503_(200)) - Mth.m_14143_(modifier) * 10;
/*     */   }
/*     */ 
/*     */   
/*     */   private Vec2 getRot(Vec3 delta) {
/* 115 */     float f = Mth.m_14116_((float)delta.m_165925_());
/* 116 */     float xRot = (float)(Mth.m_14136_(delta.f_82480_, f) * 57.2957763671875D);
/* 117 */     float yRot = (float)(Mth.m_14136_(delta.f_82479_, delta.f_82481_) * 57.2957763671875D);
/* 118 */     return new Vec2(xRot, yRot);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\ArrowsSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */