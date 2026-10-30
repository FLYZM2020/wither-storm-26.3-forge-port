/*     */ package nonamecrackers2.witherstormmod.api.common.entity;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface WitherStormBase
/*     */ {
/*     */   float getMouthAnimation(int paramInt, float paramFloat);
/*     */   
/*     */   float getBrokenJawAnimation(int paramInt, float paramFloat);
/*     */   
/*     */   float getFadeAnimation(float paramFloat);
/*     */   
/*     */   float getFadeAnimation();
/*     */   
/*     */   float getTentacleAnimation(float paramFloat);
/*     */   
/*     */   Vec3 getHeadPos(int paramInt);
/*     */   
/*     */   float getHeadYRot(int paramInt);
/*     */   
/*     */   float getHeadYRotO(int paramInt);
/*     */   
/*     */   float getHeadXRot(int paramInt);
/*     */   
/*     */   float getHeadXRotO(int paramInt);
/*     */   
/*     */   float getXBodyRot();
/*     */   
/*     */   float getXBodyRotO();
/*     */   
/*     */   boolean areOtherHeadsDisabled();
/*     */   
/*     */   boolean isHeadInjured(int paramInt);
/*     */   
/*     */   default boolean canBeDistracted(int head, DistractionType type) {
/*  43 */     return tractorBeamActive(head);
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isDistracted(int head) {
/*  48 */     return (getDistractedPos(head) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   Vec3 getDistractedPos(int paramInt);
/*     */ 
/*     */ 
/*     */   
/*     */   void setDistractedPos(int paramInt, @Nullable Vec3 paramVec3);
/*     */ 
/*     */ 
/*     */   
/*     */   void makeDistracted(Vec3 paramVec3, int paramInt1, int paramInt2);
/*     */ 
/*     */ 
/*     */   
/*     */   void setLookAt(int paramInt1, @Nullable Vec3 paramVec3, int paramInt2);
/*     */ 
/*     */ 
/*     */   
/*     */   float getHeadShakeAnim(int paramInt, float paramFloat);
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean isEntityBehindBack(Entity entity) {
/*  75 */     return isPosBehindBack(entity.m_20182_());
/*     */   } @Nullable
/*     */   LivingEntity getTarget(int paramInt); void setTarget(int paramInt, @Nullable LivingEntity paramLivingEntity); boolean canSee(int paramInt, Entity paramEntity); boolean isPosBehindBack(Vec3 paramVec3); boolean isDeadOrPlayingDead();
/*     */   boolean isPlayingDead();
/*     */   default void setLookAt(int head, @Nullable Vec3 pos) {
/*  80 */     setLookAt(head, pos, 3);
/*     */   }
/*     */ 
/*     */   
/*     */   default int getTotalHeads() {
/*  85 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   default double getTractorBeamCutoffDistance(int head) {
/*  90 */     return -1.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean tractorBeamActive(int head) {
/*  95 */     boolean flag = false;
/*  96 */     if (areOtherHeadsDisabled()) {
/*  97 */       flag = (head == 0);
/*     */     } else {
/*  99 */       flag = true;
/* 100 */     }  return (flag && !isHeadInjured(head));
/*     */   }
/*     */ 
/*     */   
/*     */   default Vec3 getViewVector(float x, float y, float range) {
/* 105 */     float f = x * 0.017453292F;
/* 106 */     float f1 = -y * 0.017453292F;
/* 107 */     float f2 = Mth.m_14089_(f1);
/* 108 */     float f3 = Mth.m_14031_(f1);
/* 109 */     float f4 = Mth.m_14089_(f);
/* 110 */     float f5 = Mth.m_14031_(f);
/* 111 */     return new Vec3((f3 * f4) * range, -f5 * range, (f2 * f4) * range);
/*     */   }
/*     */   
/*     */   public enum DistractionType
/*     */   {
/* 116 */     ENTITY_BASED,
/* 117 */     STRUCTURES;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\entity\WitherStormBase.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */