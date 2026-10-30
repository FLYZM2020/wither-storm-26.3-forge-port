/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class WitherStormLookRandomlyGoal
/*    */   extends YAffectedLookRandomlyGoal {
/*    */   private final WitherStormEntity storm;
/*    */   private final int head;
/*    */   
/*    */   public WitherStormLookRandomlyGoal(WitherStormEntity entity, int head, int Xmin, int Xmax, int Ymin, int Ymax, int lookTime) {
/* 13 */     super((Mob)entity, Xmin, Xmax, Ymin, Ymax, lookTime);
/* 14 */     this.storm = entity;
/* 15 */     this.head = head;
/*    */   }
/*    */ 
/*    */   
/*    */   public WitherStormLookRandomlyGoal(WitherStormEntity entity, int head, int lookTime) {
/* 20 */     this(entity, head, -140, -30, -80, 80, lookTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_8036_() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vec3 getPos() {
/* 31 */     return this.storm.getHeadPos(this.head).m_82520_(this.relX, this.relY, this.relZ);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getRandomLookTime() {
/* 37 */     if (this.storm.getPhase() < 4) {
/* 38 */       return 20;
/*    */     }
/* 40 */     return this.storm.isHeadInjured(this.head) ? 20 : super.getRandomLookTime();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void look() {
/* 46 */     int steps = (this.storm.isHeadInjured(this.head) || this.storm.getPhase() < 4) ? 3 : 50;
/* 47 */     this.storm.setLookAt(this.head, getPos(), steps);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\WitherStormLookRandomlyGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */