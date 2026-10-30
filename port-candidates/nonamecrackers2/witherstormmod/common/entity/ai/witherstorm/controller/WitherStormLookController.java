/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.control.LookControl;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class WitherStormLookController
/*    */   extends LookControl
/*    */ {
/*    */   private final WitherStormEntity storm;
/*    */   
/*    */   public WitherStormLookController(WitherStormEntity storm) {
/* 16 */     super((Mob)storm);
/* 17 */     this.storm = storm;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 23 */     if (this.lookAtCooldown > 0) {
/*    */       
/* 25 */       this.lookAtCooldown--;
/* 26 */       getYRotD().ifPresent(rot -> this.mob.yHeadRot = rotateTowards(this.mob.yHeadRot, rot.floatValue(), this.yMaxRotSpeed));
/*    */ 
/*    */       
/* 29 */       getXRotD().ifPresent(rot -> this.mob.setXRot(rotateTowards(this.mob.getXRot(), rot.floatValue(), this.yMaxRotSpeed)));
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 35 */       this.mob.yHeadRot = rotateTowards(this.mob.yHeadRot, this.mob.yBodyRot, 10.0F);
/*    */     } 
/*    */     
/* 38 */     clampHeadRotationToBody();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLookAt(double wantedX, double wantedY, double wantedZ, float yMaxSpeed, float xMaxRotAngle) {
/* 44 */     this.wantedX = wantedX;
/* 45 */     this.wantedY = wantedY;
/* 46 */     this.wantedZ = wantedZ;
/* 47 */     this.yMaxRotSpeed = yMaxSpeed;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     this.lookAtCooldown = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Optional<Float> getXRotD() {
/* 58 */     if (this.storm.getPhase() > 3) {
/*    */       
/* 60 */       Vec3 pos = this.storm.getHeadPos(0);
/* 61 */       double d0 = this.wantedX - pos.x;
/* 62 */       double d1 = this.wantedY - pos.y;
/* 63 */       double d2 = this.wantedZ - pos.z;
/* 64 */       double d3 = Math.sqrt(d0 * d0 + d2 * d2);
/* 65 */       return Optional.of(Float.valueOf((float)-(Mth.atan2(d1, d3) * 57.2957763671875D)));
/*    */     } 
/*    */ 
/*    */     
/* 69 */     return super.getXRotD();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Optional<Float> getYRotD() {
/* 76 */     if (this.storm.getPhase() > 3) {
/*    */       
/* 78 */       Vec3 pos = this.storm.getHeadPos(0);
/* 79 */       double d0 = this.wantedX - pos.x;
/* 80 */       double d1 = this.wantedZ - pos.z;
/* 81 */       return Optional.of(Float.valueOf((float)(Mth.atan2(d1, d0) * 57.2957763671875D) - 90.0F));
/*    */     } 
/*    */ 
/*    */     
/* 85 */     return super.getYRotD();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean resetXRotOnTick() {
/* 92 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\controller\WitherStormLookController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */