/*     */ package nonamecrackers2.witherstormmod.common.entity.section;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityDimensions;
/*     */ import net.minecraft.world.entity.EntitySelector;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ public class Section
/*     */ {
/*     */   protected AABB boundingBox;
/*     */   protected EntityDimensions size;
/*     */   protected Vec3 position;
/*     */   protected Vec3 offset;
/*     */   protected final WitherStormEntity owner;
/*     */   protected Predicate<WitherStormEntity> isActive;
/*  23 */   protected final float[] color = new float[] { 0.0F, 1.0F, 0.0F };
/*     */ 
/*     */   
/*     */   public Section(WitherStormEntity entity, float height, float width, double x, double y, double z, Predicate<WitherStormEntity> isActive) {
/*  27 */     this.owner = entity;
/*  28 */     this.size = EntityDimensions.m_20395_(width, height);
/*  29 */     this.position = entity.m_20182_();
/*  30 */     this.offset = new Vec3(x, y, z);
/*  31 */     this.boundingBox = this.size.m_20393_(entity.m_20182_());
/*  32 */     this.isActive = isActive;
/*     */   }
/*     */ 
/*     */   
/*     */   public Section(WitherStormEntity entity, float height, float width, double x, double y, double z, int phase) {
/*  37 */     this(entity, height, width, x, y, z, storm -> (storm.getPhase() > phase));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(float height, float width) {
/*  42 */     this.size = EntityDimensions.m_20395_(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffset(double x, double y, double z) {
/*  47 */     this.offset = new Vec3(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  52 */     float yBodyRot = this.owner.f_20883_ * 0.017453292F;
/*  53 */     float yBodyRot90 = (this.owner.f_20883_ + 90.0F) * 0.017453292F;
/*  54 */     float xBodyRot = (this.owner.xBodyRot + 90.0F) * 0.017453292F;
/*  55 */     double xOffset = Mth.m_14089_(yBodyRot) * getOffset().m_7096_();
/*  56 */     double zOffset = Mth.m_14031_(yBodyRot) * getOffset().m_7096_();
/*  57 */     float offset = (float)Math.atan2(getOffset().m_7094_(), getOffset().m_7098_());
/*  58 */     double rawX = (Mth.m_14089_(xBodyRot + offset) * Mth.m_14089_(yBodyRot90));
/*  59 */     double rawY = Mth.m_14031_(xBodyRot + offset);
/*  60 */     double rawZ = (Mth.m_14089_(xBodyRot + offset) * Mth.m_14031_(yBodyRot90));
/*  61 */     double x = xOffset + this.owner.m_20185_() + rawX * Math.sqrt(getOffset().m_7094_() * getOffset().m_7094_() + getOffset().m_7098_() * getOffset().m_7098_());
/*  62 */     double y = this.owner.m_20186_() + rawY * Math.sqrt(getOffset().m_7094_() * getOffset().m_7094_() + getOffset().m_7098_() * getOffset().m_7098_());
/*  63 */     double z = zOffset + this.owner.m_20189_() + rawZ * Math.sqrt(getOffset().m_7094_() * getOffset().m_7094_() + getOffset().m_7098_() * getOffset().m_7098_());
/*  64 */     this.position = new Vec3(x, y, z);
/*  65 */     this.boundingBox = this.size.m_20393_(this.position);
/*  66 */     collideWithNearbyEntities();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void collideWithNearbyEntities() {
/*  71 */     List<Entity> list = this.owner.m_9236_().m_6249_((Entity)this.owner, getBoundingBox(), EntitySelector.m_20421_((Entity)this.owner));
/*  72 */     if (!list.isEmpty())
/*     */     {
/*  74 */       for (Entity entity : list)
/*     */       {
/*  76 */         applyEntityCollision(entity);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyEntityCollision(Entity entity) {
/*  83 */     if (!this.owner.m_20365_(entity))
/*     */     {
/*  85 */       if (!entity.f_19794_ && !this.owner.f_19794_) {
/*     */         
/*  87 */         double d0 = entity.m_20185_() - this.position.f_82479_;
/*  88 */         double d1 = entity.m_20189_() - this.position.f_82481_;
/*  89 */         double d2 = Mth.m_14005_(d0, d1);
/*  90 */         if (d2 >= 0.009999999776482582D) {
/*     */           
/*  92 */           d2 = Math.sqrt(d2);
/*  93 */           d0 /= d2;
/*  94 */           d1 /= d2;
/*  95 */           double d3 = 1.0D / d2;
/*  96 */           if (d3 > 1.0D) {
/*  97 */             d3 = 1.0D;
/*     */           }
/*     */           
/* 100 */           d0 *= d3;
/* 101 */           d1 *= d3;
/* 102 */           d0 *= 0.05000000074505806D;
/* 103 */           d1 *= 0.05000000074505806D;
/*     */           
/* 105 */           if (!entity.m_20160_()) {
/* 106 */             entity.m_5997_(d0, 0.0D, d1);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public Vec3 getPosition() {
/* 114 */     return this.position;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getOffset() {
/* 119 */     return this.offset;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffset(Vec3 vector) {
/* 124 */     this.offset = vector;
/*     */   }
/*     */ 
/*     */   
/*     */   public AABB getBoundingBox() {
/* 129 */     return this.boundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public Level getLevel() {
/* 134 */     return this.owner.m_9236_();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 139 */     return this.isActive.test(this.owner);
/*     */   }
/*     */ 
/*     */   
/*     */   public Section setColor(float r, float g, float b) {
/* 144 */     this.color[0] = r;
/* 145 */     this.color[1] = g;
/* 146 */     this.color[2] = b;
/* 147 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] getColor() {
/* 152 */     return this.color;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\section\Section.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */