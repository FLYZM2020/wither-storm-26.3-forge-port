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
/*  32 */     super(EXPLOSION_RESISTANT, FeatureFlags.f_244280_.m_247355_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_245660_() {
/*  38 */     m_245724_((Block)WitherStormModBlocks.FIREWORK_BUNDLE.get());
/*  39 */     m_245724_((Block)WitherStormModBlocks.FORMIDIBOMB.get());
/*  40 */     m_245724_((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get());
/*  41 */     m_246535_((Block)WitherStormModBlocks.POTTED_TAINTED_MUSHROOM.get());
/*  42 */     m_245724_((Block)WitherStormModBlocks.SUPER_BEACON.get());
/*  43 */     m_245724_((Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get());
/*  44 */     m_245724_((Block)WitherStormModBlocks.SUPER_TNT.get());
/*  45 */     m_246481_((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get(), this::createTaintedSkullRemainsTable);
/*  46 */     m_245724_((Block)WitherStormModBlocks.TAINTED_BUTTON.get());
/*  47 */     m_245724_((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
/*  48 */     m_246481_((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get(), x$0 -> rec$.m_247233_(x$0));
/*  49 */     m_245724_((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get());
/*  50 */     m_245724_((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get());
/*  51 */     m_245724_((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
/*  52 */     m_246481_((Block)WitherStormModBlocks.TAINTED_DOOR.get(), x$0 -> rec$.m_247398_(x$0));
/*  53 */     m_245724_((Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get());
/*  54 */     m_245724_((Block)WitherStormModBlocks.TAINTED_DUST.get());
/*  55 */     m_245724_((Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get());
/*  56 */     m_245724_((Block)WitherStormModBlocks.TAINTED_FENCE.get());
/*  57 */     m_246481_((Block)WitherStormModBlocks.TAINTED_FLESH_VEINS.get(), b -> m_246235_(b, MatchTool.m_81997_(ItemPredicate.Builder.m_45068_().m_45071_(new EnchantmentPredicate(Enchantments.f_44985_, MinMaxBounds.Ints.m_55386_(1))))));
/*     */ 
/*     */     
/*  60 */     m_245724_((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get());
/*  61 */     m_245644_((Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get());
/*  62 */     m_245644_((Block)WitherStormModBlocks.TAINTED_GLASS.get());
/*  63 */     m_245724_((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get());
/*     */     
/*  65 */     m_246481_((Block)WitherStormModBlocks.TAINTED_LEAVES.get(), x$0 -> rec$.m_245170_(x$0));
/*  66 */     m_245724_((Block)WitherStormModBlocks.TAINTED_LOG.get());
/*  67 */     m_245724_((Block)WitherStormModBlocks.STRIPPED_TAINTED_LOG.get());
/*  68 */     m_245724_((Block)WitherStormModBlocks.TAINTED_TORCH.get());
/*  69 */     m_247577_((Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get(), m_247033_((ItemLike)WitherStormModBlocks.TAINTED_TORCH.get()));
/*  70 */     m_245724_((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
/*  71 */     m_245724_((Block)WitherStormModBlocks.TAINTED_PLANKS.get());
/*  72 */     m_245724_((Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get());
/*  73 */     m_245724_((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get());
/*  74 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SAND.get());
/*  75 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
/*  76 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
/*  77 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
/*  78 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
/*  79 */     m_245724_((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
/*  80 */     m_245724_((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
/*  81 */     m_245724_((Block)WitherStormModBlocks.TAINTED_CHISELED_SANDSTONE.get());
/*  82 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
/*  83 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
/*  84 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
/*  85 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.get());
/*  86 */     m_245724_((Block)WitherStormModBlocks.TAINTED_DIRT.get());
/*  87 */     m_246481_((Block)WitherStormModBlocks.TAINTED_SKELETON_WALL.get(), this::createTaintedSkullRemainsTable);
/*  88 */     m_246481_((Block)WitherStormModBlocks.TAINTED_SKULL_CEILING.get(), this::createTaintedSkullRemainsTable);
/*  89 */     m_246481_((Block)WitherStormModBlocks.TAINTED_SLAB.get(), x$0 -> rec$.m_247233_(x$0));
/*  90 */     m_245724_((Block)WitherStormModBlocks.TAINTED_STAIRS.get());
/*  91 */     m_246481_((Block)WitherStormModBlocks.TAINTED_STONE.get(), b -> m_245514_(b, (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get()));
/*     */ 
/*     */     
/*  94 */     m_245724_((Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get());
/*  95 */     m_245724_((Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get());
/*  96 */     m_246481_((Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get(), x$0 -> rec$.m_247233_(x$0));
/*  97 */     m_245724_((Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get());
/*  98 */     m_245724_((Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get());
/*  99 */     m_245724_((Block)WitherStormModBlocks.TAINTED_WOOD.get());
/* 100 */     m_245724_((Block)WitherStormModBlocks.STRIPPED_TAINTED_WOOD.get());
/* 101 */     m_246481_((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get(), this::createTaintedZombieRemainsTable);
/* 102 */     m_246481_((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get(), this::createTaintedZombieRemainsTable);
/* 103 */     m_246481_((Block)WitherStormModBlocks.TAINTED_ZOMBIE_WALL.get(), this::createTaintedZombieRemainsTable);
/* 104 */     m_245724_((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get());
/* 105 */     m_245724_((Block)WitherStormModBlocks.TAINTED_SIGN.get());
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
/* 180 */     return LootTable.m_79147_().m_79161_((LootPool.Builder)m_247733_((ItemLike)block, (ConditionUserBuilder)LootPool.m_79043_().m_165133_((NumberProvider)ConstantValue.m_165692_(2.0F))
/* 181 */           .m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.TAINTED_DUST.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)ConstantValue.m_165692_(1.0F))))
/* 182 */           .m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_BONE.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(1.0F, 2.0F))))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected LootTable.Builder createTaintedZombieRemainsTable(Block block) {
/* 187 */     return LootTable.m_79147_().m_79161_((LootPool.Builder)m_247733_((ItemLike)block, (ConditionUserBuilder)LootPool.m_79043_().m_165133_((NumberProvider)ConstantValue.m_165692_(2.0F))
/* 188 */           .m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(1.0F, 2.0F))))
/* 189 */           .m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_BONE.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)ConstantValue.m_165692_(1.0F))))));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\loot\WitherStormModBlockLootProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */