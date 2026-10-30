/*     */ package nonamecrackers2.witherstormmod.common.data;
/*     */ 
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.advancements.CriterionTriggerInstance;
/*     */ import net.minecraft.data.PackOutput;
/*     */ import net.minecraft.data.recipes.FinishedRecipe;
/*     */ import net.minecraft.data.recipes.RecipeBuilder;
/*     */ import net.minecraft.data.recipes.RecipeCategory;
/*     */ import net.minecraft.data.recipes.RecipeProvider;
/*     */ import net.minecraft.data.recipes.ShapedRecipeBuilder;
/*     */ import net.minecraft.data.recipes.ShapelessRecipeBuilder;
/*     */ import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
/*     */ import net.minecraft.data.recipes.SingleItemRecipeBuilder;
/*     */ import net.minecraft.data.recipes.SpecialRecipeBuilder;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.ItemTags;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.builder.AnvilRecipeBuilder;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.builder.SuperBeaconRecipeBuilder;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class WitherStormModRecipeProvider extends RecipeProvider {
/*     */   public WitherStormModRecipeProvider(PackOutput output) {
/*  38 */     super(output);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_245200_(@NotNull Consumer<FinishedRecipe> result) {
/*  45 */     SpecialRecipeBuilder.m_245676_((RecipeSerializer)WitherStormModRecipeSerializers.LOCK_AMULET.get()).m_126359_(result, "witherstormmod:amulet_lock");
/*  46 */     unlockedByItems((RecipeBuilder)ShapedRecipeBuilder.m_245327_(RecipeCategory.TOOLS, (ItemLike)WitherStormModItems.AMULET.get())
/*  47 */         .m_126127_(Character.valueOf('G'), (ItemLike)Items.f_42417_)
/*  48 */         .m_126127_(Character.valueOf('D'), (ItemLike)Items.f_42415_)
/*  49 */         .m_126127_(Character.valueOf('R'), (ItemLike)Items.f_42451_)
/*  50 */         .m_126127_(Character.valueOf('L'), (ItemLike)Items.f_42534_)
/*  51 */         .m_126127_(Character.valueOf('E'), (ItemLike)Items.f_42616_)
/*  52 */         .m_126127_(Character.valueOf('I'), (ItemLike)Items.f_42416_)
/*  53 */         .m_126130_("GDG")
/*  54 */         .m_126130_("RLE")
/*  55 */         .m_126130_("GIG"), new Item[] { Items.f_42417_, Items.f_42415_, Items.f_42451_, Items.f_42534_, Items.f_42616_, Items.f_42416_
/*  56 */         }).m_176498_(result);
/*     */ 
/*     */     
/*  59 */     ShapelessRecipeBuilder.m_245498_(RecipeCategory.REDSTONE, (ItemLike)WitherStormModItems.FIREWORK_BUNDLE.get())
/*  60 */       .m_126211_((ItemLike)Items.f_42688_, 8)
/*  61 */       .m_126209_((ItemLike)Items.f_42768_)
/*  62 */       .m_126132_("has_firework_rocket", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42688_))
/*  63 */       .m_126132_("has_barrel", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42768_))
/*  64 */       .m_176498_(result);
/*     */ 
/*     */     
/*  67 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.MISC, (ItemLike)WitherStormModItems.FORMIDIBOMB.get())
/*  68 */       .m_126127_(Character.valueOf('X'), (ItemLike)Items.f_42403_)
/*  69 */       .m_126127_(Character.valueOf('#'), (ItemLike)Items.f_42593_)
/*  70 */       .m_126127_(Character.valueOf('A'), (ItemLike)WitherStormModItems.SUPER_TNT.get())
/*  71 */       .m_126130_("X#X")
/*  72 */       .m_126130_("#A#")
/*  73 */       .m_126130_("X#X")
/*  74 */       .m_126132_("has_super_tnt", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.SUPER_TNT.get()))
/*  75 */       .m_176498_(result);
/*     */ 
/*     */     
/*  78 */     ShapelessRecipeBuilder.m_245498_(RecipeCategory.FOOD, (ItemLike)WitherStormModItems.GOLDEN_APPLE_STEW.get())
/*  79 */       .m_126184_(Ingredient.m_204132_(WitherStormModItemTags.CURE_INGREDIENT))
/*  80 */       .m_126209_((ItemLike)Items.f_42718_)
/*  81 */       .m_126209_((ItemLike)Items.f_42436_)
/*  82 */       .m_126184_(Ingredient.m_204132_(WitherStormModItemTags.CURE_BASE))
/*  83 */       .m_126132_("has_cure_ingredient", (CriterionTriggerInstance)m_206406_(WitherStormModItemTags.CURE_INGREDIENT))
/*  84 */       .m_126132_("has_suspicious_stew", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42718_))
/*  85 */       .m_126132_("has_golden_apple", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42436_))
/*  86 */       .m_126132_("has_cure_base", (CriterionTriggerInstance)m_206406_(WitherStormModItemTags.CURE_BASE))
/*  87 */       .m_176498_(result);
/*     */ 
/*     */     
/*  90 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.INFECTED_FLESH_BLOCK.get())
/*  91 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get())
/*  92 */       .m_126127_(Character.valueOf('A'), (ItemLike)WitherStormModItems.TAINTED_DUST.get())
/*  93 */       .m_126130_("###")
/*  94 */       .m_126130_("#A#")
/*  95 */       .m_126130_("###")
/*  96 */       .m_126132_("has_tainted_flesh_block", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get()))
/*  97 */       .m_126132_("has_tainted_dust", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
/*  98 */       .m_176498_(result);
/*     */ 
/*     */     
/* 101 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.TOOLS, (ItemLike)WitherStormModItems.PHASOMETER.get())
/* 102 */       .m_126127_(Character.valueOf('#'), (ItemLike)Items.f_42498_)
/* 103 */       .m_126127_(Character.valueOf('S'), (ItemLike)Items.f_42083_)
/* 104 */       .m_126127_(Character.valueOf('Z'), (ItemLike)Items.f_151059_)
/* 105 */       .m_126127_(Character.valueOf('R'), (ItemLike)Items.f_42451_)
/* 106 */       .m_126127_(Character.valueOf('X'), (ItemLike)Items.f_42350_)
/* 107 */       .m_126130_(" # ")
/* 108 */       .m_126130_("SZR")
/* 109 */       .m_126130_(" X ")
/* 110 */       .m_126132_("has_spyglass", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_151059_))
/* 111 */       .m_176498_(result);
/*     */ 
/*     */     
/* 114 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.NONE, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.WITHER_STORM.get())
/* 115 */       .requires((ItemLike)Items.f_42679_, 3)
/* 116 */       .unlockedBy("has_wither_skull", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42679_))
/* 117 */       .m_176498_(result);
/*     */ 
/*     */     
/* 120 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULLY_COMLETED, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get())
/* 121 */       .requires((ItemLike)WitherStormModItems.INFECTED_FLESH_BLOCK.get(), 1)
/* 122 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
/* 123 */       .requires((ItemLike)WitherStormModItems.WITHERED_BONE.get(), 1)
/* 124 */       .requires((ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get(), 1)
/* 125 */       .requires((ItemLike)Items.f_42679_)
/* 126 */       .unlockedBy("has_wither_skeleton_skull", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42679_))
/* 127 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 128 */       .unlockedBy("has_withered_bone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_BONE.get()))
/* 129 */       .unlockedBy("has_infected_flesh_block", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.INFECTED_FLESH_BLOCK.get()))
/* 130 */       .unlockedBy("has_tainted_dust_block", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get()))
/* 131 */       .m_176498_(result);
/*     */ 
/*     */     
/* 134 */     reubenPig(result);
/*     */ 
/*     */     
/* 137 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get())
/* 138 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get())
/* 139 */       .requires((ItemLike)WitherStormModItems.WITHERED_BONE.get())
/* 140 */       .requires((ItemLike)Items.f_42402_)
/* 141 */       .unlockedBy("has_withered_bone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_BONE.get()))
/* 142 */       .unlockedBy("has_feather", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42402_))
/* 143 */       .m_176498_(result);
/*     */ 
/*     */     
/* 146 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_COW.get())
/* 147 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
/* 148 */       .requires((ItemLike)Items.f_42454_, 2)
/* 149 */       .requires((ItemLike)Items.f_42579_, 2)
/* 150 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 151 */       .unlockedBy("has_leather", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42454_))
/* 152 */       .unlockedBy("has_beef", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42579_))
/* 153 */       .m_176498_(result);
/*     */ 
/*     */     
/* 156 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_PIG.get())
/* 157 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
/* 158 */       .requires((ItemLike)Items.f_42485_, 3)
/* 159 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 160 */       .unlockedBy("has_pork", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42485_))
/* 161 */       .m_176498_(result);
/*     */ 
/*     */     
/* 164 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_BEE.get())
/* 165 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
/* 166 */       .requires((ItemLike)Items.f_42787_, 2)
/* 167 */       .requires((ItemLike)Items.f_42784_, 4)
/* 168 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 169 */       .unlockedBy("has_honey_bottle", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42787_))
/* 170 */       .unlockedBy("has_honeycomb", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42784_))
/* 171 */       .m_176498_(result);
/*     */ 
/*     */     
/* 174 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get())
/* 175 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
/* 176 */       .requires((ItemLike)Items.f_42500_, 2)
/* 177 */       .requires((ItemLike)Items.f_42656_, 1)
/* 178 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 179 */       .unlockedBy("has_bone", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42500_))
/* 180 */       .unlockedBy("has_name_tag", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42656_))
/* 181 */       .m_176498_(result);
/*     */ 
/*     */     
/* 184 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_CAT.get())
/* 185 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
/* 186 */       .requires(Ingredient.m_204132_(ItemTags.f_13156_), 2)
/* 187 */       .requires((ItemLike)Items.f_42656_, 1)
/* 188 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 189 */       .unlockedBy("has_bone", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42500_))
/* 190 */       .unlockedBy("has_name_tag", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42656_))
/* 191 */       .m_176498_(result);
/*     */ 
/*     */     
/* 194 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get())
/* 195 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 1)
/*     */       
/* 197 */       .requires(Ingredient.m_43929_(new ItemLike[] { (ItemLike)Items.f_42733_, (ItemLike)Items.f_42578_, (ItemLike)Items.f_42577_, (ItemLike)Items.f_42404_, (ItemLike)Items.f_271133_ }, ), 8)
/* 198 */       .requires((ItemLike)Items.f_42656_, 1)
/* 199 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 200 */       .unlockedBy("has_bone", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42500_))
/* 201 */       .unlockedBy("has_name_tag", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42656_))
/* 202 */       .m_176498_(result);
/*     */ 
/*     */ 
/*     */     
/* 206 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get())
/* 207 */       .requires((ItemLike)Items.f_42403_, 3)
/* 208 */       .requires((ItemLike)WitherStormModItems.TAINTED_DUST.get(), 3)
/* 209 */       .unlockedBy("has_gunpowder", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42403_))
/* 210 */       .unlockedBy("has_tainted_dust", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
/* 211 */       .m_176498_(result);
/*     */ 
/*     */     
/* 214 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get())
/* 215 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
/* 216 */       .requires((ItemLike)WitherStormModItems.TAINTED_MUSHROOM.get(), 2)
/* 217 */       .requires((ItemLike)Items.f_42579_, 2)
/* 218 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 219 */       .unlockedBy("has_tainted_mushroom", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_MUSHROOM.get()))
/* 220 */       .unlockedBy("has_beef", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42579_))
/* 221 */       .m_176498_(result);
/*     */ 
/*     */     
/* 224 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get())
/* 225 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
/* 226 */       .requires((ItemLike)WitherStormModItems.WITHERED_BONE.get(), 2)
/* 227 */       .requires((ItemLike)Items.f_42714_)
/* 228 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 229 */       .unlockedBy("has_withered_bone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_BONE.get()))
/* 230 */       .unlockedBy("has_phantom_membrane", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42714_))
/* 231 */       .m_176498_(result);
/*     */ 
/*     */     
/* 234 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get())
/* 235 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
/* 236 */       .requires((ItemLike)Items.f_42717_)
/* 237 */       .requires((ItemLike)Items.f_42412_, 2)
/* 238 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 239 */       .unlockedBy("has_crossbow", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42717_))
/* 240 */       .unlockedBy("has_arrow", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42412_))
/* 241 */       .m_176498_(result);
/*     */ 
/*     */     
/* 244 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get())
/* 245 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 4)
/* 246 */       .requires((ItemLike)Items.f_42386_)
/* 247 */       .requires((ItemLike)Items.f_42616_)
/* 248 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 249 */       .unlockedBy("has_iron_axe", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42386_))
/* 250 */       .unlockedBy("has_emerald", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42616_))
/* 251 */       .m_176498_(result);
/*     */ 
/*     */     
/* 254 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get())
/* 255 */       .requires((ItemLike)Items.f_42500_, 3)
/* 256 */       .requires((ItemLike)WitherStormModItems.WITHERED_BONE.get(), 3)
/* 257 */       .unlockedBy("has_bone", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42500_))
/* 258 */       .unlockedBy("has_withered_bone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_BONE.get()))
/* 259 */       .m_176498_(result);
/*     */ 
/*     */     
/* 262 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get())
/* 263 */       .requires((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get(), 3)
/* 264 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
/* 265 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 266 */       .unlockedBy("has_withered_spider_eye", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get()))
/* 267 */       .m_176498_(result);
/*     */ 
/*     */     
/* 270 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get())
/* 271 */       .requires((ItemLike)Items.f_42583_, 2)
/* 272 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 2)
/* 273 */       .requires((ItemLike)Items.f_42616_, 2)
/* 274 */       .requires((ItemLike)Items.f_42436_)
/* 275 */       .unlockedBy("has_rotten_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42583_))
/* 276 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 277 */       .unlockedBy("has_emerald", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42616_))
/* 278 */       .unlockedBy("has_golden_apple", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42436_))
/* 279 */       .m_176498_(result);
/*     */ 
/*     */     
/* 282 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.MISC, (EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get())
/* 283 */       .requires((ItemLike)Items.f_42583_, 3)
/* 284 */       .requires((ItemLike)WitherStormModItems.WITHERED_FLESH.get(), 3)
/* 285 */       .unlockedBy("has_rotten_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42583_))
/* 286 */       .unlockedBy("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 287 */       .m_176498_(result);
/*     */ 
/*     */     
/* 290 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.MISC, (ItemLike)WitherStormModItems.SUPER_BEACON.get())
/* 291 */       .m_126127_(Character.valueOf('G'), (ItemLike)WitherStormModItems.TAINTED_GLASS.get())
/* 292 */       .m_126127_(Character.valueOf('S'), (ItemLike)WitherStormModItems.WITHERED_NETHER_STAR.get())
/* 293 */       .m_126127_(Character.valueOf('I'), (ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get())
/* 294 */       .m_126127_(Character.valueOf('A'), (ItemLike)WitherStormModItems.AMULET.get())
/* 295 */       .m_126130_("GGG")
/* 296 */       .m_126130_("GSG")
/* 297 */       .m_126130_("IAI")
/* 298 */       .m_126132_("has_withered_nether_star", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_NETHER_STAR.get()))
/* 299 */       .m_176498_(result);
/*     */ 
/*     */     
/* 302 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.MISC, (ItemLike)WitherStormModItems.SUPER_SUPPORT_BEACON.get())
/* 303 */       .m_126127_(Character.valueOf('G'), (ItemLike)WitherStormModItems.TAINTED_GLASS.get())
/* 304 */       .m_126127_(Character.valueOf('S'), (ItemLike)Items.f_42686_)
/* 305 */       .m_126127_(Character.valueOf('I'), (ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get())
/* 306 */       .m_126130_("GGG")
/* 307 */       .m_126130_("GSG")
/* 308 */       .m_126130_("III")
/* 309 */       .m_126132_("has_nether_star", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42686_))
/* 310 */       .m_176498_(result);
/*     */ 
/*     */     
/* 313 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.MISC, (ItemLike)WitherStormModItems.SUPER_TNT.get())
/* 314 */       .m_126127_(Character.valueOf('#'), (ItemLike)Items.f_41996_)
/* 315 */       .m_126127_(Character.valueOf('X'), (ItemLike)Items.f_42403_)
/* 316 */       .m_126127_(Character.valueOf('A'), (ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get())
/* 317 */       .m_126130_("X#X")
/* 318 */       .m_126130_("#A#")
/* 319 */       .m_126130_("X#X")
/* 320 */       .m_126132_("has_command_block_book", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get()))
/* 321 */       .m_176498_(result);
/*     */ 
/*     */     
/* 324 */     m_176658_((ItemLike)WitherStormModItems.TAINTED_BUTTON.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_PLANKS.get()
/* 325 */           })).m_126132_("has_tainted_planks", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
/* 326 */       .m_176498_(result);
/*     */ 
/*     */     
/* 329 */     SingleItemRecipeBuilder.m_246944_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE_SLAB
/*     */         
/* 331 */         .get(), 2)
/* 332 */       .m_126132_("has_tainted_cobblestone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()))
/* 333 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 336 */     m_246658_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get(), (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
/*     */ 
/*     */     
/* 339 */     SingleItemRecipeBuilder.m_245264_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get() }, ), RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get())
/* 340 */       .m_126132_("has_tainted_cobblestone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()))
/* 341 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 344 */     m_176710_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE_STAIRS.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get() })).m_126132_("has_tainted_cobblestone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()))
/* 345 */       .m_176498_(result);
/*     */ 
/*     */     
/* 348 */     SingleItemRecipeBuilder.m_245264_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL
/*     */         
/* 350 */         .get())
/* 351 */       .m_126132_("has_tainted_cobblestone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE_WALL.get()))
/* 352 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 355 */     m_246382_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE_WALL.get(), (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get());
/*     */ 
/*     */     
/* 358 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get())
/* 359 */       .requires((ItemLike)Items.f_42594_, 6)
/* 360 */       .unlockedBy("has_cobblestone", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42594_))
/* 361 */       .m_176498_(result);
/*     */ 
/*     */     
/* 364 */     m_176670_((ItemLike)WitherStormModItems.TAINTED_DOOR.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_PLANKS.get()
/* 365 */           })).m_126132_("has_tainted_planks", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
/* 366 */       .m_176498_(result);
/*     */ 
/*     */     
/* 369 */     m_176726_((ItemLike)WitherStormModItems.TAINTED_SIGN.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_PLANKS.get()
/* 370 */           })).m_126132_("has_tainted_planks", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
/* 371 */       .m_176498_(result);
/*     */ 
/*     */     
/* 374 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get())
/* 375 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.TAINTED_DUST.get())
/* 376 */       .m_126130_("##")
/* 377 */       .m_126130_("##")
/* 378 */       .m_126132_("has_tainted_dust", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
/* 379 */       .m_176498_(result);
/*     */ 
/*     */     
/* 382 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_DUST.get())
/* 383 */       .requires((ItemLike)Items.f_42451_, 6)
/* 384 */       .unlockedBy("has_redstone", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42451_))
/* 385 */       .m_176498_(result);
/*     */ 
/*     */     
/* 388 */     m_176684_((ItemLike)WitherStormModItems.TAINTED_FENCE_GATE.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_PLANKS.get()
/* 389 */           })).m_126132_("has_tainted_planks", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModBlocks.TAINTED_PLANKS.get()))
/* 390 */       .m_176498_(result);
/*     */ 
/*     */     
/* 393 */     m_176678_((ItemLike)WitherStormModItems.TAINTED_FENCE.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModBlocks.TAINTED_PLANKS.get()
/* 394 */           })).m_126132_("has_tainted_planks", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModBlocks.TAINTED_PLANKS.get()))
/* 395 */       .m_176498_(result);
/*     */ 
/*     */     
/* 398 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get())
/* 399 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.WITHERED_FLESH.get())
/* 400 */       .m_126130_("###")
/* 401 */       .m_126130_("###")
/* 402 */       .m_126130_("###")
/* 403 */       .m_126132_("has_withered_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()))
/* 404 */       .m_176498_(result);
/*     */ 
/*     */     
/* 407 */     ShapedRecipeBuilder.m_246608_(RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_GLASS_PANE.get(), 16)
/* 408 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.TAINTED_GLASS.get())
/* 409 */       .m_126130_("###")
/* 410 */       .m_126130_("###")
/* 411 */       .m_126132_("has_tainted_glass", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_GLASS.get()))
/* 412 */       .m_176498_(result);
/*     */ 
/*     */     
/* 415 */     SimpleCookingRecipeBuilder.m_246179_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SAND.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_GLASS.get(), 0.1F, 200)
/* 416 */       .m_126132_("has_tainted_sand", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SAND.get()))
/* 417 */       .m_176498_(result);
/*     */ 
/*     */     
/* 420 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_JACK_O_LANTERN.get())
/* 421 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get())
/* 422 */       .m_126124_(Character.valueOf('A'), Ingredient.m_43929_(new ItemLike[] { (ItemLike)Items.f_42000_, (ItemLike)WitherStormModItems.TAINTED_TORCH.get()
/* 423 */           })).m_126130_("#")
/* 424 */       .m_126130_("A")
/* 425 */       .m_126132_("has_tainted_carved_pumpkin", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get()))
/* 426 */       .m_176498_(result);
/*     */ 
/*     */     
/* 429 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_LOG.get())
/* 430 */       .requires(Ingredient.m_204132_(ItemTags.f_13182_), 6)
/* 431 */       .unlockedBy("has_log", (CriterionTriggerInstance)m_206406_(ItemTags.f_13182_))
/* 432 */       .m_176498_(result);
/*     */ 
/*     */     
/* 435 */     m_257424_(result, (ItemLike)WitherStormModItems.TAINTED_PLANKS.get(), WitherStormModItemTags.TAINTED_LOGS, 4);
/*     */ 
/*     */     
/* 438 */     m_176690_(result, (ItemLike)WitherStormModItems.TAINTED_PRESSURE_PLATE.get(), (ItemLike)WitherStormModItems.TAINTED_PLANKS.get());
/*     */ 
/*     */     
/* 441 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_SAND.get())
/* 442 */       .requires((ItemLike)Items.f_41830_, 6)
/* 443 */       .unlockedBy("has_sand", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_41830_))
/* 444 */       .m_176498_(result);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 453 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get())
/* 454 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.TAINTED_SAND.get())
/* 455 */       .m_126130_("##")
/* 456 */       .m_126130_("##")
/* 457 */       .m_126132_("has_tainted_sand", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SAND.get()))
/* 458 */       .m_176498_(result);
/*     */ 
/*     */     
/* 461 */     m_246658_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_SLAB.get(), (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get());
/*     */ 
/*     */     
/* 464 */     m_176710_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE_STAIRS.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()
/* 465 */           })).m_126132_("has_tainted_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
/* 466 */       .m_176498_(result);
/*     */ 
/*     */     
/* 469 */     SingleItemRecipeBuilder.m_246944_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_SLAB.get(), 2)
/* 470 */       .m_126132_("has_tainted_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
/* 471 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_SANDSTONE_SLAB.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 474 */     SingleItemRecipeBuilder.m_245264_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get() }, ), RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_STAIRS.get())
/* 475 */       .m_126132_("has_tainted_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
/* 476 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_SANDSTONE_STAIRS.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 479 */     SingleItemRecipeBuilder.m_245264_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModBlocks.TAINTED_SANDSTONE.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModBlocks.TAINTED_SANDSTONE_WALL
/*     */         
/* 481 */         .get())
/* 482 */       .m_126132_("has_tainted_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
/* 483 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_SANDSTONE_WALL.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 486 */     m_246382_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_WALL.get(), (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 495 */     ShapedRecipeBuilder.m_246608_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE.get(), 4)
/* 496 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get())
/* 497 */       .m_126130_("##")
/* 498 */       .m_126130_("##")
/* 499 */       .m_126132_("has_tainted_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
/* 500 */       .m_176498_(result);
/*     */ 
/*     */     
/* 503 */     m_246658_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE_SLAB.get(), (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE.get());
/*     */ 
/*     */     
/* 506 */     SingleItemRecipeBuilder.m_246944_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE_SLAB.get(), 2)
/* 507 */       .m_126132_("has_tainted_cut_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_CUT_SANDSTONE.get()))
/* 508 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_CUT_SANDSTONE_SLAB.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 517 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_CHISELED_SANDSTONE.get())
/* 518 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.TAINTED_SANDSTONE_SLAB.get())
/* 519 */       .m_126130_("#")
/* 520 */       .m_126130_("#")
/* 521 */       .m_126132_("has_tainted_sandstone_slab", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE_SLAB.get()))
/* 522 */       .m_176498_(result);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 531 */     SimpleCookingRecipeBuilder.m_246179_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get() }, ), RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get(), 0.1F, 200)
/* 532 */       .m_126132_("has_tainted_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
/* 533 */       .m_176498_(result);
/*     */ 
/*     */     
/* 536 */     m_246658_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_SLAB.get(), (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get());
/*     */ 
/*     */     
/* 539 */     m_176710_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_STAIRS.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()
/* 540 */           })).m_126132_("has_tainted_smooth_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()))
/* 541 */       .m_176498_(result);
/*     */ 
/*     */     
/* 544 */     SingleItemRecipeBuilder.m_246944_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_SLAB.get(), 2)
/* 545 */       .m_126132_("has_tainted_smooth_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()))
/* 546 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_SLAB.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 549 */     SingleItemRecipeBuilder.m_245264_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get() }, ), RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_STAIRS.get())
/* 550 */       .m_126132_("has_tainted_smooth_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()))
/* 551 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_STAIRS.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 554 */     SingleItemRecipeBuilder.m_245264_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL
/*     */         
/* 556 */         .get())
/* 557 */       .m_126132_("has_tainted_smooth_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get()))
/* 558 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 561 */     m_246382_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_WALL.get(), (ItemLike)WitherStormModItems.TAINTED_SMOOTH_SANDSTONE.get());
/*     */ 
/*     */     
/* 564 */     m_246658_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_SLAB.get(), (ItemLike)WitherStormModItems.TAINTED_PLANKS.get());
/*     */ 
/*     */     
/* 567 */     m_176710_((ItemLike)WitherStormModItems.TAINTED_STAIRS.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_PLANKS.get()
/* 568 */           })).m_126132_("has_tainted_planks", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
/* 569 */       .m_176498_(result);
/*     */ 
/*     */     
/* 572 */     m_176658_((ItemLike)WitherStormModItems.TAINTED_STONE_BUTTON.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_STONE.get()
/* 573 */           })).m_126132_("has_tainted_stone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_STONE.get()))
/* 574 */       .m_176498_(result);
/*     */ 
/*     */     
/* 577 */     m_176690_(result, (ItemLike)WitherStormModItems.TAINTED_STONE_PRESSURE_PLATE.get(), (ItemLike)WitherStormModItems.TAINTED_STONE.get());
/*     */ 
/*     */     
/* 580 */     SingleItemRecipeBuilder.m_246944_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_STONE.get() }, ), RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_STONE_SLAB.get(), 2)
/* 581 */       .m_126132_("has_tainted_stone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_STONE.get()))
/* 582 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_STONE_SLAB.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 585 */     m_246658_(result, RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_STONE_SLAB.get(), (ItemLike)WitherStormModItems.TAINTED_STONE.get());
/*     */ 
/*     */     
/* 588 */     SingleItemRecipeBuilder.m_245264_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_STONE.get() }, ), RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_STONE_STAIRS.get())
/* 589 */       .m_126132_("has_tainted_stone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_STONE.get()))
/* 590 */       .m_126140_(result, new ResourceLocation("witherstormmod", WitherStormModItems.TAINTED_STONE_STAIRS.getId().m_135815_() + "_stonecutting"));
/*     */ 
/*     */     
/* 593 */     m_176710_((ItemLike)WitherStormModItems.TAINTED_STONE_STAIRS.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_STONE.get()
/* 594 */           })).m_126132_("has_tainted_stone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_STONE.get()))
/* 595 */       .m_176498_(result);
/*     */ 
/*     */     
/* 598 */     SimpleCookingRecipeBuilder.m_246179_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get() }, ), RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_STONE.get(), 0.1F, 200)
/* 599 */       .m_126132_("has_tainted_cobblestone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_COBBLESTONE.get()))
/* 600 */       .m_176498_(result);
/*     */ 
/*     */     
/* 603 */     m_176720_((ItemLike)WitherStormModItems.TAINTED_TRAPDOOR.get(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_PLANKS.get()
/* 604 */           })).m_126132_("has_tainted_planks", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_PLANKS.get()))
/* 605 */       .m_176498_(result);
/*     */ 
/*     */     
/* 608 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_WOOD.get())
/* 609 */       .m_126127_(Character.valueOf('#'), (ItemLike)WitherStormModItems.TAINTED_LOG.get())
/* 610 */       .m_126130_("##")
/* 611 */       .m_126130_("##")
/* 612 */       .m_126132_("has_tainted_log", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_LOG.get()))
/* 613 */       .m_176498_(result);
/*     */ 
/*     */     
/* 616 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.MISC, (ItemLike)WitherStormModItems.WITHERED_BONE.get())
/* 617 */       .requires((ItemLike)Items.f_42500_, 6)
/* 618 */       .unlockedBy("has_bone", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42500_))
/* 619 */       .m_176498_(result);
/*     */ 
/*     */     
/* 622 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.MISC, (ItemLike)WitherStormModItems.WITHERED_FLESH.get())
/* 623 */       .requires((ItemLike)Items.f_42583_, 6)
/* 624 */       .unlockedBy("has_rotten_flesh", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42583_))
/* 625 */       .m_176498_(result);
/*     */ 
/*     */     
/* 628 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.MISC, (ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get())
/* 629 */       .requires((ItemLike)Items.f_42591_, 6)
/* 630 */       .unlockedBy("has_spider_eye", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42591_))
/* 631 */       .m_176498_(result);
/*     */ 
/*     */     
/* 634 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.COMBAT, (ItemLike)WitherStormModItems.EYE_OF_THE_STORM.get())
/* 635 */       .requires((ItemLike)WitherStormModItems.COMMAND_BLOCK_SWORD.get())
/* 636 */       .requires((ItemLike)WitherStormModItems.TAINTED_FLESH_BLOCK.get(), 4)
/* 637 */       .requires((ItemLike)WitherStormModItems.TAINTED_DUST.get()).requires((ItemLike)Items.f_42619_)
/* 638 */       .unlockedBy("has_command_block_sword", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.COMMAND_BLOCK_SWORD.get()))
/* 639 */       .m_176498_(result);
/*     */ 
/*     */     
/* 642 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.FULL_SUPPORTS, RecipeCategory.COMBAT, (ItemLike)WitherStormModItems.FORMIDI_BLADE.get())
/* 643 */       .requires((ItemLike)WitherStormModItems.COMMAND_BLOCK_SWORD.get())
/* 644 */       .requires((ItemLike)Items.f_42729_, 6)
/* 645 */       .requires((ItemLike)WitherStormModItems.FORMIDIBOMB.get())
/* 646 */       .unlockedBy("has_command_block_sword", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.COMMAND_BLOCK_SWORD.get()))
/* 647 */       .m_176498_(result);
/*     */ 
/*     */     
/* 650 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.REDSTONE, (ItemLike)WitherStormModItems.WITHERED_PHLEGM_BLOCK.get())
/* 651 */       .m_126127_(Character.valueOf('~'), (ItemLike)WitherStormModItems.TAINTED_DUST.get())
/* 652 */       .m_126127_(Character.valueOf('H'), (ItemLike)Items.f_42155_)
/* 653 */       .m_126127_(Character.valueOf('B'), (ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get())
/* 654 */       .m_126127_(Character.valueOf('C'), (ItemLike)Items.f_42009_)
/* 655 */       .m_126127_(Character.valueOf('S'), (ItemLike)Items.f_42518_)
/* 656 */       .m_126130_("SHS")
/* 657 */       .m_126130_("~B~")
/* 658 */       .m_126130_("SCS")
/* 659 */       .m_126132_("has_tainted_dust_block", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST_BLOCK.get()))
/* 660 */       .m_126132_("has_tainted_dust", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
/* 661 */       .m_176498_(result);
/*     */     
/* 663 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_DIRT.get())
/* 664 */       .requires((ItemLike)Items.f_42329_, 6)
/* 665 */       .unlockedBy("has_dirt", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42329_))
/* 666 */       .m_176498_(result);
/*     */     
/* 668 */     SuperBeaconRecipeBuilder.item(SuperBeaconRecipe.Condition.MAIN_ACTIVATED, RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_TORCH.get())
/* 669 */       .requires((ItemLike)Items.f_42000_, 3)
/* 670 */       .unlockedBy("has_torch", (CriterionTriggerInstance)m_125977_((ItemLike)Items.f_42000_))
/* 671 */       .m_126140_(result, WitherStormMod.id("tainted_torch_beacon"));
/*     */     
/* 673 */     ShapedRecipeBuilder.m_245327_(RecipeCategory.DECORATIONS, (ItemLike)WitherStormModItems.TAINTED_TORCH.get())
/* 674 */       .m_126127_(Character.valueOf('~'), (ItemLike)Items.f_42398_)
/* 675 */       .m_126127_(Character.valueOf('1'), (ItemLike)WitherStormModItems.TAINTED_DUST.get())
/* 676 */       .m_126130_("1")
/* 677 */       .m_126130_("~")
/* 678 */       .m_126132_("has_tainted_dust", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_DUST.get()))
/* 679 */       .m_176498_(result);
/*     */     
/* 681 */     SingleItemRecipeBuilder.m_245264_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get() }, ), RecipeCategory.BUILDING_BLOCKS, (ItemLike)WitherStormModItems.TAINTED_CHISELED_SANDSTONE.get())
/* 682 */       .m_126132_("has_tainted_sandstone", (CriterionTriggerInstance)m_125977_((ItemLike)WitherStormModItems.TAINTED_SANDSTONE.get()))
/* 683 */       .m_126140_(result, WitherStormMod.id("tainted_chiseled_sandstone_stonecutting"));
/*     */     
/* 685 */     cmdTool(result, Items.f_42388_, RecipeCategory.COMBAT, (Item)WitherStormModItems.COMMAND_BLOCK_SWORD.get());
/* 686 */     cmdTool(result, Items.f_42390_, RecipeCategory.TOOLS, (Item)WitherStormModItems.COMMAND_BLOCK_PICKAXE.get());
/* 687 */     cmdTool(result, Items.f_42391_, RecipeCategory.TOOLS, (Item)WitherStormModItems.COMMAND_BLOCK_AXE.get());
/* 688 */     cmdTool(result, Items.f_42389_, RecipeCategory.TOOLS, (Item)WitherStormModItems.COMMAND_BLOCK_SHOVEL.get());
/* 689 */     cmdTool(result, Items.f_42392_, RecipeCategory.TOOLS, (Item)WitherStormModItems.COMMAND_BLOCK_HOE.get());
/* 690 */     cmdTool(result, Items.f_42420_, RecipeCategory.COMBAT, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_SWORD.get());
/* 691 */     cmdTool(result, Items.f_42422_, RecipeCategory.TOOLS, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_PICKAXE.get());
/* 692 */     cmdTool(result, Items.f_42423_, RecipeCategory.TOOLS, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_AXE.get());
/* 693 */     cmdTool(result, Items.f_42421_, RecipeCategory.TOOLS, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_SHOVEL.get());
/* 694 */     cmdTool(result, Items.f_42424_, RecipeCategory.TOOLS, (Item)WitherStormModItems.WOOD_COMMAND_BLOCK_HOE.get());
/* 695 */     cmdTool(result, Items.f_42425_, RecipeCategory.COMBAT, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_SWORD.get());
/* 696 */     cmdTool(result, Items.f_42427_, RecipeCategory.TOOLS, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_PICKAXE.get());
/* 697 */     cmdTool(result, Items.f_42428_, RecipeCategory.TOOLS, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_AXE.get());
/* 698 */     cmdTool(result, Items.f_42426_, RecipeCategory.TOOLS, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_SHOVEL.get());
/* 699 */     cmdTool(result, Items.f_42429_, RecipeCategory.TOOLS, (Item)WitherStormModItems.STONE_COMMAND_BLOCK_HOE.get());
/* 700 */     cmdTool(result, Items.f_42383_, RecipeCategory.COMBAT, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_SWORD.get());
/* 701 */     cmdTool(result, Items.f_42385_, RecipeCategory.TOOLS, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_PICKAXE.get());
/* 702 */     cmdTool(result, Items.f_42386_, RecipeCategory.TOOLS, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_AXE.get());
/* 703 */     cmdTool(result, Items.f_42384_, RecipeCategory.TOOLS, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_SHOVEL.get());
/* 704 */     cmdTool(result, Items.f_42387_, RecipeCategory.TOOLS, (Item)WitherStormModItems.IRON_COMMAND_BLOCK_HOE.get());
/* 705 */     cmdTool(result, Items.f_42430_, RecipeCategory.COMBAT, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_SWORD.get());
/* 706 */     cmdTool(result, Items.f_42432_, RecipeCategory.TOOLS, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_PICKAXE.get());
/* 707 */     cmdTool(result, Items.f_42433_, RecipeCategory.TOOLS, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_AXE.get());
/* 708 */     cmdTool(result, Items.f_42431_, RecipeCategory.TOOLS, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_SHOVEL.get());
/* 709 */     cmdTool(result, Items.f_42434_, RecipeCategory.TOOLS, (Item)WitherStormModItems.GOLD_COMMAND_BLOCK_HOE.get());
/*     */   }
/*     */ 
/*     */   
/*     */   private static RecipeBuilder unlockedByItems(RecipeBuilder builder, Item... items) {
/* 714 */     for (Item item : items)
/* 715 */       builder.m_126132_(m_176602_((ItemLike)item), (CriterionTriggerInstance)m_125977_((ItemLike)item)); 
/* 716 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void reubenPig(Consumer<FinishedRecipe> consumer) {
/* 721 */     CompoundTag pigTag = new CompoundTag();
/* 722 */     pigTag.m_128405_("Age", -1200);
/* 723 */     CompoundTag name = new CompoundTag();
/* 724 */     name.m_128359_("text", "reuben");
/* 725 */     pigTag.m_128365_("CustomName", (Tag)name);
/* 726 */     SuperBeaconRecipeBuilder.entity(SuperBeaconRecipe.Condition.NONE, RecipeCategory.MISC, EntityType.f_20510_, pigTag).requires((ItemLike)Items.f_42485_, 16).m_126140_(consumer, new ResourceLocation("witherstormmod", "summon_pig"));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void cmdTool(Consumer<FinishedRecipe> result, Item required, RecipeCategory category, Item output) {
/* 731 */     AnvilRecipeBuilder.commandBlockTool((ItemLike)required, (ItemLike)output).m_126140_(result, WitherStormMod.id(m_176632_((ItemLike)output) + "_anvil"));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\WitherStormModRecipeProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */