/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*    */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ 
/*    */ 
/*    */ public class WitherStormPriorityTargetingGoal
/*    */   extends WitherStormTargetingGoal
/*    */ {
/*    */   public WitherStormPriorityTargetingGoal(WitherStormEntity entity, Predicate<LivingEntity> mobSelector, int headIndex) {
/* 23 */     super(entity, mobSelector.and(e -> e.m_6095_().m_204039_(WitherStormModEntityTags.FAVOURABLE_MOBS)), headIndex);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8036_() {
/* 29 */     return (((Boolean)WitherStormModConfig.SERVER.specialTargetingBias.get()).booleanValue() && this.storm.getPlayingJukeboxes().isEmpty() && this.storm.getTarget(this.headIndex) == null && this.storm.m_217043_().m_188503_(100) <= ((Integer)WitherStormModConfig.SERVER.specialTargetingBiasChance.get()).intValue() && super.m_8036_());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void findApplicableTarget() {
/* 35 */     double range = (this.storm.getPhase() > 3) ? this.storm.m_21133_(Attributes.f_22277_) : this.storm.m_21133_((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get());
/* 36 */     List<LivingEntity> nearbyEntities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.storm.m_9236_(), LivingEntity.class, getTargetSearchArea(range));
/* 37 */     List<LivingEntity> targetableEntities = Lists.newArrayList();
/* 38 */     for (LivingEntity entity : nearbyEntities) {
/*    */       
/* 40 */       if (this.storm.targetApplicable(entity, this.headIndex, this.targetConditions)) {
/* 41 */         targetableEntities.add(entity);
/*    */       }
/*    */     } 
/* 44 */     double d0 = -1.0D;
/* 45 */     LivingEntity t = null;
/* 46 */     for (LivingEntity t1 : targetableEntities) {
/*    */       
/* 48 */       double d1 = t1.m_20275_(this.storm.m_20185_(), this.storm.m_20188_(), this.storm.m_20189_());
/* 49 */       if (d0 == -1.0D || d1 < d0) {
/*    */         
/* 51 */         d0 = d1;
/* 52 */         t = t1;
/*    */       } 
/*    */     } 
/*    */     
/* 56 */     this.target = t;
/* 57 */     if (this.target != null)
/* 58 */       this.target.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(WitherSicknessTracker::countContact); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\WitherStormPriorityTargetingGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */