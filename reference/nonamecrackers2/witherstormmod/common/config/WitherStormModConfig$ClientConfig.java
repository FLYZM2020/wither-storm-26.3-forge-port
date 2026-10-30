/*     */ package nonamecrackers2.witherstormmod.common.config;
/*     */ 
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ import nonamecrackers2.crackerslib.common.config.ConfigHelper;
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
/*     */ public class ClientConfig
/*     */   extends ConfigHelper
/*     */ {
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderDebrisCloud;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderDebrisRings;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderDistantDebris;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> witherStormLOD;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> lowResModels;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderTractorBeams;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderPulse;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> distantRenderer;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> blockClusterRendering;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> witherSicknessLayer;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> playWitherStormTheme;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> playSymbiontTheme;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> chromaticAberration;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> blindingEffects;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> cameraShakeEffects;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> distantFog;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> earRingingEffects;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderTractorBeamOverlay;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderSkyAmbienceEffects;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> tractorBeamParticles;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderShine;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> optifineWarning;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> aprilFools;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> vertexBufferRendering;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> patronCosmetic;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> customPanorama;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> asyncBufferBuilders;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> hideDebrisRingsUntilSplit;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> playMinecraftMusic;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> renderEmissiveDecalForHeads;
/*     */   
/*     */   private ClientConfig(ForgeConfigSpec.Builder builder) {
/* 142 */     super(builder, "witherstormmod");
/*     */     
/* 144 */     builder.comment("Client options").push("client");
/*     */     
/* 146 */     this.playMinecraftMusic = createValue(Boolean.valueOf(true), "playMinecraftMusic", false, "Specifies if Minecraft music should play at all. Used for if you want the boss music to play but not the Minecraft music");
/*     */     
/* 148 */     builder.comment("Compatibility").push("compatibility");
/*     */ 
/*     */ 
/*     */     
/* 152 */     this.distantRenderer = createValue(Boolean.valueOf(true), "distantRenderer", false, "The distant renderer allows for the Wither Storm to be rendered from much greater distances than what vanilla allows. Disable if you're facing issues with it");
/*     */     
/* 154 */     this.witherSicknessLayer = createValue(Boolean.valueOf(true), "witherSicknessLayer", true, "Specifies if an overlay should be applied to entities that renders wither sickness");
/*     */     
/* 156 */     builder.pop();
/*     */     
/* 158 */     builder.comment("Accessibility").push("accessibility");
/*     */     
/* 160 */     this.chromaticAberration = createValue(Boolean.valueOf(true), "chromaticAberration", false, "Toggle to enable/disable the chromatic aberration effect");
/*     */     
/* 162 */     this.blindingEffects = createValue(Boolean.valueOf(true), "blindingEffects", false, "Specifies whether or not a white overlay should cover the screen during certain events");
/*     */     
/* 164 */     this.cameraShakeEffects = createValue(Boolean.valueOf(true), "cameraShakeEffects", false, "Specifies whether or not camera shake effects should be used");
/*     */     
/* 166 */     this.earRingingEffects = createValue(Boolean.valueOf(true), "earRingingEffects", false, "Turn off to disable the ear ringing effects used in the mod");
/*     */     
/* 168 */     builder.pop();
/*     */     
/* 170 */     builder.comment("Preference").push("preference");
/*     */     
/* 172 */     this.renderDebrisCloud = createValue(Boolean.valueOf(true), "renderDebrisCloud", false, "Toggles the rendering of the debris cloud surrounding the Wither Storm");
/*     */     
/* 174 */     this.renderDebrisRings = createValue(Boolean.valueOf(true), "renderDebrisRings", false, "Toggles the rendering of debris rings that surround the Wither Storm (much more performant than the debris cloud)");
/*     */     
/* 176 */     this.renderTractorBeams = createValue(Boolean.valueOf(true), "renderTractorBeams", false, "Toggles the rendering of the tractor beams");
/*     */     
/* 178 */     this.renderTractorBeamOverlay = createValue(Boolean.valueOf(true), "renderTractorBeamOverlay", false, "Turn off to disable the overlay that appears when inside a tractor beam");
/*     */     
/* 180 */     this.renderSkyAmbienceEffects = createValue(Boolean.valueOf(true), "renderSkyAmbienceEffects", false, "Specifies if sky ambience affects (sky darkening) should render when a Wither Storm is nearby");
/*     */     
/* 182 */     this.tractorBeamParticles = createValue(Boolean.valueOf(true), "tractorBeamParticles", false, "Specifies if particles inside the tractor beams of the Wither Storm should render");
/*     */     
/* 184 */     this.distantFog = createValue(Boolean.valueOf(true), "distantFog", false, "Specifies if fog should be applied to Wither Storms being rendered from a distance");
/*     */     
/* 186 */     this.renderShine = createValue(Boolean.valueOf(true), "renderShine", false, "Specifies if a purple shine should render behind the Wither Storm at night");
/*     */     
/* 188 */     this.renderPulse = createValue(Boolean.valueOf(true), "renderPulse", false, "Specifies if a pulsating effect, mimicking endermen pulling the Wither Storm apart, should be rendered once its evolution is complete");
/*     */     
/* 190 */     this.hideDebrisRingsUntilSplit = createValue(Boolean.valueOf(false), "hideDebrisRingsUntilSplit", false, "Hides the debris rings until the Wither Storm has split (phase 6)");
/*     */     
/* 192 */     this.renderEmissiveDecalForHeads = createValue(Boolean.valueOf(true), "renderEmissiveDecalForHeads", false, "Specifies if the emissive decal (eyes and teeth) for the Wither Storm heads should be rendered");
/*     */     
/* 194 */     builder.pop();
/*     */     
/* 196 */     builder.comment("Boss music").push("boss_music");
/*     */     
/* 198 */     this.playWitherStormTheme = createValue(Boolean.valueOf(false), "playWitherStormTheme", false, "Toggles the Wither Storm boss theme. NOTE: this theme is from MC:SM and is subject to copyright");
/*     */     
/* 200 */     this.playSymbiontTheme = createValue(Boolean.valueOf(true), "playSymbiontTheme", false, "Toggles the Withered Symbiont theme, created for CWSM by Mar Mar");
/*     */     
/* 202 */     builder.pop();
/*     */     
/* 204 */     builder.comment("Instancing").push("instancing");
/*     */     
/* 206 */     this.vertexBufferRendering = createValue(Boolean.valueOf(true), "vertexBufferRendering", false, "Specifies if CWSM should use more performant rendering when rendering Block Clusters and the Wither Storm's mass. NOTE: It is not recommended to disable this option unless necessary");
/*     */     
/* 208 */     this.asyncBufferBuilders = createValue(Boolean.valueOf(true), "asyncBufferBuilders", false, "Builds instanced vertex buffers off thread to help reduce stuttering. It is only recommended to disable this if facing compatibility issues");
/*     */     
/* 210 */     builder.pop();
/*     */     
/* 212 */     builder.comment("Performance").push("performance");
/*     */     
/* 214 */     this.renderDistantDebris = createValue(Boolean.valueOf(true), "renderDistantDebris", false, "Setting this value to false will disable the debris cloud when rendering the Wither Storm from large distances");
/*     */     
/* 216 */     this.witherStormLOD = createValue(Boolean.valueOf(false), "witherStormLOD", false, "Specifies if the low res models should be used when being rendered via the distant renderer");
/*     */     
/* 218 */     this.lowResModels = createValue(Boolean.valueOf(false), "lowResModels", false, "Uses larger cubes to make up the the phase 4 and up models. May result in a performance increase");
/*     */     
/* 220 */     this.blockClusterRendering = createValue(Boolean.valueOf(true), "blockClusterRendering", false, "Toggles the rendering of Block Clusters");
/*     */     
/* 222 */     builder.pop();
/*     */     
/* 224 */     this.optifineWarning = createValue(Boolean.valueOf(true), "optifineWarning", false, "Toggles the OptiFine warning upon joining a world");
/*     */     
/* 226 */     this.aprilFools = createValue(Boolean.valueOf(true), "aprilFools", false, "Toggles April Fools special effects");
/*     */     
/* 228 */     this.patronCosmetic = createValue(Boolean.valueOf(true), "patronCosmetic", false, "Toggles the patron cosmetic for nonamecrackers2's Wither Storm backers");
/*     */     
/* 230 */     this.customPanorama = createValue(Boolean.valueOf(true), "customPanorama", false, "Toggles the custom main menu panorama added by the mod");
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
/* 277 */     builder.pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\config\WitherStormModConfig$ClientConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */