/*    */ package nonamecrackers2.witherstormmod.client.resources.color;
/*    */ 
/*    */ import java.awt.Color;
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
/* 16 */   private Color tractorBeamColor = ColorSet.DEFAULT.tractorBeamColor;
/* 17 */   private Color tractorBeamNightColor = ColorSet.DEFAULT.tractorBeamNightColor;
/* 18 */   private Color nightShineColor = ColorSet.DEFAULT.nightShineColor;
/* 19 */   private SkyColorSet skyColors = ColorSet.DEFAULT.skyColors;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Color getTractorBeamColor() {
/* 25 */     return this.tractorBeamColor;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getTractorBeamNightColor() {
/* 30 */     return this.tractorBeamNightColor;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getNightShineColor() {
/* 35 */     return this.nightShineColor;
/*    */   }
/*    */ 
/*    */   
/*    */   public SkyColorSet getSkyColors() {
/* 40 */     return this.skyColors;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setTractorBeamColor(Color color) {
/* 45 */     this.tractorBeamColor = color;
/* 46 */     this.tractorBeamNightColor = color;
/* 47 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setTractorBeamNightColor(Color color) {
/* 52 */     this.tractorBeamNightColor = color;
/* 53 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setNightShineColor(Color color) {
/* 58 */     this.nightShineColor = color;
/* 59 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setSkyColors(SkyColorSet colors) {
/* 64 */     this.skyColors = colors;
/* 65 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder copy() {
/* 70 */     return (new Builder()).setTractorBeamColor(this.tractorBeamColor).setNightShineColor(this.nightShineColor).setTractorBeamNightColor(this.tractorBeamNightColor).setSkyColors(this.skyColors);
/*    */   }
/*    */ 
/*    */   
/*    */   public ColorSet build() {
/* 75 */     return new ColorSet(this.tractorBeamColor, this.tractorBeamNightColor, this.nightShineColor, this.skyColors);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\resources\color\ColorSet$Builder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */