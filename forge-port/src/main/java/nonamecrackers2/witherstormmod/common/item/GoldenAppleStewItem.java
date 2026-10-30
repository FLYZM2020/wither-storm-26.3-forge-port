package nonamecrackers2.witherstormmod.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.sickness.WitherSicknessTracker;

/** Starts the delayed cure when consumed or applied to an infected entity. */
public final class GoldenAppleStewItem extends Item {
    public GoldenAppleStewItem(Properties properties) { super(properties); }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target.level().isClientSide()) return InteractionResult.PASS;
        if (!WitherSicknessTracker.beginCure(target)) return InteractionResult.PASS;
        target.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE,
                SoundSource.NEUTRAL, 1.0F, 1.0F);
        if (!player.getAbilities().instabuild) stack.shrink(1);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && WitherSicknessTracker.beginCure(entity)) {
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE,
                    SoundSource.NEUTRAL, 1.0F, 1.0F);
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
