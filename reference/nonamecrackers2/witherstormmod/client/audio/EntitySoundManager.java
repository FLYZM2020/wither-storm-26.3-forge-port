/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.client.resources.sounds.TickableSoundInstance;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ 
/*    */ public abstract class EntitySoundManager<T extends Entity, L extends AbstractTickableSoundInstance & IForceStoppableSound>
/*    */   implements ISoundManager {
/*    */   protected final Minecraft minecraft;
/*    */   protected final Class<T> entityClass;
/* 15 */   protected final List<L> loops = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public EntitySoundManager(Minecraft minecraft, Class<T> entityClass) {
/* 19 */     this.minecraft = minecraft;
/* 20 */     this.entityClass = entityClass;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 26 */     ClientLevel world = this.minecraft.f_91073_;
/*    */     
/* 28 */     for (Entity entity : world.m_104735_()) {
/*    */       
/* 30 */       if (this.entityClass.isInstance(entity)) {
/*    */ 
/*    */         
/* 33 */         Entity entity1 = entity;
/* 34 */         if (canPlay((T)entity1)) {
/* 35 */           putLoop(create((T)entity1));
/*    */         }
/*    */       } 
/*    */     } 
/* 39 */     for (int i = 0; i < this.loops.size(); i++) {
/*    */       
/* 41 */       AbstractTickableSoundInstance abstractTickableSoundInstance = (AbstractTickableSoundInstance)this.loops.get(i);
/* 42 */       if (abstractTickableSoundInstance.m_7801_() || (abstractTickableSoundInstance instanceof FadingSoundLoop && ((FadingSoundLoop)abstractTickableSoundInstance).isStopping())) {
/* 43 */         this.loops.remove(i);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract boolean alreadyHasLoop(T paramT);
/*    */   
/*    */   protected void putLoop(L loop) {
/* 51 */     if (!this.loops.contains(loop)) {
/*    */       
/* 53 */       this.loops.add(loop);
/* 54 */       this.minecraft.m_91106_().m_120372_((TickableSoundInstance)loop);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean canPlay(T entity) {
/* 60 */     return (!alreadyHasLoop(entity) && entity.m_6084_());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void refresh() {
/* 66 */     List<L> soundsToAdd = new ArrayList<>();
/* 67 */     for (int i = 0; i < this.loops.size(); i++) {
/*    */       
/* 69 */       AbstractTickableSoundInstance abstractTickableSoundInstance = (AbstractTickableSoundInstance)this.loops.get(i);
/* 70 */       L newLoop = copyFrom((L)abstractTickableSoundInstance);
/* 71 */       ((IForceStoppableSound)abstractTickableSoundInstance).forceStop();
/* 72 */       soundsToAdd.add(newLoop);
/* 73 */       this.minecraft.m_91106_().m_120372_((TickableSoundInstance)newLoop);
/*    */     } 
/* 75 */     this.loops.clear();
/* 76 */     this.loops.addAll(soundsToAdd);
/*    */   }
/*    */   
/*    */   protected abstract L create(T paramT);
/*    */   
/*    */   protected abstract L copyFrom(L paramL);
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\EntitySoundManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */