/*     */ package nonamecrackers2.witherstormmod.client.jei.category;
/*     */ import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
/*     */ import mezz.jei.api.gui.drawable.IDrawable;
/*     */ import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
/*     */ import mezz.jei.api.helpers.IGuiHelper;
/*     */ import mezz.jei.api.recipe.IFocusGroup;
/*     */ import mezz.jei.api.recipe.RecipeIngredientRole;
/*     */ import mezz.jei.api.recipe.RecipeType;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.screens.inventory.InventoryScreen;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.SpawnEggItem;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.common.ForgeSpawnEggItem;
/*     */ import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
/*     */ import nonamecrackers2.witherstormmod.client.jei.WitherStormModJEICompat;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
/*     */ 
/*     */ public class SuperBeaconSummoning extends SuperBeaconCategory<ResummonSuperBeaconRecipe> {
/*  34 */   private static final ResourceLocation ICON_TEXTURE = new ResourceLocation("witherstormmod", "textures/gui/jei/summoning_icon.png");
/*     */   
/*     */   private final IDrawable icon;
/*     */   
/*     */   public SuperBeaconSummoning(IGuiHelper helper) {
/*  39 */     super(helper);
/*  40 */     this.icon = new SuperBeaconCategory.Icon(helper.createDrawableItemStack(new ItemStack((ItemLike)WitherStormModBlocks.SUPER_BEACON.get())), ICON_TEXTURE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeType<ResummonSuperBeaconRecipe> getRecipeType() {
/*  46 */     return WitherStormModJEICompat.SUPER_BEACON_SUMMONING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getTitle() {
/*  52 */     return (Component)Component.m_237115_("witherstormmod.jei.resummoning_super_beacon.title");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IDrawable getIcon() {
/*  58 */     return this.icon;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addResult(IRecipeLayoutBuilder builder, ResummonSuperBeaconRecipe recipe, IFocusGroup focuses, int x, int y, RegistryAccess access) {
/*  64 */     EntityType<?> type = recipe.getResummonEntity();
/*  65 */     if (type != null) {
/*     */       
/*  67 */       SpawnEggItem spawnEgg = ForgeSpawnEggItem.fromEntityType(type);
/*  68 */       if (spawnEgg != null) {
/*  69 */         builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStack(new ItemStack((ItemLike)spawnEgg));
/*     */       } else {
/*  71 */         builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStack(ItemStack.f_41583_);
/*  72 */       }  Minecraft mc = Minecraft.m_91087_();
/*  73 */       Entity entity = type.m_20615_((Level)mc.f_91073_);
/*  74 */       if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity;
/*     */         
/*  76 */         recipe.toRender = living;
/*  77 */         if (living instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)living;
/*  78 */           storm.setPhase(4); }
/*     */          }
/*     */     
/*     */     } else {
/*     */       
/*  83 */       builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStack(ItemStack.f_41583_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(ResummonSuperBeaconRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics stack, double mouseX, double mouseY) {
/*  90 */     if (recipe.toRender != null) {
/*     */       
/*  92 */       int x = getWidth() / 2;
/*  93 */       int y = getHeight() / 2;
/*  94 */       if (recipe.getCondition() != SuperBeaconRecipe.Condition.NONE)
/*  95 */         y -= 5; 
/*  96 */       y = (int)(y + recipe.toRender.m_20206_() / 2.0F * 20.0F);
/*     */       
/*  98 */       float angleX = (float)-mouseX + x;
/*  99 */       float angleY = (float)-mouseY + y - recipe.toRender.m_20192_() * 20.0F;
/*     */       
/* 101 */       stack.m_280168_().m_85836_();
/* 102 */       stack.m_280168_().m_85837_(0.0D, 0.0D, 50.0D);
/*     */       
/* 104 */       LivingEntity livingEntity = recipe.toRender; if (livingEntity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)livingEntity;
/*     */         
/* 106 */         HeadManager manager = storm.getHeadManager();
/* 107 */         for (WitherStormHead head : manager.getHeads()) {
/*     */           
/* 109 */           if (head instanceof AdditionalHead) { AdditionalHead additionalHead = (AdditionalHead)head;
/*     */             
/* 111 */             additionalHead.yRot = angleX * 20.0F * 0.017453292F + 180.0F;
/* 112 */             additionalHead.xRot = -angleY * 20.0F * 0.017453292F;
/* 113 */             additionalHead.yRotO = additionalHead.yRot;
/* 114 */             additionalHead.xRotO = additionalHead.xRot; }
/*     */         
/*     */         } 
/*     */         
/* 118 */         stack.m_280168_().m_85837_(80.0D, 60.0D, 0.0D);
/* 119 */         stack.m_280168_().m_85841_(0.1F, 0.1F, 0.1F); }
/*     */ 
/*     */       
/* 122 */       RenderBufferer.pushTempDisabled();
/* 123 */       InventoryScreen.m_274545_(stack, x, y, 20, angleX, angleY, recipe.toRender);
/*     */       
/* 125 */       stack.m_280168_().m_85849_();
/*     */     } 
/*     */     
/* 128 */     super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHandled(ResummonSuperBeaconRecipe recipe) {
/* 134 */     return !recipe.m_6423_().toString().equals("witherstormmod:summon_pig");
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\jei\category\SuperBeaconSummoning.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */