/*     */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.Recipe;
/*     */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*     */ import net.minecraft.world.item.crafting.RecipeType;
/*     */ import net.minecraft.world.item.crafting.ShapedRecipe;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ItemCraftSuperBeaconRecipe
/*     */   extends SuperBeaconRecipe {
/*     */   private final ItemStack result;
/*     */   
/*     */   public ItemCraftSuperBeaconRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, ItemStack result, SuperBeaconRecipe.Condition condition) {
/*  30 */     super(id, ingredients, condition);
/*  31 */     this.result = result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_8043_(RegistryAccess access) {
/*  37 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeType<?> m_6671_() {
/*  43 */     return (RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_ITEM.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isResummonEntity() {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> m_7707_() {
/*  55 */     return (RecipeSerializer)WitherStormModRecipeSerializers.ITEM_CRAFT_SUPER_BEACON.get();
/*     */   }
/*     */   
/*     */   public static class Serializer
/*     */     implements RecipeSerializer<ItemCraftSuperBeaconRecipe>
/*     */   {
/*     */     public ItemCraftSuperBeaconRecipe fromJson(ResourceLocation id, JsonObject object) {
/*     */       ItemStack stack;
/*  63 */       JsonArray array = GsonHelper.m_13933_(object, "ingredients");
/*  64 */       NonNullList<Ingredient> ingredients = NonNullList.m_122779_();
/*  65 */       for (int i = 0; i < array.size(); i++)
/*  66 */         ingredients.add(Ingredient.m_43917_(array.get(i))); 
/*  67 */       if (!object.has("result")) {
/*  68 */         throw new JsonSyntaxException("Missing result, expected to find a string or object");
/*     */       }
/*  70 */       if (object.get("result").isJsonObject()) {
/*     */         
/*  72 */         stack = ShapedRecipe.m_151274_(object.get("result").getAsJsonObject());
/*     */       }
/*     */       else {
/*     */         
/*  76 */         String rawId = GsonHelper.m_13906_(object, "result");
/*  77 */         ResourceLocation itemId = new ResourceLocation(rawId);
/*  78 */         Item item = (Item)ForgeRegistries.ITEMS.getValue(itemId);
/*  79 */         if (item != null) {
/*  80 */           stack = new ItemStack((ItemLike)item);
/*     */         } else {
/*  82 */           throw new JsonSyntaxException("Unknown item '" + rawId + "'");
/*     */         } 
/*  84 */       }  return new ItemCraftSuperBeaconRecipe(id, ingredients, stack, SuperBeaconRecipe.Condition.fromJson(object, "condition"));
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public ItemCraftSuperBeaconRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
/*  90 */       NonNullList<Ingredient> ingredients = (NonNullList<Ingredient>)buffer.m_236838_(NonNullList::m_182647_, b -> Ingredient.m_43940_(b));
/*  91 */       ItemStack item = buffer.m_130267_();
/*  92 */       SuperBeaconRecipe.Condition condition = (SuperBeaconRecipe.Condition)buffer.m_130066_(SuperBeaconRecipe.Condition.class);
/*  93 */       return new ItemCraftSuperBeaconRecipe(id, ingredients, item, condition);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void toNetwork(FriendlyByteBuf buffer, ItemCraftSuperBeaconRecipe recipe) {
/*  99 */       buffer.m_236828_((Collection)recipe.ingredients, (b, i) -> i.m_43923_(b));
/* 100 */       buffer.m_130055_(recipe.result);
/* 101 */       buffer.m_130068_(recipe.getCondition());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\ItemCraftSuperBeaconRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */