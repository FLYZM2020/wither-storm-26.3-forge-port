/*    */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*    */ 
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.resources.Identifier;
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
/*    */   public LockAmuletRecipe(Identifier id, CraftingBookCategory category) {
/* 18 */     super(id, category);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matches(CraftingContainer container, Level level) {
/* 24 */     boolean flag = true;
/* 25 */     int totalAmulets = 0;
/* 26 */     for (int i = 0; i < container.getContainerSize(); i++) {
/*    */       
/* 28 */       ItemStack stack = container.getItem(i);
/* 29 */       if (stack.getItem() instanceof nonamecrackers2.witherstormmod.common.item.AmuletItem)
/* 30 */         totalAmulets++; 
/* 31 */       if ((!stack.isEmpty() && !(stack.getItem() instanceof nonamecrackers2.witherstormmod.common.item.AmuletItem)) || stack.getOrCreateTag().getBoolean("Locked") || totalAmulets > 1)
/* 32 */         flag = false; 
/*    */     } 
/* 34 */     return flag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
/* 40 */     for (int i = 0; i < container.getContainerSize(); i++) {
/*    */       
/* 42 */       ItemStack slotItem = container.getItem(i);
/* 43 */       if (slotItem.getItem() instanceof nonamecrackers2.witherstormmod.common.item.AmuletItem) {
/*    */         
/* 45 */         ItemStack stack = slotItem.copy();
/* 46 */         stack.getOrCreateTag().putBoolean("Locked", true);
/* 47 */         return stack;
/*    */       } 
/*    */     } 
/* 50 */     return ItemStack.EMPTY;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canCraftInDimensions(int width, int height) {
/* 56 */     return (width * height >= 1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getSerializer() {
/* 62 */     return (RecipeSerializer)WitherStormModRecipeSerializers.LOCK_AMULET.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\LockAmuletRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */