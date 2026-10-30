/*    */ package nonamecrackers2.witherstormmod.client.jei.category;
/*    */ 
/*    */ import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
/*    */ import mezz.jei.api.gui.drawable.IDrawable;
/*    */ import mezz.jei.api.helpers.IGuiHelper;
/*    */ import mezz.jei.api.recipe.IFocusGroup;
/*    */ import mezz.jei.api.recipe.RecipeIngredientRole;
/*    */ import mezz.jei.api.recipe.RecipeType;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import nonamecrackers2.witherstormmod.client.jei.WitherStormModJEICompat;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;
/*    */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
/*    */ 
/*    */ public class SuperBeaconItemCrafting extends SuperBeaconCategory<ItemCraftSuperBeaconRecipe> {
/* 20 */   private static final ResourceLocation ICON_TEXTURE = new ResourceLocation("witherstormmod", "textures/gui/jei/crafting_icon.png");
/*    */   
/*    */   private final IDrawable icon;
/*    */   
/*    */   public SuperBeaconItemCrafting(IGuiHelper helper) {
/* 25 */     super(helper);
/* 26 */     this.icon = new SuperBeaconCategory.Icon(helper.createDrawableItemStack(new ItemStack((ItemLike)WitherStormModBlocks.SUPER_BEACON.get())), ICON_TEXTURE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeType<ItemCraftSuperBeaconRecipe> getRecipeType() {
/* 32 */     return WitherStormModJEICompat.SUPER_BEACON_ITEM_CRAFTING;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Component getTitle() {
/* 38 */     return (Component)Component.m_237115_("witherstormmod.jei.item_craft_super_beacon.title");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void addResult(IRecipeLayoutBuilder builder, ItemCraftSuperBeaconRecipe recipe, IFocusGroup focuses, int x, int y, RegistryAccess access) {
/* 44 */     builder.addSlot(RecipeIngredientRole.OUTPUT, x - 8, y - 8).addItemStack(recipe.m_8043_(access));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IDrawable getIcon() {
/* 50 */     return this.icon;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\jei\category\SuperBeaconItemCrafting.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */