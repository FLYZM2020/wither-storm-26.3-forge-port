/*    */ package nonamecrackers2.witherstormmod.common.item;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.world.entity.monster.EnderMan;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.BlockItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraftforge.client.extensions.common.IClientItemExtensions;
/*    */ import nonamecrackers2.witherstormmod.client.util.TaintedCarvedPumpkinExtensions;
/*    */ 
/*    */ 
/*    */ public class TaintedCarvedPumpkinItem
/*    */   extends BlockItem
/*    */ {
/*    */   public TaintedCarvedPumpkinItem(Block block, Item.Properties properties) {
/* 18 */     super(block, properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
/* 24 */     consumer.accept(TaintedCarvedPumpkinExtensions.INSTANCE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
/* 30 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\TaintedCarvedPumpkinItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */