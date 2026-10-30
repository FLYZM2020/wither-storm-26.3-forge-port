/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.audio.EntitySoundLoop;
/*    */ import nonamecrackers2.witherstormmod.client.audio.EntitySoundManager;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ 
/*    */ public class WitheredSymbiontSoundManager extends EntitySoundManager<WitheredSymbiontEntity, EntitySoundLoop<WitheredSymbiontEntity>> {
/*    */   static {
/* 14 */     SHOULD_STOP = (entity -> !entity.isVulnerable());
/*    */   }
/*    */ 
/*    */   
/*    */   private static final Predicate<WitheredSymbiontEntity> SHOULD_STOP;
/*    */   
/*    */   public WitheredSymbiontSoundManager(Minecraft minecraft) {
/* 21 */     super(minecraft, WitheredSymbiontEntity.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canPlay(WitheredSymbiontEntity entity) {
/* 27 */     return (super.canPlay((Entity)entity) && !SHOULD_STOP.test(entity));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean alreadyHasLoop(WitheredSymbiontEntity entity) {
/* 33 */     boolean flag = false;
/* 34 */     for (EntitySoundLoop<WitheredSymbiontEntity> loop : (Iterable<EntitySoundLoop<WitheredSymbiontEntity>>)this.loops) {
/*    */       
/* 36 */       if (loop.entity == entity) {
/*    */         
/* 38 */         flag = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 42 */     return flag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected EntitySoundLoop<WitheredSymbiontEntity> create(WitheredSymbiontEntity entity) {
/* 48 */     return new EntitySoundLoop((Entity)entity, (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_HEART_BEAT.get(), SoundSource.AMBIENT, 20, 7.0F, SHOULD_STOP);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected EntitySoundLoop<WitheredSymbiontEntity> copyFrom(EntitySoundLoop<WitheredSymbiontEntity> loop) {
/* 54 */     return new EntitySoundLoop(loop.entity, (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_HEART_BEAT.get(), SoundSource.AMBIENT, 20, 7.0F, SHOULD_STOP);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\WitheredSymbiontSoundManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */