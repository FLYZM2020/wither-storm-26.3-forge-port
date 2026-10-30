/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*    */ 
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.projectile.WitherSkull;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*    */ 
/*    */ public class WitherSkullsSpell extends SymbiontSpell {
/*    */   public WitherSkullsSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/* 20 */     super(symbiont, type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cast(LivingEntity target) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void doCasting(LivingEntity target) {
/* 31 */     if (this.entity.f_19797_ % (this.entity.shouldIncreaseDifficulty() ? 10 : 20) == 0) {
/*    */       
/* 33 */       int spellCastingTime = this.type.spellTime() - this.entity.getSpellCastingTime();
/* 34 */       double radius = 2.0D;
/* 35 */       float angle = spellCastingTime * 0.08F;
/* 36 */       float angleBetweenTarget = (float)Math.atan2(target.m_20185_() - this.entity.m_20185_(), target.m_20189_() - this.entity.m_20189_());
/* 37 */       float offset = 1.5707964F;
/* 38 */       double xOffset = Mth.m_14089_(offset - angleBetweenTarget) * radius;
/* 39 */       double zOffset = Mth.m_14031_(offset - angleBetweenTarget) * radius;
/* 40 */       double rawX = (Mth.m_14089_(angle) * Mth.m_14089_(offset * 2.0F - angleBetweenTarget)) * radius;
/* 41 */       double rawY = Mth.m_14031_(angle) * radius;
/* 42 */       double rawZ = (Mth.m_14089_(angle) * Mth.m_14031_(offset * 2.0F - angleBetweenTarget)) * radius;
/* 43 */       double x = xOffset + rawX + this.entity.m_20185_();
/* 44 */       double y = this.entity.m_20188_() + rawY;
/* 45 */       double z = zOffset + rawZ + this.entity.m_20189_();
/* 46 */       Vec3 skullPose = new Vec3(x, y, z);
/* 47 */       Vec3 delta = skullPose.m_82546_(target.m_20299_(1.0F)).m_82541_().m_82490_(-2.5D);
/* 48 */       WitherSkull skull = new WitherSkull(this.entity.m_9236_(), (LivingEntity)this.entity, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
/* 49 */       skull.m_6034_(x, y, z);
/* 50 */       if (this.entity.m_217043_().m_188503_(11) == 1)
/* 51 */         skull.m_37629_(true); 
/* 52 */       this.entity.m_9236_().m_7967_((Entity)skull);
/* 53 */       skull.m_5496_(SoundEvents.f_12558_, 4.0F, 1.0F);
/* 54 */       ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123755_, x, y, z, 20, this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), 0.01D);
/* 55 */       ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 20, this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), 0.2D);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDelay(RandomSource random, float modifier) {
/* 62 */     return Math.max(440, random.m_188503_(580)) - Mth.m_14143_(modifier) * 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\WitherSkullsSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */