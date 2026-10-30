/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ 
/*    */ 
/*    */ public class YAffectedLookRandomlyGoal
/*    */   extends Goal
/*    */ {
/*    */   private final Mob entity;
/*    */   private final int randomLookTime;
/*    */   private final int Xmin;
/*    */   private final int Xmax;
/*    */   private final int Ymin;
/*    */   private final int Ymax;
/*    */   protected double relX;
/*    */   protected double relY;
/*    */   protected double relZ;
/*    */   protected int lookTime;
/*    */   
/*    */   public YAffectedLookRandomlyGoal(Mob entity) {
/* 25 */     this(entity, -140, -30, -80, 80, 20);
/*    */   }
/*    */ 
/*    */   
/*    */   public YAffectedLookRandomlyGoal(Mob entity, int Xmin, int Xmax, int Ymin, int Ymax, int lookTime) {
/* 30 */     this.entity = entity;
/* 31 */     m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
/* 32 */     this.Xmin = Xmin;
/* 33 */     this.Xmax = Xmax;
/* 34 */     this.Ymin = Ymin;
/* 35 */     this.Ymax = Ymax;
/* 36 */     this.randomLookTime = lookTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8036_() {
/* 42 */     return (this.entity.m_217043_().m_188501_() < 0.02F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8045_() {
/* 48 */     return (this.lookTime >= 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_8056_() {
/* 54 */     double xRot = (Mth.m_14045_(-this.entity.m_217043_().m_188503_(180), this.Xmin, this.Xmax) * 0.017453292F);
/* 55 */     double yRot = ((Mth.m_14177_(this.entity.f_20883_) + 90.0F + Mth.m_14045_(this.entity.m_217043_().m_188503_(360) - 180, this.Ymin, this.Ymax)) * 0.017453292F);
/* 56 */     this.relX = Math.cos(yRot) * 30.0D;
/* 57 */     this.relY = Math.sin(xRot) * 30.0D;
/* 58 */     this.relZ = Math.sin(yRot) * 30.0D;
/* 59 */     this.lookTime = getRandomLookTime() + this.entity.m_217043_().m_188503_(20);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getRandomLookTime() {
/* 64 */     return this.randomLookTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_8037_() {
/* 70 */     this.lookTime--;
/* 71 */     look();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void look() {
/* 76 */     this.entity.m_21563_().m_24964_(getPos());
/*    */   }
/*    */ 
/*    */   
/*    */   public Vec3 getPos() {
/* 81 */     return new Vec3(this.entity.m_20185_() + this.relX, this.entity.m_20188_() + this.relY, this.entity.m_20189_() + this.relZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\YAffectedLookRandomlyGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */