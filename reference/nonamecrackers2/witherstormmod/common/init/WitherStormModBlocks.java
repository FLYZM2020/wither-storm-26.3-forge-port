/*     */ package nonamecrackers2.witherstormmod.common.init;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.ButtonBlock;
/*     */ import net.minecraft.world.level.block.CeilingHangingSignBlock;
/*     */ import net.minecraft.world.level.block.DoorBlock;
/*     */ import net.minecraft.world.level.block.FenceBlock;
/*     */ import net.minecraft.world.level.block.FenceGateBlock;
/*     */ import net.minecraft.world.level.block.FlowerPotBlock;
/*     */ import net.minecraft.world.level.block.GlassBlock;
/*     */ import net.minecraft.world.level.block.IronBarsBlock;
/*     */ import net.minecraft.world.level.block.LeavesBlock;
/*     */ import net.minecraft.world.level.block.PressurePlateBlock;
/*     */ import net.minecraft.world.level.block.RedstoneLampBlock;
/*     */ import net.minecraft.world.level.block.RotatedPillarBlock;
/*     */ import net.minecraft.world.level.block.SandBlock;
/*     */ import net.minecraft.world.level.block.SlabBlock;
/*     */ import net.minecraft.world.level.block.SoundType;
/*     */ import net.minecraft.world.level.block.StairBlock;
/*     */ import net.minecraft.world.level.block.StandingSignBlock;
/*     */ import net.minecraft.world.level.block.TrapDoorBlock;
/*     */ import net.minecraft.world.level.block.WallBlock;
/*     */ import net.minecraft.world.level.block.WallHangingSignBlock;
/*     */ import net.minecraft.world.level.block.WallSignBlock;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.BlockSetType;
/*     */ import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
/*     */ import net.minecraft.world.level.block.state.properties.WoodType;
/*     */ import net.minecraft.world.level.material.MapColor;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.RegistryObject;
/*     */ import nonamecrackers2.witherstormmod.common.block.FireworkBundleBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.FormidibombBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.NonGrowableMushroomBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.StrippableLogBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.SuperBeaconBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.SuperSupportBeaconBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.SuperTNTBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.TaintedCarvedPumpkinBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.TaintedPumpkinBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.TaintedStatue;
/*     */ import nonamecrackers2.witherstormmod.common.block.TaintedTorchBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.TaintedVeinBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.TaintedWallTorchBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.WireBlock;
/*     */ import nonamecrackers2.witherstormmod.common.block.WitheredPhlegmBlock;
/*     */ 
/*     */ public class WitherStormModBlocks {
/*  57 */   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "witherstormmod");
/*     */   
/*  59 */   public static final BlockSetType TAINTED_SET = BlockSetType.m_272115_(new BlockSetType("tainted"));
/*  60 */   public static final WoodType TAINTED = WoodType.m_61844_(new WoodType("tainted", TAINTED_SET));
/*     */ 
/*     */   
/*  63 */   public static final RegistryObject<Block> SUPER_TNT = BLOCKS.register("super_tnt", () -> new SuperTNTBlock(BlockBehaviour.Properties.m_284310_().m_60966_().m_60918_(SoundType.f_56740_)));
/*  64 */   public static final RegistryObject<Block> FORMIDIBOMB = BLOCKS.register("formidibomb", () -> new FormidibombBlock(BlockBehaviour.Properties.m_284310_().m_60978_(0.8F).m_60918_(SoundType.f_56740_).m_60953_(())));
/*     */ 
/*     */   
/*  67 */   public static final RegistryObject<Block> SUPER_BEACON = BLOCKS.register("super_beacon", () -> new SuperBeaconBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50058_).m_60978_(3.0F).m_60955_().m_60953_(())));
/*  68 */   public static final RegistryObject<Block> SUPER_SUPPORT_BEACON = BLOCKS.register("super_support_beacon", () -> new SuperSupportBeaconBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50058_).m_60978_(2.5F).m_60955_().m_60953_(())));
/*  69 */   public static final RegistryObject<Block> FIREWORK_BUNDLE = BLOCKS.register("firework_bundle", () -> new FireworkBundleBlock(BlockBehaviour.Properties.m_284310_().m_60978_(2.5F).m_60918_(SoundType.f_56740_)));
/*     */ 
/*     */   
/*  72 */   public static final RegistryObject<Block> TAINTED_ZOMBIE_SITTING = BLOCKS.register("tainted_zombie_sitting", () -> new TaintedStatue(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50374_).m_60910_().m_60913_(1.0F, 6.0F).m_60955_()));
/*  73 */   public static final RegistryObject<Block> TAINTED_ZOMBIE_WALL = BLOCKS.register("tainted_zombie_wall", () -> new TaintedStatue(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50374_).m_60910_().m_60913_(1.0F, 6.0F).m_60955_()));
/*  74 */   public static final RegistryObject<Block> TAINTED_ZOMBIE_LYING = BLOCKS.register("tainted_zombie_lying", () -> new TaintedStatue(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50374_).m_60910_().m_60913_(1.0F, 6.0F).m_60955_()));
/*  75 */   public static final RegistryObject<Block> TAINTED_BONE_PILE = BLOCKS.register("tainted_bone_pile", () -> new TaintedStatue(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50453_).m_60910_().m_60913_(1.0F, 6.0F).m_60955_()));
/*  76 */   public static final RegistryObject<Block> TAINTED_SKELETON_WALL = BLOCKS.register("tainted_skeleton_wall", () -> new TaintedStatue(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50453_).m_60910_().m_60913_(1.0F, 6.0F).m_60955_()));
/*  77 */   public static final RegistryObject<Block> TAINTED_SKULL_CEILING = BLOCKS.register("tainted_skull_ceiling", () -> new TaintedStatue(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50453_).m_60910_().m_60913_(1.0F, 6.0F).m_60955_()));
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static final RegistryObject<Block> TAINTED_FLESH_VEINS = BLOCKS.register("tainted_flesh_veins", () -> new TaintedVeinBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60978_(0.4F).m_60918_(SoundType.f_56750_)));
/*  82 */   public static final RegistryObject<Block> TAINTED_FLESH_BLOCK = BLOCKS.register("tainted_flesh_block", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.6F).m_60918_(SoundType.f_56750_)));
/*  83 */   public static final RegistryObject<Block> INFECTED_FLESH_BLOCK = BLOCKS.register("infected_flesh_block", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60918_(SoundType.f_56750_)));
/*  84 */   public static final RegistryObject<Block> HARDENED_FLESH_BLOCK = BLOCKS.register("hardened_flesh_block", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(-1.0F, 3600000.0F).m_60918_(SoundType.f_56750_)));
/*  85 */   public static final RegistryObject<Block> WITHERED_PHLEGM_BLOCK = BLOCKS.register("withered_phlegm_block", () -> new WitheredPhlegmBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.5F).m_60918_(SoundType.f_56750_).m_60953_(()).m_60955_().m_60991_(())));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static final RegistryObject<Block> TAINTED_STONE = BLOCKS.register("tainted_stone", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60999_().m_60913_(1.5F, 6.0F)));
/*  91 */   public static final RegistryObject<Block> TAINTED_STONE_STAIRS = BLOCKS.register("tainted_stone_stairs", () -> new StairBlock((), BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60999_().m_60913_(3.0F, 6.0F)));
/*  92 */   public static final RegistryObject<Block> TAINTED_STONE_SLAB = BLOCKS.register("tainted_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60999_().m_60913_(3.0F, 6.0F)));
/*  93 */   public static final RegistryObject<Block> TAINTED_STONE_BUTTON = BLOCKS.register("tainted_stone_button", () -> new ButtonBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60955_().m_60978_(0.5F).m_60918_(SoundType.f_56742_), BlockSetType.f_271479_, 20, false));
/*  94 */   public static final RegistryObject<Block> TAINTED_STONE_PRESSURE_PLATE = BLOCKS.register("tainted_stone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60955_().m_60978_(0.5F).m_60918_(SoundType.f_56742_), BlockSetType.f_271479_));
/*     */ 
/*     */   
/*  97 */   public static final RegistryObject<Block> TAINTED_COBBLESTONE = BLOCKS.register("tainted_cobblestone", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60999_().m_60913_(2.0F, 6.0F)));
/*  98 */   public static final RegistryObject<Block> TAINTED_COBBLESTONE_STAIRS = BLOCKS.register("tainted_cobblestone_stairs", () -> new StairBlock((), BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60999_().m_60913_(3.0F, 6.0F)));
/*  99 */   public static final RegistryObject<Block> TAINTED_COBBLESTONE_SLAB = BLOCKS.register("tainted_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60999_().m_60913_(3.0F, 6.0F)));
/* 100 */   public static final RegistryObject<Block> TAINTED_COBBLESTONE_WALL = BLOCKS.register("tainted_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60999_().m_60913_(3.0F, 6.0F)));
/*     */ 
/*     */   
/* 103 */   public static final RegistryObject<Block> TAINTED_SAND = BLOCKS.register("tainted_sand", () -> new SandBlock(10708917, BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.5F).m_60918_(SoundType.f_56746_)));
/* 104 */   public static final RegistryObject<Block> TAINTED_DIRT = BLOCKS.register("tainted_dirt", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.5F).m_60918_(SoundType.f_56739_)));
/* 105 */   public static final RegistryObject<Block> TAINTED_SANDSTONE = BLOCKS.register("tainted_sandstone", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60999_().m_60918_(SoundType.f_56742_)));
/* 106 */   public static final RegistryObject<Block> TAINTED_SANDSTONE_SLAB = BLOCKS.register("tainted_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60999_().m_60918_(SoundType.f_56742_)));
/* 107 */   public static final RegistryObject<Block> TAINTED_SANDSTONE_STAIRS = BLOCKS.register("tainted_sandstone_stairs", () -> new StairBlock((), BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56742_)));
/* 108 */   public static final RegistryObject<Block> TAINTED_SANDSTONE_WALL = BLOCKS.register("tainted_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60999_().m_60978_(0.8F)));
/* 109 */   public static final RegistryObject<Block> TAINTED_CUT_SANDSTONE = BLOCKS.register("tainted_cut_sandstone", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60999_().m_60918_(SoundType.f_56742_)));
/* 110 */   public static final RegistryObject<Block> TAINTED_CUT_SANDSTONE_SLAB = BLOCKS.register("tainted_cut_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60999_().m_60918_(SoundType.f_56742_)));
/* 111 */   public static final RegistryObject<Block> TAINTED_CHISELED_SANDSTONE = BLOCKS.register("tainted_chiseled_sandstone", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60999_().m_60918_(SoundType.f_56742_)));
/* 112 */   public static final RegistryObject<Block> TAINTED_SMOOTH_SANDSTONE = BLOCKS.register("tainted_smooth_sandstone", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60999_().m_60918_(SoundType.f_56742_)));
/* 113 */   public static final RegistryObject<Block> TAINTED_SMOOTH_SANDSTONE_SLAB = BLOCKS.register("tainted_smooth_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60999_().m_60918_(SoundType.f_56742_)));
/* 114 */   public static final RegistryObject<Block> TAINTED_SMOOTH_SANDSTONE_STAIRS = BLOCKS.register("tainted_smooth_sandstone_stairs", () -> new StairBlock((), BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56742_)));
/* 115 */   public static final RegistryObject<Block> TAINTED_SMOOTH_SANDSTONE_WALL = BLOCKS.register("tainted_smooth_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(0.8F).m_60999_().m_60918_(SoundType.f_56742_)));
/*     */   
/* 117 */   public static final RegistryObject<Block> TAINTED_GLASS = BLOCKS.register("tainted_glass", () -> new GlassBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50058_).m_284180_(MapColor.f_283889_).m_60913_(0.6F, 1200.0F).m_60955_()));
/* 118 */   public static final RegistryObject<Block> TAINTED_GLASS_PANE = BLOCKS.register("tainted_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50058_).m_284180_(MapColor.f_283889_).m_60913_(0.6F, 1200.0F).m_60955_()));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public static final RegistryObject<Block> TAINTED_PLANKS = BLOCKS.register("tainted_planks", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56736_)));
/* 124 */   public static final RegistryObject<Block> TAINTED_TORCH = BLOCKS.register("tainted_torch", () -> new TaintedTorchBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60966_().m_60953_(()).m_60918_(SoundType.f_56736_), (Supplier)WitherStormModParticleTypes.PHLEGM));
/* 125 */   public static final RegistryObject<Block> TAINTED_WALL_TORCH = BLOCKS.register("tainted_wall_torch", () -> new TaintedWallTorchBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60966_().m_60953_(()).m_60918_(SoundType.f_56736_), (Supplier)WitherStormModParticleTypes.PHLEGM));
/* 126 */   public static final RegistryObject<Block> TAINTED_SIGN = BLOCKS.register("tainted_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60978_(1.0F).m_60918_(SoundType.f_56736_), TAINTED));
/* 127 */   public static final RegistryObject<Block> TAINTED_WALL_SIGN = BLOCKS.register("tainted_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60978_(1.0F).m_60918_(SoundType.f_56736_), TAINTED));
/* 128 */   public static final RegistryObject<Block> TAINTED_HANGING_SIGN = BLOCKS.register("tainted_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_280606_().m_280658_(NoteBlockInstrument.BASS).m_60910_().m_60978_(1.0F).m_278183_().m_60918_(SoundType.f_56736_), TAINTED));
/* 129 */   public static final RegistryObject<Block> TAINTED_WALL_HANGING_SIGN = BLOCKS.register("tainted_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_280606_().m_280658_(NoteBlockInstrument.BASS).lootFrom((Supplier)TAINTED_HANGING_SIGN).m_60910_().m_60978_(1.0F).m_278183_().m_60918_(SoundType.f_56736_), TAINTED));
/* 130 */   public static final RegistryObject<Block> STRIPPED_TAINTED_LOG = BLOCKS.register("stripped_tainted_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_49999_).m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F)));
/* 131 */   public static final RegistryObject<Block> STRIPPED_TAINTED_WOOD = BLOCKS.register("stripped_tainted_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50011_).m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F)));
/* 132 */   public static final RegistryObject<Block> TAINTED_LOG = BLOCKS.register("tainted_log", () -> new StrippableLogBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_49999_).m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F), (Supplier)STRIPPED_TAINTED_LOG));
/* 133 */   public static final RegistryObject<Block> TAINTED_WOOD = BLOCKS.register("tainted_wood", () -> new StrippableLogBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50011_).m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F), (Supplier)STRIPPED_TAINTED_WOOD));
/* 134 */   public static final RegistryObject<Block> TAINTED_LEAVES = BLOCKS.register("tainted_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50050_).m_284180_(MapColor.f_283889_)) {
/* 135 */         public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
/* 136 */         public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 20; }
/* 137 */         public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
/* 138 */       }); public static final RegistryObject<Block> TAINTED_DOOR = BLOCKS.register("tainted_door", () -> new DoorBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56736_).m_60955_(), TAINTED_SET));
/* 139 */   public static final RegistryObject<Block> TAINTED_TRAPDOOR = BLOCKS.register("tainted_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56736_).m_60955_(), TAINTED_SET));
/* 140 */   public static final RegistryObject<Block> TAINTED_BUTTON = BLOCKS.register("tainted_button", () -> new ButtonBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60955_().m_60978_(0.5F).m_60918_(SoundType.f_56736_), TAINTED_SET, 30, true));
/* 141 */   public static final RegistryObject<Block> TAINTED_PRESSURE_PLATE = BLOCKS.register("tainted_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60955_().m_60978_(0.5F).m_60918_(SoundType.f_56736_), TAINTED_SET));
/* 142 */   public static final RegistryObject<Block> TAINTED_STAIRS = BLOCKS.register("tainted_stairs", () -> new StairBlock((), BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56736_)));
/* 143 */   public static final RegistryObject<Block> TAINTED_SLAB = BLOCKS.register("tainted_slab", () -> new SlabBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56736_)));
/* 144 */   public static final RegistryObject<Block> TAINTED_FENCE = BLOCKS.register("tainted_fence", () -> new FenceBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56736_)));
/* 145 */   public static final RegistryObject<Block> TAINTED_FENCE_GATE = BLOCKS.register("tainted_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60913_(2.0F, 3.0F).m_60918_(SoundType.f_56736_), TAINTED));
/* 146 */   public static final RegistryObject<Block> TAINTED_MUSHROOM = BLOCKS.register("tainted_mushroom", () -> new NonGrowableMushroomBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60977_().m_60966_().m_60918_(SoundType.f_56711_).m_60982_(())));
/* 147 */   public static final RegistryObject<Block> POTTED_TAINTED_MUSHROOM = BLOCKS.register("potted_tainted_mushroom", () -> new FlowerPotBlock((), (Supplier)TAINTED_MUSHROOM, BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60966_()));
/* 148 */   public static final RegistryObject<Block> TAINTED_PUMPKIN = BLOCKS.register("tainted_pumpkin", () -> new TaintedPumpkinBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(1.0F).m_60918_(SoundType.f_56736_)));
/* 149 */   public static final RegistryObject<Block> TAINTED_CARVED_PUMPKIN = BLOCKS.register("tainted_carved_pumpkin", () -> new TaintedCarvedPumpkinBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(1.0F).m_60918_(SoundType.f_56736_).m_60922_(())));
/* 150 */   public static final RegistryObject<Block> TAINTED_JACK_O_LANTERN = BLOCKS.register("tainted_jack_o_lantern", () -> new TaintedCarvedPumpkinBlock(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60978_(1.0F).m_60918_(SoundType.f_56736_).m_60953_(())));
/*     */ 
/*     */   
/* 153 */   public static final RegistryObject<Block> TAINTED_DUST = BLOCKS.register("tainted_dust", () -> new WireBlock(1.0F, 0.2509804F, 0.8392157F, BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60910_().m_60966_().m_60918_(SoundType.f_56711_)));
/* 154 */   public static final RegistryObject<Block> TAINTED_DUST_BLOCK = BLOCKS.register("tainted_dust_block", () -> new RedstoneLampBlock(BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50261_).m_284180_(MapColor.f_283889_).m_60913_(1.2F, 1.2F).m_60955_().m_60953_(()).m_60991_(())));
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModBlocks.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */