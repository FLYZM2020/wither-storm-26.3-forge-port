/*     */ package nonamecrackers2.witherstormmod.common.capability;
/*     */ 
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateEffectInstanceMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateWitherSicknessTrackerMessage;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
/*     */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*     */ 
/*     */ public class WitherSicknessTracker extends EntityCapability<WitherSicknessTracker, LivingEntity> {
/*  18 */   private int requiredProximityTicks = Math.max(240, ((Integer)WitherStormModConfig.SERVER.requiredProximitySeconds.get()).intValue() * 20);
/*  19 */   private int applicationDelay = Math.max(240, ((Integer)WitherStormModConfig.SERVER.applicationDelay.get()).intValue() * 20);
/*  20 */   private int cureDelay = Math.max(240, ((Integer)WitherStormModConfig.SERVER.cureDelay.get()).intValue() * 20);
/*  21 */   private int requiredContacts = Math.max(1, ((Integer)WitherStormModConfig.SERVER.requiredContacts.get()).intValue());
/*  22 */   private int multiplierDecreaseTime = 7200;
/*  23 */   private int contactsDecreaseTime = 9600;
/*     */   
/*     */   private int proximityTicksModifier;
/*     */   
/*     */   private int applicationDelayModifier;
/*     */   private int cureDelayModifier;
/*     */   private int proximityTicks;
/*     */   private int prevProximityTicks;
/*     */   private int delayTicks;
/*     */   private int prevDelayTicks;
/*     */   private int contacts;
/*     */   private int totalInfections;
/*     */   private int totalCures;
/*     */   private int amplifierDecreaseTicks;
/*     */   private int amplifier;
/*     */   private int contactsDecreaseTicks;
/*     */   private int cureDelayTicks;
/*     */   private int prevCureDelayTicks;
/*     */   private boolean isInfected;
/*     */   private boolean isBeingCured;
/*     */   private boolean isNearStorm;
/*     */   private boolean shouldUpdate;
/*     */   private boolean isActuallyImmune = true;
/*     */   
/*     */   public WitherSicknessTracker(LivingEntity entity) {
/*  48 */     super(entity);
/*  49 */     if (!(entity.m_9236_()).f_46443_) {
/*  50 */       randomizeModifiers();
/*     */     }
/*     */   }
/*     */   
/*     */   public void randomizeModifiers() {
/*  55 */     if (isLowImmunity()) {
/*     */       
/*  57 */       this.requiredProximityTicks = Math.max(240, ((Integer)WitherStormModConfig.SERVER.lowImmuneRequiredProximitySeconds.get()).intValue() * 20);
/*  58 */       this.applicationDelay = Math.max(240, ((Integer)WitherStormModConfig.SERVER.lowImmuneApplicationDelay.get()).intValue() * 20);
/*  59 */       this.cureDelay = Math.max(240, ((Integer)WitherStormModConfig.SERVER.lowImmuneCureDelay.get()).intValue() * 20);
/*  60 */       this.proximityTicksModifier = -this.entity.m_217043_().m_188503_(Math.max(240, ((Integer)WitherStormModConfig.SERVER.lowImmuneProximityModifierMax.get()).intValue() * 20));
/*  61 */       this.applicationDelayModifier = -this.entity.m_217043_().m_188503_(Math.max(240, ((Integer)WitherStormModConfig.SERVER.lowImmuneApplicationModifierMax.get()).intValue() * 20));
/*  62 */       this.cureDelayModifier = -this.entity.m_217043_().m_188503_(Math.max(240, ((Integer)WitherStormModConfig.SERVER.lowImmuneCureDelayModifierMax.get()).intValue() * 20));
/*     */     }
/*     */     else {
/*     */       
/*  66 */       this.proximityTicksModifier = this.entity.m_217043_().m_188503_(Math.max(240, ((Integer)WitherStormModConfig.SERVER.proximitySecondsModifierMax.get()).intValue() * 20));
/*  67 */       this.applicationDelayModifier = this.entity.m_217043_().m_188503_(Math.max(240, ((Integer)WitherStormModConfig.SERVER.applicationDelayModifierMax.get()).intValue() * 20));
/*  68 */       this.cureDelayModifier = this.entity.m_217043_().m_188503_(Math.max(240, ((Integer)WitherStormModConfig.SERVER.cureDelayModifierMax.get()).intValue() * 20));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyFrom(WitherSicknessTracker tracker) {
/*  96 */     this.requiredProximityTicks = tracker.requiredProximityTicks;
/*  97 */     this.applicationDelay = tracker.applicationDelay;
/*  98 */     this.requiredContacts = tracker.requiredContacts;
/*  99 */     this.multiplierDecreaseTime = tracker.multiplierDecreaseTime;
/* 100 */     this.contactsDecreaseTime = tracker.contactsDecreaseTime;
/* 101 */     this.proximityTicksModifier = tracker.proximityTicksModifier;
/* 102 */     this.applicationDelayModifier = tracker.applicationDelayModifier;
/* 103 */     this.proximityTicks = tracker.proximityTicks;
/* 104 */     this.prevProximityTicks = tracker.prevProximityTicks;
/* 105 */     this.delayTicks = tracker.delayTicks;
/* 106 */     this.prevDelayTicks = tracker.prevDelayTicks;
/* 107 */     this.contacts = tracker.contacts;
/* 108 */     this.totalInfections = tracker.totalInfections;
/* 109 */     this.amplifierDecreaseTicks = tracker.amplifierDecreaseTicks;
/* 110 */     this.amplifier = tracker.amplifier;
/* 111 */     this.contactsDecreaseTicks = tracker.contactsDecreaseTicks;
/* 112 */     this.isInfected = tracker.isInfected;
/* 113 */     this.isNearStorm = tracker.isNearStorm;
/* 114 */     this.shouldUpdate = tracker.shouldUpdate;
/* 115 */     this.cureDelay = tracker.cureDelay;
/* 116 */     this.cureDelayModifier = tracker.cureDelayModifier;
/* 117 */     this.cureDelayTicks = tracker.cureDelayTicks;
/* 118 */     this.isBeingCured = tracker.isBeingCured;
/* 119 */     this.prevCureDelayTicks = tracker.prevCureDelayTicks;
/* 120 */     this.totalCures = tracker.totalCures;
/* 121 */     this.isActuallyImmune = tracker.isActuallyImmune;
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyFromMessage(UpdateWitherSicknessTrackerMessage message) {
/* 126 */     this.requiredProximityTicks = message.getRequiredProximityTicks();
/* 127 */     this.applicationDelay = message.getApplicationDelay();
/* 128 */     this.cureDelay = message.getCureDelay();
/* 129 */     this.proximityTicksModifier = message.getProximityTicksModifier();
/* 130 */     this.applicationDelayModifier = message.getApplicationDelayModifier();
/* 131 */     this.proximityTicks = message.getProximityTicks();
/* 132 */     this.delayTicks = message.getDelayTicks();
/* 133 */     this.contacts = message.getContacts();
/* 134 */     this.totalInfections = message.getTotalInfections();
/* 135 */     this.amplifierDecreaseTicks = message.getMultiplierDecreaseTicks();
/* 136 */     this.amplifier = message.getMultiplier();
/* 137 */     this.contactsDecreaseTicks = message.getContactsDecreaseTicks();
/* 138 */     this.isInfected = message.isInfected();
/* 139 */     this.isNearStorm = message.isNearStorm();
/* 140 */     this.cureDelayModifier = message.getCureDelayModifier();
/* 141 */     this.cureDelayTicks = message.getCureDelayTicks();
/* 142 */     this.isBeingCured = message.isBeingCured();
/* 143 */     this.totalCures = message.getTotalCures();
/* 144 */     this.isActuallyImmune = message.isActuallyImmune();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag write() {
/* 150 */     CompoundTag compound = new CompoundTag();
/* 151 */     compound.m_128405_("ProximityTicks", getProximityTicks());
/* 152 */     compound.m_128405_("DelayTicks", getDelayTicks());
/* 153 */     compound.m_128405_("Contacts", getContacts());
/* 154 */     compound.m_128405_("MultiplierDecreaseTicks", getAmplifierDecreaseTicks());
/* 155 */     compound.m_128405_("Multiplier", getBaseMultiplier());
/* 156 */     compound.m_128405_("TotalInfections", getTotalInfections());
/* 157 */     compound.m_128379_("IsInfected", isInfected());
/* 158 */     compound.m_128405_("ProximityTicksModifier", getRequiredProximityTicksModifier());
/* 159 */     compound.m_128405_("ApplicationDelayModifier", getApplicationDelayModifier());
/* 160 */     compound.m_128405_("CureDelayModifier", getCureDelayModifier());
/* 161 */     compound.m_128405_("ContactsDecreaseTicks", getContactsDecreaseTicks());
/* 162 */     compound.m_128405_("CureDelayTicks", getCureDelayTicks());
/* 163 */     compound.m_128379_("IsBeingCured", isBeingCured());
/* 164 */     compound.m_128405_("TotalCures", getTotalCures());
/* 165 */     return compound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundTag compound) {
/* 171 */     this.proximityTicks = compound.m_128451_("ProximityTicks");
/* 172 */     this.delayTicks = compound.m_128451_("DelayTicks");
/* 173 */     this.contacts = compound.m_128451_("Contacts");
/* 174 */     this.amplifierDecreaseTicks = compound.m_128451_("AmplifierDecreaseTicks");
/* 175 */     this.amplifier = compound.m_128451_("Amplifier");
/* 176 */     this.totalInfections = compound.m_128451_("TotalInfections");
/* 177 */     this.isInfected = compound.m_128471_("IsInfected");
/* 178 */     this.proximityTicksModifier = compound.m_128451_("ProximityTicksModifier");
/* 179 */     this.applicationDelayModifier = compound.m_128451_("ApplicationDelayModifier");
/* 180 */     this.cureDelayModifier = compound.m_128451_("CureDelayModifier");
/* 181 */     this.contactsDecreaseTicks = compound.m_128451_("ContactsDecreaseTicks");
/* 182 */     this.cureDelayTicks = compound.m_128451_("CureDelayTicks");
/* 183 */     this.isBeingCured = compound.m_128471_("IsBeingCured");
/* 184 */     this.totalCures = compound.m_128451_("TotalCures");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 190 */     if (!(this.entity.m_9236_()).f_46443_) {
/*     */       
/* 192 */       boolean flag = this.entity.m_6095_().m_204039_(WitherStormModEntityTags.WITHER_SICKNESS_IMMUNE);
/* 193 */       if (this.isActuallyImmune != flag) {
/*     */         
/* 195 */         this.shouldUpdate = true;
/* 196 */         this.isActuallyImmune = flag;
/*     */       } 
/*     */       
/* 199 */       if (this.entity.f_19797_ % 120 == 0) {
/* 200 */         this.shouldUpdate = true;
/*     */       }
/* 202 */       if (this.shouldUpdate) {
/*     */         
/* 204 */         UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage((Entity)this.entity);
/* 205 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this.entity), message);
/* 206 */         this.shouldUpdate = false;
/*     */       } 
/*     */     } 
/* 209 */     if (!isActuallyImmune()) {
/*     */       
/* 211 */       this.prevProximityTicks = this.proximityTicks;
/* 212 */       this.prevDelayTicks = this.delayTicks;
/* 213 */       this.prevCureDelayTicks = this.cureDelayTicks;
/*     */       
/* 215 */       if (isNearStorm()) {
/*     */         
/* 217 */         if (this.proximityTicks < getRequiredProximityTicks()) {
/* 218 */           this.proximityTicks++;
/*     */         }
/*     */       } else {
/*     */         
/* 222 */         if (this.proximityTicks > 0) {
/* 223 */           this.proximityTicks--;
/*     */         }
/* 225 */         if (this.amplifierDecreaseTicks < getAmplifierDecreaseTime() && !this.isInfected) {
/*     */           
/* 227 */           this.amplifierDecreaseTicks++;
/*     */         }
/*     */         else {
/*     */           
/* 231 */           this.amplifierDecreaseTicks = 0;
/* 232 */           if (this.amplifier > 0) {
/* 233 */             this.amplifier--;
/*     */           }
/*     */         } 
/* 236 */         if (this.contactsDecreaseTicks < getContactsDecreaseTime()) {
/*     */           
/* 238 */           this.contactsDecreaseTicks++;
/*     */         }
/*     */         else {
/*     */           
/* 242 */           this.contactsDecreaseTicks = 0;
/* 243 */           if (this.contacts > 0) {
/* 244 */             this.contacts--;
/*     */           }
/*     */         } 
/*     */       } 
/* 248 */       if (this.prevProximityTicks != this.proximityTicks && this.proximityTicks >= getRequiredProximityTicks()) {
/* 249 */         beginInfection();
/*     */       }
/* 251 */       if (isInfected()) {
/*     */         
/* 253 */         if (this.delayTicks < getApplicationDelay() && !isBeingCured()) {
/* 254 */           this.delayTicks++;
/*     */         }
/* 256 */         if (this.delayTicks >= getApplicationDelay())
/*     */         {
/*     */           
/* 259 */           if (!(this.entity.m_9236_()).f_46443_) {
/*     */             
/* 261 */             if (this.delayTicks != this.prevDelayTicks) {
/* 262 */               infect();
/*     */             }
/* 264 */             MobEffectInstance effect = this.entity.m_21124_((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get());
/* 265 */             if (effect != null && effect.m_19557_() < 7200) {
/*     */               
/* 267 */               MobEffectInstance newEffect = new MobEffectInstance((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get(), 12000, effect.m_19564_(), false, false, true);
/* 268 */               effect.m_19558_(newEffect);
/* 269 */               UpdateEffectInstanceMessage message = new UpdateEffectInstanceMessage(this.entity.m_19879_(), newEffect, true);
/* 270 */               WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this.entity), message);
/*     */             } 
/*     */           } 
/*     */         }
/*     */         
/* 275 */         if (!(this.entity.m_9236_()).f_46443_)
/*     */         {
/* 277 */           if (!this.entity.m_21023_((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get()))
/*     */           {
/* 279 */             if (this.delayTicks >= getApplicationDelay()) {
/*     */               
/* 281 */               setInfected(false);
/* 282 */               setProximityTicks(0);
/*     */             } 
/*     */           }
/*     */         }
/*     */         
/* 287 */         if (isBeingCured()) {
/*     */           
/* 289 */           if (this.cureDelayTicks < getCureDelay()) {
/* 290 */             this.cureDelayTicks++;
/*     */           }
/* 292 */           if (this.cureDelayTicks >= getCureDelay())
/*     */           {
/* 294 */             if (this.cureDelayTicks != this.prevCureDelayTicks) {
/* 295 */               cure();
/*     */             
/*     */             }
/*     */           }
/*     */         }
/* 300 */         else if (this.cureDelayTicks >= getCureDelay()) {
/* 301 */           this.cureDelayTicks = 0;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 306 */         this.delayTicks = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredProximityTicks() {
/* 339 */     return Math.max(1200, this.requiredProximityTicks + this.proximityTicksModifier);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRequiredContacts() {
/* 344 */     return this.requiredContacts;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getApplicationDelay() {
/* 349 */     return Math.max(1200, this.applicationDelay + this.applicationDelayModifier);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProximityTicks() {
/* 354 */     return this.proximityTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDelayTicks() {
/* 359 */     return this.delayTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContacts() {
/* 364 */     return this.contacts;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTotalInfections() {
/* 369 */     return this.totalInfections;
/*     */   }
/*     */ 
/*     */   
/*     */   public void beginInfection() {
/* 374 */     if (!isActuallyImmune()) {
/*     */       
/* 376 */       this.shouldUpdate = true;
/* 377 */       this.isInfected = true;
/* 378 */       this.isBeingCured = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInfected(boolean infected) {
/* 384 */     if (!isActuallyImmune()) {
/*     */       
/* 386 */       if (infected != this.isInfected)
/* 387 */         this.shouldUpdate = true; 
/* 388 */       this.isInfected = infected;
/* 389 */       if (infected) {
/* 390 */         this.isBeingCured = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void infect() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual isBeingCured : ()Z
/*     */     //   4: ifne -> 133
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual isActuallyImmune : ()Z
/*     */     //   11: ifne -> 133
/*     */     //   14: aload_0
/*     */     //   15: getfield entity : Lnet/minecraft/world/entity/Entity;
/*     */     //   18: checkcast net/minecraft/world/entity/LivingEntity
/*     */     //   21: invokevirtual m_5833_ : ()Z
/*     */     //   24: ifne -> 133
/*     */     //   27: aload_0
/*     */     //   28: getfield entity : Lnet/minecraft/world/entity/Entity;
/*     */     //   31: astore_2
/*     */     //   32: aload_2
/*     */     //   33: instanceof net/minecraft/world/entity/player/Player
/*     */     //   36: ifeq -> 51
/*     */     //   39: aload_2
/*     */     //   40: checkcast net/minecraft/world/entity/player/Player
/*     */     //   43: astore_1
/*     */     //   44: aload_1
/*     */     //   45: invokevirtual m_7500_ : ()Z
/*     */     //   48: ifne -> 133
/*     */     //   51: aload_0
/*     */     //   52: iconst_1
/*     */     //   53: putfield shouldUpdate : Z
/*     */     //   56: aload_0
/*     */     //   57: dup
/*     */     //   58: getfield totalInfections : I
/*     */     //   61: iconst_1
/*     */     //   62: iadd
/*     */     //   63: putfield totalInfections : I
/*     */     //   66: new net/minecraft/world/effect/MobEffectInstance
/*     */     //   69: dup
/*     */     //   70: getstatic nonamecrackers2/witherstormmod/common/init/WitherStormModEffects.WITHER_SICKNESS : Lnet/minecraftforge/registries/RegistryObject;
/*     */     //   73: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   76: checkcast net/minecraft/world/effect/MobEffect
/*     */     //   79: sipush #12000
/*     */     //   82: aload_0
/*     */     //   83: invokevirtual getAmplifier : ()I
/*     */     //   86: iconst_0
/*     */     //   87: iconst_0
/*     */     //   88: iconst_1
/*     */     //   89: invokespecial <init> : (Lnet/minecraft/world/effect/MobEffect;IIZZZ)V
/*     */     //   92: astore_2
/*     */     //   93: aload_0
/*     */     //   94: getfield entity : Lnet/minecraft/world/entity/Entity;
/*     */     //   97: checkcast net/minecraft/world/entity/LivingEntity
/*     */     //   100: aload_2
/*     */     //   101: invokevirtual m_7292_ : (Lnet/minecraft/world/effect/MobEffectInstance;)Z
/*     */     //   104: pop
/*     */     //   105: getstatic nonamecrackers2/witherstormmod/common/config/WitherStormModConfig.SERVER : Lnonamecrackers2/witherstormmod/common/config/WitherStormModConfig$ServerConfig;
/*     */     //   108: getfield increaseAmplifier : Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;
/*     */     //   111: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   114: checkcast java/lang/Boolean
/*     */     //   117: invokevirtual booleanValue : ()Z
/*     */     //   120: ifeq -> 133
/*     */     //   123: aload_0
/*     */     //   124: dup
/*     */     //   125: getfield amplifier : I
/*     */     //   128: iconst_1
/*     */     //   129: iadd
/*     */     //   130: putfield amplifier : I
/*     */     //   133: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #396	-> 0
/*     */     //   #398	-> 51
/*     */     //   #399	-> 56
/*     */     //   #400	-> 66
/*     */     //   #401	-> 93
/*     */     //   #402	-> 105
/*     */     //   #403	-> 123
/*     */     //   #405	-> 133
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   44	7	1	player	Lnet/minecraft/world/entity/player/Player;
/*     */     //   93	40	2	effect	Lnet/minecraft/world/effect/MobEffectInstance;
/*     */     //   0	134	0	this	Lnonamecrackers2/witherstormmod/common/capability/WitherSicknessTracker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAmplifierDecreaseTime() {
/* 409 */     return this.multiplierDecreaseTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAmplifierDecreaseTicks() {
/* 414 */     return this.amplifierDecreaseTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAmplifier() {
/* 419 */     return isConvertable() ? (this.amplifier + 5) : this.amplifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBaseMultiplier() {
/* 424 */     return this.amplifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInfected() {
/* 429 */     return this.isInfected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProximityTicks(int amount) {
/* 434 */     if (!isActuallyImmune()) {
/*     */       
/* 436 */       if (amount != this.proximityTicks)
/* 437 */         this.shouldUpdate = true; 
/* 438 */       this.proximityTicks = amount;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDelayTicks(int amount) {
/* 444 */     if (!isActuallyImmune()) {
/*     */       
/* 446 */       if (amount != this.delayTicks)
/* 447 */         this.shouldUpdate = true; 
/* 448 */       this.delayTicks = amount;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRequiredProximityTicksModifier() {
/* 454 */     return this.proximityTicksModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getApplicationDelayModifier() {
/* 459 */     return this.applicationDelayModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContactsDecreaseTime() {
/* 464 */     return this.contactsDecreaseTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContactsDecreaseTicks() {
/* 469 */     return this.contactsDecreaseTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void countContact() {
/* 474 */     if (!isActuallyImmune()) {
/*     */       
/* 476 */       this.shouldUpdate = true;
/* 477 */       this.contacts++;
/* 478 */       if (this.contacts > this.requiredContacts) {
/* 479 */         beginInfection();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setContacts(int amount) {
/* 485 */     if (!isActuallyImmune()) {
/*     */       
/* 487 */       if (amount != this.contacts)
/* 488 */         this.shouldUpdate = true; 
/* 489 */       this.contacts = amount;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContactDecreaseTicks(int amount) {
/* 495 */     if (!isActuallyImmune()) {
/*     */       
/* 497 */       if (amount != this.contactsDecreaseTicks)
/* 498 */         this.shouldUpdate = true; 
/* 499 */       this.contactsDecreaseTicks = amount;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNearStorm() {
/* 505 */     return this.isNearStorm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNearStorm(boolean near) {
/* 510 */     if (!isActuallyImmune()) {
/*     */       
/* 512 */       if (near != this.isNearStorm)
/* 513 */         this.shouldUpdate = true; 
/* 514 */       this.isNearStorm = near;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCureDelay() {
/* 520 */     return Math.max(1200, this.cureDelay + this.cureDelayModifier);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCureDelayModifier() {
/* 525 */     return this.cureDelayModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCureDelayTicks() {
/* 530 */     return this.cureDelayTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBeingCured() {
/* 535 */     return this.isBeingCured;
/*     */   }
/*     */ 
/*     */   
/*     */   public void cure() {
/* 540 */     if (isInfected() && !isActuallyImmune()) {
/*     */       
/* 542 */       this.shouldUpdate = true;
/* 543 */       this.entity.m_21195_((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get());
/* 544 */       setInfected(false);
/* 545 */       setProximityTicks(0);
/* 546 */       setCureTicks(0);
/* 547 */       countCure();
/* 548 */       setBeingCured(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void beginCure() {
/* 554 */     if (!isActuallyImmune()) {
/*     */       
/* 556 */       this.shouldUpdate = true;
/* 557 */       this.isBeingCured = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalCures() {
/* 564 */     return this.totalCures;
/*     */   }
/*     */ 
/*     */   
/*     */   public void countCure() {
/* 569 */     if (!isActuallyImmune()) {
/*     */       
/* 571 */       this.shouldUpdate = true;
/* 572 */       this.totalCures++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBeingCured(boolean cured) {
/* 578 */     if (!isActuallyImmune()) {
/*     */       
/* 580 */       if (cured != this.isBeingCured)
/* 581 */         this.shouldUpdate = true; 
/* 582 */       this.isBeingCured = cured;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCureTicks(int amount) {
/* 588 */     if (!isActuallyImmune()) {
/*     */       
/* 590 */       if (amount != this.cureDelayTicks)
/* 591 */         this.shouldUpdate = true; 
/* 592 */       this.cureDelayTicks = amount;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLowImmunity() {
/* 598 */     return !this.entity.m_6095_().m_204039_(WitherStormModEntityTags.HIGH_IMMUNITY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConvertable() {
/* 603 */     return WorldTainting.getInstance().canConvertMob((Entity)this.entity, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActuallyImmune() {
/* 608 */     return this.isActuallyImmune;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRawRequiredProximityTicks() {
/* 613 */     return this.requiredProximityTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRawApplicationDelay() {
/* 618 */     return this.applicationDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRawCureDelay() {
/* 623 */     return this.cureDelay;
/*     */   }
/*     */   
/*     */   public WitherSicknessTracker() {}
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\WitherSicknessTracker.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */