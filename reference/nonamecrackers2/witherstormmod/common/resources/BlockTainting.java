/*     */ package nonamecrackers2.witherstormmod.common.resources;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraft.util.profiling.ProfilerFiller;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.resources.taint.SingleBlockTaintRecipe;
/*     */ import nonamecrackers2.witherstormmod.common.resources.taint.TagBasedTaintRecipe;
/*     */ import nonamecrackers2.witherstormmod.common.resources.taint.TaintRecipe;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTainting
/*     */   extends SimpleJsonResourceReloadListener
/*     */ {
/*  41 */   private static final Gson GSON = (new GsonBuilder()).create();
/*  42 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   @Nullable
/*     */   private Map<ResourceLocation, TaintRecipe> recipes;
/*     */   
/*     */   public BlockTainting() {
/*  47 */     super(GSON, "tainting/block");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
/*  53 */     Map<ResourceLocation, TaintRecipe> recipes = Maps.newHashMap();
/*  54 */     for (Map.Entry<ResourceLocation, JsonElement> entry : files.entrySet()) {
/*     */       
/*  56 */       ResourceLocation id = entry.getKey();
/*     */       
/*     */       try {
/*  59 */         JsonObject object = ((JsonElement)entry.getValue()).getAsJsonObject();
/*  60 */         MobEffect effect = null;
/*  61 */         if (object.has("potion_effect")) {
/*     */           
/*  63 */           ResourceLocation rawEffectId = ResourceLocation.m_135837_(GsonHelper.m_13906_(object, "potion_effect")).resultOrPartial(m -> { throw new JsonSyntaxException(m); }).get();
/*  64 */           effect = (MobEffect)ForgeRegistries.MOB_EFFECTS.getValue(rawEffectId);
/*  65 */           if (effect == null)
/*  66 */             throw new JsonSyntaxException("Unknown effect with id '" + rawEffectId + "'"); 
/*     */         } 
/*  68 */         BlockState replacement = BlockState.f_61039_.parse((DynamicOps)JsonOps.INSTANCE, GsonHelper.m_13930_(object, "replacement")).resultOrPartial(m -> { throw new JsonSyntaxException(m); }).get();
/*  69 */         List<Property<?>> properties = Lists.newArrayList();
/*  70 */         if (object.has("properties_to_copy")) {
/*     */           
/*  72 */           JsonArray propertiesToCopy = GsonHelper.m_13933_(object, "properties_to_copy");
/*  73 */           for (JsonElement element : propertiesToCopy) {
/*     */             
/*  75 */             String propertyName = GsonHelper.m_13805_(element, "property_name");
/*  76 */             boolean flag = true;
/*  77 */             for (Property<?> property : (Iterable<Property<?>>)replacement.m_61147_()) {
/*     */               
/*  79 */               if (property.m_61708_().equals(propertyName)) {
/*     */                 
/*  81 */                 if (properties.contains(property))
/*  82 */                   throw new IllegalStateException("Property is already specified: '" + propertyName + "'"); 
/*  83 */                 properties.add(property);
/*  84 */                 flag = false;
/*     */                 break;
/*     */               } 
/*     */             } 
/*  88 */             if (flag)
/*  89 */               throw new JsonSyntaxException("Unknown property with name '" + propertyName + "'"); 
/*     */           } 
/*     */         } 
/*  92 */         String blockEntry = GsonHelper.m_13906_(object, "block");
/*  93 */         if (blockEntry.startsWith("#")) {
/*     */           
/*  95 */           TagKey<Block> tag = TagKey.m_203882_(Registries.f_256747_, ResourceLocation.m_135837_(blockEntry.replace("#", "")).resultOrPartial(e -> {
/*     */                   throw new JsonSyntaxException(e);
/*  97 */                 }).get());
/*  98 */           recipes.put(id, new TagBasedTaintRecipe(tag, effect, replacement, properties));
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 104 */         ResourceLocation blockId = ResourceLocation.m_135837_(blockEntry).resultOrPartial(e -> { throw new JsonSyntaxException(e); }).get();
/* 105 */         if (!ForgeRegistries.BLOCKS.containsKey(blockId))
/* 106 */           throw new JsonSyntaxException("Unknown block with id '" + blockEntry + "'"); 
/* 107 */         Block block = (Block)ForgeRegistries.BLOCKS.getValue(blockId);
/* 108 */         recipes.put(id, new SingleBlockTaintRecipe(block, effect, replacement, properties));
/*     */       
/*     */       }
/* 111 */       catch (JsonSyntaxException|IllegalStateException e) {
/*     */         
/* 113 */         LOGGER.warn("Failed to read '" + id.toString() + "'", e);
/*     */       } 
/*     */     } 
/* 116 */     this.recipes = (Map<ResourceLocation, TaintRecipe>)recipes.entrySet().stream().sorted(Map.Entry.comparingByValue().reversed()).collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<ResourceLocation, TaintRecipe> getRecipes() {
/* 121 */     return Objects.<Map<ResourceLocation, TaintRecipe>>requireNonNull(this.recipes, "Recipes are not loaded");
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\resources\BlockTainting.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */