/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraft.world.item.enchantment.Enchantment;
/*    */ import net.minecraft.world.item.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.event.AnvilUpdateEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.AnvilRecipe;
/*    */ 
/*    */ public class AnvilRecipeEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void doModdedRecipes(AnvilUpdateEvent event) {
/* 20 */     ItemStack left = event.getLeft();
/* 21 */     ItemStack right = event.getRight();
/* 22 */     Level level = event.getPlayer().m_9236_();
/* 23 */     AnvilRecipe.AnvilContents contents = new AnvilRecipe.AnvilContents(left, right);
/* 24 */     List<AnvilRecipe> recipes = level.m_7465_().m_44056_((RecipeType)WitherStormModRecipeTypes.ANVIL.get(), (Container)contents, level);
/* 25 */     if (!recipes.isEmpty()) {
/*    */       
/* 27 */       AnvilRecipe recipe = recipes.get(0);
/* 28 */       Map<Enchantment, Integer> enchantments = EnchantmentHelper.m_44831_(left);
/* 29 */       enchantments.putAll(EnchantmentHelper.m_44831_(right));
/* 30 */       ItemStack output = recipe.assemble(contents, level.m_9598_());
/* 31 */       EnchantmentHelper.m_44865_(enchantments, output);
/* 32 */       event.setOutput(output);
/* 33 */       event.setCost(recipe.getCost());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\AnvilRecipeEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */