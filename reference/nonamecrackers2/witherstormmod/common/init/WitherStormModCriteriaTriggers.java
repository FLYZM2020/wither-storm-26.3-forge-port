/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.common.advancements.criterion.ActivateSuperBeaconTrigger;
/*    */ import nonamecrackers2.witherstormmod.common.advancements.criterion.CuredSickenedMobTrigger;
/*    */ import nonamecrackers2.witherstormmod.common.advancements.criterion.LinkAmuletTrigger;
/*    */ import nonamecrackers2.witherstormmod.common.advancements.criterion.SummonMobSuperBeaconTrigger;
/*    */ import nonamecrackers2.witherstormmod.common.advancements.criterion.WitherStormTrigger;
/*    */ 
/*    */ public class WitherStormModCriteriaTriggers
/*    */ {
/* 13 */   public static final WitherStormTrigger PLAY_DEAD_TRIGGER = new WitherStormTrigger(new ResourceLocation("witherstormmod", "wither_storm_play_dead"));
/* 14 */   public static final WitherStormTrigger REVIVAL_TRIGGER = new WitherStormTrigger(new ResourceLocation("witherstormmod", "wither_storm_revival"));
/* 15 */   public static final WitherStormTrigger ESCAPE_STORM = new WitherStormTrigger(new ResourceLocation("witherstormmod", "escape_wither_storm"));
/* 16 */   public static final WitherStormTrigger RING_BELL_NEAR_STORM = new WitherStormTrigger(new ResourceLocation("witherstormmod", "ring_bell_near_storm"));
/* 17 */   public static final WitherStormTrigger NEARLY_KILL_WITHER_STORM = new WitherStormTrigger(WitherStormMod.id("nearly_kill_wither_storm"));
/* 18 */   public static final CuredSickenedMobTrigger CURED_SICKENED_MOB = new CuredSickenedMobTrigger();
/* 19 */   public static final ActivateSuperBeaconTrigger ACTIVATE_SUPER_BEACON = new ActivateSuperBeaconTrigger();
/* 20 */   public static final SummonMobSuperBeaconTrigger SUMMON_MOB_SUPER_BEACON = new SummonMobSuperBeaconTrigger();
/* 21 */   public static final LinkAmuletTrigger LINK_AMULET = new LinkAmuletTrigger();
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModCriteriaTriggers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */