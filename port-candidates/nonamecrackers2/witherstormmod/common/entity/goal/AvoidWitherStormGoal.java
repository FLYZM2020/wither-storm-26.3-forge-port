/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.PathfinderMob;
/*    */ import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ 
/*    */ public class AvoidWitherStormGoal
/*    */   extends AvoidEntityGoal<WitherStormEntity> {
/*    */   private final float smallPhaseMaxDist;
/*    */   
/*    */   public AvoidWitherStormGoal(PathfinderMob mob, float maxDist, double walkSpeedModifier, double sprintSpeedModifier) {
/* 25 */     super(mob, WitherStormEntity.class, maxDist, walkSpeedModifier, sprintSpeedModifier);
/* 26 */     this.smallPhaseMaxDist = maxDist / 8.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 33 */     AABB box = this.mob.getBoundingBox().inflate(this.maxDist);
/* 34 */     List<WitherStormEntity> storms = this.mob.level().getEntitiesOfClass(WitherStormEntity.class, box, storm -> (storm != this.mob && storm.isAlive()));
/* 35 */     Objects.requireNonNull(this.mob); storms.sort(Comparator.comparingDouble(this.mob::distanceToSqr));
/*    */ 
/*    */     
/* 38 */     this.toAvoid = storms.stream().findFirst().orElse(null);
/*    */     
/* 40 */     if (this.toAvoid != null && (!((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.mob, this.toAvoid, 4.0D).getFirst()).booleanValue() || this.mob.onGround()) && WorldUtil.canSeeOrIsNotInASmallArea((Entity)this.toAvoid, (Entity)this.mob)) {
/*    */       
/* 42 */       BlockPos portalPos = null;
/* 43 */       if (((Boolean)WitherStormModConfig.SERVER.mobsRunIntoPortals.get()).booleanValue()) {
/* 44 */         portalPos = getNearestLoadedBlockPos(this.mob.position(), 16, Blocks.NETHER_PORTAL);
/*    */       }
/* 46 */       if ((((WitherStormEntity)this.toAvoid).getPhase() >= 4 || ((((WitherStormEntity)this.toAvoid).getPhase() == 2 || ((WitherStormEntity)this.toAvoid).getPhase() == 3) && this.mob.getBoundingBox().inflate(this.smallPhaseMaxDist).contains(((WitherStormEntity)this.toAvoid).position()))) && !((WitherStormEntity)this.toAvoid).isDeadOrPlayingDead()) {
/*    */         
/* 48 */         Vec3 vec3 = (portalPos != null) ? new Vec3(portalPos.getX() + 0.5D, (portalPos.getY() + 1), portalPos.getZ() + 0.5D) : this.mob.position().subtract(((WitherStormEntity)this.toAvoid).position()).normalize().scale(16.0D).add(this.mob.position());
/*    */         
/* 50 */         if (this.path == null || this.path.isDone()) {
/*    */           
/* 52 */           this.path = this.pathNav.createPath(vec3.x, vec3.y, vec3.z, 0);
/* 53 */           this.mob.setTarget(null);
/*    */         } 
/* 55 */         return (this.path != null);
/*    */       } 
/*    */     } 
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canContinueToUse() {
/* 64 */     boolean flag = (this.toAvoid == null || !((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.mob, this.toAvoid, 4.0D).getFirst()).booleanValue() || this.mob.onGround());
/* 65 */     return (super.canContinueToUse() && flag);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos getNearestLoadedBlockPos(Vec3 pos, int radius, Block block) {
/* 70 */     int minX = (int)pos.x - radius;
/* 71 */     int minY = (int)pos.y - 4;
/* 72 */     int minZ = (int)pos.z - radius;
/* 73 */     int maxX = (int)pos.x + radius;
/* 74 */     int maxY = (int)pos.y + 4;
/* 75 */     int maxZ = (int)pos.z + radius;
/* 76 */     for (BlockPos blockPos : BlockPos.betweenClosed(new BlockPos(minX, minY, minZ), new BlockPos(maxX, maxY, maxZ))) {
/*    */       
/* 78 */       BlockState blockState = this.mob.level().getBlockState(blockPos);
/* 79 */       if (blockState.getBlock() == block)
/* 80 */         return blockPos; 
/*    */     } 
/* 82 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\AvoidWitherStormGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */