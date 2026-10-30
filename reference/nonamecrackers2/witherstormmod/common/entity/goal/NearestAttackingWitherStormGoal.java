/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class NearestAttackingWitherStormGoal
/*    */   extends NearestAttackableTargetGoal<WitherStormEntity> {
/*    */   public NearestAttackingWitherStormGoal(Mob mob, int randomInterval) {
/* 15 */     super(mob, WitherStormEntity.class, randomInterval, true, false, null);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   protected AABB m_7255_(double range) {
/* 21 */     return this.f_26135_.m_20191_().m_82377_(range, range * 2.0D, range);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_26073_() {
/* 27 */     super.m_26073_();
/* 28 */     LivingEntity livingEntity = this.f_26050_; if (livingEntity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)livingEntity;
/*    */       
/* 30 */       boolean flag = true;
/* 31 */       if (storm.getPhase() > 3 && storm.m_20270_((Entity)this.f_26135_) > 30.0D)
/*    */       {
/* 33 */         if (((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.f_26135_, (LivingEntity)storm, 4.0D).getFirst()).booleanValue() && !this.f_26135_.m_20096_())
/* 34 */           flag = false; 
/*    */       }
/* 36 */       if (flag) {
/* 37 */         this.f_26050_ = null;
/*    */       } }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   protected double m_7623_() {
/* 44 */     return 100.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\NearestAttackingWitherStormGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */