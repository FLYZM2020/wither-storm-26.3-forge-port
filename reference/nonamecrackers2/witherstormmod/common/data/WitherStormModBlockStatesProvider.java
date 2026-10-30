/*     */ package nonamecrackers2.witherstormmod.common.data;
/*     */ 
/*     */ import net.minecraft.data.PackOutput;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.ButtonBlock;
/*     */ import net.minecraft.world.level.block.DoorBlock;
/*     */ import net.minecraft.world.level.block.FenceBlock;
/*     */ import net.minecraft.world.level.block.FenceGateBlock;
/*     */ import net.minecraft.world.level.block.IronBarsBlock;
/*     */ import net.minecraft.world.level.block.PressurePlateBlock;
/*     */ import net.minecraft.world.level.block.RotatedPillarBlock;
/*     */ import net.minecraft.world.level.block.SlabBlock;
/*     */ import net.minecraft.world.level.block.StairBlock;
/*     */ import net.minecraft.world.level.block.StandingSignBlock;
/*     */ import net.minecraft.world.level.block.TrapDoorBlock;
/*     */ import net.minecraft.world.level.block.WallBlock;
/*     */ import net.minecraft.world.level.block.WallSignBlock;
/*     */ import net.minecraftforge.client.model.generators.BlockModelBuilder;
/*     */ import net.minecraftforge.client.model.generators.BlockStateProvider;
/*     */ import net.minecraftforge.client.model.generators.ModelBuilder;
/*     */ import net.minecraftforge.client.model.generators.ModelFile;
/*     */ import net.minecraftforge.common.data.ExistingFileHelper;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ 
/*     */ public class WitherStormModBlockStatesProvider
/*     */   extends BlockStateProvider {
/*     */   public WitherStormModBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
/*  30 */     super(output, "witherstormmod", exFileHelper);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerStatesAndModels() {
/*  36 */     simpleBlockWithItem((Block)WitherStormModBlocks.SUPER_TNT.get(), (ModelFile)models().cubeBottomTop(WitherStormModBlocks.SUPER_TNT.getId().m_135815_(), modLoc("block/super_tnt_side"), modLoc("block/super_tnt_bottom"), modLoc("block/super_tnt_top")));
/*  37 */     ModelBuilder modelBuilder1 = models().orientableWithBottom(WitherStormModBlocks.FORMIDIBOMB.getId().m_135815_(), modLoc("block/formidibomb_side"), modLoc("block/formidibomb_front"), modLoc("block/formidibomb_bottom"), modLoc("block/formidibomb_top"));
/*  38 */     horizontalBlock((Block)WitherStormModBlocks.FORMIDIBOMB.get(), (ModelFile)modelBuilder1, 0);
/*  39 */     simpleBlockItem((Block)WitherStormModBlocks.FORMIDIBOMB.get(), (ModelFile)modelBuilder1);
/*  40 */     ModelFile.ExistingModelFile existingModelFile1 = models().getExistingFile(WitherStormModBlocks.SUPER_BEACON.getId());
/*  41 */     simpleBlockWithItem((Block)WitherStormModBlocks.SUPER_BEACON.get(), (ModelFile)existingModelFile1);
/*  42 */     ModelFile.ExistingModelFile existingModelFile2 = models().getExistingFile(WitherStormModBlocks.SUPER_SUPPORT_BEACON.getId());
/*  43 */     simpleBlockWithItem((Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get(), (ModelFile)existingModelFile2);
/*  44 */     simpleBlockWithItem((Block)WitherStormModBlocks.FIREWORK_BUNDLE.get(), (ModelFile)models().cubeBottomTop(WitherStormModBlocks.FIREWORK_BUNDLE.getId().m_135815_(), modLoc("block/firework_bundle_side"), modLoc("block/firework_bundle_bottom"), modLoc("block/firework_bundle_top")));
/*  45 */     ModelFile.ExistingModelFile existingModelFile3 = models().getExistingFile(WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.getId());
/*  46 */     horizontalBlock((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get(), (ModelFile)existingModelFile3);
/*  47 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get(), (ModelFile)existingModelFile3);
/*  48 */     ModelFile.ExistingModelFile existingModelFile4 = models().getExistingFile(WitherStormModBlocks.TAINTED_ZOMBIE_WALL.getId());
/*  49 */     horizontalBlock((Block)WitherStormModBlocks.TAINTED_ZOMBIE_WALL.get(), (ModelFile)existingModelFile4);
/*  50 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_ZOMBIE_WALL.get(), (ModelFile)existingModelFile4);
/*  51 */     ModelFile.ExistingModelFile existingModelFile5 = models().getExistingFile(WitherStormModBlocks.TAINTED_ZOMBIE_LYING.getId());
/*  52 */     horizontalBlock((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get(), (ModelFile)existingModelFile5);
/*  53 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get(), (ModelFile)existingModelFile5);
/*  54 */     ModelFile.ExistingModelFile existingModelFile6 = models().getExistingFile(WitherStormModBlocks.TAINTED_BONE_PILE.getId());
/*  55 */     horizontalBlock((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get(), (ModelFile)existingModelFile6);
/*  56 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get(), (ModelFile)existingModelFile6);
/*  57 */     ModelFile.ExistingModelFile existingModelFile7 = models().getExistingFile(WitherStormModBlocks.TAINTED_SKELETON_WALL.getId());
/*  58 */     horizontalBlock((Block)WitherStormModBlocks.TAINTED_SKELETON_WALL.get(), (ModelFile)existingModelFile7);
/*  59 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_SKELETON_WALL.get(), (ModelFile)existingModelFile7);
/*  60 */     ModelFile.ExistingModelFile existingModelFile8 = models().getExistingFile(WitherStormModBlocks.TAINTED_SKULL_CEILING.getId());
/*  61 */     horizontalBlock((Block)WitherStormModBlocks.TAINTED_SKULL_CEILING.get(), (ModelFile)existingModelFile8);
/*  62 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_SKULL_CEILING.get(), (ModelFile)existingModelFile8);
/*  63 */     ModelFile.ExistingModelFile existingModelFile9 = models().getExistingFile(WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.getId());
/*  64 */     simpleBlockItem((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get(), (ModelFile)existingModelFile9);
/*  65 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_DIRT.get(), (ModelFile)models().cubeAll(WitherStormModBlocks.TAINTED_DIRT.getId().m_135815_(), modLoc("block/tainted_dirt")));
/*  66 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_SANDSTONE.get(), (ModelFile)models().cubeBottomTop(WitherStormModBlocks.TAINTED_SANDSTONE.getId().m_135815_(), modLoc("block/tainted_sandstone"), modLoc("block/tainted_sandstone_bottom"), modLoc("block/tainted_sandstone_top")));
/*  67 */     stairsBlock((StairBlock)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get(), modLoc("block/tainted_sandstone"), modLoc("block/tainted_sandstone_bottom"), modLoc("block/tainted_sandstone_top"));
/*  68 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.getId()));
/*  69 */     slabBlock((SlabBlock)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get(), WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.getId(), modLoc("block/tainted_sandstone"), modLoc("block/tainted_sandstone_bottom"), modLoc("block/tainted_sandstone_top"));
/*  70 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.getId()));
/*  71 */     wallBlock((WallBlock)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get(), modLoc("block/tainted_sandstone"));
/*  72 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get(), (ModelFile)models().cubeBottomTop(WitherStormModBlocks.TAINTED_CUT_SANDSTONE.getId().m_135815_(), modLoc("block/tainted_cut_sandstone"), modLoc("block/tainted_sandstone_bottom"), modLoc("block/tainted_sandstone_top")));
/*  73 */     slabBlock((SlabBlock)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get(), WitherStormModBlocks.TAINTED_CUT_SANDSTONE.getId(), modLoc("block/tainted_cut_sandstone"), modLoc("block/tainted_sandstone_bottom"), modLoc("block/tainted_sandstone_top"));
/*  74 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.getId()));
/*  75 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_CHISELED_SANDSTONE.get(), (ModelFile)models().cubeBottomTop(WitherStormModBlocks.TAINTED_CHISELED_SANDSTONE.getId().m_135815_(), modLoc("block/tainted_chiseled_sandstone"), modLoc("block/tainted_sandstone_bottom"), modLoc("block/tainted_sandstone_top")));
/*  76 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get(), (ModelFile)models().cubeAll(WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.getId().m_135815_(), modLoc("block/tainted_sandstone_top")));
/*  77 */     stairsBlock((StairBlock)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get(), modLoc("block/tainted_sandstone_top"), modLoc("block/tainted_sandstone_bottom"), modLoc("block/tainted_sandstone_top"));
/*  78 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.getId()));
/*  79 */     slabBlock((SlabBlock)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get(), WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.getId(), modLoc("block/tainted_sandstone_top"));
/*  80 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.getId()));
/*  81 */     wallBlock((WallBlock)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.get(), modLoc("block/tainted_sandstone_top"));
/*  82 */     torch((Block)WitherStormModBlocks.TAINTED_TORCH.get(), modLoc("block/tainted_torch"));
/*  83 */     wallTorch((Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get(), modLoc("block/tainted_torch"));
/*  84 */     ModelFile.ExistingModelFile existingModelFile10 = models().getExistingFile(WitherStormModBlocks.TAINTED_FLESH_VEINS.getId());
/*  85 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_FLESH_VEINS.get(), (ModelFile)existingModelFile10);
/*     */     
/*  87 */     ModelBuilder modelBuilder2 = models().cubeAll(WitherStormModBlocks.TAINTED_FLESH_BLOCK.getId().m_135815_(), modLoc("block/tainted_flesh_block"));
/*  88 */     getVariantBuilder((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get()).partialState().modelForState()
/*  89 */       .modelFile((ModelFile)modelBuilder2).nextModel()
/*  90 */       .modelFile((ModelFile)modelBuilder2).rotationX(90).nextModel()
/*  91 */       .modelFile((ModelFile)modelBuilder2).rotationX(180).nextModel()
/*  92 */       .modelFile((ModelFile)modelBuilder2).rotationX(270).nextModel()
/*  93 */       .modelFile((ModelFile)modelBuilder2).rotationY(90).nextModel()
/*  94 */       .modelFile((ModelFile)modelBuilder2).rotationY(180).nextModel()
/*  95 */       .modelFile((ModelFile)modelBuilder2).rotationY(270).addModel();
/*  96 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get(), (ModelFile)modelBuilder2);
/*     */     
/*  98 */     ModelBuilder modelBuilder3 = models().cubeAll(WitherStormModBlocks.INFECTED_FLESH_BLOCK.getId().m_135815_(), modLoc("block/infected_flesh_block"));
/*  99 */     getVariantBuilder((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get()).partialState().modelForState()
/* 100 */       .modelFile((ModelFile)modelBuilder3).nextModel()
/* 101 */       .modelFile((ModelFile)modelBuilder3).rotationX(90).nextModel()
/* 102 */       .modelFile((ModelFile)modelBuilder3).rotationX(180).nextModel()
/* 103 */       .modelFile((ModelFile)modelBuilder3).rotationX(270).nextModel()
/* 104 */       .modelFile((ModelFile)modelBuilder3).rotationY(90).nextModel()
/* 105 */       .modelFile((ModelFile)modelBuilder3).rotationY(180).nextModel()
/* 106 */       .modelFile((ModelFile)modelBuilder3).rotationY(270).addModel();
/* 107 */     simpleBlockItem((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get(), (ModelFile)modelBuilder3);
/*     */     
/* 109 */     ModelBuilder modelBuilder4 = models().cubeAll(WitherStormModBlocks.HARDENED_FLESH_BLOCK.getId().m_135815_(), modLoc("block/hardened_flesh_block"));
/* 110 */     getVariantBuilder((Block)WitherStormModBlocks.HARDENED_FLESH_BLOCK.get()).partialState().modelForState()
/* 111 */       .modelFile((ModelFile)modelBuilder4).nextModel()
/* 112 */       .modelFile((ModelFile)modelBuilder4).rotationX(90).nextModel()
/* 113 */       .modelFile((ModelFile)modelBuilder4).rotationX(180).nextModel()
/* 114 */       .modelFile((ModelFile)modelBuilder4).rotationX(270).nextModel()
/* 115 */       .modelFile((ModelFile)modelBuilder4).rotationY(90).nextModel()
/* 116 */       .modelFile((ModelFile)modelBuilder4).rotationY(180).nextModel()
/* 117 */       .modelFile((ModelFile)modelBuilder4).rotationY(270).addModel();
/* 118 */     simpleBlockItem((Block)WitherStormModBlocks.HARDENED_FLESH_BLOCK.get(), (ModelFile)modelBuilder4);
/*     */     
/* 120 */     ModelBuilder modelBuilder5 = models().cubeAll(WitherStormModBlocks.TAINTED_STONE.getId().m_135815_(), modLoc("block/tainted_stone"));
/* 121 */     getVariantBuilder((Block)WitherStormModBlocks.TAINTED_STONE.get()).partialState().modelForState()
/* 122 */       .modelFile((ModelFile)modelBuilder5).nextModel()
/* 123 */       .modelFile((ModelFile)modelBuilder5).rotationY(180).addModel();
/* 124 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_STONE.get(), (ModelFile)modelBuilder5);
/*     */     
/* 126 */     stairsBlock((StairBlock)WitherStormModBlocks.TAINTED_STONE_STAIRS.get(), modLoc("block/tainted_stone"));
/* 127 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_STONE_STAIRS.getId()));
/* 128 */     slabBlock((SlabBlock)WitherStormModBlocks.TAINTED_STONE_SLAB.get(), WitherStormModBlocks.TAINTED_STONE.getId(), modLoc("block/tainted_stone"));
/* 129 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_STONE_SLAB.getId()));
/* 130 */     buttonBlock((ButtonBlock)WitherStormModBlocks.TAINTED_STONE_BUTTON.get(), modLoc("block/tainted_stone"));
/* 131 */     pressurePlateBlock((PressurePlateBlock)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get(), modLoc("block/tainted_stone"));
/* 132 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.getId()));
/* 133 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get(), (ModelFile)models().cubeAll(WitherStormModBlocks.TAINTED_COBBLESTONE.getId().m_135815_(), modLoc("block/tainted_cobblestone")));
/* 134 */     stairsBlock((StairBlock)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get(), modLoc("block/tainted_cobblestone"));
/* 135 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.getId()));
/* 136 */     slabBlock((SlabBlock)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get(), WitherStormModBlocks.TAINTED_COBBLESTONE.getId(), modLoc("block/tainted_cobblestone"));
/* 137 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.getId()));
/* 138 */     wallBlock((WallBlock)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get(), modLoc("block/tainted_cobblestone"));
/* 139 */     ModelBuilder modelBuilder6 = models().cubeAll(WitherStormModBlocks.TAINTED_SAND.getId().m_135815_(), modLoc("block/tainted_sand"));
/* 140 */     getVariantBuilder((Block)WitherStormModBlocks.TAINTED_SAND.get()).partialState().modelForState()
/* 141 */       .modelFile((ModelFile)modelBuilder6).nextModel()
/* 142 */       .modelFile((ModelFile)modelBuilder6).rotationY(90).nextModel()
/* 143 */       .modelFile((ModelFile)modelBuilder6).rotationY(180).nextModel()
/* 144 */       .modelFile((ModelFile)modelBuilder6).rotationY(270).addModel();
/* 145 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_SAND.get(), (ModelFile)modelBuilder6);
/* 146 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_GLASS.get(), (ModelFile)((BlockModelBuilder)models().cubeAll(WitherStormModBlocks.TAINTED_GLASS.getId().m_135815_(), modLoc("block/tainted_glass"))).renderType("translucent"));
/* 147 */     paneBlockWithRenderType((IronBarsBlock)WitherStormModBlocks.TAINTED_GLASS_PANE.get(), modLoc("block/tainted_glass"), modLoc("block/tainted_glass_pane_top"), "translucent");
/* 148 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_PLANKS.get(), (ModelFile)models().cubeAll(WitherStormModBlocks.TAINTED_PLANKS.getId().m_135815_(), modLoc("block/tainted_planks")));
/* 149 */     axisBlock((RotatedPillarBlock)WitherStormModBlocks.TAINTED_LOG.get(), modLoc("block/tainted_log"), modLoc("block/tainted_log_top"));
/* 150 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_LOG.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_LOG.getId()));
/* 151 */     axisBlock((RotatedPillarBlock)WitherStormModBlocks.TAINTED_WOOD.get(), modLoc("block/tainted_log"), modLoc("block/tainted_log"));
/* 152 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_WOOD.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_WOOD.getId()));
/* 153 */     axisBlock((RotatedPillarBlock)WitherStormModBlocks.STRIPPED_TAINTED_LOG.get(), modLoc("block/stripped_tainted_log"), modLoc("block/stripped_tainted_log_top"));
/* 154 */     simpleBlockItem((Block)WitherStormModBlocks.STRIPPED_TAINTED_LOG.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.STRIPPED_TAINTED_LOG.getId()));
/* 155 */     axisBlock((RotatedPillarBlock)WitherStormModBlocks.STRIPPED_TAINTED_WOOD.get(), modLoc("block/stripped_tainted_log"), modLoc("block/stripped_tainted_log"));
/* 156 */     simpleBlockItem((Block)WitherStormModBlocks.STRIPPED_TAINTED_WOOD.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.STRIPPED_TAINTED_WOOD.getId()));
/* 157 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_LEAVES.get(), (ModelFile)((BlockModelBuilder)((BlockModelBuilder)models().withExistingParent(WitherStormModBlocks.TAINTED_LEAVES.getId().m_135815_(), "block/leaves")).texture("all", modLoc("block/tainted_leaves"))).renderType("cutout"));
/* 158 */     doorBlock((DoorBlock)WitherStormModBlocks.TAINTED_DOOR.get(), modLoc("block/tainted_door_bottom"), modLoc("block/tainted_door_top"));
/* 159 */     trapdoorBlockWithRenderType((TrapDoorBlock)WitherStormModBlocks.TAINTED_TRAPDOOR.get(), modLoc("block/tainted_trapdoor"), true, "cutout");
/* 160 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get(), (ModelFile)models().getExistingFile(extend(WitherStormModBlocks.TAINTED_TRAPDOOR.getId(), "_bottom")));
/* 161 */     buttonBlock((ButtonBlock)WitherStormModBlocks.TAINTED_BUTTON.get(), modLoc("block/tainted_planks"));
/* 162 */     pressurePlateBlock((PressurePlateBlock)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get(), modLoc("block/tainted_planks"));
/* 163 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_PRESSURE_PLATE.getId()));
/* 164 */     stairsBlock((StairBlock)WitherStormModBlocks.TAINTED_STAIRS.get(), modLoc("block/tainted_planks"));
/* 165 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_STAIRS.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_STAIRS.getId()));
/* 166 */     slabBlock((SlabBlock)WitherStormModBlocks.TAINTED_SLAB.get(), WitherStormModBlocks.TAINTED_PLANKS.getId(), modLoc("block/tainted_planks"));
/* 167 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_SLAB.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_SLAB.getId()));
/* 168 */     fenceBlock((FenceBlock)WitherStormModBlocks.TAINTED_FENCE.get(), modLoc("block/tainted_planks"));
/* 169 */     fenceGateBlock((FenceGateBlock)WitherStormModBlocks.TAINTED_FENCE_GATE.get(), modLoc("block/tainted_planks"));
/* 170 */     simpleBlockItem((Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_FENCE_GATE.getId()));
/* 171 */     simpleBlock((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.TAINTED_MUSHROOM.getId()));
/* 172 */     simpleBlock((Block)WitherStormModBlocks.POTTED_TAINTED_MUSHROOM.get(), (ModelFile)models().getExistingFile(WitherStormModBlocks.POTTED_TAINTED_MUSHROOM.getId()));
/* 173 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get(), (ModelFile)models().cubeColumn(WitherStormModBlocks.TAINTED_PUMPKIN.getId().m_135815_(), modLoc("block/tainted_pumpkin_side"), modLoc("block/tainted_pumpkin_top")));
/* 174 */     pumpkin((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get(), modLoc("block/tainted_pumpkin_side"), modLoc("block/tainted_carved_pumpkin"), modLoc("block/tainted_pumpkin_top"));
/* 175 */     pumpkin((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get(), modLoc("block/tainted_pumpkin_side"), modLoc("block/tainted_jack_o_lantern"), modLoc("block/tainted_pumpkin_top"));
/* 176 */     simpleBlockWithItem((Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get(), (ModelFile)models().cubeAll(WitherStormModBlocks.TAINTED_DUST_BLOCK.getId().m_135815_(), modLoc("block/tainted_dust_block")));
/* 177 */     signBlock((StandingSignBlock)WitherStormModBlocks.TAINTED_SIGN.get(), (WallSignBlock)WitherStormModBlocks.TAINTED_WALL_SIGN.get(), modLoc("block/tainted_planks"));
/*     */   }
/*     */ 
/*     */   
/*     */   private ResourceLocation extend(ResourceLocation rl, String suffix) {
/* 182 */     return new ResourceLocation(rl.m_135827_(), rl.m_135815_() + rl.m_135815_());
/*     */   }
/*     */ 
/*     */   
/*     */   private void pumpkin(Block block, ResourceLocation side, ResourceLocation front, ResourceLocation top) {
/* 187 */     ModelBuilder modelBuilder = models().orientable(ForgeRegistries.BLOCKS.getKey(block).m_135815_(), side, front, top);
/* 188 */     horizontalBlock(block, (ModelFile)modelBuilder);
/* 189 */     simpleBlockItem(block, (ModelFile)modelBuilder);
/*     */   }
/*     */ 
/*     */   
/*     */   private void torch(Block block, ResourceLocation torch) {
/* 194 */     ModelBuilder modelBuilder = ((BlockModelBuilder)models().torch(ForgeRegistries.BLOCKS.getKey(block).m_135815_(), torch)).renderType("cutout");
/* 195 */     simpleBlock(block, (ModelFile)modelBuilder);
/*     */   }
/*     */ 
/*     */   
/*     */   private void wallTorch(Block block, ResourceLocation torch) {
/* 200 */     ModelBuilder modelBuilder = ((BlockModelBuilder)models().torchWall(ForgeRegistries.BLOCKS.getKey(block).m_135815_(), torch)).renderType("cutout");
/* 201 */     horizontalBlock(block, (ModelFile)modelBuilder, 90);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\WitherStormModBlockStatesProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */