package nonamecrackers2.witherstormmod.common.init;

import java.util.List;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.item.GoldenAppleStewItem;
import nonamecrackers2.witherstormmod.common.item.FormidibombItem;

public final class WitherStormModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WitherStormMod.MOD_ID);
    private static final FoodProperties STEW_FOOD = new FoodProperties.Builder().nutrition(5).saturationModifier(1.0F).alwaysEdible().build();
    private static final Consumable STEW_CONSUMABLE = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
            new MobEffectInstance(MobEffects.ABSORPTION, 2600, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 200, 0)))).build();
    public static final RegistryObject<Item> GOLDEN_APPLE_STEW = ITEMS.register("golden_apple_stew", () ->
            new GoldenAppleStewItem(new Item.Properties().setId(ITEMS.key("golden_apple_stew"))
                    .stacksTo(1).rarity(Rarity.RARE).food(STEW_FOOD, STEW_CONSUMABLE).usingConvertsTo(Items.BOWL)));
    public static final RegistryObject<Item> COMMAND_BLOCK_BOOK = ITEMS.register("command_block_book", () ->
            new Item(new Item.Properties().setId(ITEMS.key("command_block_book")).stacksTo(1).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> SUPER_TNT = ITEMS.register("super_tnt", () ->
            new BlockItem(WitherStormModBlocks.SUPER_TNT.get(), new Item.Properties().setId(ITEMS.key("super_tnt"))
                    .useBlockDescriptionPrefix().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> FORMIDIBOMB = ITEMS.register("formidibomb", () ->
            new FormidibombItem(WitherStormModBlocks.FORMIDIBOMB.get(), new Item.Properties().setId(ITEMS.key("formidibomb"))
                    .useBlockDescriptionPrefix().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    private WitherStormModItems() {}
}
