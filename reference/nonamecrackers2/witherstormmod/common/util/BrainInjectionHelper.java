/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.Brain;
/*    */ import net.minecraft.world.entity.ai.behavior.Behavior;
/*    */ import net.minecraft.world.entity.ai.behavior.DoNothing;
/*    */ import net.minecraft.world.entity.ai.behavior.RandomStroll;
/*    */ import net.minecraft.world.entity.ai.behavior.RunOne;
/*    */ import net.minecraft.world.entity.ai.behavior.SetEntityLookTarget;
/*    */ import net.minecraft.world.entity.ai.behavior.SetWalkTargetAwayFrom;
/*    */ import net.minecraft.world.entity.ai.memory.MemoryModuleType;
/*    */ import net.minecraft.world.entity.ai.memory.MemoryStatus;
/*    */ import net.minecraft.world.entity.ai.sensing.Sensor;
/*    */ import net.minecraft.world.entity.ai.sensing.SensorType;
/*    */ import net.minecraft.world.entity.npc.Villager;
/*    */ import net.minecraft.world.entity.schedule.Activity;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.behavior.VillagerCalmDownFromWitherStorm;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.behavior.WitherStormPanicTrigger;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModActivities;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMemoryTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSensorTypes;
/*    */ import nonamecrackers2.witherstormmod.mixin.IMixinBrain;
/*    */ 
/*    */ public class BrainInjectionHelper
/*    */ {
/*    */   public static void inject(LivingEntity entity) {
/* 35 */     if (((Boolean)WitherStormModConfig.COMMON.injectCustomAiBehavior.get()).booleanValue())
/*    */     {
/* 37 */       if (entity.m_6095_().equals(EntityType.f_20492_)) {
/*    */         
/* 39 */         Villager villager = (Villager)entity;
/* 40 */         Brain<Villager> brain = villager.m_6274_();
/* 41 */         if (brain != null) {
/*    */           
/* 43 */           addMemoryType(villager, (MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get());
/* 44 */           addSensorType(villager, (SensorType<? extends Sensor<? super Villager>>)WitherStormModSensorTypes.WITHER_STORM_SENSOR.get());
/* 45 */           addToActivity(villager, Activity.f_37978_, ImmutableList.of(Pair.of(Integer.valueOf(-1), new WitherStormPanicTrigger())));
/* 46 */           brain.m_21895_((Activity)WitherStormModActivities.WITHER_STORM_PANIC.get(), 0, ImmutableList.of(new VillagerCalmDownFromWitherStorm(), 
/*    */                 
/* 48 */                 SetWalkTargetAwayFrom.m_257370_((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get(), 0.75F, 300, true), 
/* 49 */                 RandomStroll.m_257965_(1.0F), new RunOne(
/* 50 */                   (List)ImmutableList.of(
/* 51 */                     Pair.of(SetEntityLookTarget.m_258096_(EntityType.f_20492_, 8.0F), Integer.valueOf(2)), 
/* 52 */                     Pair.of(SetEntityLookTarget.m_258096_(EntityType.f_20532_, 8.0F), Integer.valueOf(2)), 
/* 53 */                     Pair.of(new DoNothing(30, 60), Integer.valueOf(8))))), (MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM
/*    */               
/* 55 */               .get());
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static <E extends LivingEntity> void addToActivity(E entity, Activity activity, ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> behaviors) {
/* 65 */     Brain<E> brain = entity.m_6274_();
/* 66 */     IMixinBrain<E> mixinBrain = (IMixinBrain<E>)brain;
/* 67 */     Set<Pair<MemoryModuleType<?>, MemoryStatus>> previousRequirements = (Set<Pair<MemoryModuleType<?>, MemoryStatus>>)mixinBrain.getActivityRequirements().get(activity);
/* 68 */     Set<MemoryModuleType<?>> previousMemoriesToErase = (Set<MemoryModuleType<?>>)mixinBrain.getActivityMemoriesToEraseWhenStopped().getOrDefault(activity, Sets.newHashSet());
/* 69 */     brain.m_21907_(activity, behaviors, previousRequirements, previousMemoriesToErase);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static <E extends LivingEntity> IMixinBrain<E> toMixinBrain(E entity) {
/* 75 */     return (IMixinBrain<E>)entity.m_6274_();
/*    */   }
/*    */ 
/*    */   
/*    */   private static <E extends LivingEntity> void addMemoryType(E entity, MemoryModuleType<?> type) {
/* 80 */     IMixinBrain<E> brain = toMixinBrain(entity);
/* 81 */     brain.getMemories().put(type, Optional.empty());
/*    */   }
/*    */ 
/*    */   
/*    */   private static <E extends LivingEntity> void addSensorType(E entity, SensorType<? extends Sensor<? super E>> type) {
/* 86 */     IMixinBrain<E> brain = toMixinBrain(entity);
/* 87 */     Sensor<? super E> sensor = type.m_26827_();
/* 88 */     brain.getSensors().put(type, sensor);
/* 89 */     for (MemoryModuleType<?> memory : (Iterable<MemoryModuleType<?>>)sensor.m_7163_())
/* 90 */       brain.getMemories().put(memory, Optional.empty()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\BrainInjectionHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */