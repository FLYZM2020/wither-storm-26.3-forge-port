/*     */ package nonamecrackers2.witherstormmod.client.resources;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.awt.Color;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraft.util.profiling.ProfilerFiller;
/*     */ import nonamecrackers2.witherstormmod.client.resources.color.ColorSet;
/*     */ import nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet;
/*     */ import nonamecrackers2.witherstormmod.client.resources.texture.TextureSet;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormResourceConfigManager
/*     */   extends SimpleJsonResourceReloadListener
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger("witherstormmod/WitherStormResourceConfigManager");
/*  31 */   private static final Gson GSON = (new GsonBuilder()).create();
/*     */   private static final int COLOR_CONFIG_FORMAT_VERSION = 2;
/*     */   private static final int TEXTURES_FORMAT_VERSION = 1;
/*  34 */   public static final WitherStormResourceConfigManager INSTANCE = new WitherStormResourceConfigManager();
/*  35 */   private Int2ObjectMap<ColorSet> colors = (Int2ObjectMap<ColorSet>)new Int2ObjectOpenHashMap();
/*     */   private Optional<Color> bowelsFogColor;
/*  37 */   private Int2ObjectMap<TextureSet> textures = (Int2ObjectMap<TextureSet>)new Int2ObjectOpenHashMap();
/*     */ 
/*     */   
/*     */   public WitherStormResourceConfigManager() {
/*  41 */     super(GSON, "config");
/*  42 */     defaultColors();
/*  43 */     defaultTextures();
/*     */   }
/*     */ 
/*     */   
/*     */   private void defaultColors() {
/*  48 */     for (int i = 0; i <= 7; i++)
/*  49 */       this.colors.put(i, ColorSet.DEFAULT); 
/*  50 */     this.bowelsFogColor = Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   private void defaultTextures() {
/*  55 */     for (int i = 0; i <= 7; i++) {
/*  56 */       this.textures.put(i, TextureSet.DEFAULT);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
/*  62 */     Int2ObjectOpenHashMap int2ObjectOpenHashMap1 = new Int2ObjectOpenHashMap();
/*  63 */     Int2ObjectOpenHashMap int2ObjectOpenHashMap2 = new Int2ObjectOpenHashMap();
/*     */     
/*     */     try {
/*  66 */       for (Map.Entry<ResourceLocation, JsonElement> entry : files.entrySet()) {
/*     */         int version; ColorSet.Builder defaultColors; int i, j;
/*  68 */         JsonObject obj = ((JsonElement)entry.getValue()).getAsJsonObject();
/*  69 */         switch (((ResourceLocation)entry.getKey()).m_135815_()) {
/*     */ 
/*     */           
/*     */           case "colors":
/*  73 */             version = 1;
/*  74 */             if (obj.has("format_version"))
/*  75 */               version = GsonHelper.m_13927_(obj, "format_version"); 
/*  76 */             if (version < 2) {
/*  77 */               LOGGER.info("Your colors.json file is out of date. Please update in order for your colors.json file to work correctly. More info here: https://github.com/nonamecrackers2/crackers-wither-storm-mod/wiki/Advanced-Modification");
/*     */             }
/*  79 */             if (obj.has("bowels_fog")) {
/*  80 */               this.bowelsFogColor = Optional.of(colorFromJsonNoAlpha(GsonHelper.m_13930_(obj, "bowels_fog")));
/*     */             } else {
/*  82 */               this.bowelsFogColor = Optional.empty();
/*     */             } 
/*  84 */             LOGGER.debug("Populating default color sets for all phases");
/*     */             
/*  86 */             defaultColors = ColorSet.builder();
/*  87 */             populateColorSetBuilder(defaultColors, obj);
/*  88 */             int2ObjectOpenHashMap1.put(0, defaultColors);
/*     */             
/*  90 */             for (j = 1; j <= 7; j++) {
/*  91 */               int2ObjectOpenHashMap1.put(j, defaultColors.copy());
/*     */             }
/*     */             
/*  94 */             if (obj.has("sky_colors")) {
/*     */               
/*  96 */               if (version < 2) {
/*     */                 
/*  98 */                 LOGGER.debug("Doing compat stuff with older formats");
/*     */                 
/* 100 */                 JsonObject phases = GsonHelper.m_13930_(obj, "sky_colors");
/* 101 */                 for (int k = 5; k <= 7; k++)
/*     */                 {
/* 103 */                   JsonObject phase = GsonHelper.m_13930_(phases, String.valueOf(k));
/* 104 */                   ((ColorSet.Builder)int2ObjectOpenHashMap1.get(k)).setSkyColors(getColorSetFromJson(phase));
/*     */                 }
/*     */               
/*     */               } else {
/*     */                 
/* 109 */                 LOGGER.info("Looks like you're trying to use an older format for the sky color set with the newer format version. Please see the updated format: https://github.com/nonamecrackers2/crackers-wither-storm-mod/wiki/Advanced-Modification");
/*     */               }
/*     */             
/* 112 */             } else if (obj.has("by_phase")) {
/*     */               
/* 114 */               LOGGER.debug("Populating per-phase color configuration");
/*     */               
/* 116 */               JsonObject byPhase = GsonHelper.m_13930_(obj, "by_phase");
/* 117 */               int2ObjectOpenHashMap1.int2ObjectEntrySet().forEach(e -> {
/*     */                     String phaseStr = String.valueOf(e.getIntKey());
/*     */                     if (byPhase.has(phaseStr)) {
/*     */                       LOGGER.debug("Found phase: {}", Integer.valueOf(e.getIntKey()));
/*     */                       populateColorSetBuilder((ColorSet.Builder)e.getValue(), GsonHelper.m_13930_(byPhase, phaseStr));
/*     */                     } 
/*     */                   });
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case "textures":
/* 130 */             version = 1;
/* 131 */             if (obj.has("format_version"))
/* 132 */               version = GsonHelper.m_13927_(obj, "format_version"); 
/* 133 */             if (version < 1) {
/* 134 */               LOGGER.info("Your textures.json file is out of date. Please update in order for your textures.json file to work correctly. More info here: https://github.com/nonamecrackers2/crackers-wither-storm-mod/wiki/Advanced-Modification");
/*     */             }
/* 136 */             for (i = 0; i <= 7; i++) {
/*     */               
/* 138 */               String phaseStr = String.valueOf(i);
/* 139 */               if (obj.has(phaseStr)) {
/*     */                 
/* 141 */                 JsonObject phase = GsonHelper.m_13930_(obj, phaseStr);
/* 142 */                 int2ObjectOpenHashMap2.put(i, TextureSet.fromJson(phase));
/*     */               }
/*     */               else {
/*     */                 
/* 146 */                 int2ObjectOpenHashMap2.put(i, TextureSet.DEFAULT);
/*     */               } 
/*     */             } 
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 154 */     } catch (JsonSyntaxException e) {
/*     */       
/* 156 */       LOGGER.warn("Failed to read 'colors.json'", (Throwable)e);
/*     */     } 
/*     */     
/* 159 */     if (int2ObjectOpenHashMap1.isEmpty()) {
/*     */       
/* 161 */       defaultColors();
/*     */     }
/*     */     else {
/*     */       
/* 165 */       Int2ObjectOpenHashMap int2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/* 166 */       for (ObjectIterator<Int2ObjectMap.Entry<ColorSet.Builder>> objectIterator = int2ObjectOpenHashMap1.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<ColorSet.Builder> entry = objectIterator.next();
/* 167 */         int2ObjectOpenHashMap.put(entry.getIntKey(), ((ColorSet.Builder)entry.getValue()).build()); }
/* 168 */        this.colors = (Int2ObjectMap<ColorSet>)int2ObjectOpenHashMap;
/* 169 */       LOGGER.info("Found a colors.json file");
/*     */     } 
/* 171 */     if (int2ObjectOpenHashMap2.isEmpty()) {
/*     */       
/* 173 */       defaultTextures();
/*     */     }
/*     */     else {
/*     */       
/* 177 */       this.textures = (Int2ObjectMap<TextureSet>)int2ObjectOpenHashMap2;
/* 178 */       LOGGER.info("Found a textures.json file");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ColorSet getColorSetByPhase(int phase) {
/* 184 */     assertIsWithinPhaseRange(phase);
/* 185 */     return (ColorSet)this.colors.get(phase);
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureSet getTextureSetByPhase(int phase) {
/* 190 */     assertIsWithinPhaseRange(phase);
/* 191 */     return (TextureSet)this.textures.get(phase);
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<Color> getBowelsFogColor() {
/* 196 */     return this.bowelsFogColor;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void assertIsWithinPhaseRange(int phase) {
/* 201 */     if (phase < 0 || phase > 7) {
/* 202 */       throw new IllegalArgumentException("Phase outside of range: 0 ~ 7");
/*     */     }
/*     */   }
/*     */   
/*     */   private static void populateColorSetBuilder(ColorSet.Builder builder, JsonObject obj) throws JsonSyntaxException {
/* 207 */     if (obj.has("tractor_beams")) {
/*     */       
/* 209 */       builder.setTractorBeamColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(obj, "tractor_beams")));
/* 210 */       LOGGER.debug("Found tractor beam color");
/*     */     } 
/* 212 */     if (obj.has("tractor_beams_night")) {
/*     */       
/* 214 */       builder.setTractorBeamNightColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(obj, "tractor_beams_night")));
/* 215 */       LOGGER.debug("Found night time tractor beam color");
/*     */     } 
/* 217 */     if (obj.has("night_shine")) {
/*     */       
/* 219 */       builder.setNightShineColor(colorFromJsonAlpha(GsonHelper.m_13930_(obj, "night_shine")));
/* 220 */       LOGGER.debug("Found night shine color");
/*     */     } 
/* 222 */     builder.setSkyColors(getColorSetFromJson(obj));
/*     */   }
/*     */ 
/*     */   
/*     */   private static Color colorFromJsonNoAlpha(JsonObject object) throws JsonSyntaxException {
/* 227 */     int r = GsonHelper.m_13927_(object, "red");
/* 228 */     int g = GsonHelper.m_13927_(object, "green");
/* 229 */     int b = GsonHelper.m_13927_(object, "blue");
/* 230 */     return new Color(r, g, b);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Color colorFromJsonAlpha(JsonObject object) throws JsonSyntaxException {
/* 235 */     int r = GsonHelper.m_13927_(object, "red");
/* 236 */     int g = GsonHelper.m_13927_(object, "green");
/* 237 */     int b = GsonHelper.m_13927_(object, "blue");
/* 238 */     int a = 255;
/* 239 */     if (object.has("alpha"))
/* 240 */       a = GsonHelper.m_13927_(object, "alpha"); 
/* 241 */     return new Color(r, g, b, a);
/*     */   }
/*     */ 
/*     */   
/*     */   private static SkyColorSet getColorSetFromJson(JsonObject object) throws JsonSyntaxException {
/* 246 */     SkyColorSet.Builder builder = SkyColorSet.builder();
/* 247 */     if ((object.has("night") && !object.has("day")) || (!object.has("night") && object.has("day")))
/* 248 */       throw new JsonSyntaxException("Must contain both 'day' and 'night' entries, not just one"); 
/* 249 */     if (object.has("night") && object.has("day")) {
/*     */       
/* 251 */       LOGGER.debug("Found day and night sky color set");
/*     */       
/* 253 */       JsonObject night = GsonHelper.m_13930_(object, "night");
/* 254 */       JsonObject day = GsonHelper.m_13930_(object, "day");
/*     */       
/* 256 */       if (day.has("sky_darken"))
/* 257 */         builder.setSkyColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(day, "sky_darken"))); 
/* 258 */       if (day.has("sky_darken_clouds"))
/* 259 */         builder.setCloudColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(day, "sky_darken_clouds"))); 
/* 260 */       if (day.has("sky_darken_fog")) {
/* 261 */         builder.setFogColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(day, "sky_darken_fog")));
/*     */       }
/* 263 */       if (night.has("sky_darken"))
/* 264 */         builder.setNightSkyColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(night, "sky_darken"))); 
/* 265 */       if (night.has("sky_darken_clouds"))
/* 266 */         builder.setNightCloudColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(night, "sky_darken_clouds"))); 
/* 267 */       if (night.has("sky_darken_fog")) {
/* 268 */         builder.setNightFogColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(night, "sky_darken_fog")));
/*     */       }
/*     */     } else {
/*     */       
/* 272 */       LOGGER.debug("Found sky color set");
/*     */       
/* 274 */       if (object.has("sky_darken"))
/* 275 */         builder.setSkyColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(object, "sky_darken"))); 
/* 276 */       if (object.has("sky_darken_clouds"))
/* 277 */         builder.setCloudColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(object, "sky_darken_clouds"))); 
/* 278 */       if (object.has("sky_darken_fog"))
/* 279 */         builder.setFogColor(colorFromJsonNoAlpha(GsonHelper.m_13930_(object, "sky_darken_fog"))); 
/*     */     } 
/* 281 */     return builder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\resources\WitherStormResourceConfigManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */