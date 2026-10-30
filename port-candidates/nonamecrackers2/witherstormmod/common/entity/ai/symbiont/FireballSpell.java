/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*     */ 
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
/*     */ import net.minecraft.server.level.ServerChunkCache;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateDamagingProjectileMessage;
/*     */ 
/*     */ public class FireballSpell
/*     */   extends SymbiontSpell
/*     */ {
/*     */   private final ProjectileFactory factory;
/*     */   private final int count;
/*     */   private final int throwInterval;
/*     */   
/*     */   public FireballSpell(WitheredSymbiontEntity symbiont, SpellType type, ProjectileFactory factory, int count) {
/*  31 */     super(symbiont, type);
/*  32 */     this.factory = factory;
/*  33 */     this.count = count;
/*  34 */     this.throwInterval = type.spellTime() / count;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(LivingEntity target) {
/*  40 */     this.projectiles.clear();
/*  41 */     double y = this.entity.getEyeY() + 4.0D;
/*  42 */     float theta = (float)(6.283185307179586D / this.count);
/*  43 */     for (int i = 0; i < this.count; i++) {
/*     */       
/*  45 */       float angle = theta * i;
/*     */       
/*  47 */       double x = 7.0D * Mth.cos(angle) + this.entity.getX();
/*  48 */       double z = 7.0D * Mth.sin(angle) + this.entity.getZ();
/*  49 */       AbstractHurtingProjectile abstractHurtingProjectile = this.factory.make(this.entity.level(), (LivingEntity)this.entity, 0.0D, 0.0D, 0.0D);
/*  50 */       abstractHurtingProjectile.setPos(x, y, z);
/*  51 */       abstractHurtingProjectile.setNoGravity(true);
/*  52 */       this.entity.level().addFreshEntity((Entity)abstractHurtingProjectile);
/*  53 */       this.projectiles.add(abstractHurtingProjectile);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cast(LivingEntity target) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCasting(LivingEntity target) {
/*  65 */     int size = this.projectiles.size();
/*  66 */     int spellCastingTime = this.type.spellTime() - this.entity.getSpellCastingTime();
/*  67 */     float theta = 6.2831855F / size;
/*  68 */     double radius = 7.0D;
/*  69 */     for (int i = this.projectiles.size() - 1; i >= 0; i--) {
/*     */       
/*  71 */       Entity projectile = this.projectiles.get(i);
/*  72 */       if (projectile instanceof AbstractHurtingProjectile) { AbstractHurtingProjectile damaging = (AbstractHurtingProjectile)projectile; if (projectile.isAlive()) {
/*     */           
/*  74 */           float angle = theta * i + spellCastingTime * 0.08F;
/*     */           
/*  76 */           double x = radius * Mth.cos(angle) + this.entity.getX();
/*  77 */           double y = this.entity.getEyeY() + 4.0D;
/*  78 */           double z = radius * Mth.sin(angle) + this.entity.getZ();
/*     */           
/*  80 */           Vec3 wanted = new Vec3(x, y, z);
/*     */           
/*  82 */           double distance = damaging.position().distanceTo(wanted);
/*  83 */           double multiplier = Math.min(1.0D, distance);
/*  84 */           Vec3 delta = wanted.subtract(damaging.position()).normalize().multiply(multiplier, multiplier, multiplier);
/*     */           
/*  86 */           if (spellCastingTime % this.throwInterval == 0 && i == 0) {
/*     */             
/*  88 */             Vec3 targetDelta = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE).getEyePosition(1.0F).subtract(damaging.position()).normalize().multiply(0.1D, 0.1D, 0.1D);
/*  89 */             if (projectile instanceof nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity) {
/*  90 */               damaging.playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SHOOT.get(), 4.0F, 1.0F);
/*     */             } else {
/*  92 */               damaging.playSound(SoundEvents.BLAZE_SHOOT, 4.0F, 1.0F);
/*  93 */             }  this.projectiles.remove(i);
/*  94 */             damaging.setDeltaMovement(Vec3.ZERO);
/*  95 */             damaging.xPower = targetDelta.x();
/*  96 */             damaging.yPower = targetDelta.y();
/*  97 */             damaging.zPower = targetDelta.z();
/*  98 */             UpdateDamagingProjectileMessage message = new UpdateDamagingProjectileMessage(damaging);
/*  99 */             WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> damaging), message);
/*     */           }
/*     */           else {
/*     */             
/* 103 */             damaging.setDeltaMovement(delta);
/* 104 */             ((ServerChunkCache)this.entity.getCommandSenderWorld().getChunkSource()).broadcast((Entity)damaging, (Packet)new ClientboundSetEntityMotionPacket((Entity)damaging));
/*     */           } 
/*     */           continue;
/*     */         }  }
/*     */       
/* 109 */       this.projectiles.remove(i);
/*     */       continue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void finish() {
/* 117 */     for (Entity projectile : this.projectiles) {
/* 118 */       projectile.discard();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 124 */     return Math.max(400, random.nextInt(520)) - Mth.floor(modifier) * 10;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ProjectileFactory {
/*     */     AbstractHurtingProjectile make(Level param1Level, LivingEntity param1LivingEntity, double param1Double1, double param1Double2, double param1Double3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\FireballSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */