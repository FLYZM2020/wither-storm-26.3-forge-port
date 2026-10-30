/*    */ package nonamecrackers2.witherstormmod.client.resources.color;public final class ColorSet extends Record { private final Color tractorBeamColor; private final Color tractorBeamNightColor;
/*    */   private final Color nightShineColor;
/*    */   private final SkyColorSet skyColors;
/*    */   
/*  5 */   public ColorSet(Color tractorBeamColor, Color tractorBeamNightColor, Color nightShineColor, SkyColorSet skyColors) { this.tractorBeamColor = tractorBeamColor; this.tractorBeamNightColor = tractorBeamNightColor; this.nightShineColor = nightShineColor; this.skyColors = skyColors; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/client/resources/color/ColorSet;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #5	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*  5 */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/client/resources/color/ColorSet; } public Color tractorBeamColor() { return this.tractorBeamColor; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/client/resources/color/ColorSet;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #5	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/client/resources/color/ColorSet; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/client/resources/color/ColorSet;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #5	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnonamecrackers2/witherstormmod/client/resources/color/ColorSet;
/*  5 */     //   0	8	1	o	Ljava/lang/Object; } public Color tractorBeamNightColor() { return this.tractorBeamNightColor; } public Color nightShineColor() { return this.nightShineColor; } public SkyColorSet skyColors() { return this.skyColors; }
/*    */   
/*  7 */   public static final ColorSet DEFAULT = new ColorSet(new Color(128, 77, 204), new Color(128, 77, 204), new Color(150, 59, 255, 75), SkyColorSet.DEFAULT_SKY_COLORS);
/*    */ 
/*    */   
/*    */   public static Builder builder() {
/* 11 */     return new Builder();
/*    */   }
/*    */   
/*    */   public static class Builder
/*    */   {
/* 16 */     private Color tractorBeamColor = ColorSet.DEFAULT.tractorBeamColor;
/* 17 */     private Color tractorBeamNightColor = ColorSet.DEFAULT.tractorBeamNightColor;
/* 18 */     private Color nightShineColor = ColorSet.DEFAULT.nightShineColor;
/* 19 */     private SkyColorSet skyColors = ColorSet.DEFAULT.skyColors;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public Color getTractorBeamColor() {
/* 25 */       return this.tractorBeamColor;
/*    */     }
/*    */ 
/*    */     
/*    */     public Color getTractorBeamNightColor() {
/* 30 */       return this.tractorBeamNightColor;
/*    */     }
/*    */ 
/*    */     
/*    */     public Color getNightShineColor() {
/* 35 */       return this.nightShineColor;
/*    */     }
/*    */ 
/*    */     
/*    */     public SkyColorSet getSkyColors() {
/* 40 */       return this.skyColors;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setTractorBeamColor(Color color) {
/* 45 */       this.tractorBeamColor = color;
/* 46 */       this.tractorBeamNightColor = color;
/* 47 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setTractorBeamNightColor(Color color) {
/* 52 */       this.tractorBeamNightColor = color;
/* 53 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setNightShineColor(Color color) {
/* 58 */       this.nightShineColor = color;
/* 59 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setSkyColors(SkyColorSet colors) {
/* 64 */       this.skyColors = colors;
/* 65 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder copy() {
/* 70 */       return (new Builder()).setTractorBeamColor(this.tractorBeamColor).setNightShineColor(this.nightShineColor).setTractorBeamNightColor(this.tractorBeamNightColor).setSkyColors(this.skyColors);
/*    */     }
/*    */ 
/*    */     
/*    */     public ColorSet build() {
/* 75 */       return new ColorSet(this.tractorBeamColor, this.tractorBeamNightColor, this.nightShineColor, this.skyColors);
/*    */     }
/*    */   } }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\resources\color\ColorSet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */