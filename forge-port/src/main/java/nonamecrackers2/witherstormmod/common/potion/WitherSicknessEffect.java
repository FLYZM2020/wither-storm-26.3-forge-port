package nonamecrackers2.witherstormmod.common.potion;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
import nonamecrackers2.witherstormmod.common.sickness.WitherSicknessTracker;

/** Sickness damage and gradual maximum-health reduction. */
public final class WitherSicknessEffect extends MobEffect {
    private static final Identifier HEALTH_MODIFIER = WitherStormMod.id("wither_sickness_health");

    public WitherSicknessEffect() {
        super(MobEffectCategory.HARMFUL, 8192505);
        addAttributeModifier(Attributes.MAX_HEALTH, HEALTH_MODIFIER, 0.0, AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = 7200 >> amplifier;
        return interval <= 0 || duration % interval == 0;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (WitherSicknessTracker.isBeingCured(entity)) return true;
        float damage = entity.typeHolder().is(WitherStormModEntityTags.HIGH_IMMUNITY) ? 1.0F : 2.0F;
        entity.hurtServer(level, WitherStormModDamageTypes.source(level, WitherStormModDamageTypes.WITHER_SICKNESS), damage);
        AttributeInstance health = entity.getAttribute(Attributes.MAX_HEALTH);
        if (health != null) {
            AttributeModifier existing = health.getModifier(HEALTH_MODIFIER);
            if (existing != null) {
                double value = Math.max(health.getBaseValue() * -1.0 + 1.0, existing.amount() - 0.5);
                health.removeModifier(HEALTH_MODIFIER);
                health.addPermanentModifier(new AttributeModifier(HEALTH_MODIFIER, value, AttributeModifier.Operation.ADD_VALUE));
            }
        }
        return true;
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributes, int amplifier) {
        AttributeInstance health = attributes.getInstance(Attributes.MAX_HEALTH);
        if (health == null) return;
        AttributeModifier existing = health.getModifier(HEALTH_MODIFIER);
        double amount = existing == null ? 0.0 : existing.amount() * (amplifier + 1);
        if (existing != null) health.removeModifier(HEALTH_MODIFIER);
        health.addPermanentModifier(new AttributeModifier(HEALTH_MODIFIER, amount, AttributeModifier.Operation.ADD_VALUE));
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
