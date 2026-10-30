/*     */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.Recipe;
/*     */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*     */ import net.minecraft.world.item.crafting.ShapedRecipe;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public class Serializer
/*     */   implements RecipeSerializer<AnvilRecipe>
/*     */ {
/*     */   public AnvilRecipe fromJson(ResourceLocation id, JsonObject object) {
/*     */     ItemStack stack;
/* 111 */     Ingredient left = Ingredient.m_43917_(object.get("left"));
/* 112 */     Ingredient right = Ingredient.m_43917_(object.get("right"));
/*     */     
/* 114 */     if (object.get("result").isJsonObject()) {
/*     */       
/* 116 */       stack = ShapedRecipe.m_151274_(object.get("result").getAsJsonObject());
/*     */     }
/*     */     else {
/*     */       
/* 120 */       String rawId = GsonHelper.m_13906_(object, "result");
/* 121 */       ResourceLocation itemId = new ResourceLocation(rawId);
/* 122 */       Item item = (Item)ForgeRegistries.ITEMS.getValue(itemId);
/* 123 */       if (item != null) {
/* 124 */         stack = new ItemStack((ItemLike)item);
/*     */       } else {
/* 126 */         throw new JsonSyntaxException("Unknown item '" + rawId + "'");
/*     */       } 
/* 128 */     }  int cost = GsonHelper.m_13927_(object, "cost");
/* 129 */     return new AnvilRecipe(id, left, right, stack, cost);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AnvilRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
/* 135 */     Ingredient left = Ingredient.m_43940_(buffer);
/* 136 */     Ingredient right = Ingredient.m_43940_(buffer);
/* 137 */     ItemStack item = buffer.m_130267_();
/* 138 */     int cost = buffer.m_130242_();
/* 139 */     return new AnvilRecipe(id, left, right, item, cost);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toNetwork(FriendlyByteBuf buffer, AnvilRecipe recipe) {
/* 145 */     recipe.left.m_43923_(buffer);
/* 146 */     recipe.right.m_43923_(buffer);
/* 147 */     buffer.m_130055_(recipe.result);
/* 148 */     buffer.m_130130_(recipe.cost);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\AnvilRecipe$Serializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */