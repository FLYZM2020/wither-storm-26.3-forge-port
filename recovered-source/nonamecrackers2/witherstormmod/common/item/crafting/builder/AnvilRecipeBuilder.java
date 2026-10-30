package nonamecrackers2.witherstormmod.common.item.crafting.builder;

import com.google.gson.JsonObject;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;

public class AnvilRecipeBuilder implements RecipeBuilder {
   private final Ingredient left;
   private final Ingredient right;
   private final Item result;
   private final int xpCost;
   @Nullable
   private String group;

   public AnvilRecipeBuilder(Ingredient left, Ingredient right, ItemLike result, int xpCost) {
      this.left = left;
      this.right = right;
      this.result = result.m_5456_();
      this.xpCost = xpCost;
   }

   public static nonamecrackers2.witherstormmod.common.item.crafting.builder.AnvilRecipeBuilder commandBlockTool(ItemLike requirement, ItemLike result) {
      return new nonamecrackers2.witherstormmod.common.item.crafting.builder.AnvilRecipeBuilder(
         Ingredient.m_43929_(new ItemLike[]{requirement}),
         Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get()}),
         result,
         1
      );
   }

   public RecipeBuilder m_126132_(String str, CriterionTriggerInstance instance) {
      return this;
   }

   public RecipeBuilder m_126145_(String group) {
      this.group = group;
      return this;
   }

   public Item m_142372_() {
      return this.result;
   }

   public void m_126140_(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
      consumer.accept(
         new nonamecrackers2.witherstormmod.common.item.crafting.builder.AnvilRecipeBuilder.Result(
            id, this.group == null ? "" : this.group, this.left, this.right, this.result, this.xpCost
         )
      );
   }

   public record Result(ResourceLocation id, String group, Ingredient left, Ingredient right, Item result, int xpCost) implements FinishedRecipe {
      public void m_7917_(JsonObject object) {
         if (!this.group.isEmpty()) {
            object.addProperty("group", this.group);
         }

         object.add("left", this.left.m_43942_());
         object.add("right", this.right.m_43942_());
         object.addProperty("cost", this.xpCost);
         object.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
      }

      public ResourceLocation m_6445_() {
         return this.id;
      }

      public RecipeSerializer<?> m_6637_() {
         return (RecipeSerializer<?>)WitherStormModRecipeSerializers.ANVIL_RECIPE.get();
      }

      public JsonObject m_5860_() {
         return null;
      }

      public ResourceLocation m_6448_() {
         return null;
      }
   }
}
