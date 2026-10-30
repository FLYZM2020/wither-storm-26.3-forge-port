/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.client.resources.sounds.SoundInstance;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ 
/*    */ public class CommandBlockEntityLoop
/*    */   extends AbstractTickableSoundInstance implements IForceStoppableSound {
/*    */   public final CommandBlockEntity entity;
/*    */   
/*    */   public CommandBlockEntityLoop(CommandBlockEntity entity) {
/* 15 */     super((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_PULSE_LOOP.get(), SoundSource.AMBIENT, SoundInstance.createUnseededRandom());
/* 16 */     this.entity = entity;
/* 17 */     this.looping = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 23 */     if (shouldFadeOut()) {
/*    */       
/* 25 */       if (this.volume > 0.0F) {
/* 26 */         this.volume -= 0.1F;
/*    */       
/*    */       }
/*    */     }
/* 30 */     else if (this.volume < 0.5F) {
/* 31 */       this.volume += 0.1F;
/*    */     } 
/*    */     
/* 34 */     this.pitch = (this.entity.getMaxHealth() - this.entity.getHealth()) / this.entity.getMaxHealth() * 0.4F + 1.0F;
/*    */     
/* 36 */     this.x = this.entity.getX();
/* 37 */     this.y = this.entity.getY();
/* 38 */     this.z = this.entity.getZ();
/*    */     
/* 40 */     if (shouldStop()) {
/* 41 */       forceStop();
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean shouldFadeOut() {
/* 46 */     return (this.entity.getState() == CommandBlockEntity.State.PLAYING_DEAD);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canStartSilent() {
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldStop() {
/* 57 */     return !this.entity.isAlive();
/*    */   }
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 62 */     stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\CommandBlockEntityLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */