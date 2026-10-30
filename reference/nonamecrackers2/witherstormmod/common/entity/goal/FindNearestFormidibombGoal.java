/*     */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntitySelector;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.target.TargetGoal;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ 
/*     */ public class FindNearestFormidibombGoal
/*     */   extends TargetGoal
/*     */ {
/*     */   private final WitherStormEntity storm;
/*     */   private FormidibombEntity target;
/*     */   private final int randomInterval;
/*     */   
/*     */   public FindNearestFormidibombGoal(WitherStormEntity storm, int randomInterval) {
/*  27 */     super((Mob)storm, true);
/*  28 */     this.storm = storm;
/*  29 */     this.randomInterval = randomInterval;
/*  30 */     m_7021_(EnumSet.of(Goal.Flag.TARGET));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_8036_() {
/*  36 */     if (this.storm.shouldIgnoreFormidibomb)
/*     */     {
/*  38 */       return false;
/*     */     }
/*  40 */     if (this.randomInterval > 0 && this.storm.m_217043_().m_188503_(this.randomInterval) != 0)
/*     */     {
/*  42 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  46 */     findTarget();
/*  47 */     return (this.target != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void findTarget() {
/*  53 */     this.target = getNearestLoadedFormidibomb(this.storm.m_20185_(), this.storm.m_20188_(), this.storm.m_20189_(), getTargetSearchArea(m_7623_()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8056_() {
/*  59 */     this.storm.setFormidibomb(this.target);
/*  60 */     super.m_8056_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected double m_7623_() {
/*  66 */     return this.f_26135_.m_21172_(Attributes.f_22277_) + 50.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   private AABB getTargetSearchArea(double inflation) {
/*  71 */     return (this.storm.getPhase() > 3) ? this.f_26135_.m_20191_().m_82377_(inflation, inflation + 255.0D, inflation) : this.f_26135_.m_20191_().m_82377_(40.0D, 20.0D, 40.0D);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private FormidibombEntity getNearestLoadedFormidibomb(double x, double y, double z, AABB bounding) {
/*  76 */     return getNearestFormidibomb(WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.storm.m_9236_(), FormidibombEntity.class, bounding, EntitySelector.f_20408_), this.storm, x, y, z);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private FormidibombEntity getNearestFormidibomb(List<FormidibombEntity> entities, WitherStormEntity targeter, double x, double y, double z) {
/*  81 */     double d0 = -1.0D;
/*  82 */     FormidibombEntity formidibomb = null;
/*     */     
/*  84 */     for (FormidibombEntity entity : entities) {
/*     */       
/*  86 */       if (test(targeter, (Entity)entity)) {
/*     */         
/*  88 */         double d1 = entity.m_20275_(x, y, z);
/*  89 */         if (d0 == -1.0D || d1 < d0) {
/*     */           
/*  91 */           d0 = d1;
/*  92 */           formidibomb = entity;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     return formidibomb;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean test(WitherStormEntity entity, Entity target) {
/* 102 */     if (entity == target)
/*     */     {
/* 104 */       return false;
/*     */     }
/* 106 */     if (!(target instanceof FormidibombEntity))
/*     */     {
/* 108 */       return false;
/*     */     }
/* 110 */     if (target.m_5833_())
/*     */     {
/* 112 */       return false;
/*     */     }
/* 114 */     if (!target.m_6084_())
/*     */     {
/* 116 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 120 */     if (entity != null) {
/*     */       
/* 122 */       if (m_7623_() > 0.0D) {
/*     */         
/* 124 */         double d0 = Math.max(m_7623_(), 2.0D);
/* 125 */         double d1 = entity.m_20275_(target.m_20185_(), target.m_20186_(), target.m_20189_());
/* 126 */         if (d1 > d0 * d0) {
/* 127 */           return false;
/*     */         }
/*     */       } 
/* 130 */       return entity.getHeadManager().getHead(0).canSee(target);
/*     */     } 
/*     */     
/* 133 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\FindNearestFormidibombGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */