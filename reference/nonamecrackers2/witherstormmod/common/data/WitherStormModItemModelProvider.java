/*     */ package nonamecrackers2.witherstormmod.common.data;
/*     */ 
/*     */ import net.minecraft.data.PackOutput;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraftforge.client.model.generators.ItemModelBuilder;
/*     */ import net.minecraftforge.client.model.generators.ItemModelProvider;
/*     */ import net.minecraftforge.client.model.generators.ModelBuilder;
/*     */ import net.minecraftforge.client.model.generators.ModelFile;
/*     */ import net.minecraftforge.common.data.ExistingFileHelper;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ 
/*     */ public class WitherStormModItemModelProvider
/*     */   extends ItemModelProvider {
/*     */   public WitherStormModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
/*  17 */     super(output, "witherstormmod", existingFileHelper);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerModels() {
/*  23 */     handheld((Item)WitherStormModItems.WITHERED_BONE.get());
/*  24 */     basicItem((Item)WitherStormModItems.WITHERED_FLESH.get());
/*  25 */     basicItem((Item)WitherStormModItems.TAINTED_DUST.get());
/*  26 */     basicItem((Item)WitherStormModItems.WITHERED_SPIDER_EYE.get());
/*  27 */     basicItem((Item)WitherStormModItems.GOLDEN_APPLE_STEW.get());
/*  28 */     basicItem((Item)WitherStormModItems.AMULET.get());
/*  29 */     basicItem((Item)WitherStormModItems.COMMAND_BLOCK_BOOK.get());
/*  30 */     basicItem((Item)WitherStormModItems.WITHERED_NETHER_STAR.get());
/*  31 */     spawnEgg((Item)WitherStormModItems.SICKENED_CREEPER_SPAWN_EGG.get());
/*  32 */     spawnEgg((Item)WitherStormModItems.SICKENED_SKELETON_SPAWN_EGG.get());
/*  33 */     spawnEgg((Item)WitherStormModItems.SICKENED_SPIDER_SPAWN_EGG.get());
/*  34 */     spawnEgg((Item)WitherStormModItems.SICKENED_VILLAGER_SPAWN_EGG.get());
/*  35 */     spawnEgg((Item)WitherStormModItems.SICKENED_ZOMBIE_SPAWN_EGG.get());
/*  36 */     spawnEgg((Item)WitherStormModItems.SICKENED_PHANTOM_SPAWN_EGG.get());
/*  37 */     spawnEgg((Item)WitherStormModItems.SICKENED_CHICKEN_SPAWN_EGG.get());
/*  38 */     spawnEgg((Item)WitherStormModItems.SICKENED_PARROT_SPAWN_EGG.get());
/*  39 */     spawnEgg((Item)WitherStormModItems.SICKENED_WOLF_SPAWN_EGG.get());
/*  40 */     spawnEgg((Item)WitherStormModItems.SICKENED_CAT_SPAWN_EGG.get());
/*  41 */     spawnEgg((Item)WitherStormModItems.SICKENED_COW_SPAWN_EGG.get());
/*  42 */     spawnEgg((Item)WitherStormModItems.SICKENED_PIG_SPAWN_EGG.get());
/*  43 */     spawnEgg((Item)WitherStormModItems.SICKENED_MUSHROOM_COW_SPAWN_EGG.get());
/*  44 */     spawnEgg((Item)WitherStormModItems.SICKENED_BEE_SPAWN_EGG.get());
/*  45 */     spawnEgg((Item)WitherStormModItems.SICKENED_PILLAGER_SPAWN_EGG.get());
/*  46 */     spawnEgg((Item)WitherStormModItems.SICKENED_VINDICATOR_SPAWN_EGG.get());
/*  47 */     spawnEgg((Item)WitherStormModItems.SICKENED_IRON_GOLEM_SPAWN_EGG.get());
/*  48 */     spawnEgg((Item)WitherStormModItems.SICKENED_SNOW_GOLEM_SPAWN_EGG.get());
/*  49 */     spawnEgg((Item)WitherStormModItems.TENTACLE_SPAWN_EGG.get());
/*  50 */     spawnEgg((Item)WitherStormModItems.WITHERED_SYMBIONT_SPAWN_EGG.get());
/*     */     
/*  52 */     handheld((Item)WitherStormModItems.COMMAND_BLOCK_SWORD.get());
/*  53 */     handheld((Item)WitherStormModItems.COMMAND_BLOCK_PICKAXE.get());
/*  54 */     handheld((Item)WitherStormModItems.COMMAND_BLOCK_AXE.get());
/*  55 */     handheld((Item)WitherStormModItems.COMMAND_BLOCK_SHOVEL.get());
/*  56 */     handheld((Item)WitherStormModItems.COMMAND_BLOCK_HOE.get());
/*     */     
/*  58 */     handheld((Item)WitherStormModItems.WOOD_COMMAND_BLOCK_SWORD.get());
/*  59 */     handheld((Item)WitherStormModItems.WOOD_COMMAND_BLOCK_PICKAXE.get());
/*  60 */     handheld((Item)WitherStormModItems.WOOD_COMMAND_BLOCK_AXE.get());
/*  61 */     handheld((Item)WitherStormModItems.WOOD_COMMAND_BLOCK_SHOVEL.get());
/*  62 */     handheld((Item)WitherStormModItems.WOOD_COMMAND_BLOCK_HOE.get());
/*     */     
/*  64 */     handheld((Item)WitherStormModItems.STONE_COMMAND_BLOCK_SWORD.get());
/*  65 */     handheld((Item)WitherStormModItems.STONE_COMMAND_BLOCK_PICKAXE.get());
/*  66 */     handheld((Item)WitherStormModItems.STONE_COMMAND_BLOCK_AXE.get());
/*  67 */     handheld((Item)WitherStormModItems.STONE_COMMAND_BLOCK_SHOVEL.get());
/*  68 */     handheld((Item)WitherStormModItems.STONE_COMMAND_BLOCK_HOE.get());
/*     */     
/*  70 */     handheld((Item)WitherStormModItems.IRON_COMMAND_BLOCK_SWORD.get());
/*  71 */     handheld((Item)WitherStormModItems.IRON_COMMAND_BLOCK_PICKAXE.get());
/*  72 */     handheld((Item)WitherStormModItems.IRON_COMMAND_BLOCK_AXE.get());
/*  73 */     handheld((Item)WitherStormModItems.IRON_COMMAND_BLOCK_SHOVEL.get());
/*  74 */     handheld((Item)WitherStormModItems.IRON_COMMAND_BLOCK_HOE.get());
/*     */     
/*  76 */     handheld((Item)WitherStormModItems.GOLD_COMMAND_BLOCK_SWORD.get());
/*  77 */     handheld((Item)WitherStormModItems.GOLD_COMMAND_BLOCK_PICKAXE.get());
/*  78 */     handheld((Item)WitherStormModItems.GOLD_COMMAND_BLOCK_AXE.get());
/*  79 */     handheld((Item)WitherStormModItems.GOLD_COMMAND_BLOCK_SHOVEL.get());
/*  80 */     handheld((Item)WitherStormModItems.GOLD_COMMAND_BLOCK_HOE.get());
/*     */     
/*  82 */     basicItem((Item)WitherStormModItems.TAINTED_DOOR.get());
/*  83 */     ((ItemModelBuilder)((ItemModelBuilder)((ItemModelBuilder)getBuilder(WitherStormModItems.TAINTED_FLESH_VEINS.getId().toString()))
/*  84 */       .parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated")))
/*  85 */       .texture("layer0", modLoc("block/tainted_flesh_veins")))
/*  86 */       .renderType("cutout");
/*  87 */     basicItem((Item)WitherStormModItems.TAINTED_SIGN.get());
/*  88 */     ((ItemModelBuilder)((ItemModelBuilder)((ItemModelBuilder)getBuilder(WitherStormModItems.TAINTED_GLASS_PANE.getId().toString()))
/*  89 */       .parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated")))
/*  90 */       .texture("layer0", modLoc("block/tainted_glass")))
/*  91 */       .renderType("translucent");
/*  92 */     buttonInventory(WitherStormModItems.TAINTED_STONE_BUTTON.getId().m_135815_(), modLoc("block/tainted_stone"));
/*  93 */     buttonInventory(WitherStormModItems.TAINTED_BUTTON.getId().m_135815_(), modLoc("block/tainted_planks"));
/*  94 */     wallInventory(WitherStormModItems.TAINTED_COBBLESTONE_WALL.getId().m_135815_(), modLoc("block/tainted_cobblestone"));
/*  95 */     wallInventory(WitherStormModItems.TAINTED_SANDSTONE_WALL.getId().m_135815_(), modLoc("block/tainted_sandstone"));
/*  96 */     wallInventory(WitherStormModItems.TAINTED_SMOOTH_SANDSTONE_WALL.getId().m_135815_(), modLoc("block/tainted_sandstone_top"));
/*  97 */     fenceInventory(WitherStormModItems.TAINTED_FENCE.getId().m_135815_(), modLoc("block/tainted_planks"));
/*  98 */     ((ItemModelBuilder)((ItemModelBuilder)getBuilder(WitherStormModItems.TAINTED_MUSHROOM.getId().toString()))
/*  99 */       .parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated")))
/* 100 */       .texture("layer0", modLoc("block/tainted_mushroom"));
/*     */     
/* 102 */     ((ItemModelBuilder)((ItemModelBuilder)getBuilder(WitherStormModItems.TAINTED_TORCH.getId().toString()))
/* 103 */       .parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated")))
/* 104 */       .texture("layer0", modLoc("block/tainted_torch"));
/*     */     
/* 106 */     handheld((Item)WitherStormModItems.EYE_OF_THE_STORM.get());
/* 107 */     handheld((Item)WitherStormModItems.FORMIDI_BLADE.get());
/*     */     
/* 109 */     ModelBuilder modelBuilder1 = ((ItemModelBuilder)withExistingParent(WitherStormModItems.FORMIDI_BLADE.getId().m_135815_() + "_powering_up", "item/handheld")).texture("layer0", modLoc("item/formidi_blade_powering_up"));
/* 110 */     ModelBuilder modelBuilder2 = ((ItemModelBuilder)withExistingParent(WitherStormModItems.FORMIDI_BLADE.getId().m_135815_() + "_powered_up", "item/handheld")).texture("layer0", modLoc("item/formidi_blade_powered_up"));
/* 111 */     ((ItemModelBuilder)((ItemModelBuilder)withExistingParent(WitherStormModItems.FORMIDI_BLADE.getId().m_135815_(), "item/handheld")).texture("layer0", modLoc("item/formidi_blade")))
/* 112 */       .override().model((ModelFile)modelBuilder1).predicate(modLoc("anim"), 0.5F).end()
/* 113 */       .override().model((ModelFile)modelBuilder2).predicate(modLoc("anim"), 1.0F).end()
/* 114 */       .override().model((ModelFile)modelBuilder1).predicate(modLoc("anim"), 1.5F).end();
/*     */     
/* 116 */     ((ItemModelBuilder)((ItemModelBuilder)getBuilder(WitherStormModItems.TAINTED_TORCH.getId().toString()))
/* 117 */       .parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated")))
/* 118 */       .texture("layer0", modLoc("block/tainted_torch"));
/*     */   }
/*     */ 
/*     */   
/*     */   private void handheld(Item item) {
/* 123 */     ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
/* 124 */     ((ItemModelBuilder)withExistingParent(id.m_135815_(), "item/handheld")).texture("layer0", modLoc("item/" + id.m_135815_()));
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawnEgg(Item item) {
/* 129 */     ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
/* 130 */     withExistingParent(id.m_135815_(), "item/template_spawn_egg");
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\WitherStormModItemModelProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */