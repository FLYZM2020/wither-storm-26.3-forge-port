/*     */ package nonamecrackers2.witherstormmod.client.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Scanner;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ import net.minecraftforge.forgespi.locating.IModFile;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class WitherStormModelDecoder
/*     */ {
/*  17 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private static final int VERSION = 0;
/*     */   
/*     */   public static void load(String id, CubeListBuilder builder, CubeDeformation def, float texScale) {
/*  22 */     IModFile modFile = ModList.get().getModFileById("witherstormmod").getFile();
/*  23 */     Path path = modFile.findResource(new String[] { "assets/witherstormmod/models/witherstorm/" + id + ".wsm" });
/*  24 */     File file = new File(path.toString());
/*     */     
/*     */     try {
/*  27 */       Scanner scanner = new Scanner(file);
/*  28 */       boolean checkedVersion = false;
/*  29 */       while (scanner.hasNextLine()) {
/*     */         
/*  31 */         String line = scanner.nextLine();
/*  32 */         if (!checkedVersion) {
/*     */           
/*  34 */           if (line.startsWith("v")) {
/*     */ 
/*     */             
/*     */             try {
/*  38 */               if (Integer.valueOf(line.split("v")[0]).intValue() == 0) {
/*     */                 
/*  40 */                 checkedVersion = true;
/*     */                 
/*     */                 continue;
/*     */               } 
/*  44 */               LOGGER.error("Outdated model version, please update to {}", Integer.valueOf(0));
/*     */ 
/*     */             
/*     */             }
/*  48 */             catch (Exception e) {
/*     */               
/*  50 */               LOGGER.error("Failed to parse version for '{}'", id);
/*  51 */               e.printStackTrace();
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/*  57 */           LOGGER.error("Invalid version string. Must be of format 'v{version}'");
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/*  62 */         String[] functions = line.split(";");
/*  63 */         for (String function : functions) {
/*     */ 
/*     */           
/*     */           try {
/*  67 */             if (function.startsWith("texOffs"))
/*     */             {
/*  69 */               String param = function.replaceAll("texOffs(", "").replaceAll(")", "");
/*  70 */               String[] values = param.split(",");
/*  71 */               int texX = Integer.valueOf(values[0]).intValue();
/*  72 */               int texY = Integer.valueOf(values[1]).intValue();
/*  73 */               builder.m_171514_(texX, texY);
/*     */             }
/*  75 */             else if (function.startsWith("addBox"))
/*     */             {
/*  77 */               String param = function.replaceAll("addBox(", "").replaceAll(")", "");
/*  78 */               String[] values = param.split(",");
/*  79 */               float f = Float.valueOf(values[0]).floatValue();
/*  80 */               float f1 = Float.valueOf(values[0]).floatValue();
/*  81 */               float f2 = Float.valueOf(values[0]).floatValue();
/*  82 */               float f3 = Float.valueOf(values[0]).floatValue();
/*  83 */               float f4 = Float.valueOf(values[0]).floatValue();
/*  84 */               float f5 = Float.valueOf(values[0]).floatValue();
/*  85 */               builder.m_171496_(f, f1, f2, f3, f4, f5, def, texScale, texScale);
/*     */             }
/*     */             else
/*     */             {
/*  89 */               LOGGER.warn("Unknown function '{}'. Ignoring", function);
/*     */             }
/*     */           
/*  92 */           } catch (Exception e) {
/*     */             
/*  94 */             LOGGER.error("Failed to parse function");
/*  95 */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 100 */       scanner.close();
/*     */     }
/* 102 */     catch (FileNotFoundException e) {
/*     */       
/* 104 */       LOGGER.warn("Failed to find model with id '{}'", id);
/* 105 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\WitherStormModelDecoder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */