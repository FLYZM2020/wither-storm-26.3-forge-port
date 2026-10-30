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
/* 23 */     addAndCopyAllProperties(Blocks.f_50143_, MobEffects.f_19615_, (Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
/* 24 */     addAndCopyAllProperties(Blocks.f_50144_, MobEffects.f_19615_, (Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get());
/* 25 */     add(Blocks.f_50133_, MobEffects.f_19615_, ((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get()).m_49966_());
/* 26 */     addAndCopyAllProperties(Blocks.f_50072_, (Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
/* 27 */     addAndCopyAllProperties(Blocks.f_50073_, (Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
/* 28 */     addAndCopyAllProperties(Blocks.f_50088_, (Block)WitherStormModBlocks.TAINTED_DUST.get());
/* 29 */     addAndCopyAllProperties(BlockTags.f_13037_, Blocks.f_50070_);
/* 30 */     addAndCopyAllProperties(BlockTags.f_13061_, (Block)WitherStormModBlocks.TAINTED_STONE.get());
/* 31 */     addAndCopyAllProperties(Blocks.f_50635_, (Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get());
/* 32 */     addAndCopyAllProperties(Blocks.f_50404_, (Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get());
/* 33 */     addAndCopyAllProperties(Blocks.f_50124_, (Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get());
/* 34 */     addAndCopyAllProperties(Blocks.f_50165_, (Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get());
/* 35 */     addAndCopyAllProperties(Tags.Blocks.COBBLESTONE, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
/* 36 */     addAndCopyAllProperties(Blocks.f_50157_, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get());
/* 37 */     addAndCopyAllProperties(Blocks.f_50409_, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get());
/* 38 */     addAndCopyAllProperties(BlockTags.f_13032_, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get());
/* 39 */     addAndCopyAllProperties(BlockTags.f_144274_, (Block)WitherStormModBlocks.TAINTED_DIRT.get());
/* 40 */     addAndCopyAllProperties(Tags.Blocks.SAND, (Block)WitherStormModBlocks.TAINTED_SAND.get());
/* 41 */     addAndCopyAllProperties(Tags.Blocks.GLASS, (Block)WitherStormModBlocks.TAINTED_GLASS.get());
/* 42 */     addAndCopyAllProperties(Tags.Blocks.GLASS_PANES, (Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get());
/* 43 */     addAndCopyAllProperties(Blocks.f_50330_, (Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get());
/* 44 */     addAndCopyAllProperties(BlockTags.f_13090_, (Block)WitherStormModBlocks.TAINTED_PLANKS.get());
/* 45 */     addAndCopyAllProperties(BlockTags.f_215839_, (Block)WitherStormModBlocks.TAINTED_LOG.get());
/*    */ 
/*    */     
/* 48 */     addAndCopyAllProperties(BlockTags.f_13035_, (Block)WitherStormModBlocks.TAINTED_LEAVES.get());
/* 49 */     addAndCopyAllProperties(BlockTags.f_13095_, (Block)WitherStormModBlocks.TAINTED_DOOR.get());
/* 50 */     addAndCopyAllProperties(BlockTags.f_13102_, (Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get());
/* 51 */     addAndCopyAllProperties(BlockTags.f_13092_, (Block)WitherStormModBlocks.TAINTED_BUTTON.get());
/* 52 */     addAndCopyAllProperties(BlockTags.f_13100_, (Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get());
/* 53 */     addAndCopyAllProperties(BlockTags.f_13096_, (Block)WitherStormModBlocks.TAINTED_STAIRS.get());
/* 54 */     addAndCopyAllProperties(BlockTags.f_13097_, (Block)WitherStormModBlocks.TAINTED_SLAB.get());
/* 55 */     addAndCopyAllProperties(BlockTags.f_13098_, (Block)WitherStormModBlocks.TAINTED_FENCE.get());
/* 56 */     addAndCopyAllProperties(BlockTags.f_13055_, (Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get());
/* 57 */     addAndCopyAllProperties(Tags.Blocks.SANDSTONE, (Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
/* 58 */     addAndCopyAllProperties(Blocks.f_50406_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
/* 59 */     addAndCopyAllProperties(Blocks.f_50263_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
/* 60 */     addAndCopyAllProperties(Blocks.f_50613_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
/* 61 */     addAndCopyAllProperties(Blocks.f_50064_, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
/* 62 */     addAndCopyAllProperties(Blocks.f_50407_, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
/* 63 */     addAndCopyAllProperties(Blocks.f_50471_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
/* 64 */     addAndCopyAllProperties(Blocks.f_50649_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
/* 65 */     addAndCopyAllProperties(Blocks.f_50636_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
/* 66 */     addAndCopyAllProperties(Blocks.f_50394_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
/* 67 */     addAndCopyAllProperties(Blocks.f_50467_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
/* 68 */     addAndCopyAllProperties(Blocks.f_50397_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
/* 69 */     addAndCopyAllProperties(Blocks.f_50606_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
/* 70 */     addAndCopyAllProperties(Blocks.f_50396_, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
/* 71 */     addAndCopyAllProperties(Blocks.f_50468_, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
/* 72 */     addAndCopyAllProperties(Blocks.f_50473_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
/* 73 */     addAndCopyAllProperties(Blocks.f_50644_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
/* 74 */     addAndCopyAllProperties(Blocks.f_50630_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
/* 75 */     addAndCopyAllProperties(Blocks.f_50081_, (Block)WitherStormModBlocks.TAINTED_TORCH.get());
/* 76 */     addAndCopyAllProperties(Blocks.f_50082_, (Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\WitherStormModBlockTaintingRecipeProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */