/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DebrisCluster
/*     */ {
/*     */   private final float orbitalAngleOffset;
/*     */   private final float verticalOffset;
/*     */   private final float radiusFromCenter;
/*     */   private final float speed;
/*     */   private final float sizeModifier;
/*     */   private int renderPhase;
/*  19 */   private final List<Piece> pieces = Lists.newArrayList();
/*  20 */   private Vec2 rotationMotion = Vec2.f_82462_;
/*     */   
/*     */   private float xRot;
/*     */   private float xRotO;
/*     */   private float yRot;
/*     */   private float yRotO;
/*     */   private float orbitalAngle;
/*     */   private float orbitalAngleO;
/*     */   private boolean isDisabled;
/*     */   private boolean isGlowing;
/*     */   private boolean isForcedGlowing;
/*     */   
/*     */   public DebrisCluster(float orbitalAngleOffset, float verticalOffset, float radiusFromCenter, float startingSpeed, float sizeModifier) {
/*  33 */     this.orbitalAngleOffset = orbitalAngleOffset;
/*  34 */     this.verticalOffset = verticalOffset;
/*  35 */     this.radiusFromCenter = radiusFromCenter;
/*  36 */     this.speed = startingSpeed;
/*  37 */     this.sizeModifier = sizeModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomize(RandomSource random, int pieceCount, float spread) {
/*  42 */     this.xRot = random.m_188501_() * 360.0F;
/*  43 */     this.xRotO = this.xRot;
/*  44 */     this.yRot = random.m_188501_() * 360.0F;
/*  45 */     this.yRotO = this.yRot;
/*  46 */     this.rotationMotion = new Vec2(random.m_188501_() * 10.0F - 5.0F, random.m_188501_() * 10.0F - 5.0F);
/*  47 */     this.pieces.clear();
/*  48 */     for (int i = 0; i < pieceCount; i++) {
/*     */       
/*  50 */       float x = random.m_188501_() * spread * 2.0F - spread;
/*  51 */       float y = random.m_188501_() * spread * 2.0F - spread;
/*  52 */       float z = random.m_188501_() * spread * 2.0F - spread;
/*  53 */       float size = (0.3F + random.m_188501_() * 0.3F) * this.sizeModifier;
/*  54 */       this.pieces.add(new Piece(x, y, z, size));
/*     */     } 
/*  56 */     this.isGlowing = (random.m_188503_(20) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void determineRenderPhase() {
/*  61 */     if (this.radiusFromCenter > 80.0F) {
/*     */       
/*  63 */       this.renderPhase = 6;
/*     */ 
/*     */     
/*     */     }
/*  67 */     else if (this.verticalOffset < 60.0F) {
/*  68 */       this.renderPhase = 4;
/*  69 */     } else if (this.verticalOffset < 80.0F) {
/*  70 */       this.renderPhase = 5;
/*     */     } else {
/*  72 */       this.renderPhase = 6;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRenderPhase(int phase) {
/*  78 */     this.renderPhase = phase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  83 */     this.xRotO = this.xRot;
/*  84 */     this.yRotO = this.yRot;
/*  85 */     this.orbitalAngleO = this.orbitalAngle;
/*     */     
/*  87 */     this.orbitalAngle += this.speed;
/*  88 */     this.xRot += this.rotationMotion.f_82470_;
/*  89 */     this.yRot += this.rotationMotion.f_82471_;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getOrbitalAngle(float partialTick) {
/*  94 */     return Mth.m_14179_(partialTick, this.orbitalAngleO, this.orbitalAngle) + this.orbitalAngleOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getVerticalOffset() {
/*  99 */     return this.verticalOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRadiusFromCenter() {
/* 104 */     return this.radiusFromCenter;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getXRot(float partialTick) {
/* 109 */     return Mth.m_14179_(partialTick, this.xRotO, this.xRot);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getYRot(float partialTick) {
/* 114 */     return Mth.m_14179_(partialTick, this.yRotO, this.yRot);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDisabled() {
/* 119 */     return this.isDisabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisabled(boolean flag) {
/* 124 */     this.isDisabled = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGlowing(boolean flag) {
/* 129 */     this.isForcedGlowing = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Piece> getPieces() {
/* 134 */     return this.pieces;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGlowing() {
/* 139 */     return this.isGlowing;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isForcedGlowing() {
/* 144 */     return this.isForcedGlowing;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderPhase() {
/* 149 */     return this.renderPhase;
/*     */   }
/*     */   public static final class Piece extends Record { private final float x; private final float y; private final float z; private final float size;
/* 152 */     public Piece(float x, float y, float z, float size) { this.x = x; this.y = y; this.z = z; this.size = size; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/util/DebrisCluster$Piece;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #152	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 152 */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/util/DebrisCluster$Piece; } public float x() { return this.x; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/util/DebrisCluster$Piece;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #152	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/util/DebrisCluster$Piece; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/util/DebrisCluster$Piece;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #152	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/util/DebrisCluster$Piece;
/* 152 */       //   0	8	1	o	Ljava/lang/Object; } public float y() { return this.y; } public float z() { return this.z; } public float size() { return this.size; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\DebrisCluster.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */