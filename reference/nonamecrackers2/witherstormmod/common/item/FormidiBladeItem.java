/*     */ package nonamecrackers2.witherstormmod.common.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.item.ItemProperties;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
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
/*     */   public void m_6883_(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
/*  40 */     CompoundTag tag = stack.m_41783_();
/*  41 */     if (tag != null && tag.m_128425_("IsCharged", 1) && !tag.m_128471_("IsCharged")) {
/*     */       
/*  43 */       float power = tag.m_128457_("Power");
/*  44 */       if (power > 0.2F) {
/*     */         
/*  46 */         power -= 0.2F;
/*  47 */         tag.m_128350_("Power", power);
/*     */       }
/*     */       else {
/*     */         
/*  51 */         tag.m_128473_("Power");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand hand) {
/*  59 */     ItemStack stack = player.m_21120_(hand);
/*  60 */     if (!player.m_36335_().m_41519_((Item)this)) {
/*     */       
/*  62 */       CompoundTag tag = stack.m_41783_();
/*  63 */       if (tag == null || !tag.m_128425_("Power", 10) || tag.m_128457_("Power") < 1.0F) {
/*     */         
/*  65 */         player.m_6672_(hand);
/*  66 */         return InteractionResultHolder.m_19090_(stack);
/*     */       } 
/*     */     } 
/*  69 */     return InteractionResultHolder.m_19100_(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_8105_(ItemStack stack) {
/*  75 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UseAnim m_6164_(ItemStack stack) {
/*  81 */     return UseAnim.NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
/*  87 */     float power = entity.m_21252_() / 40.0F;
/*  88 */     CompoundTag tag = stack.m_41784_();
/*  89 */     tag.m_128350_("Power", Math.min(1.0F, power));
/*  90 */     tag.m_128379_("IsCharged", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7373_(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
/*  96 */     text.add(Component.m_237115_("item.witherstormmod.formidi_blade.author").m_130940_(ChatFormatting.DARK_GRAY));
/*  97 */     text.add(Component.m_237115_("item.witherstormmod.formidi_blade.use").m_130940_(ChatFormatting.DARK_GRAY));
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
/* 108 */     CompoundTag tag = stack.m_41783_();
/*     */     
/* 110 */     if (useItemTime && entity != null) {
/* 111 */       chargingPower = Math.min(entity.m_21252_() / 40.0F, 1.0F);
/*     */     } else {
/* 113 */       chargingPower = 0.0F;
/* 114 */     }  if (tag != null && tag.m_128425_("Power", 5)) {
/* 115 */       return Math.max(tag.m_128457_("Power"), chargingPower);
/*     */     }
/* 117 */     return chargingPower;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerItemProperty() {
/* 122 */     ItemProperties.register((Item)WitherStormModItems.FORMIDI_BLADE.get(), new ResourceLocation("witherstormmod", "anim"), (stack, world, entity, i) -> (entity != null) ? getPower(entity, stack, 
/*     */           
/* 124 */           (entity.m_21120_(InteractionHand.OFF_HAND) == stack || entity.m_21120_(InteractionHand.MAIN_HAND) == stack)) : 0.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\FormidiBladeItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */