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
/* 30 */     RandomSource random = this.entity.getRandom();
/* 31 */     this.stompCount = random.nextInt(3) + 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void cast(LivingEntity target) {
/* 37 */     if (this.entity.getBlockStateOn().getBlock() == Blocks.AIR)
/*    */       return; 
/* 39 */     stomp(target);
/*    */   }
/*    */ 
/*    */   
/*    */   private void stomp(LivingEntity target) {
/* 44 */     if (this.entity.getBlockStateOn().getBlock() == Blocks.AIR) {
/*    */       return;
/*    */     }
/* 47 */     float f = this.entity.getJumpPower() * 5.0F;
/* 48 */     if (this.entity.hasEffect(MobEffects.JUMP)) {
/* 49 */       f += 0.1F * (this.entity.getEffect(MobEffects.JUMP).getAmplifier() + 1);
/*    */     }
/* 51 */     Vec3 prevDelta = this.entity.getDeltaMovement();
/* 52 */     double x = this.entity.getX() - target.getX();
/* 53 */     double z = this.entity.getZ() - target.getZ();
/* 54 */     double multiplier = Math.min(0.2D, this.entity.distanceTo((Entity)target) * 0.05D);
/* 55 */     Vec3 delta = (new Vec3(x, f, z)).multiply(-multiplier, 1.0D, -multiplier);
/* 56 */     delta.add(prevDelta.x, 0.0D, prevDelta.z);
/* 57 */     this.entity.setDeltaMovement(delta);
/* 58 */     this.entity.setSmashing(true);
/* 59 */     this.entity.playSound(this.entity.getBlockStateOn().getSoundType().getBreakSound(), 4.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doCasting(LivingEntity target) {
/* 65 */     if (this.entity.shouldIncreaseDifficulty()) {
/* 66 */       ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)ParticleTypes.FLAME, this.entity.getX() + this.entity.getRandom().nextFloat(), this.entity.getY(), this.entity.getZ() + this.entity.getRandom().nextFloat(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
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
/* 89 */     return Math.max(160, random.nextInt(200)) - Mth.floor(modifier) * 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\SmashSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */