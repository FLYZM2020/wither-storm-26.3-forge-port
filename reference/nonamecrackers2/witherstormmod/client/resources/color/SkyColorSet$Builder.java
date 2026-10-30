/*    */ package nonamecrackers2.witherstormmod.client.resources.color;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Builder
/*    */ {
/* 23 */   private Color skyDarkenColor = SkyColorSet.DEFAULT_SKY_COLORS.skyColor();
/* 24 */   private Color skyDarkenCloudColor = SkyColorSet.DEFAULT_SKY_COLORS.cloudColor();
/* 25 */   private Color skyDarkenFogColor = SkyColorSet.DEFAULT_SKY_COLORS.fogColor();
/*    */   @Nullable
/*    */   private Color nightSkyDarkenColor;
/*    */   @Nullable
/*    */   private Color nightSkyDarkenCloudColor;
/*    */   @Nullable
/*    */   private Color nightSkyDarkenFogColor;
/*    */   
/*    */   public Builder setSkyColor(Color color) {
/* 34 */     this.skyDarkenColor = color;
/* 35 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setCloudColor(Color color) {
/* 40 */     this.skyDarkenCloudColor = color;
/* 41 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setFogColor(Color color) {
/* 46 */     this.skyDarkenFogColor = color;
/* 47 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setNightSkyColor(Color color) {
/* 52 */     this.nightSkyDarkenColor = color;
/* 53 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setNightCloudColor(Color color) {
/* 58 */     this.nightSkyDarkenCloudColor = color;
/* 59 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setNightFogColor(Color color) {
/* 64 */     this.nightSkyDarkenFogColor = color;
/* 65 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public SkyColorSet build() {
/* 70 */     return new SkyColorSet(this.skyDarkenColor, this.skyDarkenCloudColor, this.skyDarkenFogColor, this.nightSkyDarkenColor, this.nightSkyDarkenCloudColor, this.nightSkyDarkenFogColor);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\resources\color\SkyColorSet$Builder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */