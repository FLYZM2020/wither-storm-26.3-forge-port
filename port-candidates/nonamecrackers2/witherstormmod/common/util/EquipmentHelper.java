/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ import com.google.common.base.Supplier;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.util.Util;
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
/*  26 */     HELMETS = (List<EquipmentType>)Util.make(Lists.newArrayList(), helmetTypes -> {
/*     */           helmetTypes.add(new EquipmentType(Items.AIR, 15));
/*     */           helmetTypes.add(new EquipmentType(Items.LEATHER_HELMET, 25));
/*     */           helmetTypes.add(new EquipmentType(Items.CHAINMAIL_HELMET, 20));
/*     */           helmetTypes.add(new EquipmentType(Items.GOLDEN_HELMET, 15));
/*     */           helmetTypes.add(new EquipmentType(Items.IRON_HELMET, 10));
/*     */           helmetTypes.add(new EquipmentType(Items.DIAMOND_HELMET, 5));
/*     */           helmetTypes.add(new EquipmentType(Items.TURTLE_HELMET, 1));
/*     */         });
/*  35 */     ADVANCED_HELMETS = (List<EquipmentType>)Util.make(Lists.newArrayList(), helmetTypes -> {
/*     */           helmetTypes.addAll(HELMETS);
/*     */           helmetTypes.add(new EquipmentType(Items.LEATHER_HELMET, 5));
/*     */           helmetTypes.add(new EquipmentType(Items.CHAINMAIL_HELMET, 10));
/*     */           helmetTypes.add(new EquipmentType(Items.GOLDEN_HELMET, 15));
/*     */           helmetTypes.add(new EquipmentType(Items.IRON_HELMET, 20));
/*     */           helmetTypes.add(new EquipmentType(Items.DIAMOND_HELMET, 10));
/*     */         });
/*  43 */     CHESTPLATES = (List<EquipmentType>)Util.make(Lists.newArrayList(), chestplateTypes -> {
/*     */           chestplateTypes.add(new EquipmentType(Items.AIR, 15));
/*     */           chestplateTypes.add(new EquipmentType(Items.LEATHER_CHESTPLATE, 25));
/*     */           chestplateTypes.add(new EquipmentType(Items.CHAINMAIL_CHESTPLATE, 20));
/*     */           chestplateTypes.add(new EquipmentType(Items.GOLDEN_CHESTPLATE, 15));
/*     */           chestplateTypes.add(new EquipmentType(Items.IRON_CHESTPLATE, 10));
/*     */           chestplateTypes.add(new EquipmentType(Items.DIAMOND_CHESTPLATE, 5));
/*     */         });
/*  51 */     ADVANCED_CHESTPLATES = (List<EquipmentType>)Util.make(Lists.newArrayList(), chestplateTypes -> {
/*     */           chestplateTypes.addAll(CHESTPLATES);
/*     */           chestplateTypes.add(new EquipmentType(Items.LEATHER_CHESTPLATE, 5));
/*     */           chestplateTypes.add(new EquipmentType(Items.CHAINMAIL_CHESTPLATE, 10));
/*     */           chestplateTypes.add(new EquipmentType(Items.GOLDEN_CHESTPLATE, 15));
/*     */           chestplateTypes.add(new EquipmentType(Items.IRON_CHESTPLATE, 20));
/*     */           chestplateTypes.add(new EquipmentType(Items.DIAMOND_CHESTPLATE, 10));
/*     */         });
/*  59 */     LEGGINGS = (List<EquipmentType>)Util.make(Lists.newArrayList(), leggingsTypes -> {
/*     */           leggingsTypes.add(new EquipmentType(Items.AIR, 15));
/*     */           leggingsTypes.add(new EquipmentType(Items.LEATHER_LEGGINGS, 25));
/*     */           leggingsTypes.add(new EquipmentType(Items.CHAINMAIL_LEGGINGS, 20));
/*     */           leggingsTypes.add(new EquipmentType(Items.GOLDEN_LEGGINGS, 15));
/*     */           leggingsTypes.add(new EquipmentType(Items.IRON_LEGGINGS, 10));
/*     */           leggingsTypes.add(new EquipmentType(Items.DIAMOND_LEGGINGS, 5));
/*     */         });
/*  67 */     ADVANCED_LEGGINGS = (List<EquipmentType>)Util.make(Lists.newArrayList(), leggingsTypes -> {
/*     */           leggingsTypes.addAll(LEGGINGS);
/*     */           leggingsTypes.add(new EquipmentType(Items.LEATHER_LEGGINGS, 5));
/*     */           leggingsTypes.add(new EquipmentType(Items.CHAINMAIL_LEGGINGS, 10));
/*     */           leggingsTypes.add(new EquipmentType(Items.GOLDEN_LEGGINGS, 15));
/*     */           leggingsTypes.add(new EquipmentType(Items.IRON_LEGGINGS, 20));
/*     */           leggingsTypes.add(new EquipmentType(Items.DIAMOND_LEGGINGS, 10));
/*     */         });
/*  75 */     BOOTS = (List<EquipmentType>)Util.make(Lists.newArrayList(), bootsTypes -> {
/*     */           bootsTypes.add(new EquipmentType(Items.AIR, 15));
/*     */           bootsTypes.add(new EquipmentType(Items.LEATHER_BOOTS, 25));
/*     */           bootsTypes.add(new EquipmentType(Items.CHAINMAIL_BOOTS, 20));
/*     */           bootsTypes.add(new EquipmentType(Items.GOLDEN_BOOTS, 15));
/*     */           bootsTypes.add(new EquipmentType(Items.IRON_BOOTS, 10));
/*     */           bootsTypes.add(new EquipmentType(Items.DIAMOND_BOOTS, 5));
/*     */         });
/*  83 */     ADVANCED_BOOTS = (List<EquipmentType>)Util.make(Lists.newArrayList(), bootsTypes -> {
/*     */           bootsTypes.addAll(BOOTS);
/*     */           bootsTypes.add(new EquipmentType(Items.LEATHER_BOOTS, 5));
/*     */           bootsTypes.add(new EquipmentType(Items.CHAINMAIL_BOOTS, 15));
/*     */           bootsTypes.add(new EquipmentType(Items.GOLDEN_BOOTS, 10));
/*     */           bootsTypes.add(new EquipmentType(Items.IRON_BOOTS, 20));
/*     */           bootsTypes.add(new EquipmentType(Items.DIAMOND_BOOTS, 10));
/*     */         });
/*     */   }
/*     */   private static final List<EquipmentType> LEGGINGS; private static final List<EquipmentType> ADVANCED_LEGGINGS; private static final List<EquipmentType> BOOTS; private static final List<EquipmentType> ADVANCED_BOOTS;
/*     */   public static void applyEquipment(Monster monster, DifficultyInstance difficulty, boolean useAdvanced) {
/*  94 */     Map<EquipmentSlot, Supplier<Item>> equipmentMap = new HashMap<>();
/*  95 */     equipmentMap.put(EquipmentSlot.HEAD, () -> getRandomItemBasedOnWeight(monster.getRandom(), useAdvanced ? ADVANCED_HELMETS : HELMETS));
/*  96 */     equipmentMap.put(EquipmentSlot.CHEST, () -> getRandomItemBasedOnWeight(monster.getRandom(), useAdvanced ? ADVANCED_CHESTPLATES : HELMETS));
/*  97 */     equipmentMap.put(EquipmentSlot.LEGS, () -> getRandomItemBasedOnWeight(monster.getRandom(), useAdvanced ? ADVANCED_LEGGINGS : HELMETS));
/*  98 */     equipmentMap.put(EquipmentSlot.FEET, () -> getRandomItemBasedOnWeight(monster.getRandom(), useAdvanced ? ADVANCED_BOOTS : BOOTS));
/*  99 */     equipmentMap.forEach((equipmentSlot, itemSupplier) -> {
/*     */           if (!monster.hasItemInSlot(equipmentSlot)) {
/*     */             ItemStack equipmentStack = new ItemStack((ItemLike)itemSupplier.get());
/*     */             int enchantmentLevel = (int)(5.0F + difficulty.getSpecialMultiplier() * monster.getRandom().nextInt(40));
/*     */             EnchantmentHelper.enchantItem(monster.getRandom(), equipmentStack, enchantmentLevel, false);
/*     */             monster.setItemSlot(equipmentSlot, equipmentStack);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Item getRandomItemBasedOnWeight(RandomSource random, List<EquipmentType> equipment) {
/* 113 */     int rarityWeight = equipment.stream().mapToInt(EquipmentType::getRarity).sum();
/* 114 */     int randomWeight = random.nextInt(rarityWeight);
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