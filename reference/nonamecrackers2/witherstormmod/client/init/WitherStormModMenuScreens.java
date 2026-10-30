/*    */ package nonamecrackers2.witherstormmod.client.init;
/*    */ 
/*    */ import net.minecraft.client.gui.screens.MenuScreens;
/*    */ import net.minecraft.world.inventory.MenuType;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMenuTypes;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModMenuScreens
/*    */ {
/*    */   public static void register() {
/* 12 */     MenuScreens.m_96206_((MenuType)WitherStormModMenuTypes.SUPER_BEACON.get(), nonamecrackers2.witherstormmod.client.gui.menu.SuperBeaconScreen::new);
/* 13 */     MenuScreens.m_96206_((MenuType)WitherStormModMenuTypes.SUPER_SUPPORT_BEACON.get(), nonamecrackers2.witherstormmod.client.gui.menu.SuperBeaconScreen::new);
/* 14 */     MenuScreens.m_96206_((MenuType)WitherStormModMenuTypes.WITHERED_PHLEGM.get(), nonamecrackers2.witherstormmod.client.gui.menu.WitheredPhlegmScreen::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\init\WitherStormModMenuScreens.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */