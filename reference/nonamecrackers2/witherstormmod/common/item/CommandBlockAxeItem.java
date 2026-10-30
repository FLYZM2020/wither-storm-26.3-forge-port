/*    */ package nonamecrackers2.witherstormmod.common.item;
/*    */ 
/*    */ import net.minecraft.tags.DamageTypeTags;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.item.AxeItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.Tier;
/*    */ 
/*    */ public class CommandBlockAxeItem
/*    */   extends AxeItem
/*    */ {
/*    */   public CommandBlockAxeItem(Tier tier, int damage, float attackSpeed, Item.Properties properties) {
/* 14 */     super(tier, damage, attackSpeed, properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_8120_(ItemStack stack) {
/* 20 */     return (getMaxStackSize(stack) == 1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_41386_(DamageSource source) {
/* 26 */     return source.m_269533_(DamageTypeTags.f_268738_);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\CommandBlockAxeItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */