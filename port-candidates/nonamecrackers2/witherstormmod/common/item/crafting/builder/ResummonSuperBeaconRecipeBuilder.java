/*    */ package nonamecrackers2.witherstormmod.common.item.crafting.builder;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.data.recipes.FinishedRecipe;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResummonSuperBeaconRecipeBuilder
/*    */   extends SuperBeaconRecipeBuilder
/*    */ {
/*    */   private final EntityType<?> entity;
/*    */   private final CompoundTag nbt;
/*    */   
/*    */   public ResummonSuperBeaconRecipeBuilder(SuperBeaconRecipe.Condition condition, EntityType<?> entity, CompoundTag tag) {
/* 26 */     super(condition);
/* 27 */     this.entity = entity;
/* 28 */     this.nbt = tag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getResult() {
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void save(Consumer<FinishedRecipe> consumer, Identifier id) {
/* 40 */     consumer.accept(new Result(id, this.condition, this.entity, this.nbt, (this.group == null) ? "" : this.group, this.ingredients));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void save(Consumer<FinishedRecipe> consumer) {
/* 46 */     save(consumer, defaultRecipeId(this.entity));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void save(Consumer<FinishedRecipe> consumer, String string) {
/* 52 */     Identifier id = defaultRecipeId(this.entity);
/* 53 */     Identifier newId = Identifier.fromNamespaceAndPath(id.getNamespace(), string);
/* 54 */     if (id.equals(newId)) {
/* 55 */       throw new IllegalStateException("Recipe " + string + " should remove its 'save' argument as it is equal to the default one");
/*    */     }
/* 57 */     save(consumer, newId);
/*    */   }
/*    */ 
/*    */   
/*    */   private static Identifier defaultRecipeId(EntityType<?> type) {
/* 62 */     Identifier id = ForgeRegistries.ENTITY_TYPES.getKey(type);
/* 63 */     return Identifier.fromNamespaceAndPath(id.getNamespace(), "summon_" + id.getPath());
/*    */   }
/*    */   
/*    */   public static class Result
/*    */     extends SuperBeaconRecipeBuilder.Result
/*    */   {
/*    */     private final EntityType<?> entity;
/*    */     private final CompoundTag nbt;
/*    */     
/*    */     public Result(Identifier id, SuperBeaconRecipe.Condition condition, EntityType<?> entity, CompoundTag nbt, String group, List<Ingredient> ingredients) {
/* 73 */       super(id, condition, group, ingredients);
/* 74 */       this.entity = entity;
/* 75 */       this.nbt = nbt;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void serializeRecipeData(JsonObject object) {
/* 81 */       super.serializeRecipeData(object);
/*    */       
/* 83 */       object.addProperty("entity", ForgeRegistries.ENTITY_TYPES.getKey(this.entity).toString());
/* 84 */       if (!this.nbt.isEmpty()) {
/* 85 */         object.addProperty("nbt", this.nbt.toString());
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public RecipeSerializer<?> getType() {
/* 91 */       return (RecipeSerializer)WitherStormModRecipeSerializers.RESUMMON_SUPER_BEACON.get();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\builder\ResummonSuperBeaconRecipeBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */