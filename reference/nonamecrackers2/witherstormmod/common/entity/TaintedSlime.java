/*    */ package nonamecrackers2.witherstormmod.common.entity;
/*    */ 
/*    */ import net.minecraft.world.entity.AnimationState;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*    */ import net.minecraft.world.entity.monster.Monster;
/*    */ import net.minecraft.world.entity.monster.Slime;
/*    */ import net.minecraft.world.level.Level;
/*    */ 
/*    */ public class TaintedSlime
/*    */   extends Slime {
/* 12 */   public AnimationState idle = new AnimationState();
/*    */ 
/*    */   
/*    */   public TaintedSlime(EntityType<? extends TaintedSlime> type, Level level) {
/* 16 */     super(type, level);
/* 17 */     this.idle.m_216977_(this.f_19797_);
/*    */   }
/*    */ 
/*    */   
/*    */   public static AttributeSupplier.Builder createAttributes() {
/* 22 */     return Monster.m_33035_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\TaintedSlime.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */