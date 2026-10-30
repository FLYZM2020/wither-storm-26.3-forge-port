/*     */ package nonamecrackers2.witherstormmod.common.init;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.food.FoodProperties;
/*     */ import net.minecraft.world.item.BlockItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemNameBlockItem;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.Rarity;
/*     */ import net.minecraft.world.item.SignItem;
/*     */ import net.minecraft.world.item.SimpleFoiledItem;
/*     */ import net.minecraft.world.item.StandingAndWallBlockItem;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.alchemy.Potion;
/*     */ import net.minecraft.world.item.alchemy.PotionUtils;
/*     */ import net.minecraft.world.item.alchemy.Potions;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraftforge.common.ForgeSpawnEggItem;
/*     */ import net.minecraftforge.common.brewing.BrewingRecipe;
/*     */ import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
/*     */ import net.minecraftforge.common.brewing.IBrewingRecipe;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import net.minecraftforge.registries.RegistryObject;
/*     */ import nonamecrackers2.witherstormmod.common.item.AmuletItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.CommandBlockAxeItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.CommandBlockHoeItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.CommandBlockPickaxeItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.CommandBlockShovelItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.CommandBlockSwordItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.EyeOfTheStormItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.FormidiBladeItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.FormidibombItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.GoldenAppleStewItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.TaintedCarvedPumpkinItem;
/*     */ import nonamecrackers2.witherstormmod.common.item.WitheredNetherStarItem;
/*     */ import nonamecrackers2.witherstormmod.common.util.WitherStormModItemTier;
/*     */ 
/*     */ public class WitherStormModItems {
/*  44 */   public static final FoodProperties GOLDEN_APPLE_STEW_FOOD = (new FoodProperties.Builder()).m_38760_(5).m_38758_(1.0F)
/*  45 */     .effect(() -> new MobEffectInstance(MobEffects.f_19617_, 2600, 0), 1.0F)
/*  46 */     .effect(() -> new MobEffectInstance(MobEffects.f_19605_, 200, 0), 1.0F)
/*  47 */     .m_38765_()
/*  48 */     .m_38767_();
/*     */   
/*  50 */   public static final FoodProperties WITHERED_FLESH_FOOD = (new FoodProperties.Builder()).m_38760_(4).m_38758_(0.1F)
/*  51 */     .effect(() -> new MobEffectInstance(MobEffects.f_19612_, 800, 0), 0.8F)
/*  52 */     .effect(() -> new MobEffectInstance(MobEffects.f_19615_, 400, 0), 1.0F)
/*  53 */     .m_38757_()
/*  54 */     .m_38767_();
/*     */   
/*  56 */   public static final FoodProperties WITHERED_SPIDER_EYE_FOOD = (new FoodProperties.Builder()).m_38760_(2).m_38758_(0.8F)
/*  57 */     .effect(() -> new MobEffectInstance(MobEffects.f_19614_, 200, 0), 1.0F)
/*  58 */     .effect(() -> new MobEffectInstance(MobEffects.f_19615_, 400, 0), 1.0F)
/*  59 */     .m_38767_();
/*     */   
/*  61 */   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "witherstormmod");
/*     */ 
/*     */   
/*  64 */   public static final RegistryObject<Item> WITHERED_BONE = ITEMS.register("withered_bone", () -> new Item((new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/*  65 */   public static final RegistryObject<Item> WITHERED_FLESH = ITEMS.register("withered_flesh", () -> new Item((new Item.Properties()).m_41489_(WITHERED_FLESH_FOOD).m_41497_(Rarity.UNCOMMON)));
/*  66 */   public static final RegistryObject<Item> TAINTED_DUST = ITEMS.register("tainted_dust", () -> new ItemNameBlockItem((Block)WitherStormModBlocks.TAINTED_DUST.get(), (new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/*  67 */   public static final RegistryObject<Item> WITHERED_SPIDER_EYE = ITEMS.register("withered_spider_eye", () -> new Item((new Item.Properties()).m_41497_(Rarity.UNCOMMON).m_41489_(WITHERED_SPIDER_EYE_FOOD)));
/*     */ 
/*     */   
/*  70 */   public static final RegistryObject<Item> GOLDEN_APPLE_STEW = ITEMS.register("golden_apple_stew", () -> new GoldenAppleStewItem((new Item.Properties()).m_41497_(Rarity.RARE).m_41489_(GOLDEN_APPLE_STEW_FOOD).m_41487_(1)));
/*     */ 
/*     */   
/*  73 */   public static final RegistryObject<Item> AMULET = ITEMS.register("amulet", () -> new AmuletItem((new Item.Properties()).m_41487_(1).m_41486_().m_41497_(Rarity.UNCOMMON)));
/*  74 */   public static final RegistryObject<Item> COMMAND_BLOCK_BOOK = ITEMS.register("command_block_book", () -> new SimpleFoiledItem((new Item.Properties()).m_41497_(Rarity.RARE).m_41487_(1).m_41486_()));
/*  75 */   public static final RegistryObject<Item> WITHERED_NETHER_STAR = ITEMS.register("withered_nether_star", () -> new WitheredNetherStarItem((new Item.Properties()).m_41497_(Rarity.EPIC).m_41486_()));
/*     */ 
/*     */   
/*  78 */   public static final RegistryObject<Item> SICKENED_CREEPER_SPAWN_EGG = ITEMS.register("sickened_creeper_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_CREEPER, 9851315, 3278099, new Item.Properties()));
/*  79 */   public static final RegistryObject<Item> SICKENED_SKELETON_SPAWN_EGG = ITEMS.register("sickened_skeleton_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_SKELETON, 13606575, 3612758, new Item.Properties()));
/*  80 */   public static final RegistryObject<Item> SICKENED_SPIDER_SPAWN_EGG = ITEMS.register("sickened_spider_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_SPIDER, 2827051, 16056399, new Item.Properties()));
/*  81 */   public static final RegistryObject<Item> SICKENED_VILLAGER_SPAWN_EGG = ITEMS.register("sickened_villager_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_VILLAGER, 8551284, 11305627, new Item.Properties()));
/*  82 */   public static final RegistryObject<Item> SICKENED_ZOMBIE_SPAWN_EGG = ITEMS.register("sickened_zombie_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_ZOMBIE, 4808027, 10648470, new Item.Properties()));
/*  83 */   public static final RegistryObject<Item> SICKENED_PHANTOM_SPAWN_EGG = ITEMS.register("sickened_phantom_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_PHANTOM, 6967167, 16713046, new Item.Properties()));
/*  84 */   public static final RegistryObject<Item> SICKENED_CHICKEN_SPAWN_EGG = ITEMS.register("sickened_chicken_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_CHICKEN, 5977232, 5570648, new Item.Properties()));
/*  85 */   public static final RegistryObject<Item> SICKENED_PARROT_SPAWN_EGG = ITEMS.register("sickened_parrot_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_PARROT, 7032441, 5911693, new Item.Properties()));
/*  86 */   public static final RegistryObject<Item> SICKENED_WOLF_SPAWN_EGG = ITEMS.register("sickened_wolf_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_WOLF, 4866401, 7960207, new Item.Properties()));
/*  87 */   public static final RegistryObject<Item> SICKENED_CAT_SPAWN_EGG = ITEMS.register("sickened_cat_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_CAT, 1775149, 9595267, new Item.Properties()));
/*  88 */   public static final RegistryObject<Item> SICKENED_COW_SPAWN_EGG = ITEMS.register("sickened_cow_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_COW, 3613496, 10066329, new Item.Properties()));
/*  89 */   public static final RegistryObject<Item> SICKENED_PIG_SPAWN_EGG = ITEMS.register("sickened_pig_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_PIG, 6706811, 5786734, new Item.Properties()));
/*  90 */   public static final RegistryObject<Item> SICKENED_MUSHROOM_COW_SPAWN_EGG = ITEMS.register("sickened_mushroom_cow_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW, 8200599, 11887564, new Item.Properties()));
/*  91 */   public static final RegistryObject<Item> SICKENED_BEE_SPAWN_EGG = ITEMS.register("sickened_bee_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_BEE, 10711155, 3023140, new Item.Properties()));
/*  92 */   public static final RegistryObject<Item> SICKENED_PILLAGER_SPAWN_EGG = ITEMS.register("sickened_pillager_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_PILLAGER, 4403259, 10190758, new Item.Properties()));
/*  93 */   public static final RegistryObject<Item> SICKENED_VINDICATOR_SPAWN_EGG = ITEMS.register("sickened_vindicator_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_VINDICATOR, 10190758, 3422273, new Item.Properties()));
/*  94 */   public static final RegistryObject<Item> SICKENED_IRON_GOLEM_SPAWN_EGG = ITEMS.register("sickened_iron_golem_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_IRON_GOLEM, 13541842, 15270143, new Item.Properties()));
/*  95 */   public static final RegistryObject<Item> SICKENED_SNOW_GOLEM_SPAWN_EGG = ITEMS.register("sickened_snow_golem_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM, 15589887, 12754175, new Item.Properties()));
/*  96 */   public static final RegistryObject<Item> TENTACLE_SPAWN_EGG = ITEMS.register("tentacle_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.TENTACLE, 722193, 1379103, new Item.Properties()));
/*  97 */   public static final RegistryObject<Item> WITHERED_SYMBIONT_SPAWN_EGG = ITEMS.register("withered_symbiont_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)WitherStormModEntityTypes.WITHERED_SYMBIONT, 2233397, 16056568, new Item.Properties()));
/*     */ 
/*     */   
/* 100 */   public static final RegistryObject<Item> COMMAND_BLOCK_SWORD = ITEMS.register("command_block_sword", () -> new CommandBlockSwordItem((Tier)WitherStormModItemTier.COMMAND_BLOCK, 3, -2.4F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 101 */   public static final RegistryObject<Item> COMMAND_BLOCK_PICKAXE = ITEMS.register("command_block_pickaxe", () -> new CommandBlockPickaxeItem((Tier)WitherStormModItemTier.COMMAND_BLOCK, 1, -2.8F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 102 */   public static final RegistryObject<Item> COMMAND_BLOCK_AXE = ITEMS.register("command_block_axe", () -> new CommandBlockAxeItem((Tier)WitherStormModItemTier.COMMAND_BLOCK, 5, -3.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 103 */   public static final RegistryObject<Item> COMMAND_BLOCK_SHOVEL = ITEMS.register("command_block_shovel", () -> new CommandBlockShovelItem((Tier)WitherStormModItemTier.COMMAND_BLOCK, 1.5F, -3.4F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 104 */   public static final RegistryObject<Item> COMMAND_BLOCK_HOE = ITEMS.register("command_block_hoe", () -> new CommandBlockHoeItem((Tier)WitherStormModItemTier.COMMAND_BLOCK, -4, 0.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/*     */ 
/*     */ 
/*     */   
/* 108 */   public static final RegistryObject<Item> WOOD_COMMAND_BLOCK_SWORD = ITEMS.register("wooden_command_block_sword", () -> new CommandBlockSwordItem((Tier)WitherStormModItemTier.WOOD_CMD, 3, -2.4F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 109 */   public static final RegistryObject<Item> WOOD_COMMAND_BLOCK_PICKAXE = ITEMS.register("wooden_command_block_pickaxe", () -> new CommandBlockPickaxeItem((Tier)WitherStormModItemTier.WOOD_CMD, 1, -2.8F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 110 */   public static final RegistryObject<Item> WOOD_COMMAND_BLOCK_AXE = ITEMS.register("wooden_command_block_axe", () -> new CommandBlockAxeItem((Tier)WitherStormModItemTier.WOOD_CMD, 6, -3.2F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 111 */   public static final RegistryObject<Item> WOOD_COMMAND_BLOCK_SHOVEL = ITEMS.register("wooden_command_block_shovel", () -> new CommandBlockShovelItem((Tier)WitherStormModItemTier.WOOD_CMD, 1.5F, -3.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 112 */   public static final RegistryObject<Item> WOOD_COMMAND_BLOCK_HOE = ITEMS.register("wooden_command_block_hoe", () -> new CommandBlockHoeItem((Tier)WitherStormModItemTier.WOOD_CMD, -4, 3.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/*     */ 
/*     */   
/* 115 */   public static final RegistryObject<Item> STONE_COMMAND_BLOCK_SWORD = ITEMS.register("stone_command_block_sword", () -> new CommandBlockSwordItem((Tier)WitherStormModItemTier.STONE_CMD, 3, -2.4F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 116 */   public static final RegistryObject<Item> STONE_COMMAND_BLOCK_PICKAXE = ITEMS.register("stone_command_block_pickaxe", () -> new CommandBlockPickaxeItem((Tier)WitherStormModItemTier.STONE_CMD, 1, -2.8F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 117 */   public static final RegistryObject<Item> STONE_COMMAND_BLOCK_AXE = ITEMS.register("stone_command_block_axe", () -> new CommandBlockAxeItem((Tier)WitherStormModItemTier.STONE_CMD, 6, -3.2F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 118 */   public static final RegistryObject<Item> STONE_COMMAND_BLOCK_SHOVEL = ITEMS.register("stone_command_block_shovel", () -> new CommandBlockShovelItem((Tier)WitherStormModItemTier.STONE_CMD, 1.5F, -2.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 119 */   public static final RegistryObject<Item> STONE_COMMAND_BLOCK_HOE = ITEMS.register("stone_command_block_hoe", () -> new CommandBlockHoeItem((Tier)WitherStormModItemTier.STONE_CMD, -3, -3.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/*     */ 
/*     */   
/* 122 */   public static final RegistryObject<Item> IRON_COMMAND_BLOCK_SWORD = ITEMS.register("iron_command_block_sword", () -> new CommandBlockSwordItem((Tier)WitherStormModItemTier.IRON_CMD, 4, -2.8F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 123 */   public static final RegistryObject<Item> IRON_COMMAND_BLOCK_PICKAXE = ITEMS.register("iron_command_block_pickaxe", () -> new CommandBlockPickaxeItem((Tier)WitherStormModItemTier.IRON_CMD, 3, -3.2F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 124 */   public static final RegistryObject<Item> IRON_COMMAND_BLOCK_AXE = ITEMS.register("iron_command_block_axe", () -> new CommandBlockAxeItem((Tier)WitherStormModItemTier.IRON_CMD, 6, -3.1F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 125 */   public static final RegistryObject<Item> IRON_COMMAND_BLOCK_SHOVEL = ITEMS.register("iron_command_block_shovel", () -> new CommandBlockShovelItem((Tier)WitherStormModItemTier.IRON_CMD, 2.5F, -3.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 126 */   public static final RegistryObject<Item> IRON_COMMAND_BLOCK_HOE = ITEMS.register("iron_command_block_hoe", () -> new CommandBlockHoeItem((Tier)WitherStormModItemTier.IRON_CMD, 9, -3.5F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/*     */ 
/*     */   
/* 129 */   public static final RegistryObject<Item> GOLD_COMMAND_BLOCK_SWORD = ITEMS.register("gold_command_block_sword", () -> new CommandBlockSwordItem((Tier)WitherStormModItemTier.GOLD_CMD, -1, -1.2F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 130 */   public static final RegistryObject<Item> GOLD_COMMAND_BLOCK_PICKAXE = ITEMS.register("gold_command_block_pickaxe", () -> new CommandBlockPickaxeItem((Tier)WitherStormModItemTier.GOLD_CMD, 1, -2.8F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 131 */   public static final RegistryObject<Item> GOLD_COMMAND_BLOCK_AXE = ITEMS.register("gold_command_block_axe", () -> new CommandBlockAxeItem((Tier)WitherStormModItemTier.GOLD_CMD, 6, -3.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 132 */   public static final RegistryObject<Item> GOLD_COMMAND_BLOCK_SHOVEL = ITEMS.register("gold_command_block_shovel", () -> new CommandBlockShovelItem((Tier)WitherStormModItemTier.GOLD_CMD, 1.5F, -3.0F, (new Item.Properties()).m_41486_().m_41497_(Rarity.EPIC)));
/* 133 */   public static final RegistryObject<Item> GOLD_COMMAND_BLOCK_HOE = ITEMS.register("gold_command_block_hoe", () -> new CommandBlockHoeItem((Tier)WitherStormModItemTier.GOLD_CMD, 0, -3.0F, (new Item.Properties()).m_41486_().m_41486_().m_41497_(Rarity.EPIC)));
/*     */   
/* 135 */   public static final RegistryObject<Item> EYE_OF_THE_STORM = ITEMS.register("eye_of_the_storm", () -> new EyeOfTheStormItem((Tier)WitherStormModItemTier.EYE_OF_THE_STORM, 3, -2.4F, (new Item.Properties()).m_41497_(Rarity.EPIC)));
/* 136 */   public static final RegistryObject<Item> FORMIDI_BLADE = ITEMS.register("formidi_blade", () -> new FormidiBladeItem((Tier)WitherStormModItemTier.FORMIDI_BLADE, 3, -3.7F, (new Item.Properties()).m_41497_(Rarity.EPIC)));
/*     */   
/* 138 */   public static final RegistryObject<Item> SUPER_TNT = ITEMS.register("super_tnt", () -> new BlockItem((Block)WitherStormModBlocks.SUPER_TNT.get(), (new Item.Properties()).m_41497_(Rarity.RARE)));
/* 139 */   public static final RegistryObject<Item> FORMIDIBOMB = ITEMS.register("formidibomb", () -> new FormidibombItem((Block)WitherStormModBlocks.FORMIDIBOMB.get(), (new Item.Properties()).m_41497_(Rarity.EPIC).m_41487_(1).m_41486_()));
/* 140 */   public static final RegistryObject<Item> SUPER_BEACON = ITEMS.register("super_beacon", () -> new BlockItem((Block)WitherStormModBlocks.SUPER_BEACON.get(), (new Item.Properties()).m_41497_(Rarity.EPIC).m_41486_()));
/* 141 */   public static final RegistryObject<Item> SUPER_SUPPORT_BEACON = ITEMS.register("super_support_beacon", () -> new BlockItem((Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get(), (new Item.Properties()).m_41497_(Rarity.RARE).m_41486_()));
/* 142 */   public static final RegistryObject<Item> FIREWORK_BUNDLE = ITEMS.register("firework_bundle", () -> new BlockItem((Block)WitherStormModBlocks.FIREWORK_BUNDLE.get(), new Item.Properties()));
/* 143 */   public static final RegistryObject<Item> PHASOMETER = ITEMS.register("phasometer", () -> new PhasometerItem((new Item.Properties()).m_41487_(1).m_41497_(Rarity.UNCOMMON)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public static final RegistryObject<Item> TAINTED_ZOMBIE_SITTING = ITEMS.register("tainted_zombie_sitting", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get(), (new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/* 150 */   public static final RegistryObject<Item> TAINTED_ZOMBIE_WALL = ITEMS.register("tainted_zombie_wall", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_ZOMBIE_WALL.get(), (new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/* 151 */   public static final RegistryObject<Item> TAINTED_ZOMBIE_LYING = ITEMS.register("tainted_zombie_lying", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get(), (new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/* 152 */   public static final RegistryObject<Item> TAINTED_BONE_PILE = ITEMS.register("tainted_bone_pile", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get(), (new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/* 153 */   public static final RegistryObject<Item> TAINTED_SKELETON_WALL = ITEMS.register("tainted_skeleton_wall", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SKELETON_WALL.get(), (new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/* 154 */   public static final RegistryObject<Item> TAINTED_SKULL_CEILING = ITEMS.register("tainted_skull_ceiling", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SKULL_CEILING.get(), (new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/*     */ 
/*     */   
/* 157 */   public static final RegistryObject<Item> TAINTED_FLESH_VEINS = ITEMS.register("tainted_flesh_veins", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_FLESH_VEINS.get(), new Item.Properties()));
/* 158 */   public static final RegistryObject<Item> TAINTED_FLESH_BLOCK = ITEMS.register("tainted_flesh_block", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get(), new Item.Properties()));
/* 159 */   public static final RegistryObject<Item> INFECTED_FLESH_BLOCK = ITEMS.register("infected_flesh_block", () -> new BlockItem((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get(), new Item.Properties()));
/* 160 */   public static final RegistryObject<Item> HARDENED_FLESH_BLOCK = ITEMS.register("hardened_flesh_block", () -> new BlockItem((Block)WitherStormModBlocks.HARDENED_FLESH_BLOCK.get(), new Item.Properties()));
/* 161 */   public static final RegistryObject<Item> WITHERED_PHLEGM_BLOCK = ITEMS.register("withered_phlegm_block", () -> new BlockItem((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get(), (new Item.Properties()).m_41497_(Rarity.UNCOMMON)));
/*     */ 
/*     */   
/* 164 */   public static final RegistryObject<Item> TAINTED_STONE = ITEMS.register("tainted_stone", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_STONE.get(), new Item.Properties()));
/* 165 */   public static final RegistryObject<Item> TAINTED_STONE_STAIRS = ITEMS.register("tainted_stone_stairs", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get(), new Item.Properties()));
/* 166 */   public static final RegistryObject<Item> TAINTED_STONE_SLAB = ITEMS.register("tainted_stone_slab", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get(), new Item.Properties()));
/* 167 */   public static final RegistryObject<Item> TAINTED_STONE_BUTTON = ITEMS.register("tainted_stone_button", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get(), new Item.Properties()));
/* 168 */   public static final RegistryObject<Item> TAINTED_STONE_PRESSURE_PLATE = ITEMS.register("tainted_stone_pressure_plate", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get(), new Item.Properties()));
/*     */ 
/*     */   
/* 171 */   public static final RegistryObject<Item> TAINTED_COBBLESTONE = ITEMS.register("tainted_cobblestone", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get(), new Item.Properties()));
/* 172 */   public static final RegistryObject<Item> TAINTED_COBBLESTONE_STAIRS = ITEMS.register("tainted_cobblestone_stairs", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get(), new Item.Properties()));
/* 173 */   public static final RegistryObject<Item> TAINTED_COBBLESTONE_SLAB = ITEMS.register("tainted_cobblestone_slab", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get(), new Item.Properties()));
/* 174 */   public static final RegistryObject<Item> TAINTED_COBBLESTONE_WALL = ITEMS.register("tainted_cobblestone_wall", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get(), new Item.Properties()));
/*     */ 
/*     */   
/* 177 */   public static final RegistryObject<Item> TAINTED_DIRT = ITEMS.register("tainted_dirt", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_DIRT.get(), new Item.Properties()));
/* 178 */   public static final RegistryObject<Item> TAINTED_SAND = ITEMS.register("tainted_sand", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SAND.get(), new Item.Properties()));
/* 179 */   public static final RegistryObject<Item> TAINTED_SANDSTONE = ITEMS.register("tainted_sandstone", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SANDSTONE.get(), new Item.Properties()));
/* 180 */   public static final RegistryObject<Item> TAINTED_SANDSTONE_SLAB = ITEMS.register("tainted_sandstone_slab", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get(), new Item.Properties()));
/* 181 */   public static final RegistryObject<Item> TAINTED_SANDSTONE_STAIRS = ITEMS.register("tainted_sandstone_stairs", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get(), new Item.Properties()));
/* 182 */   public static final RegistryObject<Item> TAINTED_SANDSTONE_WALL = ITEMS.register("tainted_sandstone_wall", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get(), new Item.Properties()));
/* 183 */   public static final RegistryObject<Item> TAINTED_CUT_SANDSTONE = ITEMS.register("tainted_cut_sandstone", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get(), new Item.Properties()));
/* 184 */   public static final RegistryObject<Item> TAINTED_CUT_SANDSTONE_SLAB = ITEMS.register("tainted_cut_sandstone_slab", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get(), new Item.Properties()));
/* 185 */   public static final RegistryObject<Item> TAINTED_CHISELED_SANDSTONE = ITEMS.register("tainted_chiseled_sandstone", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_CHISELED_SANDSTONE.get(), new Item.Properties()));
/* 186 */   public static final RegistryObject<Item> TAINTED_SMOOTH_SANDSTONE = ITEMS.register("tainted_smooth_sandstone", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get(), new Item.Properties()));
/* 187 */   public static final RegistryObject<Item> TAINTED_SMOOTH_SANDSTONE_STAIRS = ITEMS.register("tainted_smooth_sandstone_stairs", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get(), new Item.Properties()));
/* 188 */   public static final RegistryObject<Item> TAINTED_SMOOTH_SANDSTONE_SLAB = ITEMS.register("tainted_smooth_sandstone_slab", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get(), new Item.Properties()));
/* 189 */   public static final RegistryObject<Item> TAINTED_SMOOTH_SANDSTONE_WALL = ITEMS.register("tainted_smooth_sandstone_wall", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.get(), new Item.Properties()));
/* 190 */   public static final RegistryObject<Item> TAINTED_GLASS = ITEMS.register("tainted_glass", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_GLASS.get(), new Item.Properties()));
/* 191 */   public static final RegistryObject<Item> TAINTED_GLASS_PANE = ITEMS.register("tainted_glass_pane", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get(), new Item.Properties()));
/*     */ 
/*     */   
/* 194 */   public static final RegistryObject<Item> TAINTED_PLANKS = ITEMS.register("tainted_planks", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_PLANKS.get(), new Item.Properties()));
/* 195 */   public static final RegistryObject<Item> TAINTED_LOG = ITEMS.register("tainted_log", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_LOG.get(), new Item.Properties()));
/* 196 */   public static final RegistryObject<Item> TAINTED_WOOD = ITEMS.register("tainted_wood", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_WOOD.get(), new Item.Properties()));
/* 197 */   public static final RegistryObject<Item> STRIPPED_TAINTED_LOG = ITEMS.register("stripped_tainted_log", () -> new BlockItem((Block)WitherStormModBlocks.STRIPPED_TAINTED_LOG.get(), new Item.Properties()));
/* 198 */   public static final RegistryObject<Item> STRIPPED_TAINTED_WOOD = ITEMS.register("stripped_tainted_wood", () -> new BlockItem((Block)WitherStormModBlocks.STRIPPED_TAINTED_WOOD.get(), new Item.Properties()));
/* 199 */   public static final RegistryObject<Item> TAINTED_LEAVES = ITEMS.register("tainted_leaves", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_LEAVES.get(), new Item.Properties()));
/* 200 */   public static final RegistryObject<Item> TAINTED_DOOR = ITEMS.register("tainted_door", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_DOOR.get(), new Item.Properties()));
/* 201 */   public static final RegistryObject<Item> TAINTED_TRAPDOOR = ITEMS.register("tainted_trapdoor", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get(), new Item.Properties()));
/* 202 */   public static final RegistryObject<Item> TAINTED_BUTTON = ITEMS.register("tainted_button", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_BUTTON.get(), new Item.Properties()));
/* 203 */   public static final RegistryObject<Item> TAINTED_PRESSURE_PLATE = ITEMS.register("tainted_pressure_plate", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get(), new Item.Properties()));
/* 204 */   public static final RegistryObject<Item> TAINTED_STAIRS = ITEMS.register("tainted_stairs", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_STAIRS.get(), new Item.Properties()));
/* 205 */   public static final RegistryObject<Item> TAINTED_SLAB = ITEMS.register("tainted_slab", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_SLAB.get(), new Item.Properties()));
/* 206 */   public static final RegistryObject<Item> TAINTED_FENCE = ITEMS.register("tainted_fence", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_FENCE.get(), new Item.Properties()));
/* 207 */   public static final RegistryObject<Item> TAINTED_FENCE_GATE = ITEMS.register("tainted_fence_gate", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get(), new Item.Properties()));
/* 208 */   public static final RegistryObject<Item> TAINTED_MUSHROOM = ITEMS.register("tainted_mushroom", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get(), new Item.Properties()));
/* 209 */   public static final RegistryObject<Item> TAINTED_TORCH = ITEMS.register("tainted_torch", () -> new StandingAndWallBlockItem((Block)WitherStormModBlocks.TAINTED_TORCH.get(), (Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
/* 210 */   public static final RegistryObject<Item> TAINTED_SIGN = ITEMS.register("tainted_sign", () -> new SignItem(new Item.Properties(), (Block)WitherStormModBlocks.TAINTED_SIGN.get(), (Block)WitherStormModBlocks.TAINTED_WALL_SIGN.get()));
/* 211 */   public static final RegistryObject<Item> TAINTED_PUMPKIN = ITEMS.register("tainted_pumpkin", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get(), new Item.Properties()));
/* 212 */   public static final RegistryObject<Item> TAINTED_CARVED_PUMPKIN = ITEMS.register("tainted_carved_pumpkin", () -> new TaintedCarvedPumpkinItem((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get(), new Item.Properties()));
/* 213 */   public static final RegistryObject<Item> TAINTED_JACK_O_LANTERN = ITEMS.register("tainted_jack_o_lantern", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get(), new Item.Properties()));
/*     */ 
/*     */   
/* 216 */   public static final RegistryObject<Item> TAINTED_DUST_BLOCK = ITEMS.register("tainted_dust_block", () -> new BlockItem((Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get(), new Item.Properties()));
/*     */ 
/*     */   
/*     */   public static void registerBrewingRecipes() {
/* 220 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42589_, Potions.f_43584_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.WITHER.get()));
/* 221 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42589_, Potions.f_43585_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.LONG_WITHER.get()));
/* 222 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42589_, Potions.f_43586_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.STRONG_WITHER.get()));
/*     */     
/* 224 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42736_, Potions.f_43584_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.WITHER.get()));
/* 225 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42736_, Potions.f_43585_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.LONG_WITHER.get()));
/* 226 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42736_, Potions.f_43586_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.STRONG_WITHER.get()));
/*     */     
/* 228 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42739_, Potions.f_43584_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.WITHER.get()));
/* 229 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42739_, Potions.f_43585_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.LONG_WITHER.get()));
/* 230 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42739_, Potions.f_43586_, (Item)WITHERED_SPIDER_EYE.get(), (Potion)WitherStormModPotions.STRONG_WITHER.get()));
/*     */     
/* 232 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42589_, (Potion)WitherStormModPotions.WITHER.get(), Items.f_42451_, (Potion)WitherStormModPotions.LONG_WITHER.get()));
/* 233 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42589_, (Potion)WitherStormModPotions.WITHER.get(), Items.f_42525_, (Potion)WitherStormModPotions.STRONG_WITHER.get()));
/*     */     
/* 235 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42736_, (Potion)WitherStormModPotions.WITHER.get(), Items.f_42451_, (Potion)WitherStormModPotions.LONG_WITHER.get()));
/* 236 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42736_, (Potion)WitherStormModPotions.WITHER.get(), Items.f_42525_, (Potion)WitherStormModPotions.STRONG_WITHER.get()));
/*     */     
/* 238 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42739_, (Potion)WitherStormModPotions.WITHER.get(), Items.f_42451_, (Potion)WitherStormModPotions.LONG_WITHER.get()));
/* 239 */     BrewingRecipeRegistry.addRecipe((IBrewingRecipe)createBrewingRecipe(Items.f_42739_, (Potion)WitherStormModPotions.WITHER.get(), Items.f_42525_, (Potion)WitherStormModPotions.STRONG_WITHER.get()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static BrewingRecipe createBrewingRecipe(Item potionType, Potion potion, Item ingredient, Potion output) {
/* 244 */     return new BrewingRecipe(Ingredient.m_43927_(new ItemStack[] { PotionUtils.m_43549_(new ItemStack((ItemLike)potionType), potion) }), Ingredient.m_43929_(new ItemLike[] { (ItemLike)ingredient }, ), PotionUtils.m_43549_(new ItemStack((ItemLike)potionType), output));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModItems.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */