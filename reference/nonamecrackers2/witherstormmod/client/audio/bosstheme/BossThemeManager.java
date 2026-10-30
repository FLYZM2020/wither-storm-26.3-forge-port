/*     */ package nonamecrackers2.witherstormmod.client.audio.bosstheme;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.client.resources.sounds.SoundInstance;
/*     */ import net.minecraft.client.resources.sounds.TickableSoundInstance;
/*     */ import net.minecraft.client.sounds.SoundEngine;
/*     */ import net.minecraft.client.sounds.SoundManager;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BossThemeEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ import nonamecrackers2.witherstormmod.mixin.MixinSoundEngineAccessor;
/*     */ import nonamecrackers2.witherstormmod.mixin.MixinSoundManagerAccessor;
/*     */ 
/*     */ public class BossThemeManager implements ISoundManager {
/*     */   public static final int WATERMARK_TIME = 160;
/*     */   protected final Minecraft minecraft;
/*     */   @Nullable
/*     */   protected BossThemeLoop theme;
/*     */   protected Component watermark;
/*     */   protected int watermarkTime;
/*     */   protected int startTime;
/*     */   
/*     */   public BossThemeManager(Minecraft minecraft) {
/*  30 */     this.minecraft = minecraft;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossThemeManager() {
/*  35 */     this(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  41 */     ClientLevel world = this.minecraft.f_91073_;
/*  42 */     SoundManager manager = this.minecraft.m_91106_();
/*     */     
/*  44 */     for (Entity entity : world.m_104735_()) {
/*     */       
/*  46 */       if (entity instanceof BossThemeEntity) {
/*     */         
/*  48 */         BossThemeEntity bossEntity = (BossThemeEntity)entity;
/*     */         
/*  50 */         if (this.minecraft.f_91066_.m_92147_(bossEntity.getCategory()) > 0.0F && this.minecraft.f_91066_.m_92147_(SoundSource.MASTER) > 0.0F)
/*     */         {
/*  52 */           if (bossEntity.shouldPlayBossTheme() && inRange(bossEntity, this.minecraft.f_91074_) && hasGeneralAccessTo(bossEntity, this.minecraft.f_91074_)) {
/*     */             
/*  54 */             BossThemeEntity current = getEntity();
/*  55 */             if (current != null) {
/*     */               
/*  57 */               if (current == bossEntity) {
/*     */                 
/*  59 */                 this.theme.continueSound(); continue;
/*     */               } 
/*  61 */               if (bossEntity.hasPriority(current)) {
/*     */                 
/*  63 */                 if (this.theme != null)
/*  64 */                   this.theme.stopSound(); 
/*  65 */                 playLoop(bossEntity);
/*     */               } 
/*     */               
/*     */               continue;
/*     */             } 
/*  70 */             playLoop(bossEntity);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     SoundEngine engine = ((MixinSoundManagerAccessor)manager).witherstormmod$getSoundEngine();
/*  88 */     if (this.theme != null)
/*     */     {
/*  90 */       if (this.theme.m_7801_()) {
/*     */         
/*  92 */         this.theme = null;
/*     */       }
/*  94 */       else if (!manager.m_120403_((SoundInstance)this.theme) && !((MixinSoundEngineAccessor)engine).witherstormmod$getQueuedTickableSounds().contains(this.theme)) {
/*     */         
/*  96 */         this.theme = null;
/*     */       }
/*  98 */       else if (this.minecraft.f_91066_.m_92147_(this.theme.m_8070_()) <= 0.0F || this.minecraft.f_91066_.m_92147_(SoundSource.MASTER) <= 0.0F) {
/*     */         
/* 100 */         this.theme.forceStop();
/* 101 */         this.theme = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     if (this.theme != null)
/*     */     {
/* 113 */       if (!this.theme.event.equals(this.theme.getEntity().getBossTheme())) {
/*     */         
/* 115 */         this.theme.stopSound();
/* 116 */         playLoop(this.theme.getEntity());
/*     */       } 
/*     */     }
/*     */     
/* 120 */     if (this.watermarkTime > 0)
/* 121 */       this.watermarkTime--; 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BossThemeEntity getEntity() {
/* 126 */     if (this.theme != null) {
/* 127 */       return this.theme.getEntity();
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void playLoop(BossThemeEntity entity) {
/* 134 */     this.theme = new BossThemeLoop(entity);
/* 135 */     this.minecraft.m_91106_().m_120372_((TickableSoundInstance)this.theme);
/* 136 */     this.watermark = entity.getWatermark();
/* 137 */     this.watermarkTime = 160;
/* 138 */     this.startTime = 160;
/*     */   }
/*     */ 
/*     */   
/*     */   public void forceStop() {
/* 143 */     if (this.theme != null) {
/* 144 */       this.theme.forceStop();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 150 */     if (this.theme != null && !this.theme.m_7801_() && !this.theme.isStopping()) {
/*     */       
/* 152 */       this.theme.forceStop();
/* 153 */       playLoop(this.theme.getEntity());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlaying() {
/* 159 */     return (this.theme != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean inRange(BossThemeEntity entity, LocalPlayer player) {
/* 164 */     boolean flag = true;
/* 165 */     if (entity.distanceToPlay() > 0.0D)
/* 166 */       flag = (Math.sqrt(player.m_20275_(entity.getPosition().m_7096_(), entity.getPosition().m_7098_(), entity.getPosition().m_7094_())) < entity.distanceToPlay()); 
/* 167 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean hasGeneralAccessTo(BossThemeEntity theme, LocalPlayer player) {
/* 172 */     if (theme.smartBossMusic()) {
/* 173 */       return WorldUtil.canSeeOrIsNotInASmallArea((Entity)theme, (Entity)player);
/*     */     }
/* 175 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Component getWatermark() {
/* 180 */     return this.watermark;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWatermarkTime() {
/* 185 */     return this.watermarkTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWatermarkStartTime() {
/* 190 */     return this.startTime;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\bosstheme\BossThemeManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */