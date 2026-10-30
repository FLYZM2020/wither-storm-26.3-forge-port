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
/* 36 */     super(FeatureFlags.f_244280_.m_247355_());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_246942_() {
/* 42 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42402_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))));
/* 43 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42454_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(1.0F, 3.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))));
/* 44 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(1.0F, 3.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))));
/* 45 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42403_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)TagEntry.m_205095_(ItemTags.f_13159_)).m_79080_(LootItemEntityPropertyCondition.m_81864_(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.m_36633_().m_204077_(EntityTypeTags.f_13120_)))));
/* 46 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42454_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(1.0F, 3.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))));
/* 47 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42714_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F)))).m_79080_(LootItemKilledByPlayerCondition.m_81901_())));
/* 48 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42412_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_BONE.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))));
/* 49 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42401_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(-1.0F, 1.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F)))).m_79080_(LootItemKilledByPlayerCondition.m_81901_())));
/* 50 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42416_)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42619_)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42620_)).m_79080_(LootItemKilledByPlayerCondition.m_81901_()).m_79080_(LootItemRandomChanceWithLootingCondition.m_81963_(0.025F, 0.01F))));
/* 51 */     m_245309_((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get()))));
/* 52 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)WitherStormModItems.WITHERED_FLESH.get()).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_(0.0F, 1.0F))))).m_79161_(LootPool.m_79043_().m_79080_(LootItemKilledByPlayerCondition.m_81901_()).m_79080_(LootItemRandomChanceWithLootingCondition.m_81963_(0.025F, 0.01F)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42416_)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42619_)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42620_).m_79078_((LootItemFunction.Builder)SmeltItemFunction.m_81271_().m_79080_(LootItemEntityPropertyCondition.m_81867_(LootContext.EntityTarget.THIS, EntityPredicate.Builder.m_36633_().m_36642_(EntityFlagsPredicate.Builder.m_33713_().m_33714_(Boolean.valueOf(true)).m_33716_()).m_36662_()))))));
/* 53 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_41951_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(0.0F, 2.0F))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42416_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(3.0F, 5.0F))))));
/* 54 */     m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42452_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_(3.0F, 5.0F))))));
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