/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ import com.google.common.base.Supplier;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.Util;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.monster.Monster;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ 
/*     */ public class EquipmentHelper {
/*     */   private static final List<EquipmentType> HELMETS;
/*     */   private static final List<EquipmentType> ADVANCED_HELMETS;
/*     */   private static final List<EquipmentType> CHESTPLATES;
/*     */   private static final List<EquipmentType> ADVANCED_CHESTPLATES;
/*     */   
/*     */   static {
/*  26 */     HELMETS = (List<EquipmentType>)Util.m_137469_(Lists.newArrayList(), helmetTypes -> {
/*     */           helmetTypes.add(new EquipmentType(Items.f_41852_, 15));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42407_, 25));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42464_, 20));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42476_, 15));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42468_, 10));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42472_, 5));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42354_, 1));
/*     */         });
/*  35 */     ADVANCED_HELMETS = (List<EquipmentType>)Util.m_137469_(Lists.newArrayList(), helmetTypes -> {
/*     */           helmetTypes.addAll(HELMETS);
/*     */           helmetTypes.add(new EquipmentType(Items.f_42407_, 5));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42464_, 10));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42476_, 15));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42468_, 20));
/*     */           helmetTypes.add(new EquipmentType(Items.f_42472_, 10));
/*     */         });
/*  43 */     CHESTPLATES = (List<EquipmentType>)Util.m_137469_(Lists.newArrayList(), chestplateTypes -> {
/*     */           chestplateTypes.add(new EquipmentType(Items.f_41852_, 15));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42408_, 25));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42465_, 20));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42477_, 15));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42469_, 10));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42473_, 5));
/*     */         });
/*  51 */     ADVANCED_CHESTPLATES = (List<EquipmentType>)Util.m_137469_(Lists.newArrayList(), chestplateTypes -> {
/*     */           chestplateTypes.addAll(CHESTPLATES);
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42408_, 5));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42465_, 10));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42477_, 15));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42469_, 20));
/*     */           chestplateTypes.add(new EquipmentType(Items.f_42473_, 10));
/*     */         });
/*  59 */     LEGGINGS = (List<EquipmentType>)Util.m_137469_(Lists.newArrayList(), leggingsTypes -> {
/*     */           leggingsTypes.add(new EquipmentType(Items.f_41852_, 15));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42462_, 25));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42466_, 20));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42478_, 15));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42470_, 10));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42474_, 5));
/*     */         });
/*  67 */     ADVANCED_LEGGINGS = (List<EquipmentType>)Util.m_137469_(Lists.newArrayList(), leggingsTypes -> {
/*     */           leggingsTypes.addAll(LEGGINGS);
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42462_, 5));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42466_, 10));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42478_, 15));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42470_, 20));
/*     */           leggingsTypes.add(new EquipmentType(Items.f_42474_, 10));
/*     */         });
/*  75 */     BOOTS = (List<EquipmentType>)Util.m_137469_(Lists.newArrayList(), bootsTypes -> {
/*     */           bootsTypes.add(new EquipmentType(Items.f_41852_, 15));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42463_, 25));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42467_, 20));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42479_, 15));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42471_, 10));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42475_, 5));
/*     */         });
/*  83 */     ADVANCED_BOOTS = (List<EquipmentType>)Util.m_137469_(Lists.newArrayList(), bootsTypes -> {
/*     */           bootsTypes.addAll(BOOTS);
/*     */           bootsTypes.add(new EquipmentType(Items.f_42463_, 5));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42467_, 15));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42479_, 10));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42471_, 20));
/*     */           bootsTypes.add(new EquipmentType(Items.f_42475_, 10));
/*     */         });
/*     */   }
/*     */   private static final List<EquipmentType> LEGGINGS; private static final List<EquipmentType> ADVANCED_LEGGINGS; private static final List<EquipmentType> BOOTS; private static final List<EquipmentType> ADVANCED_BOOTS;
/*     */   public static void applyEquipment(Monster monster, DifficultyInstance difficulty, boolean useAdvanced) {
/*  94 */     Map<EquipmentSlot, Supplier<Item>> equipmentMap = new HashMap<>();
/*  95 */     equipmentMap.put(EquipmentSlot.HEAD, () -> getRandomItemBasedOnWeight(monster.m_217043_(), useAdvanced ? ADVANCED_HELMETS : HELMETS));
/*  96 */     equipmentMap.put(EquipmentSlot.CHEST, () -> getRandomItemBasedOnWeight(monster.m_217043_(), useAdvanced ? ADVANCED_CHESTPLATES : HELMETS));
/*  97 */     equipmentMap.put(EquipmentSlot.LEGS, () -> getRandomItemBasedOnWeight(monster.m_217043_(), useAdvanced ? ADVANCED_LEGGINGS : HELMETS));
/*  98 */     equipmentMap.put(EquipmentSlot.FEET, () -> getRandomItemBasedOnWeight(monster.m_217043_(), useAdvanced ? ADVANCED_BOOTS : BOOTS));
/*  99 */     equipmentMap.forEach((equipmentSlot, itemSupplier) -> {
/*     */           if (!monster.m_21033_(equipmentSlot)) {
/*     */             ItemStack equipmentStack = new ItemStack((ItemLike)itemSupplier.get());
/*     */             int enchantmentLevel = (int)(5.0F + difficulty.m_19057_() * monster.m_217043_().m_188503_(40));
/*     */             EnchantmentHelper.m_220292_(monster.m_217043_(), equipmentStack, enchantmentLevel, false);
/*     */             monster.m_8061_(equipmentSlot, equipmentStack);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Item getRandomItemBasedOnWeight(RandomSource random, List<EquipmentType> equipment) {
/* 113 */     int rarityWeight = equipment.stream().mapToInt(EquipmentType::getRarity).sum();
/* 114 */     int randomWeight = random.m_188503_(rarityWeight);
/* 115 */     Item selectedItem = null;
/* 116 */     int cumWeight = 0;
/* 117 */     for (EquipmentType type : equipment) {
/*     */       
/* 119 */       cumWeight += type.getRarity();
/* 120 */       if (randomWeight < cumWeight) {
/*     */         
/* 122 */         selectedItem = type.getItem();
/*     */         break;
/*     */       } 
/*     */     } 
/* 126 */     return Objects.<Item>requireNonNull(selectedItem, "Could not get an item! Is the equipment list empty?");
/*     */   }
/*     */ 
/*     */   
/*     */   private static class EquipmentType
/*     */   {
/*     */     private final Item item;
/*     */     private final int rarity;
/*     */     
/*     */     public EquipmentType(Item item, int rarity) {
/* 136 */       this.item = item;
/* 137 */       this.rarity = rarity;
/*     */     }
/*     */ 
/*     */     
/*     */     public Item getItem() {
/* 142 */       return this.item;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRarity() {
/* 147 */       return this.rarity;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\EquipmentHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */