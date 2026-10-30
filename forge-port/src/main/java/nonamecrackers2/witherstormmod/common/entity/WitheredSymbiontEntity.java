package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.level.Level;

/** Temporary melee stand-in; original symbiont model, spells and boss behavior remain unported. */
public final class WitheredSymbiontEntity extends WitherSkeleton {
    public WitheredSymbiontEntity(EntityType<? extends WitherSkeleton> type, Level level) {
        super(type, level);
    }
}
