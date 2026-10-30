/*     */ package nonamecrackers2.witherstormmod.client.shader;
/*     */ 
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.PostChain;
/*     */ import net.minecraft.client.renderer.PostPass;
/*     */ import net.minecraft.resources.Identifier;
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
/*  25 */   public static final PostProcessingShaders INSTANCE = new PostProcessingShaders(Minecraft.getInstance());
/*  26 */   private static final Identifier ABERRATION = Identifier.fromNamespaceAndPath("witherstormmod", "shaders/post/aberration.json");
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
/*  41 */     this.minecraft.getProfiler().push("chromatic_aberration_effect");
/*  42 */     if (shouldRenderChromaticAberration()) {
/*     */       
/*  44 */       if (this.prevWidth != this.minecraft.getWindow().getWidth() || this.prevHeight != this.minecraft.getWindow().getHeight()) {
/*  45 */         this.aberrationEffect.resize(this.minecraft.getWindow().getWidth(), this.minecraft.getWindow().getHeight());
/*     */       }
/*  47 */       ClientLevel world = this.minecraft.level;
/*  48 */       world.getCapability(WitherStormModClientCapabilities.FORMIDIBOMB_EFFECTS).ifPresent(effects -> {
/*     */             if (effects.getStartFuse() > 0) {
/*     */               float multiplier = (effects.getStartFuse() - effects.getLife()) / effects.getStartFuse();
/*     */               
/*     */               for (PostPass shader : getShaders()) {
/*     */                 shader.getEffect().safeGetUniform("Multiplier").set(multiplier * 0.1F);
/*     */               }
/*     */               this.aberrationEffect.process(partialTicks);
/*     */               this.minecraft.getMainRenderTarget().bindWrite(false);
/*     */             } 
/*     */           });
/*     */     } 
/*  60 */     this.minecraft.getProfiler().pop();
/*     */     
/*  62 */     this.prevWidth = this.minecraft.getWindow().getWidth();
/*  63 */     this.prevHeight = this.minecraft.getWindow().getHeight();
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
/*  74 */       this.aberrationEffect = new PostChain(this.minecraft.getTextureManager(), manager, this.minecraft.getMainRenderTarget(), ABERRATION);
/*  75 */       this.aberrationEffect.resize(this.minecraft.getWindow().getWidth(), this.minecraft.getWindow().getHeight());
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
/*     */   public void onResourceManagerReload(ResourceManager resourceManager) {
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