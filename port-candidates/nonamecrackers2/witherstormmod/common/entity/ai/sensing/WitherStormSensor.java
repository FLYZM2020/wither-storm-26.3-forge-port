/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.sensing;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.memory.MemoryModuleType;
/*    */ import net.minecraft.world.entity.ai.sensing.Sensor;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMemoryTypes;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormSensor
/*    */   extends Sensor<LivingEntity>
/*    */ {
/*    */   protected void doTick(ServerLevel level, LivingEntity entity) {
/* 23 */     AABB box = entity.getBoundingBox().inflate(300.0D);
/* 24 */     List<WitherStormEntity> storms = level.getEntitiesOfClass(WitherStormEntity.class, box, storm -> (storm != entity && storm.isAlive()));
/* 25 */     Objects.requireNonNull(entity); storms.sort(Comparator.comparingDouble(entity::distanceToSqr));
/* 26 */     Optional<WitherStormEntity> storm = storms.stream().findFirst();
/* 27 */     entity.getBrain().setMemory((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get(), storm);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<MemoryModuleType<?>> requires() {
/* 33 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\sensing\WitherStormSensor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */