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
/*    */   public void tick() {
/* 28 */     super.tick();
/*    */     
/* 30 */     this.x = this.entity.getX();
/* 31 */     this.y = this.entity.getY();
/* 32 */     this.z = this.entity.getZ();
/*    */     
/* 34 */     if (!this.entity.isAlive() || this.shouldStop.test(this.entity)) {
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
/* 53 */     stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\EntitySoundLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */