package nonamecrackers2.witherstormmod.mixin;

import com.mojang.datafixers.util.Pair;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.ExpirableValue;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Brain.class})
public interface IMixinBrain<E extends net.minecraft.world.entity.LivingEntity> {
  @Accessor
  Map<Activity, Set<Pair<MemoryModuleType<?>, MemoryStatus>>> getActivityRequirements();
  
  @Accessor
  Map<Activity, Set<MemoryModuleType<?>>> getActivityMemoriesToEraseWhenStopped();
  
  @Accessor
  Map<MemoryModuleType<?>, Optional<? extends ExpirableValue<?>>> getMemories();
  
  @Accessor
  Map<SensorType<? extends Sensor<? super E>>, Sensor<? super E>> getSensors();
  
  @Accessor
  Set<Activity> getCoreActivities();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinBrain.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */