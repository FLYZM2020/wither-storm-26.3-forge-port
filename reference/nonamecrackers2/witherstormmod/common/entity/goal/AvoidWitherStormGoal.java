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
/*    */   public boolean m_8036_() {
/* 33 */     AABB box = this.f_25015_.m_20191_().m_82400_(this.f_25017_);
/* 34 */     List<WitherStormEntity> storms = this.f_25015_.m_9236_().m_6443_(WitherStormEntity.class, box, storm -> (storm != this.f_25015_ && storm.m_6084_()));
/* 35 */     Objects.requireNonNull(this.f_25015_); storms.sort(Comparator.comparingDouble(this.f_25015_::m_20280_));
/*    */ 
/*    */     
/* 38 */     this.f_25016_ = storms.stream().findFirst().orElse(null);
/*    */     
/* 40 */     if (this.f_25016_ != null && (!((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.f_25015_, this.f_25016_, 4.0D).getFirst()).booleanValue() || this.f_25015_.m_20096_()) && WorldUtil.canSeeOrIsNotInASmallArea((Entity)this.f_25016_, (Entity)this.f_25015_)) {
/*    */       
/* 42 */       BlockPos portalPos = null;
/* 43 */       if (((Boolean)WitherStormModConfig.SERVER.mobsRunIntoPortals.get()).booleanValue()) {
/* 44 */         portalPos = getNearestLoadedBlockPos(this.f_25015_.m_20182_(), 16, Blocks.f_50142_);
/*    */       }
/* 46 */       if ((((WitherStormEntity)this.f_25016_).getPhase() >= 4 || ((((WitherStormEntity)this.f_25016_).getPhase() == 2 || ((WitherStormEntity)this.f_25016_).getPhase() == 3) && this.f_25015_.m_20191_().m_82400_(this.smallPhaseMaxDist).m_82390_(((WitherStormEntity)this.f_25016_).m_20182_()))) && !((WitherStormEntity)this.f_25016_).isDeadOrPlayingDead()) {
/*    */         
/* 48 */         Vec3 vec3 = (portalPos != null) ? new Vec3(portalPos.m_123341_() + 0.5D, (portalPos.m_123342_() + 1), portalPos.m_123343_() + 0.5D) : this.f_25015_.m_20182_().m_82546_(((WitherStormEntity)this.f_25016_).m_20182_()).m_82541_().m_82490_(16.0D).m_82549_(this.f_25015_.m_20182_());
/*    */         
/* 50 */         if (this.f_25018_ == null || this.f_25018_.m_77392_()) {
/*    */           
/* 52 */           this.f_25018_ = this.f_25019_.m_26524_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_, 0);
/* 53 */           this.f_25015_.m_6710_(null);
/*    */         } 
/* 55 */         return (this.f_25018_ != null);
/*    */       } 
/*    */     } 
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8045_() {
/* 64 */     boolean flag = (this.f_25016_ == null || !((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.f_25015_, this.f_25016_, 4.0D).getFirst()).booleanValue() || this.f_25015_.m_20096_());
/* 65 */     return (super.m_8045_() && flag);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos getNearestLoadedBlockPos(Vec3 pos, int radius, Block block) {
/* 70 */     int minX = (int)pos.f_82479_ - radius;
/* 71 */     int minY = (int)pos.f_82480_ - 4;
/* 72 */     int minZ = (int)pos.f_82481_ - radius;
/* 73 */     int maxX = (int)pos.f_82479_ + radius;
/* 74 */     int maxY = (int)pos.f_82480_ + 4;
/* 75 */     int maxZ = (int)pos.f_82481_ + radius;
/* 76 */     for (BlockPos blockPos : BlockPos.m_121940_(new BlockPos(minX, minY, minZ), new BlockPos(maxX, maxY, maxZ))) {
/*    */       
/* 78 */       BlockState blockState = this.f_25015_.m_9236_().m_8055_(blockPos);
/* 79 */       if (blockState.m_60734_() == block)
/* 80 */         return blockPos; 
/*    */     } 
/* 82 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\AvoidWitherStormGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */