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
/*    */   protected boolean canStillUse(ServerLevel level, LivingEntity entity, long seed) {
/* 25 */     return entity.getBrain().hasMemoryValue((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void start(ServerLevel level, LivingEntity entity, long seed) {
/* 31 */     if (isNearWitherStorm(entity)) {
/*    */       
/* 33 */       Brain<?> brain = entity.getBrain();
/* 34 */       if (!brain.isActive(Activity.PANIC)) {
/*    */         
/* 36 */         brain.eraseMemory(MemoryModuleType.PATH);
/* 37 */         brain.eraseMemory(MemoryModuleType.WALK_TARGET);
/* 38 */         brain.eraseMemory(MemoryModuleType.LOOK_TARGET);
/* 39 */         brain.eraseMemory(MemoryModuleType.BREED_TARGET);
/* 40 */         brain.eraseMemory(MemoryModuleType.INTERACTION_TARGET);
/* 41 */         brain.eraseMemory(MemoryModuleType.DOORS_TO_CLOSE);
/* 42 */         brain.eraseMemory(MemoryModuleType.INTERACTABLE_DOORS);
/* 43 */         brain.eraseMemory(MemoryModuleType.MEETING_POINT);
/* 44 */         brain.eraseMemory(MemoryModuleType.HIDING_PLACE);
/* 45 */         brain.eraseMemory(MemoryModuleType.INTERACTION_TARGET);
/* 46 */         brain.eraseMemory(MemoryModuleType.HOME);
/*    */       } 
/*    */       
/* 49 */       brain.setActiveActivityIfPossible((Activity)WitherStormModActivities.WITHER_STORM_PANIC.get());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isNearWitherStorm(LivingEntity entity) {
/* 55 */     WitherStormEntity storm = entity.getBrain().getMemory((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get()).orElse(null);
/* 56 */     if (storm != null && !storm.isDeadOrPlayingDead()) {
/*    */       
/* 58 */       if (storm.getPhase() < 4) {
/* 59 */         return entity.getBoundingBox().inflate(100.0D).contains(storm.position());
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