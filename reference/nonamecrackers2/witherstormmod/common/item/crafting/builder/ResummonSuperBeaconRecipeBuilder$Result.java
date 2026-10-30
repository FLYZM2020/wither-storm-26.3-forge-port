/*    */ package nonamecrackers2.witherstormmod.common.item.crafting.builder;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.List;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Result
/*    */   extends SuperBeaconRecipeBuilder.Result
/*    */ {
/*    */   private final EntityType<?> entity;
/*    */   private final CompoundTag nbt;
/*    */   
/*    */   public Result(ResourceLocation id, SuperBeaconRecipe.Condition condition, EntityType<?> entity, CompoundTag nbt, String group, List<Ingredient> ingredients) {
/* 73 */     super(id, condition, group, ingredients);
/* 74 */     this.entity = entity;
/* 75 */     this.nbt = nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7917_(JsonObject object) {
/* 81 */     super.m_7917_(object);
/*    */     
/* 83 */     object.addProperty("entity", ForgeRegistries.ENTITY_TYPES.getKey(this.entity).toString());
/* 84 */     if (!this.nbt.m_128456_()) {
/* 85 */       object.addProperty("nbt", this.nbt.toString());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> m_6637_() {
/* 91 */     return (RecipeSerializer)WitherStormModRecipeSerializers.RESUMMON_SUPER_BEACON.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\builder\ResummonSuperBeaconRecipeBuilder$Result.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */