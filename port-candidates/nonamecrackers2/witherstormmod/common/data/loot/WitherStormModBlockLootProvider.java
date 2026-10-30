/*     */ package nonamecrackers2.witherstormmod.common.data.loot;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.advancements.critereon.EnchantmentPredicate;
/*     */ import net.minecraft.advancements.critereon.ItemPredicate;
/*     */ import net.minecraft.advancements.critereon.MinMaxBounds;
/*     */ import net.minecraft.data.loot.BlockLootSubProvider;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.enchantment.Enchantments;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.storage.loot.LootPool;
/*     */ import net.minecraft.world.level.storage.loot.LootTable;
/*     */ import net.minecraft.world.level.storage.loot.entries.LootItem;
/*     */ import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
/*     */ import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
/*     */ import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
/*     */ import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
/*     */ import net.minecraft.world.level.storage.loot.predicates.MatchTool;
/*     */ import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
/*     */ import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
/*     */ import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ 
/*     */ public class WitherStormModBlockLootProvider extends BlockLootSubProvider {
/*  28 */   private static final Set<Item> EXPLOSION_RESISTANT = Set.of(WitherStormModItems.FORMIDIBOMB.get(), WitherStormModItems.INFECTED_FLESH_BLOCK.get(), WitherStormModItems.SUPER_BEACON.get(), WitherStormModItems.SUPER_SUPPORT_BEACON.get(), WitherStormModItems.SUPER_TNT.get());
/*     */ 
/*     */   
/*     */   public WitherStormModBlockLootProvider() {
/*  32 */     super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generate() {
/*  38 */     dropSelf((Block)WitherStormModBlocks.FIREWORK_BUNDLE.get());
/*  39 */     dropSelf((Block)WitherStormModBlocks.FORMIDIBOMB.get());
/*  40 */     dropSelf((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get());
/*  41 */     dropPottedContents((Block)WitherStormModBlocks.POTTED_TAINTED_MUSHROOM.get());
/*  42 */     dropSelf((Block)WitherStormModBlocks.SUPER_BEACON.get());
/*  43 */     dropSelf((Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get());
/*  44 */     dropSelf((Block)WitherStormModBlocks.SUPER_TNT.get());
/*  45 */     add((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get(), this::createTaintedSkullRemainsTable);
/*  46 */     dropSelf((Block)WitherStormModBlocks.TAINTED_BUTTON.get());
/*  47 */     dropSelf((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
/*  48 */     add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get(), x$0 -> rec$.createSlabItemTable(x$0));
/*  49 */     dropSelf((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get());
/*  50 */     dropSelf((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get());
/*  51 */     dropSelf((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
/*  52 */     add((Block)WitherStormModBlocks.TAINTED_DOOR.get(), x$0 -> rec$.createDoorTable(x$0));
/*  53 */     dropSelf((Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get());
/*  54 */     dropSelf((Block)WitherStormModBlocks.TAINTED_DUST.get());
/*  55 */     dropSelf((Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get());
/*  56 */     dropSelf((Block)WitherStormModBlocks.TAINTED_FENCE.get());
/*  57 */     add((Block)WitherStormModBlocks.TAINTED_FLESH_VEINS.get(), b -> createMultifaceBlockDrops(b, MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))));
/*     */ 
/*     */     
/*  60 */     dropSelf((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get());
/*  61 */     dropWhenSilkTouch((Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get());
/*  62 */     dropWhenSilkTouch((Block)WitherStormModBlocks.TAINTED_GLASS.get());
/*  63 */     dropSelf((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get());
/*     */     
/*  65 */     add((Block)WitherStormModBlocks.TAINTED_LEAVES.get(), x$0 -> rec$.createMangroveLeavesDrops(x$0));
/*  66 */     dropSelf((Block)WitherStormModBlocks.TAINTED_LOG.get());
/*  67 */     dropSelf((Block)WitherStormModBlocks.STRIPPED_TAINTED_LOG.get());
/*  68 */     dropSelf((Block)WitherStormModBlocks.TAINTED_TORCH.get());
/*  69 */     add((Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get(), createSingleItemTable((ItemLike)WitherStormModBlocks.TAINTED_TORCH.get()));
/*  70 */     dropSelf((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
/*  71 */     dropSelf((Block)WitherStormModBlocks.TAINTED_PLANKS.get());
/*  72 */     dropSelf((Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get());
/*  73 */     dropSelf((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get());
/*  74 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SAND.get());
/*  75 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
/*  76 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
/*  77 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
/*  78 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
/*  79 */     dropSelf((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
/*  80 */     dropSelf((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
/*  81 */     dropSelf((Block)WitherStormModBlocks.TAINTED_CHISELED_SANDSTONE.get());
/*  82 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
/*  83 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
/*  84 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
/*  85 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.get());
/*  86 */     dropSelf((Block)WitherStormModBlocks.TAINTED_DIRT.get());
/*  87 */     add((Block)WitherStormModBlocks.TAINTED_SKELETON_WALL.get(), this::createTaintedSkullRemainsTable);
/*  88 */     add((Block)WitherStormModBlocks.TAINTED_SKULL_CEILING.get(), this::createTaintedSkullRemainsTable);
/*  89 */     add((Block)WitherStormModBlocks.TAINTED_SLAB.get(), x$0 -> rec$.createSlabItemTable(x$0));
/*  90 */     dropSelf((Block)WitherStormModBlocks.TAINTED_STAIRS.get());
/*  91 */     add((Block)WitherStormModBlocks.TAINTED_STONE.get(), b -> createSingleItemTableWithSilkTouch(b, (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get()));
/*     */ 
/*     */     
/*  94 */     dropSelf((Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get());
/*  95 */     dropSelf((Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get());
/*  96 */     add((Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get(), x$0 -> rec$.createSlabItemTable(x$0));
/*  97 */     dropSelf((Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get());
/*  98 */     dropSelf((Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get());
/*  99 */     dropSelf((Block)WitherStormModBlocks.TAINTED_WOOD.get());
/* 100 */     dropSelf((Block)WitherStormModBlocks.STRIPPED_TAINTED_WOOD.get());
/* 101 */     add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get(), this::createTaintedZombieRemainsTable);
/* 102 */     add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get(), this::createTaintedZombieRemainsTable);
/* 103 */     add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_WALL.get(), this::createTaintedZombieRemainsTable);
/* 104 */     dropSelf((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get());
/* 105 */     dropSelf((Block)WitherStormModBlocks.TAINTED_SIGN.get());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterable<Block> getKnownBlocks() {
/* 111 */     List<Block> list = Lists.newArrayList();
/* 112 */     list.add((Block)WitherStormModBlocks.FIREWORK_BUNDLE.get());
/* 113 */     list.add((Block)WitherStormModBlocks.FORMIDIBOMB.get());
/* 114 */     list.add((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get());
/* 115 */     list.add((Block)WitherStormModBlocks.POTTED_TAINTED_MUSHROOM.get());
/* 116 */     list.add((Block)WitherStormModBlocks.SUPER_BEACON.get());
/* 117 */     list.add((Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get());
/* 118 */     list.add((Block)WitherStormModBlocks.SUPER_TNT.get());
/* 119 */     list.add((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get());
/* 120 */     list.add((Block)WitherStormModBlocks.TAINTED_BUTTON.get());
/* 121 */     list.add((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
/* 122 */     list.add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get());
/* 123 */     list.add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get());
/* 124 */     list.add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get());
/* 125 */     list.add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
/* 126 */     list.add((Block)WitherStormModBlocks.TAINTED_DOOR.get());
/* 127 */     list.add((Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get());
/* 128 */     list.add((Block)WitherStormModBlocks.TAINTED_DUST.get());
/* 129 */     list.add((Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get());
/* 130 */     list.add((Block)WitherStormModBlocks.TAINTED_FENCE.get());
/* 131 */     list.add((Block)WitherStormModBlocks.TAINTED_FLESH_VEINS.get());
/* 132 */     list.add((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get());
/* 133 */     list.add((Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get());
/* 134 */     list.add((Block)WitherStormModBlocks.TAINTED_GLASS.get());
/* 135 */     list.add((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get());
/* 136 */     list.add((Block)WitherStormModBlocks.TAINTED_LEAVES.get());
/* 137 */     list.add((Block)WitherStormModBlocks.TAINTED_LOG.get());
/* 138 */     list.add((Block)WitherStormModBlocks.STRIPPED_TAINTED_LOG.get());
/* 139 */     list.add((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
/* 140 */     list.add((Block)WitherStormModBlocks.TAINTED_TORCH.get());
/* 141 */     list.add((Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get());
/* 142 */     list.add((Block)WitherStormModBlocks.TAINTED_PLANKS.get());
/* 143 */     list.add((Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get());
/* 144 */     list.add((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get());
/* 145 */     list.add((Block)WitherStormModBlocks.TAINTED_SAND.get());
/* 146 */     list.add((Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
/* 147 */     list.add((Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
/* 148 */     list.add((Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
/* 149 */     list.add((Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
/* 150 */     list.add((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
/* 151 */     list.add((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
/* 152 */     list.add((Block)WitherStormModBlocks.TAINTED_CHISELED_SANDSTONE.get());
/* 153 */     list.add((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
/* 154 */     list.add((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
/* 155 */     list.add((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
/* 156 */     list.add((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.get());
/* 157 */     list.add((Block)WitherStormModBlocks.TAINTED_DIRT.get());
/* 158 */     list.add((Block)WitherStormModBlocks.TAINTED_SKELETON_WALL.get());
/* 159 */     list.add((Block)WitherStormModBlocks.TAINTED_SKULL_CEILING.get());
/* 160 */     list.add((Block)WitherStormModBlocks.TAINTED_SLAB.get());
/* 161 */     list.add((Block)WitherStormModBlocks.TAINTED_STAIRS.get());
/* 162 */     list.add((Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get());
/* 163 */     list.add((Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get());
/* 164 */     list.add((Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get());
/* 165 */     list.add((Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get());
/* 166 */     list.add((Block)WitherStormModBlocks.TAINTED_STONE.get());
/* 167 */     list.add((Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get());
/* 168 */     list.add((Block)WitherStormModBlocks.TAINTED_WOOD.get());
/* 169 */     list.add((Block)WitherStormModBlocks.STRIPPED_TAINTED_WOOD.get());
/* 170 */     list.add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get());
/* 171 */     list.add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get());
/* 172 */     list.add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_WALL.get());
/* 173 */     list.add((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get());
/* 174 */     list.add((Block)WitherStormModBlocks.TAINTED_SIGN.get());
/* 175 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   protected LootTable.Builder createTaintedSkullRemainsTable(Block block) {
/* 180 */     return LootTable.lootTable().withPool((LootPool.Builder)applyExplosionCondition((ItemLike)block, (ConditionUserBuilder)LootPool.lootPool().setRolls((NumberProvider)ConstantValue.exactly(2.0F))
/* 181 */           .add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.TAINTED_DUST.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)ConstantValue.exactly(1.0F))))
/* 182 */           .add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_BONE.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(1.0F, 2.0F))))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected LootTable.Builder createTaintedZombieRemainsTable(Block block) {
/* 187 */     return LootTable.lootTable().withPool((LootPool.Builder)applyExplosionCondition((ItemLike)block, (ConditionUserBuilder)LootPool.lootPool().setRolls((NumberProvider)ConstantValue.exactly(2.0F))
/* 188 */           .add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(1.0F, 2.0F))))
/* 189 */           .add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_BONE.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)ConstantValue.exactly(1.0F))))));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\loot\WitherStormModBlockLootProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */