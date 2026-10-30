/*    */ package nonamecrackers2.witherstormmod.common.resources;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
/*    */ import net.minecraft.util.GsonHelper;
/*    */ import net.minecraft.util.profiling.ProfilerFiller;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import nonamecrackers2.witherstormmod.common.resources.taint.MobConversion;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MobConversions
/*    */   extends SimpleJsonResourceReloadListener
/*    */ {
/* 30 */   private static final Gson GSON = (new GsonBuilder()).create();
/* 31 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   @Nullable
/*    */   private Map<ResourceLocation, MobConversion> mobConversions;
/*    */   
/*    */   public MobConversions() {
/* 36 */     super(GSON, "tainting/entity");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
/* 42 */     Map<ResourceLocation, MobConversion> conversions = Maps.newHashMap();
/* 43 */     for (Map.Entry<ResourceLocation, JsonElement> entry : files.entrySet()) {
/*    */       
/* 45 */       ResourceLocation id = entry.getKey();
/*    */       
/*    */       try {
/* 48 */         JsonObject obj = ((JsonElement)entry.getValue()).getAsJsonObject();
/* 49 */         EntityType<?> from = parseEntityType(GsonHelper.m_13906_(obj, "from"));
/* 50 */         if (conversions.values().stream().anyMatch(c -> c.from().equals(from)))
/* 51 */           LOGGER.warn("Duplicate mob conversion detected: {} has been registered multiple times", from); 
/* 52 */         EntityType<?> to = parseEntityType(GsonHelper.m_13906_(obj, "to"));
/* 53 */         boolean canBeConvertedFromWitherSickness = GsonHelper.m_13912_(obj, "convert_from_sickness");
/* 54 */         conversions.put(id, new MobConversion(from, to, canBeConvertedFromWitherSickness));
/*    */       }
/* 56 */       catch (JsonSyntaxException|IllegalStateException e) {
/*    */         
/* 58 */         LOGGER.warn("Failed to read '" + id.toString() + "'", e);
/*    */       } 
/*    */     } 
/* 61 */     this.mobConversions = (Map<ResourceLocation, MobConversion>)ImmutableMap.copyOf(conversions);
/*    */   }
/*    */ 
/*    */   
/*    */   private static EntityType<?> parseEntityType(String rawId) throws JsonSyntaxException {
/* 66 */     ResourceLocation id = ResourceLocation.m_135820_(rawId);
/* 67 */     if (id == null)
/* 68 */       throw new JsonSyntaxException("Not a valid id: '" + rawId + "'"); 
/* 69 */     if (!ForgeRegistries.ENTITY_TYPES.containsKey(id))
/* 70 */       throw new JsonSyntaxException("Unknown entity with id '" + rawId + "'"); 
/* 71 */     return (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<ResourceLocation, MobConversion> getConversions() {
/* 76 */     return Objects.<Map<ResourceLocation, MobConversion>>requireNonNull(this.mobConversions, "Mob conversions are not loaded");
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\resources\MobConversions.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */