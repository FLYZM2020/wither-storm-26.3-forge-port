/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ 
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.util.Mth;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StructureAnimationHelper
/*     */ {
/*     */   public float baseXRotOffset;
/*     */   public float baseYRotOffset;
/*     */   public float baseXRot;
/*     */   public float baseXRotO;
/*     */   private float lerpBaseXRot;
/*     */   private int lerpBaseXRotSteps;
/*     */   public float baseYRot;
/*     */   public float baseYRotO;
/*     */   private float lerpBaseYRot;
/*     */   private int lerpBaseYRotSteps;
/*     */   public float xRot;
/*     */   public float xRotO;
/*     */   private float lerpXRot;
/*     */   private int lerpXRotSteps;
/*     */   public float yRot;
/*     */   public float yRotO;
/*     */   private float lerpYRot;
/*     */   private int lerpYRotSteps;
/*     */   public boolean twisted;
/*     */   
/*     */   public StructureAnimationHelper setBaseRotationAngle(float x, float y) {
/*  37 */     this.baseXRotOffset = x;
/*  38 */     this.baseYRotOffset = y;
/*  39 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(CompoundTag compound) {
/*  44 */     compound.m_128350_("BaseXRot", this.baseXRot);
/*  45 */     compound.m_128350_("BaseYRot", this.baseYRot);
/*  46 */     compound.m_128350_("XRot", this.xRot);
/*  47 */     compound.m_128350_("YRot", this.yRot);
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundTag compound) {
/*  52 */     this.baseXRot = compound.m_128457_("BaseXRot");
/*  53 */     this.baseXRotO = this.baseXRot;
/*  54 */     this.baseYRot = compound.m_128457_("BaseYRot");
/*  55 */     this.baseYRotO = this.baseYRot;
/*  56 */     this.xRot = compound.m_128457_("XRot");
/*  57 */     this.xRotO = this.xRot;
/*  58 */     this.yRot = compound.m_128457_("YRot");
/*  59 */     this.yRotO = this.yRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeBuffer(FriendlyByteBuf buffer) {
/*  64 */     buffer.writeFloat(this.baseXRot);
/*  65 */     buffer.writeFloat(this.lerpBaseXRot);
/*  66 */     buffer.writeInt(this.lerpBaseXRotSteps);
/*  67 */     buffer.writeFloat(this.baseYRot);
/*  68 */     buffer.writeFloat(this.lerpBaseYRot);
/*  69 */     buffer.writeInt(this.lerpBaseYRotSteps);
/*  70 */     buffer.writeFloat(this.xRot);
/*  71 */     buffer.writeFloat(this.lerpXRot);
/*  72 */     buffer.writeInt(this.lerpXRotSteps);
/*  73 */     buffer.writeFloat(this.yRot);
/*  74 */     buffer.writeFloat(this.lerpYRot);
/*  75 */     buffer.writeInt(this.lerpYRotSteps);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readBuffer(FriendlyByteBuf buffer) {
/*  80 */     this.baseXRot = buffer.readFloat();
/*  81 */     this.lerpBaseXRot = buffer.readFloat();
/*  82 */     this.lerpBaseXRotSteps = buffer.readInt();
/*  83 */     this.baseYRot = buffer.readFloat();
/*  84 */     this.lerpBaseYRot = buffer.readFloat();
/*  85 */     this.lerpBaseYRotSteps = buffer.readInt();
/*  86 */     this.xRot = buffer.readFloat();
/*  87 */     this.lerpXRot = buffer.readFloat();
/*  88 */     this.lerpXRotSteps = buffer.readInt();
/*  89 */     this.yRot = buffer.readFloat();
/*  90 */     this.lerpYRot = buffer.readFloat();
/*  91 */     this.lerpYRotSteps = buffer.readInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  96 */     this.baseXRotO = this.baseXRot;
/*  97 */     this.baseYRotO = this.baseYRot;
/*  98 */     this.xRotO = this.xRot;
/*  99 */     this.yRotO = this.yRot;
/*     */     
/* 101 */     if (this.lerpBaseXRotSteps > 0) {
/*     */       
/* 103 */       this.baseXRot = (float)(this.baseXRot + Mth.m_14175_(this.lerpBaseXRot - this.baseXRot) / this.lerpBaseXRotSteps);
/* 104 */       this.lerpBaseXRotSteps--;
/*     */     } 
/*     */     
/* 107 */     if (this.lerpBaseYRotSteps > 0) {
/*     */       
/* 109 */       this.baseYRot = (float)(this.baseYRot + Mth.m_14175_(this.lerpBaseYRot - this.baseYRot) / this.lerpBaseYRotSteps);
/* 110 */       this.lerpBaseYRotSteps--;
/*     */     } 
/*     */     
/* 113 */     if (this.lerpXRotSteps > 0) {
/*     */       
/* 115 */       this.xRot = (float)(this.xRot + Mth.m_14175_(this.lerpXRot - this.xRot) / this.lerpXRotSteps);
/* 116 */       this.lerpXRotSteps--;
/*     */     } 
/*     */     
/* 119 */     if (this.lerpYRotSteps > 0) {
/*     */       
/* 121 */       this.yRot = (float)(this.yRot + Mth.m_14175_(this.lerpYRot - this.yRot) / this.lerpYRotSteps);
/* 122 */       this.lerpYRotSteps--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void lerpBaseTo(CommandBlockEntity entity, float baseRotX, float baseRotY, int steps) {
/* 128 */     if (this.lerpBaseXRot != baseRotX || this.lerpBaseYRot != baseRotY) {
/* 129 */       entity.getMode().playMovementSound(entity);
/*     */     }
/* 131 */     if (this.lerpBaseXRot != baseRotX) {
/*     */       
/* 133 */       this.lerpBaseXRot = baseRotX;
/* 134 */       this.lerpBaseXRotSteps = steps;
/*     */     } 
/* 136 */     if (this.lerpBaseYRot != baseRotY) {
/*     */       
/* 138 */       this.lerpBaseYRot = baseRotY;
/* 139 */       this.lerpBaseYRotSteps = steps;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void lerpTo(CommandBlockEntity entity, float rotX, float rotY, int steps) {
/* 145 */     if (this.lerpXRot != rotX || this.lerpYRot != rotY) {
/* 146 */       entity.getMode().playMovementSound(entity);
/*     */     }
/* 148 */     if (this.lerpXRot != rotX) {
/*     */       
/* 150 */       this.lerpXRot = rotX;
/* 151 */       this.lerpXRotSteps = steps;
/*     */     } 
/* 153 */     if (this.lerpYRot != rotY) {
/*     */       
/* 155 */       this.lerpYRot = rotY;
/* 156 */       this.lerpYRotSteps = steps;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseXRot(float partialTicks) {
/* 162 */     return Mth.m_14179_(partialTicks, this.baseXRotO, this.baseXRot) + this.baseXRotOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseYRot(float partialTicks) {
/* 167 */     return Mth.m_14179_(partialTicks, this.baseYRotO, this.baseYRot) + this.baseYRotOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getXRot(float partialTicks) {
/* 172 */     return Mth.m_14179_(partialTicks, this.xRotO, this.xRot);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getYRot(float partialTicks) {
/* 177 */     return Mth.m_14179_(partialTicks, this.yRotO, this.yRot);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\StructureAnimationHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */