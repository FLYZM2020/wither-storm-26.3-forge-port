/*     */ package nonamecrackers2.witherstormmod.common.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.item.ItemProperties;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.UseAnim;
/*     */ import net.minecraft.world.level.Level;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ 
/*     */ 
/*     */ public class FormidiBladeItem
/*     */   extends CommandBlockSwordItem
/*     */ {
/*     */   public static final int DEFAULT_RELEASE_TIME = 40;
/*     */   public static final String POWER = "Power";
/*     */   public static final String IS_CHARGED = "IsCharged";
/*     */   private static final float POWER_DECREASE_PER_TICK = 0.2F;
/*     */   
/*     */   public FormidiBladeItem(Tier tier, int damage, float attackSpeed, Item.Properties properties) {
/*  34 */     super(tier, damage, attackSpeed, properties);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
/*  40 */     CompoundTag tag = stack.getTag();
/*  41 */     if (tag != null && tag.contains("IsCharged", 1) && !tag.getBoolean("IsCharged")) {
/*     */       
/*  43 */       float power = tag.getFloat("Power");
/*  44 */       if (power > 0.2F) {
/*     */         
/*  46 */         power -= 0.2F;
/*  47 */         tag.putFloat("Power", power);
/*     */       }
/*     */       else {
/*     */         
/*  51 */         tag.remove("Power");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
/*  59 */     ItemStack stack = player.getItemInHand(hand);
/*  60 */     if (!player.getCooldowns().isOnCooldown((Item)this)) {
/*     */       
/*  62 */       CompoundTag tag = stack.getTag();
/*  63 */       if (tag == null || !tag.contains("Power", 10) || tag.getFloat("Power") < 1.0F) {
/*     */         
/*  65 */         player.startUsingItem(hand);
/*  66 */         return InteractionResultHolder.success(stack);
/*     */       } 
/*     */     } 
/*  69 */     return InteractionResultHolder.fail(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUseDuration(ItemStack stack) {
/*  75 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UseAnim getUseAnimation(ItemStack stack) {
/*  81 */     return UseAnim.NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
/*  87 */     float power = entity.getTicksUsingItem() / 40.0F;
/*  88 */     CompoundTag tag = stack.getOrCreateTag();
/*  89 */     tag.putFloat("Power", Math.min(1.0F, power));
/*  90 */     tag.putBoolean("IsCharged", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendHoverText(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
/*  96 */     text.add(Component.translatable("item.witherstormmod.formidi_blade.author").withStyle(ChatFormatting.DARK_GRAY));
/*  97 */     text.add(Component.translatable("item.witherstormmod.formidi_blade.use").withStyle(ChatFormatting.DARK_GRAY));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
/* 103 */     return slotChanged ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : false;
/*     */   }
/*     */   
/*     */   public static float getPower(@Nullable LivingEntity entity, ItemStack stack, boolean useItemTime) {
/*     */     float chargingPower;
/* 108 */     CompoundTag tag = stack.getTag();
/*     */     
/* 110 */     if (useItemTime && entity != null) {
/* 111 */       chargingPower = Math.min(entity.getTicksUsingItem() / 40.0F, 1.0F);
/*     */     } else {
/* 113 */       chargingPower = 0.0F;
/* 114 */     }  if (tag != null && tag.contains("Power", 5)) {
/* 115 */       return Math.max(tag.getFloat("Power"), chargingPower);
/*     */     }
/* 117 */     return chargingPower;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerItemProperty() {
/* 122 */     ItemProperties.register((Item)WitherStormModItems.FORMIDI_BLADE.get(), Identifier.fromNamespaceAndPath("witherstormmod", "anim"), (stack, world, entity, i) -> (entity != null) ? getPower(entity, stack, 
/*     */           
/* 124 */           (entity.getItemInHand(InteractionHand.OFF_HAND) == stack || entity.getItemInHand(InteractionHand.MAIN_HAND) == stack)) : 0.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\FormidiBladeItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */