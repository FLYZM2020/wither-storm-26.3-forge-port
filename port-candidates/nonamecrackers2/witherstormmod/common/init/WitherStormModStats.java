/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.core.Registry;
/*    */ import net.minecraft.core.registries.BuiltInRegistries;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.stats.StatFormatter;
/*    */ import net.minecraft.stats.Stats;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModStats
/*    */ {
/*    */   public static Identifier INTERACT_WITH_SUPER_BEACON;
/*    */   
/*    */   public static void register() {
/* 16 */     INTERACT_WITH_SUPER_BEACON = makeCustomStat("interact_with_super_beacon", StatFormatter.DEFAULT);
/*    */   }
/*    */ 
/*    */   
/*    */   private static Identifier makeCustomStat(String id, StatFormatter formatter) {
/* 21 */     Identifier rl = Identifier.fromNamespaceAndPath("witherstormmod", id);
/* 22 */     Registry.register(BuiltInRegistries.CUSTOM_STAT, rl, rl);
/* 23 */     Stats.CUSTOM.get(rl, formatter);
/* 24 */     return rl;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModStats.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */