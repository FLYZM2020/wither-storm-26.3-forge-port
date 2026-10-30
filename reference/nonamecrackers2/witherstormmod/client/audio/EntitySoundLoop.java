/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ 
/*    */ public class EntitySoundLoop<T extends Entity>
/*    */   extends FadingSoundLoop
/*    */   implements IForceStoppableSound
/*    */ {
/*    */   public final T entity;
/*    */   protected int fadeTime;
/*    */   protected float maxVolume;
/*    */   protected final Predicate<T> shouldStop;
/*    */   
/*    */   public EntitySoundLoop(T entity, SoundEvent event, SoundSource category, int fadeTime, float maxVolume, Predicate<T> shouldStop) {
/* 18 */     super(event, category);
/* 19 */     this.entity = entity;
/* 20 */     this.fadeTime = fadeTime;
/* 21 */     this.maxVolume = maxVolume;
/* 22 */     this.shouldStop = shouldStop;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7788_() {
/* 28 */     super.m_7788_();
/*    */     
/* 30 */     this.f_119575_ = this.entity.m_20185_();
/* 31 */     this.f_119576_ = this.entity.m_20186_();
/* 32 */     this.f_119577_ = this.entity.m_20189_();
/*    */     
/* 34 */     if (!this.entity.m_6084_() || this.shouldStop.test(this.entity)) {
/* 35 */       stopSound();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getFadeTime() {
/* 41 */     return this.fadeTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float maximumVolume() {
/* 47 */     return this.maxVolume;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 53 */     m_119609_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\EntitySoundLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */