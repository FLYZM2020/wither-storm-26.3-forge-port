/*    */ package nonamecrackers2.witherstormmod.client.resources.color;public final class SkyColorSet extends Record { private final Color skyColor; private final Color cloudColor; private final Color fogColor; @Nullable
/*    */   private final Color nightSkyColor; @Nullable
/*    */   private final Color nightCloudColor;
/*    */   @Nullable
/*    */   private final Color nightFogColor;
/*    */   
/*  7 */   public SkyColorSet(Color skyColor, Color cloudColor, Color fogColor, @Nullable Color nightSkyColor, @Nullable Color nightCloudColor, @Nullable Color nightFogColor) { this.skyColor = skyColor; this.cloudColor = cloudColor; this.fogColor = fogColor; this.nightSkyColor = nightSkyColor; this.nightCloudColor = nightCloudColor; this.nightFogColor = nightFogColor; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/client/resources/color/SkyColorSet;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #7	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*  7 */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/client/resources/color/SkyColorSet; } public Color skyColor() { return this.skyColor; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/client/resources/color/SkyColorSet;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #7	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/client/resources/color/SkyColorSet; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/client/resources/color/SkyColorSet;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #7	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnonamecrackers2/witherstormmod/client/resources/color/SkyColorSet;
/*  7 */     //   0	8	1	o	Ljava/lang/Object; } public Color cloudColor() { return this.cloudColor; } public Color fogColor() { return this.fogColor; } @Nullable public Color nightSkyColor() { return this.nightSkyColor; } @Nullable public Color nightCloudColor() { return this.nightCloudColor; } @Nullable public Color nightFogColor() { return this.nightFogColor; }
/*    */   
/*  9 */   public static final SkyColorSet DEFAULT_SKY_COLORS = new SkyColorSet(new Color(20, 0, 19), new Color(28, 10, 27), new Color(133, 69, 62));
/*    */ 
/*    */   
/*    */   public SkyColorSet(Color skyColor, Color cloudColor, Color fogColor) {
/* 13 */     this(skyColor, cloudColor, fogColor, null, null, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Builder builder() {
/* 18 */     return new Builder();
/*    */   }
/*    */   
/*    */   public static class Builder
/*    */   {
/* 23 */     private Color skyDarkenColor = SkyColorSet.DEFAULT_SKY_COLORS.skyColor();
/* 24 */     private Color skyDarkenCloudColor = SkyColorSet.DEFAULT_SKY_COLORS.cloudColor();
/* 25 */     private Color skyDarkenFogColor = SkyColorSet.DEFAULT_SKY_COLORS.fogColor();
/*    */     @Nullable
/*    */     private Color nightSkyDarkenColor;
/*    */     @Nullable
/*    */     private Color nightSkyDarkenCloudColor;
/*    */     @Nullable
/*    */     private Color nightSkyDarkenFogColor;
/*    */     
/*    */     public Builder setSkyColor(Color color) {
/* 34 */       this.skyDarkenColor = color;
/* 35 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setCloudColor(Color color) {
/* 40 */       this.skyDarkenCloudColor = color;
/* 41 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setFogColor(Color color) {
/* 46 */       this.skyDarkenFogColor = color;
/* 47 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setNightSkyColor(Color color) {
/* 52 */       this.nightSkyDarkenColor = color;
/* 53 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setNightCloudColor(Color color) {
/* 58 */       this.nightSkyDarkenCloudColor = color;
/* 59 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setNightFogColor(Color color) {
/* 64 */       this.nightSkyDarkenFogColor = color;
/* 65 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public SkyColorSet build() {
/* 70 */       return new SkyColorSet(this.skyDarkenColor, this.skyDarkenCloudColor, this.skyDarkenFogColor, this.nightSkyDarkenColor, this.nightSkyDarkenCloudColor, this.nightSkyDarkenFogColor);
/*    */     }
/*    */   } }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\resources\color\SkyColorSet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */