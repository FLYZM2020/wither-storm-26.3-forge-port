/*     */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraft.world.scores.Team;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class WitherStormTargetingGoal
/*     */   extends Goal
/*     */ {
/*  32 */   private static final Logger LOGGER = LogManager.getLogger("witherstormmod/WitherStormTargetingGoal");
/*     */ 
/*     */   
/*     */   private static final boolean LOGGING = false;
/*     */   
/*     */   protected final WitherStormEntity storm;
/*     */   
/*     */   protected final int headIndex;
/*     */ 
/*     */   
/*     */   public WitherStormTargetingGoal(WitherStormEntity entity, Predicate<LivingEntity> mobSelector, int headIndex) {
/*  43 */     this.storm = entity;
/*  44 */     this.targetConditions = TargetingConditions.forCombat().selector(mobSelector).ignoreLineOfSight();
/*  45 */     this.headIndex = headIndex;
/*  46 */     setFlags(EnumSet.of(Goal.Flag.TARGET));
/*     */   }
/*     */   protected TargetingConditions targetConditions; @Nullable
/*     */   protected LivingEntity target; private int unseenTicks; @Nullable
/*     */   private Vec3 lastTargetPos;
/*     */   
/*     */   public boolean canUse() {
/*  53 */     if (this.storm.isPlayingDead() || this.storm.isAttractingFormidibomb() || this.storm.isDistracted(this.headIndex)) {
/*  54 */       return false;
/*     */     }
/*  56 */     findApplicableTarget();
/*  57 */     return (this.target != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canContinueToUse() {
/*  64 */     LivingEntity entity = this.storm.getTarget(this.headIndex);
/*     */     
/*  66 */     if (entity == null) {
/*  67 */       return false;
/*     */     }
/*  69 */     if (!entity.isAlive()) {
/*  70 */       return false;
/*     */     }
/*  72 */     Team team = this.storm.getTeam();
/*  73 */     Team team1 = entity.getTeam();
/*  74 */     if (team != null && team1 == team) {
/*  75 */       return false;
/*     */     }
/*  77 */     double d0 = getFollowDistance();
/*  78 */     if (this.storm.distanceToSqr((Entity)entity) > d0 * d0) {
/*  79 */       return false;
/*     */     }
/*  81 */     if (this.storm.getHeadManager().getHead(this.headIndex).canSee((Entity)entity)) {
/*  82 */       this.unseenTicks = 0;
/*  83 */     } else if (++this.unseenTicks > ((this.storm.getPhase() < 4) ? 80 : 20)) {
/*  84 */       return false;
/*     */     } 
/*  86 */     if (entity instanceof Player) { Player player = (Player)entity; if ((player.getAbilities()).invulnerable)
/*  87 */         return false;  }
/*     */     
/*  89 */     if (this.storm.getPhase() > 3 && this.storm.isEntityBehindBack((Entity)entity)) {
/*  90 */       return false;
/*     */     }
/*  92 */     if (!entity.level().dimension().equals(this.storm.level().dimension())) {
/*  93 */       return false;
/*     */     }
/*  95 */     if (this.lastTargetPos != null && entity.position().distanceTo(this.lastTargetPos) > 20.0D) {
/*  96 */       return false;
/*     */     }
/*  98 */     if (this.storm.getTrackedEntities().contains((Entity)entity)) {
/*  99 */       return false;
/*     */     }
/* 101 */     this.lastTargetPos = entity.position();
/*     */     
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 112 */     this.storm.getHeadManager().getHead(this.headIndex).setTarget(this.target);
/* 113 */     this.unseenTicks = 0;
/* 114 */     this.lastTargetPos = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 120 */     this.storm.getHeadManager().getHead(this.headIndex).setTarget(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected AABB getTargetSearchArea(double range) {
/* 127 */     return (this.storm.getPhase() > 3) ? this.storm.getBoundingBox().inflate(range, range + 50.0D, range) : this.storm.getBoundingBox().inflate(range, range * 2.0D, range);
/*     */   }
/*     */ 
/*     */   
/*     */   protected double getFollowDistance() {
/* 132 */     return this.storm.getAttributeValue(Attributes.FOLLOW_RANGE) + 100.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void findApplicableTarget() {
/* 138 */     double range = (this.storm.getPhase() > 3) ? this.storm.getAttributeValue(Attributes.FOLLOW_RANGE) : this.storm.getAttributeValue((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get());
/*     */     
/* 140 */     List<LivingEntity> nearbyEntities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.storm.level(), LivingEntity.class, getTargetSearchArea(range));
/*     */     
/* 142 */     List<LivingEntity> targetableEntities = Lists.newArrayList();
/* 143 */     for (LivingEntity entity : nearbyEntities) {
/*     */       
/* 145 */       if (this.storm.targetApplicable(entity, this.headIndex, this.targetConditions)) {
/* 146 */         targetableEntities.add(entity);
/*     */       }
/*     */     } 
/*     */     
/* 150 */     double d0 = -1.0D;
/* 151 */     LivingEntity t = null;
/* 152 */     for (LivingEntity t1 : targetableEntities) {
/*     */ 
/*     */       
/* 155 */       if (t1.getBoundingBox().getSize() <= 0.5D && t1.getRandom().nextInt(4) > 0) {
/*     */         continue;
/*     */       }
/* 158 */       double d1 = t1.distanceToSqr(this.storm.getX(), this.storm.getEyeY(), this.storm.getZ());
/* 159 */       if (d0 == -1.0D || d1 < d0) {
/*     */         
/* 161 */         d0 = d1;
/* 162 */         t = t1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     this.target = t;
/* 178 */     if (this.target != null)
/* 179 */       this.target.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(WitherSicknessTracker::countContact); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\WitherStormTargetingGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */