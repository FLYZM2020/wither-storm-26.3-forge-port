/*     */ package nonamecrackers2.witherstormmod.api.common.data;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.nio.file.Path;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import net.minecraft.data.CachedOutput;
/*     */ import net.minecraft.data.DataProvider;
/*     */ import net.minecraft.data.PackOutput;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.resources.taint.SingleBlockTaintRecipe;
/*     */ import nonamecrackers2.witherstormmod.common.resources.taint.TagBasedTaintRecipe;
/*     */ import nonamecrackers2.witherstormmod.common.resources.taint.TaintRecipe;
/*     */ 
/*     */ public abstract class BlockTaintingRecipeProvider
/*     */   implements DataProvider {
/*  27 */   private final List<TaintRecipe> recipes = Lists.newArrayList();
/*     */   
/*     */   private final Path outputPath;
/*     */   
/*     */   public BlockTaintingRecipeProvider(PackOutput output, String modid) {
/*  32 */     this.outputPath = output.m_247566_(PackOutput.Target.DATA_PACK).resolve(modid).resolve("tainting/block");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void add(TaintRecipe recipe) {
/*  39 */     this.recipes.add(recipe);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(Block from, MobEffect effect, BlockState to, Property<?>... propertiesToCopy) {
/*  44 */     add((TaintRecipe)new SingleBlockTaintRecipe(from, effect, to, Lists.newArrayList((Object[])propertiesToCopy)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(Block from, MobEffect effect, BlockState to) {
/*  49 */     add(from, effect, to, (Property<?>[])new Property[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAndCopyAllProperties(Block from, MobEffect effect, Block to) {
/*  54 */     BlockState state = to.m_49966_();
/*  55 */     add(from, effect, state, (Property<?>[])state.m_61147_().toArray(i -> new Property[i]));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAndCopyAllProperties(Block from, Block to) {
/*  60 */     BlockState state = to.m_49966_();
/*  61 */     add(from, state, (Property<?>[])state.m_61147_().toArray(i -> new Property[i]));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(Block from, BlockState to, Property<?>... propertiesToCopy) {
/*  66 */     add((TaintRecipe)new SingleBlockTaintRecipe(from, null, to, Lists.newArrayList((Object[])propertiesToCopy)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(Block from, BlockState to) {
/*  71 */     add(from, to, (Property<?>[])new Property[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(TagKey<Block> from, MobEffect effect, BlockState to, Property<?>... propertiesToCopy) {
/*  76 */     add((TaintRecipe)new TagBasedTaintRecipe(from, effect, to, Lists.newArrayList((Object[])propertiesToCopy)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(TagKey<Block> from, MobEffect effect, BlockState to) {
/*  81 */     add(from, effect, to, (Property<?>[])new Property[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAndCopyAllProperties(TagKey<Block> from, MobEffect effect, Block to) {
/*  86 */     BlockState state = to.m_49966_();
/*  87 */     add(from, effect, state, (Property<?>[])state.m_61147_().toArray(i -> new Property[i]));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(TagKey<Block> from, BlockState to, Property<?>... propertiesToCopy) {
/*  92 */     add((TaintRecipe)new TagBasedTaintRecipe(from, null, to, Lists.newArrayList((Object[])propertiesToCopy)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(TagKey<Block> from, BlockState to) {
/*  97 */     add(from, to, (Property<?>[])new Property[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAndCopyAllProperties(TagKey<Block> from, Block to) {
/* 102 */     BlockState state = to.m_49966_();
/* 103 */     add(from, state, (Property<?>[])state.m_61147_().toArray(i -> new Property[i]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompletableFuture<?> m_213708_(CachedOutput output) {
/* 109 */     addRecipes();
/* 110 */     return CompletableFuture.allOf((CompletableFuture<?>[])this.recipes.stream().map(recipe -> {
/*     */             JsonObject object = new JsonObject();
/*     */             
/*     */             recipe.serializeFrom(object);
/*     */             if (recipe.effect() != null) {
/*     */               object.addProperty("potion_effect", ForgeRegistries.MOB_EFFECTS.getKey(recipe.effect()).toString());
/*     */             }
/*     */             object.add("replacement", BlockState.f_61039_.encodeStart((DynamicOps)JsonOps.INSTANCE, recipe.replacement()).result().get());
/*     */             if (!recipe.propertiesToCopy().isEmpty()) {
/*     */               JsonArray propertiesToCopy = new JsonArray();
/*     */               for (Property<?> property : (Iterable<Property<?>>)recipe.propertiesToCopy()) {
/*     */                 String name = property.m_61708_();
/*     */                 propertiesToCopy.add(name);
/*     */               } 
/*     */               object.add("properties_to_copy", (JsonElement)propertiesToCopy);
/*     */             } 
/*     */             return DataProvider.m_253162_(output, (JsonElement)object, this.outputPath.resolve(recipe.getName() + "_tainting.json"));
/* 127 */           }).toArray(i -> new CompletableFuture[i]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String m_6055_() {
/* 135 */     return "Block tainting recipes";
/*     */   }
/*     */   
/*     */   protected abstract void addRecipes();
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\data\BlockTaintingRecipeProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */