/*    */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Vec3i;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.goal.Goal;
/*    */ import net.minecraft.world.level.ClipContext;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.event.ForgeEventFactory;
/*    */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ 
/*    */ public class NearestBlockDistractionGoal<T extends Mob & WitherStormBase> extends Goal {
/*    */   private static final int TICKS_UNTIL_RETRY = 60;
/*    */   private static final int LOOK_AT_DISTRACTION_TIME_MIN = 120;
/*    */   private static final int LOOK_AT_DISTRACTION_TIME_MAX = 180;
/*    */   private static final double MINIMUM_DISTANCE_BETWEEN_BEAMS = 10.0D;
/*    */   protected final T mob;
/*    */   protected final int headIndex;
/*    */   private BlockPos targetPos;
/*    */   private int retry;
/*    */   
/*    */   public NearestBlockDistractionGoal(T mob, int headIndex) {
/* 33 */     this.mob = mob;
/* 34 */     this.headIndex = headIndex;
/* 35 */     m_7021_(EnumSet.of(Goal.Flag.TARGET));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8036_() {
/* 41 */     if (!ForgeEventFactory.getMobGriefingEvent(this.mob.m_9236_(), (Entity)this.mob)) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (this.retry > 0) {
/*    */       
/* 46 */       this.retry--;
/* 47 */       return false;
/*    */     } 
/* 49 */     if (((WitherStormBase)this.mob).getTarget(this.headIndex) != null && TractorBeamHelper.isInsideTractorBeam((Entity)Objects.requireNonNull(((WitherStormBase)this.mob).getTarget(this.headIndex)), (LivingEntity)this.mob, 4.0D, this.headIndex))
/*    */     {
/* 51 */       return false;
/*    */     }
/* 53 */     if (((WitherStormBase)this.mob).canBeDistracted(this.headIndex, WitherStormBase.DistractionType.STRUCTURES) && !((WitherStormBase)this.mob).isDistracted(this.headIndex)) {
/*    */       
/* 55 */       BlockPos pos = findTargetPosition();
/* 56 */       if (pos != null) {
/*    */         
/* 58 */         for (int i = 0; i < ((WitherStormBase)this.mob).getTotalHeads(); i++) {
/*    */           
/* 60 */           Vec3 distractedPos = ((WitherStormBase)this.mob).getDistractedPos(i);
/* 61 */           if (distractedPos != null && distractedPos.m_82531_(pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D) < Math.pow(10.0D, 2.0D) && this.mob.m_217043_().m_188503_(5) != 0) {
/*    */             
/* 63 */             this.targetPos = null;
/* 64 */             this.retry = 60;
/* 65 */             return false;
/*    */           } 
/*    */         } 
/* 68 */         if (((WitherStormBase)this.mob).isPosBehindBack(Vec3.m_82512_((Vec3i)pos)))
/* 69 */           return false; 
/*    */       } 
/* 71 */       this.targetPos = pos;
/* 72 */       return (this.targetPos != null);
/*    */     } 
/*    */ 
/*    */     
/* 76 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_8056_() {
/* 83 */     ((WitherStormBase)this.mob).makeDistracted(Vec3.m_82512_((Vec3i)this.targetPos), this.mob.m_217043_().m_188503_(60) + 120, this.headIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   private BlockPos findTargetPosition() {
/* 88 */     int scanRadius = ((Integer)WitherStormModConfig.SERVER.tractorBeamBlockSearchRadius.get()).intValue();
/* 89 */     Vec3 currentTarget = ((WitherStormBase)this.mob).getHeadPos(this.headIndex);
/* 90 */     Vec3 beamEnd = currentTarget.m_82549_(((WitherStormBase)this.mob).getViewVector(((WitherStormBase)this.mob).getHeadXRot(this.headIndex), ((WitherStormBase)this.mob).getHeadYRot(this.headIndex), 200.0F));
/* 91 */     BlockHitResult result = this.mob.m_9236_().m_45547_(new ClipContext(currentTarget, beamEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
/* 92 */     BlockPos hitPos = result.m_82425_().m_7918_(this.mob.m_217043_().m_216332_(-4, 4), this.mob.m_217043_().m_216332_(-4, 4), this.mob.m_217043_().m_216332_(-4, 4));
/* 93 */     if (WorldUtil.isLoaded((ServerLevel)this.mob.m_9236_(), hitPos))
/* 94 */       return WorldUtil.forEachBlockSpiralOutwards(this.mob.m_9236_(), hitPos, scanRadius, p -> this.mob.m_9236_().m_8055_(p).m_204336_(WitherStormModBlockTags.TRACTOR_BEAM_DISTRACTION_BLOCKS)); 
/* 95 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\NearestBlockDistractionGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */