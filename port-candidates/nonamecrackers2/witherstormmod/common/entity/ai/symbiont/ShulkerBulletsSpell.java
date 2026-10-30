/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*     */ 
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.projectile.ShulkerBullet;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinShulkerBullet;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class ShulkerBulletsSpell
/*     */   extends SymbiontSpell
/*     */ {
/*     */   public ShulkerBulletsSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/*  24 */     super(symbiont, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(@NotNull LivingEntity target) {
/*  30 */     this.projectiles.clear();
/*  31 */     double y = this.entity.getEyeY();
/*  32 */     int amount = this.entity.shouldIncreaseDifficulty() ? 6 : 5;
/*  33 */     for (int i = 0; i < amount; i++) {
/*     */       
/*  35 */       float theta = (float)(6.283185307179586D / amount);
/*  36 */       float angle = theta * i;
/*     */       
/*  38 */       double x = 5.0D * Mth.cos(angle) + this.entity.getX();
/*  39 */       double z = 5.0D * Mth.sin(angle) + this.entity.getZ();
/*  40 */       ShulkerBullet projectile = new ShulkerBullet(EntityType.SHULKER_BULLET, this.entity.level());
/*  41 */       projectile.moveTo(x, y, z);
/*  42 */       projectile.setNoGravity(true);
/*  43 */       projectile.setOwner((Entity)this.entity);
/*  44 */       this.entity.level().addFreshEntity((Entity)projectile);
/*  45 */       this.projectiles.add(projectile);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void cast(LivingEntity target) {
/*  52 */     int size = this.projectiles.size();
/*  53 */     for (int i = 0; i < size; i++) {
/*     */       
/*  55 */       Entity projectile = this.projectiles.get(i);
/*  56 */       if (projectile instanceof ShulkerBullet) {
/*     */         
/*  58 */         IMixinShulkerBullet bullet = (IMixinShulkerBullet)projectile;
/*  59 */         bullet.setFinalTarget((Entity)this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE));
/*  60 */         bullet.setCurrentMoveDirection(Direction.UP);
/*  61 */         bullet.callSelectNextMoveDirection(Direction.Axis.Y);
/*  62 */         projectile.setNoGravity(false);
/*     */       } 
/*     */     } 
/*  65 */     this.entity.playSound(SoundEvents.SHULKER_SHOOT, 4.0F, 1.0F);
/*  66 */     this.projectiles.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCasting(LivingEntity target) {
/*  72 */     int size = this.projectiles.size();
/*  73 */     int spellCastingTime = this.type.spellTime() - this.entity.getSpellCastingTime();
/*  74 */     for (int i = 0; i < size; i++) {
/*     */       
/*  76 */       Entity entity = this.projectiles.get(i);
/*  77 */       if (entity.isAlive()) {
/*     */         
/*  79 */         float theta = (float)(6.283185307179586D / size);
/*  80 */         float angle = theta * i + spellCastingTime * 0.1F;
/*     */         
/*  82 */         double radius = 5.0D;
/*  83 */         double x = radius * Mth.cos(angle) + this.entity.getX();
/*  84 */         double y = this.entity.getEyeY();
/*  85 */         double z = radius * Mth.sin(angle) + this.entity.getZ();
/*     */         
/*  87 */         Vec3 wanted = new Vec3(x, y, z);
/*  88 */         double distance = entity.position().distanceTo(wanted);
/*  89 */         Vec3 delta = wanted.subtract(entity.position()).normalize().multiply(distance, distance, distance);
/*  90 */         entity.setDeltaMovement(delta);
/*  91 */         BlockPos.betweenClosedStream(entity.getBoundingBox().inflate(1.0D)).forEach(pos -> entity.level().destroyBlock(pos, true));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 101 */     return Math.max(500, random.nextInt(620)) - Mth.floor(modifier) * 10;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\ShulkerBulletsSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */