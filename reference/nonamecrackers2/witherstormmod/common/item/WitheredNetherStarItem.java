/*    */ package nonamecrackers2.witherstormmod.common.item;
/*    */ 
/*    */ import net.minecraft.tags.DamageTypeTags;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.SimpleFoiledItem;
/*    */ 
/*    */ public class WitheredNetherStarItem
/*    */   extends SimpleFoiledItem
/*    */ {
/*    */   public WitheredNetherStarItem(Item.Properties properties) {
/* 12 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_41386_(DamageSource source) {
/* 18 */     return source.m_269533_(DamageTypeTags.f_268738_);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\WitheredNetherStarItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */