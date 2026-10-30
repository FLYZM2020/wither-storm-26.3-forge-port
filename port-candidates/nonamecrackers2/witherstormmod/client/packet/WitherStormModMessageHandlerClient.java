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
/*     */ import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
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
/*  76 */     Minecraft mc = Minecraft.getInstance();
/*  77 */     LocalPlayer player = mc.player;
/*  78 */     if (player != null) {
/*  79 */       player.playNotifySound(message.getSoundEvent(), SoundSource.HOSTILE, message.getVolume(), message.getPitch());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void processPlayerMotionMessage(PlayerMotionMessage message) {
/*  84 */     Minecraft mc = Minecraft.getInstance();
/*  85 */     Entity vehicle = mc.player.getVehicle();
/*  86 */     if (mc.player.isPassenger()) {
/*  87 */       vehicle.setDeltaMovement(message.getMotion());
/*     */     } else {
/*  89 */       mc.player.setDeltaMovement(message.getMotion());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void processWitherStormToDistantRendererMessage(WitherStormToDistantRendererMessage message) {
/*  94 */     Minecraft mc = Minecraft.getInstance();
/*  95 */     ClientLevel world = mc.level;
/*  96 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/*  97 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = (WitherStormEntity)((EntityType)ForgeRegistries.ENTITY_TYPES.getValue(message.getType())).create((Level)mc.level);
/*     */           double x = (message.getPos()).x;
/*     */           double y = (message.getPos()).y;
/*     */           double z = (message.getPos()).z;
/*     */           float yRot = (message.getYRot() * 360) / 256.0F;
/*     */           float xRot = (message.getXRot() * 360) / 256.0F;
/*     */           entity.syncPacketPositionCodec((message.getPos()).x, (message.getPos()).y, (message.getPos()).z);
/*     */           entity.yBodyRot = (message.getHeadYRot() * 360) / 256.0F;
/*     */           entity.yHeadRot = (message.getHeadYRot() * 360) / 256.0F;
/*     */           entity.getHeadManager().updateHeadsFromPacked(message.getRots());
/*     */           entity.setId(message.getId());
/*     */           entity.setUUID(message.getUUID());
/*     */           entity.absMoveTo(x, y, z, yRot, xRot);
/*     */           entity.setDeltaMovement(new Vec3(message.getDeltaMovement().getX() / 8000.0D, message.getDeltaMovement().getY() / 8000.0D, message.getDeltaMovement().getZ() / 8000.0D));
/*     */           entity.getEntityData().assignValues(message.getUnpackedData());
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
/* 126 */     Minecraft mc = Minecraft.getInstance();
/* 127 */     ClientLevel world = mc.level;
/* 128 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 129 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getId());
/*     */           if (entity != null) {
/*     */             entity.discard();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processUpdateStormPositionMessage(UpdateStormPositionMessage message) {
/* 139 */     Minecraft mc = Minecraft.getInstance();
/* 140 */     ClientLevel world = mc.level;
/* 141 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 142 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             if (!entity.isControlledByLocalInstance()) {
/*     */               if (message.hasPosition()) {
/*     */                 VecDeltaCodec codec = entity.getPositionCodec();
/*     */                 Vec3 vector3d = codec.decode(message.getX(), message.getY(), message.getZ());
/*     */                 codec.setBase(vector3d);
/*     */                 float yRot = message.hasRotation() ? ((message.getYRot() * 360) / 256.0F) : entity.getYRot();
/*     */                 float xRot = message.hasRotation() ? ((message.getXRot() * 360) / 256.0F) : entity.getXRot();
/*     */                 entity.lerpTo(vector3d.x, vector3d.y, vector3d.z, yRot, xRot, 3, false);
/*     */               } else if (message.hasRotation()) {
/*     */                 float yRot = (message.getYRot() * 360) / 256.0F;
/*     */                 float xRot = (message.getXRot() * 360) / 256.0F;
/*     */                 entity.lerpTo(entity.getX(), entity.getY(), entity.getZ(), yRot, xRot, 3, false);
/*     */               } 
/*     */               entity.setOnGround(message.onGround());
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
/* 173 */     Minecraft mc = Minecraft.getInstance();
/* 174 */     ClientLevel world = mc.level;
/* 175 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 176 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             double x = message.getX();
/*     */             double y = message.getY();
/*     */             double z = message.getZ();
/*     */             entity.syncPacketPositionCodec(x, y, z);
/*     */             if (!entity.isControlledByLocalInstance()) {
/*     */               float yRot = (message.getYRot() * 360) / 256.0F;
/*     */               float xRot = (message.getXRot() * 360) / 256.0F;
/*     */               if (world.getEntity(entity.getId()) == null) {
/*     */                 entity.lerpTo(x, y, z, yRot, xRot, 3, false);
/*     */               } else {
/*     */                 entity.xo = x;
/*     */                 entity.yo = y;
/*     */                 entity.zo = z;
/*     */                 entity.xOld = x;
/*     */                 entity.yOld = y;
/*     */                 entity.zOld = z;
/*     */                 entity.yRotO = yRot;
/*     */                 entity.xRotO = xRot;
/*     */                 entity.setYRot(yRot);
/*     */                 entity.setXRot(xRot);
/*     */               } 
/*     */               entity.setOnGround(message.onGround());
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
/* 214 */     Minecraft mc = Minecraft.getInstance();
/* 215 */     ClientLevel world = mc.level;
/* 216 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 217 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             entity.lerpMotion(message.getX() / 8000.0D, message.getY() / 8000.0D, message.getZ() / 8000.0D);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processUpdateStormHeadLookMessage(UpdateStormHeadLookMessage message) {
/* 227 */     Minecraft mc = Minecraft.getInstance();
/* 228 */     ClientLevel world = mc.level;
/* 229 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 230 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             float yHeadRot = (message.getYRot() * 360) / 256.0F;
/*     */             entity.lerpHeadTo(yHeadRot, 3);
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
/* 253 */     Minecraft mc = Minecraft.getInstance();
/* 254 */     ClientLevel world = mc.level;
/* 255 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 256 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null && message.getUnpackedItems() != null) {
/*     */             entity.getEntityData().assignValues(message.getUnpackedItems());
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processStormAttributesMessage(StormAttributesMessage message) {
/* 266 */     Minecraft mc = Minecraft.getInstance();
/* 267 */     ClientLevel world = mc.level;
/* 268 */     purgeNonApplicable(world, (DistantRendererMessage)message);
/* 269 */     getDistantRenderer(world).ifPresent(distantRenderer -> {
/*     */           WitherStormEntity entity = distantRenderer.get(message.getEntityID());
/*     */           if (entity != null) {
/*     */             AttributeMap manager = entity.getAttributes();
/*     */             for (ClientboundUpdateAttributesPacket.AttributeSnapshot snapshot : message.getAttributes()) {
/*     */               AttributeInstance attribute = manager.getInstance(snapshot.getAttribute());
/*     */               if (attribute == null) {
/*     */                 LOGGER.warn("WitherStormEntity {} does not have attribute {}", entity, ForgeRegistries.ATTRIBUTES.getKey(snapshot.getAttribute()));
/*     */                 continue;
/*     */               } 
/*     */               attribute.setBaseValue(snapshot.getBase());
/*     */               attribute.removeModifiers();
/*     */               for (AttributeModifier modifier : snapshot.getModifiers()) {
/*     */                 attribute.addTransientModifier(modifier);
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
/* 297 */     Minecraft mc = Minecraft.getInstance();
/* 298 */     ClientLevel world = mc.level;
/* 299 */     getLoopingSoundManager(world).ifPresent(loopingManager -> {
/*     */           double distance = mc.player.distanceToSqr(message.getX(), message.getY(), message.getZ());
/*     */           float fade = Math.max(1.0F, (float)(distance / 1000.0D) / 32.0F);
/*     */           loopingManager.putSound(message.getEntityID(), new WitherStormSoundLoop(new Vec3(message.getX(), message.getY(), message.getZ()), WitherStormEntity.getSoundForLoop(message.getPhase(), fade)));
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processStormSoundPositionMessage(StormSoundPositionMessage message) {
/* 309 */     Minecraft mc = Minecraft.getInstance();
/* 310 */     ClientLevel world = mc.level;
/* 311 */     getLoopingSoundManager(world).ifPresent(loopingManager -> {
/*     */           WitherStormSoundLoop loop = loopingManager.getSound(message.getEntityID());
/*     */           if (loop != null) {
/*     */             loop.prevPos = loop.pos;
/*     */             loop.pos = new Vec3(message.getX(), message.getY(), message.getZ());
/*     */             double distance = mc.player.distanceToSqr(message.getX(), message.getY(), message.getZ());
/*     */             float fade = Math.max(1.0F, (float)(distance / 1000.0D) / 32.0F);
/*     */             SoundEvent event = WitherStormEntity.getSoundForLoop(message.getPhase(), fade);
/*     */             if (!loop.soundevent.getLocation().equals(event.getLocation()) && !loopingManager.alreadyHasReplacement(message.getEntityID())) {
/*     */               loopingManager.replace(message.getEntityID(), new WitherStormSoundLoop(new Vec3(message.getX(), message.getY(), message.getZ()), event));
/*     */             }
/*     */           } else {
/*     */             double distance = mc.player.distanceToSqr(message.getX(), message.getY(), message.getZ());
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
/* 342 */     Minecraft mc = Minecraft.getInstance();
/* 343 */     ClientLevel world = mc.level;
/* 344 */     getLoopingSoundManager(world).ifPresent(loopingManager -> loopingManager.stopSound(message.getId()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processNotifyHeadInjuryMessage(NotifyHeadInjuryMessage message) {
/* 352 */     Minecraft mc = Minecraft.getInstance();
/* 353 */     WitherStormEntity entity = (WitherStormEntity)mc.level.getEntity(message.getEntityID());
/* 354 */     if (entity != null) {
/*     */       
/* 356 */       HeadManager manager = entity.getHeadManager();
/* 357 */       manager.getHead(message.getHead()).hurt(null, manager.getHeadInjuryTime());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processUpdateEffectInstanceMessage(UpdateEffectInstanceMessage message) {
/* 363 */     Minecraft mc = Minecraft.getInstance();
/* 364 */     Entity entity = mc.level.getEntity(message.getEntityID());
/* 365 */     if (entity instanceof LivingEntity) {
/*     */       
/* 367 */       LivingEntity living = (LivingEntity)entity;
/* 368 */       MobEffectInstance effect = living.getEffect((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get());
/* 369 */       if (effect != null) {
/*     */         
/* 371 */         MobEffectInstance newEffect = new MobEffectInstance((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get(), message.getDuration(), message.getAmplifier());
/* 372 */         effect.update(newEffect);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processUpdateWitherSicknessTrackerMessage(UpdateWitherSicknessTrackerMessage message) {
/* 379 */     Minecraft mc = Minecraft.getInstance();
/* 380 */     ClientLevel world = mc.level;
/* 381 */     Entity entity = world.getEntity(message.getId());
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
/* 392 */     Minecraft mc = Minecraft.getInstance();
/* 393 */     ClientLevel world = mc.level;
/* 394 */     Entity entity = world.getEntity(message.getEntityID());
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
/* 422 */     Minecraft mc = Minecraft.getInstance();
/* 423 */     ClientLevel world = mc.level;
/* 424 */     Entity entity = world.getEntity(message.getEntityID());
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
/* 444 */     Minecraft mc = Minecraft.getInstance();
/* 445 */     ClientLevel world = mc.level;
/* 446 */     Entity entity = world.getEntity(message.getId());
/* 447 */     if (entity != null && entity instanceof EntitySyncableData) {
/*     */       
/* 449 */       EntitySyncableData syncable = (EntitySyncableData)entity;
/* 450 */       syncable.readData(message.getBuffer());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processPlayAdditionalLoopingSoundMessage(PlayAdditionalLoopingSoundMessage message) {
/* 456 */     Minecraft mc = Minecraft.getInstance();
/* 457 */     ClientLevel world = mc.level;
/* 458 */     getLoopingSoundManager(world).ifPresent(loopingManager -> {
/*     */           SoundEvent event = message.getSound();
/*     */           Entity entity = world.getEntity(message.getEntityID());
/*     */           if (entity instanceof WitherStormEntity) {
/*     */             loopingManager.putAdditionalSound(message.getEntityID(), new WitherStormSoundLoop((WitherStormEntity)entity, new Vec3(message.getX(), message.getY(), message.getZ()), event));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processRemoveAdditionalLoopingSoundMessage(RemoveAdditionalLoopingSoundMessage message) {
/* 469 */     Minecraft mc = Minecraft.getInstance();
/* 470 */     ClientLevel world = mc.level;
/* 471 */     getLoopingSoundManager(world).ifPresent(loopingManager -> loopingManager.stopAdditional(message.getId()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processShakeScreenMessage(ShakeScreenMessage message) {
/* 478 */     Minecraft mc = Minecraft.getInstance();
/* 479 */     mc.player.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> shaker.shake(message.getDuration(), message.getPower()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processFormidibombExplosionMessage(FormidibombExplosionMessage message) {
/* 486 */     Minecraft mc = Minecraft.getInstance();
/* 487 */     ClientLevel world = mc.level;
/* 488 */     Entity entity = world.getEntity(message.getId());
/* 489 */     FormidibombEntity.explode((Level)world, entity, message.getRadius(), message.getSquish(), message.getX(), message.getY(), message.getZ());
/* 490 */     mc.player.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> shaker.shake(100.0F, 7.5F));
/*     */ 
/*     */     
/* 493 */     if (Math.sqrt(mc.player.distanceToSqr(message.getX(), message.getY(), message.getZ())) <= 250.0D)
/*     */     {
/* 495 */       mc.player.getCapability(WitherStormModClientCapabilities.SCREEN_BLINDER).ifPresent(blinder -> blinder.blind(260, 40, 240));
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
/* 506 */     Minecraft mc = Minecraft.getInstance();
/* 507 */     ClientLevel world = mc.level;
/* 508 */     Entity entity = world.getEntity(message.getEntityId());
/* 509 */     if (entity instanceof AbstractHurtingProjectile) {
/*     */       
/* 511 */       AbstractHurtingProjectile projectile = (AbstractHurtingProjectile)entity;
/* 512 */       projectile.xPower = message.getXPower();
/* 513 */       projectile.yPower = message.getYPower();
/* 514 */       projectile.zPower = message.getZPower();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processBlindScreenMessage(BlindScreenMessage message) {
/* 520 */     Minecraft mc = Minecraft.getInstance();
/* 521 */     mc.player.getCapability(WitherStormModClientCapabilities.SCREEN_BLINDER).ifPresent(blinder -> blinder.blind(message.getDuration(), message.getFadeInDuration(), message.getFadeOutDuration()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processSuperBeaconValidEffectsMessage(SuperBeaconValidEffectsMessage message) {
/* 528 */     Minecraft mc = Minecraft.getInstance();
/* 529 */     Screen screen = mc.screen; if (screen instanceof SuperBeaconScreen) { SuperBeaconScreen superBeaconScreen = (SuperBeaconScreen)screen;
/* 530 */       superBeaconScreen.setValidEffects(XCldrStub.ImmutableSet.copyOf(message.getEffects())); }
/*     */   
/*     */   }
/*     */   
/*     */   public static void processUpdateDistantSuperBeaconMessage(UpdateDistantSuperBeaconMessage message) {
/* 535 */     Minecraft mc = Minecraft.getInstance();
/* 536 */     getDistantRenderer(mc.level).ifPresent(renderer -> renderer.addAndOrUpdateSuperBeacon(message.getPos(), message.getColor(), message.isActive(), message.getBeaconHeight(), message.getBeamWidth(), message.getOuterBeamWidth()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processRemoveDistantSuperBeaconMessage(RemoveDistantSuperBeaconMessage message) {
/* 541 */     Minecraft mc = Minecraft.getInstance();
/* 542 */     getDistantRenderer(mc.level).ifPresent(renderer -> renderer.removeSuperBeacon(message.getPos()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processOnHeadAttackedMessage(OnHeadAttackedMessage message) {
/* 547 */     Minecraft mc = Minecraft.getInstance();
/* 548 */     Entity entity = mc.level.getEntity(message.getEntityId());
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
/*     */             if (!message.getApplicable().contains(Integer.valueOf(known.getId())))
/*     */               known.discard(); 
/*     */           } 
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\packet\WitherStormModMessageHandlerClient.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */