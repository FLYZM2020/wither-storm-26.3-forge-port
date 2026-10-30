/*     */ package nonamecrackers2.witherstormmod.mixin;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.accessor.LivingEntityAccessor;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
/*     */ import nonamecrackers2.witherstormmod.common.util.BrainInjectionHelper;
/*     */ import nonamecrackers2.witherstormmod.common.util.PhlegmGravestoneHelper;
/*     */ import org.apache.commons.lang3.mutable.MutableBoolean;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({LivingEntity.class})
/*     */ public abstract class MixinLivingEntity
/*     */   extends Entity
/*     */   implements LivingEntityAccessor
/*     */ {
/*     */   @Unique
/*     */   private boolean hasDeathProtection;
/*     */   
/*     */   private MixinLivingEntity() {
/*  39 */     super(null, null);
/*  40 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Inject(method = {"<init>"}, at = {@At("TAIL")})
/*     */   public void constructorTail(EntityType<? extends LivingEntity> type, Level level, CallbackInfo ci) {
/*  49 */     BrainInjectionHelper.inject((LivingEntity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"dropAllDeathLoot"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;dropExperience()V")}, cancellable = true)
/*     */   public void witherstormmod$preventDrops_dropAllDeathLoot(DamageSource source, CallbackInfo ci) {
/*  55 */     MutableBoolean flag = new MutableBoolean();
/*  56 */     PhlegmGravestoneHelper.findPotentialPhlegmClusterPos((LivingEntity)this, source).ifPresent(pos -> {
/*     */           List<ItemStack> items = captureDrops().stream().map(ItemEntity::m_32055_).toList();
/*     */           
/*     */           if (!items.isEmpty()) {
/*     */             PhlegmGravestoneHelper.spawnForEntity((LivingEntity)this, pos, items);
/*     */             
/*     */             captureDrops(null);
/*     */             flag.setTrue();
/*     */           } 
/*     */         });
/*  66 */     if (flag.getValue().booleanValue()) {
/*  67 */       ci.cancel();
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"checkTotemDeathProtection"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V")})
/*     */   public void witherstormmod$setHasDeathProtection_checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> ci) {
/*  73 */     this.hasDeathProtection = true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"checkTotemDeathProtection"}, at = {@At("TAIL")}, cancellable = true)
/*     */   public void witherstormmod$evolveWitherStormIfDying_checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> ci) {
/*  79 */     MixinLivingEntity mixinLivingEntity = this; if (mixinLivingEntity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)mixinLivingEntity;
/*     */       
/*  81 */       if (storm.isCompletelyInvulnerable() && storm.getPhase() < 4) {
/*     */         
/*  83 */         float health = storm.m_21223_() / storm.m_21233_();
/*  84 */         if (health <= 0.1F) {
/*     */           
/*  86 */           storm.evolveToPhase(4);
/*  87 */           storm.m_21153_(storm.m_21233_());
/*  88 */           ci.setReturnValue(Boolean.valueOf(true));
/*  89 */           Entity entity = damageSource.m_7639_(); if (entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity;
/*  90 */             WitherStormModCriteriaTriggers.NEARLY_KILL_WITHER_STORM.trigger(player, storm); }
/*     */         
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   @Inject(method = {"removeAllEffects"}, at = {@At("RETURN")})
/*     */   public void witherstormmod$resetHasDeathProtection_removeAllEffects(CallbackInfoReturnable<Boolean> ci) {
/*  99 */     if (!(m_9236_()).f_46443_) {
/* 100 */       this.hasDeathProtection = false;
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"canAttack"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void witherstormmod$preventCertainMobsFromAttackingSickenedMobs_canAttack(LivingEntity entity, CallbackInfoReturnable<Boolean> ci) {
/* 106 */     if (this instanceof net.minecraft.world.entity.boss.wither.WitherBoss && (entity.m_6095_().m_204039_(WitherStormModEntityTags.SICKENED_MOBS) || entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherSickened || entity.m_6336_() == WitherStormModMobTypes.SICKENED)) {
/* 107 */       ci.setReturnValue(Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasDeathProtection(boolean flag) {
/* 113 */     this.hasDeathProtection = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasDeathProtection() {
/* 119 */     return this.hasDeathProtection;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinLivingEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */