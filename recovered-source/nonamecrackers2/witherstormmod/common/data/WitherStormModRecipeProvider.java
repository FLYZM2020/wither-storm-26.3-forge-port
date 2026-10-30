package nonamecrackers2.witherstormmod.common.data;

import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe.Condition;
import nonamecrackers2.witherstormmod.common.item.crafting.builder.AnvilRecipeBuilder;
import nonamecrackers2.witherstormmod.common.item.crafting.builder.SuperBeaconRecipeBuilder;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
import org.jetbrains.annotations.NotNull;

public class WitherStormModRecipeProvider extends RecipeProvider {
   public WitherStormModRecipeProvider(PackOutput output) {
      super(output);
   }

   protected void m_245200_(@NotNull Consumer<FinishedRecipe> result) {
      SpecialRecipeBuilder.m_245676_((RecipeSerializer)WitherStormModRecipeSerializers.LOCK_AMULET.get()).m_126359_(result, "witherstormmod:amulet_lock");
      unlockedByItems(
            ShapedRecipeBuilder.m_245327_(RecipeCategory.TOOLS, (ItemLike)WitherStormModItems.AMULET.get())
               .m_126127_('G', Items.f_42417_)
               .m_126127_('D', Items.f_42415_)
               .m_126127_('R', Items.f_42451_)
               .m_126127_('L', Items.f_42534_)
               .m_126127_('E', Items.f_42616_)
               .m_126127_('I', Items.f_42416_)
               .m_126130_("GDG")
               .m_126130_("RLE")
               .m_126130_("GIG"),
            Items.f_42417_,
            Items.f_42415_,
            Items.f_42451_,
            Items.f_42534_,
            Items.f_42616_,
            Items.f_42416_
         )
         .m_176498_(result);
      ShapelessRecipeBuilder.m_245498_(RecipeCategory.REDSTONE, (ItemLike)WitherStormModItems.FIREWORK_BUNDLE.get())
         .m_126211_(Items.f_42688_, 8)
         .m_126209_(Items.f_42768_)
         .m_126132_("has_firework_rocket", m_125977_(Items.f_42688_))
         .m_126132_("has_barrel", m_125977_(Items.f_42768_))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.MISC, (ItemLike)WitherStormModItems.FORMIDIBOMB.get())
         .m_126127_('X', Items.f_42403_)
         .m_126127_('#', Items.f_42593_)
         .m_126127_('A', (ItemLike)WitherStormModItems.SUPER_TNT.get())
         .m_126130_("X#X")
         .m_126130_("#A#")
         .m_126130_("X#X")
         .m_126132_("has_super_tnt", m_125977_((ItemLike)WitherStormModItems.SUPER_TNT.get()))
         .m_176498_(result);
      ShapelessRecipeBuilder.m_245498_(RecipeCategory.FOOD, (ItemLike)WitherStormModItems.GOLDEN_APPLE_STEW.get())
         .m_126184_(Ingredient.m_204132_(WitherStormModItemTags.CURE_INGREDIENT))
         .m_126209_(Items.f_42718_)
         .m_126209_(Items.f_42436_)
         .m_126184_(Ingredient.m_204132_(WitherStormModItemTags.CURE_BASE))
         .m_126132_("has_cure_ingredient", m_206406_(WitherStormModItemTags.CURE_INGREDIENT))
         .m_126132_("has_suspicious_stew", m_125977_(Items.f_42718_))
         .m_126132_("has_golden_apple", m_125977_(Items.f_42436_))
         .m_126132_("has_cure_base", m_206406_(WitherStormModItemTags.CURE_BASE))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.INFECTED_FLESH_BLOCK.get())
         .m_126127_('#', (ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get())
         .m_126127_('A', (ItemLike)WitherStormModItems.TAINTED_DUST.get())
         .m_126130_("###")
         .m_126130_("#A#")
         .m_126130_("###")
         .m_126132_("has_tainted_flesh_block", m_125977_((ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get()))
         .m_126132_("has_tainted_dust", m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.TOOLS, (ItemLike)WitherStormModItems.PHASOMETER.get())
         .m_126127_('#', Items.f_42498_)
         .m_126127_('S', Items.f_42083_)
         .m_126127_('Z', Items.f_151059_)
         .m_126127_('R', Items.f_42451_)
         .m_126127_('X', Items.f_42350_)
         .m_126130_(" # ")
         .m_126130_("SZR")
         .m_126130_(" X ")
         .m_126132_("has_spyglass", m_125977_(Items.f_151059_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.NONE, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.WITHER_STORM.get())
         .requires(Items.f_42679_, 3)
         .unlockedBy("has_wither_skull", m_125977_(Items.f_42679_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULLY_COMLETED, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get())
         .requires((ItemLike)WitherStormModItems.INFECTED_FLESH_BLOCK.get(), 1)
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
         .requires((ItemLike)WitherStormModItems.WITHERED_BONE.get(), 1)
         .requires((ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get(), 1)
         .requires(Items.f_42679_)
         .unlockedBy("has_wither_skeleton_skull", m_125977_(Items.f_42679_))
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_withered_bone", m_125977_((ItemLike)WitherStormModItems.WITHERED_BONE.get()))
         .unlockedBy("has_infected_flesh_block", m_125977_((ItemLike)WitherStormModItems.INFECTED_FLESH_BLOCK.get()))
         .unlockedBy("has_tainted_dust_block", m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get()))
         .m_176498_(result);
      reubenPig(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_BONE.get())
         .requires(Items.f_42402_)
         .unlockedBy("has_withered_bone", m_125977_((ItemLike)WitherStormModItems.WITHERED_BONE.get()))
         .unlockedBy("has_feather", m_125977_(Items.f_42402_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_COW.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
         .requires(Items.f_42454_, 2)
         .requires(Items.f_42579_, 2)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_leather", m_125977_(Items.f_42454_))
         .unlockedBy("has_beef", m_125977_(Items.f_42579_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_PIG.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
         .requires(Items.f_42485_, 3)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_pork", m_125977_(Items.f_42485_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_BEE.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
         .requires(Items.f_42787_, 2)
         .requires(Items.f_42784_, 4)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_honey_bottle", m_125977_(Items.f_42787_))
         .unlockedBy("has_honeycomb", m_125977_(Items.f_42784_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
         .requires(Items.f_42500_, 2)
         .requires(Items.f_42656_, 1)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_bone", m_125977_(Items.f_42500_))
         .unlockedBy("has_name_tag", m_125977_(Items.f_42656_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_CAT.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
         .requires(Ingredient.m_204132_(ItemTags.f_13156_), 2)
         .requires(Items.f_42656_, 1)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_bone", m_125977_(Items.f_42500_))
         .unlockedBy("has_name_tag", m_125977_(Items.f_42656_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 1)
         .requires(Ingredient.m_43929_(new ItemLike[]{Items.f_42733_, Items.f_42578_, Items.f_42577_, Items.f_42404_, Items.f_271133_}), 8)
         .requires(Items.f_42656_, 1)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_bone", m_125977_(Items.f_42500_))
         .unlockedBy("has_name_tag", m_125977_(Items.f_42656_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get())
         .requires(Items.f_42403_, 3)
         .requires((ItemLike)WitherStormModItems.TAINTED_DUST.get(), 3)
         .unlockedBy("has_gunpowder", m_125977_(Items.f_42403_))
         .unlockedBy("has_tainted_dust", m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
         .requires((ItemLike)WitherStormModItems.TAINTED_MUSHROOM.get(), 2)
         .requires(Items.f_42579_, 2)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_tainted_mushroom", m_125977_((ItemLike)WitherStormModItems.TAINTED_MUSHROOM.get()))
         .unlockedBy("has_beef", m_125977_(Items.f_42579_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
         .requires((ItemLike)WitherStormModItems.WITHERED_BONE.get(), 2)
         .requires(Items.f_42714_)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_withered_bone", m_125977_((ItemLike)WitherStormModItems.WITHERED_BONE.get()))
         .unlockedBy("has_phantom_membrane", m_125977_(Items.f_42714_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
         .requires(Items.f_42717_)
         .requires(Items.f_42412_, 2)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_crossbow", m_125977_(Items.f_42717_))
         .unlockedBy("has_arrow", m_125977_(Items.f_42412_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 4)
         .requires(Items.f_42386_)
         .requires(Items.f_42616_)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_iron_axe", m_125977_(Items.f_42386_))
         .unlockedBy("has_emerald", m_125977_(Items.f_42616_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get())
         .requires(Items.f_42500_, 3)
         .requires((ItemLike)WitherStormModItems.WITHERED_BONE.get(), 3)
         .unlockedBy("has_bone", m_125977_(Items.f_42500_))
         .unlockedBy("has_withered_bone", m_125977_((ItemLike)WitherStormModItems.WITHERED_BONE.get()))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get())
         .requires((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get(), 3)
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_withered_spider_eye", m_125977_((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get()))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get())
         .requires(Items.f_42583_, 2)
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
         .requires(Items.f_42616_, 2)
         .requires(Items.f_42436_)
         .unlockedBy("has_rotten_flesh", m_125977_(Items.f_42583_))
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .unlockedBy("has_emerald", m_125977_(Items.f_42616_))
         .unlockedBy("has_golden_apple", m_125977_(Items.f_42436_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.entity(Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get())
         .requires(Items.f_42583_, 3)
         .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
         .unlockedBy("has_rotten_flesh", m_125977_(Items.f_42583_))
         .unlockedBy("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.MISC, (ItemLike)WitherStormModItems.SUPER_BEACON.get())
         .m_126127_('G', (ItemLike)WitherStormModItems.TAINTED_GLASS.get())
         .m_126127_('S', (ItemLike)WitherStormModItems.WITHERED_NETHER_STAR.get())
         .m_126127_('I', (ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get())
         .m_126127_('A', (ItemLike)WitherStormModItems.AMULET.get())
         .m_126130_("GGG")
         .m_126130_("GSG")
         .m_126130_("IAI")
         .m_126132_("has_withered_nether_star", m_125977_((ItemLike)WitherStormModItems.WITHERED_NETHER_STAR.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.MISC, (ItemLike)WitherStormModItems.SUPER_SUPPORT_BEACON.get())
         .m_126127_('G', (ItemLike)WitherStormModItems.TAINTED_GLASS.get())
         .m_126127_('S', Items.f_42686_)
         .m_126127_('I', (ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get())
         .m_126130_("GGG")
         .m_126130_("GSG")
         .m_126130_("III")
         .m_126132_("has_nether_star", m_125977_(Items.f_42686_))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.MISC, (ItemLike)WitherStormModItems.SUPER_TNT.get())
         .m_126127_('#', Items.f_41996_)
         .m_126127_('X', Items.f_42403_)
         .m_126127_('A', (ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get())
         .m_126130_("X#X")
         .m_126130_("#A#")
         .m_126130_("X#X")
         .m_126132_("has_command_block_book", m_125977_((ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get()))
         .m_176498_(result);
      m_176658_((ItemLike)WitherStormModItems.TAINTED_BUTTON.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_PLANKS.get()}))
         .m_126132_("has_tainted_planks", m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
         .m_176498_(result);
      SingleItemRecipeBuilder.m_246944_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE_SLAB.get(),
            2
         )
         .m_126132_("has_tainted_cobblestone", m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.getId().m_135815_() + "_stonecutting"));
      m_246658_(
         result,
         RecipeCategory.DECORATIONS,
         (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get(),
         (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get()
      );
      SingleItemRecipeBuilder.m_245264_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get()}),
            RecipeCategory.BUILDING_BLOCKS,
            (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get()
         )
         .m_126132_("has_tainted_cobblestone", m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.getId().m_135815_() + "_stonecutting"));
      m_176710_(
            (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE_STAIRS.get(),
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()})
         )
         .m_126132_("has_tainted_cobblestone", m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()))
         .m_176498_(result);
      SingleItemRecipeBuilder.m_245264_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get()
         )
         .m_126132_("has_tainted_cobblestone", m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE_WALL.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.getId().m_135815_() + "_stonecutting"));
      m_246382_(
         result,
         RecipeCategory.DECORATIONS,
         (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE_WALL.get(),
         (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()
      );
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get())
         .requires(Items.f_42594_, 6)
         .unlockedBy("has_cobblestone", m_125977_(Items.f_42594_))
         .m_176498_(result);
      m_176670_((ItemLike)WitherStormModItems.TAINTED_DOOR.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_PLANKS.get()}))
         .m_126132_("has_tainted_planks", m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
         .m_176498_(result);
      m_176726_((ItemLike)WitherStormModItems.TAINTED_SIGN.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_PLANKS.get()}))
         .m_126132_("has_tainted_planks", m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get())
         .m_126127_('#', (ItemLike)WitherStormModItems.TAINTED_DUST.get())
         .m_126130_("##")
         .m_126130_("##")
         .m_126132_("has_tainted_dust", m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_DUST.get())
         .requires(Items.f_42451_, 6)
         .unlockedBy("has_redstone", m_125977_(Items.f_42451_))
         .m_176498_(result);
      m_176684_((ItemLike)WitherStormModItems.TAINTED_FENCE_GATE.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_PLANKS.get()}))
         .m_126132_("has_tainted_planks", m_125977_((ItemLike)WitherStormModBlocks.TAINTED_PLANKS.get()))
         .m_176498_(result);
      m_176678_((ItemLike)WitherStormModItems.TAINTED_FENCE.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModBlocks.TAINTED_PLANKS.get()}))
         .m_126132_("has_tainted_planks", m_125977_((ItemLike)WitherStormModBlocks.TAINTED_PLANKS.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get())
         .m_126127_('#', (ItemLike)WitherStormModItems.WITHERED_FLESH.get())
         .m_126130_("###")
         .m_126130_("###")
         .m_126130_("###")
         .m_126132_("has_withered_flesh", m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_246608_(RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_GLASS_PANE.get(), 16)
         .m_126127_('#', (ItemLike)WitherStormModItems.TAINTED_GLASS.get())
         .m_126130_("###")
         .m_126130_("###")
         .m_126132_("has_tainted_glass", m_125977_((ItemLike)WitherStormModItems.TAINTED_GLASS.get()))
         .m_176498_(result);
      SimpleCookingRecipeBuilder.m_246179_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SAND.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModItems.TAINTED_GLASS.get(),
            0.1F,
            200
         )
         .m_126132_("has_tainted_sand", m_125977_((ItemLike)WitherStormModItems.TAINTED_SAND.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_JACK_O_LANTERN.get())
         .m_126127_('#', (ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get())
         .m_126124_('A', Ingredient.m_43929_(new ItemLike[]{Items.f_42000_, (ItemLike)WitherStormModItems.TAINTED_TORCH.get()}))
         .m_126130_("#")
         .m_126130_("A")
         .m_126132_("has_tainted_carved_pumpkin", m_125977_((ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get()))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_LOG.get())
         .requires(Ingredient.m_204132_(ItemTags.f_13182_), 6)
         .unlockedBy("has_log", m_206406_(ItemTags.f_13182_))
         .m_176498_(result);
      m_257424_(result, (ItemLike)WitherStormModItems.TAINTED_PLANKS.get(), WitherStormModItemTags.TAINTED_LOGS, 4);
      m_176690_(result, (ItemLike)WitherStormModItems.TAINTED_PRESSURE_PLATE.get(), (ItemLike)WitherStormModItems.TAINTED_PLANKS.get());
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_SAND.get())
         .requires(Items.f_41830_, 6)
         .unlockedBy("has_sand", m_125977_(Items.f_41830_))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get())
         .m_126127_('#', (ItemLike)WitherStormModItems.TAINTED_SAND.get())
         .m_126130_("##")
         .m_126130_("##")
         .m_126132_("has_tainted_sand", m_125977_((ItemLike)WitherStormModItems.TAINTED_SAND.get()))
         .m_176498_(result);
      m_246658_(
         result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_SLAB.get(), (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()
      );
      m_176710_(
            (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_STAIRS.get(),
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()})
         )
         .m_126132_("has_tainted_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
         .m_176498_(result);
      SingleItemRecipeBuilder.m_246944_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_SLAB.get(),
            2
         )
         .m_126132_("has_tainted_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_SANDSTONE_SLAB.getId().m_135815_() + "_stonecutting"));
      SingleItemRecipeBuilder.m_245264_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()}),
            RecipeCategory.BUILDING_BLOCKS,
            (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_STAIRS.get()
         )
         .m_126132_("has_tainted_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_SANDSTONE_STAIRS.getId().m_135815_() + "_stonecutting"));
      SingleItemRecipeBuilder.m_245264_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModBlocks.TAINTED_SANDSTONE.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get()
         )
         .m_126132_("has_tainted_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_SANDSTONE_WALL.getId().m_135815_() + "_stonecutting"));
      m_246382_(
         result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_WALL.get(), (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()
      );
      ShapedRecipeBuilder.m_246608_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE.get(), 4)
         .m_126127_('#', (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get())
         .m_126130_("##")
         .m_126130_("##")
         .m_126132_("has_tainted_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
         .m_176498_(result);
      m_246658_(
         result,
         RecipeCategory.DECORATIONS,
         (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE_SLAB.get(),
         (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE.get()
      );
      SingleItemRecipeBuilder.m_246944_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE_SLAB.get(),
            2
         )
         .m_126132_("has_tainted_cut_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_CUT_SANDSTONE_SLAB.getId().m_135815_() + "_stonecutting"));
      ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_CHISELED_SANDSTONE.get())
         .m_126127_('#', (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_SLAB.get())
         .m_126130_("#")
         .m_126130_("#")
         .m_126132_("has_tainted_sandstone_slab", m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE_SLAB.get()))
         .m_176498_(result);
      SimpleCookingRecipeBuilder.m_246179_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()}),
            RecipeCategory.BUILDING_BLOCKS,
            (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get(),
            0.1F,
            200
         )
         .m_126132_("has_tainted_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
         .m_176498_(result);
      m_246658_(
         result,
         RecipeCategory.DECORATIONS,
         (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_SLAB.get(),
         (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()
      );
      m_176710_(
            (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_STAIRS.get(),
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()})
         )
         .m_126132_("has_tainted_smooth_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()))
         .m_176498_(result);
      SingleItemRecipeBuilder.m_246944_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_SLAB.get(),
            2
         )
         .m_126132_("has_tainted_smooth_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_SLAB.getId().m_135815_() + "_stonecutting"));
      SingleItemRecipeBuilder.m_245264_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()}),
            RecipeCategory.BUILDING_BLOCKS,
            (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_STAIRS.get()
         )
         .m_126132_("has_tainted_smooth_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_STAIRS.getId().m_135815_() + "_stonecutting"));
      SingleItemRecipeBuilder.m_245264_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.get()
         )
         .m_126132_("has_tainted_smooth_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.getId().m_135815_() + "_stonecutting"));
      m_246382_(
         result,
         RecipeCategory.DECORATIONS,
         (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_WALL.get(),
         (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()
      );
      m_246658_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SLAB.get(), (ItemLike)WitherStormModItems.TAINTED_PLANKS.get());
      m_176710_((ItemLike)WitherStormModItems.TAINTED_STAIRS.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_PLANKS.get()}))
         .m_126132_("has_tainted_planks", m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
         .m_176498_(result);
      m_176658_(
            (ItemLike)WitherStormModItems.TAINTED_STONE_BUTTON.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_STONE.get()})
         )
         .m_126132_("has_tainted_stone", m_125977_((ItemLike)WitherStormModItems.TAINTED_STONE.get()))
         .m_176498_(result);
      m_176690_(result, (ItemLike)WitherStormModItems.TAINTED_STONE_PRESSURE_PLATE.get(), (ItemLike)WitherStormModItems.TAINTED_STONE.get());
      SingleItemRecipeBuilder.m_246944_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_STONE.get()}),
            RecipeCategory.DECORATIONS,
            (ItemLike)WitherStormModItems.TAINTED_STONE_SLAB.get(),
            2
         )
         .m_126132_("has_tainted_stone", m_125977_((ItemLike)WitherStormModItems.TAINTED_STONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_STONE_SLAB.getId().m_135815_() + "_stonecutting"));
      m_246658_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_STONE_SLAB.get(), (ItemLike)WitherStormModItems.TAINTED_STONE.get());
      SingleItemRecipeBuilder.m_245264_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_STONE.get()}),
            RecipeCategory.BUILDING_BLOCKS,
            (ItemLike)WitherStormModItems.TAINTED_STONE_STAIRS.get()
         )
         .m_126132_("has_tainted_stone", m_125977_((ItemLike)WitherStormModItems.TAINTED_STONE.get()))
         .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_STONE_STAIRS.getId().m_135815_() + "_stonecutting"));
      m_176710_(
            (ItemLike)WitherStormModItems.TAINTED_STONE_STAIRS.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_STONE.get()})
         )
         .m_126132_("has_tainted_stone", m_125977_((ItemLike)WitherStormModItems.TAINTED_STONE.get()))
         .m_176498_(result);
      SimpleCookingRecipeBuilder.m_246179_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()}),
            RecipeCategory.BUILDING_BLOCKS,
            (ItemLike)WitherStormModItems.TAINTED_STONE.get(),
            0.1F,
            200
         )
         .m_126132_("has_tainted_cobblestone", m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()))
         .m_176498_(result);
      m_176720_((ItemLike)WitherStormModItems.TAINTED_TRAPDOOR.get(), Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_PLANKS.get()}))
         .m_126132_("has_tainted_planks", m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_WOOD.get())
         .m_126127_('#', (ItemLike)WitherStormModItems.TAINTED_LOG.get())
         .m_126130_("##")
         .m_126130_("##")
         .m_126132_("has_tainted_log", m_125977_((ItemLike)WitherStormModItems.TAINTED_LOG.get()))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.MISC, (ItemLike)WitherStormModItems.WITHERED_BONE.get())
         .requires(Items.f_42500_, 6)
         .unlockedBy("has_bone", m_125977_(Items.f_42500_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.MISC, (ItemLike)WitherStormModItems.WITHERED_FLESH.get())
         .requires(Items.f_42583_, 6)
         .unlockedBy("has_rotten_flesh", m_125977_(Items.f_42583_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.MISC, (ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get())
         .requires(Items.f_42591_, 6)
         .unlockedBy("has_spider_eye", m_125977_(Items.f_42591_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.FULL_SUPPORTS, RecipeCategory.COMBAT, (ItemLike)WitherStormModItems.EYE_OF_THE_STORM.get())
         .requires((ItemLike)WitherStormModItems.COMMAND_BLOCK_SWORD.get())
         .requires((ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get(), 4)
         .requires((ItemLike)WitherStormModItems.TAINTED_DUST.get())
         .requires(Items.f_42619_)
         .unlockedBy("has_command_block_sword", m_125977_((ItemLike)WitherStormModItems.COMMAND_BLOCK_SWORD.get()))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.FULL_SUPPORTS, RecipeCategory.COMBAT, (ItemLike)WitherStormModItems.FORMIDI_BLADE.get())
         .requires((ItemLike)WitherStormModItems.COMMAND_BLOCK_SWORD.get())
         .requires(Items.f_42729_, 6)
         .requires((ItemLike)WitherStormModItems.FORMIDIBOMB.get())
         .unlockedBy("has_command_block_sword", m_125977_((ItemLike)WitherStormModItems.COMMAND_BLOCK_SWORD.get()))
         .m_176498_(result);
      ShapedRecipeBuilder.m_245327_(RecipeCategory.REDSTONE, (ItemLike)WitherStormModItems.WITHERED_PHLEGM_BLOCK.get())
         .m_126127_('~', (ItemLike)WitherStormModItems.TAINTED_DUST.get())
         .m_126127_('H', Items.f_42155_)
         .m_126127_('B', (ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get())
         .m_126127_('C', Items.f_42009_)
         .m_126127_('S', Items.f_42518_)
         .m_126130_("SHS")
         .m_126130_("~B~")
         .m_126130_("SCS")
         .m_126132_("has_tainted_dust_block", m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get()))
         .m_126132_("has_tainted_dust", m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_DIRT.get())
         .requires(Items.f_42329_, 6)
         .unlockedBy("has_dirt", m_125977_(Items.f_42329_))
         .m_176498_(result);
      SuperBeaconRecipeBuilder.item(Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_TORCH.get())
         .requires(Items.f_42000_, 3)
         .unlockedBy("has_torch", m_125977_(Items.f_42000_))
         .m_126140_(result, WitherStormMod.id("tainted_torch_beacon"));
      ShapedRecipeBuilder.m_245327_(RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_TORCH.get())
         .m_126127_('~', Items.f_42398_)
         .m_126127_('1', (ItemLike)WitherStormModItems.TAINTED_DUST.get())
         .m_126130_("1")
         .m_126130_("~")
         .m_126132_("has_tainted_dust", m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
         .m_176498_(result);
      SingleItemRecipeBuilder.m_245264_(
            Ingredient.m_43929_(new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()}),
            RecipeCategory.BUILDING_BLOCKS,
            (ItemLike)WitherStormModItems.TAINTED_CHISELED_SANDSTONE.get()
         )
         .m_126132_("has_tainted_sandstone", m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
         .m_126140_(result, WitherStormMod.id("tainted_chiseled_sandstone_stonecutting"));
      cmdTool(result, Items.f_42388_, RecipeCategory.COMBAT, (Item)WitherStormModItems.COMMAND_BLOCK_SWORD.get());
      cmdTool(result, Items.f_42390_, RecipeCategory.TOOLS, (Item)WitherStormModItems.COMMAND_BLOCK_PICKAXE.get());
      cmdTool(result, Items.f_42391_, RecipeCategory.TOOLS, (Item)WitherStormModItems.COMMAND_BLOCK_AXE.get());
      cmdTool(result, Items.f_42389_, RecipeCategory.TOOLS, (Item)WitherStormModItems.COMMAND_BLOCK_SHOVEL.get());
      cmdTool(result, Items.f_42392_, RecipeCategory.TOOLS, (Item)WitherStormModItems.COMMAND_BLOCK_HOE.get());
      cmdTool(result, Items.f_42420_, RecipeCategory.COMBAT, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_SWORD.get());
      cmdTool(result, Items.f_42422_, RecipeCategory.TOOLS, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_PICKAXE.get());
      cmdTool(result, Items.f_42423_, RecipeCategory.TOOLS, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_AXE.get());
      cmdTool(result, Items.f_42421_, RecipeCategory.TOOLS, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_SHOVEL.get());
      cmdTool(result, Items.f_42424_, RecipeCategory.TOOLS, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_HOE.get());
      cmdTool(result, Items.f_42425_, RecipeCategory.COMBAT, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_SWORD.get());
      cmdTool(result, Items.f_42427_, RecipeCategory.TOOLS, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_PICKAXE.get());
      cmdTool(result, Items.f_42428_, RecipeCategory.TOOLS, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_AXE.get());
      cmdTool(result, Items.f_42426_, RecipeCategory.TOOLS, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_SHOVEL.get());
      cmdTool(result, Items.f_42429_, RecipeCategory.TOOLS, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_HOE.get());
      cmdTool(result, Items.f_42383_, RecipeCategory.COMBAT, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_SWORD.get());
      cmdTool(result, Items.f_42385_, RecipeCategory.TOOLS, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_PICKAXE.get());
      cmdTool(result, Items.f_42386_, RecipeCategory.TOOLS, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_AXE.get());
      cmdTool(result, Items.f_42384_, RecipeCategory.TOOLS, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_SHOVEL.get());
      cmdTool(result, Items.f_42387_, RecipeCategory.TOOLS, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_HOE.get());
      cmdTool(result, Items.f_42430_, RecipeCategory.COMBAT, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_SWORD.get());
      cmdTool(result, Items.f_42432_, RecipeCategory.TOOLS, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_PICKAXE.get());
      cmdTool(result, Items.f_42433_, RecipeCategory.TOOLS, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_AXE.get());
      cmdTool(result, Items.f_42431_, RecipeCategory.TOOLS, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_SHOVEL.get());
      cmdTool(result, Items.f_42434_, RecipeCategory.TOOLS, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_HOE.get());
   }

   private static RecipeBuilder unlockedByItems(RecipeBuilder builder, Item... items) {
      for (Item item : items) {
         builder.m_126132_(m_176602_(item), m_125977_(item));
      }

      return builder;
   }

   private static void reubenPig(Consumer<FinishedRecipe> consumer) {
      CompoundTag pigTag = new CompoundTag();
      pigTag.m_128405_("Age", -1200);
      CompoundTag name = new CompoundTag();
      name.m_128359_("text", "reuben");
      pigTag.m_128365_("CustomName", name);
      SuperBeaconRecipeBuilder.entity(Condition.NONE, RecipeCategory.MISC, EntityType.f_20510_, pigTag)
         .requires(Items.f_42485_, 16)
         .m_126140_(consumer, new ResourceLocation("witherstormmod", "summon_pig"));
   }

   private static void cmdTool(Consumer<FinishedRecipe> result, Item required, RecipeCategory category, Item output) {
      AnvilRecipeBuilder.commandBlockTool(required, output).m_126140_(result, WitherStormMod.id(m_176632_(output) + "_anvil"));
   }
}
