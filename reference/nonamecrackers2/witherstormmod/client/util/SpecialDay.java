/*    */ package nonamecrackers2.witherstormmod.client.util;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.time.temporal.ChronoField;
/*    */ import java.util.function.BiFunction;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.Mth;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ public enum SpecialDay
/*    */ {
/*    */   CHRISTMAS,
/* 15 */   HALLOWEEN(new Color(135, 82, 28), isDate(10, 31));
/*    */   
/*    */   private final BiFunction<Integer, Integer, Boolean> shouldShow;
/*    */   private final ColorGetter color;
/*    */   
/*    */   static {
/* 21 */     CHRISTMAS = new SpecialDay("CHRISTMAS", 1, (w, p, h) -> { float tickCount = ((w.f_19797_ + h * 1000 + w.m_19879_() * 1000) + p) / 100.0F; float r = Mth.m_14116_(Math.max(Mth.m_14031_(tickCount), 0.0F)); float g = Mth.m_14116_(Math.max(Mth.m_14031_(tickCount + 3.1415927F), 0.0F)); return (ColorGetter)new Color(r * 0.5F, g * 0.5F, 0.0F); }isDayInMonth(12, new int[] { 24, 25 }));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SpecialDay(ColorGetter color, BiFunction<Integer, Integer, Boolean> shouldShow) {
/* 28 */     this.color = color;
/* 29 */     this.shouldShow = shouldShow;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Color getColor(WitherStormEntity storm, float partialTicks, int head) {
/* 39 */     return this.color.getColor(storm, partialTicks, head);
/*    */   }
/*    */ 
/*    */   
/*    */   private static BiFunction<Integer, Integer, Boolean> isDate(int month, int day) {
/* 44 */     return (m, d) -> Boolean.valueOf(
/* 45 */         (m.intValue() == month && d.intValue() == day));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static BiFunction<Integer, Integer, Boolean> isDayInMonth(int month, int... days) {
/* 51 */     return (m, d) -> {
/*    */         if (m.intValue() == month) {
/*    */           for (int day : days) {
/*    */             if (d.intValue() == day) {
/*    */               return Boolean.valueOf(true);
/*    */             }
/*    */           } 
/*    */         }
/*    */         return Boolean.valueOf(false);
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static SpecialDay getForCurrentDate() {
/* 67 */     int month = WitherStormMod.DATE.get(ChronoField.MONTH_OF_YEAR);
/* 68 */     int day = WitherStormMod.DATE.get(ChronoField.DAY_OF_MONTH);
/* 69 */     for (SpecialDay colors : values()) {
/*    */       
/* 71 */       if (((Boolean)colors.shouldShow.apply(Integer.valueOf(month), Integer.valueOf(day))).booleanValue())
/* 72 */         return colors; 
/*    */     } 
/* 74 */     return null;
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ColorGetter {
/*    */     Color getColor(WitherStormEntity param1WitherStormEntity, float param1Float, int param1Int);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\SpecialDay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */