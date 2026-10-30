/*    */ package nonamecrackers2.witherstormmod.api.common.event;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ @Cancelable
/*    */ public class WitherStormConsumeEvent
/*    */   extends WitherStormEvent
/*    */ {
/*    */   @Nullable
/*    */   private final Entity consumedEntity;
/*    */   private int consumedAmount;
/*    */   
/*    */   public WitherStormConsumeEvent(WitherStormEntity storm, @Nullable Entity consumedEntity, int consumedAmount) {
/* 17 */     super(storm);
/* 18 */     this.consumedEntity = consumedEntity;
/* 19 */     this.consumedAmount = consumedAmount;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity getConsumedEntity() {
/* 24 */     return this.consumedEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getConsumedAmount() {
/* 29 */     return this.consumedAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConsumedAmount(int amount) {
/* 34 */     this.consumedAmount = amount;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\event\WitherStormConsumeEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */