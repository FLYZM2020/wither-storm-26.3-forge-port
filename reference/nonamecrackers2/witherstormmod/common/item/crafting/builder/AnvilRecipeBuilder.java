/*     */ package nonamecrackers2.witherstormmod.common.item.crafting.builder;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.advancements.CriterionTriggerInstance;
/*     */ import net.minecraft.data.recipes.FinishedRecipe;
/*     */ import net.minecraft.data.recipes.RecipeBuilder;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnvilRecipeBuilder
/*     */   implements RecipeBuilder
/*     */ {
/*     */   private final Ingredient left;
/*     */   private final Ingredient right;
/*     */   private final Item result;
/*     */   private final int xpCost;
/*     */   @Nullable
/*     */   private String group;
/*     */   
/*     */   public AnvilRecipeBuilder(Ingredient left, Ingredient right, ItemLike result, int xpCost) {
/*  31 */     this.left = left;
/*  32 */     this.right = right;
/*  33 */     this.result = result.m_5456_();
/*  34 */     this.xpCost = xpCost;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AnvilRecipeBuilder commandBlockTool(ItemLike requirement, ItemLike result) {
/*  39 */     return new AnvilRecipeBuilder(Ingredient.m_43929_(new ItemLike[] { requirement }, ), Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get() }, ), result, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeBuilder m_126132_(String str, CriterionTriggerInstance instance) {
/*  45 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeBuilder m_126145_(String group) {
/*  51 */     this.group = group;
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item m_142372_() {
/*  58 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_126140_(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
/*  64 */     consumer.accept(new Result(id, (this.group == null) ? "" : this.group, this.left, this.right, this.result, this.xpCost));
/*     */   }
/*     */   public static final class Result extends Record implements FinishedRecipe { private final ResourceLocation id; private final String group; private final Ingredient left; private final Ingredient right; private final Item result; private final int xpCost;
/*  67 */     public Result(ResourceLocation id, String group, Ingredient left, Ingredient right, Item result, int xpCost) { this.id = id; this.group = group; this.left = left; this.right = right; this.result = result; this.xpCost = xpCost; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/item/crafting/builder/AnvilRecipeBuilder$Result;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #67	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  67 */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/item/crafting/builder/AnvilRecipeBuilder$Result; } public ResourceLocation id() { return this.id; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/item/crafting/builder/AnvilRecipeBuilder$Result;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #67	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/item/crafting/builder/AnvilRecipeBuilder$Result; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/item/crafting/builder/AnvilRecipeBuilder$Result;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #67	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/item/crafting/builder/AnvilRecipeBuilder$Result;
/*  67 */       //   0	8	1	o	Ljava/lang/Object; } public String group() { return this.group; } public Ingredient left() { return this.left; } public Ingredient right() { return this.right; } public Item result() { return this.result; } public int xpCost() { return this.xpCost; }
/*     */ 
/*     */ 
/*     */     
/*     */     public void m_7917_(JsonObject object) {
/*  72 */       if (!this.group.isEmpty())
/*  73 */         object.addProperty("group", this.group); 
/*  74 */       object.add("left", this.left.m_43942_());
/*  75 */       object.add("right", this.right.m_43942_());
/*  76 */       object.addProperty("cost", Integer.valueOf(this.xpCost));
/*  77 */       object.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ResourceLocation m_6445_() {
/*  83 */       return this.id;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public RecipeSerializer<?> m_6637_() {
/*  89 */       return (RecipeSerializer)WitherStormModRecipeSerializers.ANVIL_RECIPE.get();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObject m_5860_() {
/*  95 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ResourceLocation m_6448_() {
/* 101 */       return null;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\builder\AnvilRecipeBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */