/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({PlayerItemInHandLayer.class})
/*    */ public class MixinPlayerItemInHandLayer
/*    */ {
/*    */   @Redirect(method = {"renderArmWithItem"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
/*    */   public boolean redirectIsRenderArmWithItem(ItemStack stack, Item item) {
/* 21 */     return (stack.m_150930_(item) || stack.m_150930_((Item)WitherStormModItems.PHASOMETER.get()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinPlayerItemInHandLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */