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
/*  31 */     double y = this.entity.m_20188_();
/*  32 */     int amount = this.entity.shouldIncreaseDifficulty() ? 6 : 5;
/*  33 */     for (int i = 0; i < amount; i++) {
/*     */       
/*  35 */       float theta = (float)(6.283185307179586D / amount);
/*  36 */       float angle = theta * i;
/*     */       
/*  38 */       double x = 5.0D * Mth.m_14089_(angle) + this.entity.m_20185_();
/*  39 */       double z = 5.0D * Mth.m_14031_(angle) + this.entity.m_20189_();
/*  40 */       ShulkerBullet projectile = new ShulkerBullet(EntityType.f_20522_, this.entity.m_9236_());
/*  41 */       projectile.m_6027_(x, y, z);
/*  42 */       projectile.m_20242_(true);
/*  43 */       projectile.m_5602_((Entity)this.entity);
/*  44 */       this.entity.m_9236_().m_7967_((Entity)projectile);
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
/*  62 */         projectile.m_20242_(false);
/*     */       } 
/*     */     } 
/*  65 */     this.entity.m_5496_(SoundEvents.f_12417_, 4.0F, 1.0F);
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
/*  77 */       if (entity.m_6084_()) {
/*     */         
/*  79 */         float theta = (float)(6.283185307179586D / size);
/*  80 */         float angle = theta * i + spellCastingTime * 0.1F;
/*     */         
/*  82 */         double radius = 5.0D;
/*  83 */         double x = radius * Mth.m_14089_(angle) + this.entity.m_20185_();
/*  84 */         double y = this.entity.m_20188_();
/*  85 */         double z = radius * Mth.m_14031_(angle) + this.entity.m_20189_();
/*     */         
/*  87 */         Vec3 wanted = new Vec3(x, y, z);
/*  88 */         double distance = entity.m_20182_().m_82554_(wanted);
/*  89 */         Vec3 delta = wanted.m_82546_(entity.m_20182_()).m_82541_().m_82542_(distance, distance, distance);
/*  90 */         entity.m_20256_(delta);
/*  91 */         BlockPos.m_121921_(entity.m_20191_().m_82400_(1.0D)).forEach(pos -> entity.m_9236_().m_46961_(pos, true));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 101 */     return Math.max(500, random.m_188503_(620)) - Mth.m_14143_(modifier) * 10;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\ShulkerBulletsSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */