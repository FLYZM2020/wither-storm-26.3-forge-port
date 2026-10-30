/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.renderer.DimensionSpecialEffects;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BowelsSpecialEffects
/*    */   extends DimensionSpecialEffects.NetherEffects
/*    */ {
/*    */   public Vec3 m_5927_(Vec3 col, float dayCycle) {
/* 14 */     Color color = WitherStormResourceConfigManager.INSTANCE.getBowelsFogColor().orElse(null);
/* 15 */     if (color != null) {
/* 16 */       return new Vec3((color.getRed() / 255.0F), (color.getGreen() / 255.0F), (color.getBlue() / 255.0F));
/*    */     }
/* 18 */     return super.m_5927_(col, dayCycle);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\BowelsSpecialEffects.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */