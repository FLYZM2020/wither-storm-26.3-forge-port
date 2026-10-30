/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*     */ 
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.PlayAdditionalLoopingSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveAdditionalLoopingSoundMessage;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ enum null
/*     */ {
/*     */   public void tick(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 362 */     super.tick(world, entity, manager);
/*     */     
/* 364 */     if (!world.f_46443_) {
/*     */ 
/*     */       
/* 367 */       if (manager.getTicks() % 8 == 0 && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity))
/*     */       {
/* 369 */         for (int i = 0; i < 3; i++) {
/* 370 */           entity.dropSmallMassCluster(1);
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 379 */       if (entity.getSegmentsManager().isPresent() && manager.getTicks() == 201) {
/*     */         
/* 381 */         SegmentsManager segmentsManager = entity.getSegmentsManager().get();
/* 382 */         if (entity.getPhase() == 5) {
/*     */           
/* 384 */           entity.setPhase(6);
/* 385 */           entity.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SPLITS.get(), 1.0F, 1.0F);
/* 386 */           entity.setOtherHeadsDisabled(true);
/* 387 */           segmentsManager.createSegments();
/* 388 */           segmentsManager.addSegments();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 393 */     if (!entity.m_20096_()) {
/*     */       
/* 395 */       float xOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 396 */       float yOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 397 */       float zOffset = (entity.m_217043_().m_188501_() - 0.5F) * (float)entity.m_20191_().m_82376_();
/* 398 */       world.m_7106_((ParticleOptions)ParticleTypes.f_123813_, entity.m_20185_() + xOffset, entity.m_20188_() + yOffset, entity.m_20189_() + zOffset, -5.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/* 401 */     float percentage = (120.0F - Math.min(manager.ticksSinceInit, 120)) / 120.0F;
/* 402 */     for (DebrisRingSettings settings : entity.getDebrisRings())
/* 403 */       settings.setAlpha(percentage); 
/* 404 */     entity.setShineAlpha(percentage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 410 */     super.init(world, entity, manager);
/*     */     
/* 412 */     if (!world.f_46443_) {
/*     */       
/* 414 */       for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 415 */         head.doRoar(entity.m_217043_().m_188499_());
/*     */       }
/* 417 */       if (entity.shouldPlaySoundLoop) {
/*     */         
/* 419 */         PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get());
/* 420 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*     */       } 
/*     */     } 
/*     */     
/* 424 */     for (WitherStormHead head : entity.getHeadManager().getHeads()) {
/* 425 */       head.lerpHeadTo(-50.0F, entity.f_20883_, 64.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void finish(Level world, WitherStormEntity entity, PlayDeadManager manager, PlayDeadManager.State next) {
/* 431 */     super.finish(world, entity, manager, next);
/* 432 */     if (!world.f_46443_ && entity.shouldPlaySoundLoop) {
/*     */       
/* 434 */       RemoveAdditionalLoopingSoundMessage message = new RemoveAdditionalLoopingSoundMessage(entity);
/* 435 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> world.m_46472_()), message);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean disablesAi() {
/* 442 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPastInterval(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 448 */     return entity.m_20096_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendAdditionalPackets(Level world, WitherStormEntity entity, PlayDeadManager manager) {
/* 454 */     super.sendAdditionalPackets(world, entity, manager);
/*     */     
/* 456 */     if (entity.shouldPlaySoundLoop) {
/*     */       
/* 458 */       PlayAdditionalLoopingSoundMessage message = new PlayAdditionalLoopingSoundMessage(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TREMBLE.get());
/* 459 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\PlayDeadManager$State$2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */