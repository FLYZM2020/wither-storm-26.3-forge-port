/*    */ package nonamecrackers2.witherstormmod.common.item.crafting.builder;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.data.recipes.FinishedRecipe;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
/*    */ 
/*    */ 
/*    */ public class ItemCraftSuperBeaconRecipeBuilder
/*    */   extends SuperBeaconRecipeBuilder
/*    */ {
/*    */   private final Item result;
/*    */   private final int count;
/*    */   
/*    */   public ItemCraftSuperBeaconRecipeBuilder(SuperBeaconRecipe.Condition condition, ItemLike result, int count) {
/* 25 */     super(condition);
/* 26 */     this.result = result.m_5456_();
/* 27 */     this.count = count;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Item m_142372_() {
/* 33 */     return this.result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_126140_(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
/* 39 */     consumer.accept(new Result(id, this.condition, this.result, this.count, (this.group == null) ? "" : this.group, this.ingredients));
/*    */   }
/*    */   
/*    */   public static class Result
/*    */     extends SuperBeaconRecipeBuilder.Result
/*    */   {
/*    */     private final Item result;
/*    */     private final int count;
/*    */     
/*    */     public Result(ResourceLocation id, SuperBeaconRecipe.Condition condition, Item result, int count, String group, List<Ingredient> ingredients) {
/* 49 */       super(id, condition, group, ingredients);
/* 50 */       this.result = result;
/* 51 */       this.count = count;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void m_7917_(JsonObject object) {
/* 57 */       super.m_7917_(object);
/*    */       
/* 59 */       JsonObject result = new JsonObject();
/* 60 */       result.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
/* 61 */       if (this.count > 1)
/* 62 */         result.addProperty("count", Integer.valueOf(this.count)); 
/* 63 */       object.add("result", (JsonElement)result);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public RecipeSerializer<?> m_6637_() {
/* 69 */       return (RecipeSerializer)WitherStormModRecipeSerializers.ITEM_CRAFT_SUPER_BEACON.get();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\builder\ItemCraftSuperBeaconRecipeBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */