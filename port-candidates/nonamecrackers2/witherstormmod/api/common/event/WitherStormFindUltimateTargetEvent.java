/*    */ package nonamecrackers2.witherstormmod.api.common.event;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public class WitherStormFindUltimateTargetEvent
/*    */   extends WitherStormEvent
/*    */ {
/*    */   @Nullable
/*    */   private LivingEntity target;
/*    */   
/*    */   public WitherStormFindUltimateTargetEvent(WitherStormEntity storm, @Nullable LivingEntity originalTarget) {
/* 14 */     super(storm);
/* 15 */     this.target = originalTarget;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getOriginalUltimateTarget() {
/* 20 */     return this.target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUltimateTarget(@Nullable LivingEntity target) {
/* 25 */     this.target = target;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\event\WitherStormFindUltimateTargetEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */