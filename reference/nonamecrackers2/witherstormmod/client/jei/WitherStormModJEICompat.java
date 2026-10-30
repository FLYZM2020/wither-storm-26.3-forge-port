/*    */ package nonamecrackers2.witherstormmod.client.jei;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import mezz.jei.api.IModPlugin;
/*    */ import mezz.jei.api.JeiPlugin;
/*    */ import mezz.jei.api.constants.RecipeTypes;
/*    */ import mezz.jei.api.helpers.IJeiHelpers;
/*    */ import mezz.jei.api.recipe.RecipeType;
/*    */ import mezz.jei.api.recipe.category.IRecipeCategory;
/*    */ import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
/*    */ import mezz.jei.api.registration.IRecipeCatalystRegistration;
/*    */ import mezz.jei.api.registration.IRecipeCategoryRegistration;
/*    */ import mezz.jei.api.registration.IRecipeRegistration;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.crafting.RecipeManager;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import nonamecrackers2.witherstormmod.client.jei.category.SuperBeaconItemCrafting;
/*    */ import nonamecrackers2.witherstormmod.client.jei.category.SuperBeaconSummoning;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.AnvilRecipe;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;
/*    */ 
/*    */ @JeiPlugin
/*    */ public class WitherStormModJEICompat implements IModPlugin {
/* 35 */   private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "jei_compat");
/* 36 */   public static final RecipeType<ItemCraftSuperBeaconRecipe> SUPER_BEACON_ITEM_CRAFTING = RecipeType.create("witherstormmod", "item_craft_super_beacon", ItemCraftSuperBeaconRecipe.class);
/* 37 */   public static final RecipeType<ResummonSuperBeaconRecipe> SUPER_BEACON_SUMMONING = RecipeType.create("witherstormmod", "resummoning_super_beacon", ResummonSuperBeaconRecipe.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getPluginUid() {
/* 42 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerRecipes(IRecipeRegistration registration) {
/* 48 */     Minecraft mc = Minecraft.m_91087_();
/* 49 */     RecipeManager manager = mc.f_91073_.m_7465_();
/* 50 */     registration.addRecipes(SUPER_BEACON_ITEM_CRAFTING, manager.m_44013_((RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_ITEM.get()));
/* 51 */     registration.addRecipes(SUPER_BEACON_SUMMONING, manager.m_44013_((RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_RESUMMON.get()));
/* 52 */     MutableComponent mutableComponent1 = Component.m_237115_("withered_beacon.info");
/* 53 */     registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.SUPER_BEACON.get()), new Component[] { (Component)mutableComponent1 });
/* 54 */     registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get()), new Component[] { (Component)mutableComponent1 });
/* 55 */     MutableComponent mutableComponent2 = Component.m_237115_("witherstormmod.jei.tainted_pumpkin_info");
/* 56 */     registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_PUMPKIN.get()), new Component[] { (Component)mutableComponent2 });
/* 57 */     registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()), new Component[] { (Component)mutableComponent2 });
/* 58 */     registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()), new Component[] { (Component)mutableComponent2 });
/* 59 */     List<IJeiAnvilRecipe> recipes = Lists.newArrayList();
/* 60 */     for (AnvilRecipe recipe : manager.m_44013_((RecipeType)WitherStormModRecipeTypes.ANVIL.get())) {
/* 61 */       recipes.add(registration.getVanillaRecipeFactory().createAnvilRecipe(Arrays.asList(recipe.getLeft().m_43908_()), Arrays.asList(recipe.getRight().m_43908_()), Lists.newArrayList((Object[])new ItemStack[] { recipe.getOutputRaw() })));
/* 62 */     }  registration.addRecipes(RecipeTypes.ANVIL, recipes);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerCategories(IRecipeCategoryRegistration registration) {
/* 68 */     IJeiHelpers helpers = registration.getJeiHelpers();
/* 69 */     registration.addRecipeCategories(new IRecipeCategory[] { (IRecipeCategory)new SuperBeaconItemCrafting(helpers.getGuiHelper()) });
/* 70 */     registration.addRecipeCategories(new IRecipeCategory[] { (IRecipeCategory)new SuperBeaconSummoning(helpers.getGuiHelper()) });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
/* 76 */     registration.addRecipeCatalyst(new ItemStack((ItemLike)WitherStormModItems.SUPER_BEACON.get()), new RecipeType[] { SUPER_BEACON_ITEM_CRAFTING });
/* 77 */     registration.addRecipeCatalyst(new ItemStack((ItemLike)WitherStormModItems.SUPER_SUPPORT_BEACON.get()), new RecipeType[] { SUPER_BEACON_ITEM_CRAFTING });
/* 78 */     registration.addRecipeCatalyst(new ItemStack((ItemLike)WitherStormModItems.SUPER_BEACON.get()), new RecipeType[] { SUPER_BEACON_SUMMONING });
/* 79 */     registration.addRecipeCatalyst(new ItemStack((ItemLike)WitherStormModItems.SUPER_SUPPORT_BEACON.get()), new RecipeType[] { SUPER_BEACON_SUMMONING });
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\jei\WitherStormModJEICompat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */