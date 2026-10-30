/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ 
/*    */ 
/*    */ public class EmptySpell
/*    */   extends SymbiontSpell
/*    */ {
/*    */   public EmptySpell(WitheredSymbiontEntity symbiont, SpellType type) {
/* 15 */     super(symbiont, type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cast(@Nonnull LivingEntity target) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDelay(RandomSource random, float modifier) {
/* 26 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\EmptySpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */