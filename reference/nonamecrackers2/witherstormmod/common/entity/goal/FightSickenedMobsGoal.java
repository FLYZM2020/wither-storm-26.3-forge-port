/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*    */ 
/*    */ 
/*    */ public class FightSickenedMobsGoal
/*    */   extends NearestAttackableTargetGoal<Mob>
/*    */ {
/*    */   public FightSickenedMobsGoal(Mob mob) {
/* 12 */     super(mob, Mob.class, 10, true, false, entity -> (entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherSickened && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.SickenedCreeper)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\FightSickenedMobsGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */