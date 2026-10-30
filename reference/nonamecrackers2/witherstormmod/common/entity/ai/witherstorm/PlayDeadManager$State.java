/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.server.level.ServerBossEvent;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.PlayAdditionalLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveAdditionalLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
/*     */ import nonamecrackers2.witherstormmod.common.util.DebrisCluster;
/*     */ import nonamecrackers2.witherstormmod.common.util.DebrisRingSettings;
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
/*     */ public enum State
/*     */ {
/* 333 */   NORMAL_BEHAVIOR
/*     */   {
/*     */     
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager)
/*     */     {
/* 338 */       super.tick(world, entity, manager);
/*     */       
/* 340 */       float percentage = Math.min(manager.ticksSinceInit, 80) / 80.0F;
/* 341 */       for (DebrisRingSettings settings : entity.getDebrisRings())
/* 342 */         settings.setAlpha(percentage); 
/* 343 */       entity.setShineAlpha(percentage);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, State next) {
/* 349 */       List<Mob> entities = world.m_45976_(Mob.class, entity.getSearchBox());
/* 350 */       for (Mob mob : entities) {
/*     */         
/* 352 */         if (mob.m_5448_() == entity)
/* 353 */           mob.m_6710_(null); 
/*     */       } 
/*     */     }
/*     */   },
/* 357 */   FALLING
/*     */   {
/*     */     
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager)
/*     */     {
/* 362 */       super.tick(world, entity, manager);
/*     */       
/* 364 */       if (!world.f_46443_) {
/*     */ 
/*     */         
/* 367 */         if (manager.getTicks() % 8 == 0 && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity))
/*     */         {
/* 369 */           for (int i = 0; i < 3; i++) {
/* 370 */             entity.dropSmallMassCluster(1);
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 379 */         if (entity.getSegmentsManager().isPresent() && manager.getTicks() == 201) {
/*     */           
/* 381 */           SegmentsManager segmentsManager = entity.getSegmentsManager().get();
/* 382 */           if (entity.getPhase() == 5) {
/*     */             
/* 384 */             entity.setPhase(6);
/* 385 */             entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SPLITS.get(), 1.0F, 1.0F);
/* 386 */             entity.setOtherHeadsDisabled(true);
/* 387 */             segmentsManager.createSegments();
/* 388 */             segmentsManager.addSegments();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 393 */       if (!entity.m_20096_()) {
/*     */         
/* 395 */         float xOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 396 */         float yOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 397 */         float zOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 398 */         world.m_7106_((ParticleOptions)ParticleTypes.f_123813_, entity.m_20185_() + xOffset, entity.m_20188_() + yOffset, entity.m_20189_() + zOffset, -5.0D, 0.0D, 0.0D);
/*     */       } 
/*     */       
/* 401 */       float percentage = (120.0F - Math.min(manager.ticksSinceInit, 120)) / 120.0F;
/* 402 */       for (DebrisRingSettings settings : entity.getDebrisRings())
/* 403 */         settings.setAlpha(percentage); 
/* 404 */       entity.setShineAlpha(percentage);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 410 */       super.init(world, entity, manager);
/*     */       
/* 412 */       if (!world.f_46443_) {
/*     */         
/* 414 */         for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 415 */           head.doRoar(entity.m_217043_().m_188499_());
/*     */         }
/* 417 */         if (entity.shouldPlaySoundLoop) {
/*     */           
/* 419 */           PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get());
/* 420 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*     */         } 
/*     */       } 
/*     */       
/* 424 */       for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 425 */         head.lerpHeadTo(-50.0F, entity.f_20883_, 64.0F);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, State next) {
/* 431 */       super.finish(world, entity, manager, next);
/* 432 */       if (!world.f_46443_ && entity.shouldPlaySoundLoop) {
/*     */         
/* 434 */         RemoveAdditionalLoopingSoundMessage message = new RemoveAdditionalLoopingSoundMessage(entity);
/* 435 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> world.m_46472_()), message);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean disablesAi() {
/* 442 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 448 */       return entity.m_20096_();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void sendAdditionalPackets(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 454 */       super.sendAdditionalPackets(world, entity, manager);
/*     */       
/* 456 */       if (entity.shouldPlaySoundLoop) {
/*     */         
/* 458 */         PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get());
/* 459 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*     */       } 
/*     */     }
/*     */   },
/* 463 */   PLAYING_DEAD
/*     */   {
/*     */     
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager)
/*     */     {
/* 468 */       super.tick(world, entity, manager);
/*     */       
/* 470 */       if (entity.isOnBack() && entity.m_6084_() && !entity.m_21224_()) {
/*     */         
/* 472 */         manager.placePodium();
/* 473 */         BlockPos pos = manager.getPodiumPos();
/* 474 */         if (pos != null) {
/* 475 */           entity.spawnConsumedPets(new Vec3(pos.m_123341_() + 0.5D, pos.m_123342_() + 12.0D, pos.m_123343_() + 0.5D));
/*     */         }
/*     */       } 
/* 478 */       for (DebrisRingSettings settings : entity.getDebrisRings())
/* 479 */         settings.setAlpha(0.0F); 
/* 480 */       entity.setShineAlpha(0.0F);
/*     */       
/* 482 */       if (!world.f_46443_) {
/*     */         
/* 484 */         CommandBlockEntity commandBlock = manager.getCommandBlock();
/* 485 */         if (commandBlock != null && (!commandBlock.m_6084_() || commandBlock.m_20270_((Entity)entity) > 64.0D)) {
/* 486 */           manager.timeSinceCommandBlockMissing++;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 493 */       return (manager.timeSinceCommandBlockMissing > 200);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean disablesAi() {
/* 499 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 505 */       super.init(world, entity, manager);
/*     */       
/* 507 */       manager.timeSinceCommandBlockMissing = 0;
/*     */       
/* 509 */       for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 510 */         head.lerpHeadTo(40.0F, entity.f_20883_, 16.0F);
/*     */       }
/* 512 */       if (!world.f_46443_)
/*     */       {
/* 514 */         if (entity.getSegmentsManager().isPresent()) {
/*     */           
/* 516 */           SegmentsManager segmentsManager = entity.getSegmentsManager().get();
/* 517 */           if (entity.getPhase() == 5) {
/*     */             
/* 519 */             entity.setPhase(6);
/* 520 */             entity.setOtherHeadsDisabled(true);
/* 521 */             segmentsManager.createSegments();
/* 522 */             segmentsManager.addSegments();
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, State next) {
/* 531 */       manager.timeSinceCommandBlockMissing = 0;
/* 532 */       if (next != State.REVIVING)
/* 533 */         manager.removePodium(); 
/*     */     }
/*     */   },
/* 536 */   REVIVING
/*     */   {
/*     */     
/*     */     public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager)
/*     */     {
/* 541 */       super.tick(world, entity, manager);
/*     */       
/* 543 */       if (!world.f_46443_ && manager.getTicks() > 20) {
/*     */         
/* 545 */         entity.shake(60.0F, 4.0F);
/* 546 */         BlockPos pos = manager.getPodiumPos();
/* 547 */         if (pos != null)
/* 548 */           world.m_254849_((Entity)entity, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), 16.0F, Level.ExplosionInteraction.NONE); 
/* 549 */         world.m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
/* 550 */         manager.setState(State.NORMAL_BEHAVIOR);
/* 551 */         manager.removePodium();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 558 */       manager.setRecentlyRevived(true);
/*     */       
/* 560 */       super.init(world, entity, manager);
/*     */       
/* 562 */       if (!world.f_46443_ && entity.shouldPlayGlobalSounds) {
/* 563 */         entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_REACTIVATES.get(), 10.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   };
/*     */   
/*     */   public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 569 */     manager.ticksSinceInitO = manager.ticksSinceInit;
/* 570 */     manager.ticksSinceInit++;
/*     */     
/* 572 */     if (!disablesAi()) {
/* 573 */       manager.revivalTicks++;
/*     */     }
/* 575 */     if (isPastInterval(world, entity, manager)) {
/* 576 */       manager.nextState();
/*     */     }
/* 578 */     if (disablesAi()) {
/*     */       
/* 580 */       if (manager.ticksSinceInit % 5 == 0) {
/*     */         
/* 582 */         int size = Math.max(10, entity.m_217043_().m_188503_(15));
/* 583 */         for (int i = 0; i < size; i++)
/*     */         {
/* 585 */           for (DebrisCluster cluster : entity.getDebrisClusters())
/*     */           {
/* 587 */             if (!cluster.isDisabled()) {
/* 588 */               cluster.setDisabled((entity.m_217043_().m_188503_(entity.getDebrisClusters().size()) == 0));
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/* 595 */     } else if (manager.ticksSinceInit % 10 == 0) {
/*     */       
/* 597 */       int size = Math.max(10, entity.m_217043_().m_188503_(15));
/* 598 */       for (int i = 0; i < size; i++) {
/*     */         
/* 600 */         for (DebrisCluster cluster : entity.getDebrisClusters()) {
/*     */           
/* 602 */           if (cluster.isDisabled() && entity.m_217043_().m_188503_(entity.getDebrisClusters().size()) == 0) {
/* 603 */             cluster.setDisabled(false);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 612 */     manager.ticksSinceInit = 0;
/* 613 */     manager.ticksSinceInitO = 0;
/* 614 */     if (disablesAi()) {
/*     */       
/* 616 */       manager.revivalTicks = 0;
/* 617 */       manager.setRecentlyRevived(false);
/*     */     } 
/*     */     
/* 620 */     if (!world.f_46443_) {
/*     */       
/* 622 */       if (disablesAi()) {
/*     */         
/* 624 */         entity.getTrackedEntities().clearAndMakeAllFall();
/*     */         
/* 626 */         if (entity.shouldPlaySoundLoop)
/*     */         {
/* 628 */           RemoveSoundLoopMessage message = new RemoveSoundLoopMessage(entity);
/* 629 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> world.m_46472_()), message);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 634 */         for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 635 */           head.doRoar(entity.m_217043_().m_188499_());
/*     */         }
/*     */       } 
/* 638 */       entity.getBossInfo().ifPresent(info -> info.m_8321_(!disablesAi()));
/*     */     } 
/*     */     
/* 641 */     entity.m_6210_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, State next) {}
/*     */   
/*     */   public boolean disablesAi() {
/* 648 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 653 */     return false;
/*     */   }
/*     */   
/*     */   public void sendAdditionalPackets(Level world, WitherStormEntity entity, PlayDeadManager manager) {}
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\PlayDeadManager$State.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */