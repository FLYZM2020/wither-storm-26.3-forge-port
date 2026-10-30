package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Phantom.class})
public interface IMixinPhantom {
  @Accessor
  Vec3 getMoveTargetPoint();
  
  @Accessor
  void setMoveTargetPoint(Vec3 paramVec3);
  
  @Accessor
  BlockPos getAnchorPoint();
  
  @Accessor
  void setAnchorPoint(BlockPos paramBlockPos);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinPhantom.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */