/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.world.entity.schedule.Activity;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ 
/*    */ public class WitherStormModActivities
/*    */ {
/* 11 */   public static final DeferredRegister<Activity> ACTIVITIES = DeferredRegister.create(ForgeRegistries.ACTIVITIES, "witherstormmod");
/*    */   
/* 13 */   public static final RegistryObject<Activity> WITHER_STORM_PANIC = ACTIVITIES.register("wither_storm_panic", () -> new Activity("wither_storm_panic"));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModActivities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */