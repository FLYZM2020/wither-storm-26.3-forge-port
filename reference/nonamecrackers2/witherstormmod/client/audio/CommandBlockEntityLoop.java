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
/* 15 */     super((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_PULSE_LOOP.get(), SoundSource.AMBIENT, SoundInstance.m_235150_());
/* 16 */     this.entity = entity;
/* 17 */     this.f_119578_ = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7788_() {
/* 23 */     if (shouldFadeOut()) {
/*    */       
/* 25 */       if (this.f_119573_ > 0.0F) {
/* 26 */         this.f_119573_ -= 0.1F;
/*    */       
/*    */       }
/*    */     }
/* 30 */     else if (this.f_119573_ < 0.5F) {
/* 31 */       this.f_119573_ += 0.1F;
/*    */     } 
/*    */     
/* 34 */     this.f_119574_ = (this.entity.m_21233_() - this.entity.m_21223_()) / this.entity.m_21233_() * 0.4F + 1.0F;
/*    */     
/* 36 */     this.f_119575_ = this.entity.m_20185_();
/* 37 */     this.f_119576_ = this.entity.m_20186_();
/* 38 */     this.f_119577_ = this.entity.m_20189_();
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
/*    */   public boolean m_7784_() {
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldStop() {
/* 57 */     return !this.entity.m_6084_();
/*    */   }
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 62 */     m_119609_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\CommandBlockEntityLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */