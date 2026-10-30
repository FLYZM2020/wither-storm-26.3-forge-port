/*     */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.Recipe;
/*     */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*     */ import net.minecraft.world.item.crafting.RecipeType;
/*     */ import net.minecraft.world.item.crafting.ShapedRecipe;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class AnvilRecipe
/*     */   implements Recipe<AnvilRecipe.AnvilContents>
/*     */ {
/*     */   private final Identifier id;
/*     */   private final Ingredient left;
/*     */   private final Ingredient right;
/*     */   private final ItemStack result;
/*     */   private final int cost;
/*     */   
/*     */   public AnvilRecipe(Identifier id, Ingredient left, Ingredient right, ItemStack result, int cost) {
/*  36 */     this.id = id;
/*  37 */     this.left = left;
/*  38 */     this.right = right;
/*  39 */     this.result = result;
/*  40 */     this.cost = cost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(AnvilContents contents, Level level) {
/*  46 */     return (this.left.test(contents.left) && this.right.test(contents.right));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack assemble(AnvilContents contents, RegistryAccess access) {
/*  52 */     return getResultItem(access).copy();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCraftInDimensions(int width, int height) {
/*  58 */     return (width == 1 && width == 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getId() {
/*  64 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getResultItem(RegistryAccess access) {
/*  70 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getSerializer() {
/*  76 */     return (RecipeSerializer)WitherStormModRecipeSerializers.ANVIL_RECIPE.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeType<?> getType() {
/*  82 */     return (RecipeType)WitherStormModRecipeTypes.ANVIL.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCost() {
/*  87 */     return this.cost;
/*     */   }
/*     */ 
/*     */   
/*     */   public Ingredient getLeft() {
/*  92 */     return this.left;
/*     */   }
/*     */ 
/*     */   
/*     */   public Ingredient getRight() {
/*  97 */     return this.right;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getOutputRaw() {
/* 102 */     return this.result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Serializer
/*     */     implements RecipeSerializer<AnvilRecipe>
/*     */   {
/*     */     public AnvilRecipe fromJson(Identifier id, JsonObject object) {
/*     */       ItemStack stack;
/* 111 */       Ingredient left = Ingredient.fromJson(object.get("left"));
/* 112 */       Ingredient right = Ingredient.fromJson(object.get("right"));
/*     */       
/* 114 */       if (object.get("result").isJsonObject()) {
/*     */         
/* 116 */         stack = ShapedRecipe.itemStackFromJson(object.get("result").getAsJsonObject());
/*     */       }
/*     */       else {
/*     */         
/* 120 */         String rawId = GsonHelper.getAsString(object, "result");
/* 121 */         Identifier itemId = Identifier.parse(rawId);
/* 122 */         Item item = (Item)ForgeRegistries.ITEMS.getValue(itemId);
/* 123 */         if (item != null) {
/* 124 */           stack = new ItemStack((ItemLike)item);
/*     */         } else {
/* 126 */           throw new JsonSyntaxException("Unknown item '" + rawId + "'");
/*     */         } 
/* 128 */       }  int cost = GsonHelper.getAsInt(object, "cost");
/* 129 */       return new AnvilRecipe(id, left, right, stack, cost);
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public AnvilRecipe fromNetwork(Identifier id, FriendlyByteBuf buffer) {
/* 135 */       Ingredient left = Ingredient.fromNetwork(buffer);
/* 136 */       Ingredient right = Ingredient.fromNetwork(buffer);
/* 137 */       ItemStack item = buffer.readItem();
/* 138 */       int cost = buffer.readVarInt();
/* 139 */       return new AnvilRecipe(id, left, right, item, cost);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void toNetwork(FriendlyByteBuf buffer, AnvilRecipe recipe) {
/* 145 */       recipe.left.toNetwork(buffer);
/* 146 */       recipe.right.toNetwork(buffer);
/* 147 */       buffer.writeItem(recipe.result);
/* 148 */       buffer.writeVarInt(recipe.cost);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AnvilContents
/*     */     implements Container
/*     */   {
/*     */     private final ItemStack left;
/*     */     private final ItemStack right;
/*     */     
/*     */     public AnvilContents(ItemStack left, ItemStack right) {
/* 159 */       this.left = left;
/* 160 */       this.right = right;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void clearContent() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public int getContainerSize() {
/* 171 */       return 2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 177 */       return (this.left.isEmpty() && this.right.isEmpty());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack getItem(int slot) {
/* 183 */       switch (slot) {
/*     */         
/*     */         case 0:
/* 186 */           return this.left;
/*     */         case 1:
/* 188 */           return this.right;
/*     */       } 
/* 190 */       return ItemStack.EMPTY;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack removeItem(int slot, int amount) {
/* 197 */       return ItemStack.EMPTY;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack removeItemNoUpdate(int slot) {
/* 203 */       return ItemStack.EMPTY;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setItem(int slot, ItemStack stack) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setChanged() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean stillValid(Player player) {
/* 219 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\AnvilRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */