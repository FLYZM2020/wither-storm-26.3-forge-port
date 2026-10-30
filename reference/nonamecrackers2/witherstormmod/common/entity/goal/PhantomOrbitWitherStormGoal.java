/*     */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.monster.Phantom;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinPhantom;
/*     */ 
/*     */ public class PhantomOrbitWitherStormGoal
/*     */   extends Goal {
/*     */   private final Phantom entity;
/*     */   @Nullable
/*     */   private WitherStormEntity storm;
/*     */   private float angle;
/*     */   private float distance;
/*     */   private float height;
/*     */   private float clockwise;
/*     */   private final int chance;
/*     */   
/*     */   public PhantomOrbitWitherStormGoal(Phantom entity) {
/*  29 */     this(entity, 100);
/*     */   }
/*     */ 
/*     */   
/*     */   public PhantomOrbitWitherStormGoal(Phantom entity, int chance) {
/*  34 */     this.entity = entity;
/*  35 */     this.chance = chance;
/*  36 */     m_7021_(EnumSet.of(Goal.Flag.MOVE));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean touchingTarget() {
/*  41 */     return (((IMixinPhantom)this.entity).getMoveTargetPoint().m_82531_(this.entity.m_20185_(), this.entity.m_20186_(), this.entity.m_20189_()) < 4.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_8036_() {
/*  47 */     if (this.entity.m_217043_().m_188503_(this.chance) == 0)
/*  48 */       this.storm = findStorm(); 
/*  49 */     return (this.storm != null && this.storm.getPhase() > 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8056_() {
/*  55 */     this.distance = 25.0F + this.entity.m_217043_().m_188501_() * 10.0F;
/*  56 */     this.height = -4.0F + this.entity.m_217043_().m_188501_() * 9.0F;
/*  57 */     this.clockwise = this.entity.m_217043_().m_188499_() ? 1.0F : -1.0F;
/*  58 */     selectNext();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8037_() {
/*  64 */     if (this.entity.m_217043_().m_188503_(350) == 0) {
/*  65 */       this.height = -4.0F + this.entity.m_217043_().m_188501_() * 9.0F;
/*     */     }
/*  67 */     if (this.entity.m_217043_().m_188503_(250) == 0) {
/*     */       
/*  69 */       this.distance++;
/*  70 */       if (this.distance > 75.0F) {
/*     */         
/*  72 */         this.distance = 25.0F;
/*  73 */         this.clockwise = -this.clockwise;
/*     */       } 
/*     */     } 
/*     */     
/*  77 */     if (this.entity.m_217043_().m_188503_(450) == 0) {
/*     */       
/*  79 */       this.angle = this.entity.m_217043_().m_188501_() * 2.0F * 3.1415927F;
/*  80 */       selectNext();
/*     */     } 
/*     */     
/*  83 */     if (touchingTarget()) {
/*  84 */       selectNext();
/*     */     }
/*  86 */     if ((((IMixinPhantom)this.entity).getMoveTargetPoint()).f_82480_ < this.entity.m_20186_() && !this.entity.m_9236_().m_46859_(this.entity.m_20183_().m_6625_(1))) {
/*     */       
/*  88 */       this.height = Math.max(1.0F, this.height);
/*  89 */       selectNext();
/*     */     } 
/*     */     
/*  92 */     if ((((IMixinPhantom)this.entity).getMoveTargetPoint()).f_82480_ > this.entity.m_20186_() && !this.entity.m_9236_().m_46859_(this.entity.m_20183_().m_6625_(1))) {
/*     */       
/*  94 */       this.height = Math.min(-1.0F, this.height);
/*  95 */       selectNext();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void selectNext() {
/* 101 */     this.angle += this.clockwise * 15.0F * 0.017453292F;
/*     */     
/* 103 */     if (this.storm != null) {
/* 104 */       ((IMixinPhantom)this.entity).setAnchorPoint(this.storm.m_20183_().m_6630_((int)this.storm.m_20206_() + 20));
/*     */     }
/* 106 */     ((IMixinPhantom)this.entity).setMoveTargetPoint(Vec3.m_82528_((Vec3i)((IMixinPhantom)this.entity).getAnchorPoint()).m_82520_((this.distance * Mth.m_14089_(this.angle)), (-4.0F + this.height), (this.distance * Mth.m_14031_(this.angle))));
/*     */   }
/*     */ 
/*     */   
/*     */   private WitherStormEntity findStorm() {
/* 111 */     List<WitherStormEntity> entities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.entity.m_9236_(), WitherStormEntity.class, this.entity.m_20191_().m_82400_(100.0D));
/*     */     
/* 113 */     double d0 = -1.0D;
/* 114 */     WitherStormEntity closest = null;
/* 115 */     for (WitherStormEntity storm : entities) {
/*     */       
/* 117 */       if (this.entity.m_142582_((Entity)storm)) {
/*     */         
/* 119 */         double d1 = storm.m_20275_(this.entity.m_20185_(), this.entity.m_20186_(), this.entity.m_20189_());
/* 120 */         if (d0 == -1.0D || d1 < d0) {
/*     */           
/* 122 */           d0 = d1;
/* 123 */           closest = storm;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     return closest;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\PhantomOrbitWitherStormGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */