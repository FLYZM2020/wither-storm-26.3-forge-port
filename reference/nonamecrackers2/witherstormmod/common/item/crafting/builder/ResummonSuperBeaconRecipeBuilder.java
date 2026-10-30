/*    */ package nonamecrackers2.witherstormmod.common.item.crafting.builder;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.data.recipes.FinishedRecipe;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.resources.ResourceLocation;
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
/*    */   public Item m_142372_() {
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_126140_(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
/* 40 */     consumer.accept(new Result(id, this.condition, this.entity, this.nbt, (this.group == null) ? "" : this.group, this.ingredients));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_176498_(Consumer<FinishedRecipe> consumer) {
/* 46 */     m_126140_(consumer, defaultRecipeId(this.entity));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_176500_(Consumer<FinishedRecipe> consumer, String string) {
/* 52 */     ResourceLocation id = defaultRecipeId(this.entity);
/* 53 */     ResourceLocation newId = new ResourceLocation(id.m_135827_(), string);
/* 54 */     if (id.equals(newId)) {
/* 55 */       throw new IllegalStateException("Recipe " + string + " should remove its 'save' argument as it is equal to the default one");
/*    */     }
/* 57 */     m_126140_(consumer, newId);
/*    */   }
/*    */ 
/*    */   
/*    */   private static ResourceLocation defaultRecipeId(EntityType<?> type) {
/* 62 */     ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(type);
/* 63 */     return new ResourceLocation(id.m_135827_(), "summon_" + id.m_135815_());
/*    */   }
/*    */   
/*    */   public static class Result
/*    */     extends SuperBeaconRecipeBuilder.Result
/*    */   {
/*    */     private final EntityType<?> entity;
/*    */     private final CompoundTag nbt;
/*    */     
/*    */     public Result(ResourceLocation id, SuperBeaconRecipe.Condition condition, EntityType<?> entity, CompoundTag nbt, String group, List<Ingredient> ingredients) {
/* 73 */       super(id, condition, group, ingredients);
/* 74 */       this.entity = entity;
/* 75 */       this.nbt = nbt;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void m_7917_(JsonObject object) {
/* 81 */       super.m_7917_(object);
/*    */       
/* 83 */       object.addProperty("entity", ForgeRegistries.ENTITY_TYPES.getKey(this.entity).toString());
/* 84 */       if (!this.nbt.m_128456_()) {
/* 85 */         object.addProperty("nbt", this.nbt.toString());
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public RecipeSerializer<?> m_6637_() {
/* 91 */       return (RecipeSerializer)WitherStormModRecipeSerializers.RESUMMON_SUPER_BEACON.get();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\builder\ResummonSuperBeaconRecipeBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */