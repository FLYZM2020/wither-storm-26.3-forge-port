/*    */ package nonamecrackers2.witherstormmod.client.audio.bosstheme;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.resources.sounds.SoundInstance;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.audio.FadingSoundLoop;
/*    */ import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
/*    */ import nonamecrackers2.witherstormmod.common.entity.BossThemeEntity;
/*    */ 
/*    */ public class BossThemeLoop
/*    */   extends FadingSoundLoop
/*    */   implements IForceStoppableSound {
/*    */   private final Minecraft minecraft;
/*    */   protected BossThemeEntity entity;
/*    */   protected final SoundEvent event;
/*    */   
/*    */   public BossThemeLoop(BossThemeEntity entity) {
/* 20 */     super(entity.getBossTheme(), entity.getCategory());
/* 21 */     this.f_119582_ = true;
/* 22 */     this.f_119580_ = SoundInstance.Attenuation.NONE;
/* 23 */     this.minecraft = Minecraft.m_91087_();
/* 24 */     this.entity = entity;
/* 25 */     this.event = entity.getBossTheme();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BossThemeLoop(BossThemeEntity entity, SoundEvent event) {
/* 30 */     super(event, entity.getCategory());
/* 31 */     this.minecraft = Minecraft.m_91087_();
/* 32 */     this.entity = entity;
/* 33 */     this.event = event;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7788_() {
/* 39 */     super.m_7788_();
/*    */     
/* 41 */     this.f_119575_ = this.entity.getPosition().m_7096_();
/* 42 */     this.f_119576_ = this.entity.getPosition().m_7098_();
/* 43 */     this.f_119577_ = this.entity.getPosition().m_7094_();
/* 44 */     if (!this.entity.shouldPlayBossTheme() || !BossThemeManager.inRange(this.entity, this.minecraft.f_91074_) || !BossThemeManager.hasGeneralAccessTo(this.entity, this.minecraft.f_91074_)) {
/*    */       
/* 46 */       ClientLevel world = this.minecraft.f_91073_;
/* 47 */       boolean flag = false;
/* 48 */       for (Entity entity : world.m_104735_()) {
/*    */         
/* 50 */         if (entity instanceof BossThemeEntity) {
/*    */           
/* 52 */           BossThemeEntity bossEntity = (BossThemeEntity)entity;
/* 53 */           if (bossEntity.shouldPlayBossTheme() && this.entity.matches(bossEntity) && BossThemeManager.inRange(bossEntity, this.minecraft.f_91074_) && BossThemeManager.hasGeneralAccessTo(bossEntity, this.minecraft.f_91074_)) {
/*    */             
/* 55 */             this.entity = bossEntity;
/* 56 */             flag = true;
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/* 61 */       if (!flag) {
/* 62 */         stopSound();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getFadeTime() {
/* 69 */     return this.entity.getFadeTime();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 75 */     m_119609_();
/*    */   }
/*    */ 
/*    */   
/*    */   public BossThemeEntity getEntity() {
/* 80 */     return this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\bosstheme\BossThemeLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */