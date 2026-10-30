/*     */ package nonamecrackers2.witherstormmod.common.item.crafting.builder;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.advancements.CriterionTriggerInstance;
/*     */ import net.minecraft.data.recipes.FinishedRecipe;
/*     */ import net.minecraft.data.recipes.RecipeBuilder;
/*     */ import net.minecraft.data.recipes.RecipeCategory;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
/*     */ 
/*     */ 
/*     */ public abstract class SuperBeaconRecipeBuilder
/*     */   implements RecipeBuilder
/*     */ {
/*  24 */   protected final List<Ingredient> ingredients = Lists.newArrayList();
/*     */   protected final SuperBeaconRecipe.Condition condition;
/*     */   @Nullable
/*     */   protected String group;
/*     */   
/*     */   public SuperBeaconRecipeBuilder(SuperBeaconRecipe.Condition condition) {
/*  30 */     this.condition = condition;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemCraftSuperBeaconRecipeBuilder item(SuperBeaconRecipe.Condition condition, RecipeCategory category, ItemLike result, int count) {
/*  35 */     return new ItemCraftSuperBeaconRecipeBuilder(condition, result, count);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemCraftSuperBeaconRecipeBuilder item(SuperBeaconRecipe.Condition condition, RecipeCategory category, ItemLike result) {
/*  40 */     return new ItemCraftSuperBeaconRecipeBuilder(condition, result, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResummonSuperBeaconRecipeBuilder entity(SuperBeaconRecipe.Condition condition, RecipeCategory category, EntityType<?> type, CompoundTag tag) {
/*  45 */     return new ResummonSuperBeaconRecipeBuilder(condition, type, tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResummonSuperBeaconRecipeBuilder entity(SuperBeaconRecipe.Condition condition, RecipeCategory category, EntityType<?> type) {
/*  50 */     return new ResummonSuperBeaconRecipeBuilder(condition, type, new CompoundTag());
/*     */   }
/*     */ 
/*     */   
/*     */   public SuperBeaconRecipeBuilder requires(ItemLike item) {
/*  55 */     return requires(item, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public SuperBeaconRecipeBuilder requires(ItemLike item, int count) {
/*  60 */     for (int i = 0; i < count; i++) {
/*  61 */       requires(Ingredient.m_43929_(new ItemLike[] { item }));
/*  62 */     }  return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SuperBeaconRecipeBuilder requires(Ingredient ingredient) {
/*  67 */     return requires(ingredient, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public SuperBeaconRecipeBuilder requires(Ingredient ingredient, int count) {
/*  72 */     for (int i = 0; i < count; i++)
/*  73 */       this.ingredients.add(ingredient); 
/*  74 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SuperBeaconRecipeBuilder unlockedBy(String name, CriterionTriggerInstance instance) {
/*  80 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeBuilder m_126145_(String group) {
/*  86 */     this.group = group;
/*  87 */     return null;
/*     */   }
/*     */   
/*     */   public static abstract class Result
/*     */     implements FinishedRecipe
/*     */   {
/*     */     private final ResourceLocation id;
/*     */     private final SuperBeaconRecipe.Condition condition;
/*     */     private final String group;
/*     */     private final List<Ingredient> ingredients;
/*     */     
/*     */     public Result(ResourceLocation id, SuperBeaconRecipe.Condition condition, String group, List<Ingredient> ingredients) {
/*  99 */       this.id = id;
/* 100 */       this.condition = condition;
/* 101 */       this.group = group;
/* 102 */       this.ingredients = ingredients;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void m_7917_(JsonObject object) {
/* 108 */       object.addProperty("condition", this.condition.m_7912_());
/*     */       
/* 110 */       if (!this.group.isEmpty()) {
/* 111 */         object.addProperty("group", this.group);
/*     */       }
/* 113 */       JsonArray array = new JsonArray();
/* 114 */       for (Ingredient ingredient : this.ingredients)
/* 115 */         array.add(ingredient.m_43942_()); 
/* 116 */       object.add("ingredients", (JsonElement)array);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ResourceLocation m_6445_() {
/* 122 */       return this.id;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObject m_5860_() {
/* 128 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ResourceLocation m_6448_() {
/* 134 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\builder\SuperBeaconRecipeBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */