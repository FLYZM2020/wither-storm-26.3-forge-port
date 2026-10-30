/*    */ package nonamecrackers2.witherstormmod.client.init;
/*    */ 
/*    */ import net.minecraft.client.RecipeBookCategories;
/*    */ import net.minecraft.world.item.crafting.Recipe;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
/*    */ 
/*    */ public class WitherStormModRecipeBookTypes {
/*    */   public static void registerRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
/* 11 */     event.registerRecipeCategoryFinder((RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_ITEM.get(), r -> RecipeBookCategories.UNKNOWN);
/* 12 */     event.registerRecipeCategoryFinder((RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_RESUMMON.get(), r -> RecipeBookCategories.UNKNOWN);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\init\WitherStormModRecipeBookTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */