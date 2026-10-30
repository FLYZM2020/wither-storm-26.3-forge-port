/*     */ package nonamecrackers2.witherstormmod.client.jei.category;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
/*     */ import mezz.jei.api.gui.drawable.IDrawable;
/*     */ import mezz.jei.api.gui.ingredient.IRecipeSlotView;
/*     */ import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
/*     */ import mezz.jei.api.helpers.IGuiHelper;
/*     */ import mezz.jei.api.recipe.IFocusGroup;
/*     */ import mezz.jei.api.recipe.RecipeIngredientRole;
/*     */ import mezz.jei.api.recipe.category.IRecipeCategory;
/*     */ import mezz.jei.library.gui.ingredients.RecipeSlot;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
/*     */ 
/*     */ public abstract class SuperBeaconCategory<T extends SuperBeaconRecipe>
/*     */   implements IRecipeCategory<T> {
/*  25 */   protected static final ResourceLocation SLOT = new ResourceLocation("witherstormmod", "textures/gui/jei/slot.png");
/*     */   
/*     */   private final IDrawable background;
/*     */   
/*     */   protected SuperBeaconCategory(IGuiHelper helper) {
/*  30 */     this.background = (IDrawable)helper.createBlankDrawable(180, 120);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IDrawable getBackground() {
/*  36 */     return this.background;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
/*  42 */     NonNullList<Ingredient> nonNullList = recipe.m_7527_();
/*  43 */     int totalSize = nonNullList.size();
/*  44 */     float angleOffset = 360.0F / totalSize;
/*  45 */     int halfWidth = getWidth() / 2;
/*  46 */     int halfHeight = getHeight() / 2;
/*  47 */     if (recipe.getCondition() != SuperBeaconRecipe.Condition.NONE)
/*  48 */       halfHeight -= 5; 
/*  49 */     for (int i = 0; i < totalSize; i++) {
/*     */       
/*  51 */       float angle = angleOffset * i;
/*  52 */       float x = Mth.m_14089_(angle * 0.017453292F) * 40.0F + halfWidth - 8.0F;
/*  53 */       float y = Mth.m_14031_(angle * 0.017453292F) * 40.0F + halfHeight - 8.0F;
/*  54 */       Ingredient ingredient = nonNullList.get(i);
/*  55 */       builder.addSlot(RecipeIngredientRole.INPUT, (int)x, (int)y).addIngredients(ingredient);
/*     */     } 
/*  57 */     Minecraft mc = Minecraft.m_91087_();
/*  58 */     addResult(builder, recipe, focuses, halfWidth, halfHeight, mc.f_91073_.m_9598_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics stack, double mouseX, double mouseY) {
/*  66 */     String desc = recipe.getCondition().getDescription();
/*  67 */     if (desc != null) {
/*     */       
/*  69 */       Minecraft mc = Minecraft.m_91087_();
/*  70 */       Objects.requireNonNull(mc.f_91062_); stack.m_280653_(mc.f_91062_, (Component)Component.m_237115_(desc), getWidth() / 2, getHeight() - 9 - 1, -1);
/*     */     } 
/*  72 */     for (IRecipeSlotView slotView : recipeSlotsView.getSlotViews()) {
/*     */       
/*  74 */       if (slotView instanceof RecipeSlot) { RecipeSlot slot = (RecipeSlot)slotView;
/*     */         
/*  76 */         int x = slot.getRect().m_110085_();
/*  77 */         int y = slot.getRect().m_110086_();
/*  78 */         stack.m_280398_(SLOT, x - 1, y - 1, 0, 0.0F, 0.0F, 18, 18, 256, 256); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void addResult(IRecipeLayoutBuilder paramIRecipeLayoutBuilder, T paramT, IFocusGroup paramIFocusGroup, int paramInt1, int paramInt2, RegistryAccess paramRegistryAccess);
/*     */   
/*     */   protected static class Icon implements IDrawable {
/*     */     private final IDrawable wrapped;
/*     */     private final ResourceLocation iconTexture;
/*     */     
/*     */     public Icon(IDrawable wrapped, ResourceLocation iconTexture) {
/*  90 */       this.wrapped = wrapped;
/*  91 */       this.iconTexture = iconTexture;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getWidth() {
/*  97 */       return this.wrapped.getWidth();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getHeight() {
/* 103 */       return this.wrapped.getHeight();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void draw(GuiGraphics stack, int xOffset, int yOffset) {
/* 109 */       this.wrapped.draw(stack, xOffset, yOffset);
/* 110 */       stack.m_280398_(this.iconTexture, xOffset + 8, yOffset + 8, 0, 0.0F, 0.0F, 8, 8, 8, 8);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\jei\category\SuperBeaconCategory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */