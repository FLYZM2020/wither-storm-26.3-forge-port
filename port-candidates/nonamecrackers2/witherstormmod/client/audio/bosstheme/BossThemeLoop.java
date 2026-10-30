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
/* 21 */     this.relative = true;
/* 22 */     this.attenuation = SoundInstance.Attenuation.NONE;
/* 23 */     this.minecraft = Minecraft.getInstance();
/* 24 */     this.entity = entity;
/* 25 */     this.event = entity.getBossTheme();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BossThemeLoop(BossThemeEntity entity, SoundEvent event) {
/* 30 */     super(event, entity.getCategory());
/* 31 */     this.minecraft = Minecraft.getInstance();
/* 32 */     this.entity = entity;
/* 33 */     this.event = event;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 39 */     super.tick();
/*    */     
/* 41 */     this.x = this.entity.getPosition().x();
/* 42 */     this.y = this.entity.getPosition().y();
/* 43 */     this.z = this.entity.getPosition().z();
/* 44 */     if (!this.entity.shouldPlayBossTheme() || !BossThemeManager.inRange(this.entity, this.minecraft.player) || !BossThemeManager.hasGeneralAccessTo(this.entity, this.minecraft.player)) {
/*    */       
/* 46 */       ClientLevel world = this.minecraft.level;
/* 47 */       boolean flag = false;
/* 48 */       for (Entity entity : world.entitiesForRendering()) {
/*    */         
/* 50 */         if (entity instanceof BossThemeEntity) {
/*    */           
/* 52 */           BossThemeEntity bossEntity = (BossThemeEntity)entity;
/* 53 */           if (bossEntity.shouldPlayBossTheme() && this.entity.matches(bossEntity) && BossThemeManager.inRange(bossEntity, this.minecraft.player) && BossThemeManager.hasGeneralAccessTo(bossEntity, this.minecraft.player)) {
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
/* 75 */     stop();
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