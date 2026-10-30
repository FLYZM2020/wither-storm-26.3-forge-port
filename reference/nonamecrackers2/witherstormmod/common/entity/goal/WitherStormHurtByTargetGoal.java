/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.PathfinderMob;
/*    */ import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
/*    */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*    */ 
/*    */ 
/*    */ public class WitherStormHurtByTargetGoal
/*    */   extends HurtByTargetGoal
/*    */ {
/*    */   private final Predicate<LivingEntity> entitySelector;
/*    */   
/*    */   public WitherStormHurtByTargetGoal(PathfinderMob mob, Predicate<LivingEntity> entitySelector) {
/* 16 */     super(mob, new Class[0]);
/* 17 */     this.entitySelector = entitySelector;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean m_26150_(LivingEntity entity, TargetingConditions conditions) {
/* 23 */     return (this.entitySelector.test(entity) && super.m_26150_(entity, conditions));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\WitherStormHurtByTargetGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */