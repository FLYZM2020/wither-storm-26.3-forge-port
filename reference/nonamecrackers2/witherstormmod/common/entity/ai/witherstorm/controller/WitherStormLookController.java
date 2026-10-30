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
/*    */   public void m_8128_() {
/* 23 */     if (this.f_186068_ > 0) {
/*    */       
/* 25 */       this.f_186068_--;
/* 26 */       m_180896_().ifPresent(rot -> this.f_24937_.f_20885_ = m_24956_(this.f_24937_.f_20885_, rot.floatValue(), this.f_24938_));
/*    */ 
/*    */       
/* 29 */       m_180897_().ifPresent(rot -> this.f_24937_.m_146926_(m_24956_(this.f_24937_.m_146909_(), rot.floatValue(), this.f_24938_)));
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 35 */       this.f_24937_.f_20885_ = m_24956_(this.f_24937_.f_20885_, this.f_24937_.f_20883_, 10.0F);
/*    */     } 
/*    */     
/* 38 */     m_142586_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_24950_(double wantedX, double wantedY, double wantedZ, float yMaxSpeed, float xMaxRotAngle) {
/* 44 */     this.f_24941_ = wantedX;
/* 45 */     this.f_24942_ = wantedY;
/* 46 */     this.f_24943_ = wantedZ;
/* 47 */     this.f_24938_ = yMaxSpeed;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     this.f_186068_ = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Optional<Float> m_180897_() {
/* 58 */     if (this.storm.getPhase() > 3) {
/*    */       
/* 60 */       Vec3 pos = this.storm.getHeadPos(0);
/* 61 */       double d0 = this.f_24941_ - pos.f_82479_;
/* 62 */       double d1 = this.f_24942_ - pos.f_82480_;
/* 63 */       double d2 = this.f_24943_ - pos.f_82481_;
/* 64 */       double d3 = Math.sqrt(d0 * d0 + d2 * d2);
/* 65 */       return Optional.of(Float.valueOf((float)-(Mth.m_14136_(d1, d3) * 57.2957763671875D)));
/*    */     } 
/*    */ 
/*    */     
/* 69 */     return super.m_180897_();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Optional<Float> m_180896_() {
/* 76 */     if (this.storm.getPhase() > 3) {
/*    */       
/* 78 */       Vec3 pos = this.storm.getHeadPos(0);
/* 79 */       double d0 = this.f_24941_ - pos.f_82479_;
/* 80 */       double d1 = this.f_24943_ - pos.f_82481_;
/* 81 */       return Optional.of(Float.valueOf((float)(Mth.m_14136_(d1, d0) * 57.2957763671875D) - 90.0F));
/*    */     } 
/*    */ 
/*    */     
/* 85 */     return super.m_180896_();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean m_8106_() {
/* 92 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\controller\WitherStormLookController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */