/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormNearestDistractionGoal
/*    */   extends NearestDistractionGoal<WitherStormEntity>
/*    */ {
/*    */   public WitherStormNearestDistractionGoal(WitherStormEntity entity, int head, Predicate<Entity> condition, int interval) {
/* 15 */     super(entity, head, condition, interval);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected AABB getSearchArea() {
/* 21 */     return this.entity.getSearchBox();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Vec3 getTargetPos() {
/* 27 */     if (this.target instanceof net.minecraft.world.entity.projectile.FireworkRocketEntity) {
/* 28 */       return super.getTargetPos().m_82520_(0.0D, 10.0D, 0.0D);
/*    */     }
/* 30 */     return super.getTargetPos();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\WitherStormNearestDistractionGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */