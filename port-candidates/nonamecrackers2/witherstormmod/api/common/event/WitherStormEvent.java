/*    */ package nonamecrackers2.witherstormmod.api.common.event;
/*    */ 
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ public abstract class WitherStormEvent
/*    */   extends Event
/*    */ {
/*    */   private final WitherStormEntity storm;
/*    */   
/*    */   public WitherStormEvent(WitherStormEntity storm) {
/* 12 */     this.storm = storm;
/*    */   }
/*    */ 
/*    */   
/*    */   public WitherStormEntity getEntity() {
/* 17 */     return this.storm;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\event\WitherStormEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */