package nonamecrackers2.witherstormmod.mixin;

import javax.annotation.Nullable;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({ShulkerBullet.class})
public interface IMixinShulkerBullet {
  @Accessor
  void setFinalTarget(Entity paramEntity);
  
  @Accessor
  void setCurrentMoveDirection(Direction paramDirection);
  
  @Invoker
  void callSelectNextMoveDirection(@Nullable Direction.Axis paramAxis);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinShulkerBullet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */