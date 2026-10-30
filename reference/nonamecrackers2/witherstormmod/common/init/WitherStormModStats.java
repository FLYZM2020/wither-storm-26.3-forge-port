/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.core.Registry;
/*    */ import net.minecraft.core.registries.BuiltInRegistries;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.stats.StatFormatter;
/*    */ import net.minecraft.stats.Stats;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModStats
/*    */ {
/*    */   public static ResourceLocation INTERACT_WITH_SUPER_BEACON;
/*    */   
/*    */   public static void register() {
/* 16 */     INTERACT_WITH_SUPER_BEACON = makeCustomStat("interact_with_super_beacon", StatFormatter.f_12873_);
/*    */   }
/*    */ 
/*    */   
/*    */   private static ResourceLocation makeCustomStat(String id, StatFormatter formatter) {
/* 21 */     ResourceLocation rl = new ResourceLocation("witherstormmod", id);
/* 22 */     Registry.m_122965_(BuiltInRegistries.f_256771_, rl, rl);
/* 23 */     Stats.f_12988_.m_12899_(rl, formatter);
/* 24 */     return rl;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModStats.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */