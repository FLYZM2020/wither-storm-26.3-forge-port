package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

@FunctionalInterface
public interface ProjectileFactory {
  AbstractHurtingProjectile make(Level paramLevel, LivingEntity paramLivingEntity, double paramDouble1, double paramDouble2, double paramDouble3);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\FireballSpell$ProjectileFactory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */