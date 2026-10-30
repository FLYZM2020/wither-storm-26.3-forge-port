/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.AnvilRecipe;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.LockAmuletRecipe;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;
/*    */ 
/*    */ public class WitherStormModRecipeSerializers
/*    */ {
/* 16 */   public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "witherstormmod");
/*    */   
/* 18 */   public static final RegistryObject<SimpleCraftingRecipeSerializer<LockAmuletRecipe>> LOCK_AMULET = RECIPE_SERIALIZERS.register("lock_amulet", () -> new SimpleCraftingRecipeSerializer(LockAmuletRecipe::new));
/* 19 */   public static final RegistryObject<ResummonSuperBeaconRecipe.Serializer> RESUMMON_SUPER_BEACON = RECIPE_SERIALIZERS.register("resummon_super_beacon", nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe.Serializer::new);
/* 20 */   public static final RegistryObject<ItemCraftSuperBeaconRecipe.Serializer> ITEM_CRAFT_SUPER_BEACON = RECIPE_SERIALIZERS.register("item_craft_super_beacon", nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe.Serializer::new);
/* 21 */   public static final RegistryObject<AnvilRecipe.Serializer> ANVIL_RECIPE = RECIPE_SERIALIZERS.register("anvil", nonamecrackers2.witherstormmod.common.item.crafting.AnvilRecipe.Serializer::new);
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModRecipeSerializers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */