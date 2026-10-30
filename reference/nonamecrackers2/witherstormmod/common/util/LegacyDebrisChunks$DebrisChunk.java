/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.world.phys.Vec2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DebrisChunk
/*     */ {
/*     */   private final Random random;
/*     */   private final float orbitOffset;
/*     */   private final float distanceFromCenter;
/*     */   private final float verticalOffset;
/*     */   private final float radius;
/*     */   private final Vec2 motion;
/*     */   public float speed;
/*     */   public float xRot;
/*     */   public float yRot;
/*     */   public float xRotO;
/*     */   public float yRotO;
/*     */   public float orbitalAngle;
/*     */   public float orbitalAngleO;
/*     */   public boolean disabled;
/*     */   
/*     */   public DebrisChunk(Random random) {
/*  90 */     this(random, 80, 20);
/*     */   }
/*     */ 
/*     */   
/*     */   public DebrisChunk(Random random, int verticalOffset, int distanceFromCenter) {
/*  95 */     this.random = random;
/*  96 */     this.orbitOffset = this.random.nextInt(360);
/*  97 */     this.distanceFromCenter = (float)(this.random.nextInt(distanceFromCenter) * 0.1D / 2.0D);
/*  98 */     this.verticalOffset = (this.random.nextInt(verticalOffset) / 2);
/*  99 */     this.radius = Math.max(0.0F, this.random.nextFloat() - 0.5F);
/* 100 */     this.motion = new Vec2(((this.random.nextInt(60) - 30) / 2), ((this.random.nextInt(60) - 30) / 2));
/* 101 */     this.speed = this.random.nextFloat() * 6.0F - 3.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getOrbitOffset() {
/* 106 */     return this.orbitOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public float distanceFromCenter() {
/* 111 */     return this.distanceFromCenter;
/*     */   }
/*     */ 
/*     */   
/*     */   public float verticalOffset() {
/* 116 */     return this.verticalOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRadius() {
/* 121 */     return this.radius;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec2 getMotion() {
/* 126 */     return this.motion;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotation(float x, float y) {
/* 131 */     this.xRotO = this.xRot;
/* 132 */     this.yRotO = this.yRot;
/* 133 */     this.xRot = x % 360.0F;
/* 134 */     this.yRot = y % 360.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrbitalAngle(float angle) {
/* 139 */     this.orbitalAngleO = this.orbitalAngle;
/* 140 */     this.orbitalAngle = angle % 360.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\LegacyDebrisChunks$DebrisChunk.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */