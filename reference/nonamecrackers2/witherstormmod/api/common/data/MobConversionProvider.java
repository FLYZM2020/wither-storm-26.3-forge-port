/*    */ package nonamecrackers2.witherstormmod.api.common.data;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.nio.file.Path;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.data.CachedOutput;
/*    */ import net.minecraft.data.DataProvider;
/*    */ import net.minecraft.data.PackOutput;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import nonamecrackers2.witherstormmod.common.resources.taint.MobConversion;
/*    */ 
/*    */ public abstract class MobConversionProvider
/*    */   implements DataProvider
/*    */ {
/* 21 */   private final Map<EntityType<?>, MobConversion> conversions = Maps.newHashMap();
/*    */   
/*    */   private final Path outputPath;
/*    */   
/*    */   public MobConversionProvider(PackOutput output, String modid) {
/* 26 */     this.outputPath = output.m_247566_(PackOutput.Target.DATA_PACK).resolve(modid).resolve("tainting/entity");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void add(MobConversion conversion) {
/* 33 */     if (this.conversions.containsKey(conversion.from()))
/* 34 */       throw new IllegalArgumentException("Type '" + conversion.from() + "' is already mapped"); 
/* 35 */     this.conversions.put(conversion.from(), conversion);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void add(EntityType<? extends Mob> from, EntityType<? extends Mob> to, boolean convertFromWitherSickness) {
/* 40 */     add(new MobConversion(from, to, convertFromWitherSickness));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void add(EntityType<? extends Mob> from, EntityType<? extends Mob> to) {
/* 45 */     add(from, to, true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompletableFuture<?> m_213708_(CachedOutput output) {
/* 51 */     addConversions();
/* 52 */     return CompletableFuture.allOf((CompletableFuture<?>[])this.conversions.values().stream().map(conversion -> {
/*    */             JsonObject object = new JsonObject();
/*    */             ResourceLocation from = ForgeRegistries.ENTITY_TYPES.getKey(conversion.from());
/*    */             ResourceLocation to = ForgeRegistries.ENTITY_TYPES.getKey(conversion.to());
/*    */             object.addProperty("from", from.toString());
/*    */             object.addProperty("to", to.toString());
/*    */             object.addProperty("convert_from_sickness", Boolean.valueOf(conversion.canBeConvertedFromWitherSickness()));
/*    */             return DataProvider.m_253162_(output, (JsonElement)object, this.outputPath.resolve(from.m_135815_() + "_to_" + from.m_135815_() + ".json"));
/* 60 */           }).toArray(i -> new CompletableFuture[i]));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String m_6055_() {
/* 68 */     return "Mob conversions";
/*    */   }
/*    */   
/*    */   protected abstract void addConversions();
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\data\MobConversionProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */