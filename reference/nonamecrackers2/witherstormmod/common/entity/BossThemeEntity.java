/*    */ package nonamecrackers2.witherstormmod.common.entity;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface BossThemeEntity
/*    */ {
/*    */   SoundEvent getBossTheme();
/*    */   
/*    */   default SoundSource getCategory() {
/* 16 */     return SoundSource.MUSIC;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean matches(BossThemeEntity entity) {
/* 21 */     return (getBossTheme() == entity.getBossTheme() && getCategory() == entity.getCategory() && priority() == entity.priority());
/*    */   }
/*    */ 
/*    */   
/*    */   default int getFadeTime() {
/* 26 */     return 240;
/*    */   }
/*    */ 
/*    */   
/*    */   boolean isStillAlive();
/*    */   
/*    */   default boolean shouldPlayBossTheme() {
/* 33 */     return (isStillAlive() && getBossTheme() != null && checkConfig());
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean checkConfig() {
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   default double distanceToPlay() {
/* 43 */     return 0.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   int priority();
/*    */   
/*    */   default boolean hasPriority(BossThemeEntity entity) {
/* 50 */     return (priority() > entity.priority());
/*    */   }
/*    */   
/*    */   Vec3 getPosition();
/*    */   
/*    */   @Nullable
/*    */   default Component getWatermark() {
/* 57 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean smartBossMusic() {
/* 62 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\BossThemeEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */