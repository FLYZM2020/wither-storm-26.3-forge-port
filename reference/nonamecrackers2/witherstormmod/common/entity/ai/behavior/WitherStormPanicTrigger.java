/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.behavior;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.Brain;
/*    */ import net.minecraft.world.entity.ai.behavior.Behavior;
/*    */ import net.minecraft.world.entity.ai.memory.MemoryModuleType;
/*    */ import net.minecraft.world.entity.schedule.Activity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModActivities;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMemoryTypes;
/*    */ 
/*    */ public class WitherStormPanicTrigger
/*    */   extends Behavior<LivingEntity>
/*    */ {
/*    */   public WitherStormPanicTrigger() {
/* 19 */     super((Map)ImmutableMap.of());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean m_6737_(ServerLevel level, LivingEntity entity, long seed) {
/* 25 */     return entity.m_6274_().m_21874_((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_6735_(ServerLevel level, LivingEntity entity, long seed) {
/* 31 */     if (isNearWitherStorm(entity)) {
/*    */       
/* 33 */       Brain<?> brain = entity.m_6274_();
/* 34 */       if (!brain.m_21954_(Activity.f_37984_)) {
/*    */         
/* 36 */         brain.m_21936_(MemoryModuleType.f_26377_);
/* 37 */         brain.m_21936_(MemoryModuleType.f_26370_);
/* 38 */         brain.m_21936_(MemoryModuleType.f_26371_);
/* 39 */         brain.m_21936_(MemoryModuleType.f_26375_);
/* 40 */         brain.m_21936_(MemoryModuleType.f_26374_);
/* 41 */         brain.m_21936_(MemoryModuleType.f_26379_);
/* 42 */         brain.m_21936_(MemoryModuleType.f_26378_);
/* 43 */         brain.m_21936_(MemoryModuleType.f_26362_);
/* 44 */         brain.m_21936_(MemoryModuleType.f_26324_);
/* 45 */         brain.m_21936_(MemoryModuleType.f_26374_);
/* 46 */         brain.m_21936_(MemoryModuleType.f_26359_);
/*    */       } 
/*    */       
/* 49 */       brain.m_21889_((Activity)WitherStormModActivities.WITHER_STORM_PANIC.get());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isNearWitherStorm(LivingEntity entity) {
/* 55 */     WitherStormEntity storm = entity.m_6274_().m_21952_((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get()).orElse(null);
/* 56 */     if (storm != null && !storm.isDeadOrPlayingDead()) {
/*    */       
/* 58 */       if (storm.getPhase() < 4) {
/* 59 */         return entity.m_20191_().m_82400_(100.0D).m_82390_(storm.m_20182_());
/*    */       }
/* 61 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 65 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\behavior\WitherStormPanicTrigger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */