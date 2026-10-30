package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.world.damagesource.DamageSource;

public interface IMultipartHurtable<T extends net.minecraftforge.entity.PartEntity<?>> {
  boolean hurt(T paramT, DamageSource paramDamageSource, float paramFloat);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\IMultipartHurtable.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */