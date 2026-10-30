/*    */ package nonamecrackers2.witherstormmod.common.data.loot;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.stream.Stream;
/*    */ import net.minecraft.advancements.critereon.EntityFlagsPredicate;
/*    */ import net.minecraft.advancements.critereon.EntityPredicate;
/*    */ import net.minecraft.data.loot.EntityLootSubProvider;
/*    */ import net.minecraft.tags.EntityTypeTags;
/*    */ import net.minecraft.tags.ItemTags;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.flag.FeatureFlags;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraft.world.level.storage.loot.LootContext;
/*    */ import net.minecraft.world.level.storage.loot.LootPool;
/*    */ import net.minecraft.world.level.storage.loot.LootTable;
/*    */ import net.minecraft.world.level.storage.loot.entries.LootItem;
/*    */ import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
/*    */ import net.minecraft.world.level.storage.loot.entries.TagEntry;
/*    */ import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
/*    */ import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
/*    */ import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
/*    */ import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
/*    */ import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
/*    */ import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
/*    */ import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
/*    */ import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
/*    */ import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*    */ 
/*    */ public class WitherStormModEntityLootProvider
/*    */   extends EntityLootSubProvider {
/*    */   public WitherStormModEntityLootProvider() {
/* 36 */     super(FeatureFlags.REGISTRY.allFlags());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate() {
/* 42 */     add((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.FEATHER).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))));
/* 43 */     add((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.LEATHER).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(1.0F, 3.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))));
/* 44 */     add((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(1.0F, 3.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))));
/* 45 */     add((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 1.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.GUNPOWDER).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));
/* 46 */     add((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.LEATHER).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(1.0F, 3.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))));
/* 47 */     add((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.PHANTOM_MEMBRANE).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 1.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F)))).when(LootItemKilledByPlayerCondition.killedByPlayer())));
/* 48 */     add((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.ARROW).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_BONE.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))));
/* 49 */     add((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.STRING).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(-1.0F, 1.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F)))).when(LootItemKilledByPlayerCondition.killedByPlayer())));
/* 50 */     add((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.IRON_INGOT)).add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.CARROT)).add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.POTATO)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F))));
/* 51 */     add((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get()))));
/* 52 */     add((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))).apply((LootItemFunction.Builder)LootingEnchantFunction.lootingMultiplier((NumberProvider)UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F)).add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.IRON_INGOT)).add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.CARROT)).add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.POTATO).apply((LootItemFunction.Builder)SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(Boolean.valueOf(true)).build()).build()))))));
/* 53 */     add((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.WITHER_ROSE).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(0.0F, 2.0F))))).withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.IRON_INGOT).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(3.0F, 5.0F))))));
/* 54 */     add((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)LootItem.lootTableItem((ItemLike)Items.SNOWBALL).apply((LootItemFunction.Builder)SetItemCountFunction.setCount((NumberProvider)UniformGenerator.between(3.0F, 5.0F))))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Stream<EntityType<?>> getKnownEntityTypes() {
/* 60 */     List<EntityType<?>> list = Lists.newArrayList();
/* 61 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get());
/* 62 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_COW.get());
/* 63 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get());
/* 64 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get());
/* 65 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get());
/* 66 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get());
/* 67 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get());
/* 68 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get());
/* 69 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get());
/* 70 */     list.add((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get());
/* 71 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get());
/* 72 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get());
/* 73 */     list.add((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get());
/* 74 */     return list.stream();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\loot\WitherStormModEntityLootProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */