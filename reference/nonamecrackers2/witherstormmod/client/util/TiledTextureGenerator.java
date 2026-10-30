/*     */ package nonamecrackers2.witherstormmod.client.util;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.DefaultedVertexConsumer;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import net.minecraft.core.Direction;
/*     */ import org.joml.Matrix3f;
/*     */ import org.joml.Matrix3fc;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Matrix4fc;
/*     */ import org.joml.Quaternionfc;
/*     */ import org.joml.Vector3f;
/*     */ import org.joml.Vector4f;
/*     */ 
/*     */ public class TiledTextureGenerator
/*     */   extends DefaultedVertexConsumer
/*     */ {
/*     */   private final VertexConsumer delegate;
/*     */   private final PoseStack stack;
/*     */   private float x;
/*     */   private float y;
/*     */   private float z;
/*     */   private int r;
/*     */   private int g;
/*     */   private int b;
/*     */   private int a;
/*     */   private int overlayU;
/*     */   private int overlayV;
/*     */   private int lightCoords;
/*     */   private float nx;
/*     */   private float ny;
/*     */   private float nz;
/*     */   private float texScale;
/*     */   
/*     */   public TiledTextureGenerator(VertexConsumer consumer, PoseStack stack, float texScale) {
/*  36 */     this.delegate = consumer;
/*  37 */     this.stack = stack;
/*  38 */     resetState();
/*  39 */     this.texScale = texScale;
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetState() {
/*  44 */     this.x = 0.0F;
/*  45 */     this.y = 0.0F;
/*  46 */     this.z = 0.0F;
/*  47 */     this.r = 255;
/*  48 */     this.g = 255;
/*  49 */     this.b = 255;
/*  50 */     this.a = 255;
/*  51 */     this.overlayU = 0;
/*  52 */     this.overlayV = 10;
/*  53 */     this.lightCoords = 15728880;
/*  54 */     this.nx = 0.0F;
/*  55 */     this.ny = 1.0F;
/*  56 */     this.nz = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VertexConsumer m_5483_(double x, double y, double z) {
/*  62 */     this.x = (float)x;
/*  63 */     this.y = (float)y;
/*  64 */     this.z = (float)z;
/*  65 */     return (VertexConsumer)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VertexConsumer m_6122_(int red, int green, int blue, int alpha) {
/*  71 */     this.r = red;
/*  72 */     this.g = green;
/*  73 */     this.b = blue;
/*  74 */     this.a = alpha;
/*  75 */     return (VertexConsumer)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VertexConsumer m_7421_(float u, float v) {
/*  81 */     return (VertexConsumer)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VertexConsumer m_7122_(int overlayU, int overlayV) {
/*  87 */     this.overlayU = overlayU;
/*  88 */     this.overlayV = overlayV;
/*  89 */     return (VertexConsumer)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VertexConsumer m_7120_(int coord1, int coord2) {
/*  95 */     this.lightCoords = coord1 | coord2 << 16;
/*  96 */     return (VertexConsumer)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VertexConsumer m_5601_(float x, float y, float z) {
/* 102 */     this.nx = x;
/* 103 */     this.ny = y;
/* 104 */     this.nz = z;
/* 105 */     return (VertexConsumer)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_5752_() {
/* 111 */     Vector3f vector3f = (new Matrix3f((Matrix3fc)this.stack.m_85850_().m_252943_())).invert().transform(new Vector3f(this.nx, this.ny, this.nz));
/* 112 */     Direction direction = Direction.m_122372_(vector3f.x(), vector3f.y(), vector3f.z());
/* 113 */     Vector4f vector4f = (new Matrix4f((Matrix4fc)this.stack.m_85850_().m_252922_())).invert().transform(new Vector4f(this.x, this.y, this.z, 1.0F));
/* 114 */     vector4f.rotateY(3.1415927F);
/* 115 */     vector4f.rotateX(-1.5707964F);
/* 116 */     vector4f.rotate((Quaternionfc)direction.m_253075_());
/* 117 */     float f = (((direction.m_122434_() == Direction.Axis.X) ? -1.0F : 1.0F) * vector4f.x() + 0.5F) * this.texScale + getUOffset(direction);
/* 118 */     float f1 = (vector4f.y() + 0.5F) * this.texScale + getVOffset(direction);
/* 119 */     this.delegate.m_5483_(this.x, this.y, this.z).m_6122_(this.r, this.g, this.b, this.a).m_7421_(f, f1).m_7122_(this.overlayU, this.overlayV).m_85969_(this.lightCoords).m_5601_(this.nx, this.ny, this.nz).m_5752_();
/* 120 */     resetState();
/*     */   }
/*     */ 
/*     */   
/*     */   private float getUOffset(Direction direction) {
/* 125 */     Vector3f step = direction.m_253071_();
/* 126 */     if (step.x > 0.0F || step.x < 0.0F)
/*     */     {
/* 128 */       return 0.0F;
/*     */     }
/* 130 */     if (step.z > 0.0F || step.z < 0.0F)
/*     */     {
/* 132 */       return this.texScale;
/*     */     }
/* 134 */     if (step.y > 0.0F || step.y < 0.0F)
/*     */     {
/* 136 */       return this.texScale * 2.0F;
/*     */     }
/*     */ 
/*     */     
/* 140 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float getVOffset(Direction direction) {
/* 146 */     Vector3f step = direction.m_253071_();
/* 147 */     if (step.x < 0.0F || step.z < 0.0F || step.y < 0.0F)
/*     */     {
/* 149 */       return 0.0F;
/*     */     }
/* 151 */     if (step.x > 0.0F || step.z > 0.0F || step.y > 0.0F)
/*     */     {
/* 153 */       return this.texScale * 2.0F;
/*     */     }
/*     */ 
/*     */     
/* 157 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\TiledTextureGenerator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */