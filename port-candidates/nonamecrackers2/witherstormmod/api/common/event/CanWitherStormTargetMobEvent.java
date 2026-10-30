/*    */ package nonamecrackers2.witherstormmod.api.common.event;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ @Cancelable
/*    */ public class CanWitherStormTargetMobEvent
/*    */   extends WitherStormEvent
/*    */ {
/*    */   private final LivingEntity entity;
/*    */   
/*    */   public CanWitherStormTargetMobEvent(WitherStormEntity storm, LivingEntity entity) {
/* 14 */     super(storm);
/* 15 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public LivingEntity getPotentialTarget() {
/* 20 */     return this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\event\CanWitherStormTargetMobEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */