package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.block.FormidibombBlock;
import nonamecrackers2.witherstormmod.common.block.SuperTntBlock;

public final class WitherStormModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WitherStormMod.MOD_ID);
    public static final RegistryObject<Block> SUPER_TNT = BLOCKS.register("super_tnt", () ->
            new SuperTntBlock(BlockBehaviour.Properties.of().setId(BLOCKS.key("super_tnt")).instabreak()
                    .sound(SoundType.GRASS).pushReaction(PushReaction.IMMOVEABLE)));
    public static final RegistryObject<Block> FORMIDIBOMB = BLOCKS.register("formidibomb", () ->
            new FormidibombBlock(BlockBehaviour.Properties.of().setId(BLOCKS.key("formidibomb"))
                    .strength(0.8F).sound(SoundType.GRASS).lightLevel(state -> 7)));
    private WitherStormModBlocks() {}
}
