/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SickenedMobsAttackGoal
/*    */   extends NearestAttackableTargetGoal<Mob>
/*    */ {
/*    */   public SickenedMobsAttackGoal(Mob mob) {
/* 27 */     super(mob, Mob.class, 10, true, false, entity -> (!(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherSickened) && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity) && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormEntity) && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity) && !(entity instanceof nonamecrackers2.witherstormmod.common.entity.TentacleEntity) && !(entity instanceof net.minecraft.world.entity.boss.wither.WitherBoss) && !(entity instanceof net.minecraft.world.entity.monster.WitherSkeleton) && !(entity instanceof net.minecraft.world.entity.monster.Creeper) && !(entity instanceof net.minecraft.world.entity.monster.EnderMan) && (entity instanceof net.minecraft.world.entity.npc.AbstractVillager || entity instanceof net.minecraft.world.entity.animal.AbstractGolem || entity instanceof net.minecraft.world.entity.monster.Monster || entity instanceof net.minecraft.world.entity.monster.Slime || entity instanceof net.minecraft.world.entity.ambient.Bat || entity instanceof net.minecraft.world.entity.animal.Animal || entity instanceof net.minecraft.world.entity.NeutralMob || entity instanceof net.minecraft.world.entity.player.Player)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\SickenedMobsAttackGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */