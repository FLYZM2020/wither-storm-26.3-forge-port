/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.client.audio.EntitySoundManager;
/*    */ import nonamecrackers2.witherstormmod.client.audio.TractorBeamLoop;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ 
/*    */ public class WitherStormHeadSoundManager extends EntitySoundManager<WitherStormHeadEntity, TractorBeamLoop<WitherStormHeadEntity>> {
/*    */   public WitherStormHeadSoundManager(Minecraft minecraft) {
/* 13 */     super(minecraft, WitherStormHeadEntity.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canPlay(WitherStormHeadEntity entity) {
/* 19 */     double distance = Math.sqrt(this.minecraft.f_91074_.m_20238_(TractorBeamHelper.calculateClosestPoint((Entity)this.minecraft.f_91074_, (LivingEntity)entity)));
/* 20 */     return (super.canPlay((Entity)entity) && !entity.isDeadOrPlayingDead() && distance <= TractorBeamLoop.DISTANCE_REQUIRED && entity.tractorBeamActive(0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean alreadyHasLoop(WitherStormHeadEntity entity) {
/* 26 */     for (TractorBeamLoop<WitherStormHeadEntity> loop : (Iterable<TractorBeamLoop<WitherStormHeadEntity>>)this.loops) {
/*    */       
/* 28 */       if (loop.getEntity() == entity)
/* 29 */         return true; 
/*    */     } 
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected TractorBeamLoop<WitherStormHeadEntity> create(WitherStormHeadEntity entity) {
/* 37 */     return new TractorBeamLoop((LivingEntity)entity, loopEntity -> !loopEntity.tractorBeamActive(0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected TractorBeamLoop<WitherStormHeadEntity> copyFrom(TractorBeamLoop<WitherStormHeadEntity> loop) {
/* 43 */     return new TractorBeamLoop(loop.getEntity(), loopEntity -> !loopEntity.tractorBeamActive(0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\WitherStormHeadSoundManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */