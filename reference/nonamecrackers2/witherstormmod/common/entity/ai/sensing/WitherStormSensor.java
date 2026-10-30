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
/*    */   protected void m_5578_(ServerLevel level, LivingEntity entity) {
/* 23 */     AABB box = entity.m_20191_().m_82400_(300.0D);
/* 24 */     List<WitherStormEntity> storms = level.m_6443_(WitherStormEntity.class, box, storm -> (storm != entity && storm.m_6084_()));
/* 25 */     Objects.requireNonNull(entity); storms.sort(Comparator.comparingDouble(entity::m_20280_));
/* 26 */     Optional<WitherStormEntity> storm = storms.stream().findFirst();
/* 27 */     entity.m_6274_().m_21886_((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get(), storm);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<MemoryModuleType<?>> m_7163_() {
/* 33 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\sensing\WitherStormSensor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */