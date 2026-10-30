/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ 
/*    */ public class WitherStormBossLoop
/*    */   extends FadingSoundLoop
/*    */   implements IForceStoppableSound {
/*    */   private WitherStormEntity entity;
/*    */   
/*    */   public WitherStormBossLoop(WitherStormEntity entity, SoundEvent event) {
/* 16 */     super(event, SoundSource.RECORDS);
/* 17 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public WitherStormBossLoop(WitherStormEntity entity) {
/* 22 */     this(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BOSS_THEME.get());
/*    */   }
/*    */ 
/*    */   
/*    */   public WitherStormEntity getEntity() {
/* 27 */     return this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7788_() {
/* 33 */     if (this.entity == null) {
/*    */       
/* 35 */       m_119609_();
/*    */       
/*    */       return;
/*    */     } 
/* 39 */     this.f_119575_ = this.entity.m_20185_();
/* 40 */     this.f_119576_ = this.entity.m_20186_();
/* 41 */     this.f_119577_ = this.entity.m_20189_();
/*    */     
/* 43 */     if (!this.entity.shouldPlayBossTheme()) {
/*    */       
/* 45 */       ClientLevel world = (ClientLevel)this.entity.m_9236_();
/* 46 */       boolean flag = false;
/* 47 */       for (Entity entity : world.m_104735_()) {
/*    */         
/* 49 */         if (entity instanceof WitherStormEntity) {
/*    */           
/* 51 */           WitherStormEntity storm = (WitherStormEntity)entity;
/* 52 */           if (storm.shouldPlayBossTheme()) {
/*    */             
/* 54 */             this.entity = storm;
/* 55 */             flag = true;
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/* 60 */       if (!flag) {
/* 61 */         stopSound();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 68 */     m_119609_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getFadeTime() {
/* 74 */     return 240;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\WitherStormBossLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */