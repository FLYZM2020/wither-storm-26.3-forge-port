/*    */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*    */ 
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.inventory.CraftingContainer;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.crafting.CraftingBookCategory;
/*    */ import net.minecraft.world.item.crafting.CustomRecipe;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraft.world.level.Level;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*    */ 
/*    */ public class LockAmuletRecipe
/*    */   extends CustomRecipe
/*    */ {
/*    */   public LockAmuletRecipe(ResourceLocation id, CraftingBookCategory category) {
/* 18 */     super(id, category);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matches(CraftingContainer container, Level level) {
/* 24 */     boolean flag = true;
/* 25 */     int totalAmulets = 0;
/* 26 */     for (int i = 0; i < container.m_6643_(); i++) {
/*    */       
/* 28 */       ItemStack stack = container.m_8020_(i);
/* 29 */       if (stack.m_41720_() instanceof nonamecrackers2.witherstormmod.common.item.AmuletItem)
/* 30 */         totalAmulets++; 
/* 31 */       if ((!stack.m_41619_() && !(stack.m_41720_() instanceof nonamecrackers2.witherstormmod.common.item.AmuletItem)) || stack.m_41784_().m_128471_("Locked") || totalAmulets > 1)
/* 32 */         flag = false; 
/*    */     } 
/* 34 */     return flag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
/* 40 */     for (int i = 0; i < container.m_6643_(); i++) {
/*    */       
/* 42 */       ItemStack slotItem = container.m_8020_(i);
/* 43 */       if (slotItem.m_41720_() instanceof nonamecrackers2.witherstormmod.common.item.AmuletItem) {
/*    */         
/* 45 */         ItemStack stack = slotItem.m_41777_();
/* 46 */         stack.m_41784_().m_128379_("Locked", true);
/* 47 */         return stack;
/*    */       } 
/*    */     } 
/* 50 */     return ItemStack.f_41583_;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8004_(int width, int height) {
/* 56 */     return (width * height >= 1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> m_7707_() {
/* 62 */     return (RecipeSerializer)WitherStormModRecipeSerializers.LOCK_AMULET.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\LockAmuletRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */