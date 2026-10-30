/*     */ package nonamecrackers2.witherstormmod.client.util;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Contributors
/*     */ {
/*  28 */   public static final Logger LOGGER = LogManager.getLogger();
/*     */   private static final String CONTRIBUTORS_URL = "https://drive.google.com/uc?export=view&id=1GAFSqpBucUPP_d5QCEfBD7bwkSCRYU5O";
/*  30 */   private static final Gson GSON = new Gson();
/*     */   
/*     */   private static final boolean READ_FROM_DRIVE = false;
/*  33 */   private static List<String> DEVELOPERS = (List<String>)ImmutableList.of();
/*  34 */   private static List<String> PATRONS = (List<String>)ImmutableList.of();
/*  35 */   private static List<String> KOFI = (List<String>)ImmutableList.of();
/*     */   
/*     */   private static boolean IS_PATRON_ONLY_BUILD = false;
/*     */   
/*     */   private static List<String> builtInContributors;
/*     */   
/*     */   public static void getContributors() {
/*     */     try {
/*  43 */       ImmutableList.Builder<String> devs = ImmutableList.builder();
/*  44 */       ImmutableList.Builder<String> patrons = ImmutableList.builder();
/*  45 */       ImmutableList.Builder<String> kofi = ImmutableList.builder();
/*  46 */       readContributors(line -> {
/*     */             String[] split = line.split(Pattern.quote("."));
/*     */             
/*     */             if (split.length == 2) {
/*     */               if (split[0].equals("dev")) {
/*     */                 devs.add(split[1]);
/*     */               }
/*     */               if (split[0].equals("patron")) {
/*     */                 patrons.add(split[1]);
/*     */               }
/*     */               if (split[0].equals("kofi")) {
/*     */                 kofi.add(split[1]);
/*     */               }
/*     */             } else {
/*     */               LOGGER.warn("Entry must be of format 'type.username'");
/*     */             } 
/*     */           });
/*  63 */       DEVELOPERS = (List<String>)devs.build();
/*  64 */       PATRONS = (List<String>)patrons.build();
/*  65 */       KOFI = (List<String>)kofi.build();
/*     */     }
/*  67 */     catch (IOException e) {
/*     */       
/*  69 */       LOGGER.warn("Failed to fetch contributors.txt");
/*  70 */       e.printStackTrace();
/*     */     } 
/*     */   }
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
/*     */   private static void readContributors(Consumer<String> consumer) throws IOException {
/*  85 */     if (builtInContributors == null) {
/*     */       
/*  87 */       builtInContributors = Lists.newArrayList();
/*  88 */       builtInContributors.add("dev.Dev");
/*  89 */       builtInContributors.add("patron.Dev");
/*  90 */       builtInContributors.add("kofi.Dev");
/*  91 */       builtInContributors.add("dev.nonamecrackers2");
/*  92 */       builtInContributors.add("dev.WONazareth");
/*  93 */       builtInContributors.add("dev.TCBlizz707");
/*  94 */       builtInContributors.add("dev.TheFrostySoul");
/*  95 */       builtInContributors.add("patron.GEST2122");
/*  96 */       builtInContributors.add("patron.ThePandaMan69420");
/*  97 */       builtInContributors.add("patron.WizardOfZinks");
/*  98 */       builtInContributors.add("patron.WizardPilot7515");
/*  99 */       builtInContributors.add("patron.Nov1st2k10");
/* 100 */       builtInContributors.add("patron.McFella");
/* 101 */       builtInContributors.add("patron.Darkheart78");
/* 102 */       builtInContributors.add("patron.Kraken56YT");
/* 103 */       builtInContributors.add("patron.MDtheDemonmaster");
/* 104 */       builtInContributors.add("patron.MegaMathew200");
/* 105 */       builtInContributors.add("patron.LightningRod2441");
/* 106 */       builtInContributors.add("patron.Tordx_");
/* 107 */       builtInContributors.add("patron.Yamper1163");
/* 108 */       builtInContributors.add("patron.SmoothWolf20");
/* 109 */       builtInContributors.add("patron.sonicfan9353");
/* 110 */       builtInContributors.add("patron.brettge");
/* 111 */       builtInContributors.add("patron.Doggyboy200");
/* 112 */       builtInContributors.add("patron.itsOutares");
/* 113 */       builtInContributors.add("patron.Tibus0119");
/* 114 */       builtInContributors.add("patron.DaSenate");
/* 115 */       builtInContributors.add("patron.Romeo_The_Admin");
/* 116 */       builtInContributors.add("patron.MRN22");
/* 117 */       builtInContributors.add("patron.DairyCamera");
/* 118 */       builtInContributors.add("patron.minecrafthero11");
/* 119 */       builtInContributors.add("patron.Kingofcheeze");
/* 120 */       builtInContributors.add("patron.Malolve");
/* 121 */       builtInContributors.add("patron.EuroControl");
/* 122 */       builtInContributors.add("patron.RayJasi11");
/* 123 */       builtInContributors.add("patron.BLU3_ZOMB1EX");
/* 124 */       builtInContributors.add("patron.NOTNA9015");
/* 125 */       builtInContributors.add("patron.Auxi0us");
/* 126 */       builtInContributors.add("patron.Joebiben1");
/* 127 */       builtInContributors.add("patron.RelliGin");
/* 128 */       builtInContributors.add("patron.RippedRumble806");
/* 129 */       builtInContributors.add("patron._ThatBoi_");
/* 130 */       builtInContributors.add("kofi.WONazareth");
/* 131 */       builtInContributors.add("kofi.nonamecrackers2");
/* 132 */       builtInContributors.add("kofi.Parm_on_John");
/* 133 */       builtInContributors.add("kofi.Shadow_Parad0x");
/* 134 */       builtInContributors.add("kofi.Doggyboy200");
/* 135 */       builtInContributors.add("kofi.GEST2122");
/* 136 */       builtInContributors.add("kofi.Tibus0119");
/* 137 */       builtInContributors.add("kofi.DE4D_Gamez");
/*     */     } 
/*     */     
/* 140 */     for (String s : builtInContributors) {
/* 141 */       consumer.accept(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isDeveloper(String name) {
/* 147 */     return DEVELOPERS.contains(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPatron(String name) {
/* 152 */     return PATRONS.contains(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isKofi(String name) {
/* 157 */     return KOFI.contains(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean currentPlayerHasCosmetic() {
/* 162 */     Minecraft mc = Minecraft.m_91087_();
/* 163 */     String name = mc.m_91094_().m_92546_();
/* 164 */     return (isDeveloper(name) || isPatron(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static Result getAccess(String uuid) {
/* 172 */     if (IS_PATRON_ONLY_BUILD && FMLEnvironment.production) {
/*     */       try {
/*     */         Reader streamReader;
/*     */         
/* 176 */         URL url = new URL("https://patronauthenticator-sp4uwbgqwa-uc.a.run.app/validate?mc_uuid=" + uuid);
/* 177 */         HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/* 178 */         connection.setRequestMethod("GET");
/* 179 */         connection.setConnectTimeout(5000);
/* 180 */         connection.setReadTimeout(5000);
/* 181 */         int status = connection.getResponseCode();
/*     */         
/* 183 */         if (status >= 300) {
/* 184 */           streamReader = new InputStreamReader(connection.getErrorStream());
/*     */         } else {
/* 186 */           streamReader = new InputStreamReader(connection.getInputStream());
/* 187 */         }  BufferedReader in = new BufferedReader(streamReader);
/* 188 */         Result result = Result.NOT_VALIDATED;
/*     */         String line;
/* 190 */         while ((line = in.readLine()) != null) {
/*     */           
/* 192 */           JsonObject object = (JsonObject)GSON.fromJson(line, JsonObject.class);
/* 193 */           LOGGER.info("Received: " + object);
/* 194 */           if (object.has("result")) {
/*     */             
/* 196 */             if (GsonHelper.m_13906_(object, "result").equals("validated")) {
/*     */               
/* 198 */               result = Result.VALIDATED;
/*     */               
/*     */               break;
/*     */             } 
/*     */             continue;
/*     */           } 
/* 204 */           result = Result.ERROR;
/*     */         } 
/*     */ 
/*     */         
/* 208 */         in.close();
/* 209 */         connection.disconnect();
/* 210 */         return result;
/*     */       }
/* 212 */       catch (Exception e) {
/*     */         
/* 214 */         LOGGER.error("Failed to validate user", e);
/* 215 */         return Result.ERROR;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 220 */     return Result.NO_RESTRICTIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public enum Result
/*     */   {
/* 226 */     VALIDATED(true),
/* 227 */     NO_RESTRICTIONS(true),
/* 228 */     NOT_VALIDATED(false),
/* 229 */     ERROR(false);
/*     */     
/*     */     private boolean canLaunchGame;
/*     */ 
/*     */     
/*     */     Result(boolean canLaunchGame) {
/* 235 */       this.canLaunchGame = canLaunchGame;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canLaunchGame() {
/* 240 */       return this.canLaunchGame;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\Contributors.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */