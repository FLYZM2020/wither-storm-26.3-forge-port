/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.control.LookControl;
/*    */ 
/*    */ 
/*    */ public class ConditionalLookController<T extends Mob>
/*    */   extends LookControl
/*    */ {
/*    */   private final T mob;
/*    */   private final Predicate<T> resetXRot;
/*    */   
/*    */   public ConditionalLookController(T mob, Predicate<T> resetXRot) {
/* 15 */     super((Mob)mob);
/* 16 */     this.mob = mob;
/* 17 */     this.resetXRot = resetXRot;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean m_8106_() {
/* 23 */     return this.resetXRot.test(this.mob);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\ConditionalLookController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */