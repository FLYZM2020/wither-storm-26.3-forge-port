/*     */ package nonamecrackers2.witherstormmod.common.item.crafting.builder;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.List;
/*     */ import net.minecraft.data.recipes.FinishedRecipe;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
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
/*     */ public abstract class Result
/*     */   implements FinishedRecipe
/*     */ {
/*     */   private final ResourceLocation id;
/*     */   private final SuperBeaconRecipe.Condition condition;
/*     */   private final String group;
/*     */   private final List<Ingredient> ingredients;
/*     */   
/*     */   public Result(ResourceLocation id, SuperBeaconRecipe.Condition condition, String group, List<Ingredient> ingredients) {
/*  99 */     this.id = id;
/* 100 */     this.condition = condition;
/* 101 */     this.group = group;
/* 102 */     this.ingredients = ingredients;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7917_(JsonObject object) {
/* 108 */     object.addProperty("condition", this.condition.m_7912_());
/*     */     
/* 110 */     if (!this.group.isEmpty()) {
/* 111 */       object.addProperty("group", this.group);
/*     */     }
/* 113 */     JsonArray array = new JsonArray();
/* 114 */     for (Ingredient ingredient : this.ingredients)
/* 115 */       array.add(ingredient.m_43942_()); 
/* 116 */     object.add("ingredients", (JsonElement)array);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation m_6445_() {
/* 122 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonObject m_5860_() {
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation m_6448_() {
/* 134 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\builder\SuperBeaconRecipeBuilder$Result.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */