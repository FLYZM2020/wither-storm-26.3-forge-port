/*    */ package nonamecrackers2.witherstormmod.client.instancing;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.BufferBuilder;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexBuffer;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import nonamecrackers2.witherstormmod.mixin.MixinBufferBuilder;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.lwjgl.system.MemoryUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AsyncBufferedInstance
/*    */   extends BufferedInstance
/*    */ {
/* 25 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   @Nullable
/*    */   private CompletableFuture<BufferBuilder> bufferBuilder;
/*    */   
/*    */   public AsyncBufferedInstance(RenderType type, BufferedInstance.Bufferable bufferer, Supplier<Boolean> shouldRemove) {
/* 30 */     super(type, bufferer, shouldRemove);
/*    */   }
/*    */ 
/*    */   
/*    */   public void checkBufferBuilderStatus() {
/* 35 */     if (this.bufferBuilder != null && this.bufferBuilder.isDone()) {
/*    */ 
/*    */       
/*    */       try {
/* 39 */         BufferBuilder builder = this.bufferBuilder.get();
/* 40 */         if (this.buffer != null)
/* 41 */           this.buffer.close(); 
/* 42 */         this.buffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
/* 43 */         BufferBuilder.RenderedBuffer rendered = builder.m_231175_();
/* 44 */         this.buffer.m_85921_();
/* 45 */         this.buffer.m_231221_(rendered);
/* 46 */         VertexBuffer.m_85931_();
/* 47 */         builder.m_85729_();
/* 48 */         MixinBufferBuilder mixin = (MixinBufferBuilder)builder;
/* 49 */         MemoryUtil.memFree(mixin.witherstormmod$getBuffer());
/* 50 */         mixin.witherstormmod$setBuffer(null);
/*    */       }
/* 52 */       catch (InterruptedException|java.util.concurrent.ExecutionException e) {
/*    */         
/* 54 */         LOGGER.error("Failed to get BufferBuilder", e);
/*    */       } 
/* 56 */       this.bufferBuilder = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void buildBuffer(PoseStack stack, ExecutorService pool) {
/* 63 */     this
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 69 */       .bufferBuilder = CompletableFuture.supplyAsync(() -> { RenderType type = getRenderType(); BufferBuilder builder = new BufferBuilder(512); builder.m_166779_(type.m_173186_(), type.m_110508_()); bufferInto(stack, (VertexConsumer)builder, 15728880, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F); return builder; }pool).handle((b, e) -> {
/*    */           if (e != null)
/*    */             LOGGER.error("Failed to build buffer asynchronously", e); 
/*    */           b.m_85729_();
/*    */           return b;
/*    */         });
/* 75 */     this.dirty = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\instancing\AsyncBufferedInstance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */