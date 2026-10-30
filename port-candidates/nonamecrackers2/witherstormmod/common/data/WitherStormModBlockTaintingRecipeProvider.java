/*    */ package nonamecrackers2.witherstormmod.common.data;
/*    */ 
/*    */ import net.minecraft.data.PackOutput;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.world.effect.MobEffects;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import net.minecraftforge.common.Tags;
/*    */ import nonamecrackers2.witherstormmod.api.common.data.BlockTaintingRecipeProvider;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ 
/*    */ public class WitherStormModBlockTaintingRecipeProvider
/*    */   extends BlockTaintingRecipeProvider
/*    */ {
/*    */   public WitherStormModBlockTaintingRecipeProvider(PackOutput output) {
/* 16 */     super(output, "witherstormmod");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void addRecipes() {
/* 23 */     addAndCopyAllProperties(Blocks.CARVED_PUMPKIN, MobEffects.WITHER, (Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
/* 24 */     addAndCopyAllProperties(Blocks.JACK_O_LANTERN, MobEffects.WITHER, (Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get());
/* 25 */     add(Blocks.PUMPKIN, MobEffects.WITHER, ((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get()).defaultBlockState());
/* 26 */     addAndCopyAllProperties(Blocks.BROWN_MUSHROOM, (Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
/* 27 */     addAndCopyAllProperties(Blocks.RED_MUSHROOM, (Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
/* 28 */     addAndCopyAllProperties(Blocks.REDSTONE_WIRE, (Block)WitherStormModBlocks.TAINTED_DUST.get());
/* 29 */     addAndCopyAllProperties(BlockTags.SMALL_FLOWERS, Blocks.WITHER_ROSE);
/* 30 */     addAndCopyAllProperties(BlockTags.BASE_STONE_OVERWORLD, (Block)WitherStormModBlocks.TAINTED_STONE.get());
/* 31 */     addAndCopyAllProperties(Blocks.STONE_STAIRS, (Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get());
/* 32 */     addAndCopyAllProperties(Blocks.STONE_SLAB, (Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get());
/* 33 */     addAndCopyAllProperties(Blocks.STONE_BUTTON, (Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get());
/* 34 */     addAndCopyAllProperties(Blocks.STONE_PRESSURE_PLATE, (Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get());
/* 35 */     addAndCopyAllProperties(Tags.Blocks.COBBLESTONE, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
/* 36 */     addAndCopyAllProperties(Blocks.COBBLESTONE_STAIRS, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get());
/* 37 */     addAndCopyAllProperties(Blocks.COBBLESTONE_SLAB, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get());
/* 38 */     addAndCopyAllProperties(BlockTags.WALLS, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get());
/* 39 */     addAndCopyAllProperties(BlockTags.DIRT, (Block)WitherStormModBlocks.TAINTED_DIRT.get());
/* 40 */     addAndCopyAllProperties(Tags.Blocks.SAND, (Block)WitherStormModBlocks.TAINTED_SAND.get());
/* 41 */     addAndCopyAllProperties(Tags.Blocks.GLASS, (Block)WitherStormModBlocks.TAINTED_GLASS.get());
/* 42 */     addAndCopyAllProperties(Tags.Blocks.GLASS_PANES, (Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get());
/* 43 */     addAndCopyAllProperties(Blocks.REDSTONE_BLOCK, (Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get());
/* 44 */     addAndCopyAllProperties(BlockTags.PLANKS, (Block)WitherStormModBlocks.TAINTED_PLANKS.get());
/* 45 */     addAndCopyAllProperties(BlockTags.OVERWORLD_NATURAL_LOGS, (Block)WitherStormModBlocks.TAINTED_LOG.get());
/*    */ 
/*    */     
/* 48 */     addAndCopyAllProperties(BlockTags.LEAVES, (Block)WitherStormModBlocks.TAINTED_LEAVES.get());
/* 49 */     addAndCopyAllProperties(BlockTags.WOODEN_DOORS, (Block)WitherStormModBlocks.TAINTED_DOOR.get());
/* 50 */     addAndCopyAllProperties(BlockTags.WOODEN_TRAPDOORS, (Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get());
/* 51 */     addAndCopyAllProperties(BlockTags.WOODEN_BUTTONS, (Block)WitherStormModBlocks.TAINTED_BUTTON.get());
/* 52 */     addAndCopyAllProperties(BlockTags.WOODEN_PRESSURE_PLATES, (Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get());
/* 53 */     addAndCopyAllProperties(BlockTags.WOODEN_STAIRS, (Block)WitherStormModBlocks.TAINTED_STAIRS.get());
/* 54 */     addAndCopyAllProperties(BlockTags.WOODEN_SLABS, (Block)WitherStormModBlocks.TAINTED_SLAB.get());
/* 55 */     addAndCopyAllProperties(BlockTags.WOODEN_FENCES, (Block)WitherStormModBlocks.TAINTED_FENCE.get());
/* 56 */     addAndCopyAllProperties(BlockTags.FENCE_GATES, (Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get());
/* 57 */     addAndCopyAllProperties(Tags.Blocks.SANDSTONE, (Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
/* 58 */     addAndCopyAllProperties(Blocks.SANDSTONE_SLAB, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
/* 59 */     addAndCopyAllProperties(Blocks.SANDSTONE_STAIRS, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
/* 60 */     addAndCopyAllProperties(Blocks.SANDSTONE_WALL, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
/* 61 */     addAndCopyAllProperties(Blocks.CUT_SANDSTONE, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
/* 62 */     addAndCopyAllProperties(Blocks.CUT_SANDSTONE_SLAB, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
/* 63 */     addAndCopyAllProperties(Blocks.SMOOTH_SANDSTONE, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
/* 64 */     addAndCopyAllProperties(Blocks.SMOOTH_SANDSTONE_SLAB, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
/* 65 */     addAndCopyAllProperties(Blocks.SMOOTH_SANDSTONE_STAIRS, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
/* 66 */     addAndCopyAllProperties(Blocks.RED_SANDSTONE, (Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
/* 67 */     addAndCopyAllProperties(Blocks.RED_SANDSTONE_SLAB, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
/* 68 */     addAndCopyAllProperties(Blocks.RED_SANDSTONE_STAIRS, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
/* 69 */     addAndCopyAllProperties(Blocks.RED_SANDSTONE_WALL, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
/* 70 */     addAndCopyAllProperties(Blocks.CUT_RED_SANDSTONE, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
/* 71 */     addAndCopyAllProperties(Blocks.CUT_RED_SANDSTONE_SLAB, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
/* 72 */     addAndCopyAllProperties(Blocks.SMOOTH_RED_SANDSTONE, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
/* 73 */     addAndCopyAllProperties(Blocks.SMOOTH_RED_SANDSTONE_SLAB, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
/* 74 */     addAndCopyAllProperties(Blocks.SMOOTH_RED_SANDSTONE_STAIRS, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
/* 75 */     addAndCopyAllProperties(Blocks.TORCH, (Block)WitherStormModBlocks.TAINTED_TORCH.get());
/* 76 */     addAndCopyAllProperties(Blocks.WALL_TORCH, (Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\WitherStormModBlockTaintingRecipeProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */