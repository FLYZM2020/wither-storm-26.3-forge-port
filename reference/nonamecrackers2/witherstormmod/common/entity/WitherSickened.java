/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*     */ 
/*     */ public interface WitherSickened {
/*     */   public static final Predicate<Mob> CAN_WEAR_ARMOR;
/*     */   
/*     */   static {
/*  43 */     if (null.$assertionsDisabled);
/*     */     
/*  45 */     CAN_WEAR_ARMOR = (mob -> (mob instanceof SickenedZombie || mob instanceof SickenedSkeleton || mob instanceof SickenedVillager || mob instanceof SickenedPillager));
/*     */   }
/*     */   
/*     */   default int getConversionProgress() {
/*  49 */     Mob mob = witherstormmod$cast();
/*  50 */     int i = 1;
/*  51 */     if (mob.m_217043_().m_188501_() < 0.01F) {
/*     */       
/*  53 */       int j = 0;
/*  54 */       BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
/*     */       
/*  56 */       for (int k = (int)mob.m_20185_() - 4; k < (int)mob.m_20185_() + 4 && j < 14; k++) {
/*     */         
/*  58 */         for (int l = (int)mob.m_20186_() - 4; l < (int)mob.m_20186_() + 4 && j < 14; l++) {
/*     */           
/*  60 */           for (int i1 = (int)mob.m_20189_() - 4; i1 < (int)mob.m_20189_() + 4 && j < 14; i1++) {
/*     */             
/*  62 */             Block block = mob.m_9236_().m_8055_((BlockPos)pos.m_122178_(k, l, i1)).m_60734_();
/*  63 */             if (block == Blocks.f_50183_ || block instanceof net.minecraft.world.level.block.BedBlock) {
/*     */               
/*  65 */               if (mob.m_217043_().m_188501_() < 0.3F) {
/*  66 */                 i++;
/*     */               }
/*  68 */               j++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  75 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default void sickenedTick() {
/*  81 */     Mob cast = witherstormmod$cast();
/*  82 */     Data data = getData();
/*  83 */     if (!(cast.m_9236_()).f_46443_ && cast.m_6084_() && isConverting()) {
/*     */       
/*  85 */       int i = getConversionProgress();
/*  86 */       data.conversionTime -= i;
/*  87 */       if (data.getConversionTime() <= 0 && ForgeEventFactory.canLivingConvert((LivingEntity)cast, cast.m_6095_(), timer -> data.conversionTime = timer.intValue())) {
/*  88 */         cure((ServerLevel)cast.m_9236_());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   default <E extends Mob> E sickenedConvertTo(EntityType<E> type, boolean loot) {
/*  94 */     Mob cast = witherstormmod$cast();
/*  95 */     Data data = getData();
/*  96 */     if (!cast.m_6084_())
/*     */     {
/*  98 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 102 */     Mob mob1 = (Mob)type.m_20615_(cast.m_9236_());
/*     */     
/* 104 */     if (data.getOriginalData() != null) {
/* 105 */       if (!null.$assertionsDisabled && mob1 == null) throw new AssertionError(); 
/* 106 */       mob1.m_20258_(data.getOriginalData());
/*     */     } 
/* 108 */     if (!null.$assertionsDisabled && mob1 == null) throw new AssertionError(); 
/* 109 */     mob1.m_21153_(cast.m_21223_());
/* 110 */     mob1.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(WitherSicknessTracker::cure);
/* 111 */     mob1.m_21219_();
/*     */     
/* 113 */     mob1.m_20359_((Entity)cast);
/* 114 */     mob1.m_6863_(cast.m_6162_());
/* 115 */     mob1.m_21557_(cast.m_21525_());
/* 116 */     if (cast.m_8077_()) {
/*     */       
/* 118 */       mob1.m_6593_(cast.m_7770_());
/* 119 */       mob1.m_20340_(cast.m_20151_());
/*     */     } 
/*     */     
/* 122 */     if (cast.m_21532_()) {
/* 123 */       mob1.m_21530_();
/*     */     }
/* 125 */     mob1.m_20331_(cast.m_20147_());
/*     */     
/* 127 */     if (loot) {
/*     */       
/* 129 */       mob1.m_21553_(cast.m_21531_());
/*     */       
/* 131 */       for (EquipmentSlot slot : EquipmentSlot.values()) {
/*     */         
/* 133 */         ItemStack stack = cast.m_6844_(slot);
/* 134 */         if (!stack.m_41619_()) {
/*     */           
/* 136 */           mob1.m_8061_(slot, stack.m_41777_());
/* 137 */           mob1.m_21409_(slot, getSickenedEquipmentDropChance(slot));
/* 138 */           stack.m_41764_(0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     cast.m_9236_().m_7967_((Entity)mob1);
/* 144 */     if (cast.m_20159_()) {
/*     */       
/* 146 */       Entity entity = cast.m_20202_();
/* 147 */       cast.m_8127_();
/* 148 */       if (!null.$assertionsDisabled && entity == null) throw new AssertionError(); 
/* 149 */       mob1.m_7998_(entity, true);
/*     */     } 
/*     */     
/* 152 */     cast.m_146870_();
/* 153 */     return (E)mob1;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default InteractionResult sickenedMobInteract(Player player, InteractionHand hand) {
/* 159 */     Mob cast = witherstormmod$cast();
/* 160 */     ItemStack stack = player.m_21120_(hand);
/* 161 */     if (stack.m_41720_() == WitherStormModItems.GOLDEN_APPLE_STEW.get()) {
/*     */       
/* 163 */       if (!isConverting() && getOriginalType() != null) {
/*     */         
/* 165 */         if (!(player.m_150110_()).f_35937_) {
/* 166 */           stack.m_41774_(1);
/*     */         }
/* 168 */         if (!(cast.m_9236_()).f_46443_) {
/* 169 */           startConverting(player.m_20148_(), cast.m_217043_().m_188503_(2401) + 3600);
/*     */         }
/* 171 */         return InteractionResult.SUCCESS;
/*     */       } 
/*     */ 
/*     */       
/* 175 */       return InteractionResult.CONSUME;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 180 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean sickenedRemoveWhenFarAway(double dist) {
/* 186 */     return !isConverting();
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean sickenedAddEffect(MobEffectInstance effect, @Nullable Entity entity) {
/* 191 */     return (effect.m_19544_() != WitherStormModEffects.WITHER_SICKNESS.get() && effect.m_19544_() != MobEffects.f_19615_);
/*     */   }
/*     */ 
/*     */   
/*     */   default float sickenedGetVoicePitch() {
/* 196 */     return sickenedGetVoicePitch(1.35F, 0.85F);
/*     */   }
/*     */ 
/*     */   
/*     */   default float sickenedGetVoicePitch(float babyBasePitch, float adultBasePitch) {
/* 201 */     Mob cast = witherstormmod$cast();
/* 202 */     return cast.m_6162_() ? ((cast.m_217043_().m_188501_() - cast.m_217043_().m_188501_()) * 0.2F + babyBasePitch) : ((cast.m_217043_().m_188501_() - cast.m_217043_().m_188501_()) * 0.2F + adultBasePitch);
/*     */   }
/*     */ 
/*     */   
/*     */   default void sickenedSave(CompoundTag compound) {
/* 207 */     Data data = getData();
/* 208 */     String id = data.encodeOriginalId();
/* 209 */     if (id != null)
/* 210 */       compound.m_128359_("OriginalType", id); 
/* 211 */     if (data.getOriginalData() != null)
/* 212 */       compound.m_128365_("OriginalData", (Tag)data.getOriginalData()); 
/* 213 */     compound.m_128405_("ConversionTime", isConverting() ? data.getConversionTime() : -1);
/* 214 */     if (data.getConversionStarter() != null) {
/* 215 */       compound.m_128362_("ConversionPlayer", data.getConversionStarter());
/*     */     }
/*     */   }
/*     */   
/*     */   default void sickenedRead(CompoundTag compound) {
/* 220 */     Data data = getData();
/* 221 */     if (compound.m_128441_("OriginalType")) {
/*     */       
/* 223 */       ResourceLocation location = ResourceLocation.m_135820_(compound.m_128461_("OriginalType"));
/* 224 */       EntityType<?> type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(location);
/* 225 */       data.setOriginal(type, compound.m_128441_("OriginalData") ? compound.m_128469_("OriginalData") : null);
/*     */     } 
/* 227 */     if (compound.m_128441_("ConversionTime") && compound.m_128451_("ConversionTime") > -1) {
/* 228 */       startConverting(compound.m_128403_("ConversionPlayer") ? compound.m_128342_("ConversionPlayer") : null, compound.m_128451_("ConversionTime"));
/*     */     }
/*     */   }
/*     */   
/*     */   default boolean cure(ServerLevel world) {
/* 233 */     Mob cast = witherstormmod$cast();
/* 234 */     Data data = getData();
/* 235 */     EntityType<?> original = getOriginalType();
/* 236 */     if (original != null) {
/*     */ 
/*     */       
/* 239 */       Mob entity = cast.m_21406_(original, false);
/*     */       
/* 241 */       if (entity != null) {
/*     */         
/* 243 */         for (EquipmentSlot slot : EquipmentSlot.values()) {
/*     */           
/* 245 */           ItemStack stack = cast.m_6844_(slot);
/* 246 */           if (!stack.m_41619_())
/*     */           {
/* 248 */             if (EnchantmentHelper.m_44920_(stack)) {
/*     */               
/* 250 */               entity.m_141942_(slot.m_20749_() + 300).m_142104_(stack);
/*     */             }
/*     */             else {
/*     */               
/* 254 */               double d0 = getSickenedEquipmentDropChance(slot);
/* 255 */               if (d0 > 1.0D) {
/* 256 */                 cast.m_19983_(stack);
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/* 261 */         WorldTainting.copyExtraData(witherstormmod$cast(), entity);
/* 262 */         doExtraHandling(entity);
/*     */         
/* 264 */         if (data.conversionStarter != null) {
/*     */           
/* 266 */           Player player = world.m_46003_(data.conversionStarter);
/* 267 */           if (player instanceof ServerPlayer) {
/* 268 */             WitherStormModCriteriaTriggers.CURED_SICKENED_MOB.trigger((ServerPlayer)player, cast, entity);
/*     */           }
/*     */         } 
/* 271 */         entity.m_7292_(new MobEffectInstance(MobEffects.f_19604_, 200, 0));
/* 272 */         cast.m_216990_((SoundEvent)WitherStormModSoundEvents.MOB_CURED.get());
/*     */         
/* 274 */         ForgeEventFactory.onLivingConvert((LivingEntity)cast, (LivingEntity)entity);
/*     */         
/* 276 */         return true;
/*     */       } 
/*     */     } 
/* 279 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   default void startConverting(@Nullable UUID uuid, int duration) {
/* 284 */     Mob cast = witherstormmod$cast();
/* 285 */     Data data = getData();
/* 286 */     if (getOriginalType() != null) {
/*     */       
/* 288 */       data.conversionStarter = uuid;
/* 289 */       data.conversionTime = duration;
/* 290 */       setConverting(true);
/* 291 */       cast.m_7292_(new MobEffectInstance(MobEffects.f_19600_, duration, Math.min(cast.m_9236_().m_46791_().m_19028_() - 1, 0)));
/* 292 */       playCureSound();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   default void playCureSound() {
/* 298 */     Mob cast = witherstormmod$cast();
/* 299 */     cast.m_5496_(SoundEvents.f_12644_, 1.0F + cast.m_217043_().m_188501_(), cast.m_217043_().m_188501_() * 0.7F + 0.3F);
/*     */   }
/*     */ 
/*     */   
/*     */   default Mob witherstormmod$cast() {
/* 304 */     return (Mob)this;
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean sickenedCanAttack(LivingEntity entity) {
/* 309 */     return (!(entity instanceof WitherSickened) && !(entity instanceof TentacleEntity) && !(entity instanceof WitherStormEntity) && !(entity instanceof WitheredSymbiontEntity) && !(entity instanceof WitherStormHeadEntity));
/*     */   }
/*     */ 
/*     */   
/*     */   default void addWitherToTarget(Entity entity) {
/* 314 */     if (witherstormmod$cast().m_21205_().m_41619_() && entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity;
/*     */       
/* 316 */       float f = witherstormmod$cast().m_9236_().m_6436_(witherstormmod$cast().m_20183_()).m_19056_();
/* 317 */       living.m_147207_(new MobEffectInstance(MobEffects.f_19615_, 120 * (int)f), (Entity)witherstormmod$cast()); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   default void addPotentWitherToTarget(Entity entity) {
/* 323 */     if (witherstormmod$cast().m_21205_().m_41619_() && entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity;
/*     */       
/* 325 */       float f = witherstormmod$cast().m_9236_().m_6436_(witherstormmod$cast().m_20183_()).m_19056_();
/* 326 */       living.m_147207_(new MobEffectInstance(MobEffects.f_19615_, 75 * (int)f, 1), (Entity)witherstormmod$cast()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void doExtraHandling(Mob mob) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void convertFrom(Mob mob) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getInfectedHealAmount() {
/* 344 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean sickenedInfect(LivingEntity entity) {
/* 349 */     if (entity instanceof Mob) { Mob mob = (Mob)entity; if (WorldTainting.getInstance().convertMob(mob, false)) {
/*     */         
/* 351 */         int healAmount = getInfectedHealAmount();
/* 352 */         if (healAmount > 0)
/* 353 */           witherstormmod$cast().m_5634_(healAmount); 
/* 354 */         return false;
/*     */       }  }
/*     */ 
/*     */     
/* 358 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean sickenedCanBeHurt(DamageSource source, float amount) {
/* 364 */     if (source.m_7640_() instanceof net.minecraft.world.entity.projectile.AbstractHurtingProjectile || source.m_7640_() instanceof net.minecraft.world.entity.projectile.AbstractArrow) {
/*     */       
/* 366 */       Projectile projectile = (Projectile)source.m_7640_();
/* 367 */       if (projectile.m_19749_() instanceof WitherSickened)
/* 368 */         return false; 
/*     */     } 
/* 370 */     return true;
/*     */   } Data getData();
/*     */   boolean isConverting();
/*     */   @Nullable
/*     */   default EntityType<?> getOriginalType() {
/* 375 */     EntityType<?> storedOriginal = getData().getStoredOriginalType();
/* 376 */     return (storedOriginal == null) ? WorldTainting.getInstance().getOriginalTypeFromConvertedType(witherstormmod$cast().m_6095_()) : storedOriginal;
/*     */   }
/*     */   
/*     */   void setConverting(boolean paramBoolean);
/*     */   
/*     */   float getSickenedEquipmentDropChance(EquipmentSlot paramEquipmentSlot);
/*     */   
/*     */   public static class Data {
/*     */     protected int conversionTime;
/*     */     protected UUID conversionStarter;
/*     */     
/*     */     @Nullable
/*     */     public final String encodeOriginalId() {
/* 389 */       if (this.original != null) {
/*     */         
/* 391 */         EntityType<?> type = this.original;
/* 392 */         ResourceLocation location = ForgeRegistries.ENTITY_TYPES.getKey(type);
/* 393 */         return (type.m_20584_() && location != null) ? location.toString() : null;
/*     */       } 
/*     */ 
/*     */       
/* 397 */       return null;
/*     */     }
/*     */     @Nullable
/*     */     protected EntityType<?> original; @Nullable
/*     */     protected CompoundTag originalData;
/*     */     public UUID getConversionStarter() {
/* 403 */       return this.conversionStarter;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getConversionTime() {
/* 408 */       return this.conversionTime;
/*     */     }
/*     */ 
/*     */     
/*     */     public EntityType<?> getStoredOriginalType() {
/* 413 */       return this.original;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     protected CompoundTag getOriginalData() {
/* 418 */       return this.originalData;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setOriginal(EntityType<?> originalType, CompoundTag originalData) {
/* 423 */       this.original = originalType;
/* 424 */       this.originalData = originalData;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\WitherSickened.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */