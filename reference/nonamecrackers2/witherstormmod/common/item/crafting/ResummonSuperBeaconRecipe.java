/*     */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.TagParser;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.Recipe;
/*     */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*     */ import net.minecraft.world.item.crafting.RecipeType;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ResummonSuperBeaconRecipe
/*     */   extends SuperBeaconRecipe {
/*     */   private final EntityType<?> entity;
/*     */   private final CompoundTag nbt;
/*     */   @Nullable
/*     */   public LivingEntity toRender;
/*     */   
/*     */   public ResummonSuperBeaconRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, EntityType<?> entity, CompoundTag nbt, SuperBeaconRecipe.Condition condition) {
/*  35 */     super(id, ingredients, condition);
/*  36 */     this.entity = entity;
/*  37 */     this.nbt = nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_8043_(RegistryAccess access) {
/*  43 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeType<?> m_6671_() {
/*  49 */     return (RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_RESUMMON.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isResummonEntity() {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityType<?> getResummonEntity() {
/*  61 */     return this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag getResummonEntityNBT() {
/*  67 */     return this.nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> m_7707_() {
/*  73 */     return (RecipeSerializer)WitherStormModRecipeSerializers.RESUMMON_SUPER_BEACON.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Serializer
/*     */     implements RecipeSerializer<ResummonSuperBeaconRecipe>
/*     */   {
/*     */     public ResummonSuperBeaconRecipe fromJson(ResourceLocation id, JsonObject object) {
/*  81 */       JsonArray array = GsonHelper.m_13933_(object, "ingredients");
/*  82 */       NonNullList<Ingredient> ingredients = NonNullList.m_122779_();
/*  83 */       for (int i = 0; i < array.size(); i++)
/*  84 */         ingredients.add(Ingredient.m_43917_(array.get(i))); 
/*  85 */       String rawEntityId = GsonHelper.m_13906_(object, "entity");
/*  86 */       ResourceLocation entityId = new ResourceLocation(rawEntityId);
/*  87 */       EntityType<?> type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(entityId);
/*  88 */       if (type == null)
/*  89 */         throw new JsonSyntaxException("Unknown entity of id '" + rawEntityId + "'"); 
/*  90 */       CompoundTag tag = new CompoundTag();
/*  91 */       if (object.has("nbt")) {
/*     */         
/*     */         try {
/*  94 */           tag = TagParser.m_129359_(GsonHelper.m_13906_(object, "nbt"));
/*  95 */         } catch (CommandSyntaxException exception) {
/*  96 */           throw new JsonSyntaxException("Invalid nbt tag: " + exception.getMessage());
/*     */         } 
/*     */       }
/*  99 */       return new ResummonSuperBeaconRecipe(id, ingredients, type, tag, SuperBeaconRecipe.Condition.fromJson(object, "condition"));
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public ResummonSuperBeaconRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
/* 105 */       NonNullList<Ingredient> ingredients = (NonNullList<Ingredient>)buffer.m_236838_(NonNullList::m_182647_, b -> Ingredient.m_43940_(b));
/* 106 */       EntityType<?> type = (EntityType)buffer.readRegistryId();
/* 107 */       CompoundTag tag = buffer.m_130260_();
/* 108 */       SuperBeaconRecipe.Condition condition = (SuperBeaconRecipe.Condition)buffer.m_130066_(SuperBeaconRecipe.Condition.class);
/* 109 */       return new ResummonSuperBeaconRecipe(id, ingredients, type, tag, condition);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void toNetwork(FriendlyByteBuf buffer, ResummonSuperBeaconRecipe recipe) {
/* 115 */       buffer.m_236828_((Collection)recipe.ingredients, (b, i) -> i.m_43923_(b));
/* 116 */       buffer.writeRegistryId(ForgeRegistries.ENTITY_TYPES, recipe.entity);
/* 117 */       buffer.m_130079_(recipe.nbt);
/* 118 */       buffer.m_130068_(recipe.getCondition());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\ResummonSuperBeaconRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */