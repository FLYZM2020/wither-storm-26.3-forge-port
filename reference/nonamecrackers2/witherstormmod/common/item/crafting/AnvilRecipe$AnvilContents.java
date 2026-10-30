/*     */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*     */ 
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnvilContents
/*     */   implements Container
/*     */ {
/*     */   private final ItemStack left;
/*     */   private final ItemStack right;
/*     */   
/*     */   public AnvilContents(ItemStack left, ItemStack right) {
/* 159 */     this.left = left;
/* 160 */     this.right = right;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6211_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_6643_() {
/* 171 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7983_() {
/* 177 */     return (this.left.m_41619_() && this.right.m_41619_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_8020_(int slot) {
/* 183 */     switch (slot) {
/*     */       
/*     */       case 0:
/* 186 */         return this.left;
/*     */       case 1:
/* 188 */         return this.right;
/*     */     } 
/* 190 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_7407_(int slot, int amount) {
/* 197 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_8016_(int slot) {
/* 203 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6836_(int slot, ItemStack stack) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6596_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6542_(Player player) {
/* 219 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\AnvilRecipe$AnvilContents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */