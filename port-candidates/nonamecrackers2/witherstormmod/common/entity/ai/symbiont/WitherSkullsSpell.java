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
/*    */ import net.minecraft.world.entity.projectile.hurtingprojectile.WitherSkull;
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
/* 31 */     if (this.entity.tickCount % (this.entity.shouldIncreaseDifficulty() ? 10 : 20) == 0) {
/*    */       
/* 33 */       int spellCastingTime = this.type.spellTime() - this.entity.getSpellCastingTime();
/* 34 */       double radius = 2.0D;
/* 35 */       float angle = spellCastingTime * 0.08F;
/* 36 */       float angleBetweenTarget = (float)Math.atan2(target.getX() - this.entity.getX(), target.getZ() - this.entity.getZ());
/* 37 */       float offset = 1.5707964F;
/* 38 */       double xOffset = Mth.cos(offset - angleBetweenTarget) * radius;
/* 39 */       double zOffset = Mth.sin(offset - angleBetweenTarget) * radius;
/* 40 */       double rawX = (Mth.cos(angle) * Mth.cos(offset * 2.0F - angleBetweenTarget)) * radius;
/* 41 */       double rawY = Mth.sin(angle) * radius;
/* 42 */       double rawZ = (Mth.cos(angle) * Mth.sin(offset * 2.0F - angleBetweenTarget)) * radius;
/* 43 */       double x = xOffset + rawX + this.entity.getX();
/* 44 */       double y = this.entity.getEyeY() + rawY;
/* 45 */       double z = zOffset + rawZ + this.entity.getZ();
/* 46 */       Vec3 skullPose = new Vec3(x, y, z);
/* 47 */       Vec3 delta = skullPose.subtract(target.getEyePosition(1.0F)).normalize().scale(-2.5D);
/* 48 */       WitherSkull skull = new WitherSkull(this.entity.level(), (LivingEntity)this.entity, delta.x(), delta.y(), delta.z());
/* 49 */       skull.setPos(x, y, z);
/* 50 */       if (this.entity.getRandom().nextInt(11) == 1)
/* 51 */         skull.setDangerous(true); 
/* 52 */       this.entity.level().addFreshEntity((Entity)skull);
/* 53 */       skull.playSound(SoundEvents.WITHER_SHOOT, 4.0F, 1.0F);
/* 54 */       ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)ParticleTypes.LARGE_SMOKE, x, y, z, 20, this.entity.getRandom().nextGaussian(), this.entity.getRandom().nextGaussian(), this.entity.getRandom().nextGaussian(), 0.01D);
/* 55 */       ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 20, this.entity.getRandom().nextGaussian(), this.entity.getRandom().nextGaussian(), this.entity.getRandom().nextGaussian(), 0.2D);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDelay(RandomSource random, float modifier) {
/* 62 */     return Math.max(440, random.nextInt(580)) - Mth.floor(modifier) * 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\WitherSkullsSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */