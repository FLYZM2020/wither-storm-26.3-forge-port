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
/*     */ import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
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
/*  41 */     double y = this.entity.m_20188_() + 4.0D;
/*  42 */     float theta = (float)(6.283185307179586D / this.count);
/*  43 */     for (int i = 0; i < this.count; i++) {
/*     */       
/*  45 */       float angle = theta * i;
/*     */       
/*  47 */       double x = 7.0D * Mth.m_14089_(angle) + this.entity.m_20185_();
/*  48 */       double z = 7.0D * Mth.m_14031_(angle) + this.entity.m_20189_();
/*  49 */       AbstractHurtingProjectile abstractHurtingProjectile = this.factory.make(this.entity.m_9236_(), (LivingEntity)this.entity, 0.0D, 0.0D, 0.0D);
/*  50 */       abstractHurtingProjectile.m_6034_(x, y, z);
/*  51 */       abstractHurtingProjectile.m_20242_(true);
/*  52 */       this.entity.m_9236_().m_7967_((Entity)abstractHurtingProjectile);
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
/*  72 */       if (projectile instanceof AbstractHurtingProjectile) { AbstractHurtingProjectile damaging = (AbstractHurtingProjectile)projectile; if (projectile.m_6084_()) {
/*     */           
/*  74 */           float angle = theta * i + spellCastingTime * 0.08F;
/*     */           
/*  76 */           double x = radius * Mth.m_14089_(angle) + this.entity.m_20185_();
/*  77 */           double y = this.entity.m_20188_() + 4.0D;
/*  78 */           double z = radius * Mth.m_14031_(angle) + this.entity.m_20189_();
/*     */           
/*  80 */           Vec3 wanted = new Vec3(x, y, z);
/*     */           
/*  82 */           double distance = damaging.m_20182_().m_82554_(wanted);
/*  83 */           double multiplier = Math.min(1.0D, distance);
/*  84 */           Vec3 delta = wanted.m_82546_(damaging.m_20182_()).m_82541_().m_82542_(multiplier, multiplier, multiplier);
/*     */           
/*  86 */           if (spellCastingTime % this.throwInterval == 0 && i == 0) {
/*     */             
/*  88 */             Vec3 targetDelta = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE).m_20299_(1.0F).m_82546_(damaging.m_20182_()).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/*  89 */             if (projectile instanceof nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity) {
/*  90 */               damaging.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SHOOT.get(), 4.0F, 1.0F);
/*     */             } else {
/*  92 */               damaging.m_5496_(SoundEvents.f_11705_, 4.0F, 1.0F);
/*  93 */             }  this.projectiles.remove(i);
/*  94 */             damaging.m_20256_(Vec3.f_82478_);
/*  95 */             damaging.f_36813_ = targetDelta.m_7096_();
/*  96 */             damaging.f_36814_ = targetDelta.m_7098_();
/*  97 */             damaging.f_36815_ = targetDelta.m_7094_();
/*  98 */             UpdateDamagingProjectileMessage message = new UpdateDamagingProjectileMessage(damaging);
/*  99 */             WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> damaging), message);
/*     */           }
/*     */           else {
/*     */             
/* 103 */             damaging.m_20256_(delta);
/* 104 */             ((ServerChunkCache)this.entity.m_20193_().m_7726_()).m_8445_((Entity)damaging, (Packet)new ClientboundSetEntityMotionPacket((Entity)damaging));
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
/* 118 */       projectile.m_146870_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 124 */     return Math.max(400, random.m_188503_(520)) - Mth.m_14143_(modifier) * 10;
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