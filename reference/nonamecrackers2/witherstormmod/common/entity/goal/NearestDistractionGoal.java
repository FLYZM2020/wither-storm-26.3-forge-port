/*     */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ 
/*     */ public class NearestDistractionGoal<T extends Mob & WitherStormBase>
/*     */   extends Goal
/*     */ {
/*     */   protected final T entity;
/*     */   protected int head;
/*  23 */   protected int unseenTime = 180;
/*     */   @Nullable
/*     */   protected Entity target;
/*     */   protected int unseenTicks;
/*     */   protected Predicate<Entity> condition;
/*     */   protected int randomInterval;
/*     */   
/*     */   public NearestDistractionGoal(T entity, int head, Predicate<Entity> condition, int interval) {
/*  31 */     this.entity = entity;
/*  32 */     this.head = head;
/*  33 */     this.condition = condition;
/*  34 */     this.randomInterval = interval;
/*  35 */     m_7021_(EnumSet.of(Goal.Flag.TARGET));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_8036_() {
/*  41 */     if (((WitherStormBase)this.entity).canBeDistracted(this.head, WitherStormBase.DistractionType.ENTITY_BASED)) {
/*     */       
/*  43 */       if (!((WitherStormBase)this.entity).isDistracted(this.head)) {
/*     */         
/*  45 */         if (this.randomInterval > 0 && this.entity.m_217043_().m_188503_(this.randomInterval) != 0)
/*     */         {
/*  47 */           return false;
/*     */         }
/*     */ 
/*     */         
/*  51 */         findTarget();
/*  52 */         return (this.target != null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  57 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_8045_() {
/*  69 */     Entity entity = this.target;
/*     */     
/*  71 */     if (((WitherStormBase)this.entity).isDistracted(this.head)) {
/*     */       
/*  73 */       if (entity == null || !entity.m_6084_()) {
/*     */         
/*  75 */         if (this.entity.m_217043_().m_188503_(8) == 0) {
/*     */           
/*  77 */           Vec3 vector = ((WitherStormBase)this.entity).getDistractedPos(this.head);
/*  78 */           assert vector != null;
/*  79 */           ((WitherStormBase)this.entity).setDistractedPos(this.head, vector.m_82520_(this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_()));
/*     */         } 
/*  81 */         findTarget();
/*  82 */         if (this.target != null && entity != this.target)
/*  83 */           ((WitherStormBase)this.entity).makeDistracted(getTargetPos(), this.entity.m_217043_().m_188503_(80) + 80, this.head); 
/*     */       } 
/*  85 */       return true;
/*     */     } 
/*     */     
/*  88 */     if (entity == null)
/*     */     {
/*  90 */       return false;
/*     */     }
/*  92 */     if (!entity.m_6084_())
/*     */     {
/*  94 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  98 */     if (this.entity.m_20270_(entity) > getFollowDistance())
/*     */     {
/* 100 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 104 */     if (((WitherStormBase)this.entity).canSee(this.head, entity)) {
/* 105 */       this.unseenTicks = 0;
/* 106 */     } else if (this.unseenTicks++ > this.unseenTime) {
/* 107 */       return false;
/*     */     } 
/*     */     
/* 110 */     if (!((WitherStormBase)this.entity).isDistracted(this.head)) {
/* 111 */       ((WitherStormBase)this.entity).makeDistracted(getTargetPos(), this.entity.m_217043_().m_188503_(80) + 80, this.head);
/*     */     } else {
/* 113 */       ((WitherStormBase)this.entity).setDistractedPos(this.head, getTargetPos());
/*     */     } 
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void findTarget() {
/* 121 */     List<Entity> entities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.entity.m_9236_(), Entity.class, getSearchArea());
/*     */     
/* 123 */     double d0 = -1.0D;
/* 124 */     Entity e = null;
/*     */     
/* 126 */     for (Entity e1 : entities) {
/*     */       
/* 128 */       if (canBeDistractedBy(e1) && !((WitherStormBase)this.entity).isEntityBehindBack(e1)) {
/*     */         
/* 130 */         double d1 = e1.m_20238_(((WitherStormBase)this.entity).getHeadPos(this.head));
/* 131 */         if (d0 == -1.0D || d1 < d0) {
/*     */           
/* 133 */           d0 = d1;
/* 134 */           e = e1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     this.target = e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8056_() {
/* 145 */     this.unseenTicks = 0;
/* 146 */     ((WitherStormBase)this.entity).setTarget(this.head, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8041_() {
/* 152 */     ((WitherStormBase)this.entity).setDistractedPos(this.head, null);
/* 153 */     this.target = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double getFollowDistance() {
/* 158 */     return this.entity.m_21133_(Attributes.f_22277_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected AABB getSearchArea() {
/* 163 */     return this.entity.m_20191_().m_82400_(getFollowDistance());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canBeDistractedBy(Entity entity) {
/* 168 */     return this.condition.test(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3 getTargetPos() {
/* 173 */     assert this.target != null;
/* 174 */     return this.target.m_20182_();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\NearestDistractionGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */