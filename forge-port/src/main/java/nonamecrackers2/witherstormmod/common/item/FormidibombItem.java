package nonamecrackers2.witherstormmod.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import nonamecrackers2.witherstormmod.common.config.StormConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import org.jspecify.annotations.Nullable;

/** Crafted bomb fuse stored on the item, including when it is dropped. */
public final class FormidibombItem extends BlockItem {
    private static final String FUSE = "witherstormmod:Fuse", START = "witherstormmod:StartFuse";
    public FormidibombItem(Block block, Properties properties) { super(block, properties); }

    @Override
    public void onCraftedBy(ItemStack stack, Player player) {
        super.onCraftedBy(stack, player);
        int duration = StormConfig.FORMIDIBOMB_CRAFT_FUSE.get();
        setFuse(stack, duration, duration);
    }

    public static int getFuse(ItemStack stack) { return data(stack).getIntOr(FUSE, 0); }
    public static int getStartFuse(ItemStack stack) { return data(stack).getIntOr(START, 0); }
    private static CompoundTag data(ItemStack stack) { return stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag(); }
    public static void setFuse(ItemStack stack, int fuse, int start) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> { tag.putInt(FUSE, fuse); tag.putInt(START, start); });
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        tickFuse(stack, level, owner, owner.blockPosition());
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            tickFuse(stack, level, null, entity.blockPosition());
            if (stack.isEmpty()) { entity.discard(); return true; }
        }
        return false;
    }

    private void tickFuse(ItemStack stack, ServerLevel level, @Nullable Entity owner, BlockPos pos) {
        int start = getStartFuse(stack);
        if (start <= 0 || !StormConfig.FORMIDIBOMB_AUTO_FUSE.get()) return;
        int fuse = Math.max(0, getFuse(stack) - 1);
        setFuse(stack, fuse, start);
        if (fuse <= start / 4) {
            LivingEntity igniter = owner instanceof LivingEntity living ? living : null;
            FormidibombEntity bomb = FormidibombEntity.create(level, pos, WitherStormModBlocks.FORMIDIBOMB.get().defaultBlockState(), igniter, Math.max(1, fuse));
            if (level.addFreshEntity(bomb)) {
                stack.shrink(1);
                level.playSound(null, bomb.getX(), bomb.getY(), bomb.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    @Override public boolean isBarVisible(ItemStack stack) { return getStartFuse(stack) > 0; }
    @Override public int getBarWidth(ItemStack stack) { int start = getStartFuse(stack); return start <= 0 ? 0 : Math.clamp(Math.round(13.0F * getFuse(stack) / start), 0, 13); }
    @Override public int getBarColor(ItemStack stack) { return 0xC20F00; }
}
