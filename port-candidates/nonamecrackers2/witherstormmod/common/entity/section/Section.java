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
/*  28 */     this.size = EntityDimensions.scalable(width, height);
/*  29 */     this.position = entity.position();
/*  30 */     this.offset = new Vec3(x, y, z);
/*  31 */     this.boundingBox = this.size.makeBoundingBox(entity.position());
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
/*  42 */     this.size = EntityDimensions.scalable(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffset(double x, double y, double z) {
/*  47 */     this.offset = new Vec3(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  52 */     float yBodyRot = this.owner.yBodyRot * 0.017453292F;
/*  53 */     float yBodyRot90 = (this.owner.yBodyRot + 90.0F) * 0.017453292F;
/*  54 */     float xBodyRot = (this.owner.xBodyRot + 90.0F) * 0.017453292F;
/*  55 */     double xOffset = Mth.cos(yBodyRot) * getOffset().x();
/*  56 */     double zOffset = Mth.sin(yBodyRot) * getOffset().x();
/*  57 */     float offset = (float)Math.atan2(getOffset().z(), getOffset().y());
/*  58 */     double rawX = (Mth.cos(xBodyRot + offset) * Mth.cos(yBodyRot90));
/*  59 */     double rawY = Mth.sin(xBodyRot + offset);
/*  60 */     double rawZ = (Mth.cos(xBodyRot + offset) * Mth.sin(yBodyRot90));
/*  61 */     double x = xOffset + this.owner.getX() + rawX * Math.sqrt(getOffset().z() * getOffset().z() + getOffset().y() * getOffset().y());
/*  62 */     double y = this.owner.getY() + rawY * Math.sqrt(getOffset().z() * getOffset().z() + getOffset().y() * getOffset().y());
/*  63 */     double z = zOffset + this.owner.getZ() + rawZ * Math.sqrt(getOffset().z() * getOffset().z() + getOffset().y() * getOffset().y());
/*  64 */     this.position = new Vec3(x, y, z);
/*  65 */     this.boundingBox = this.size.makeBoundingBox(this.position);
/*  66 */     collideWithNearbyEntities();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void collideWithNearbyEntities() {
/*  71 */     List<Entity> list = this.owner.level().getEntities((Entity)this.owner, getBoundingBox(), EntitySelector.pushableBy((Entity)this.owner));
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
/*  83 */     if (!this.owner.isPassengerOfSameVehicle(entity))
/*     */     {
/*  85 */       if (!entity.noPhysics && !this.owner.noPhysics) {
/*     */         
/*  87 */         double d0 = entity.getX() - this.position.x;
/*  88 */         double d1 = entity.getZ() - this.position.z;
/*  89 */         double d2 = Mth.absMax(d0, d1);
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
/* 105 */           if (!entity.isVehicle()) {
/* 106 */             entity.push(d0, 0.0D, d1);
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
/* 134 */     return this.owner.level();
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