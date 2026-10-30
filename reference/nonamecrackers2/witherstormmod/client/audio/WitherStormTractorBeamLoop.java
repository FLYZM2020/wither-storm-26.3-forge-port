/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ 
/*    */ public class WitherStormTractorBeamLoop
/*    */   extends TractorBeamLoop<WitherStormEntity> {
/*    */   private final int head;
/*    */   
/*    */   public WitherStormTractorBeamLoop(WitherStormEntity entity, int head) {
/* 13 */     super(entity);
/* 14 */     this.head = head;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHead() {
/* 19 */     return this.head;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldStop(double distance) {
/* 25 */     return (this.entity.isDeadOrPlayingDead() || !this.entity.m_6084_() || distance > TractorBeamLoop.DISTANCE_REQUIRED || !this.entity.tractorBeamActive(this.head));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void calculateVolume() {
/* 31 */     if (this.head == 0 || (this.head > 0 && !this.entity.areOtherHeadsDisabled())) {
/* 32 */       this.f_119573_ = Math.max(0.0F, 0.3F - getDistance(new Vec3(this.f_119575_, this.f_119576_, this.f_119577_)) / 60.0F);
/*    */     } else {
/* 34 */       this.f_119573_ = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected Vec3 calculateClosestPoint() {
/* 40 */     return TractorBeamHelper.calculateClosestPoint(this.player.m_20182_(), (LivingEntity)this.entity, this.head);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\WitherStormTractorBeamLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */