/*    */ package nonamecrackers2.witherstormmod.common.entity.section;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ public class FallingSection
/*    */   extends Section
/*    */ {
/*    */   public FallingSection(WitherStormEntity entity, float height, float width, double x, double y, double z, Predicate<WitherStormEntity> isActive) {
/* 12 */     super(entity, height, width, x, y, z, isActive);
/*    */   }
/*    */ 
/*    */   
/*    */   public FallingSection(WitherStormEntity entity, float height, float width, double x, double y, double z, int phase) {
/* 17 */     super(entity, height, width, x, y, z, phase);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vec3 getOffset() {
/* 23 */     double sin = Math.sin(Math.toRadians(this.owner.xBodyRot));
/* 24 */     double x = this.offset.f_82479_ * sin;
/* 25 */     double y = this.offset.f_82480_;
/* 26 */     double z = this.offset.f_82481_ * sin;
/* 27 */     return new Vec3(x, y, z);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\section\FallingSection.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */