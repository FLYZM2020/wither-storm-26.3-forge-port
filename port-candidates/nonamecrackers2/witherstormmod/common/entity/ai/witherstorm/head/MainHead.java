/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class MainHead
/*    */   extends WitherStormHead
/*    */ {
/*    */   public MainHead(WitherStormEntity storm, int headIndex) {
/* 11 */     super(storm, headIndex, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLookPos(Vec3 pos, int steps) {
/* 17 */     if (pos != null) {
/* 18 */       this.storm.getLookControl().setLookAt(pos);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public float getHeadXRot() {
/* 24 */     return this.storm.getXRot();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getHeadXRotO() {
/* 30 */     return this.storm.xRotO;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getHeadYRot() {
/* 36 */     return this.storm.yHeadRot;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getHeadYRotO() {
/* 42 */     return this.storm.yHeadRotO;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHeadXRot(float rot) {
/* 48 */     this.storm.setXRot(rot);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHeadYRot(float rot) {
/* 54 */     this.storm.setYRot(rot);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LivingEntity getTarget() {
/* 60 */     return this.storm.getTarget();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTarget(LivingEntity target) {
/* 66 */     this.storm.setTarget(target);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canShootFlamingSkull() {
/* 72 */     return (super.canShootFlamingSkull() || (this.storm.getPhase() < 4 && getTarget() != null));
/*    */   }
/*    */   
/*    */   public void doHeadLookLogic() {}
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\head\MainHead.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */