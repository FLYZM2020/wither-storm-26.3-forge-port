/*     */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.ResourceLocation;
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
/*     */   private final ResourceLocation id;
/*     */   private final Ingredient left;
/*     */   private final Ingredient right;
/*     */   private final ItemStack result;
/*     */   private final int cost;
/*     */   
/*     */   public AnvilRecipe(ResourceLocation id, Ingredient left, Ingredient right, ItemStack result, int cost) {
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
/*  52 */     return m_8043_(access).m_41777_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_8004_(int width, int height) {
/*  58 */     return (width == 1 && width == 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation m_6423_() {
/*  64 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_8043_(RegistryAccess access) {
/*  70 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> m_7707_() {
/*  76 */     return (RecipeSerializer)WitherStormModRecipeSerializers.ANVIL_RECIPE.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeType<?> m_6671_() {
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
/*     */     public AnvilRecipe fromJson(ResourceLocation id, JsonObject object) {
/*     */       ItemStack stack;
/* 111 */       Ingredient left = Ingredient.m_43917_(object.get("left"));
/* 112 */       Ingredient right = Ingredient.m_43917_(object.get("right"));
/*     */       
/* 114 */       if (object.get("result").isJsonObject()) {
/*     */         
/* 116 */         stack = ShapedRecipe.m_151274_(object.get("result").getAsJsonObject());
/*     */       }
/*     */       else {
/*     */         
/* 120 */         String rawId = GsonHelper.m_13906_(object, "result");
/* 121 */         ResourceLocation itemId = new ResourceLocation(rawId);
/* 122 */         Item item = (Item)ForgeRegistries.ITEMS.getValue(itemId);
/* 123 */         if (item != null) {
/* 124 */           stack = new ItemStack((ItemLike)item);
/*     */         } else {
/* 126 */           throw new JsonSyntaxException("Unknown item '" + rawId + "'");
/*     */         } 
/* 128 */       }  int cost = GsonHelper.m_13927_(object, "cost");
/* 129 */       return new AnvilRecipe(id, left, right, stack, cost);
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public AnvilRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
/* 135 */       Ingredient left = Ingredient.m_43940_(buffer);
/* 136 */       Ingredient right = Ingredient.m_43940_(buffer);
/* 137 */       ItemStack item = buffer.m_130267_();
/* 138 */       int cost = buffer.m_130242_();
/* 139 */       return new AnvilRecipe(id, left, right, item, cost);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void toNetwork(FriendlyByteBuf buffer, AnvilRecipe recipe) {
/* 145 */       recipe.left.m_43923_(buffer);
/* 146 */       recipe.right.m_43923_(buffer);
/* 147 */       buffer.m_130055_(recipe.result);
/* 148 */       buffer.m_130130_(recipe.cost);
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
/*     */     public void m_6211_() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_6643_() {
/* 171 */       return 2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean m_7983_() {
/* 177 */       return (this.left.m_41619_() && this.right.m_41619_());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack m_8020_(int slot) {
/* 183 */       switch (slot) {
/*     */         
/*     */         case 0:
/* 186 */           return this.left;
/*     */         case 1:
/* 188 */           return this.right;
/*     */       } 
/* 190 */       return ItemStack.f_41583_;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack m_7407_(int slot, int amount) {
/* 197 */       return ItemStack.f_41583_;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack m_8016_(int slot) {
/* 203 */       return ItemStack.f_41583_;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void m_6836_(int slot, ItemStack stack) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void m_6596_() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean m_6542_(Player player) {
/* 219 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\AnvilRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */