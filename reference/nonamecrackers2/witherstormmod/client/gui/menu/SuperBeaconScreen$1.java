/*    */ package nonamecrackers2.witherstormmod.client.gui.menu;
/*    */ 
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.inventory.ContainerListener;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.AbstractSuperBeaconMenu;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements ContainerListener
/*    */ {
/*    */   public void m_7934_(AbstractContainerMenu container, int slot, ItemStack stack) {}
/*    */   
/*    */   public void m_142153_(AbstractContainerMenu container, int slot, int value) {
/* 73 */     SuperBeaconScreen.this.primary = menu.getPrimaryEffect();
/* 74 */     SuperBeaconScreen.this.level = menu.getLevel();
/* 75 */     SuperBeaconScreen.this.shouldShowArea = menu.shouldShowArea();
/* 76 */     SuperBeaconScreen.this.setEffectCooldown = menu.getCooldown();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\menu\SuperBeaconScreen$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */