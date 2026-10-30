/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.world.flag.FeatureFlags;
/*    */ import net.minecraft.world.inventory.MenuType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.SuperBeaconMenu;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.SuperSupportBeaconMenu;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.WitheredPhlegmMenu;
/*    */ 
/*    */ 
/*    */ public class WitherStormModMenuTypes
/*    */ {
/* 15 */   public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "witherstormmod");
/*    */   
/* 17 */   public static final RegistryObject<MenuType<SuperBeaconMenu>> SUPER_BEACON = MENU_TYPES.register("super_beacon", () -> new MenuType(SuperBeaconMenu::new, FeatureFlags.f_244332_));
/* 18 */   public static final RegistryObject<MenuType<SuperSupportBeaconMenu>> SUPER_SUPPORT_BEACON = MENU_TYPES.register("super_support_beacon", () -> new MenuType(SuperSupportBeaconMenu::new, FeatureFlags.f_244332_));
/* 19 */   public static final RegistryObject<MenuType<WitheredPhlegmMenu>> WITHERED_PHLEGM = MENU_TYPES.register("withered_phlegm", () -> new MenuType(WitheredPhlegmMenu::new, FeatureFlags.f_244332_));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModMenuTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */