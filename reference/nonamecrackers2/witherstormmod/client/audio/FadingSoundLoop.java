/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.client.resources.sounds.SoundInstance;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ 
/*    */ public abstract class FadingSoundLoop
/*    */   extends AbstractTickableSoundInstance
/*    */ {
/*    */   protected float fade;
/*    */   protected boolean stopping;
/*    */   protected boolean starting = true;
/*    */   protected float dampen;
/*    */   
/*    */   protected FadingSoundLoop(SoundEvent event, SoundSource category) {
/* 17 */     super(event, category, SoundInstance.m_235150_());
/* 18 */     this.f_119573_ = 0.0F;
/* 19 */     this.f_119579_ = 0;
/* 20 */     this.f_119578_ = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7788_() {
/* 26 */     if (this.stopping) {
/*    */       
/* 28 */       if (this.fade > 0.0F) {
/* 29 */         this.fade--;
/*    */       } else {
/* 31 */         m_119609_();
/*    */       } 
/* 33 */     } else if (this.starting) {
/*    */       
/* 35 */       if (this.fade < getFadeTime()) {
/* 36 */         this.fade++;
/*    */       } else {
/* 38 */         this.starting = false;
/*    */       } 
/*    */     } 
/* 41 */     float volume = Math.max(0.0F, Math.min(this.fade / getFadeTime(), 1.0F)) * maximumVolume();
/* 42 */     this.f_119573_ = volume - Math.max(0.0F, this.dampen * 0.02F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean shouldFadeOut() {
/* 47 */     return this.stopping;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStopping() {
/* 52 */     return this.stopping;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStarting() {
/* 57 */     return this.starting;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopSound() {
/* 62 */     if (!this.stopping) {
/*    */       
/* 64 */       this.stopping = true;
/* 65 */       this.fade = getFadeTime();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void continueSound() {
/* 71 */     if (this.stopping) {
/*    */       
/* 73 */       this.stopping = false;
/* 74 */       this.starting = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract int getFadeTime();
/*    */   
/*    */   protected float maximumVolume() {
/* 82 */     return 1.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_7784_() {
/* 88 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\FadingSoundLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */