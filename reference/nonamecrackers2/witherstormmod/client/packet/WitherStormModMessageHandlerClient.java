/*     */ package nonamecrackers2.witherstormmod.client.packet;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
/*     */ import net.minecraft.network.protocol.game.VecDeltaCodec;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeMap;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.entity.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.client.audio.WitherStormSoundLoop;
/*     */ import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.PlayerCameraShaker;
/*     */ import nonamecrackers2.witherstormmod.client.capability.PlayerScreenBlinder;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormLoopingSoundManager;
/*     */ import nonamecrackers2.witherstormmod.client.gui.menu.SuperBeaconScreen;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
/*     */ import nonamecrackers2.witherstormmod.common.packet.BlindScreenMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.CreateDebrisMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.CreateLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.DistantRendererMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.EntitySyncableDataMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.FormidibombExplosionMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.GlobalSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.NotifyHeadInjuryMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.OnHeadAttackedMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.PlayAdditionalLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveAdditionalLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveDistantSuperBeaconMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveStormFromDistantRendererMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.StormAttributesMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.StormMetadataMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.StormSoundPositionMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.StormTeleportMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.SuperBeaconValidEffectsMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateDamagingProjectileMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateDistantSuperBeaconMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateEffectInstanceMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdatePlayDeadManagerMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormHeadLookMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormPositionMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateStormVelocityMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateWitherSicknessTrackerMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.WitherStormToDistantRendererMessage;
/*     */ import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class WitherStormModMessageHandlerClient {
/*  72 */   public static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   public static void processGlobalSoundMessage(GlobalSoundMessage message) {
/*  76 */     Minecraft mc = Minecraft.m_91087_();
/*  77 */     LocalPlayer player = mc.f_91074_;
/*  78 */     if (player != null) {
/*  79 */       player.m_6330_(message.getSoundEvent(), SoundSource.HOSTILE, message.getVolume(), message.getPitch());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void processPlayerMotionMessage(PlayerMotionMessage message) {
/*  84 */     Minecraft mc = Minecraft.m_91087_();
/*  85 */     Entity vehicle = mc.f_91074_.m_20202_();
/*  86 */     if (mc.f_91074_.m_20159_()) {
/*  87 */       vehicle.m_20256_(message.getMotion());
/*     */     } else {
/*  89 */       mc.f_91074_.m_20256_(message.getMotion());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void processWitherStormToDistantRendererMessage(WitherStormToDistantRendererMessage message) {
/*  94 */     Minecraft mc = Minecraft.m_91087_();
/*  95 */     ClientLevel world = mc.f_91073_;
/*  96 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/*  97 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = (WitherStormEntity)((EntityType)ForgeRegistries.ENTITY_TYPES.getValue(message.getType())).m_20615_((Level)mc.f_91073_);
/*     */           double x = (message.getPos()).f_82479_;
/*     */           double y = (message.getPos()).f_82480_;
/*     */           double z = (message.getPos()).f_82481_;
/*     */           float yRot = (message.getYRot() * 360) / 256.0F;
/*     */           float xRot = (message.getXRot() * 360) / 256.0F;
/*     */           entity.m_217006_((message.getPos()).f_82479_, (message.getPos()).f_82480_, (message.getPos()).f_82481_);
/*     */           entity.f_20883_ = (message.getHeadYRot() * 360) / 256.0F;
/*     */           entity.f_20885_ = (message.getHeadYRot() * 360) / 256.0F;
/*     */           entity.getHeadManager().updateHeadsFromPacked(message.getRots());
/*     */           entity.m_20234_(message.getId());
/*     */           entity.m_20084_(message.getUUID());
/*     */           entity.m_19890_(x, y, z, yRot, xRot);
/*     */           entity.m_20256_(new Vec3(message.getDeltaMovement().m_123341_() / 8000.0D, message.getDeltaMovement().m_123342_() / 8000.0D, message.getDeltaMovement().m_123343_() / 8000.0D));
/*     */           entity.m_20088_().m_135356_(message.getUnpackedData());
/*     */           entity.setOnDistantRenderer();
/*     */           if (!distantRenderer.contains(message.getId())) {
/*     */             distantRenderer.addWitherStorm(message.getId(), entity);
/*     */           }
/*     */           if (entity instanceof IEntityAdditionalSpawnData) {
/*     */             ((IEntityAdditionalSpawnData)entity).readSpawnData(message.getBuffer());
/*     */           }
/*     */           if (entity instanceof EntitySyncableData)
/*     */             entity.readData(message.getBuffer()); 
/*     */         });
/*     */   }
/*     */   
/*     */   public static void processRemoveStormFromDistantRendererMessage(RemoveStormFromDistantRendererMessage message) {
/* 126 */     Minecraft mc = Minecraft.m_91087_();
/* 127 */     ClientLevel world = mc.f_91073_;
/* 128 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 129 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getId());
/*     */           if (entity != null) {
/*     */             entity.m_146870_();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processUpdateStormPositionMessage(UpdateStormPositionMessage message) {
/* 139 */     Minecraft mc = Minecraft.m_91087_();
/* 140 */     ClientLevel world = mc.f_91073_;
/* 141 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 142 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             if (!entity.m_6109_()) {
/*     */               if (message.hasPosition()) {
/*     */                 VecDeltaCodec codec = entity.m_217001_();
/*     */                 Vec3 vector3d = codec.m_238021_(message.getX(), message.getY(), message.getZ());
/*     */                 codec.m_238033_(vector3d);
/*     */                 float yRot = message.hasRotation() ? ((message.getYRot() * 360) / 256.0F) : entity.m_146908_();
/*     */                 float xRot = message.hasRotation() ? ((message.getXRot() * 360) / 256.0F) : entity.m_146909_();
/*     */                 entity.m_6453_(vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_, yRot, xRot, 3, false);
/*     */               } else if (message.hasRotation()) {
/*     */                 float yRot = (message.getYRot() * 360) / 256.0F;
/*     */                 float xRot = (message.getXRot() * 360) / 256.0F;
/*     */                 entity.m_6453_(entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), yRot, xRot, 3, false);
/*     */               } 
/*     */               entity.m_6853_(message.onGround());
/*     */             } 
/*     */           }
/*     */         });
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
/*     */   public static void processStormTeleportMessage(StormTeleportMessage message) {
/* 173 */     Minecraft mc = Minecraft.m_91087_();
/* 174 */     ClientLevel world = mc.f_91073_;
/* 175 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 176 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             double x = message.getX();
/*     */             double y = message.getY();
/*     */             double z = message.getZ();
/*     */             entity.m_217006_(x, y, z);
/*     */             if (!entity.m_6109_()) {
/*     */               float yRot = (message.getYRot() * 360) / 256.0F;
/*     */               float xRot = (message.getXRot() * 360) / 256.0F;
/*     */               if (world.m_6815_(entity.m_19879_()) == null) {
/*     */                 entity.m_6453_(x, y, z, yRot, xRot, 3, false);
/*     */               } else {
/*     */                 entity.f_19854_ = x;
/*     */                 entity.f_19855_ = y;
/*     */                 entity.f_19856_ = z;
/*     */                 entity.f_19790_ = x;
/*     */                 entity.f_19791_ = y;
/*     */                 entity.f_19792_ = z;
/*     */                 entity.f_19859_ = yRot;
/*     */                 entity.f_19860_ = xRot;
/*     */                 entity.m_146922_(yRot);
/*     */                 entity.m_146926_(xRot);
/*     */               } 
/*     */               entity.m_6853_(message.onGround());
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processUpdateStormVelocityMessage(UpdateStormVelocityMessage message) {
/* 214 */     Minecraft mc = Minecraft.m_91087_();
/* 215 */     ClientLevel world = mc.f_91073_;
/* 216 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 217 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             entity.m_6001_(message.getX() / 8000.0D, message.getY() / 8000.0D, message.getZ() / 8000.0D);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processUpdateStormHeadLookMessage(UpdateStormHeadLookMessage message) {
/* 227 */     Minecraft mc = Minecraft.m_91087_();
/* 228 */     ClientLevel world = mc.f_91073_;
/* 229 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 230 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             float yHeadRot = (message.getYRot() * 360) / 256.0F;
/*     */             entity.m_6541_(yHeadRot, 3);
/*     */           } 
/*     */         });
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
/*     */   public static void processStormMetadataMessage(StormMetadataMessage message) {
/* 253 */     Minecraft mc = Minecraft.m_91087_();
/* 254 */     ClientLevel world = mc.f_91073_;
/* 255 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 256 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null && message.getUnpackedItems() != null) {
/*     */             entity.m_20088_().m_135356_(message.getUnpackedItems());
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processStormAttributesMessage(StormAttributesMessage message) {
/* 266 */     Minecraft mc = Minecraft.m_91087_();
/* 267 */     ClientLevel world = mc.f_91073_;
/* 268 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 269 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             AttributeMap manager = entity.m_21204_();
/*     */             for (ClientboundUpdateAttributesPacket.AttributeSnapshot snapshot : message.getAttributes()) {
/*     */               AttributeInstance attribute = manager.m_22146_(snapshot.m_133601_());
/*     */               if (attribute == null) {
/*     */                 LOGGER.warn("WitherStormEntity {} does not have attribute {}", entity, ForgeRegistries.ATTRIBUTES.getKey(snapshot.m_133601_()));
/*     */                 continue;
/*     */               } 
/*     */               attribute.m_22100_(snapshot.m_133602_());
/*     */               attribute.m_22132_();
/*     */               for (AttributeModifier modifier : snapshot.m_133603_()) {
/*     */                 attribute.m_22118_(modifier);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processCreateLoopingSoundMessage(CreateLoopingSoundMessage message) {
/* 297 */     Minecraft mc = Minecraft.m_91087_();
/* 298 */     ClientLevel world = mc.f_91073_;
/* 299 */     getLoopingSoundManager(world).ifPresent(loopingManager -> {
/*     */           double distance = mc.f_91074_.m_20275_(message.getX(), message.getY(), message.getZ());
/*     */           float fade = Math.max(1.0F, (float)(distance / 1000.0D) / 32.0F);
/*     */           loopingManager.putSound(message.getEntityID(), new WitherStormSoundLoop(new Vec3(message.getX(), message.getY(), message.getZ()), WitherStormEntity.getSoundForLoop(message.getPhase(), fade)));
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processStormSoundPositionMessage(StormSoundPositionMessage message) {
/* 309 */     Minecraft mc = Minecraft.m_91087_();
/* 310 */     ClientLevel world = mc.f_91073_;
/* 311 */     getLoopingSoundManager(world).ifPresent(loopingManager -> {
/*     */           WitherStormSoundLoop loop = loopingManager.getSound(message.getEntityID());
/*     */           if (loop != null) {
/*     */             loop.prevPos = loop.pos;
/*     */             loop.pos = new Vec3(message.getX(), message.getY(), message.getZ());
/*     */             double distance = mc.f_91074_.m_20275_(message.getX(), message.getY(), message.getZ());
/*     */             float fade = Math.max(1.0F, (float)(distance / 1000.0D) / 32.0F);
/*     */             SoundEvent event = WitherStormEntity.getSoundForLoop(message.getPhase(), fade);
/*     */             if (!loop.soundevent.m_11660_().equals(event.m_11660_()) && !loopingManager.alreadyHasReplacement(message.getEntityID())) {
/*     */               loopingManager.replace(message.getEntityID(), new WitherStormSoundLoop(new Vec3(message.getX(), message.getY(), message.getZ()), event));
/*     */             }
/*     */           } else {
/*     */             double distance = mc.f_91074_.m_20275_(message.getX(), message.getY(), message.getZ());
/*     */             float fade = Math.max(1.0F, (float)(distance / 1000.0D) / 32.0F);
/*     */             loopingManager.putSound(message.getEntityID(), new WitherStormSoundLoop(new Vec3(message.getX(), message.getY(), message.getZ()), WitherStormEntity.getSoundForLoop(message.getPhase(), fade)));
/*     */           } 
/*     */           WitherStormSoundLoop additional = loopingManager.getAdditional(message.getEntityID());
/*     */           if (additional != null) {
/*     */             additional.prevPos = additional.pos;
/*     */             additional.pos = new Vec3(message.getX(), message.getY(), message.getZ());
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processRemoveSoundLoopMessage(RemoveSoundLoopMessage message) {
/* 342 */     Minecraft mc = Minecraft.m_91087_();
/* 343 */     ClientLevel world = mc.f_91073_;
/* 344 */     getLoopingSoundManager(world).ifPresent(loopingManager -> loopingManager.stopSound(message.getId()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processNotifyHeadInjuryMessage(NotifyHeadInjuryMessage message) {
/* 352 */     Minecraft mc = Minecraft.m_91087_();
/* 353 */     WitherStormEntity entity = (WitherStormEntity)mc.f_91073_.m_6815_(message.getEntityID());
/* 354 */     if (entity != null) {
/*     */       
/* 356 */       HeadManager manager = entity.getHeadManager();
/* 357 */       manager.getHead(message.getHead()).hurt(null, manager.getHeadInjuryTime());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processUpdateEffectInstanceMessage(UpdateEffectInstanceMessage message) {
/* 363 */     Minecraft mc = Minecraft.m_91087_();
/* 364 */     Entity entity = mc.f_91073_.m_6815_(message.getEntityID());
/* 365 */     if (entity instanceof LivingEntity) {
/*     */       
/* 367 */       LivingEntity living = (LivingEntity)entity;
/* 368 */       MobEffectInstance effect = living.m_21124_((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get());
/* 369 */       if (effect != null) {
/*     */         
/* 371 */         MobEffectInstance newEffect = new MobEffectInstance((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get(), message.getDuration(), message.getAmplifier());
/* 372 */         effect.m_19558_(newEffect);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processUpdateWitherSicknessTrackerMessage(UpdateWitherSicknessTrackerMessage message) {
/* 379 */     Minecraft mc = Minecraft.m_91087_();
/* 380 */     ClientLevel world = mc.f_91073_;
/* 381 */     Entity entity = world.m_6815_(message.getId());
/* 382 */     if (entity != null)
/*     */     {
/* 384 */       entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> tracker.copyFromMessage(message));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processUpdatePlayDeadManagerMessage(UpdatePlayDeadManagerMessage message) {
/* 392 */     Minecraft mc = Minecraft.m_91087_();
/* 393 */     ClientLevel world = mc.f_91073_;
/* 394 */     Entity entity = world.m_6815_(message.getEntityID());
/* 395 */     if (entity != null && entity instanceof WitherStormEntity) {
/*     */       
/* 397 */       WitherStormEntity storm = (WitherStormEntity)entity;
/* 398 */       updatePlayDeadManager(storm.getPlayDeadManager(), message);
/*     */     } 
/* 400 */     getDistantRenderer(world).ifPresent(manager -> {
/*     */           WitherStormEntity storm = manager.get(message.getEntityID());
/*     */           if (storm != null) {
/*     */             updatePlayDeadManager(storm.getPlayDeadManager(), message);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private static void updatePlayDeadManager(PlayDeadManager manager, UpdatePlayDeadManagerMessage message) {
/* 410 */     if (!message.shouldUpdateTick()) {
/*     */       
/* 412 */       manager.setState(message.getState());
/* 413 */       manager.setTicksSinceRevival(message.getTicksSinceRevival());
/*     */     } 
/* 415 */     manager.setRecentlyRevived(message.hasRecentlyBeenRevived());
/* 416 */     manager.setTickAmount(message.getTicks());
/* 417 */     manager.setRevivalPlayerProtectionTime(message.getRevivalPlayerProtectionTime());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processCreateDebrisMessage(CreateDebrisMessage message) {
/* 422 */     Minecraft mc = Minecraft.m_91087_();
/* 423 */     ClientLevel world = mc.f_91073_;
/* 424 */     Entity entity = world.m_6815_(message.getEntityID());
/* 425 */     if (entity != null && entity instanceof WitherStormEntity) {
/*     */       
/* 427 */       WitherStormEntity storm = (WitherStormEntity)entity;
/* 428 */       storm.createDebrisClusters(message.isDebrisHidden());
/* 429 */       storm.createDebrisRings(message.isDebrisHidden());
/*     */     } 
/* 431 */     getDistantRenderer(world).ifPresent(manager -> {
/*     */           WitherStormEntity storm = manager.get(message.getEntityID());
/*     */           if (storm != null) {
/*     */             storm.createDebrisClusters(message.isDebrisHidden());
/*     */             storm.createDebrisRings(message.isDebrisHidden());
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processEntitySyncableDataMessage(EntitySyncableDataMessage message) {
/* 444 */     Minecraft mc = Minecraft.m_91087_();
/* 445 */     ClientLevel world = mc.f_91073_;
/* 446 */     Entity entity = world.m_6815_(message.getId());
/* 447 */     if (entity != null && entity instanceof EntitySyncableData) {
/*     */       
/* 449 */       EntitySyncableData syncable = (EntitySyncableData)entity;
/* 450 */       syncable.readData(message.getBuffer());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processPlayAdditionalLoopingSoundMessage(PlayAdditionalLoopingSoundMessage message) {
/* 456 */     Minecraft mc = Minecraft.m_91087_();
/* 457 */     ClientLevel world = mc.f_91073_;
/* 458 */     getLoopingSoundManager(world).ifPresent(loopingManager -> {
/*     */           SoundEvent event = message.getSound();
/*     */           Entity entity = world.m_6815_(message.getEntityID());
/*     */           if (entity instanceof WitherStormEntity) {
/*     */             loopingManager.putAdditionalSound(message.getEntityID(), new WitherStormSoundLoop((WitherStormEntity)entity, new Vec3(message.getX(), message.getY(), message.getZ()), event));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processRemoveAdditionalLoopingSoundMessage(RemoveAdditionalLoopingSoundMessage message) {
/* 469 */     Minecraft mc = Minecraft.m_91087_();
/* 470 */     ClientLevel world = mc.f_91073_;
/* 471 */     getLoopingSoundManager(world).ifPresent(loopingManager -> loopingManager.stopAdditional(message.getId()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processShakeScreenMessage(ShakeScreenMessage message) {
/* 478 */     Minecraft mc = Minecraft.m_91087_();
/* 479 */     mc.f_91074_.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> shaker.shake(message.getDuration(), message.getPower()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processFormidibombExplosionMessage(FormidibombExplosionMessage message) {
/* 486 */     Minecraft mc = Minecraft.m_91087_();
/* 487 */     ClientLevel world = mc.f_91073_;
/* 488 */     Entity entity = world.m_6815_(message.getId());
/* 489 */     FormidibombEntity.explode((Level)world, entity, message.getRadius(), message.getSquish(), message.getX(), message.getY(), message.getZ());
/* 490 */     mc.f_91074_.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> shaker.shake(100.0F, 7.5F));
/*     */ 
/*     */     
/* 493 */     if (Math.sqrt(mc.f_91074_.m_20275_(message.getX(), message.getY(), message.getZ())) <= 250.0D)
/*     */     {
/* 495 */       mc.f_91074_.getCapability(WitherStormModClientCapabilities.SCREEN_BLINDER).ifPresent(blinder -> blinder.blind(260, 40, 240));
/*     */     }
/*     */ 
/*     */     
/* 499 */     getBossThemeManager(world).ifPresent(manager -> manager.forceStop());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processUpdateDamagingProjectileMessage(UpdateDamagingProjectileMessage message) {
/* 506 */     Minecraft mc = Minecraft.m_91087_();
/* 507 */     ClientLevel world = mc.f_91073_;
/* 508 */     Entity entity = world.m_6815_(message.getEntityId());
/* 509 */     if (entity instanceof AbstractHurtingProjectile) {
/*     */       
/* 511 */       AbstractHurtingProjectile projectile = (AbstractHurtingProjectile)entity;
/* 512 */       projectile.f_36813_ = message.getXPower();
/* 513 */       projectile.f_36814_ = message.getYPower();
/* 514 */       projectile.f_36815_ = message.getZPower();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processBlindScreenMessage(BlindScreenMessage message) {
/* 520 */     Minecraft mc = Minecraft.m_91087_();
/* 521 */     mc.f_91074_.getCapability(WitherStormModClientCapabilities.SCREEN_BLINDER).ifPresent(blinder -> blinder.blind(message.getDuration(), message.getFadeInDuration(), message.getFadeOutDuration()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processSuperBeaconValidEffectsMessage(SuperBeaconValidEffectsMessage message) {
/* 528 */     Minecraft mc = Minecraft.m_91087_();
/* 529 */     Screen screen = mc.f_91080_; if (screen instanceof SuperBeaconScreen) { SuperBeaconScreen superBeaconScreen = (SuperBeaconScreen)screen;
/* 530 */       superBeaconScreen.setValidEffects(XCldrStub.ImmutableSet.copyOf(message.getEffects())); }
/*     */   
/*     */   }
/*     */   
/*     */   public static void processUpdateDistantSuperBeaconMessage(UpdateDistantSuperBeaconMessage message) {
/* 535 */     Minecraft mc = Minecraft.m_91087_();
/* 536 */     getDistantRenderer(mc.f_91073_).ifPresent(renderer -> renderer.addAndOrUpdateSuperBeacon(message.getPos(), message.getColor(), message.isActive(), message.getBeaconHeight(), message.getBeamWidth(), message.getOuterBeamWidth()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processRemoveDistantSuperBeaconMessage(RemoveDistantSuperBeaconMessage message) {
/* 541 */     Minecraft mc = Minecraft.m_91087_();
/* 542 */     getDistantRenderer(mc.f_91073_).ifPresent(renderer -> renderer.removeSuperBeacon(message.getPos()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processOnHeadAttackedMessage(OnHeadAttackedMessage message) {
/* 547 */     Minecraft mc = Minecraft.m_91087_();
/* 548 */     Entity entity = mc.f_91073_.m_6815_(message.getEntityId());
/* 549 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/* 550 */       storm.getHeadManager().getHead(message.getHeadIndex()).handleHeadAttackedOnClient(); }
/*     */   
/*     */   }
/*     */   
/*     */   private static LazyOptional<WitherStormDistantRenderer> getDistantRenderer(ClientLevel world) {
/* 555 */     return world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER);
/*     */   }
/*     */ 
/*     */   
/*     */   private static LazyOptional<WitherStormLoopingSoundManager> getLoopingSoundManager(ClientLevel world) {
/* 560 */     return world.getCapability(WitherStormModClientCapabilities.LOOPING_MANAGER);
/*     */   }
/*     */ 
/*     */   
/*     */   private static LazyOptional<BossThemeManager> getBossThemeManager(ClientLevel world) {
/* 565 */     return world.getCapability(WitherStormModClientCapabilities.BOSS_THEME_MANAGER);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void purgeNonApplicable(ClientLevel level, DistantRendererMessage message) {
/* 570 */     getDistantRenderer(level).ifPresent(renderer -> {
/*     */           for (WitherStormEntity known : renderer.getKnown()) {
/*     */             if (!message.getApplicable().contains(Integer.valueOf(known.m_19879_())))
/*     */               known.m_146870_(); 
/*     */           } 
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\packet\WitherStormModMessageHandlerClient.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */