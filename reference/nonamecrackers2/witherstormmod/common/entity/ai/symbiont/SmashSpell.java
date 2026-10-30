/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*    */ 
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.effect.MobEffects;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ 
/*    */ public class SmashSpell extends SymbiontSpell {
/*    */   private int stompCount;
/*    */   private int stompCooldown;
/*    */   
/*    */   public SmashSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/* 22 */     super(symbiont, type);
/* 23 */     this.stompCount = 0;
/* 24 */     this.stompCooldown = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void start(LivingEntity target) {
/* 30 */     RandomSource random = this.entity.m_217043_();
/* 31 */     this.stompCount = random.m_188503_(3) + 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void cast(LivingEntity target) {
/* 37 */     if (this.entity.m_20075_().m_60734_() == Blocks.f_50016_)
/*    */       return; 
/* 39 */     stomp(target);
/*    */   }
/*    */ 
/*    */   
/*    */   private void stomp(LivingEntity target) {
/* 44 */     if (this.entity.m_20075_().m_60734_() == Blocks.f_50016_) {
/*    */       return;
/*    */     }
/* 47 */     float f = this.entity.m_6118_() * 5.0F;
/* 48 */     if (this.entity.m_21023_(MobEffects.f_19603_)) {
/* 49 */       f += 0.1F * (this.entity.m_21124_(MobEffects.f_19603_).m_19564_() + 1);
/*    */     }
/* 51 */     Vec3 prevDelta = this.entity.m_20184_();
/* 52 */     double x = this.entity.m_20185_() - target.m_20185_();
/* 53 */     double z = this.entity.m_20189_() - target.m_20189_();
/* 54 */     double multiplier = Math.min(0.2D, this.entity.m_20270_((Entity)target) * 0.05D);
/* 55 */     Vec3 delta = (new Vec3(x, f, z)).m_82542_(-multiplier, 1.0D, -multiplier);
/* 56 */     delta.m_82520_(prevDelta.f_82479_, 0.0D, prevDelta.f_82481_);
/* 57 */     this.entity.m_20256_(delta);
/* 58 */     this.entity.setSmashing(true);
/* 59 */     this.entity.m_5496_(this.entity.m_20075_().m_60827_().m_56775_(), 4.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doCasting(LivingEntity target) {
/* 65 */     if (this.entity.shouldIncreaseDifficulty()) {
/* 66 */       ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123744_, this.entity.m_20185_() + this.entity.m_217043_().m_188501_(), this.entity.m_20186_(), this.entity.m_20189_() + this.entity.m_217043_().m_188501_(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */     }
/* 68 */     if (this.stompCount > 0 && this.stompCooldown <= 0) {
/*    */       
/* 70 */       stomp(target);
/* 71 */       this.stompCount--;
/* 72 */       this.stompCooldown = 5;
/* 73 */       if (this.stompCount > 0)
/*    */       {
/* 75 */         LivingEntity newTarget = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/* 76 */         cast(newTarget);
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 81 */       this.stompCooldown--;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDelay(RandomSource random, float modifier) {
/* 89 */     return Math.max(160, random.m_188503_(200)) - Mth.m_14143_(modifier) * 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\SmashSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */