/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ public class DebrisRingSettings
/*    */ {
/*    */   private final int segments;
/*    */   private final float bottomRadius;
/*    */   private final float topRadius;
/*    */   private final float y;
/*    */   private final float height;
/*    */   private final float speedModifier;
/*    */   private final boolean clockwise;
/*    */   private final int phaseRequirement;
/* 13 */   private float alpha = 1.0F;
/*    */ 
/*    */   
/*    */   public DebrisRingSettings(int segments, float bottomRadius, float topRadius, float y, float height, float speedModifier, boolean clockwise, int phase, boolean hidden) {
/* 17 */     this.segments = segments;
/* 18 */     this.bottomRadius = bottomRadius;
/* 19 */     this.topRadius = topRadius;
/* 20 */     this.y = y;
/* 21 */     this.height = y + height;
/* 22 */     this.speedModifier = speedModifier;
/* 23 */     this.clockwise = clockwise;
/* 24 */     this.phaseRequirement = phase;
/* 25 */     if (hidden) {
/* 26 */       this.alpha = 0.0F;
/*    */     }
/*    */   }
/*    */   
/*    */   public int getSegments() {
/* 31 */     return this.segments;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBottomRadius() {
/* 36 */     return this.bottomRadius;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getTopRadius() {
/* 41 */     return this.topRadius;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getY() {
/* 46 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getHeight() {
/* 51 */     return this.height;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getSpeedModifier() {
/* 56 */     return this.speedModifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean clockwise() {
/* 61 */     return this.clockwise;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPhaseRequirement() {
/* 66 */     return this.phaseRequirement;
/*    */   }
/*    */ 
/*    */   
/*    */   public float alpha() {
/* 71 */     return this.alpha;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAlpha(float alpha) {
/* 76 */     this.alpha = alpha;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\DebrisRingSettings.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */