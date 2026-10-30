/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.tags.ItemTags;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraft.world.item.Tier;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum WitherStormModItemTier
/*    */   implements Tier
/*    */ {
/* 20 */   WOOD_CMD(5, 0, 3.5F, 3.75F, 32, () -> Ingredient.m_204132_(ItemTags.f_13168_)),
/*    */   
/* 22 */   STONE_CMD(3, 0, 16.0F, 5.25F, 5, () -> Ingredient.m_204132_(ItemTags.f_13165_)),
/*    */   
/* 24 */   IRON_CMD(5, 0, 8.0F, 6.5F, 10, () -> Ingredient.m_43929_(new ItemLike[] { (ItemLike)Items.f_42416_
/*    */       })),
/* 26 */   GOLD_CMD(1, 0, 32.0F, 2.5F, 64, () -> Ingredient.m_43929_(new ItemLike[] { (ItemLike)Items.f_42417_
/*    */       })),
/* 28 */   COMMAND_BLOCK(5, 0, 14.0F, 6.0F, 15, () -> Ingredient.m_43929_(new ItemLike[] { (ItemLike)Items.f_42415_
/*    */       })),
/* 30 */   EYE_OF_THE_STORM(5, 0, 12.0F, 7.5F, 25, () -> Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.TAINTED_DUST.get()
/*    */       })),
/* 32 */   FORMIDI_BLADE(5, 0, 12.0F, 6.0F, 15, () -> Ingredient.m_43929_(new ItemLike[] { (ItemLike)WitherStormModItems.FORMIDIBOMB.get() }));
/*    */   
/*    */   private final int level;
/*    */   
/*    */   private final int uses;
/*    */   
/*    */   private final float speed;
/*    */   private final float damage;
/*    */   private final int enchantmentValue;
/*    */   private final Supplier<Ingredient> repairIngredient;
/*    */   
/*    */   WitherStormModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredient) {
/* 44 */     this.level = level;
/* 45 */     this.uses = uses;
/* 46 */     this.speed = speed;
/* 47 */     this.damage = damage;
/* 48 */     this.enchantmentValue = enchantmentValue;
/* 49 */     this.repairIngredient = ingredient;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int m_6609_() {
/* 55 */     return this.uses;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float m_6624_() {
/* 61 */     return this.speed;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float m_6631_() {
/* 67 */     return this.damage;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int m_6604_() {
/* 73 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int m_6601_() {
/* 79 */     return this.enchantmentValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ingredient m_6282_() {
/* 85 */     return this.repairIngredient.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\WitherStormModItemTier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */