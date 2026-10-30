/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.resources.sounds.SoundInstance;
/*    */ import net.minecraft.client.sounds.MusicManager;
/*    */ import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeManager;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({MusicManager.class})
/*    */ public abstract class MixinMusicManager
/*    */ {
/*    */   @Final
/*    */   @Shadow
/*    */   private Minecraft minecraft;
/*    */   @Shadow
/*    */   @Nullable
/*    */   private SoundInstance currentMusic;
/*    */   
/*    */   @Inject(method = {"tick"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void witherstormmod$stopCurrentMusicIfBossMusicPlaying_tick(CallbackInfo ci) {
/* 37 */     if (WitherStormModConfig.CLIENT_SPEC.isLoaded() && !((Boolean)WitherStormModConfig.CLIENT.playMinecraftMusic.get()).booleanValue()) {
/*    */       
/* 39 */       if (this.currentMusic != null)
/* 40 */         stopPlaying(); 
/* 41 */       ci.cancel();
/*    */     } 
/* 43 */     ClientLevel level = this.minecraft.level;
/* 44 */     if (level != null) {
/*    */       
/* 46 */       BossThemeManager manager = (BossThemeManager)level.getCapability(WitherStormModClientCapabilities.BOSS_THEME_MANAGER).orElse(null);
/* 47 */       if (manager != null && manager.isPlaying()) {
/*    */         
/* 49 */         if (this.currentMusic != null)
/* 50 */           stopPlaying(); 
/* 51 */         ci.cancel();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   public abstract void stopPlaying();
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinMusicManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */