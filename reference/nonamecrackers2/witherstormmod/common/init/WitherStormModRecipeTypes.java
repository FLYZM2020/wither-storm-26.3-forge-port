/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.AnvilRecipe;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModRecipeTypes
/*    */ {
/* 15 */   public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, "witherstormmod");
/*    */   
/* 17 */   public static final RegistryObject<RecipeType<ResummonSuperBeaconRecipe>> SUPER_BEACON_RESUMMON = register("super_beacon_resummon");
/* 18 */   public static final RegistryObject<RecipeType<ItemCraftSuperBeaconRecipe>> SUPER_BEACON_ITEM = register("super_beacon_item");
/* 19 */   public static final RegistryObject<RecipeType<AnvilRecipe>> ANVIL = register("anvil");
/*    */ 
/*    */   
/*    */   private static <T extends net.minecraft.world.item.crafting.Recipe<?>> RegistryObject<RecipeType<T>> register(final String id) {
/* 23 */     return RECIPE_TYPES.register(id, () -> new RecipeType<T>()
/*    */         {
/*    */           
/*    */           public String toString()
/*    */           {
/* 28 */             return id;
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModRecipeTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */