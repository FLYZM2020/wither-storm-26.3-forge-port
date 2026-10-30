/*     */ package nonamecrackers2.witherstormmod.client.shader;
/*     */ 
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.PostChain;
/*     */ import net.minecraft.client.renderer.PostPass;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
/*     */ import nonamecrackers2.witherstormmod.client.capability.FormidibombEffectsManager;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinPostChain;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PostProcessingShaders
/*     */   implements ResourceManagerReloadListener
/*     */ {
/*  25 */   public static final PostProcessingShaders INSTANCE = new PostProcessingShaders(Minecraft.m_91087_());
/*  26 */   private static final ResourceLocation ABERRATION = new ResourceLocation("witherstormmod", "shaders/post/aberration.json");
/*  27 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final Minecraft minecraft;
/*     */   
/*     */   private PostChain aberrationEffect;
/*     */   private float prevWidth;
/*     */   private float prevHeight;
/*     */   
/*     */   public PostProcessingShaders(Minecraft minecraft) {
/*  36 */     this.minecraft = minecraft;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderShaders(float partialTicks) {
/*  41 */     this.minecraft.m_91307_().m_6180_("chromatic_aberration_effect");
/*  42 */     if (shouldRenderChromaticAberration()) {
/*     */       
/*  44 */       if (this.prevWidth != this.minecraft.m_91268_().m_85441_() || this.prevHeight != this.minecraft.m_91268_().m_85442_()) {
/*  45 */         this.aberrationEffect.m_110025_(this.minecraft.m_91268_().m_85441_(), this.minecraft.m_91268_().m_85442_());
/*     */       }
/*  47 */       ClientLevel world = this.minecraft.f_91073_;
/*  48 */       world.getCapability(WitherStormModClientCapabilities.FORMIDIBOMB_EFFECTS).ifPresent(effects -> {
/*     */             if (effects.getStartFuse() > 0) {
/*     */               float multiplier = (effects.getStartFuse() - effects.getLife()) / effects.getStartFuse();
/*     */               
/*     */               for (PostPass shader : getShaders()) {
/*     */                 shader.m_110074_().m_108960_("Multiplier").m_5985_(multiplier * 0.1F);
/*     */               }
/*     */               this.aberrationEffect.m_110023_(partialTicks);
/*     */               this.minecraft.m_91385_().m_83947_(false);
/*     */             } 
/*     */           });
/*     */     } 
/*  60 */     this.minecraft.m_91307_().m_7238_();
/*     */     
/*  62 */     this.prevWidth = this.minecraft.m_91268_().m_85441_();
/*  63 */     this.prevHeight = this.minecraft.m_91268_().m_85442_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initShader(ResourceManager manager) {
/*  68 */     if (this.aberrationEffect != null) {
/*  69 */       this.aberrationEffect.close();
/*     */     }
/*  71 */     this.aberrationEffect = null;
/*     */     
/*     */     try {
/*  74 */       this.aberrationEffect = new PostChain(this.minecraft.m_91097_(), manager, this.minecraft.m_91385_(), ABERRATION);
/*  75 */       this.aberrationEffect.m_110025_(this.minecraft.m_91268_().m_85441_(), this.minecraft.m_91268_().m_85442_());
/*     */     }
/*  77 */     catch (JsonSyntaxException e) {
/*     */       
/*  79 */       LOGGER.warn("Failed to parse shader: {}", ABERRATION, e);
/*     */     }
/*  81 */     catch (IOException e) {
/*     */       
/*  83 */       LOGGER.warn("Failed to load shader: {}", ABERRATION, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRenderChromaticAberration() {
/*  89 */     return (((Boolean)WitherStormModConfig.CLIENT.chromaticAberration.get()).booleanValue() && this.aberrationEffect != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6213_(ResourceManager resourceManager) {
/*  95 */     initShader(resourceManager);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PostPass> getShaders() {
/* 100 */     return ((IMixinPostChain)this.aberrationEffect).getPasses();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\shader\PostProcessingShaders.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */