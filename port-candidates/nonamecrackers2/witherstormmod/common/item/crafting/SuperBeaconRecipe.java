/*     */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraft.util.StringRepresentable;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.player.StackedContents;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.Recipe;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ 
/*     */ 
/*     */ public abstract class SuperBeaconRecipe
/*     */   implements Recipe<SuperBeaconBlockEntity>
/*     */ {
/*     */   protected final Identifier id;
/*     */   protected final NonNullList<Ingredient> ingredients;
/*     */   protected final Condition condition;
/*     */   
/*     */   public SuperBeaconRecipe(Identifier id, NonNullList<Ingredient> ingredients, Condition condition) {
/*  32 */     this.id = id;
/*  33 */     this.ingredients = ingredients;
/*  34 */     this.condition = condition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(SuperBeaconBlockEntity block, Level level) {
/*  40 */     StackedContents contents = new StackedContents();
/*  41 */     int j = 0;
/*     */     
/*  43 */     for (int i = 0; i < block.getContainerSize(); i++) {
/*     */       
/*  45 */       ItemStack item = block.getItem(i);
/*  46 */       if (!item.isEmpty()) {
/*     */         
/*  48 */         j++;
/*  49 */         contents.accountStack(item, 1);
/*     */       } 
/*     */     } 
/*     */     
/*  53 */     return (j == this.ingredients.size() && contents.canCraft(this, null));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack assemble(SuperBeaconBlockEntity entity, RegistryAccess access) {
/*  59 */     if (!isResummonEntity()) {
/*  60 */       return getResultItem(access).copy();
/*     */     }
/*  62 */     return ItemStack.EMPTY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCraftInDimensions(int width, int height) {
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getToastSymbol() {
/*  74 */     return new ItemStack((ItemLike)WitherStormModBlocks.SUPER_BEACON.get());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityType<?> getResummonEntity() {
/*  81 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CompoundTag getResummonEntityNBT() {
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NonNullList<Ingredient> getIngredients() {
/*  92 */     return this.ingredients;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getId() {
/*  98 */     return this.id;
/*     */   }
/*     */   public abstract boolean isResummonEntity();
/*     */   
/*     */   public Condition getCondition() {
/* 103 */     return this.condition;
/*     */   }
/*     */   public enum Condition implements StringRepresentable { FULLY_COMLETED,
/*     */     FULL_SUPPORTS,
/*     */     MAIN_ACTIVATED,
/* 108 */     NONE("none", null, (String)(e -> true)); static {
/* 109 */       MAIN_ACTIVATED = new Condition("MAIN_ACTIVATED", 1, "main_activated", "witherstormmod.jei.super_beacon.requiresMainActivated", e -> e.isActive());
/*     */ 
/*     */       
/* 112 */       FULL_SUPPORTS = new Condition("FULL_SUPPORTS", 2, "all_supports", "witherstormmod.jei.super_beacon.requiresAllSupports", e -> 
/* 113 */           (e.isActive() && e.getConnected().size() == 4));
/*     */       
/* 115 */       FULLY_COMLETED = new Condition("FULLY_COMLETED", 3, "fully_completed", "witherstormmod.jei.super_beacon.requiresFullBeacon", e -> 
/* 116 */           (e.isActive() && e.getConnected().size() == 4 && e.beaconLevel == 4));
/*     */     }
/*     */     
/*     */     private final Predicate<SuperBeaconBlockEntity> predicate;
/*     */     @Nullable
/*     */     private final String description;
/*     */     private final String name;
/*     */     
/*     */     Condition(String name, String description, Predicate<SuperBeaconBlockEntity> predicate) {
/* 125 */       this.name = name;
/* 126 */       this.description = description;
/* 127 */       this.predicate = predicate;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String getSerializedName() {
/* 133 */       return this.name;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canCraft(SuperBeaconBlockEntity entity) {
/* 138 */       return this.predicate.test(entity);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getDescription() {
/* 143 */       return this.description;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Condition fromJson(JsonObject object, String name) {
/* 148 */       if (object.has(name)) {
/*     */         
/* 150 */         String id = GsonHelper.getAsString(object, name);
/* 151 */         for (Condition value : values()) {
/*     */           
/* 153 */           if (value.name.equals(id))
/* 154 */             return value; 
/*     */         } 
/*     */       } 
/* 157 */       return NONE;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\SuperBeaconRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */