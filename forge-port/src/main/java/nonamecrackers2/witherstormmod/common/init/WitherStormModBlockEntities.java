package nonamecrackers2.witherstormmod.common.init;

import java.util.Set;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;

public final class WitherStormModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WitherStormMod.MOD_ID);
    public static final RegistryObject<BlockEntityType<FormidibombBlockEntity>> FORMIDIBOMB = TYPES.register("formidibomb", () ->
            new BlockEntityType<>(FormidibombBlockEntity::new, Set.of(WitherStormModBlocks.FORMIDIBOMB.get())));
    private WitherStormModBlockEntities() {}
}
