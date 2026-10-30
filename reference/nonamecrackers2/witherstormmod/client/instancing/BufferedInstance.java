/*     */ package nonamecrackers2.witherstormmod.client.instancing;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.BufferBuilder;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.Tesselator;
/*     */ import com.mojang.blaze3d.vertex.VertexBuffer;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BufferedInstance
/*     */ {
/*     */   @Nullable
/*     */   protected VertexBuffer buffer;
/*     */   private final RenderType type;
/*     */   private Bufferable bufferer;
/*     */   protected boolean dirty = true;
/*     */   @Nullable
/*     */   private final Supplier<Boolean> shouldRemove;
/*     */   private boolean wasRenderedLastFrame = true;
/*     */   
/*     */   public BufferedInstance(RenderType type, Bufferable bufferer, @Nullable Supplier<Boolean> shouldRemove) {
/*  29 */     this.type = type;
/*  30 */     this.bufferer = bufferer;
/*  31 */     this.shouldRemove = shouldRemove;
/*     */   }
/*     */ 
/*     */   
/*     */   public void bufferInto(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
/*  36 */     this.bufferer.bufferInto(stack, consumer, packedLight, overlayTexture, r, g, b, a);
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderType getRenderType() {
/*  41 */     return this.type;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public VertexBuffer getBuffer() {
/*  46 */     return this.buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  51 */     if (this.buffer != null) {
/*  52 */       this.buffer.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean requiresComputing() {
/*  57 */     return this.dirty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void markDirty() {
/*  62 */     this.dirty = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildBuffer(PoseStack stack, ExecutorService pool) {
/*  67 */     if (this.buffer != null)
/*  68 */       this.buffer.close(); 
/*  69 */     this.buffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
/*  70 */     RenderType type = getRenderType();
/*  71 */     BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
/*  72 */     buffer.m_166779_(type.m_173186_(), type.m_110508_());
/*  73 */     bufferInto(stack, (VertexConsumer)buffer, 15728880, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/*  74 */     BufferBuilder.RenderedBuffer rendered = buffer.m_231175_();
/*  75 */     this.buffer.m_85921_();
/*  76 */     this.buffer.m_231221_(rendered);
/*  77 */     VertexBuffer.m_85931_();
/*  78 */     this.dirty = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBufferer(Bufferable bufferer) {
/*  83 */     this.bufferer = bufferer;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRemove() {
/*  88 */     return (this.shouldRemove != null) ? ((Boolean)this.shouldRemove.get()).booleanValue() : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRenderedLastFrame(boolean flag) {
/*  93 */     this.wasRenderedLastFrame = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasRenderedLastFrame() {
/*  98 */     return this.wasRenderedLastFrame;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRemoveSupplier() {
/* 103 */     return (this.shouldRemove != null);
/*     */   }
/*     */   
/*     */   public static interface Bufferable {
/*     */     void bufferInto(PoseStack param1PoseStack, VertexConsumer param1VertexConsumer, int param1Int1, int param1Int2, float param1Float1, float param1Float2, float param1Float3, float param1Float4);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\instancing\BufferedInstance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */